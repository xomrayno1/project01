package com.app.exception;

public class OrderNotFoundException extends DomainException{

	public OrderNotFoundException(String message) {
		super(message);
	}

}
