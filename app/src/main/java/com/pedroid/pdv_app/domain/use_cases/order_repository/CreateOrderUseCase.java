package com.pedroid.pdv_app.domain.use_cases.order_repository;

import com.pedroid.pdv_app.data.repository.OrderRepository;
import com.pedroid.pdv_app.domain.model.Order;

import io.reactivex.rxjava3.core.Single;
import javax.inject.Inject;

public class CreateOrderUseCase {

    private final OrderRepository orderRepository;

    @Inject
    public CreateOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Single<Order> execute(Order order) {
        return orderRepository.createOrder(order);
    }
}
