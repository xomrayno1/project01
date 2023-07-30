package com.app.service;

public interface SagaStep<T> {
	
	void process(T data);
	
    void rollback(T data);
    
}
