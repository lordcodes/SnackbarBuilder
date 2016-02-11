package com.github.andrewlord1990.toastbuilder;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.andrewlord1990.snackbarbuilder.R;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(shadows = {CustomShadowToast.class})
public class ToastBuilderTest {

    private ToastBuilder builderUnderTest;

    @Test
    public void whenCreated_thenMessageTextColorFromCustomThemeAttribute() {
        //Given
        RuntimeEnvironment.application.setTheme(R.style.CustomAttrTheme);

        //When
        ToastBuilder builder = new ToastBuilder(RuntimeEnvironment.application);

        //Then
        assertThat(builder.messageTextColor).isEqualTo(0xFF123456);
    }

    @Test
    public void whenCreated_thenDurationFromCustomThemeAttribute() {
        //Given
        RuntimeEnvironment.application.setTheme(R.style.CustomAttrTheme);

        //When
        ToastBuilder builder = new ToastBuilder(RuntimeEnvironment.application);

        //Then
        assertThat(builder.duration).isEqualTo(Toast.LENGTH_SHORT);
    }

    @Test
    public void whenCustomView_thenCustomViewSet() {
        //Given
        createBuilder();
        View customView = new View(RuntimeEnvironment.application);

        //When
        builderUnderTest.customView(customView);

        //Then
        assertThat(builderUnderTest.customView).isEqualTo(customView);
    }

    @Test
    public void whenCustomViewMessageViewId_thenCustomViewMessageViewIdSet() {
        //Given
        createBuilder();

        //When
        builderUnderTest.customViewMessageViewId(R.id.test_id);

        //Then
        assertThat(builderUnderTest.customViewMessageViewId).isEqualTo(R.id.test_id);
    }

    @Test
    public void whenMessageWithString_thenMessageSet() {
        //Given
        createBuilder();

        //When
        builderUnderTest.message("message");

        //Then
        assertThat(builderUnderTest.message).isEqualTo("message");
    }

    @Test
    public void whenMessageWithStringResource_thenMessageSet() {
        //Given
        createBuilder();

        //When
        builderUnderTest.message(R.string.test);

        //Then
        assertThat(builderUnderTest.message).isEqualTo("Test");
    }

    @Test
    public void whenMessageTextColorRes_thenMessageTextColorSet() {
        //Given
        createBuilder();

        //When
        builderUnderTest.messageTextColorRes(R.color.test);

        //Then
        assertThat(builderUnderTest.messageTextColor).isEqualTo(0xFF444444);
    }

    @Test
    public void whenMessageTextColor_thenMessageTextColorSet() {
        //Given
        createBuilder();

        //When
        builderUnderTest.messageTextColor(0xFF333333);

        //Then
        assertThat(builderUnderTest.messageTextColor).isEqualTo(0xFF333333);
    }

    @Test
    public void whenDuration_thenDurationSet() {
        //Given
        createBuilder();

        //When
        builderUnderTest.duration(Toast.LENGTH_LONG);

        //Then
        assertThat(builderUnderTest.duration).isEqualTo(Toast.LENGTH_LONG);
    }

    @Test
    public void whenGravity_thenGravitySet() {
        //Given
        createBuilder();
        int expected = Gravity.TOP | Gravity.END;

        //When
        builderUnderTest.gravity(expected);

        //Then
        assertThat(builderUnderTest.gravity).isEqualTo(expected);
    }

    @Test
    public void whenGravityOffsetX_thenGravityOffsetXSet() {
        //Given
        createBuilder();
        int expected = 600;

        //When
        builderUnderTest.gravityOffsetX(expected);

        //Then
        assertThat(builderUnderTest.gravityOffsetX).isEqualTo(expected);
    }

    @Test
    public void whenGravityOffsetY_thenGravityOffsetYSet() {
        //Given
        createBuilder();
        int expected = -30;

        //When
        builderUnderTest.gravityOffsetY(expected);

        //Then
        assertThat(builderUnderTest.gravityOffsetY).isEqualTo(expected);
    }

    @Test
    public void whenBuild_thenToastSetup() {
        //Given
        int messageTextColor = 0xFF111111;
        String message = "message";
        RuntimeEnvironment.application.setTheme(R.style.AppTheme);

        //When
        Toast toast = new ToastBuilder(RuntimeEnvironment.application)
                .messageTextColor(messageTextColor)
                .message(message)
                .duration(Toast.LENGTH_SHORT)
                .gravity(Gravity.TOP)
                .build();

        //Then
        assertThat(toast.getDuration()).isEqualTo(Toast.LENGTH_SHORT);

        TextView textView = (TextView) toast.getView().findViewById(android.R.id.message);
        assertThat(textView.getCurrentTextColor()).isEqualTo(messageTextColor);
        assertThat(textView.getText().toString()).isEqualTo(message);

        assertThat(toast.getGravity()).isEqualTo(Gravity.TOP);
    }

    @Test
    public void givenCustomView_whenBuild_thenCustomViewSetup() {
        //Given
        RuntimeEnvironment.application.setTheme(R.style.AppTheme);
        View customView = new View(RuntimeEnvironment.application);

        //When
        Toast toast = new ToastBuilder(RuntimeEnvironment.application)
                .customView(customView)
                .duration(Toast.LENGTH_LONG)
                .build();

        //Then
        assertThat(toast.getDuration()).isEqualTo(Toast.LENGTH_LONG);
        assertThat(toast.getView()).isEqualTo(customView);
    }

    @Test
    public void givenCustomViewAndMessageViewId_whenBuild_thenCustomViewSetup() {
        //Given
        RuntimeEnvironment.application.setTheme(R.style.AppTheme);
        LinearLayout customView = new LinearLayout(RuntimeEnvironment.application);
        customView.setLayoutParams(new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        TextView messageView = new TextView(RuntimeEnvironment.application);
        messageView.setLayoutParams(new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        messageView.setId(R.id.test_id);
        customView.addView(messageView);
        int messageTextColor = 0xFF111111;
        String message = "message";

        //When
        Toast toast = new ToastBuilder(RuntimeEnvironment.application)
                .customView(customView)
                .customViewMessageViewId(R.id.test_id)
                .messageTextColor(messageTextColor)
                .message(message)
                .duration(Toast.LENGTH_LONG)
                .build();

        //Then
        assertThat(toast.getDuration()).isEqualTo(Toast.LENGTH_LONG);
        assertThat(toast.getView()).isEqualTo(customView);

        TextView textView = (TextView) toast.getView().findViewById(R.id.test_id);
        assertThat(textView.getCurrentTextColor()).isEqualTo(messageTextColor);
        assertThat(textView.getText().toString()).isEqualTo(message);
    }

    private void createBuilder() {
        RuntimeEnvironment.application.setTheme(R.style.AppTheme);
        builderUnderTest = new ToastBuilder(RuntimeEnvironment.application);
    }

}