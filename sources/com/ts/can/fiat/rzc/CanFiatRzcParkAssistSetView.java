package com.ts.can.fiat.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFiatRzcParkAssistSetView extends CanScrollCarInfoView {
    private static final int ITEM_ACTIVE_REAR_VIEW_CAMERA_GUI = 5;
    private static final int ITEM_BRAKE_CONTROL = 2;
    private static final int ITEM_BRAKE_CONTROL_SENS = 3;
    private static final int ITEM_MAX = 7;
    private static final int ITEM_PARK_SENSE = 0;
    private static final int ITEM_RAIN_SENS_WIPERS = 6;
    private static final int ITEM_REAR_PARKSENSE_VOL = 1;
    private static final int ITEM_REAR_VIEW_CAMREA_DELAY = 4;
    CanDataInfo.FlatRzcSetData mSetData;

    public CanFiatRzcParkAssistSetView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.FiatRzcCarSet(48, item);
                return;
            case 1:
                CanJni.FiatRzcCarSet(49, item);
                return;
            case 2:
                CanJni.FiatRzcCarSet(50, item);
                return;
            case 3:
                CanJni.FiatRzcCarSet(51, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 4:
                CanJni.FiatRzcCarSet(52, Neg(this.mSetData.RearViewCamera));
                return;
            case 5:
                CanJni.FiatRzcCarSet(53, Neg(this.mSetData.ActiveRearViewCameraGuidelines));
                return;
            case 6:
                CanJni.FiatRzcCarSet(54, Neg(this.mSetData.SensingWipers));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_psbcld, R.string.can_hpsyl, R.string.can_brake_control, R.string.can_brake_control_sens, R.string.can_rearview_camera_delay, R.string.can_active_rear_view_camera_guidelines, R.string.can_jp_ylgysyg};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK};
        this.mPopValueIds[0] = new int[]{R.string.can_type_vol, R.string.can_vol_img};
        this.mPopValueIds[1] = new int[]{R.string.can_cdjd, R.string.can_cdzj, R.string.can_cdjg};
        this.mPopValueIds[2] = new int[]{R.string.can_off, R.string.can_only_act_braking, R.string.can_warn_act_brk};
        this.mPopValueIds[3] = new int[]{R.string.can_sdqfwxjgjl_3, R.string.can_sdqfwxjgjl_2, R.string.can_sdqfwxjgjl_1};
        this.mSetData = new CanDataInfo.FlatRzcSetData();
    }

    public void ResetData(boolean check) {
        CanJni.FiatRzcGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.ParkSense);
            updateItem(1, this.mSetData.RearParkSenseVol);
            updateItem(2, this.mSetData.BrakeControl);
            updateItem(3, this.mSetData.BrakeControlSensitivity);
            updateItem(4, this.mSetData.RearViewCamera);
            updateItem(5, this.mSetData.ActiveRearViewCameraGuidelines);
            updateItem(6, this.mSetData.SensingWipers);
        }
    }

    public void QueryData() {
        CanJni.FiatRzcQuery(128, 48);
        Sleep(5);
        CanJni.FiatRzcQuery(128, 49);
        Sleep(5);
        CanJni.FiatRzcQuery(128, 50);
        Sleep(5);
        CanJni.FiatRzcQuery(128, 51);
        Sleep(5);
        CanJni.FiatRzcQuery(128, 52);
        Sleep(5);
        CanJni.FiatRzcQuery(128, 53);
        Sleep(5);
        CanJni.FiatRzcQuery(128, 54);
        Sleep(5);
    }
}
