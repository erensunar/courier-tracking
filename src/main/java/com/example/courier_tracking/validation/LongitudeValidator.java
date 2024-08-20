package com.example.courier_tracking.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LongitudeValidator implements ConstraintValidator<ValidLongitude, Double> {

    @Override
    public boolean isValid(Double longitude, ConstraintValidatorContext context) {
        return longitude != null && longitude >= -180 && longitude <= 180;
    }
}
