package com.andrewlord.snackbarbuilder;

import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Duration;

public enum SnackbarDuration {
    SHORT(Snackbar.LENGTH_SHORT),
    LONG(Snackbar.LENGTH_LONG),
    INDEFINITE(Snackbar.LENGTH_INDEFINITE);

    @Duration
    private int duration;

    SnackbarDuration(@Duration int duration) {
        this.duration = duration;
    }

    @Duration
    int getAsInt() {
        return duration;
    }

    static SnackbarDuration fromInt(@Duration int duration) {
        for (SnackbarDuration snackbarDuration : SnackbarDuration.values()) {
            if (snackbarDuration.getAsInt() == duration) {
                return snackbarDuration;
            }
        }
        return LONG;
    }

    static SnackbarDuration fromThemeAttributeEnum(int duration) {
        switch (duration) {
            case 0:
                return SHORT;
            case 1:
                return LONG;
            case 2:
                return INDEFINITE;
        }
        throw new IllegalArgumentException("Unexpected value for duration theme attribute");
    }

}
