package com.ts.can.toyota.wc.crown_h;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.can.CanBaseCarDeviceActivity;
import com.ts.can.CanFunc;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.canview.CanItemMsgBox;
import com.yyw.ts70xhw.FtSet;

public class CanCrownhWcCarDeviceView extends CanRelativeCarInfoView {
    private CanItemMsgBox mMsgBox;
    private CanDataInfo.CrownWcHeadStatus wcHeadStatus;

    public CanCrownhWcCarDeviceView(Activity activity) {
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
        this.wcHeadStatus = new CanDataInfo.CrownWcHeadStatus();
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
    }

    public void ResetData(boolean check) {
        CanJni.CrownWcGetHeadStatus(this.wcHeadStatus);
        if (i2b(this.wcHeadStatus.UpdateOnce)) {
            if (!check || i2b(this.wcHeadStatus.Update)) {
                this.wcHeadStatus.Update = 0;
                if (this.wcHeadStatus.Mode == 0) {
                    CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -1);
                } else {
                    CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -2);
                }
            }
        } else if (FtSet.Getlgb1() == 0) {
            CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -1);
        } else {
            CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -2);
        }
    }

    public static void DealStatusChanged() {
        CanDataInfo.CrownWcHeadStatus wcHeadStatus2 = new CanDataInfo.CrownWcHeadStatus();
        CanJni.CrownWcGetHeadStatus(wcHeadStatus2);
        if (wcHeadStatus2.UpdateOnce > 0) {
            FtSet.Setlgb1(wcHeadStatus2.Mode);
            if (wcHeadStatus2.Mode == 0) {
                CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -1);
            } else if (wcHeadStatus2.Mode == 1) {
                CanFunc.showCanActivity(CanBaseCarDeviceActivity.class, -2);
            }
        }
    }

    public void QueryData() {
    }
}
