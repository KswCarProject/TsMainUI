package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanScrollList;

public class CanHondDACarDistanceActivity extends CanBaseActivity implements CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange, UserCallBack {
    public static final int ITEM_ADJUSTOUTSIDETEMP = 3;
    public static final int ITEM_RYXHLXSSDBJZM = 4;
    public static final int ITEM_TRIPARESETTIMING = 2;
    public static final int ITEM_TRIPBRESETTIMING = 1;
    public static final String TAG = "CanHondDACarDistanceActivity";
    private static final int[] mTripRYXHLXSSDBJZM = {R.string.can_trunk_close, R.string.can_trunk_open};
    private static final int[] mTripResetTimingArr = {R.string.can_tripbresettiming_1, R.string.can_tripbresettiming_2, R.string.can_tripbresettiming_3};
    private CanItemProgressList mItemAdjustOutsideTemp;
    protected CanItemPopupList mItemRYXHDBJZM;
    protected CanItemPopupList mItemTripAReset;
    protected CanItemPopupList mItemTripBReset;
    protected CanScrollList mManager;
    protected CanDataInfo.HondaSetData mSetData = new CanDataInfo.HondaSetData();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
        this.mItemTripBReset = AddPopupItem(R.string.can_tripbresettiming, mTripResetTimingArr, 1);
        this.mItemTripAReset = AddPopupItem(R.string.can_triparesettiming, mTripResetTimingArr, 2);
        this.mItemAdjustOutsideTemp = new CanItemProgressList((Context) this, R.string.can_adjustoutsidetemp);
        this.mItemAdjustOutsideTemp.SetMinMax(0, 6);
        this.mItemAdjustOutsideTemp.SetIdCallBack(3, this);
        this.mManager.AddView(this.mItemAdjustOutsideTemp.GetView());
        this.mItemRYXHDBJZM = AddPopupItem(R.string.can_rybjtm, mTripRYXHLXSSDBJZM, 4);
        if (CanJni.GetSubType() != 6 && CanJni.GetSubType() != 7 && CanJni.GetSubType() != 12 && CanJni.GetSubType() != 11) {
            this.mItemRYXHDBJZM.ShowGone(false);
        }
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

    public void onChanged(int id, int pos) {
        if (id == 3) {
            CanJni.HondaDACarSet(0, pos);
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.HondaDACarSet(3, item);
                return;
            case 2:
                CanJni.HondaDACarSet(2, item);
                return;
            case 4:
                CanJni.HondaDACarSet(1, item);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.HondaDAGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.DistanceUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.DistanceUpdate)) {
            this.mSetData.DistanceUpdate = 0;
            this.mItemTripBReset.SetSel(this.mSetData.TripBReset);
            this.mItemTripAReset.SetSel(this.mSetData.TripAReset);
            this.mItemAdjustOutsideTemp.SetCurVal(this.mSetData.AdjustTemp);
            this.mItemAdjustOutsideTemp.SetValText(new StringBuilder(String.valueOf(this.mSetData.AdjustTemp - 3)).toString());
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
