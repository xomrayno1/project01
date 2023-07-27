package com.tamnc.domain.event;

import java.time.ZonedDateTime;

import com.tamnc.domain.entity.Order;

import lombok.Getter;
 
@Getter
public class OrderCreatedEvent extends OrderEvent{

	public OrderCreatedEvent(Order order, ZonedDateTime createAt) {
		super(order, createAt);
	}
	 
}
