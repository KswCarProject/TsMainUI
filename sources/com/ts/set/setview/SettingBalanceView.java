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
import com.txznet.sdk.TXZResourceManager;

public class SettingBalanceView extends View {
    private static final int BK_X_DT = 0;
    private static final int BK_Y_DT = 0;
    private static int PT_HALF = 18;
    private static int SP_DT = 18;
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
    private Resources res;

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
        canvas.drawBitmap(this.mBmpBk, (float) (PT_HALF + 0), (float) (PT_HALF + 0), this.mPaint);
        Log.e(TXZResourceManager.STYLE_DEFAULT, "x = " + (this.mPtCenX - PT_HALF) + " y = " + (this.mPtCenY - PT_HALF));
        if (this.mDragFlg) {
            canvas.drawBitmap(this.mBmpPtDn, (float) (this.mPtCenX - PT_HALF), (float) (this.mPtCenY - PT_HALF), this.mPaint);
        } else {
            canvas.drawBitmap(this.mBmpPtUp, (float) (this.mPtCenX - PT_HALF), (float) (this.mPtCenY - PT_HALF), this.mPaint);
        }
        super.onDraw(canvas);
    }

    /* access modifiers changed from: package-private */
    public void getPtCenXY() {
        this.mPtCenX = (this.mBal * SP_DT) + PT_HALF + 0 + stringToInteger(R.string.mDot_move);
        this.mPtCenY = (this.mFad * SP_DT) + PT_HALF + 0 + stringToInteger(R.string.mDot_move);
    }

    private void initImage(Context context) {
        this.mPaint = new Paint();
        this.res = context.getResources();
        this.mBmpBk = ((BitmapDrawable) this.res.getDrawable(R.drawable.setup_balance_seat)).getBitmap();
        this.mBmpPtDn = ((BitmapDrawable) this.res.getDrawable(R.drawable.setup_bal_dot_dn)).getBitmap();
        this.mBmpPtUp = ((BitmapDrawable) this.res.getDrawable(R.drawable.setup_bal_dot_up)).getBitmap();
        Log.e(TXZResourceManager.STYLE_DEFAULT, "x = " + this.mBmpPtUp.getWidth() + " y = " + this.mBmpPtUp.getHeight());
        PT_HALF = stringToInteger(R.string.mDot_width_half);
        SP_DT = stringToInteger(R.string.mImg_seat_lattice_width);
    }

    private int stringToInteger(int id) {
        try {
            return Integer.parseInt(this.res.getString(id));
        } catch (NumberFormatException e) {
            Log.d("com.ts.MainUI", "SettingBalanceView reason:" + e.toString());
            e.printStackTrace();
            return 0;
        }
    }

    private void dealTouch(int x, int y) {
        int curBal;
        int curFad;
        int dx = x - 3;
        int dy = y - 2;
        if (dx <= 0) {
            curBal = 0;
        } else {
            curBal = dx / SP_DT;
            if (dx % SP_DT >= SP_DT / 2) {
                curBal++;
            }
            if (curBal > 14) {
                curBal = 14;
            }
        }
        if (dy <= 0) {
            curFad = 0;
        } else {
            curFad = dy / SP_DT;
            if (dy % SP_DT >= SP_DT / 2) {
                curFad++;
            }
            if (curFad > 14) {
                curFad = 14;
            }
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
        if (x >= this.mPtCenX - PT_HALF && x <= this.mPtCenX + PT_HALF && y >= this.mPtCenY - PT_HALF && y <= this.mPtCenY + PT_HALF) {
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
