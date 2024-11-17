package org.hdo.trackFood.dto;

import org.hdo.trackFood.model.ProductSize;

import java.util.List;

public record DishDto(Long id,
                      String mealName,
                      String comment,
                      Double calories,
                      Long gr,
                      List<ProductSizeDto> productSizeDtoList) {
}
