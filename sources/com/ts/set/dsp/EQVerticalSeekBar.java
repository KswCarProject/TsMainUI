package com.ts.set.dsp;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

public class EQVerticalSeekBar extends SeekBar {
    private boolean fromUser = false;

    public boolean isFromUser() {
        return this.fromUser;
    }

    public EQVerticalSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public EQVerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EQVerticalSeekBar(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }

    /* access modifiers changed from: protected */
    public synchronized void onDraw(Canvas canvas) {
        canvas.rotate(-90.0f);
        canvas.translate((float) (-getHeight()), 0.0f);
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }
        switch (event.getAction()) {
            case 0:
                int thumbLeft = getThumb().getBounds().left;
                int thumbRight = getThumb().getBounds().right;
                int y = 265 - ((int) event.getY());
                if (y <= thumbLeft || y >= thumbRight) {
                    return false;
                }
                this.fromUser = true;
                setProgress(getMax() - ((int) ((((float) getMax()) * event.getY()) / ((float) getHeight()))));
                onSizeChanged(getWidth(), getHeight(), 0, 0);
                return true;
            case 1:
                this.fromUser = false;
                setProgress(getMax() - ((int) ((((float) getMax()) * event.getY()) / ((float) getHeight()))));
                onSizeChanged(getWidth(), getHeight(), 0, 0);
                return true;
            case 2:
                this.fromUser = true;
                setProgress(getMax() - ((int) ((((float) getMax()) * event.getY()) / ((float) getHeight()))));
                onSizeChanged(getWidth(), getHeight(), 0, 0);
                return true;
            case 3:
                this.fromUser = false;
                return true;
            default:
                return false;
        }
    }

    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
        onSizeChanged(getWidth(), getHeight(), 0, 0);
    }
}
