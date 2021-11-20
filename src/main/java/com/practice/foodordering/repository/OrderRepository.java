package com.practice.foodordering.repository;

import com.practice.foodordering.dto.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderRepository {

    List<Order> orderList = new ArrayList<>();
    public void save(Order order) {
        orderList.add(order);

    }
}
