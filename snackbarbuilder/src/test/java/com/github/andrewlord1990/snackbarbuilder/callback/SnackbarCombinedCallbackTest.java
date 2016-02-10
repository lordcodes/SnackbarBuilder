package com.github.andrewlord1990.snackbarbuilder.callback;

import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Callback;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
public class SnackbarCombinedCallbackTest {

    private SnackbarCombinedCallback callbackUnderTest;

    @Mock
    SnackbarCallback snackbarCallback;

    @Mock
    Callback callback;

    @Mock
    Snackbar snackbar;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

        callbackUnderTest = new SnackbarCombinedCallback(snackbarCallback, callback);
    }

    @Test
    public void whenCreated_thenSetupCorrectly() {
        assertThat(callbackUnderTest.snackbarCallback).isEqualTo(snackbarCallback);
        assertThat(callbackUnderTest.callback).isEqualTo(callback);
    }

    @Test
    public void givenNoSnackbarCallback_whenCreated_thenSetupCorrectly() {
        //Given
        int dismissEvent = Callback.DISMISS_EVENT_ACTION;

        //When
        SnackbarCombinedCallback testCallback = new SnackbarCombinedCallback(callback);
        testCallback.onShown(snackbar);
        testCallback.onDismissed(snackbar, dismissEvent);

        //Then
        assertThat(testCallback.snackbarCallback).isNull();
        assertThat(testCallback.callback).isEqualTo(callback);
        verify(callback).onShown(snackbar);
        verify(callback).onDismissed(snackbar, dismissEvent);
    }

    @Test
    public void whenOnDismissed_thenCallbackInformedOfEvent() {
        //Given
        int dismissEvent = Callback.DISMISS_EVENT_MANUAL;

        //When
        callbackUnderTest.onDismissed(snackbar, dismissEvent);

        //Then
        verify(callback).onDismissed(snackbar, dismissEvent);
    }

    @Test
    public void whenOnDismissed_thenSnackbarCallbackInformedOfEvent() {
        //When
        callbackUnderTest.onDismissed(snackbar, Callback.DISMISS_EVENT_MANUAL);

        //Then
        verify(snackbarCallback).onSnackbarDismissed(snackbar);
    }

    @Test
    public void givenSwipeEvent_whenOnDismissed_thenSnackbarCallbackSwipedEvent() {
        //Given
        int dismissEvent = Callback.DISMISS_EVENT_SWIPE;

        //When
        callbackUnderTest.onDismissed(snackbar, dismissEvent);

        //Then
        verify(snackbarCallback).onSnackbarSwiped(snackbar);
    }

    @Test
    public void givenActionEvent_whenOnDismissed_thenSnackbarCallbackActionEvent() {
        //Given
        int dismissEvent = Callback.DISMISS_EVENT_ACTION;

        //When
        callbackUnderTest.onDismissed(snackbar, dismissEvent);

        //Then
        verify(snackbarCallback).onSnackbarActionPressed(snackbar);
    }

    @Test
    public void givenTimeoutEvent_whenOnDismissed_thenSnackbarCallbackTimeoutEvent() {
        //Given
        int dismissEvent = Callback.DISMISS_EVENT_TIMEOUT;

        //When
        callbackUnderTest.onDismissed(snackbar, dismissEvent);

        //Then
        verify(snackbarCallback).onSnackbarTimedOut(snackbar);
    }

    @Test
    public void givenManualEvent_whenOnDismissed_thenSnackbarCallbackManualEvent() {
        //Given
        int dismissEvent = Callback.DISMISS_EVENT_MANUAL;

        //When
        callbackUnderTest.onDismissed(snackbar, dismissEvent);

        //Then
        verify(snackbarCallback).onSnackbarManuallyDismissed(snackbar);
    }

    @Test
    public void givenConsecutiveEvent_whenOnDismissed_thenSnackbarCallbackConsecutiveEvent() {
        //Given
        int dismissEvent = Callback.DISMISS_EVENT_CONSECUTIVE;

        //When
        callbackUnderTest.onDismissed(snackbar, dismissEvent);

        //Then
        verify(snackbarCallback).onSnackbarDismissedAfterAnotherShown(snackbar);
    }

    @Test
    public void whenOnShown_thenCallbackInformedOfEvent() {
        //When
        callbackUnderTest.onShown(snackbar);

        //Then
        verify(callback).onShown(snackbar);
        verify(snackbarCallback).onSnackbarShown(snackbar);
    }

}