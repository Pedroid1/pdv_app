package com.pedroid.pdv_app.presentation.create_order;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pedroid.pdv_app.domain.ValidationResult;
import com.pedroid.pdv_app.domain.use_cases.ui_validation.ValidateCustomerName;
import com.pedroid.pdv_app.domain.use_cases.ui_validation.ValidatePriceUseCase;
import com.pedroid.pdv_app.domain.use_cases.ui_validation.ValidateProductName;
import com.pedroid.pdv_app.domain.use_cases.ui_validation.ValidateQuantityUseCase;
import com.pedroid.pdv_app.presentation.utils.Event;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CreateOrderViewModel extends ViewModel {

    private final ValidateCustomerName validateCustomerName;
    private final ValidateProductName validateProductName;
    private final ValidateQuantityUseCase validateQuantityUseCase;
    private final ValidatePriceUseCase validatePriceUseCase;

    private final MutableLiveData<Event<FieldErrorValidation>> fieldErrorLiveData = new MutableLiveData<>();

    @Inject
    public CreateOrderViewModel(ValidateCustomerName validateCustomerName,
                                ValidateProductName validateProductName,
                                ValidateQuantityUseCase validateQuantityUseCase,
                                ValidatePriceUseCase validatePriceUseCase) {
        this.validateCustomerName = validateCustomerName;
        this.validateProductName = validateProductName;
        this.validateQuantityUseCase = validateQuantityUseCase;
        this.validatePriceUseCase = validatePriceUseCase;
    }

    public void createOrder(String customerName, String product, String quantity, String price) {
        if (validateFields(customerName, product, quantity, price)) {
            //TODO Save in API
        }
    }

    private boolean validateFields(String customerName, String product, String quantity, String price) {
        ValidationResult customerNameResult = validateCustomerName.execute(customerName);
        if (!customerNameResult.isSuccessful()) {
            FieldErrorValidation fieldErrorValidation = new FieldErrorValidation(CreateOrderFields.CUSTOMER_NAME, customerNameResult.getErrorMessage());
            fieldErrorLiveData.postValue(new Event<>(fieldErrorValidation));
            return false;
        }

        ValidationResult productNameResult = validateProductName.execute(product);
        if (!productNameResult.isSuccessful()) {
            FieldErrorValidation fieldErrorValidation = new FieldErrorValidation(CreateOrderFields.PRODUCT_NAME, productNameResult.getErrorMessage());
            fieldErrorLiveData.postValue(new Event<>(fieldErrorValidation));
            return false;
        }

        ValidationResult quantityResult = validateQuantityUseCase.execute(quantity);
        if (!quantityResult.isSuccessful()) {
            FieldErrorValidation fieldErrorValidation = new FieldErrorValidation(CreateOrderFields.QUANTITY, quantityResult.getErrorMessage());
            fieldErrorLiveData.postValue(new Event<>(fieldErrorValidation));
            return false;
        }

        ValidationResult priceResult = validatePriceUseCase.execute(price);
        if (!priceResult.isSuccessful()) {
            FieldErrorValidation fieldErrorValidation = new FieldErrorValidation(CreateOrderFields.PRICE, priceResult.getErrorMessage());
            fieldErrorLiveData.postValue(new Event<>(fieldErrorValidation));
            return false;
        }
        return true;
    }

    public MutableLiveData<Event<FieldErrorValidation>> getFieldErrorLiveData() {
        return fieldErrorLiveData;
    }
}
