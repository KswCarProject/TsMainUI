package com.ts.can.renault.kadjar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemFsSetList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollBaseActivity;

public class CanKadjarUserSetActivity extends CanScrollBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange, CanItemFsSetList.onFsSetClick {
    protected static final int ITEM_CMZDSS = 5;
    protected static final int ITEM_DCSHYSKQ = 2;
    protected static final int ITEM_FWD = 8;
    protected static final int ITEM_FWDQD = 12;
    protected static final int ITEM_FWDYS = 11;
    protected static final int ITEM_HBQY = 10;
    protected static final int ITEM_HBZSQ = 1;
    protected static final int ITEM_MAX = 14;
    protected static final int ITEM_MIN = 1;
    protected static final int ITEM_QBQY = 9;
    protected static final int ITEM_RESET = 14;
    protected static final int ITEM_TSYL = 6;
    protected static final int ITEM_WBHYD = 4;
    protected static final int ITEM_YJYJ = 13;
    protected static final int ITEM_ZDZCD = 3;
    protected static final int ITEM_ZNYSXT = 7;
    public static final String TAG = "CanKadjarUserSetActivity";
    protected static final int[] mColorArr = {R.string.can_magoten_light_color_2, R.string.can_color_red, R.string.can_color_blue, R.string.can_purple, R.string.can_orange_color};
    protected static final String[] mTsylArr = {"1", "2", "3"};
    protected CanItemSwitchList mItemCmzdss;
    protected CanItemSwitchList mItemDcshyskq;
    protected CanItemSwitchList mItemFwd;
    protected CanItemProgressList mItemFwdqd;
    protected CanItemPopupList mItemFwdys;
    protected CanItemSwitchList mItemHbqy;
    protected CanItemSwitchList mItemHbzsq;
    protected CanItemSwitchList mItemQbqy;
    protected CanItemFsSetList mItemReset;
    protected CanItemPopupList mItemTsyl;
    protected CanItemSwitchList mItemWbhyd;
    protected CanItemSwitchList mItemYjyj;
    protected CanItemSwitchList mItemZdzcd;
    protected CanItemSwitchList mItemZnysxt;
    protected CanDataInfo.KadjarSet mSetData = new CanDataInfo.KadjarSet();
    protected boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mItemHbzsq = AddSwitch((View.OnClickListener) this, R.string.can_hbzsq, 1);
        this.mItemDcshyskq = AddSwitch((View.OnClickListener) this, R.string.can_dcshyskq, 2);
        this.mItemZdzcd = AddSwitch((View.OnClickListener) this, R.string.can_zdzcd, 3);
        this.mItemWbhyd = AddSwitch((View.OnClickListener) this, R.string.can_wbhyd, 4);
        this.mItemCmzdss = AddSwitch((View.OnClickListener) this, R.string.can_jsscmzdss, 5);
        this.mItemTsyl = AddPopup((CanItemPopupList.onPopItemClick) this, R.string.can_tsqyl, mTsylArr, 6);
        this.mItemZnysxt = AddSwitch((View.OnClickListener) this, R.string.can_znysxt, 7);
        this.mItemFwd = AddSwitch((View.OnClickListener) this, R.string.can_hant_fwd, 8);
        this.mItemQbqy = AddSwitch((View.OnClickListener) this, R.string.can_qbqy, 9);
        this.mItemHbqy = AddSwitch((View.OnClickListener) this, R.string.can_hbqy, 10);
        this.mItemFwdys = AddPopup((CanItemPopupList.onPopItemClick) this, R.string.can_fwd_color, mColorArr, 11);
        this.mItemFwdqd = AddProgress(this, R.string.can_fwd_value, 12);
        this.mItemFwdqd.SetMinMax(0, 100);
        this.mItemFwdqd.SetStep(5);
        this.mItemYjyj = AddSwitch((View.OnClickListener) this, R.string.can_yjyj, 13);
        this.mItemReset = AddFsSetItem(this, R.string.can_setup_reset, 14);
        this.mItemReset.SetMsgText(R.string.can_sure_reset);
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.KadjarGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemHbzsq.SetCheck(this.mSetData.Hbzsq);
            this.mItemDcshyskq.SetCheck(this.mSetData.Dcshyskq);
            this.mItemZdzcd.SetCheck(this.mSetData.Zdzcd);
            this.mItemWbhyd.SetCheck(this.mSetData.Wbhyd);
            this.mItemCmzdss.SetCheck(this.mSetData.Cmzdss);
            this.mItemTsyl.SetSel(this.mSetData.Tsyl - 1);
            this.mItemZnysxt.SetCheck(this.mSetData.Znysxt);
            this.mItemFwd.SetCheck(this.mSetData.Fwd);
            this.mItemQbqy.SetCheck(this.mSetData.Qbqy);
            this.mItemHbqy.SetCheck(this.mSetData.Hbqy);
            this.mItemFwdys.SetSel(this.mSetData.Fwdys - 1);
            this.mItemFwdqd.SetCurVal(this.mSetData.Fwdqd);
            this.mItemYjyj.SetCheck(this.mSetData.Yjyj);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.KadjarCarQuery(113, 0);
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
        for (int i = 1; i <= 14; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        switch (item) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 13:
                break;
            case 14:
                break;
        }
        return i2b(1);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean z = true;
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemHbzsq.ShowGone(show);
                return;
            case 2:
                this.mItemDcshyskq.ShowGone(show);
                return;
            case 3:
                this.mItemZdzcd.ShowGone(show);
                return;
            case 4:
                this.mItemWbhyd.ShowGone(show);
                return;
            case 5:
                this.mItemCmzdss.ShowGone(show);
                return;
            case 6:
                this.mItemTsyl.ShowGone(show);
                return;
            case 7:
                CanItemSwitchList canItemSwitchList = this.mItemZnysxt;
                if (CanJni.GetSubType() != 3) {
                    z = false;
                }
                canItemSwitchList.ShowGone(z);
                return;
            case 8:
                CanItemSwitchList canItemSwitchList2 = this.mItemFwd;
                if (CanJni.GetSubType() != 3) {
                    z = false;
                }
                canItemSwitchList2.ShowGone(z);
                return;
            case 9:
                CanItemSwitchList canItemSwitchList3 = this.mItemQbqy;
                if (CanJni.GetSubType() != 3) {
                    z = false;
                }
                canItemSwitchList3.ShowGone(z);
                return;
            case 10:
                CanItemSwitchList canItemSwitchList4 = this.mItemHbqy;
                if (CanJni.GetSubType() != 3) {
                    z = false;
                }
                canItemSwitchList4.ShowGone(z);
                return;
            case 11:
                CanItemPopupList canItemPopupList = this.mItemFwdys;
                if (CanJni.GetSubType() != 3) {
                    z = false;
                }
                canItemPopupList.ShowGone(z);
                return;
            case 12:
                CanItemProgressList canItemProgressList = this.mItemFwdqd;
                if (CanJni.GetSubType() != 3) {
                    z = false;
                }
                canItemProgressList.ShowGone(z);
                return;
            case 13:
                this.mItemYjyj.ShowGone(show);
                return;
            case 14:
                this.mItemReset.ShowGone(show);
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
            case 1:
                CanJni.KadjarCarSet(12, Neg(this.mSetData.Hbzsq));
                return;
            case 2:
                CanJni.KadjarCarSet(11, Neg(this.mSetData.Dcshyskq));
                return;
            case 3:
                CanJni.KadjarCarSet(10, Neg(this.mSetData.Zdzcd));
                return;
            case 4:
                CanJni.KadjarCarSet(9, Neg(this.mSetData.Wbhyd));
                return;
            case 5:
                CanJni.KadjarCarSet(6, Neg(this.mSetData.Cmzdss));
                return;
            case 7:
                CanJni.KadjarCarSet(20, Neg(this.mSetData.Znysxt));
                return;
            case 8:
                CanJni.KadjarCarSet(21, Neg(this.mSetData.Fwd));
                return;
            case 9:
                CanJni.KadjarCarSet(22, Neg(this.mSetData.Qbqy));
                return;
            case 10:
                CanJni.KadjarCarSet(23, Neg(this.mSetData.Hbqy));
                return;
            case 13:
                CanJni.KadjarCarSet(36, Neg(this.mSetData.Yjyj));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 6:
                CanJni.KadjarCarSet(7, item + 1);
                return;
            case 11:
                CanJni.KadjarCarSet(24, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 12:
                CanJni.KadjarCarSet(25, pos);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int para) {
    }

    public void onFsItem(int id, boolean sure) {
        if (sure) {
            CanJni.KadjarCarSet(128, 2);
            Sleep(10);
            CanJni.KadjarCarSet(128, 5);
        }
    }
}
