package com.pedroid.pdv_app.domain.use_cases.order_repository;

import com.pedroid.pdv_app.data.repository.OrderRepository;

import io.reactivex.rxjava3.core.Single;
import javax.inject.Inject;

public class DeleteOrderUseCase {

    private final OrderRepository orderRepository;

    @Inject
    public DeleteOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Single<Boolean> execute(int orderId) {
        return orderRepository.deleteOrder(orderId);
    }
}
