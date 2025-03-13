package com.pedroid.pdv_app.domain.use_cases.ui_validation;

import com.pedroid.pdv_app.R;
import com.pedroid.pdv_app.domain.UiText;
import com.pedroid.pdv_app.domain.ValidationResult;

public class ValidatePriceUseCase {
    public ValidationResult execute(String price) {
        try {
            double totalPrice = Double.parseDouble(price);
            if (totalPrice <= 0) {
                return new ValidationResult(false, new UiText.StringResource(R.string.invalid_total_price));
            }
        } catch (NumberFormatException e) {
            return new ValidationResult(false, new UiText.StringResource(R.string.invalid_total_price));
        }
        return new ValidationResult(true);
    }
}
