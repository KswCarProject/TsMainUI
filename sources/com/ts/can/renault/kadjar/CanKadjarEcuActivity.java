package com.ts.can.renault.kadjar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemFsSetList;
import com.ts.canview.CanItemIcoVal;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanScrollBaseActivity;

public class CanKadjarEcuActivity extends CanScrollBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange, CanItemFsSetList.onFsSetClick {
    protected static final int ITEM_AVG_CONSUMP = 1;
    protected static final int ITEM_AVG_SPEED = 2;
    protected static final int ITEM_MAX = 4;
    protected static final int ITEM_MIN = 1;
    protected static final int ITEM_RANGE = 3;
    protected static final int ITEM_RESET = 4;
    public static final String TAG = "CanKadjarEcuActivity";
    protected CanDataInfo.KadjarECU mEcuData = new CanDataInfo.KadjarECU();
    protected CanItemIcoVal mItemAvgConsump;
    protected CanItemIcoVal mItemAvgSpeed;
    protected CanItemIcoVal mItemRange;
    protected CanItemFsSetList mItemReset;
    protected boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mItemAvgConsump = AddIcoVal(R.drawable.can_icon_youhao, R.string.can_avg_consump, 1);
        this.mItemAvgSpeed = AddIcoVal(R.drawable.can_icon_sudu, R.string.can_avg_spped, 2);
        this.mItemRange = AddIcoVal(R.drawable.can_icon_kdlc, R.string.can_dis_trav, 3);
        this.mItemReset = AddFsSetItem(this, R.string.can_setup_reset, 4);
        this.mItemReset.SetMsgText(R.string.can_sure_reset);
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.KadjarGetEcu(this.mEcuData);
        if (!i2b(this.mEcuData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mEcuData.Update)) {
            this.mEcuData.Update = 0;
            if (this.mEcuData.AvgConsump >= 65535) {
                this.mItemAvgConsump.SetVal("--.- L/100KM");
            } else {
                this.mItemAvgConsump.SetVal(String.format("%.1f L/100KM", new Object[]{Float.valueOf(((float) this.mEcuData.AvgConsump) / 10.0f)}));
            }
            if (this.mEcuData.AvgSpeed >= 65535) {
                this.mItemAvgSpeed.SetVal("--.- KM/H");
            } else {
                this.mItemAvgSpeed.SetVal(String.format("%.1f KM/H", new Object[]{Float.valueOf(((float) this.mEcuData.AvgSpeed) / 10.0f)}));
            }
            if (this.mEcuData.Range >= 131071) {
                this.mItemRange.SetVal("---- KM");
                return;
            }
            this.mItemRange.SetVal(String.format("%.1f KM", new Object[]{Float.valueOf(((float) this.mEcuData.Range) / 10.0f)}));
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
                this.mItemAvgConsump.ShowGone(show);
                return;
            case 2:
                this.mItemAvgSpeed.ShowGone(show);
                return;
            case 3:
                this.mItemRange.ShowGone(show);
                return;
            case 4:
                this.mItemReset.ShowGone(show);
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
            case 4:
                CarSet(0, 0);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int para) {
    }

    public void onFsItem(int id, boolean sure) {
        if (sure) {
            CanJni.KadjarCarSet(128, 1);
        }
    }
}
