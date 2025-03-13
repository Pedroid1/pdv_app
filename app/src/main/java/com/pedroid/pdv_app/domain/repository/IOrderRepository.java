package com.pedroid.pdv_app.domain.repository;

import com.pedroid.pdv_app.domain.model.Order;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface IOrderRepository {

    Single<List<Order>> fetchAllOrders();
    Single<Order> createOrder(Order order);
    Single<Boolean> deleteOrder(int id);
}
