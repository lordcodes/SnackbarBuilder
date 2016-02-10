package com.github.andrewlord1990.toastbuilder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar.Duration;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.andrewlord1990.snackbarbuilder.R;

public class ToastBuilder {

    Context context;

    CharSequence message;

    View customView;

    int customViewMessageViewId = android.R.id.message;

    @Duration
    int duration = Toast.LENGTH_LONG;

    int messageTextColor;

    int gravity;
    int gravityOffsetX;
    int gravityOffsetY;

    public ToastBuilder(Context context) {
        this.context = context;
        loadThemeAttributes();
    }

    public ToastBuilder customView(View customView) {
        this.customView = customView;
        return this;
    }

    public ToastBuilder customViewMessageViewId(int customViewMessageViewId) {
        this.customViewMessageViewId = customViewMessageViewId;
        return this;
    }

    public ToastBuilder message(CharSequence message) {
        this.message = message;
        return this;
    }

    public ToastBuilder message(@StringRes int messageResId) {
        this.message = context.getString(messageResId);
        return this;
    }

    public ToastBuilder messageTextColorRes(@ColorRes int messageTextColor) {
        this.messageTextColor = getColor(messageTextColor);
        return this;
    }

    public ToastBuilder messageTextColor(int messageTextColor) {
        this.messageTextColor = messageTextColor;
        return this;
    }

    public ToastBuilder duration(@Duration int duration) {
        this.duration = duration;
        return this;
    }

    public ToastBuilder gravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public ToastBuilder gravityOffsetX(int gravityOffsetX) {
        this.gravityOffsetX = gravityOffsetX;
        return this;
    }

    public ToastBuilder gravityOffsetY(int gravityOffsetY) {
        this.gravityOffsetY = gravityOffsetY;
        return this;
    }

    @SuppressLint("ShowToast")
    public Toast build() {
        Toast toast = Toast.makeText(context, message, duration);

        TextView toastMessage = setupToastView(toast);
        setToastMessageTextColor(toastMessage);
        setToastGravity(toast);

        return toast;
    }

    private TextView setupToastView(Toast toast) {
        if (customView != null) {
            toast.setView(customView);

            if (message != null) {
                TextView messageView = (TextView) customView.findViewById(customViewMessageViewId);
                if (messageView != null) {
                    messageView.setText(message);
                    return messageView;
                }
            }
        }
        return getToastMessageView(toast.getView());
    }

    private void setToastMessageTextColor(TextView toastMessage) {
        if (messageTextColor != 0) {
            toastMessage.setTextColor(messageTextColor);
        }
    }

    private void setToastGravity(Toast toast) {
        if (gravity != 0) {
            toast.setGravity(gravity, gravityOffsetX, gravityOffsetY);
        }
    }

    private TextView getToastMessageView(View toastView) {
        return (TextView) toastView.findViewById(android.R.id.message);
    }

    private int getColor(@ColorRes int color) {
        return ContextCompat.getColor(context, color);
    }

    private void loadThemeAttributes() {
        TypedArray attrs = context.obtainStyledAttributes(getAttributes());
        try {
            messageTextColor = attrs.getColor(0, 0);
            int durationAttr = attrs.getInteger(1, Integer.MIN_VALUE);
            if (durationAttr > Integer.MIN_VALUE) {
                duration = durationAttr;
            }
        } finally {
            attrs.recycle();
        }
    }

    private int[] getAttributes() {
        return new int[]{
                R.attr.toastBuilder_messageTextColor,
                R.attr.toastBuilder_duration,
        };
    }

}
