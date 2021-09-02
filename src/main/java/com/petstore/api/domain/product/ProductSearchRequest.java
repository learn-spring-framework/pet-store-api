package com.petstore.api.domain.product;

import com.petstore.api.validation.CheckNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.StringUtils;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductSearchRequest {
    private static final String CATEGORY_ID_INVALID_MESSAGE = "CategoryId must be numeric and greater than 0";
    private static final String PRODUCER_ID_INVALID_MESSAGE = "ProducerId must be numeric and greater than 0";
    private static final String CODE_LENGTH_ERROR_MESSAGE = "Code length must be less than or equal 10";
    private static final String NAME_LENGTH_ERROR_MESSAGE = "Name length must be less than or equal 100";

    private final int codeLength = 10;
    private final int nameLength = 100;

    @CheckNumber(isNull = true, message = CATEGORY_ID_INVALID_MESSAGE)
    private String categoryId;
    @CheckNumber(isNull = true, message = PRODUCER_ID_INVALID_MESSAGE)
    private String producerId;
    @Length(max = codeLength, message = CODE_LENGTH_ERROR_MESSAGE)
    private String code;
    @Length(max = nameLength, message = NAME_LENGTH_ERROR_MESSAGE)
    private String name;

    public boolean isListAll() {
        return !StringUtils.hasText(categoryId)
                && !StringUtils.hasText(producerId)
                && !StringUtils.hasText(code)
                && !StringUtils.hasText(name);
    }
}
