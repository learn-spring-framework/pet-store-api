package com.petstore.api.controller.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryAddRequestBody {
    private static final String NAME_REQUIRED_MESSAGE = "Category name is required";
    private static final String NAME_LENGTH_ERROR_MESSAGE = "Producer Name length must be less than or equal 50";

    private static final int nameLength = 50;

    @NotBlank(message = NAME_REQUIRED_MESSAGE)
    @Length(max = nameLength, message = NAME_LENGTH_ERROR_MESSAGE)
    private String name;
}
