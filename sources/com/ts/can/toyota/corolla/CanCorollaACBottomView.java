package com.ts.can.toyota.corolla;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseACWidgetView;
import com.ts.can.CanCameraUI;
import com.ts.can.toyota.dj.CanToyotaDJCarDeviceView;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;
import com.yyw.ts70xhw.Mcu;

public class CanCorollaACBottomView extends CanBaseACWidgetView implements View.OnTouchListener {
    public static final int ITEM_AC = 13;
    public static final int ITEM_AUTO = 12;
    public static final int ITEM_DUAL = 14;
    public static final int ITEM_LOOPER = 15;
    public static final int ITEM_LT_HOT = 9;
    public static final int ITEM_LT_MODE = 18;
    public static final int ITEM_LT_TEMP_DEC = 4;
    public static final int ITEM_LT_TEMP_INC = 3;
    public static final int ITEM_MAX_FRONT = 11;
    public static final int ITEM_POWER = 17;
    public static final int ITEM_REAR = 16;
    public static final int ITEM_RT_HOT = 10;
    public static final int ITEM_RT_MODE = 19;
    public static final int ITEM_RT_TEMP_DEC = 6;
    public static final int ITEM_RT_TEMP_INC = 5;
    public static final int ITEM_VOL_ADD = 2;
    public static final int ITEM_VOL_SUB = 1;
    public static final int ITEM_WIND_DEC = 8;
    public static final int ITEM_WIND_INC = 7;
    private static final String TAG = "CanCorollaACBottomView";
    /* access modifiers changed from: private */
    public boolean isLongPressed;
    private boolean isSelected;
    private CanDataInfo.CAN_ACInfo mAcInfo;
    private ParamButton mBtnAC;
    private ParamButton mBtnAuto;
    private ParamButton mBtnDual;
    private ParamButton mBtnLooper;
    private ParamButton mBtnLtHot;
    private ParamButton mBtnLtMode;
    private ParamButton mBtnLtTempDec;
    private ParamButton mBtnLtTempInc;
    private ParamButton mBtnMaxFront;
    private ParamButton mBtnPower;
    private ParamButton mBtnRear;
    private ParamButton mBtnRtHot;
    private ParamButton mBtnRtMode;
    private ParamButton mBtnRtTempDec;
    private ParamButton mBtnRtTempInc;
    private ParamButton mBtnVolDec;
    private ParamButton mBtnVolInc;
    private ParamButton mBtnWindDec;
    private ParamButton mBtnWindInc;
    private CanDataInfo.ToyotaChairInfo mChairInfo = new CanDataInfo.ToyotaChairInfo();
    private CustomImgView[] mIvLtHots = new CustomImgView[3];
    private CustomImgView[] mIvLtWindMode = new CustomImgView[3];
    private CustomImgView[] mIvRtHots = new CustomImgView[3];
    private CustomImgView[] mIvRtWindMode = new CustomImgView[3];
    private CustomImgView[] mIvWinds = new CustomImgView[7];
    private Runnable mSendKey = new Runnable() {
        public void run() {
            if (CanCorollaACBottomView.this.isLongPressed) {
                int id = ((Integer) CanCorollaACBottomView.this.mView.getTag()).intValue();
                if (id == 2) {
                    Mcu.SetCkey(19);
                } else if (id == 1) {
                    Mcu.SetCkey(20);
                }
                CanCorollaACBottomView.this.mView.postDelayed(this, 300);
            }
        }
    };
    private TextView mTvLtTemp;
    private TextView mTvRtTemp;
    /* access modifiers changed from: private */
    public View mView;

    public CanCorollaACBottomView(RelativeLayout layout) {
        super(layout);
    }

