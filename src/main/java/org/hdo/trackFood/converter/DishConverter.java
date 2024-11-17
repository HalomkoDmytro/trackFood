package org.hdo.trackFood.converter;

import org.hdo.trackFood.dto.DishDto;
import org.hdo.trackFood.dto.ProductSizeDto;
import org.hdo.trackFood.model.Dish;
import org.hdo.trackFood.model.ProductSize;

import java.util.List;

public final class DishConverter {

    public static DishDto convert(final Dish dish) {
        List<ProductSizeDto> productSizeDtos = dish.getProductSizes().stream()
                .map(ProductSizeConverter::convert)
                .toList();

        return new DishDto(dish.getId(),
                dish.getMealName(),
                dish.getComment(),
                dish.getCalories(),
                dish.getGr(),
                productSizeDtos);
    }

    public static Dish convert(final DishDto dto) {
        List<ProductSize> productSizes = dto.productSizeDtoList().stream()
                .map(ProductSizeConverter::convert)
                .toList();

        return Dish.builder()
                .id(dto.id())
                .mealName(dto.mealName())
                .comment(dto.comment())
                .calories(dto.calories())
                .gr(dto.gr())
                .productSizes(productSizes)
                .build();
    }
}
