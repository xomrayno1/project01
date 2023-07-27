package com.tamnc.domain.entity;

import com.tamnc.domain.objects.OrderItemId;
import com.tamnc.entity.BaseEntity;
import com.tamnc.objects.Money;
import com.tamnc.objects.OrderId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

 
 
@Getter
@AllArgsConstructor
@Builder
public class OrderItem extends BaseEntity<OrderItemId>{
	private OrderId orderId;
	private final Product product;
	private final Money price;
	private final Money subTotal;
	private final int quantity;
	
	public void initializeOrderItem(OrderId orderId, OrderItemId orderItemId) {
		this.orderId = orderId;
		setId(orderItemId);
	}
	
	boolean isPriceValid(){
		return price.isGreaterThanZero() && product.getPrice().equals(price)  && price.multiply(quantity).equals(subTotal);
	}
  	 
}
