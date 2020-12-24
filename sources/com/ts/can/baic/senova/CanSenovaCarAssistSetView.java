package com.ts.can.baic.senova;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanSenovaCarAssistSetView extends CanScrollCarInfoView {
    public static final int ITEM_CDPLYJ = 0;
    public static final int ITEM_MAX = 2;
    public static final int ITEM_MQYJ = 1;
    private CanDataInfo.SenovaRzc_SetData mSetData;

    public CanSenovaCarAssistSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int temp = 0;
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                if (this.mSetData.Mqyj > 0) {
                    temp = 64;
                }
                if (this.mSetData.Cdplyj == 0) {
                    temp |= 128;
                }
                CanJni.SenovaCameraSet(5, temp, 0);
                return;
            case 1:
                if (this.mSetData.Cdplyj > 0) {
                    temp = 128;
                }
                if (this.mSetData.Mqyj == 0) {
                    temp |= 64;
                }
                CanJni.SenovaCameraSet(5, temp, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_jp_cdpljg, R.string.can_mqtc};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mSetData = new CanDataInfo.SenovaRzc_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.SenovaRzcGetSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Cdplyj);
            updateItem(1, this.mSetData.Mqyj);
        }
    }

    public void QueryData() {
        CanJni.SenovaQuery(39, 0);
    }
}
