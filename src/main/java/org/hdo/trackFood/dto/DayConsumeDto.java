package org.hdo.trackFood.dto;

import java.sql.Timestamp;
import java.util.List;

public record DayConsumeDto(Long id,
                            Timestamp timestamp,
                            String comment,
                            Double calories,
                            Long gr,
                            List<DishDto> dishes) {
}
