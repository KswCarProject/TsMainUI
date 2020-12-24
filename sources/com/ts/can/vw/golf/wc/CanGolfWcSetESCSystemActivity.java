package com.ts.can.vw.golf.wc;

import android.content.Context;
import android.os.Bundle;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;

public class CanGolfWcSetESCSystemActivity extends CanBaseActivity implements CanItemPopupList.onPopItemClick, UserCallBack {
    private static final int ITEM_ESC_SYSTEM = 0;
    private int[] mEsc = {R.string.can_a_s_r_off, R.string.can_active_already};
    private CanDataInfo.GolfWcSportModeInfo mEscAdt = new CanDataInfo.GolfWcSportModeInfo();
    private CanDataInfo.GolfWcSportModeInfo mEscInfo = new CanDataInfo.GolfWcSportModeInfo();
    private int[] mEscSystem = {R.string.can_a_s_r_off, R.string.can_active_already, R.string.can_esc_sports_mode};
    protected CanItemPopupList mItemEscSystem;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GolfWcGetSportModeInfo(1, this.mEscAdt);
        CanJni.GolfWcGetSportModeInfo(0, this.mEscInfo);
        if (i2b(this.mEscAdt.UpdateOnce) && (!check || i2b(this.mEscAdt.Update))) {
            this.mEscAdt.Update = 0;
            this.mItemEscSystem.ShowGone(this.mEscAdt.Esc);
        }
        if (!i2b(this.mEscInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mEscInfo.Update)) {
            this.mEscInfo.Update = 0;
            this.mItemEscSystem.SetSel(this.mEscInfo.Esc);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GolfWcQueryData(1, 133);
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

    private void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemEscSystem = new CanItemPopupList((Context) this, R.string.can_esc_system, this.mEscSystem, (CanItemPopupList.onPopItemClick) this);
        this.mItemEscSystem.SetId(0);
        this.mItemEscSystem.ShowGone(false);
        this.mManager.AddView(this.mItemEscSystem.GetView());
    }

    public void onItem(int Id, int item) {
        if (Id == 0) {
            CanJni.GolfWcSportModeSet(3, item);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
