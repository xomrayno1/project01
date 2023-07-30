package com.app.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.app.common.OrderDataMapper;
import com.app.entity.Customer;
import com.app.entity.Order;
import com.app.entity.Restaurant;
import com.app.exception.OrderDomainException;
import com.app.model.event.OrderCreatedEvent;
import com.app.model.request.CreateOrderCommand;
import com.app.model.response.CreateOrderResponse;
import com.app.repository.CustomerRepository;
import com.app.repository.OrderRepository;
import com.app.repository.RestaurantRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class OrderCreateCommandHandler {
	private final OrderDomainService orderDomainService;
	
	private final OrderRepository orderRepository;
	
	private final CustomerRepository customerRepository;
	
	private final RestaurantRepository restaurantRepository;
	
	private final OrderDataMapper orderDataMapper;
	
//	private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessage;
	
	private final ApplicationEventPublisher applicationEventPublisher;
	
	@Transactional
	public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
		checkCustomer(createOrderCommand.getCustomerId());
		Restaurant restaurant = checkRestaurant(createOrderCommand);
		Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
		OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
		Order orderResult = saveOrder(order);
		log.info("Order is created with id: {} ", orderCreatedEvent.getOrder().getId());
		
		applicationEventPublisher.publishEvent(orderCreatedEvent);
		return orderDataMapper.orderToCreateOrderResponse(orderResult, "Order created successfully");
	}
	 
	public void checkCustomer(UUID customerId) {
		Optional<Customer> customer = customerRepository.findCustomer(customerId);
		if(customer.isEmpty()) {
			log.warn("Counld not find customer with customer id {} ", customer);
			throw new OrderDomainException("Counld not find customer with customer id " + customer);
		}
	}

	public Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
		Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
		Optional<Restaurant> optionalRestaurant = restaurantRepository.findRestaurantInfomation(restaurant);
		if(optionalRestaurant.isEmpty()) {
			log.warn("Counld not find restaurant with restaurant id {} ", createOrderCommand.getRestaurantId());
			throw new OrderDomainException("Counld not find restaurant with restaurant id " + createOrderCommand.getRestaurantId());
		}
		return optionalRestaurant.get();
	}
	
	private Order saveOrder(Order order) {
		Order orderResult = orderRepository.save(order);
		if(orderResult == null) {
			throw new OrderDomainException("Could not save order");
		}
		log.info("save order success", orderResult.getId());
		return orderResult;
	}
	
 
}
