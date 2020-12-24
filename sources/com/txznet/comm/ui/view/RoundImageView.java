package com.txznet.comm.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import com.txznet.comm.ui.IKeepClass;

/* compiled from: Proguard */
public class RoundImageView extends ImageView implements IKeepClass {

    /* renamed from: T  reason: collision with root package name */
    private int f658T;
    private Matrix T9;
    private RectF TE;
    private int TZ;
    private BitmapShader Tk;
    private int Tn;
    private int Tr;
    private Paint Ty;

    public RoundImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.f658T = 1;
        this.T9 = new Matrix();
        this.Ty = new Paint();
        this.Ty.setAntiAlias(true);
        this.Tr = (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics());
        this.f658T = 1;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.f658T == 0) {
            this.TZ = Math.min(getMeasuredWidth(), getMeasuredHeight());
            this.Tn = this.TZ / 2;
            setMeasuredDimension(this.TZ, this.TZ);
            return;
        }
        this.TZ = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(this.TZ, this.TZ);
    }

    private void T() {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            Bitmap bmp = T(drawable);
            this.Tk = new BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            float scale = 1.0f;
            if (this.f658T == 0) {
                scale = (((float) this.TZ) * 1.0f) / ((float) Math.min(bmp.getWidth(), bmp.getHeight()));
            } else if (this.f658T == 1) {
                scale = Math.max((((float) getWidth()) * 1.0f) / ((float) bmp.getWidth()), (((float) getHeight()) * 1.0f) / ((float) bmp.getHeight()));
            }
            this.T9.setScale(scale, scale);
            this.Tk.setLocalMatrix(this.T9);
            this.Ty.setShader(this.Tk);
        }
    }

    private Bitmap T(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (getDrawable() != null) {
            T();
            if (this.f658T == 1) {
                canvas.drawRoundRect(this.TE, (float) this.Tr, (float) this.Tr, this.Ty);
            } else {
                canvas.drawCircle((float) this.Tn, (float) this.Tn, (float) this.Tn, this.Ty);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (this.f658T == 1) {
            this.TE = new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("state_instance", super.onSaveInstanceState());
        bundle.putInt("state_type", this.f658T);
        bundle.putInt("state_border_radius", this.Tr);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(((Bundle) state).getParcelable("state_instance"));
            this.f658T = bundle.getInt("state_type");
            this.Tr = bundle.getInt("state_border_radius");
            return;
        }
        super.onRestoreInstanceState(state);
    }

    public void setBorderRadius(int borderRadius) {
        int pxVal = dp2px(borderRadius);
        if (this.Tr != pxVal) {
            this.Tr = pxVal;
            invalidate();
        }
    }

    public void setType(int type) {
        if (this.f658T != type) {
            this.f658T = type;
            if (!(this.f658T == 1 || this.f658T == 0)) {
                this.f658T = 0;
            }
            requestLayout();
        }
    }

    public int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(1, (float) dpVal, getResources().getDisplayMetrics());
    }
}
