package com.petstore.api.controller.product;

import com.petstore.api.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductsSearchResponse {
    private List<Product> results;
}
