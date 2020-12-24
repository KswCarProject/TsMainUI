package com.ts.can.jac.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanJACRefineWcCarInfoView extends CanScrollCarInfoView {
    public CanJACRefineWcCarInfoView(Activity activity) {
        super(activity, 6);
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
        int id = ((Integer) v.getTag()).intValue();
        if (id == 1) {
            enterSubWin(CanJACRefineWcTpmsActivity.class);
        } else {
            enterSubWin(CanCarInfoSub1Activity.class, id);
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_type_select, R.string.can_tmps, R.string.can_mzd_cx4_drive, R.string.can_jac_nlxx, R.string.can_cdzt, R.string.can_car_info};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CAR_TYPE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[0] = new int[]{R.array.can_fs_declare_110};
    }

    public void doOnResume() {
        updateItem(0, CanJni.GetSubType());
    }

    public void ResetData(boolean check) {
        if (CanJni.GetSubType() != 1) {
            showItem(2, 0);
            showItem(3, 0);
            showItem(4, 0);
        }
        if (CanJni.GetSubType() != 2) {
            showItem(5, 0);
        }
    }

    public void QueryData() {
    }
}
