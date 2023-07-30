package com.app.repository;

import java.util.Optional;
import java.util.UUID;

import com.app.entity.Order;

public interface OrderRepository {
	Order save(Order order);
	
	Optional<Order> findByTrackingId(UUID trackingId);
}
