package com.ts.can.cc.dj.hf;

import android.app.Activity;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanHfDjExdView extends CanRelativeCarInfoView {
    public static final int BTN_NEXT = 1;
    public static final int BTN_OFF = 3;
    public static final int BTN_PLAY = 2;
    public static final int BTN_PREV = 0;
    public static final int BTN_RANDOM = 5;
    public static final int BTN_REPEAT = 4;
    public static final String TAG = "CanCivicWcExdView";
    private ParamButton mBtnNext;
    private ParamButton mBtnOff;
    private ParamButton mBtnPlay;
    private ParamButton mBtnPrev;
    private ParamButton mBtnRandom;
    private ParamButton mBtnRepeat;
    private CustomTextView mCurMode;
    private String mCurSongNum;
    private CustomTextView mCurTime;
    private CustomImgView mImgCd;
    private String mPlayMode;
    private String[] mPlayModeStrs;
    private String mPlayTime;
    private CustomTextView mSongNum;
    private CanDataInfo.CcHfDj_SrcInfo mSrcInfo;
    private CustomTextView mStatus;
    private String mVolStatus;
    private String[] mVolStatusStrs;

    public CanHfDjExdView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int id = ((Integer) v.getTag()).intValue();
        int action = event.getAction();
        if (action != 0) {
            if (action == 1) {
                switch (id) {
                    case 0:
                        CanJni.CcHfDjMediaKey(9, 0);
                        break;
                    case 1:
                        CanJni.CcHfDjMediaKey(8, 0);
                        break;
                    case 2:
                        if (this.mSrcInfo.fgPp != 1) {
                            CanJni.CcHfDjMediaCmd(17, 0);
                            break;
                        } else {
                            CanJni.CcHfDjMediaCmd(16, 0);
                            break;
                        }
                }
            }
        } else {
            switch (id) {
                case 0:
                    CanJni.CcHfDjMediaKey(9, 1);
                    break;
                case 1:
                    CanJni.CcHfDjMediaKey(8, 1);
                    break;
                case 2:
                    if (this.mSrcInfo.fgPp != 1) {
                        CanJni.CcHfDjMediaCmd(17, 1);
                        break;
                    } else {
                        CanJni.CcHfDjMediaCmd(16, 1);
                        break;
                    }
            }
        }
        return false;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 3:
                CanJni.CcHfDjMediaCmd(2, 0);
                return;
            case 4:
                CanJni.CcHfDjMediaCmd(2, 1);
                return;
            case 5:
                CanJni.CcHfDjMediaCmd(2, 2);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        initCommonScreen();
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
    }

    private void initCommonScreen() {
        Resources mResources = getActivity().getResources();
        this.mVolStatus = mResources.getString(R.string.can_syzt);
        this.mCurSongNum = mResources.getString(R.string.can_dqqm);
        this.mPlayTime = mResources.getString(R.string.can_bfsj);
        this.mPlayMode = mResources.getString(R.string.can_play_mode);
        this.mVolStatusStrs = new String[2];
        this.mVolStatusStrs[0] = mResources.getString(R.string.can_vol_unmute);
        this.mVolStatusStrs[1] = mResources.getString(R.string.can_vol_mute);
        this.mPlayModeStrs = new String[3];
        this.mPlayModeStrs[0] = mResources.getString(R.string.can_order_play);
        this.mPlayModeStrs[1] = mResources.getString(R.string.can_repeat_play);
        this.mPlayModeStrs[2] = mResources.getString(R.string.can_random_play);
        this.mSrcInfo = new CanDataInfo.CcHfDj_SrcInfo();
        this.mImgCd = addImage(200, 79, R.drawable.original_car_cd);
        this.mStatus = getRelativeManager().AddCusText(CanCameraUI.BTN_GEELY_YJX6_MODE1, 105, 300, 38);
        this.mStatus.setGravity(19);
        this.mStatus.SetPxSize(35);
        this.mStatus.setTextColor(-1);
        this.mSongNum = getRelativeManager().AddCusText(CanCameraUI.BTN_GEELY_YJX6_MODE1, Can.CAN_FORD_WC, 300, 38);
        this.mSongNum.setGravity(19);
        this.mSongNum.SetPxSize(35);
        this.mSongNum.setTextColor(-1);
        this.mCurTime = getRelativeManager().AddCusText(CanCameraUI.BTN_GEELY_YJX6_MODE1, 209, 300, 38);
        this.mCurTime.setGravity(19);
        this.mCurTime.SetPxSize(35);
        this.mCurTime.setTextColor(-1);
        this.mCurMode = getRelativeManager().AddCusText(CanCameraUI.BTN_GEELY_YJX6_MODE1, 261, 300, 38);
        this.mCurMode.setGravity(19);
        this.mCurMode.SetPxSize(35);
        this.mCurMode.setTextColor(-1);
        this.mBtnPrev = getRelativeManager().AddButton(92, 427);
        this.mBtnPrev.setTag(0);
        this.mBtnPrev.setStateUpDn(R.drawable.original_car_seekup_up, R.drawable.original_car_seekup_dn);
        this.mBtnPrev.setOnTouchListener(this);
        this.mBtnPlay = getRelativeManager().AddButton(Can.CAN_MZD_LUOMU, 427);
        this.mBtnPlay.setTag(2);
        this.mBtnPlay.setStateUpDn(R.drawable.original_car_play_up, R.drawable.original_car_play_dn);
        this.mBtnPlay.setOnTouchListener(this);
        this.mBtnNext = addButton(392, 427);
        this.mBtnNext.setTag(1);
        this.mBtnNext.setStateUpDn(R.drawable.original_car_seekdn_up, R.drawable.original_car_seekdn_dn);
        this.mBtnNext.setOnTouchListener(this);
        this.mBtnOff = getRelativeManager().AddButton(CanCameraUI.BTN_CHANA_ALSVINV7_MODE3, 427);
        this.mBtnOff.setTag(3);
        this.mBtnOff.setStateDrawable(R.drawable.original_car_sx_up, R.drawable.original_car_sx_dn, R.drawable.original_car_sx_dn);
        this.mBtnOff.setOnClickListener(this);
        this.mBtnRepeat = getRelativeManager().AddButton(682, 427);
        this.mBtnRepeat.setTag(4);
        this.mBtnRepeat.setStateDrawable(R.drawable.original_car_xh_up, R.drawable.original_car_xh_dn, R.drawable.original_car_xh_dn);
        this.mBtnRepeat.setOnClickListener(this);
        this.mBtnRandom = getRelativeManager().AddButton(KeyDef.SKEY_NAVI_4, 427);
        this.mBtnRandom.setTag(5);
        this.mBtnRandom.setStateDrawable(R.drawable.original_car_sj_up, R.drawable.original_car_sj_dn, R.drawable.original_car_sj_dn);
        this.mBtnRandom.setOnClickListener(this);
    }

    public void ResetData(boolean check) {
        CanJni.CcHfDjGetCdInfo(this.mSrcInfo);
        if (!i2b(this.mSrcInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSrcInfo.Update)) {
            this.mSrcInfo.Update = 0;
            if (this.mSrcInfo.Mode == 0) {
                this.mStatus.setText(String.valueOf(this.mVolStatus) + ":  " + "--");
                this.mCurMode.setText(String.valueOf(this.mPlayMode) + ":  " + "--");
                this.mSongNum.setText(String.valueOf(this.mCurSongNum) + ":  " + "--");
                this.mCurTime.setText(String.valueOf(this.mPlayTime) + ":  " + "--");
                return;
            }
            if (this.mSrcInfo.fgPp == 1) {
                this.mBtnPlay.setStateUpDn(R.drawable.original_car_pause_up, R.drawable.original_car_pause_dn);
            } else {
                this.mBtnPlay.setStateUpDn(R.drawable.original_car_play_up, R.drawable.original_car_play_dn);
            }
            if (this.mSrcInfo.fgMute >= 0 && this.mSrcInfo.fgMute < 2) {
                this.mStatus.setText(String.valueOf(this.mVolStatus) + ":  " + this.mVolStatusStrs[this.mSrcInfo.fgMute]);
            }
            if (this.mSrcInfo.fgRpt == 0 && this.mSrcInfo.fgRand == 0) {
                this.mCurMode.setText(String.valueOf(this.mPlayMode) + ":  " + this.mPlayModeStrs[0]);
            } else if (this.mSrcInfo.fgRpt == 1) {
                this.mCurMode.setText(String.valueOf(this.mPlayMode) + ":  " + this.mPlayModeStrs[1]);
            } else if (this.mSrcInfo.fgRand == 1) {
                this.mCurMode.setText(String.valueOf(this.mPlayMode) + ":  " + this.mPlayModeStrs[2]);
            }
            this.mSongNum.setText(String.valueOf(this.mCurSongNum) + ":  " + this.mSrcInfo.CurTrack);
            this.mCurTime.setText(String.valueOf(this.mPlayTime) + ":  " + String.format("%02d:%02d", new Object[]{Integer.valueOf(this.mSrcInfo.PlayMin), Integer.valueOf(this.mSrcInfo.PlaySec)}));
        }
    }

    public void QueryData() {
        CanJni.CcHfDjQuery(28);
    }
}
