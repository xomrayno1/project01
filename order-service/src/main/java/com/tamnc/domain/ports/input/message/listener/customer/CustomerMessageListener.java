package com.tamnc.domain.ports.input.message.listener.customer;

import com.tamnc.domain.ports.input.message.dto.CustomerModel;

public interface CustomerMessageListener {
	void customerCreated(CustomerModel customerModel);
}
