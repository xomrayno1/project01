package com.app.model.event;

import java.time.ZonedDateTime;

import com.app.entity.Order;

import lombok.Getter;
 
@Getter
public class OrderCreatedEvent extends OrderEvent{

	public OrderCreatedEvent(Order order, ZonedDateTime createAt) {
		super(order, createAt);
	}
	 
}
