# SnackbarBuilder

[![Build Status](https://travis-ci.org/andrewlord1990/SnackbarBuilder.svg?branch=master)](https://travis-ci.org/andrewlord1990/SnackbarBuilder)
[![Coverage Status](https://coveralls.io/repos/andrewlord1990/SnackbarBuilder/badge.svg?branch=master&service=github)](https://coveralls.io/github/andrewlord1990/SnackbarBuilder?branch=master)
[![API](https://img.shields.io/badge/API-7%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=7)

The Android Design Support library introduced a Snackbar class. SnackbarBuilder is a builder pattern that makes Snackbars easier to make and customise.

It is not yet available on `Maven Central` or `Bintray`, but it will be soon.

## Main Features

- Builder pattern to create Snackbars
- Customise message and action text color with global theme attributes and on a per-Snackbar basis
- Specify dismiss callbacks with separate methods for each type
- Duration through an enum
- Specify default view ID in activity to attach Snackbars too, so you don't need to provide parent view each time you wish to show one


## Usage

### Create

Build the Snackbar, making it very easy to build and customise the snackbar.

```java
Snackbar snackbar = new SnackbarBuilder(this)
              .duration(SnackbarDuration.LONG)
              .message("message")
              .actionText("Action")
              .snackbarCallback(new MySnackbarCallback())
              .build()
              .show();
```

### Callback

Extend the `SnackbarCallback` class and override only the methods you are interested in. Rather than needing to check the `dismissEvent` integer in the `Snackbar.Callback` class, there is a separate method to override for each dismiss type.

```java
private class MySnackbarCallback extends SnackbarCallback {

    public void onSnackbarShown(Snackbar snackbar) {
        // Snackbar shown
    }

    public void onSnackbarActionPressed(Snackbar snackbar) {
        // Snackbar action button pressed
    }

    public void onSnackbarSwiped(Snackbar snackbar) {
        // Snackbar dismissed via swipe
    }

    public void onSnackbarTimedOut(Snackbar snackbar) {
        // Snackbar dismissed via timeout (after duration passed)
    }

    public void onSnackbarManuallyDismissed(Snackbar snackbar) {
        // Snackbar dimissed via call to dismiss()
    }

    public void onSnackbarDismissedAfterAnotherShown(Snackbar snackbar) {
        // Snackbar dismissed because another Snackbar shown
    }
}
```

If there is any features that have been missed that you are interested in then please open an Issue.
