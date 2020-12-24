package com.ts.can.honda.accord_xbs;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanAccordXbsSetDriveAssActivity extends CanAccordXbsBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_ACC_QCTZTSY = 2;
    public static final int ITEM_CDPLFXSDXT = 3;
    public static final int ITEM_DCTSY = 6;
    public static final int ITEM_JTBSSBXT = 1;
    private static final int ITEM_MAX = 6;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_YKQDXT = 5;
    public static final int ITEM_ZT_LKAS_TSY = 4;
    public static final String TAG = "CanAccordLockActivity";
    private static final int[] mCdplfxxtArr = {R.string.can_cdplfxxtsd_1, R.string.can_cdplfxxtsd_2, R.string.can_cdplfxxtsd_3};
    private CanItemSwitchList mItemAccQctztsy;
    private CanItemPopupList mItemCdplfxxtsd;
    private CanItemSwitchList mItemDctsy;
    private CanItemSwitchList mItemJtbssbxt;
    private CanItemSwitchList mItemYkqdxt;
    private CanItemSwitchList mItemZtlkastsy;
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
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemJtbssbxt.SetCheck(this.mSetData.Jtbssbxt);
            this.mItemAccQctztsy.SetCheck(this.mSetData.AccQctztsy);
            this.mItemZtlkastsy.SetCheck(this.mSetData.ZtLkasTsy);
            this.mItemYkqdxt.SetCheck(this.mSetData.Ykqdxt);
            this.mItemDctsy.SetCheck(this.mSetData.Dctsy);
            this.mItemCdplfxxtsd.SetSel(this.mSetData.Cdplfxxtsz);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query(10, 0);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemJtbssbxt = AddCheckItem(R.string.can_jtbssbxt, 1);
        this.mItemAccQctztsy = AddCheckItem(R.string.can_accqctztsy, 2);
        this.mItemZtlkastsy = AddCheckItem(R.string.can_ztlkastsy, 4);
        this.mItemYkqdxt = AddCheckItem(R.string.can_remotestartsystem, 5);
        this.mItemDctsy = AddCheckItem(R.string.can_dctsy, 6);
        this.mItemCdplfxxtsd = AddPopupItem(R.string.can_cdplfxxtsd, mCdplfxxtArr, 3);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        GetAdtData();
        for (int i = 1; i <= 6; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.Jtbssbxt;
                break;
            case 2:
                ret = this.mAdtData.AccQctztsy;
                break;
            case 3:
                ret = this.mAdtData.Cdplfxxtsz;
                break;
            case 4:
                ret = this.mAdtData.ZtLkasTsy;
                break;
            case 5:
                ret = this.mAdtData.Ykqdxt;
                break;
            case 6:
                ret = this.mAdtData.Dctsy;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemJtbssbxt.ShowGone(show);
                return;
            case 2:
                this.mItemAccQctztsy.ShowGone(show);
                return;
            case 3:
                this.mItemCdplfxxtsd.ShowGone(show);
                return;
            case 4:
                this.mItemZtlkastsy.ShowGone(show);
                return;
            case 5:
                this.mItemYkqdxt.ShowGone(show);
                return;
            case 6:
                this.mItemDctsy.ShowGone(show);
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

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CarSet(31, Neg(this.mSetData.Jtbssbxt));
                return;
            case 2:
                CarSet(28, Neg(this.mSetData.AccQctztsy));
                return;
            case 4:
                CarSet(30, Neg(this.mSetData.ZtLkasTsy));
                return;
            case 5:
                CarSet(26, Neg(this.mSetData.Ykqdxt));
                return;
            case 6:
                CarSet(32, Neg(this.mSetData.Dctsy));
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 3:
                CarSet(29, item);
                return;
            default:
                return;
        }
    }
}
