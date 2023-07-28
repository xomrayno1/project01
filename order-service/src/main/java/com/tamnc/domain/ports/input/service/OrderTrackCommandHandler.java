package com.tamnc.domain.ports.input.service;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tamnc.domain.entity.Order;
import com.tamnc.domain.exception.OrderNotFoundException;
import com.tamnc.domain.mapper.OrderDataMapper;
import com.tamnc.domain.objects.TrackingId;
import com.tamnc.domain.ports.output.repository.OrderRepository;
import com.tamnc.service.track.TrackOrderQuery;
import com.tamnc.service.track.TrackOrderResponse;

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
		Optional<Order> orderOptional = orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));
		if(orderOptional.isEmpty()) {
			log.warn("Could not find order with tracking id: {} ", trackOrderQuery.getOrderTrackingId());
			throw new OrderNotFoundException("Could not find order with tracking id: " + trackOrderQuery.getOrderTrackingId());
		}
		return orderDataMapper.orderToTrackOrderResponse(orderOptional.get());
	}
	
}
