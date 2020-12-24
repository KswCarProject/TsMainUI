package com.ts.can.bmw.mini;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemIcoVal;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTitleValList;
import com.ts.canview.CanScrollList;

public abstract class CanBMWMiniBaseActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemPopupList.onPopItemClick {
    protected CanDataInfo.MiniCheck mCarCheck = new CanDataInfo.MiniCheck();
    protected CanDataInfo.MiniCarPC mCarPC = new CanDataInfo.MiniCarPC();
    protected CanDataInfo.MiniService mCarService = new CanDataInfo.MiniService();
    protected CanDataInfo.MiniEngineOil mEngineOil = new CanDataInfo.MiniEngineOil();
    protected CanScrollList mManager;
    protected CanDataInfo.MiniRPA mRpa = new CanDataInfo.MiniRPA();
    protected CanDataInfo.MiniCarSetData mSetData = new CanDataInfo.MiniCarSetData();

    /* access modifiers changed from: protected */
    public abstract void AddItemView();

    /* access modifiers changed from: protected */
    public abstract void Query();

    /* access modifiers changed from: protected */
    public abstract void ResetData(boolean z);

    /* access modifiers changed from: protected */
    public void GetMiniCarPC() {
        CanJni.MiniGetCarPc(this.mCarPC);
    }

    /* access modifiers changed from: protected */
    public void GetMiniSetData() {
        CanJni.MiniGetCarSet(this.mSetData);
    }

    /* access modifiers changed from: protected */
    public void GetMiniEngineOil() {
        CanJni.MiniGetCarEngineOil(this.mEngineOil);
    }

    /* access modifiers changed from: protected */
    public void GetMiniRpa() {
        CanJni.MiniGetCarRpa(this.mRpa);
    }

    /* access modifiers changed from: protected */
    public void GetMiniService() {
        CanJni.MiniGetCarService(this.mCarService);
    }

    /* access modifiers changed from: protected */
    public void GetMiniCheck() {
        CanJni.MiniGetCarCheck(this.mCarCheck);
    }

    /* access modifiers changed from: protected */
    public void Query(int cmd) {
        CanJni.MiniQuery(cmd);
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int param1, int param2, int param3, int param4) {
        CanJni.MiniCarSet(cmd, param1, param2, param3, param4);
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int param) {
        CarSet(cmd, param, 0, 0, 0);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        SetLayoutContainer();
        AddItemView();
    }

    /* access modifiers changed from: protected */
    public void SetLayoutContainer() {
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Query();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    public void onItem(int id, int item) {
    }

    public void onClick(View v) {
    }

    public void UserAll() {
        ResetData(true);
    }

    /* access modifiers changed from: protected */
    public CanItemIcoVal AddItemIcoVal(int ico, int text, int id) {
        CanItemIcoVal item = new CanItemIcoVal(this, ico, text);
        this.mManager.AddView(item.GetView());
        item.SetIdClickListener(this, id);
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddItemCheck(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddItemPopup(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemTitleValList AddItemTitleVal(int text, int id) {
        CanItemTitleValList item = new CanItemTitleValList(this, text);
        item.SetIdClickListener(this, id);
        item.SetIconVisibility(0);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }
}
