package com.ts.can.saic.t60_rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanDtT60RzcCarInfoView extends CanScrollCarInfoView {
    public CanDtT60RzcCarInfoView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 0) {
            enterSubWin(CanDtT60RzcCarInfoActivity.class);
        } else {
            enterSubWin(CanCarInfoSub1Activity.class, id);
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_info, R.string.can_tpms};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        if (CanJni.GetSubType() != 3) {
            this.mItemVisibles[1] = 0;
        }
    }

    public void doOnResume() {
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
