package com.tamnc.domain.ports.output.repository;

import java.util.Optional;

import com.tamnc.domain.entity.Restaurant;

public interface RestaurantRepository {

	Optional<Restaurant> findRestaurantInfomation(Restaurant restaurant);
	
}
