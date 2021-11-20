package com.practice.foodordering.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Restaurant {

    String dish;
    float price;
    List<String> serviceablePins;
    int quantity;
    String name;
    double avgRating;
}
