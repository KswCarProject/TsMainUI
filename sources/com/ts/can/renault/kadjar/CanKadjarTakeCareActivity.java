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
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollBaseActivity;

public class CanKadjarTakeCareActivity extends CanScrollBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    protected static final int ITEM_KQZL = 1;
    protected static final int ITEM_LZFSQ = 2;
    protected static final int ITEM_MAX = 4;
    protected static final int ITEM_MIN = 1;
    protected static final int ITEM_QGGZNJSXT = 3;
    protected static final int ITEM_SDBJ = 4;
    public static final String TAG = "CanKadjarTakeCareActivity";
    protected static final int[] mQggznjsxtArr = {R.string.can_eco, R.string.can_gxh, R.string.can_sport, R.string.can_comfort, R.string.can_mode_normal};
    protected CanItemTextBtnList mItemKqzl;
    protected CanItemTextBtnList mItemLzfsq;
    protected CanItemPopupList mItemQggznjsxt;
    protected CanItemSwitchList mItemSdbj;
    protected CanDataInfo.KadjarSet mSetData = new CanDataInfo.KadjarSet();
    protected boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mItemKqzl = AddTextBtn(this, R.string.can_kqzl, 1);
        this.mItemLzfsq = AddTextBtn(this, R.string.can_lzfsq, 2);
        this.mItemQggznjsxt = AddPopup((CanItemPopupList.onPopItemClick) this, R.string.can_qggznjsxt, mQggznjsxtArr, 3);
        this.mItemSdbj = AddSwitch((View.OnClickListener) this, R.string.can_sdbj, 4);
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
            this.mItemQggznjsxt.SetSel(this.mSetData.Qggznjsxt - 1);
            this.mItemSdbj.SetCheck(this.mSetData.Sdbj);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.KadjarCarQuery(113, 0);
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
        for (int i = 1; i <= 4; i++) {
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
            case 3:
                break;
            case 4:
                break;
        }
        return i2b(1);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemKqzl.ShowGone(show);
                return;
            case 2:
                this.mItemLzfsq.ShowGone(show);
                return;
            case 3:
                this.mItemQggznjsxt.ShowGone(show);
                return;
            case 4:
                this.mItemSdbj.ShowGone(show);
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
            case 1:
                enterSubWin(CanKadjarKqzlActivity.class);
                return;
            case 2:
                enterSubWin(CanKadjarLzfsqActivity.class);
                return;
            case 4:
                CanJni.KadjarCarSet(35, Neg(this.mSetData.Sdbj));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 3:
                CanJni.KadjarCarSet(34, item + 1);
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
