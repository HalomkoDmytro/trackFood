package org.hdo.trackFood.service;

import lombok.RequiredArgsConstructor;
import org.hdo.trackFood.converter.ProductConverter;
import org.hdo.trackFood.converter.ProductSizeConverter;
import org.hdo.trackFood.dto.ProductSizeDto;
import org.hdo.trackFood.model.Dish;
import org.hdo.trackFood.model.ProductSize;
import org.hdo.trackFood.repository.DishRepository;
import org.hdo.trackFood.repository.ProductSizeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSizeService {

    private ProductSizeRepository productSizeRepository;
    private DishRepository dishRepository;

    public ProductSize update(ProductSizeDto dto) {
        ProductSize productSize;
        if (dto.id() == null) {
            productSize = ProductSizeConverter.convert(dto);
        } else {
            Dish dish = dishRepository.findById(dto.dishId())
                    .orElse(null);

            productSize = ProductSize.builder()
                    .gr(dto.gr())
                    .dish(dish)
                    .product(ProductConverter.convert(dto.productDto()))
                    .build();
        }

        return productSizeRepository.save(productSize);
    }

    public Long getGr(List<ProductSize> productSizes) {
        return productSizes.stream()
                .map(ProductSize::getGr)
                .reduce(0L, Long::sum);
    }

}
