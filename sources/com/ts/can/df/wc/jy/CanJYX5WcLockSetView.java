package com.ts.can.df.wc.jy;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanJYX5WcLockSetView extends CanScrollCarInfoView {
    private CanDataInfo.DfJyX5_Wc_LockSet mLockSet;

    public CanJYX5WcLockSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 0) {
            CanJni.DfJyWcGetCarLockSet(2, Neg(this.mLockSet.Csss));
        } else if (id == 1) {
            CanJni.DfJyWcGetCarLockSet(1, Neg(this.mLockSet.Zdcss));
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_df_jyx5_csss, R.string.can_df_jyx5_zdcss};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mLockSet = new CanDataInfo.DfJyX5_Wc_LockSet();
    }

    public void ResetData(boolean check) {
        CanJni.DfJyWcGetCarLockInfo(this.mLockSet);
        if (!i2b(this.mLockSet.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mLockSet.Update)) {
            this.mLockSet.Update = 0;
            showItem(new int[]{this.mLockSet.AdtCsss, this.mLockSet.AdtZdcss});
            updateItem(new int[]{this.mLockSet.Csss, this.mLockSet.Zdcss});
        }
    }

    public void QueryData() {
        CanJni.DfJyWcGetCarQuery(5, 1, 102);
    }
}
