package com.ts.can.ht.x7;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHtX5LightSetView extends CanScrollCarInfoView {
    private static final int ITEM_FWDLD = 0;
    private static final int ITEM_FWDYS = 1;

    public CanHtX5LightSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.HanTRzcCarSet(1, item);
                return;
            case 1:
                CanJni.HanTRzcCarSet(2, item);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
    }

    public void onChanged(int id, int pos) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mItemTitleIds = new int[]{R.string.can_fwdlddj, R.string.can_fwd_color};
        this.mPopValueIds[0] = new int[]{R.array.can_hant_fwd_num};
        this.mPopValueIds[1] = new int[]{R.array.can_hant_fwd_num};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
