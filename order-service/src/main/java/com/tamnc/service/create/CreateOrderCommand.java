package com.tamnc.service.create;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class CreateOrderCommand {
	
	private final UUID customerId;
	
	private final UUID restaurantId;
	
	private final BigDecimal price;
	
	private final List<OrderItemDTO> items;
	
	private final OrderAddress address;

}
