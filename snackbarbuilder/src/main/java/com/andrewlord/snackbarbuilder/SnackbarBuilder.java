package com.andrewlord.snackbarbuilder;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.internal.widget.ThemeUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SnackbarBuilder {

    static int defaultActionTextColor;
    static int defaultMessageTextColor;

    @IdRes
    protected static int defaultParentViewId;

    Context context;
    View parentView;
    CharSequence message;
    SnackbarDuration duration = SnackbarDuration.LONG;
    CharSequence actionText;
    OnClickListener actionClickListener;
    Snackbar.Callback callback;
    SnackbarCallback snackbarCallback;
    int actionTextColor = defaultActionTextColor;
    int messageTextColor = defaultMessageTextColor;

    public SnackbarBuilder(View view) {
        this.parentView = view;
        context = view.getContext();

        initialiseDefaultActionTextColor();
        initialiseDefaultMessageTextColor();
    }

    public SnackbarBuilder(Activity activity) {
        this(activity.findViewById(defaultParentViewId));
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

    public void buildAndShow() {
        build().show();
    }

    private int getColor(@ColorRes int color) {
        return ContextCompat.getColor(context, color);
    }

    private static int getColor(Context context, @ColorRes int color) {
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

    public static void setDefaultActionTextColor(int defaultActionTextColor) {
        SnackbarBuilder.defaultActionTextColor = defaultActionTextColor;
    }

    public static void setDefaultActionTextColorRes(Context context,
                                                    @ColorRes int defaultActionTextColor) {
        SnackbarBuilder.defaultActionTextColor = getColor(context, defaultActionTextColor);
    }

    public static void setDefaultMessageTextColor(int defaultMessageTextColor) {
        SnackbarBuilder.defaultMessageTextColor = defaultMessageTextColor;
    }

    public static void setDefaultMessageTextColorRes(Context context,
                                                     @ColorRes int defaultMessageTextColor) {
        SnackbarBuilder.defaultMessageTextColor = getColor(context, defaultMessageTextColor);
    }

    public static void setDefaultParentViewId(@IdRes int defaultParentViewId) {
        SnackbarBuilder.defaultParentViewId = defaultParentViewId;
    }

}


