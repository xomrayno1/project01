package com.tamnc.domain.ports.input.message.listener.restaurantapproval;

import org.springframework.stereotype.Service;

import com.tamnc.service.message.RestaurantAprrovalResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RestaurantApprovalResponseMessageListenerImpl implements RestaurantApprovalResponseMessageListener{
	
	@Override
	public void orderAprroval(RestaurantAprrovalResponse restaurantAprrovalResponse) {
		
	}

	@Override
	public void orderReject(RestaurantAprrovalResponse restaurantAprrovalResponse) {
	 
		
	}

}
