package com.ts.can.obd;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemTitleValList;
import com.ts.canview.CanScrollList;

public class CanObdCarOtherInfoActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener {
    public static final int ITEM_KZMKDY = 1;
    private static final int ITEM_MAX = 1;
    private static final int ITEM_MIN = 1;
    public static final String TAG = "CanObdCarOtherInfoActivity";
    protected CanItemTitleValList mItemKzmkdy;
    private CanScrollList mManager;
    protected CanDataInfo.Obd_Other mSetData = new CanDataInfo.Obd_Other();
    private CanDataInfo.Obd_Adt m_AdtDate = new CanDataInfo.Obd_Adt();
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.CanObdGetOther(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemKzmkdy.SetVal(String.format("%.3f  V", new Object[]{Double.valueOf(((double) this.mSetData.Kzmkdy) * 0.001d)}));
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.CanObdQuery(11);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        LayoutUI();
        ResetData(false);
        QueryData();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemKzmkdy = this.mManager.addItemTitleValList(R.string.can_kzmkdy, 0, false, this);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 1; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        CanJni.CanObdGetAdt(this.m_AdtDate);
        switch (item) {
            case 1:
                ret = this.m_AdtDate.Kzmkdy;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemKzmkdy.ShowGone(show);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }
}
