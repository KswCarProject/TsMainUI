package com.txznet.comm.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import com.txznet.comm.ui.IKeepClass;
import com.txznet.comm.ui.TE.Tr;

/* compiled from: Proguard */
public class GradientProgressBar extends View implements IKeepClass {

    /* renamed from: T  reason: collision with root package name */
    private int f655T = 0;

    public GradientProgressBar(Context context) {
        super(context);
        T();
    }

    public GradientProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        T();
    }

    private void T() {
    }

    public void setProgress(int val) {
        if (val >= 0 && val <= 100) {
            this.f655T = val;
            if (Looper.myLooper() == Looper.getMainLooper()) {
                invalidate();
            } else {
                postInvalidate();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (getWidth() != 0 && getHeight() != 0) {
            int width = getWidth();
            int height = getHeight();
            canvas.drawColor(0);
            Drawable mDrawable = Tr.Ty("gradient_bg");
            mDrawable.setBounds(0, 0, (int) (((float) width) * ((((float) this.f655T) * 1.0f) / 100.0f)), height);
            mDrawable.setLevel((int) (10000.0f * ((((float) this.f655T) * 1.0f) / 100.0f)));
            mDrawable.draw(canvas);
        }
    }
}
