package com.ts.can.df.fg_rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanDFFGRzcDcxxSetView extends CanScrollCarInfoView {
    private static final int DCZZDWD = 4;
    private static final int DCZZDWDZ = 6;
    private static final int DCZZGWD = 5;
    private static final int DCZZGWDZ = 7;
    private static final int DTZDDY = 0;
    private static final int DTZDDYZH = 2;
    private static final int DTZGDY = 1;
    private static final int DTZGDYZH = 3;
    private static final int SYDL = 8;
    private CanDataInfo.Dffg_RzcCarInfo mSetData;

    public CanDFFGRzcDcxxSetView(Activity activity) {
        super(activity, 9);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_dtzddy, R.string.can_dtzgdy, R.string.can_dtzddyzh, R.string.can_dtzgdyzh, R.string.can_dczzdwd, R.string.can_dczzgwd, R.string.can_dczzdwdzh, R.string.can_dczzgwdzh, R.string.can_dfqc_battery_e};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mSetData = new CanDataInfo.Dffg_RzcCarInfo();
    }

    public void ResetData(boolean check) {
        CanJni.DffgRzcGetCarInfo(this.mSetData);
        if (!i2b(this.mSetData.DcUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.DcUpdate)) {
            this.mSetData.DcUpdate = 0;
            updateItem(0, this.mSetData.Dtzddy, String.format("%.3f V", new Object[]{Float.valueOf(((float) this.mSetData.Dtzddy) * 0.001f)}));
            updateItem(1, this.mSetData.Dtzgdy, String.format("%.3f V", new Object[]{Float.valueOf(((float) this.mSetData.Dtzgdy) * 0.001f)}));
            updateItem(2, this.mSetData.Dtzddyzh, String.format("%d V", new Object[]{Integer.valueOf(this.mSetData.Dtzddyzh)}));
            updateItem(3, this.mSetData.Dtzgdyzh, String.format("%d V", new Object[]{Integer.valueOf(this.mSetData.Dtzgdyzh)}));
            updateItem(4, this.mSetData.Dczzdwd, String.format("%d ℃", new Object[]{Integer.valueOf(this.mSetData.Dczzdwd - 50)}));
            updateItem(5, this.mSetData.Dczzgwd, String.format("%d ℃", new Object[]{Integer.valueOf(this.mSetData.Dczzgwd - 50)}));
            updateItem(6, this.mSetData.Dczzdwdz, String.format("%d ℃", new Object[]{Integer.valueOf(this.mSetData.Dczzdwdz)}));
            updateItem(7, this.mSetData.Dczzgwdz, String.format("%d ℃", new Object[]{Integer.valueOf(this.mSetData.Dczzgwdz)}));
            updateItem(8, this.mSetData.Sydl, String.format("%d %%", new Object[]{Integer.valueOf(this.mSetData.Sydl)}));
        }
    }

    public void QueryData() {
    }
}
