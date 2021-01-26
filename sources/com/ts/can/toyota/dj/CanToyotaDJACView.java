package com.ts.can.toyota.dj;

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

public class CanToyotaDJACView extends CanBaseACView {
    public static final int ITEM_AC = 2;
    public static final int ITEM_AUTO = 4;
    public static final int ITEM_CLEAN = 21;
    public static final int ITEM_DUAL = 20;
    public static final int ITEM_FORE_WIND = 5;
    public static final int ITEM_ION = 22;
    public static final int ITEM_LOOP = 3;
    public static final int ITEM_LTEMP_DEC = 8;
    public static final int ITEM_LTEMP_INC = 7;
    public static final int ITEM_LTSEAT_HOT = 15;
    public static final int ITEM_MODE = 1;
    public static final int ITEM_OFF = 17;
    public static final int ITEM_QCJR = 24;
    public static final int ITEM_REAR_WIND = 6;
    public static final int ITEM_RTEMP_DEC = 14;
    public static final int ITEM_RTEMP_INC = 13;
    public static final int ITEM_RTSEAT_HOT = 16;
    public static final int ITEM_SWING = 23;
    public static final int ITEM_WD_DN = 11;
    public static final int ITEM_WD_PX = 9;
    public static final int ITEM_WD_PX_DN = 10;
    public static final int ITEM_WD_UP_DN = 12;
    public static final int ITEM_WIND_DEC = 18;
    public static final int ITEM_WIND_INC = 19;
    private CanDataInfo.CAN_ACInfo mACInfo;
    private CanDataInfo.ToyotaDj_AirAssit mAirAssit;
    private ParamButton mBtnAc;
    private ParamButton mBtnAuto;
    private ParamButton mBtnClean;
    private ParamButton mBtnDual;
    private ParamButton mBtnForeWind;
    private ParamButton mBtnIon;
    private ParamButton mBtnLoop;
    private ParamButton mBtnLtTempDec;
    private ParamButton mBtnLtTempInc;
    private ParamButton mBtnMode;
    private ParamButton mBtnOff;
    private ParamButton mBtnQcjr;
    private ParamButton mBtnRearWind;
    private ParamButton mBtnRtTempDec;
    private ParamButton mBtnRtTempInc;
    private ParamButton mBtnSwing;
    private ParamButton mBtnWdDn;
    private ParamButton mBtnWdPx;
    private ParamButton mBtnWdPxDn;
    private ParamButton mBtnWdUpDn;
    private ParamButton mBtnWindDec;
    private ParamButton mBtnWindInc;
    /* access modifiers changed from: private */
    public int mCurrentId;
    /* access modifiers changed from: private */
    public View mCurrentView;
    private CustomImgView mIvWindAuto;
    private Runnable mSendKey = new Runnable() {
        public void run() {
            CanToyotaDJACView.this.SendKey(CanToyotaDJACView.this.mCurrentId, 2);
            CanToyotaDJACView.this.mCurrentView.postDelayed(this, 100);
        }
    };
    private CustomTextView mTvLtTemp;
    private CustomTextView mTvRtTemp;
    private CustomTextView mTvWindVal;
    private MyProgressBar mWindProg;

