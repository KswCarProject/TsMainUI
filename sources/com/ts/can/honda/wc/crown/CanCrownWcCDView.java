package com.ts.can.honda.wc.crown;

import android.app.Activity;
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
import java.util.ArrayList;

public class CanCrownWcCDView extends CanRelativeCarInfoView {
    public static final int ITEM_DISC = 10;
    public static final int ITEM_FF = 6;
    public static final int ITEM_FR = 1;
    public static final int ITEM_MODE = 11;
    public static final int ITEM_NEXT = 5;
    public static final int ITEM_PAUSE = 4;
    public static final int ITEM_PLAY = 3;
    public static final int ITEM_PREV = 2;
    public static final int ITEM_RDM = 8;
    public static final int ITEM_RPT = 7;
    public static final int ITEM_SEARCH = 9;
    protected static String[] mChangeSta = {"reading TOC", "Pause", "Play", "Fast", "User search", "Internal search", "Stop", "Rom read", "Rom Search", "Retrieving", "Disc changing(User)", "Disc changing(Internal", "Eject"};
    protected static String[] mModeSta = {"单碟扫描", "所有碟片扫描", "单碟重复", "所有碟片重复", "单碟随机", "所有碟片随机"};
    protected static int mOldSta = -1;
    protected static String[] mStrSta = {"无碟", "播放", "入碟", "正在读碟", "出碟", "暂停", "停止", "无效", "换曲", "错误"};
    protected ArrayList<CustomImgView> CDList;
    protected ArrayList<CustomTextView> CDTXTList;
    protected CustomTextView mAlbum;
    protected CustomTextView mArtist;
    protected ParamButton mBtnDISC;
    protected ParamButton mBtnFF;
    protected ParamButton mBtnFR;
    protected ParamButton mBtnMODE;
    protected ParamButton mBtnNext;
    protected ParamButton mBtnPause;
    protected ParamButton mBtnPlay;
    protected ParamButton mBtnPrev;
    protected ParamButton mBtnRdm;
    protected ParamButton mBtnRpt;
    protected ParamButton mBtnSEARCH;
    protected CustomImgView mCD1;
    protected CustomImgView mCD2;
    protected CustomImgView mCD3;
    protected CustomImgView mCD4;
    protected CustomImgView mCD5;
    protected CustomImgView mCD6;
    protected CanDataInfo.CrownWcCdInfo mInfoData;
    protected CustomTextView mMode;
    protected CustomTextView mSong;
    protected CustomTextView mSta;
    protected CustomTextView mTime;
    protected CustomTextView mTrack;
    protected CustomTextView mTxtCD1;
    protected CustomTextView mTxtCD2;
    protected CustomTextView mTxtCD3;
    protected CustomTextView mTxtCD4;
    protected CustomTextView mTxtCD5;
    protected CustomTextView mTxtCD6;
    protected CustomTextView mWorkSta;

