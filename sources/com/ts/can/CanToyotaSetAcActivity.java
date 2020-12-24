package com.ts.can;

import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanScrollList;

public class CanToyotaSetAcActivity extends CanToyotaBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_AC_LINK_AUTO = 1;
    public static final int ITEM_LOOP_LINK_AUTO = 2;
    private static final int ITEM_MAX = 2;
    private static final int ITEM_MIN = 1;
    public static final String TAG = "CanToyotaSetAcActivity";
    private CanItemCheckList mItemAcLinkAuto;
    private CanItemCheckList mItemLoopLinkAuto;
    private CanScrollList mManager;
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetSetData();
        if (!this.mbLayout) {
            GetAdaptData();
            if (i2b(this.mAdtData.UpdateOnce)) {
                LayoutUI();
                check = false;
                this.mbLayout = true;
            } else {
                return;
            }
        }
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemAcLinkAuto.SetCheck(this.mSetData.fgSEByAuto);
            this.mItemLoopLinkAuto.SetCheck(this.mSetData.fgLoopByAuto);
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
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        for (int i = 1; i <= 2; i++) {
            InitItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        this.mManager.RemoveAllViews();
        for (int i = 1; i <= 2; i++) {
            AddItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.AcLinkAuto;
                break;
            case 2:
                ret = this.mAdtData.LoopLinkAuto;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void InitItem(int item) {
        switch (item) {
            case 1:
                this.mItemAcLinkAuto = AddCheckItem(R.string.can_ac_link_auto, item);
                return;
            case 2:
                this.mItemLoopLinkAuto = AddCheckItem(R.string.can_loop_link_auto, item);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void AddItem(int item) {
        if (IsHaveItem(item)) {
            switch (item) {
                case 1:
                    this.mManager.AddView(this.mItemAcLinkAuto.GetView());
                    return;
                case 2:
                    this.mManager.AddView(this.mItemLoopLinkAuto.GetView());
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public CanItemCheckList AddCheckItem(int resId, int Id) {
        CanItemCheckList item = new CanItemCheckList(this, resId);
        item.SetIdClickListener(this, Id);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CarSet(18, Neg(this.mSetData.fgSEByAuto));
                return;
            case 2:
                CarSet(19, Neg(this.mSetData.fgLoopByAuto));
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
