package org.hdo.trackFood.controller;

import lombok.RequiredArgsConstructor;
import org.hdo.trackFood.converter.DayConsumeConverter;
import org.hdo.trackFood.dto.DayConsumeDto;
import org.hdo.trackFood.service.DayConsumeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DayConsumeController {

    private final DayConsumeService dayConsumeService;

    @GetMapping("/dayConsume/{id}")
    public DayConsumeDto getById(@PathVariable Long id) {
        return DayConsumeConverter.convert(dayConsumeService.getById(id));
    }

    @PostMapping("/dayConsume:update")
    public DayConsumeDto update(@RequestBody DayConsumeDto dto) {
        return DayConsumeConverter.convert(dayConsumeService.update(dto));
    }

    @PostMapping("/dayConsume:delete/{id}")
    public void delete(@PathVariable Long id) {
        dayConsumeService.delete(id);
    }

}
