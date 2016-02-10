package com.github.andrewlord1990.snackbarbuilder.callback;

import android.support.design.widget.Snackbar;

import com.github.andrewlord1990.snackbarbuilder.callback.SnackbarCallback;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.ShadowLog.LogItem;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
public class SnackbarCallbackTest {

    private SnackbarCallback callbackUnderTest;

    @Mock
    Snackbar snackbar;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

        callbackUnderTest = new SnackbarCallback();
    }

    @Test
    public void whenOnSnackbarShown_thenSnackbarShownMessageLogged() {
        //When
        callbackUnderTest.onSnackbarShown(snackbar);

        //Then
        List<LogItem> logs = ShadowLog.getLogsForTag(SnackbarCallback.class.getSimpleName());
        assertThat(logs).hasSize(1);
        assertThat(logs.get(0).msg).isEqualTo("onSnackbarShown");
    }

    @Test
    public void whenOnSnackbarDismissed_thenSnackbarDismissedMessageLogged() {
        //When
        callbackUnderTest.onSnackbarDismissed(snackbar);

        //Then
        List<LogItem> logs = ShadowLog.getLogsForTag(SnackbarCallback.class.getSimpleName());
        assertThat(logs).hasSize(1);
        assertThat(logs.get(0).msg).isEqualTo("onSnackbarDismissed");
    }

    @Test
    public void whenOnSnackbarActionPressed_thenSnackbarActionPressedMessageLogged() {
        //When
        callbackUnderTest.onSnackbarActionPressed(snackbar);

        //Then
        List<LogItem> logs = ShadowLog.getLogsForTag(SnackbarCallback.class.getSimpleName());
        assertThat(logs).hasSize(1);
        assertThat(logs.get(0).msg).isEqualTo("onSnackbarActionPressed");
    }

    @Test
    public void whenOnSnackbarSwiped_thenSnackbarSwipedMessageLogged() {
        //When
        callbackUnderTest.onSnackbarSwiped(snackbar);

        //Then
        List<LogItem> logs = ShadowLog.getLogsForTag(SnackbarCallback.class.getSimpleName());
        assertThat(logs).hasSize(1);
        assertThat(logs.get(0).msg).isEqualTo("onSnackbarSwiped");
    }

    @Test
    public void whenOnSnackbarTimedOut_thenSnackbarTimedOutMessageLogged() {
        //When
        callbackUnderTest.onSnackbarTimedOut(snackbar);

        //Then
        List<LogItem> logs = ShadowLog.getLogsForTag(SnackbarCallback.class.getSimpleName());
        assertThat(logs).hasSize(1);
        assertThat(logs.get(0).msg).isEqualTo("onSnackbarTimedOut");
    }

    @Test
    public void whenOnSnackbarManuallyDismissed_thenSnackbarManuallyDismissedMessageLogged() {
        //When
        callbackUnderTest.onSnackbarManuallyDismissed(snackbar);

        //Then
        List<LogItem> logs = ShadowLog.getLogsForTag(SnackbarCallback.class.getSimpleName());
        assertThat(logs).hasSize(1);
        assertThat(logs.get(0).msg).isEqualTo("onSnackbarManuallyDismissed");
    }

    @Test
    public void whenOnSnackbarDismissedAfterAnotherShown_thenSnackbarDismissedAfterAnotherShownMessageLogged() {
        //When
        callbackUnderTest.onSnackbarDismissedAfterAnotherShown(snackbar);

        //Then
        List<LogItem> logs = ShadowLog.getLogsForTag(SnackbarCallback.class.getSimpleName());
        assertThat(logs).hasSize(1);
        assertThat(logs.get(0).msg).isEqualTo("onSnackbarDismissedAfterAnotherShown");
    }

}