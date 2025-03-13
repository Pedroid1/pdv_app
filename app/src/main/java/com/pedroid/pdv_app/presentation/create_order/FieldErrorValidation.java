package com.pedroid.pdv_app.presentation.create_order;

import com.pedroid.pdv_app.domain.UiText;

public class FieldErrorValidation {
    private final CreateOrderFields createOrderFields;
    private final UiText errorMessage;

    public FieldErrorValidation(CreateOrderFields createOrderFields, UiText errorMessage) {
        this.createOrderFields = createOrderFields;
        this.errorMessage = errorMessage;
    }

    public CreateOrderFields getFieldError() {
        return createOrderFields;
    }

    public UiText getErrorMessage() {
        return errorMessage;
    }
}

