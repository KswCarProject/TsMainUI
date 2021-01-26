package com.ts.can.mzd.axela;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.can.CanIF;
import com.ts.canview.CanItemProgressList;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanMzd3CDActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_FF = 6;
    public static final int ITEM_FR = 1;
    public static final int ITEM_NEXT = 5;
    public static final int ITEM_PAUSE = 4;
    public static final int ITEM_PLAY = 3;
    public static final int ITEM_PREV = 2;
    public static final int ITEM_RDM = 8;
    public static final int ITEM_RPT = 7;
    public static final String TAG = "CanMzd3CDActivity";
    protected static int mOldSta = -1;
    protected CustomTextView mAlbum;
    protected CustomTextView mArtist;
    protected ParamButton mBtnFF;
    protected ParamButton mBtnFR;
    protected ParamButton mBtnNext;
    protected ParamButton mBtnPause;
    protected ParamButton mBtnPlay;
    protected ParamButton mBtnPrev;
    protected ParamButton mBtnRdm;
    protected ParamButton mBtnRpt;
    protected CanDataInfo.Axela_Cd_Id3 mId3Data = new CanDataInfo.Axela_Cd_Id3();
    protected CanDataInfo.Axela_Cd_PlayInfo mInfoData = new CanDataInfo.Axela_Cd_PlayInfo();
    protected RelativeLayoutManager mManager;
    protected CustomTextView mSong;
    protected CustomTextView mSta;
    protected CanDataInfo.Axela_Cd_Sta mStaData = new CanDataInfo.Axela_Cd_Sta();
    protected int mStartCount = 0;
    protected String[] mStrSta = {"无碟", "正在出碟", TXZResourceManager.STYLE_DEFAULT, "正在入碟", "读取中", "播放", "暂停"};
    protected CustomTextView mTime;
    protected CustomTextView mTrack;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_jeep_ycsb_bg);
        this.mBtnPrev = AddBtn(2, 257, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_prv_up, R.drawable.can_jeep_ycsb_prv_dn);
        this.mBtnPlay = AddBtn(3, 391, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_play_up, R.drawable.can_jeep_ycsb_play_dn);
        this.mBtnPause = AddBtn(4, CanCameraUI.BTN_GEELY_YJX6_GJ, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_pause_up, R.drawable.can_jeep_ycsb_pause_dn);
        this.mBtnNext = AddBtn(5, 660, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_next_up, R.drawable.can_jeep_ycsb_next_dn);
        this.mBtnRpt = AddBtn(7, 46, 25, R.drawable.can_jeep_ycsb_cycle_up, R.drawable.can_jeep_ycsb_cycle_dn);
        this.mBtnRdm = AddBtn(8, 180, 25, R.drawable.can_jeep_ycsb_random_up, R.drawable.can_jeep_ycsb_random_dn);
        this.mManager.AddImage(44, 127, R.drawable.can_jeep_ycsb_sjx);
        this.mSta = AddTxtLt(84, 119, 300, 42);
        this.mManager.AddImage(44, 184, R.drawable.can_jeep_ycsb_music);
        this.mManager.AddImage(44, Can.CAN_MZD_LUOMU, R.drawable.can_jeep_ycsb_artist);
        this.mSong = AddTxtLt(84, 176, 300, 42);
        this.mArtist = AddTxtLt(84, Can.CAN_LIEBAO_WC, 300, 42);
        this.mTrack = AddTxtLt(450, 23, 200, 55);
        this.mTrack.SetPixelSize(40);
        this.mTime = AddTxtCenter(362, KeyDef.RKEY_res4, 300, 35);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Log.d(TAG, "-----onResume-----");
        Evc.GetInstance().evol_workmode_set(12);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        CanJni.ChrOthQuery(66, 0, 0, 0);
        Sleep(1);
        CanJni.ChrOthQuery(67, 0, 0, 0);
        Log.d(TAG, "-----onPause-----");
    }

    private void ResetData(boolean check) {
        CanJni.AxelaGetCdInfo(this.mStaData, this.mInfoData, this.mId3Data);
        if (i2b(this.mStaData.UpdateOnce) && (!check || i2b(this.mStaData.Update))) {
            this.mStaData.Update = 0;
            UpdateInfo();
            if (this.mStaData.CurSta >= this.mStrSta.length) {
                this.mSta.setText("其他状态");
            } else {
                this.mSta.setText(this.mStrSta[this.mStaData.CurSta]);
            }
            this.mBtnRdm.SetSel(this.mStaData.fgRdm);
            this.mBtnRpt.SetSel(this.mStaData.fgRpt);
        }
        if (i2b(this.mInfoData.UpdateOnce) && (!check || i2b(this.mInfoData.Update))) {
            this.mInfoData.Update = 0;
            UpdateInfo();
        }
        if (i2b(this.mId3Data.UpdateOnce) && (!check || i2b(this.mId3Data.Update))) {
            this.mId3Data.Update = 0;
            this.mSong.setText(CanIF.byte2String(this.mId3Data.Title));
            this.mArtist.setText(CanIF.byte2String(this.mId3Data.Artist));
        }
        CheckOtherSta2Play();
    }

    /* access modifiers changed from: protected */
    public void CheckOtherSta2Play() {
        if (!CanIF.IsExdMode() || 16 != CanJni.AxelaGetCdSta()) {
            this.mStartCount = 0;
            return;
        }
        this.mStartCount++;
        if (this.mStartCount < 60 && this.mStartCount % 5 == 0) {
            CanJni.AxelaCDCtrl(Can.CAN_VOLKS_XP, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void UpdateInfo() {
        if (5 == this.mStaData.CurSta || 6 == this.mStaData.CurSta) {
            this.mTrack.setText(String.format("TRACK %d", new Object[]{Integer.valueOf(this.mInfoData.CurTrack)}));
            if (this.mInfoData.TotalHour >= 1) {
                this.mTime.setText(String.format("%02d:%02d:%02d / %02d:%02d:%02d", new Object[]{Integer.valueOf(this.mInfoData.CurHour), Integer.valueOf(this.mInfoData.CurMin), Integer.valueOf(this.mInfoData.CurSec), Integer.valueOf(this.mInfoData.TotalHour), Integer.valueOf(this.mInfoData.TotalMin), Integer.valueOf(this.mInfoData.TotalSec)}));
                return;
            }
            this.mTime.setText(String.format("%02d:%02d / %02d:%02d", new Object[]{Integer.valueOf(this.mInfoData.CurMin), Integer.valueOf(this.mInfoData.CurSec), Integer.valueOf(this.mInfoData.TotalMin), Integer.valueOf(this.mInfoData.TotalSec)}));
            return;
        }
        this.mTrack.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mTime.setText(TXZResourceManager.STYLE_DEFAULT);
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTxtLt(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPixelSize(30);
        temp.setGravity(19);
        return temp;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTxtCenter(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPixelSize(30);
        temp.setGravity(17);
        return temp;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanIF.Mzd3Prev();
                return;
            case 3:
                CanIF.Mzd3Play();
                return;
            case 4:
                CanIF.Mzd3Pause();
                return;
            case 5:
                CanIF.Mzd3Next();
                return;
            case 7:
                CanIF.Mzd3Rpt();
                return;
            case 8:
                CanIF.Mzd3Rdm();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
    }

    /* access modifiers changed from: protected */
    public void CdCtrl(int cmd) {
        CanJni.ChrOthCDCtrl(cmd, 0);
    }

    public static void DealStaChange() {
        int curSta = CanJni.AxelaGetCdSta();
        if (curSta != mOldSta) {
            Log.d(TAG, "DealStaChange old = " + mOldSta + ", cur = " + curSta);
            mOldSta = curSta;
            switch (curSta) {
                case 1:
                    CanJni.AxelaCDCtrl(0, 0);
                    return;
                case 4:
                    if (CanIF.IsExdMode()) {
                        CanJni.AxelaCDCtrl(Can.CAN_VOLKS_XP, 0);
                        return;
                    } else if (CanFunc.IsCamMode() == 0) {
                        CanFunc.showCanActivity(CanMzd3CDActivity.class);
                        return;
                    } else {
                        return;
                    }
                case 16:
                    if (CanIF.IsExdMode()) {
                        CanJni.AxelaCDCtrl(Can.CAN_VOLKS_XP, 0);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
