package com.practice.foodordering.repository;

import com.practice.foodordering.dto.Restaurant;
import com.practice.foodordering.exception.NoDataException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RestaurantRepository {

    Map<String, Restaurant> restaurantDetails = new HashMap<>();

    Map<String, List<Restaurant>> regionalRestaurant = new HashMap<>();

    public void registerRestaurant(Restaurant restaurant) {

        restaurantDetails.put(restaurant.getName(), restaurant);
        restaurant.getServiceablePins().stream().forEach(pin -> {
            List<Restaurant> restaurants = regionalRestaurant.getOrDefault(pin, new ArrayList<>());
            restaurants.add(restaurant);
            regionalRestaurant.put(pin, restaurants);
        });
    }

    public void updateQuantity(String restaurantName, int quantity) throws NoDataException {
        Restaurant restaurant = restaurantDetails.get(restaurantName);
        if (restaurant == null) {
            throw new NoDataException("No restaurant Found!!");
        }
        restaurant.setQuantity(restaurant.getQuantity() + quantity);

    }


    public List<Restaurant> showRestaurants(String pin) {

        List<Restaurant> restaurants = regionalRestaurant.get(pin);
        return restaurants.stream().filter(restaurant -> restaurant.getQuantity() > 0).collect(Collectors.toList());
    }

    public void updateRating(String restaurantName, double rating1) {
        Restaurant restaurant = restaurantDetails.get(restaurantName);
        if (restaurant == null) {
            throw new RuntimeException("No restaurant Found!!");
        }
        restaurant.setAvgRating(rating1);
    }

    public Restaurant getRestaurant(String name) {

        return restaurantDetails.get(name);
    }



    public void updateRestaurant(Restaurant name) {
        restaurantDetails.put(name.getName(),name );
    }

}
