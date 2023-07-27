package com.tamnc.domain.event;

import java.time.ZonedDateTime;

import com.tamnc.domain.entity.Order;
import com.tamnc.event.DomainEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class OrderEvent implements DomainEvent<Order>{
	private final Order order;
	private final ZonedDateTime createAt;
}
