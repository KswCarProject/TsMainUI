package com.ts.can.vw.golf.wc;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemBlankTextList;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanScrollList;

public class CanGolfWcSetMWActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_FOLD_AWAY = 4;
    public static final int ITEM_LOWER_REV = 3;
    private static final int ITEM_MAX = 7;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_MIRROR_TITLE = 1;
    public static final int ITEM_REAR_WINDOW_GEAR = 7;
    public static final int ITEM_SYNC_ADJ = 2;
    public static final int ITEM_WIPER_TITLE = 5;
    public static final int ITEM_WIPE_IN_RAIN = 6;
    private CanItemCheckList mItemFoldAway;
    private CanItemCheckList mItemLowerRev;
    private CanItemBlankTextList mItemMirrorTitle;
    private CanItemCheckList mItemRearWidnowGear;
    private CanItemCheckList mItemSyncAdj;
    private CanItemCheckList mItemWipeInRain;
    private CanItemBlankTextList mItemWiperTitle;
    private CanScrollList mManager;
    private CanDataInfo.GolfWcMirrorWipers mWiperAdt = new CanDataInfo.GolfWcMirrorWipers();
    private CanDataInfo.GolfWcMirrorWipers mWiperData = new CanDataInfo.GolfWcMirrorWipers();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        boolean z = true;
        CanJni.GolfWcGetMirrorWipersData(1, this.mWiperAdt);
        CanJni.GolfWcGetMirrorWipersData(0, this.mWiperData);
        if (i2b(this.mWiperAdt.UpdateOnce) && (!check || i2b(this.mWiperAdt.Update))) {
            this.mWiperAdt.Update = 0;
            this.mItemSyncAdj.ShowGone(this.mWiperAdt.SyncAdjustment);
            this.mItemLowerRev.ShowGone(this.mWiperAdt.LowerWhileReversing);
            this.mItemFoldAway.ShowGone(this.mWiperAdt.FoldInWhenParked);
            this.mItemMirrorTitle.ShowGone((this.mWiperAdt.SyncAdjustment == 0 && this.mWiperAdt.LowerWhileReversing == 0 && this.mWiperAdt.FoldInWhenParked == 0) ? false : true);
            this.mItemWipeInRain.ShowGone(this.mWiperAdt.AutomaticWipingInRain);
            this.mItemRearWidnowGear.ShowGone(this.mWiperAdt.RearWindowWipingInReverseGear);
            CanItemBlankTextList canItemBlankTextList = this.mItemWiperTitle;
            if (this.mWiperAdt.AutomaticWipingInRain == 0 && this.mWiperAdt.RearWindowWipingInReverseGear == 0) {
                z = false;
            }
            canItemBlankTextList.ShowGone(z);
        }
        if (!i2b(this.mWiperData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mWiperData.Update)) {
            this.mWiperData.Update = 0;
            this.mItemSyncAdj.SetCheck(this.mWiperData.SyncAdjustment);
            this.mItemLowerRev.SetCheck(this.mWiperData.LowerWhileReversing);
            this.mItemFoldAway.SetCheck(this.mWiperData.FoldInWhenParked);
            this.mItemWipeInRain.SetCheck(this.mWiperData.AutomaticWipingInRain);
            this.mItemRearWidnowGear.SetCheck(this.mWiperData.RearWindowWipingInReverseGear);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GolfWcQueryData(1, 105);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        for (int i = 1; i <= 7; i++) {
            InitItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public void InitItem(int item) {
        switch (item) {
            case 1:
                this.mItemMirrorTitle = new CanItemBlankTextList((Context) this, R.string.can_mirrors);
                this.mItemMirrorTitle.ShowGone(false);
                this.mManager.AddView(this.mItemMirrorTitle.GetView());
                return;
            case 2:
                this.mItemSyncAdj = new CanItemCheckList(this, R.string.can_sync_adj);
                this.mItemSyncAdj.SetIdClickListener(this, 2);
                this.mItemSyncAdj.ShowGone(false);
                this.mManager.AddView(this.mItemSyncAdj.GetView());
                return;
            case 3:
                this.mItemLowerRev = new CanItemCheckList(this, R.string.can_lower_rev);
                this.mItemLowerRev.SetIdClickListener(this, 3);
                this.mItemLowerRev.ShowGone(false);
                this.mManager.AddView(this.mItemLowerRev.GetView());
                return;
            case 4:
                this.mItemFoldAway = new CanItemCheckList(this, R.string.can_fold_af_parking);
                this.mItemFoldAway.SetIdClickListener(this, 4);
                this.mItemFoldAway.ShowGone(false);
                this.mManager.AddView(this.mItemFoldAway.GetView());
                return;
            case 5:
                this.mItemWiperTitle = new CanItemBlankTextList((Context) this, R.string.can_wipers);
                this.mItemWiperTitle.ShowGone(false);
                this.mManager.AddView(this.mItemWiperTitle.GetView());
                return;
            case 6:
                this.mItemWipeInRain = new CanItemCheckList(this, R.string.can_wipe_in_rain);
                this.mItemWipeInRain.SetIdClickListener(this, 6);
                this.mItemWipeInRain.ShowGone(false);
                this.mManager.AddView(this.mItemWipeInRain.GetView());
                return;
            case 7:
                this.mItemRearWidnowGear = new CanItemCheckList(this, R.string.can_r_win_in_r_gear);
                this.mItemRearWidnowGear.SetIdClickListener(this, 7);
                this.mItemRearWidnowGear.ShowGone(false);
                this.mManager.AddView(this.mItemRearWidnowGear.GetView());
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.GolfWcMirrorWiperSet(1, Neg(this.mWiperData.SyncAdjustment));
                return;
            case 3:
                CanJni.GolfWcMirrorWiperSet(2, Neg(this.mWiperData.LowerWhileReversing));
                return;
            case 4:
                CanJni.GolfWcMirrorWiperSet(3, Neg(this.mWiperData.FoldInWhenParked));
                return;
            case 6:
                CanJni.GolfWcMirrorWiperSet(4, Neg(this.mWiperData.AutomaticWipingInRain));
                return;
            case 7:
                CanJni.GolfWcMirrorWiperSet(5, Neg(this.mWiperData.RearWindowWipingInReverseGear));
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
