package com.andrewlord.snackbarbuilder;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.andrewlord.snackbarbuilder.theme.ThemeAttr;

public class SnackbarBuilder {

    private static int defaultActionTextColor;
    private static int defaultMessageTextColor;

    @IdRes
    private static int defaultParentViewId;

    private Context context;
    private View parentView;
    private CharSequence message;
    private SnackbarDuration duration = SnackbarDuration.LONG;
    private CharSequence actionText;
    private OnClickListener actionClickListener;
    private Snackbar.Callback callback;
    private SnackbarActionCallback snackbarActionCallback;

    private int actionTextColor = defaultActionTextColor;
    private int messageTextColor = defaultMessageTextColor;

    public SnackbarBuilder(View view) {
        this.parentView = view;
        context = view.getContext();

        setDefaultActionTextColor();
        setDefaultMessageTextColor();
    }

    public SnackbarBuilder(Activity activity) {
        this(activity.findViewById(defaultParentViewId));
    }

    public SnackbarBuilder(Fragment fragment) {
        this(fragment.getActivity().findViewById(defaultParentViewId));
    }

    public SnackbarBuilder message(CharSequence message) {
        this.message = message;
        return this;
    }

    public SnackbarBuilder message(@StringRes int messageResId) {
        this.message = context.getString(messageResId);
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

    public SnackbarBuilder snackbarActionCallback(SnackbarActionCallback snackbarActionCallback) {
        this.snackbarActionCallback = snackbarActionCallback;
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

        if (snackbarActionCallback != null) {
            snackbar.setCallback(new SnackbarCallbackWrapper(snackbarActionCallback, this.callback));
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

    private void setDefaultMessageTextColor() {
        if (messageTextColor == 0) {
            messageTextColor = ThemeAttr.resolveColor(context,
                    R.attr.snackbarBuilder_messageTextColor,
                    getColor(R.color.default_message));
        }
    }

    private void setDefaultActionTextColor() {
        if (actionTextColor == 0) {
            int fallback = ThemeAttr.resolveColor(context, R.attr.colorAccent);
            actionTextColor = ThemeAttr.resolveColor(context,
                    R.attr.snackbarBuilder_actionTextColor, fallback);
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


