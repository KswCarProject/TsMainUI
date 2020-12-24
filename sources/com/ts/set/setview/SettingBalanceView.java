package com.ts.set.setview;

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
import com.yyw.ts70xhw.Iop;

public class SettingBalanceView extends View {
    private static final int BK_X_DT = 0;
    private static final int BK_Y_DT = 0;
    private static final int PT_HALF = 16;
    private static final int SP_DT = 16;
    private int fadLockedValue = -1;
    private int mBal;
    private Bitmap mBmpBk;
    private Bitmap mBmpPtDn;
    private Bitmap mBmpPtUp;
    private onTouchBalanceChanged mChangedCb = null;
    private boolean mDragFlg;
    private int mFad;
    private Paint mPaint;
    private int mPtCenX;
    private int mPtCenY;

    public interface onTouchBalanceChanged {
        void onChanged(View view, int i, int i2);
    }

    public SettingBalanceView(Context context) {
        super(context);
        initImage(context);
    }

    public SettingBalanceView(Context context, AttributeSet attributeSet) {
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
        canvas.drawBitmap(this.mBmpBk, 16.0f, 16.0f, this.mPaint);
        Log.e("", "x = " + (this.mPtCenX - 16) + " y = " + (this.mPtCenY - 16));
        if (this.mDragFlg) {
            canvas.drawBitmap(this.mBmpPtDn, (float) (this.mPtCenX - 16), (float) (this.mPtCenY - 16), this.mPaint);
        } else {
            canvas.drawBitmap(this.mBmpPtUp, (float) (this.mPtCenX - 16), (float) (this.mPtCenY - 16), this.mPaint);
        }
        super.onDraw(canvas);
    }

    /* access modifiers changed from: package-private */
    public void getPtCenXY() {
        this.mPtCenX = (this.mBal * 16) + 16;
        this.mPtCenY = (this.mFad * 16) + 16;
    }

    private void dealFadLock(Context context) {
        int id = getResources().getIdentifier("eq_fad_locked", "string", context.getPackageName());
        if (id != 0) {
            try {
                this.fadLockedValue = Integer.parseInt(getResources().getString(id));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if (this.fadLockedValue >= 0) {
            Iop.FadSet(this.fadLockedValue);
        }
    }

    private void initImage(Context context) {
        this.mPaint = new Paint();
        Resources res = context.getResources();
        this.mBmpBk = ((BitmapDrawable) res.getDrawable(R.drawable.setup_balance_seat)).getBitmap();
        this.mBmpPtDn = ((BitmapDrawable) res.getDrawable(R.drawable.setup_bal_dot_dn)).getBitmap();
        this.mBmpPtUp = ((BitmapDrawable) res.getDrawable(R.drawable.setup_bal_dot_up)).getBitmap();
        Log.e("", "x = " + this.mBmpPtUp.getWidth() + " y = " + this.mBmpPtUp.getHeight());
        dealFadLock(context);
    }

    private void dealTouch(int x, int y) {
        int curBal;
        int curFad;
        int dx = x - 3;
        int dy = y - 2;
        if (dx <= 0) {
            curBal = 0;
        } else {
            curBal = dx / 16;
            if (dx % 16 >= 8) {
                curBal++;
            }
            if (curBal > 14) {
                curBal = 14;
            }
        }
        if (dy <= 0) {
            curFad = 0;
        } else {
            curFad = dy / 16;
            if (dy % 16 >= 8) {
                curFad++;
            }
            if (curFad > 14) {
                curFad = 14;
            }
        }
        if (this.fadLockedValue >= 0 && curFad != this.fadLockedValue) {
            curFad = this.fadLockedValue;
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
        if (x >= this.mPtCenX - 16 && x <= this.mPtCenX + 16 && y >= this.mPtCenY - 16 && y <= this.mPtCenY + 16) {
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
