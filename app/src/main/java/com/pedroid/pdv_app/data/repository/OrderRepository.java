package com.pedroid.pdv_app.data.repository;

import com.pedroid.pdv_app.data.remote.api.OrderAPI;
import com.pedroid.pdv_app.domain.model.Order;
import com.pedroid.pdv_app.domain.repository.IOrderRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class OrderRepository implements IOrderRepository {

    private OrderAPI orderAPI;

    @Inject
    public OrderRepository(OrderAPI orderApi) {
        this.orderAPI = orderApi;
    }

    public Single<List<Order>> fetchAllOrders() {
        return orderAPI.getAllOrders();
    }

    public Single<Order> createOrder(Order order) {
        return orderAPI.createOrder(order);
    }

    public Single<Boolean> deleteOrder(int id) {
        return orderAPI.deleteOrder(id);
    }
}
