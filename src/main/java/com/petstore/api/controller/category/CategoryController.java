package com.petstore.api.controller.category;

import com.petstore.api.config.ApiPathConfig;
import com.petstore.api.controller.Success;
import com.petstore.api.domain.category.CategoryRepository;
import com.petstore.api.exception.InvalidParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
public class CategoryController {

    private static final String ADD_SUCCESS_MESSAGE = "Add category successfully";
    private static final String DELIMITER = ", ";

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping(ApiPathConfig.GET_ALL_CATEGORY_URL)
    public CategoriesResponse getAll() {
        return new CategoriesResponse(categoryRepository.getAll());
    }

    @PostMapping(ApiPathConfig.ADD_CATEGORY_URL)
    public Success add(@Valid @RequestBody final CategoryAddRequestBody request,
                       final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(getErrorMessage(bindingResult));
        }
        categoryRepository.add(request.getName());
        return new Success(HttpStatus.OK.value(), ADD_SUCCESS_MESSAGE);
    }

    private String getErrorMessage(final BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(DELIMITER));
    }
}
