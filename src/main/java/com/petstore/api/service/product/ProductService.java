package com.petstore.api.service.product;

import com.petstore.api.domain.category.Category;
import com.petstore.api.domain.category.CategoryRepository;
import com.petstore.api.domain.producer.Producer;
import com.petstore.api.domain.producer.ProducerRepository;
import com.petstore.api.domain.product.Product;
import com.petstore.api.domain.product.ProductRepository;
import com.petstore.api.exception.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private static final String CATEGORY_NOT_FOUND = "Category is not found. Id: [%s]";
    private static final String PRODUCER_NOT_FOUND = "Producer is not found. Id: [%s]";

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProducerRepository producerRepository;

    public ProductService(final ProductRepository productRepository,
                          final CategoryRepository categoryRepository,
                          final ProducerRepository producerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.producerRepository = producerRepository;
    }

    public void add(final int categoryId, final int producerId, final Product product) {
        final Optional<Category> category = categoryRepository.getById(categoryId);
        category.orElseThrow(() -> new DataNotFoundException(String.format(CATEGORY_NOT_FOUND, categoryId)));

        final Optional<Producer> producer = producerRepository.getById(producerId);
        producer.orElseThrow(() -> new DataNotFoundException(String.format(PRODUCER_NOT_FOUND, producerId)));

        productRepository.add(categoryId, producerId, product);
    }
}
