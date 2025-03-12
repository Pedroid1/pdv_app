package com.pedroid.pdv_app.presentation.list_orders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedroid.pdv_app.databinding.FragmentListOrdersBinding;
import com.pedroid.pdv_app.domain.model.Order;

import java.util.ArrayList;
import java.util.List;

public class ListOrdersFragment extends Fragment {

    private FragmentListOrdersBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListOrdersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<OrderListAdapterItem> orders = new ArrayList<>();
        orders.add(new OrderListAdapterItem.EmptyItem());
        //orders.add(new OrderListAdapterItem.OrderItem(new Order(1, "Pedro", "Fone", 1, 100D)));
        //orders.add(new OrderListAdapterItem.OrderItem(new Order(2, "Andressa", "Computador", 2, 10000D)));

        OrdersAdapter adapter = new OrdersAdapter(OrdersAdapter.DIFFUTILS);
        binding.rvPedidos.setAdapter(adapter);
        adapter.submitList(orders);
    }
}