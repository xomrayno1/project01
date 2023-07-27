package com.tamnc.domain.entity;

import com.tamnc.entity.BaseEntity;
import com.tamnc.objects.Money;
import com.tamnc.objects.ProductId;

import lombok.Getter;
 
@Getter
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
}
