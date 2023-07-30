package com.app.model.response;

import java.util.List;
import java.util.UUID;

import com.app.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TrackOrderResponse {
	private final UUID orderTrackingId;
	private final OrderStatus orderStatus;
	private final List<String> failureMessages;
}
