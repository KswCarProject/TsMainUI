package com.ts.can.ford.wc.mondeo;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFordMondeoWcCarSetView extends CanScrollCarInfoView {
    private CanDataInfo.FordWcMondeo2013SetData mMsgData;

    public CanFordMondeoWcCarSetView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.FordWcMondeo2013CarSet(3, item, 0, 0);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_fwd_color};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_trunk_close, R.string.can_cold_blue, R.string.can_orange_color, R.string.can_soft_blue, R.string.can_color_red, R.string.can_magoten_light_color_2, R.string.can_color_blue, R.string.can_purple};
        this.mMsgData = new CanDataInfo.FordWcMondeo2013SetData();
    }

    public void ResetData(boolean check) {
        CanJni.FordWcMondeo2013GetCarSet(this.mMsgData);
        if (!i2b(this.mMsgData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mMsgData.Update)) {
            this.mMsgData.Update = 0;
            updateItem(new int[]{this.mMsgData.Fwd});
        }
    }

    public void QueryData() {
        CanJni.FordWcMondeo2013Query(1, 104);
    }
}
