package com.ts.can.vw.rzc.golf;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanGolfRzcCarInfoView extends CanScrollCarInfoView {
    public CanGolfRzcCarInfoView(Activity activity) {
        super(activity, 8);
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
        if (id == 7) {
            CanFunc.mLastDriveProfileTick = CanFunc.getTickCount();
            if (CanJni.GetSubType() == 3) {
                enterSubWin(CanCarInfoSub1Activity.class, -7);
            } else if (CanJni.GetSubType() == 4) {
                enterSubWin(CanCarInfoSub1Activity.class, -6);
            } else {
                enterSubWin(CanCarInfoSub1Activity.class, -8);
            }
        } else {
            enterSubWin(CanCarInfoSub1Activity.class, id);
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_type_select, R.string.can_driving_data, R.string.can_conv_consumers, R.string.can_vehi_status, R.string.can_dzxszt, R.string.can_vehi_setup, R.string.can_lang_set, R.string.can_psa_wc_jsms};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CAR_TYPE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[0] = new int[]{R.array.can_fs_declare_2};
        if (CanJni.GetSubType() == 4) {
            this.mItemVisibles[4] = 1;
        } else {
            this.mItemVisibles[4] = 0;
        }
    }

    public void ResetData(boolean check) {
        updateItem(0, CanJni.GetSubType());
    }

    public void QueryData() {
    }

    public void doOnResume() {
        updateItem(0, CanJni.GetSubType());
    }
}
