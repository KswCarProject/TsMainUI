package com.ts.can.mzd.wc;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.can.CanIF;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanMzdWcCDView extends CanRelativeCarInfoView {
    public static final int ITEM_FF = 6;
    public static final int ITEM_FR = 1;
    public static final int ITEM_NEXT = 5;
    public static final int ITEM_PAUSE = 4;
    public static final int ITEM_PLAY = 3;
    public static final int ITEM_PREV = 2;
    public static final int ITEM_RDM = 8;
    public static final int ITEM_RPT = 7;
    private static int mOldSta = -1;
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
    private CanDataInfo.MzdWcCD mCDInfo;
    private CanDataInfo.MzdWcCD_Id3 mCdIds;
    private int mRequestTime = 0;
    protected CustomTextView mSong;
    protected CustomTextView mSta;
    protected String[] mStrSta = {"无碟", "播放", "入碟", "正在读碟", "出碟", "暂停", "停止", "无效", "换曲", "错误"};
    protected CustomTextView mTime;
    protected CustomTextView mTrack;

    public CanMzdWcCDView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanIF.MzdWcFR();
                return;
            case 2:
                CanIF.MzdWcPrev();
                return;
            case 3:
                CanIF.MzdWcPlay();
                return;
            case 4:
                CanIF.MzdWcPause();
                return;
            case 5:
                CanIF.MzdWcNext();
                return;
            case 6:
                CanIF.MzdWcFF();
                return;
            case 7:
                CanIF.MzdWcRpt(this.mCDInfo.Rpt);
                return;
            case 8:
                CanIF.MzdWcRdm(this.mCDInfo.Rdm);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        setBackgroundResource(R.drawable.can_jeep_ycsb_bg);
        this.mBtnFR = AddBtn(1, 123, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_down_up, R.drawable.can_jeep_ycsb_down_dn);
        this.mBtnPrev = AddBtn(2, 257, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_prv_up, R.drawable.can_jeep_ycsb_prv_dn);
        this.mBtnPlay = AddBtn(3, 391, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_play_up, R.drawable.can_jeep_ycsb_play_dn);
        this.mBtnPause = AddBtn(4, CanCameraUI.BTN_GEELY_YJX6_GJ, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_pause_up, R.drawable.can_jeep_ycsb_pause_dn);
        this.mBtnNext = AddBtn(5, 660, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_next_up, R.drawable.can_jeep_ycsb_next_dn);
        this.mBtnFF = AddBtn(6, KeyDef.SKEY_CHUP_1, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_speed_up, R.drawable.can_jeep_ycsb_speed_dn);
        this.mBtnRpt = AddBtn(7, 46, 25, R.drawable.can_jeep_ycsb_cycle_up, R.drawable.can_jeep_ycsb_cycle_dn);
        this.mBtnRdm = AddBtn(8, 180, 25, R.drawable.can_jeep_ycsb_random_up, R.drawable.can_jeep_ycsb_random_dn);
        addImage(44, 127, R.drawable.can_jeep_ycsb_sjx);
        addImage(44, 186, R.drawable.can_jeep_ycsb_music);
        addImage(44, Can.CAN_BYD_M6_DJ, R.drawable.can_jeep_ycsb_disc);
        addImage(44, 300, R.drawable.can_jeep_ycsb_artist);
        this.mSta = AddTxtLt(84, 123, 300, 40);
        this.mSong = AddTxtLt(84, 181, 300, 40);
        this.mAlbum = AddTxtLt(84, Can.CAN_GM_CAPTIVA_OD, 300, 40);
        this.mArtist = AddTxtLt(84, KeyDef.RKEY_RADIO_SCAN, 300, 40);
        this.mTrack = AddTxtLt(450, 23, 360, 55);
        this.mTrack.SetPixelSize(40);
        this.mTime = AddTxtLt(362, KeyDef.RKEY_res4, 300, 35);
        this.mTime.setGravity(17);
        this.mCDInfo = new CanDataInfo.MzdWcCD();
        this.mCdIds = new CanDataInfo.MzdWcCD_Id3();
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
    }

    public void ResetData(boolean check) {
        CanJni.MzdWcGetCDInfo(this.mCDInfo);
        CanJni.MzdWcGetCDId3(this.mCdIds);
        if (i2b(this.mCDInfo.UpdateOnce) && (!check || i2b(this.mCDInfo.Update))) {
            this.mCDInfo.Update = 0;
            setShowGone((View) this.mSta, this.mCDInfo.DiscVaild);
            if (this.mCDInfo.PlayStaVaild == 0) {
                this.mBtnRpt.setSelected(false);
                this.mBtnRdm.setSelected(false);
            }
            setShowGone((View) this.mTrack, this.mCDInfo.TrackVaild);
            setShowGone((View) this.mTime, this.mCDInfo.TrackVaild);
            if (this.mCDInfo.DiscSta < this.mStrSta.length) {
                this.mSta.setText(this.mStrSta[this.mCDInfo.DiscSta]);
            } else {
                this.mSta.setText("");
            }
            this.mBtnRpt.setSelected(this.mCDInfo.Rpt == 2);
            this.mBtnRdm.setSelected(this.mCDInfo.Rdm == 2);
            if (this.mCDInfo.DiscSta == 1 || this.mCDInfo.DiscSta == 5) {
                this.mTrack.setText(String.format("TRACK %d/%d", new Object[]{Integer.valueOf(this.mCDInfo.CurTrackNum), Integer.valueOf(this.mCDInfo.TotalCDTrack)}));
                int hour = this.mCDInfo.PlayTime / 3600;
                int min = (this.mCDInfo.PlayTime - (hour * 3600)) / 60;
                int second = this.mCDInfo.PlayTime % 60;
                int totalHour = this.mCDInfo.CurTrackTime / 3600;
                int totalMin = (this.mCDInfo.CurTrackTime - (totalHour * 3600)) / 60;
                int totalSecond = this.mCDInfo.CurTrackTime % 60;
                if (hour >= 1) {
                    this.mTime.setText(String.format("%02d:%02d:%02d / %02d:%02d:%02d", new Object[]{Integer.valueOf(hour), Integer.valueOf(min), Integer.valueOf(second), Integer.valueOf(totalHour), Integer.valueOf(totalMin), Integer.valueOf(totalSecond)}));
                } else {
                    this.mTime.setText(String.format("%02d:%02d / %02d:%02d", new Object[]{Integer.valueOf(min), Integer.valueOf(second), Integer.valueOf(totalMin), Integer.valueOf(totalSecond)}));
                }
            } else {
                this.mTime.setText("");
                this.mTrack.setText("");
                this.mSong.setText("");
                this.mArtist.setText("");
                this.mAlbum.setText("");
            }
        }
        if (i2b(this.mCdIds.UpdateOnce) && (!check || i2b(this.mCdIds.Update))) {
            this.mCdIds.Update = 0;
            if (i2b(this.mCdIds.NameUpdateOnce) && i2b(this.mCdIds.NameUpdate)) {
                this.mCdIds.NameUpdate = 0;
                this.mSong.setText(CanIF.byte2String(this.mCdIds.NameId3));
                CanJni.MzdWcCdCmd(10, 1);
                CanJni.MzdWcCdCmd(10, 2);
                this.mRequestTime = 100;
            }
            if (i2b(this.mCdIds.SongerUpdateOnce) && i2b(this.mCdIds.SongerUpdate)) {
                this.mCdIds.SongerUpdate = 0;
                this.mArtist.setText(CanIF.byte2String(this.mCdIds.SongerId3));
                CanJni.MzdWcCdCmd(10, 0);
                CanJni.MzdWcCdCmd(10, 2);
                this.mRequestTime = 100;
            }
            if (i2b(this.mCdIds.AlbumUpdateOnce) && i2b(this.mCdIds.AlbumUpdate)) {
                this.mCdIds.AlbumUpdate = 0;
                this.mAlbum.setText(CanIF.byte2String(this.mCdIds.AlbumId3));
                CanJni.MzdWcCdCmd(10, 0);
                CanJni.MzdWcCdCmd(10, 1);
                this.mRequestTime = 100;
            }
        }
        if (this.mRequestTime == 0 && (this.mCDInfo.DiscSta == 1 || this.mCDInfo.DiscSta == 5)) {
            CanJni.MzdWcCdCmd(10, 0);
            CanJni.MzdWcCdCmd(10, 1);
            CanJni.MzdWcCdCmd(10, 2);
            this.mRequestTime = 100;
        }
        this.mRequestTime--;
    }

    public static void DealStatusChange() {
        CanDataInfo.MzdWcCdSta cdSta = new CanDataInfo.MzdWcCdSta();
        CanJni.MzdWcGetCdSta(cdSta);
        Log.d("HAHA", "DealStaChange old = " + mOldSta + ", cur = " + cdSta.Sta);
        if (cdSta.Sta != mOldSta) {
            mOldSta = cdSta.Sta;
            switch (cdSta.Sta) {
                case 3:
                    if (CanIF.IsExdMode()) {
                        CanJni.MzdWcCdCmd(9, 1);
                        return;
                    } else if (CanFunc.IsCamMode() == 0) {
                        CanFunc.showCanActivity(CanCarInfoSub1Activity.class, -1);
                        return;
                    } else {
                        return;
                    }
                case 4:
                    CanJni.MzdWcCdCmd(9, 0);
                    return;
                default:
                    return;
            }
        }
    }

    public void QueryData() {
        CanJni.MzdWcQuery(5, 1, 174);
        Sleep(5);
        CanJni.MzdWcQuery(5, 1, 165);
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
    public CustomTextView AddTxtLt(int x, int y, int w, int h) {
        CustomTextView temp = getRelativeManager().AddCusText(x, y, w, h);
        temp.SetPixelSize(30);
        temp.setGravity(19);
        return temp;
    }
}
