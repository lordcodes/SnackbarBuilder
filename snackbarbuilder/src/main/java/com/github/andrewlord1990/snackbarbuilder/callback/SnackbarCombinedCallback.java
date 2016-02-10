package com.github.andrewlord1990.snackbarbuilder.callback;

import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Callback;

public class SnackbarCombinedCallback extends SnackbarCallbackWrapper {

    protected SnackbarCallback snackbarCallback;
    protected Callback callback;

    public SnackbarCombinedCallback(Callback callback) {
        super(callback);
    }

    public SnackbarCombinedCallback(SnackbarCallback snackbarCallback, Callback callback) {
        super(callback);

        this.snackbarCallback = snackbarCallback;
    }

    @Override
    public void onDismissed(Snackbar snackbar, @DismissEvent int event) {
        super.onDismissed(snackbar, event);

        if (snackbarCallback != null) {
            switch (event) {
                case Callback.DISMISS_EVENT_SWIPE:
                    snackbarCallback.onSnackbarSwiped(snackbar);
                    break;
                case Callback.DISMISS_EVENT_ACTION:
                    snackbarCallback.onSnackbarActionPressed(snackbar);
                    break;
                case Callback.DISMISS_EVENT_TIMEOUT:
                    snackbarCallback.onSnackbarTimedOut(snackbar);
                    break;
                case Callback.DISMISS_EVENT_MANUAL:
                    snackbarCallback.onSnackbarManuallyDismissed(snackbar);
                    break;
                case Callback.DISMISS_EVENT_CONSECUTIVE:
                    snackbarCallback.onSnackbarDismissedAfterAnotherShown(snackbar);
                    break;
            }
            snackbarCallback.onSnackbarDismissed(snackbar);
        }
    }

    @Override
    public void onShown(Snackbar snackbar) {
        super.onShown(snackbar);
        
        if (snackbarCallback != null) {
            snackbarCallback.onSnackbarShown(snackbar);
        }
    }
}
