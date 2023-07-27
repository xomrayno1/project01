package com.tamnc.domain.ports.output.message.publisher.payment;

import com.tamnc.domain.event.OrderCancelledEvent;
import com.tamnc.event.publisher.DomainEventPublisher;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent>{
	

}
