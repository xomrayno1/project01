package com.tamnc.domain.ports.output.message.publisher.payment;

import com.tamnc.domain.event.OrderCreatedEvent;
import com.tamnc.event.publisher.DomainEventPublisher;

public interface OrderCreatedPaymentRequestMessage extends DomainEventPublisher<OrderCreatedEvent>{
	

}
