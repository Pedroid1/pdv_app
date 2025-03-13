package com.pedroid.pdv_app.domain.use_cases.ui_validation;

import org.junit.Test;
import static org.junit.Assert.*;

import com.pedroid.pdv_app.domain.ValidationResult;

public class ValidateCustomerNameTest {

    @Test
    public void testValidName() {
        ValidateCustomerName validator = new ValidateCustomerName();
        ValidationResult result = validator.execute("Pedro Henrique");
        assertTrue(result.isSuccessful());
    }

    @Test
    public void testNullName() {
        ValidateCustomerName validator = new ValidateCustomerName();
        ValidationResult result = validator.execute(null);
        assertFalse(result.isSuccessful());
    }

    @Test
    public void testEmptyName() {
        ValidateCustomerName validator = new ValidateCustomerName();
        ValidationResult result = validator.execute("");
        assertFalse(result.isSuccessful());
    }

    @Test
    public void testNameWithSpacesOnly() {
        ValidateCustomerName validator = new ValidateCustomerName();
        ValidationResult result = validator.execute("   ");
        assertFalse(result.isSuccessful());
    }
}