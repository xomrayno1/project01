package com.tamnc.domain.exception;

import com.tamnc.exception.DomainException;

public class OrderNotFoundException extends DomainException{

	public OrderNotFoundException(String message) {
		super(message);
	}

}
