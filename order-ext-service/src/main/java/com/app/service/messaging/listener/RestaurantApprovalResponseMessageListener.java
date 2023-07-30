package com.app.service.messaging.listener;

import com.app.model.response.RestaurantAprrovalResponse;

public interface RestaurantApprovalResponseMessageListener {
	
	void orderAprroval(RestaurantAprrovalResponse restaurantAprrovalResponse);
	
	void orderReject(RestaurantAprrovalResponse restaurantAprrovalResponse);
}
