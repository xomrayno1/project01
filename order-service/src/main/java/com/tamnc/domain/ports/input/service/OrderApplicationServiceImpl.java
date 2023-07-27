package com.tamnc.domain.ports.input.service;

import org.springframework.stereotype.Service;

import com.tamnc.service.create.CreateOrderCommand;
import com.tamnc.service.create.CreateOrderResponse;
import com.tamnc.service.track.TrackOrderQuery;
import com.tamnc.service.track.TrackOrderResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderApplicationServiceImpl implements OrderApplicationService{
	
	private final OrderCreateCommandHandler oOrderCreateCommandHandler;
	
	private final OrderTrackCommandHandler orderTrackCommandHandler;
	
	public OrderApplicationServiceImpl(OrderCreateCommandHandler oOrderCreateCommandHandler,
			OrderTrackCommandHandler orderTrackCommandHandler) {
		this.oOrderCreateCommandHandler = oOrderCreateCommandHandler;
		this.orderTrackCommandHandler = orderTrackCommandHandler;
	}

	@Override
	public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
		return oOrderCreateCommandHandler.createOrder(createOrderCommand);
	}

	@Override
	public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
		return orderTrackCommandHandler.trackOrderResponse(trackOrderQuery);
	}

}
