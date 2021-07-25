package com.petstore.api.domain.product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAll();
    List<Product> search(final String categoryId, final String producerId, final String code, final String name);
    void add(final int categoryId, final int producerId, final Product product);
}
