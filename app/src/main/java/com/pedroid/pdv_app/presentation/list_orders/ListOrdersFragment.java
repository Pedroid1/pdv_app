package com.pedroid.pdv_app.presentation.list_orders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.pedroid.pdv_app.databinding.FragmentListOrdersBinding;
import com.pedroid.pdv_app.presentation.utils.ViewUtils;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListOrdersFragment extends Fragment {

    private FragmentListOrdersBinding binding;
    private OrdersAdapter adapter;
    private ListOrdersViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ListOrdersViewModel.class);
        binding = FragmentListOrdersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupObservers();
        setupOnClick();
    }

    private void setupOnClick() {
        binding.imgBack.setOnClickListener(v -> {
            requireActivity().getOnBackPressedDispatcher().onBackPressed();
        });
    }

    private void setupObservers() {
        viewModel.getOrders().observe(getViewLifecycleOwner(), orderListAdapterItems -> {
            if (adapter == null) setupAdapter();
            adapter.submitList(orderListAdapterItems);
        });
        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), messageEvent -> {
            String message = messageEvent.getContentIfNotHandled();
            if (message != null) {
                ViewUtils.showSnackbar(requireView(), message, Snackbar.LENGTH_LONG);
            }
        });
    }

    private void setupAdapter() {
        adapter = new OrdersAdapter(OrdersAdapter.DIFFUTILS);
        binding.rvOrders.setAdapter(adapter);
    }
}