package com.tamnc.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tamnc.domain.exception.OrderDomainException;
import com.tamnc.domain.exception.OrderNotFoundException;
import com.tamnc.exception.ErrorDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class OrderGlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(value = {OrderDomainException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDTO handleException(OrderDomainException orderDomainException) {
		log.error(orderDomainException.getMessage(), orderDomainException);
		return ErrorDTO.builder()
				.code(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.message(orderDomainException.getMessage())
				.build();
	}
	
	@ResponseBody
	@ExceptionHandler(value = {OrderNotFoundException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDTO handleException(OrderNotFoundException orderNotFoundException) {
		log.error(orderNotFoundException.getMessage(), orderNotFoundException);
		return ErrorDTO.builder()
				.code(HttpStatus.NOT_FOUND.getReasonPhrase())
				.message(orderNotFoundException.getMessage())
				.build();
	}
	
	
}
