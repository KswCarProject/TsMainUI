package com.ts.can.zotye.x5;

import android.app.Activity;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseACActivity;
import com.ts.can.CanBaseACView;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.canview.MyProgressBar;
import com.ts.main.common.KeyTouch;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanZotyetT500ACView extends CanBaseACView {
    public static final int ITEM_AC = 11;
    public static final int ITEM_AUTO = 12;
    public static final int ITEM_FORE_WIND = 8;
    private static final int ITEM_HEAT = 18;
    public static final int ITEM_LOOP_IN = 7;
    public static final int ITEM_LOOP_OUT = 10;
    public static final int ITEM_LTEMP_DEC = 2;
    public static final int ITEM_LTEMP_INC = 1;
    public static final int ITEM_MAXAC = 13;
    public static final int ITEM_MODE = 4;
    public static final int ITEM_PWR = 3;
    public static final int ITEM_REAR_WIND = 9;
    private static final int ITEM_RTEMP_DEC = 17;
    private static final int ITEM_RTEMP_INC = 16;
    public static final int ITEM_WIND_DEC = 5;
    public static final int ITEM_WIND_INC = 6;
    private static final int STATUS_LHOT = 14;
    private static final int STATUS_RHOT = 15;
    protected static boolean mIsAC;
    private CanDataInfo.CAN_ACInfo mACInfo;
    protected boolean mAutoFinish = false;
    private ParamButton mBtnAC;
    private ParamButton mBtnAUTO;
    private ParamButton mBtnForeWind;
    private ParamButton mBtnHeat;
    private ParamButton mBtnLHOT;
    private ParamButton mBtnLoopIn;
    private ParamButton mBtnLoopOut;
    private ParamButton mBtnLtTempDec;
    private ParamButton mBtnLtTempInc;
    private ParamButton mBtnMAXAC;
    private ParamButton mBtnMode;
    private ParamButton mBtnOff;
    private ParamButton mBtnRHOT;
    private ParamButton mBtnRearWind;
    private ParamButton mBtnRtTempDec;
    private ParamButton mBtnRtTempInc;
    private ParamButton mBtnWindDec;
    private ParamButton mBtnWindInc;
    private CustomImgView mIvWindAuto;
    private CustomImgView mIvWindDirectAuto;
    private CustomImgView mIvWindDn;
    private CustomImgView mIvWindPx;
    private CustomImgView mIvWindUp;
    private CustomImgView[] mLHotIcons;
    private CustomImgView[] mRHotIcons;
    private TextView mTvLtTemp;
    private TextView mTvRtTemp;
    private MyProgressBar mWindProg;

    public CanZotyetT500ACView(Activity activity) {
        super(activity);
    }

    public static void showAc() {
        if (!mIsAC) {
            CanFunc.showCanActivity(CanBaseACActivity.class);
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        CanFunc.mLastACTick = SystemClock.uptimeMillis();
        if (action == 0) {
            switch (Id) {
                case 1:
                    CanJni.ZtDmX7CarAcSet(0, 0, 0, 2, 0, 0);
                    break;
                case 2:
                    CanJni.ZtDmX7CarAcSet(0, 0, 0, 1, 0, 0);
                    break;
                case 3:
                    CanJni.ZtDmX7CarAcSet(128, 0, 0, 0, 0, 0);
                    break;
                case 4:
                    CanJni.ZtDmX7CarAcSet(64, 0, 0, 0, 0, 0);
                    break;
                case 5:
                    CanJni.ZtDmX7CarAcSet(0, 1, 0, 0, 0, 0);
                    break;
                case 6:
                    CanJni.ZtDmX7CarAcSet(0, 2, 0, 0, 0, 0);
                    break;
                case 7:
                    CanJni.ZtDmX7CarAcSet(4, 0, 0, 0, 0, 0);
                    break;
                case 8:
                    CanJni.ZtDmX7CarAcSet(16, 0, 0, 0, 0, 0);
                    break;
                case 9:
                    CanJni.ZtDmX7CarAcSet(0, 4, 0, 0, 0, 0);
                    break;
                case 10:
                    CanJni.ZtDmX7CarAcSet(8, 0, 0, 0, 0, 0);
                    break;
                case 11:
                    CanJni.ZtDmX7CarAcSet(2, 0, 0, 0, 0, 0);
                    break;
                case 12:
                    CanJni.ZtDmX7CarAcSet(32, 0, 0, 0, 0, 0);
                    break;
                case 13:
                    CanJni.ZtDmX7CarAcSet(1, 0, 0, 0, 0, 0);
                    break;
                case 14:
                    CanJni.ZtDmX7CarAcSet(0, 0, 0, 0, 0, 1);
                    break;
                case 15:
                    CanJni.ZtDmX7CarAcSet(0, 0, 0, 0, 0, 2);
                    break;
                case 16:
                    CanJni.ZtDmX7CarAcSet(0, 0, 0, 0, 2, 0);
                    break;
                case 17:
                    CanJni.ZtDmX7CarAcSet(0, 0, 0, 0, 1, 0);
                    break;
                case 18:
                    CanJni.ZtDmX7CarAcSet(0, 0, 0, 0, 0, 4);
                    break;
            }
        } else if (1 == action) {
            CanJni.ZtDmX7CarAcSet(0, 0, 0, 0, 0, 0);
        }
        return false;
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
    }

    /* access modifiers changed from: protected */
    public void InitViews() {
        if (CanJni.GetSubType() == 4 || CanJni.GetSubType() == 6 || CanJni.GetSubType() == 7) {
            setBackgroundResource(R.drawable.can_psa_408_bg);
        } else {
            setBackgroundResource(R.drawable.can_mg_bg);
        }
        initCommonScreen();
    }

    private void initCommonScreen() {
        this.mLHotIcons = new CustomImgView[3];
        this.mRHotIcons = new CustomImgView[3];
        this.mACInfo = Can.mACInfo;
        int y = 0;
        if (CanJni.GetSubType() == 4 || CanJni.GetSubType() == 6 || CanJni.GetSubType() == 7) {
            y = -91;
        }
        this.mTvLtTemp = addText(y + 109, 18, 131, 62);
        this.mTvLtTemp.setTextSize(0, 40.0f);
        this.mTvLtTemp.setTextColor(-1);
        this.mTvLtTemp.setGravity(17);
        this.mBtnLtTempInc = addButton(y + 138, 107);
        this.mBtnLtTempInc.setDrawable(R.drawable.can_yl_upward_up, R.drawable.can_yl_upward_dn);
        this.mBtnLtTempDec = addButton(y + 138, 287);
        this.mBtnLtTempDec.setDrawable(R.drawable.can_yl_down_up, R.drawable.can_yl_down_dn);
        this.mTvRtTemp = addText(878, 18, 131, 62);
        this.mTvRtTemp.setTextSize(0, 40.0f);
        this.mTvRtTemp.setTextColor(-1);
        this.mTvRtTemp.setGravity(17);
        this.mBtnRtTempInc = addButton(907, 107);
        this.mBtnRtTempInc.setDrawable(R.drawable.can_yl_upward_up, R.drawable.can_yl_upward_dn);
        this.mBtnRtTempDec = addButton(907, 287);
        this.mBtnRtTempDec.setDrawable(R.drawable.can_yl_down_up, R.drawable.can_yl_down_dn);
        this.mWindProg = new MyProgressBar(getActivity(), R.drawable.can_yl_rect_up, R.drawable.can_yl_rect_dn);
        this.mWindProg.SetMinMax(0, 7);
        this.mWindProg.SetCurPos(1);
        getRelativeManager().AddViewWrapContent(this.mWindProg, y + 352, 292);
        this.mBtnWindDec = addButton(y + 287, 271);
        this.mBtnWindDec.setDrawable(R.drawable.can_yl_jian_up, R.drawable.can_yl_jian_dn);
        this.mBtnWindInc = addButton(y + KeyDef.SKEY_SPEECH_3, 271);
        this.mBtnWindInc.setDrawable(R.drawable.can_yl_jia_up, R.drawable.can_yl_jia_dn);
        this.mIvWindAuto = addImage(y + CanCameraUI.BTN_TRUMPCHI_GS7_MODE6, 296, R.drawable.can_yl_wind_auto);
        addImage(y + CanCameraUI.BTN_TRUMPCHI_GS7_MODE4, 76, R.drawable.can_mg_people);
        this.mIvWindUp = addImage(y + CanCameraUI.BTN_VW_WC_MODE1, 62, R.drawable.can_mg_wind);
        this.mIvWindPx = addImage(y + 569, 89, R.drawable.can_mg_right);
        this.mIvWindDn = addImage(y + CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST9, 112, R.drawable.can_mg_down);
        this.mIvWindDirectAuto = addImage(y + 535, 84, R.drawable.can_mg_auto);
        this.mBtnOff = addButton(y + KeyTouch.GAMMA_MAX_NUM, 72);
        this.mBtnOff.setDrawable(R.drawable.can_mg_del_up, R.drawable.can_mg_del_dn2);
        this.mBtnMode = addButton(y + 747, 72);
        this.mBtnMode.setDrawable(R.drawable.can_mg_mode_up, R.drawable.can_mg_mode_dn);
        this.mBtnLoopIn = addButton(53, 418);
        this.mBtnLoopIn.setDrawable(R.drawable.can_mg_car01_up, R.drawable.can_mg_car01_dn);
        this.mBtnLoopOut = addButton(190, 418);
        this.mBtnLoopOut.setDrawable(R.drawable.can_mg_car02_up, R.drawable.can_mg_car02_dn);
        this.mBtnForeWind = addButton(KeyDef.RKEY_RADIO_5S, 418);
        this.mBtnForeWind.setDrawable(R.drawable.can_mg_wind_up, R.drawable.can_mg_wind_dn);
        this.mBtnRearWind = addButton(465, 418);
        this.mBtnRearWind.setDrawable(R.drawable.can_mg_rear_up, R.drawable.can_mg_rear_dn);
        this.mBtnAC = addButton(CanCameraUI.BTN_GOLF_WC_MODE3, 418);
        this.mBtnAC.setDrawable(R.drawable.can_psa_408_ac_up, R.drawable.can_psa_408_ac_dn);
        this.mBtnAUTO = addButton(739, 418);
        this.mBtnAUTO.setDrawable(R.drawable.can_mg_auto_up, R.drawable.can_mg_auto_dn);
        this.mBtnMAXAC = addButton(876, 418);
        this.mBtnMAXAC.setDrawable(R.drawable.can_mg_acmax_up, R.drawable.can_mg_acmax_dn);
        this.mBtnLHOT = addButton(y + 360, 172);
        this.mBtnLHOT.setDrawable(R.drawable.can_rh7_ljr0_up, R.drawable.can_rh7_ljr0_dn);
        this.mBtnRHOT = addButton(y + 741, 172);
        this.mBtnRHOT.setDrawable(R.drawable.can_rh7_rjr0_up, R.drawable.can_rh7_rjr0_dn);
        this.mBtnHeat = addButton(885, 192);
        this.mBtnHeat.setDrawable(R.drawable.can_psa_heat_up, R.drawable.can_psa_heat_dn);
        for (int i = 0; i < this.mLHotIcons.length; i++) {
            this.mLHotIcons[i] = addImage((i * 8) + 388 + y, 222, R.drawable.can_rh7_jt);
        }
        for (int i2 = 0; i2 < this.mRHotIcons.length; i2++) {
            this.mRHotIcons[i2] = addImage((i2 * 8) + KeyDef.SKEY_VOLDN_3 + y, 222, R.drawable.can_rh7_jt);
        }
        setIdTouchListener(this.mBtnLtTempInc, 1).setIdTouchListener(this.mBtnLtTempDec, 2).setIdTouchListener(this.mBtnWindDec, 5).setIdTouchListener(this.mBtnWindInc, 6).setIdTouchListener(this.mBtnOff, 3).setIdTouchListener(this.mBtnMode, 4).setIdTouchListener(this.mBtnLoopIn, 7).setIdTouchListener(this.mBtnLoopOut, 10).setIdTouchListener(this.mBtnForeWind, 8).setIdTouchListener(this.mBtnRearWind, 9).setIdTouchListener(this.mBtnAC, 11).setIdTouchListener(this.mBtnAUTO, 12).setIdTouchListener(this.mBtnMAXAC, 13).setIdTouchListener(this.mBtnLHOT, 14).setIdTouchListener(this.mBtnRHOT, 15).setIdTouchListener(this.mBtnRtTempInc, 16).setIdTouchListener(this.mBtnRtTempDec, 17).setIdTouchListener(this.mBtnHeat, 18);
        if (CanJni.GetSubType() == 4 || CanJni.GetSubType() == 6 || CanJni.GetSubType() == 7) {
            this.mTvRtTemp.setVisibility(0);
            this.mBtnRtTempDec.setVisibility(0);
            this.mBtnRtTempInc.setVisibility(0);
        } else {
            this.mTvRtTemp.setVisibility(8);
            this.mBtnRtTempDec.setVisibility(8);
            this.mBtnRtTempInc.setVisibility(8);
        }
        if (CanJni.GetSubType() == 7) {
            this.mBtnHeat.setVisibility(0);
        } else {
            this.mBtnHeat.setVisibility(8);
        }
    }

    private void setLHotValue(int value) {
        int i = 0;
        while (i < this.mLHotIcons.length) {
            this.mLHotIcons[i].Show(i < value);
            i++;
        }
    }

    private void setRHotValue(int value) {
        int i = 0;
        while (i < this.mRHotIcons.length) {
            this.mRHotIcons[i].Show(i < value);
            i++;
        }
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        this.mACInfo = Can.mACInfo;
        this.mTvLtTemp.setText(this.mACInfo.szLtTemp);
        this.mTvRtTemp.setText(this.mACInfo.szRtTemp);
        this.mBtnOff.SetSel(1 - this.mACInfo.PWR);
        this.mIvWindAuto.Show(this.mACInfo.fgAutoWind);
        if (this.mACInfo.fgAutoWind != 0) {
            this.mWindProg.SetCurPos(0);
        } else {
            this.mWindProg.SetCurPos(this.mACInfo.nWindValue);
        }
        this.mIvWindUp.Show(this.mACInfo.fgUpWind);
        this.mIvWindPx.Show(this.mACInfo.fgParallelWind);
        this.mIvWindDn.Show(this.mACInfo.fgDownWind);
        this.mIvWindDirectAuto.Show(this.mACInfo.nWindAutoLevel);
        this.mBtnForeWind.SetSel(this.mACInfo.fgDFBL);
        this.mBtnRearWind.SetSel(this.mACInfo.fgRearLight);
        this.mBtnAC.SetSel(this.mACInfo.fgAC);
        this.mBtnLoopIn.SetSel(this.mACInfo.fgInnerLoop);
        this.mBtnLoopOut.SetSel(Neg(this.mACInfo.fgInnerLoop));
        this.mBtnMAXAC.SetSel(this.mACInfo.fgACMax);
        this.mBtnAUTO.SetSel(this.mACInfo.nAutoLight);
        setLHotValue(this.mACInfo.nLtChairHot);
        setRHotValue(this.mACInfo.nRtChairHot);
        this.mBtnHeat.SetSel(this.mACInfo.fgHeat);
    }

    public void doOnResume() {
        super.doOnResume();
        mIsAC = true;
    }

    public void doOnPause() {
        super.doOnPause();
        mIsAC = false;
    }
}
