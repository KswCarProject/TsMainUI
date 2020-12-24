package com.ts.can;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

@SuppressLint({"NewApi"})
public class WaveView extends View {
    private float mArcRadius;
    private int mGreenColor;
    private Paint mPaint;
    private Path mPath;
    private int mProgress;
    private int mRedColor;
    private int mWaveHeight;

    public WaveView(Context context) {
        this(context, (AttributeSet) null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mProgress = 0;
        this.mWaveHeight = 10;
        this.mArcRadius = 6.0f;
        init(context);
    }

    private void init(Context context) {
        this.mPath = new Path();
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mRedColor = Color.parseColor("#ff0000");
        this.mGreenColor = Color.parseColor("#0c6828");
    }

    public void setProgress(int progress) {
        if (progress >= 0 && progress <= 100 && this.mProgress != progress) {
            this.mProgress = progress;
            invalidate();
        }
    }

    public void setWaveHeight(int waveHeight) {
        if (this.mWaveHeight != waveHeight) {
            this.mWaveHeight = waveHeight;
            invalidate();
        }
    }

    public void setArcRadius(float arcRadius) {
        if (this.mArcRadius != arcRadius) {
            this.mArcRadius = arcRadius;
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mProgress > 0) {
            if (this.mProgress >= 100) {
                this.mPaint.setColor(this.mGreenColor);
                canvas.drawRoundRect(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), this.mArcRadius, this.mArcRadius, this.mPaint);
                return;
            }
            if (this.mProgress <= 8) {
                this.mPaint.setColor(this.mRedColor);
            } else {
                this.mPaint.setColor(this.mGreenColor);
            }
            canvas.drawPath(getWavePath(), this.mPaint);
        }
    }

    private Path getWavePath() {
        int y = (getHeight() / 100) * (100 - this.mProgress);
        if (this.mWaveHeight + y >= getHeight() || y - this.mWaveHeight <= 0) {
            return getRoundRectPath(y);
        }
        this.mPath.reset();
        this.mPath.moveTo(0.0f, (float) y);
        this.mPath.quadTo((float) (getWidth() / 4), (float) (y - this.mWaveHeight), (float) (getWidth() / 2), (float) y);
        this.mPath.quadTo((float) ((getWidth() / 4) * 3), (float) (this.mWaveHeight + y), (float) getWidth(), (float) y);
        this.mPath.lineTo((float) getWidth(), ((float) getHeight()) - this.mArcRadius);
        this.mPath.arcTo(((float) getWidth()) - (2.0f * this.mArcRadius), ((float) getHeight()) - (2.0f * this.mArcRadius), (float) getWidth(), (float) getHeight(), 0.0f, 90.0f, false);
        this.mPath.lineTo(this.mArcRadius, (float) getHeight());
        this.mPath.arcTo(0.0f, ((float) getHeight()) - (2.0f * this.mArcRadius), 2.0f * this.mArcRadius, (float) getHeight(), 90.0f, 90.0f, false);
        this.mPath.close();
        return this.mPath;
    }

    private Path getRoundRectPath(int y) {
        this.mPath.reset();
        this.mPath.moveTo(0.0f, (float) y);
        this.mPath.addRoundRect(0.0f, (float) y, (float) getWidth(), (float) getHeight(), this.mArcRadius, this.mArcRadius, Path.Direction.CW);
        this.mPath.close();
        return this.mPath;
    }
}
