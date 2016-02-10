package com.github.andrewlord1990.snackbarbuilder.callback;

import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Callback;

public class SnackbarCallbackWrapper extends Callback {

    protected Callback callback;

    public SnackbarCallbackWrapper(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onDismissed(Snackbar snackbar, @DismissEvent int event) {
        if (callback != null) {
            callback.onDismissed(snackbar, event);
        }
    }

    @Override
    public void onShown(Snackbar snackbar) {
        if (callback != null) {
            callback.onShown(snackbar);
        }
    }
}
