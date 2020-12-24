package com.ts.can.gm.sb;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanGMSBSetConvActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    public static final int ITEM_DCHYSZDQD = 1;
    public static final int ITEM_DDHSJQX = 4;
    public static final int ITEM_DYJYWZ = 2;
    public static final int ITEM_HSJZDZD = 5;
    public static final int ITEM_JSYBLXC = 3;
    private static final int ITEM_MAX = 6;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_ZDYS = 6;
    private CanDataInfo.GM_AdtConv mAdtData = new CanDataInfo.GM_AdtConv();
    private CanItemSwitchList mItemDchyszdqd;
    private CanItemSwitchList mItemDdhsjqx;
    private CanItemSwitchList mItemDyjywz;
    private CanItemSwitchList mItemHsjzdzd;
    private CanItemSwitchList mItemJsyblxc;
    private CanItemSwitchList mItemZdys;
    private CanScrollList mManager;
    private CanDataInfo.GmSb_CarSetAdtEx mSBAdtData = new CanDataInfo.GmSb_CarSetAdtEx();
    private CanDataInfo.GmSb_CarSetEx mSBSetData = new CanDataInfo.GmSb_CarSetEx();
    private CanDataInfo.GM_CarSet mSetData = new CanDataInfo.GM_CarSet();

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.GMCarCtrl(11, Neg(this.mSetData.DCYHSZDQD));
                return;
            case 2:
                CanJni.GMCarCtrl(21, Neg(this.mSBSetData.Dyjywz));
                return;
            case 3:
                CanJni.GMCarCtrl(22, Neg(this.mSBSetData.Jsyblxc));
                return;
            case 4:
                CanJni.GMCarCtrl(23, Neg(this.mSBSetData.Dchszdqx));
                return;
            case 5:
                CanJni.GMCarCtrl(24, Neg(this.mSBSetData.Zdhszd));
                return;
            case 6:
                CanJni.GMCarCtrl(25, Neg(this.mSBSetData.Zdys));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemDchyszdqd = this.mManager.addItemCheckBox(R.string.can_dcyhszdqd, 1, this);
        this.mItemDyjywz = this.mManager.addItemCheckBox(R.string.can_dyjywz, 2, this);
        this.mItemJsyblxc = this.mManager.addItemCheckBox(R.string.can_jsyblxc, 3, this);
        this.mItemDdhsjqx = this.mManager.addItemCheckBox(R.string.can_dchsjzdqx, 4, this);
        this.mItemHsjzdzd = this.mManager.addItemCheckBox(R.string.can_zdhsjzd, 5, this);
        this.mItemZdys = this.mManager.addItemCheckBox(R.string.can_zdys, 6, this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GmSbGetCarSetEx(this.mSBSetData);
        CanJni.GmSbGetCarSetExAdt(this.mSBAdtData);
        CanJni.GMGetCarSet(this.mSetData);
        CanJni.GMGetAdtConv(this.mAdtData);
        if (i2b(this.mSBAdtData.UpdateOnce) && (!check || i2b(this.mSBAdtData.Update))) {
            this.mSBAdtData.Update = 0;
            LayoutUI();
            check = false;
        }
        if (i2b(this.mAdtData.UpdateOnce) && (!check || i2b(this.mAdtData.Update))) {
            this.mAdtData.Update = 0;
            LayoutUI();
            check = false;
        }
        if (i2b(this.mSBSetData.UpdateOnce) && (!check || i2b(this.mSBSetData.Update))) {
            this.mSBSetData.Update = 0;
            this.mItemDyjywz.SetCheck(this.mSBSetData.Dyjywz);
            this.mItemJsyblxc.SetCheck(this.mSBSetData.Jsyblxc);
            this.mItemDdhsjqx.SetCheck(this.mSBSetData.Dchszdqx);
            this.mItemHsjzdzd.SetCheck(this.mSBSetData.Zdhszd);
            this.mItemZdys.SetCheck(this.mSBSetData.Zdys);
        }
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemDchyszdqd.SetCheck(this.mSetData.DCYHSZDQD);
        }
    }

    private void LayoutUI() {
        for (int i = 1; i <= 6; i++) {
            ShowItem(i);
        }
    }

    private void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemDchyszdqd.ShowGone(show);
                return;
            case 2:
                this.mItemDyjywz.ShowGone(show);
                return;
            case 3:
                this.mItemJsyblxc.ShowGone(show);
                return;
            case 4:
                this.mItemDdhsjqx.ShowGone(show);
                return;
            case 5:
                this.mItemHsjzdzd.ShowGone(show);
                return;
            case 6:
                this.mItemZdys.ShowGone(show);
                return;
            default:
                return;
        }
    }

    private boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.DCYHSZDQD;
                break;
            case 2:
                ret = this.mSBAdtData.Dyjywz;
                break;
            case 3:
                ret = this.mSBAdtData.Jsyblxc;
                break;
            case 4:
                ret = this.mSBAdtData.Dchszdqx;
                break;
            case 5:
                ret = this.mSBAdtData.Zdhszd;
                break;
            case 6:
                ret = this.mSBAdtData.Zdys;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
