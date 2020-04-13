package com.trujillodev.api.validators.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.trujillodev.api.validators.MinValueValidator;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { MinValueValidator.class })
public @interface MinValueAnnotation {
 
   String message() default "Valor incorrecto";
 
   Class<?>[] groups() default {};
 
   Class<? extends Payload>[] payload() default {};
 
   int value();
 
}