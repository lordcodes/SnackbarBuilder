package com.github.andrewlord1990.snackbarbuilder;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Duration;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SnackbarBuilder {

    Context context;
    View parentView;
    CharSequence message;

    @Duration
    int duration = Snackbar.LENGTH_LONG;

    CharSequence actionText;
    OnClickListener actionClickListener;
    Snackbar.Callback callback;
    SnackbarCallback snackbarCallback;
    boolean actionAllCaps = true;

    int backgroundColor;
    int actionTextColor;
    int messageTextColor;
    int parentViewId;

    public SnackbarBuilder(View view) {
        parentView = view;
        context = parentView.getContext();
        loadThemeAttributes();
    }

    public SnackbarBuilder(Activity activity) {
        context = activity;
        loadThemeAttributes();
        parentView = activity.findViewById(parentViewId);
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

    public SnackbarBuilder duration(@Duration int duration) {
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

    public SnackbarBuilder backgroundColorRes(@ColorRes int backgroundColor) {
        this.backgroundColor = ContextCompat.getColor(context, backgroundColor);
        return this;
    }

    public SnackbarBuilder backgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
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

    public SnackbarBuilder lowercaseAction() {
        actionAllCaps = false;
        return this;
    }

    public Snackbar build() {
        Snackbar snackbar = Snackbar.make(parentView, message, duration);

        if (messageTextColor != 0) {
            TextView messageView = (TextView) snackbar.getView().findViewById(R.id.snackbar_text);
            messageView.setTextColor(messageTextColor);
        }

        if (actionTextColor != 0) {
            snackbar.setActionTextColor(actionTextColor);
        }

        if (backgroundColor != 0) {
            snackbar.getView().setBackgroundColor(backgroundColor);
        }

        if (actionClickListener == null) {
            actionClickListener = new OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            };
        }

        if (actionText != null) {
            snackbar.setAction(actionText, actionClickListener);
        }

        if (snackbarCallback != null) {
            snackbar.setCallback(new SnackbarCallbackWrapper(snackbarCallback, callback));
        } else {
            snackbar.setCallback(callback);
        }

        Button action = (Button) snackbar.getView().findViewById(R.id.snackbar_action);
        if (isApiAtLeast14()) {
            action.setAllCaps(actionAllCaps);
        }

        return snackbar;
    }

    private boolean isApiAtLeast14() {
        return VERSION.SDK_INT >= VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    private int getColor(@ColorRes int color) {
        return ContextCompat.getColor(context, color);
    }

    private void loadThemeAttributes() {
        TypedArray attrs = context.obtainStyledAttributes(getAttributes());
        try {
            messageTextColor = attrs.getColor(0, 0);
            actionTextColor = attrs.getColor(1, 0);
            parentViewId = attrs.getResourceId(2, 0);
            int durationAttr = attrs.getInteger(3, Integer.MIN_VALUE);
            if (durationAttr > Integer.MIN_VALUE) {
                duration = durationAttr;
            }
            backgroundColor = attrs.getColor(4, 0);
            loadFallbackAttributes(attrs);
        } finally {
            attrs.recycle();
        }
    }

    private void loadFallbackAttributes(TypedArray attrs) {
        if (messageTextColor == 0) {
            messageTextColor = getColor(R.color.default_message);
        }
        if (actionTextColor == 0) {
            actionTextColor = attrs.getColor(5, 0);
        }
    }

    private int[] getAttributes() {
        return new int[]{
                R.attr.snackbarBuilder_messageTextColor,
                R.attr.snackbarBuilder_actionTextColor,
                R.attr.snackbarBuilder_parentViewId,
                R.attr.snackbarBuilder_duration,
                R.attr.snackbarBuilder_backgroundColor,
                R.attr.colorAccent
        };
    }

}


