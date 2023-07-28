package com.tamnc.domain.ports;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.tamnc.domain.event.OrderCreatedEvent;
import com.tamnc.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;

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
