package com.ts.can.kaiyi.x3;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanKY3XSetLockActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    private static final int ITEM_AQKZ = 4;
    private static final int ITEM_BDSG = 3;
    private static final int ITEM_JJZDSS = 2;
    private static final int ITEM_XHJS = 1;
    private static final int ITEM_ZDLS = 0;
    private static String[] mAqkzArray;
    private static String[] mBdsgArray;
    private CanItemPopupList mItemAqkz;
    private CanItemPopupList mItemBdsg;
    private CanItemSwitchList mItemJjzdss;
    private CanItemSwitchList mItemXhjs;
    private CanItemSwitchList mItemZdjs;
    private CanScrollList mManager;
    private CanDataInfo.KaiYi3X_CarSet mSetData = new CanDataInfo.KaiYi3X_CarSet();

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.KaiYi3xCarSet(3, Neg(this.mSetData.Zdls));
                return;
            case 1:
                CanJni.KaiYi3xCarSet(4, Neg(this.mSetData.Xhjs));
                return;
            case 2:
                CanJni.KaiYi3xCarSet(2, Neg(this.mSetData.Jjzdss));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 3:
                CanJni.KaiYi3xCarSet(6, item);
                return;
            case 4:
                CanJni.KaiYi3xCarSet(5, item + 1);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        mBdsgArray = getResources().getStringArray(R.array.can_kaiyi_3x_bdsg);
        mAqkzArray = getResources().getStringArray(R.array.can_kaiyi_3x_aqkz);
        this.mManager = new CanScrollList(this);
        this.mItemZdjs = this.mManager.addItemCheckBox(R.string.can_tigger7_auto_lock, 0, this);
        this.mItemXhjs = this.mManager.addItemCheckBox(R.string.can_kaiyi_3x_xhjs, 1, this);
        this.mItemJjzdss = this.mManager.addItemCheckBox(R.string.can_kaiyi_3x_jjzdss, 2, this);
        this.mItemBdsg = this.mManager.addItemPopupList(R.string.can_kaiyi_3x_bdsg, mBdsgArray, 3, (CanItemPopupList.onPopItemClick) this);
        this.mItemAqkz = this.mManager.addItemPopupList(R.string.can_kaiyi_3x_aqkz, mAqkzArray, 4, (CanItemPopupList.onPopItemClick) this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.KaiYi3xGetInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemZdjs.SetCheck(this.mSetData.Zdls);
            this.mItemXhjs.SetCheck(this.mSetData.Xhjs);
            this.mItemJjzdss.SetCheck(this.mSetData.Jjzdss);
            this.mItemBdsg.SetSel(this.mSetData.Bdsg);
            this.mItemAqkz.SetSel(this.mSetData.Aqkz - 1);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
