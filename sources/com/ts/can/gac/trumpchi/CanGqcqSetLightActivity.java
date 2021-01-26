package com.ts.can.gac.trumpchi;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanGqcqSetLightActivity extends CanGqcqBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    public static final int ITEM_BWHJ = 1;
    public static final int ITEM_FWD = 5;
    public static final int ITEM_FWDLD = 6;
    public static final int ITEM_FWDYS = 7;
    private static final int ITEM_MAX = 8;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_RXD = 3;
    public static final int ITEM_WDZXFZ = 2;
    public static final int ITEM_ZDDGLMD = 4;
    public static final int ITEM_ZNYGD = 8;
    public static final String TAG = "CanGqcqSetLightActivity";
    protected int[] mBwhjArr = {R.string.can_off, R.string.can_only_jgd, R.string.can_jghwd};
    private CanItemPopupList mItemBwhj;
    private CanItemPopupList mItemFwdkz;
    private CanItemProgressList mItemFwdld;
    private CanItemProgressList mItemFwdys;
    private CanItemPopupList mItemRxd;
    private CanItemPopupList mItemWdzxfz;
    private CanItemPopupList mItemZddglmd;
    private CanItemSwitchList mItemZnygd;
    private CanScrollList mManager;
    protected int[] mZddglmdArr = {R.string.can_cdjd, R.string.can_cdzj, R.string.can_cdjg};
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
            this.mItemBwhj.SetSel(this.mSetData.Bwhj - 1);
            this.mItemWdzxfz.SetSel(this.mSetData.Wdzxfz - 1);
            this.mItemRxd.SetSel(this.mSetData.Rxd - 1);
            this.mItemZddglmd.SetSel(this.mSetData.AutoLightSens - 1);
            this.mItemFwdkz.SetSel(this.mSetData.Fwdkz - 1);
            this.mItemFwdld.SetCurVal(this.mSetData.Fwdld);
            this.mItemFwdld.SetValText(new StringBuilder(String.valueOf(this.mSetData.Fwdld + 1)).toString());
            this.mItemFwdys.SetCurVal(this.mSetData.Fwdys);
            this.mItemFwdys.SetValText(new StringBuilder(String.valueOf(this.mSetData.Fwdys + 1)).toString());
            this.mItemZnygd.SetCheck(this.mSetData.Znygd - 1);
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
        Log.d(TAG, "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemBwhj = AddPopupItem(R.string.can_bwhj, this.mBwhjArr, 1);
        this.mItemWdzxfz = AddPopupItem(R.string.can_wdzxfz, this.mSWArr, 2);
        this.mItemRxd = AddPopupItem(R.string.can_light_rxd, this.mSWArr, 3);
        this.mItemZddglmd = AddPopupItem(R.string.can_zddglmd, this.mZddglmdArr, 4);
        this.mItemFwdkz = AddPopupItem(R.string.can_wc_car_inner_light, this.mSWArr, 5);
        this.mItemFwdld = AddProgressItem(R.string.can_fwdlddj, 6);
        this.mItemFwdld.SetMinMax(0, 7);
        this.mItemFwdys = AddProgressItem(R.string.can_fwd_color, 7);
        this.mItemFwdys.SetMinMax(0, 31);
        this.mItemZnygd = AddCheckItem(R.string.can_znygd, 8);
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 8; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        return i2b(1);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemBwhj.ShowGone(show);
                return;
            case 2:
                this.mItemWdzxfz.ShowGone(show);
                return;
            case 3:
                this.mItemRxd.ShowGone(show);
                return;
            case 4:
                this.mItemZddglmd.ShowGone(show);
                return;
            case 5:
                this.mItemFwdkz.ShowGone(show);
                return;
            case 6:
                this.mItemFwdld.ShowGone(show);
                return;
            case 7:
                this.mItemFwdys.ShowGone(show);
                return;
            case 8:
                this.mItemZnygd.ShowGone(show);
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
    public CanItemProgressList AddProgressItem(int resId, int Id) {
        CanItemProgressList item = new CanItemProgressList((Context) this, resId);
        item.SetIdCallBack(Id, this);
        item.ShowGone(false);
        this.mManager.AddView(item.GetView());
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 8:
                CarSet(43, Neg(this.mSetData.Znygd - 1));
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
                CarSet(18, item);
                return;
            case 2:
                CarSet(19, item);
                return;
            case 3:
                CarSet(20, item);
                return;
            case 4:
                CarSet(21, item);
                return;
            case 5:
                CarSet(36, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 6:
                CanJni.GqcqCarSet(41, pos);
                return;
            case 7:
                CanJni.GqcqCarSet(42, pos);
                return;
            default:
                return;
        }
    }
}
