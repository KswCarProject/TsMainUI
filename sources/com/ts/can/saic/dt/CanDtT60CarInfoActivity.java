package com.ts.can.saic.dt;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanDtT60CarInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemMsgBox.onMsgBoxClick {
    public static final int ITEM_BCLDSJ = 2;
    public static final int ITEM_BWHJ = 1;
    public static final int ITEM_CLSSFK = 6;
    public static final int ITEM_FSET = 7;
    private static final int ITEM_MAX = 7;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_XCFK = 3;
    public static final int ITEM_XCZDLS = 4;
    public static final int ITEM_XHZDLS = 5;
    public static final String TAG = "CanDtT60CarInfoActivity";
    private static final int[] mBcldsjArrays = {R.string.can_30s, R.string.can_60s, R.string.can_90s};
    private static final int[] mClssfkArrays = {R.string.can_sdmd, R.string.can_shand, R.string.can_mingd};
    private static final int[] mXcfkArrays = {R.string.can_shand, R.string.can_sdmd};
    protected CanItemPopupList mItemBcldsj;
    private CanItemSwitchList mItemBwhj;
    protected CanItemPopupList mItemClssfk;
    protected CanItemTextBtnList mItemFactory;
    protected CanItemPopupList mItemXcfk;
    protected CanItemSwitchList mItemXczdls;
    protected CanItemSwitchList mItemXhzdls;
    private CanScrollList mManager;
    protected CanDataInfo.DT_T60_DATA1 mSetData = new CanDataInfo.DT_T60_DATA1();
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
    public void ResetData(boolean check) {
        CanJni.DtT60GetCarData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemXhzdls.SetCheck(this.mSetData.Xhzdjs);
            this.mItemBwhj.SetCheck(SwSet(this.mSetData.Bwhj));
            this.mItemXczdls.SetCheck(SwSet(this.mSetData.Xczdls));
            this.mItemBcldsj.SetSel(this.mSetData.Bcldsj);
            this.mItemXcfk.SetSel(this.mSetData.Xcfk);
            this.mItemClssfk.SetSel(this.mSetData.Clssfk);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.DtT60Query(67, 0);
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
        this.mItemBwhj = AddCheckItem(R.string.can_bwhj, 1);
        this.mItemXczdls = AddCheckItem(R.string.can_tigger7_auto_lock, 4);
        this.mItemXhzdls = AddCheckItem(R.string.can_kaiyi_3x_xhjs, 5);
        this.mItemBcldsj = this.mManager.addItemPopupList(R.string.can_bcldsj, mBcldsjArrays, 2, (CanItemPopupList.onPopItemClick) this);
        this.mItemXcfk = this.mManager.addItemPopupList(R.string.can_xcfk, mXcfkArrays, 3, (CanItemPopupList.onPopItemClick) this);
        this.mItemClssfk = this.mManager.addItemPopupList(R.string.can_clssfk, mClssfkArrays, 6, (CanItemPopupList.onPopItemClick) this);
        this.mItemFactory = AddTextBtn(R.string.can_factory_set, 7);
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
                this.mItemBwhj.ShowGone(show);
                return;
            case 2:
                this.mItemBcldsj.ShowGone(show);
                return;
            case 3:
                this.mItemXcfk.ShowGone(show);
                return;
            case 4:
                this.mItemXczdls.ShowGone(show);
                return;
            case 5:
                this.mItemXhzdls.ShowGone(show);
                return;
            case 6:
                this.mItemClssfk.ShowGone(show);
                return;
            case 7:
                this.mItemFactory.ShowGone(show);
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

    private CanItemTextBtnList AddTextBtn(int text, int id) {
        CanItemTextBtnList btn = new CanItemTextBtnList((Context) this, text);
        btn.SetIdClickListener(this, id);
        this.mManager.AddView(btn.GetView());
        btn.ShowGone(false);
        return btn;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.DtT60CarSet(1, 1, NegSwSet(this.mSetData.Bwhj));
                return;
            case 4:
                CanJni.DtT60CarSet(2, 1, NegSwSet(this.mSetData.Xczdls));
                return;
            case 5:
                CanJni.DtT60CarSet(2, 2, NegSwSet(this.mSetData.Xhzdjs));
                return;
            case 7:
                new CanItemMsgBox(7, this, R.string.can_sure_setup, this);
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
            case 2:
                CanJni.DtT60CarSet(1, 2, item);
                return;
            case 3:
                CanJni.DtT60CarSet(1, 3, item);
                return;
            case 6:
                CanJni.DtT60CarSet(2, 3, item);
                return;
            default:
                return;
        }
    }

    public void onOK(int param) {
        CanJni.DtT60CarSet(3, 1, 0);
    }
}
