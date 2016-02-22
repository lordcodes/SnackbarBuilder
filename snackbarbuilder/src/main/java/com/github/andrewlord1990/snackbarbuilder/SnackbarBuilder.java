/*
 * Copyright (C) 2015 Andrew Lord
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

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

import com.github.andrewlord1990.snackbarbuilder.callback.SnackbarCallback;
import com.github.andrewlord1990.snackbarbuilder.callback.SnackbarCombinedCallback;

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
        this.messageTextColor = getColor(messageTextColor);
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
        this.actionTextColor = getColor(actionTextColor);
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
        this.backgroundColor = getColor(backgroundColor);
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

        setMessageTextColor(snackbar);
        setActionTextColor(snackbar);
        setBackgroundColor(snackbar);
        setAction(snackbar);
        setCallback(snackbar);
        setActionAllCaps(snackbar);

        return snackbar;
    }

    private void setMessageTextColor(Snackbar snackbar) {
        if (messageTextColor != 0) {
            TextView messageView = (TextView) snackbar.getView().findViewById(R.id.snackbar_text);
            messageView.setTextColor(messageTextColor);
        }
    }

    private void setActionTextColor(Snackbar snackbar) {
        if (actionTextColor != 0) {
            snackbar.setActionTextColor(actionTextColor);
        }
    }

    private void setBackgroundColor(Snackbar snackbar) {
        if (backgroundColor != 0) {
            snackbar.getView().setBackgroundColor(backgroundColor);
        }
    }

    private void setAction(Snackbar snackbar) {
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
    }

    private void setCallback(Snackbar snackbar) {
        if (snackbarCallback != null) {
            snackbar.setCallback(new SnackbarCombinedCallback(snackbarCallback, callback));
        } else {
            snackbar.setCallback(callback);
        }
    }

    private void setActionAllCaps(Snackbar snackbar) {
        Button action = (Button) snackbar.getView().findViewById(R.id.snackbar_action);
        if (isApiAtLeast14()) {
            action.setAllCaps(actionAllCaps);
        }
    }

    private boolean isApiAtLeast14() {
        return VERSION.SDK_INT >= VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    private int getColor(@ColorRes int color) {
        return ContextCompat.getColor(context, color);
    }

    private void loadThemeAttributes() {
        TypedArray attrs = context.obtainStyledAttributes(R.styleable.SnackbarBuilderStyle);
        try {
            loadMessageTextColor(attrs);
            loadActionTextColor(attrs);
            loadParentViewId(attrs);
            loadDuration(attrs);
            loadBackgroundColor(attrs);

            loadFallbackAttributes(attrs);
        } finally {
            attrs.recycle();
        }
    }

    private void loadBackgroundColor(TypedArray attrs) {
        backgroundColor = attrs.getColor(
                R.styleable.SnackbarBuilderStyle_snackbarBuilder_backgroundColor, 0);
    }

    private void loadDuration(TypedArray attrs) {
        int durationAttr = attrs.getInteger(
                R.styleable.SnackbarBuilderStyle_snackbarBuilder_duration, Integer.MIN_VALUE);
        if (durationAttr > Integer.MIN_VALUE) {
            duration = durationAttr;
        }
    }

    private void loadParentViewId(TypedArray attrs) {
        parentViewId = attrs.getResourceId(
                R.styleable.SnackbarBuilderStyle_snackbarBuilder_parentViewId, 0);
    }

    private void loadActionTextColor(TypedArray attrs) {
        actionTextColor = attrs.getColor(
                R.styleable.SnackbarBuilderStyle_snackbarBuilder_actionTextColor, 0);
    }

    private void loadMessageTextColor(TypedArray attrs) {
        messageTextColor = attrs.getColor(
                R.styleable.SnackbarBuilderStyle_snackbarBuilder_messageTextColor, 0);
    }

    private void loadFallbackAttributes(TypedArray attrs) {
        if (messageTextColor == 0) {
            messageTextColor = getColor(R.color.default_message);
        }
        if (actionTextColor == 0) {
            actionTextColor = attrs.getColor(R.styleable.SnackbarBuilderStyle_colorAccent, 0);
        }
    }

}


