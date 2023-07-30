package com.app.exception;

public class KafkaProducerException extends RuntimeException{ 
	
	public KafkaProducerException(String message) {
		super(message);
	}

}
