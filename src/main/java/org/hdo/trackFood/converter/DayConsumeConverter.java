package org.hdo.trackFood.converter;

import org.hdo.trackFood.dto.DayConsumeDto;
import org.hdo.trackFood.dto.DishDto;
import org.hdo.trackFood.model.DayConsume;
import org.hdo.trackFood.model.Dish;

import java.util.List;

public final class DayConsumeConverter {

    public static DayConsume convert(DayConsumeDto dto) {
        List<Dish> dishes = dto.dishes().stream()
                .map(DishConverter::convert)
                .toList();

        return DayConsume.builder()
                .id(dto.id())
                .timestamp(dto.timestamp())
                .comment(dto.comment())
                .calories(dto.calories())
                .gr(dto.gr())
                .dishes(dishes)
                .build();
    }

    public static DayConsumeDto convert(DayConsume dayConsume) {
        List<DishDto> listDish = dayConsume.getDishes().stream()
                .map(DishConverter::convert)
                .toList();

        return new DayConsumeDto(
                dayConsume.getId(),
                dayConsume.getTimestamp(),
                dayConsume.getComment(),
                dayConsume.getCalories(),
                dayConsume.getGr(),
                listDish
        );
    }

}
