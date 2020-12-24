package com.ts.can.df.ax7;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseACView;
import com.ts.canview.MyProgressBar;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanDFAX7CarACView extends CanBaseACView {
    public static final int ITEM_AC = 2;
    public static final int ITEM_AUTO = 4;
    public static final int ITEM_FORE_WIND = 5;
    public static final int ITEM_LOOP = 3;
    public static final int ITEM_LTEMP_DEC = 8;
    public static final int ITEM_LTEMP_INC = 7;
    public static final int ITEM_MODE = 22;
    public static final int ITEM_OFF = 17;
    public static final int ITEM_REAR_WIND = 6;
    public static final int ITEM_RTEMP_DEC = 15;
    public static final int ITEM_RTEMP_INC = 14;
    public static final int ITEM_SYNC = 20;
    public static final int ITEM_WD_DN = 11;
    public static final int ITEM_WD_PX = 9;
    public static final int ITEM_WD_PX_DN = 10;
    public static final int ITEM_WD_UP = 13;
    public static final int ITEM_WD_UP_DN = 12;
    public static final int ITEM_WIND_DEC = 18;
    public static final int ITEM_WIND_INC = 19;
    private CanDataInfo.CAN_ACInfo mACInfo;
    private ParamButton mBtnAc;
    private ParamButton mBtnAuto;
    private ParamButton mBtnForeWind;
    private ParamButton mBtnLoop;
    private ParamButton mBtnOff;
    private ParamButton mBtnRearWind;
    private ParamButton mBtnSync;
    private ParamButton mBtnWdDn;
    private ParamButton mBtnWdPx;
    private ParamButton mBtnWdPxDn;
    private ParamButton mBtnWdUpDn;
    private CustomTextView mTvLtTemp;
    private CustomTextView mTvPM25;
    private CustomTextView mTvRtTemp;
    private CustomTextView mTvWindVal;
    private MyProgressBar mWindProg;

    public CanDFAX7CarACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (action == 0) {
            switch (Id) {
            }
            return false;
        } else if (1 != action) {
            return false;
        } else {
            switch (Id) {
            }
            return false;
        }
    }

    public void onClick(View v) {
        int cmd;
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.DfFengShenRzcAirCmd(2, 0, 0, 0, 0, 0);
                return;
            case 3:
                if (this.mACInfo.fgInnerLoop == 1) {
                    cmd = 8;
                } else {
                    cmd = 4;
                }
                CanJni.DfFengShenRzcAirCmd(cmd, 0, 0, 0, 0, 0);
                return;
            case 4:
                CanJni.DfFengShenRzcAirCmd(32, 0, 0, 0, 0, 0);
                return;
            case 5:
                CanJni.DfFengShenRzcAirCmd(16, 0, 0, 0, 0, 0);
                return;
            case 6:
                CanJni.DfFengShenRzcAirCmd(0, 4, 0, 0, 0, 0);
                return;
            case 7:
                CanJni.DfFengShenRzcAirCmd(0, 0, 0, 2, 0, 0);
                return;
            case 8:
                CanJni.DfFengShenRzcAirCmd(0, 0, 0, 1, 0, 0);
                return;
            case 9:
                CanJni.DfFengShenRzcAirCmd(0, 0, 2, 0, 0, 0);
                return;
            case 10:
                CanJni.DfFengShenRzcAirCmd(0, 0, 4, 0, 0, 0);
                return;
            case 11:
                CanJni.DfFengShenRzcAirCmd(0, 0, 8, 0, 0, 0);
                return;
            case 12:
                CanJni.DfFengShenRzcAirCmd(0, 0, 16, 0, 0, 0);
                return;
            case 14:
                CanJni.DfFengShenRzcAirCmd(0, 0, 0, 0, 2, 0);
                return;
            case 15:
                CanJni.DfFengShenRzcAirCmd(0, 0, 0, 0, 1, 0);
                return;
            case 17:
                CanJni.DfFengShenRzcAirCmd(128, 0, 0, 0, 0, 0);
                return;
            case 18:
                CanJni.DfFengShenRzcAirCmd(0, 1, 0, 0, 0, 0);
                return;
            case 19:
                CanJni.DfFengShenRzcAirCmd(0, 2, 0, 0, 0, 0);
                return;
            default:
                return;
        }
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
        AddBtn(22, 0, 25, R.drawable.can_jeep_ac_mode_up, R.drawable.can_jeep_ac_mode_dn);
        this.mBtnAc = AddBtn(2, 174, 25, R.drawable.can_jeep_ac_ac_up, R.drawable.can_jeep_ac_ac_dn);
        this.mBtnLoop = AddBtn(3, 344, 25, R.drawable.can_jeep_ac_nxh_up, R.drawable.can_jeep_ac_nxh_dn);
        this.mBtnAuto = AddBtn(4, 513, 25, R.drawable.can_jeep_ac_auto_up, R.drawable.can_jeep_ac_auto_dn);
        this.mBtnForeWind = AddBtn(5, 682, 25, R.drawable.can_jeep_ac_window_up, R.drawable.can_jeep_ac_window_dn);
        this.mBtnRearWind = AddBtn(6, 852, 25, 170, 69, R.drawable.can_jeep_ac_rear_up, R.drawable.can_jeep_ac_rear_dn);
        AddBtn(8, 45, KeyDef.RKEY_res1, R.drawable.can_jeep_ac_leng_up, R.drawable.can_jeep_ac_leng_dn);
        AddBtn(7, 45, 124, R.drawable.can_jeep_ac_re_up, R.drawable.can_jeep_ac_re_dn);
        this.mTvLtTemp = AddTemp(44, Can.CAN_FORD_SYNC3, 93, 51);
        AddBtn(15, 890, KeyDef.RKEY_res1, R.drawable.can_jeep_ac_leng_up, R.drawable.can_jeep_ac_leng_dn);
        AddBtn(14, 890, 124, R.drawable.can_jeep_ac_re_up, R.drawable.can_jeep_ac_re_dn);
        this.mTvRtTemp = AddTemp(889, Can.CAN_FORD_SYNC3, 95, 51);
        this.mBtnWdPx = AddBtn(9, Can.CAN_NISSAN_XFY, 164, R.drawable.can_jeep_ac_01_up, R.drawable.can_jeep_ac_01_dn);
        this.mBtnWdPxDn = AddBtn(10, 393, 164, R.drawable.can_jeep_ac_02_up, R.drawable.can_jeep_ac_02_dn);
        this.mBtnWdDn = AddBtn(11, 536, 164, R.drawable.can_jeep_ac_03_up, R.drawable.can_jeep_ac_03_dn);
        this.mBtnWdUpDn = AddBtn(12, 679, 164, R.drawable.can_jeep_ac_04_up, R.drawable.can_jeep_ac_04_dn);
        this.mBtnOff = AddBtn(17, 0, 407, R.drawable.can_jeep_ac_off_up, R.drawable.can_jeep_ac_off_dn);
        AddBtn(18, 134, 407, R.drawable.can_jeep_ac_xfans_up, R.drawable.can_jeep_ac_xfans_dn);
        AddBtn(19, 763, 407, R.drawable.can_jeep_ac_dfans_up, R.drawable.can_jeep_ac_dfans_dn);
        this.mTvWindVal = AddTemp(717, 441, 32, 40);
        this.mWindProg = new MyProgressBar(getActivity(), R.drawable.can_jeep_ac_pro_up, R.drawable.can_jeep_ac_pro_dn);
        this.mWindProg.SetMinMax(1, 8);
        getRelativeManager().AddViewWrapContent(this.mWindProg, 287, 448);
        this.mBtnSync = AddBtn(20, 893, 407, R.drawable.can_jeep_ac_sync_up, R.drawable.can_jeep_ac_sync_dn);
        this.mTvPM25 = AddTemp(430, KeyDef.RKEY_MEDIA_SLOW, 180, 80);
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        this.mACInfo = Can.mACInfo;
        this.mTvLtTemp.setText(this.mACInfo.szLtTemp);
        this.mTvRtTemp.setText(this.mACInfo.szRtTemp);
        this.mBtnAc.SetSel(this.mACInfo.fgAC);
        this.mBtnAuto.SetSel(this.mACInfo.nAutoLight);
        if (this.mACInfo.fgAQS != 0) {
            this.mBtnLoop.setDrawable(R.drawable.can_jeep_ac_a_up, R.drawable.can_jeep_ac_a_dn);
        } else if (this.mACInfo.fgInnerLoop != 0) {
            this.mBtnLoop.setDrawable(R.drawable.can_jeep_ac_nxh_up, R.drawable.can_jeep_ac_nxh_dn);
        } else {
            this.mBtnLoop.setDrawable(R.drawable.can_jeep_ac_wxh_up, R.drawable.can_jeep_ac_wxh_dn);
        }
        this.mBtnRearWind.SetSel(this.mACInfo.fgRearLight);
        if (this.mACInfo.fgDownWind > 0 && this.mACInfo.fgDFBL > 0) {
            this.mBtnWdUpDn.setSelected(true);
            this.mBtnWdPxDn.setSelected(false);
            this.mBtnWdPx.setSelected(false);
            this.mBtnWdDn.setSelected(false);
            this.mBtnForeWind.setSelected(false);
        } else if (this.mACInfo.fgParallelWind > 0 && this.mACInfo.fgDownWind > 0) {
            this.mBtnWdPxDn.setSelected(true);
            this.mBtnWdUpDn.setSelected(false);
            this.mBtnWdPx.setSelected(false);
            this.mBtnWdDn.setSelected(false);
            this.mBtnForeWind.setSelected(false);
        } else if (this.mACInfo.fgParallelWind > 0) {
            this.mBtnWdPxDn.setSelected(false);
            this.mBtnWdUpDn.setSelected(false);
            this.mBtnWdPx.setSelected(true);
            this.mBtnWdDn.setSelected(false);
            this.mBtnForeWind.setSelected(false);
        } else if (this.mACInfo.fgDownWind > 0) {
            this.mBtnWdPxDn.setSelected(false);
            this.mBtnWdUpDn.setSelected(false);
            this.mBtnWdPx.setSelected(false);
            this.mBtnWdDn.setSelected(true);
            this.mBtnForeWind.setSelected(false);
        } else if (this.mACInfo.fgDFBL > 0) {
            this.mBtnWdPxDn.setSelected(false);
            this.mBtnWdUpDn.setSelected(false);
            this.mBtnWdPx.setSelected(false);
            this.mBtnWdDn.setSelected(false);
            this.mBtnForeWind.setSelected(true);
        } else {
            this.mBtnWdPxDn.setSelected(false);
            this.mBtnWdUpDn.setSelected(false);
            this.mBtnWdPx.setSelected(false);
            this.mBtnWdDn.setSelected(false);
            this.mBtnForeWind.setSelected(false);
        }
        this.mBtnOff.SetSel(1 - this.mACInfo.PWR);
        this.mBtnSync.SetSel(this.mACInfo.fgDual);
        this.mWindProg.SetCurPos(this.mACInfo.nWindValue);
        this.mTvWindVal.setText(new StringBuilder(String.valueOf(this.mACInfo.nWindValue)).toString());
        this.mTvPM25.setText("PM2.5 : " + this.mACInfo.Pm25);
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn, int resid) {
        ParamButton btn = getRelativeManager().AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
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
        btn.setOnClickListener(this);
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
