package com.pedroid.pdv_app.presentation.list_orders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.pedroid.pdv_app.databinding.EmptyItemBinding;
import com.pedroid.pdv_app.databinding.OrderItemBinding;
import com.pedroid.pdv_app.domain.model.Order;

import java.util.Objects;

public class OrdersAdapter extends ListAdapter<OrderListAdapterItem, OrdersAdapter.OrderViewHolder> {

    protected OrdersAdapter(@NonNull DiffUtil.ItemCallback<OrderListAdapterItem> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (EnumOrderListAdapterViewType.getEnumByOrdinal(viewType) == EnumOrderListAdapterViewType.ORDER) {
            OrderItemBinding binding = OrderItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new OrderViewHolder.OrderItem(binding);
        } else {
            EmptyItemBinding binding = EmptyItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new OrderViewHolder.EmptyItem(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderListAdapterItem item = getItem(position);
        if (holder instanceof OrderViewHolder.OrderItem) {
            ((OrderViewHolder.OrderItem) holder).bind((OrderListAdapterItem.OrderItem) item);
        } else if (holder instanceof OrderViewHolder.EmptyItem) {
            ((OrderViewHolder.EmptyItem) holder).bind();
        }
    }

    public static abstract class OrderViewHolder extends RecyclerView.ViewHolder {

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public static class OrderItem extends OrderViewHolder {
            private final OrderItemBinding binding;

            public OrderItem(OrderItemBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }

            public void bind(OrderListAdapterItem.OrderItem orderItem) {
                binding.customerName.setText(orderItem.getOrder().getCustomerName());

                int quantity = orderItem.getOrder().getQuantity();
                binding.quantity.setText(String.format("%dx", quantity));

                binding.product.setText(orderItem.getOrder().getProduct());

                double price = orderItem.getOrder().getPrice();
                String formattedPrice = String.format("R$ %.2f", price);
                binding.total.setText(formattedPrice);
            }
        }

        public static class EmptyItem extends OrderViewHolder {
            private final EmptyItemBinding binding;

            public EmptyItem(EmptyItemBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }

            public void bind() {

            }
        }
    }

    public static final DiffUtil.ItemCallback<OrderListAdapterItem> DIFFUTILS = new DiffUtil.ItemCallback<OrderListAdapterItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull OrderListAdapterItem oldItem, @NonNull OrderListAdapterItem newItem) {
            if (oldItem instanceof OrderListAdapterItem.OrderItem && newItem instanceof OrderListAdapterItem.OrderItem) {
                Order oldOrder = ((OrderListAdapterItem.OrderItem) oldItem).getOrder();
                Order newOrder = ((OrderListAdapterItem.OrderItem) newItem).getOrder();

                if (oldOrder != null && newOrder != null) {
                    return Objects.equals(oldOrder.getId(), newOrder.getId());
                }
            }
            return oldItem.getClass().equals(newItem.getClass());
        }

        @Override
        public boolean areContentsTheSame(@NonNull OrderListAdapterItem oldItem, @NonNull OrderListAdapterItem newItem) {
            if (oldItem instanceof OrderListAdapterItem.OrderItem && newItem instanceof OrderListAdapterItem.OrderItem) {
                Order oldOrder = ((OrderListAdapterItem.OrderItem) oldItem).getOrder();
                Order newOrder = ((OrderListAdapterItem.OrderItem) newItem).getOrder();

                if (oldOrder != null && newOrder != null) {
                    return Objects.equals(oldOrder.getCustomerName(), newOrder.getCustomerName()) &&
                            Objects.equals(oldOrder.getProduct(), newOrder.getProduct()) &&
                            Objects.equals(oldOrder.getQuantity(), newOrder.getQuantity()) &&
                            Objects.equals(oldOrder.getPrice(), newOrder.getPrice());
                }
            }
            return false;
        }
    };

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType().ordinal();
    }
}
