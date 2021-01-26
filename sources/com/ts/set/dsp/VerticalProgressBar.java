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
import com.txznet.sdk.TXZResourceManager;

public class VerticalProgressBar extends View {
    int curY = 0;
    float index = 0.0f;
    boolean isFromUser = false;
    Bitmap mBitmapProgress;
    Bitmap mBitmapThumb;
    Bitmap mBitmapThumbGray;
    int mCacheProgress = 0;
    int mChange = -1;
    int mFlag = -1;
    int mHeight;
    int mMax = 100;
    OnSeekBarChangeListener mOnSeekBarChangeListener;
    public Paint mPaint;
    int mPointHalfWidth;
    int mProgress = 0;
    String mStrProgress = TXZResourceManager.STYLE_DEFAULT;
    int mThumbHeight;
    int mThumbWidth;
    int mWidth;

    public interface OnSeekBarChangeListener {
        void onProgressChanged(VerticalProgressBar verticalProgressBar, int i, boolean z);
    }

    /* access modifiers changed from: package-private */
    public void setOnSeekBarChangeListener(OnSeekBarChangeListener onSeekBarChangeListener) {
        this.mOnSeekBarChangeListener = onSeekBarChangeListener;
    }

    public VerticalProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public VerticalProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerticalProgressBar(Context context) {
        super(context);
        init();
    }

    /* access modifiers changed from: package-private */
    public void init() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeWidth(5.0f);
        this.mPaint.setColor(-1);
        this.mPaint.setTextSize(22.0f);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mBitmapProgress = BitmapFactory.decodeResource(getResources(), R.drawable.dsp_eq_sound_pillar);
        this.mBitmapThumb = BitmapFactory.decodeResource(getResources(), R.drawable.dsp_eq_sound_point01);
        this.mBitmapThumbGray = BitmapFactory.decodeResource(getResources(), R.drawable.dsp_eq_sound_point_hui01);
        this.mWidth = this.mBitmapProgress.getWidth();
        this.mHeight = this.mBitmapProgress.getHeight();
        this.mThumbWidth = this.mBitmapThumb.getWidth();
        this.mThumbHeight = this.mBitmapThumb.getHeight();
        this.mPointHalfWidth = this.mThumbWidth / 2;
        this.index = (1.0f * ((float) this.mHeight)) / ((float) this.mMax);
        this.curY = (int) ((((float) (this.mMax - this.mProgress)) * this.index) + ((float) this.mPointHalfWidth));
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(this.mBitmapProgress, 0.0f, (float) (this.mThumbHeight / 2), this.mPaint);
        if (isEnabled()) {
            canvas.drawBitmap(this.mBitmapThumb, 8.0f, (float) (this.curY - this.mPointHalfWidth), this.mPaint);
        } else {
            canvas.drawBitmap(this.mBitmapThumbGray, 8.0f, (float) (this.curY - this.mPointHalfWidth), this.mPaint);
        }
        this.mCacheProgress = this.mProgress;
        if (this.mChange != -1) {
            this.mCacheProgress = (this.mCacheProgress * this.mChange) / this.mMax;
            if (this.mFlag != -1) {
                this.mCacheProgress += this.mFlag;
            }
        } else if (this.mFlag != -1) {
            this.mCacheProgress += this.mFlag;
        }
        this.mStrProgress = new StringBuilder(String.valueOf(this.mCacheProgress)).toString();
        canvas.drawText(this.mStrProgress, 60.0f, (float) ((this.curY - this.mPointHalfWidth) + 28), this.mPaint);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {
            case 0:
                this.isFromUser = true;
                dealTouch(x, y);
                invalidate();
                return true;
            case 1:
                dealTouch(x, y);
                invalidate();
                this.isFromUser = false;
                return true;
            case 2:
                this.isFromUser = true;
                dealTouch(x, y);
                invalidate();
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    private void dealTouch(int x, int y) {
        int progress;
        int dy = y;
        int i = this.mProgress;
        if (dy <= 0) {
            progress = this.mMax;
        } else if (dy >= this.mHeight) {
            progress = 0;
        } else {
            int progress2 = (int) (((float) dy) / this.index);
            if (((float) dy) % this.index >= this.index / 2.0f) {
                progress2++;
            }
            if (progress2 > this.mMax) {
                progress2 = this.mMax;
            }
            progress = this.mMax - progress2;
        }
        Log.d("lh10", "mProgress = " + progress);
        this.curY = (int) ((((float) (this.mMax - this.mProgress)) * this.index) + ((float) this.mPointHalfWidth));
        if (progress != this.mProgress) {
            this.mProgress = progress;
            if (this.mOnSeekBarChangeListener != null) {
                this.mOnSeekBarChangeListener.onProgressChanged(this, this.mProgress, this.isFromUser);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setMax(int max) {
        this.mMax = max;
        this.index = (1.0f * ((float) this.mHeight)) / ((float) this.mMax);
        this.curY = (int) ((((float) (this.mMax - this.mProgress)) * this.index) + ((float) this.mPointHalfWidth));
    }

    /* access modifiers changed from: package-private */
    public void setProgress(int progress) {
        if (progress != this.mProgress) {
            this.mProgress = progress;
            this.curY = (int) ((((float) (this.mMax - this.mProgress)) * this.index) + ((float) this.mPointHalfWidth));
            invalidate();
        }
    }

    /* access modifiers changed from: package-private */
    public void setChange(int change) {
        this.mChange = change;
    }

    /* access modifiers changed from: package-private */
    public void setFlag(int flag) {
        this.mFlag = flag;
    }
}
