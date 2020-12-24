package com.ts.can.mzd.cx4;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public abstract class CanMzdCx4BaseActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemPopupList.onPopItemClick {
    protected CanDataInfo.Cx4_CarSet_Data mCarSetData = new CanDataInfo.Cx4_CarSet_Data();
    protected CanScrollList mManager;

    /* access modifiers changed from: protected */
    public abstract void InitUI();

    /* access modifiers changed from: protected */
    public abstract void Query();

    /* access modifiers changed from: protected */
    public abstract void ResetData(boolean z);

    /* access modifiers changed from: protected */
    public void MzdCx4GetCarSetInfo() {
        CanJni.MzdCx4GetCarSetInfo(this.mCarSetData);
    }

    /* access modifiers changed from: protected */
    public void MzdCx4CarSet(int cmd, int param) {
        CanJni.MzdCx4CarSet(cmd, param);
    }

    /* access modifiers changed from: protected */
    public void MzdCx4SWCarSet(int cmd, int param) {
        MzdCx4CarSet(cmd, Neg(param));
    }

    /* access modifiers changed from: protected */
    public void MzdCx4CarQuery(int cmd, int param) {
        CanJni.MzdCx4Query(cmd, param);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        SetContentLayout();
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void SetContentLayout() {
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        MainTask.GetInstance().SetUserCallBack(this);
        super.onResume();
        ResetData(false);
        Query();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
    }

    public void onClick(View v) {
    }

    public void onItem(int id, int item) {
    }

    public void UserAll() {
        ResetData(true);
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupListItem(int text, int[] array, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, array, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        item.ShowGone(true);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int text, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, text);
        item.SetIdClickListener(this, Id);
        item.ShowGone(true);
        this.mManager.AddView(item.GetView());
        return item;
    }
}
