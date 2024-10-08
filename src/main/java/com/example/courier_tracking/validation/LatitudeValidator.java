package com.example.courier_tracking.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LatitudeValidator implements ConstraintValidator<ValidLatitude, Double> {

    @Override
    public void initialize(ValidLatitude constraintAnnotation) {
    }

    @Override
    public boolean isValid(Double latitude, ConstraintValidatorContext context) {
        return latitude != null && latitude >= -90 && latitude <= 90;
    }
}
