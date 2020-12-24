package com.ts.can.bmw.x1.wc;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanBMWX1WcDriveComputeView extends CanScrollCarInfoView {
    private CanDataInfo.BmwX1Wc_Pc mPcInfos;

    public CanBMWX1WcDriveComputeView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 3) {
            new CanItemMsgBox(id, getActivity(), R.string.can_cmp_reset_notice, new CanItemMsgBox.onMsgBoxClick() {
                public void onOK(int param) {
                    CanJni.BmwX1WcPcSet(1, 0, 0);
                }
            });
        } else if (id == 4) {
            new CanItemMsgBox(id, getActivity(), R.string.can_cmp_reset_notice, new CanItemMsgBox.onMsgBoxClick() {
                public void onOK(int param) {
                    CanJni.BmwX1WcPcSet(2, 0, 0);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_consumption, R.string.can_range_xhlc, R.string.can_avg_speed, R.string.can_avg_speed_reset, R.string.can_avg_oil_reset};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
        this.mPcInfos = new CanDataInfo.BmwX1Wc_Pc();
    }

    public void ResetData(boolean check) {
        CanJni.BmwX1WcGetPcInfo(this.mPcInfos);
        Log.d("HAHA", "mPcInfos.Update = " + this.mPcInfos.Update);
        if (!i2b(this.mPcInfos.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mPcInfos.Update)) {
            this.mPcInfos.Update = 0;
            Log.d("HAHA", "mPcInfos.Fule = " + this.mPcInfos.Fule);
            if (this.mPcInfos.Fule > 4000) {
                updateItem(0, 0, "--");
            } else {
                updateItem(0, 0, String.format("%.1f L/100km", new Object[]{Float.valueOf(((float) this.mPcInfos.Fule) / 10.0f)}));
            }
            if (this.mPcInfos.Xhlc > 4000) {
                updateItem(1, 0, "--");
            } else {
                updateItem(1, 0, String.valueOf(this.mPcInfos.Xhlc) + " km");
            }
            if (this.mPcInfos.Pjcs > 2600) {
                updateItem(2, 0, "--");
                return;
            }
            updateItem(2, 0, String.format("%.1f km/h", new Object[]{Float.valueOf(((float) this.mPcInfos.Pjcs) / 10.0f)}));
        }
    }

    public void QueryData() {
    }
}
