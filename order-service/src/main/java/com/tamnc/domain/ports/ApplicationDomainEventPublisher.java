package com.tamnc.domain.ports;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import com.tamnc.domain.event.OrderCreatedEvent;
import com.tamnc.event.publisher.DomainEventPublisher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApplicationDomainEventPublisher implements ApplicationEventPublisherAware, DomainEventPublisher<OrderCreatedEvent>{
	
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}
	
	@Override
	public void publish(OrderCreatedEvent orderCreatedEvent) {
		this.applicationEventPublisher.publishEvent(orderCreatedEvent);
		log.info("OrderCreatedEvent is publisher for order id {} ", orderCreatedEvent.getOrder().getId().getValue());
	}
 
}
