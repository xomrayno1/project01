package com.tamnc.domain.entity;

import java.util.List;

import com.tamnc.entity.AggregateRoot;
import com.tamnc.objects.RestaurantId;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

 
@Getter
@Setter
@Builder
public class Restaurant extends AggregateRoot<RestaurantId>{
	
	private RestaurantId restaurantId;
	private final List<Product> products;
	private boolean active;

}
