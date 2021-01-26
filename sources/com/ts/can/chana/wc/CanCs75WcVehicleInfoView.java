package com.ts.can.chana.wc;

import android.app.Activity;
import android.view.View;
import com.android.SdkConstants;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanCs75WcVehicleInfoView extends CanScrollCarInfoView {
    private CanDataInfo.CAN_Msg mMsgData;

    public CanCs75WcVehicleInfoView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_rpm, R.string.can_speed, R.string.can_battery, R.string.can_jie_qi_men, R.string.can_rest_oil, R.string.can_lqywd, R.string.can_oil_pressure};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mMsgData = new CanDataInfo.CAN_Msg();
    }

    public void ResetData(boolean check) {
        CanJni.GetCarInfo(this.mMsgData);
        if (!i2b(this.mMsgData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mMsgData.Update)) {
            this.mMsgData.Update = 0;
            if (this.mMsgData.Rpm == 65535) {
                updateItem(0, 0, " --");
            } else {
                updateItem(0, 0, String.valueOf(this.mMsgData.Rpm) + " rpm");
            }
            if (this.mMsgData.Speed == 65535) {
                updateItem(1, 0, String.valueOf(this.mMsgData.Speed) + " --");
            } else {
                updateItem(1, 0, String.valueOf(this.mMsgData.Speed) + " km/h");
            }
            updateItem(2, 0, String.valueOf(((float) this.mMsgData.BatV) * 0.1f) + " V");
            if (this.mMsgData.Jqmwz == 255) {
                updateItem(3, 0, SdkConstants.RES_QUALIFIER_SEP);
            } else if (this.mMsgData.Jqmwz >= 0 && this.mMsgData.Jqmwz <= 100) {
                updateItem(3, 0, String.valueOf(this.mMsgData.Jqmwz) + "%");
            }
            updateItem(4, 0, String.valueOf(this.mMsgData.Syyl) + " L");
            updateItem(5, 0, String.valueOf((((float) this.mMsgData.Lqywd) * 0.5f) - 40.0f) + " °C");
            if (this.mMsgData.Jyyl == 65535) {
                updateItem(6, 0, String.valueOf(this.mMsgData.Jyyl) + " --");
            } else {
                updateItem(6, 0, String.valueOf(this.mMsgData.Jyyl) + " KPa");
            }
        }
    }

    public void QueryData() {
        CanJni.GmWcCarQuery(5, 1, 50);
    }
}
