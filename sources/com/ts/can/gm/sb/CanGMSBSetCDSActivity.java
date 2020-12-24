package com.ts.can.gm.sb;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanGMSBSetCDSActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    public static final int ITEM_CDBHJS = 7;
    public static final int ITEM_CYMQBJ = 1;
    public static final int ITEM_HFCLTGJS = 6;
    private static final int ITEM_MAX = 7;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_QCZTTZ = 2;
    public static final int ITEM_TSLX = 5;
    public static final int ITEM_ZDFZZB = 3;
    public static final int ITEM_ZSYXHZDTX = 4;
    private static final int[] mQczttzArray = {R.string.can_off, R.string.can_on};
    private static final int[] mTxlxArray = {R.string.can_bjying, R.string.can_aqtszy};
    private static final int[] mZdfzzbArr = {R.string.can_off, R.string.can_baojing, R.string.can_bjhzd};
    private CanDataInfo.GM_AdtPzjc mAdtPzjcData = new CanDataInfo.GM_AdtPzjc();
    private CanItemSwitchList mItemCdbhjs;
    private CanItemSwitchList mItemCymqbj;
    private CanItemSwitchList mItemHfcltgjs;
    private CanItemPopupList mItemQczttz;
    private CanItemPopupList mItemTslx;
    private CanItemPopupList mItemZdfzzb;
    private CanItemSwitchList mItemZsyxhzdtx;
    private CanScrollList mManager;
    private CanDataInfo.GmSb_CarSetAdtEx mSBAdtData = new CanDataInfo.GmSb_CarSetAdtEx();
    private CanDataInfo.GmSb_CarSetEx mSBSetData = new CanDataInfo.GmSb_CarSetEx();
    private CanDataInfo.GM_CarSet mSetData = new CanDataInfo.GM_CarSet();

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.GMCarCtrl(15, Neg(this.mSetData.CYMQJB));
                return;
            case 4:
                CanJni.GMCarCtrl(34, Neg(this.mSBSetData.Zsyxhqdtx));
                return;
            case 6:
                CanJni.GMCarCtrl(19, Neg(this.mSBSetData.Hfcltgts));
                return;
            case 7:
                CanJni.GMCarCtrl(20, Neg(this.mSBSetData.Cdbhts));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.GMCarCtrl(18, item);
                return;
            case 3:
                CanJni.GMCarCtrl(17, item);
                return;
            case 5:
                CanJni.GMCarCtrl(16, item);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemCymqbj = this.mManager.addItemCheckBox(R.string.can_cymqbj, 1, this);
        this.mItemTslx = this.mManager.addItemPopupList(R.string.can_tslx, mTxlxArray, 5, (CanItemPopupList.onPopItemClick) this);
        this.mItemZdfzzb = this.mManager.addItemPopupList(R.string.can_zdfzzb, mZdfzzbArr, 3, (CanItemPopupList.onPopItemClick) this);
        this.mItemHfcltgjs = this.mManager.addItemCheckBox(R.string.can_hfcltgjs, 6, this);
        this.mItemCdbhjs = this.mManager.addItemCheckBox(R.string.can_cdbhjs, 7, this);
        this.mItemQczttz = this.mManager.addItemPopupList(R.string.can_qczttz, mQczttzArray, 2, (CanItemPopupList.onPopItemClick) this);
        this.mItemZsyxhzdtx = this.mManager.addItemCheckBox(R.string.can_gl8_2017_zsyxhzdtx, 4, this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GMGetCarSet(this.mSetData);
        CanJni.GMGetAdtPzjc(this.mAdtPzjcData);
        CanJni.GmSbGetCarSetEx(this.mSBSetData);
        CanJni.GmSbGetCarSetExAdt(this.mSBAdtData);
        if (i2b(this.mAdtPzjcData.UpdateOnce) && (!check || i2b(this.mAdtPzjcData.Update))) {
            this.mAdtPzjcData.Update = 0;
            LayoutUI();
            check = false;
        }
        if (i2b(this.mSBAdtData.UpdateOnce) && (!check || i2b(this.mSBAdtData.Update))) {
            this.mSBAdtData.Update = 0;
            LayoutUI();
        }
        if (i2b(this.mSetData.UpdateOnce) && (!check || i2b(this.mSetData.Update))) {
            this.mSetData.Update = 0;
            this.mItemCymqbj.SetCheck(this.mSetData.CYMQJB);
        }
        if (!i2b(this.mSBSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSBSetData.Update)) {
            this.mSBSetData.Update = 0;
            this.mItemZdfzzb.SetSel(this.mSBSetData.Zdfzzb);
            this.mItemZsyxhzdtx.SetCheck(this.mSBSetData.Zsyxhqdtx);
            this.mItemHfcltgjs.SetCheck(this.mSBSetData.Hfcltgts);
            this.mItemCdbhjs.SetCheck(this.mSBSetData.Cdbhts);
        }
    }

    private void LayoutUI() {
        for (int i = 1; i <= 7; i++) {
            ShowItem(i);
        }
    }

    private void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemCymqbj.ShowGone(show);
                return;
            case 2:
                this.mItemQczttz.ShowGone(show);
                return;
            case 3:
                this.mItemZdfzzb.ShowGone(show);
                return;
            case 4:
                this.mItemZsyxhzdtx.ShowGone(show);
                return;
            case 5:
                this.mItemTslx.ShowGone(show);
                return;
            case 6:
                this.mItemHfcltgjs.ShowGone(show);
                return;
            case 7:
                this.mItemCdbhjs.ShowGone(show);
                return;
            default:
                return;
        }
    }

    private boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtPzjcData.CYMQJB;
                break;
            case 2:
                ret = this.mSBAdtData.Qczttz;
                break;
            case 3:
                ret = this.mSBAdtData.Zdfzzb;
                break;
            case 4:
                ret = this.mSBAdtData.Zsyxhqdtx;
                break;
            case 5:
                ret = this.mSBAdtData.TslxODI;
                break;
            case 6:
                ret = this.mSBAdtData.Hfcltgts;
                break;
            case 7:
                ret = this.mSBAdtData.Cdbhts;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
