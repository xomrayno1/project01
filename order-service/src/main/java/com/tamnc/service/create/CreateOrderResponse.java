package com.tamnc.service.create;

import java.util.UUID;

import com.tamnc.objects.OrderStatus;

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
