package com.ts.can.swm.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanSwmRzcCarSetView extends CanScrollCarInfoView {
    public static final int ITEM_BWHJSJSZ = 0;
    public static final int ITEM_HFMQJC = 1;
    public static final int ITEM_KMYJ = 3;
    public static final int ITEM_MAX = 6;
    public static final int ITEM_YDWTYJ = 2;
    public static final int ITEM_ZTGLKG = 5;
    public static final int ITEM_ZTSZ = 4;
    private CanDataInfo.SwmRzc_Set mSetData;

    public CanSwmRzcCarSetView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.SwmRzcCarSet(12, item);
                return;
            case 4:
                CanJni.SwmRzcCarSet(8, item);
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
                CanJni.SwmRzcCarSet(5, Neg(this.mSetData.Hfmqjc));
                return;
            case 2:
                CanJni.SwmRzcCarSet(3, Neg(this.mSetData.Ydwtyc));
                return;
            case 3:
                CanJni.SwmRzcCarSet(4, Neg(this.mSetData.Kmyj));
                return;
            case 5:
                CanJni.SwmRzcCarSet(14, Neg(this.mSetData.Ztglkg));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_dgsjkz_bwhj, R.string.can_hfmqjc, R.string.can_ydwtyj, R.string.can_kmyj, R.string.can_ztsz, R.string.can_ztglkg};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.string.can_off, R.string.can_15s, R.string.can_30s, R.string.can_time_45s, R.string.can_60s};
        this.mPopValueIds[4] = new int[]{R.string.can_color_kjl, R.string.can_color_qwh, R.string.can_color_jdh};
        this.mSetData = new CanDataInfo.SwmRzc_Set();
    }

    public void ResetData(boolean check) {
        CanJni.SwmRzcGetSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Bwhjsjsz);
            updateItem(1, this.mSetData.Hfmqjc);
            updateItem(2, this.mSetData.Ydwtyc);
            updateItem(3, this.mSetData.Kmyj);
            updateItem(4, this.mSetData.Ztsz);
            updateItem(5, this.mSetData.Ztglkg);
        }
    }

    public void QueryData() {
        CanJni.SwmRzcQuery(65, 0);
    }
}
