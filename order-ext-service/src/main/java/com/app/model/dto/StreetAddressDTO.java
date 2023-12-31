package com.app.model.dto;

import java.util.Objects;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StreetAddressDTO {
	private final UUID id;
	private final String street;
	private final String postalCode;
	private final String city;
	
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		StreetAddressDTO streetAddress = (StreetAddressDTO) obj;
		return street.equals(streetAddress.getStreet()) && postalCode.equals(streetAddress.getPostalCode()) && city.equals(streetAddress.getCity());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, street, city);
	}
	
}
