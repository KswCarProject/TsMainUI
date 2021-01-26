package com.ts.can.chrysler.xbs;

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
import com.ts.can.CanIF;
import com.ts.canview.CanItemProgressList;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanXbsygCDActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_FF = 6;
    public static final int ITEM_FR = 1;
    public static final int ITEM_LIST = 9;
    public static final int ITEM_NEXT = 5;
    public static final int ITEM_PAUSE = 4;
    public static final int ITEM_PLAY = 3;
    public static final int ITEM_PREV = 2;
    public static final int ITEM_RDM = 8;
    public static final int ITEM_RPT = 7;
    public static final String TAG = "CanXbsygCDActivity";
    protected CustomTextView mAlbum;
    protected CanDataInfo.ChrOthText mAlbumData = new CanDataInfo.ChrOthText();
    protected CustomTextView mArtist;
    protected CanDataInfo.ChrOthText mArtistData = new CanDataInfo.ChrOthText();
    protected ParamButton mBtnFF;
    protected ParamButton mBtnFR;
    protected ParamButton mBtnList;
    protected ParamButton mBtnNext;
    protected ParamButton mBtnPause;
    protected ParamButton mBtnPlay;
    protected ParamButton mBtnPrev;
    protected ParamButton mBtnRdm;
    protected ParamButton mBtnRpt;
    protected CanDataInfo.ChrOthCdInfo mInfoData = new CanDataInfo.ChrOthCdInfo();
    protected RelativeLayoutManager mManager;
    protected CanDataInfo.ChrOthText mNameData = new CanDataInfo.ChrOthText();
    protected CustomTextView mSong;
    protected CustomTextView mSta;
    protected CanDataInfo.ChrOthCdSta mStaData = new CanDataInfo.ChrOthCdSta();
    protected String[] mStrSta = {"无碟", "播放", "加载中", "读取中", "正在出碟", "暂停", "停止", TXZResourceManager.STYLE_DEFAULT, "换曲", "错误"};
    protected CustomTextView mTime;
    protected CustomTextView mTrack;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_jeep_ycsb_bg);
        this.mBtnFR = AddBtn(1, 123, 424, R.drawable.can_jeep_ycsb_down_up, R.drawable.can_jeep_ycsb_down_dn);
        this.mBtnPrev = AddBtn(2, 257, 424, R.drawable.can_jeep_ycsb_prv_up, R.drawable.can_jeep_ycsb_prv_dn);
        this.mBtnPlay = AddBtn(3, 391, 424, R.drawable.can_jeep_ycsb_play_up, R.drawable.can_jeep_ycsb_play_dn);
        this.mBtnPause = AddBtn(4, CanCameraUI.BTN_GEELY_YJX6_GJ, 424, R.drawable.can_jeep_ycsb_pause_up, R.drawable.can_jeep_ycsb_pause_dn);
        this.mBtnNext = AddBtn(5, 660, 424, R.drawable.can_jeep_ycsb_next_up, R.drawable.can_jeep_ycsb_next_dn);
        this.mBtnFF = AddBtn(6, KeyDef.SKEY_CHUP_1, 424, R.drawable.can_jeep_ycsb_speed_up, R.drawable.can_jeep_ycsb_speed_dn);
        this.mBtnRpt = AddBtn(7, 46, 25, R.drawable.can_jeep_ycsb_cycle_up, R.drawable.can_jeep_ycsb_cycle_dn);
        this.mBtnRdm = AddBtn(8, 180, 25, R.drawable.can_jeep_ycsb_random_up, R.drawable.can_jeep_ycsb_random_dn);
        this.mBtnList = AddBtn(9, 850, 25, R.drawable.can_jeep_ycsb_list_up, R.drawable.can_jeep_ycsb_list_dn);
        this.mManager.AddImage(44, 127, R.drawable.can_jeep_ycsb_sjx);
        this.mSta = AddTxtLt(84, 124, 300, 36);
        this.mManager.AddImage(44, 184, R.drawable.can_jeep_ycsb_music);
        this.mManager.AddImage(44, Can.CAN_MZD_LUOMU, R.drawable.can_jeep_ycsb_disc);
        this.mManager.AddImage(44, 299, R.drawable.can_jeep_ycsb_artist);
        this.mSong = AddTxtLt(84, 181, 600, 36);
        this.mAlbum = AddTxtLt(84, Can.CAN_GM_CAPTIVA_OD, 600, 36);
        this.mArtist = AddTxtLt(84, 296, 600, 36);
        this.mTrack = AddTxtCenter(412, 33, 200, 35);
        this.mTime = AddTxtCenter(362, 351, 300, 35);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        CanJni.ChrOthQuery(13, 0, 0, 0);
        Sleep(20);
        CanJni.ChrOthQuery(14, 0, 0, 0);
        Log.d(TAG, "-----onResume-----");
        Evc.GetInstance().evol_workmode_set(12);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
    }

    private void ResetData(boolean check) {
        CanJni.ChrOthGetCdInfo(this.mInfoData);
        CanJni.ChrOthGetCdSta(this.mStaData);
        if (i2b(this.mStaData.UpdateOnce) && (!check || i2b(this.mStaData.Update))) {
            this.mBtnList.Show(IsLoadOK());
            this.mStaData.Update = 0;
            UpdateInfo();
            if (this.mStaData.Sta >= this.mStrSta.length) {
                this.mSta.setText("其他状态");
            } else {
                this.mSta.setText(this.mStrSta[this.mStaData.Sta]);
            }
            this.mBtnRdm.SetSel(this.mStaData.Rnd);
            this.mBtnRpt.SetSel(this.mStaData.Rpt);
        }
        if (i2b(this.mInfoData.UpdateOnce) && (!check || i2b(this.mInfoData.Update))) {
            this.mInfoData.Update = 0;
            UpdateInfo();
        }
        CanJni.ChrOthGetID3(this.mNameData, this.mAlbumData, this.mArtistData);
        if (i2b(this.mNameData.UpdateOnce) && (!check || i2b(this.mNameData.Update))) {
            this.mNameData.Update = 0;
            this.mSong.setText(CanIF.byte2UnicodeStr(this.mNameData.szData));
        }
        if (i2b(this.mAlbumData.UpdateOnce) && (!check || i2b(this.mAlbumData.Update))) {
            this.mAlbumData.Update = 0;
            this.mAlbum.setText(CanIF.byte2UnicodeStr(this.mAlbumData.szData));
        }
        if (!i2b(this.mArtistData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mArtistData.Update)) {
            this.mArtistData.Update = 0;
            this.mArtist.setText(CanIF.byte2UnicodeStr(this.mArtistData.szData));
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsLoadOK() {
        if (1 == this.mStaData.Sta || 5 == this.mStaData.Sta || 6 == this.mStaData.Sta || 8 == this.mStaData.Sta) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void UpdateInfo() {
        if (IsLoadOK()) {
            if (this.mInfoData.TotalTrack <= 0 || this.mInfoData.TotalTrack < this.mInfoData.CurTrack) {
                this.mTrack.setText(String.format("%d", new Object[]{Integer.valueOf(this.mInfoData.CurTrack)}));
            } else {
                this.mTrack.setText(String.format("%d/%d", new Object[]{Integer.valueOf(this.mInfoData.CurTrack), Integer.valueOf(this.mInfoData.TotalTrack)}));
            }
            this.mTime.setText(String.format("%02d:%02d:%02d / %02d:%02d:%02d", new Object[]{Integer.valueOf(this.mInfoData.CurHour), Integer.valueOf(this.mInfoData.CurMin), Integer.valueOf(this.mInfoData.CurSec), Integer.valueOf(this.mInfoData.TotalHour), Integer.valueOf(this.mInfoData.TotalMin), Integer.valueOf(this.mInfoData.TotalSec)}));
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
            case 1:
                CdCtrl(6);
                return;
            case 2:
                CdCtrl(3);
                return;
            case 3:
                CdCtrl(2);
                return;
            case 4:
                CdCtrl(1);
                return;
            case 5:
                CdCtrl(4);
                return;
            case 6:
                CdCtrl(5);
                return;
            case 7:
                CdCtrl(Can.CAN_MZD_LUOMU);
                return;
            case 8:
                CdCtrl(Can.CAN_SITECHDEV_CW);
                return;
            case 9:
                enterSubWin(CanXbsygFileListActivity.class);
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
}
