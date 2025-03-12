package com.pedroid.pdv_app.domain.use_cases.ui_validation;

import com.pedroid.pdv_app.R;
import com.pedroid.pdv_app.domain.UiText;
import com.pedroid.pdv_app.domain.ValidationResult;

public class ValidateQuantityUseCase {
    public ValidationResult execute(String quantity) {
        try {
            int qty = Integer.parseInt(quantity);
            if (qty <= 0) {
                return new ValidationResult(false, new UiText.StringResource(R.string.invalid_quantity));
            }
        } catch (NumberFormatException e) {
            return new ValidationResult(false, new UiText.StringResource(R.string.invalid_quantity));
        }
        return new ValidationResult(true);
    }
}
