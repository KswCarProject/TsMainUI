package com.ts.can.cc.dj.hf;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHfDjCarStatusView extends CanScrollCarInfoView {
    private CanDataInfo.CcHfDj_CarInfo mCarInfo;
    private String[] mStrings;

    public CanHfDjCarStatusView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_air_pressure, R.string.can_rpm_temp, R.string.can_bsqyw, R.string.can_xdcdy, R.string.can_cch9_front_wheel_torque_ratio, R.string.can_cch9_trailer};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        Resources resources = getActivity().getResources();
        this.mStrings = new String[2];
        this.mStrings[0] = resources.getString(R.string.can_cch9_trailer_key1);
        this.mStrings[1] = resources.getString(R.string.can_cch9_trailer_key2);
        this.mCarInfo = new CanDataInfo.CcHfDj_CarInfo();
    }

    public void ResetData(boolean check) {
        CanJni.CcHfDjGetCarInfo(this.mCarInfo);
        if (!i2b(this.mCarInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarInfo.Update)) {
            this.mCarInfo.Update = 0;
            updateItem(0, 0, String.valueOf(this.mCarInfo.Dqyl) + " hPA");
            updateItem(1, 0, String.valueOf(this.mCarInfo.Fdjsw) + " ℃");
            updateItem(2, 0, String.valueOf(this.mCarInfo.Bsqyw) + " ℃");
            updateItem(3, 0, String.valueOf(((float) this.mCarInfo.Xdcdy) * 0.1f) + " v");
            updateItem(4, 0, String.valueOf(this.mCarInfo.Qlnjpb) + " %");
            if (this.mCarInfo.Tc < 2 && this.mCarInfo.Tc >= 0) {
                updateItem(5, 0, this.mStrings[this.mCarInfo.Tc]);
            }
        }
    }

    public void QueryData() {
        CanJni.CcHfDjQuery(16);
    }
}
