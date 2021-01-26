package com.ts.other;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import java.util.HashMap;

public class CustomImgView extends ImageView {
    protected Canvas mCanvas;
    protected onPaint mCbPaint;
    protected boolean mIsDrawing;
    protected HashMap<String, Bitmap> mMap;
    private String mOldThemeName;
    protected int mP1;
    protected int mP2;
    protected Paint mPaint;
    protected Resources mRes;
    private int mResourceId;
    private String mThemeName;
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
        this.mThemeName = null;
        this.mOldThemeName = null;
        this.mResourceId = 0;
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
        this.mThemeName = null;
        this.mOldThemeName = null;
        this.mResourceId = 0;
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

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0019, code lost:
        r2 = java.lang.String.valueOf(r2) + "_" + r7.mThemeName;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void drawImage(int r8, int r9, int r10) {
        /*
            r7 = this;
            boolean r3 = r7.mIsDrawing
            if (r3 != 0) goto L_0x0005
        L_0x0004:
            return
        L_0x0005:
            if (r8 <= 0) goto L_0x0004
            android.content.res.Resources r3 = r7.mRes
            java.lang.String r2 = r3.getResourceEntryName(r8)
            java.lang.String r3 = r7.mThemeName
            if (r3 == 0) goto L_0x005d
            java.lang.String r3 = r7.mThemeName
            boolean r3 = r2.endsWith(r3)
            if (r3 != 0) goto L_0x005d
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = java.lang.String.valueOf(r2)
            r3.<init>(r4)
            java.lang.String r4 = "_"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = r7.mThemeName
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r2 = r3.toString()
            android.content.res.Resources r3 = r7.mRes
            java.lang.String r4 = "drawable"
            android.content.res.Resources r5 = r7.mRes
            java.lang.String r5 = r5.getResourcePackageName(r8)
            int r8 = r3.getIdentifier(r2, r4, r5)
            if (r8 > 0) goto L_0x005d
            java.lang.Exception r3 = new java.lang.Exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "no resource named "
            r4.<init>(r5)
            java.lang.StringBuilder r4 = r4.append(r2)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            r3.printStackTrace()
            goto L_0x0004
        L_0x005d:
            java.util.HashMap<java.lang.String, android.graphics.Bitmap> r3 = r7.mMap
            java.lang.Object r0 = r3.get(r2)
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0
            if (r0 != 0) goto L_0x0078
            android.content.res.Resources r3 = r7.mRes
            android.graphics.drawable.Drawable r1 = r3.getDrawable(r8)
            android.graphics.drawable.BitmapDrawable r1 = (android.graphics.drawable.BitmapDrawable) r1
            android.graphics.Bitmap r0 = r1.getBitmap()
            java.util.HashMap<java.lang.String, android.graphics.Bitmap> r3 = r7.mMap
            r3.put(r2, r0)
        L_0x0078:
            android.graphics.Canvas r3 = r7.mCanvas
            int r4 = r7.mXDrawDt
            int r4 = r4 + r9
            float r4 = (float) r4
            int r5 = r7.mYDrawDt
            int r5 = r5 + r10
            float r5 = (float) r5
            android.graphics.Paint r6 = r7.mPaint
            r3.drawBitmap(r0, r4, r5, r6)
            goto L_0x0004
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.other.CustomImgView.drawImage(int, int, int):void");
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

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0014, code lost:
        r0 = r0.subSequence(0, r0.length() - (r4.mOldThemeName.length() + 1)).toString();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setImageResource(int r5) {
        /*
            r4 = this;
            if (r5 <= 0) goto L_0x00a8
            android.content.res.Resources r1 = r4.mRes
            java.lang.String r0 = r1.getResourceEntryName(r5)
            java.lang.String r1 = r4.mOldThemeName
            if (r1 == 0) goto L_0x0054
            java.lang.String r1 = r4.mOldThemeName
            boolean r1 = r0.endsWith(r1)
            if (r1 == 0) goto L_0x0054
            r1 = 0
            int r2 = r0.length()
            java.lang.String r3 = r4.mOldThemeName
            int r3 = r3.length()
            int r3 = r3 + 1
            int r2 = r2 - r3
            java.lang.CharSequence r1 = r0.subSequence(r1, r2)
            java.lang.String r0 = r1.toString()
            android.content.res.Resources r1 = r4.mRes
            java.lang.String r2 = "drawable"
            android.content.res.Resources r3 = r4.mRes
            java.lang.String r3 = r3.getResourcePackageName(r5)
            int r5 = r1.getIdentifier(r0, r2, r3)
            if (r5 > 0) goto L_0x0054
            java.lang.Exception r1 = new java.lang.Exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "no resource named "
            r2.<init>(r3)
            java.lang.StringBuilder r2 = r2.append(r0)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            r1.printStackTrace()
        L_0x0053:
            return
        L_0x0054:
            java.lang.String r1 = r4.mThemeName
            if (r1 == 0) goto L_0x00a4
            java.lang.String r1 = r4.mThemeName
            boolean r1 = r0.endsWith(r1)
            if (r1 != 0) goto L_0x00a4
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = java.lang.String.valueOf(r0)
            r1.<init>(r2)
            java.lang.String r2 = "_"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = r4.mThemeName
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r0 = r1.toString()
            android.content.res.Resources r1 = r4.mRes
            java.lang.String r2 = "drawable"
            android.content.res.Resources r3 = r4.mRes
            java.lang.String r3 = r3.getResourcePackageName(r5)
            int r5 = r1.getIdentifier(r0, r2, r3)
            if (r5 > 0) goto L_0x00a4
            java.lang.Exception r1 = new java.lang.Exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "no resource named "
            r2.<init>(r3)
            java.lang.StringBuilder r2 = r2.append(r0)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            r1.printStackTrace()
            goto L_0x0053
        L_0x00a4:
            java.lang.String r1 = r4.mThemeName
            r4.mOldThemeName = r1
        L_0x00a8:
            r4.mResourceId = r5
            super.setImageResource(r5)
            goto L_0x0053
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.other.CustomImgView.setImageResource(int):void");
    }

    public void applyTheme(String themeName) {
        this.mThemeName = themeName;
        if (this.mResourceId != 0) {
            setImageResource(this.mResourceId);
        }
        invalidate();
    }
}
