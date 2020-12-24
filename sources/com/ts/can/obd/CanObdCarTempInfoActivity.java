package com.ts.can.obd;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTitleValList;
import com.ts.canview.CanScrollList;

public class CanObdCarTempInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_JYWD = 2;
    private static final int ITEM_MAX = 2;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_SW = 1;
    public static final String TAG = "CanObdCarTempInfoActivity";
    protected CanItemTitleValList mItemJywd;
    protected CanItemTitleValList mItemSw;
    private CanScrollList mManager;
    protected CanDataInfo.Obd_Temp mSetData = new CanDataInfo.Obd_Temp();
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
        CanJni.CanObdGetTemp(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemSw.SetVal(String.format("%d  ℃", new Object[]{Integer.valueOf(this.mSetData.Sw - 40)}));
            this.mItemJywd.SetVal(String.format("%d  ℃", new Object[]{Integer.valueOf(this.mSetData.Jywd - 40)}));
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.CanObdQuery(9);
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
        this.mItemSw = this.mManager.addItemTitleValList(R.string.can_rpm_temp, 0, false, this);
        this.mItemJywd = this.mManager.addItemTitleValList(R.string.can_jywd, 0, false, this);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 2; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        CanJni.CanObdGetAdt(this.m_AdtDate);
        switch (item) {
            case 1:
                ret = this.m_AdtDate.Swxx;
                break;
            case 2:
                ret = this.m_AdtDate.Jywd;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemSw.ShowGone(show);
                return;
            case 2:
                this.mItemJywd.ShowGone(show);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemIcoList AddIcoItem(int ico, int text, int id) {
        CanItemIcoList item = new CanItemIcoList(this, ico, text);
        item.SetIdClickListener(this, id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
    }
}
