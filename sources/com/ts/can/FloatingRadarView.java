package com.ts.can;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;
import com.ts.MainUI.R;
import java.lang.reflect.Array;

public class FloatingRadarView extends View {
    private static float[][] mLeftRadius = {new float[]{100.0f, 230.0f, 206.0f, 190.0f}, new float[]{100.0f, 230.0f, 210.0f, 194.0f}, new float[]{100.0f, 230.0f, 213.0f, 197.0f}, new float[]{100.0f, 235.0f, 217.0f, 200.0f}};
    private static float[][] mRightRadius = {new float[]{100.0f, 222.0f, 206.0f, 190.0f}, new float[]{100.0f, 279.0f, 226.0f, 194.0f}, new float[]{100.0f, 279.0f, 230.0f, 197.0f}, new float[]{100.0f, 235.0f, 217.0f, 200.0f}};
    private static float[] mSweepAngles = {-40.0f, -20.0f, 0.0f, 20.0f};
    private Paint mArcPaint;
    private PointF mCenterPoint;
    private Bitmap mColorBmp;
    private float[][] mCurrentLeftRadius;
    private float[][] mCurrentRightRadius;
    private Bitmap mGreyBmp;
    private Paint mRadarPaint;

    public FloatingRadarView(Context context) {
        this(context, (AttributeSet) null);
    }

    public FloatingRadarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatingRadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init();
    }

    private void Init() {
        this.mRadarPaint = new Paint();
        this.mRadarPaint.setAntiAlias(true);
        this.mRadarPaint.setDither(true);
        this.mRadarPaint.setStyle(Paint.Style.FILL);
        this.mArcPaint = new Paint();
        this.mArcPaint.setAntiAlias(true);
        this.mArcPaint.setDither(true);
        this.mArcPaint.setStyle(Paint.Style.FILL);
        this.mColorBmp = BitmapFactory.decodeResource(getResources(), R.drawable.can_h5_radar_color);
        this.mGreyBmp = BitmapFactory.decodeResource(getResources(), R.drawable.can_h5_radar_grey);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = this.mColorBmp.getWidth();
        int height = this.mColorBmp.getHeight();
        setMeasuredDimension(width, height);
        this.mCenterPoint = new PointF(((float) width) / 2.0f, ((float) height) / 2.0f);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(this.mGreyBmp, 0.0f, 0.0f, (Paint) null);
        if (this.mCurrentLeftRadius != null) {
            canvas.drawBitmap(getRadarBmp(), 0.0f, 0.0f, (Paint) null);
        }
    }

    private Bitmap getRadarBmp() {
        Bitmap arcBmp = Bitmap.createBitmap(this.mColorBmp.getWidth(), this.mColorBmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas arcCanvas = new Canvas(arcBmp);
        this.mRadarPaint.setXfermode((Xfermode) null);
        for (int i = 0; i < this.mCurrentLeftRadius[0].length; i++) {
            arcCanvas.drawBitmap(getArcBitmap(i, this.mCurrentLeftRadius[0][i], this.mCurrentLeftRadius[1][i], false), 0.0f, 0.0f, this.mRadarPaint);
        }
        for (int i2 = 0; i2 < this.mCurrentRightRadius[0].length; i2++) {
            arcCanvas.drawBitmap(getArcBitmap(i2, this.mCurrentRightRadius[0][i2], this.mCurrentRightRadius[1][i2], true), 0.0f, 0.0f, this.mRadarPaint);
        }
        this.mRadarPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        arcCanvas.drawBitmap(this.mColorBmp, 0.0f, 0.0f, this.mRadarPaint);
        return arcBmp;
    }

    private Bitmap getArcBitmap(int index, float minRadius, float maxRadius, boolean isRight) {
        Bitmap arcBmp = Bitmap.createBitmap(this.mColorBmp.getWidth(), this.mColorBmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas arcCanvas = new Canvas(arcBmp);
        this.mArcPaint.setXfermode((Xfermode) null);
        float sweepAngle = isRight ? mSweepAngles[index] : -180.0f - mSweepAngles[index];
        float degree = isRight ? 20.0f : -20.0f;
        float radius = minRadius;
        arcCanvas.drawArc(new RectF(this.mCenterPoint.x - radius, this.mCenterPoint.y - radius, this.mCenterPoint.x + radius, this.mCenterPoint.y + radius), sweepAngle, degree, true, this.mArcPaint);
        this.mArcPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        float radius2 = maxRadius;
        arcCanvas.drawArc(new RectF(this.mCenterPoint.x - radius2, this.mCenterPoint.y - radius2, this.mCenterPoint.x + radius2, this.mCenterPoint.y + radius2), sweepAngle, degree, true, this.mArcPaint);
        return arcBmp;
    }

    public void updateRadar(int[] data) {
        this.mCurrentLeftRadius = (float[][]) Array.newInstance(Float.TYPE, new int[]{2, 4});
        this.mCurrentRightRadius = (float[][]) Array.newInstance(Float.TYPE, new int[]{2, 4});
        for (int i = 0; i < 4; i++) {
            int index = data[i] > 3 ? 0 : data[i];
            this.mCurrentRightRadius[0][i] = mRightRadius[i][index];
            if (index != 0) {
                index = index + 1 > 3 ? 0 : index + 1;
            }
            this.mCurrentRightRadius[1][i] = mRightRadius[i][index];
        }
        for (int i2 = 4; i2 < data.length; i2++) {
            int index2 = data[i2] > 3 ? 0 : data[i2];
            this.mCurrentLeftRadius[0][i2 - 4] = mLeftRadius[i2 - 4][index2];
            if (index2 != 0) {
                index2 = index2 + 1 > 3 ? 0 : index2 + 1;
            }
            this.mCurrentLeftRadius[1][i2 - 4] = mLeftRadius[i2 - 4][index2];
        }
        invalidate();
    }
}
