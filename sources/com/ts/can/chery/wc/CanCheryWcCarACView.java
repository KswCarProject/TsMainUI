package com.ts.can.chery.wc;

import android.app.Activity;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseACView;
import com.ts.other.CustomImgView;

public class CanCheryWcCarACView extends CanBaseACView {
    private static final int AC = 5;
    private static final int AC_MAX = 21;
    private static final int AUTO = 4;
    private static final int CLOSED = 10;
    private static final int DUAL = 19;
    private static final int FRONT_WIN = 8;
    private static final int IMG_WIND_0 = 18;
    private static final int LOOP = 6;
    private static final int LT_TEMP_DECREASE = 1;
    private static final int LT_TEMP_INCREASE = 0;
    private static final int LT_TEMP_VALUE = 16;
    private static final int MODE_DOWN = 13;
    private static final int MODE_MID = 12;
    private static final int MODE_MID_DOWN = 20;
    private static final int MODE_UP_DOWN = 11;
    private static final int REAR_WIN = 9;
    private static final int RT_TEMP_DECREASE = 15;
    private static final int RT_TEMP_INCREASE = 14;
    private static final int RT_TEMP_VALUE = 17;
    private static final int WIND_DECREASE = 3;
    private static final int WIND_INCREASE = 2;
    private static int[] mIcons = {R.drawable.can_rh7_signal01_dn, R.drawable.can_rh7_signal02_dn, R.drawable.can_rh7_signal03_dn, R.drawable.can_rh7_signal04_dn, R.drawable.can_rh7_signal05_dn, R.drawable.can_rh7_signal06_dn, R.drawable.can_rh7_signal07_dn};
    private CanDataInfo.CAN_ACInfo mACInfo;
    private CustomImgView[] mWindIcons;

