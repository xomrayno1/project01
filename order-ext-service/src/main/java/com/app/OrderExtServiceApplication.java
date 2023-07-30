package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.app.service.OrderDomainService;
import com.app.service.impl.OrderDomainServiceImpl;

@SpringBootApplication
public class OrderExtServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderExtServiceApplication.class, args);
	}

	@Bean
	public OrderDomainService orderDomainService() {
		return new OrderDomainServiceImpl();
	}

}
