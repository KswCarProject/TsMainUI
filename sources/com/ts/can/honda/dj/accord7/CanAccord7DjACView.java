package com.ts.can.honda.dj.accord7;

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

public class CanAccord7DjACView extends CanBaseACView {
    public static final int ITEM_AC = 2;
    public static final int ITEM_AC_MAX = 5;
    public static final int ITEM_AUTO = 4;
    public static final int ITEM_DUAL = 20;
    public static final int ITEM_HFQC = 23;
    public static final int ITEM_LOOP = 3;
    public static final int ITEM_LTEMP_DEC = 8;
    public static final int ITEM_LTEMP_INC = 7;
    public static final int ITEM_LTSEAT_HOT = 15;
    public static final int ITEM_MODE = 1;
    public static final int ITEM_MODE_FORE = 21;
    public static final int ITEM_OFF = 17;
    public static final int ITEM_QCCB = 24;
    public static final int ITEM_REAR_WIND = 6;
    public static final int ITEM_RTEMP_DEC = 14;
    public static final int ITEM_RTEMP_INC = 13;
    public static final int ITEM_RTSEAT_HOT = 16;
    public static final int ITEM_SWING = 22;
    public static final int ITEM_WD_DN = 11;
    public static final int ITEM_WD_PX = 9;
    public static final int ITEM_WD_PX_DN = 10;
    public static final int ITEM_WD_UP_DN = 12;
    public static final int ITEM_WIND_DEC = 18;
    public static final int ITEM_WIND_INC = 19;
    private CanDataInfo.CAN_ACInfo mACInfo;
    private ParamButton mBtnAc;
    private ParamButton mBtnAcMax;
    private ParamButton mBtnAuto;
    private ParamButton mBtnDual;
    private ParamButton mBtnHfqc;
    private ParamButton mBtnLoop;
    private ParamButton mBtnLtTempDec;
    private ParamButton mBtnLtTempInc;
    private ParamButton mBtnMode;
    private ParamButton mBtnModeFore;
    private ParamButton mBtnOff;
    private ParamButton mBtnQccb;
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
    private CustomImgView mIvModedAuto;
    private CustomImgView mIvWindAuto;
    private CustomTextView mTvLtTemp;
    private CustomTextView mTvRtTemp;
    private CustomTextView mTvWindVal;
    private MyProgressBar mWindProg;

