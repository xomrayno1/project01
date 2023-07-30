package com.app.service.messaging.publisher;

import java.util.function.BiConsumer;

import com.app.enums.OutboxStatus;
import com.app.model.outbox.OrderPaymentOutboxMessage;

public interface PaymentRequestMessagePublisher {
	
	  void publish(OrderPaymentOutboxMessage orderPaymentOutboxMessage,
              BiConsumer<OrderPaymentOutboxMessage, OutboxStatus> outboxCallback);
	  
}
