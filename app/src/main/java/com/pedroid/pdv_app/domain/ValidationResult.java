package com.pedroid.pdv_app.domain;

public class ValidationResult {
    private final boolean successful;
    private final UiText errorMessage;

    public ValidationResult(boolean successful, UiText errorMessage) {
        this.successful = successful;
        this.errorMessage = errorMessage;
    }

    public ValidationResult(boolean successful) {
        this(successful, null);
    }

    public boolean isSuccessful() {
        return successful;
    }

    public UiText getErrorMessage() {
        return errorMessage;
    }
}