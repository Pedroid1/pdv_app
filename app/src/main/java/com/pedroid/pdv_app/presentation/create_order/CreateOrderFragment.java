package com.pedroid.pdv_app.presentation.create_order;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.pedroid.pdv_app.R;
import com.pedroid.pdv_app.databinding.FragmentCreateOrderBinding;
import com.pedroid.pdv_app.domain.model.Order;
import com.pedroid.pdv_app.presentation.utils.ViewUtils;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CreateOrderFragment extends Fragment {

    private FragmentCreateOrderBinding binding;
    private CreateOrderViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(CreateOrderViewModel.class);
        binding = FragmentCreateOrderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupOnClick();
        setupObservers();
    }

    private void setupObservers() {
        viewModel.getFieldErrorLiveData().observe(getViewLifecycleOwner(), fieldErrorValidationEvent -> {
            FieldErrorValidation fieldErrorValidation = fieldErrorValidationEvent.getContentIfNotHandled();
            if (fieldErrorValidation != null) {
                switch (fieldErrorValidation.getFieldError()) {
                    case CUSTOMER_NAME:
                        binding.tilCustomer.setError(fieldErrorValidation.getErrorMessage().asString(requireContext()));
                        break;
                    case PRODUCT_NAME:
                        binding.tilProduct.setError(fieldErrorValidation.getErrorMessage().asString(requireContext()));
                        break;
                    case QUANTITY:
                        binding.tilQuantity.setError(fieldErrorValidation.getErrorMessage().asString(requireContext()));
                        break;
                    case PRICE:
                        binding.tilPrice.setError(fieldErrorValidation.getErrorMessage().asString(requireContext()));
                        break;
                }
            }
        });
        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), messageEvent -> {
            String message = messageEvent.getContentIfNotHandled();
            if (message != null) {
                ViewUtils.showSnackbar(requireView(), message, Snackbar.LENGTH_LONG);
            }
        });
        viewModel.getOrderCreated().observe(getViewLifecycleOwner(), orderEvent -> {
            Order order = orderEvent.getContentIfNotHandled();
            if (order != null) {
                ViewUtils.showSnackbar(requireView(), getString(R.string.order_created), Snackbar.LENGTH_SHORT);
                resetAllEditText();

            }
        });
    }

    private void resetAllEditText() {
        binding.etCustomer.setText("");
        binding.etProduct.setText("");
        binding.etQuantity.setText("");
        binding.etPrice.setText("");

        binding.tilCustomer.setError(null);
        binding.tilProduct.setError(null);
        binding.tilQuantity.setError(null);
        binding.tilPrice.setError(null);
    }

    private void setupOnClick() {
        binding.btnSaveOrder.setOnClickListener(view -> {
            ViewUtils.hideKeyboard(requireView());
            viewModel.createOrder(
                    binding.etCustomer.getText().toString().trim(),
                    binding.etProduct.getText().toString().trim(),
                    binding.etQuantity.getText().toString().trim(),
                    binding.etPrice.getText().toString().trim()
            );
        });
        binding.btnShowOrders.setOnClickListener(view -> {
            Navigation.findNavController(requireView()).navigate(CreateOrderFragmentDirections.actionCreateOrderFragmentToListOrdersFragment());
        });
    }
}