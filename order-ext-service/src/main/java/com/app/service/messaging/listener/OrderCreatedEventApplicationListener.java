package com.app.service.messaging.listener;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.app.model.event.OrderCreatedEvent;
import com.app.service.messaging.publisher.OrderCreatedPaymentRequestMessagePublisher;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCreatedEventApplicationListener {
	private final OrderCreatedPaymentRequestMessagePublisher  orderCreatedPaymentRequestMessage;
	
	@TransactionalEventListener
	void process(OrderCreatedEvent orderCreatedEvent) {
		
	}
}
