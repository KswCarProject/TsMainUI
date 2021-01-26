package com.ts.can;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.view.View;
import com.android.SdkConstants;
import com.ts.MainUI.R;
import com.ts.main.common.MainSet;
import com.yyw.ts70xhw.FtSet;

public class CameraUIView extends View {
    private static final int MAX_Y = 70;
    private static final int MIN_Y = -200;
    private boolean isCanTurnable;
    private boolean isHigherVer;
    private boolean isTurnRight;
    private Bitmap mBmp;
    private int mCenterBmpId;
    private int mCount;
    private PointF[] mHigherMidPoints;
    private PointF mLeftControlPoint;
    private PointF mLeftFixPoint;
    private PointF mLeftPoint1;
    private PointF mLeftPoint2;
    private PointF[] mLeftPoints;
    private PointF mLeftTopPoint;
    private int mLineType;
    private PointF[] mMidPoints;
    private Paint mPaint;
    private PointF mRightControlPoint;
    private PointF mRightFixPoint;
    private PointF mRightPoint1;
    private PointF mRightPoint2;
    private PointF[] mRightPoints;
    private PointF mRightTopPoint;
    private Xfermode mXfermode;

    public CameraUIView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CameraUIView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraUIView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mMidPoints = new PointF[]{new PointF(408.0f, 240.0f), new PointF(615.0f, 240.0f), new PointF(360.0f, 273.0f), new PointF(660.0f, 273.0f), new PointF(250.0f, 350.0f), new PointF(770.0f, 350.0f), new PointF(305.0f, 311.0f), new PointF(715.0f, 311.0f)};
        this.mHigherMidPoints = new PointF[]{new PointF(448.0f, 121.0f), new PointF(609.0f, 121.0f), new PointF(333.0f, 226.0f), new PointF(709.0f, 226.0f), new PointF(197.0f, 353.0f), new PointF(830.0f, 353.0f), new PointF(265.0f, 289.0f), new PointF(769.0f, 289.0f)};
        this.mLeftPoints = new PointF[]{new PointF(123.0f, 276.0f), new PointF(292.0f, 242.0f), new PointF(165.0f, 337.0f), new PointF(485.0f, 280.0f), new PointF(120.0f, 440.0f), new PointF(678.0f, 340.0f), new PointF(280.0f, 345.0f), new PointF(680.0f, 295.0f)};
        this.mRightPoints = new PointF[]{new PointF(742.0f, 242.0f), new PointF(901.0f, 276.0f), new PointF(564.0f, 275.0f), new PointF(834.0f, 337.0f), new PointF(366.0f, 335.0f), new PointF(922.0f, 455.0f), new PointF(344.0f, 300.0f), new PointF(740.0f, 337.0f)};
        this.mLeftFixPoint = new PointF(36.0f, 506.0f);
        this.mRightFixPoint = new PointF(988.0f, 506.0f);
        this.isCanTurnable = true;
        this.isTurnRight = false;
        this.isHigherVer = false;
        this.mCount = 23;
    }

    private void initPaint() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(8.0f);
        this.mPaint.setColor(-256);
        this.mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    private void initPoints() {
        this.mLeftTopPoint = new PointF(this.mMidPoints[0].x, this.mMidPoints[0].y);
        this.mRightTopPoint = new PointF(this.mMidPoints[1].x, this.mMidPoints[1].y);
        this.mLeftPoint1 = new PointF(this.mMidPoints[2].x, this.mMidPoints[2].y);
        this.mRightPoint1 = new PointF(this.mMidPoints[3].x, this.mMidPoints[3].y);
        this.mLeftPoint2 = new PointF(this.mMidPoints[4].x, this.mMidPoints[4].y);
        this.mRightPoint2 = new PointF(this.mMidPoints[5].x, this.mMidPoints[5].y);
        this.mLeftControlPoint = new PointF(this.mMidPoints[6].x, this.mMidPoints[6].y);
        this.mRightControlPoint = new PointF(this.mMidPoints[7].x, this.mMidPoints[7].y);
    }

    public void setCanTurnable(boolean isCan) {
        this.isCanTurnable = isCan;
    }

    public void setLineType(int lineType) {
        this.mLineType = lineType;
        if (this.mLineType > 0) {
            this.mCenterBmpId = this.mLineType == 1 ? R.drawable.can_angle_m : R.drawable.can_benz_m;
            this.mBmp = BitmapFactory.decodeResource(getResources(), this.mCenterBmpId);
            return;
        }
        initPaint();
        initPoints();
    }

    public void setHigherVer(boolean isHigher) {
        this.isHigherVer = isHigher;
        if (this.isHigherVer) {
            this.isCanTurnable = false;
            this.mMidPoints = this.mHigherMidPoints;
            showCenterLine();
        }
    }

    public void showCenterLine() {
        if (this.mLineType > 0) {
            if (this.mBmp != null) {
                this.mBmp.recycle();
            }
            this.mBmp = BitmapFactory.decodeResource(getResources(), this.mCenterBmpId);
        } else {
            initPoints();
        }
        invalidate();
        setVisibility(0);
    }

    public void turnLeft(int index) {
        if (this.isCanTurnable) {
            this.isTurnRight = false;
            setVisibility(0);
            if (this.mLineType > 0) {
                updateBitmap(index);
                return;
            }
            initPoints();
            changeDegree(true, index);
        }
    }

    public void turnRight(int index) {
        if (this.isCanTurnable) {
            this.isTurnRight = true;
            setVisibility(0);
            if (this.mLineType > 0) {
                updateBitmap(index);
                return;
            }
            initPoints();
            changeDegree(false, index);
        }
    }

    private void updateBitmap(int index) {
        String name;
        this.mBmp.recycle();
        if (this.isTurnRight) {
            if (this.mLineType == 1) {
                name = "can_angle_r" + (index * 2);
            } else {
                name = "can_benz_r" + (index * 2);
            }
        } else if (this.mLineType == 1) {
            name = "can_angle_l" + (index * 2);
        } else {
            name = "can_benz_l" + (index * 2);
        }
        int id = getResources().getIdentifier(name, "drawable", getContext().getPackageName());
        if (id > 0) {
            this.mBmp = BitmapFactory.decodeResource(getResources(), id);
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mLineType > 0) {
            drawCameraBitmap(canvas);
        } else {
            drawCameraLine(canvas);
        }
    }

    private void drawCameraBitmap(Canvas canvas) {
        int linePos = FtSet.GetLinePos();
        if (linePos > 70) {
            linePos = 70;
        } else if (linePos < MIN_Y) {
            linePos = MIN_Y;
        }
        canvas.drawBitmap(this.mBmp, (float) ((getWidth() - this.mBmp.getWidth()) / 2), (float) (((getHeight() - this.mBmp.getHeight()) / 2) + 50 + linePos), (Paint) null);
    }

    private void drawCameraLine(Canvas canvas) {
        int screenWidth;
        if (MainSet.GetScreenType() != 5 || MainSet.GetInstance().IsXT5()) {
            screenWidth = getResources().getDisplayMetrics().widthPixels;
        } else {
            screenWidth = 1280;
        }
        float translateY = 0.0f;
        if (MainSet.GetScreenType() == 8) {
            translateY = 150.0f;
        }
        int id = getResources().getIdentifier("camera_ui_translate_y", SdkConstants.TAG_STRING, getContext().getPackageName());
        if (id != 0) {
            translateY = Float.parseFloat(getContext().getString(id));
        }
        this.mPaint.setStyle(Paint.Style.STROKE);
        int layerId = canvas.saveLayer(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), (Paint) null, 31);
        float translateX = ((float) (screenWidth - 1024)) / 2.0f;
        int linePos = FtSet.GetLinePos();
        if (linePos > 70) {
            linePos = 70;
        } else if (linePos < MIN_Y) {
            linePos = MIN_Y;
        }
        canvas.translate(translateX, ((float) linePos) + translateY);
        canvas.drawPath(getLeftQuadPath(), this.mPaint);
        canvas.drawPath(getRightQuadPath(), this.mPaint);
        drawRects(canvas, screenWidth, translateX);
        drawLines(canvas);
        canvas.restoreToCount(layerId);
    }

    private void drawLines(Canvas canvas) {
        int x1;
        int x12;
        int x2;
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setXfermode((Xfermode) null);
        this.mPaint.setColor(Color.parseColor("#4CAF50"));
        canvas.drawLine(((float) (this.isTurnRight ? -3 : -1)) + this.mLeftTopPoint.x, (this.mLeftTopPoint.y + ((float) (this.isTurnRight ? 0 : 2))) - 1.0f, this.mRightTopPoint.x + ((float) (this.isTurnRight ? 3 : 1)) + 1.0f, this.mRightTopPoint.y, this.mPaint);
        this.mPaint.setColor(-256);
        if (this.isTurnRight) {
            x1 = 3;
        } else {
            x1 = -3;
        }
        canvas.drawLine(this.mLeftPoint1.x + ((float) x1) + 2.0f, this.mLeftPoint1.y, this.mRightPoint1.x + ((float) (this.isTurnRight ? 3 : -2)) + 2.0f, this.mRightPoint1.y, this.mPaint);
        this.mPaint.setColor(SupportMenu.CATEGORY_MASK);
        if (this.isTurnRight) {
            x12 = 2;
        } else {
            x12 = -3;
        }
        if (this.isTurnRight) {
            x2 = 3;
        } else {
            x2 = 3;
        }
        canvas.drawLine(3.0f + this.mLeftPoint2.x + ((float) x12), this.mLeftPoint2.y, (this.mRightPoint2.x + ((float) x2)) - 1.0f, this.mRightPoint2.y, this.mPaint);
        this.mPaint.setColor(-256);
    }

    private void drawRects(Canvas canvas, int screenWidth, float translateX) {
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setXfermode(this.mXfermode);
        this.mPaint.setColor(SupportMenu.CATEGORY_MASK);
        canvas.drawRect(0.0f, this.mLeftPoint2.y - 5.0f, this.mLeftPoint2.x + 5.0f, this.mLeftFixPoint.y + 5.0f, this.mPaint);
        canvas.drawRect(this.mRightPoint2.x - 5.0f, this.mRightPoint2.y - 5.0f, ((float) screenWidth) - translateX, this.mRightFixPoint.y + 5.0f, this.mPaint);
        this.mPaint.setColor(-256);
        canvas.drawRect(0.0f, this.mLeftPoint1.y - 3.0f, this.mLeftPoint1.x, this.mLeftPoint2.y - 5.0f, this.mPaint);
        canvas.drawRect(this.mRightPoint1.x, this.mRightPoint1.y - 3.0f, ((float) screenWidth) - translateX, this.mRightPoint2.y - 5.0f, this.mPaint);
        this.mPaint.setColor(Color.parseColor("#4CAF50"));
        canvas.drawRect(0.0f, this.mLeftTopPoint.y - 5.0f, (this.mLeftTopPoint.x >= this.mLeftPoint1.x ? this.mLeftTopPoint.x : this.mLeftPoint1.x) + 8.0f, this.mLeftPoint1.y - 3.0f, this.mPaint);
        canvas.drawRect((this.mRightTopPoint.x <= this.mRightPoint1.x ? this.mRightTopPoint.x : this.mRightPoint1.x) - 8.0f, this.mRightTopPoint.y - 5.0f, ((float) screenWidth) - translateX, this.mRightPoint1.y - 3.0f, this.mPaint);
    }

    private void changeDegree(boolean isTurnLeft, int index) {
        PointF[] points;
        if (isTurnLeft) {
            points = this.mLeftPoints;
        } else {
            points = this.mRightPoints;
        }
        float leftTopPointDx = (this.mMidPoints[0].x - points[0].x) / ((float) this.mCount);
        float leftTopPointDy = (this.mMidPoints[0].y - points[0].y) / ((float) this.mCount);
        float rightTopPointDx = (this.mMidPoints[1].x - points[1].x) / ((float) this.mCount);
        float rightTopPointDy = (this.mMidPoints[1].y - points[1].y) / ((float) this.mCount);
        float leftPoint1Dx = (this.mMidPoints[2].x - points[2].x) / ((float) this.mCount);
        float leftPoint1Dy = (this.mMidPoints[2].y - points[2].y) / ((float) this.mCount);
        float rightPoint1Dx = (this.mMidPoints[3].x - points[3].x) / ((float) this.mCount);
        float rightPoint1Dy = (this.mMidPoints[3].y - points[3].y) / ((float) this.mCount);
        float leftPoint2Dx = (this.mMidPoints[4].x - points[4].x) / ((float) this.mCount);
        float leftPoint2Dy = (this.mMidPoints[4].y - points[4].y) / ((float) this.mCount);
        float rightPoint2Dx = (this.mMidPoints[5].x - points[5].x) / ((float) this.mCount);
        float rightPoint2Dy = (this.mMidPoints[5].y - points[5].y) / ((float) this.mCount);
        float leftControlPointDx = (this.mMidPoints[6].x - points[6].x) / ((float) this.mCount);
        float leftControlPointDy = (this.mMidPoints[6].y - points[6].y) / ((float) this.mCount);
        float rightControlPointDx = (this.mMidPoints[7].x - points[7].x) / ((float) this.mCount);
        float rightControlPointDy = (this.mMidPoints[7].y - points[7].y) / ((float) this.mCount);
        if (isTurnLeft) {
            float halfCount = ((float) this.mCount) / 2.0f;
            leftPoint1Dx -= ((((float) index) - halfCount) * 1.0f) / halfCount;
        } else {
            rightPoint1Dx -= ((((float) (this.mCount - index)) * 1.0f) / ((float) this.mCount)) * 2.3f;
        }
        this.mLeftTopPoint.x -= leftTopPointDx * ((float) index);
        this.mLeftTopPoint.y -= leftTopPointDy * ((float) index);
        this.mRightTopPoint.x -= rightTopPointDx * ((float) index);
        this.mRightTopPoint.y -= rightTopPointDy * ((float) index);
        this.mLeftPoint1.x -= leftPoint1Dx * ((float) index);
        this.mLeftPoint1.y -= leftPoint1Dy * ((float) index);
        this.mRightPoint1.x -= rightPoint1Dx * ((float) index);
        this.mRightPoint1.y -= rightPoint1Dy * ((float) index);
        this.mLeftPoint2.x -= leftPoint2Dx * ((float) index);
        this.mLeftPoint2.y -= leftPoint2Dy * ((float) index);
        this.mRightPoint2.x -= rightPoint2Dx * ((float) index);
        this.mRightPoint2.y -= rightPoint2Dy * ((float) index);
        this.mLeftControlPoint.x -= leftControlPointDx * ((float) index);
        this.mLeftControlPoint.y -= leftControlPointDy * ((float) index);
        this.mRightControlPoint.x -= rightControlPointDx * ((float) index);
        this.mRightControlPoint.y -= rightControlPointDy * ((float) index);
        invalidate();
    }

    private Path getLeftQuadPath() {
        Path path = new Path();
        path.moveTo(this.mLeftFixPoint.x, this.mLeftFixPoint.y);
        path.quadTo(this.mLeftControlPoint.x, this.mLeftControlPoint.y, this.mLeftTopPoint.x, this.mLeftTopPoint.y);
        return path;
    }

    private Path getRightQuadPath() {
        Path path = new Path();
        path.moveTo(this.mRightFixPoint.x, this.mRightFixPoint.y);
        path.quadTo(this.mRightControlPoint.x, this.mRightControlPoint.y, this.mRightTopPoint.x, this.mRightTopPoint.y);
        return path;
    }
}
