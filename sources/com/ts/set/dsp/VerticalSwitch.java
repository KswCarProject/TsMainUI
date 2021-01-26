package com.ts.set.dsp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.ts.MainUI.R;

public class VerticalSwitch extends View {
    int curPos;
    int endX;
    boolean isChecked = false;
    int mHeight = 114;
    OnCheckedChangeListener mOnCheckedChangeListener;
    Paint mPaint;
    Bitmap mThumbBmp;
    int mThumbWidth;
    Bitmap mTrackDnBmp;
    Bitmap mTrackUpBmp;
    int mTrackWidth;
    int mWidth = 62;

    interface OnCheckedChangeListener {
        void onCheckedChanged(View view, boolean z);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener mOnCheckedChangeListener2) {
        this.mOnCheckedChangeListener = mOnCheckedChangeListener2;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
        postInvalidate();
    }

    public VerticalSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public VerticalSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerticalSwitch(Context context) {
        super(context);
        init();
    }

    /* access modifiers changed from: package-private */
    public void init() {
        this.mTrackDnBmp = BitmapFactory.decodeResource(getResources(), R.drawable.dsp_switch_track_dn);
        this.mTrackUpBmp = BitmapFactory.decodeResource(getResources(), R.drawable.dsp_switch_track_up);
        this.mThumbBmp = BitmapFactory.decodeResource(getResources(), R.drawable.dsp_switch_up);
        this.mPaint = new Paint();
        this.mTrackWidth = this.mTrackDnBmp.getHeight();
        this.mThumbWidth = this.mThumbBmp.getHeight();
        this.endX = this.mTrackWidth - this.mThumbWidth;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("switch", "onDraw:" + this.isChecked);
        if (this.isChecked) {
            canvas.drawBitmap(this.mTrackDnBmp, 0.0f, 0.0f, this.mPaint);
            canvas.drawBitmap(this.mThumbBmp, 0.0f, (float) this.curPos, this.mPaint);
            if (this.curPos != this.endX) {
                if (this.curPos + 2 > this.endX) {
                    this.curPos = this.endX;
                } else {
                    this.curPos += 2;
                }
                postInvalidate();
                return;
            }
            return;
        }
        canvas.drawBitmap(this.mTrackUpBmp, 0.0f, 0.0f, this.mPaint);
        canvas.drawBitmap(this.mThumbBmp, 0.0f, (float) this.curPos, this.mPaint);
        if (this.curPos != 0) {
            if (this.curPos - 2 < 0) {
                this.curPos = 0;
            } else {
                this.curPos -= 2;
            }
            postInvalidate();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        boolean z;
        int action = event.getAction();
        Log.d("switch", "action = " + action);
        switch (action) {
            case 0:
                return true;
            case 1:
                int x = (int) event.getX();
                int y = (int) event.getY();
                if (x < 0 || x > this.mWidth || y < 0 || y > this.mHeight) {
                    return true;
                }
                if (this.isChecked) {
                    z = false;
                } else {
                    z = true;
                }
                this.isChecked = z;
                if (this.mOnCheckedChangeListener != null) {
                    this.mOnCheckedChangeListener.onCheckedChanged(this, this.isChecked);
                }
                postInvalidate();
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }
}
