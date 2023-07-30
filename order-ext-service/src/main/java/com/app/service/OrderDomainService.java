package com.app.service;

import java.util.List;

import com.app.entity.Order;
import com.app.entity.Restaurant;
import com.app.model.event.OrderCancelledEvent;
import com.app.model.event.OrderCreatedEvent;
import com.app.model.event.OrderPaidEvent;

public interface OrderDomainService {
	
	OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);
	
	OrderPaidEvent payOrder(Order order);
	
	void aprroveOrder(Order order);
	
	OrderCancelledEvent	cancelOrderPayment(Order order, List<String> failureMessage);
	
	void cancelOrder(Order order, List<String> failureMessage);

}
