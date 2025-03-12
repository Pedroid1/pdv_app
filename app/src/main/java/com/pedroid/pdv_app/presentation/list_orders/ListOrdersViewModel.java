package com.pedroid.pdv_app.presentation.list_orders;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pedroid.pdv_app.domain.model.Order;
import com.pedroid.pdv_app.domain.use_cases.order_repository.DeleteOrderUseCase;
import com.pedroid.pdv_app.domain.use_cases.order_repository.FetchAllOrdersUseCase;
import com.pedroid.pdv_app.presentation.utils.Event;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class ListOrdersViewModel extends ViewModel {

    private final CompositeDisposable disposables;
    private final FetchAllOrdersUseCase fetchAllOrdersUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;
    private final MutableLiveData<List<OrderListAdapterItem>> orders = new MutableLiveData<>();
    private final MutableLiveData<Event<String>> errorMessage = new MutableLiveData<>();

    @Inject
    public ListOrdersViewModel(FetchAllOrdersUseCase fetchAllOrdersUseCase, DeleteOrderUseCase deleteOrderUseCase) {
        this.fetchAllOrdersUseCase = fetchAllOrdersUseCase;
        this.deleteOrderUseCase = deleteOrderUseCase;
        disposables = new CompositeDisposable();

        fetchAllOrders();
    }

    public void fetchAllOrders() {
        disposables.add(
                fetchAllOrdersUseCase.execute()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                this::generateRecyclerList,
                                throwable -> errorMessage.setValue(new Event<>("Erro ao buscar pedidos: " + throwable.getMessage()))
                        )
        );
    }

    public void deleteOrder(int orderId) {
        disposables.add(
                deleteOrderUseCase.execute(orderId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                success -> {

                                },
                                throwable -> errorMessage.setValue(new Event<>("Erro ao deletar o pedido: " + throwable.getMessage()))
                        )
        );
    }

    private void generateRecyclerList(List<Order> orders) {
        List<OrderListAdapterItem> items = new ArrayList<>();
        if (orders.isEmpty()) {
            items.add(new OrderListAdapterItem.EmptyItem());
        } else {
            for (Order order : orders) {
                items.add(new OrderListAdapterItem.OrderItem(order));
            }
        }
        this.orders.setValue(items);
    }

    public MutableLiveData<List<OrderListAdapterItem>> getOrders() {
        return orders;
    }

    public MutableLiveData<Event<String>> getErrorMessage() {
        return errorMessage;
    }
}
