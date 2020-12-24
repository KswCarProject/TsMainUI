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

public class CanObdCarSpeedInfoActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener {
    public static final int ITEM_FDJZS = 3;
    private static final int ITEM_MAX = 3;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_PJCS = 2;
    public static final int ITEM_SSCS = 1;
    public static final String TAG = "CanObdCarSpeedInfoActivity";
    protected CanItemTitleValList mItemFdjzs;
    protected CanItemTitleValList mItemPjcs;
    protected CanItemTitleValList mItemSscs;
    private CanScrollList mManager;
    protected CanDataInfo.Obd_Speed mSetData = new CanDataInfo.Obd_Speed();
    protected CanDataInfo.Obd_Moto mSetData1 = new CanDataInfo.Obd_Moto();
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
        CanJni.CanObdGetSpeed(this.mSetData);
        if (i2b(this.mSetData.UpdateOnce) && (!check || i2b(this.mSetData.Update))) {
            this.mSetData.Update = 0;
            if (this.mSetData.SsDw > 0) {
                this.mItemSscs.SetVal(String.format("%.2f  Mile/h", new Object[]{Double.valueOf(((double) this.mSetData.SsCs) * 0.01d)}));
            } else {
                this.mItemSscs.SetVal(String.format("%.2f  Km/h", new Object[]{Double.valueOf(((double) this.mSetData.SsCs) * 0.01d)}));
            }
            if (this.mSetData.PjDw > 0) {
                this.mItemPjcs.SetVal(String.format("%.2f  Mile/h", new Object[]{Double.valueOf(((double) this.mSetData.PjCs) * 0.01d)}));
            } else {
                this.mItemPjcs.SetVal(String.format("%.2f  Km/h", new Object[]{Double.valueOf(((double) this.mSetData.PjCs) * 0.01d)}));
            }
        }
        CanJni.CanObdGetMoto(this.mSetData1);
        if (!i2b(this.mSetData1.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData1.Update)) {
            this.mSetData1.Update = 0;
            this.mItemFdjzs.SetVal(String.format("%.2f  Rpm", new Object[]{Double.valueOf(((double) this.mSetData1.Zs) * 0.25d)}));
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.CanObdQuery(4);
        Sleep(10);
        CanJni.CanObdQuery(10);
        Sleep(10);
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
        this.mItemSscs = this.mManager.addItemTitleValList(R.string.can_curspeed, 0, false, this);
        this.mItemPjcs = this.mManager.addItemTitleValList(R.string.can_avg_speed, 0, false, this);
        this.mItemFdjzs = this.mManager.addItemTitleValList(R.string.can_rpm, 0, false, this);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 3; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        CanJni.CanObdGetAdt(this.m_AdtDate);
        switch (item) {
            case 1:
                ret = this.m_AdtDate.Sscs;
                break;
            case 2:
                ret = this.m_AdtDate.Pjcs;
                break;
            case 3:
                ret = this.m_AdtDate.Fdjzs;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemSscs.ShowGone(show);
                return;
            case 2:
                this.mItemPjcs.ShowGone(show);
                return;
            case 3:
                this.mItemFdjzs.ShowGone(show);
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
