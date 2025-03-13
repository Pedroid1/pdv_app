package com.pedroid.pdv_app.domain.use_cases.ui_validation;

import org.junit.Test;
import static org.junit.Assert.*;

import com.pedroid.pdv_app.domain.ValidationResult;

public class ValidateProductNameTest {

    @Test
    public void testValidProductName() {
        ValidateProductName validator = new ValidateProductName();
        ValidationResult result = validator.execute("Product X");
        assertTrue(result.isSuccessful());
    }

    @Test
    public void testNullProductName() {
        ValidateProductName validator = new ValidateProductName();
        ValidationResult result = validator.execute(null);
        assertFalse(result.isSuccessful());
    }

    @Test
    public void testEmptyProductName() {
        ValidateProductName validator = new ValidateProductName();
        ValidationResult result = validator.execute("");
        assertFalse(result.isSuccessful());
    }

    @Test
    public void testProductNameWithSpacesOnly() {
        ValidateProductName validator = new ValidateProductName();
        ValidationResult result = validator.execute("   ");
        assertFalse(result.isSuccessful());
    }
} 