package org.hdo.trackFood.converter;

import org.hdo.trackFood.dto.ProductDto;
import org.hdo.trackFood.model.Product;

public final class ProductConverter {

    public static Product convert(ProductDto dto) {
        return Product.builder()
                .id(dto.id())
                .name(dto.name())
                .calories(dto.calories())
                .proteins(dto.proteins())
                .fats(dto.fats())
                .carbohydrates(dto.carbohydrates())
                .isFiber(dto.isFiber())
                .build();
    }

    public static ProductDto convert(Product product) {
        return new ProductDto(product.getId(),
                product.getName(),
                product.getCalories(),
                product.getProteins(),
                product.getFats(),
                product.getCarbohydrates(),
                product.getIsFiber());
    }

}
