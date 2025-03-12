package com.pedroid.pdv_app.domain;

import android.content.Context;

public abstract class UiText {
    public static class DynamicString extends UiText {
        private final String message;

        public DynamicString(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class StringResource extends UiText {
        private final int resId;
        private final Object[] args;

        public StringResource(int resId, Object... args) {
            this.resId = resId;
            this.args = args;
        }

        public int getResId() {
            return resId;
        }

        public Object[] getArgs() {
            return args;
        }
    }

    public String asString(Context context) {
        if (this instanceof DynamicString) {
            return ((DynamicString) this).getMessage();
        } else if (this instanceof StringResource) {
            StringResource res = (StringResource) this;
            return context.getString(res.getResId(), res.getArgs());
        }
        return "";
    }
}