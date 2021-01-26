package com.ts.set.dsp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.ts.MainUI.R;
import com.txznet.sdk.TXZResourceManager;

public class SetDspBalanceView extends View {
    private static final int BK_X_DT = 0;
    private static final int BK_Y_DT = 0;
    private static float PT_HALF = 69.0f;
    private static float SP_DT_X = 10.714286f;
    private static float SP_DT_Y = 13.142858f;
    private int mBal;
    private Bitmap mBmpPtDn;
    private Bitmap mBmpPtUp;
    private onTouchBalanceChanged mChangedCb = null;
    private boolean mDragFlg;
    private int mFad;
    private Paint mPaint;
    private int mPtCenX;
    private int mPtCenY;
    private Resources res;

    public interface onTouchBalanceChanged {
        void onChanged(View view, int i, int i2);
    }

    public SetDspBalanceView(Context context) {
        super(context);
        initImage(context);
    }

    public SetDspBalanceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initImage(context);
    }

    public void setFad(int fad) {
        if (this.mFad != fad) {
            this.mFad = fad;
            invalidate();
        }
    }

    public void setBal(int bal) {
        if (this.mBal != bal) {
            invalidate();
            this.mBal = bal;
        }
    }

    public void setBalance(int fad, int bal) {
        setFad(fad);
        setBal(bal);
    }

    public void setBalanceChangedListener(onTouchBalanceChanged l) {
        this.mChangedCb = l;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        getPtCenXY();
        Log.e("lh", "x = " + this.mPtCenX + " y = " + this.mPtCenY);
        if (this.mDragFlg) {
            canvas.drawBitmap(this.mBmpPtDn, (float) this.mPtCenX, (float) this.mPtCenY, this.mPaint);
        } else {
            canvas.drawBitmap(this.mBmpPtUp, (float) this.mPtCenX, (float) this.mPtCenY, this.mPaint);
        }
        super.onDraw(canvas);
    }

    /* access modifiers changed from: package-private */
    public void getPtCenXY() {
        Log.d("lh", "mBal = " + this.mBal);
        Log.d("lh", "mFad = " + this.mFad);
        this.mPtCenX = (int) (((float) this.mBal) * SP_DT_X);
        this.mPtCenY = (int) (((float) this.mFad) * SP_DT_Y);
    }

    private void initImage(Context context) {
        Resources res2 = context.getResources();
        PT_HALF = res2.getDimension(R.dimen.dsp_balanceview_dot_width);
        float dimension = res2.getDimension(R.dimen.dsp_balanceview_width);
        this.mPaint = new Paint();
        Resources res3 = context.getResources();
        this.mBmpPtDn = ((BitmapDrawable) res3.getDrawable(R.drawable.dsp_bal_dot_dn)).getBitmap();
        this.mBmpPtUp = ((BitmapDrawable) res3.getDrawable(R.drawable.dsp_bal_dot_up)).getBitmap();
        Log.e(TXZResourceManager.STYLE_DEFAULT, "x = " + this.mBmpPtUp.getWidth() + " y = " + this.mBmpPtUp.getHeight());
    }

    private void dealTouch(int x, int y) {
        int curBal;
        int curFad;
        int dx = x;
        int dy = y;
        if (dx <= 0) {
            curBal = 0;
        } else {
            curBal = (int) (((float) dx) / SP_DT_X);
            if (((float) dx) % SP_DT_X >= SP_DT_X / 2.0f) {
                curBal++;
            }
            if (curBal > 14) {
                curBal = 14;
            }
        }
        if (dy <= 0) {
            curFad = 0;
        } else {
            curFad = (int) (((float) dy) / SP_DT_X);
            if (((float) dy) % SP_DT_X >= SP_DT_X / 2.0f) {
                curFad++;
            }
            if (curFad > 14) {
                curFad = 14;
            }
        }
        if (curFad < 7) {
            curFad = 7;
        }
        if (curBal != this.mBal || curFad != this.mFad) {
            setBalance(curFad, curBal);
            if (this.mChangedCb != null) {
                this.mChangedCb.onChanged(this, curFad, curBal);
            }
        }
    }

    private void checkDrag(int x, int y) {
        getPtCenXY();
        if (x >= this.mPtCenX && ((float) x) <= ((float) this.mPtCenX) + PT_HALF && y >= this.mPtCenY && ((float) y) <= ((float) this.mPtCenY) + PT_HALF) {
            this.mDragFlg = true;
            invalidate();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case 0:
                checkDrag(x, y);
                dealTouch(x, y);
                return true;
            case 1:
                dealTouch(x, y);
                this.mDragFlg = false;
                invalidate();
                return true;
            case 2:
                dealTouch(x, y);
                return true;
            default:
                return true;
        }
    }
}