    public CanAccord7DjACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (action != 0) {
            if (1 == action) {
                switch (Id) {
                    case 1:
                        CanJni.HondaAccord7DjAirCmd(11, 0);
                        break;
                    case 2:
                        CanJni.HondaAccord7DjAirCmd(8, 0);
                        break;
                    case 3:
                        CanJni.HondaAccord7DjAirCmd(3, 0);
                        break;
                    case 4:
                        CanJni.HondaAccord7DjAirCmd(1, 0);
                        break;
                    case 5:
                        CanJni.HondaAccord7DjAirCmd(16, 0);
                        break;
                    case 6:
                        CanJni.HondaAccord7DjAirCmd(5, 0);
                        break;
                    case 7:
                        CanJni.HondaAccord7DjAirCmd(12, 0);
                        break;
                    case 8:
                        CanJni.HondaAccord7DjAirCmd(13, 0);
                        break;
                    case 13:
                        CanJni.HondaAccord7DjAirCmd(14, 0);
                        break;
                    case 14:
                        CanJni.HondaAccord7DjAirCmd(15, 0);
                        break;
                    case 17:
                        CanJni.HondaAccord7DjAirCmd(9, 0);
                        break;
                    case 18:
                        CanJni.HondaAccord7DjAirCmd(7, 0);
                        break;
                    case 19:
                        CanJni.HondaAccord7DjAirCmd(6, 0);
                        break;
                    case 20:
                        CanJni.HondaAccord7DjAirCmd(2, 0);
                        break;
                    case 21:
                        CanJni.HondaAccord7DjAirCmd(35, 0);
                        break;
                }
            }
        } else {
            switch (Id) {
                case 1:
                    CanJni.HondaAccord7DjAirCmd(11, 1);
                    break;
                case 2:
                    CanJni.HondaAccord7DjAirCmd(8, 1);
                    break;
                case 3:
                    CanJni.HondaAccord7DjAirCmd(3, 1);
                    break;
                case 4:
                    CanJni.HondaAccord7DjAirCmd(1, 1);
                    break;
                case 5:
                    CanJni.HondaAccord7DjAirCmd(16, 1);
                    break;
                case 6:
                    CanJni.HondaAccord7DjAirCmd(5, 1);
                    break;
                case 7:
                    CanJni.HondaAccord7DjAirCmd(12, 1);
                    break;
                case 8:
                    CanJni.HondaAccord7DjAirCmd(13, 1);
                    break;
                case 13:
                    CanJni.HondaAccord7DjAirCmd(14, 1);
                    break;
                case 14:
                    CanJni.HondaAccord7DjAirCmd(15, 1);
                    break;
                case 17:
                    CanJni.HondaAccord7DjAirCmd(9, 1);
                    break;
                case 18:
                    CanJni.HondaAccord7DjAirCmd(7, 1);
                    break;
                case 19:
                    CanJni.HondaAccord7DjAirCmd(6, 1);
                    break;
                case 20:
                    CanJni.HondaAccord7DjAirCmd(2, 1);
                    break;
                case 21:
                    CanJni.HondaAccord7DjAirCmd(35, 1);
                    break;
            }
        }
        return false;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
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
        this.mBtnMode = AddBtn(1, 0, 25, R.drawable.can_jeep_ac_mode_up, R.drawable.can_jeep_ac_mode_dn);
        this.mBtnAc = AddBtn(2, 174, 25, R.drawable.can_jeep_ac_ac_up, R.drawable.can_jeep_ac_ac_dn);
        this.mBtnLoop = AddBtn(3, 344, 25, R.drawable.can_jeep_ac_nxh_up, R.drawable.can_jeep_ac_nxh_dn);
        this.mBtnAuto = AddBtn(4, 513, 25, R.drawable.can_jeep_ac_auto_up, R.drawable.can_jeep_ac_auto_dn);
        this.mBtnAcMax = AddBtn(5, 682, 25, R.drawable.can_jeep_ac_max_up, R.drawable.can_jeep_ac_max_dn);
        this.mBtnRearWind = AddBtn(6, 852, 25, R.drawable.can_jeep_ac_rear_up, R.drawable.can_jeep_ac_rear_dn);
        this.mBtnLtTempDec = AddBtn(8, 45, KeyDef.RKEY_res1, R.drawable.can_jeep_ac_leng_up, R.drawable.can_jeep_ac_leng_dn);
        this.mBtnLtTempInc = AddBtn(7, 45, 124, R.drawable.can_jeep_ac_re_up, R.drawable.can_jeep_ac_re_dn);
        this.mTvLtTemp = AddTemp(44, Can.CAN_FORD_SYNC3, 93, 51);
        this.mBtnRtTempDec = AddBtn(14, 890, KeyDef.RKEY_res1, R.drawable.can_jeep_ac_leng_up, R.drawable.can_jeep_ac_leng_dn);
        this.mBtnRtTempInc = AddBtn(13, 890, 124, R.drawable.can_jeep_ac_re_up, R.drawable.can_jeep_ac_re_dn);
        this.mTvRtTemp = AddTemp(889, Can.CAN_FORD_SYNC3, 95, 51);
        this.mBtnModeFore = AddBtn(21, Can.CAN_SITECHDEV_CW, 259, R.drawable.can_jeep_ac_10_up, R.drawable.can_jeep_ac_10_dn);
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
        this.mIvModedAuto = addImage(467, 100, R.drawable.can_yl_wind_auto);
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
        boolean z5;
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
        this.mBtnAcMax.SetSel(this.mACInfo.fgACMax);
        this.mBtnRearWind.SetSel(this.mACInfo.fgRearLight);
        int windDirect = (this.mACInfo.fgUpWind * 1) + (this.mACInfo.fgParallelWind * 2) + (this.mACInfo.fgDownWind * 4);
        ParamButton paramButton = this.mBtnWdPx;
        if (2 == windDirect) {
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
        if (4 == windDirect) {
            z3 = true;
        } else {
            z3 = false;
        }
        paramButton3.setSelected(z3);
        ParamButton paramButton4 = this.mBtnWdUpDn;
        if (5 == windDirect) {
            z4 = true;
        } else {
            z4 = false;
        }
        paramButton4.setSelected(z4);
        ParamButton paramButton5 = this.mBtnModeFore;
        if (this.mACInfo.fgDFBL > 0) {
            z5 = true;
        } else {
            z5 = false;
        }
        paramButton5.setSelected(z5);
        if (this.mACInfo.fgAutoMode > 0) {
            this.mIvModedAuto.Show(true);
        } else {
            this.mIvModedAuto.Show(false);
        }
        this.mBtnOff.SetSel(this.mACInfo.nWindValue > 0 ? 0 : 1);
        this.mBtnDual.SetSel(this.mACInfo.fgDual);
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

    public void QueryData() {
        super.QueryData();
        CanJni.CrownhWcQuery(5, 1, 130);
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnTouchListener(this);
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnTouchListener(this);
        btn.setOnClickListener(this);
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
