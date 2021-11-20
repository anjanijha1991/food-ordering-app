package com.practice.foodordering.resource;

import com.practice.foodordering.dto.Preference;
import com.practice.foodordering.dto.Restaurant;
import com.practice.foodordering.exception.NoDataException;
import com.practice.foodordering.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController("/restaurant")
public class RestaurantResource {

    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/register1")
    ResponseEntity registerRestaurant(@RequestParam("dish") String dish,
                                      @RequestParam("restaurantName") String restaurantName,
                                      @RequestParam("price") float price,
                                      @RequestParam("pin") String pin,
                                      @RequestParam("quantity") int quantity) {
        try {
            String[] pins = pin.split("/");
            restaurantService.registerRestaurant(Restaurant
                    .builder().dish(dish).name(restaurantName).price(price)
                    .quantity(quantity)
                    .serviceablePins(Arrays.asList(pins)).build());
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            System.out.println("error registering user " + e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @PostMapping("/update")
    ResponseEntity update(@RequestParam("name") String restaurantName, @RequestParam("quantity") int quantity) {
        try {
            restaurantService.updateQuantity(restaurantName, quantity);
            return ResponseEntity.ok().build();

        } catch (Exception | NoDataException e) {
            System.out.println("error registering user " + e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/rate")
    ResponseEntity rate(@RequestParam("name") String restaurantName, @RequestParam("rating") int rating,
                        @RequestParam("comment") String comment) {
        try {
            double rate = restaurantService.rate(restaurantName, rating, comment);
            return ResponseEntity.ok("Avg rating of Restaurant " + rate);
        } catch (Exception e) {
            System.out.println("error registering user " + e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


    @PostMapping("/show")
    ResponseEntity rate(@RequestParam("preference") Preference preference) {
        try {
            List<Restaurant> restaurants = restaurantService.showRestaurants(preference);
            return ResponseEntity.ok(restaurants);
        } catch (Exception e) {
            System.out.println("error registering user " + e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
