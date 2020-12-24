package com.ts.can.nissan.wc.rich6;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanNissanRich6WcCarSetView extends CanScrollCarInfoView {
    public static final String TAG = "CanNissanRich6WcCarInfoView";
    private CanDataInfo.NissanRich6Wc_SetData mSetData;

    public CanNissanRich6WcCarSetView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.NissanRiChWcCarSet(1, Neg(this.mSetData.Cszdbs), 255, 255);
                return;
            case 1:
                CanJni.NissanRiChWcCarSet(2, Neg(this.mSetData.Xhzdjs), 255, 255);
                return;
            case 2:
                CanJni.NissanRiChWcCarSet(3, Neg(this.mSetData.Bwhj), 255, 255);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mSetData = new CanDataInfo.NissanRich6Wc_SetData();
        this.mItemTitleIds = new int[]{R.string.can_xczdls, R.string.can_xhzdjs, R.string.can_bwhj};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK};
    }

    public void ResetData(boolean check) {
        CanJni.NissanRiChWcGetSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Cszdbs);
            updateItem(1, this.mSetData.Xhzdjs);
            updateItem(2, this.mSetData.Bwhj);
        }
    }

    public void QueryData() {
        CanJni.NissanRiChWcQuery(5, 1, 97);
    }
}
