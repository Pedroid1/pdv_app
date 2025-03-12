package com.pedroid.pdv_app.presentation.create_order;

import androidx.lifecycle.ViewModel;

import com.pedroid.pdv_app.domain.use_cases.ui_validation.ValidateCustomerName;
import com.pedroid.pdv_app.domain.use_cases.ui_validation.ValidatePriceUseCase;
import com.pedroid.pdv_app.domain.use_cases.ui_validation.ValidateProductName;
import com.pedroid.pdv_app.domain.use_cases.ui_validation.ValidateQuantityUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CreateOrderViewModel extends ViewModel {

    private final ValidateCustomerName validateCustomerName;
    private final ValidateProductName validateProductName;
    private final ValidateQuantityUseCase validateQuantityUseCase;
    private final ValidatePriceUseCase validatePriceUseCase;

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
}
