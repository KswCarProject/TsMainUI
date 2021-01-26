package com.ts.set.dsp;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class DspViewPager extends ViewPager {
    private boolean isCanScroll = true;

    public DspViewPager(Context context) {
        super(context);
    }

    public DspViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScanScroll(boolean isCanScroll2) {
        this.isCanScroll = isCanScroll2;
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        return false;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
