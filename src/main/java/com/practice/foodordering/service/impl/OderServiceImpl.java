package com.practice.foodordering.service.impl;

import com.practice.foodordering.dto.Order;
import com.practice.foodordering.dto.Restaurant;
import com.practice.foodordering.dto.User;
import com.practice.foodordering.exception.NoDataException;
import com.practice.foodordering.exception.NoUserLoggedInException;
import com.practice.foodordering.repository.OrderRepository;
import com.practice.foodordering.service.LoginService;
import com.practice.foodordering.service.OrderService;
import com.practice.foodordering.service.RestaurantService;
import com.practice.foodordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @Autowired
    RestaurantService restaurantService;


    @Override
    public void createOrder(String name, int quantity) throws NoDataException {
        String loggedInUser = loginService.getLoggedInUser();
        if (loggedInUser.isEmpty()) {
            throw new NoUserLoggedInException();
        }

        User user = userService.getUser(loggedInUser);

        Order order = Order.builder()
                .createdAt(System.currentTimeMillis())
                .quantity(quantity).restaurantName(name)
                .user(loggedInUser).build();

        Restaurant restaurant = restaurantService.getRestaurant(name);

        restaurant.setQuantity(restaurant.getQuantity() - quantity);
        restaurantService.updateQuantity(name, -quantity);

        orderRepository.save(order);
    }
}
