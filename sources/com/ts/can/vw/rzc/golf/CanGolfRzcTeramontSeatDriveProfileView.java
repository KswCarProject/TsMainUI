package com.ts.can.vw.rzc.golf;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCarInfoSub2Activity;
import com.ts.can.CanFunc;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.main.common.MainSet;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class CanGolfRzcTeramontSeatDriveProfileView extends CanRelativeCarInfoView {
    private static final int BTN_PROFILE_COMFORT = 4;
    private static final int BTN_PROFILE_JINGJI = 1;
    private static final int BTN_PROFILE_XUEDI = 0;
    private static final int BTN_PROFILE_YUEYE = 2;
    private static final int BTN_PROFILE_YUEYEGXH = 3;
    public static final String TAG = "CanGolfRzcTeramontSeatDriveProfileView";
    private HorizontalScrollView hsv;
    private LinearLayout mContainer;
    private ParamButton[] mDrvieProfile;
    private int[] mItemIds;
    private RelativeLayoutManager mManager;
    private int[] mProfileDnIds;
    private int[] mProfileTitles;
    private int[] mProfileUpIds;
    private CanDataInfo.GolfSeatDriveProfile mSeatDriveProfile;

    public CanGolfRzcTeramontSeatDriveProfileView(Activity activity) {
        super(activity);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = getRelativeManager();
        setBackgroundResource(R.drawable.can_dzjcms_bg);
        this.mDrvieProfile = new ParamButton[5];
        this.mProfileTitles = new int[]{R.string.can_teramont_xuedi, R.string.can_eco, R.string.can_teramont_cross, R.string.can_teramont_cross_ind, R.string.can_mode_ss};
        this.mProfileUpIds = new int[]{R.drawable.can_dzjcms_car05_up, R.drawable.can_dzjcms_car06_up, R.drawable.can_dzjcms_car07_up, R.drawable.can_dzjcms_car08_up, R.drawable.can_dzjcms_car02_up};
        this.mProfileDnIds = new int[]{R.drawable.can_dzjcms_car05_dn, R.drawable.can_dzjcms_car06_dn, R.drawable.can_dzjcms_car07_dn, R.drawable.can_dzjcms_car08_dn, R.drawable.can_dzjcms_car02_dn};
        int[] iArr = new int[5];
        iArr[1] = 1;
        iArr[2] = 2;
        iArr[3] = 3;
        iArr[4] = 4;
        this.mItemIds = iArr;
        this.mSeatDriveProfile = new CanDataInfo.GolfSeatDriveProfile();
        String[] stringArray = getStringArray(R.array.can_teramont_drive_profile_array);
        if (MainSet.GetScreenType() == 3) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
            lp.width = 1024;
            lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
            lp.gravity = 17;
            this.mManager.GetLayout().setLayoutParams(lp);
            initUI();
            this.mManager.GetLayout().setScaleX(0.78f);
            this.mManager.GetLayout().setScaleY(0.79f);
            return;
        }
        initUI();
    }

    private void initUI() {
        this.hsv = new HorizontalScrollView(getActivity());
        this.hsv.setHorizontalScrollBarEnabled(false);
        this.hsv.setHorizontalFadingEdgeEnabled(false);
        this.mManager.AddView(this.hsv, 115, Can.CAN_JAC_REFINE_OD, 812, 184);
        this.mContainer = new LinearLayout(getActivity());
        this.mContainer.setOrientation(0);
        this.mContainer.setGravity(16);
        this.mContainer.setLayoutParams(new ViewGroup.LayoutParams(812, 184));
        this.hsv.addView(this.mContainer);
        for (int i = 0; i < this.mDrvieProfile.length; i++) {
            this.mDrvieProfile[i] = AddButton(190, 184);
            this.mDrvieProfile[i].setStateDrawable(this.mProfileUpIds[i], this.mProfileDnIds[i], this.mProfileDnIds[i]);
            this.mDrvieProfile[i].setTag(Integer.valueOf(this.mItemIds[i]));
            this.mDrvieProfile[i].setOnClickListener(this);
            this.mDrvieProfile[i].setText(this.mProfileTitles[i]);
            this.mDrvieProfile[i].setTextColor(-1);
            this.mDrvieProfile[i].setTextSize(0, 30.0f);
            this.mDrvieProfile[i].setGravity(1);
            this.mDrvieProfile[i].setPadding(0, 124, 0, 0);
        }
    }

    private ParamButton AddButton(int w, int h) {
        ParamButton btn = new ParamButton(getActivity());
        LinearLayout.LayoutParams rl = new LinearLayout.LayoutParams(w, h);
        rl.rightMargin = 13;
        btn.setLayoutParams(rl);
        this.mContainer.addView(btn);
        return btn;
    }

    public void doOnResume() {
        super.doOnResume();
        ResetData(false);
        QueryData();
    }

    public void doOnPause() {
        super.doOnPause();
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        this.mDrvieProfile[4].setSelected(id == 4);
        switch (id) {
            case 0:
                CanJni.GolfSendCmd(208, 4);
                startProfileSetView(0);
                return;
            case 1:
                startProfileSetView(1);
                return;
            case 2:
                CanJni.GolfSendCmd(208, 5);
                startProfileSetView(2);
                return;
            case 3:
                CanJni.GolfSendCmd(208, 6);
                startProfileSetView(3);
                return;
            case 4:
                CanJni.GolfSendCmd(208, 7);
                return;
            default:
                return;
        }
    }

    private void startProfileSetView(int type) {
        Intent i = new Intent(getActivity(), CanCarInfoSub2Activity.class);
        i.putExtra("drive_pattern", type);
        i.putExtra("ID", -1);
        getActivity().startActivity(i);
        getActivity().overridePendingTransition(0, 0);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void ResetData(boolean check) {
        CanJni.GolfGetSeatDriveProfile(this.mSeatDriveProfile);
        if (i2b(this.mSeatDriveProfile.UpdateOnce) && (!check || i2b(this.mSeatDriveProfile.Update))) {
            this.mSeatDriveProfile.Update = 0;
            for (ParamButton selected : this.mDrvieProfile) {
                selected.setSelected(false);
            }
            if (this.mSeatDriveProfile.ProfileType <= 3) {
                this.mDrvieProfile[1].setSelected(true);
            } else if (this.mSeatDriveProfile.ProfileType == 4) {
                this.mDrvieProfile[0].setSelected(true);
            } else if (this.mSeatDriveProfile.ProfileType == 5) {
                this.mDrvieProfile[2].setSelected(true);
            } else if (this.mSeatDriveProfile.ProfileType == 6) {
                this.mDrvieProfile[3].setSelected(true);
            } else if (this.mSeatDriveProfile.ProfileType == 7) {
                this.mDrvieProfile[4].setSelected(true);
            }
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        long j = CanFunc.mLastDriveProfileTick;
    }

    public void QueryData() {
        CanJni.GolfQuery(64, Can.CAN_CHANA_CS75_WC);
    }
}
