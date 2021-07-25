package com.petstore.api.controller.producer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProducerAddRequestBody {
    private static final String NAME_REQUIRED_MESSAGE = "Producer name is a required field";
    private static final String NAME_LENGTH_ERROR_MESSAGE = "Producer Name length must be less than or equal 20";
    private final int nameLength = 20;

    @NotBlank(message = NAME_REQUIRED_MESSAGE)
    @Length(max = nameLength, message = NAME_LENGTH_ERROR_MESSAGE)
    private String name;
}
