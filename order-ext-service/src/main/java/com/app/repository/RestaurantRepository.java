package com.app.repository;

import java.util.Optional;

import com.app.entity.Restaurant;

public interface RestaurantRepository {
	
	Optional<Restaurant> findRestaurantInfomation(Restaurant restaurant);

}
