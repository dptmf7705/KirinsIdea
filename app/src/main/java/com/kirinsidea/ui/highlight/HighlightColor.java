package com.kirinsidea.ui.highlight;

import androidx.annotation.NonNull;

import com.kirinsidea.R;

public enum HighlightColor {

    YELLOW("YELLOW", R.color.highlight_yellow),
    GREEN("GREEN", R.color.highlight_green),
    RED("RED", R.color.highlight_red);

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
