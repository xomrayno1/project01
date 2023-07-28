package com.tamnc;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tamnc.domain.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import com.tamnc.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.tamnc.domain.ports.output.message.publisher.restaurantaprroval.OrderPaidRestaurantRequestMessagePublisher;
import com.tamnc.domain.ports.output.repository.CustomerRepository;
import com.tamnc.domain.ports.output.repository.OrderRepository;
import com.tamnc.domain.ports.output.repository.RestaurantRepository;
import com.tamnc.domain.service.OrderDomainService;

@SpringBootApplication(scanBasePackages = "com.tamnc")
public class OrderTestConfiguration {

	@Bean
	public OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher() {
		return Mockito.mock(OrderCreatedPaymentRequestMessagePublisher.class);
	}
	
	@Bean
	public OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher() {
		return Mockito.mock(OrderCancelledPaymentRequestMessagePublisher.class);
	}
	
	@Bean
	public OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMessagePublisher() {
		return Mockito.mock(OrderPaidRestaurantRequestMessagePublisher.class);
	}
	
	@Bean
	public OrderRepository orderRepository() {
		return Mockito.mock(OrderRepository.class);
	}
	
	@Bean
	public CustomerRepository customerRepository() {
		return Mockito.mock(CustomerRepository.class);
	}
	
	@Bean
	public RestaurantRepository restaurantRepository() {
		return Mockito.mock(RestaurantRepository.class);
	}
	
//	@Bean
//	public OrderDomainService orderDomainService() {
//		return Mockito.mock(OrderDomainService.class);
//	}
}
