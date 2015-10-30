package com.andrewlord.snackbarbuilder;

import android.support.design.widget.Snackbar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
public class SnackbarDurationTest {

    @Test
    public void givenShort_whenGetAsInt_thenSnackbarLengthShort() {
        assertThat(SnackbarDuration.SHORT.getAsInt()).isEqualTo(Snackbar.LENGTH_SHORT);
    }

    @Test
    public void givenLong_whenGetAsInt_thenSnackbarLengthLong() {
        assertThat(SnackbarDuration.LONG.getAsInt()).isEqualTo(Snackbar.LENGTH_LONG);
    }

    @Test
    public void givenIndefinite_whenGetAsInt_thenSnackbarLengthIndefinite() {
        assertThat(SnackbarDuration.INDEFINITE.getAsInt()).isEqualTo(Snackbar.LENGTH_INDEFINITE);
    }

    @Test
    public void givenSnackbarDurationInt_whenFromInt_thenCorrectDuration() {
        //Given
        int length = Snackbar.LENGTH_SHORT;

        //When
        SnackbarDuration duration = SnackbarDuration.fromInt(length);

        //Then
        assertThat(duration).isEqualTo(SnackbarDuration.SHORT);
    }

    @Test
    public void givenOtherInt_whenFromInt_thenDurationLong() {
        assertThat(SnackbarDuration.fromInt(-5)).isEqualTo(SnackbarDuration.LONG);
    }

    @Test
    public void givenValidIndices_whenFromThemeAttributeEnum_thenCorrectDuration() {
        //When
        SnackbarDuration shortDuration = SnackbarDuration.fromThemeAttributeEnum(0);
        SnackbarDuration longDuration = SnackbarDuration.fromThemeAttributeEnum(1);
        SnackbarDuration indefiniteDuration = SnackbarDuration.fromThemeAttributeEnum(2);

        //Then
        assertThat(shortDuration).isEqualTo(SnackbarDuration.SHORT);
        assertThat(longDuration).isEqualTo(SnackbarDuration.LONG);
        assertThat(indefiniteDuration).isEqualTo(SnackbarDuration.INDEFINITE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenIndexLessThan0_whenFromThemeAttribute_thenIllegalArgumentException() {
        SnackbarDuration.fromThemeAttributeEnum(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenIndexGreaterThan2_whenFromThemeAttribute_thenIllegalArgumentException() {
        SnackbarDuration.fromThemeAttributeEnum(3);
    }

}