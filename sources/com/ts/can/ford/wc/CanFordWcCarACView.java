package com.ts.can.ford.wc;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseACView;
import com.ts.can.CanCameraUI;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanFordWcCarACView extends CanBaseACView {
    private static final int ITEM_AC = 1;
    private static final int ITEM_AUTO = 3;
    private static final int ITEM_CLOSE_POP = 31;
    private static final int ITEM_DEFROST_CONTROL = 29;
    private static final int ITEM_DOWN_MODE = 9;
    private static final int ITEM_DUAL = 2;
    private static final int ITEM_FORE_HOT = 26;
    private static final int ITEM_FORE_WIN = 4;
    private static final int ITEM_LOOPPER = 6;
    private static final int ITEM_LT_HOT = 16;
    private static final int ITEM_LT_TEMP_DEC = 13;
    private static final int ITEM_LT_TEMP_INC = 12;
    private static final int ITEM_LT_WIND = 18;
    private static final int ITEM_MAX_AC = 20;
    private static final int ITEM_MID_MODE = 8;
    private static final int ITEM_POWER = 0;
    private static final int ITEM_REAR_LOCK = 23;
    private static final int ITEM_REAR_POWER = 28;
    private static final int ITEM_REAR_WIN = 5;
    private static final int ITEM_RR_TEMP_DEC = 22;
    private static final int ITEM_RR_TEMP_INC = 21;
    private static final int ITEM_RR_WIND_DEC = 25;
    private static final int ITEM_RR_WIND_INC = 24;
    private static final int ITEM_RT_HOT = 17;
    private static final int ITEM_RT_TEMP_DEC = 15;
    private static final int ITEM_RT_TEMP_INC = 14;
    private static final int ITEM_RT_WIND = 19;
    private static final int ITEM_SHOW_REAR = 30;
    private static final int ITEM_UP_MODE = 7;
    private static final int ITEM_WHEEL_HOT = 27;
    private static final int ITEM_WIND_DEC = 11;
    private static final int ITEM_WIND_INC = 10;
    private CanDataInfo.CAN_ACInfo mACInfo;
    private ParamButton mBtnAC;
    private ParamButton mBtnAuto;
    private ParamButton mBtnAutoMode;
    private ParamButton mBtnDnMode;
    private ParamButton mBtnDual;
    private ParamButton mBtnMidMode;
    private ParamButton mBtnPower;
    private ParamButton mBtnRearFog;
    private ParamButton mBtnRearLock;
    private ParamButton mBtnRearPower;
    private ParamButton mBtnUpMode;
    private ParamButton mBtnWheelHot;
    private ParamButton mBtnforeFog;
    private ParamButton mBtnforeHot;
    private ImageView[] mIvLtIcons;
    private ImageView[] mIvRRTempIcons;
    private ImageView[] mIvRRWindIcons;
    private ImageView[] mIvRtIcons;
    private ImageView[] mIvWindIcons;
    private RelativeLayoutManager mPopContainer;
    private TextView mTvLtTemp;
    private TextView mTvRtTemp;
    private WindowManager mWinManager;
    private WindowManager.LayoutParams mWinParams;

    public CanFordWcCarACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int id = ((Integer) v.getTag()).intValue();
        if (event.getAction() == 0) {
            sendTouchKey(id, 1);
        } else if (event.getAction() == 1) {
            sendTouchKey(id, 0);
        }
        return false;
    }

    private void sendTouchKey(int id, int param) {
        switch (id) {
            case 0:
                CanJni.FordWcCarAirSet(1, param);
                return;
            case 1:
                CanJni.FordWcCarAirSet(2, param);
                return;
            case 2:
                CanJni.FordWcCarAirSet(3, param);
                return;
            case 3:
                CanJni.FordWcCarAirSet(4, param);
                return;
            case 4:
                CanJni.FordWcCarAirSet(5, param);
                return;
            case 5:
                CanJni.FordWcCarAirSet(6, param);
                return;
            case 6:
                CanJni.FordWcCarAirSet(7, param);
                return;
            case 7:
                CanJni.FordWcCarAirSet(8, param);
                return;
            case 8:
                CanJni.FordWcCarAirSet(9, param);
                return;
            case 9:
                CanJni.FordWcCarAirSet(10, param);
                return;
            case 10:
                CanJni.FordWcCarAirSet(11, param);
                return;
            case 11:
                CanJni.FordWcCarAirSet(12, param);
                return;
            case 12:
                CanJni.FordWcCarAirSet(13, param);
                return;
            case 13:
                CanJni.FordWcCarAirSet(14, param);
                return;
            case 14:
                CanJni.FordWcCarAirSet(15, param);
                return;
            case 15:
                CanJni.FordWcCarAirSet(16, param);
                return;
            case 16:
                CanJni.FordWcCarAirSet(17, param);
                return;
            case 17:
                CanJni.FordWcCarAirSet(18, param);
                return;
            case 18:
                CanJni.FordWcCarAirSet(23, param);
                return;
            case 19:
                CanJni.FordWcCarAirSet(24, param);
                return;
            case 20:
                CanJni.FordWcCarAirSet(26, param);
                return;
            case 21:
                CanJni.FordWcCarAirSet(32, param);
                return;
            case 22:
                CanJni.FordWcCarAirSet(33, param);
                return;
            case 23:
                CanJni.FordWcCarAirSet(34, param);
                return;
            case 24:
                CanJni.FordWcCarAirSet(42, param);
                return;
            case 25:
                CanJni.FordWcCarAirSet(43, param);
                return;
            case 26:
                CanJni.FordWcCarAirSet(44, param);
                return;
            case 27:
                CanJni.FordWcCarAirSet(45, param);
                return;
            case 28:
                CanJni.FordWcCarAirSet(46, param);
                return;
            default:
                return;
        }
    }

    public void doOnPause() {
        super.doOnPause();
        dismissPopWin();
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 29) {
            showPopWin(false);
        } else if (id == 30) {
            showPopWin(true);
        } else if (id == 31) {
            dismissPopWin();
        }
    }

    private void showPopWin(boolean showRearAir) {
        if (this.mWinParams == null) {
            this.mWinManager = getActivity().getWindowManager();
            this.mPopContainer = new RelativeLayoutManager(new RelativeLayout(getActivity()));
            this.mPopContainer.GetLayout().setBackgroundResource(R.drawable.can_rj19_ac_popup_bg);
            this.mWinParams = new WindowManager.LayoutParams();
            this.mWinParams.width = 886;
            this.mWinParams.height = CanCameraUI.BTN_HMS7_MODE4;
            this.mWinParams.type = 2005;
            this.mWinParams.gravity = 17;
            this.mWinParams.format = 8;
        }
        this.mPopContainer.RemoveAllViews();
        if (showRearAir) {
            initRearAirContainer();
        } else {
            initDefrostContainer();
        }
        if (this.mPopContainer.GetLayout().getParent() == null) {
            this.mWinManager.addView(this.mPopContainer.GetLayout(), this.mWinParams);
            updateRearACAir();
        }
    }

    private void dismissPopWin() {
        if (this.mPopContainer != null && this.mPopContainer.GetLayout().getParent() != null) {
            this.mWinManager.removeView(this.mPopContainer.GetLayout());
        }
    }

    private void initDefrostContainer() {
        addPopButton(21, 417, R.drawable.can_rj19_ac_close_up, R.drawable.can_rj19_ac_close_dn, 31, "Close");
        TextView mTvPopTitle = this.mPopContainer.AddText(KeyDef.RKEY_RDS_TA, 1, Can.CAN_BENZ_SMART_OD, 71);
        mTvPopTitle.setGravity(17);
        mTvPopTitle.setTextColor(-16777216);
        mTvPopTitle.setTextSize(20.0f);
        mTvPopTitle.setText("Defrost Controls");
        this.mBtnforeFog = addPopButton(KeyDef.RKEY_RDS_TA, 66, R.drawable.can_rj19_ac_max_up, R.drawable.can_rj19_ac_max_dn, 4);
        this.mBtnUpMode = addPopButton(KeyDef.RKEY_RDS_TA, 151, R.drawable.can_rj19_ac_wd_up, R.drawable.can_rj19_ac_wd_dn, 7);
        this.mBtnforeHot = addPopButton(KeyDef.RKEY_RDS_TA, Can.CAN_NISSAN_RICH6_WC, R.drawable.can_rj19_ac_wd2_up, R.drawable.can_rj19_ac_wd2_dn, 26);
        this.mBtnRearFog = addPopButton(KeyDef.RKEY_RDS_TA, KeyDef.RKEY_MEDIA_10, R.drawable.can_rj19_ac_rwd_up, R.drawable.can_rj19_ac_rwd_dn, 5);
    }

    private void initRearAirContainer() {
        this.mPopContainer.AddImage(47, 107, R.drawable.can_rj19_ac_sbg01);
        addPopButton(47, 107, R.drawable.can_rj19_ac_s_up, R.drawable.can_rj19_ac_s_dn, 24);
        addPopButton(47, 280, R.drawable.can_rj19_ac_x_up, R.drawable.can_rj19_ac_x_dn, 25);
        this.mPopContainer.AddImage(CanCameraUI.BTN_CCH9_MODE4, 107, R.drawable.can_rj19_ac_sbg03);
        addPopButton(CanCameraUI.BTN_CCH9_MODE4, 107, R.drawable.can_rj19_ac_s_up_red, R.drawable.can_rj19_ac_s_dn, 21);
        addPopButton(CanCameraUI.BTN_CCH9_MODE4, 280, R.drawable.can_rj19_ac_x_up_blue, R.drawable.can_rj19_ac_x_dn, 22);
        this.mIvRRWindIcons = new ImageView[7];
        this.mIvRRTempIcons = new ImageView[9];
        for (int i = 0; i < this.mIvRRWindIcons.length; i++) {
            this.mIvRRWindIcons[i] = this.mPopContainer.AddImage(53, ((6 - i) * 16) + 173, R.drawable.can_rj19_ac_long_up);
        }
        for (int i2 = 0; i2 < this.mIvRRTempIcons.length; i2++) {
            this.mIvRRTempIcons[i2] = this.mPopContainer.AddImageEx(697, ((8 - i2) * 10) + 184, 59, 7, R.drawable.can_rj19_ac_short_gray);
        }
        this.mPopContainer.AddImage(124, 190, R.drawable.can_rj19_ac_fan);
        this.mBtnRearPower = addPopButton(KeyDef.RKEY_RDS_TA, 107, R.drawable.can_rj19_ac_shut_up, R.drawable.can_rj19_ac_shut_dn, 28);
        this.mBtnRearLock = addPopButton(KeyDef.RKEY_RDS_TA, 280, R.drawable.can_rj19_ac_lock_up, R.drawable.can_rj19_ac_lock_dn, 23);
        addPopButton(21, 417, R.drawable.can_rj19_ac_close_up, R.drawable.can_rj19_ac_close_dn, 31, "Close");
        TextView mTvPopTitle = this.mPopContainer.AddText(KeyDef.RKEY_RADIO_5S, 21, Can.CAN_BENZ_SMART_OD, 71);
        mTvPopTitle.setGravity(17);
        mTvPopTitle.setTextColor(-16777216);
        mTvPopTitle.setTextSize(24.0f);
        mTvPopTitle.setText("Rear Climate");
    }

    /* access modifiers changed from: protected */
    public void InitData() {
    }

    /* access modifiers changed from: protected */
    public void InitViews() {
        setBackgroundResource(R.drawable.can_rj19_ac_bg);
        this.mBtnWheelHot = addTouchButton(25, 40, R.drawable.can_rj19_ac_fxpjr_up, R.drawable.can_rj19_ac_fxpjr_dn, 27);
        this.mBtnAuto = addTouchButton(275, 40, R.drawable.can_rj19_ac_auto_up, R.drawable.can_rj19_ac_auto_dn, 3);
        this.mBtnPower = addTouchButton(CanCameraUI.BTN_GEELY_YJX6_GJ, 40, R.drawable.can_rj19_ac_shut_up, R.drawable.can_rj19_ac_shut_dn, 0);
        this.mBtnDual = addTouchButton(KeyDef.SKEY_VOLUP_3, 40, R.drawable.can_rj19_ac_dual_up, R.drawable.can_rj19_ac_dual_dn, 2);
        addImage(25, 126, R.drawable.can_rj19_ac_sbg01);
        addImage(275, 126, R.drawable.can_rj19_ac_sbg01);
        addImage(KeyDef.SKEY_VOLUP_3, 126, R.drawable.can_rj19_ac_sbg01);
        addImage(CanCameraUI.BTN_HMS7_HELP_LINE, 126, R.drawable.can_rj19_ac_sbg02);
        addTouchButton(25, 126, R.drawable.can_rj19_ac_s_up_red, R.drawable.can_rj19_ac_s_dn, 12);
        addTouchButton(25, KeyDef.RKEY_MEDIA_PP, R.drawable.can_rj19_ac_x_up_blue, R.drawable.can_rj19_ac_x_dn, 13);
        addTouchButton(KeyDef.SKEY_VOLUP_3, 126, R.drawable.can_rj19_ac_s_up_red, R.drawable.can_rj19_ac_s_dn, 14);
        addTouchButton(KeyDef.SKEY_VOLUP_3, KeyDef.RKEY_MEDIA_PP, R.drawable.can_rj19_ac_x_up_blue, R.drawable.can_rj19_ac_x_dn, 15);
        addTouchButton(CanCameraUI.BTN_GEELY_YJX6_GJ, 126, R.drawable.can_rj19_ac_s_up, R.drawable.can_rj19_ac_s_dn, 10);
        addTouchButton(CanCameraUI.BTN_GEELY_YJX6_GJ, KeyDef.RKEY_MEDIA_PP, R.drawable.can_rj19_ac_x_up, R.drawable.can_rj19_ac_x_dn, 11);
        this.mTvLtTemp = addTempText(50, 200);
        this.mTvRtTemp = addTempText(800, 200);
        setIdClickListener(addButtonState(275, 126, R.drawable.can_rj19_ac_defrost_up, R.drawable.can_rj19_ac_defrost_dn), 29);
        this.mBtnMidMode = addTouchButton(275, Can.CAN_LEXUS_ZMYT, R.drawable.can_rj19_ac_swind_up, R.drawable.can_rj19_ac_swind_dn, 8);
        this.mBtnDnMode = addTouchButton(275, KeyDef.RKEY_MEDIA_PP, R.drawable.can_rj19_ac_xwind_up, R.drawable.can_rj19_ac_xwind_dn, 9);
        addTouchButton(25, 387, R.drawable.can_rj19_ac_rect_up, R.drawable.can_rj19_ac_rect_up, -1);
        addTouchButton(KeyDef.SKEY_VOLUP_3, 387, R.drawable.can_rj19_ac_rect_up, R.drawable.can_rj19_ac_rect_up, -1);
        addTouchButton(25, 387, R.drawable.can_rj19_ac_lchair_up, R.drawable.can_rj19_ac_lchair_dn, 18);
        addTouchButton(144, 387, R.drawable.can_rj19_ac_rchair_up, R.drawable.can_rj19_ac_rchair_dn, 16);
        addTouchButton(KeyDef.SKEY_VOLUP_3, 387, R.drawable.can_rj19_ac_lchair_up, R.drawable.can_rj19_ac_lchair_dn, 19);
        addTouchButton(895, 387, R.drawable.can_rj19_ac_rchair_up, R.drawable.can_rj19_ac_rchair_dn, 17);
        this.mIvWindIcons = new ImageView[7];
        for (int i = 0; i < this.mIvWindIcons.length; i++) {
            this.mIvWindIcons[i] = addImageState(CanCameraUI.BTN_CHANA_ALSVINV7_MODE3, ((6 - i) * 16) + 192, R.drawable.can_rj19_ac_long_up, R.drawable.can_rj19_ac_long_dn);
        }
        addImage(CanCameraUI.BTN_GOLF_WC_MODE4, 209, R.drawable.can_rj19_ac_fan);
        this.mBtnAutoMode = addTouchButton(CanCameraUI.BTN_HMS7_MODE3, 197, R.drawable.can_rj19_ac_bauto_up, R.drawable.can_rj19_ac_bauto_dn, -1);
        this.mIvLtIcons = new ImageView[3];
        this.mIvRtIcons = new ImageView[3];
        for (int i2 = 0; i2 < this.mIvLtIcons.length; i2++) {
            this.mIvLtIcons[i2] = addImage(131, ((2 - i2) * 15) + CanCameraUI.BTN_TRUMPCHI_GS4_MODE3, R.drawable.can_rj19_ac_ct_black);
        }
        for (int i3 = 0; i3 < this.mIvRtIcons.length; i3++) {
            this.mIvRtIcons[i3] = addImage(882, ((2 - i3) * 15) + CanCameraUI.BTN_TRUMPCHI_GS4_MODE3, R.drawable.can_rj19_ac_ct_black);
        }
        this.mBtnAC = addTouchButton(275, 387, R.drawable.can_rj19_ac_ac_up, R.drawable.can_rj19_ac_ac_dn, 1);
        setIdClickListener(addButtonState(CanCameraUI.BTN_GEELY_YJX6_GJ, 387, R.drawable.can_rj19_ac_rear_up, R.drawable.can_rj19_ac_rear_dn), 30);
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        this.mACInfo = Can.mACInfo;
        updateForeACAir();
        updateRearACAir();
    }

    private void updateForeACAir() {
        this.mBtnPower.SetSel(this.mACInfo.PWR);
        this.mBtnAuto.SetSel(this.mACInfo.nAutoLight);
        this.mBtnDual.SetSel(this.mACInfo.fgDual);
        this.mBtnWheelHot.SetSel(this.mACInfo.fgWheelHot);
        this.mBtnAC.SetSel(this.mACInfo.fgAC);
        this.mBtnMidMode.SetSel(this.mACInfo.fgParallelWind);
        this.mBtnDnMode.SetSel(this.mACInfo.fgDownWind);
        this.mBtnAutoMode.SetSel(this.mACInfo.fgAutoMode);
        this.mTvLtTemp.setText(this.mACInfo.szLtTemp);
        this.mTvRtTemp.setText(this.mACInfo.szRtTemp);
        int i = 0;
        while (i < this.mIvWindIcons.length) {
            this.mIvWindIcons[i].setSelected(i < this.mACInfo.nWindValue);
            i++;
        }
        for (int i2 = 0; i2 < this.mIvLtIcons.length; i2++) {
            if (this.mACInfo.nLtChairHot > 0) {
                if (i2 < this.mACInfo.nLtChairHot) {
                    this.mIvLtIcons[i2].setImageResource(R.drawable.can_rj19_ac_ct_red);
                } else {
                    this.mIvLtIcons[i2].setImageResource(R.drawable.can_rj19_ac_ct_black);
                }
            } else if (i2 < this.mACInfo.nLtChairWind) {
                this.mIvLtIcons[i2].setImageResource(R.drawable.can_rj19_ac_ct_blue);
            } else {
                this.mIvLtIcons[i2].setImageResource(R.drawable.can_rj19_ac_ct_black);
            }
        }
        for (int i3 = 0; i3 < this.mIvRtIcons.length; i3++) {
            if (this.mACInfo.nRtChairHot > 0) {
                if (i3 < this.mACInfo.nRtChairHot) {
                    this.mIvRtIcons[i3].setImageResource(R.drawable.can_rj19_ac_ct_red);
                } else {
                    this.mIvRtIcons[i3].setImageResource(R.drawable.can_rj19_ac_ct_black);
                }
            } else if (i3 < this.mACInfo.nRtChairWind) {
                this.mIvRtIcons[i3].setImageResource(R.drawable.can_rj19_ac_ct_blue);
            } else {
                this.mIvRtIcons[i3].setImageResource(R.drawable.can_rj19_ac_ct_black);
            }
        }
    }

    private void updateRearACAir() {
        if (this.mIvRRWindIcons != null) {
            for (int i = 0; i < this.mIvRRWindIcons.length; i++) {
                if (i < this.mACInfo.nRearWindValue) {
                    this.mIvRRWindIcons[i].setImageResource(R.drawable.can_rj19_ac_long_dn);
                } else {
                    this.mIvRRWindIcons[i].setImageResource(R.drawable.can_rj19_ac_long_up);
                }
            }
        }
        if (this.mIvRRTempIcons != null) {
            for (int i2 = 0; i2 < this.mIvRRTempIcons.length; i2++) {
                if (i2 < this.mACInfo.nRearTemp) {
                    this.mIvRRTempIcons[i2].setImageResource(R.drawable.can_rj19_ac_short_red);
                } else {
                    this.mIvRRTempIcons[i2].setImageResource(R.drawable.can_rj19_ac_short_gray);
                }
            }
        }
        if (this.mBtnRearPower != null) {
            this.mBtnRearPower.SetSel(this.mACInfo.fgRac);
            this.mBtnRearLock.SetSel(this.mACInfo.fgRearLock);
        }
        if (this.mBtnforeHot != null) {
            this.mBtnUpMode.SetSel(this.mACInfo.fgForeWindMode);
            this.mBtnforeHot.SetSel(this.mACInfo.bForeWindHotFlg);
            this.mBtnforeFog.SetSel(this.mACInfo.fgDFBL);
            this.mBtnRearFog.SetSel(this.mACInfo.fgRearLight);
        }
    }

    private ParamButton addTouchButton(int x, int y, int up, int dn, int id) {
        ParamButton btn = addButtonState(x, y, up, dn, dn);
        if (id != -1) {
            setIdTouchListener(btn, id);
        }
        return btn;
    }

    private TextView addTempText(int x, int y) {
        TextView tv = addText(x, y, 180, 70);
        setTextStyle(tv, -1, 40, 17);
        tv.setText("26.0°");
        return tv;
    }

    private ParamButton addPopButton(int x, int y, int up, int dn, int id) {
        ParamButton btn = this.mPopContainer.AddButton(x, y);
        btn.setStateDrawable(up, dn, dn);
        btn.setTag(Integer.valueOf(id));
        btn.setOnTouchListener(this);
        return btn;
    }

    private ParamButton addPopButton(int x, int y, int up, int dn, int id, String text) {
        ParamButton btn = this.mPopContainer.AddButton(x, y);
        btn.setStateDrawable(up, dn, dn);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setText(text);
        btn.setTextColor(-16777216);
        btn.setGravity(17);
        btn.setTextSize(20.0f);
        return btn;
    }
}
