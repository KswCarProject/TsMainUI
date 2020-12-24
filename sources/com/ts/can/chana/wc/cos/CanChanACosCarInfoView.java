package com.ts.can.chana.wc.cos;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanChanACosCarInfoView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick, CanItemMsgBox.onMsgBoxClick2 {
    public static final int COS_HFCCSZ = 2;

    public CanChanACosCarInfoView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 2) {
            new CanItemMsgBox(2, getActivity(), R.string.can_sure_setup, this, this);
        } else {
            enterSubWin(CanCarInfoSub1Activity.class, id);
        }
    }

    public void onOK(int param) {
        if (param == 2) {
            CanJni.ChanAWcCos1CarSet(17, 1);
        }
    }

    public void onCancel(int param) {
        if (param == 2) {
            CanJni.ChanAWcCos1CarSet(17, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_info, R.string.can_tyres_tpms, R.string.can_hfcssz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
