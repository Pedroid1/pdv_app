package com.pedroid.pdv_app.presentation.utils;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class ViewUtils {

    public static void showSnackbar(View view, String message, Integer duration) {
        Snackbar.make(view, message, duration).show();
    }
}
