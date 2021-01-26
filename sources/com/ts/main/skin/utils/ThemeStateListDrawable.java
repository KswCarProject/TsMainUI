package com.ts.main.skin.utils;

import android.graphics.drawable.StateListDrawable;

public class ThemeStateListDrawable extends StateListDrawable {
    private int mOriginalBgId;

    public int getmOriginalBgId() {
        return this.mOriginalBgId;
    }

    public ThemeStateListDrawable(int id) {
        this.mOriginalBgId = id;
    }
}
