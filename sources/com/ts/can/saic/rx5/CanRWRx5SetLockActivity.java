package com.ts.can.saic.rx5;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanRWRx5SetLockActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    private static final int ITEM_YKJSMS = 0;
    private static final int ITEM_ZDLS = 2;
    private static final int ITEM_ZNJCJS = 1;
    private static final int MAX_ITEM = 2;
    private static final int MIN_ITEM = 0;
    private CanItemPopupList mItemYkjsms;
    private CanItemSwitchList mItemZdls;
    private CanItemPopupList mItemZnjcjs;
    private CanScrollList mManager;
    private CanDataInfo.RwRx5_SetAdt mRx5Adt = new CanDataInfo.RwRx5_SetAdt();
    private CanDataInfo.RwRx5_SetInfo mRx5SetInfo = new CanDataInfo.RwRx5_SetInfo();
    private final int[] mValueArray = {R.string.can_jsym, R.string.can_sym};

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemYkjsms = this.mManager.addItemPopupList(R.string.can_ykjssz, this.mValueArray, 0, (CanItemPopupList.onPopItemClick) this);
        this.mItemZnjcjs = this.mManager.addItemPopupList(R.string.can_smart_near_lock, this.mValueArray, 1, (CanItemPopupList.onPopItemClick) this);
        this.mItemZdls = this.mManager.addItemCheckBox(R.string.can_tigger7_auto_lock, 2, this);
    }

    private void showItem() {
        for (int i = 0; i <= 2; i++) {
            switch (i) {
                case 0:
                    this.mItemYkjsms.ShowGone(this.mRx5Adt.Ykjssd);
                    break;
                case 1:
                    this.mItemZnjcjs.ShowGone(this.mRx5Adt.Znjckssd);
                    break;
                case 2:
                    this.mItemZdls.ShowGone(this.mRx5Adt.Zdls);
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
            this.mItemYkjsms.SetSel(this.mRx5SetInfo.Ykjssd);
            this.mItemZnjcjs.SetSel(this.mRx5SetInfo.Znjckssd);
            this.mItemZdls.SetCheck(this.mRx5SetInfo.Zdls);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.RwRx5Query(5, 1, 102);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.RwRx5CarSet(61, Neg(this.mRx5SetInfo.Zdls), 255, 255);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.RwRx5CarSet(18, item, 255, 255);
                return;
            case 1:
                CanJni.RwRx5CarSet(32, item, 255, 255);
                return;
            default:
                return;
        }
    }
}
