package com.practice.foodordering.service.impl;

import com.practice.foodordering.dto.Preference;
import com.practice.foodordering.dto.Rating;
import com.practice.foodordering.dto.Restaurant;
import com.practice.foodordering.dto.User;
import com.practice.foodordering.exception.BadDataException;
import com.practice.foodordering.exception.NoDataException;
import com.practice.foodordering.exception.NoUserLoggedInException;
import com.practice.foodordering.repository.RestaurantRepository;
import com.practice.foodordering.service.LoginService;
import com.practice.foodordering.service.RatingService;
import com.practice.foodordering.service.RestaurantService;
import com.practice.foodordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    RestaurantRepository repository;

    @Autowired
    RatingService ratingService;
    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;
    @Override
    public void registerRestaurant(Restaurant restaurant) {
        if(restaurant.getServiceablePins().isEmpty()){
            throw new BadDataException();
        }
        repository.registerRestaurant(restaurant);

    }


    @Override
    public void updateQuantity(String name, int quantity ) throws NoDataException {
        repository.updateQuantity(name, quantity);

    }

    @Override
    public double rate(String restaurantName, int rating, String comment) {
        String loggedInUser = loginService.getLoggedInUser();
        if(loggedInUser.isEmpty()){
            throw new NoUserLoggedInException();
        }
        ratingService.addRating(Rating.builder().rating(rating)
                .restaurantName(restaurantName)
                        .comment(comment).user(loggedInUser)
                        .ratedAt(LocalDateTime.now())
                        .build()
                );


          double rating1 = ratingService.getAvgRating(restaurantName);
          repository.updateRating(restaurantName, rating1);
          return rating1;
    }

    @Override
    public List<Restaurant> showRestaurants(Preference preference){
        String loggedInUser = loginService.getLoggedInUser();
        if(loggedInUser.isEmpty()){
            throw new NoUserLoggedInException();
        }

        User user = userService.getUser(loggedInUser);

        List<Restaurant> restaurants = repository.showRestaurants(user.getPin());
        if(preference.equals(Preference.PRICE)){
            return restaurants.stream().sorted(Comparator.comparing(Restaurant::getPrice)).collect(Collectors.toList());
        }else{
            return restaurants.stream().sorted(Comparator.comparing(Restaurant::getAvgRating)).collect(Collectors.toList());

        }
    }

    @Override
    public Restaurant getRestaurant(String name) {
        return repository.getRestaurant(name);
    }

    @Override
    public void update(Restaurant name) {
         repository.updateRestaurant(name);
    }

}
