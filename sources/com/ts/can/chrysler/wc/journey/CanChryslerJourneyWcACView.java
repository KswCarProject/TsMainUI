package com.ts.can.chrysler.wc.journey;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
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

public class CanChryslerJourneyWcACView extends CanBaseACView {
    public static final int ITEM_AC = 2;
    public static final int ITEM_AUTO = 4;
    public static final int ITEM_FORE_WIND = 5;
    public static final int ITEM_LOOP = 3;
    public static final int ITEM_LTEMP_DEC = 8;
    public static final int ITEM_LTEMP_INC = 7;
    public static final int ITEM_LTSEAT_HOT = 15;
    public static final int ITEM_MAX_AC = 1;
    public static final int ITEM_OFF = 17;
    public static final int ITEM_REAR_AIR = 21;
    public static final int ITEM_REAR_AUTO = 22;
    public static final int ITEM_REAR_LOCK = 23;
    public static final int ITEM_REAR_WIND = 6;
    public static final int ITEM_RTEMP_DEC = 14;
    public static final int ITEM_RTEMP_INC = 13;
    public static final int ITEM_RTSEAT_HOT = 16;
    public static final int ITEM_SYNC = 20;
    public static final int ITEM_WD_DN = 11;
    public static final int ITEM_WD_PX = 9;
    public static final int ITEM_WD_PX_DN = 10;
    public static final int ITEM_WD_UP_DN = 12;
    public static final int ITEM_WIND_DEC = 18;
    public static final int ITEM_WIND_INC = 19;
    private CanDataInfo.CAN_ACInfo mACInfo;
    private ParamButton mBtnAc;
    private ParamButton mBtnAuto;
    private ParamButton mBtnForeWind;
    private ParamButton mBtnLoop;
    private ParamButton mBtnLtHot;
    private ParamButton mBtnLtTempDec;
    private ParamButton mBtnLtTempInc;
    private ParamButton mBtnMaxAc;
    private ParamButton mBtnOff;
    private ParamButton mBtnRearAc;
    private ParamButton mBtnRearAuto;
    private ParamButton mBtnRearLock;
    private ParamButton mBtnRearWind;
    private ParamButton mBtnRtHot;
    private ParamButton mBtnRtTempDec;
    private ParamButton mBtnRtTempInc;
    private ParamButton mBtnSync;
    private ParamButton mBtnWdDn;
    private ParamButton mBtnWdPx;
    private ParamButton mBtnWdPxDn;
    private ParamButton mBtnWdUpDn;
    private ParamButton mBtnWindDec;
    private ParamButton mBtnWindInc;
    private CustomImgView mIvLtTemp;
    private CustomImgView mIvWindAuto;
    private CustomTextView mTvLtTemp;
    private CustomTextView mTvRtTemp;
    private CustomTextView mTvWindVal;
    private MyProgressBar mWindProg;
    private int nAirUI = 0;

    public CanChryslerJourneyWcACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (action != 0) {
            if (1 == action) {
                switch (Id) {
                    case 1:
                        ACUPSet(21);
                        break;
                    case 2:
                        ACUPSet(2);
                        break;
                    case 3:
                        ACUPSet(7);
                        break;
                    case 4:
                        ACUPSet(4);
                        break;
                    case 5:
                        ACUPSet(5);
                        break;
                    case 6:
                        ACUPSet(6);
                        break;
                    case 7:
                        ACUPSet(13);
                        break;
                    case 8:
                        ACUPSet(14);
                        break;
                    case 9:
                        if (this.nAirUI <= 0) {
                            ACUPSet(9);
                            break;
                        } else {
                            ACUPSet(82);
                            break;
                        }
                    case 10:
                        if (this.nAirUI <= 0) {
                            ACUPSet(24);
                            break;
                        } else {
                            ACUPSet(83);
                            break;
                        }
                    case 11:
                        if (this.nAirUI <= 0) {
                            ACUPSet(10);
                            break;
                        } else {
                            ACUPSet(81);
                            break;
                        }
                    case 12:
                        ACUPSet(23);
                        break;
                    case 13:
                        if (this.nAirUI <= 0) {
                            ACUPSet(15);
                            break;
                        } else {
                            ACUPSet(32);
                            break;
                        }
                    case 14:
                        if (this.nAirUI <= 0) {
                            ACUPSet(16);
                            break;
                        } else {
                            ACUPSet(33);
                            break;
                        }
                    case 15:
                        ACUPSet(17);
                        break;
                    case 16:
                        ACUPSet(18);
                        break;
                    case 17:
                        if (this.nAirUI <= 0) {
                            ACUPSet(1);
                            break;
                        } else {
                            ACUPSet(46);
                            break;
                        }
                    case 18:
                        if (this.nAirUI <= 0) {
                            ACUPSet(12);
                            break;
                        } else {
                            ACUPSet(43);
                            break;
                        }
                    case 19:
                        if (this.nAirUI <= 0) {
                            ACUPSet(11);
                            break;
                        } else {
                            ACUPSet(42);
                            break;
                        }
                    case 20:
                        ACUPSet(3);
                        break;
                    case 22:
                        ACUPSet(66);
                        break;
                    case 23:
                        ACUPSet(34);
                        break;
                }
            }
        } else {
            switch (Id) {
                case 1:
                    ACSet(21);
                    break;
                case 2:
                    ACSet(2);
                    break;
                case 3:
                    ACSet(7);
                    break;
                case 4:
                    ACSet(4);
                    break;
                case 5:
                    ACSet(5);
                    break;
                case 6:
                    ACSet(6);
                    break;
                case 7:
                    ACSet(13);
                    break;
                case 8:
                    ACSet(14);
                    break;
                case 9:
                    if (this.nAirUI <= 0) {
                        ACSet(9);
                        break;
                    } else {
                        ACSet(82);
                        break;
                    }
                case 10:
                    if (this.nAirUI <= 0) {
                        ACSet(24);
                        break;
                    } else {
                        ACSet(83);
                        break;
                    }
                case 11:
                    if (this.nAirUI <= 0) {
                        ACSet(10);
                        break;
                    } else {
                        ACSet(81);
                        break;
                    }
                case 12:
                    ACSet(23);
                    break;
                case 13:
                    if (this.nAirUI <= 0) {
                        ACSet(15);
                        break;
                    } else {
                        ACSet(32);
                        break;
                    }
                case 14:
                    if (this.nAirUI <= 0) {
                        ACSet(16);
                        break;
                    } else {
                        ACSet(33);
                        break;
                    }
                case 15:
                    ACSet(17);
                    break;
                case 16:
                    ACSet(18);
                    break;
                case 17:
                    if (this.nAirUI <= 0) {
                        ACSet(1);
                        break;
                    } else {
                        ACSet(46);
                        break;
                    }
                case 18:
                    if (this.nAirUI <= 0) {
                        ACSet(12);
                        break;
                    } else {
                        ACSet(43);
                        break;
                    }
                case 19:
                    if (this.nAirUI <= 0) {
                        ACSet(11);
                        break;
                    } else {
                        ACSet(42);
                        break;
                    }
                case 20:
                    ACSet(3);
                    break;
                case 21:
                    if (this.nAirUI == 0) {
                        this.nAirUI = 1;
                        this.mBtnAuto.Show(false);
                        this.mBtnForeWind.Show(false);
                        this.mBtnWdUpDn.Show(false);
                        this.mBtnLtTempDec.Show(false);
                        this.mBtnLtTempInc.Show(false);
                        this.mTvLtTemp.ShowGone(false);
                        this.mBtnLtHot.Show(false);
                        this.mBtnRtHot.Show(false);
                        this.mIvLtTemp.Show(false);
                        this.mBtnRearAuto.Show(true);
                        this.mBtnRearLock.Show(true);
                        this.mBtnRearAc.setText(R.string.can_front_air);
                    } else {
                        this.nAirUI = 0;
                        this.mBtnAuto.Show(true);
                        this.mBtnForeWind.Show(true);
                        this.mBtnWdUpDn.Show(true);
                        this.mBtnLtTempDec.Show(true);
                        this.mBtnLtTempInc.Show(true);
                        this.mTvLtTemp.ShowGone(true);
                        this.mBtnLtHot.Show(true);
                        this.mBtnRtHot.Show(true);
                        this.mIvLtTemp.Show(true);
                        this.mBtnRearAuto.Show(false);
                        this.mBtnRearLock.Show(false);
                        this.mBtnRearAc.setText(R.string.can_rear_air);
                    }
                    updateACUI();
                    break;
                case 22:
                    ACSet(66);
                    break;
                case 23:
                    ACSet(34);
                    break;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void ACSet(int cmd) {
        CanJni.ChryslerWcAirKey(cmd, 1);
    }

    /* access modifiers changed from: protected */
    public void ACUPSet(int cmd) {
        CanJni.ChryslerWcAirKey(cmd, 0);
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
        this.mIvLtTemp = addImage(44, Can.CAN_FORD_SYNC3, R.drawable.can_jeep_ac_tmp);
        addImage(889, Can.CAN_FORD_SYNC3, R.drawable.can_jeep_ac_tmp);
        this.mBtnMaxAc = AddBtn(1, 0, 24, R.drawable.can_jeep_ac_max_up, R.drawable.can_jeep_ac_max_dn);
        this.mBtnAc = AddBtn(2, 174, 24, R.drawable.can_jeep_ac_ac_up, R.drawable.can_jeep_ac_ac_dn);
        this.mBtnLoop = AddBtn(3, 344, 24, R.drawable.can_jeep_ac_nxh_up, R.drawable.can_jeep_ac_nxh_dn);
        this.mBtnAuto = AddBtn(4, 513, 24, R.drawable.can_jeep_ac_auto_up, R.drawable.can_jeep_ac_auto_dn);
        this.mBtnForeWind = AddBtn(5, 682, 24, R.drawable.can_jeep_ac_window_up, R.drawable.can_jeep_ac_window_dn);
        this.mBtnRearWind = AddBtn(6, 852, 24, R.drawable.can_jeep_ac_rear_up, R.drawable.can_jeep_ac_rear_dn);
        this.mBtnLtTempDec = AddBtn(8, 45, KeyDef.RKEY_res1, R.drawable.can_jeep_ac_leng_up, R.drawable.can_jeep_ac_leng_dn);
        this.mBtnLtTempInc = AddBtn(7, 45, 124, R.drawable.can_jeep_ac_re_up, R.drawable.can_jeep_ac_re_dn);
        this.mTvLtTemp = AddTemp(44, Can.CAN_FORD_SYNC3, 93, 51);
        this.mBtnRtTempDec = AddBtn(14, 890, KeyDef.RKEY_res1, R.drawable.can_jeep_ac_leng_up, R.drawable.can_jeep_ac_leng_dn);
        this.mBtnRtTempInc = AddBtn(13, 890, 124, R.drawable.can_jeep_ac_re_up, R.drawable.can_jeep_ac_re_dn);
        this.mTvRtTemp = AddTemp(889, Can.CAN_FORD_SYNC3, 95, 51);
        this.mBtnWdPx = AddBtn(9, 171, 144, R.drawable.can_jeep_ac_01_up, R.drawable.can_jeep_ac_01_dn);
        this.mBtnWdPxDn = AddBtn(10, KeyDef.RKEY_ANGLEDN, 144, R.drawable.can_jeep_ac_02_up, R.drawable.can_jeep_ac_02_dn);
        this.mBtnWdDn = AddBtn(11, 466, 144, R.drawable.can_jeep_ac_03_up, R.drawable.can_jeep_ac_03_dn);
        this.mBtnWdUpDn = AddBtn(12, CanCameraUI.BTN_CCH9_MODE4, 144, R.drawable.can_jeep_ac_04_up, R.drawable.can_jeep_ac_04_dn);
        this.mBtnLtHot = AddBtn(15, 171, 294, R.drawable.can_jeep_ac_lchair_up, R.drawable.can_jeep_ac_lchair_dn);
        this.mBtnRtHot = AddBtn(16, 466, 294, R.drawable.can_jeep_ac_rchair_up, R.drawable.can_jeep_ac_rchair_dn);
        this.mBtnLtHot.setPadding(0, 0, 162, 0);
        this.mBtnLtHot.setTextSize(0, 30.0f);
        this.mBtnLtHot.setGravity(21);
        this.mBtnLtHot.setTextColor(-1);
        this.mBtnRtHot.setPadding(180, 0, 0, 0);
        this.mBtnRtHot.setTextSize(0, 30.0f);
        this.mBtnRtHot.setGravity(19);
        this.mBtnRtHot.setTextColor(-1);
        this.mBtnOff = AddBtn(17, 0, 406, R.drawable.can_jeep_ac_off_up, R.drawable.can_jeep_ac_off_dn);
        this.mBtnWindDec = AddBtn(18, 134, 406, R.drawable.can_jeep_ac_xfans_up, R.drawable.can_jeep_ac_xfans_dn);
        this.mBtnWindInc = AddBtn(19, 763, 406, R.drawable.can_jeep_ac_dfans_up, R.drawable.can_jeep_ac_dfans_dn);
        this.mWindProg = new MyProgressBar(getActivity(), R.drawable.can_jeep_ac_pro_up, R.drawable.can_jeep_ac_pro_dn);
        this.mWindProg.SetMinMax(0, 7);
        getRelativeManager().AddViewWrapContent(this.mWindProg, 287, 448);
        this.mIvWindAuto = addImage(457, 450, R.drawable.can_yl_wind_auto);
        this.mBtnSync = AddBtn(20, 893, 406, R.drawable.can_jeep_ac_sync_up, R.drawable.can_jeep_ac_sync_dn);
        this.mTvWindVal = AddTemp(717, 441, 32, 40);
        this.mBtnRearAc = AddBtn(21, 745, 200, 110, 130, R.drawable.can_jeep_ac_kong_up, R.drawable.can_jeep_ac_kong_dn, R.string.can_rear_air);
        this.mBtnRearAuto = AddBtn(22, 513, 24, 169, 69, R.drawable.can_jeep_ac_kong01_up, R.drawable.can_jeep_ac_kong01_dn, R.string.can_rear_auto);
        this.mBtnRearAuto.Show(false);
        this.mBtnRearLock = AddBtn(23, 682, 24, 169, 69, R.drawable.can_jeep_ac_kong01_up, R.drawable.can_jeep_ac_kong01_dn, R.string.can_rear_air_lock);
        this.mBtnRearLock.Show(false);
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
        if (this.nAirUI > 0) {
            this.mTvRtTemp.setText(this.mACInfo.szRearTemp);
        } else {
            this.mTvRtTemp.setText(this.mACInfo.szRtTemp);
        }
        this.mBtnMaxAc.SetSel(this.mACInfo.fgACMax);
        this.mBtnAc.SetSel(this.mACInfo.fgAC);
        this.mBtnAuto.SetSel(this.mACInfo.nAutoLight);
        if (this.mACInfo.fgInnerLoop != 0) {
            this.mBtnLoop.setDrawable(R.drawable.can_jeep_ac_nxh_up, R.drawable.can_jeep_ac_nxh_dn);
        } else {
            this.mBtnLoop.setDrawable(R.drawable.can_jeep_ac_wxh_up, R.drawable.can_jeep_ac_wxh_dn);
        }
        this.mBtnForeWind.SetSel(this.mACInfo.fgDFBL);
        this.mBtnRearWind.SetSel(this.mACInfo.fgRearLight);
        int windDirect = (this.mACInfo.fgForeWindMode * 1) + (this.mACInfo.fgParallelWind * 2) + (this.mACInfo.fgDownWind * 4);
        if (this.nAirUI > 0) {
            windDirect = (this.mACInfo.bRearUpWindFlg * 1) + (this.mACInfo.bRearParallelWindFlg * 2) + (this.mACInfo.bRearDownWindFlg * 4);
        }
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
        this.mBtnLtHot.SetSel(this.mACInfo.nLtChairHot);
        this.mBtnRtHot.SetSel(this.mACInfo.nRtChairHot);
        switch (this.mACInfo.nLtChairHot) {
            case 0:
                this.mBtnLtHot.setText(R.string.can_off);
                break;
            case 1:
                this.mBtnLtHot.setText(R.string.can_ac_low);
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
            default:
                this.mBtnRtHot.setText(R.string.can_ac_high);
                break;
        }
        if (this.nAirUI > 0) {
            this.mBtnOff.SetSel(1 - this.mACInfo.fgRac);
        } else {
            this.mBtnOff.SetSel(1 - this.mACInfo.PWR);
        }
        this.mBtnSync.SetSel(this.mACInfo.fgDual);
        if (this.nAirUI > 0) {
            this.mWindProg.SetCurPos(this.mACInfo.nRearWindValue);
            this.mTvWindVal.setText(new StringBuilder(String.valueOf(this.mACInfo.nRearWindValue)).toString());
            this.mIvWindAuto.Show(false);
        } else if (15 == this.mACInfo.nWindValue) {
            this.mWindProg.SetCurPos(0);
            this.mTvWindVal.setText(TXZResourceManager.STYLE_DEFAULT);
            this.mIvWindAuto.Show(true);
        } else {
            this.mWindProg.SetCurPos(this.mACInfo.nWindValue);
            this.mTvWindVal.setText(new StringBuilder(String.valueOf(this.mACInfo.nWindValue)).toString());
            this.mIvWindAuto.Show(false);
        }
        this.mBtnRearAuto.SetSel(this.mACInfo.nRearAutoLight);
        this.mBtnRearLock.SetSel(this.mACInfo.fgRearLock);
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
    public CustomTextView AddTemp(int x, int y, int w, int h) {
        CustomTextView temp = getRelativeManager().AddCusText(x, y, w, h);
        temp.SetPxSize(40);
        temp.setGravity(17);
        return temp;
    }
}
