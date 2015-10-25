package com.andrewlord.snackbarbuilder;

import android.support.design.widget.Snackbar;
import android.util.Log;

public class SnackbarCallback {

    private static final String TAG = SnackbarCallback.class.getSimpleName();

    public void onSnackbarShown(Snackbar snackbar) {
        Log.v(TAG, "onSnackbarShown " + snackbar.toString());
    }

    public void onSnackbarActionPressed(Snackbar snackbar) {
        Log.v(TAG, "onSnackbarActionPressed " + snackbar.toString());
    }

    public void onSnackbarSwiped(Snackbar snackbar) {
        Log.v(TAG, "onSnackbarSwiped " + snackbar.toString());
    }

    public void onSnackbarTimedOut(Snackbar snackbar) {
        Log.v(TAG, "onSnackbarTimedOut " + snackbar.toString());
    }

    public void onSnackbarManuallyDismissed(Snackbar snackbar) {
        Log.v(TAG, "onSnackbarManuallyDismissed " + snackbar.toString());
    }

    public void onSnackbarDismissedAfterAnotherShown(Snackbar snackbar) {
        Log.v(TAG, "onSnackbarDismissedAfterAnotherShown " + snackbar.toString());
    }

}
