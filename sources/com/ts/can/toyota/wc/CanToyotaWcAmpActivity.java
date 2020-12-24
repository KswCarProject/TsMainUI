package com.ts.can.toyota.wc;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanToyotaWcAmpActivity extends CanToyotaWCBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    private static final int ITEM_BAL_FRONT_REAR = 4;
    private static final int ITEM_BAL_LEFT_RIGHT = 3;
    public static final int ITEM_HRYX = 2;
    private static final int ITEM_MAX = 8;
    private static final int ITEM_MIN = 1;
    private static final int ITEM_VOLUME = 8;
    private static final int ITEM_VOL_HIGH = 7;
    private static final int ITEM_VOL_LOW = 5;
    private static final int ITEM_VOL_MIDDLE = 6;
    public static final int ITEM_ZSYCSTJ = 1;
    public static final String TAG = "CanToyotaWcAmpActivity";
    private CanItemProgressList mItemBalFrontRear;
    private CanItemProgressList mItemBalLtRt;
    protected CanItemSwitchList mItemHryx;
    private CanItemProgressList mItemVolHigh;
    private CanItemProgressList mItemVolLow;
    private CanItemProgressList mItemVolMiddle;
    private CanItemProgressList mItemVolume;
    protected CanItemSwitchList mItemZsycstj;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetAmpData();
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            setValText(this.mAmpData.Bal, true);
            setValText(this.mAmpData.Fad, false);
            this.mItemVolLow.SetCurVal(this.mAmpData.Bas);
            this.mItemVolLow.SetValText(String.valueOf(this.mAmpData.Bas));
            this.mItemVolMiddle.SetCurVal(this.mAmpData.Mid);
            this.mItemVolMiddle.SetValText(String.valueOf(this.mAmpData.Mid));
            this.mItemVolHigh.SetCurVal(this.mAmpData.Tre);
            this.mItemVolHigh.SetValText(String.valueOf(this.mAmpData.Tre));
            this.mItemVolume.SetCurVal(this.mAmpData.Vol);
            this.mItemVolume.SetValText(String.valueOf(this.mAmpData.Vol));
            this.mItemZsycstj.SetCheck(this.mAmpData.VolLinkSpeed);
            this.mItemHryx.SetCheck(this.mAmpData.Surround);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        LayoutUI();
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
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemZsycstj = AddCheckItem(R.string.can_a_s_l, 1);
        this.mItemHryx = AddCheckItem(R.string.can_around, 2);
        this.mItemBalLtRt = this.mManager.addItemProgressList(R.string.can_balance_left_right, 3, (CanItemProgressList.onPosChange) this);
        this.mItemBalLtRt.SetMinMax(0, 14);
        this.mItemBalLtRt.SetStep(1);
        this.mItemBalLtRt.SetUserValText();
        this.mItemBalFrontRear = this.mManager.addItemProgressList(R.string.can_balance_front_rear, 4, (CanItemProgressList.onPosChange) this);
        this.mItemBalFrontRear.SetMinMax(0, 14);
        this.mItemBalFrontRear.SetStep(1);
        this.mItemBalFrontRear.SetUserValText();
        this.mItemVolLow = this.mManager.addItemProgressList(R.string.can_vol_low, 5, (CanItemProgressList.onPosChange) this);
        this.mItemVolLow.SetMinMax(0, 10);
        this.mItemVolLow.SetStep(1);
        this.mItemVolLow.SetUserValText();
        this.mItemVolMiddle = this.mManager.addItemProgressList(R.string.can_vol_middle, 6, (CanItemProgressList.onPosChange) this);
        this.mItemVolMiddle.SetMinMax(0, 10);
        this.mItemVolMiddle.SetStep(1);
        this.mItemVolMiddle.SetUserValText();
        this.mItemVolHigh = this.mManager.addItemProgressList(R.string.can_vol_high, 7, (CanItemProgressList.onPosChange) this);
        this.mItemVolHigh.SetMinMax(0, 10);
        this.mItemVolHigh.SetStep(1);
        this.mItemVolHigh.SetUserValText();
        this.mItemVolume = this.mManager.addItemProgressList(R.string.can_vol, 8, (CanItemProgressList.onPosChange) this);
        this.mItemVolume.SetMinMax(0, 63);
        this.mItemVolume.SetStep(1);
        this.mItemVolume.SetUserValText();
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 8; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        switch (item) {
            case 1:
                this.mItemZsycstj.ShowGone(true);
                return;
            case 2:
                this.mItemHryx.ShowGone(true);
                return;
            case 3:
                this.mItemBalLtRt.ShowGone(true);
                return;
            case 4:
                this.mItemBalFrontRear.ShowGone(true);
                return;
            case 5:
                this.mItemVolLow.ShowGone(true);
                return;
            case 6:
                this.mItemVolMiddle.ShowGone(true);
                return;
            case 7:
                this.mItemVolHigh.ShowGone(true);
                return;
            case 8:
                this.mItemVolume.ShowGone(true);
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
            case 1:
                CanJni.ToyotaWcAmpSet(7, Neg(this.mAmpData.VolLinkSpeed));
                return;
            case 2:
                CanJni.ToyotaWcAmpSet(8, Neg(this.mAmpData.Surround));
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 3:
                int val = Math.abs(this.mAmpData.Bal - pos);
                if (val > 1) {
                    val = 1;
                }
                if (this.mAmpData.Bal > pos) {
                    CanJni.ToyotaWcAmpSet(2, this.mAmpData.Bal - val);
                    return;
                } else {
                    CanJni.ToyotaWcAmpSet(2, this.mAmpData.Bal + val);
                    return;
                }
            case 4:
                int val2 = Math.abs(this.mAmpData.Fad - pos);
                if (val2 > 1) {
                    val2 = 1;
                }
                if (this.mAmpData.Fad > pos) {
                    CanJni.ToyotaWcAmpSet(3, this.mAmpData.Fad - val2);
                    return;
                } else {
                    CanJni.ToyotaWcAmpSet(3, this.mAmpData.Fad + val2);
                    return;
                }
            case 5:
                int val3 = Math.abs(this.mAmpData.Bas - pos);
                if (val3 > 1) {
                    val3 = 1;
                }
                if (this.mAmpData.Bas > pos) {
                    CanJni.ToyotaWcAmpSet(4, this.mAmpData.Bas - val3);
                    return;
                } else {
                    CanJni.ToyotaWcAmpSet(4, this.mAmpData.Bas + val3);
                    return;
                }
            case 6:
                int val4 = Math.abs(this.mAmpData.Mid - pos);
                if (val4 > 1) {
                    val4 = 1;
                }
                if (this.mAmpData.Mid > pos) {
                    CanJni.ToyotaWcAmpSet(5, this.mAmpData.Mid - val4);
                    return;
                } else {
                    CanJni.ToyotaWcAmpSet(5, this.mAmpData.Mid + val4);
                    return;
                }
            case 7:
                int val5 = Math.abs(this.mAmpData.Tre - pos);
                if (val5 > 1) {
                    val5 = 1;
                }
                if (this.mAmpData.Tre > pos) {
                    CanJni.ToyotaWcAmpSet(6, this.mAmpData.Tre - val5);
                    return;
                } else {
                    CanJni.ToyotaWcAmpSet(6, this.mAmpData.Tre + val5);
                    return;
                }
            case 8:
                int val6 = Math.abs(this.mAmpData.Vol - pos);
                if (val6 > 5) {
                    val6 = 5;
                }
                if (this.mAmpData.Vol > pos) {
                    CanJni.ToyotaWcAmpSet(1, -val6);
                    return;
                } else {
                    CanJni.ToyotaWcAmpSet(1, val6);
                    return;
                }
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
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

    public void onItem(int id, int item) {
    }
}
