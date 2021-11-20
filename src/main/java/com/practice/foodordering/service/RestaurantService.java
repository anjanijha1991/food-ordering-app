package com.practice.foodordering.service;

import com.practice.foodordering.dto.Preference;
import com.practice.foodordering.dto.Restaurant;
import com.practice.foodordering.exception.NoDataException;

import java.util.List;

public interface RestaurantService {
    void registerRestaurant(Restaurant build);

    void updateQuantity(String name, int quantity ) throws NoDataException;

    double rate(String restaurantName, int rating, String comment);

    List<Restaurant> showRestaurants(Preference preference);

    Restaurant getRestaurant(String name);

    void update(Restaurant name);
}
