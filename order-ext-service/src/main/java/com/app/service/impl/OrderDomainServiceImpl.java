package com.app.service.impl;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.entity.Order;
import com.app.entity.Product;
import com.app.entity.Restaurant;
import com.app.exception.OrderDomainException;
import com.app.model.event.OrderCancelledEvent;
import com.app.model.event.OrderCreatedEvent;
import com.app.model.event.OrderPaintEvent;
import com.app.service.OrderDomainService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderDomainServiceImpl implements OrderDomainService {
	
	private static final String UTC = "UTC";

	@Override
	public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
		validateRestaurant(restaurant);
		setOrderProductInfomation(order, restaurant);
		order.validateOrder();
		order.initializeOrder();
		log.info("Order with id: {} is initital " , order.getId());
		return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
	}

	@Override
	public OrderPaintEvent payOrder(Order order) {
		order.pay();
		log.info("Order with id : {} is paid ", order.getId());
		return new OrderPaintEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
	}

	@Override
	public void aprroveOrder(Order order) {
		order.aprrove();
		log.info("Order with id : {} is aprroved ", order.getId());
	}

	@Override
	public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessage) {
		order.initCancel(failureMessage);
		log.info("Order payment is cancelling for order id : {} ", order.getId());
		return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
	}

	@Override
	public void cancelOrder(Order order, List<String> failureMessage) {
		order.cancel(failureMessage);
		log.info("Order with id : {} is cancelled ", order.getId());
	}
	
	public void validateRestaurant(Restaurant restaurant) {
		if(!restaurant.isActive()) {
			throw new OrderDomainException("Restaurant with id " + restaurant.getId() + " is currently not active! ");
		}
	}
	
	public void setOrderProductInfomation(Order order, Restaurant restaurant) {
		order.getItems().forEach(orderItem -> restaurant.getProducts().forEach(restaurantProduct -> {
			Product currentProduct = orderItem.getProduct();
			if(currentProduct.equals(restaurantProduct)) {
				currentProduct.updateWithConfirmedNameAndPrice(restaurantProduct.getName(), restaurantProduct.getPrice());
			}
		}));
	}

}
