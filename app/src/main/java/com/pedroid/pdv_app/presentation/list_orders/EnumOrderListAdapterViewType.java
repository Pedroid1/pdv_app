package com.pedroid.pdv_app.presentation.list_orders;

public enum EnumOrderListAdapterViewType {
    ORDER,
    EMPTY;

    public static EnumOrderListAdapterViewType getEnumByOrdinal(int index) {
        return values()[index];
    }
}