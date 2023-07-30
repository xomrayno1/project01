package com.app.model.event.publisher;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.app.model.event.OrderCreatedEvent;

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
