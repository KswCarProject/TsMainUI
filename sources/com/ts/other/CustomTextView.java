package com.ts.other;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {
    public CustomTextView(Context context) {
        super(context);
        setTextColor(-1);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void SetSize(int size) {
        setTextSize(0, (float) ((int) (((double) size) / 1.5d)));
    }

    public void SetPxSize(int size) {
        setTextSize(0, (float) ((int) (((double) size) / 1.3d)));
    }

    public void SetPixelSize(int size) {
        setTextSize(0, (float) size);
    }

    public void ShowGone(int show) {
        ShowGone(show != 0);
    }

    public void ShowGone(boolean show) {
        if (show) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }

    public void ShowHide(int show) {
        ShowHide(show != 0);
    }

    public void ShowHide(boolean show) {
        if (show) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }

    public void SetId(int id) {
        setTag(Integer.valueOf(id));
    }

    public int GetId() {
        Object ret = getTag();
        if (ret instanceof Integer) {
            return ((Integer) ret).intValue();
        }
        return 0;
    }
}
