package com.practice.foodordering.service.impl;

import com.practice.foodordering.dto.Rating;
import com.practice.foodordering.repository.RatingRepository;
import com.practice.foodordering.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository repository;

    @Override
    public void addRating(Rating rating) {
        repository.addRating(rating);
    }

    @Override
    public double getAvgRating(String restaurantName) {
        return repository.getAvg(restaurantName);
    }


}
