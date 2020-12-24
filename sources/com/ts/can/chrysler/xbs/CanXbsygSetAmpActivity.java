package com.ts.can.chrysler.xbs;

import android.content.Context;
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
import com.ts.canview.CanScrollList;
import com.ts.main.common.MainSet;

public class CanXbsygSetAmpActivity extends CanXbsygBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    private static final int ITEM_BAL_FRONT_REAR = 4;
    private static final int ITEM_BAL_LEFT_RIGHT = 3;
    private static final int ITEM_GF_TOGGLE = 8;
    public static final int ITEM_HRYX = 2;
    private static final int ITEM_MAX = 8;
    private static final int ITEM_MIN = 1;
    private static final int ITEM_VOL = 9;
    private static final int ITEM_VOL_HIGH = 7;
    private static final int ITEM_VOL_LOW = 5;
    private static final int ITEM_VOL_MIDDLE = 6;
    public static final int ITEM_ZSYCSTJ = 1;
    public static final String TAG = "CanRZygSetAmpActivity";
    private CanDataInfo.ChrOthAMPInfo mAmpInfo = new CanDataInfo.ChrOthAMPInfo();
    private CanItemProgressList mItemBalFrontRear;
    private CanItemProgressList mItemBalLtRt;
    private CanItemSwitchList mItemGFToggle;
    protected CanItemSwitchList mItemHryx;
    private CanItemProgressList mItemVol;
    private CanItemProgressList mItemVolHigh;
    private CanItemProgressList mItemVolLow;
    private CanItemProgressList mItemVolMiddle;
    protected CanItemPopupList mItemZsycstj;
    private CanScrollList mManager;
    private String[] mZsycstjArr = {"", MainSet.SP_XPH5, MainSet.SP_RLF_KORON, MainSet.SP_XH_DMAX};
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetSetData();
        if (i2b(this.mSetData.AmpUpdateOnce) && (!check || i2b(this.mSetData.AmpUpdate))) {
            this.mSetData.AmpUpdate = 0;
            this.mItemZsycstj.SetSel(this.mSetData.AmpASL);
            this.mItemHryx.SetCheck(this.mSetData.AmpHyyx);
        }
        CanJni.CompassGetAmpInfo(this.mAmpInfo);
        if (!i2b(this.mAmpInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpInfo.Update)) {
            this.mAmpInfo.Update = 0;
            setValText(this.mAmpInfo.Bal, true);
            setValText(this.mAmpInfo.Fad, false);
            this.mItemVolLow.SetCurVal(this.mAmpInfo.Bas);
            this.mItemVolLow.SetValText(String.valueOf(this.mAmpInfo.Bas - 10));
            this.mItemVolMiddle.SetCurVal(this.mAmpInfo.Mid);
            this.mItemVolMiddle.SetValText(String.valueOf(this.mAmpInfo.Mid - 10));
            this.mItemVolHigh.SetCurVal(this.mAmpInfo.Tre);
            this.mItemVolHigh.SetValText(String.valueOf(this.mAmpInfo.Tre - 10));
            this.mItemGFToggle.SetCheck(this.mAmpInfo.PWROn);
            this.mItemVol.SetCurVal(this.mAmpInfo.Vol);
            this.mItemVol.SetValText(String.valueOf(this.mAmpInfo.Vol));
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query2(3);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        LayoutUI();
        ResetData(false);
        QueryData();
        Log.d("CanRZygSetAmpActivity", "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d("CanRZygSetAmpActivity", "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mZsycstjArr[0] = getResources().getString(R.string.can_off);
        this.mItemZsycstj = AddPopupItem(R.string.can_a_s_l, this.mZsycstjArr, 1);
        this.mItemHryx = AddCheckItem(R.string.can_around, 2);
        this.mItemBalLtRt = this.mManager.addItemProgressList(R.string.can_balance_left_right, 3, (CanItemProgressList.onPosChange) this);
        this.mItemBalLtRt.SetMinMax(1, 19);
        this.mItemBalLtRt.SetStep(1);
        this.mItemBalLtRt.SetUserValText();
        this.mItemBalFrontRear = this.mManager.addItemProgressList(R.string.can_balance_front_rear, 4, (CanItemProgressList.onPosChange) this);
        this.mItemBalFrontRear.SetMinMax(1, 19);
        this.mItemBalFrontRear.SetStep(1);
        this.mItemBalFrontRear.SetUserValText();
        this.mItemVolLow = this.mManager.addItemProgressList(R.string.can_vol_low, 5, (CanItemProgressList.onPosChange) this);
        this.mItemVolLow.SetMinMax(1, 19);
        this.mItemVolLow.SetStep(1);
        this.mItemVolLow.SetUserValText();
        this.mItemVolMiddle = this.mManager.addItemProgressList(R.string.can_vol_middle, 6, (CanItemProgressList.onPosChange) this);
        this.mItemVolMiddle.SetMinMax(1, 19);
        this.mItemVolMiddle.SetStep(1);
        this.mItemVolMiddle.SetUserValText();
        this.mItemVolHigh = this.mManager.addItemProgressList(R.string.can_vol_high, 7, (CanItemProgressList.onPosChange) this);
        this.mItemVolHigh.SetMinMax(1, 19);
        this.mItemVolHigh.SetStep(1);
        this.mItemVolHigh.SetUserValText();
        this.mItemGFToggle = this.mManager.addItemCheckBox(R.string.can_gf_toggle, 8, this);
        this.mItemVol = this.mManager.addItemProgressList(R.string.can_vol, 9, (CanItemProgressList.onPosChange) this);
        this.mItemVol.SetMinMax(0, 38);
        this.mItemVol.SetStep(1);
        this.mItemVol.SetUserValText();
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        GetAdtData();
        for (int i = 1; i <= 8; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.AmpASL;
                break;
            case 2:
                ret = this.mAdtData.AmpHyyx;
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
                ret = 0;
                break;
            case 9:
                ret = 1;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemZsycstj.ShowGone(show);
                return;
            case 2:
                this.mItemHryx.ShowGone(show);
                return;
            case 3:
                this.mItemBalLtRt.ShowGone(show);
                return;
            case 4:
                this.mItemBalFrontRear.ShowGone(show);
                return;
            case 5:
                this.mItemVolLow.ShowGone(show);
                return;
            case 6:
                this.mItemVolMiddle.ShowGone(show);
                return;
            case 7:
                this.mItemVolHigh.ShowGone(show);
                return;
            case 8:
                this.mItemGFToggle.ShowGone(show);
                return;
            case 9:
                this.mItemVol.ShowGone(show);
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
    public CanItemPopupList AddPopupItem(int text, String[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                AmpSet(8, Neg(this.mSetData.AmpHyyx));
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 3:
                AmpSet(5, pos);
                return;
            case 4:
                AmpSet(6, pos);
                return;
            case 5:
                AmpSet(2, pos);
                return;
            case 6:
                AmpSet(3, pos);
                return;
            case 7:
                AmpSet(4, pos);
                return;
            case 9:
                AmpSet(1, pos);
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
            case 1:
                AmpSet(7, item);
                return;
            default:
                return;
        }
    }

    public void AmpSet(int cmd, int para) {
        CanJni.ChrXbsSetAudio(cmd, para);
    }

    private void setValText(int val, boolean isLtRt) {
        if (isLtRt) {
            this.mItemBalLtRt.SetCurVal(val);
        } else {
            this.mItemBalFrontRear.SetCurVal(val);
        }
        if (isLtRt) {
            if (val == 10) {
                this.mItemBalLtRt.SetValText("0");
            } else if (val < 10) {
                this.mItemBalLtRt.SetValText("L" + String.valueOf(10 - val));
            } else if (val > 10) {
                this.mItemBalLtRt.SetValText("R" + String.valueOf(val - 10));
            }
        } else if (val == 10) {
            this.mItemBalFrontRear.SetValText("0");
        } else if (val < 10) {
            this.mItemBalFrontRear.SetValText("F" + String.valueOf(10 - val));
        } else if (val > 10) {
            this.mItemBalFrontRear.SetValText("R" + String.valueOf(val - 10));
        }
    }
}
