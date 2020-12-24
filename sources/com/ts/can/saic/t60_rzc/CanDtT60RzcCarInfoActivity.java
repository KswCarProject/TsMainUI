package com.ts.can.saic.t60_rzc;

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
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanDtT60RzcCarInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemMsgBox.onMsgBoxClick, CanItemProgressList.onPosChange {
    public static final int ITEM_BCLDSJ = 2;
    public static final int ITEM_BWHJ = 1;
    public static final int ITEM_CLSSFK = 6;
    public static final int ITEM_CSBJ = 9;
    public static final int ITEM_CSBJSZ = 10;
    public static final int ITEM_FSET = 12;
    public static final int ITEM_HSJZDZD = 11;
    private static final int ITEM_MAX = 12;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_WYSJS = 7;
    public static final int ITEM_XCFK = 3;
    public static final int ITEM_XCZDLS = 4;
    public static final int ITEM_XHZDLS = 5;
    public static final int ITEM_YSJS = 8;
    public static final String TAG = "CanDtT60RzcCarInfoActivity";
    private static final int[] mBcldsjArrays = {R.string.can_30s, R.string.can_60s, R.string.can_90s};
    private static final int[] mClssfkArrays = {R.string.can_mingd, R.string.can_sdmd, R.string.can_shand};
    private static final int[] mWysjsArrays = {R.string.can_sym, R.string.can_jsym};
    private static final int[] mXcfkArrays = {R.string.can_shand, R.string.can_sdmd};
    private static final int[] mYsjsArrays = {R.string.can_sym, R.string.can_jsym};
    protected CanItemPopupList mItemBcldsj;
    private CanItemSwitchList mItemBwhj;
    protected CanItemPopupList mItemClssfk;
    protected CanItemSwitchList mItemCsbj;
    protected CanItemProgressList mItemCsbjsz;
    protected CanItemTextBtnList mItemFactory;
    protected CanItemSwitchList mItemHsjzdzd;
    protected CanItemPopupList mItemWysjs;
    protected CanItemPopupList mItemXcfk;
    protected CanItemSwitchList mItemXczdls;
    protected CanItemSwitchList mItemXhzdls;
    protected CanItemPopupList mItemYsjs;
    private CanScrollList mManager;
    protected CanDataInfo.DT_T60_RZC_DATA mSetData = new CanDataInfo.DT_T60_RZC_DATA();
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
        CanJni.DtT60RzcGetCarData(this.mSetData);
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
            this.mItemWysjs.SetSel(this.mSetData.Wysjs);
            this.mItemYsjs.SetSel(this.mSetData.Ysjs);
            this.mItemCsbj.SetCheck(this.mSetData.Csbj);
            this.mItemCsbjsz.SetCurVal(this.mSetData.CsbjVal);
            this.mItemCsbjsz.SetValText(String.valueOf(this.mSetData.CsbjVal) + "Km/h");
            this.mItemHsjzdzd.SetCheck(this.mSetData.Hsjzdzd);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.DtT60RzcQuery(81, 0);
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
        this.mItemHsjzdzd = AddCheckItem(R.string.can_zdhsjzd, 11);
        this.mItemCsbj = AddCheckItem(R.string.can_tigger7_speed_warn, 9);
        this.mItemCsbjsz = this.mManager.addItemProgressList(R.string.can_tigger7_speed_value, 10, (CanItemProgressList.onPosChange) this);
        this.mItemCsbjsz.SetMinMax(30, 220);
        this.mItemCsbjsz.SetStep(5);
        this.mItemCsbjsz.SetUserValText();
        this.mItemBcldsj = this.mManager.addItemPopupList(R.string.can_bcldsj, mBcldsjArrays, 2, (CanItemPopupList.onPopItemClick) this);
        this.mItemXcfk = this.mManager.addItemPopupList(R.string.can_xcfk, mXcfkArrays, 3, (CanItemPopupList.onPopItemClick) this);
        this.mItemClssfk = this.mManager.addItemPopupList(R.string.can_clssfk, mClssfkArrays, 6, (CanItemPopupList.onPopItemClick) this);
        this.mItemWysjs = this.mManager.addItemPopupList(R.string.can_wysjr, mWysjsArrays, 7, (CanItemPopupList.onPopItemClick) this);
        this.mItemYsjs = this.mManager.addItemPopupList(R.string.can_key_unlock, mYsjsArrays, 8, (CanItemPopupList.onPopItemClick) this);
        this.mItemFactory = AddTextBtn(R.string.can_factory_set, 12);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 12; i++) {
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
            case 8:
                ret = 1;
                break;
            case 9:
                ret = 1;
                break;
            case 10:
                ret = 1;
                break;
            case 11:
                ret = 1;
                break;
            case 12:
                ret = 0;
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
                this.mItemWysjs.ShowGone(show);
                return;
            case 8:
                this.mItemYsjs.ShowGone(show);
                return;
            case 9:
                this.mItemCsbj.ShowGone(show);
                return;
            case 10:
                this.mItemCsbjsz.ShowGone(show);
                return;
            case 11:
                this.mItemHsjzdzd.ShowGone(show);
                return;
            case 12:
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
                CanJni.DtT60RzcCarSet(0, NegSwSet(this.mSetData.Bwhj));
                return;
            case 4:
                CanJni.DtT60RzcCarSet(3, NegSwSet(this.mSetData.Xczdls));
                return;
            case 5:
                CanJni.DtT60RzcCarSet(4, NegSwSet(this.mSetData.Xhzdjs));
                return;
            case 9:
                CanJni.DtT60RzcCarSet(8, NegSwSet(this.mSetData.Csbj));
                return;
            case 11:
                CanJni.DtT60RzcCarSet(10, NegSwSet(this.mSetData.Hsjzdzd));
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 10:
                CanJni.DtT60RzcCarSet(9, pos);
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
                CanJni.DtT60RzcCarSet(1, item);
                return;
            case 3:
                CanJni.DtT60RzcCarSet(5, item);
                return;
            case 6:
                CanJni.DtT60RzcCarSet(2, item);
                return;
            case 7:
                CanJni.DtT60RzcCarSet(6, item);
                return;
            case 8:
                CanJni.DtT60RzcCarSet(7, item);
                return;
            default:
                return;
        }
    }

    public void onOK(int param) {
    }
}
