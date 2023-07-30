package com.tamnc.domain.ports.output.repository;

import java.util.Optional;
import java.util.UUID;

import com.tamnc.domain.entity.Customer;

public interface CustomerRepository {

	Optional<Customer> findCustomer(UUID customerId);
	
	Customer save(Customer customer);
	
}
