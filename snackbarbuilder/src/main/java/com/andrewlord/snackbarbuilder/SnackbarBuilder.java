package com.andrewlord.snackbarbuilder;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.internal.widget.ThemeUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SnackbarBuilder {

    Context context;
    View parentView;
    CharSequence message;
    SnackbarDuration duration = SnackbarDuration.LONG;
    CharSequence actionText;
    OnClickListener actionClickListener;
    Snackbar.Callback callback;
    SnackbarCallback snackbarCallback;
    int actionTextColor;
    int messageTextColor;

    public SnackbarBuilder(View view) {
        parentView = view;
        context = parentView.getContext();
        setup();
    }

    public SnackbarBuilder(Activity activity) {
        context = activity;
        int parentViewId = getParentViewId();
        parentView = activity.findViewById(parentViewId);
        setup();
    }

    private void setup() {
        initialiseDefaultActionTextColor();
        initialiseDefaultMessageTextColor();
    }

    public SnackbarBuilder message(CharSequence message) {
        this.message = message;
        return this;
    }

    public SnackbarBuilder message(@StringRes int messageResId) {
        this.message = context.getString(messageResId);
        return this;
    }

    public SnackbarBuilder messageTextColorRes(@ColorRes int messageTextColor) {
        this.messageTextColor = ContextCompat.getColor(context, messageTextColor);
        return this;
    }

    public SnackbarBuilder messageTextColor(int messageTextColor) {
        this.messageTextColor = messageTextColor;
        return this;
    }

    public SnackbarBuilder duration(int duration) {
        this.duration = SnackbarDuration.fromInt(duration);
        return this;
    }

    public SnackbarBuilder duration(SnackbarDuration duration) {
        this.duration = duration;
        return this;
    }

    public SnackbarBuilder actionTextColorRes(@ColorRes int actionTextColor) {
        this.actionTextColor = ContextCompat.getColor(context, actionTextColor);
        return this;
    }

    public SnackbarBuilder actionTextColor(int actionTextColor) {
        this.actionTextColor = actionTextColor;
        return this;
    }

    public SnackbarBuilder actionText(@StringRes int actionText) {
        this.actionText = context.getString(actionText);
        return this;
    }

    public SnackbarBuilder actionText(String actionText) {
        this.actionText = actionText;
        return this;
    }

    public SnackbarBuilder actionClickListener(OnClickListener actionClickListener) {
        this.actionClickListener = actionClickListener;
        return this;
    }

    public SnackbarBuilder callback(Snackbar.Callback callback) {
        this.callback = callback;
        return this;
    }

    public SnackbarBuilder snackbarCallback(SnackbarCallback snackbarCallback) {
        this.snackbarCallback = snackbarCallback;
        return this;
    }

    public Snackbar build() {
        Snackbar snackbar = Snackbar.make(parentView, message, duration.getAsInt());

        if (messageTextColor != 0) {
            TextView messageView = (TextView) snackbar.getView().findViewById(R.id.snackbar_text);
            messageView.setTextColor(messageTextColor);
        }

        if (actionTextColor != 0) {
            snackbar.setActionTextColor(actionTextColor);
        }

        if (actionText != null) {
            snackbar.setAction(actionText, actionClickListener);
        }

        if (snackbarCallback != null) {
            snackbar.setCallback(new SnackbarCallbackWrapper(snackbarCallback, callback));
        } else {
            snackbar.setCallback(callback);
        }

        return snackbar;
    }

    private int getColor(@ColorRes int color) {
        return ContextCompat.getColor(context, color);
    }

    private void initialiseDefaultMessageTextColor() {
        if (messageTextColor == 0) {
            messageTextColor = ThemeUtils.getThemeAttrColor(context,
                    R.attr.snackbarBuilder_messageTextColor);
            if (messageTextColor == 0) {
                messageTextColor = getColor(R.color.default_message);
            }
        }
    }

    private void initialiseDefaultActionTextColor() {
        if (actionTextColor == 0) {
            actionTextColor = ThemeUtils.getThemeAttrColor(context,
                    R.attr.snackbarBuilder_actionTextColor);
            if (actionTextColor == 0) {
                actionTextColor = ThemeUtils.getThemeAttrColor(context, R.attr.colorAccent);
            }
        }
    }

    private int getParentViewId() {
        return getThemeAttr(R.attr.snackbarBuilder_parentViewId);
    }

    private int getThemeAttr(int attr) {
        int[] attrs = new int[]{attr};
        TypedArray a = context.obtainStyledAttributes(null, attrs);
        try {
            return a.getResourceId(0, 0);
        } finally {
            a.recycle();
        }
    }

}


