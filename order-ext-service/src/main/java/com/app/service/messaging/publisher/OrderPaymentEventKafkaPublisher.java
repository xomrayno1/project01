package com.app.service.messaging.publisher;

import java.util.function.BiConsumer;

import org.springframework.stereotype.Component;

import com.app.common.OrderMessagingDataMapper;
import com.app.common.OrderServiceConfigData;
import com.app.enums.OutboxStatus;
import com.app.kafka.model.PaymentRequestAvroModel;
import com.app.kafka.producer.KafkaMessageHelper;
import com.app.kafka.producer.KafkaProducer;
import com.app.model.event.publisher.PaymentRequestMessagePublisher;
import com.app.model.outbox.OrderPaymentEventPayload;
import com.app.model.outbox.OrderPaymentOutboxMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderPaymentEventKafkaPublisher implements PaymentRequestMessagePublisher {

	private final OrderMessagingDataMapper orderMessagingDataMapper;
	private final KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;
	private final OrderServiceConfigData orderServiceConfigData;
	private final KafkaMessageHelper kafkaMessageHelper;

	public OrderPaymentEventKafkaPublisher(OrderMessagingDataMapper orderMessagingDataMapper,
			KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer, OrderServiceConfigData orderServiceConfigData,
			KafkaMessageHelper kafkaMessageHelper) {
		this.orderMessagingDataMapper = orderMessagingDataMapper;
		this.kafkaProducer = kafkaProducer;
		this.orderServiceConfigData = orderServiceConfigData;
		this.kafkaMessageHelper = kafkaMessageHelper;
	}

	@Override
	public void publish(OrderPaymentOutboxMessage orderPaymentOutboxMessage,
			BiConsumer<OrderPaymentOutboxMessage, OutboxStatus> outboxCallback) {
		OrderPaymentEventPayload orderPaymentEventPayload = kafkaMessageHelper
				.getOrderEventPayload(orderPaymentOutboxMessage.getPayload(), OrderPaymentEventPayload.class);

		String sagaId = orderPaymentOutboxMessage.getSagaId().toString();

		log.info("Received OrderPaymentOutboxMessage for order id: {} and saga id: {}",
				orderPaymentEventPayload.getOrderId(), sagaId);

		try {
			PaymentRequestAvroModel paymentRequestAvroModel = orderMessagingDataMapper
					.orderPaymentEventToPaymentRequestAvroModel(sagaId, orderPaymentEventPayload);

			kafkaProducer.send(orderServiceConfigData.getPaymentRequestTopicName(), sagaId, paymentRequestAvroModel,
					kafkaMessageHelper.getKafkaCallback(orderServiceConfigData.getPaymentRequestTopicName(),
							paymentRequestAvroModel, orderPaymentOutboxMessage, outboxCallback,
							orderPaymentEventPayload.getOrderId(), "PaymentRequestAvroModel"));

			log.info("OrderPaymentEventPayload sent to Kafka for order id: {} and saga id: {}",
					orderPaymentEventPayload.getOrderId(), sagaId);
		} catch (Exception e) {
			log.error(
					"Error while sending OrderPaymentEventPayload"
							+ " to kafka with order id: {} and saga id: {}, error: {}",
					orderPaymentEventPayload.getOrderId(), sagaId, e.getMessage());
		}

	}
}