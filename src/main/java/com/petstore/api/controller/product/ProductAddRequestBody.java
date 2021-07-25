package com.petstore.api.controller.product;

import com.petstore.api.validation.CheckNumber;
import com.petstore.api.validation.CheckPrice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductAddRequestBody {

    private static final String CODE_REQUIRED_MESSAGE = "Code is required";
    private static final String CODE_LENGTH_INVALID_MESSAGE = "code length must be less than 10";
    private static final String PRODUCER_ID_INVALID_MESSAGE = "Producer id must be numeric and greater than 0";
    private static final String CATEGORY_ID_INVALID_MESSAGE = "Category id must be numeric and greater than 0";
    private static final String NAME_REQUIRED_MESSAGE = "Name is required";
    private static final String NAME_LENGTH_INVALID_MESSAGE = "Name length must be less than 100";
    private static final String PRICE_INVALID_MESSAGE = "Price must be numeric and greater than or equal to 0";
    private static final String QUANTITY_STOCK_INVALID_MESSAGE =
            "Quantity stock must be numeric and greater than or equal to 0";

    private static final int codeLength = 10;
    private static final int nameLength = 100;

    @NotBlank(message = CODE_REQUIRED_MESSAGE)
    @Length(max = codeLength, message = CODE_LENGTH_INVALID_MESSAGE)
    private String code;
    @CheckNumber(message = CATEGORY_ID_INVALID_MESSAGE)
    private String categoryId;
    @CheckNumber(message = PRODUCER_ID_INVALID_MESSAGE)
    private String producerId;
    @NotBlank(message = NAME_REQUIRED_MESSAGE)
    @Length(max = nameLength, message = NAME_LENGTH_INVALID_MESSAGE)
    private String name;
    @CheckPrice(message = PRICE_INVALID_MESSAGE)
    private String price;
    @CheckNumber(message = QUANTITY_STOCK_INVALID_MESSAGE)
    private String quantityStock;
}
