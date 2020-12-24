package com.ts.can;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanTeramontSeatDriveProfileActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener {
    private static final int BTN_PROFILE_JINGJI = 1;
    private static final int BTN_PROFILE_XUEDI = 0;
    private static final int BTN_PROFILE_YUEYE = 2;
    private static final int BTN_PROFILE_YUEYEGXH = 3;
    private CustomImgView[] mDrvieProfile = new CustomImgView[4];
    private RelativeLayoutManager mManager;
    private TextView[] mProfile = new TextView[4];
    private CanDataInfo.GolfSeatDriveProfile mSeatDriveProfile = new CanDataInfo.GolfSeatDriveProfile();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_golf_seat_drive_profile);
        String[] mProfileArray = getResources().getStringArray(R.array.can_teramont_drive_profile_array);
        this.mManager = new RelativeLayoutManager(this, R.id.can_golf_seat_drive_profile_layout);
        if (MainSet.GetScreenType() == 3) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
            lp.width = 1024;
            lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
            lp.gravity = 17;
            this.mManager.GetLayout().setLayoutParams(lp);
            initCommon(mProfileArray);
            this.mManager.GetLayout().setScaleX(0.78f);
            this.mManager.GetLayout().setScaleY(0.79f);
            return;
        }
        initCommon(mProfileArray);
    }

    private void initCommon(String[] mProfileArray) {
        this.mDrvieProfile[0] = this.mManager.AddImage(115, Can.CAN_JAC_REFINE_OD, 190, 184);
        this.mDrvieProfile[0].setStateDrawable(R.drawable.can_dzjcms_car05_up, R.drawable.can_dzjcms_car05_dn, R.drawable.can_dzjcms_car05_dn);
        this.mDrvieProfile[0].setTag(0);
        this.mDrvieProfile[0].setOnClickListener(this);
        this.mDrvieProfile[1] = this.mManager.AddImage(KeyDef.RKEY_ANGLEDN, Can.CAN_JAC_REFINE_OD, 190, 184);
        this.mDrvieProfile[1].setStateDrawable(R.drawable.can_dzjcms_car06_up, R.drawable.can_dzjcms_car06_dn, R.drawable.can_dzjcms_car06_dn);
        this.mDrvieProfile[1].setTag(1);
        this.mDrvieProfile[1].setOnClickListener(this);
        this.mDrvieProfile[2] = this.mManager.AddImage(CanCameraUI.BTN_GEELY_YJX6_MODE2, Can.CAN_JAC_REFINE_OD, 190, 184);
        this.mDrvieProfile[2].setStateDrawable(R.drawable.can_dzjcms_car07_up, R.drawable.can_dzjcms_car07_dn, R.drawable.can_dzjcms_car07_dn);
        this.mDrvieProfile[2].setTag(2);
        this.mDrvieProfile[2].setOnClickListener(this);
        this.mDrvieProfile[3] = this.mManager.AddImage(724, Can.CAN_JAC_REFINE_OD, 190, 184);
        this.mDrvieProfile[3].setStateDrawable(R.drawable.can_dzjcms_car08_up, R.drawable.can_dzjcms_car08_dn, R.drawable.can_dzjcms_car08_dn);
        this.mDrvieProfile[3].setTag(3);
        this.mDrvieProfile[3].setOnClickListener(this);
        for (int i = 0; i < this.mProfile.length; i++) {
            this.mProfile[i] = this.mManager.AddText((i * 203) + 115, 200, 190, 184);
            this.mProfile[i].setTextSize(0, 30.0f);
            this.mProfile[i].setTextColor(-1);
            this.mProfile[i].setText(mProfileArray[i]);
            this.mProfile[i].setGravity(17);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
    }

    private void ResetData(boolean check) {
        CanJni.GolfGetSeatDriveProfile(this.mSeatDriveProfile);
        if (!i2b(this.mSeatDriveProfile.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSeatDriveProfile.Update)) {
            this.mSeatDriveProfile.Update = 0;
            for (CustomImgView selected : this.mDrvieProfile) {
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
            }
        }
    }

    private void QueryData() {
        CanJni.GolfQuery(64, Can.CAN_CHANA_CS75_WC);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.GolfSendCmd(208, 4);
                startProfileSetActivity(0);
                return;
            case 1:
                startProfileSetActivity(1);
                return;
            case 2:
                CanJni.GolfSendCmd(208, 5);
                startProfileSetActivity(2);
                return;
            case 3:
                CanJni.GolfSendCmd(208, 6);
                startProfileSetActivity(3);
                return;
            default:
                return;
        }
    }

    private void startProfileSetActivity(int type) {
        Intent i = new Intent(this, CanTeramontDriveProfileSetActivity.class);
        i.putExtra("drive_pattern", type);
        startActivity(i);
        overridePendingTransition(0, 0);
    }

    public void UserAll() {
        ResetData(true);
        long GetTickCount = GetTickCount();
        long j = CanFunc.mLastDriveProfileTick;
    }
}
