package com.ts.can.bmw.mini.fstt;

import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCommonActivity;
import com.ts.canview.RelativeLayoutContainer;

public class CanBmwMiniFsttCarInfoActivity extends CanCommonActivity {
    private RelativeLayoutContainer mContainer;
    private CanDataInfo.BMW_Trip_MINI mTripInfo = new CanDataInfo.BMW_Trip_MINI();
    private TextView mTvOil;
    private TextView mTvRange;
    private TextView mTvSpeed;

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_relative;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mContainer = new RelativeLayoutContainer(this);
        this.mContainer.addImage(Can.CAN_JAC_REFINE_OD, Can.CAN_JAC_REFINE_OD, R.drawable.can_ecu_fuel);
        this.mContainer.addImage(CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, Can.CAN_JAC_REFINE_OD, R.drawable.can_ecu_speed);
        this.mContainer.addImage(CanCameraUI.BTN_LANDWIND_3D_LEFT_UP, Can.CAN_JAC_REFINE_OD, R.drawable.can_ecu_range);
        this.mTvOil = this.mContainer.addText(120, 280, Can.CAN_NISSAN_XFY, 100);
        this.mTvSpeed = this.mContainer.addText(365, 280, Can.CAN_NISSAN_XFY, 100);
        this.mTvRange = this.mContainer.addText(660, 280, Can.CAN_NISSAN_XFY, 100);
        TextView oilUnit = this.mContainer.addText(170, 380);
        TextView speedUnit = this.mContainer.addText(450, 380);
        this.mContainer.setTextStyle(this.mTvOil, 0, 17, -1, 35).setTextStyle(this.mTvSpeed, 0, 17, -1, 35).setTextStyle(this.mTvRange, 0, 17, -1, 35).setTextStyle1(oilUnit, R.string.can_fuels_lkm, -1, 25).setTextStyle1(speedUnit, R.string.can_fuels_kml, -1, 25).setTextStyle1(this.mContainer.addText(760, 380), R.string.can_distance_km, -1, 25);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.BmwMiniGetTrip(this.mTripInfo);
        if (!i2b(this.mTripInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTripInfo.Update)) {
            this.mTripInfo.Update = 0;
            this.mTvOil.setText(String.format("%.1f", new Object[]{Float.valueOf((((float) this.mTripInfo.Consumption) * 1.0f) / 10.0f)}));
            this.mTvSpeed.setText(String.valueOf(this.mTripInfo.Speed));
            this.mTvRange.setText(String.valueOf(this.mTripInfo.Range));
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
