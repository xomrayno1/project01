package com.tamnc.service.create;

import java.util.List;

import com.tamnc.domain.entity.Product;
import com.tamnc.objects.RestaurantId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class RestaurantDTO {
	private RestaurantId restaurantId;
	private final List<Product> products;
	private boolean active;
}
