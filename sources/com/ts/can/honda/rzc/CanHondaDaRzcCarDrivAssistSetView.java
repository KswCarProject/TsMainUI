package com.ts.can.honda.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemPopupList;

public class CanHondaDaRzcCarDrivAssistSetView extends CanScrollCarInfoView {
    public static final int ITEM_ACCQCTZTSY = 2;
    public static final int ITEM_CDPLFXXTSD = 5;
    public static final int ITEM_DDTSY = 7;
    public static final int ITEM_FDJJNZDTTXS = 1;
    public static final int ITEM_JSYZYLKCQ = 6;
    public static final int ITEM_JSZSGXHSDJY = 8;
    public static final int ITEM_SDQFWXJGJL = 4;
    public static final int ITEM_YYBJXTDYL = 0;
    public static final int ITEM_ZTLKASTSY = 3;
    public static final int ITEM_ZYWZYD = 9;
    public static final int ITEM_ZZDDXBS = 10;
    private static int[] mCdplfxxtsdAccord10Arr;
    private static int[] mCdplfxxtsdArr;
    private static int[] mCdplfxxtsdHaoyingArr;
    protected CanDataInfo.HondaSetData mSetData;

    public CanHondaDaRzcCarDrivAssistSetView(Activity activity) {
        super(activity, 11);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.HondaDACarSet(30, item);
                return;
            case 4:
                CanJni.HondaDACarSet(31, item);
                return;
            case 5:
                CanJni.HondaDACarSet(34, item);
                return;
            case 6:
                CanJni.HondaDACarSet(36, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.HondaDACarSet(29, Neg(this.mSetData.fdjjnzdqtxs));
                return;
            case 2:
                CanJni.HondaDACarSet(32, Neg(this.mSetData.qctztsy));
                return;
            case 3:
                CanJni.HondaDACarSet(33, Neg(this.mSetData.ztlkastsy));
                return;
            case 7:
                CanJni.HondaDACarSet(50, Neg(this.mSetData.Ddtsy));
                return;
            case 8:
                CanJni.HondaDACarSet(39, Neg(this.mSetData.Jszsgxhdsjywzld));
                return;
            case 9:
                CanJni.HondaDACarSet(40, Neg(this.mSetData.Zywzyd));
                return;
            case 10:
                CanJni.HondaDACarSet(72, Neg(this.mSetData.Zzddxbs));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_yybjxtyl, R.string.can_fdjjnzdqtxs, R.string.can_accqctztsy, R.string.can_ztlkastsy, R.string.can_sdqfwxjgjl, R.string.can_cdplfxxtsd, R.string.can_jsyzyljcq, R.string.can_dctsy, R.string.can_jszsgxhsd, R.string.can_zywzyd, R.string.can_zzddxbs};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.string.can_yybjxtyl_1, R.string.can_yybjxtyl_2};
        this.mPopValueIds[4] = new int[]{R.string.can_sdqfwxjgjl_1, R.string.can_sdqfwxjgjl_2, R.string.can_sdqfwxjgjl_3};
        this.mPopValueIds[5] = new int[0];
        this.mPopValueIds[6] = new int[]{R.string.can_trunk_close, R.string.can_sjjg, R.string.can_cjhsjjg};
        this.mSetData = new CanDataInfo.HondaSetData();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        mCdplfxxtsdArr = new int[]{R.string.can_cdplfxxtsd_1, R.string.can_cdplfxxtsd_2, R.string.can_cdplfxxtsd_3};
        mCdplfxxtsdAccord10Arr = new int[]{R.string.can_cdpyyzxt_1, R.string.can_cdpyyzxt_2, R.string.can_cdpyyzxt_3, R.string.can_cdpyyzxt_4};
        mCdplfxxtsdHaoyingArr = new int[]{R.string.can_cdpyyzxt_1, R.string.can_cdplfxxtsd_2, R.string.can_cdpyyzxt_3, R.string.can_cdpyyzxt_5};
        if (CanJni.GetSubType() == 3) {
            getScrollManager().RemoveView(5);
            this.mItemObjects[5] = getScrollManager().addItemPopupList(this.mItemTitleIds[5], mCdplfxxtsdAccord10Arr, 5, (CanItemPopupList.onPopItemClick) this);
        } else if (CanJni.GetSubType() == 5) {
            getScrollManager().RemoveView(5);
            this.mItemObjects[5] = getScrollManager().addItemPopupList(this.mItemTitleIds[5], mCdplfxxtsdHaoyingArr, 5, (CanItemPopupList.onPopItemClick) this);
        } else {
            getScrollManager().RemoveView(5);
            this.mItemObjects[5] = getScrollManager().addItemPopupList(this.mItemTitleIds[5], mCdplfxxtsdArr, 5, (CanItemPopupList.onPopItemClick) this);
        }
    }

    public void ResetData(boolean check) {
        CanJni.HondaDAGetCarSet(this.mSetData);
        if (i2b(this.mSetData.JsfzUpdateOnce) && (!check || i2b(this.mSetData.JsfzUpdate))) {
            this.mSetData.JsfzUpdate = 0;
            updateItem(0, this.mSetData.yybjxtdyl);
            updateItem(1, this.mSetData.fdjjnzdqtxs);
            updateItem(2, this.mSetData.qctztsy);
            updateItem(3, this.mSetData.ztlkastsy);
            updateItem(4, this.mSetData.sdqfwxjgjl);
            updateItem(5, this.mSetData.cdplfxxtsd);
            updateItem(6, this.mSetData.Jsyzyljcq);
            updateItem(7, this.mSetData.Ddtsy);
            updateItem(8, this.mSetData.Jszsgxhdsjywzld);
            updateItem(9, this.mSetData.Zywzyd);
        }
        if (!i2b(this.mSetData.SysAssistUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.SysAssistUpdate)) {
            this.mSetData.SysAssistUpdate = 0;
            updateItem(10, this.mSetData.Zzddxbs);
        }
    }

    public void QueryData() {
    }
}
