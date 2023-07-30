package com.app.entity;

import java.util.UUID;

import com.app.model.dto.Money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

 
 
@Getter
@AllArgsConstructor
@Builder
public class OrderItem extends BaseEntity<Long>{
	private UUID orderId;
	private final Product product;
	private final Money price;
	private final Money subTotal;
	private final int quantity;
	
	public void initializeOrderItem(UUID orderId, Long orderItemId) {
		this.orderId = orderId;
		setId(orderItemId);
	}
	
	boolean isPriceValid(){
		return price.isGreaterThanZero() && price.multiply(quantity).equals(subTotal);
	}
  	 
}
