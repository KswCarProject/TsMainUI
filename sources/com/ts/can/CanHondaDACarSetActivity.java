package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanHondaDACarSetActivity extends CanBaseActivity implements View.OnClickListener, CanItemPopupList.onPopItemClick, UserCallBack {
    public static final int ITEM_ADJUSTALARMVOL = 1;
    public static final int ITEM_AUTOHEADLIGHT = 7;
    public static final int ITEM_FUELEFFICIENCYL = 2;
    public static final int ITEM_NEWMESSAGE = 3;
    public static final int ITEM_SPEEDDISTANCE = 4;
    public static final int ITEM_TACHOMETER = 5;
    public static final int ITEM_WALKAWAY = 6;
    public static final String TAG = "CanHondaDACarSetActivity";
    private static final int[] mAjustalarmArr = {R.string.can_sensitivity_high, R.string.can_sensitivity_mid, R.string.can_sensitivity_low};
    private static final int[] mSpeedDistanceArr = {R.string.can_speeddistanceunits_1, R.string.can_speeddistanceunits_2};
    protected CanItemPopupList mItemAdjustalarm;
    protected CanItemSwitchList mItemAutoHeadlight;
    protected CanItemSwitchList mItemFuelefficiency;
    protected CanItemSwitchList mItemNewMessage;
    protected CanItemPopupList mItemSpeedDistance;
    protected CanItemSwitchList mItemTachometer;
    protected CanItemSwitchList mItemWalkLock;
    protected CanScrollList mManager;
    protected CanDataInfo.HondaSetData mSetData = new CanDataInfo.HondaSetData();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
        this.mItemAdjustalarm = AddPopupItem(R.string.can_adjustalarmvolume, mAjustalarmArr, 1);
        this.mItemFuelefficiency = AddCheckItem(R.string.can_fuelefficiencybacklight, 2);
        this.mItemNewMessage = AddCheckItem(R.string.can_newmessage, 3);
        this.mItemSpeedDistance = AddPopupItem(R.string.can_speeddistanceunits, mSpeedDistanceArr, 4);
        this.mItemTachometer = AddCheckItem(R.string.can_tachometer, 5);
        this.mItemWalkLock = AddCheckItem(R.string.can_walkawayautolock, 6);
        this.mItemAutoHeadlight = AddCheckItem(R.string.can_autoheadlinghtonwitchwiper, 7);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.HondaDACarSet(19, Neg(this.mSetData.jnmsdbjzm));
                return;
            case 3:
                CanJni.HondaDACarSet(20, Neg(this.mSetData.xxxtx));
                return;
            case 5:
                CanJni.HondaDACarSet(22, Neg(this.mSetData.tachometer));
                return;
            case 6:
                CanJni.HondaDACarSet(23, Neg(this.mSetData.lkszgxhsz));
                return;
            case 7:
                CanJni.HondaDACarSet(28, Neg(this.mSetData.yshzdddldgxhsd));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.HondaDACarSet(18, item);
                return;
            case 4:
                CanJni.HondaDACarSet(21, item);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.HondaDAGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.CarSetUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.CarSetUpdate)) {
            this.mSetData.CarSetUpdate = 0;
            this.mItemAdjustalarm.SetSel(this.mSetData.tzbjyl);
            this.mItemFuelefficiency.SetCheck(this.mSetData.jnmsdbjzm);
            this.mItemNewMessage.SetCheck(this.mSetData.xxxtx);
            this.mItemSpeedDistance.SetSel(this.mSetData.speeddisunit);
            this.mItemTachometer.SetCheck(this.mSetData.tachometer);
            this.mItemWalkLock.SetCheck(this.mSetData.lkszgxhsz);
            this.mItemAutoHeadlight.SetCheck(this.mSetData.yshzdddldgxhsd);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
