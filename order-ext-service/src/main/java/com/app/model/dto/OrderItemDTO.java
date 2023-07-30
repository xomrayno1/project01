package com.app.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OrderItemDTO {
	
	private final UUID productId;
	private final Integer quantity;
	private final BigDecimal price;
	private final BigDecimal subTotal;

}