    public void InitUI() {
        getManager().AddImage(0, 0, CanToyotaDJCarDeviceView.ITEM_PLAY, 130).setBackgroundResource(R.drawable.can_spcom_air_bg);
        this.mBtnVolDec = AddBtn(1, 9, 8, 62, 115, R.drawable.can_spcom_air_vol_dec_up, R.drawable.can_spcom_air_vol_dec_dn);
        this.mBtnVolInc = AddBtn(2, 697, 8, 62, 115, R.drawable.can_spcom_air_vol_inc_up, R.drawable.can_spcom_air_vol_inc_dn);
        this.mBtnLtTempInc = AddBtn(3, 81, 8, 74, 41, R.drawable.can_spcom_air_heighten_up, R.drawable.can_spcom_air_heighten_dn);
        this.mBtnLtTempDec = AddBtn(4, 81, 82, 74, 41, R.drawable.can_spcom_air_lower_up, R.drawable.can_spcom_air_lower_dn);
        this.mBtnRtTempInc = AddBtn(5, CanCameraUI.BTN_CCH9_MODE4, 8, 74, 41, R.drawable.can_spcom_air_heighten_up, R.drawable.can_spcom_air_heighten_dn);
        this.mBtnRtTempDec = AddBtn(6, CanCameraUI.BTN_CCH9_MODE4, 82, 74, 41, R.drawable.can_spcom_air_lower_up, R.drawable.can_spcom_air_lower_dn);
        this.mBtnWindDec = AddBtn(8, Can.CAN_CHRYSLER_ONE_HC, 5, 76, 36, R.drawable.can_spcom_air_breeze_up, R.drawable.can_spcom_air_breeze_dn);
        this.mBtnWindInc = AddBtn(7, 457, 5, 76, 36, R.drawable.can_spcom_air_gale_up, R.drawable.can_spcom_air_gale_dn);
        this.mBtnLtHot = AddBtn(9, 270, 85, 28, 35, R.drawable.can_spcom_air_seat_l_up, R.drawable.can_spcom_air_seat_l_dn);
        this.mBtnRtHot = AddBtn(10, 470, 85, 28, 35, R.drawable.can_spcom_air_seat_r_up, R.drawable.can_spcom_air_seat_r_dn);
        this.mBtnLtMode = AddBtn(18, 219, 85, 44, 35, R.drawable.can_spcom_air_person_l_up, R.drawable.can_spcom_air_person_l_dn);
        this.mBtnRtMode = AddBtn(19, CanCameraUI.BTN_HMS7_HELP_LINE, 85, 44, 35, R.drawable.can_spcom_air_person_r_up, R.drawable.can_spcom_air_person_r_dn);
        this.mTvLtTemp = AddText(80, 52, 69, 20);
        this.mTvRtTemp = AddText(CanCameraUI.BTN_CCH9_MODE5, 52, 69, 20);
        for (int i = 0; i < this.mIvWinds.length; i++) {
            this.mIvWinds[i] = AddImage((i * 20) + KeyDef.RKEY_MEDIA_10, 12, 8, 21, R.drawable.can_spcom_air_bar_up, R.drawable.can_spcom_air_bar_dn);
        }
        this.mBtnMaxFront = AddBtn(11, 165, 8, 64, 69, R.drawable.can_spcom_air_max_up, R.drawable.can_spcom_air_max_dn);
        this.mBtnAuto = AddBtn(12, Can.CAN_CHRYSLER_ONE_HC, 44, 76, 36, R.drawable.can_spcom_air_auto_up, R.drawable.can_spcom_air_auto_dn);
        this.mBtnAC = AddBtn(13, KeyDef.RKEY_MEDIA_OSD, 44, 72, 36, R.drawable.can_spcom_air_ac_up, R.drawable.can_spcom_air_ac_dn);
        this.mBtnDual = AddBtn(14, 384, 44, 73, 36, R.drawable.can_spcom_air_dual_up, R.drawable.can_spcom_air_dual_dn);
        this.mBtnLooper = AddBtn(15, 457, 44, 76, 36, R.drawable.can_spcom_air_outer_up, R.drawable.can_spcom_air_outer_dn);
        this.mBtnRear = AddBtn(16, 539, 8, 64, 69, R.drawable.can_spcom_air_rear_up, R.drawable.can_spcom_air_rear_dn);
        this.mBtnPower = AddBtn(17, KeyDef.RKEY_EJECT, 85, 138, 38, R.drawable.can_spcom_air_close_up, R.drawable.can_spcom_air_close_dn);
        this.mIvLtWindMode[0] = AddImage(182, 85, 29, 20, R.drawable.can_spcom_air_direction);
        this.mIvLtWindMode[1] = AddImage(182, 105, 29, 8, R.drawable.can_spcom_air_head_l);
        this.mIvLtWindMode[2] = AddImage(182, 113, 29, 11, R.drawable.can_spcom_air_foot_l);
        this.mIvRtWindMode[0] = AddImage(557, 85, 29, 20, R.drawable.can_spcom_air_direction);
        this.mIvRtWindMode[1] = AddImage(557, 105, 29, 8, R.drawable.can_spcom_air_head_r);
        this.mIvRtWindMode[2] = AddImage(557, 113, 29, 11, R.drawable.can_spcom_air_foot_r);
        this.mIvLtHots[0] = AddImage(273, 118, 6, 12, R.drawable.can_spcom_air_gears_l_up, R.drawable.can_spcom_air_gears_l_dn);
        this.mIvLtHots[1] = AddImage(279, 118, 6, 12, R.drawable.can_spcom_air_gears_l_up, R.drawable.can_spcom_air_gears_l_dn);
        this.mIvLtHots[2] = AddImage(285, 118, 6, 12, R.drawable.can_spcom_air_gears_l_up, R.drawable.can_spcom_air_gears_l_dn);
        this.mIvRtHots[0] = AddImage(481, 118, 6, 12, R.drawable.can_spcom_air_gears_r_up, R.drawable.can_spcom_air_gears_r_dn);
        this.mIvRtHots[1] = AddImage(487, 118, 6, 12, R.drawable.can_spcom_air_gears_r_up, R.drawable.can_spcom_air_gears_r_dn);
        this.mIvRtHots[2] = AddImage(493, 118, 6, 12, R.drawable.can_spcom_air_gears_r_up, R.drawable.can_spcom_air_gears_r_dn);
        for (int i2 = 0; i2 < 3; i2++) {
            this.mIvLtWindMode[i2].Show(false);
            this.mIvRtWindMode[i2].Show(false);
            this.mIvLtHots[i2].Show(false);
            this.mIvRtHots[i2].Show(false);
        }
    }

