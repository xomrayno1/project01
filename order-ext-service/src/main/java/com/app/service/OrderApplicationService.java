package com.app.service;

import com.app.model.request.CreateOrderCommand;
import com.app.model.request.TrackOrderQuery;
import com.app.model.response.CreateOrderResponse;
import com.app.model.response.TrackOrderResponse;

public interface OrderApplicationService {

	CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand);
	
	TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery);
	
	
}
