package com.ts.can;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;
import com.ts.MainUI.R;

public class RadarUIView extends View {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$ts$can$RadarUIView$Type;
    private Paint mArcPaint;
    private float mCenterY;
    private Bitmap mCurrentBmp;
    private float[] mCurrentRadius;
    private float[] mCurrentStartDegrees;
    private float[] mCurrentSweepDegrees;
    private Xfermode mDstOutXfermode;
    private Kind mKind;
    private float mOffsetX;
    private float[] mRadarRadius;
    private boolean mShowBigRadar;
    private boolean mShowLeftRadar;
    private boolean mShowRearRadar;
    private Type mType;

    public static final class Common {
        public static final float[] mBigLeftSideRadius = {0.0f, 60.0f, 40.0f, 28.0f, 14.0f};
        public static final float[] mBigRadius = {470.0f, 234.0f, 260.0f, 281.0f, 305.0f, 328.0f, 350.0f, 372.0f, 395.0f, 417.0f, 440.0f};
        public static final float[] mBigRightSideRadius = {0.0f, 60.0f, -46.0f, -32.0f, -16.0f};
        public static final float[] mBigSideStartDegrees = {0.0f, 80.0f, 160.0f, 246.0f};
        public static final float[] mBigSideSweepDegrees = {80.0f, 80.0f, 84.0f, 90.0f};
        public static final float[] mBigStartDegrees = {236.0f, 255.5f, 270.0f, 284.5f};
        public static final float[] mBigSweepDegrees = {19.5f, 14.5f, 14.5f, 14.5f};
        public static int[] mBmpIds = {R.drawable.radar_shang, R.drawable.radar_xia, R.drawable.big_radar_shang, R.drawable.big_radar_xia};
        public static final float[] mLeftSideRadius = {0.0f, 40.0f, 26.0f, 18.0f, 8.0f};
        public static final float[] mRadius = {185.0f, 80.0f, 93.0f, 103.0f, 112.0f, 122.0f, 133.0f, 141.0f, 151.0f, 162.0f, 171.0f};
        public static final float[] mRightSideRadius = {0.0f, 40.0f, -32.0f, -22.0f, -14.0f};
        public static int[] mSideBmpIds = {R.drawable.radar_left, R.drawable.radar_right, R.drawable.big_radar_left, R.drawable.big_radar_right};
        public static final float[] mSideStartDegrees = {0.0f, 40.0f, 80.0f, 120.0f};
        public static final float[] mSideSweepDegrees = {40.0f, 40.0f, 42.0f, 45.0f};
        public static final float[] mStartDegrees = {236.0f, 253.5f, 270.0f, 286.0f};
        public static final float[] mSweepDegrees = {18.0f, 16.5f, 16.5f, 18.0f};
    }

    public enum Kind {
        BASE,
        SIDE
    }

    public enum Type {
        COMMON,
        FORD,
        XT5
    }

