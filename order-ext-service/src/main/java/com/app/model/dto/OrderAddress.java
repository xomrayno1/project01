package com.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OrderAddress {
	private final String postalCode;
	private final String street;
	private final String city;

}
