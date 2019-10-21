package com.kirinsidea.ui.highlight;

import androidx.annotation.NonNull;

import com.kirinsidea.R;

public enum HighlightColor {

    Yellow(R.color.colorYellow, "yellow"),
    Blue(R.color.colorBlue, "blue"),
    Green(R.color.colorGreen, "green"),
    Red(R.color.colorRed, "red");

    private final int colorResId;
    @NonNull
    private final String colorName;

    HighlightColor(final int colorResId,
                   @NonNull final String colorName) {
        this.colorResId = colorResId;
        this.colorName = colorName;
    }

    public int getColorResId() {
        return colorResId;
    }

    @NonNull
    public String getColorName() {
        return colorName;
    }
}
