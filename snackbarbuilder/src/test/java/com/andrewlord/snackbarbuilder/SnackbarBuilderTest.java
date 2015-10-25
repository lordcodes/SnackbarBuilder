package com.andrewlord.snackbarbuilder;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Callback;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
public class SnackbarBuilderTest {

    private SnackbarBuilder builderUnderTest;

    @Mock
    CoordinatorLayout parentView;

    @Mock
    Activity activity;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

        SnackbarBuilder.defaultActionTextColor = 0;
        SnackbarBuilder.defaultMessageTextColor = 0;
        SnackbarBuilder.defaultParentViewId = 0;

        when(parentView.getContext()).thenReturn(RuntimeEnvironment.application);
        builderUnderTest = new SnackbarBuilder(parentView);
    }

    @After
    public void after() {
        RuntimeEnvironment.application.setTheme(0);
    }

    @Test
    public void givenView_whenCreated_thenParentViewSetCorrectly() {
        //When
        SnackbarBuilder builder = new SnackbarBuilder(parentView);

        //Then
        assertThat(builder.parentView).isEqualTo(parentView);
        assertThat(builder.context).isEqualTo(parentView.getContext());
    }

    @Test
    public void givenActivity_whenCreated_thenParentViewFoundUsingDefaultParentViewId() {
        //Given
        @IdRes int defaultId = 10;
        when(activity.findViewById(defaultId)).thenReturn(parentView);
        SnackbarBuilder.defaultParentViewId = defaultId;

        //When
        SnackbarBuilder builder = new SnackbarBuilder(activity);

        //Then
        assertThat(builder.parentView).isEqualTo(parentView);
        assertThat(builder.context).isEqualTo(parentView.getContext());
    }

    @Test
    public void givenDefaultActionTextColor_whenCreated_thenActionTextColorIsDefault() {
        //Given
        SnackbarBuilder.defaultActionTextColor = 0xFF123456;

        //When
        SnackbarBuilder builder = new SnackbarBuilder(parentView);

        //Then
        assertThat(builder.actionTextColor).isEqualTo(0xFF123456);
    }

    @Test
    public void givenNoDefaultActionTextColor_whenCreated_thenActionTextColorFromCustomThemeAttribute() {
        //Given
        RuntimeEnvironment.application.setTheme(R.style.CustomAttrTheme);

        //When
        SnackbarBuilder builder = new SnackbarBuilder(parentView);

        //Then
        assertThat(builder.actionTextColor).isEqualTo(0xFF454545);
    }

    @Test
    public void givenNoDefaultActionTextColorOrCustomThemeAttribute_whenCreated_thenActionTextColorFromColorAccentThemeAttribute() {
        //Given
        RuntimeEnvironment.application.setTheme(R.style.FallbackAttrTheme);

        //When
        SnackbarBuilder builder = new SnackbarBuilder(parentView);

        //Then
        assertThat(builder.actionTextColor).isEqualTo(0xFF232323);
    }

    @Test
    public void givenDefaultMessageTextColor_whenCreated_thenMessageTextColorIsDefault() {
        //Given
        SnackbarBuilder.defaultMessageTextColor = 0xFF123456;

        //When
        SnackbarBuilder builder = new SnackbarBuilder(parentView);

        //Then
        assertThat(builder.messageTextColor).isEqualTo(0xFF123456);
    }

    @Test
    public void givenNoDefaultMessageTextColor_whenCreated_thenMessageTextColorFromCustomThemeAttribute() {
        //Given
        RuntimeEnvironment.application.setTheme(R.style.CustomAttrTheme);

        //When
        SnackbarBuilder builder = new SnackbarBuilder(parentView);

        //Then
        assertThat(builder.messageTextColor).isEqualTo(0xFF987654);
    }

    @Test
    public void givenNoDefaultMessageTextColorOrCustomThemeAttribute_whenCreated_thenMessageTextColorFromColorAccentThemeAttribute() {
        //When
        SnackbarBuilder builder = new SnackbarBuilder(parentView);

        //Then
        assertThat(builder.messageTextColor).isEqualTo(Color.WHITE);
    }

    @Test
    public void whenMessageWithString_thenMessageSet() {
        assertThat(builderUnderTest.message("message").message).isEqualTo("message");
    }

    @Test
    public void whenMessageWithStringResource_thenMessageSet() {
        assertThat(builderUnderTest.message(R.string.test).message).isEqualTo("Test");
    }

    @Test
    public void whenMessageTextColorRes_thenMessageTextColorSet() {
        assertThat(builderUnderTest.messageTextColorRes(R.color.test).messageTextColor)
                .isEqualTo(0xFF444444);
    }

    @Test
    public void whenMessageTextColor_thenMessageTextColorSet() {
        assertThat(builderUnderTest.messageTextColor(0xFF333333).messageTextColor)
                .isEqualTo(0xFF333333);
    }

    @Test
    public void whenDurationWithInt_thenDurationSet() {
        assertThat(builderUnderTest.duration(Snackbar.LENGTH_INDEFINITE).duration)
                .isEqualTo(SnackbarDuration.INDEFINITE);
    }

    @Test
    public void whenDurationWithEnum_thenDurationSet() {
        assertThat(builderUnderTest.duration(SnackbarDuration.SHORT).duration)
                .isEqualTo(SnackbarDuration.SHORT);
    }

    @Test
    public void whenActionTextColorRes_thenActionTextColorSet() {
        assertThat(builderUnderTest.actionTextColorRes(R.color.test).actionTextColor)
                .isEqualTo(0xFF444444);
    }

    @Test
    public void whenActionTextColor_thenActionTextColorSet() {
        assertThat(builderUnderTest.actionTextColor(0xFF333333).actionTextColor)
                .isEqualTo(0xFF333333);
    }

    @Test
    public void whenActionTextWithString_thenActionTextSet() {
        assertThat(builderUnderTest.actionText("text").actionText).isEqualTo("text");
    }

    @Test
    public void whenActionTextWithStringResource_thenActionTextSet() {
        assertThat(builderUnderTest.actionText(R.string.test).actionText).isEqualTo("Test");
    }

    @Test
    public void whenActionClickListener_thenActionClickListenerSet() {
        OnClickListener click = new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Click
            }
        };
        assertThat(builderUnderTest.actionClickListener(click).actionClickListener).isEqualTo(click);
    }

    @Test
    public void whenCallback_thenCallbackSet() {
        Callback callback = new Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
            }
        };
        assertThat(builderUnderTest.callback(callback).callback).isEqualTo(callback);
    }

    @Test
    public void whenSnackbarCallback_thenSnackbarCallbackSet() {
        SnackbarCallback callback = new SnackbarCallback();
        assertThat(builderUnderTest.snackbarCallback(callback).snackbarCallback).isEqualTo(callback);
    }

    @Test
    public void whenBuild_thenSnackbarSetup() {
        //Given
        int messageTextColor = 0xFF111111;
        int actionTextColor = 0xFF999999;
        String message = "message";
        String action = "action";
        SnackbarDuration duration = SnackbarDuration.INDEFINITE;

        //When
        Snackbar snackbar = new SnackbarBuilder(parentView)
                .messageTextColor(messageTextColor)
                .actionTextColor(actionTextColor)
                .message(message)
                .actionText(action)
                .duration(duration)
                .build();

        //Then
        assertThat(snackbar.getDuration()).isEqualTo(Snackbar.LENGTH_INDEFINITE);

        TextView textView = (TextView) snackbar.getView().findViewById(R.id.snackbar_text);
        assertThat(textView.getCurrentTextColor()).isEqualTo(messageTextColor);

        Button button = (Button) snackbar.getView().findViewById(R.id.snackbar_action);
        assertThat(button.getCurrentTextColor()).isEqualTo(actionTextColor);
    }

}