package org.hdo.trackFood.dto;

public record ProductDto(Long id,
                         String name,
                         Double calories,
                         Double proteins,
                         Double fats,
                         Double carbohydrates,
                         Boolean isFiber) {
}
