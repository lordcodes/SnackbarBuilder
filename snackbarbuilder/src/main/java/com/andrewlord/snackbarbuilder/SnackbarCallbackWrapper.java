package com.andrewlord.snackbarbuilder;

import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Callback;

public class SnackbarCallbackWrapper extends Callback {

    protected SnackbarCallback snackbarCallback;
    protected Callback callback;

    public SnackbarCallbackWrapper(SnackbarCallback snackbarCallback, Callback callback) {
        this.snackbarCallback = snackbarCallback;
        this.callback = callback;
    }

    @Override
    public void onDismissed(Snackbar snackbar, @DismissEvent int event) {
        if (callback != null) {
            callback.onDismissed(snackbar, event);
        }

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
    }

    @Override
    public void onShown(Snackbar snackbar) {
        if (callback != null) {
            callback.onShown(snackbar);
        }
        snackbarCallback.onSnackbarShown(snackbar);
    }
}
