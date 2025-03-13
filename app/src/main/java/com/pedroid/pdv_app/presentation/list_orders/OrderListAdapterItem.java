package com.pedroid.pdv_app.presentation.list_orders;

import com.pedroid.pdv_app.domain.model.Order;

public abstract class OrderListAdapterItem {
    private final EnumOrderListAdapterViewType viewType;

    public OrderListAdapterItem(EnumOrderListAdapterViewType viewType) {
        this.viewType = viewType;
    }

    public EnumOrderListAdapterViewType getViewType() {
        return viewType;
    }
    public static class OrderItem extends OrderListAdapterItem {
        private final Order order;

        public OrderItem(Order order) {
            super(EnumOrderListAdapterViewType.ORDER);
            this.order = order;
        }

        public Order getOrder() {
            return order;
        }
    }

    public static class EmptyItem extends OrderListAdapterItem {
        public EmptyItem() {
            super(EnumOrderListAdapterViewType.EMPTY);
        }
    }
}
