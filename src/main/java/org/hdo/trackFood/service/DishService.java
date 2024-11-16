package org.hdo.trackFood.service;

import lombok.RequiredArgsConstructor;
import org.hdo.trackFood.converter.DishConverter;
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
                    .gr(dto.gr())
                    .calories(dto.calories())
                    .build();
        }

        return dishRepository.save(dish);
    }

    public void addProductSize(Dish dish, ProductSize ps) {
        List<ProductSize> productSizes = dish.getProductSizes();
        productSizes.add(ps);
        if (dish.getCalories() == null || dish.getGr() == null) {
            recalculate(dish);
        } else {
            dish.setCalories(dish.getCalories() + ps.getProduct().getCalories());
            dish.setGr(dish.getGr() + ps.getGr());
        }
    }

    public void removeProductSize(Dish dish, ProductSize ps) {
        List<ProductSize> productSizes = dish.getProductSizes();
        productSizes.remove(ps);
        if (dish.getCalories() == null || dish.getGr() == null) {
            recalculate(dish);
        } else {
            dish.setCalories(dish.getCalories() - ps.getProduct().getCalories());
            dish.setGr(dish.getGr() - ps.getGr());
        }
    }

    private void recalculate(Dish dish) {
        List<ProductSize> productSizes = dish.getProductSizes();
        dish.setCalories(productSizes.stream()
                .map(p -> p.getProduct().getCalories())
                .reduce(0.0, Double::sum));
        dish.setGr(productSizes.stream()
                .map(ProductSize::getGr)
                .reduce(0L, Long::sum));
    }

}
