package com.petstore.api.repository.category;

import com.petstore.api.domain.category.Category;
import com.petstore.api.domain.category.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryMapper categoryMapper;

    public CategoryRepositoryImpl(final CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> getAll() {
        return categoryMapper.getAll();
    }

    @Override
    public Optional<Category> getById(final int id) {
        return Optional.ofNullable(categoryMapper.getById(id));
    }

    @Override
    public void add(final String id) {
        categoryMapper.add(id);
    }
}
