package com.tamnc.service.message;

import java.time.Instant;
import java.util.List;

import com.tamnc.objects.OrderApprovalStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RestaurantAprrovalResponse {
	private String id;
	private String sagaId;
	private String orderId;
	private String restaurantId;
	private Instant createAt;
	private OrderApprovalStatus orderApprovalStatus;
	private List<String> failureMessages;
}
