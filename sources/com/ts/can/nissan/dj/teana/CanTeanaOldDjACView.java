package com.ts.can.nissan.dj.teana;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseACView;
import com.ts.canview.MyProgressBar;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanTeanaOldDjACView extends CanBaseACView {
    public static final int ITEM_AC = 2;
    public static final int ITEM_AC_MAX = 1;
    public static final int ITEM_AQS = 23;
    public static final int ITEM_AUTO = 4;
    public static final int ITEM_FORE_WIND = 5;
    public static final int ITEM_ION = 24;
    public static final int ITEM_LOOP = 3;
    public static final int ITEM_LTEMP_DEC = 8;
    public static final int ITEM_LTEMP_INC = 7;
    public static final int ITEM_LTSEAT_HOT = 15;
    public static final int ITEM_MODE = 22;
    public static final int ITEM_OFF = 17;
    public static final int ITEM_REAR_WIND = 6;
    public static final int ITEM_RTEMP_DEC = 14;
    public static final int ITEM_RTEMP_INC = 13;
    public static final int ITEM_RTSEAT_HOT = 16;
    public static final int ITEM_SYNC = 20;
    public static final int ITEM_WD_DN = 11;
    public static final int ITEM_WD_PX = 9;
    public static final int ITEM_WD_PX_DN = 10;
    public static final int ITEM_WD_UP = 21;
    public static final int ITEM_WD_UP_DN = 12;
    public static final int ITEM_WIND_DEC = 18;
    public static final int ITEM_WIND_INC = 19;
    private CanDataInfo.CAN_ACInfo mACInfo;
    private ParamButton mBtnAc;
    private ParamButton mBtnAcMax;
    private ParamButton mBtnAuto;
    private ParamButton mBtnForeWind;
    private ParamButton mBtnLoop;
    private ParamButton mBtnLtHot;
    private ParamButton mBtnLtTempDec;
    private ParamButton mBtnLtTempInc;
    private ParamButton mBtnMode;
    private ParamButton mBtnOff;
    private ParamButton mBtnRearWind;
    private ParamButton mBtnRtHot;
    private ParamButton mBtnRtTempDec;
    private ParamButton mBtnRtTempInc;
    private ParamButton mBtnSync;
    private ParamButton mBtnWdDn;
    private ParamButton mBtnWdPx;
    private ParamButton mBtnWdPxDn;
    private ParamButton mBtnWdUp;
    private ParamButton mBtnWdUpDn;
    private ParamButton mBtnWindDec;
    private ParamButton mBtnWindInc;
    private CustomImgView[] mIvIonLev;
    private CustomImgView mIvWindAuto;
    private CustomTextView mTvLtTemp;
    private CustomTextView mTvRtTemp;
    private CustomTextView mTvWindVal;
    private MyProgressBar mWindProg;

    public CanTeanaOldDjACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (action != 0) {
            if (1 == action) {
                switch (Id) {
                    case 2:
                        CanJni.TeanaOldDjAirSet(8, 0);
                        break;
                    case 3:
                        CanJni.TeanaOldDjAirSet(3, 0);
                        break;
                    case 4:
                        CanJni.TeanaOldDjAirSet(1, 0);
                        break;
                    case 5:
                        CanJni.TeanaOldDjAirSet(33, 0);
                        break;
                    case 6:
                        CanJni.TeanaOldDjAirSet(5, 0);
                        break;
                    case 7:
                        CanJni.TeanaOldDjAirSet(12, 0);
                        break;
                    case 8:
                        CanJni.TeanaOldDjAirSet(13, 0);
                        break;
                    case 13:
                        CanJni.TeanaOldDjAirSet(14, 0);
                        break;
                    case 14:
                        CanJni.TeanaOldDjAirSet(15, 0);
                        break;
                    case 17:
                        CanJni.TeanaOldDjAirSet(10, 0);
                        break;
                    case 18:
                        CanJni.TeanaOldDjAirSet(7, 0);
                        break;
                    case 19:
                        CanJni.TeanaOldDjAirSet(6, 0);
                        break;
                    case 20:
                        CanJni.TeanaOldDjAirSet(2, 0);
                        break;
                    case 22:
                        CanJni.TeanaOldDjAirSet(9, 0);
                        break;
                }
            }
        } else {
            switch (Id) {
                case 2:
                    CanJni.TeanaOldDjAirSet(8, 1);
                    break;
                case 3:
                    CanJni.TeanaOldDjAirSet(3, 1);
                    break;
                case 4:
                    CanJni.TeanaOldDjAirSet(1, 1);
                    break;
                case 5:
                    CanJni.TeanaOldDjAirSet(33, 1);
                    break;
                case 6:
                    CanJni.TeanaOldDjAirSet(5, 1);
                    break;
                case 7:
                    CanJni.TeanaOldDjAirSet(12, 1);
                    break;
                case 8:
                    CanJni.TeanaOldDjAirSet(13, 1);
                    break;
                case 13:
                    CanJni.TeanaOldDjAirSet(14, 1);
                    break;
                case 14:
                    CanJni.TeanaOldDjAirSet(15, 1);
                    break;
                case 17:
                    CanJni.TeanaOldDjAirSet(10, 1);
                    break;
                case 18:
                    CanJni.TeanaOldDjAirSet(7, 1);
                    break;
                case 19:
                    CanJni.TeanaOldDjAirSet(6, 1);
                    break;
                case 20:
                    CanJni.TeanaOldDjAirSet(2, 1);
                    break;
                case 22:
                    CanJni.TeanaOldDjAirSet(9, 1);
                    break;
            }
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
        setBackgroundResource(R.drawable.can_jeep_ac_bg);
        initCommonScreen();
    }

    private void initCommonScreen() {
        addImage(44, Can.CAN_FORD_SYNC3, R.drawable.can_jeep_ac_tmp);
        addImage(889, Can.CAN_FORD_SYNC3, R.drawable.can_jeep_ac_tmp);
        this.mBtnAc = AddBtn(2, 174, 25, R.drawable.can_jeep_ac_ac_up, R.drawable.can_jeep_ac_ac_dn);
        this.mBtnLoop = AddBtn(3, 344, 25, R.drawable.can_jeep_ac_nxh_up, R.drawable.can_jeep_ac_nxh_dn);
        this.mBtnAuto = AddBtn(4, 513, 25, R.drawable.can_jeep_ac_auto_up, R.drawable.can_jeep_ac_auto_dn);
        this.mBtnForeWind = AddBtn(5, 682, 25, R.drawable.can_jeep_ac_window_up, R.drawable.can_jeep_ac_window_dn);
        this.mBtnRearWind = AddBtn(6, 852, 25, R.drawable.can_jeep_ac_rear_up, R.drawable.can_jeep_ac_rear_dn);
        this.mBtnLtTempDec = AddBtn(8, 45, KeyDef.RKEY_res1, R.drawable.can_jeep_ac_leng_up, R.drawable.can_jeep_ac_leng_dn);
        this.mBtnLtTempInc = AddBtn(7, 45, 124, R.drawable.can_jeep_ac_re_up, R.drawable.can_jeep_ac_re_dn);
        this.mTvLtTemp = AddTemp(44, Can.CAN_FORD_SYNC3, 93, 51);
        this.mBtnRtTempDec = AddBtn(14, 890, KeyDef.RKEY_res1, R.drawable.can_jeep_ac_leng_up, R.drawable.can_jeep_ac_leng_dn);
        this.mBtnRtTempInc = AddBtn(13, 890, 124, R.drawable.can_jeep_ac_re_up, R.drawable.can_jeep_ac_re_dn);
        this.mTvRtTemp = AddTemp(889, Can.CAN_FORD_SYNC3, 95, 51);
        this.mBtnWdPx = AddBtn(9, KeyDef.RKEY_CMMB_PBC, 150, R.drawable.can_jeep_ac_01_up, R.drawable.can_jeep_ac_01_dn);
        this.mBtnWdDn = AddBtn(11, 460, 150, R.drawable.can_jeep_ac_03_up, R.drawable.can_jeep_ac_03_dn);
        this.mBtnWdUp = AddBtn(21, 607, 150, R.drawable.can_jeep_ac_09_up, R.drawable.can_jeep_ac_09_dn);
        this.mBtnLtHot = AddBtn(15, Can.CAN_SITECHDEV_CW, 294, R.drawable.can_jeep_ac_lchair_up, R.drawable.can_jeep_ac_lchair_up);
        this.mBtnRtHot = AddBtn(16, 536, 294, R.drawable.can_jeep_ac_rchair_up, R.drawable.can_jeep_ac_rchair_up);
        this.mBtnLtHot.setPadding(0, 0, 162, 0);
        this.mBtnLtHot.setTextSize(0, 30.0f);
        this.mBtnLtHot.setGravity(21);
        this.mBtnLtHot.setTextColor(-1);
        this.mBtnRtHot.setPadding(180, 0, 0, 0);
        this.mBtnRtHot.setTextSize(0, 30.0f);
        this.mBtnRtHot.setGravity(19);
        this.mBtnRtHot.setTextColor(-1);
        this.mBtnOff = AddBtn(17, 0, 407, R.drawable.can_jeep_ac_off_up, R.drawable.can_jeep_ac_off_dn);
        this.mBtnWindDec = AddBtn(18, 134, 407, R.drawable.can_jeep_ac_xfans_up, R.drawable.can_jeep_ac_xfans_dn);
        this.mBtnWindInc = AddBtn(19, 763, 407, R.drawable.can_jeep_ac_dfans_up, R.drawable.can_jeep_ac_dfans_dn);
        this.mWindProg = new MyProgressBar(getActivity(), R.drawable.can_jeep_ac_pro_up, R.drawable.can_jeep_ac_pro_dn);
        this.mWindProg.SetMinMax(0, 7);
        getRelativeManager().AddViewWrapContent(this.mWindProg, 287, 448);
        this.mIvWindAuto = addImage(457, 450, R.drawable.can_yl_wind_auto);
        this.mBtnSync = AddBtn(20, 893, 407, R.drawable.can_jeep_ac_sync_up, R.drawable.can_jeep_ac_sync_dn);
        this.mTvWindVal = AddTemp(717, 441, 32, 40);
        this.mBtnMode = AddBtn(22, 0, 25, R.drawable.can_jeep_ac_mode_up, R.drawable.can_jeep_ac_mode_dn);
        this.mIvIonLev = new CustomImgView[3];
        for (int i = 0; i < 3; i++) {
            this.mIvIonLev[i] = addImage(720, 348 - (i * 6), R.drawable.can_jeep_ac_ye);
            this.mIvIonLev[i].Show(false);
        }
    }

    protected static int uint2Bool(int val) {
        if (val == 0) {
            return 1;
        }
        return 0;
    }

    protected static boolean int2Bool(int val) {
        if (val == 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        this.mACInfo = Can.mACInfo;
        this.mTvLtTemp.setText(this.mACInfo.szLtTemp);
        this.mTvRtTemp.setText(this.mACInfo.szRtTemp);
        this.mBtnAc.SetSel(this.mACInfo.fgAC);
        this.mBtnAuto.SetSel(this.mACInfo.nAutoLight);
        if (this.mACInfo.fgInnerLoop != 0) {
            this.mBtnLoop.setDrawable(R.drawable.can_jeep_ac_nxh_up, R.drawable.can_jeep_ac_nxh_dn);
        } else {
            this.mBtnLoop.setDrawable(R.drawable.can_jeep_ac_wxh_up, R.drawable.can_jeep_ac_wxh_dn);
        }
        this.mBtnForeWind.SetSel(this.mACInfo.fgMaxFornt);
        this.mBtnRearWind.SetSel(this.mACInfo.fgRearLight);
        int i = (this.mACInfo.fgForeWindMode * 1) + (this.mACInfo.fgParallelWind * 2) + (this.mACInfo.fgDownWind * 4);
        this.mBtnWdPx.setSelected(i2b(this.mACInfo.fgParallelWind));
        this.mBtnWdDn.setSelected(i2b(this.mACInfo.fgDownWind));
        this.mBtnWdUp.setSelected(i2b(this.mACInfo.fgUpWind));
        this.mBtnLtHot.SetSel(this.mACInfo.nLtChairHot);
        this.mBtnRtHot.SetSel(this.mACInfo.nRtChairHot);
        switch (this.mACInfo.nLtChairHot) {
            case 0:
                this.mBtnLtHot.setText(R.string.can_off);
                break;
            case 1:
                this.mBtnLtHot.setText(R.string.can_ac_low);
                break;
            case 2:
                this.mBtnLtHot.setText(R.string.can_ac_mid);
                break;
            default:
                this.mBtnLtHot.setText(R.string.can_ac_high);
                break;
        }
        switch (this.mACInfo.nRtChairHot) {
            case 0:
                this.mBtnRtHot.setText(R.string.can_off);
                break;
            case 1:
                this.mBtnRtHot.setText(R.string.can_ac_low);
                break;
            case 2:
                this.mBtnRtHot.setText(R.string.can_ac_mid);
                break;
            default:
                this.mBtnRtHot.setText(R.string.can_ac_high);
                break;
        }
        this.mBtnOff.SetSel(1 - this.mACInfo.PWR);
        this.mBtnSync.SetSel(this.mACInfo.fgDual);
        if (15 == this.mACInfo.nWindValue) {
            this.mWindProg.SetCurPos(0);
            this.mTvWindVal.setText(TXZResourceManager.STYLE_DEFAULT);
            this.mIvWindAuto.Show(true);
        } else {
            this.mWindProg.SetCurPos(this.mACInfo.nWindValue);
            this.mTvWindVal.setText(new StringBuilder(String.valueOf(this.mACInfo.nWindValue)).toString());
            this.mIvWindAuto.Show(false);
        }
        for (int i2 = 0; i2 < 3; i2++) {
            if (this.mACInfo.fgIon > i2) {
                this.mIvIonLev[i2].Show(true);
            } else {
                this.mIvIonLev[i2].Show(false);
            }
        }
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnTouchListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnTouchListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTemp(int x, int y, int w, int h) {
        CustomTextView temp = getRelativeManager().AddCusText(x, y, w, h);
        temp.SetPxSize(40);
        temp.setGravity(17);
        return temp;
    }
}
