package com.app.service.messaging.listener;

import com.app.model.dto.CustomerModel;

public interface CustomerMessageListener {
	void customerCreated(CustomerModel customerModel);
}
