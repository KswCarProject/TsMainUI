package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;
import com.ts.main.common.MainSet;
import com.ts.other.ParamButton;

public class CanToyotaSetOtherActivity extends CanToyotaBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    private static final int ID_TRACK_0 = 2000;
    private static final int ID_TRACK_1 = 2001;
    private static final int ID_TRACK_2 = 2002;
    private static final int ITEM_DRIVE_SEAT_EASY_EXIT = 5;
    private static final int ITEM_EDOOR = 2;
    private static final int ITEM_MAX = 5;
    private static final int ITEM_MIN = 1;
    private static final int ITEM_RADAR_VOL_SW = 4;
    private static final int ITEM_SRC_COLOR = 3;
    private static final int ITEM_TRACK = 1;
    public static final String TAG = "CanGolfSetMFDActivity";
    public static final String[] mStrHbxArray = {"1", "2", "3", MainSet.SP_KS_QOROS, MainSet.SP_TW_CJW};
    private static int[] mStrSeatEasyExitArr = {R.string.can_mzd_cx4_mode_off, R.string.can_partial, R.string.can_full};
    private static int[] mStrSrcColorArr = {R.string.can_clear_blue, R.string.can_blue_green, R.string.can_deep_orange, R.string.can_radiant_orange};
    private ParamButton[] mBtnTrackType = new ParamButton[3];
    private CanItemPopupList mItemDriveSeatEasyExit;
    private CanItemPopupList mItemEDoor;
    private CanItemSwitchList mItemRadarVolSw;
    private CanItemPopupList mItemSrcColor;
    private CanItemTextBtnList mItemTrack;
    private RelativeLayout mLayout;
    private CanScrollList mManager;
    protected CanDataInfo.ToyotaCtrlInfo mToyotaCtrlInfo = new CanDataInfo.ToyotaCtrlInfo();
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
        if (!this.mbLayout) {
            GetAdaptData();
            if (i2b(this.mAdtData.UpdateOnce)) {
                LayoutUI();
                check = false;
                this.mbLayout = true;
            } else {
                return;
            }
        }
        if (i2b(this.mSetData.UpdateOnce) && (!check || i2b(this.mSetData.Update))) {
            this.mSetData.Update = 0;
            for (ParamButton SetSel : this.mBtnTrackType) {
                SetSel.SetSel(0);
            }
            if (this.mSetData.TrackMode < 3) {
                this.mBtnTrackType[this.mSetData.TrackMode].SetSel(1);
            }
            this.mItemEDoor.SetSel(this.mSetData.ERearDoorGear);
            this.mItemSrcColor.SetSel(this.mSetData.Xpyssz);
            this.mItemDriveSeatEasyExit.SetSel(this.mSetData.DriveSeatEasyExit);
        }
        CanJni.ToyotaGetCtrlInfo(this.mToyotaCtrlInfo);
        if (!i2b(this.mToyotaCtrlInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mToyotaCtrlInfo.Update)) {
            this.mToyotaCtrlInfo.Update = 0;
            this.mItemRadarVolSw.SetCheck(this.mToyotaCtrlInfo.VolSw);
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
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void setViewPos(View view, int x, int y) {
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(-2, -2);
        rl.leftMargin = x;
        rl.topMargin = y;
        view.setLayoutParams(rl);
        this.mLayout.addView(view);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemTrack = new CanItemTextBtnList((Context) this, R.string.can_rc_track_set);
        this.mItemTrack.ShowArrow(false);
        this.mLayout = (RelativeLayout) this.mItemTrack.GetView();
        this.mItemTrack.GetView().setClickable(false);
        for (int i = 0; i < this.mBtnTrackType.length; i++) {
            this.mBtnTrackType[i] = new ParamButton(this);
            this.mBtnTrackType[i].setTag(Integer.valueOf(i + 2000));
            this.mBtnTrackType[i].setOnClickListener(this);
        }
        this.mBtnTrackType[0].setDrawable(R.drawable.can_camera_tarack_line_up, R.drawable.can_camera_tarack_line_dn);
        this.mBtnTrackType[1].setDrawable(R.drawable.can_camera_tarack_cha_up, R.drawable.can_camera_tarack_cha_dn);
        this.mBtnTrackType[2].setDrawable(R.drawable.can_camera_tarack_wan_up, R.drawable.can_camera_tarack_wan_dn);
        for (int i2 = 0; i2 < this.mBtnTrackType.length; i2++) {
            setViewPos(this.mBtnTrackType[i2], (i2 * 160) + 500, 8);
        }
        this.mItemEDoor = new CanItemPopupList((Context) this, R.string.can_hbx_kd, mStrHbxArray, (CanItemPopupList.onPopItemClick) this);
        this.mItemEDoor.SetId(2);
        this.mItemSrcColor = new CanItemPopupList((Context) this, R.string.can_car_color, mStrSrcColorArr, (CanItemPopupList.onPopItemClick) this);
        this.mItemSrcColor.SetId(3);
        this.mItemRadarVolSw = new CanItemSwitchList(this, R.string.can_lddcsy);
        this.mItemRadarVolSw.SetIdClickListener(this, 4);
        this.mItemDriveSeatEasyExit = new CanItemPopupList((Context) this, R.string.can_jsyblxc, mStrSeatEasyExitArr, (CanItemPopupList.onPopItemClick) this);
        this.mItemDriveSeatEasyExit.SetId(5);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        this.mManager.RemoveAllViews();
        for (int i = 1; i <= 5; i++) {
            AddItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.TrackMode;
                break;
            case 2:
                ret = this.mAdtData.EDoor;
                break;
            case 3:
            case 4:
            case 5:
                if (CanJni.GetCanFsTp() == 3) {
                    ret = 1;
                    break;
                }
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void AddItem(int item) {
        if (IsHaveItem(item)) {
            switch (item) {
                case 1:
                    this.mManager.AddView(this.mItemTrack.GetView());
                    return;
                case 2:
                    this.mManager.AddView(this.mItemEDoor.GetView());
                    return;
                case 3:
                    this.mManager.AddView(this.mItemSrcColor.GetView());
                    return;
                case 4:
                    this.mManager.AddView(this.mItemRadarVolSw.GetView());
                    return;
                case 5:
                    this.mManager.AddView(this.mItemDriveSeatEasyExit.GetView());
                    return;
                default:
                    return;
            }
        }
    }

    public void onClick(View v) {
        int item = ((Integer) v.getTag()).intValue();
        switch (item) {
            case 4:
                CarSet(26, Neg(this.mToyotaCtrlInfo.VolSw));
                return;
            case 2000:
            case ID_TRACK_1 /*2001*/:
            case ID_TRACK_2 /*2002*/:
                CarSet(34, item - 2000);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 2:
                CarSet(35, item);
                return;
            case 3:
                CarSet(24, item);
                return;
            case 5:
                CarSet(43, item);
                return;
            default:
                return;
        }
    }
}
