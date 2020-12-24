package com.ts.can.fiat.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFiatRzcCarSetView extends CanScrollCarInfoView {
    private static final int ITEM_BEEP = 1;
    private static final int ITEM_CARTYPE = 2;
    private static final int ITEM_DISPLAY_TRIPB = 0;
    private static final int ITEM_MAX = 3;
    CanDataInfo.FlatRzcSetData mSetData;

    public CanFiatRzcCarSetView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.FiatRzcCarSet(Can.CAN_BYD_M6_DJ, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.FiatRzcCarSet(240, Neg(this.mSetData.DisplayTripB));
                return;
            case 1:
                CanJni.FiatRzcCarSet(Can.CAN_MZD_TXB, Neg(this.mSetData.Fmq));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_tripb_disp, R.string.can_beep_sta, R.string.can_car_auto};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[2] = new int[]{R.string.can_num_1, R.string.can_num_2};
        this.mSetData = new CanDataInfo.FlatRzcSetData();
    }

    public void ResetData(boolean check) {
        CanJni.FiatRzcGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.DisplayTripB);
            updateItem(1, this.mSetData.Fmq);
            updateItem(2, this.mSetData.Cxpz);
        }
    }

    public void QueryData() {
        CanJni.FiatRzcQuery(128, 240);
        Sleep(5);
        CanJni.FiatRzcQuery(128, Can.CAN_MZD_TXB);
        Sleep(5);
        CanJni.FiatRzcQuery(128, Can.CAN_BYD_M6_DJ);
        Sleep(5);
    }
}
