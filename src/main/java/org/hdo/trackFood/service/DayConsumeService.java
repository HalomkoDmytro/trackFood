package org.hdo.trackFood.service;

import lombok.RequiredArgsConstructor;
import org.hdo.trackFood.converter.DishConverter;
import org.hdo.trackFood.dto.DayConsumeDto;
import org.hdo.trackFood.model.DayConsume;
import org.hdo.trackFood.model.Dish;
import org.hdo.trackFood.repository.DayConsumeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DayConsumeService {

    private final DayConsumeRepository dayConsumeRepository;
    private final DishService dishService;

    public DayConsume getById(Long id) {
        return dayConsumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DayConsume not found"));
    }

    public DayConsume update(DayConsumeDto dto) {
        DayConsume dayConsume;
        if (dto.id() == null) {
            dayConsume = new DayConsume();
        } else {
            dayConsume = dayConsumeRepository.findById(dto.id())
                    .orElseThrow(() -> new RuntimeException("DayConsume not found"));

            List<Dish> dishes = dto.dishes().stream()
                    .map(DishConverter::convert)
                    .toList();
            dayConsume.setComment(dayConsume.getComment());
            recalculate(dayConsume, dishes);
        }

        return dayConsumeRepository.save(dayConsume);
    }

    public void delete(Long id) {
        dayConsumeRepository.deleteById(id);
    }

    private void recalculate(DayConsume dayConsume, List<Dish> dishes) {
        dayConsume.setDishes(dishes);
        dayConsume.setGr(dishService.getGr(dishes));
        dayConsume.setCalories(dishService.getCalories(dishes));
    }

}
