package com.ts.can.renault.renault;

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
import com.yyw.ts70xhw.KeyDef;

public class CanRenaultLuoMuAcView extends CanBaseACView {
    private static final int AC = 5;
    private static final int AUTO = 4;
    private static final int CLOSED = 10;
    private static final int DUAL = 21;
    private static final int FRONT_WIN = 8;
    private static final int IMG_WIND_0 = 20;
    private static final int LOOP = 6;
    private static final int LT_TEMP_DECREASE = 1;
    private static final int LT_TEMP_INCREASE = 0;
    private static final int LT_TEMP_VALUE = 18;
    private static final int MODE_DOWN = 13;
    private static final int MODE_MID = 12;
    private static final int MODE_MID_DOWN = 14;
    private static final int MODE_UP = 15;
    private static final int MODE_UP_DOWN = 11;
    private static final int REAR_WIN = 9;
    private static final int RT_TEMP_DECREASE = 17;
    private static final int RT_TEMP_INCREASE = 16;
    private static final int RT_TEMP_VALUE = 19;
    private static final int WIND_DECREASE = 3;
    private static final int WIND_INCREASE = 2;
    private static int[] mIcons = {R.drawable.can_ac_lm_fl01_dn, R.drawable.can_ac_lm_fl02_dn, R.drawable.can_ac_lm_fl03_dn, R.drawable.can_ac_lm_fl04_dn, R.drawable.can_ac_lm_fl05_dn, R.drawable.can_ac_lm_fl06_dn, R.drawable.can_ac_lm_fl07_dn, R.drawable.can_ac_lm_fl08_dn};
    private CanDataInfo.CAN_ACInfo mACInfo;
    private CustomImgView[] mWindIcons;

    public CanRenaultLuoMuAcView(Activity activity) {
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
                CanJni.CCH2sAcSet(30, key);
                return;
            case 1:
                CanJni.CCH2sAcSet(31, key);
                return;
            case 2:
                CanJni.CCH2sAcSet(28, key);
                return;
            case 3:
                CanJni.CCH2sAcSet(29, key);
                return;
            case 4:
                CanJni.CCH2sAcSet(34, key);
                return;
            case 5:
                CanJni.CCH2sAcSet(17, key);
                return;
            case 6:
                CanJni.CCH2sAcSet(19, key);
                return;
            case 10:
                CanJni.CCH2sAcSet(16, key);
                return;
            case 11:
                CanJni.CCH2sAcSet(27, key);
                return;
            case 12:
                CanJni.CCH2sAcSet(24, key);
                return;
            case 13:
                CanJni.CCH2sAcSet(26, key);
                return;
            case 14:
                CanJni.CCH2sAcSet(25, key);
                return;
            case 15:
                CanJni.CCH2sAcSet(21, key);
                return;
            case 16:
                CanJni.CCH2sAcSet(32, key);
                return;
            case 17:
                CanJni.CCH2sAcSet(33, key);
                return;
            case 21:
                CanJni.CCH2sAcSet(35, key);
                return;
            default:
                return;
        }
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mTextAttrs = new int[][]{new int[]{178, 171, Can.CAN_SE_DX7_RZC, 142, 18}};
        this.mButtonAttrs = new int[][]{new int[]{555, 113, R.drawable.can_renault_ac_lm_sjt_up, R.drawable.can_renault_ac_lm_sjt_dn, 15}, new int[]{555, 171, R.drawable.can_renault_ac_lm_cjt_up, R.drawable.can_renault_ac_lm_cjt_dn, 12}, new int[]{555, 228, R.drawable.can_renault_ac_lm_xjt_up, R.drawable.can_renault_ac_lm_xjt_dn, 13}, new int[]{265, 444, R.drawable.can_renault_ac_lm_wmax_up, R.drawable.can_renault_ac_lm_wmax_dn, 8}, new int[]{86, 444, R.drawable.can_renault_ac_lm_window_up, R.drawable.can_renault_ac_lm_window_dn, 9}, new int[]{627, 444, R.drawable.can_renault_ac_lm_xh_up, R.drawable.can_renault_ac_lm_xh_dn, 6}, new int[]{799, 444, R.drawable.can_renault_ac_lm_auto_up, R.drawable.can_renault_ac_lm_auto_dn, 4}, new int[]{445, 444, R.drawable.can_renault_ac_lm_ac_up, R.drawable.can_renault_ac_lm_ac_dn, 5}, new int[]{837, 361, R.drawable.can_renault_ac_lm_off_up, R.drawable.can_renault_ac_lm_off_dn, 10}};
        this.mButtonTouch = new int[this.mButtonAttrs.length];
        for (int i = 0; i < this.mButtonTouch.length; i++) {
            this.mButtonTouch[i] = 1;
        }
    }

    /* access modifiers changed from: protected */
    public void InitViews() {
        setBackgroundResource(R.drawable.can_renault_ac_lm_bg);
        setTextStyle(18, Color.parseColor("#ffffff"), 80, 17);
        this.mWindIcons = new CustomImgView[8];
        this.mWindIcons[0] = addImage(872, KeyDef.RKEY_MEDIA_10, mIcons[0]);
        this.mWindIcons[1] = addImage(872, 293, mIcons[1]);
        this.mWindIcons[2] = addImage(872, 264, mIcons[2]);
        this.mWindIcons[3] = addImage(872, Can.CAN_ZH_WC, mIcons[3]);
        this.mWindIcons[4] = addImage(872, Can.CAN_SAIL_RW550_MG6_WC, mIcons[4]);
        this.mWindIcons[5] = addImage(872, 177, mIcons[5]);
        this.mWindIcons[6] = addImage(872, 148, mIcons[6]);
        this.mWindIcons[7] = addImage(872, 115, mIcons[7]);
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        this.mACInfo = Can.mACInfo;
        updateText(18, this.mACInfo.szLtTemp);
        setWindValue(this.mACInfo.nWindValue);
        updateButton(4, this.mACInfo.nAutoLight);
        updateButton(8, this.mACInfo.fgDFBL);
        updateButton(9, this.mACInfo.fgRearLight);
        updateButton(5, this.mACInfo.fgAC);
        updateButton(6, this.mACInfo.fgInnerLoop);
        updateButton(10, this.mACInfo.PWR);
        updateButton(12, this.mACInfo.fgParallelWind);
        updateButton(13, this.mACInfo.fgDownWind);
        updateButton(15, this.mACInfo.fgForeWindMode);
    }

    private void setWindValue(int wind) {
        for (int i = 0; i < this.mWindIcons.length; i++) {
            this.mWindIcons[i].Show(i + 1 <= wind);
        }
    }
}
