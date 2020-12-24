package com.ts.canview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.Log;
import android.view.View;

public class MyProgressBar extends View {
    private static final String TAG = "MyProgressBar";
    private int mBkX = 0;
    private int mBkY = 0;
    private Bitmap mBmpBk;
    private int mBmpH;
    private Bitmap mBmpProgress;
    private int mBmpW;
    private int mCur;
    private int mMax;
    private int mMin;
    private Paint mPaint;

    public MyProgressBar(Context context, int bkId, int progressId) {
        super(context);
        this.mBmpBk = ((BitmapDrawable) getResources().getDrawable(bkId)).getBitmap();
        this.mBmpProgress = ((BitmapDrawable) getResources().getDrawable(progressId)).getBitmap();
        this.mBmpW = this.mBmpBk.getWidth();
        this.mBmpH = this.mBmpBk.getHeight();
        this.mPaint = new Paint();
    }

    public void SetMinMax(int min, int max) {
        if (max > min) {
            this.mMin = min;
            this.mMax = max;
        }
    }

    public void SetCurPos(int pos) {
        this.mCur = pos;
        invalidate();
    }

    public void SetDt(int x, int y) {
        if (x > 0 && y > 0) {
            this.mBkX = x;
            this.mBkY = y;
        }
    }

    public void Show(int show) {
        if (show == 0) {
            setVisibility(8);
        } else {
            setVisibility(0);
        }
    }

    public void Show(boolean show) {
        if (!show) {
            setVisibility(8);
        } else {
            setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(this.mBmpBk, (float) this.mBkX, (float) this.mBkY, this.mPaint);
        if (this.mCur >= this.mMin && this.mCur <= this.mMax && this.mMin < this.mMax) {
            int drawW = ((this.mCur - this.mMin) * this.mBmpW) / (this.mMax - this.mMin);
            canvas.drawBitmap(this.mBmpProgress, new Rect(0, 0, drawW, this.mBmpH), new Rect(this.mBkX, this.mBkY, this.mBkX + drawW, this.mBkY + this.mBmpH), this.mPaint);
        }
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(calcMeasure(widthMeasureSpec, this.mBmpW + (this.mBkX * 2)), calcMeasure(heightMeasureSpec, this.mBmpH + (this.mBkY * 2)));
    }

    /* access modifiers changed from: protected */
    public int calcMeasure(int val, int def) {
        int result = 0;
        int specMode = View.MeasureSpec.getMode(val);
        int specSize = View.MeasureSpec.getSize(val);
        switch (specMode) {
            case ExploreByTouchHelper.INVALID_ID:
                Log.d(TAG, "子容器可以是声明大小内的任意大小, 大小为: " + specSize);
                result = def;
                break;
            case 0:
                Log.d(TAG, "父容器对于子容器没有任何限制,大小为:" + specSize);
                result = def;
                break;
            case 1073741824:
                Log.d(TAG, "父容器已经为子容器设置了尺寸,大小为:" + specSize);
                result = specSize;
                break;
        }
        Log.d(TAG, "result = " + result);
        return result;
    }
}
