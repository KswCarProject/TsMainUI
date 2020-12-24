package com.ts.can.ford.wc.mondeo;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFordMondeoWcDrivAssistSetView extends CanScrollCarInfoView {
    CanDataInfo.FordWcMondeo2013FuncAdt mAdtData;
    CanDataInfo.FordWcMondeo2013CarData mCarData;

    public CanFordMondeoWcDrivAssistSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_rpm, R.string.can_curspeed};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mAdtData = new CanDataInfo.FordWcMondeo2013FuncAdt();
        this.mCarData = new CanDataInfo.FordWcMondeo2013CarData();
    }

    public void ResetData(boolean check) {
        String nValString;
        String nValString2;
        CanJni.FordWcMondeo2013GetFuncAdt(this.mAdtData);
        CanJni.FordWcMondeo2013GetCarData(this.mCarData);
        if (i2b(this.mAdtData.UpdateOnce) && (!check || i2b(this.mAdtData.Update))) {
            this.mAdtData.Update = 0;
            showItem(0, this.mAdtData.Rpm);
            showItem(1, this.mAdtData.Speed);
        }
        if (!i2b(this.mCarData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarData.Update)) {
            this.mCarData.Update = 0;
            if (this.mCarData.Rpm == 65535) {
                nValString = "--";
            } else {
                nValString = String.format("%d R/min", new Object[]{Integer.valueOf(this.mCarData.Rpm)});
            }
            updateItem(0, this.mCarData.Rpm, nValString);
            if (this.mCarData.Speed == 65535) {
                nValString2 = "--";
            } else {
                nValString2 = String.format("%d Km/h", new Object[]{Integer.valueOf(this.mCarData.Speed)});
            }
            updateItem(1, this.mCarData.Speed, nValString2);
        }
    }

    public void QueryData() {
        CanJni.FordWcMondeo2013Query(1, 63);
        Sleep(1);
        CanJni.FordWcMondeo2013Query(1, 50);
    }
}
