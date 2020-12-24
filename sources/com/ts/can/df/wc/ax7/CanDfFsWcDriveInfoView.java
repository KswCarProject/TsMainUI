package com.ts.can.df.wc.ax7;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanDfFsWcDriveInfoView extends CanScrollCarInfoView {
    private static final int ITEM_MAX = 3;
    private static final int ITEM_SYYL = 1;
    private static final int ITEM_XHLC = 2;
    private static final int ITEM_ZLC = 0;
    private CanDataInfo.CAN_Msg m_DriveData;

    public CanDfFsWcDriveInfoView(Activity activity) {
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
        this.mItemTitleIds = new int[]{R.string.can_total_mile, R.string.can_rest_oil, R.string.can_range_xhlc};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.m_DriveData = new CanDataInfo.CAN_Msg();
    }

    public void ResetData(boolean check) {
        CanJni.GetCarInfo(this.m_DriveData);
        if (!i2b(this.m_DriveData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_DriveData.Update)) {
            this.m_DriveData.Update = 0;
            if (this.m_DriveData.Distance == 16777215) {
                updateItem(0, 0, "--");
            } else {
                updateItem(0, 0, String.format("%.1f", new Object[]{Double.valueOf(((double) this.m_DriveData.Distance) * 0.1d)}));
            }
            updateItem(1, 0, String.format("%d %%", new Object[]{Integer.valueOf(this.m_DriveData.Syyw)}));
            if (this.m_DriveData.EndurOil == 65535) {
                updateItem(2, 0, "--");
                return;
            }
            updateItem(2, 0, String.format("%d Km", new Object[]{Integer.valueOf(this.m_DriveData.EndurOil)}));
        }
    }

    public void QueryData() {
        CanJni.DfWcCarQuery(5, 1, 19);
    }
}
