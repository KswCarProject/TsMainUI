package com.ts.can.vw.rzc.golf;

import android.app.Activity;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanGolfRzcSeatDriveProfileView extends CanRelativeCarInfoView {
    private static final int ITEM_CHANG_BUT = 1;
    public static final String TAG = "CanGolfRzcSeatDriveProfileView";
    private ParamButton mChangBut;
    private CustomImgView[] mDrvieProfile;
    private RelativeLayoutManager mManager;
    private TextView[] mProfile;
    private CanDataInfo.GolfSeatDriveProfile mSeatDriveProfile;

    public CanGolfRzcSeatDriveProfileView(Activity activity) {
        super(activity);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = getRelativeManager();
        this.mProfile = new TextView[4];
        this.mDrvieProfile = new CustomImgView[4];
        this.mSeatDriveProfile = new CanDataInfo.GolfSeatDriveProfile();
        setBackgroundResource(R.drawable.can_dzjcms_bg);
        if (MainSet.GetScreenType() == 3) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
            lp.width = 1024;
            lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
            lp.gravity = 17;
            this.mManager.GetLayout().setLayoutParams(lp);
            initCommon();
            this.mManager.GetLayout().setScaleX(0.78f);
            this.mManager.GetLayout().setScaleY(0.79f);
            return;
        }
        initCommon();
    }

    private void initCommon() {
        this.mDrvieProfile[0] = this.mManager.AddImage(115, 116, 190, 184);
        this.mDrvieProfile[0].setStateDrawable(R.drawable.can_dzjcms_car01_up, R.drawable.can_dzjcms_car01_dn);
        this.mDrvieProfile[1] = this.mManager.AddImage(KeyDef.RKEY_ANGLEDN, 116, 190, 184);
        this.mDrvieProfile[1].setStateDrawable(R.drawable.can_dzjcms_car02_up, R.drawable.can_dzjcms_car02_dn);
        this.mDrvieProfile[2] = this.mManager.AddImage(CanCameraUI.BTN_GEELY_YJX6_MODE2, 116, 190, 184);
        this.mDrvieProfile[2].setStateDrawable(R.drawable.can_dzjcms_car03_up, R.drawable.can_dzjcms_car03_dn);
        this.mDrvieProfile[3] = this.mManager.AddImage(724, 116, 190, 184);
        this.mDrvieProfile[3].setStateDrawable(R.drawable.can_dzjcms_car04_up, R.drawable.can_dzjcms_car04_dn);
        this.mProfile[0] = this.mManager.AddText(115, 166, 190, 184);
        this.mProfile[0].setTextSize(0, 30.0f);
        this.mProfile[0].setTextColor(-1);
        this.mProfile[0].setText(R.string.can_golf_seat_drive_eco);
        this.mProfile[0].setGravity(17);
        this.mProfile[1] = this.mManager.AddText(KeyDef.RKEY_ANGLEDN, 166, 190, 184);
        this.mProfile[1].setTextSize(0, 30.0f);
        this.mProfile[1].setTextColor(-1);
        this.mProfile[1].setText(R.string.can_golf_seat_drive_normal);
        this.mProfile[1].setGravity(17);
        this.mProfile[2] = this.mManager.AddText(CanCameraUI.BTN_GEELY_YJX6_MODE2, 166, 190, 184);
        this.mProfile[2].setTextSize(0, 30.0f);
        this.mProfile[2].setTextColor(-1);
        this.mProfile[2].setText(R.string.can_golf_seat_drive_sport);
        this.mProfile[2].setGravity(17);
        this.mProfile[3] = this.mManager.AddText(724, 166, 190, 184);
        this.mProfile[3].setTextSize(0, 30.0f);
        this.mProfile[3].setTextColor(-1);
        this.mProfile[3].setText(R.string.can_golf_seat_drive_individual);
        this.mProfile[3].setGravity(17);
        this.mChangBut = this.mManager.AddButton(262, 389);
        this.mChangBut.setStateDrawable(R.drawable.can_golf_case_right_up, R.drawable.can_dzjcms_chang_up, R.drawable.can_dzjcms_chang_dn);
        this.mChangBut.setText(R.string.can_golf_seat_drive_chang);
        this.mChangBut.setTextSize(0, 40.0f);
        this.mChangBut.setTextColor(-1);
        this.mChangBut.setTag(1);
        this.mChangBut.setOnClickListener(this);
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
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                int seat = this.mSeatDriveProfile.ProfileType + 1;
                if (seat > 3) {
                    seat = 0;
                }
                CanJni.GolfSendCmd(208, seat);
                CanFunc.mLastDriveProfileTick = GetTickCount();
                return;
            default:
                return;
        }
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void ResetData(boolean check) {
        CanJni.GolfGetSeatDriveProfile(this.mSeatDriveProfile);
        if (!check || i2b(this.mSeatDriveProfile.Update)) {
            if (this.mSeatDriveProfile.ProfileType == 0) {
                this.mDrvieProfile[0].SetSel(0);
                this.mDrvieProfile[1].SetSel(1);
                this.mDrvieProfile[2].SetSel(0);
                this.mDrvieProfile[3].SetSel(0);
            } else if (this.mSeatDriveProfile.ProfileType == 1) {
                this.mDrvieProfile[0].SetSel(0);
                this.mDrvieProfile[1].SetSel(0);
                this.mDrvieProfile[2].SetSel(1);
                this.mDrvieProfile[3].SetSel(0);
            } else if (this.mSeatDriveProfile.ProfileType == 2) {
                this.mDrvieProfile[0].SetSel(1);
                this.mDrvieProfile[1].SetSel(0);
                this.mDrvieProfile[2].SetSel(0);
                this.mDrvieProfile[3].SetSel(0);
            } else if (this.mSeatDriveProfile.ProfileType == 3) {
                this.mDrvieProfile[0].SetSel(0);
                this.mDrvieProfile[1].SetSel(0);
                this.mDrvieProfile[2].SetSel(0);
                this.mDrvieProfile[3].SetSel(1);
            }
            this.mSeatDriveProfile.Update = 0;
        }
        if (GetTickCount() > CanFunc.mLastDriveProfileTick + 6000) {
            getActivity().finish();
        }
    }

    public void QueryData() {
        CanJni.GolfQuery(64, 160);
    }

    private long GetTickCount() {
        return SystemClock.uptimeMillis();
    }
}
