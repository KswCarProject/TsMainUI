package com.ts.can.chrysler.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanChryslerWcCarLockSetView extends CanScrollCarInfoView {
    private CanDataInfo.ChrWcDoorLock mDoorADT;
    private CanDataInfo.ChrWcDoorLock mDoorData;

    public CanChryslerWcCarLockSetView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.ChryslerWcDoorLockSet(6, item);
                return;
            case 3:
                CanJni.ChryslerWcDoorLockSet(3, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.ChryslerWcDoorLockSet(5, Neg(this.mDoorData.Cmbj));
                return;
            case 2:
                CanJni.ChryslerWcDoorLockSet(4, Neg(this.mDoorData.Wysjr));
                return;
            case 4:
                CanJni.ChryslerWcDoorLockSet(2, Neg(this.mDoorData.Xcszdjs));
                return;
            case 5:
                CanJni.ChryslerWcDoorLockSet(1, Neg(this.mDoorData.Zdcmsd));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_scsfctsy, R.string.can_jp_cmbj, R.string.can_wysjr, R.string.can_ycjs, R.string.can_xcszdjs, R.string.can_zdcmsd};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.string.can_autodooroff, R.string.can_scsfctsy_1press, R.string.can_scsfctsy_2press};
        this.mPopValueIds[3] = new int[]{R.string.can_remotedoor_driver, R.string.can_remotedoor_all};
        this.mDoorADT = new CanDataInfo.ChrWcDoorLock();
        this.mDoorData = new CanDataInfo.ChrWcDoorLock();
    }

    public void ResetData(boolean check) {
        CanJni.ChryslerWcGetDoorLock(this.mDoorADT, 0);
        CanJni.ChryslerWcGetDoorLock(this.mDoorData, 1);
        if (i2b(this.mDoorADT.UpdateOnce) && (!check || i2b(this.mDoorADT.Update))) {
            this.mDoorADT.Update = 0;
            showItem(new int[]{this.mDoorADT.Scsfctsy, this.mDoorADT.Cmbj, this.mDoorADT.Wysjr, this.mDoorADT.Ycjs, this.mDoorADT.Xcszdjs, this.mDoorADT.Zdcmsd});
        }
        if (!i2b(this.mDoorData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDoorData.Update)) {
            this.mDoorData.Update = 0;
            updateItem(new int[]{this.mDoorData.Scsfctsy, this.mDoorData.Cmbj, this.mDoorData.Wysjr, this.mDoorData.Ycjs, this.mDoorData.Xcszdjs, this.mDoorData.Zdcmsd});
        }
    }

    public void QueryData() {
        CanJni.ChryslerWcQuery(1, 96);
    }
}
