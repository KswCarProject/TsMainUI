package com.ts.can.ford.fiesta;

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
import com.ts.canview.CanScrollBaseActivity;

public class CanOldFiestaSetActivity extends CanScrollBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    protected static final int ITEM_HJD = 6;
    protected static final int ITEM_INFO = 5;
    protected static final int ITEM_LCDW = 2;
    protected static final int ITEM_MAX = 6;
    protected static final int ITEM_MIN = 2;
    protected static final int ITEM_WARN = 4;
    protected static final int ITEM_ZXD = 3;
    public static final String TAG = "CanOldFiestaSetActivity";
    protected static final int[] mHjdArr = {R.string.can_off, R.string.can_on};
    protected static final int[] mInfoArr = {R.string.can_off, R.string.can_on};
    protected static final int[] mLcdwArr = new int[0];
    protected static final int[] mWarnArr = {R.string.can_off, R.string.can_on};
    protected static final int[] mZxdArr = new int[0];
    protected CanItemPopupList mItemHjd;
    protected CanItemPopupList mItemInfo;
    protected CanItemPopupList mItemLcdw;
    protected CanItemPopupList mItemWarn;
    protected CanItemPopupList mItemZxd;
    protected CanDataInfo.FiestaSet mSetData = new CanDataInfo.FiestaSet();
    protected boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mItemLcdw = AddPopup((CanItemPopupList.onPopItemClick) this, 0, getResources().getStringArray(R.array.can_fist_l_c), 2);
        this.mItemZxd = AddPopup((CanItemPopupList.onPopItemClick) this, 0, getResources().getStringArray(R.array.can_fist_zxd), 3);
        this.mItemWarn = AddPopup((CanItemPopupList.onPopItemClick) this, 0, mWarnArr, 4);
        this.mItemInfo = AddPopup((CanItemPopupList.onPopItemClick) this, 0, mInfoArr, 5);
        this.mItemHjd = AddPopup((CanItemPopupList.onPopItemClick) this, 0, mHjdArr, 6);
        LayoutUI();
        String[] strArry = getResources().getStringArray(R.array.can_fist_set_title);
        this.mItemLcdw.GetBtn().setText(strArry[0]);
        this.mItemZxd.GetBtn().setText(strArry[1]);
        this.mItemWarn.GetBtn().setText(strArry[2]);
        this.mItemInfo.GetBtn().setText(strArry[3]);
        this.mItemHjd.GetBtn().setText(strArry[4]);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.OldFiestaGetSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemLcdw.SetSel(this.mSetData.ubLCDW);
            this.mItemZxd.SetSel(this.mSetData.ubZXD);
            this.mItemWarn.SetSel(this.mSetData.fgWarn);
            this.mItemInfo.SetSel(this.mSetData.fgInfo);
            this.mItemHjd.SetSel(this.mSetData.fgHJD);
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
        for (int i = 2; i <= 6; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        switch (item) {
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
        }
        return i2b(1);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 2:
                this.mItemLcdw.ShowGone(show);
                return;
            case 3:
                this.mItemZxd.ShowGone(show);
                return;
            case 4:
                this.mItemWarn.ShowGone(show);
                return;
            case 5:
                this.mItemInfo.ShowGone(show);
                return;
            case 6:
                this.mItemHjd.ShowGone(show);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CarSet(0, item);
                return;
            case 3:
                CarSet(1, item);
                return;
            case 4:
                CarSet(2, item);
                return;
            case 5:
                CarSet(3, item);
                return;
            case 6:
                CarSet(4, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int para) {
        CanJni.OldFiestaCarSet(cmd, para);
    }
}
