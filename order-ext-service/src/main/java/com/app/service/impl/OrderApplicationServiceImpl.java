package com.app.service.impl;

import org.springframework.stereotype.Service;

import com.app.model.request.CreateOrderCommand;
import com.app.model.request.TrackOrderQuery;
import com.app.model.response.CreateOrderResponse;
import com.app.model.response.TrackOrderResponse;
import com.app.service.OrderApplicationService;
import com.app.service.OrderCreateCommandHandler;
import com.app.service.OrderTrackCommandHandler;

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
		return orderTrackCommandHandler.trackOrder(trackOrderQuery);
	}

}
