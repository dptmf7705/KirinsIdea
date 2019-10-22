package com.kirinsidea.ui.highlight;

import androidx.annotation.NonNull;

import com.kirinsidea.R;

public enum HighlightColor {

    YELLOW(R.color.colorYellow, "yellow"),
    BLUE(R.color.colorBlue, "blue"),
    GREEN(R.color.colorGreen, "green"),
    RED(R.color.colorRed, "red");

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
