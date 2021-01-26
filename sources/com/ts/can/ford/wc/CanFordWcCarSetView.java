package com.ts.can.ford.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFordWcCarSetView extends CanScrollCarInfoView {
    private CanDataInfo.FordWcCameraSet mCameraSet;
    private CanDataInfo.FordWcCarSetData mCarSet;
    private CanDataInfo.FordWcLightSet mLightSet;
    private CanDataInfo.FordWcSportSet mSportSet;
    private CanDataInfo.FordWcTsMsg mTsMsg;

    public CanFordWcCarSetView(Activity activity) {
        super(activity, 10);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.FordWcCarLightSet(3, item);
                return;
            case 2:
                CanJni.FordWcCarLightSet(5, Neg(item));
                return;
            case 5:
                CanJni.FordWcTsSet(4, item);
                return;
            case 8:
                CanJni.FordWcCarSet(3, item, 0, 0);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.FordWcCarLightSet(4, Neg(this.mLightSet.Hjd));
                return;
            case 3:
                CanJni.FordWcCarLightSet(6, Neg(this.mLightSet.Xxts));
                return;
            case 4:
                CanJni.FordWcCarLightSet(7, Neg(this.mLightSet.Jgts));
                return;
            case 6:
                CanJni.FordWcCarSportSet(3, Neg(this.mSportSet.Qylkz));
                return;
            case 7:
                CanJni.FordWcCarCameraSet(6, Neg(this.mCameraSet.Sxtys));
                return;
            case 9:
                CanJni.FordWcTsSet(6, Neg(this.mTsMsg.Ldfmq));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_turning_lamp, R.string.can_environment_light, R.string.can_lcdw, R.string.can_msg_tip, R.string.can_warn_tip, R.string.can_temperature, R.string.can_traction_control_sys, R.string.can_camera_delay, R.string.can_fwd_color, R.string.can_fmqkg};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.array.can_fist_zxd};
        this.mPopValueIds[2] = new int[]{R.array.can_fist_l_c};
        this.mPopValueIds[5] = new int[]{R.array.can_psa_wc_temperature_array};
        this.mPopValueIds[8] = new int[]{R.string.can_off, R.string.can_cold_blue, R.string.can_orange_color, R.string.can_soft_blue, R.string.can_color_red, R.string.can_magoten_light_color_2, R.string.can_magoten_light_color_3, R.string.can_purple};
        this.mTsMsg = new CanDataInfo.FordWcTsMsg();
        this.mLightSet = new CanDataInfo.FordWcLightSet();
        this.mSportSet = new CanDataInfo.FordWcSportSet();
        this.mCameraSet = new CanDataInfo.FordWcCameraSet();
        this.mCarSet = new CanDataInfo.FordWcCarSetData();
    }

    public void ResetData(boolean check) {
        CanJni.FordWcGetLightSet(this.mLightSet);
        if (i2b(this.mLightSet.UpdateOnce) && (!check || i2b(this.mLightSet.Update))) {
            this.mLightSet.Update = 0;
            updateItem(0, this.mLightSet.Zxdss);
            updateItem(1, this.mLightSet.Hjd);
            updateItem(2, Neg(this.mLightSet.Dldw));
            updateItem(3, this.mLightSet.Xxts);
            updateItem(4, this.mLightSet.Jgts);
        }
        CanJni.FordWcGetTsMsg(this.mTsMsg);
        if (i2b(this.mTsMsg.UpdateOnce) && (!check || i2b(this.mTsMsg.Update))) {
            this.mTsMsg.Update = 0;
            updateItem(5, this.mTsMsg.Wddw);
            updateItem(9, this.mTsMsg.Ldfmq);
        }
        CanJni.FordWcGetSportSet(this.mSportSet);
        if (i2b(this.mSportSet.UpdateOnce) && (!check || i2b(this.mSportSet.Update))) {
            this.mSportSet.Update = 0;
            updateItem(6, this.mSportSet.Qylkz);
        }
        CanJni.FordWcGetCameraSet(this.mCameraSet);
        if (i2b(this.mCameraSet.UpdateOnce) && (!check || i2b(this.mCameraSet.Update))) {
            this.mCameraSet.Update = 0;
            updateItem(7, this.mCameraSet.Sxtys);
        }
        CanJni.FordWcGetCarSet(this.mCarSet);
        if (!i2b(this.mCarSet.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarSet.Update)) {
            this.mCarSet.Update = 0;
            updateItem(8, this.mCarSet.Fwd);
        }
    }

    public void QueryData() {
        CanJni.FordWcQuery(5, 1, 104);
        Sleep(5);
        CanJni.FordWcQuery(5, 1, 108);
        Sleep(5);
        CanJni.FordWcQuery(5, 1, 133);
        Sleep(5);
        CanJni.FordWcQuery(5, 1, Can.CAN_FLAT_WC);
        Sleep(5);
        CanJni.FordWcQuery(5, 1, 97);
    }
}
