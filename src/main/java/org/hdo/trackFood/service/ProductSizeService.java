package org.hdo.trackFood.service;

import lombok.RequiredArgsConstructor;
import org.hdo.trackFood.converter.ProductConverter;
import org.hdo.trackFood.converter.ProductSizeConverter;
import org.hdo.trackFood.dto.ProductSizeDto;
import org.hdo.trackFood.model.ProductSize;
import org.hdo.trackFood.repository.ProductSizeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSizeService {

    private ProductSizeRepository productSizeRepository;

    public ProductSize update(ProductSizeDto dto) {
        ProductSize productSize;
        if (dto.id() == null) {
            productSize = ProductSizeConverter.convert(dto);
        } else {
            productSize = ProductSize.builder()
                    .gr(dto.gr())
//                    .dish() // todo
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
