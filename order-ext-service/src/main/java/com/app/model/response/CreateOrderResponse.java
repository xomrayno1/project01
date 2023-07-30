package com.app.model.response;

import java.util.UUID;

import com.app.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class CreateOrderResponse {
	private final UUID orderTrackingId;
	private final OrderStatus orderStatus;
	private final String message;
}
