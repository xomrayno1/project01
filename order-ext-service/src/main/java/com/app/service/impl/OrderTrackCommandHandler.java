package com.app.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.app.common.OrderDataMapper;
import com.app.entity.Order;
import com.app.exception.OrderNotFoundException;
import com.app.model.request.TrackOrderQuery;
import com.app.model.response.TrackOrderResponse;
import com.app.repository.OrderRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class OrderTrackCommandHandler {
	
	private final OrderDataMapper orderDataMapper;
	
	private final OrderRepository orderRepository;

	@Transactional(readOnly = true)
	public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
		Optional<Order> orderOptional = orderRepository.findByTrackingId(trackOrderQuery.getOrderTrackingId());
		if(orderOptional.isEmpty()) {
			log.warn("Could not find order with tracking id: {} ", trackOrderQuery.getOrderTrackingId());
			throw new OrderNotFoundException("Could not find order with tracking id: " + trackOrderQuery.getOrderTrackingId());
		}
		return orderDataMapper.orderToTrackOrderResponse(orderOptional.get());
	}
	
}
