package com.ts.can.toyota.wc;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanScrollList;

public class CanToyotaWCSetAcActivity extends CanToyotaWCBaseActivity implements View.OnClickListener, UserCallBack, CanItemProgressList.onPosChange {
    public static final int ITEM_AC_LINK_AUTO = 1;
    public static final int ITEM_LOOP_LINK_AUTO = 2;
    private static final int ITEM_MAX = 5;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_RQFZWWDZDKZMS = 5;
    public static final int ITEM_YWCGQLMD = 3;
    public static final int ITEM_ZQFZWWDZDKZMS = 4;
    public static final String TAG = "CanToyotaSetAcActivity";
    private CanItemCheckList mItemAcLinkAuto;
    private CanItemCheckList mItemLoopLinkAuto;
    private CanItemProgressList mItemRqfzwwdzdkzms;
    private CanItemProgressList mItemYwcgqlmd;
    private CanItemProgressList mItemZqfzwwdzdkzms;
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
            LayoutUI();
            check = false;
            this.mbLayout = true;
        }
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemAcLinkAuto.SetCheck(this.mSetData.fgSEByAuto);
            this.mItemLoopLinkAuto.SetCheck(this.mSetData.fgLoopByAuto);
            this.mItemYwcgqlmd.SetCurVal(this.mSetData.Ywcgqlmd);
            this.mItemYwcgqlmd.SetValText(String.valueOf(this.mSetData.Ywcgqlmd - 3));
            this.mItemZqfzwwdzdkzms.SetCurVal(this.mSetData.Zqfzwwdzdkzms);
            this.mItemZqfzwwdzdkzms.SetValText(String.valueOf(this.mSetData.Zqfzwwdzdkzms - 2));
            this.mItemRqfzwwdzdkzms.SetCurVal(this.mSetData.Yqfzwwdzdkzms);
            this.mItemRqfzwwdzdkzms.SetValText(String.valueOf(this.mSetData.Yqfzwwdzdkzms - 2));
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
        for (int i = 1; i <= 5; i++) {
            InitItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        this.mManager.RemoveAllViews();
        for (int i = 1; i <= 5; i++) {
            AddItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mSetData.fgSEByAuto;
                break;
            case 2:
                ret = this.mSetData.fgLoopByAuto;
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
            case 3:
                this.mItemYwcgqlmd = addProgressList(R.string.can_ywcgq_lmd, item, this);
                this.mItemYwcgqlmd.SetMinMax(0, 6);
                this.mItemYwcgqlmd.SetStep(1);
                this.mItemYwcgqlmd.SetUserValText();
                return;
            case 4:
                this.mItemZqfzwwdzdkzms = addProgressList(R.string.can_zqfzwwdzdkzms, item, this);
                this.mItemZqfzwwdzdkzms.SetMinMax(0, 4);
                this.mItemZqfzwwdzdkzms.SetStep(1);
                this.mItemZqfzwwdzdkzms.SetUserValText();
                return;
            case 5:
                this.mItemRqfzwwdzdkzms = addProgressList(R.string.can_rqfzwwdzdkzms, item, this);
                this.mItemRqfzwwdzdkzms.SetMinMax(0, 4);
                this.mItemRqfzwwdzdkzms.SetStep(1);
                this.mItemRqfzwwdzdkzms.SetUserValText();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void AddItem(int item) {
        switch (item) {
            case 1:
                this.mManager.AddView(this.mItemAcLinkAuto.GetView());
                return;
            case 2:
                this.mManager.AddView(this.mItemLoopLinkAuto.GetView());
                return;
            case 3:
                this.mManager.AddView(this.mItemYwcgqlmd.GetView());
                return;
            case 4:
                this.mManager.AddView(this.mItemZqfzwwdzdkzms.GetView());
                return;
            case 5:
                this.mManager.AddView(this.mItemRqfzwwdzdkzms.GetView());
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public CanItemCheckList AddCheckItem(int resId, int Id) {
        CanItemCheckList item = new CanItemCheckList(this, resId);
        item.SetIdClickListener(this, Id);
        return item;
    }

    public CanItemProgressList addProgressList(int textId, int id, CanItemProgressList.onPosChange callBack) {
        CanItemProgressList item = new CanItemProgressList((Context) this, textId);
        item.SetIdCallBack(id, callBack);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CarSet(1, 6, Neg(this.mSetData.fgSEByAuto));
                return;
            case 2:
                CarSet(1, 7, Neg(this.mSetData.fgLoopByAuto));
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 3:
                CarSet(1, 14, pos);
                return;
            case 4:
                CarSet(1, 12, pos);
                return;
            case 5:
                CarSet(1, 13, pos);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
