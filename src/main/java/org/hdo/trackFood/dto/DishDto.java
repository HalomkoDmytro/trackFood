package org.hdo.trackFood.dto;

public record DishDto(Long id,
                      String mealName,
                      String comment,
                      Double calories,
                      Long gr) {
    // todo: add productSizes
}
