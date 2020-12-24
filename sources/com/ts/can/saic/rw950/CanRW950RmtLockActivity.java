package com.ts.can.saic.rw950;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanRW950RmtLockActivity extends CanRW950BaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_CMJS = 7;
    public static final int ITEM_CXYKSZDKDM = 8;
    public static final int ITEM_JSDGFK = 5;
    public static final int ITEM_SMDGLBFK = 6;
    public static final String TAG = "CanGMCarInfoActivity";
    private static final int[] mCmjsArr = {R.string.can_jsym, R.string.can_sym};
    private static final int[] mSmdglbfkArr = {R.string.can_only_light, R.string.can_dghlb, R.string.can_only_lb, R.string.can_off};
    private CanItemPopupList mItemCmjs;
    private CanItemSwitchList mItemCxykszdkdm;
    private CanItemSwitchList mItemJsdgfk;
    private CanItemPopupList mItemSmdglbfk;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetSetData();
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemJsdgfk.SetCheck(this.mSetData.ubYKJSDGFK);
            this.mItemSmdglbfk.SetSel(this.mSetData.ubYKFK);
            this.mItemCmjs.SetSel(this.mSetData.ubYKJS);
            this.mItemCxykszdkdm.SetCheck(this.mSetData.ubCXYKSZDKDM);
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
        this.mItemJsdgfk = AddCheckItem(R.string.can_ykjsdgfk, 5);
        this.mItemSmdglbfk = AddPopupItem(R.string.can_ykdglbfk, mSmdglbfkArr, 6);
        this.mItemCmjs = AddPopupItem(R.string.can_ykjssz, mCmjsArr, 7);
        this.mItemCxykszdkdm = AddCheckItem(R.string.can_cxszdkdm, 8);
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 5:
                CarSet(6, Neg(this.mSetData.ubYKJSDGFK));
                return;
            case 8:
                CarSet(9, Neg(this.mSetData.ubCXYKSZDKDM));
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 6:
                CarSet(7, item);
                return;
            case 7:
                CarSet(8, item);
                return;
            default:
                return;
        }
    }
}
