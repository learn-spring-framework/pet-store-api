package com.petstore.api.validation.validator;

import com.petstore.api.validation.CheckPrice;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckPriceValidator implements ConstraintValidator<CheckPrice, String> {
    private static final float MIN_VALUE = 0.00f;
    private boolean isNull;

    @Override
    public void initialize(CheckPrice checkPrice) {
        this.isNull = checkPrice.isNull();
    }

    @Override
    public boolean isValid(String price, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(price)) {
            return isNull;
        }
        try {
            return Float.parseFloat(price) >= MIN_VALUE;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
