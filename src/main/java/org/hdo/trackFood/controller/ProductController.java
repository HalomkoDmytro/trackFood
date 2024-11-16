package org.hdo.trackFood.controller;

import lombok.RequiredArgsConstructor;
import org.hdo.trackFood.converter.ProductConverter;
import org.hdo.trackFood.dto.ProductDto;
import org.hdo.trackFood.model.Product;
import org.hdo.trackFood.service.ExelFileReaderUtilService;
import org.hdo.trackFood.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ExelFileReaderUtilService service;

    @GetMapping("test")
    public List<Product> hello() {
        List<Product> list = service.read().stream()
                .map(ProductConverter::convert)
                .map(productService::update)
                .toList();
        return list;
    }

    @PostMapping("/product:create")
    public ProductDto createProduct(@RequestBody ProductDto dto) {
        Product newProduct = productService.update(dto);
        return ProductConverter.convert(newProduct);
    }

    @GetMapping("/product:all")
    public List<ProductDto> getAll() {
        return productService.getAll()
                .stream().map(ProductConverter::convert)
                .toList();
    }

}
