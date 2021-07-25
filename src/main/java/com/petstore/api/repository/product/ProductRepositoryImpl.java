package com.petstore.api.repository.product;

import com.petstore.api.domain.product.Product;
import com.petstore.api.domain.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductMapper productMapper;

    @Autowired
    public ProductRepositoryImpl(final ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> getAll() {
        return productMapper.getAll();
    }

    @Override
    public List<Product> search(final String categoryId, final String producerId, final String code, final String name) {
        return productMapper.search(categoryId, producerId, code, name);
    }

    @Override
    public void add(final int categoryId, final int producerId, final Product product) {
        productMapper.add(categoryId, producerId, product);
    }
}
