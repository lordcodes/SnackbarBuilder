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

package com.github.andrewlord1990.snackbarbuilder.callback;

import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Callback;

public class SnackbarCombinedCallback extends SnackbarCallbackWrapper {

    protected SnackbarCallback snackbarCallback;

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
