package com.petstore.api.domain.category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<Category> getAll();
    Optional<Category> getById(final int id);
    void add(final String id);
}
