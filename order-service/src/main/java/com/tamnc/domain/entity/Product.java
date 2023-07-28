package com.tamnc.domain.entity;

import com.tamnc.entity.BaseEntity;
import com.tamnc.objects.Money;
import com.tamnc.objects.ProductId;

import lombok.AllArgsConstructor;
import lombok.Getter;
 
@Getter
@AllArgsConstructor
public class Product extends BaseEntity<ProductId>{
  
	private String name;
	private Money price;
	
	public void updateWithConfirmedNameAndPrice(String name, Money price) {
		this.name = name;
		this.price = price;
	}
 
	public Product(ProductId productId) {
		super.setId(productId);
	}
	
	public Product(ProductId productId, String name, Money price) {
		super.setId(productId);
		this.name = name;
		this.price = price;
	}
}
