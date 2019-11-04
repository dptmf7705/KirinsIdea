package com.kirinsidea.ui.highlight;

import androidx.annotation.NonNull;

import com.kirinsidea.R;

public enum HighlightColor {

    YELLOW("YELLOW", R.color.highlightYellow),
    GREEN("GREEN", R.color.highlightGreen),
    RED("RED", R.color.highlightRed);

    @NonNull
    private final String colorName;
    private final int colorResId;

    HighlightColor(@NonNull final String colorName,
                   final int colorResId) {
        this.colorName = colorName;
        this.colorResId = colorResId;
    }

    @NonNull
    public String getColorName() {
        return colorName;
    }

    public int getColorResId() {
        return colorResId;
    }

}
