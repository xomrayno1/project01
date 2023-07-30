package com.app.entity;

import java.util.UUID;

import com.app.model.dto.Money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
 
@Getter
@AllArgsConstructor
@Builder
public class Product extends BaseEntity<UUID>{
  
	private String name;
	private Money price;
	
	public void updateWithConfirmedNameAndPrice(String name, Money price) {
		this.name = name;
		this.price = price;
	}
 
	public Product(UUID productId) {
		super.setId(productId);
	}
	
	public Product(UUID productId, String name, Money price) {
		super.setId(productId);
		this.name = name;
		this.price = price;
	}
}
