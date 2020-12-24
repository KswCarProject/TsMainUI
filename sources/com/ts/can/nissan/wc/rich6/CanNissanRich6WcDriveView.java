package com.ts.can.nissan.wc.rich6;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanNissanRich6WcDriveView extends CanScrollCarInfoView {
    public static final String TAG = "CanNissanRich6WcCarInfoView";
    private CanDataInfo.CAN_Msg mCanMsg;

    public CanNissanRich6WcDriveView(Activity activity) {
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
        this.mCanMsg = new CanDataInfo.CAN_Msg();
        this.mItemTitleIds = new int[]{R.string.can_avg_consump, R.string.can_total_mile, R.string.can_avg_speed};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
    }

    public void ResetData(boolean check) {
        CanJni.GetCarInfo(this.mCanMsg);
        if (!i2b(this.mCanMsg.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCanMsg.Update)) {
            this.mCanMsg.Update = 0;
            updateItem(0, 0, String.valueOf(String.format("%.1f", new Object[]{Double.valueOf(((double) this.mCanMsg.AveOil) * 0.1d)})) + "  L/100km");
            if (this.mCanMsg.Distance == 16777215) {
                updateItem(1, 0, "--  km");
            } else {
                updateItem(1, 0, String.valueOf(String.format("%.1f", new Object[]{Double.valueOf(((double) this.mCanMsg.Distance) * 0.1d)})) + "  km");
            }
            updateItem(2, 0, String.valueOf(this.mCanMsg.Speed) + "  KM/h");
        }
    }

    public void QueryData() {
        CanJni.NissanRiChWcQuery(5, 1, 19);
    }
}
