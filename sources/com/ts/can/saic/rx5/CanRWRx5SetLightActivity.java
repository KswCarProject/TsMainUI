package com.ts.can.saic.rx5;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanRWRx5SetLightActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    private static final int ITEM_BWHJ = 0;
    private static final int ITEM_XCZS = 1;
    private static final int MAX_ITEM = 1;
    private static final int MIN_ITEM = 0;
    private CanItemSwitchList mItemBwhj;
    private CanItemPopupList mItemXczs;
    private CanScrollList mManager;
    private CanDataInfo.RwRx5_SetAdt mRx5Adt = new CanDataInfo.RwRx5_SetAdt();
    private CanDataInfo.RwRx5_SetInfo mRx5SetInfo = new CanDataInfo.RwRx5_SetInfo();
    private final int[] mXczsArray = {R.string.can_only_light, R.string.can_dghlb};

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemBwhj = this.mManager.addItemCheckBox(R.string.can_bwhj, 0, this);
        this.mItemXczs = this.mManager.addItemPopupList(R.string.can_xcd, this.mXczsArray, 1, (CanItemPopupList.onPopItemClick) this);
    }

    private void showItem() {
        for (int i = 0; i <= 1; i++) {
            switch (i) {
                case 0:
                    this.mItemBwhj.ShowGone(this.mRx5Adt.Bwhjsn);
                    break;
                case 1:
                    this.mItemXczs.ShowGone(this.mRx5Adt.Xczssn);
                    break;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.RwRx5GetAdt(this.mRx5Adt);
        CanJni.RwRx5GetSet(this.mRx5SetInfo);
        if (i2b(this.mRx5Adt.UpdateOnce) && (!check || i2b(this.mRx5Adt.Update))) {
            this.mRx5Adt.Update = 0;
            showItem();
        }
        if (!i2b(this.mRx5SetInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mRx5SetInfo.Update)) {
            this.mRx5SetInfo.Update = 0;
            this.mItemBwhj.SetCheck(this.mRx5SetInfo.Bwhjsn);
            this.mItemXczs.SetSel(this.mRx5SetInfo.Xczssn);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.RwRx5Query(5, 1, 102);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.RwRx5CarSet(58, Neg(this.mRx5SetInfo.Bwhjsn), 255, 255);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.RwRx5CarSet(59, item, 255, 255);
                return;
            default:
                return;
        }
    }
}
