package com.petstore.api.validation.validator;

import com.petstore.api.validation.CheckPrice;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckPriceValidator implements ConstraintValidator<CheckPrice, String> {
    private static final float MIN_VALUE = 0.00f;

    @Override
    public void initialize(CheckPrice checkPrice) {
    }

    @Override
    public boolean isValid(String price, ConstraintValidatorContext context) {
        try {
            return Float.parseFloat(price) >= MIN_VALUE;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
