package com.pedroid.pdv_app.domain.use_cases.ui_validation;

import org.junit.Test;
import static org.junit.Assert.*;

import com.pedroid.pdv_app.domain.ValidationResult;

public class ValidateQuantityUseCaseTest {

    @Test
    public void testValidQuantity() {
        ValidateQuantityUseCase validator = new ValidateQuantityUseCase();
        ValidationResult result = validator.execute("5");
        assertTrue(result.isSuccessful());
    }

    @Test
    public void testZeroQuantity() {
        ValidateQuantityUseCase validator = new ValidateQuantityUseCase();
        ValidationResult result = validator.execute("0");
        assertFalse(result.isSuccessful());
    }

    @Test
    public void testNegativeQuantity() {
        ValidateQuantityUseCase validator = new ValidateQuantityUseCase();
        ValidationResult result = validator.execute("-3");
        assertFalse(result.isSuccessful());
    }

    @Test
    public void testInvalidQuantityFormat() {
        ValidateQuantityUseCase validator = new ValidateQuantityUseCase();
        ValidationResult result = validator.execute("abc");
        assertFalse(result.isSuccessful());
    }

    @Test
    public void testEmptyQuantity() {
        ValidateQuantityUseCase validator = new ValidateQuantityUseCase();
        ValidationResult result = validator.execute("");
        assertFalse(result.isSuccessful());
    }

    @Test
    public void testNullQuantity() {
        ValidateQuantityUseCase validator = new ValidateQuantityUseCase();
        ValidationResult result = validator.execute(null);
        assertFalse(result.isSuccessful());
    }
} 