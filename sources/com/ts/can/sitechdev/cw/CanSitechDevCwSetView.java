package com.ts.can.sitechdev.cw;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanSitechDevCwSetView extends CanScrollCarInfoView {
    public static final String TAG = "CopyOfCanSitechDevCwSetView";
    private CanDataInfo.SitechDev_CarInfo4 m_CarInfo4;

    public CanSitechDevCwSetView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.SitechDevCwAcKey(175, 1);
                Sleep(20);
                CanJni.SitechDevCwAcKey(175, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.m_CarInfo4 = new CanDataInfo.SitechDev_CarInfo4();
        this.mItemTitleIds = new int[]{R.string.can_beep_sta};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CHECK};
    }

    public void ResetData(boolean check) {
        CanJni.SitechDevCwGetCarInfo4(this.m_CarInfo4);
        if (!i2b(this.m_CarInfo4.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_CarInfo4.Update)) {
            this.m_CarInfo4.Update = 0;
            updateItem(0, this.m_CarInfo4.Beep);
        }
    }

    public void QueryData() {
    }
}
