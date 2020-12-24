package com.ts.can.saic.baojun.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanBaojunCarInfoView extends CanScrollCarInfoView {
    private CanDataInfo.BaoJunWcSet mAdtData;
    private CanDataInfo.BaoJunWcSet mSetData;

    public CanBaojunCarInfoView(Activity activity) {
        super(activity, 2);
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
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.BaoJunWcCarSet(1, Neg(this.mSetData.Hsjzdzd));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_type_select, R.string.can_zdhsjzd};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CAR_TYPE, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.array.can_fs_declare_182};
        this.mSetData = new CanDataInfo.BaoJunWcSet();
        this.mAdtData = new CanDataInfo.BaoJunWcSet();
    }

    public void doOnResume() {
        updateItem(0, CanJni.GetSubType());
    }

    public void ResetData(boolean check) {
        CanJni.BaoJunWcGetCarSet(this.mAdtData, 0);
        CanJni.BaoJunWcGetCarSet(this.mSetData, 1);
        if (i2b(this.mAdtData.UpdateOnce) && (!check || i2b(this.mAdtData.Update))) {
            this.mAdtData.Update = 0;
            showItem(1, this.mAdtData.Hsjzdzd);
        }
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(1, this.mSetData.Hsjzdzd);
        }
    }

    public void QueryData() {
        CanJni.BaoJunWcQuery(5, 1, 135);
    }
}
