package com.tamnc.domain.event;

import java.time.ZonedDateTime;

import com.tamnc.domain.entity.Order;

import lombok.Getter;

@Getter
public class OrderCancelledEvent extends OrderEvent{
	public OrderCancelledEvent(Order order, ZonedDateTime createAt) {
		super(order, createAt);
	}
}
