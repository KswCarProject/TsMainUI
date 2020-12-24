package com.ts.can.gac.trumpchi_wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanTrumpchiWcAttachSetView extends CanScrollCarInfoView {
    private CanDataInfo.GCWcCarSet mCarSet;

    public CanTrumpchiWcAttachSetView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            CanJni.TrumpchiWcCarSet(12, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.TrumpchiWcCarSet(13, Neg(this.mCarSet.Csss));
                return;
            case 2:
                CanJni.TrumpchiWcCarSet(14, Neg(this.mCarSet.Zdjs));
                return;
            case 3:
                CanJni.TrumpchiWcCarSet(15, Neg(this.mCarSet.Ykzqchtc));
                return;
            case 4:
                CanJni.TrumpchiWcCarSet(16, Neg(this.mCarSet.Qygwhgn));
                return;
            case 5:
                CanJni.TrumpchiWcCarSet(17, Neg(this.mCarSet.Hygddzdgsgn));
                return;
            case 6:
                CanJni.TrumpchiWcCarSet(25, Neg(this.mCarSet.Whsjzdzd));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_ykjs, R.string.can_csss, R.string.can_zdjs, R.string.can_ykqctc, R.string.can_ygwh, R.string.can_ddzdhyg, R.string.can_hsjzdzd};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.string.can_door_unlock_key2, R.string.can_door_unlock_key1};
        this.mCarSet = new CanDataInfo.GCWcCarSet();
    }

    public void ResetData(boolean check) {
        CanJni.TrumpchiWcGetCarSet(this.mCarSet);
        if (!i2b(this.mCarSet.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarSet.Update)) {
            this.mCarSet.Update = 0;
            updateItem(0, this.mCarSet.Ykjs);
            updateItem(1, this.mCarSet.Csss);
            updateItem(2, this.mCarSet.Zdjs);
            updateItem(3, this.mCarSet.Ykzqchtc);
            updateItem(4, this.mCarSet.Qygwhgn);
            updateItem(5, this.mCarSet.Hygddzdgsgn);
            updateItem(6, this.mCarSet.Whsjzdzd);
        }
    }

    public void QueryData() {
    }
}
