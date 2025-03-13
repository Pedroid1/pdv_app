package com.pedroid.pdv_app.data.remote.api;

import com.pedroid.pdv_app.domain.model.Order;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderAPI {
    @GET("api/pedidos")
    Single<List<Order>> getAllOrders();

    @POST("api/pedidos")
    Single<Order> createOrder(@Body Order order);

    @DELETE("api/pedidos/{id}")
    Single<Boolean> deleteOrder(@Path("id") int id);
}
