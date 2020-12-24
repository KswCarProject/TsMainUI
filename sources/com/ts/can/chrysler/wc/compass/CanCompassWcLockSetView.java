package com.ts.can.chrysler.wc.compass;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanCompassWcLockSetView extends CanScrollCarInfoView {
    private CanDataInfo.ChrWcDoorLock mLockAdt;
    private CanDataInfo.ChrWcDoorLock mLockData;

    public CanCompassWcLockSetView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.ChryslerWcDoorLockSet(3, item);
                return;
            case 4:
                CanJni.ChryslerWcDoorLockSet(6, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.ChryslerWcDoorLockSet(1, Neg(this.mLockData.Zdcmsd));
                return;
            case 1:
                CanJni.ChryslerWcDoorLockSet(2, Neg(this.mLockData.Xcszdjs));
                return;
            case 3:
                CanJni.ChryslerWcDoorLockSet(4, Neg(this.mLockData.Wysjr));
                return;
            case 5:
                CanJni.ChryslerWcDoorLockSet(7, Neg(this.mLockData.Ycqdtsy));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_zdcmsd, R.string.can_xcszdjs, R.string.can_sccysjs, R.string.can_wysjr, R.string.can_scsfctsy, R.string.can_jeep_znz_ycqdtsy};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[2] = new int[]{R.string.can_remotedoor_driver, R.string.can_remotedoor_all};
        this.mPopValueIds[4] = new int[]{R.string.can_autodooroff, R.string.can_scsfctsy_1press, R.string.can_scsfctsy_2press};
        this.mLockAdt = new CanDataInfo.ChrWcDoorLock();
        this.mLockData = new CanDataInfo.ChrWcDoorLock();
    }

    public void ResetData(boolean check) {
        CanJni.ChryslerWcGetDoorLock(this.mLockAdt, 0);
        CanJni.ChryslerWcGetDoorLock(this.mLockData, 1);
        if (i2b(this.mLockAdt.UpdateOnce) && (!check || i2b(this.mLockAdt.Update))) {
            this.mLockAdt.Update = 0;
            showItem(new int[]{this.mLockAdt.Zdcmsd, this.mLockAdt.Xcszdjs, this.mLockAdt.Ycjs, this.mLockAdt.Wysjr, this.mLockAdt.Scsfctsy, this.mLockAdt.Ycqdtsy});
        }
        if (!i2b(this.mLockData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mLockData.Update)) {
            this.mLockData.Update = 0;
            updateItem(new int[]{this.mLockData.Zdcmsd, this.mLockData.Xcszdjs, this.mLockData.Ycjs, this.mLockData.Wysjr, this.mLockData.Scsfctsy, this.mLockData.Ycqdtsy});
        }
    }

    public void QueryData() {
        CanJni.ChryslerWcQuery(1, 96);
    }
}
