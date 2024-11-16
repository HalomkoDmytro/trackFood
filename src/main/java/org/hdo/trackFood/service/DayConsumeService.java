package org.hdo.trackFood.service;

import lombok.RequiredArgsConstructor;
import org.hdo.trackFood.model.DayConsume;
import org.hdo.trackFood.repository.DayConsumeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DayConsumeService {

    private final DayConsumeRepository dayConsumeRepository;

    private DayConsume getById(Long id) {
        return dayConsumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DayConsume not found"));
    }
}
