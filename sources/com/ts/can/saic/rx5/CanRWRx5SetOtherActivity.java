package com.ts.can.saic.rx5;

import android.view.View;
import android.widget.Toast;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanRWRx5SetOtherActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    private static final int ITEM_FS_SET = 2;
    private static final int ITEM_HSJZDZD = 3;
    private static final int ITEM_LANGUAGE = 0;
    private static final int ITEM_TYFW = 1;
    private static final int MAX_ITEM = 3;
    private static final int MIN_ITEM = 0;
    private CanItemTextBtnList mItemFsSet;
    private CanItemSwitchList mItemHsjzdzd;
    private CanItemTextBtnList mItemTyfw;
    private final int[] mLanguageArray = {R.string.can_language_english, R.string.can_language_chinese};
    private CanScrollList mManager;
    private CanDataInfo.RwRx5_SetAdt mRx5Adt = new CanDataInfo.RwRx5_SetAdt();
    /* access modifiers changed from: private */
    public CanDataInfo.RwRx5_SetInfo mRx5SetInfo = new CanDataInfo.RwRx5_SetInfo();

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mManager.addItemPopupList(R.string.can_lang_set, this.mLanguageArray, 0, (CanItemPopupList.onPopItemClick) this);
        this.mItemHsjzdzd = this.mManager.addItemCheckBox(R.string.can_zdzdwhsj, 3, this);
        this.mItemTyfw = this.mManager.addItemFsSetList(R.string.can_rw_rx5_tyfw, 1, this);
        this.mItemFsSet = this.mManager.addItemFsSetList(R.string.can_rw_rx5_hfccsz, 2, this);
    }

    private void showItem() {
        for (int i = 0; i <= 3; i++) {
            switch (i) {
                case 1:
                    this.mItemTyfw.ShowGone(this.mRx5Adt.Tyfw);
                    break;
                case 2:
                    this.mItemFsSet.ShowGone(this.mRx5Adt.Hfccszsn);
                    break;
                case 3:
                    this.mItemHsjzdzd.ShowGone(this.mRx5Adt.Hsjzd);
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
            if (this.mRx5SetInfo.Tyfw == 0) {
                Toast.makeText(this, R.string.can_rw_rx5_tyfw_finish, 1).show();
            } else {
                Toast.makeText(this, R.string.can_rw_rx5_tyfw_running, 1).show();
            }
            this.mItemHsjzdzd.SetCheck(this.mRx5SetInfo.Hsjzd);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.RwRx5Query(5, 1, 102);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                new CanItemMsgBox(1, this, R.string.can_rw_rx5_sure, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanJni.RwRx5CarSet(169, CanRWRx5SetOtherActivity.this.Neg(CanRWRx5SetOtherActivity.this.mRx5SetInfo.Tyfw), 255, 255);
                    }
                });
                return;
            case 2:
                new CanItemMsgBox(2, this, R.string.can_sure_setup, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanJni.RwRx5CarSet(170, 1, 255, 255);
                    }
                });
                return;
            case 3:
                CanJni.RwRx5CarSet(60, Neg(this.mRx5SetInfo.Hsjzd), 255, 255);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.RwRx5LangSet(1, item + 1);
                return;
            default:
                return;
        }
    }
}
