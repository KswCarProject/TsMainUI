package com.ts.canview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

public class CanVerticalBar extends View {
    private Bitmap mBmpBg;
    private int mBmpH;
    private Bitmap mBmpProgress;
    private int mBmpW;
    private float mCurPos = 0.0f;
    private float mMaxPos = 0.0f;
    private float mMinPos = 0.0f;
    private Paint mPaint;

    public CanVerticalBar(Context context, int resId) {
        super(context);
        initImage(context, resId);
    }

    public CanVerticalBar(Context context, int resProg, int resBg) {
        super(context);
        initImage(context, resProg, resBg);
    }

    public CanVerticalBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initImage(context, 0);
    }

    public void setCurPos(int pos) {
        this.mCurPos = (float) pos;
        invalidate();
    }

    public void setMinMax(float min, float max) {
        this.mMaxPos = max;
        this.mMinPos = min;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mBmpBg != null) {
            canvas.drawBitmap(this.mBmpBg, 0.0f, 0.0f, this.mPaint);
        }
        if (this.mCurPos >= this.mMinPos && this.mCurPos <= this.mMaxPos && this.mBmpProgress != null) {
            Rect src = new Rect();
            int drawH = (int) ((((float) this.mBmpH) * (this.mCurPos - this.mMinPos)) / (this.mMaxPos - this.mMinPos));
            src.left = 0;
            src.top = this.mBmpH - drawH;
            src.right = this.mBmpW;
            src.bottom = this.mBmpH;
            canvas.drawBitmap(this.mBmpProgress, src, src, this.mPaint);
        }
        super.onDraw(canvas);
    }

    private void initImage(Context context, int resId) {
        this.mPaint = new Paint();
        if (resId != 0) {
            this.mBmpProgress = ((BitmapDrawable) context.getResources().getDrawable(resId)).getBitmap();
            this.mBmpW = this.mBmpProgress.getWidth();
            this.mBmpH = this.mBmpProgress.getHeight();
        }
    }

    private void initImage(Context context, int resProg, int resBg) {
        initImage(context, resProg);
        if (resBg != 0) {
            this.mBmpBg = ((BitmapDrawable) context.getResources().getDrawable(resBg)).getBitmap();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(this.mBmpW, this.mBmpH);
    }
}
