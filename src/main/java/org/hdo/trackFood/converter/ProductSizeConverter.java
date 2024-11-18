package org.hdo.trackFood.converter;

import org.hdo.trackFood.dto.ProductSizeDto;
import org.hdo.trackFood.model.ProductSize;

public final class ProductSizeConverter {

    public static ProductSize convert(ProductSizeDto dto) {
        return ProductSize.builder()
                .id(dto.id())
                .gr(dto.gr())
                .product(ProductConverter.convert(dto.productDto()))
                .build();
    }

    public static ProductSizeDto convert(ProductSize productSize) {
        return new ProductSizeDto(productSize.getId(),
                productSize.getGr(),
                productSize.getDish() == null ? null : productSize.getDish().getId(),
                ProductConverter.convert(productSize.getProduct())
        );
    }

}