    public CanToyotaDJACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int id = ((Integer) v.getTag()).intValue();
        this.mCurrentId = id;
        this.mCurrentView = v;
        if (action == 0) {
            SendKey(id, 1);
            v.postDelayed(this.mSendKey, 100);
        } else if (1 == action || 3 == action) {
            v.removeCallbacks(this.mSendKey);
            SendKey(id, 0);
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void SendKey(int id, int keycode) {
        switch (id) {
            case 1:
                ACSet(9, keycode);
                return;
            case 2:
                ACSet(8, keycode);
                return;
            case 3:
                ACSet(3, keycode);
                return;
            case 4:
                ACSet(1, keycode);
                return;
            case 5:
                ACSet(4, keycode);
                return;
            case 6:
                ACSet(5, keycode);
                return;
            case 7:
                ACSet(12, keycode);
                return;
            case 8:
                ACSet(13, keycode);
                return;
            case 9:
                ACSet(34, keycode);
                return;
            case 10:
                ACSet(18, keycode);
                return;
            case 11:
                ACSet(35, keycode);
                return;
            case 12:
                ACSet(19, keycode);
                return;
            case 13:
                ACSet(14, keycode);
                return;
            case 14:
                ACSet(15, keycode);
                return;
            case 17:
                ACSet(10, keycode);
                return;
            case 18:
                ACSet(7, keycode);
                return;
            case 19:
                ACSet(6, keycode);
                return;
            case 20:
                ACSet(2, keycode);
                return;
            case 21:
                ACSet(38, keycode);
                return;
            case 22:
                ACSet(39, keycode);
                return;
            case 23:
                ACSet(40, keycode);
                return;
            case 24:
                ACSet(41, keycode);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void ACSet(int para, int keycode) {
        CanJni.ToyotaDjAirKey(para, keycode);
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
    }

    /* access modifiers changed from: protected */
    public void InitViews() {
        setBackgroundResource(R.drawable.can_jeep_ac_bg);
        initCommonScreen();
        this.mAirAssit = new CanDataInfo.ToyotaDj_AirAssit();
    }

    private void initCommonScreen() {
        addImage(44, Can.CAN_FORD_SYNC3, R.drawable.can_jeep_ac_tmp);
        addImage(889, Can.CAN_FORD_SYNC3, R.drawable.can_jeep_ac_tmp);
        this.mBtnMode = AddBtn(1, 0, 25, R.drawable.can_jeep_ac_mode_up, R.drawable.can_jeep_ac_mode_dn);
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
        this.mBtnClean = AddBtn(21, Can.CAN_SITECHDEV_CW, 259, R.drawable.can_hg_ac_swing_up, R.drawable.can_hg_ac_swing_dn);
        this.mBtnSwing = AddBtn(23, 388, 259, R.drawable.can_hg_ac_clean_up, R.drawable.can_hg_ac_clean_dn);
        this.mBtnQcjr = AddBtn(24, 536, 259, R.drawable.can_hg_ac_qccb_up, R.drawable.can_hg_ac_qccb_dn);
        this.mBtnIon = AddBtn(22, 683, 259, R.drawable.can_hg_ac_hfqc_up, R.drawable.can_hg_ac_hfqc_dn);
        this.mBtnWdPx = AddBtn(9, Can.CAN_SITECHDEV_CW, 144, R.drawable.can_jeep_ac_01_up, R.drawable.can_jeep_ac_01_dn);
        this.mBtnWdPxDn = AddBtn(10, 388, 144, R.drawable.can_jeep_ac_02_up, R.drawable.can_jeep_ac_02_dn);
        this.mBtnWdDn = AddBtn(11, 536, 144, R.drawable.can_jeep_ac_03_up, R.drawable.can_jeep_ac_03_dn);
        this.mBtnWdUpDn = AddBtn(12, 683, 144, R.drawable.can_jeep_ac_04_up, R.drawable.can_jeep_ac_04_dn);
        this.mBtnOff = AddBtn(17, 0, 407, R.drawable.can_jeep_ac_off_up, R.drawable.can_jeep_ac_off_dn);
        this.mBtnWindDec = AddBtn(18, 134, 407, R.drawable.can_jeep_ac_xfans_up, R.drawable.can_jeep_ac_xfans_dn);
        this.mBtnWindInc = AddBtn(19, 763, 407, R.drawable.can_jeep_ac_dfans_up, R.drawable.can_jeep_ac_dfans_dn);
        this.mWindProg = new MyProgressBar(getActivity(), R.drawable.can_jeep_ac_pro_up, R.drawable.can_jeep_ac_pro_dn);
        this.mWindProg.SetMinMax(0, 7);
        getRelativeManager().AddViewWrapContent(this.mWindProg, 287, 448);
        this.mIvWindAuto = addImage(457, 450, R.drawable.can_yl_wind_auto);
        this.mBtnDual = AddBtn(20, 893, 407, R.drawable.can_hg_ac_dual_up, R.drawable.can_hg_ac_dual_dn);
        this.mTvWindVal = AddTemp(717, 441, 32, 40);
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
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
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
        int windDirect = (this.mACInfo.fgDFBL * 8) + (this.mACInfo.fgParallelWind * 4) + (this.mACInfo.fgDownWind * 2);
        ParamButton paramButton = this.mBtnWdPx;
        if (4 == windDirect) {
            z = true;
        } else {
            z = false;
        }
        paramButton.setSelected(z);
        ParamButton paramButton2 = this.mBtnWdPxDn;
        if (6 == windDirect) {
            z2 = true;
        } else {
            z2 = false;
        }
        paramButton2.setSelected(z2);
        ParamButton paramButton3 = this.mBtnWdDn;
        if (2 == windDirect) {
            z3 = true;
        } else {
            z3 = false;
        }
        paramButton3.setSelected(z3);
        ParamButton paramButton4 = this.mBtnWdUpDn;
        if (10 == windDirect) {
            z4 = true;
        } else {
            z4 = false;
        }
        paramButton4.setSelected(z4);
        this.mBtnOff.SetSel(1 - this.mACInfo.PWR);
        this.mBtnDual.SetSel(this.mACInfo.fgDual);
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

    public void ResetData(boolean check) {
        CanJni.ToyotaDjGetAirAssit(this.mAirAssit);
        if (!i2b(this.mAirAssit.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAirAssit.Update)) {
            this.mAirAssit.Update = 0;
            this.mBtnClean.SetSel(this.mAirAssit.Kqjh);
            this.mBtnSwing.SetSel(this.mAirAssit.Pxsf);
            this.mBtnQcjr.SetSel(this.mAirAssit.Qbljr);
            this.mBtnIon.SetSel(this.mAirAssit.Flz);
        }
    }

    public void QueryData() {
        CanJni.ToyotaDjQuery(40, 0);
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
