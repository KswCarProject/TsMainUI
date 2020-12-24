package com.ts.can.gm.sb;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanGMSBSetModeActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    private static final int ITEM_MAX = 4;
    private static final int ITEM_MIN = 1;
    private static final int ITEM_YDXFXP = 1;
    private static final int ITEM_YDXSLQD = 3;
    private static final int ITEM_YDXXG = 2;
    private static final int ITEM_ZDMS = 4;
    private static int[] mZdmsArrays = {R.string.can_off, R.string.can_yiban, R.string.can_ydg};
    private CanItemSwitchList mItemFxp;
    private CanItemSwitchList mItemSlqd;
    private CanItemSwitchList mItemXg;
    private CanItemPopupList mItemZdms;
    private CanScrollList mManager;
    private CanDataInfo.GmSb_CarSetAdtEx mSBAdtData = new CanDataInfo.GmSb_CarSetAdtEx();
    private CanDataInfo.GmSb_CarSetEx mSBSetData = new CanDataInfo.GmSb_CarSetEx();

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.GMCarCtrl(28, Neg(this.mSBSetData.Ydxfxp));
                return;
            case 2:
                CanJni.GMCarCtrl(29, Neg(this.mSBSetData.Ydxxg));
                return;
            case 3:
                CanJni.GMCarCtrl(30, Neg(this.mSBSetData.Ydxslqd));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        if (id == 4) {
            CanJni.GMCarCtrl(31, item);
        }
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemFxp = this.mManager.addItemCheckBox(R.string.can_ydxfxp, 1, this);
        this.mItemXg = this.mManager.addItemCheckBox(R.string.can_ydxxg, 2, this);
        this.mItemSlqd = this.mManager.addItemCheckBox(R.string.can_ydxslqd, 3, this);
        this.mItemZdms = this.mManager.addItemPopupList(R.string.can_zdmszdy, mZdmsArrays, 4, (CanItemPopupList.onPopItemClick) this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GmSbGetCarSetEx(this.mSBSetData);
        CanJni.GmSbGetCarSetExAdt(this.mSBAdtData);
        if (i2b(this.mSBAdtData.UpdateOnce) && (!check || i2b(this.mSBAdtData.Update))) {
            this.mSBAdtData.Update = 0;
            LayoutUI();
        }
        if (!i2b(this.mSBSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSBSetData.Update)) {
            this.mSBSetData.Update = 0;
            this.mItemFxp.SetCheck(this.mSBSetData.Ydxfxp);
            this.mItemXg.SetCheck(this.mSBSetData.Ydxxg);
            this.mItemSlqd.SetCheck(this.mSBSetData.Ydxslqd);
            this.mItemZdms.SetSel(this.mSBSetData.AutoMode);
        }
    }

    private void LayoutUI() {
        for (int i = 1; i <= 4; i++) {
            ShowItem(i);
        }
    }

    private void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemFxp.ShowGone(show);
                return;
            case 2:
                this.mItemXg.ShowGone(show);
                return;
            case 3:
                this.mItemSlqd.ShowGone(show);
                return;
            case 4:
                this.mItemZdms.ShowGone(show);
                return;
            default:
                return;
        }
    }

    private boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mSBAdtData.Ydxfxp;
                break;
            case 2:
                ret = this.mSBAdtData.Ydxxg;
                break;
            case 3:
                ret = this.mSBAdtData.Ydxslqd;
                break;
            case 4:
                ret = this.mSBAdtData.AutoMode;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
