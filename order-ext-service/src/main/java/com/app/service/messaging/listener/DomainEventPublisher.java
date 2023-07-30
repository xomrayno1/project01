package com.app.service.messaging.listener;

import com.app.model.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {
	void publish(T domainEvent);

}