    public void ResetData() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        this.mAcInfo = Can.mACInfo;
        this.mAcInfo.Update = 0;
        CanJni.ToyotaOdGetCarChairInfo(this.mChairInfo);
        if (!i2b(this.mAcInfo.PWR)) {
            this.mBtnPower.setSelected(true);
            this.mBtnMaxFront.setSelected(false);
            this.mBtnAuto.setSelected(false);
            this.mBtnAC.setSelected(false);
            this.mBtnDual.setSelected(false);
            this.mBtnRear.setSelected(false);
            this.mBtnLooper.setSelected(false);
            return;
        }
        this.mBtnPower.setSelected(false);
        this.mBtnMaxFront.SetSel(this.mAcInfo.fgMaxFornt);
        this.mBtnAuto.SetSel(this.mAcInfo.nAutoLight);
        this.mBtnAC.SetSel(this.mAcInfo.fgAC);
        this.mBtnDual.SetSel(this.mAcInfo.fgDual);
        this.mBtnRear.SetSel(this.mAcInfo.fgRearLight);
        if (i2b(this.mAcInfo.fgInnerLoop)) {
            this.mBtnLooper.setStateDrawable(R.drawable.can_spcom_air_inner_up, R.drawable.can_spcom_air_inner_dn, R.drawable.can_spcom_air_inner_dn);
            this.mBtnLooper.setSelected(true);
        } else {
            this.mBtnLooper.setStateDrawable(R.drawable.can_spcom_air_outer_up, R.drawable.can_spcom_air_outer_dn, R.drawable.can_spcom_air_outer_dn);
            this.mBtnLooper.setSelected(true);
        }
        this.mTvLtTemp.setText(this.mAcInfo.szLtTemp);
        this.mTvRtTemp.setText(this.mAcInfo.szRtTemp);
        int wind = this.mAcInfo.nWindValue;
        for (int i = 0; i < this.mIvWinds.length; i++) {
            CustomImgView customImgView = this.mIvWinds[i];
            if (i < wind) {
                z5 = true;
            } else {
                z5 = false;
            }
            customImgView.setSelected(z5);
        }
        int ltHot = this.mChairInfo.nLtChairHot;
        int rtHot = this.mChairInfo.nRtChairHot;
        for (int i2 = 0; i2 < this.mIvLtHots.length; i2++) {
            CustomImgView customImgView2 = this.mIvLtHots[i2];
            if (i2 < ltHot) {
                z = true;
            } else {
                z = false;
            }
            customImgView2.Show(z);
            CustomImgView customImgView3 = this.mIvRtHots[i2];
            if (i2 < rtHot) {
                z2 = true;
            } else {
                z2 = false;
            }
            customImgView3.Show(z2);
            CustomImgView customImgView4 = this.mIvLtHots[i2];
            if (i2 < ltHot) {
                z3 = true;
            } else {
                z3 = false;
            }
            customImgView4.setSelected(z3);
            CustomImgView customImgView5 = this.mIvRtHots[i2];
            if (i2 < rtHot) {
                z4 = true;
            } else {
                z4 = false;
            }
            customImgView5.setSelected(z4);
        }
        this.mIvLtWindMode[0].Show(this.mAcInfo.fgUpWind);
        this.mIvLtWindMode[1].Show(this.mAcInfo.fgParallelWind);
        this.mIvLtWindMode[2].Show(this.mAcInfo.fgDownWind);
        this.mIvRtWindMode[0].Show(this.mAcInfo.fgUpWind);
        this.mIvRtWindMode[1].Show(this.mAcInfo.fgParallelWind);
        this.mIvRtWindMode[2].Show(this.mAcInfo.fgDownWind);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        this.mView = v;
        if (action == 0) {
            Log.d(TAG, "****ACTION_DOWN*****");
            this.isSelected = v.isSelected();
            v.setSelected(true);
            sendAirKey(v, 1);
        } else if (2 == action) {
            v.setSelected(true);
            sendAirKey(v, 2);
        } else if (1 == action) {
            Log.d(TAG, "****ACTION_UP*****");
            v.setSelected(this.isSelected);
            sendAirKey(v, 0);
        }
        return true;
    }

    private void sendAirKey(View v, int key) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                if (key == 1) {
                    Mcu.SetCkey(20);
                    return;
                } else if (key != 2) {
                    this.isLongPressed = false;
                    v.removeCallbacks(this.mSendKey);
                    return;
                } else if (!this.isLongPressed) {
                    v.postDelayed(this.mSendKey, 300);
                    this.isLongPressed = true;
                    return;
                } else {
                    return;
                }
            case 2:
                if (key == 1) {
                    Mcu.SetCkey(19);
                    return;
                } else if (key != 2) {
                    this.isLongPressed = false;
                    v.removeCallbacks(this.mSendKey);
                    return;
                } else if (!this.isLongPressed) {
                    v.postDelayed(this.mSendKey, 300);
                    this.isLongPressed = true;
                    return;
                } else {
                    return;
                }
            case 3:
                CanJni.ToyotaOdCarAirKey(3, key);
                return;
            case 4:
                CanJni.ToyotaOdCarAirKey(2, key);
                return;
            case 5:
                CanJni.ToyotaOdCarAirKey(5, key);
                return;
            case 6:
                CanJni.ToyotaOdCarAirKey(4, key);
                return;
            case 7:
                CanJni.ToyotaOdCarAirKey(10, key);
                return;
            case 8:
                CanJni.ToyotaOdCarAirKey(9, key);
                return;
            case 9:
                CanJni.ToyotaOdCarAirKey(11, key);
                return;
            case 10:
                CanJni.ToyotaOdCarAirKey(13, key);
                return;
            case 11:
                CanJni.ToyotaOdCarAirKey(19, key);
                return;
            case 12:
                CanJni.ToyotaOdCarAirKey(21, key);
                return;
            case 13:
                CanJni.ToyotaOdCarAirKey(23, key);
                return;
            case 14:
                CanJni.ToyotaOdCarAirKey(16, key);
                return;
            case 15:
                CanJni.ToyotaOdCarAirKey(25, key);
                return;
            case 16:
                CanJni.ToyotaOdCarAirKey(20, key);
                return;
            case 17:
                CanJni.ToyotaOdCarAirKey(1, key);
                return;
            case 18:
                CanJni.ToyotaOdCarAirKey(67, key);
                return;
            case 19:
                CanJni.ToyotaOdCarAirKey(69, key);
                return;
            default:
                return;
        }
    }

    public void QueryData() {
    }
}
