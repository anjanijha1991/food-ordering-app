package com.practice.foodordering.repository;

import com.practice.foodordering.dto.Rating;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RatingRepository {

    Map<String, List<Rating>> restaurantRatings = new HashMap<>();
    List<Rating> ratings = new ArrayList<>();


    public void addRating(Rating rating) {
        ratings.add(rating);
        List<Rating> restaurantRatingsList = restaurantRatings.getOrDefault(rating.getRestaurantName(), new ArrayList<>());
        restaurantRatingsList.add(rating);
        restaurantRatings.put(rating.getRestaurantName(), restaurantRatingsList);
    }


    public double getAvg(String  restaurantName) {
        List<Rating> restaurantRatingsList = restaurantRatings.getOrDefault(restaurantName, new ArrayList<>());

       return restaurantRatingsList.stream().mapToDouble(Rating::getRating)
                .average()
                .orElse(0.0d);
    }
}
