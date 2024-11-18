package org.hdo.trackFood.controller;

import lombok.RequiredArgsConstructor;
import org.hdo.trackFood.converter.DishConverter;
import org.hdo.trackFood.dto.DishDto;
import org.hdo.trackFood.service.DishService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping("/dish/{id}")
    public DishDto getDishById(Long id) {
        return DishConverter.convert(dishService.getById(id));
    }

    @PostMapping("/dish:update")
    public DishDto updateDish(@RequestBody DishDto dto) {
        return DishConverter.convert(dishService.update(dto));
    }

    @PostMapping("/dish:delete/{id}")
    public void deleteDish(@PathVariable Long id) {
        dishService.delete(id);
    }

}
