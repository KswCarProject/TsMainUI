package com.ts.can.sitechdev.cw;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanSitechDevCwDisInfoView extends CanScrollCarInfoView {
    public static final String TAG = "CanSitechDevCwDtInfoView";
    private CanDataInfo.SitechDev_Dis mDev_Dis;

    public CanSitechDevCwDisInfoView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mDev_Dis = new CanDataInfo.SitechDev_Dis();
        this.mItemTitleIds = new int[]{R.string.can_speed, R.string.can_ljlc, R.string.can_kxslc};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
    }

    public void ResetData(boolean check) {
        CanJni.SitechDevCwGetDisData(this.mDev_Dis);
        if (!i2b(this.mDev_Dis.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDev_Dis.Update)) {
            this.mDev_Dis.Update = 0;
            updateItem(0, 0, GetSpeed(this.mDev_Dis.Speed));
            updateItem(1, 0, String.valueOf(this.mDev_Dis.Ljlc) + "Km");
            updateItem(2, 0, GetKxslc(this.mDev_Dis.Kxslc));
        }
    }

    public String GetSpeed(int speed) {
        if (speed == 65535) {
            return "--KPH";
        }
        if (speed == 0) {
            return String.valueOf(speed) + "KPH";
        }
        return String.valueOf(String.format("%.2f", new Object[]{Float.valueOf((float) (((double) speed) * 0.05d))})) + "KPH";
    }

    public String GetKxslc(int mile) {
        if (mile == 65535) {
            return "--Km";
        }
        if (mile == 0) {
            return String.valueOf(mile) + "Km";
        }
        return String.valueOf(String.format("%.1f", new Object[]{Float.valueOf((float) (((double) mile) * 0.5d))})) + "Km";
    }

    public void QueryData() {
    }
}
