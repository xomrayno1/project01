package com.app.service.messaging.publisher;

import com.app.model.event.OrderCancelledEvent;
import com.app.service.messaging.listener.DomainEventPublisher;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent>{
	

}
