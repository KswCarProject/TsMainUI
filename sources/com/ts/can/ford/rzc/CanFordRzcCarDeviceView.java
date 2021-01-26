package com.ts.can.ford.rzc;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.can.CanBaseCarDeviceActivity;
import com.ts.can.CanRelativeCarInfoView;

public class CanFordRzcCarDeviceView extends CanRelativeCarInfoView {
    private CanDataInfo.FordRzcHostInfo mHostInfo;
    private int nQueryTick = 0;

    public CanFordRzcCarDeviceView(Activity activity) {
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
        this.mHostInfo = new CanDataInfo.FordRzcHostInfo();
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
    }

    public void ResetData(boolean check) {
        CanJni.FordRzcGetHostInfo(this.mHostInfo);
        if (!i2b(this.mHostInfo.UpdateOnce)) {
            this.nQueryTick++;
            if (this.nQueryTick > 50) {
                this.nQueryTick = 0;
                CanJni.FordQuery(101, 0);
            }
        } else if (!check || i2b(this.mHostInfo.Update)) {
            this.mHostInfo.Update = 0;
            if (this.mHostInfo.Src == 1) {
                enterSubWin(CanBaseCarDeviceActivity.class, -1);
            } else if (this.mHostInfo.Src == 2) {
                enterSubWin(CanBaseCarDeviceActivity.class, -2);
            }
        }
    }

    public void QueryData() {
    }

    public static void Init() {
        if (CanJni.GetSubType() == 7 || CanJni.GetSubType() == 8 || CanJni.GetSubType() == 9) {
            CanJni.FordRzcCarSrcCmd(0);
        }
    }
}
