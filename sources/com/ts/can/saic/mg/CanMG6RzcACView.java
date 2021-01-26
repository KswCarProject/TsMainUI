package com.ts.can.saic.mg;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseACView;
import com.ts.can.CanCameraUI;
import com.ts.canview.MyProgressBar;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanMG6RzcACView extends CanBaseACView {
    public static final int ITEM_AC = 2;
    public static final int ITEM_AQS = 23;
    public static final int ITEM_AUTO = 4;
    public static final int ITEM_FORE_WIND = 5;
    public static final int ITEM_HEAT = 1;
    public static final int ITEM_ION = 24;
    public static final int ITEM_LOOP = 3;
    public static final int ITEM_LTEMP_DEC = 8;
    public static final int ITEM_LTEMP_INC = 7;
    public static final int ITEM_LTSEAT_HOT = 15;
    public static final int ITEM_LTSEAT_WIND = 26;
    public static final int ITEM_MODE = 22;
    public static final int ITEM_OFF = 17;
    public static final int ITEM_REAR_WIND = 6;
    public static final int ITEM_RTEMP_DEC = 14;
    public static final int ITEM_RTEMP_INC = 13;
    public static final int ITEM_RTSEAT_HOT = 16;
    public static final int ITEM_SYNC = 20;
    public static final int ITEM_TF = 25;
    public static final int ITEM_WD_DN = 11;
    public static final int ITEM_WD_PX = 9;
    public static final int ITEM_WD_PX_DN = 10;
    public static final int ITEM_WD_UP = 21;
    public static final int ITEM_WD_UP_DN = 12;
    public static final int ITEM_WIND_DEC = 18;
    public static final int ITEM_WIND_INC = 19;
    protected static DisplayMetrics mDisplayMetrics = new DisplayMetrics();
    private CanDataInfo.CAN_ACInfo mACInfo;
    private ParamButton mBtnAc;
    private ParamButton mBtnAuto;
    private ParamButton mBtnForeWind;
    private ParamButton mBtnHeat;
    private ParamButton mBtnLoop;
    private ParamButton mBtnLtHot;
    private ParamButton mBtnLtTempDec;
    private ParamButton mBtnLtTempInc;
    private ParamButton mBtnLtWind;
    private ParamButton mBtnMode;
    private ParamButton mBtnOff;
    private ParamButton mBtnRearWind;
    private ParamButton mBtnRtHot;
    private ParamButton mBtnRtTempDec;
    private ParamButton mBtnRtTempInc;
    private ParamButton mBtnSync;
    private ParamButton mBtnTf;
    private ParamButton mBtnWdDn;
    private ParamButton mBtnWdPx;
    private ParamButton mBtnWdPxDn;
    private ParamButton mBtnWdUp;
    private ParamButton mBtnWdUpDn;
    private ParamButton mBtnWindDec;
    private ParamButton mBtnWindInc;
    private CustomImgView[] mIvIonLev;
    private CustomImgView mIvModeAuto;
    private CustomImgView mIvWindAuto;
    private CustomTextView mTvLtTemp;
    private CustomTextView mTvRtTemp;
    private CustomTextView mTvWindVal;
    private MyProgressBar mWindProg;

    public CanMG6RzcACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (action != 0) {
            if (1 == action) {
                switch (Id) {
                    case 2:
                        CanJni.MGGSACSet(8, 0);
                        break;
                    case 3:
                        CanJni.MGGSACSet(3, 0);
                        break;
                    case 4:
                        CanJni.MGGSACSet(9, 0);
                        break;
                    case 5:
                        CanJni.MGGSACSet(6, 0);
                        break;
                    case 6:
                        CanJni.MGGSACSet(7, 0);
                        break;
                    case 7:
                        CanJni.MGGSACSet(1, 0);
                        break;
                    case 8:
                        CanJni.MGGSACSet(1, 0);
                        break;
                    case 9:
                        CanJni.MGGSACSet(16, 0);
                        break;
                    case 10:
                        CanJni.MGGSACSet(16, 0);
                        break;
                    case 11:
                        CanJni.MGGSACSet(16, 0);
                        break;
                    case 12:
                        CanJni.MGGSACSet(16, 0);
                        break;
                    case 13:
                        CanJni.MGGSACSet(21, 0);
                        break;
                    case 14:
                        CanJni.MGGSACSet(21, 0);
                        break;
                    case 15:
                        CanJni.MGGSACSet(17, 0);
                        break;
                    case 16:
                        CanJni.MGGSACSet(18, 0);
                        break;
                    case 17:
                        CanJni.MGGSACSet(5, 0);
                        break;
                    case 18:
                        CanJni.MGGSACSet(4, 0);
                        break;
                    case 19:
                        CanJni.MGGSACSet(4, 0);
                        break;
                    case 22:
                        CanJni.MGGSACSet(2, 0);
                        break;
                }
            }
        } else {
            switch (Id) {
                case 2:
                    CanJni.MGGSACSet(8, 1);
                    break;
                case 3:
                    CanJni.MGGSACSet(3, 1);
                    break;
                case 4:
                    CanJni.MGGSACSet(9, 1);
                    break;
                case 5:
                    CanJni.MGGSACSet(6, 1);
                    break;
                case 6:
                    CanJni.MGGSACSet(7, 1);
                    break;
                case 7:
                    CanJni.MGGSACSet(1, 1);
                    break;
                case 8:
                    CanJni.MGGSACSet(1, 2);
                    break;
                case 9:
                    CanJni.MGGSACSet(16, 1);
                    break;
                case 10:
                    CanJni.MGGSACSet(16, 2);
                    break;
                case 11:
                    CanJni.MGGSACSet(16, 3);
                    break;
                case 12:
                    CanJni.MGGSACSet(16, 4);
                    break;
                case 13:
                    CanJni.MGGSACSet(21, 1);
                    break;
                case 14:
                    CanJni.MGGSACSet(21, 2);
                    break;
                case 15:
                    CanJni.MGGSACSet(17, 1);
                    break;
                case 16:
                    CanJni.MGGSACSet(18, 1);
                    break;
                case 17:
                    CanJni.MGGSACSet(5, 1);
                    break;
                case 18:
                    CanJni.MGGSACSet(4, 2);
                    break;
                case 19:
                    CanJni.MGGSACSet(4, 1);
                    break;
                case 22:
                    CanJni.MGGSACSet(2, 1);
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
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        Log.d("nyw", String.format("%d,%d", new Object[]{Integer.valueOf(mDisplayMetrics.widthPixels), Integer.valueOf(mDisplayMetrics.heightPixels)}));
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) getRelativeManager().GetLayout().getLayoutParams();
        lp.width = 1024;
        lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
        lp.gravity = 17;
        getRelativeManager().GetLayout().setLayoutParams(lp);
        BaseUI();
        getRelativeManager().GetLayout().setScaleX((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f);
        getRelativeManager().GetLayout().setScaleY((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f);
        Log.d("nyw", String.format("%.2f,%.2f", new Object[]{Float.valueOf((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f), Float.valueOf((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f)}));
    }

    private void BaseUI() {
        addImage(44, Can.CAN_FORD_SYNC3, R.drawable.can_jeep_ac_tmp);
        addImage(889, Can.CAN_FORD_SYNC3, R.drawable.can_jeep_ac_tmp);
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
        this.mBtnWdPx = AddBtn(9, 175, 164, R.drawable.can_jeep_ac_01_up, R.drawable.can_jeep_ac_01_dn);
        this.mBtnWdPxDn = AddBtn(10, KeyDef.RKEY_ANGLEDN, 164, R.drawable.can_jeep_ac_02_up, R.drawable.can_jeep_ac_02_dn);
        this.mBtnWdDn = AddBtn(11, 461, 164, R.drawable.can_jeep_ac_03_up, R.drawable.can_jeep_ac_03_dn);
        this.mBtnWdUpDn = AddBtn(12, 604, 164, R.drawable.can_jeep_ac_04_up, R.drawable.can_jeep_ac_04_dn);
        this.mBtnWdUp = AddBtn(21, 747, 164, R.drawable.can_jeep_ac_10_up, R.drawable.can_jeep_ac_10_dn);
        this.mBtnLtWind = AddBtn(26, 536, 294, R.drawable.can_jeep_ac_lchair03_up, R.drawable.can_jeep_ac_lchair03_dn);
        this.mBtnLtWind.setPadding(0, 0, 162, 0);
        this.mBtnLtWind.setTextSize(0, 30.0f);
        this.mBtnLtWind.setGravity(21);
        this.mBtnLtWind.setTextColor(-1);
        this.mBtnLtWind.Show(false);
        this.mBtnLtHot = AddBtn(15, Can.CAN_SITECHDEV_CW, 294, R.drawable.can_jeep_ac_lchair_up, R.drawable.can_jeep_ac_lchair_dn);
        this.mBtnLtHot.setPadding(0, 0, 162, 0);
        this.mBtnLtHot.setTextSize(0, 30.0f);
        this.mBtnLtHot.setGravity(21);
        this.mBtnLtHot.setTextColor(-1);
        this.mBtnRtHot = AddBtn(16, 536, 294, R.drawable.can_jeep_ac_rchair_up, R.drawable.can_jeep_ac_rchair_dn);
        this.mBtnRtHot.setPadding(180, 0, 0, 0);
        this.mBtnRtHot.setTextSize(0, 30.0f);
        this.mBtnRtHot.setGravity(19);
        this.mBtnRtHot.setTextColor(-1);
        this.mBtnOff = AddBtn(17, 0, 407, R.drawable.can_jeep_ac_off_up, R.drawable.can_jeep_ac_off_dn);
        this.mBtnWindDec = AddBtn(18, 134, 407, R.drawable.can_jeep_ac_xfans_up, R.drawable.can_jeep_ac_xfans_dn);
        this.mBtnWindInc = AddBtn(19, 763, 407, R.drawable.can_jeep_ac_dfans_up, R.drawable.can_jeep_ac_dfans_dn);
        this.mWindProg = new MyProgressBar(getActivity(), R.drawable.can_jeep_ac_pro_up, R.drawable.can_jeep_ac_pro_dn);
        this.mWindProg.SetMinMax(0, 8);
        getRelativeManager().AddViewWrapContent(this.mWindProg, 287, 448);
        this.mIvWindAuto = addImage(457, 450, R.drawable.can_yl_wind_auto);
        this.mIvModeAuto = addImage(457, 265, R.drawable.can_yl_wind_auto);
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
        Log.d("lq", "mACInfo.nWindValue: " + this.mACInfo.nWindValue);
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
        if (this.mACInfo.fgDownWind > 0 && this.mACInfo.fgUpWind > 0) {
            this.mBtnWdUpDn.setSelected(true);
            this.mBtnWdPxDn.setSelected(false);
            this.mBtnWdPx.setSelected(false);
            this.mBtnWdDn.setSelected(false);
            this.mBtnWdUp.setSelected(false);
        } else if (this.mACInfo.fgParallelWind > 0 && this.mACInfo.fgDownWind > 0) {
            this.mBtnWdPxDn.setSelected(true);
            this.mBtnWdUpDn.setSelected(false);
            this.mBtnWdPx.setSelected(false);
            this.mBtnWdDn.setSelected(false);
            this.mBtnWdUp.setSelected(false);
        } else if (this.mACInfo.fgParallelWind > 0) {
            this.mBtnWdPxDn.setSelected(false);
            this.mBtnWdUpDn.setSelected(false);
            this.mBtnWdPx.setSelected(true);
            this.mBtnWdDn.setSelected(false);
            this.mBtnWdUp.setSelected(false);
        } else if (this.mACInfo.fgDownWind > 0) {
            this.mBtnWdPxDn.setSelected(false);
            this.mBtnWdUpDn.setSelected(false);
            this.mBtnWdPx.setSelected(false);
            this.mBtnWdDn.setSelected(true);
            this.mBtnWdUp.setSelected(false);
        } else if (this.mACInfo.fgUpWind > 0) {
            this.mBtnWdPxDn.setSelected(false);
            this.mBtnWdUpDn.setSelected(false);
            this.mBtnWdPx.setSelected(false);
            this.mBtnWdDn.setSelected(false);
            this.mBtnWdUp.setSelected(true);
        } else {
            this.mBtnWdPxDn.setSelected(false);
            this.mBtnWdUpDn.setSelected(false);
            this.mBtnWdPx.setSelected(false);
            this.mBtnWdDn.setSelected(false);
            this.mBtnWdUp.setSelected(false);
        }
        if (this.mACInfo.nWindAutoLevel > 0) {
            this.mIvModeAuto.Show(true);
        } else {
            this.mIvModeAuto.Show(false);
        }
        this.mBtnLtWind.SetSel(this.mACInfo.nLtChairWind);
        this.mBtnLtHot.SetSel(this.mACInfo.nLtChairHot);
        this.mBtnRtHot.SetSel(this.mACInfo.nRtChairHot);
        this.mBtnMode.SetSel(this.mACInfo.fgACMax);
        this.mBtnOff.SetSel(1 - this.mACInfo.PWR);
        this.mBtnSync.SetSel(this.mACInfo.fgDual);
        if (this.mACInfo.fgAutoWind > 0) {
            this.mWindProg.SetCurPos(0);
            this.mTvWindVal.setText(TXZResourceManager.STYLE_DEFAULT);
            this.mIvWindAuto.Show(true);
            return;
        }
        this.mWindProg.SetCurPos(this.mACInfo.nWindValue);
        this.mTvWindVal.setText(new StringBuilder(String.valueOf(this.mACInfo.nWindValue)).toString());
        this.mIvWindAuto.Show(false);
    }

    private void setLtText(int num, ParamButton v) {
        switch (num) {
            case 0:
                v.setText(R.string.can_off);
                return;
            case 1:
                v.setText(R.string.can_ac_low);
                return;
            case 2:
                v.setText(R.string.can_ac_mid);
                return;
            default:
                v.setText(R.string.can_ac_high);
                return;
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