package com.petstore.api.controller.product;

import com.petstore.api.controller.product.ProductGetByCategoryResponse.ProductRecord;
import com.petstore.api.domain.product.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductGetByCategoryResponseFactory {

    private static final int FIRST_INDEX = 0;

    public ProductGetByCategoryResponse toProductGetByCategoryResponse(final List<Product> products) {
        return ProductGetByCategoryResponse.builder()
                                           .categoryId(products.get(FIRST_INDEX).getCategoryId())
                                           .categoryName(products.get(FIRST_INDEX).getCategoryName())
                                           .products(toProductRecords(products))
                                           .build();

    }

    private List<ProductRecord> toProductRecords(final List<Product> products) {
        return products.stream().map(this::toProductRecord).collect(Collectors.toList());
    }

    private ProductRecord toProductRecord(final Product product) {
        return new ProductRecord(
                product.getId(),
                product.getCode(),
                product.getName(),
                product.getProducerName(),
                product.getPrice(),
                product.getQuantityStock(),
                product.getStatus()
        );
    }
}
