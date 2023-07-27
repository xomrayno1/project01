package com.tamnc.domain.service;

import java.util.List;

import com.tamnc.domain.entity.Order;
import com.tamnc.domain.entity.Restaurant;
import com.tamnc.domain.event.OrderCancelledEvent;
import com.tamnc.domain.event.OrderCreatedEvent;
import com.tamnc.domain.event.OrderPaintEvent;

public interface OrderDomainService {
	
	OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);
	
	OrderPaintEvent payOrder(Order order);
	
	void aprroveOrder(Order order);
	
	OrderCancelledEvent	cancelOrderPayment(Order order, List<String> failureMessage);
	
	void cancelOrder(Order order, List<String> failureMessage);

}
