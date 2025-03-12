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
import android.widget.Toast;

import com.pedroid.pdv_app.R;
import com.pedroid.pdv_app.databinding.FragmentCreateOrderBinding;
import com.pedroid.pdv_app.domain.UiText;
import com.pedroid.pdv_app.domain.ValidationResult;

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
    }

    private void setupOnClick() {
        binding.btnSalvarPedido.setOnClickListener(view -> {
            Toast.makeText(requireContext(), "Salvar pedido", Toast.LENGTH_SHORT).show();
        });
        binding.btnVerPedidos.setOnClickListener(view -> {
            Navigation.findNavController(requireView()).navigate(CreateOrderFragmentDirections.actionCreateOrderFragmentToListOrdersFragment());
        });
    }
}