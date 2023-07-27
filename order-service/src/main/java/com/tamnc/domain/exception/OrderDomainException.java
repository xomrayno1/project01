package com.tamnc.domain.exception;

import com.tamnc.exception.DomainException;

public class OrderDomainException extends DomainException{

	public OrderDomainException(String message) {
		super(message);
	}
	
	public OrderDomainException(String message, Throwable cause) {
		super(message, cause);
	}

}
