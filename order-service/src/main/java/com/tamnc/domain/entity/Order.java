package com.tamnc.domain.entity;

import java.util.List;
import java.util.UUID;

import com.tamnc.domain.exception.OrderDomainException;
import com.tamnc.domain.objects.OrderItemId;
import com.tamnc.domain.objects.StreetAddress;
import com.tamnc.domain.objects.TrackingId;
import com.tamnc.entity.AggregateRoot;
import com.tamnc.objects.CustomerId;
import com.tamnc.objects.Money;
import com.tamnc.objects.OrderId;
import com.tamnc.objects.OrderStatus;
import com.tamnc.objects.RestaurantId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Order extends AggregateRoot<OrderId>{
	private final CustomerId customerId;
	private final RestaurantId restaurantId;
	private final StreetAddress deliveryAddress;
	private final Money price;
	private final List<OrderItem> items;
	
	private TrackingId trackingId;
	private OrderStatus orderStatus;
	private List<String> failureMessage;
	
	
	public void initializeOrder() {
		setId(new OrderId(UUID.randomUUID()));
		trackingId = new TrackingId(UUID.randomUUID());
		orderStatus = OrderStatus.PENDING;
		initializeOrderItem();
		
	}
	
	public void pay() {
		if(orderStatus != OrderStatus.PENDING) {
			throw new OrderDomainException("Order is not incorrect state for pay operation!");
		}
		orderStatus = OrderStatus.PAID;
	}
	
	public void aprrove() {
		if(orderStatus != OrderStatus.PAID) {
			throw new OrderDomainException("Order is not incorrect state for aprrove operation!");
		}
		orderStatus = OrderStatus.APPROVED;
	}
	
	public void initCancel(List<String> failureMessage) {
		if(orderStatus != OrderStatus.PAID) {
			throw new OrderDomainException("Order is not incorrect state for initcancel operation!");
		}
		orderStatus = OrderStatus.CANCELLING;
		updateFailureMessages(failureMessage);
	}
	

	public void cancel(List<String> failureMessage) {
		if(!(orderStatus == OrderStatus.CANCELLING || orderStatus == OrderStatus.PENDING)) {
			throw new OrderDomainException("Order is not incorrect state for cancel operation!");
		}
		orderStatus = OrderStatus.CANCELLED;
		updateFailureMessages(failureMessage);
	}
	
	public void updateFailureMessages(List<String> failureMessage) {
		if(this.failureMessage != null && failureMessage != null) {
			this.failureMessage.addAll(failureMessage.stream().filter(message -> !message.isEmpty()).toList());
		}
		
		if(this.failureMessage == null) {
			this.failureMessage = failureMessage;
		}
	}
	
	public void validateOrder() {
		validateInitialOrder();
		validateTotalPrice();
		validateItemsPrice();
	}
	
	private void validateInitialOrder() {
		if(orderStatus != null || getId() == null) {
			throw new OrderDomainException("Order is not incorrect state for initialization!");
		}
	}
	
	private void validateTotalPrice() {
		if(price == null || !price.isGreaterThanZero()) {
			throw new OrderDomainException("Total price must be great than zero!");
		}
	}
	
	private void validateItemsPrice() {
		Money orderItemsTotal = items.stream().map(orderItem -> {
			validateItemPrice(orderItem);
			return orderItem.getSubTotal();
		}).reduce(Money.ZERO, Money::add);
		
		if(!price.equals(orderItemsTotal)) {
			throw new OrderDomainException("Total Price: " + price.getAmount() 
				+ " is not equal to Order ttems total : " + orderItemsTotal.getAmount() + "!"
					);
		}
	}
	
	private void validateItemPrice(OrderItem orderItem) {
		if(!orderItem.isPriceValid()) {
			throw new OrderDomainException("Order item price " + orderItem.getPrice().getAmount() 
					+ " is not valid for product " + orderItem.getProduct().getId().getValue());
		}
	}
	 
	private void initializeOrderItem() {
		long itemId = 1;
		for(OrderItem orderItem: items) {
			orderItem.initializeOrderItem(super.getId(), new OrderItemId(itemId++));
		}
	}
}
