package com.ts.can.chrysler.xbs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanXbsygSetLightActivity extends CanXbsygBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_CNFWD = 9;
    public static final int ITEM_DDYCXM = 1;
    public static final int ITEM_FXYGD = 5;
    public static final int ITEM_HSJTGJ = 12;
    public static final int ITEM_KJDDLQ = 3;
    private static final int ITEM_MAX = 12;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_QDYGZDQDDD = 6;
    public static final int ITEM_QZDLMD = 10;
    public static final int ITEM_RXD = 4;
    public static final int ITEM_SCSZXDSS = 2;
    public static final int ITEM_YBD = 7;
    public static final int ITEM_ZJFZD = 11;
    public static final int ITEM_ZXD = 8;
    public static final String TAG = "CanChrOthSetLightActivity";
    private static final int[] mDdycxmArr = {R.string.can_0s, R.string.can_30s, R.string.can_60s, R.string.can_90s};
    private static final int[] mKCnwfn = {R.string.can_headlightsens_0, R.string.can_headlightsens_1, R.string.can_headlightsens_2, R.string.can_headlightsens_3, R.string.can_headlightsens_4, R.string.can_headlightsens_5, R.string.can_headlightsens_6};
    private static final int[] mKheadlightsens = {R.string.can_headlightsens_1, R.string.can_headlightsens_2, R.string.can_headlightsens_3};
    private static final int[] mKjddlqArr = {R.string.can_0s, R.string.can_30s, R.string.can_60s, R.string.can_90s};
    protected CanItemPopupList mItemCnfwd;
    protected CanItemPopupList mItemDdycxm;
    protected CanItemSwitchList mItemFxygd;
    protected CanItemSwitchList mItemHsjtgj;
    protected CanItemPopupList mItemKjddlq;
    protected CanItemSwitchList mItemQdygzdqddd;
    protected CanItemPopupList mItemQzdlmd;
    protected CanItemSwitchList mItemRxd;
    protected CanItemSwitchList mItemScszxdss;
    protected CanItemSwitchList mItemYbd;
    protected CanItemSwitchList mItemZjfzd;
    protected CanItemSwitchList mItemZxd;
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
        if (!i2b(this.mSetData.LightsUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.LightsUpdate)) {
            this.mSetData.LightsUpdate = 0;
            this.mItemDdycxm.SetSel(this.mSetData.HeadlightDelay);
            this.mItemScszxdss.SetCheck(this.mSetData.FlashLights);
            this.mItemKjddlq.SetSel(this.mSetData.Illnminated);
            this.mItemRxd.SetCheck(this.mSetData.DaytimeLights);
            this.mItemFxygd.SetCheck(this.mSetData.Zdfxygd);
            this.mItemQdygzdqddd.SetCheck(this.mSetData.Qdygzdqddd);
            this.mItemYbd.SetCheck(this.mSetData.Ybd);
            this.mItemZxd.SetCheck(this.mSetData.Zxd);
            this.mItemCnfwd.SetSel(this.mSetData.Cnfwd);
            if (this.mSetData.Qzdlmd >= 1) {
                this.mItemQzdlmd.SetSel(this.mSetData.Qzdlmd - 1);
            }
            this.mItemZjfzd.SetCheck(this.mSetData.Zjfzd);
            this.mItemHsjtgj.SetCheck(this.mSetData.Hsjtgj);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query2(12);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        LayoutUI();
        ResetData(false);
        QueryData();
        Log.d("CanChrOthSetLightActivity", "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d("CanChrOthSetLightActivity", "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemScszxdss = AddCheckItem(R.string.can_scszxdss, 2);
        this.mItemRxd = AddCheckItem(R.string.can_rjxcd, 4);
        this.mItemFxygd = AddCheckItem(R.string.can_jp_zdfxygd, 5);
        this.mItemQdygzdqddd = AddCheckItem(R.string.can_jp_qdygszdqddd, 6);
        this.mItemDdycxm = AddPopupItem(R.string.can_car_ddycxm, mDdycxmArr, 1);
        this.mItemKjddlq = AddPopupItem(R.string.can_kjddlq, mKjddlqArr, 3);
        this.mItemYbd = AddCheckItem(R.string.can_ybzm, 7);
        this.mItemZxd = AddCheckItem(R.string.can_turning_lamp, 8);
        this.mItemCnfwd = AddPopupItem(R.string.can_env_light, mKCnwfn, 9);
        this.mItemQzdlmd = AddPopupItem(R.string.can_headlightsens, mKheadlightsens, 10);
        this.mItemZjfzd = AddCheckItem(R.string.can_corneringLights, 11);
        this.mItemHsjtgj = AddCheckItem(R.string.can_jp_hsjtgj, 12);
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
                ret = this.mAdtData.HeadlightDelay;
                break;
            case 2:
                ret = this.mAdtData.FlashLights;
                break;
            case 3:
                ret = this.mAdtData.Illnminated;
                break;
            case 4:
                ret = this.mAdtData.DaytimeLights;
                break;
            case 5:
                ret = this.mAdtData.Zdfxygd;
                break;
            case 6:
                ret = this.mAdtData.Qdygzdqddd;
                break;
            case 7:
                ret = this.mAdtData.Ybd;
                break;
            case 8:
                ret = this.mAdtData.Zxd;
                break;
            case 9:
                ret = this.mAdtData.Cnfwd;
                break;
            case 10:
                ret = this.mAdtData.Qzdlmd;
                break;
            case 11:
                ret = this.mAdtData.Zjfzd;
                break;
            case 12:
                ret = this.mAdtData.Hsjtgj;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemDdycxm.ShowGone(show);
                return;
            case 2:
                this.mItemScszxdss.ShowGone(show);
                return;
            case 3:
                this.mItemKjddlq.ShowGone(show);
                return;
            case 4:
                this.mItemRxd.ShowGone(show);
                return;
            case 5:
                this.mItemFxygd.ShowGone(show);
                return;
            case 6:
                this.mItemQdygzdqddd.ShowGone(show);
                return;
            case 7:
                this.mItemYbd.ShowGone(show);
                return;
            case 8:
                this.mItemZxd.ShowGone(show);
                return;
            case 9:
                this.mItemCnfwd.ShowGone(show);
                return;
            case 10:
                this.mItemQzdlmd.ShowGone(show);
                return;
            case 11:
                this.mItemZjfzd.ShowGone(show);
                return;
            case 12:
                this.mItemHsjtgj.ShowGone(show);
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
            case 2:
                CarSWSet(37, this.mSetData.FlashLights);
                return;
            case 4:
                CarSWSet(36, this.mSetData.DaytimeLights);
                return;
            case 5:
                CarSWSet(34, this.mSetData.Zdfxygd);
                return;
            case 6:
                CarSWSet(33, this.mSetData.Qdygzdqddd);
                return;
            case 11:
                CarSet(42, Neg(this.mSetData.Zjfzd));
                return;
            case 12:
                CarSet(35, Neg(this.mSetData.Hsjtgj));
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
            case 1:
                CarSet(38, item);
                return;
            case 3:
                CarSet(39, item);
                return;
            case 9:
                CarSet(43, item);
                return;
            case 10:
                CarSet(44, item + 1);
                return;
            default:
                return;
        }
    }
}
