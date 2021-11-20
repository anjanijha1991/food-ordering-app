package com.practice.foodordering.resource;

import com.practice.foodordering.exception.NoDataException;
import com.practice.foodordering.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    ResponseEntity create(@RequestParam String name, @RequestParam int quantity) {
        try {
            orderService.createOrder(name, quantity);
            return ResponseEntity.ok().build();
        } catch (Exception | NoDataException e) {
            System.out.println("error registering user " + e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
}
