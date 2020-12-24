package com.ts.can.psa.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanPSAWCCarTypeView extends CanScrollCarInfoView {
    public CanPSAWCCarTypeView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            FtSet.SetCanSubT(item);
            Mcu.SendXKey(20);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        enterSubWin(CanCarInfoSub1Activity.class, ((Integer) v.getTag()).intValue());
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_type_select};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CAR_TYPE};
        this.mPopValueIds[0] = new int[]{R.array.can_fs_declare_148};
    }

    public void ResetData(boolean check) {
        if (!check) {
            updateItem(0, CanJni.GetSubType());
        }
    }

    public void QueryData() {
    }
}
