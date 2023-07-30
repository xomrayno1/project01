package com.app.model.event.publisher;

import com.app.model.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {
	void publish(T domainEvent);

}
