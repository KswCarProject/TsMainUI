package com.ts.set.dsp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.ts.MainUI.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChartView extends View {
    int[] mAdjValues = new int[this.mValues.length];
    int mHeight = 88;
    int mOffset = 4;
    Paint mPaint;
    ArrayList<Point> mPoints;
    int[] mValues = {-20, 10, -15, 15, -8, 12, -3, -9, 10, 20, -1, -19, 10, 17, -20, 10, -8, 12, -15, 13, -5, 18, -19, 14, -13, 19, -15, 15, -20, 20};
    int mWidth = 875;

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint(context);
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    public ChartView(Context context) {
        super(context);
        initPaint(context);
    }

    public void initPaint(Context context) {
        Resources res = context.getResources();
        this.mWidth = res.getDimensionPixelSize(R.dimen.dsp_chartview_width);
        this.mHeight = res.getDimensionPixelSize(R.dimen.dsp_chartview_height);
        this.mOffset = res.getDimensionPixelSize(R.dimen.dsp_chartview_offset);
        LinearGradient lg = new LinearGradient(0.0f, 0.0f, (float) this.mWidth, (float) this.mHeight, res.getColor(R.color.chartview_start_color), res.getColor(R.color.chartview_end_color), Shader.TileMode.CLAMP);
        this.mPoints = new ArrayList<>();
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeWidth(3.0f);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setShader(lg);
        if (res.getBoolean(R.bool.is_path_effect)) {
            this.mPaint.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f, 5.0f, 5.0f}, 1.0f));
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void updatePoints(int[] values) {
        this.mValues = values;
        updateAdjValues();
    }

    public void updateAdjValues() {
        float indexX = (float) ((((double) this.mWidth) * 1.0d) / (((double) (this.mValues.length - 1)) * 1.0d));
        float indexY = (float) ((((double) (this.mHeight - this.mOffset)) * 1.0d) / 24.0d);
        this.mPoints.clear();
        for (int i = 0; i < this.mValues.length; i++) {
            this.mAdjValues[i] = numApproach(this.mValues[i], this.mAdjValues[i]);
            this.mPoints.add(new Point((float) ((int) (((float) i) * indexX)), (float) (this.mHeight - ((int) ((((double) this.mAdjValues[i]) * 1.0d) * ((double) indexY))))));
        }
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path mPath = new Path();
        for (int j = 0; j < this.mPoints.size() - 1; j++) {
            Point startp = this.mPoints.get(j);
            Point endp = this.mPoints.get(j + 1);
            int wt = (int) ((startp.x + endp.x) / 2.0f);
            Point p3 = new Point();
            Point p4 = new Point();
            p3.y = startp.y;
            p3.x = (float) wt;
            p4.y = endp.y;
            p4.x = (float) wt;
            if (j == 0) {
                mPath.moveTo(startp.x, startp.y);
            }
            mPath.cubicTo(p3.x, p3.y, p4.x, p4.y, endp.x, endp.y);
        }
        canvas.drawPath(mPath, this.mPaint);
        if (!Arrays.equals(this.mValues, this.mAdjValues) || this.mAdjValues == null) {
            updateAdjValues();
        }
    }

    private int numApproach(int des, int cal) {
        int src = cal;
        if (des == src) {
            return src;
        }
        if (src < des) {
            return src + 1;
        }
        return src - 1;
    }

    private Path getPointCurvePath(List<Point> points) {
        Point p3 = new Point();
        Point p4 = new Point();
        Path path = new Path();
        if (!(points == null || points.size() == 0)) {
            Point startp = points.get(0);
            path.moveTo(startp.x, startp.y);
            for (int i = 0; i < points.size() - 1; i++) {
                Point startp2 = points.get(i);
                Point endp = points.get(i + 1);
                int xCenter = ((int) (startp2.getX() + endp.getX())) / 2;
                int y = ((int) (startp2.getY() + endp.getY())) / 2;
                p3.y = startp2.y;
                p3.x = (float) xCenter;
                p4.y = endp.y;
                p4.x = (float) xCenter;
                path.cubicTo(p3.x, p3.y, p4.x, p4.y, endp.x, endp.y);
            }
        }
        return path;
    }

    class Point {
        public float x;
        public float y;

        public Point() {
            setXY(0.0f, 0.0f);
        }

        public Point(float x2, float y2) {
            setXY(x2, y2);
        }

        public float getX() {
            return this.x;
        }

        public void setX(float x2) {
            this.x = x2;
        }

        public float getY() {
            return this.y;
        }

        public void setY(float y2) {
            this.y = y2;
        }

        public void setXY(float x2, float y2) {
            this.x = x2;
            this.y = y2;
        }
    }
}
