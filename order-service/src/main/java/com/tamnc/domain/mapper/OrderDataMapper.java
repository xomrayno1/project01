package com.tamnc.domain.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tamnc.domain.entity.Customer;
import com.tamnc.domain.entity.Order;
import com.tamnc.domain.entity.OrderItem;
import com.tamnc.domain.entity.Product;
import com.tamnc.domain.entity.Restaurant;
import com.tamnc.domain.objects.StreetAddress;
import com.tamnc.domain.ports.input.message.dto.CustomerModel;
import com.tamnc.objects.CustomerId;
import com.tamnc.objects.Money;
import com.tamnc.objects.ProductId;
import com.tamnc.objects.RestaurantId;
import com.tamnc.service.create.CreateOrderCommand;
import com.tamnc.service.create.CreateOrderResponse;
import com.tamnc.service.create.OrderAddress;
import com.tamnc.service.create.OrderItemDTO;
import com.tamnc.service.track.TrackOrderResponse;

@Component
public class OrderDataMapper {
	
	public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
		Restaurant restaurant =  Restaurant.builder()
				.products(
						createOrderCommand.getItems().stream()
						.map(orderItem -> new Product(new ProductId(orderItem.getProductId())))
						.collect(Collectors.toList())
				)
				.build();
		restaurant.setId(new RestaurantId(createOrderCommand.getRestaurantId()));
		return restaurant;
	}
	
	public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
		return Order.builder()
				.customerId(new CustomerId(createOrderCommand.getCustomerId()))
				.restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
				.deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
				.items(orderItemsToOrderItemEntites(createOrderCommand.getItems()))
				.price(new Money(createOrderCommand.getPrice()))
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
	
	public TrackOrderResponse orderToTrackOrderResponse(Order order) {
		return TrackOrderResponse.builder()
				.orderStatus(order.getOrderStatus())
				.failureMessages(order.getFailureMessage())
				.orderTrackingId(order.getTrackingId().getValue())
				.build();
	}
	
    public Customer customerModelToCustomer(CustomerModel customerModel) {
        return new Customer(new CustomerId(UUID.fromString(customerModel.getId())),
                customerModel.getUsername(),
                customerModel.getFirstName(),
                customerModel.getLastName());
    }

}
