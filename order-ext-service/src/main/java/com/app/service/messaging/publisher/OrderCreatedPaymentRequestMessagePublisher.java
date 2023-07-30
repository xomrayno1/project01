package com.app.service.messaging.publisher;

import com.app.model.event.OrderCreatedEvent;
import com.app.service.messaging.listener.DomainEventPublisher;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent>{
	

}
