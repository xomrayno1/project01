package com.app.model.event;

import java.time.ZonedDateTime;

import com.app.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class OrderEvent implements DomainEvent<Order>{
	private final Order order;
	private final ZonedDateTime createAt;
}
