package com.ts.can.zotye.x7;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanZotyetX7CarInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    public static final int ITEM_BWHJ = 4;
    public static final int ITEM_BWHJCXSJ = 5;
    public static final int ITEM_CDPL = 6;
    public static final int ITEM_DDGDTJ = 7;
    public static final int ITEM_FWDLDDJ = 1;
    public static final int ITEM_FWDSW = 2;
    private static final int ITEM_MAX = 7;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_ZYLRSW = 3;
    public static final String TAG = "CanZotyetX7CarInfoActivity";
    protected CanItemSwitchList mItemBwhj;
    protected CanItemProgressList mItemBwhjdsj;
    protected CanItemSwitchList mItemCdpl;
    protected CanItemProgressList mItemDdgdtj;
    private CanItemSwitchList mItemFwdSw;
    protected CanItemProgressList mItemFwdlddj;
    protected CanItemSwitchList mItemZylrSw;
    private CanScrollList mManager;
    protected CanDataInfo.ZtCarSet mSetData = new CanDataInfo.ZtCarSet();
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public int SwSet(int val) {
        if (1 == val) {
            return 1;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public int NegSwSet(int val) {
        if (1 == val) {
            return 0;
        }
        return 1;
    }

    /* access modifiers changed from: protected */
    public int NegBWHJSet(int val) {
        if (val == 0) {
            return 128;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.ZtDmX7GetCarData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemFwdlddj.SetCurVal(this.mSetData.Fwdlddj);
            this.mItemFwdSw.SetCheck(SwSet(this.mSetData.FwdSw));
            this.mItemZylrSw.SetCheck(SwSet(this.mSetData.ZylrSw));
            this.mItemBwhj.SetCheck(this.mSetData.Bwhjd);
            this.mItemBwhjdsj.SetCurVal(this.mSetData.Bwhjdsj);
            this.mItemBwhjdsj.SetValText(new StringBuilder(String.valueOf(this.mSetData.Bwhjdsj * 20)).toString());
            this.mItemCdpl.SetCheck(this.mSetData.Cdpl);
            this.mItemDdgdtj.SetCurVal(this.mSetData.Ddgdtj);
            this.mItemDdgdtj.SetValText(getDdgdtjText(this.mSetData.Ddgdtj));
        }
    }

    private String getDdgdtjText(int ddgdtj) {
        switch (ddgdtj) {
            case 0:
                return getResources().getString(R.string.can_cdzd);
            case 1:
                return getResources().getString(R.string.can_cdjd);
            case 2:
                return getResources().getString(R.string.can_cdjg);
            case 3:
                return getResources().getString(R.string.can_cdzg);
            default:
                return "";
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.ZotyeQuery(65, 0);
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
        this.mItemFwdlddj = new CanItemProgressList((Context) this, R.string.can_fwdlddj);
        this.mItemFwdlddj.SetMinMax(0, 7);
        this.mItemFwdlddj.SetIdCallBack(1, this);
        this.mManager.AddView(this.mItemFwdlddj.GetView());
        this.mItemFwdSw = AddCheckItem(R.string.can_env_light, 2);
        this.mItemZylrSw = AddCheckItem(R.string.can_zylrkg, 3);
        this.mItemBwhj = AddCheckItem(R.string.can_bwhj, 4);
        this.mItemCdpl = AddCheckItem(R.string.can_cdpl, 6);
        this.mItemBwhjdsj = new CanItemProgressList((Context) this, R.string.can_dgsjkz_bwhj);
        this.mItemBwhjdsj.SetMinMax(1, 14);
        this.mItemBwhjdsj.SetIdCallBack(5, this);
        this.mManager.AddView(this.mItemBwhjdsj.GetView());
        this.mItemDdgdtj = new CanItemProgressList((Context) this, R.string.can_ddgdtj);
        this.mItemDdgdtj.SetMinMax(0, 3);
        this.mItemDdgdtj.SetIdCallBack(7, this);
        this.mManager.AddView(this.mItemDdgdtj.GetView());
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 7; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = 1;
                break;
            case 2:
                ret = 1;
                break;
            case 3:
                ret = 1;
                break;
            case 4:
                ret = 1;
                break;
            case 5:
                ret = 1;
                break;
            case 6:
                ret = 1;
                break;
            case 7:
                ret = 1;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemFwdlddj.ShowGone(show);
                return;
            case 2:
                this.mItemFwdSw.ShowGone(show);
                return;
            case 3:
                this.mItemZylrSw.ShowGone(show);
                return;
            case 4:
                this.mItemBwhj.ShowGone(show);
                return;
            case 5:
                this.mItemBwhjdsj.ShowGone(show);
                return;
            case 6:
                this.mItemCdpl.ShowGone(show);
                return;
            case 7:
                this.mItemDdgdtj.ShowGone(show);
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
    public CanItemIcoList AddIcoItem(int ico, int text, int id) {
        CanItemIcoList item = new CanItemIcoList(this, ico, text);
        item.SetIdClickListener(this, id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.ZtDmX7CarSet(148, NegSwSet(this.mSetData.FwdSw));
                return;
            case 3:
                CanJni.ZtDmX7CarSet(147, NegSwSet(this.mSetData.ZylrSw));
                return;
            case 4:
                CanJni.ZtDmX7CarSet(Can.CAN_JAC_REFINE_OD, NegBWHJSet(this.mSetData.Bwhjd) + this.mSetData.Bwhjdsj);
                return;
            case 6:
                CanJni.ZtDmX7CarSet(151, NegSwSet(this.mSetData.Cdpl));
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        if (id == 1) {
            CanJni.ZtDmX7CarSet(149, pos);
        } else if (id == 5) {
            CanJni.ZtDmX7CarSet(Can.CAN_JAC_REFINE_OD, NegBWHJSet(NegBWHJSet(this.mSetData.Bwhjd)) + pos);
        } else if (id == 7) {
            CanJni.ZtDmX7CarSet(Can.CAN_AUDI_ZMYT, pos);
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
    }
}
