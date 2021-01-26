package com.ts.main.benz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.ts.MainUI.R;

public class ColorView extends View {
    private Bitmap mBmp;
    private PointF mCenterPoint;
    private OnColorChangedListener mListener;

    public interface OnColorChangedListener {
        void onColorChanged(int i, float f);
    }

    public void setOnColorChangedListener(OnColorChangedListener listener) {
        this.mListener = listener;
    }

    public ColorView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ColorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init();
    }

    private void Init() {
        this.mBmp = BitmapFactory.decodeResource(getResources(), R.drawable.can_rgb_up);
        this.mCenterPoint = new PointF(((float) this.mBmp.getWidth()) / 2.0f, ((float) this.mBmp.getHeight()) / 2.0f);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(this.mBmp.getWidth(), this.mBmp.getHeight());
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(this.mBmp, 0.0f, 0.0f, (Paint) null);
    }

    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (x < 0.0f || x >= ((float) this.mBmp.getWidth()) || y < 0.0f || y >= ((float) this.mBmp.getHeight())) {
            return false;
        }
        if (this.mListener != null) {
            int color = this.mBmp.getPixel((int) x, (int) y);
            if (Color.alpha(color) == 255) {
                this.mListener.onColorChanged(color, getRadian(new PointF(x, y), this.mCenterPoint));
            }
        }
        return true;
    }

    private static float getRadian(PointF a, PointF b) {
        float lenA = b.x - a.x;
        float lenB = b.y - a.y;
        return (float) (((((double) (180.0f * (((float) Math.acos((double) (lenA / ((float) Math.sqrt((double) ((lenA * lenA) + (lenB * lenB))))))) * ((float) (b.y < a.y ? -1 : 1))))) / 3.141592653589793d) + 360.0d) % 360.0d);
    }
}
