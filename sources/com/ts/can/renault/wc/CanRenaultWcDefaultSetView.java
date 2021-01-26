package com.ts.can.renault.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanRenaultWcDefaultSetView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick, CanItemMsgBox.onMsgBoxClick2 {
    protected static final int ITEM_DEFAULT_FW = 1;
    protected static final int ITEM_DEFAULT_HY = 3;
    protected static final int ITEM_DEFAULT_JS = 0;
    protected static final int ITEM_DEFAULT_MULTI_SENSE = 4;
    protected static final int ITEM_DEFAULT_YSDG = 2;

    public CanRenaultWcDefaultSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                new CanItemMsgBox(0, getActivity(), R.string.can_cmp_reset_notice, this, this);
                return;
            case 1:
                new CanItemMsgBox(1, getActivity(), R.string.can_cmp_reset_notice, this, this);
                return;
            case 2:
                new CanItemMsgBox(2, getActivity(), R.string.can_cmp_reset_notice, this, this);
                return;
            case 3:
                new CanItemMsgBox(3, getActivity(), R.string.can_cmp_reset_notice, this, this);
                return;
            case 4:
                new CanItemMsgBox(4, getActivity(), R.string.can_cmp_reset_notice, this, this);
                return;
            default:
                return;
        }
    }

    public void onOK(int param) {
        switch (param) {
            case 0:
                CanJni.RenaultWcCarSet(48, 1, 0, 0);
                return;
            case 1:
                CanJni.RenaultWcCarSet(49, 1, 0, 0);
                return;
            case 2:
                CanJni.RenaultWcCarSet(50, 1, 0, 0);
                return;
            case 3:
                CanJni.RenaultWcCarSet(51, 1, 0, 0);
                return;
            case 4:
                CanJni.RenaultWcCarSet(52, 1, 0, 0);
                return;
            default:
                return;
        }
    }

    public void onCancel(int param) {
        switch (param) {
            case 0:
                CanJni.RenaultWcCarSet(48, 0, 0, 0);
                return;
            case 1:
                CanJni.RenaultWcCarSet(49, 0, 0, 0);
                return;
            case 2:
                CanJni.RenaultWcCarSet(50, 0, 0, 0);
                return;
            case 3:
                CanJni.RenaultWcCarSet(51, 0, 0, 0);
                return;
            case 4:
                CanJni.RenaultWcCarSet(52, 0, 0, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_default_js, R.string.can_default_fw, R.string.can_default_ysdg, R.string.can_default_hy, R.string.can_default_multi_sense};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
