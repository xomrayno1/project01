package com.app.repository;

import java.util.Optional;
import java.util.UUID;

import com.app.entity.Customer;

public interface CustomerRepository {
	
	Optional<Customer> findCustomer(UUID customerId);
	
	Customer save(Customer customer);

}
