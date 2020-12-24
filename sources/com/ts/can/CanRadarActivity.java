package com.ts.can;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.RadarUIView;
import com.ts.other.CustomImgView;
import com.ts.other.ValCal;
import com.yyw.ts70xhw.Mcu;

public class CanRadarActivity extends CanBaseActivity implements UserCallBack {
    private static final int RADAR_TIME_DELAY = 4000;
    private static final int RADAR_TIME_OUT = 3000;
    protected static final String TAG = "CanRadarActivity";
    public static int mForeLeftMax = 10;
    public static int mForeMidLtMax = 10;
    public static int mForeMidRtMax = 10;
    public static int mForeRightMax = 10;
    public static int mRearLeftMax = 10;
    public static int mRearMidLtMax = 10;
    public static int mRearMidRtMax = 10;
    public static int mRearRightMax = 10;
    private static boolean mbRadarWin = false;
    private CustomImgView.onPaint mBigCarPaint = new CustomImgView.onPaint() {
        public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
            canvas.drawBitmap(BitmapFactory.decodeResource(CanRadarActivity.this.getResources(), R.drawable.can_radar_big_car01), 0.0f, 0.0f, paint);
            return false;
        }
    };
    private RadarUIView mBigForeRadar;
    private RadarUIView mBigLeftSideRadar;
    private LinearLayout mBigRadarLayout;
    private RadarUIView mBigRearRadar;
    private RadarUIView mBigRightSideRadar;
    private RadarUIView mLeftSideRadar;
    private int mOffsetY = 0;
    private int mRadarMarginTop = CanCameraUI.BTN_TRUMPCHI_GS4_MODE1;
    private RadarUIView mRightForeRadar;
    private RadarUIView mRightRearRadar;
    private RadarUIView mRightSideRadar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        RadarUIView.Type type;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_radar);
        this.mBigRadarLayout = (LinearLayout) findViewById(R.id.big_radar_layout);
        this.mRightForeRadar = (RadarUIView) findViewById(R.id.right_fore_radar);
        this.mRightRearRadar = (RadarUIView) findViewById(R.id.right_rear_radar);
        this.mBigForeRadar = (RadarUIView) findViewById(R.id.big_fore_radar);
        this.mBigRearRadar = (RadarUIView) findViewById(R.id.big_rear_radar);
        this.mLeftSideRadar = (RadarUIView) findViewById(R.id.left_side_radar);
        this.mRightSideRadar = (RadarUIView) findViewById(R.id.right_side_radar);
        this.mBigLeftSideRadar = (RadarUIView) findViewById(R.id.big_left_side_radar);
        this.mBigRightSideRadar = (RadarUIView) findViewById(R.id.big_right_side_radar);
        ((CustomImgView) findViewById(R.id.big_car)).setUserPaint(this.mBigCarPaint);
        Log.d(TAG, "GetCanType = " + CanJni.GetCanType());
        switch (CanJni.GetCanType()) {
            case 2:
            case 122:
            case 198:
                type = RadarUIView.Type.COMMON;
                RadarUIView.Common.mBmpIds = new int[]{R.drawable.radar_shang_golf, R.drawable.radar_xia_golf, R.drawable.big_radar_shang_golf, R.drawable.big_radar_xia_golf};
                break;
            case 13:
            case 146:
                type = RadarUIView.Type.FORD;
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) this.mRightSideRadar.getLayoutParams();
                lp.rightMargin = 64;
                this.mRightSideRadar.setLayoutParams(lp);
                RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) this.mBigLeftSideRadar.getLayoutParams();
                lp2.leftMargin = 240;
                lp2.topMargin = 460;
                this.mBigLeftSideRadar.setLayoutParams(lp2);
                RelativeLayout.LayoutParams lp3 = (RelativeLayout.LayoutParams) this.mBigRightSideRadar.getLayoutParams();
                lp3.leftMargin = CanCameraUI.BTN_YG9_XBS_MODE1;
                lp3.topMargin = 460;
                this.mBigRightSideRadar.setLayoutParams(lp3);
                this.mRadarMarginTop = 460;
                showLeftRightRadar(type);
                break;
            case 110:
                type = RadarUIView.Type.COMMON;
                if (CanJni.GetSubType() != 2) {
                    RadarUIView.Common.mBmpIds = new int[]{R.drawable.radar_shang_golf, R.drawable.radar_xia_golf, R.drawable.big_radar_shang_golf, R.drawable.big_radar_xia_golf};
                    break;
                }
                break;
            default:
                type = RadarUIView.Type.COMMON;
                if (CanJni.IsHaveLrRadar()) {
                    this.mRadarMarginTop = CanCameraUI.BTN_TRUMPCHI_GS4_MODE1;
                    showLeftRightRadar(type);
                    break;
                }
                break;
        }
        this.mRightForeRadar.setRadarType(type);
        this.mRightRearRadar.setRadarType(type);
        this.mBigForeRadar.setRadarType(type);
        this.mBigRearRadar.setRadarType(type);
        this.mRightForeRadar.showRadar(false, false);
        this.mRightRearRadar.showRadar(false, true);
        this.mBigForeRadar.showRadar(true, false);
        this.mBigRearRadar.showRadar(true, true);
    }

    private void showLeftRightRadar(RadarUIView.Type type) {
        this.mLeftSideRadar.setVisibility(0);
        this.mRightSideRadar.setVisibility(0);
        this.mBigLeftSideRadar.setVisibility(0);
        this.mBigRightSideRadar.setVisibility(0);
        this.mLeftSideRadar.setRadarType(type, RadarUIView.Kind.SIDE);
        this.mRightSideRadar.setRadarType(type, RadarUIView.Kind.SIDE);
        this.mBigLeftSideRadar.setRadarType(type, RadarUIView.Kind.SIDE);
        this.mBigRightSideRadar.setRadarType(type, RadarUIView.Kind.SIDE);
        this.mLeftSideRadar.showSideRadar(false, true);
        this.mRightSideRadar.showSideRadar(false, false);
        this.mBigLeftSideRadar.showSideRadar(true, true);
        this.mBigRightSideRadar.showSideRadar(true, false);
    }

    private void setBigRadarOffsetY(int offset) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) this.mBigRadarLayout.getLayoutParams();
        lp.topMargin = offset + 105;
        this.mBigRadarLayout.setLayoutParams(lp);
        RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) this.mBigLeftSideRadar.getLayoutParams();
        lp2.topMargin = this.mRadarMarginTop + offset;
        this.mBigLeftSideRadar.setLayoutParams(lp2);
        RelativeLayout.LayoutParams lp3 = (RelativeLayout.LayoutParams) this.mBigRightSideRadar.getLayoutParams();
        lp3.topMargin = this.mRadarMarginTop + offset;
        this.mBigRightSideRadar.setLayoutParams(lp3);
    }

    private int getOffsetY() {
        if (i2b(Mcu.GetReverse())) {
            return -570;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        mbRadarWin = true;
        this.mOffsetY = getOffsetY();
        setBigRadarOffsetY(this.mOffsetY);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        mbRadarWin = false;
    }

    public static boolean IsRadarWin() {
        return mbRadarWin;
    }

    public static boolean isForeRadarValid() {
        if (GetTickCount() - ((long) Can.mRadarForeInfo.dwTick) < 4000) {
            return true;
        }
        return false;
    }

    public static boolean isRearRadarValid() {
        if (GetTickCount() - ((long) Can.mRadarRearInfo.dwTick) < 4000) {
            return true;
        }
        return false;
    }

    public static void invalidateRadar(RadarUIView foreRadar, RadarUIView rearRadar) {
        switch (CanJni.GetCanType()) {
            case 13:
            case 146:
                invalidateFordRadar(foreRadar, rearRadar);
                return;
            default:
                invalidateCommonRadar(foreRadar, rearRadar);
                return;
        }
    }

    public static void invalidateSideRadar(RadarUIView leftRadar, RadarUIView rightRadar) {
        switch (CanJni.GetCanType()) {
            case 13:
            case 146:
                invalidateFordSideRadar(leftRadar, rightRadar);
                return;
            default:
                if (CanJni.IsHaveLrRadar()) {
                    invalidateCommonSideRadar(leftRadar, rightRadar);
                    return;
                }
                return;
        }
    }

    private static void invalidateCommonSideRadar(RadarUIView leftRadar, RadarUIView rightRadar) {
        CanDataInfo.CAN_RadarInfo mLeftRadar = Can.mRadarLeftInfo;
        CanDataInfo.CAN_RadarInfo mRightRadar = Can.mRadarRightInfo;
        leftRadar.invalidateRadar(new int[]{mLeftRadar.nLeftDis, mLeftRadar.nMidLtDis, mLeftRadar.nMidRtDis, mLeftRadar.nRightDis}, 4, 4);
        rightRadar.invalidateRadar(new int[]{mRightRadar.nLeftDis, mRightRadar.nMidLtDis, mRightRadar.nMidRtDis, mRightRadar.nRightDis}, 4, 4);
    }

    private static void invalidateCommonRadar(RadarUIView foreRadar, RadarUIView rearRadar) {
        CanDataInfo.CAN_RadarInfo mForeRadar = Can.mRadarForeInfo;
        CanDataInfo.CAN_RadarInfo mRearRadar = Can.mRadarRearInfo;
        if (isForeRadarValid()) {
            foreRadar.invalidateRadar(new int[]{mForeRadar.nLeftDis, mForeRadar.nMidLtDis, mForeRadar.nMidRtDis, mForeRadar.nRightDis}, mForeLeftMax, mForeMidLtMax);
        }
        if (isRearRadarValid()) {
            rearRadar.invalidateRadar(new int[]{mRearRadar.nLeftDis, mRearRadar.nMidLtDis, mRearRadar.nMidRtDis, mRearRadar.nRightDis}, mRearLeftMax, mRearMidLtMax);
        }
    }

    private static void invalidateFordSideRadar(RadarUIView leftRadar, RadarUIView rightRadar) {
        CanDataInfo.FordForeRadarEx fore = new CanDataInfo.FordForeRadarEx();
        CanJni.FordGetForeRadarEx(fore);
        CanDataInfo.FordRearRadarEx rear = new CanDataInfo.FordRearRadarEx();
        CanJni.FordGetRearRadarEx(rear);
        int[] leftRadarData = {fore.LtMidAssist, rear.LtMidAssist, rear.LtAssist};
        for (int i = 0; i < leftRadarData.length; i++) {
            int data = leftRadarData[i];
            if (data >= 2 && data <= 7) {
                leftRadarData[i] = 2;
            } else if (data != 1) {
                leftRadarData[i] = 0;
            }
        }
        leftRadar.invalidateRadar(leftRadarData, 2, 2);
        int[] rightRadarData = {fore.RtMidAssist, rear.RtMidAssist, rear.RtAssist};
        for (int i2 = 0; i2 < rightRadarData.length; i2++) {
            int data2 = rightRadarData[i2];
            if (data2 >= 2 && data2 <= 7) {
                rightRadarData[i2] = 2;
            } else if (data2 != 1) {
                rightRadarData[i2] = 0;
            }
        }
        rightRadar.invalidateRadar(rightRadarData, 2, 2);
    }

    private static void invalidateFordRadar(RadarUIView foreRadar, RadarUIView rearRadar) {
        CanDataInfo.CAN_RadarInfo mForeRadar = Can.mRadarForeInfo;
        CanDataInfo.CAN_RadarInfo mRearRadar = Can.mRadarRearInfo;
        if (isForeRadarValid()) {
            CanDataInfo.FordForeRadarEx fore = new CanDataInfo.FordForeRadarEx();
            CanJni.FordGetForeRadarEx(fore);
            foreRadar.invalidateRadar(new int[]{fore.LtAssist, mForeRadar.nLeftDis, mForeRadar.nMidLtDis, mForeRadar.nMidRtDis, mForeRadar.nRightDis, fore.RtAssist}, 7, 13);
        }
        if (isRearRadarValid()) {
            rearRadar.invalidateRadar(new int[]{mRearRadar.nLeftDis, mRearRadar.nMidLtDis, mRearRadar.nMidRtDis, mRearRadar.nRightDis}, 7, 31);
        }
    }

    public boolean isRadarValid() {
        if (GetTickCount() < CanFunc.mLastRadarTick + 3000) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void checkReserve() {
        int offset = getOffsetY();
        if (this.mOffsetY != offset) {
            this.mOffsetY = ValCal.NumApproach(offset, this.mOffsetY);
            setBigRadarOffsetY(this.mOffsetY);
        }
    }

    public void UserAll() {
        if (CanFunc.mbRadarUIUpdate) {
            CanFunc.mbRadarUIUpdate = false;
            invalidateRadar(this.mBigForeRadar, this.mBigRearRadar);
            invalidateRadar(this.mRightForeRadar, this.mRightRearRadar);
            invalidateSideRadar(this.mLeftSideRadar, this.mRightSideRadar);
            invalidateSideRadar(this.mBigLeftSideRadar, this.mBigRightSideRadar);
        }
        checkReserve();
        if (!isRadarValid()) {
            finish();
        }
    }

    public static void InitRadarMax(int canId) {
        switch (canId) {
            case 2:
                mForeLeftMax = 4;
                mForeMidLtMax = 8;
                mForeMidRtMax = 8;
                mForeRightMax = 4;
                mRearLeftMax = 4;
                mRearMidLtMax = 10;
                mRearMidRtMax = 10;
                mRearRightMax = 4;
                return;
            case 88:
                if (CanJni.GetSubType() == 3) {
                    mForeLeftMax = 4;
                    mForeMidLtMax = 4;
                    mForeMidRtMax = 4;
                    mForeRightMax = 4;
                    mRearLeftMax = 4;
                    mRearMidLtMax = 4;
                    mRearMidRtMax = 4;
                    mRearRightMax = 4;
                    return;
                }
                return;
            case 110:
                if (CanJni.GetSubType() != 2) {
                    mForeLeftMax = 4;
                    mForeMidLtMax = 10;
                    mForeMidRtMax = 10;
                    mForeRightMax = 4;
                    mRearLeftMax = 4;
                    mRearMidLtMax = 10;
                    mRearMidRtMax = 10;
                    mRearRightMax = 4;
                    return;
                }
                return;
            default:
                return;
        }
    }
}
