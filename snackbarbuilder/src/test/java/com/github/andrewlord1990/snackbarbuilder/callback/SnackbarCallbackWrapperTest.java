package com.github.andrewlord1990.snackbarbuilder.callback;

import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Callback;

import com.github.andrewlord1990.snackbarbuilder.callback.SnackbarCallbackWrapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
public class SnackbarCallbackWrapperTest {

    private SnackbarCallbackWrapper callbackUnderTest;

    @Mock
    Callback callback;

    @Mock
    Snackbar snackbar;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

        callbackUnderTest = new SnackbarCallbackWrapper(callback);
    }

    @Test
    public void whenCreated_thenSetupCorrectly() throws Exception {
        assertThat(callbackUnderTest.callback).isEqualTo(callback);
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
    public void whenOnShown_thenCallbackInformedOfEvent() {
        //When
        callbackUnderTest.onShown(snackbar);

        //Then
        verify(callback).onShown(snackbar);
    }
}