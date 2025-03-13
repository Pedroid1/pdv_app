package com.pedroid.pdv_app.domain.use_cases.order_repository;

import com.pedroid.pdv_app.data.repository.OrderRepository;
import com.pedroid.pdv_app.domain.model.Order;

import io.reactivex.rxjava3.core.Single;
import java.util.List;
import javax.inject.Inject;

public class FetchAllOrdersUseCase {

    private final OrderRepository orderRepository;

    @Inject
    public FetchAllOrdersUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Single<List<Order>> execute() {
        return orderRepository.fetchAllOrders();
    }
}
