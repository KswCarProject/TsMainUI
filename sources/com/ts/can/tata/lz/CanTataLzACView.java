package com.ts.can.tata.lz;

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

public class CanTataLzACView extends CanBaseACView {
    public static final int ITEM_AC = 2;
    public static final int ITEM_AQS = 23;
    public static final int ITEM_AUTO = 4;
    public static final int ITEM_ECO = 25;
    public static final int ITEM_FORE_WIND = 5;
    public static final int ITEM_HEAT = 1;
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
    private ParamButton mBtnAuto;
    private ParamButton mBtnEco;
    private ParamButton mBtnForeWind;
    private ParamButton mBtnHeat;
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

    public CanTataLzACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (action == 0) {
            switch (Id) {
                case 2:
                    CanJni.TaTaLzCarSet(13, 8);
                    break;
                case 3:
                    CanJni.TaTaLzCarSet(13, 9);
                    break;
                case 4:
                    CanJni.TaTaLzCarSet(13, 10);
                    break;
                case 5:
                    CanJni.TaTaLzCarSet(13, 12);
                    break;
                case 6:
                    CanJni.TaTaLzCarSet(13, 13);
                    break;
                case 7:
                    CanJni.TaTaLzCarSet(13, 1);
                    break;
                case 8:
                    CanJni.TaTaLzCarSet(13, 2);
                    break;
                case 13:
                    CanJni.TaTaLzCarSet(13, 3);
                    break;
                case 14:
                    CanJni.TaTaLzCarSet(13, 4);
                    break;
                case 18:
                    CanJni.TaTaLzCarSet(13, 6);
                    break;
                case 19:
                    CanJni.TaTaLzCarSet(13, 5);
                    break;
                case 20:
                    CanJni.TaTaLzCarSet(13, 7);
                    break;
                case 22:
                    CanJni.TaTaLzCarSet(13, 14);
                    break;
                case 25:
                    CanJni.TaTaLzCarSet(13, 11);
                    break;
            }
        } else if (1 == action) {
            CanJni.TaTaLzCarSet(13, 0);
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
        this.mBtnEco = AddBtn(25, 0, 407, 134, 111, R.drawable.can_jeep_ac_kong01_up, R.drawable.can_jeep_ac_kong01_dn, "ECO");
        this.mBtnAc = AddBtn(2, 174, 25, R.drawable.can_jeep_ac_ac_up, R.drawable.can_jeep_ac_ac_dn);
        this.mBtnLoop = AddBtn(3, 344, 25, R.drawable.can_jeep_ac_nxh_up, R.drawable.can_jeep_ac_nxh_dn);
        this.mBtnAuto = AddBtn(4, 513, 25, R.drawable.can_jeep_ac_auto_up, R.drawable.can_jeep_ac_auto_dn);
        this.mBtnForeWind = AddBtn(5, 682, 25, R.drawable.can_jeep_ac_window_up, R.drawable.can_jeep_ac_window_dn);
        this.mBtnRearWind = AddBtn(6, 852, 25, 170, 69, R.drawable.can_jeep_ac_rear_up, R.drawable.can_jeep_ac_rear_dn);
        this.mBtnLtTempDec = AddBtn(8, 45, KeyDef.RKEY_res1, R.drawable.can_jeep_ac_leng_up, R.drawable.can_jeep_ac_leng_dn);
        this.mBtnLtTempInc = AddBtn(7, 45, 124, R.drawable.can_jeep_ac_re_up, R.drawable.can_jeep_ac_re_dn);
        this.mTvLtTemp = AddTemp(44, Can.CAN_FORD_SYNC3, 93, 51);
        this.mBtnRtTempDec = AddBtn(14, 890, KeyDef.RKEY_res1, R.drawable.can_jeep_ac_leng_up, R.drawable.can_jeep_ac_leng_dn);
        this.mBtnRtTempInc = AddBtn(13, 890, 124, R.drawable.can_jeep_ac_re_up, R.drawable.can_jeep_ac_re_dn);
        this.mTvRtTemp = AddTemp(889, Can.CAN_FORD_SYNC3, 95, 51);
        this.mBtnWdPx = AddBtn(9, KeyDef.RKEY_ANGLEDN, 164, R.drawable.can_jeep_ac_01_up, R.drawable.can_jeep_ac_01_dn);
        this.mBtnWdDn = AddBtn(11, 461, 164, R.drawable.can_jeep_ac_03_up, R.drawable.can_jeep_ac_03_dn);
        this.mBtnWdUp = AddBtn(21, 604, 164, R.drawable.can_jeep_ac_10_up, R.drawable.can_jeep_ac_10_dn);
        this.mBtnWindDec = AddBtn(18, 134, 407, R.drawable.can_jeep_ac_xfans_up, R.drawable.can_jeep_ac_xfans_dn);
        this.mBtnWindInc = AddBtn(19, 763, 407, R.drawable.can_jeep_ac_dfans_up, R.drawable.can_jeep_ac_dfans_dn);
        this.mWindProg = new MyProgressBar(getActivity(), R.drawable.can_jeep_ac_pro_up, R.drawable.can_jeep_ac_pro_dn);
        this.mWindProg.SetMinMax(0, 7);
        getRelativeManager().AddViewWrapContent(this.mWindProg, 287, 448);
        this.mIvWindAuto = addImage(457, 450, R.drawable.can_yl_wind_auto);
        this.mBtnSync = AddBtn(20, 893, 407, R.drawable.can_jeep_ac_sync_up, R.drawable.can_jeep_ac_sync_dn);
        this.mTvWindVal = AddTemp(717, 441, 32, 40);
        this.mBtnMode = AddBtn(22, 0, 25, R.drawable.can_jeep_ac_mode_up, R.drawable.can_jeep_ac_mode_dn);
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
        this.mBtnEco.SetSel(this.mACInfo.fgEco);
        this.mBtnAc.SetSel(this.mACInfo.fgAC);
        this.mBtnAuto.SetSel(this.mACInfo.nAutoLight);
        if (this.mACInfo.fgAQS != 0) {
            this.mBtnLoop.setDrawable(R.drawable.can_jeep_ac_a_up, R.drawable.can_jeep_ac_a_dn);
        } else if (this.mACInfo.fgInnerLoop != 0) {
            this.mBtnLoop.setDrawable(R.drawable.can_jeep_ac_nxh_up, R.drawable.can_jeep_ac_nxh_dn);
        } else {
            this.mBtnLoop.setDrawable(R.drawable.can_jeep_ac_wxh_up, R.drawable.can_jeep_ac_wxh_dn);
        }
        this.mBtnForeWind.SetSel(this.mACInfo.fgDFBL);
        this.mBtnRearWind.SetSel(this.mACInfo.fgRearLight);
        this.mBtnWdPx.setSelected(i2b(this.mACInfo.fgParallelWind));
        this.mBtnWdDn.setSelected(i2b(this.mACInfo.fgDownWind));
        this.mBtnWdUp.setSelected(i2b(this.mACInfo.fgForeWindMode));
        this.mBtnSync.SetSel(this.mACInfo.fgDual);
        if (15 == this.mACInfo.nWindValue) {
            this.mWindProg.SetCurPos(0);
            this.mTvWindVal.setText(TXZResourceManager.STYLE_DEFAULT);
            this.mIvWindAuto.Show(true);
            return;
        }
        this.mWindProg.SetCurPos(this.mACInfo.nWindValue);
        this.mTvWindVal.setText(new StringBuilder(String.valueOf(this.mACInfo.nWindValue)).toString());
        this.mIvWindAuto.Show(false);
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
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn, int resid) {
        ParamButton btn = getRelativeManager().AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnTouchListener(this);
        btn.setDrawable(up, dn);
        btn.setText(resid);
        btn.setTextSize(20.0f);
        btn.setTextColor(-1);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn, String str) {
        ParamButton btn = getRelativeManager().AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnTouchListener(this);
        btn.setDrawable(up, dn);
        btn.setText(str);
        btn.setTextSize(20.0f);
        btn.setTextColor(-1);
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
