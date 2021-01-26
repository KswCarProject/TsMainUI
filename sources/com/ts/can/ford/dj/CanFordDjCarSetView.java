package com.ts.can.ford.dj;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFordDjCarSetView extends CanScrollCarInfoView {
    private static final int ITEM_JGTS = 2;
    private static final int ITEM_LCDW = 0;
    private static final int ITEM_XXTS = 3;
    private static final int ITEM_ZXDSSZS = 1;
    private CanDataInfo.FordDjSetData mSetData;

    public CanFordDjCarSetView(Activity activity) {
        super(activity, 4);
    }

    private int GetBSet() {
        int temp = 0;
        if (this.mSetData.Lcdw > 0) {
            temp = 0 | 128;
        }
        if (this.mSetData.Zxdsszs > 0) {
            temp |= 64;
        }
        if (this.mSetData.Jgts > 0) {
            temp |= 32;
        }
        if (this.mSetData.Xxts > 0) {
            return temp | 16;
        }
        return temp;
    }

    public void onItem(int id, int item) {
        int temp = GetBSet();
        switch (id) {
            case 0:
                int temp2 = temp & 127;
                if (item == 1) {
                    temp2 |= 128;
                }
                CanJni.FordDjCarSet(temp2);
                return;
            case 1:
                int temp3 = temp & 191;
                if (item == 1) {
                    temp3 |= 64;
                }
                CanJni.FordDjCarSet(temp3);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        int temp = GetBSet();
        switch (id) {
            case 2:
                int temp2 = temp & Can.CAN_X80_RZC;
                if (this.mSetData.Jgts == 0) {
                    temp2 |= 32;
                }
                CanJni.FordDjCarSet(temp2);
                return;
            case 3:
                int temp3 = temp & Can.CAN_GM_CAPTIVA_OD;
                if (this.mSetData.Xxts == 0) {
                    temp3 |= 16;
                }
                CanJni.FordDjCarSet(temp3);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_lcdw, R.string.can_zxdsszs, R.string.can_warn_tip, R.string.can_msg_tip};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.string.can_service_distance_km, R.string.can_service_distance_mi};
        this.mPopValueIds[1] = new int[]{R.string.can_1c, R.string.can_3c};
        this.mSetData = new CanDataInfo.FordDjSetData();
    }

    public void ResetData(boolean check) {
        CanJni.FordDjGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Lcdw, this.mSetData.Zxdsszs, this.mSetData.Jgts, this.mSetData.Xxts});
        }
    }

    public void QueryData() {
        CanJni.FordDjQuery(3);
    }
}
