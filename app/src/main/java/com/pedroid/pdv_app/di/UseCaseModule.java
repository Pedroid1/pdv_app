package com.pedroid.pdv_app.di;

import com.pedroid.pdv_app.data.repository.OrderRepository;
import com.pedroid.pdv_app.domain.use_cases.order_repository.CreateOrderUseCase;
import com.pedroid.pdv_app.domain.use_cases.order_repository.DeleteOrderUseCase;
import com.pedroid.pdv_app.domain.use_cases.order_repository.FetchAllOrdersUseCase;
import com.pedroid.pdv_app.domain.use_cases.ui_validation.ValidateCustomerName;
import com.pedroid.pdv_app.domain.use_cases.ui_validation.ValidatePriceUseCase;
import com.pedroid.pdv_app.domain.use_cases.ui_validation.ValidateProductName;
import com.pedroid.pdv_app.domain.use_cases.ui_validation.ValidateQuantityUseCase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UseCaseModule {

    @Provides
    public CreateOrderUseCase provideCreateOrderUseCase(OrderRepository orderRepository) {
        return new CreateOrderUseCase(orderRepository);
    }

    @Provides
    public FetchAllOrdersUseCase provideFetchAllOrdersUseCase(OrderRepository orderRepository) {
        return new FetchAllOrdersUseCase(orderRepository);
    }

    @Provides
    public DeleteOrderUseCase provideDeleteOrderUseCase(OrderRepository orderRepository) {
        return new DeleteOrderUseCase(orderRepository);
    }

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
