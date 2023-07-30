package com.app.service.impl;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.app.common.OrderDataMapper;
import com.app.entity.Order;
import com.app.enums.OrderStatus;
import com.app.enums.OutboxStatus;
import com.app.enums.PaymentStatus;
import com.app.enums.SagaStatus;
import com.app.exception.OrderDomainException;
import com.app.exception.OrderNotFoundException;
import com.app.model.event.OrderPaidEvent;
import com.app.model.outbox.OrderApprovalOutboxMessage;
import com.app.model.outbox.OrderPaymentOutboxMessage;
import com.app.model.response.PaymentResponse;
import com.app.repository.OrderRepository;
import com.app.service.OrderDomainService;
import com.app.service.SagaStep;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderPaymentSaga implements SagaStep<PaymentResponse> {

	public static final String UTC = "UTC";

	private final OrderDomainService orderDomainService;
	private final OrderRepository orderRepository;
	private final PaymentOutboxHelper paymentOutboxHelper;
	private final ApprovalOutboxHelper approvalOutboxHelper;
	private final OrderSagaHelper orderSagaHelper;
	private final OrderDataMapper orderDataMapper;

	public OrderPaymentSaga(OrderDomainService orderDomainService, OrderRepository orderRepository,
			PaymentOutboxHelper paymentOutboxHelper, ApprovalOutboxHelper approvalOutboxHelper,
			OrderSagaHelper orderSagaHelper, OrderDataMapper orderDataMapper) {
		this.orderDomainService = orderDomainService;
		this.orderRepository = orderRepository;
		this.paymentOutboxHelper = paymentOutboxHelper;
		this.approvalOutboxHelper = approvalOutboxHelper;
		this.orderSagaHelper = orderSagaHelper;
		this.orderDataMapper = orderDataMapper;
	}

	@Override
	@Transactional
	public void process(PaymentResponse paymentResponse) {
		Optional<OrderPaymentOutboxMessage> orderPaymentOutboxMessageResponse = paymentOutboxHelper
				.getPaymentOutboxMessageBySagaIdAndSagaStatus(UUID.fromString(paymentResponse.getSagaId()),
						SagaStatus.STARTED);

		if (orderPaymentOutboxMessageResponse.isEmpty()) {
			log.info("An outbox message with saga id: {} is already processed!", paymentResponse.getSagaId());
			return;
		}

		OrderPaymentOutboxMessage orderPaymentOutboxMessage = orderPaymentOutboxMessageResponse.get();

		OrderPaidEvent domainEvent = completePaymentForOrder(paymentResponse);

		SagaStatus sagaStatus = orderSagaHelper.orderStatusToSagaStatus(domainEvent.getOrder().getOrderStatus());

		paymentOutboxHelper.save(getUpdatedPaymentOutboxMessage(orderPaymentOutboxMessage,
				domainEvent.getOrder().getOrderStatus(), sagaStatus));

		approvalOutboxHelper.saveApprovalOutboxMessage(
				orderDataMapper.orderPaidEventToOrderApprovalEventPayload(domainEvent),
				domainEvent.getOrder().getOrderStatus(), sagaStatus, OutboxStatus.STARTED,
				UUID.fromString(paymentResponse.getSagaId()));

		log.info("Order with id: {} is paid", domainEvent.getOrder().getId());
	}

	@Override
	@Transactional
	public void rollback(PaymentResponse paymentResponse) {

		Optional<OrderPaymentOutboxMessage> orderPaymentOutboxMessageResponse = paymentOutboxHelper
				.getPaymentOutboxMessageBySagaIdAndSagaStatus(UUID.fromString(paymentResponse.getSagaId()),
						getCurrentSagaStatus(paymentResponse.getPaymentStatus()));

		if (orderPaymentOutboxMessageResponse.isEmpty()) {
			log.info("An outbox message with saga id: {} is already roll backed!", paymentResponse.getSagaId());
			return;
		}

		OrderPaymentOutboxMessage orderPaymentOutboxMessage = orderPaymentOutboxMessageResponse.get();

		Order order = rollbackPaymentForOrder(paymentResponse);

		SagaStatus sagaStatus = orderSagaHelper.orderStatusToSagaStatus(order.getOrderStatus());

		paymentOutboxHelper
				.save(getUpdatedPaymentOutboxMessage(orderPaymentOutboxMessage, order.getOrderStatus(), sagaStatus));

		if (paymentResponse.getPaymentStatus().equals(PaymentStatus.CANCELLED)) {
			approvalOutboxHelper.save(
					getUpdatedApprovalOutboxMessage(paymentResponse.getSagaId(), order.getOrderStatus(), sagaStatus));
		}

		log.info("Order with id: {} is cancelled", order.getId());
	}

	private Order findOrder(String orderId) {
		Optional<Order> orderResponse = orderRepository.findById(UUID.fromString(orderId));
		if (orderResponse.isEmpty()) {
			log.error("Order with id: {} could not be found!", orderId);
			throw new OrderNotFoundException("Order with id " + orderId + " could not be found!");
		}
		return orderResponse.get();
	}

	private OrderPaymentOutboxMessage getUpdatedPaymentOutboxMessage(
			OrderPaymentOutboxMessage orderPaymentOutboxMessage, OrderStatus orderStatus, SagaStatus sagaStatus) {
		orderPaymentOutboxMessage.setProcessedAt(ZonedDateTime.now(ZoneId.of(UTC)));
		orderPaymentOutboxMessage.setOrderStatus(orderStatus);
		orderPaymentOutboxMessage.setSagaStatus(sagaStatus);
		return orderPaymentOutboxMessage;
	}

	private OrderPaidEvent completePaymentForOrder(PaymentResponse paymentResponse) {
		log.info("Completing payment for order with id: {}", paymentResponse.getOrderId());
		Order order = findOrder(paymentResponse.getOrderId());
		OrderPaidEvent domainEvent = orderDomainService.payOrder(order);
		orderRepository.save(order);
		return domainEvent;
	}

	private SagaStatus[] getCurrentSagaStatus(PaymentStatus paymentStatus) {
		return switch (paymentStatus) {
		case COMPLETED -> new SagaStatus[] { SagaStatus.STARTED };
		case CANCELLED -> new SagaStatus[] { SagaStatus.PROCESSING };
		case FAILED -> new SagaStatus[] { SagaStatus.STARTED, SagaStatus.PROCESSING };
		};
	}

	private Order rollbackPaymentForOrder(PaymentResponse paymentResponse) {
		log.info("Cancelling order with id: {}", paymentResponse.getOrderId());
		Order order = findOrder(paymentResponse.getOrderId());
		orderDomainService.cancelOrder(order, paymentResponse.getFailureMessages());
		orderRepository.save(order);
		return order;
	}

	private OrderApprovalOutboxMessage getUpdatedApprovalOutboxMessage(String sagaId, OrderStatus orderStatus,
			SagaStatus sagaStatus) {
		Optional<OrderApprovalOutboxMessage> orderApprovalOutboxMessageResponse = approvalOutboxHelper
				.getApprovalOutboxMessageBySagaIdAndSagaStatus(UUID.fromString(sagaId), SagaStatus.COMPENSATING);
		if (orderApprovalOutboxMessageResponse.isEmpty()) {
			throw new OrderDomainException(
					"Approval outbox message could not be found in " + SagaStatus.COMPENSATING.name() + " status!");
		}
		OrderApprovalOutboxMessage orderApprovalOutboxMessage = orderApprovalOutboxMessageResponse.get();
		orderApprovalOutboxMessage.setProcessedAt(ZonedDateTime.now(ZoneId.of(UTC)));
		orderApprovalOutboxMessage.setOrderStatus(orderStatus);
		orderApprovalOutboxMessage.setSagaStatus(sagaStatus);
		return orderApprovalOutboxMessage;
	}
}