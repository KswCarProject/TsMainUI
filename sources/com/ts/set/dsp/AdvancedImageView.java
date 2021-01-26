package com.ts.set.dsp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.ts.MainUI.R;

public class AdvancedImageView extends ImageView {
    Bitmap mBitmap;
    Context mContext;
    float mOffset = 0.0f;
    Paint mPaint;
    int topMargin = 0;

    public AdvancedImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public AdvancedImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AdvancedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AdvancedImageView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        this.topMargin = context.getResources().getDimensionPixelSize(R.dimen.dsp_advanceimageview_top_margin);
        this.mPaint = new Paint();
        this.mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.dsp_gj_yling);
    }

    public void setDefaultBitmap(int id) {
        this.mBitmap = BitmapFactory.decodeResource(this.mContext.getResources(), id);
    }

    public void setOffset(float offset) {
        this.mOffset = offset;
        postInvalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(this.mBitmap, this.mOffset, (float) this.topMargin, this.mPaint);
    }
}
