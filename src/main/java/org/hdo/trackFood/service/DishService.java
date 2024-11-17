package org.hdo.trackFood.service;

import lombok.RequiredArgsConstructor;
import org.hdo.trackFood.converter.DishConverter;
import org.hdo.trackFood.converter.ProductSizeConverter;
import org.hdo.trackFood.dto.DishDto;
import org.hdo.trackFood.model.Dish;
import org.hdo.trackFood.model.ProductSize;
import org.hdo.trackFood.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;
    private final ProductSizeService productSizeService;

    public Dish getById(int id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MealSize not found"));
    }

    public Dish update(DishDto dto) {
        Dish dish;
        if (dto.id() == null) {
            dish = DishConverter.convert(dto);
        } else {
            dish = Dish.builder()
                    .id(dto.id())
                    .mealName(dto.mealName())
                    .comment(dto.comment())
                    .build();
            List<ProductSize> productSizes = dto.productSizeDtoList().stream()
                    .map(ProductSizeConverter::convert)
                    .toList();

            recalculate(dish, productSizes);
        }

        return dishRepository.save(dish);
    }

    public void delete(int id) {
        dishRepository.deleteById(id);
    }

    public Long getGr(List<Dish> dishes) {
        return dishes.stream()
                .map(Dish::getGr)
                .reduce(0L, Long::sum);
    }

    public Double getCalories(List<Dish> dishes) {
        return dishes.stream()
                .map(Dish::getCalories)
                .reduce(0.0, Double::sum);
    }

    private void recalculate(Dish dish, List<ProductSize> productSizes) {
        dish.setProductSizes(productSizes);
        dish.setGr(productSizeService.getGr(productSizes));
    }

}
