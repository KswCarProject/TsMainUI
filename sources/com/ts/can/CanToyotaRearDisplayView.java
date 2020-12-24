package com.ts.can;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.other.ParamButton;

public class CanToyotaRearDisplayView extends CanRelativeCarInfoView {
    private static final int BTN_POWER = 0;
    private static final int BTN_PP = 4;
    private static final int BTN_REAR_LOCK = 1;
    private static final int BTN_REAR_MODE = 2;
    private static final int BTN_STOP = 3;
    private static String[] mCDStateArray = {" ", "Load", "Wait", "Reading", "Play", "", "Eject", "Error"};
    private static String[] mStateArray = {"OFF", "DISC", "DISC CD", "DISC DVD", "SD", "USB", "A/V"};
    private ParamButton mBtnPower;
    private ParamButton mBtnRearLock;
    private TextView mTVCDState;
    private TextView mTVError;
    private TextView mTVState;
    private CanDataInfo.ToyotaRzc_Xdp mXdpData;

    public CanToyotaRearDisplayView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.ToyotaAmpSet(96, Neg(this.mXdpData.Power));
                return;
            case 1:
                CanJni.ToyotaAmpSet(97, Neg(this.mXdpData.Hpxtsz));
                return;
            case 2:
                CanJni.ToyotaAmpSet(98, 1);
                return;
            case 3:
                CanJni.ToyotaAmpSet(99, 1);
                return;
            case 4:
                CanJni.ToyotaAmpSet(100, 1);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mTVState = addText((int) Can.CAN_JAC_REFINE_OD, 100, 0);
        this.mTVCDState = addText((int) CanCameraUI.BTN_GEELY_YJX6_MODE1, 100, 0);
        this.mTVError = addText((int) CanCameraUI.BTN_CC_WC_DIRECTION1, (int) Can.CAN_JAC_REFINE_OD, 0);
        this.mTVState.setTextColor(-1);
        this.mTVCDState.setTextColor(-1);
        this.mTVError.setTextColor(-1);
        this.mTVState.setTextSize(30.0f);
        this.mTVCDState.setTextSize(30.0f);
        this.mTVError.setTextSize(15.0f);
        this.mBtnPower = addButtonState(90, 300, R.drawable.can_sync_bk_up, R.drawable.can_sync_bk_dn);
        this.mBtnRearLock = addButtonState(CanCameraUI.BTN_GEELY_YJX6_MODE1, 300, R.drawable.can_sync_bk_up, R.drawable.can_sync_bk_dn);
        ParamButton btnRearMode = addButtonState(CanCameraUI.BTN_GEELY_YJX6_MODE1, 390, R.drawable.can_sync_bk_up, R.drawable.can_sync_bk_dn);
        ParamButton btnStop = addButtonState(20, 460, R.drawable.can_sync_zfx_up, R.drawable.can_sync_zfx_dn);
        ParamButton btnPP = addButtonState(200, 460, R.drawable.can_pp_up, R.drawable.can_pp_dn);
        this.mBtnPower.setText(R.string.can_power);
        this.mBtnRearLock.setText(R.string.can_rear_lock);
        btnRearMode.setText(R.string.can_rear_mode);
        this.mBtnPower.setTextColor(-1);
        this.mBtnPower.setTextSize(20.0f);
        this.mBtnRearLock.setTextColor(-1);
        this.mBtnRearLock.setTextSize(20.0f);
        btnRearMode.setTextColor(-1);
        btnRearMode.setTextSize(20.0f);
        setIdClickListener(this.mBtnPower, 0).setIdClickListener(this.mBtnRearLock, 1).setIdClickListener(btnRearMode, 2).setIdClickListener(btnStop, 3).setIdClickListener(btnPP, 4);
        this.mXdpData = new CanDataInfo.ToyotaRzc_Xdp();
    }

    public void ResetData(boolean check) {
        CanJni.ToyotaRzcGetXdpData(this.mXdpData);
        if (!i2b(this.mXdpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mXdpData.Update)) {
            this.mXdpData.Update = 0;
            int state = this.mXdpData.Mode;
            if (state < 0 || state > 6) {
                this.mTVState.setText("");
            } else {
                this.mTVState.setText(mStateArray[state]);
            }
            int cdState = this.mXdpData.CdSta;
            if (cdState < 0 || cdState > 7) {
                this.mTVCDState.setText("");
            } else {
                this.mTVCDState.setText(mCDStateArray[cdState]);
            }
            if (cdState == 7) {
                this.mTVError.setText("ErrorCode：" + this.mXdpData.Error);
            } else {
                this.mTVError.setText(" ");
            }
            this.mBtnPower.SetSel(this.mXdpData.Power);
            this.mBtnRearLock.SetSel(this.mXdpData.Hpxtsz);
        }
    }

    public void QueryData() {
    }
}
