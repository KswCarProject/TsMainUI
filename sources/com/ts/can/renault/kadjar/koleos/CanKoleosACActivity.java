package com.ts.can.renault.kadjar.koleos;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCommonACActivity;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanKoleosACActivity extends CanCommonACActivity {
    private static final int MODE_FAST = 23;
    private static final int MODE_FRONT_WIN = 21;
    private static final int MODE_NORMAL = 25;
    private static final int MODE_SOFT = 22;
    private static final int STATUS_AUTO_LOOP = 24;
    private static int[] mModeArrays = {R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn, R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn, R.drawable.can_rh7_icon05_up, R.drawable.can_rh7_icon05_dn};
    private int cmd;
    private ParamButton mBtnAutoLoop;
    private ParamButton mBtnEco;
    private ParamButton mBtnFast;
    private ParamButton mBtnNormal;
    private ParamButton mBtnSoft;

    /* access modifiers changed from: protected */
    public void InitACButtons() {
        boolean z = true;
        InitClickableStatusButtons(true);
        InitTempButtons(true);
        InitWindButtons(true);
        InitModeButtons(true, true);
        showGoneView((View) this.mBtnAcMax, false);
        showGoneView((View) this.mBtnMode, false);
        showGoneView((View) this.mBtnAcToggle, false);
        this.mBtnAutoLoop = this.mContainer.addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 398);
        this.mBtnEco = this.mContainer.addButton(200, 20);
        this.mBtnSoft = this.mContainer.addButton(KeyDef.RKEY_MEDIA_SLOW, 20);
        this.mBtnFast = this.mContainer.addButton(450, 20);
        this.mBtnNormal = this.mContainer.addButton(30, 17);
        ParamButton paramButton = this.mBtnNormal;
        if (CanJni.GetSubType() != 3) {
            z = false;
        }
        showGoneView((View) paramButton, z);
        this.mContainer.setDrawableUpSel(this.mBtnEco, R.drawable.conditioning_eco_01_up, R.drawable.conditioning_eco_01_dn).setDrawableUpDnSel(this.mBtnSoft, R.drawable.conditioning_soft_up, R.drawable.conditioning_soft_dn).setDrawableUpDnSel(this.mBtnFast, R.drawable.conditioning_fast_up, R.drawable.conditioning_fast_dn).setDrawableUpDnSel(this.mBtnNormal, R.drawable.conditioning_normal_up, R.drawable.conditioning_normal_dn).setDrawableUpDnSel(this.mBtnAutoLoop, R.drawable.can_rh7_autoloop_up, R.drawable.can_rh7_autoloop_dn).setIdTouchListener(this.mBtnSoft, 22, this).setIdTouchListener(this.mBtnFast, 23, this).setIdTouchListener(this.mBtnNormal, 25, this).setIdTouchListener(this.mBtnAutoLoop, 24, this);
    }

    /* access modifiers changed from: protected */
    public void InitModeButtons(boolean isTouchable, boolean isClickable) {
        this.mBtnModeArrays = new ParamButton[3];
        for (int i = 0; i < this.mBtnModeArrays.length; i++) {
            this.mBtnModeArrays[i] = this.mContainer.addButton(KeyDef.RKEY_MEDIA_ANGLE, (i * 100) + 108);
            this.mContainer.setDrawableUpDnSel(this.mBtnModeArrays[i], mModeArrays[i * 2], mModeArrays[(i * 2) + 1]);
            if (isTouchable) {
                this.mContainer.setIdTouchListener(this.mBtnModeArrays[i], i + 6, this);
            } else if (isClickable) {
                this.mContainer.setIdClickListener(this.mBtnModeArrays[i], i + 6, this);
            } else {
                this.mBtnModeArrays[i].setClickable(false);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setWindMode(int fgParallel, int fgDown, int fgForeWind, int fgDFBL) {
        for (ParamButton btnMode : this.mBtnModeArrays) {
            setViewSelected((View) btnMode, false);
        }
        if (i2b(fgParallel) && !i2b(fgDown) && !i2b(fgForeWind)) {
            setViewSelected((View) this.mBtnModeArrays[0], true);
        } else if (!i2b(fgParallel) && i2b(fgDown) && !i2b(fgForeWind)) {
            setViewSelected((View) this.mBtnModeArrays[1], true);
        } else if (!i2b(fgParallel) && !i2b(fgDown) && i2b(this.mAcInfo.fgUpWind)) {
            setViewSelected((View) this.mBtnModeArrays[2], true);
        }
        if (!this.isForeWindMode && this.mBtnFront != null) {
            setViewSelected((View) this.mBtnFront, fgDFBL);
        }
        setViewSelected((View) this.mBtnEco, this.mAcInfo.fgEco);
        setViewSelected((View) this.mBtnSoft, this.mAcInfo.fgAcErr1);
        setViewSelected((View) this.mBtnFast, this.mAcInfo.fgAcErr2);
        setViewSelected((View) this.mBtnNormal, this.mAcInfo.fgAcErr3);
        setViewSelected((View) this.mBtnAutoLoop, this.mAcInfo.fgAQS);
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View v, MotionEvent event) {
        int id = ((Integer) v.getTag()).intValue();
        int action = event.getAction();
        if (action == 0) {
            switch (id) {
                case 0:
                    this.cmd = 13;
                    break;
                case 1:
                    this.cmd = 14;
                    break;
                case 2:
                    this.cmd = 15;
                    break;
                case 3:
                    this.cmd = 16;
                    break;
                case 4:
                    this.cmd = 11;
                    break;
                case 5:
                    this.cmd = 12;
                    break;
                case 6:
                    this.cmd = 7;
                    break;
                case 8:
                    this.cmd = 9;
                    break;
                case 10:
                    this.cmd = 1;
                    break;
                case 12:
                    this.cmd = 21;
                    break;
                case 13:
                    this.cmd = 2;
                    break;
                case 14:
                    this.cmd = 3;
                    break;
                case 15:
                    this.cmd = 6;
                    break;
                case 16:
                    this.cmd = 23;
                    break;
                case 21:
                    this.cmd = 24;
                    break;
                case 22:
                    this.cmd = 25;
                    break;
                case 23:
                    this.cmd = 26;
                    break;
                case 24:
                    this.cmd = 22;
                    break;
                case 25:
                    this.cmd = 27;
                    break;
            }
            setViewSelected(v, true);
            CanJni.KoleosAcSet(this.cmd, 1);
        } else if (action == 1) {
            setViewSelected(v, false);
            CanJni.KoleosAcSet(this.cmd, 0);
        }
        return true;
    }
}
