package com.ts.can.ford;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanACActivity;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanFordACActivity extends CanBaseActivity implements UserCallBack {
    private static final int AC_SHOW_TIME = 3000;
    public static final boolean DEBUG = false;
    public static final String TAG = "CanFordACActivity";
    private CanDataInfo.CAN_ACInfo mACInfo;
    private ImageView mAc;
    private ImageView mAcMax;
    private ImageView mAuto;
    private ImageView mDual;
    private ImageView mEco;
    private ImageView mForeLight;
    private CustomImgView mForePWR;
    private ImageView mFrontMax;
    private long mLastTime = 0;
    private ImageView mLoop;
    private ImageView mLtFire;
    private ImageView[] mLtHot = new ImageView[3];
    private TextView mLtTemp;
    private RelativeLayoutManager mManger;
    private CanDataInfo.FordReadAC mRearAcData = new CanDataInfo.FordReadAC();
    private ImageView mRearLight;
    private ImageView mRearLock;
    private CustomImgView mRearPWR;
    private CustomImgView[] mRearTemp = new CustomImgView[9];
    private CustomImgView[] mRearWind = new CustomImgView[7];
    private ImageView mRtFire;
    private ImageView[] mRtHot = new ImageView[3];
    private TextView mRtTemp;
    private ImageView mWindDn;
    private ImageView mWindFr;
    private ProgressBar mWindProgress;
    private ImageView mWindPx;
    private ImageView mWindUp;
    private TextView mWindVal;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_ford_ac);
        if (MainSet.GetScreenType() == 3) {
            initScreen_768x1024();
        } else {
            initCommonScreen();
        }
    }

    private void initScreen_768x1024() {
        this.mLoop = (ImageView) findViewById(R.id.can_ac_loop);
        this.mEco = (ImageView) findViewById(R.id.can_ac_eco);
        this.mDual = (ImageView) findViewById(R.id.can_ac_dual);
        this.mAc = (ImageView) findViewById(R.id.can_ac_ac);
        this.mAuto = (ImageView) findViewById(R.id.can_ac_auto);
        this.mRearLight = (ImageView) findViewById(R.id.can_ac_rear_light);
        this.mForeLight = (ImageView) findViewById(R.id.can_ac_fore_light);
        this.mRearLock = (ImageView) findViewById(R.id.can_ac_rear_lock);
        this.mAcMax = (ImageView) findViewById(R.id.can_ac_acmax);
        this.mFrontMax = (ImageView) findViewById(R.id.can_ac_frontmax);
        this.mLtFire = (ImageView) findViewById(R.id.can_ac_ltfire);
        this.mLtHot[0] = (ImageView) findViewById(R.id.can_ac_lthot1);
        this.mLtHot[1] = (ImageView) findViewById(R.id.can_ac_lthot2);
        this.mLtHot[2] = (ImageView) findViewById(R.id.can_ac_lthot3);
        this.mRtFire = (ImageView) findViewById(R.id.can_ac_rtfire);
        this.mRtHot[0] = (ImageView) findViewById(R.id.can_ac_rthot1);
        this.mRtHot[1] = (ImageView) findViewById(R.id.can_ac_rthot2);
        this.mRtHot[2] = (ImageView) findViewById(R.id.can_ac_rthot3);
        this.mWindFr = (ImageView) findViewById(R.id.can_ac_wind_fore);
        this.mWindUp = (ImageView) findViewById(R.id.can_ac_wind_up);
        this.mWindPx = (ImageView) findViewById(R.id.can_ac_wind_px);
        this.mWindDn = (ImageView) findViewById(R.id.can_ac_wind_dn);
        this.mLtTemp = (TextView) findViewById(R.id.can_ac_lttemp);
        this.mRtTemp = (TextView) findViewById(R.id.can_ac_rttemp);
        this.mWindVal = (TextView) findViewById(R.id.can_ac_wind_txt);
        this.mWindProgress = (ProgressBar) findViewById(R.id.can_ac_wind_progress);
        setIvStateDrawable(this.mEco, R.drawable.conditioning_eco_up, R.drawable.conditioning_eco_dn);
        setIvStateDrawable(this.mDual, R.drawable.conditioning_dual_up, R.drawable.conditioning_dual_dn);
        setIvStateDrawable(this.mAc, R.drawable.conditioning_ac_up, R.drawable.conditioning_ac_dn);
        setIvStateDrawable(this.mRearLight, R.drawable.conditioning_heat_up, R.drawable.conditioning_heat_dn);
        setIvStateDrawable(this.mForeLight, R.drawable.conditioning_wind_up, R.drawable.conditioning_wind_dn);
        setIvStateDrawable(this.mRearLock, R.drawable.conditioning_rear_up, R.drawable.conditioning_rear_dn);
        setIvStateDrawable(this.mAcMax, R.drawable.conditioning_acmax_up, R.drawable.conditioning_acmax_dn);
        setIvStateDrawable(this.mFrontMax, R.drawable.conditioning_max_up, R.drawable.conditioning_max_dn);
        for (int i = 0; i < 3; i++) {
            setIvStateDrawable(this.mLtHot[i], R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
            setIvStateDrawable(this.mRtHot[i], R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
        }
        this.mManger = new RelativeLayoutManager(this, R.id.can_layout_ford_ac);
        this.mForePWR = this.mManger.AddImage(534, 3, 70, 53);
        this.mForePWR.setStateDrawable(R.drawable.can_rj_gj_up, R.drawable.can_rj_gj_dn);
        this.mRearPWR = this.mManger.AddImage(272, 367, 70, 53);
        this.mRearPWR.setStateDrawable(R.drawable.can_rj_gj_up, R.drawable.can_rj_gj_dn);
        for (int i2 = 0; i2 < this.mRearWind.length; i2++) {
            this.mRearWind[i2] = this.mManger.AddImage((i2 * 16) + 63, 374, 14, 32);
            this.mRearWind[i2].setStateDrawable(R.drawable.can_rj_rect_gray, R.drawable.can_rj_rect_blue);
        }
        for (int i3 = 0; i3 < this.mRearTemp.length; i3++) {
            this.mRearTemp[i3] = this.mManger.AddImage((i3 * 16) + CanCameraUI.BTN_CCH9_MODE7, 374, 14, 32);
            this.mRearTemp[i3].setImageResource(R.drawable.can_rj_rect_gray);
        }
    }

    private void initCommonScreen() {
        this.mLoop = (ImageView) findViewById(R.id.can_ac_loop);
        this.mEco = (ImageView) findViewById(R.id.can_ac_eco);
        this.mDual = (ImageView) findViewById(R.id.can_ac_dual);
        this.mAc = (ImageView) findViewById(R.id.can_ac_ac);
        this.mAuto = (ImageView) findViewById(R.id.can_ac_auto);
        this.mRearLight = (ImageView) findViewById(R.id.can_ac_rear_light);
        this.mForeLight = (ImageView) findViewById(R.id.can_ac_fore_light);
        this.mRearLock = (ImageView) findViewById(R.id.can_ac_rear_lock);
        this.mAcMax = (ImageView) findViewById(R.id.can_ac_acmax);
        this.mFrontMax = (ImageView) findViewById(R.id.can_ac_frontmax);
        this.mLtFire = (ImageView) findViewById(R.id.can_ac_ltfire);
        this.mLtHot[0] = (ImageView) findViewById(R.id.can_ac_lthot1);
        this.mLtHot[1] = (ImageView) findViewById(R.id.can_ac_lthot2);
        this.mLtHot[2] = (ImageView) findViewById(R.id.can_ac_lthot3);
        this.mRtFire = (ImageView) findViewById(R.id.can_ac_rtfire);
        this.mRtHot[0] = (ImageView) findViewById(R.id.can_ac_rthot1);
        this.mRtHot[1] = (ImageView) findViewById(R.id.can_ac_rthot2);
        this.mRtHot[2] = (ImageView) findViewById(R.id.can_ac_rthot3);
        this.mWindFr = (ImageView) findViewById(R.id.can_ac_wind_fore);
        this.mWindUp = (ImageView) findViewById(R.id.can_ac_wind_up);
        this.mWindPx = (ImageView) findViewById(R.id.can_ac_wind_px);
        this.mWindDn = (ImageView) findViewById(R.id.can_ac_wind_dn);
        this.mLtTemp = (TextView) findViewById(R.id.can_ac_lttemp);
        this.mRtTemp = (TextView) findViewById(R.id.can_ac_rttemp);
        this.mWindVal = (TextView) findViewById(R.id.can_ac_wind_txt);
        this.mWindProgress = (ProgressBar) findViewById(R.id.can_ac_wind_progress);
        setIvStateDrawable(this.mEco, R.drawable.conditioning_eco_up, R.drawable.conditioning_eco_dn);
        setIvStateDrawable(this.mDual, R.drawable.conditioning_dual_up, R.drawable.conditioning_dual_dn);
        setIvStateDrawable(this.mAc, R.drawable.conditioning_ac_up, R.drawable.conditioning_ac_dn);
        setIvStateDrawable(this.mRearLight, R.drawable.conditioning_heat_up, R.drawable.conditioning_heat_dn);
        setIvStateDrawable(this.mForeLight, R.drawable.conditioning_wind_up, R.drawable.conditioning_wind_dn);
        setIvStateDrawable(this.mRearLock, R.drawable.conditioning_rear_up, R.drawable.conditioning_rear_dn);
        setIvStateDrawable(this.mAcMax, R.drawable.conditioning_acmax_up, R.drawable.conditioning_acmax_dn);
        setIvStateDrawable(this.mFrontMax, R.drawable.conditioning_max_up, R.drawable.conditioning_max_dn);
        for (int i = 0; i < 3; i++) {
            setIvStateDrawable(this.mLtHot[i], R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
            setIvStateDrawable(this.mRtHot[i], R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
        }
        this.mManger = new RelativeLayoutManager(this, R.id.can_layout_ford_ac);
        this.mForePWR = this.mManger.AddImage(715, (int) getResources().getDimension(R.dimen.y_ford_ac_flg));
        this.mForePWR.setStateDrawable(R.drawable.can_rj_gj_up, R.drawable.can_rj_gj_dn);
        this.mRearPWR = this.mManger.AddImage(362, 458);
        this.mRearPWR.setStateDrawable(R.drawable.can_rj_gj_up, R.drawable.can_rj_gj_dn);
        for (int i2 = 0; i2 < this.mRearWind.length; i2++) {
            this.mRearWind[i2] = this.mManger.AddImage((i2 * 24) + 95, 471);
            this.mRearWind[i2].setStateDrawable(R.drawable.can_rj_rect_gray, R.drawable.can_rj_rect_blue);
        }
        for (int i3 = 0; i3 < this.mRearTemp.length; i3++) {
            this.mRearTemp[i3] = this.mManger.AddImage((i3 * 24) + KeyDef.SKEY_VOLDN_5, 471);
            this.mRearTemp[i3].setImageResource(R.drawable.can_rj_rect_gray);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Can.updateAC();
        updateACUI();
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        Log.d("CanFordACActivity", "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d("CanFordACActivity", "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        this.mLastTime = GetTickCount();
        this.mACInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        if (int2Bool(this.mACInfo.fgLoopDisable)) {
            setIvDrawable(this.mLoop, R.drawable.conditioning_car_up);
        } else if (int2Bool(this.mACInfo.fgAQS)) {
            setIvDrawable(this.mLoop, R.drawable.conditioning_car04_dn);
        } else if (int2Bool(this.mACInfo.fgInnerLoop)) {
            setIvDrawable(this.mLoop, R.drawable.conditioning_car01_dn);
        } else {
            setIvDrawable(this.mLoop, R.drawable.conditioning_car_dn);
        }
        setIvSel(this.mEco, this.mACInfo.fgEco);
        setIvSel(this.mDual, this.mACInfo.fgDual);
        setIvSel(this.mAc, this.mACInfo.fgAC);
        switch (this.mACInfo.nAutoLight) {
            case 1:
                setIvDrawable(this.mAuto, R.drawable.conditioning_auto02_dn);
                break;
            case 3:
                setIvDrawable(this.mAuto, R.drawable.conditioning_auto01_dn);
                break;
            default:
                setIvDrawable(this.mAuto, R.drawable.conditioning_auto_up);
                break;
        }
        setIvSel(this.mRearLight, this.mACInfo.fgRearLight);
        setIvSel(this.mForeLight, this.mACInfo.fgDFBL);
        setIvSel(this.mRearLock, this.mACInfo.fgRearLock);
        setIvSel(this.mAcMax, this.mACInfo.fgACMax);
        setIvSel(this.mFrontMax, this.mACInfo.fgMaxFornt);
        int ltHot = this.mACInfo.nLtChairHot & 3;
        int rtHot = this.mACInfo.nRtChairHot & 3;
        for (int i = 0; i < ltHot; i++) {
            this.mLtHot[i].setSelected(true);
        }
        for (int i2 = ltHot; i2 < 3; i2++) {
            this.mLtHot[i2].setSelected(false);
        }
        for (int i3 = 0; i3 < rtHot; i3++) {
            this.mRtHot[i3].setSelected(true);
        }
        for (int i4 = rtHot; i4 < 3; i4++) {
            this.mRtHot[i4].setSelected(false);
        }
        setIvShow(this.mLtFire, ltHot);
        setIvShow(this.mRtFire, rtHot);
        for (int i5 = 0; i5 < 3; i5++) {
            setIvShow(this.mLtHot[i5], ltHot);
            setIvShow(this.mRtHot[i5], rtHot);
        }
        setIvShow(this.mWindFr, this.mACInfo.fgForeWindMode);
        setIvShow(this.mWindUp, this.mACInfo.fgUpWind);
        setIvShow(this.mWindPx, this.mACInfo.fgParallelWind);
        setIvShow(this.mWindDn, this.mACInfo.fgDownWind);
        this.mWindVal.setText(Integer.toString(this.mACInfo.nWindValue));
        this.mWindProgress.setMax(this.mACInfo.nWindMax);
        this.mWindProgress.setProgress(this.mACInfo.nWindValue);
        try {
            this.mLtTemp.setText(this.mACInfo.szLtTemp);
            this.mRtTemp.setText(this.mACInfo.szRtTemp);
        } catch (Exception e) {
            Log.e("CanFordACActivity", "set Temp text Exception!");
        }
        CanJni.FordGetRearAC(this.mRearAcData);
        this.mForePWR.SetSel(this.mACInfo.PWR);
        this.mRearPWR.SetSel(this.mRearAcData.PWR);
        int rearWind = this.mRearAcData.Wind & 7;
        for (int i6 = 0; i6 < rearWind; i6++) {
            this.mRearWind[i6].setSelected(true);
        }
        for (int i7 = rearWind; i7 < 7; i7++) {
            this.mRearWind[i7].setSelected(false);
        }
        int rearTemp = this.mRearAcData.Hot;
        if (rearTemp > 9) {
            rearTemp = 9;
        }
        if (9 == rearTemp) {
            for (int i8 = 0; i8 < 9; i8++) {
                this.mRearTemp[i8].setImageResource(0);
            }
            return;
        }
        this.mRearTemp[4].setImageResource(R.drawable.can_rj_rect_white);
        if (rearTemp <= 4) {
            for (int i9 = 0; i9 < rearTemp; i9++) {
                this.mRearTemp[i9].setImageResource(R.drawable.can_rj_rect_gray);
            }
            for (int i10 = rearTemp; i10 < 4; i10++) {
                this.mRearTemp[i10].setImageResource(R.drawable.can_rj_rect_blue);
            }
            for (int i11 = 5; i11 < 9; i11++) {
                this.mRearTemp[i11].setImageResource(R.drawable.can_rj_rect_gray);
            }
            return;
        }
        for (int i12 = 0; i12 < 4; i12++) {
            this.mRearTemp[i12].setImageResource(R.drawable.can_rj_rect_gray);
        }
        for (int i13 = 5; i13 <= rearTemp; i13++) {
            this.mRearTemp[i13].setImageResource(R.drawable.can_rj_rect_red);
        }
        for (int i14 = rearTemp + 1; i14 < 9; i14++) {
            this.mRearTemp[i14].setImageResource(R.drawable.can_rj_rect_gray);
        }
    }

    public static boolean showAc(Context context) {
        if (!Can.updateAC()) {
            return false;
        }
        Intent intent = new Intent();
        intent.setClass(context, CanACActivity.class);
        intent.setFlags(268435456);
        context.startActivity(intent);
        return true;
    }

    public void UserAll() {
        Can.updateAC();
        if (Can.mACInfo.Update != 0) {
            updateACUI();
        }
        long GetTickCount = GetTickCount();
        long j = CanFunc.mLastACTick;
    }
}
