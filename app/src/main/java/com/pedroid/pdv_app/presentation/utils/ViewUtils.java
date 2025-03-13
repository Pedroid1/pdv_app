package com.pedroid.pdv_app.presentation.utils;

import android.app.Service;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.snackbar.Snackbar;

public class ViewUtils {

    public static void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Service.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showSnackbar(View view, String message, Integer duration) {
        Snackbar.make(view, message, duration).show();
    }
}
