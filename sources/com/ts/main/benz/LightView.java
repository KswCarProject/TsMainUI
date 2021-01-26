package com.ts.main.benz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.ts.MainUI.R;

public class LightView extends View {
    private Bitmap mBmpDn;
    private Bitmap mBmpProgress;
    private Bitmap mBmpUp;
    private OnLightChangedListener mListener;
    private int mProgress;

    public interface OnLightChangedListener {
        void onLightChanged(int i);
    }

    public void setOnLightChangedListener(OnLightChangedListener listener) {
        this.mListener = listener;
    }

    public LightView(Context context) {
        this(context, (AttributeSet) null);
    }

    public LightView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LightView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init();
    }

    private void Init() {
        this.mBmpUp = BitmapFactory.decodeResource(getResources(), R.drawable.can_light_up);
        this.mBmpDn = BitmapFactory.decodeResource(getResources(), R.drawable.can_light_dn);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(this.mBmpUp.getWidth(), this.mBmpUp.getHeight());
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(this.mBmpUp, 0.0f, 0.0f, (Paint) null);
        if (this.mBmpProgress != null) {
            canvas.drawBitmap(this.mBmpProgress, 0.0f, 0.0f, (Paint) null);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int acion = event.getAction();
        float x = event.getX();
        float y = event.getY();
        if (x >= 0.0f && y >= -30.0f && x <= ((float) this.mBmpUp.getWidth()) && y <= ((float) (this.mBmpUp.getHeight() + 30))) {
            switch (acion) {
                case 1:
                case 2:
                    invalidateProgress(x);
                    break;
            }
        }
        return true;
    }

    private void invalidateProgress(float x) {
        if (x <= 5.0f) {
            x = 0.0f;
        }
        if (x >= ((float) (this.mBmpUp.getWidth() - 5))) {
            x = (float) this.mBmpUp.getWidth();
        }
        if (x == 0.0f) {
            this.mBmpProgress = null;
        } else {
            this.mBmpProgress = Bitmap.createBitmap(this.mBmpDn, 0, 0, (int) x, this.mBmpDn.getHeight(), (Matrix) null, false);
        }
        invalidate();
        if (this.mListener != null) {
            this.mProgress = (int) ((x / ((float) this.mBmpUp.getWidth())) * 100.0f);
            this.mListener.onLightChanged(this.mProgress);
        }
    }

    public void setLight(int progress) {
        if (this.mProgress != progress) {
            if (progress <= 0 || progress > 100) {
                this.mBmpProgress = null;
            } else {
                this.mBmpProgress = Bitmap.createBitmap(this.mBmpDn, 0, 0, (int) (((float) this.mBmpDn.getWidth()) * ((((float) progress) * 1.0f) / 100.0f)), this.mBmpDn.getHeight(), (Matrix) null, false);
            }
            invalidate();
        }
    }
}
