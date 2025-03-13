package com.pedroid.pdv_app.domain.use_cases.ui_validation;

import org.junit.Test;
import static org.junit.Assert.*;

import com.pedroid.pdv_app.domain.ValidationResult;

public class ValidatePriceUseCaseTest {

    @Test
    public void testValidPrice() {
        ValidatePriceUseCase validator = new ValidatePriceUseCase();
        ValidationResult result = validator.execute("25.99");
        assertTrue(result.isSuccessful());
    }

    @Test
    public void testZeroPrice() {
        ValidatePriceUseCase validator = new ValidatePriceUseCase();
        ValidationResult result = validator.execute("0");
        assertFalse(result.isSuccessful());
    }

    @Test
    public void testNegativePrice() {
        ValidatePriceUseCase validator = new ValidatePriceUseCase();
        ValidationResult result = validator.execute("-5.50");
        assertFalse(result.isSuccessful());
    }

    @Test
    public void testInvalidPriceFormat() {
        ValidatePriceUseCase validator = new ValidatePriceUseCase();
        ValidationResult result = validator.execute("abc");
        assertFalse(result.isSuccessful());
    }

    @Test
    public void testEmptyPrice() {
        ValidatePriceUseCase validator = new ValidatePriceUseCase();
        ValidationResult result = validator.execute("");
        assertFalse(result.isSuccessful());
    }
} 