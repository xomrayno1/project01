package com.app.model.event;

import java.time.ZonedDateTime;

import com.app.entity.Order;

import lombok.Getter;

@Getter
public class OrderPaidEvent extends OrderEvent{

	public OrderPaidEvent(Order order, ZonedDateTime createAt) {
		super(order, createAt);
	}
 
}
