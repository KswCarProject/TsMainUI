package com.ts.can.renault.kadjar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollBaseActivity;

public class CanKadjarLzfsqActivity extends CanScrollBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    protected static final int ITEM_LZFSQ = 1;
    protected static final int ITEM_MAX = 2;
    protected static final int ITEM_MIN = 1;
    protected static final int ITEM_QCQDSZDKQ = 2;
    public static final String TAG = "CanKadjarLzfsqActivity";
    protected static final int[] mLzfsqArr = {R.string.can_off, R.string.can_zlz_fs, R.string.can_flz_qj};
    protected CanItemPopupList mItemLzfsq;
    protected CanItemSwitchList mItemQcqdszdkq;
    protected CanDataInfo.KadjarSet mSetData = new CanDataInfo.KadjarSet();
    protected boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mItemLzfsq = AddPopup((CanItemPopupList.onPopItemClick) this, R.string.can_lzfsq, mLzfsqArr, 1);
        this.mItemQcqdszdkq = AddSwitch((View.OnClickListener) this, R.string.can_qcqdszdkq, 2);
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.KadjarGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemLzfsq.SetSel(this.mSetData.Lzfsq);
            this.mItemQcqdszdkq.SetCheck(this.mSetData.Qcqdszdkq);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
        Log.d(TAG, "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 2; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        switch (item) {
            case 1:
                break;
            case 2:
                break;
        }
        return i2b(1);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemLzfsq.ShowGone(show);
                return;
            case 2:
                this.mItemQcqdszdkq.ShowGone(show);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.KadjarCarSet(5, Neg(this.mSetData.Qcqdszdkq));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.KadjarCarSet(4, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int para) {
    }
}
