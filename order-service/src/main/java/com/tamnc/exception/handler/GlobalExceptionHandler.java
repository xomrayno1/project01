package com.tamnc.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tamnc.exception.ErrorDTO;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(value = {Exception.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDTO handleException(Exception exception) {
		log.error(exception.getMessage(), exception);
		return ErrorDTO.builder()
				.code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.message("Unexpected error!")
				.build();
	}
	
//	@ResponseBody
//	@ExceptionHandler(value = {ValidateException.class})
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	public ErrorDTO handleException(ValidateException validateException) {
//		ErrorDTO errorDTO;
//		if(validateException instanceof ValidateException) {
//			String violation = extractViolationsFromException((ValidateException) validateException);
//		}
//		
//		
//		log.error(validateException.getMessage(), validateException);
//		return ErrorDTO.builder()
//				.code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
//				.message("Unexpected error!")
//				.build();
//	}
//	
//	private String extractViolationsFromException(ValidateException validateException) {
//		validateException.getConstraintViolations().stream().map(ConnstraintViolation::getMessage).collect(Collectors.joining("--"));
//	}
}
