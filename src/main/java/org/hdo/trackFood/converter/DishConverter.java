package org.hdo.trackFood.converter;

import org.hdo.trackFood.dto.DishDto;
import org.hdo.trackFood.model.Dish;

public final class DishConverter {

    public static DishDto convert(final Dish dish) {
        return new DishDto(dish.getId(),
                dish.getMealName(),
                dish.getComment(),
                dish.getCalories(),
                dish.getGr());
    }

    public static Dish convert(final DishDto dto) {
        return Dish.builder()
                .id(dto.id())
                .build();
    }
}
