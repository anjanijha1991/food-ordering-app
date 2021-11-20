package com.practice.foodordering.service;

import com.practice.foodordering.exception.NoDataException;

public interface OrderService {

    void createOrder(String name, int quantity) throws NoDataException;
}
