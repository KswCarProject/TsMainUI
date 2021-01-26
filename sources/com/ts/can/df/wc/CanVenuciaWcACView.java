package com.ts.can.df.wc;

import android.app.Activity;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseACView;
import com.ts.can.CanCameraUI;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanVenuciaWcACView extends CanBaseACView {
    private static final int AC_TOGGLE = 18;
    private static final int LT_TEMP_DECREASE = 1;
    private static final int LT_TEMP_INCREASE = 0;
    private static final int MODE = 19;
    private static final int RT_TEMP_DECREASE = 3;
    private static final int RT_TEMP_INCREASE = 2;
    private static final int STATUS_AC = 10;
    private static final int STATUS_LOOPER_INNER = 11;
    private static final int STATUS_LOOPER_OUTTER = 12;
    private static final int WIND_DECREASE = 5;
    private static final int WIND_INCREASE = 4;
    private static int[] mWindArrays = {R.drawable.can_rh7_signal01_dn, R.drawable.can_rh7_signal02_dn, R.drawable.can_rh7_signal03_dn, R.drawable.can_rh7_signal04_dn, R.drawable.can_rh7_signal05_dn, R.drawable.can_rh7_signal06_dn, R.drawable.can_rh7_signal07_dn};
    private ParamButton leftTempDecrease;
    private ParamButton leftTempIncrease;
    private CanDataInfo.CAN_ACInfo mAcInfo;
    private ParamButton mBtnAc;
    private ParamButton mBtnAcMax;
    private ParamButton mBtnAcToggle;
    private ParamButton mBtnDual;
    private ParamButton mBtnFront;
    private ParamButton mBtnLooperInner;
    private ParamButton mBtnLooperOutter;
    private ParamButton mBtnMode;
    private ParamButton mBtnModeFoot;
    private ParamButton mBtnModeFootFront;
    private ParamButton mBtnModeHead;
    private ParamButton mBtnModeHeadFoot;
    private ParamButton mBtnRear;
    private ImageView[] mIvWinds;
    private TextView mTvLeftTemp;
    private TextView mTvRightTemp;
    private ParamButton rightTempDecrease;
    private ParamButton rightTempIncrease;
    private ParamButton windDecrease;
    private ParamButton windIncrease;

    public CanVenuciaWcACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (action == 0) {
            switch (Id) {
                case 0:
                    ACSet(13);
                    return false;
                case 1:
                    ACSet(14);
                    return false;
                case 2:
                    ACSet(15);
                    return false;
                case 3:
                    ACSet(16);
                    return false;
                case 4:
                    ACSet(11);
                    return false;
                case 5:
                    ACSet(12);
                    return false;
                case 10:
                    ACSet(2);
                    return false;
                case 11:
                    ACSet(7);
                    return false;
                case 12:
                    ACSet(7);
                    return false;
                case 18:
                    ACSet(1);
                    return false;
                case 19:
                    ACSet(21);
                    return false;
                default:
                    return false;
            }
        } else if (1 != action) {
            return false;
        } else {
            switch (Id) {
                case 0:
                    ACUPSet(13);
                    return false;
                case 1:
                    ACUPSet(14);
                    return false;
                case 2:
                    ACUPSet(15);
                    return false;
                case 3:
                    ACUPSet(16);
                    return false;
                case 4:
                    ACUPSet(11);
                    return false;
                case 5:
                    ACUPSet(12);
                    return false;
                case 10:
                    ACUPSet(2);
                    return false;
                case 11:
                    ACUPSet(7);
                    return false;
                case 12:
                    ACUPSet(7);
                    return false;
                case 18:
                    ACUPSet(1);
                    return false;
                case 19:
                    ACUPSet(21);
                    return false;
                default:
                    return false;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void ACSet(int cmd) {
        CanJni.VenuciaWcAirKey(cmd, 1);
    }

    /* access modifiers changed from: protected */
    public void ACUPSet(int cmd) {
        CanJni.VenuciaWcAirKey(cmd, 0);
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
    }

    /* access modifiers changed from: protected */
    public void InitViews() {
        setBackgroundResource(R.drawable.can_rh7_bg_01);
        initCommonScreen();
    }

    private void initCommonScreen() {
        this.mIvWinds = new ImageView[7];
        this.leftTempIncrease = AddBtn(0, 50, 117, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn);
        this.leftTempDecrease = AddBtn(1, 50, KeyDef.RKEY_POWER, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn);
        this.rightTempIncrease = AddBtn(2, 880, 117, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn);
        this.rightTempDecrease = AddBtn(3, 880, KeyDef.RKEY_POWER, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn);
        this.windIncrease = AddBtn(4, 188, 117, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn);
        this.windDecrease = AddBtn(5, 188, KeyDef.RKEY_POWER, R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn);
        addImage(191, Can.CAN_TEANA_OLD_DJ, R.drawable.can_rh7_signal_up);
        for (int i = 0; i < this.mIvWinds.length; i++) {
            this.mIvWinds[i] = addImage(191, Can.CAN_TEANA_OLD_DJ, mWindArrays[i]);
        }
        this.mTvLeftTemp = AddTemp(53, Can.CAN_TEANA_OLD_DJ, 95, 61);
        this.mTvRightTemp = AddTemp(883, Can.CAN_TEANA_OLD_DJ, 95, 61);
        this.mBtnModeHead = addButtonState(305, 108, R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn);
        this.mBtnModeHeadFoot = addButtonState(305, 185, R.drawable.can_rh7_icon02_up, R.drawable.can_rh7_icon02_dn);
        this.mBtnModeFoot = addButtonState(305, 264, R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn);
        this.mBtnModeFootFront = addButtonState(305, KeyDef.RKEY_res5, R.drawable.can_rh7_icon04_up, R.drawable.can_rh7_icon04_dn);
        this.mBtnDual = addButtonState(200, 20, R.drawable.conditioning_dual_up, R.drawable.conditioning_dual_dn);
        this.mBtnAcMax = addButtonState(KeyDef.RKEY_MEDIA_SLOW, 20, R.drawable.conditioning_acmax_up, R.drawable.conditioning_acmax_dn);
        this.mBtnFront = addButtonState(450, 17, R.drawable.conditioning_wind_up, R.drawable.conditioning_wind_dn);
        this.mBtnRear = addButtonState(CanCameraUI.BTN_TRUMPCHI_GS7_MODE1, 17, R.drawable.conditioning_heat_up, R.drawable.conditioning_heat_dn);
        this.mBtnAc = AddBtn(10, CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 110, R.drawable.can_rh7_ac_up, R.drawable.can_rh7_ac_dn);
        this.mBtnLooperOutter = AddBtn(12, 757, 110, R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_wxh_dn);
        this.mBtnLooperInner = AddBtn(11, CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 222, R.drawable.can_rh7_nxh_up, R.drawable.can_rh7_nxh_dn);
        this.mBtnMode = AddBtn(19, 757, 222, R.drawable.can_rh7_mode_up, R.drawable.can_rh7_mode_dn);
        this.mBtnAcToggle = AddBtn(18, CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, KeyDef.RKEY_res5, R.drawable.can_rh7_del_up, R.drawable.can_rh7_del_dn);
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        this.mAcInfo = Can.mACInfo;
        this.mTvLeftTemp.setText(this.mAcInfo.szLtTemp);
        this.mTvRightTemp.setText(this.mAcInfo.szRtTemp);
        setViewSelected((View) this.mBtnAc, this.mAcInfo.fgAC);
        setViewSelected((View) this.mBtnLooperInner, this.mAcInfo.fgInnerLoop);
        setViewSelected((View) this.mBtnLooperOutter, !i2b(this.mAcInfo.fgInnerLoop));
        setViewSelected((View) this.mBtnDual, this.mAcInfo.fgDual);
        setViewSelected((View) this.mBtnAcMax, this.mAcInfo.fgACMax);
        setViewSelected((View) this.mBtnRear, this.mAcInfo.fgRearLight);
        setViewSelected((View) this.mBtnFront, this.mAcInfo.fgDFBL);
        setWindValue(this.mAcInfo.nWindValue);
        setWindMode(this.mAcInfo.fgParallelWind, this.mAcInfo.fgDownWind, this.mAcInfo.fgForeWindMode);
    }

    private void setWindMode(int fgParallel, int fgDown, int fgForeWind) {
        setViewSelected((View) this.mBtnModeHead, false);
        setViewSelected((View) this.mBtnModeHeadFoot, false);
        setViewSelected((View) this.mBtnModeFoot, false);
        setViewSelected((View) this.mBtnModeFootFront, false);
        if (i2b(fgParallel) && !i2b(fgDown) && !i2b(fgForeWind)) {
            setViewSelected((View) this.mBtnModeHead, true);
        } else if (i2b(fgParallel) && i2b(fgDown) && !i2b(fgForeWind)) {
            setViewSelected((View) this.mBtnModeHeadFoot, true);
        } else if (!i2b(fgParallel) && i2b(fgDown) && !i2b(fgForeWind)) {
            setViewSelected((View) this.mBtnModeFoot, true);
        } else if (!i2b(fgParallel) && i2b(fgDown) && i2b(fgForeWind)) {
            setViewSelected((View) this.mBtnModeFootFront, true);
        }
    }

    public void setViewSelected(View view, int selected) {
        view.setSelected(i2b(selected));
    }

    public void setViewSelected(View view, boolean selected) {
        view.setSelected(selected);
    }

    private void setWindValue(int wind) {
        for (ImageView windIcon : this.mIvWinds) {
            showGoneView(windIcon, false);
        }
        for (int i = 0; i < wind; i++) {
            if (i < this.mIvWinds.length) {
                showGoneView(this.mIvWinds[i], true);
            }
        }
    }

    public void showGoneView(View view, boolean show) {
        view.setVisibility(show ? 0 : 8);
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnTouchListener(this);
        btn.setStateDrawable(up, dn, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnTouchListener(this);
        btn.setStateDrawable(up, dn, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTemp(int x, int y, int w, int h) {
        CustomTextView temp = getRelativeManager().AddCusText(x, y, w, h);
        temp.SetPxSize(40);
        temp.setTextColor(Color.rgb(8, 210, 211));
        temp.setGravity(17);
        return temp;
    }
}
