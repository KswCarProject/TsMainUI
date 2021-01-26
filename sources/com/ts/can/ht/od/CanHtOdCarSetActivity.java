package com.ts.can.ht.od;

import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanHtOdCarSetActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    private static final int ITEM_FWDDTCYS = 6;
    private static final int ITEM_FWDDTSD = 10;
    private static final int ITEM_FWDDTSZ = 3;
    private static final int ITEM_FWDJTLLHCYS = 8;
    private static final int ITEM_FWDJTLZHCYS = 9;
    private static final int ITEM_FWDJTSZ = 5;
    private static final int ITEM_FWDKZFS = 4;
    private static final int ITEM_FWDLDDJ = 7;
    private static final int ITEM_FWDSW = 1;
    private static final int ITEM_ZYLRSW = 2;
    private static int[] mFwddtcysArray = {R.string.can_bluegreenred, R.string.can_bluepurplered};
    private static int[] mFwdjtszArray = {R.string.can_dansstatic, R.string.can_dansbreathe, R.string.can_biansbreathe};
    private static int[] mFwdkzfsArray = {R.string.can_nothave, R.string.can_bklctl, R.string.can_force};
    private CanItemSwitchList mItemFwdSw;
    private CanItemPopupList mItemFwddtcys;
    private CanItemProgressList mItemFwddtsd;
    private CanItemSwitchList mItemFwddtsz;
    private CanItemProgressList mItemFwdjtllhcys;
    private CanItemProgressList mItemFwdjtlzhcys;
    private CanItemPopupList mItemFwdjtsz;
    private CanItemPopupList mItemFwdkzfs;
    private CanItemProgressList mItemFwdlddj;
    private CanItemSwitchList mItemZylrSw;
    private CanDataInfo.HanT_CarSet mSetData = new CanDataInfo.HanT_CarSet();

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        CanScrollList manager = new CanScrollList(this);
        this.mItemFwdSw = manager.addItemCheckBox(R.string.can_env_light, 1, this);
        this.mItemZylrSw = manager.addItemCheckBox(R.string.can_zylrkg, 2, this);
        this.mItemFwddtsz = manager.addItemCheckBox(R.string.can_fwddtsz, 3, this);
        this.mItemFwdkzfs = manager.addItemPopupList(R.string.can_fwdkzfs, mFwdkzfsArray, 4, (CanItemPopupList.onPopItemClick) this);
        this.mItemFwdjtsz = manager.addItemPopupList(R.string.can_fwdjtsz, mFwdjtszArray, 5, (CanItemPopupList.onPopItemClick) this);
        this.mItemFwddtcys = manager.addItemPopupList(R.string.can_fwddtcys, mFwddtcysArray, 6, (CanItemPopupList.onPopItemClick) this);
        this.mItemFwdlddj = manager.addItemProgressList(R.string.can_fwdlddj, 7, (CanItemProgressList.onPosChange) this);
        this.mItemFwdlddj.SetStep(1);
        this.mItemFwdlddj.SetMinMax(1, 7);
        this.mItemFwdjtllhcys = manager.addItemProgressList(R.string.can_fwdjtllhcys, 8, (CanItemProgressList.onPosChange) this);
        this.mItemFwdjtllhcys.SetStep(1);
        this.mItemFwdjtllhcys.SetMinMax(0, 255);
        this.mItemFwdjtlzhcys = manager.addItemProgressList(R.string.can_fwdjtlzhcys, 9, (CanItemProgressList.onPosChange) this);
        this.mItemFwdjtlzhcys.SetStep(1);
        this.mItemFwdjtlzhcys.SetMinMax(0, 255);
        this.mItemFwddtsd = manager.addItemProgressList(R.string.can_fwddtsd, 10, (CanItemProgressList.onPosChange) this);
        this.mItemFwddtsd.SetStep(1);
        this.mItemFwddtsd.SetMinMax(0, 255);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.HanTOdGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemFwdSw.SetCheck(this.mSetData.FwdSw);
            this.mItemZylrSw.SetCheck(this.mSetData.ZylrSw);
            this.mItemFwddtsz.SetCheck(this.mSetData.Fwddtsz);
            this.mItemFwdkzfs.SetSel(this.mSetData.Fwdkzfs);
            this.mItemFwdjtsz.SetSel(this.mSetData.Fwdjtsz);
            this.mItemFwddtcys.SetSel(this.mSetData.Fwddtcys);
            this.mItemFwdlddj.SetCurVal(this.mSetData.Fwdlddj);
            this.mItemFwdjtllhcys.SetCurVal(this.mSetData.Fwdjtllhcys);
            this.mItemFwdjtlzhcys.SetCurVal(this.mSetData.Fwdjtlzhcys);
            this.mItemFwddtsd.SetCurVal(this.mSetData.Fwddtsd);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.HanTOdQuery(65);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.HanTOdCarSet(148, Neg(this.mSetData.FwdSw));
                return;
            case 2:
                CanJni.HanTOdCarSet(147, Neg(this.mSetData.ZylrSw));
                return;
            case 3:
                CanJni.HanTOdCarSet(Can.CAN_CC_WC, Neg(this.mSetData.Fwddtsz));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 4:
                CanJni.HanTOdCarSet(150, item);
                return;
            case 5:
                CanJni.HanTOdCarSet(151, item);
                return;
            case 6:
                CanJni.HanTOdCarSet(Can.CAN_DFFG_S560, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 7:
                CanJni.HanTOdCarSet(149, pos);
                return;
            case 8:
                CanJni.HanTOdCarSet(152, pos);
                return;
            case 9:
                CanJni.HanTOdCarSet(153, pos);
                return;
            case 10:
                CanJni.HanTOdCarSet(Can.CAN_HONDA_WC, pos);
                return;
            default:
                return;
        }
    }
}
