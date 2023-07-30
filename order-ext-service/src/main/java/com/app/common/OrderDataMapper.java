package com.app.common;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.app.entity.Customer;
import com.app.entity.Order;
import com.app.entity.OrderItem;
import com.app.entity.Product;
import com.app.entity.Restaurant;
import com.app.kafka.model.RestaurantOrderStatus;
import com.app.model.dto.CustomerModel;
import com.app.model.dto.Money;
import com.app.model.dto.OrderAddressDTO;
import com.app.model.dto.OrderItemDTO;
import com.app.model.dto.StreetAddressDTO;
import com.app.model.event.OrderPaidEvent;
import com.app.model.outbox.OrderApprovalEventPayload;
import com.app.model.outbox.OrderApprovalEventProduct;
import com.app.model.request.CreateOrderCommand;
import com.app.model.response.CreateOrderResponse;
import com.app.model.response.TrackOrderResponse;

@Component
public class OrderDataMapper {
	public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
		Restaurant restaurant =  Restaurant.builder()
				.products(
						createOrderCommand.getItems().stream()
						.map(orderItem -> new Product(orderItem.getProductId()))
						.collect(Collectors.toList())
				)
				.build();
		restaurant.setId(createOrderCommand.getRestaurantId());
		return restaurant;
	}
	
	public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
		return Order.builder()
				.customerId(createOrderCommand.getCustomerId())
				.restaurantId(createOrderCommand.getRestaurantId())
				.deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
				.items(orderItemsToOrderItemEntites(createOrderCommand.getItems()))
				.price(new Money(createOrderCommand.getPrice()))
				.build();
	}
	
	public StreetAddressDTO orderAddressToStreetAddress(OrderAddressDTO address) {
		return new StreetAddressDTO(UUID.randomUUID(), address.getStreet(), address.getPostalCode(), address.getCity());
	}
 
	public List<OrderItem> orderItemsToOrderItemEntites(List<OrderItemDTO> orderItems){
		return orderItems.stream()
				.map(orderItemDTO -> OrderItem.builder()
						.product(new Product(orderItemDTO.getProductId()))
						.price(new Money(orderItemDTO.getPrice()))
						.quantity(orderItemDTO.getQuantity())
						.subTotal(new Money(orderItemDTO.getSubTotal()))
						.build()
				).collect(Collectors.toList());
				
	}
	
	public CreateOrderResponse orderToCreateOrderResponse(Order order, String message) {
		return CreateOrderResponse.builder()
				.orderTrackingId(order.getId())
				.orderStatus(order.getOrderStatus())
				.message(message)
				.build();
	}
	
	public TrackOrderResponse orderToTrackOrderResponse(Order order) {
		return TrackOrderResponse.builder()
				.orderStatus(order.getOrderStatus())
				.failureMessages(order.getFailureMessage())
				.orderTrackingId(order.getId())
				.build();
	}
	
    public Customer customerModelToCustomer(CustomerModel customerModel) {
        return new Customer(UUID.fromString(customerModel.getId()),
                customerModel.getUsername(),
                customerModel.getFirstName(),
                customerModel.getLastName());
    }
    
    public OrderApprovalEventPayload orderPaidEventToOrderApprovalEventPayload(OrderPaidEvent orderPaidEvent) {
        return OrderApprovalEventPayload.builder()
                .orderId(orderPaidEvent.getOrder().getId().toString())
                .restaurantId(orderPaidEvent.getOrder().getRestaurantId().toString())
                .restaurantOrderStatus(RestaurantOrderStatus.PAID.name())
                .products(orderPaidEvent.getOrder().getItems().stream().map(orderItem ->
                        OrderApprovalEventProduct.builder()
                                .id(orderItem.getProduct().getId().toString())
                                .quantity(orderItem.getQuantity())
                                .build()).collect(Collectors.toList()))
                .price(orderPaidEvent.getOrder().getPrice().getAmount())
               // .createdAt(orderPaidEvent.getCreatedAt())
                .build();
    }

}
