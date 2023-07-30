package com.app.common;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.app.enums.OrderApprovalStatus;
import com.app.enums.PaymentStatus;
import com.app.kafka.model.CustomerAvroModel;
import com.app.kafka.model.PaymentOrderStatus;
import com.app.kafka.model.PaymentRequestAvroModel;
import com.app.kafka.model.PaymentResponseAvroModel;
import com.app.kafka.model.Product;
import com.app.kafka.model.RestaurantApprovalRequestAvroModel;
import com.app.kafka.model.RestaurantApprovalResponseAvroModel;
import com.app.kafka.model.RestaurantOrderStatus;
import com.app.model.dto.CustomerModel;
import com.app.model.outbox.OrderApprovalEventPayload;
import com.app.model.outbox.OrderPaymentEventPayload;
import com.app.model.response.PaymentResponse;
import com.app.model.response.RestaurantApprovalResponse;

@Component
public class OrderMessagingDataMapper {
	public PaymentResponse paymentResponseAvroModelToPaymentResponse(
			PaymentResponseAvroModel paymentResponseAvroModel) {
		return PaymentResponse.builder().id(paymentResponseAvroModel.getId())
				.sagaId(paymentResponseAvroModel.getSagaId()).paymentId(paymentResponseAvroModel.getPaymentId())
				.customerId(paymentResponseAvroModel.getCustomerId()).orderId(paymentResponseAvroModel.getOrderId())
				.price(paymentResponseAvroModel.getPrice())
				//.createdAt(paymentResponseAvroModel.getCreatedAt())
				.paymentStatus(PaymentStatus
						.valueOf(paymentResponseAvroModel.getPaymentStatus().name()))
				.failureMessages(paymentResponseAvroModel.getFailureMessages())
				.build();
	}

	public RestaurantApprovalResponse approvalResponseAvroModelToApprovalResponse(
			RestaurantApprovalResponseAvroModel restaurantApprovalResponseAvroModel) {
		return RestaurantApprovalResponse.builder().id(restaurantApprovalResponseAvroModel.getId())
				.sagaId(restaurantApprovalResponseAvroModel.getSagaId())
				.restaurantId(restaurantApprovalResponseAvroModel.getRestaurantId())
				.orderId(restaurantApprovalResponseAvroModel.getOrderId())
				.createdAt(restaurantApprovalResponseAvroModel.getCreatedAt())
				.orderApprovalStatus(OrderApprovalStatus
						.valueOf(restaurantApprovalResponseAvroModel.getOrderApprovalStatus().name()))
				.failureMessages(restaurantApprovalResponseAvroModel.getFailureMessages()).build();
	}

	public PaymentRequestAvroModel orderPaymentEventToPaymentRequestAvroModel(String sagaId,
			OrderPaymentEventPayload orderPaymentEventPayload) {
		return PaymentRequestAvroModel.builder().id(UUID.randomUUID().toString()).sagaId(sagaId)
				.customerId(orderPaymentEventPayload.getCustomerId())
				.orderId(orderPaymentEventPayload.getOrderId()).price(orderPaymentEventPayload.getPrice())
				.createdAt(orderPaymentEventPayload.getCreatedAt().toInstant())
				.paymentOrderStatus(PaymentOrderStatus.valueOf(orderPaymentEventPayload.getPaymentOrderStatus()))
				.build();
	}

	public RestaurantApprovalRequestAvroModel orderApprovalEventToRestaurantApprovalRequestAvroModel(String sagaId,
			OrderApprovalEventPayload orderApprovalEventPayload) {
		return RestaurantApprovalRequestAvroModel.builder().id(UUID.randomUUID().toString()).sagaId(sagaId)
				.orderId(orderApprovalEventPayload.getOrderId())
				.restaurantId(orderApprovalEventPayload.getRestaurantId())
				.restaurantOrderStatus(
						RestaurantOrderStatus.valueOf(orderApprovalEventPayload.getRestaurantOrderStatus()))
				.products(orderApprovalEventPayload.getProducts().stream()
						.map(orderApprovalEventProduct -> Product.builder()
								.id(orderApprovalEventProduct.getId())
								.quantity(orderApprovalEventProduct.getQuantity()).build())
						.collect(Collectors.toList()))
				.price(orderApprovalEventPayload.getPrice())
				.createdAt(orderApprovalEventPayload.getCreatedAt().toInstant()).build();
	}

	public CustomerModel customerAvroModeltoCustomerModel(CustomerAvroModel customerAvroModel) {
		return CustomerModel.builder().id(customerAvroModel.getId()).username(customerAvroModel.getUsername())
				.firstName(customerAvroModel.getFirstName()).lastName(customerAvroModel.getLastName()).build();
	}
}
