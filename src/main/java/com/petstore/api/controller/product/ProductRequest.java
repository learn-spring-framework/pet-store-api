package com.petstore.api.controller.product;

import com.petstore.api.validation.CheckNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {
    private static final String CATEGORY_ID_INVALID_MESSAGE = "CategoryId must be numeric and greater than 0";

    @CheckNumber(message = CATEGORY_ID_INVALID_MESSAGE)
    private String categoryId;
}
