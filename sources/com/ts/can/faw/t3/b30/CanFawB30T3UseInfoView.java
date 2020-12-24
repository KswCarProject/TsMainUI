package com.ts.can.faw.t3.b30;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFawB30T3UseInfoView extends CanScrollCarInfoView {
    private CanDataInfo.FawB30T3_DriveInfo mDriveInfo;

    public CanFawB30T3UseInfoView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_pjyh, R.string.can_ktgl, R.string.can_total_mile, R.string.can_range_xhlc, R.string.can_sycdsj};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mDriveInfo = new CanDataInfo.FawB30T3_DriveInfo();
    }

    public void ResetData(boolean check) {
        CanJni.FawB30T3GetDriveInfo(this.mDriveInfo);
        if (!i2b(this.mDriveInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDriveInfo.Update)) {
            this.mDriveInfo.Update = 0;
            if (this.mDriveInfo.Pjdh < 0 || this.mDriveInfo.Pjdh > 1000) {
                updateItem(0, 0, "-.-");
            } else {
                updateItem(0, this.mDriveInfo.Pjdh, String.valueOf(((float) this.mDriveInfo.Pjdh) * 0.1f) + " KW-H/100HM");
            }
            if (this.mDriveInfo.Ktgl != 16777215) {
                updateItem(1, this.mDriveInfo.Ktgl, String.valueOf(this.mDriveInfo.Ktgl) + " W");
            } else {
                updateItem(1, 0, "-.-");
            }
            if (this.mDriveInfo.Zlc != -1) {
                updateItem(2, this.mDriveInfo.Zlc, String.format("%.1f KM", new Object[]{Float.valueOf(((float) this.mDriveInfo.Zlc) * 0.1f)}));
            } else {
                updateItem(2, 0, "-.-");
            }
            if (this.mDriveInfo.Xhlc < 0 || this.mDriveInfo.Xhlc > 999) {
                updateItem(3, 0, "-.-");
            } else {
                updateItem(3, this.mDriveInfo.Xhlc, String.valueOf(this.mDriveInfo.Xhlc) + "  KM");
            }
            if (this.mDriveInfo.Sycdsj < 0 || this.mDriveInfo.Sycdsj > 100) {
                updateItem(4, 0, "-.-");
            } else {
                updateItem(4, this.mDriveInfo.Sycdsj, String.valueOf(this.mDriveInfo.Sycdsj * 5) + "  MIN");
            }
        }
    }

    public void QueryData() {
        CanJni.FawB30T3Query(3);
    }
}
