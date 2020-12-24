package com.ts.other;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import java.util.HashMap;

public class CustomImgView extends ImageView {
    protected Canvas mCanvas;
    protected onPaint mCbPaint;
    protected boolean mIsDrawing;
    protected HashMap<Integer, Bitmap> mMap;
    protected int mP1;
    protected int mP2;
    protected Paint mPaint;
    protected Resources mRes;
    protected int mXDrawDt;
    protected int mYDrawDt;

    public interface onPaint {
        boolean userPaint(CustomImgView customImgView, Canvas canvas, Paint paint);
    }

    public CustomImgView(Context context) {
        super(context);
        this.mCbPaint = null;
        this.mPaint = new Paint();
        this.mCanvas = null;
        this.mIsDrawing = false;
        this.mMap = new HashMap<>();
        this.mRes = context.getResources();
    }

    public CustomImgView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImgView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mCbPaint = null;
        this.mPaint = new Paint();
        this.mCanvas = null;
        this.mIsDrawing = false;
        this.mMap = new HashMap<>();
        this.mRes = context.getResources();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.mIsDrawing = true;
        this.mCanvas = canvas;
        if (this.mCbPaint != null) {
            this.mCbPaint.userPaint(this, canvas, this.mPaint);
        }
        this.mIsDrawing = false;
        super.onDraw(canvas);
    }

    public void setUserPaint(onPaint paint) {
        this.mCbPaint = paint;
    }

    public void setDrawDt(int xDt, int yDt) {
        this.mXDrawDt = xDt;
        this.mYDrawDt = yDt;
    }

    public void drawImage(int resId, int xOffset, int yOffset) {
        if (this.mIsDrawing) {
            Bitmap bmp = this.mMap.get(Integer.valueOf(resId));
            if (bmp == null) {
                bmp = ((BitmapDrawable) this.mRes.getDrawable(resId)).getBitmap();
                this.mMap.put(Integer.valueOf(resId), bmp);
            }
            this.mCanvas.drawBitmap(bmp, (float) (this.mXDrawDt + xOffset), (float) (this.mYDrawDt + yOffset), this.mPaint);
        }
    }

    public void setIntParam1(int p1) {
        this.mP1 = p1;
    }

    public void setIntParma2(int p2) {
        this.mP2 = p2;
    }

    public int getIntParam1() {
        return this.mP1;
    }

    public int getIntParam2() {
        return this.mP2;
    }

    public void setStateDrawable(int normal, int selected) {
        Drawable iSelected = null;
        Resources res = this.mRes;
        StateListDrawable sd = new StateListDrawable();
        Drawable iNormal = normal == -1 ? null : res.getDrawable(normal);
        if (selected != -1) {
            iSelected = res.getDrawable(selected);
        }
        sd.addState(new int[]{16842913}, iSelected);
        sd.addState(new int[]{16842919}, (Drawable) null);
        sd.addState(new int[]{16842910}, iNormal);
        sd.addState(new int[0], iNormal);
        setBackground(sd);
    }

    public void setStateDrawable(int normal, int down, int selected) {
        Drawable iSelected = null;
        Resources res = this.mRes;
        StateListDrawable sd = new StateListDrawable();
        Drawable iNormal = normal == -1 ? null : res.getDrawable(normal);
        Drawable iPressed = down == -1 ? null : res.getDrawable(down);
        if (selected != -1) {
            iSelected = res.getDrawable(selected);
        }
        sd.addState(new int[]{16842913}, iSelected);
        sd.addState(new int[]{16842919}, iPressed);
        sd.addState(new int[]{16842910}, iNormal);
        sd.addState(new int[0], iNormal);
        setBackground(sd);
    }

    public void Show(boolean show) {
        if (show) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }

    public void Show(int show) {
        if (show != 0) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }

    public void SetSel(int val) {
        if (val == 0) {
            setSelected(false);
        } else {
            setSelected(true);
        }
    }
}
