package com.tamnc.event.publisher;

import com.tamnc.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {
	void publish(T domainEvent);

}
