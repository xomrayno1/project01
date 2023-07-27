package com.tamnc.domain.ports.output.repository;

import java.util.Optional;

import com.tamnc.domain.entity.Order;
import com.tamnc.domain.objects.TrackingId;

public interface OrderRepository {

	Order save(Order order);
	
	Optional<Order> findByTrackingId(TrackingId trackingId);
}