    public CanCrownWcCDView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CdCtrl(8, 0);
                return;
            case 2:
                CdCtrl(7, 0);
                return;
            case 3:
                CdCtrl(1, 0);
                return;
            case 4:
                CdCtrl(2, 0);
                return;
            case 5:
                CdCtrl(7, 1);
                return;
            case 6:
                CdCtrl(8, 1);
                return;
            case 7:
                CdCtrl(16, 1);
                return;
            case 8:
                CdCtrl(18, 1);
                return;
            case 9:
                CdCtrl(17, 1);
                return;
            case 10:
                CdCtrl(6, 1);
                return;
            case 11:
                CanDataInfo.CrownWcTunerInfo tunerInfo = new CanDataInfo.CrownWcTunerInfo();
                CanJni.CrownWcGetTunerInfo(tunerInfo);
                if (tunerInfo.Band == 1) {
                    CanJni.CrownWcSourceSet(1, 1);
                    return;
                } else if (tunerInfo.Band == 2) {
                    CanJni.CrownWcSourceSet(1, 2);
                    return;
                } else if (tunerInfo.Band == 3) {
                    CanJni.CrownWcSourceSet(1, 3);
                    return;
                } else {
                    CanJni.CrownWcSourceSet(1, 2);
                    return;
                }
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
        this.mInfoData = new CanDataInfo.CrownWcCdInfo();
        setBackgroundResource(R.drawable.can_jeep_ycsb_bg02);
        this.mBtnFR = AddBtn(1, 123, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_down_up, R.drawable.can_jeep_ycsb_down_dn);
        this.mBtnPrev = AddBtn(2, 257, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_prv_up, R.drawable.can_jeep_ycsb_prv_dn);
        this.mBtnPlay = AddBtn(3, 391, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_play_up, R.drawable.can_jeep_ycsb_play_dn);
        this.mBtnPause = AddBtn(4, CanCameraUI.BTN_GEELY_YJX6_GJ, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_pause_up, R.drawable.can_jeep_ycsb_pause_dn);
        this.mBtnNext = AddBtn(5, 660, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_next_up, R.drawable.can_jeep_ycsb_next_dn);
        this.mBtnFF = AddBtn(6, KeyDef.SKEY_CHUP_1, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_speed_up, R.drawable.can_jeep_ycsb_speed_dn);
        this.mBtnRpt = AddBtn(7, 46, 25, R.drawable.can_jeep_ycsb_cycle_up, R.drawable.can_jeep_ycsb_cycle_dn);
        this.mBtnRdm = AddBtn(8, 180, 25, R.drawable.can_jeep_ycsb_random_up, R.drawable.can_jeep_ycsb_random_dn);
        this.mBtnSEARCH = AddBtn(9, 694, 25, R.drawable.can_jeep_ycsb_search_up, R.drawable.can_jeep_ycsb_search_dn);
        this.mBtnDISC = AddBtn(10, KeyDef.SKEY_PP_5, 25, R.drawable.can_jeep_ycsb_cd_up, R.drawable.can_jeep_ycsb_cd_dn);
        this.mBtnMODE = AddBtn(11, 447, 25, R.drawable.can_jeep_ycsb_qh_up, R.drawable.can_jeep_ycsb_qh_dn);
        this.mTxtCD1 = AddTxtCenter(604, 200, 81, 30);
        this.mTxtCD2 = AddTxtCenter(748, 200, 81, 30);
        this.mTxtCD3 = AddTxtCenter(892, 200, 81, 30);
        this.mTxtCD4 = AddTxtCenter(604, KeyDef.RKEY_RDS_TA, 81, 30);
        this.mTxtCD5 = AddTxtCenter(748, KeyDef.RKEY_RDS_TA, 81, 30);
        this.mTxtCD6 = AddTxtCenter(892, KeyDef.RKEY_RDS_TA, 81, 30);
        this.CDTXTList = new ArrayList<>();
        this.CDTXTList.add(this.mTxtCD1);
        this.CDTXTList.add(this.mTxtCD2);
        this.CDTXTList.add(this.mTxtCD3);
        this.CDTXTList.add(this.mTxtCD4);
        this.CDTXTList.add(this.mTxtCD5);
        this.CDTXTList.add(this.mTxtCD6);
        this.mCD1 = getRelativeManager().AddImage(604, 113, R.drawable.can_jeep_ycsb_cd03);
        this.mCD2 = getRelativeManager().AddImage(748, 113, R.drawable.can_jeep_ycsb_cd03);
        this.mCD3 = getRelativeManager().AddImage(892, 113, R.drawable.can_jeep_ycsb_cd03);
        this.mCD4 = getRelativeManager().AddImage(604, Can.CAN_BYD_M6_DJ, R.drawable.can_jeep_ycsb_cd03);
        this.mCD5 = getRelativeManager().AddImage(748, Can.CAN_BYD_M6_DJ, R.drawable.can_jeep_ycsb_cd03);
        this.mCD6 = getRelativeManager().AddImage(892, Can.CAN_BYD_M6_DJ, R.drawable.can_jeep_ycsb_cd03);
        this.CDList = new ArrayList<>();
        this.CDList.add(this.mCD1);
        this.CDList.add(this.mCD2);
        this.CDList.add(this.mCD3);
        this.CDList.add(this.mCD4);
        this.CDList.add(this.mCD5);
        this.CDList.add(this.mCD6);
        this.mTrack = AddTxtLt(44, 133, 300, 36);
        this.mMode = AddTxtLt(44, Can.CAN_SGMW_WC, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 50);
        this.mWorkSta = AddTxtLt(44, KeyDef.RKEY_POWER_OFF, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 50);
        this.mTime = AddTxtCenter(362, KeyDef.RKEY_res4, 300, 35);
    }

    public void ResetData(boolean check) {
        CanJni.CrownWcGetCdInfo(this.mInfoData);
        if (!i2b(this.mInfoData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mInfoData.Update)) {
            this.mInfoData.Update = 0;
            for (int i = 0; i < this.CDTXTList.size(); i++) {
                this.CDTXTList.get(i).setText("CD" + (i + 1));
            }
            int cdNum = this.mInfoData.CurCD;
            if (cdNum <= this.CDList.size()) {
                setCDStatus(this.mInfoData.DiscStatus);
                this.CDList.get(cdNum - 1).setImageResource(R.drawable.can_jeep_ycsb_cd02);
                this.CDTXTList.get(cdNum - 1).setText("播放");
            }
            this.mTrack.setText("Track:" + String.format("%d", new Object[]{Integer.valueOf(this.mInfoData.TrackNum)}));
            this.mTime.setText(String.format("%02d:%02d", new Object[]{Integer.valueOf(this.mInfoData.PlayMin), Integer.valueOf(this.mInfoData.PlaySec)}));
            if (getPlayMode(this.mInfoData.PlayMode) == 255) {
                this.mMode.ShowGone(false);
            } else {
                this.mMode.ShowGone(true);
                if (getPlayMode(this.mInfoData.PlayMode) < mModeSta.length) {
                    this.mMode.setText("模式:" + mModeSta[getPlayMode(this.mInfoData.PlayMode)]);
                }
            }
            if (this.mInfoData.Status < mChangeSta.length) {
                this.mWorkSta.ShowGone(true);
                this.mWorkSta.setText("工作:" + mChangeSta[this.mInfoData.Status]);
                return;
            }
            this.mWorkSta.ShowGone(false);
        }
    }

    private int getPlayMode(int status) {
        if ((status & 128) > 0) {
            return 0;
        }
        if ((status & 64) > 0) {
            return 1;
        }
        if ((status & 32) > 0) {
            return 2;
        }
        if ((status & 16) > 0) {
            return 3;
        }
        if ((status & 8) > 0) {
            return 4;
        }
        if ((status & 4) > 0) {
            return 5;
        }
        return 255;
    }

    private void showAll() {
        this.mMode.setText("模式:" + mModeSta[1]);
        this.mWorkSta.setText("工作:Disc changing(Internal)");
        this.mTime.setText("59:59");
        this.mTrack.setText("Track:" + String.format("%d", new Object[]{6}));
        for (int i = 0; i < this.CDTXTList.size(); i++) {
            this.CDTXTList.get(i).setText("CD" + (i + 1));
        }
    }

    private void setCDStatus(int discStatus) {
        if ((discStatus & 1) > 0) {
            this.mCD1.setImageResource(R.drawable.can_jeep_ycsb_cd01);
        } else {
            this.mCD1.setImageResource(R.drawable.can_jeep_ycsb_cd03);
        }
        if ((discStatus & 2) > 0) {
            this.mCD2.setImageResource(R.drawable.can_jeep_ycsb_cd01);
        } else {
            this.mCD2.setImageResource(R.drawable.can_jeep_ycsb_cd03);
        }
        if ((discStatus & 4) > 0) {
            this.mCD3.setImageResource(R.drawable.can_jeep_ycsb_cd01);
        } else {
            this.mCD3.setImageResource(R.drawable.can_jeep_ycsb_cd03);
        }
        if ((discStatus & 8) > 0) {
            this.mCD4.setImageResource(R.drawable.can_jeep_ycsb_cd01);
        } else {
            this.mCD4.setImageResource(R.drawable.can_jeep_ycsb_cd03);
        }
        if ((discStatus & 16) > 0) {
            this.mCD5.setImageResource(R.drawable.can_jeep_ycsb_cd01);
        } else {
            this.mCD5.setImageResource(R.drawable.can_jeep_ycsb_cd03);
        }
        if ((discStatus & 32) > 0) {
            this.mCD6.setImageResource(R.drawable.can_jeep_ycsb_cd01);
        } else {
            this.mCD6.setImageResource(R.drawable.can_jeep_ycsb_cd03);
        }
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTxtLt(int x, int y, int w, int h) {
        CustomTextView temp = getRelativeManager().AddCusText(x, y, w, h);
        temp.SetPixelSize(30);
        temp.setGravity(19);
        return temp;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTxtCenter(int x, int y, int w, int h) {
        CustomTextView temp = getRelativeManager().AddCusText(x, y, w, h);
        temp.SetPixelSize(26);
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public void CdCtrl(int cmd, int part1) {
        CanJni.CrownWcCdSet(cmd, part1);
    }

    public void QueryData() {
        CanJni.CrownWcQuery(134, 255);
    }
}
