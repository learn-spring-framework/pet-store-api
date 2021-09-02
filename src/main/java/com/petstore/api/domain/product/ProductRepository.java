package com.petstore.api.domain.product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAll();
    List<Product> search(final ProductSearchRequest productSearchRequest);
    void add(final int categoryId, final int producerId, final Product product);
    List<Product> getByCategoryId(final int categoryId);
}
