package com.github.andrewlord1990.snackbarbuilder;

import android.support.design.widget.Snackbar;
import android.util.Log;

public class SnackbarCallback {

    private static final String TAG = SnackbarCallback.class.getSimpleName();

    public void onSnackbarShown(Snackbar snackbar) {
        Log.v(TAG, "onSnackbarShown");
    }

    public void onSnackbarDismissed(Snackbar snackbar) {
        Log.v(TAG, "onSnackbarDismissed");
    }

    public void onSnackbarActionPressed(Snackbar snackbar) {
        Log.v(TAG, "onSnackbarActionPressed");
    }

    public void onSnackbarSwiped(Snackbar snackbar) {
        Log.v(TAG, "onSnackbarSwiped");
    }

    public void onSnackbarTimedOut(Snackbar snackbar) {
        Log.v(TAG, "onSnackbarTimedOut");
    }

    public void onSnackbarManuallyDismissed(Snackbar snackbar) {
        Log.v(TAG, "onSnackbarManuallyDismissed");
    }

    public void onSnackbarDismissedAfterAnotherShown(Snackbar snackbar) {
        Log.v(TAG, "onSnackbarDismissedAfterAnotherShown");
    }

}
