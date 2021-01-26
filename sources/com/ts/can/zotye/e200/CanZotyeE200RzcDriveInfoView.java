package com.ts.can.zotye.e200;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanZotyeE200RzcDriveInfoView extends CanScrollCarInfoView {
    public static final int ITEM_DCDL = 1;
    public static final int ITEM_DCDY = 0;
    public static final int ITEM_DCDYMAX = 8;
    public static final int ITEM_DCDYMIN = 9;
    public static final int ITEM_DCWD = 3;
    public static final int ITEM_DCWDMAX = 10;
    public static final int ITEM_DCWDMIN = 11;
    public static final int ITEM_DJGL = 6;
    public static final int ITEM_DJKZQWD = 4;
    public static final int ITEM_DJWD = 5;
    public static final int ITEM_DJZS = 7;
    public static final int ITEM_SYDL = 2;
    private CanDataInfo.ZtRzcDriveInfo mDriveData;

    public CanZotyeE200RzcDriveInfoView(Activity activity) {
        super(activity, 12);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_dfqc_battery_v, R.string.can_dfqc_battery_a, R.string.can_dfqc_battery_e, R.string.can_dfqc_battery_c, R.string.can_qddjkzwd, R.string.can_dfqc_motor_c, R.string.can_dfqc_motor_g, R.string.can_dfqc_motor_r, R.string.can_dcdymax, R.string.can_dcdymin, R.string.can_dczzgwd, R.string.can_dczzdwd};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mDriveData = new CanDataInfo.ZtRzcDriveInfo();
    }

    public void ResetData(boolean check) {
        CanJni.ZotyeRzcGetDrivenfo(this.mDriveData);
        if (!i2b(this.mDriveData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDriveData.Update)) {
            this.mDriveData.Update = 0;
            updateItem(0, 0, String.valueOf(((float) this.mDriveData.Dcdy) * 0.1f) + " V");
            updateItem(1, 0, String.valueOf(((float) (this.mDriveData.Dcdl - 6000)) * 0.1f) + " A");
            updateItem(2, 0, String.valueOf(this.mDriveData.Sydl) + "%");
            updateItem(3, 0, "H:" + (this.mDriveData.Dcwdh - 50) + " °C" + "  L:" + (this.mDriveData.Dcwdl - 50) + " °C");
            updateItem(4, 0, String.valueOf(this.mDriveData.Djkzqwd - 50) + " °C");
            updateItem(5, 0, String.valueOf(this.mDriveData.Djwd - 50) + " °C");
            updateItem(6, 0, String.valueOf(this.mDriveData.Djgl - 200) + " KW");
            updateItem(7, 0, String.valueOf(this.mDriveData.Djzs - 32000) + " RPM");
            updateItem(8, 0, "N:" + this.mDriveData.Dcdymaxn + "   " + (((float) this.mDriveData.Dcdymax) * 0.1f) + " V");
            updateItem(9, 0, "N:" + this.mDriveData.Dcdyminn + "   " + (((float) this.mDriveData.Dcdymin) * 0.1f) + " V");
            updateItem(10, 0, String.valueOf(this.mDriveData.Dcwdmax) + " °C");
            updateItem(11, 0, String.valueOf(this.mDriveData.Dcwdmin) + " °C");
        }
    }

    public void QueryData() {
        CanJni.ZotyeQuery(71, 0);
    }
}
