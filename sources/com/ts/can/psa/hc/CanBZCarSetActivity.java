package com.ts.can.psa.hc;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollBaseActivity;

public class CanBZCarSetActivity extends CanScrollBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    protected static final int ITEM_CMJSFS = 7;
    protected static final int ITEM_DDYCXM = 5;
    protected static final int ITEM_HYSQ = 3;
    protected static final int ITEM_MAX = 11;
    protected static final int ITEM_MIN = 2;
    protected static final int ITEM_RXD = 6;
    protected static final int ITEM_SDZXDD = 10;
    protected static final int ITEM_TPMS = 11;
    protected static final int ITEM_XLXG = 4;
    protected static final int ITEM_YBZM = 9;
    protected static final int ITEM_ZCFZ = 2;
    protected static final int ITEM_ZDZC = 8;
    public static final String TAG = "CanBZCarSetActivity";
    protected static final int[] mCmjsfsArr = {R.string.can_sym, R.string.can_jsym};
    protected static final int[] mDdycxmArr = {R.string.can_off, R.string.can_15s, R.string.can_30s, R.string.can_60s};
    protected static final int[] mHysqArr = {R.string.can_set_disable, R.string.can_set_enable};
    protected static final int[] mTpmsArr = {R.string.can_rpa_cancel_reset, R.string.can_rpa_reset};
    protected static final int[] mXlxgArr = {R.string.can_set_disable, R.string.can_set_enable};
    protected static final int[] mYbzmArr = {R.string.can_off, R.string.can_15s, R.string.can_30s, R.string.can_60s};
    protected static final int[] mZcfzArr = {R.string.can_set_disable, R.string.can_set_enable};
    protected static final int[] mZdzcArr = {R.string.can_set_disable, R.string.can_set_enable};
    private CanDataInfo.PeugAdt mAdtData = new CanDataInfo.PeugAdt();
    protected CanItemPopupList mItemCmjsfs;
    protected CanItemPopupList mItemDdycxm;
    protected CanItemPopupList mItemHysq;
    protected CanItemSwitchList mItemRxd;
    protected CanItemSwitchList mItemSdzxdd;
    protected CanItemPopupList mItemTpms;
    protected CanItemPopupList mItemXlxg;
    protected CanItemPopupList mItemYbzm;
    protected CanItemPopupList mItemZcfz;
    protected CanItemPopupList mItemZdzc;
    protected CanDataInfo.PeugSet mSetData = new CanDataInfo.PeugSet();
    protected boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mItemZcfz = AddPopup((CanItemPopupList.onPopItemClick) this, R.string.can_car_zcfz, mZcfzArr, 2);
        this.mItemHysq = AddPopup((CanItemPopupList.onPopItemClick) this, R.string.can_dcs_hys, mHysqArr, 3);
        this.mItemXlxg = AddPopup((CanItemPopupList.onPopItemClick) this, R.string.can_xlxgsd, mXlxgArr, 4);
        this.mItemDdycxm = AddPopup((CanItemPopupList.onPopItemClick) this, R.string.can_car_ddycxm, mDdycxmArr, 5);
        this.mItemRxd = AddSwitch((View.OnClickListener) this, R.string.can_light_rxd, 6);
        this.mItemCmjsfs = AddPopup((CanItemPopupList.onPopItemClick) this, R.string.can_cmjsfs, mCmjsfsArr, 7);
        this.mItemZdzc = AddPopup((CanItemPopupList.onPopItemClick) this, R.string.can_zdzc, mZdzcArr, 8);
        this.mItemYbzm = AddPopup((CanItemPopupList.onPopItemClick) this, R.string.can_ybzm, mYbzmArr, 9);
        this.mItemSdzxdd = AddSwitch((View.OnClickListener) this, R.string.can_zxd, 10);
        this.mItemTpms = AddPopup((CanItemPopupList.onPopItemClick) this, R.string.can_tpms_reset_notice, mTpmsArr, 11);
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.BZGetSetup(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemZcfz.SetSel(this.mSetData.fgZcfz);
            this.mItemHysq.SetSel(this.mSetData.fgRearWiper);
            this.mItemXlxg.SetSel(this.mSetData.Xlxg);
            this.mItemDdycxm.SetSel(this.mSetData.Bwhj);
            this.mItemRxd.SetCheck(this.mSetData.fgRxd);
            this.mItemCmjsfs.SetSel(this.mSetData.DoorOpt);
            this.mItemZdzc.SetSel(this.mSetData.Zdzc);
            this.mItemYbzm.SetSel(this.mSetData.Ybzm);
            this.mItemSdzxdd.SetCheck(this.mSetData.Sdzxdd);
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
        Log.d(TAG, "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 2; i <= 11; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 1;
        switch (item) {
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
                if (CanJni.GetSubType() != 4) {
                    ret = 0;
                    break;
                } else {
                    ret = 1;
                    break;
                }
            case 10:
                if (CanJni.GetSubType() != 4) {
                    ret = 0;
                    break;
                } else {
                    ret = 1;
                    break;
                }
            case 11:
                ret = 1;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 2:
                this.mItemZcfz.ShowGone(show);
                return;
            case 3:
                this.mItemHysq.ShowGone(show);
                return;
            case 4:
                this.mItemXlxg.ShowGone(show);
                return;
            case 5:
                this.mItemDdycxm.ShowGone(show);
                return;
            case 6:
                this.mItemRxd.ShowGone(show);
                return;
            case 7:
                this.mItemCmjsfs.ShowGone(show);
                return;
            case 8:
                this.mItemZdzc.ShowGone(show);
                return;
            case 9:
                this.mItemYbzm.ShowGone(show);
                return;
            case 10:
                this.mItemSdzxdd.ShowGone(show);
                return;
            case 11:
                this.mItemTpms.ShowGone(show);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 6:
                CarSet(8, Neg(this.mSetData.fgRxd));
                return;
            case 10:
                CarSet(12, Neg(this.mSetData.Sdzxdd));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CarSet(1, item);
                return;
            case 3:
                CarSet(2, item);
                return;
            case 4:
                CarSet(3, item);
                return;
            case 5:
                CarSet(4, item);
                return;
            case 7:
                CarSet(9, item);
                return;
            case 8:
                CarSet(10, item);
                return;
            case 9:
                CarSet(11, item);
                return;
            case 11:
                CanJni.BZTpmsSet(item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int para) {
        CanJni.BZCarSet(cmd, para);
    }
}
