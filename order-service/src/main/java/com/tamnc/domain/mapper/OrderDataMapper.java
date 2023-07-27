package com.tamnc.domain.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tamnc.domain.entity.Order;
import com.tamnc.domain.entity.OrderItem;
import com.tamnc.domain.entity.Product;
import com.tamnc.domain.entity.Restaurant;
import com.tamnc.domain.objects.StreetAddress;
import com.tamnc.objects.CustomerId;
import com.tamnc.objects.Money;
import com.tamnc.objects.ProductId;
import com.tamnc.objects.RestaurantId;
import com.tamnc.service.create.CreateOrderCommand;
import com.tamnc.service.create.CreateOrderResponse;
import com.tamnc.service.create.OrderAddress;
import com.tamnc.service.create.OrderItemDTO;

@Component
public class OrderDataMapper {
	
	public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
		return Restaurant.builder()
				.restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
				.products(
						createOrderCommand.getItems().stream()
						.map(orderItem -> new Product(new ProductId(orderItem.getProductId())))
						.collect(Collectors.toList())
				)
				.build();
	}
	
	public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
		return Order.builder()
				.customerId(new CustomerId(createOrderCommand.getCustomerId()))
				.restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
				.deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
				.items(orderItemsToOrderItemEntites(createOrderCommand.getItems()))
				.build();
	}
	
	public StreetAddress orderAddressToStreetAddress(OrderAddress address) {
		return new StreetAddress(UUID.randomUUID(), address.getStreet(), address.getPostalCode(), address.getCity());
	}
 
	public List<OrderItem> orderItemsToOrderItemEntites(List<OrderItemDTO> orderItems){
		return orderItems.stream()
				.map(orderItemDTO -> OrderItem.builder()
						.product(new Product(new ProductId(orderItemDTO.getProductId())))
						.price(new Money(orderItemDTO.getPrice()))
						.quantity(orderItemDTO.getQuantity())
						.subTotal(new Money(orderItemDTO.getSubTotal()))
						.build()
				).collect(Collectors.toList());
				
	}
	
	public CreateOrderResponse orderToCreateOrderResponse(Order order, String message) {
		return CreateOrderResponse.builder()
				.orderTrackingId(order.getTrackingId().getValue())
				.orderStatus(order.getOrderStatus())
				.message(message)
				.build();
	}

}
