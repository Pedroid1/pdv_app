package com.pedroid.pdv_app.di;

import com.pedroid.pdv_app.data.repository.OrderRepository;
import com.pedroid.pdv_app.domain.use_cases.order_repository.CreateOrderUseCase;
import com.pedroid.pdv_app.domain.use_cases.order_repository.DeleteOrderUseCase;
import com.pedroid.pdv_app.domain.use_cases.order_repository.FetchAllOrdersUseCase;

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
}
