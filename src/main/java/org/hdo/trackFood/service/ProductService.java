package org.hdo.trackFood.service;

import lombok.RequiredArgsConstructor;
import org.hdo.trackFood.converter.ProductConverter;
import org.hdo.trackFood.dto.ProductDto;
import org.hdo.trackFood.model.Product;
import org.hdo.trackFood.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product update(ProductDto dto) {
        Product product;
        if (dto.id() == null) {
            product = ProductConverter.convert(dto);
        } else {
            product = productRepository.findById(dto.id())
                    .orElseGet(() -> ProductConverter.convert(dto));
            product.setName(dto.name());
            product.setCalories(dto.calories());
            product.setProteins(dto.proteins());
            product.setFats(dto.fats());
            product.setCarbohydrates(dto.carbohydrates());
            product.setIsFiber(dto.isFiber());
        }

        product = productRepository.save(product);

        return product;
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found"));
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}
