package com.ts.can.renault.kadjar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseCarInfoActivity;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanScrollBaseActivity;

public class CanKadjarCarInfoActivity extends CanScrollBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    protected static final int ITEM_AMZY = 7;
    protected static final int ITEM_CAR_ECU = 2;
    protected static final int ITEM_CAR_TYPE = 0;
    protected static final int ITEM_HUD = 8;
    protected static final int ITEM_MAX = 8;
    protected static final int ITEM_MIN = 0;
    protected static final int ITEM_PARK_ASS = 1;
    protected static final int ITEM_SYSTEM = 5;
    protected static final int ITEM_TAKE_CARE = 3;
    protected static final int ITEM_TPMS = 6;
    protected static final int ITEM_USER_SET = 4;
    public static final String TAG = "CanKadjarCarInfoActivity";
    protected CanItemIcoList mItemAmzy;
    protected CanItemIcoList mItemCarEcu;
    protected CanItemIcoList mItemCarType;
    protected CanItemIcoList mItemHud;
    protected CanItemIcoList mItemParkAss;
    protected CanItemIcoList mItemSystem;
    protected CanItemIcoList mItemTakeCare;
    protected CanItemIcoList mItemTpms;
    protected CanItemIcoList mItemUserSet;
    protected boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mItemCarType = AddIcoItem((View.OnClickListener) this, R.drawable.can_icon_esc, R.string.can_car_type_select, 0);
        this.mItemParkAss = AddIcoItem((View.OnClickListener) this, R.drawable.can_icon_cds, R.string.can_bcfz, 1);
        this.mItemCarEcu = AddIcoItem((View.OnClickListener) this, R.drawable.can_icon_mfd, R.string.can_czdn, 2);
        this.mItemTakeCare = AddIcoItem((View.OnClickListener) this, R.drawable.can_icon_ac, "Take Care", 3);
        this.mItemUserSet = AddIcoItem((View.OnClickListener) this, R.drawable.can_icon_setup, R.string.can_yhsz, 4);
        this.mItemSystem = AddIcoItem((View.OnClickListener) this, R.drawable.can_icon_service, R.string.can_system, 5);
        this.mItemTpms = AddIcoItem((View.OnClickListener) this, R.drawable.can_icon_tpms, R.string.can_tmps, 6);
        this.mItemAmzy = AddIcoItem((View.OnClickListener) this, R.drawable.can_icon_light2, R.string.can_amzy_sz, 7);
        this.mItemHud = AddIcoItem((View.OnClickListener) this, R.drawable.can_golf_icon14, R.string.can_hud_set, 8);
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
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
        CanJni.KadjarCarSet(0, 1);
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
        for (int i = 0; i <= 8; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = 1;
                break;
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
                if (CanJni.GetSubType() == 3) {
                    ret = 1;
                    break;
                }
                break;
            case 7:
                ret = 1;
                break;
            case 8:
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
                this.mItemParkAss.ShowGone(show);
                return;
            case 2:
                this.mItemCarEcu.ShowGone(show);
                return;
            case 3:
                this.mItemTakeCare.ShowGone(show);
                return;
            case 4:
                this.mItemUserSet.ShowGone(show);
                return;
            case 5:
                this.mItemSystem.ShowGone(show);
                return;
            case 6:
                this.mItemTpms.ShowGone(show);
                return;
            case 7:
                this.mItemAmzy.ShowGone(show);
                return;
            case 8:
                this.mItemHud.ShowGone(show);
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
            case 0:
                enterSubWin(CanKadjarCarTypeActivity.class);
                return;
            case 1:
                enterSubWin(CanKadjarParkAssActivity.class);
                return;
            case 2:
                enterSubWin(CanKadjarEcuActivity.class);
                return;
            case 3:
                enterSubWin(CanKadjarTakeCareActivity.class);
                return;
            case 4:
                enterSubWin(CanKadjarUserSetActivity.class);
                return;
            case 5:
                enterSubWin(CanKadjarSystemActivity.class);
                return;
            case 6:
                enterSubWin(CanBaseCarInfoActivity.class);
                return;
            case 7:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 0);
                return;
            case 8:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 1);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }
}
