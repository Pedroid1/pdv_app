package com.pedroid.pdv_app.di;

import com.pedroid.pdv_app.domain.use_cases.ui_validation.ValidateCustomerName;
import com.pedroid.pdv_app.domain.use_cases.ui_validation.ValidatePriceUseCase;
import com.pedroid.pdv_app.domain.use_cases.ui_validation.ValidateProductName;
import com.pedroid.pdv_app.domain.use_cases.ui_validation.ValidateQuantityUseCase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
public class UseCasesModule {

    @Provides
    public ValidateCustomerName provideValidateCustomerNameUseCase() {
        return new ValidateCustomerName();
    }

    @Provides
    public ValidateProductName provideValidateProductNameUseCase() {
        return new ValidateProductName();
    }

    @Provides
    public ValidateQuantityUseCase provideValidateQuantityUseCase() {
        return new ValidateQuantityUseCase();
    }

    @Provides
    public ValidatePriceUseCase provideValidatePriceUseCase() {
        return new ValidatePriceUseCase();
    }
}
