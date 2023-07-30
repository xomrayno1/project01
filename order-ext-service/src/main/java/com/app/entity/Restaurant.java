package com.app.entity;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

 
@Getter
@Setter
@Builder
public class Restaurant extends AggregateRoot<UUID>{
	
 
	private final List<Product> products;
	private boolean active;

}
