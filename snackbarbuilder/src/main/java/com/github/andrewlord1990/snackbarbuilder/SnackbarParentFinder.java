package com.github.andrewlord1990.snackbarbuilder;

import android.app.Activity;
import android.view.View;

public interface SnackbarParentFinder {
    View findSnackbarParentView(Activity activity);
}
