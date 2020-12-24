package com.ts.can.mzd.rzc;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.bt.BtExe;
import com.ts.can.CanBaseCarDeviceActivity;
import com.ts.can.CanFunc;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.main.common.WinShow;

public class CanMzdRzcCarDeviceView extends CanRelativeCarInfoView {
    public static int mOldBtSta = 0;
    private CanDataInfo.Mzd_Rzc_AudioInfo mAudioData;

    public CanMzdRzcCarDeviceView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mAudioData = new CanDataInfo.Mzd_Rzc_AudioInfo();
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
    }

    public void ResetData(boolean check) {
        CanJni.MzdRzcGetCarAudioInfo(this.mAudioData);
        if (!i2b(this.mAudioData.UpdateOnce)) {
            CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -2);
        } else if (!check || i2b(this.mAudioData.Update)) {
            this.mAudioData.Update = 0;
            if (this.mAudioData.Src == 1 || this.mAudioData.Src == 2) {
                CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -1);
            } else if (this.mAudioData.Src == 3 || this.mAudioData.Src == 5) {
                CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -3);
            } else {
                CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -2);
            }
        }
    }

    public static void DealStatusChanged() {
        CanDataInfo.Mzd_Rzc_AudioInfo mAudioData2 = new CanDataInfo.Mzd_Rzc_AudioInfo();
        CanJni.MzdRzcGetCarAudioInfo(mAudioData2);
        if (mAudioData2.UpdateOnce <= 0) {
            return;
        }
        if (mAudioData2.Src == 1 || mAudioData2.Src == 2) {
            CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -1);
        } else if (mAudioData2.Src == 5) {
            CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -3);
        } else if (mAudioData2.Src == 3) {
            if (WinShow.getTopActivityName().equals("com.ts.can.CanBaseCarDeviceActivity")) {
                CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -3);
            }
        } else if (mAudioData2.Src == 4) {
            CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -2);
        }
    }

    public static void DealDevEvent() {
        if (CanJni.GetSubType() == 2 || CanJni.GetSubType() == 3) {
            BtExe bt = BtExe.getBtInstance();
            if (mOldBtSta != bt.getSta()) {
                mOldBtSta = bt.getSta();
                if (mOldBtSta == 3 || mOldBtSta == 2 || mOldBtSta == 4) {
                    CanJni.MzdRzcCarAudioSet(5, 1);
                } else {
                    CanJni.MzdRzcCarAudioSet(5, 0);
                }
            }
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(113, 0);
    }
}
