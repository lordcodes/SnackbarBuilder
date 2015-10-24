package com.andrewlord.snackbarbuilder;

import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Callback;

public class SnackbarCallbackWrapper extends Callback {

    private SnackbarActionCallback snackbarActionCallback;
    private Callback callback;

    public SnackbarCallbackWrapper(SnackbarActionCallback snackbarActionCallback, Callback callback) {
        this.snackbarActionCallback = snackbarActionCallback;
        this.callback = callback;
    }

    @Override
    public void onDismissed(Snackbar snackbar, @DismissEvent int event) {
        if (callback != null) {
            callback.onDismissed(snackbar, event);
        }

        switch (event) {
            case Callback.DISMISS_EVENT_SWIPE:
                snackbarActionCallback.onSnackbarSwiped(snackbar);
                break;
            case Callback.DISMISS_EVENT_ACTION:
                snackbarActionCallback.onSnackbarActionPressed(snackbar);
                break;
            case Callback.DISMISS_EVENT_TIMEOUT:
                snackbarActionCallback.onSnackbarTimedOut(snackbar);
                break;
            case Callback.DISMISS_EVENT_MANUAL:
                snackbarActionCallback.onSnackbarManuallyDismissed(snackbar);
                break;
            case Callback.DISMISS_EVENT_CONSECUTIVE:
                snackbarActionCallback.onSnackbarDismissedAfterAnotherShown(snackbar);
                break;
        }
    }

    @Override
    public void onShown(Snackbar snackbar) {
        if (callback != null) {
            callback.onShown(snackbar);
        }
        snackbarActionCallback.onSnackbarShown(snackbar);
    }
}
