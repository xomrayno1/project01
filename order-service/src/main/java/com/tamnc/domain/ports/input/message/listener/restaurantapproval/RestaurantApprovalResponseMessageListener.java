package com.tamnc.domain.ports.input.message.listener.restaurantapproval;

import com.tamnc.service.message.RestaurantAprrovalResponse;

public interface RestaurantApprovalResponseMessageListener {
	
	void orderAprroval(RestaurantAprrovalResponse restaurantAprrovalResponse);
	
	void orderReject(RestaurantAprrovalResponse restaurantAprrovalResponse);
}
