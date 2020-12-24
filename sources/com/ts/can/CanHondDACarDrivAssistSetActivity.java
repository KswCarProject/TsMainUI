package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanHondDACarDrivAssistSetActivity extends CanBaseActivity implements View.OnClickListener, CanItemPopupList.onPopItemClick, UserCallBack {
    public static final int ITEM_ACCQCTZTSY = 3;
    public static final int ITEM_CDPLFXXTSD = 6;
    public static final int ITEM_DDTSY = 8;
    public static final int ITEM_FDJJNZDTTXS = 2;
    public static final int ITEM_JSYZYLKCQ = 7;
    public static final int ITEM_JSZSGXHSDJY = 9;
    public static final int ITEM_SDQFWXJGJL = 5;
    public static final int ITEM_YYBJXTDYL = 1;
    public static final int ITEM_ZTLKASTSY = 4;
    public static final int ITEM_ZYWZYD = 10;
    public static final String TAG = "CanHondDACarDrivAssistSetActivity";
    private static final int[] mCdplfxxtsdAccord10Arr = {R.string.can_cdpyyzxt_1, R.string.can_cdpyyzxt_2, R.string.can_cdpyyzxt_3, R.string.can_cdpyyzxt_4};
    private static final int[] mCdplfxxtsdArr = {R.string.can_cdplfxxtsd_1, R.string.can_cdplfxxtsd_2, R.string.can_cdplfxxtsd_3};
    private static final int[] mJsyzylkcqArr = {R.string.can_trunk_close, R.string.can_sjjg, R.string.can_cjhsjjg};
    private static final int[] mSdqfwxjgjlArr = {R.string.can_sdqfwxjgjl_1, R.string.can_sdqfwxjgjl_2, R.string.can_sdqfwxjgjl_3};
    private static final int[] mYybjxtdylArr = {R.string.can_yybjxtyl_1, R.string.can_yybjxtyl_2};
    protected CanItemSwitchList mItemAccQctztsy;
    protected CanItemPopupList mItemCdplfxxtsd;
    protected CanItemSwitchList mItemDdtsy;
    protected CanItemSwitchList mItemFdjjnzdqtxs;
    protected CanItemPopupList mItemJsyzyljcq;
    protected CanItemSwitchList mItemJszsgxhsdjywzld;
    protected CanItemPopupList mItemSdqfwxjgjl;
    protected CanItemPopupList mItemYybjxtdyl;
    protected CanItemSwitchList mItemZtLKAStsy;
    protected CanItemSwitchList mItemZywzyd;
    protected CanScrollList mManager;
    protected CanDataInfo.HondaSetData mSetData = new CanDataInfo.HondaSetData();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
        this.mItemYybjxtdyl = AddPopupItem(R.string.can_yybjxtyl, mYybjxtdylArr, 1);
        this.mItemFdjjnzdqtxs = AddCheckItem(R.string.can_fdjjnzdqtxs, 2);
        this.mItemAccQctztsy = AddCheckItem(R.string.can_accqctztsy, 3);
        this.mItemZtLKAStsy = AddCheckItem(R.string.can_ztlkastsy, 4);
        this.mItemSdqfwxjgjl = AddPopupItem(R.string.can_sdqfwxjgjl, mSdqfwxjgjlArr, 5);
        if (CanJni.GetSubType() != 11) {
            this.mItemCdplfxxtsd = AddPopupItem(R.string.can_cdplfxxtsd, mCdplfxxtsdArr, 6);
        } else {
            this.mItemCdplfxxtsd = AddPopupItem(R.string.can_cdplfxxtsd, mCdplfxxtsdAccord10Arr, 6);
        }
        this.mItemJsyzyljcq = AddPopupItem(R.string.can_jsyzyljcq, mJsyzylkcqArr, 7);
        this.mItemDdtsy = AddCheckItem(R.string.can_dctsy, 8);
        this.mItemJszsgxhsdjywzld = AddCheckItem(R.string.can_jszsgxhsd, 9);
        this.mItemZywzyd = AddCheckItem(R.string.can_zywzyd, 10);
        if (!(CanJni.GetSubType() == 8 || CanJni.GetSubType() == 9)) {
            this.mItemDdtsy.ShowGone(false);
            this.mItemJszsgxhsdjywzld.ShowGone(false);
            this.mItemZywzyd.ShowGone(false);
        }
        if (CanJni.GetSubType() == 6 || CanJni.GetSubType() == 7) {
            this.mItemDdtsy.ShowGone(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.HondaDACarSet(29, Neg(this.mSetData.fdjjnzdqtxs));
                return;
            case 3:
                CanJni.HondaDACarSet(32, Neg(this.mSetData.qctztsy));
                return;
            case 4:
                CanJni.HondaDACarSet(33, Neg(this.mSetData.ztlkastsy));
                return;
            case 8:
                if (CanJni.GetSubType() == 6 || CanJni.GetSubType() == 7) {
                    CanJni.HondaDACarSet(50, Neg(this.mSetData.Ddtsy));
                    return;
                } else {
                    CanJni.HondaDACarSet(36, Neg(this.mSetData.Ddtsy));
                    return;
                }
            case 9:
                CanJni.HondaDACarSet(39, Neg(this.mSetData.Jszsgxhdsjywzld));
                return;
            case 10:
                CanJni.HondaDACarSet(40, Neg(this.mSetData.Zywzyd));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.HondaDACarSet(30, item);
                return;
            case 5:
                CanJni.HondaDACarSet(31, item);
                return;
            case 6:
                CanJni.HondaDACarSet(34, item);
                return;
            case 7:
                if (CanJni.GetSubType() == 8 || CanJni.GetSubType() == 9) {
                    CanJni.HondaDACarSet(41, item);
                    return;
                } else {
                    CanJni.HondaDACarSet(36, item);
                    return;
                }
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.HondaDAGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.JsfzUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.JsfzUpdate)) {
            this.mSetData.JsfzUpdate = 0;
            this.mItemYybjxtdyl.SetSel(this.mSetData.yybjxtdyl);
            this.mItemFdjjnzdqtxs.SetCheck(this.mSetData.fdjjnzdqtxs);
            this.mItemAccQctztsy.SetCheck(this.mSetData.qctztsy);
            this.mItemZtLKAStsy.SetCheck(this.mSetData.ztlkastsy);
            this.mItemSdqfwxjgjl.SetSel(this.mSetData.sdqfwxjgjl);
            this.mItemCdplfxxtsd.SetSel(this.mSetData.cdplfxxtsd);
            this.mItemJsyzyljcq.SetSel(this.mSetData.Jsyzyljcq);
            this.mItemDdtsy.SetCheck(this.mSetData.Ddtsy);
            this.mItemJszsgxhsdjywzld.SetCheck(this.mSetData.Jszsgxhdsjywzld);
            this.mItemZywzyd.SetCheck(this.mSetData.Zywzyd);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
