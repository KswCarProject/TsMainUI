package com.txznet.comm.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.txznet.comm.ui.IKeepClass;

/* compiled from: Proguard */
public class BoundedLinearLayout extends FrameLayout implements IKeepClass {

    /* renamed from: T  reason: collision with root package name */
    private int f649T;
    private int Tr;

    public BoundedLinearLayout(Context context) {
        super(context);
    }

    public BoundedLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setBoundedWidth(int mBoundedWidth) {
        this.f649T = mBoundedWidth;
        invalidate();
    }

    public void setBoundedHeight(int mBoundedHeight) {
        this.Tr = mBoundedHeight;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        if (this.f649T > 0 && this.f649T < measuredWidth) {
            widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.f649T, View.MeasureSpec.getMode(widthMeasureSpec));
        }
        int measuredHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        if (this.Tr > 0 && this.Tr < measuredHeight) {
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.Tr, View.MeasureSpec.getMode(heightMeasureSpec));
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
