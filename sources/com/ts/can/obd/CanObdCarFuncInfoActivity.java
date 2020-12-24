package com.ts.can.obd;

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

public class CanObdCarFuncInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_HWD = 7;
    public static final int ITEM_JGD = 2;
    private static final int ITEM_MAX = 8;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_QWD = 6;
    public static final int ITEM_SKD = 1;
    public static final int ITEM_YGD = 3;
    public static final int ITEM_YGDJ = 8;
    public static final int ITEM_YZXD = 5;
    public static final int ITEM_ZZXD = 4;
    public static final String TAG = "CanObdCarFuncInfoActivity";
    private String[] Ygsddj = {"不刮", "1级", "2级", "3级", "4级", "5级", "6级", "7级"};
    protected CanItemSwitchList mItemHwd;
    protected CanItemSwitchList mItemJgd;
    protected CanItemSwitchList mItemQwd;
    protected CanItemSwitchList mItemSkd;
    protected CanItemSwitchList mItemYgd;
    protected CanItemTitleValList mItemYgdj;
    protected CanItemSwitchList mItemYzxd;
    protected CanItemSwitchList mItemZzxd;
    private CanScrollList mManager;
    protected CanDataInfo.Obd_Ill mSetData = new CanDataInfo.Obd_Ill();
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
        CanJni.CanObdGetIll(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemSkd.SetCheck(this.mSetData.Skd);
            this.mItemJgd.SetCheck(this.mSetData.Jgd);
            this.mItemYgd.SetCheck(this.mSetData.Ygd);
            this.mItemZzxd.SetCheck(this.mSetData.Zzxd);
            this.mItemYzxd.SetCheck(this.mSetData.Yzxd);
            this.mItemQwd.SetCheck(this.mSetData.Qwd);
            this.mItemHwd.SetCheck(this.mSetData.Hwd);
            this.mItemYgdj.SetVal(this.Ygsddj[this.mSetData.Ygsddj]);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.CanObdQuery(3);
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
        this.mItemSkd = AddCheckItem(R.string.can_ill_skd, 1);
        this.mItemJgd = AddCheckItem(R.string.can_ill_jgd, 2);
        this.mItemYgd = AddCheckItem(R.string.can_ill_ygd, 3);
        this.mItemZzxd = AddCheckItem(R.string.can_ill_zzxd, 4);
        this.mItemYzxd = AddCheckItem(R.string.can_ill_yzxd, 5);
        this.mItemQwd = AddCheckItem(R.string.can_ill_qwd, 6);
        this.mItemHwd = AddCheckItem(R.string.can_ill_hwd, 7);
        this.mItemYgdj = this.mManager.addItemTitleValList(R.string.can_ygdj, 0, false, this);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 8; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        CanJni.CanObdGetAdt(this.m_AdtDate);
        switch (item) {
            case 1:
                ret = this.m_AdtDate.Skd;
                break;
            case 2:
                ret = this.m_AdtDate.Jgd;
                break;
            case 3:
                ret = this.m_AdtDate.Ygd;
                break;
            case 4:
                ret = this.m_AdtDate.Zzxd;
                break;
            case 5:
                ret = this.m_AdtDate.Yzxd;
                break;
            case 6:
                ret = this.m_AdtDate.Qwd;
                break;
            case 7:
                ret = this.m_AdtDate.Hwd;
                break;
            case 8:
                ret = this.m_AdtDate.Yg;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemSkd.ShowGone(show);
                return;
            case 2:
                this.mItemJgd.ShowGone(show);
                return;
            case 3:
                this.mItemYgd.ShowGone(show);
                return;
            case 4:
                this.mItemZzxd.ShowGone(show);
                return;
            case 5:
                this.mItemYzxd.ShowGone(show);
                return;
            case 6:
                this.mItemQwd.ShowGone(show);
                return;
            case 7:
                this.mItemHwd.ShowGone(show);
                return;
            case 8:
                this.mItemYgdj.ShowGone(show);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
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
