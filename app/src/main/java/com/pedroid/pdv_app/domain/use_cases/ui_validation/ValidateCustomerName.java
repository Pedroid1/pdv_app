package com.pedroid.pdv_app.domain.use_cases.ui_validation;

import com.pedroid.pdv_app.R;
import com.pedroid.pdv_app.domain.UiText;
import com.pedroid.pdv_app.domain.ValidationResult;

public class ValidateCustomerName {

    public ValidationResult execute(String name) {
        if (name == null || name.trim().isEmpty()) {
            return new ValidationResult(false, new UiText.StringResource(R.string.invalid_name));
        }
        return new ValidationResult(true);
    }
}