    static /* synthetic */ int[] $SWITCH_TABLE$com$ts$can$RadarUIView$Type() {
        int[] iArr = $SWITCH_TABLE$com$ts$can$RadarUIView$Type;
        if (iArr == null) {
            iArr = new int[Type.values().length];
            try {
                iArr[Type.COMMON.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Type.FORD.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Type.XT5.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$com$ts$can$RadarUIView$Type = iArr;
        }
        return iArr;
    }

    private static final class Ford {
        public static final float[] mBigForeRadius = {475.0f, 225.0f, 242.0f, 261.0f, 279.0f, 297.0f, 315.0f, 333.0f, 353.0f, 373.0f, 393.0f, 413.0f, 433.0f, 453.0f};
        public static final float[] mBigForeStartDegree = {225.0f, 240.5f, 255.0f, 269.5f, 284.5f, 299.0f};
        public static final float[] mBigForeSweepDegree = {16.0f, 14.5f, 15.0f, 15.0f, 15.0f, 16.0f};
        public static final float[] mBigLeftRadius = {0.0f, 40.0f, 18.0f};
        public static final float[] mBigLeftStartDegree = {0.0f, 106.0f, 220.0f};
        public static final float[] mBigLeftSweepDegree = {110.0f, 110.0f, 110.0f};
        public static final float[] mBigRearRadius = {557.0f, 240.0f, 254.0f, 264.5f, 274.5f, 284.5f, 294.5f, 304.5f, 315.0f, 325.0f, 335.0f, 345.0f, 355.0f, 365.0f, 375.0f, 385.0f, 395.0f, 405.0f, 415.0f, 425.0f, 435.0f, 445.0f, 455.0f, 465.0f, 475.0f, 485.0f, 495.0f, 505.0f, 515.0f, 525.0f, 535.0f, 545.0f};
        public static final float[] mBigRearStartDegree = {-225.0f, -255.0f, -270.0f, -285.0f};
        public static final float[] mBigRearSweepDegree = {-30.0f, -15.0f, -15.0f, -15.0f};
        public static final float[] mBigRightRadius = {40.0f, 0.0f, 18.0f};
        public static final float[] mBigRightStartDegree = {0.0f, 106.0f, 220.0f};
        public static final float[] mBigRightSweepDegree = {110.0f, 110.0f, 110.0f};
        public static final int[] mBmpIds = {R.drawable.ford_radar_up, R.drawable.ford_radar_down, R.drawable.ford_radar_big_up, R.drawable.ford_radar_big_down};
        public static final float[] mForeRadius = {203.0f, 96.0f, 106.0f, 114.0f, 121.5f, 129.0f, 137.0f, 145.0f, 153.0f, 161.0f, 169.0f, 177.0f, 185.0f, 193.0f};
        public static final float[] mForeStartDegree = {225.0f, 241.5f, 255.5f, 269.5f, 283.5f, 298.0f};
        public static final float[] mForeSweepDegree = {17.0f, 14.5f, 14.5f, 15.0f, 15.0f, 17.0f};
        public static final float[] mLeftRadius = {0.0f, 20.0f, 9.0f};
        public static final float[] mLeftStartDegree = {0.0f, 50.0f, 100.0f};
        public static final float[] mLeftSweepDegree = {50.0f, 50.0f, 50.0f};
        public static final float[] mRearRadius = {225.0f, 86.0f, 94.0f, 98.0f, 102.0f, 107.0f, 111.0f, 115.0f, 120.0f, 124.0f, 129.0f, 133.0f, 137.0f, 141.0f, 146.0f, 150.0f, 155.0f, 159.0f, 163.0f, 167.0f, 172.0f, 176.0f, 181.0f, 185.0f, 190.0f, 194.0f, 198.0f, 202.0f, 206.0f, 211.0f, 216.0f, 220.0f};
        public static final float[] mRearStartDegree = {-225.0f, -255.0f, -270.0f, -284.0f};
        public static final float[] mRearSweepDegree = {-30.5f, -15.0f, -15.0f, -15.0f};
        public static final float[] mRightRadius = {20.0f, 0.0f, 9.0f};
        public static final float[] mRightStartDegree = {0.0f, 50.0f, 100.0f};
        public static final float[] mRightSweepDegree = {50.0f, 50.0f, 50.0f};
        public static final int[] mSideBmpIds = {R.drawable.ford_radar_left, R.drawable.ford_radar_right, R.drawable.ford_big_radar_left, R.drawable.ford_big_radar_right};

        private Ford() {
        }
    }

    private static final class XT5 {
        public static int[] mBmpIds = {R.drawable.xt5_radar_up, R.drawable.xt5_radar_down};
        public static final float[] mRadius = {180.0f, 80.0f, 98.0f, 120.0f, 140.0f};
        public static final float[] mStartDegrees = {232.0f, 251.5f, 270.0f, 286.0f};
        public static final float[] mSweepDegrees = {20.0f, 18.0f, 16.5f, 22.0f};

        private XT5() {
        }
    }

    public RadarUIView(Context context) {
        this(context, (AttributeSet) null);
    }

    public RadarUIView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarUIView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mShowRearRadar = false;
        this.mShowBigRadar = false;
        this.mShowLeftRadar = false;
        this.mCurrentBmp = null;
        this.mType = Type.COMMON;
        this.mKind = Kind.BASE;
        Init();
    }

    private void Init() {
        this.mArcPaint = new Paint();
        this.mArcPaint.setAntiAlias(true);
        this.mArcPaint.setDither(true);
        this.mArcPaint.setStyle(Paint.Style.FILL);
        this.mDstOutXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    }

    public void setRadarType(Type type) {
        this.mType = type;
    }

    public void setRadarType(Type type, Kind kind) {
        this.mType = type;
        this.mKind = kind;
    }

    public void showRadar(boolean showBigRadar, boolean showRearRadar) {
        this.mShowBigRadar = showBigRadar;
        this.mShowRearRadar = showRearRadar;
        initDataForBase();
    }

    public void showSideRadar(boolean showBigRadar, boolean showLeftRadar) {
        this.mShowBigRadar = showBigRadar;
        this.mShowLeftRadar = showLeftRadar;
        initDataForSide();
    }

    private void initDataForSide() {
        int i;
        int i2;
        switch ($SWITCH_TABLE$com$ts$can$RadarUIView$Type()[this.mType.ordinal()]) {
            case 1:
                if (!this.mShowBigRadar) {
                    this.mRadarRadius = this.mShowLeftRadar ? Common.mLeftSideRadius : Common.mRightSideRadius;
                    this.mCurrentRadius = new float[]{this.mRadarRadius[0], this.mRadarRadius[0], this.mRadarRadius[0], this.mRadarRadius[0]};
                    this.mCurrentStartDegrees = Common.mSideStartDegrees;
                    this.mCurrentSweepDegrees = Common.mSideSweepDegrees;
                    this.mCurrentBmp = BitmapFactory.decodeResource(getResources(), this.mShowLeftRadar ? Common.mSideBmpIds[0] : Common.mSideBmpIds[1]);
                    this.mOffsetX = 40.0f;
                    return;
                }
                this.mRadarRadius = this.mShowLeftRadar ? Common.mBigLeftSideRadius : Common.mBigRightSideRadius;
                this.mCurrentRadius = new float[]{this.mRadarRadius[0], this.mRadarRadius[0], this.mRadarRadius[0], this.mRadarRadius[0]};
                this.mCurrentStartDegrees = Common.mBigSideStartDegrees;
                this.mCurrentSweepDegrees = Common.mBigSideSweepDegrees;
                this.mCurrentBmp = BitmapFactory.decodeResource(getResources(), this.mShowLeftRadar ? Common.mSideBmpIds[2] : Common.mSideBmpIds[3]);
                this.mOffsetX = 60.0f;
                return;
            case 2:
                if (!this.mShowBigRadar) {
                    this.mRadarRadius = this.mShowLeftRadar ? Ford.mLeftRadius : Ford.mRightRadius;
                    float radius = this.mShowLeftRadar ? Ford.mLeftRadius[0] : Ford.mRightRadius[0];
                    this.mCurrentRadius = new float[]{radius, radius, radius};
                    this.mCurrentStartDegrees = this.mShowLeftRadar ? Ford.mLeftStartDegree : Ford.mRightStartDegree;
                    this.mCurrentSweepDegrees = this.mShowLeftRadar ? Ford.mLeftSweepDegree : Ford.mRightSweepDegree;
                    this.mCurrentBmp = BitmapFactory.decodeResource(getResources(), this.mShowLeftRadar ? Ford.mSideBmpIds[0] : Ford.mSideBmpIds[1]);
                    if (this.mShowLeftRadar) {
                        i2 = 20;
                    } else {
                        i2 = -20;
                    }
                    this.mOffsetX = (float) i2;
                    return;
                }
                this.mRadarRadius = this.mShowLeftRadar ? Ford.mBigLeftRadius : Ford.mBigRightRadius;
                float radius2 = this.mShowLeftRadar ? Ford.mBigLeftRadius[0] : Ford.mBigRightRadius[0];
                this.mCurrentRadius = new float[]{radius2, radius2, radius2};
                this.mCurrentStartDegrees = this.mShowLeftRadar ? Ford.mBigLeftStartDegree : Ford.mBigRightStartDegree;
                this.mCurrentSweepDegrees = this.mShowLeftRadar ? Ford.mBigLeftSweepDegree : Ford.mBigRightSweepDegree;
                this.mCurrentBmp = BitmapFactory.decodeResource(getResources(), this.mShowLeftRadar ? Ford.mSideBmpIds[2] : Ford.mSideBmpIds[3]);
                if (this.mShowLeftRadar) {
                    i = 40;
                } else {
                    i = -40;
                }
                this.mOffsetX = (float) i;
                return;
            default:
                return;
        }
    }

    private void initDataForBase() {
        switch ($SWITCH_TABLE$com$ts$can$RadarUIView$Type()[this.mType.ordinal()]) {
            case 1:
                if (this.mShowBigRadar) {
                    this.mRadarRadius = Common.mBigRadius;
                    this.mCurrentRadius = new float[]{Common.mBigRadius[0], Common.mBigRadius[0], Common.mBigRadius[0], Common.mBigRadius[0]};
                    if (this.mShowRearRadar) {
                        this.mCurrentStartDegrees = NegArray(Common.mBigStartDegrees);
                        this.mCurrentSweepDegrees = NegArray(Common.mBigSweepDegrees);
                        this.mCurrentBmp = BitmapFactory.decodeResource(getResources(), Common.mBmpIds[3]);
                        this.mCenterY = -209.0f;
                        return;
                    }
                    this.mCurrentStartDegrees = Common.mBigStartDegrees;
                    this.mCurrentSweepDegrees = Common.mBigSweepDegrees;
                    this.mCurrentBmp = BitmapFactory.decodeResource(getResources(), Common.mBmpIds[2]);
                    this.mCenterY = ((float) this.mCurrentBmp.getHeight()) + 209.0f;
                    return;
                }
                this.mRadarRadius = Common.mRadius;
                this.mCurrentRadius = new float[]{Common.mRadius[0], Common.mRadius[0], Common.mRadius[0], Common.mRadius[0]};
                if (this.mShowRearRadar) {
                    this.mCurrentStartDegrees = NegArray(Common.mStartDegrees);
                    this.mCurrentSweepDegrees = NegArray(Common.mSweepDegrees);
                    this.mCurrentBmp = BitmapFactory.decodeResource(getResources(), Common.mBmpIds[1]);
                    this.mCenterY = -70.0f;
                    return;
                }
                this.mCurrentStartDegrees = Common.mStartDegrees;
                this.mCurrentSweepDegrees = Common.mSweepDegrees;
                this.mCurrentBmp = BitmapFactory.decodeResource(getResources(), Common.mBmpIds[0]);
                this.mCenterY = ((float) this.mCurrentBmp.getHeight()) + 70.0f;
                return;
            case 2:
                if (this.mShowBigRadar && this.mShowRearRadar) {
                    this.mRadarRadius = Ford.mBigRearRadius;
                    this.mCurrentRadius = new float[]{Ford.mBigRearRadius[0], Ford.mBigRearRadius[0], Ford.mBigRearRadius[0], Ford.mBigRearRadius[0]};
                    this.mCurrentStartDegrees = Ford.mBigRearStartDegree;
                    this.mCurrentSweepDegrees = Ford.mBigRearSweepDegree;
                    this.mCurrentBmp = BitmapFactory.decodeResource(getResources(), Ford.mBmpIds[3]);
                    this.mCenterY = -213.0f;
                    return;
                } else if (this.mShowBigRadar) {
                    this.mRadarRadius = Ford.mBigForeRadius;
                    this.mCurrentRadius = new float[]{Ford.mBigForeRadius[0], Ford.mBigForeRadius[0], Ford.mBigForeRadius[0], Ford.mBigForeRadius[0], Ford.mBigForeRadius[0], Ford.mBigForeRadius[0]};
                    this.mCurrentStartDegrees = Ford.mBigForeStartDegree;
                    this.mCurrentSweepDegrees = Ford.mBigForeSweepDegree;
                    this.mCurrentBmp = BitmapFactory.decodeResource(getResources(), Ford.mBmpIds[2]);
                    this.mCenterY = ((float) this.mCurrentBmp.getHeight()) + 162.0f;
                    return;
                } else if (this.mShowRearRadar) {
                    this.mRadarRadius = Ford.mRearRadius;
                    this.mCurrentRadius = new float[]{Ford.mRearRadius[0], Ford.mRearRadius[0], Ford.mRearRadius[0], Ford.mRearRadius[0]};
                    this.mCurrentStartDegrees = Ford.mRearStartDegree;
                    this.mCurrentSweepDegrees = Ford.mRearSweepDegree;
                    this.mCurrentBmp = BitmapFactory.decodeResource(getResources(), Ford.mBmpIds[1]);
                    this.mCenterY = -78.0f;
                    return;
                } else {
                    this.mRadarRadius = Ford.mForeRadius;
                    this.mCurrentRadius = new float[]{Ford.mForeRadius[0], Ford.mForeRadius[0], Ford.mForeRadius[0], Ford.mForeRadius[0], Ford.mForeRadius[0], Ford.mForeRadius[0]};
                    this.mCurrentStartDegrees = Ford.mForeStartDegree;
                    this.mCurrentSweepDegrees = Ford.mForeSweepDegree;
                    this.mCurrentBmp = BitmapFactory.decodeResource(getResources(), Ford.mBmpIds[0]);
                    this.mCenterY = ((float) this.mCurrentBmp.getHeight()) + 71.0f;
                    return;
                }
            case 3:
                this.mRadarRadius = XT5.mRadius;
                this.mCurrentRadius = new float[]{XT5.mRadius[0], XT5.mRadius[0], XT5.mRadius[0], XT5.mRadius[0]};
                if (this.mShowRearRadar) {
                    this.mCurrentStartDegrees = NegArray(XT5.mStartDegrees);
                    this.mCurrentSweepDegrees = NegArray(XT5.mSweepDegrees);
                    this.mCurrentBmp = BitmapFactory.decodeResource(getResources(), XT5.mBmpIds[1]);
                    this.mCenterY = -70.0f;
                    return;
                }
                this.mCurrentStartDegrees = XT5.mStartDegrees;
                this.mCurrentSweepDegrees = XT5.mSweepDegrees;
                this.mCurrentBmp = BitmapFactory.decodeResource(getResources(), XT5.mBmpIds[0]);
                this.mCenterY = ((float) this.mCurrentBmp.getHeight()) + 70.0f;
                return;
            default:
                return;
        }
    }

    private float[] NegArray(float[] arrays) {
        float[] negArray = new float[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            negArray[i] = -arrays[i];
        }
        return negArray;
    }

    private Bitmap getRadarBmp() {
        Bitmap arcBmp = Bitmap.createBitmap(this.mCurrentBmp.getWidth(), this.mCurrentBmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas arcCanvas = new Canvas(arcBmp);
        this.mArcPaint.setXfermode((Xfermode) null);
        arcCanvas.drawBitmap(this.mCurrentBmp, 0.0f, 0.0f, this.mArcPaint);
        this.mArcPaint.setXfermode(this.mDstOutXfermode);
        float centerX = ((float) getMeasuredWidth()) / 2.0f;
        for (int i = 0; i < this.mCurrentRadius.length; i++) {
            arcCanvas.drawArc(new RectF((centerX - this.mCurrentRadius[i]) - 5.0f, this.mCenterY - this.mCurrentRadius[i], this.mCurrentRadius[i] + centerX + 5.0f, this.mCenterY + this.mCurrentRadius[i]), this.mCurrentStartDegrees[i], this.mCurrentSweepDegrees[i], true, this.mArcPaint);
        }
        return arcBmp;
    }

    private Bitmap getSideRadarBmp() {
        Bitmap arcBmp = Bitmap.createBitmap(this.mCurrentBmp.getWidth(), this.mCurrentBmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas arcCanvas = new Canvas(arcBmp);
        this.mArcPaint.setXfermode((Xfermode) null);
        arcCanvas.drawBitmap(this.mCurrentBmp, 0.0f, 0.0f, this.mArcPaint);
        this.mArcPaint.setXfermode(this.mDstOutXfermode);
        for (int i = 0; i < this.mCurrentRadius.length; i++) {
            arcCanvas.drawRect(this.mCurrentRadius[i], this.mCurrentStartDegrees[i], this.mCurrentRadius[i] + this.mOffsetX, this.mCurrentStartDegrees[i] + this.mCurrentSweepDegrees[i], this.mArcPaint);
        }
        return arcBmp;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(this.mCurrentBmp.getWidth(), this.mCurrentBmp.getHeight());
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mKind == Kind.BASE) {
            canvas.drawBitmap(getRadarBmp(), 0.0f, 0.0f, (Paint) null);
        } else if (this.mKind == Kind.SIDE) {
            canvas.drawBitmap(getSideRadarBmp(), 0.0f, 0.0f, (Paint) null);
        }
    }

    public void invalidateRadar(int[] radarData, int minCount, int maxCount) {
        int i = 0;
        while (i < radarData.length) {
            int radarIndex = radarData[i];
            if (radarIndex >= 0) {
                switch ($SWITCH_TABLE$com$ts$can$RadarUIView$Type()[this.mType.ordinal()]) {
                    case 2:
                        if (this.mKind != Kind.BASE) {
                            if (this.mKind == Kind.SIDE && radarIndex > minCount) {
                                radarIndex = 0;
                                break;
                            }
                        } else if (!this.mShowRearRadar) {
                            if (i != 2 && i != 3) {
                                if (radarIndex <= minCount) {
                                    break;
                                } else {
                                    radarIndex = 0;
                                    break;
                                }
                            } else if (radarIndex <= maxCount) {
                                break;
                            } else {
                                radarIndex = 0;
                                break;
                            }
                        } else if (i != 1 && i != 2) {
                            if (radarIndex <= minCount) {
                                break;
                            } else {
                                radarIndex = 0;
                                break;
                            }
                        } else if (radarIndex <= maxCount) {
                            break;
                        } else {
                            radarIndex = 0;
                            break;
                        }
                        break;
                    default:
                        if (i != 1 && i != 2) {
                            if (radarIndex <= minCount) {
                                break;
                            } else {
                                radarIndex = 0;
                                break;
                            }
                        } else if (radarIndex <= maxCount) {
                            break;
                        } else {
                            radarIndex = 0;
                            break;
                        }
                }
                this.mCurrentRadius[i] = this.mRadarRadius[radarIndex];
                i++;
            } else {
                return;
            }
        }
        invalidate();
    }
}
