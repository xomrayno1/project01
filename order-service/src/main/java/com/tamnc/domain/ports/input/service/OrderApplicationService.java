package com.tamnc.domain.ports.input.service;

import com.tamnc.service.create.CreateOrderCommand;
import com.tamnc.service.create.CreateOrderResponse;
import com.tamnc.service.track.TrackOrderQuery;
import com.tamnc.service.track.TrackOrderResponse;

public interface OrderApplicationService {

	CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand);
	
	TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery);
	
	
}
