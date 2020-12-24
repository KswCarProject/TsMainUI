package com.ts.can.df.fg_rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanDFFGRzcCarSetView extends CanScrollCarInfoView {
    private static final int NLHSDJ = 0;
    private static final int TTXSXTKG = 1;
    private CanDataInfo.Dffg_RzcSet mSetData;

    public CanDFFGRzcCarSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.DffgRzcCarSet(1, item);
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
                CanJni.DffgRzcCarSet(2, Neg(this.mSetData.Ttxsxtkg));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_gc_nlhsdj, R.string.can_ttxsxtkg};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.string.can_headlightsens_0, R.string.can_headlightsens_1, R.string.can_headlightsens_2, R.string.can_headlightsens_3};
        this.mSetData = new CanDataInfo.Dffg_RzcSet();
    }

    public void ResetData(boolean check) {
        CanJni.DffgRzcGetSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Nlhsdj, this.mSetData.Ttxsxtkg});
        }
    }

    public void QueryData() {
        CanJni.DffgRzcCarQuery(41);
    }
}
