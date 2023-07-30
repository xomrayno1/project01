package com.tamnc.domain.ports.input.message.listener.customer;

import org.springframework.stereotype.Service;

import com.tamnc.domain.entity.Customer;
import com.tamnc.domain.exception.OrderDomainException;
import com.tamnc.domain.mapper.OrderDataMapper;
import com.tamnc.domain.ports.input.message.dto.CustomerModel;
import com.tamnc.domain.ports.output.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerMessageListenerImpl  implements CustomerMessageListener{
	private final CustomerRepository customerRepository;
    private final OrderDataMapper orderDataMapper;

    public CustomerMessageListenerImpl(CustomerRepository customerRepository, OrderDataMapper orderDataMapper) {
        this.customerRepository = customerRepository;
        this.orderDataMapper = orderDataMapper;
    }

    @Override
    public void customerCreated(CustomerModel customerModel) {
        Customer customer = customerRepository.save(orderDataMapper.customerModelToCustomer(customerModel));
        if (customer == null) {
            log.error("Customer could not be created in order database with id: {}", customerModel.getId());
            throw new OrderDomainException("Customer could not be created in order database with id " +
                    customerModel.getId());
        }
        log.info("Customer is created in order database with id: {}", customer.getId());
    }
}
