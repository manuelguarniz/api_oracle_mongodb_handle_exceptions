package com.trujillodev.api.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.trujillodev.api.validators.annotations.MinValueAnnotation;

public class MinValueValidator implements ConstraintValidator<MinValueAnnotation, Integer> {
	private int minValueLength;

	@Override
	public void initialize(MinValueAnnotation annotation) {
		this.minValueLength = annotation.value();
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return value <= this.minValueLength;
	}

}
