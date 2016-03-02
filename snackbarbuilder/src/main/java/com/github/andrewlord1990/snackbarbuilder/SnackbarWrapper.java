/*
 * Copyright (C) 2016 Andrew Lord
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

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Callback;
import android.support.design.widget.Snackbar.Duration;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.github.andrewlord1990.snackbarbuilder.callback.SnackbarCallback;
import com.github.andrewlord1990.snackbarbuilder.callback.SnackbarCombinedCallback;

public class SnackbarWrapper {

    private Context context;

    private Snackbar snackbar;
    private TextView messageView;
    private Button actionView;

    private Callback callback;
    private SnackbarIconBuilder iconBuilder;

    //TODO: Append text
    //TODO: Tests

    /**
     * Create from wrapping a {@link Snackbar}.
     *
     * @param snackbar The {@link Snackbar} to wrap.
     */
    public SnackbarWrapper(Snackbar snackbar) {
        this.snackbar = snackbar;
        messageView = (TextView) getView().findViewById(R.id.snackbar_text);
        actionView = (Button) getView().findViewById(R.id.snackbar_action);
        iconBuilder = SnackbarIconBuilder.builder(snackbar);
        context = snackbar.getView().getContext();
    }

    /**
     * Set the action to be displayed in the {@link Snackbar}.
     *
     * @param actionTextResId String resource to display as an action.
     * @return This instance.
     */
    public SnackbarWrapper setActionText(@StringRes int actionTextResId) {
        actionView.setText(actionTextResId);
        return this;
    }

    /**
     * Set the action to be displayed in the {@link Snackbar}.
     *
     * @param actionText Text to display as an action.
     * @return This instance.
     */
    public SnackbarWrapper setActionText(CharSequence actionText) {
        actionView.setText(actionText);
        return this;
    }

    /**
     * Get the action displayed in the {@link Snackbar}.
     *
     * @return Text displayed as an action.
     */
    public CharSequence getActionText() {
        return actionView.getText();
    }

    /**
     * Set the callback to be invoked when the action is clicked.
     *
     * @param actionClickListener Callback to be invoked when the action is clicked.
     * @return This instance.
     */
    public SnackbarWrapper setActionClickListener(OnClickListener actionClickListener) {
        actionView.setOnClickListener(actionClickListener);
        return this;
    }

    /**
     * Set the action to be displayed in the {@link Snackbar} and the callback to
     * invoke when the action is clicked.
     *
     * @param actionTextResId     String resource to display as an action.
     * @param actionClickListener Callback to be invoked when the action is clicked.
     * @return This instance.
     */
    @NonNull
    public SnackbarWrapper setAction(@StringRes int actionTextResId, OnClickListener actionClickListener) {
        snackbar.setAction(actionTextResId, actionClickListener);
        return this;
    }

    /**
     * Set the action to be displayed in the {@link Snackbar}.
     *
     * @param actionText          Text to display as an action.
     * @param actionClickListener Callback to be invoked when the action is clicked.
     * @return This instance.
     */
    @NonNull
    public SnackbarWrapper setAction(CharSequence actionText, OnClickListener actionClickListener) {
        snackbar.setAction(actionText, actionClickListener);
        return this;
    }

    /**
     * Set the text color for the action on the {@link Snackbar}.
     *
     * @param colors Colors state list to apply to the action text.
     * @return This instance.
     */
    @NonNull
    public SnackbarWrapper setActionTextColor(ColorStateList colors) {
        snackbar.setActionTextColor(colors);
        return this;
    }

    /**
     * Set the text color for the action on the {@link Snackbar}.
     *
     * @param color Color to make the action text.
     * @return This instance.
     */
    @NonNull
    public SnackbarWrapper setActionTextColor(@ColorInt int color) {
        snackbar.setActionTextColor(color);
        return this;
    }

    /**
     * Get the text color for the action on the {@link Snackbar}.
     *
     * @return The action text color.
     */
    public ColorStateList getActionTextColorStateList() {
        return actionView.getTextColors();
    }

    /**
     * Get the text color for the action on the {@link Snackbar}.
     *
     * @return The action text color.
     */
    @ColorInt
    public int getActionTextColor() {
        return actionView.getCurrentTextColor();
    }

    /**
     * Set the text color for the action on the {@link Snackbar}.
     *
     * @param colorResId Color to make the action text.
     * @return This instance.
     */
    @NonNull
    public SnackbarWrapper setActionTextColorResId(@ColorRes int colorResId) {
        snackbar.setActionTextColor(ContextCompat.getColor(context, colorResId));
        return this;
    }

    /**
     * Make the action text on the {@link Snackbar} lowercase.
     *
     * @return This instance.
     */
    @NonNull
    public SnackbarWrapper setLowercaseActionText() {
        Compatibility.getInstance().setAllCaps(actionView, false);
        return this;
    }

    /**
     * Make the action text on the {@link Snackbar} uppercase.
     *
     * @return This instance.
     */
    @NonNull
    public SnackbarWrapper setUppercaseActionText() {
        Compatibility.getInstance().setAllCaps(actionView, true);
        return this;
    }

    /**
     * Update the message in this {@link Snackbar}.
     *
     * @param message The new message.
     * @return This instance.
     */
    @NonNull
    public SnackbarWrapper setText(@NonNull CharSequence message) {
        snackbar.setText(message);
        return this;
    }

    /**
     * Update the message in the {@link Snackbar}.
     *
     * @param messageResId The new message.
     * @return This instance.
     */
    @NonNull
    public SnackbarWrapper setText(@StringRes int messageResId) {
        snackbar.setText(messageResId);
        return this;
    }

    /**
     * Get the message shown in the {@link Snackbar}.
     *
     * @return The message shown.
     */
    public CharSequence getText() {
        return messageView.getText();
    }

    /**
     * Set the text color for the message shown in the {@link Snackbar}.
     *
     * @param color The message text color.
     * @return This instance.
     */
    public SnackbarWrapper setTextColor(@ColorInt int color) {
        messageView.setTextColor(color);
        return this;
    }

    /**
     * Set the text color for the message shown in the {@link Snackbar}.
     *
     * @param colors The message text color state list.
     * @return This instance.
     */
    public SnackbarWrapper setTextColor(ColorStateList colors) {
        messageView.setTextColor(colors);
        return this;
    }

    /**
     * Set the text color for the message shown in the {@link Snackbar}.
     *
     * @param colorResId The message text color resource.
     * @return This instance.
     */
    public SnackbarWrapper setTetColorRes(@ColorRes int colorResId) {
        messageView.setTextColor(ContextCompat.getColor(context, colorResId));
        return this;
    }

    /**
     * Set the total time to show the {@link Snackbar} for.
     *
     * @param duration The length of time for which to show the {@link Snackbar},
     *                 can either be one of the predefined lengths: LENGTH_SHORT,
     *                 LENGTH_LONG or a custom duration in milliseconds.
     * @return This instance.
     */
    @NonNull
    public SnackbarWrapper setDuration(@Duration int duration) {
        snackbar.setDuration(duration);
        return this;
    }

    /**
     * Get the total duration to show the {@link Snackbar} for.
     *
     * @return The total duration to show for.
     */
    @Duration
    public int getDuration() {
        return snackbar.getDuration();
    }

    /**
     * Get the {@link Snackbar}'s view.
     *
     * @return This {@link Snackbar}'s view.
     */
    @NonNull
    public View getView() {
        return snackbar.getView();
    }

    /**
     * Set the background color of the {@link Snackbar}.
     *
     * @param color The background color.
     * @return This instance.
     */
    public SnackbarWrapper setBackgroundColor(@ColorInt int color) {
        getView().setBackgroundColor(color);
        return this;
    }

    /**
     * Set the background color of the {@link Snackbar}.
     *
     * @param colorResId The background color resource.
     * @return This instance.
     */
    public SnackbarWrapper setBackgroundColorRes(@ColorRes int colorResId) {
        getView().setBackgroundColor(ContextCompat.getColor(context, colorResId));
        return this;
    }

    /**
     * Set the callback to be called when the {@link Snackbar} changes visibility.
     * This will overwrite any callback that has already been set on the {@link Snackbar}.
     *
     * @param callback The callback to be called.
     * @return This instance.
     */
    @NonNull
    public SnackbarWrapper setCallback(Callback callback) {
        snackbar.setCallback(callback);
        this.callback = callback;
        return this;
    }

    /**
     * Set the callback to be called when the {@link Snackbar} changes visibility.
     * If {@link this.setCallback(Callback)} has already been called, this will be
     * combined with the {@link Callback} specified.
     *
     * @param callback The callback to be called.
     * @return This instance.
     */
    @NonNull
    public SnackbarWrapper setSnackbarCallback(SnackbarCallback callback) {
        return setCallback(new SnackbarCombinedCallback(callback, this.callback));
    }

    /**
     * Set the icon at the start of the {@link Snackbar}.  If there is no icon
     * it will be added, or if there is then it will be replaced.
     *
     * @param iconResId The icon drawable resource to display.
     * @return This instance.
     */
    public SnackbarWrapper setIcon(@DrawableRes int iconResId) {
        return setIcon(ContextCompat.getDrawable(context, iconResId));
    }

    /**
     * Set the icon at the start of the {@link Snackbar}. If there is no icon
     * it will be added, or if there is then it will be replaced.
     *
     * @param icon The icon to display.
     * @return This instance.
     */
    public SnackbarWrapper setIcon(Drawable icon) {
        iconBuilder
                .icon(icon)
                .bindToSnackbar();
        return this;
    }

    /**
     * Set the margin to have before the icon of the {@link Snackbar}. This
     * margin will be left of the icon on left-to-right layouts and
     * right of the icon on right-to-left layouts.
     *
     * @param iconMarginStart The dimension resource for the margin to have before the icon.
     * @return This instance.
     */
    public SnackbarWrapper setIconMarginStart(@DimenRes int iconMarginStart) {
        return setIconMarginStartPixels(context.getResources().getDimensionPixelSize(iconMarginStart));
    }

    /**
     * Set the margin to have before the icon of the {@link Snackbar}. This
     * margin will be left of the icon on left-to-right layouts and
     * right of the icon on right-to-left layouts.
     *
     * @param iconMarginStartPixels The margin to have before the icon.
     * @return This instance.
     */
    public SnackbarWrapper setIconMarginStartPixels(int iconMarginStartPixels) {
        iconBuilder
                .iconMarginStartPixels(iconMarginStartPixels)
                .bindToSnackbar();
        return this;
    }

    /**
     * Set the margin to have after the icon of the {@link Snackbar}. This
     * margin will be right of the icon on left-to-right layouts and
     * left of the icon on right-to-left layouts.
     *
     * @param iconMarginEnd The dimension resource for the margin to have after the icon.
     * @return This instance.
     */
    public SnackbarWrapper setIconMarginEnd(@DimenRes int iconMarginEnd) {
        return setIconMarginEndPixels(context.getResources().getDimensionPixelSize(iconMarginEnd));
    }

    /**
     * Set the margin to have after the icon of the {@link Snackbar}. This
     * margin will be right of the icon on left-to-right layouts and
     * left of the icon on right-to-left layouts.
     *
     * @param iconMarginEndPixels The margin to have after the icon.
     * @return This instance.
     */
    public SnackbarWrapper setIconMarginEndPixels(int iconMarginEndPixels) {
        iconBuilder
                .iconMarginEndPixels(iconMarginEndPixels)
                .bindToSnackbar();
        return this;
    }

    /**
     * Show the {@link Snackbar}.
     *
     * @return This instance.
     */
    public SnackbarWrapper show() {
        snackbar.show();
        return this;
    }

    /**
     * Dismiss the {@link Snackbar}.
     *
     * @return This instance.
     */
    public SnackbarWrapper dismiss() {
        snackbar.dismiss();
        return this;
    }

    /**
     * Get whether the {@link Snackbar} is showing.
     *
     * @return Whether this {@link Snackbar} is currently being shown.
     */
    public boolean isShown() {
        return snackbar.isShown();
    }

    /**
     * Get whether the {@link Snackbar} is showing, or is queued to be shown next.
     *
     * @return Whether the {@link Snackbar} is currently being shown, or is queued to be
     * shown next.
     */
    public boolean isShownOrQueued() {
        return snackbar.isShownOrQueued();
    }

}
