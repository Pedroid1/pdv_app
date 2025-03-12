package com.pedroid.pdv_app.presentation.create_order;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pedroid.pdv_app.R;
import com.pedroid.pdv_app.databinding.FragmentCreateOrderBinding;

public class CreateOrderFragment extends Fragment {

    private FragmentCreateOrderBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
            Toast.makeText(requireContext(), "Ver pedidos", Toast.LENGTH_SHORT).show();
        });
    }
}