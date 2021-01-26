package com.ts.main.update;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.R;

public class CompleteView extends View {
    BitmapDrawable mBitmapDrawable;
    Context mContext;
    int mHeight = 95;
    Paint mPaint;
    float mRotate = 0.0f;
    Bitmap mSourceBitmap;
    Bitmap mTargetBitmap;
    Canvas mTargetCanvas;
    Paint mTargetPaint;
    int mWidth = 95;

    public CompleteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CompleteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CompleteView(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) {
        this.mContext = context;
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mTargetPaint = new Paint();
        this.mTargetPaint.setAntiAlias(true);
        this.mSourceBitmap = BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.countdown_contour_dn);
        this.mTargetBitmap = Bitmap.createBitmap(this.mSourceBitmap.getWidth(), this.mSourceBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        this.mTargetCanvas = new Canvas(this.mTargetBitmap);
    }

    public void updateProgress(float rotate) {
        this.mRotate = rotate;
        postInvalidate();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(0.0f, 0.0f, (float) this.mWidth, (float) this.mHeight);
        this.mTargetCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        float rotate = (float) ((((double) this.mRotate) / 5.0d) * 360.0d);
        Log.d("lh3", "ratate " + rotate);
        this.mTargetCanvas.drawArc(rectF, 90.0f, rotate, true, this.mPaint);
        this.mTargetPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        this.mTargetCanvas.drawBitmap(this.mSourceBitmap, 0.0f, 0.0f, this.mTargetPaint);
        canvas.drawBitmap(this.mTargetBitmap, 0.0f, 0.0f, (Paint) null);
    }
}
