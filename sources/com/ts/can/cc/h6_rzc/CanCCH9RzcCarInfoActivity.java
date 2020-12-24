package com.ts.can.cc.h6_rzc;

import android.content.res.Resources;
import android.os.Bundle;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemTitleValList;
import com.ts.canview.CanScrollList;

public class CanCCH9RzcCarInfoActivity extends CanBaseActivity implements UserCallBack, CanItemPopupList.onPopItemClick {
    private static final int ITEM_ALTITUDE = 4;
    public static final int ITEM_BSQYW = 6;
    public static final int ITEM_DQYYL = 8;
    private static final int ITEM_FRONTWHEELTQUERATIO = 1;
    private static final int ITEM_INCLINATION = 2;
    public static final int ITEM_LQYWD = 5;
    private static final int ITEM_MAX = 8;
    private static final int ITEM_MIN = 0;
    private static final int ITEM_SLOPE = 0;
    private static final int ITEM_TRAILER = 3;
    public static final int ITEM_XDCDY = 7;
    private CanItemTitleValList mAltitudeItem;
    private CanDataInfo.H2CarData mCarData = new CanDataInfo.H2CarData();
    private CanItemTitleValList mFrontWheelTorqueRatioItem;
    private CanDataInfo.H9CarSet2 mH9CarSet2 = new CanDataInfo.H9CarSet2();
    private CanItemTitleValList mInclinationItem;
    private CanItemTitleValList mItemBsqyw;
    private CanItemTitleValList mItemDqyyl;
    private CanItemTitleValList mItemLqywd;
    private CanItemTitleValList mItemXdcdy;
    private CanScrollList mManager;
    Resources mResources;
    private CanItemTitleValList mSlopeItem;
    private CanItemTitleValList mTrailerItem;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        System.out.println("onCreate");
        this.mResources = getResources();
        InitUI();
    }

    private void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mSlopeItem = AddItemTitleVal(R.string.can_cch9_slope, 0);
        this.mFrontWheelTorqueRatioItem = AddItemTitleVal(R.string.can_cch9_front_wheel_torque_ratio, 1);
        this.mInclinationItem = AddItemTitleVal(R.string.can_cch9_inclination, 2);
        this.mTrailerItem = AddItemTitleVal(R.string.can_cch9_trailer, 2);
        this.mAltitudeItem = AddItemTitleVal(R.string.can_cch9_altitude, 4);
        this.mItemLqywd = AddItemTitleVal(R.string.can_lqywd, 5);
        this.mItemBsqyw = AddItemTitleVal(R.string.can_bsqyw, 6);
        this.mItemXdcdy = AddItemTitleVal(R.string.can_xdcdy, 7);
        this.mItemDqyyl = AddItemTitleVal(R.string.can_dqyyl, 8);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        LayoutUI();
        CanJni.CCCarQueryRzc(54, 0);
        Sleep(5);
        CanJni.CCCarQueryRzc(41, 0);
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                if (CanJni.GetSubType() == 8 || CanJni.GetSubType() == 3 || CanJni.GetSubType() == 7 || CanJni.GetSubType() == 10) {
                    ret = 1;
                    break;
                }
            case 5:
            case 6:
            case 7:
                if (CanJni.GetSubType() == 3 || CanJni.GetSubType() == 7 || CanJni.GetSubType() == 8 || CanJni.GetSubType() == 10 || CanJni.GetSubType() == 11) {
                    ret = 1;
                    break;
                }
            case 8:
                if (CanJni.GetSubType() == 7 || CanJni.GetSubType() == 8 || CanJni.GetSubType() == 10 || CanJni.GetSubType() == 11) {
                    ret = 1;
                    break;
                }
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 0:
                this.mSlopeItem.ShowGone(show);
                return;
            case 1:
                this.mFrontWheelTorqueRatioItem.ShowGone(show);
                return;
            case 2:
                this.mInclinationItem.ShowGone(show);
                return;
            case 3:
                this.mTrailerItem.ShowGone(show);
                return;
            case 4:
                this.mAltitudeItem.ShowGone(show);
                return;
            case 5:
                this.mItemLqywd.ShowGone(show);
                return;
            case 6:
                this.mItemBsqyw.ShowGone(show);
                return;
            case 7:
                this.mItemXdcdy.ShowGone(show);
                return;
            case 8:
                this.mItemDqyyl.ShowGone(show);
                return;
            default:
                return;
        }
    }

    private void LayoutUI() {
        for (int i = 0; i <= 8; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public CanItemTitleValList AddItemTitleVal(int text, int id) {
        CanItemTitleValList item = new CanItemTitleValList(this, text);
        item.SetIconVisibility(0);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        item.SetIconVisibility(8);
        return item;
    }

    private void ResetData(boolean check) {
        CanJni.CcHfH9GetCarSet2(this.mH9CarSet2);
        if (i2b(this.mH9CarSet2.UpdateOnce) && (!check || i2b(this.mH9CarSet2.Update))) {
            this.mH9CarSet2.Update = 0;
            if ((this.mH9CarSet2.Pd & 128) > 0) {
                this.mSlopeItem.SetVal(String.valueOf(this.mResources.getString(R.string.can_cch9_down)) + " " + (this.mH9CarSet2.Pd - 128));
            } else {
                this.mSlopeItem.SetVal(String.valueOf(this.mResources.getString(R.string.can_cch9_up)) + " " + this.mH9CarSet2.Pd);
            }
            if (this.mH9CarSet2.Qlnjpb == 255) {
                this.mFrontWheelTorqueRatioItem.SetVal("--");
            } else {
                this.mFrontWheelTorqueRatioItem.SetVal(String.valueOf(this.mH9CarSet2.Qlnjpb) + "%");
            }
            if ((this.mH9CarSet2.Qj & 128) > 0) {
                this.mInclinationItem.SetVal(String.valueOf(this.mResources.getString(R.string.can_cch9_right)) + " " + (this.mH9CarSet2.Qj - 128));
            } else {
                this.mInclinationItem.SetVal(String.valueOf(this.mResources.getString(R.string.can_cch9_left)) + " " + this.mH9CarSet2.Qj);
            }
            if (this.mH9CarSet2.Tc == 1) {
                this.mTrailerItem.SetVal(this.mResources.getString(R.string.can_cch9_trailer_key2));
            } else {
                this.mTrailerItem.SetVal(this.mResources.getString(R.string.can_cch9_trailer_key1));
            }
            this.mAltitudeItem.SetVal(new StringBuilder().append(this.mH9CarSet2.Hb).toString());
        }
        CanJni.CCH2sGetCarSet(this.mCarData);
        if (!i2b(this.mCarData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarData.Update)) {
            this.mCarData.Update = 0;
            this.mItemLqywd.SetVal(String.format("%.2f ℃", new Object[]{Double.valueOf((((double) this.mCarData.Lqywd) * 0.75d) - 48.0d)}));
            this.mItemBsqyw.SetVal(String.format("%d ℃", new Object[]{Integer.valueOf(this.mCarData.Bsqyw - 40)}));
            this.mItemXdcdy.SetVal(String.format("%.2fV", new Object[]{Double.valueOf(((double) this.mCarData.Xdcdy) * 0.25d)}));
            this.mItemDqyyl.SetVal(String.format("%.2fKpa", new Object[]{Double.valueOf(((double) this.mCarData.Dqyyl) * 0.59d)}));
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
    }

    public void onItem(int id, int item) {
    }

    public void UserAll() {
        ResetData(true);
    }
}
