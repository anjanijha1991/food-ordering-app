package com.practice.foodordering.service;

import com.practice.foodordering.dto.Rating;

public interface RatingService {
    void addRating(Rating build);

    double getAvgRating(String restaurantName);
}