    public CanCheryWcCarACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int id = ((Integer) v.getTag()).intValue();
        int action = event.getAction();
        if (action == 0) {
            SendKey(id, 1);
        } else if (action == 1) {
            SendKey(id, 0);
        }
        return false;
    }

    private void SendKey(int id, int key) {
        switch (id) {
            case 0:
                if (this.mACInfo.nLeftTemp != 255) {
                    CanJni.CheryWcAirKey(13, key);
                    return;
                }
                return;
            case 1:
                if (this.mACInfo.nLeftTemp != 254) {
                    CanJni.CheryWcAirKey(14, key);
                    return;
                }
                return;
            case 2:
                CanJni.CheryWcAirKey(11, key);
                return;
            case 3:
                CanJni.CheryWcAirKey(12, key);
                return;
            case 4:
                CanJni.CheryWcAirKey(4, key);
                return;
            case 5:
                CanJni.CheryWcAirKey(2, key);
                return;
            case 6:
                CanJni.CheryWcAirKey(7, key);
                return;
            case 8:
                CanJni.CheryWcAirKey(5, key);
                return;
            case 10:
                CanJni.CheryWcAirKey(1, key);
                return;
            case 11:
                CanJni.CheryWcAirKey(27, key);
                return;
            case 12:
                CanJni.CheryWcAirKey(25, key);
                return;
            case 13:
                CanJni.CheryWcAirKey(28, key);
                return;
            case 14:
                if (this.mACInfo.nRightTemp != 255) {
                    CanJni.CheryWcAirKey(15, key);
                    return;
                }
                return;
            case 15:
                if (this.mACInfo.nRightTemp != 254) {
                    CanJni.CheryWcAirKey(16, key);
                    return;
                }
                return;
            case 19:
                CanJni.CheryWcAirKey(3, key);
                return;
            case 20:
                CanJni.CheryWcAirKey(26, key);
                return;
            case 21:
                CanJni.CheryWcAirKey(30, key);
                return;
            default:
                return;
        }
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mTextAttrs = new int[][]{new int[]{53, Can.CAN_TEANA_OLD_DJ, 92, 61, 16}, new int[]{883, Can.CAN_TEANA_OLD_DJ, 92, 61, 17}};
        this.mImageAttrs = new int[][]{new int[]{191, 228, R.drawable.can_rh7_signal_up, 18}};
        int[] iArr = new int[5];
        iArr[0] = 50;
        iArr[1] = 117;
        iArr[2] = R.drawable.can_rh7_jia_up;
        iArr[3] = R.drawable.can_rh7_jia_dn;
        this.mButtonAttrs = new int[][]{iArr, new int[]{50, 314, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn, 1}, new int[]{880, 117, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn, 14}, new int[]{880, 314, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn, 15}, new int[]{188, 117, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn, 2}, new int[]{188, 314, R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn, 3}, new int[]{305, 108, R.drawable.can_rh7_icon04_up, R.drawable.can_rh7_icon04_dn, 11}, new int[]{305, 188, R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn, 12}, new int[]{305, 268, R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn, 13}, new int[]{305, 348, R.drawable.can_rh7_icon02_up, R.drawable.can_rh7_icon02_dn, 20}, new int[]{649, 172, R.drawable.can_rh7_window_up, R.drawable.can_rh7_window_dn, 8}, new int[]{305, 28, R.drawable.can_rh7_window_02_up, R.drawable.can_rh7_window_02_dn, 9}, new int[]{770, 60, R.drawable.can_rh7_wxh_dn, R.drawable.can_rh7_nxh_dn, 6}, new int[]{649, 60, R.drawable.can_rh7_auto_up, R.drawable.can_rh7_auto_dn, 4}, new int[]{770, 172, R.drawable.can_rh7_ac_up, R.drawable.can_rh7_ac_dn, 5}, new int[]{770, 285, R.drawable.can_rh7_dual_up, R.drawable.can_rh7_dual_dn, 19}, new int[]{470, 420, R.drawable.can_rh7_del_up, R.drawable.can_rh7_del_dn, 10}, new int[]{649, 285, R.drawable.can_rh7_max_up, R.drawable.can_rh7_max_dn, 21}};
        this.mButtonTouch = new int[this.mButtonAttrs.length];
        for (int i = 0; i < this.mButtonTouch.length; i++) {
            this.mButtonTouch[i] = 1;
        }
    }

    /* access modifiers changed from: protected */
    public void InitViews() {
        setBackgroundResource(R.drawable.can_rh7_bg_01);
        setTextStyle(16, Color.parseColor("#ffffff"), 18, 17);
        setTextStyle(17, Color.parseColor("#ffffff"), 18, 17);
        this.mWindIcons = new CustomImgView[7];
        for (int i = 0; i < this.mWindIcons.length; i++) {
            this.mWindIcons[i] = addImage(191, Can.CAN_TEANA_OLD_DJ, mIcons[i]);
        }
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        this.mACInfo = Can.mACInfo;
        updateText(16, this.mACInfo.szLtTemp);
        updateText(17, this.mACInfo.szRtTemp);
        setWindValue(this.mACInfo.nWindValue);
        updateButton(4, this.mACInfo.nAutoLight);
        updateButton(8, this.mACInfo.fgDFBL);
        updateButton(9, this.mACInfo.fgRearLight);
        updateButton(5, this.mACInfo.fgAC);
        updateButton(6, this.mACInfo.fgInnerLoop);
        if (i2b(this.mACInfo.fgForeWindMode) && i2b(this.mACInfo.fgDownWind)) {
            updateButton(11, 1);
            updateButton(20, 0);
            updateButton(12, 0);
            updateButton(13, 0);
        } else if (!i2b(this.mACInfo.fgParallelWind) || !i2b(this.mACInfo.fgDownWind)) {
            updateButton(11, 0);
            updateButton(20, 0);
            updateButton(12, this.mACInfo.fgParallelWind);
            updateButton(13, this.mACInfo.fgDownWind);
        } else {
            updateButton(20, 1);
            updateButton(11, 0);
            updateButton(12, 0);
            updateButton(13, 0);
        }
        updateButton(19, this.mACInfo.fgDual);
        updateButton(21, this.mACInfo.fgACMax);
    }

    private void setWindValue(int wind) {
        for (int i = 0; i < this.mWindIcons.length; i++) {
            this.mWindIcons[i].Show(i + 1 <= wind);
        }
    }
}
