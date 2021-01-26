package com.ts.set.setview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.ts.MainUI.R;

public class SettingSeekBar extends View {
    private static final int PT_SIDE_W = 0;
    private static final int PT_X_DT = 0;
    private static final int PT_Y_DT = 0;
    private int mBkX;
    private int mBkY;
    private Bitmap mBmpBk;
    private int mBmpH;
    private Bitmap mBmpPoint;
    private int mBmpPointH;
    private int mBmpPointW;
    private Bitmap mBmpProgress;
    private int mBmpW;
    private int mCurPos = 0;
    private int mMaxPos = 0;
    private Paint mPaint;
    private onTouchPosChanged mPosCb = null;

    public interface onTouchPosChanged {
        void onChanged(View view, int i);
    }

    public SettingSeekBar(Context context) {
        super(context);
        initImage(context);
    }

    public SettingSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initImage(context);
    }

    public void setCurPos(int pos) {
        this.mCurPos = pos;
        invalidate();
    }

    public int getMostSuitableWidth() {
        return this.mBmpW + 0;
    }

    public void setMaxPos(int pos) {
        this.mMaxPos = pos;
        invalidate();
    }

    public void setOnTouchChangedListener(onTouchPosChanged l) {
        this.mPosCb = l;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int drawH = 0;
        Rect src = new Rect();
        new Rect();
        if (this.mCurPos != 0 && this.mMaxPos != 0 && this.mCurPos <= this.mMaxPos) {
            drawH = ((this.mBmpH - this.mBmpPointH) * this.mCurPos) / this.mMaxPos;
            int i = (this.mCurPos * this.mBmpW) / this.mMaxPos;
            src.left = 0;
            src.top = this.mBmpH - drawH;
            src.right = this.mBmpW;
            src.bottom = this.mBmpH;
            canvas.drawBitmap(this.mBmpProgress, src, src, this.mPaint);
        }
        canvas.drawBitmap(this.mBmpPoint, 0.0f, (float) ((this.mBmpH - drawH) - this.mBmpPointH), this.mPaint);
        canvas.drawBitmap(this.mBmpBk, (float) this.mBkX, (float) this.mBkY, this.mPaint);
        super.onDraw(canvas);
    }

    private void initImage(Context context) {
        this.mPaint = new Paint();
        this.mBkX = 0;
        this.mBkY = 0;
        Resources res = context.getResources();
        this.mBmpBk = ((BitmapDrawable) res.getDrawable(R.drawable.set_eq_sound_pillar)).getBitmap();
        this.mBmpProgress = ((BitmapDrawable) res.getDrawable(R.drawable.set_eq_sound_orange)).getBitmap();
        this.mBmpPoint = ((BitmapDrawable) res.getDrawable(R.drawable.set_eq_sound_cover)).getBitmap();
        this.mBmpW = this.mBmpProgress.getWidth();
        this.mBmpH = this.mBmpProgress.getHeight();
        Log.d("hdd", String.valueOf(this.mBmpW) + "---" + this.mBmpH);
        this.mBmpPointW = this.mBmpPoint.getWidth();
        this.mBmpPointH = this.mBmpPoint.getHeight();
    }

    private void dealTouch(int x, int y) {
        int pos;
        int i = this.mCurPos;
        if (y < this.mBmpPointH) {
            pos = this.mMaxPos;
        } else if (y > this.mBmpH) {
            pos = 0;
        } else {
            pos = ((this.mBmpH - y) * this.mMaxPos) / (this.mBmpH - this.mBmpPointH);
        }
        if (pos != this.mCurPos) {
            this.mCurPos = pos;
            if (this.mPosCb != null) {
                this.mPosCb.onChanged(this, pos);
            }
            invalidate();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                dealTouch((int) event.getX(), (int) event.getY());
                return true;
            case 1:
                dealTouch((int) event.getX(), (int) event.getY());
                return true;
            case 2:
                dealTouch((int) event.getX(), (int) event.getY());
                return true;
            default:
                return true;
        }
    }
}
