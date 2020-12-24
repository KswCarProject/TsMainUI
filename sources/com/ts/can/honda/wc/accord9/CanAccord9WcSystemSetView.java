package com.ts.can.honda.wc.accord9;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanAccord9WcSystemSetView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick {
    public CanAccord9WcSystemSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                new CanItemMsgBox(0, getActivity(), R.string.can_sure_tybd, this);
                return;
            case 1:
                new CanItemMsgBox(1, getActivity(), R.string.can_sure_setup, this);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_tpms_set, R.string.can_hfcssz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }

    public void onOK(int param) {
        switch (param) {
            case 0:
                CanJni.HondaWcTpmsSet(4, 1);
                return;
            case 1:
                CanJni.HondaWcAccord9OtherSet(3, 0);
                return;
            default:
                return;
        }
    }
}
