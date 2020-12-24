package com.ts.can.vw.golf.wc;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanFunc;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class CanGolfWcSeatDriveProfileActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener {
    private static final int ITEM_CHANG_BUT = 9;
    private static final int ITEM_COMFORT = 1;
    private static final int ITEM_ECO = 4;
    private static final int ITEM_INDIVIDUAL = 5;
    private static final int ITEM_NEXT = 11;
    private static final int ITEM_NORMAL = 2;
    private static final int ITEM_PRE = 10;
    private static final int ITEM_SPORT = 3;
    private static final int ITEM_XUEDI = 6;
    private static final int ITEM_YUEYE = 7;
    private static final int ITEM_YUEYEGXH = 8;
    public static boolean isEnd = false;
    private HorizontalScrollView hsv;
    private ParamButton mBtnNext;
    private ParamButton mBtnPre;
    private LinearLayout mContainer;
    private int mCount = 0;
    private ParamButton[] mDrvieProfiles = new ParamButton[8];
    private int[] mItemIds = {1, 2, 3, 4, 5, 6, 7, 8};
    private CanDataInfo.GolfWcDrivingModeSelection mProfileAdt = new CanDataInfo.GolfWcDrivingModeSelection();
    private CanDataInfo.GolfWcDrivingModeSelection mProfileData = new CanDataInfo.GolfWcDrivingModeSelection();
    private int[] mProfileDnIds = {R.drawable.can_dzjcms_car02_dn, R.drawable.can_dzjcms_car02_dn, R.drawable.can_dzjcms_car03_dn, R.drawable.can_dzjcms_car01_dn, R.drawable.can_dzjcms_car04_dn, R.drawable.can_dzjcms_car05_dn, R.drawable.can_dzjcms_car07_dn, R.drawable.can_dzjcms_car08_dn};
    private int[] mProfileTitles = {R.string.can_mode_ss, R.string.can_mode_normal, R.string.can_sport, R.string.can_eco, R.string.can_gxh, R.string.can_teramont_xuedi, R.string.can_teramont_cross, R.string.can_teramont_cross_ind};
    private int[] mProfileUpIds = {R.drawable.can_dzjcms_car02_up, R.drawable.can_dzjcms_car02_up, R.drawable.can_dzjcms_car03_up, R.drawable.can_dzjcms_car01_up, R.drawable.can_dzjcms_car04_up, R.drawable.can_dzjcms_car05_up, R.drawable.can_dzjcms_car07_up, R.drawable.can_dzjcms_car08_up};

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_golf_seat_drive_profile);
        RelativeLayoutManager manager = new RelativeLayoutManager(this, R.id.can_golf_seat_drive_profile_layout);
        this.mBtnPre = manager.AddButton(22, 195);
        this.mBtnPre.setStateUpDn(R.drawable.lin_ac_bel_up_up, R.drawable.lin_ac_bel_up_dn);
        this.mBtnPre.setOnClickListener(this);
        this.mBtnPre.setTag(10);
        this.mBtnPre.Show(false);
        this.mBtnNext = manager.AddButton(947, 195);
        this.mBtnNext.setStateUpDn(R.drawable.lin_ac_bel_dn_up, R.drawable.lin_ac_bel_dn_dn);
        this.mBtnNext.setOnClickListener(this);
        this.mBtnNext.setTag(11);
        this.mBtnNext.Show(false);
        this.hsv = new HorizontalScrollView(this);
        this.hsv.setHorizontalScrollBarEnabled(false);
        this.hsv.setHorizontalFadingEdgeEnabled(false);
        manager.AddView(this.hsv, 115, Can.CAN_JAC_REFINE_OD, 812, 184);
        this.mContainer = new LinearLayout(this);
        this.mContainer.setOrientation(0);
        this.mContainer.setGravity(16);
        this.mContainer.setLayoutParams(new ViewGroup.LayoutParams(812, 184));
        this.hsv.addView(this.mContainer);
        InitUI();
    }

    private ParamButton AddButton(int w, int h) {
        ParamButton btn = new ParamButton(this);
        LinearLayout.LayoutParams rl = new LinearLayout.LayoutParams(w, h);
        rl.rightMargin = 13;
        btn.setLayoutParams(rl);
        btn.Show(false);
        this.mContainer.addView(btn);
        return btn;
    }

    private void InitUI() {
        for (int i = 0; i < this.mDrvieProfiles.length; i++) {
            this.mDrvieProfiles[i] = AddButton(190, 184);
            this.mDrvieProfiles[i].setStateDrawable(this.mProfileUpIds[i], this.mProfileDnIds[i], this.mProfileDnIds[i]);
            this.mDrvieProfiles[i].setTag(Integer.valueOf(this.mItemIds[i]));
            this.mDrvieProfiles[i].setOnClickListener(this);
            this.mDrvieProfiles[i].setText(this.mProfileTitles[i]);
            this.mDrvieProfiles[i].setTextColor(-1);
            this.mDrvieProfiles[i].setTextSize(0, 30.0f);
            this.mDrvieProfiles[i].setGravity(1);
            this.mDrvieProfiles[i].setPadding(0, 124, 0, 0);
        }
    }

    private void ResetData(boolean check) {
        CanJni.GolfWcGetDrivingModeSelection(1, this.mProfileAdt);
        CanJni.GolfWcGetDrivingModeSelection(0, this.mProfileData);
        if (i2b(this.mProfileAdt.UpdateOnce) && (!check || i2b(this.mProfileAdt.Update))) {
            this.mProfileAdt.Update = 0;
            ShowProfiles(new int[]{this.mProfileAdt.Comfort, this.mProfileAdt.Normal, this.mProfileAdt.Sport, this.mProfileAdt.Eco, this.mProfileAdt.Indivdual, this.mProfileAdt.Snow, this.mProfileAdt.OffRoad, this.mProfileAdt.OffRoadPerson});
            CanFunc.mLastDriveProfileTick = GetTickCount();
        }
        if (!i2b(this.mProfileData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mProfileData.Update)) {
            this.mProfileData.Update = 0;
            SelectProfiles(new int[]{this.mProfileData.Comfort, this.mProfileData.Normal, this.mProfileData.Sport, this.mProfileData.Eco, this.mProfileData.Indivdual, this.mProfileData.Snow, this.mProfileData.OffRoad, this.mProfileData.OffRoadPerson});
            CanFunc.mLastDriveProfileTick = GetTickCount();
        }
    }

    private void ShowProfiles(int[] values) {
        boolean z;
        boolean z2 = true;
        this.mCount = 0;
        for (int i = 0; i < values.length; i++) {
            this.mDrvieProfiles[i].Show(i2b(values[i]));
            if (i2b(values[i])) {
                this.mCount++;
            }
        }
        ParamButton paramButton = this.mBtnPre;
        if (this.mCount > 4) {
            z = true;
        } else {
            z = false;
        }
        paramButton.Show(z);
        ParamButton paramButton2 = this.mBtnNext;
        if (this.mCount <= 4) {
            z2 = false;
        }
        paramButton2.Show(z2);
    }

    private void SelectProfiles(int[] values) {
        for (int i = 0; i < values.length; i++) {
            this.mDrvieProfiles[i].SetSel(values[i]);
        }
    }

    private void QueryData() {
        CanJni.GolfWcQueryData(1, 134);
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

    private void scrollItem(boolean isNext) {
        int scrollX = this.hsv.getScrollX();
        int maxScrollX = (this.mCount - 4) * 203;
        if (isNext) {
            if (scrollX + 203 > maxScrollX) {
                this.hsv.scrollBy(maxScrollX - scrollX, 0);
            } else {
                this.hsv.scrollBy(203, 0);
            }
        } else if (scrollX - 203 < 0) {
            this.hsv.scrollBy(-scrollX, 0);
        } else {
            this.hsv.scrollBy(-203, 0);
        }
        CanFunc.mLastDriveProfileTick = GetTickCount();
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.GolfWcDrivingModeSet(1, 0, 0);
                return;
            case 2:
                CanJni.GolfWcDrivingModeSet(2, 0, 0);
                return;
            case 3:
                CanJni.GolfWcDrivingModeSet(3, 0, 0);
                return;
            case 4:
                CanJni.GolfWcDrivingModeSet(4, 0, 0);
                return;
            case 5:
                CanJni.GolfWcDrivingModeSet(5, 0, 0);
                enterSubWin(CanGolfWcIndividualDriveProfileSetActivity.class);
                return;
            case 6:
                CanJni.GolfWcDrivingModeSet(6, 0, 0);
                return;
            case 7:
                CanJni.GolfWcDrivingModeSet(8, 0, 0);
                return;
            case 8:
                CanJni.GolfWcDrivingModeSet(9, 0, 0);
                enterSubWin(CanGolfWcDriveProfileSetActivity.class);
                return;
            case 10:
                scrollItem(false);
                return;
            case 11:
                scrollItem(true);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
        if (GetTickCount() > CanFunc.mLastDriveProfileTick + 6000 || isEnd) {
            isEnd = false;
            finish();
        }
    }
}
