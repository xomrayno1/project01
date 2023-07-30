package com.tamnc.rest;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tamnc.domain.ports.input.service.OrderApplicationService;
import com.tamnc.service.create.CreateOrderCommand;
import com.tamnc.service.create.CreateOrderResponse;
import com.tamnc.service.track.TrackOrderQuery;
import com.tamnc.service.track.TrackOrderResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/orders", produces = "application/vnd.api.v1+json")
@AllArgsConstructor
public class OrderController {
	private final OrderApplicationService orderApplicationService;
	
	@PostMapping
	public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderCommand createOrderCommand){
		log.info("Creating order for customer: {} at restaurant: {}", createOrderCommand.getCustomerId(), createOrderCommand.getRestaurantId());
		CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
		log.info("Order created with tracking id: {}", createOrderResponse.getOrderTrackingId());
		return ResponseEntity.ok(createOrderResponse);
	}
	
	@GetMapping("/{trackingId}")
	public ResponseEntity<TrackOrderResponse> getOrderByTrackingId(@PathVariable UUID trackingId){
		TrackOrderResponse trackOrderResponse = orderApplicationService.trackOrder(
				TrackOrderQuery.builder().orderTrackingId(trackingId).build()
			);
		log.info("Returning order status with tracking id: {}", trackOrderResponse.getOrderTrackingId());
		return ResponseEntity.ok(trackOrderResponse);
	}

}
