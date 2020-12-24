package com.ts.can.gm.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGMWcVehicleInfoView extends CanScrollCarInfoView {
    private CanDataInfo.GmWc_CompassData mCompassData;
    private CanDataInfo.CAN_Msg mMsgData;

    public CanGMWcVehicleInfoView(Activity activity) {
        super(activity, 14);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_info, R.string.can_rpm, R.string.can_speed, R.string.can_battery, R.string.can_jie_qi_men, R.string.can_rest_oil, R.string.can_lqywd, R.string.can_oil_pressure, R.string.can_air_pressure, R.string.can_rest_oil_percent, R.string.can_compass, R.string.can_mzd_cx4_drive_jiaozhun, R.string.can_zone, R.string.can_angle};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mMsgData = new CanDataInfo.CAN_Msg();
        this.mCompassData = new CanDataInfo.GmWc_CompassData();
        this.mItemVisibles[10] = 0;
        this.mItemVisibles[11] = 0;
        this.mItemVisibles[12] = 0;
        this.mItemVisibles[13] = 0;
    }

    public void ResetData(boolean check) {
        CanJni.GetCarInfo(this.mMsgData);
        if (i2b(this.mMsgData.UpdateOnce) && (!check || i2b(this.mMsgData.Update))) {
            this.mMsgData.Update = 0;
            if (this.mMsgData.Rpm == 65535) {
                updateItem(1, 0, " --");
            } else {
                updateItem(1, 0, String.valueOf(this.mMsgData.Rpm) + " rpm");
            }
            if (this.mMsgData.Speed == 65535) {
                updateItem(2, 0, String.valueOf(this.mMsgData.Speed) + " --");
            } else {
                updateItem(2, 0, String.valueOf(this.mMsgData.Speed) + " km/h");
            }
            updateItem(3, 0, String.valueOf(((float) this.mMsgData.BatV) * 0.1f) + " V");
            if (this.mMsgData.Jqmwz == 255) {
                updateItem(4, 0, "-");
            } else if (this.mMsgData.Jqmwz >= 0 && this.mMsgData.Jqmwz <= 100) {
                updateItem(4, 0, String.valueOf(this.mMsgData.Jqmwz) + "%");
            }
            updateItem(5, 0, String.valueOf(this.mMsgData.Syyl) + " L");
            updateItem(6, 0, String.valueOf(this.mMsgData.Lqywd - 40) + " °C");
            if (this.mMsgData.Jyyl == 65535) {
                updateItem(7, 0, String.valueOf(this.mMsgData.Jyyl) + " --");
            } else {
                updateItem(7, 0, String.valueOf(this.mMsgData.Jyyl) + " KPa");
            }
            updateItem(8, 0, String.valueOf(this.mMsgData.Dqyl) + " Pa");
            updateItem(9, 0, String.valueOf(this.mMsgData.Syyw) + "%");
        }
        CanJni.GmWcGetCarCompassInfo(this.mCompassData);
        if (!i2b(this.mCompassData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCompassData.Update)) {
            this.mCompassData.Update = 0;
            showItem(10, this.mCompassData.Vaild);
            showItem(11, this.mCompassData.Vaild);
            showItem(12, this.mCompassData.Vaild);
            showItem(13, this.mCompassData.Vaild);
            if (i2b(this.mCompassData.Calibration)) {
                updateItem(11, 0, getActivity().getString(R.string.can_zzjz));
            } else {
                updateItem(11, 0, getActivity().getString(R.string.can_fjz));
            }
            updateItem(12, 0, new StringBuilder().append(this.mCompassData.Area).toString());
            updateItem(13, 0, String.valueOf(((float) this.mCompassData.Angle) * 1.5f) + "°");
        }
    }

    public void QueryData() {
        CanJni.GmWcCarQuery(5, 1, 105);
        Sleep(5);
        CanJni.GmWcCarQuery(5, 1, 50);
    }
}
