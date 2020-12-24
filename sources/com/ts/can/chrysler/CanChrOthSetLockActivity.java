package com.ts.can.chrysler;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanChrOthSetLockActivity extends CanChrOthBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_AUTO_CLOSE = 11;
    public static final int ITEM_CMBJ = 10;
    public static final int ITEM_DDWM = 12;
    private static final int ITEM_MAX = 12;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_REMOTEDOORUNLOCK = 8;
    public static final int ITEM_REMOTESTART = 7;
    public static final int ITEM_SCSFCTSY = 6;
    public static final int ITEM_SCYSJS = 4;
    public static final int ITEM_SCZXDSS = 3;
    public static final int ITEM_WYSJR = 5;
    public static final int ITEM_XCZDJS = 2;
    public static final int ITEM_ZDCMSD = 1;
    public static final int ITEM_ZNYSGXH = 9;
    public static final String TAG = "CanChrOthSetLockActivity";
    private static final String[] mHornLock = {"OFF", "1st Press(ON)", "2nd Press(ON)"};
    private static final int[] mRemotedoorunlockArr = {R.string.can_remotedoor_all, R.string.can_remotedoor_driver};
    private static final int[] mScysjsArr = {R.string.can_sym, R.string.can_jsym};
    protected CanItemSwitchList mItemAutoClose;
    protected CanItemSwitchList mItemCmbj;
    protected CanItemSwitchList mItemDdwm;
    protected CanItemSwitchList mItemHornremotestart;
    protected CanItemPopupList mItemRemotedoorunlock;
    protected CanItemPopupList mItemScsfctsy;
    protected CanItemPopupList mItemScysjs;
    protected CanItemSwitchList mItemSczxdss;
    protected CanItemSwitchList mItemWysjr;
    protected CanItemSwitchList mItemXczdjs;
    protected CanItemSwitchList mItemZdcmsd;
    protected CanItemSwitchList mItemZnysgxh;
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
        if (!i2b(this.mSetData.LockUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.LockUpdate)) {
            this.mSetData.LockUpdate = 0;
            this.mItemZdcmsd.SetCheck(this.mSetData.AutoLock);
            this.mItemXczdjs.SetCheck(this.mSetData.AutoUnlockExit);
            this.mItemSczxdss.SetCheck(this.mSetData.FlashLock);
            this.mItemWysjr.SetCheck(this.mSetData.PassiveEntry);
            this.mItemScysjs.SetSel(this.mSetData.RmtUnlock);
            this.mItemScsfctsy.SetSel(this.mSetData.HornLock);
            this.mItemHornremotestart.SetCheck(this.mSetData.HornRemoteStart);
            this.mItemRemotedoorunlock.SetSel(this.mSetData.RemoteDoorUnlck);
            this.mItemZnysgxh.SetCheck(this.mSetData.Znysgxhsz);
            this.mItemCmbj.SetCheck(this.mSetData.Cmbj);
            this.mItemAutoClose.SetCheck(this.mSetData.AutoClose);
            this.mItemDdwm.SetCheck(this.mSetData.Ddwmbj);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query(64, 48);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        LayoutUI();
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
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemZdcmsd = AddCheckItem(R.string.can_zmzdsd, 1);
        this.mItemXczdjs = AddCheckItem(R.string.can_xczdjs, 2);
        this.mItemSczxdss = AddCheckItem(R.string.can_scszxdss, 3);
        this.mItemWysjr = AddCheckItem(R.string.can_wysjr, 5);
        this.mItemScysjs = AddPopupItem(R.string.can_sccysjs, mScysjsArr, 4);
        this.mItemScsfctsy = AddPopupItem(R.string.can_scsfctsy, mHornLock, 6);
        this.mItemHornremotestart = AddCheckItem(R.string.can_hornremotestart, 7);
        this.mItemRemotedoorunlock = AddPopupItem(R.string.can_remotedoorunlock, mRemotedoorunlockArr, 8);
        this.mItemZnysgxh = AddCheckItem(R.string.can_znysgxhsz, 9);
        this.mItemCmbj = AddCheckItem(R.string.can_jp_cmbj, 10);
        this.mItemAutoClose = AddCheckItem(R.string.can_xczdls, 11);
        this.mItemDdwm = AddCheckItem(R.string.can_ddwmbj, 12);
        if (CanFunc.getInstance().IsCustomShow("Jeep")) {
            findViewById(R.id.can_comm_list_layout).setBackgroundResource(R.drawable.can_grand_cherokee_bg);
        }
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        GetAdtData();
        for (int i = 1; i <= 12; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.AutoLock;
                break;
            case 2:
                ret = this.mAdtData.AutoUnlockExit;
                break;
            case 3:
                ret = this.mAdtData.FlashLock;
                break;
            case 4:
                ret = this.mAdtData.RmtUnlock;
                break;
            case 5:
                ret = this.mAdtData.PassiveEntry;
                break;
            case 6:
                ret = this.mAdtData.HornLock;
                break;
            case 7:
                ret = this.mAdtData.HornRemoteStart;
                break;
            case 8:
                ret = this.mAdtData.RemoteDoorUnlck;
                break;
            case 9:
                ret = this.mAdtData.Znysgxhsz;
                break;
            case 10:
                ret = this.mAdtData.Cmbj;
                break;
            case 11:
                ret = this.mAdtData.AutoClose;
                break;
            case 12:
                ret = this.mAdtData.Ddwmbj;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemZdcmsd.ShowGone(show);
                return;
            case 2:
                this.mItemXczdjs.ShowGone(show);
                return;
            case 3:
                this.mItemSczxdss.ShowGone(show);
                return;
            case 4:
                this.mItemScysjs.ShowGone(show);
                return;
            case 5:
                this.mItemWysjr.ShowGone(show);
                return;
            case 6:
                this.mItemScsfctsy.ShowGone(show);
                return;
            case 7:
                this.mItemHornremotestart.ShowGone(show);
                return;
            case 8:
                this.mItemRemotedoorunlock.ShowGone(show);
                return;
            case 9:
                this.mItemZnysgxh.ShowGone(show);
                return;
            case 10:
                this.mItemCmbj.ShowGone(show);
                return;
            case 11:
                this.mItemAutoClose.ShowGone(show);
                return;
            case 12:
                this.mItemDdwm.ShowGone(show);
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
    public CanItemPopupList AddPopupItem(int text, String[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CarSWSet(48, this.mSetData.AutoLock);
                return;
            case 2:
                CarSWSet(49, this.mSetData.AutoUnlockExit);
                return;
            case 3:
                CarSWSet(50, this.mSetData.FlashLock);
                return;
            case 5:
                CarSWSet(54, this.mSetData.PassiveEntry);
                return;
            case 7:
                CarSWSet(59, this.mSetData.HornRemoteStart);
                return;
            case 9:
                CarSWSet(56, this.mSetData.Znysgxhsz);
                return;
            case 10:
                CarSWSet(58, this.mSetData.Cmbj);
                return;
            case 11:
                CarSWSet(53, this.mSetData.AutoClose);
                return;
            case 12:
                CarSWSet(57, this.mSetData.Ddwmbj);
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
            case 4:
                CarSet(52, item);
                return;
            case 6:
                CarSet(55, item);
                return;
            case 8:
                CarSet(60, item);
                return;
            default:
                return;
        }
    }
}
