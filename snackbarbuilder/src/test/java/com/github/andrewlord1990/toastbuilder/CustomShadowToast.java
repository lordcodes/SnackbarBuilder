package com.github.andrewlord1990.toastbuilder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

@Implements(Toast.class)
public class CustomShadowToast {

    @Implementation
    @SuppressLint("ShowToast")
    public static Toast makeText(Context context, CharSequence text, int duration) {

        LinearLayout layout = new LinearLayout(context);
        layout.setLayoutParams(
                new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        TextView messageView = new TextView(context);
        layout.setLayoutParams(
                new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        messageView.setId(android.R.id.message);

        layout.addView(messageView);

        Toast toast = new Toast(context);
        toast.setView(layout);
        toast.setDuration(duration);
        toast.setText(text);
        return toast;
    }

}
