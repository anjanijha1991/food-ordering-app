package com.practice.foodordering.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Rating {

    String restaurantName;
    String user;
    String comment;
    int rating;
    LocalDateTime ratedAt;
}
