package com.ts.can.chrysler.wc;

import android.app.Activity;
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
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanChryslerWcCDView extends CanRelativeCarInfoView {
    public static final int ITEM_FF = 6;
    public static final int ITEM_FR = 1;
    public static final int ITEM_NEXT = 5;
    public static final int ITEM_PAUSE = 4;
    public static final int ITEM_PLAY = 3;
    public static final int ITEM_PREV = 2;
    public static final int ITEM_RDM = 8;
    public static final int ITEM_RPT = 7;
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
    protected CanDataInfo.ChrWcCD_Id3 mCDIDData;
    protected CanDataInfo.ChrWcCDInfo mInfoData;
    protected CustomTextView mSong;
    protected CustomTextView mSta;
    protected String[] mStrSta = {"无碟", "播放", "入碟", "正在读碟", "出碟", "暂停", "停止", "无效", "换曲", "错误"};
    protected CustomTextView mTime;
    protected CustomTextView mTrack;

    public CanChryslerWcCDView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CdCtrl(3, 1, 0);
                return;
            case 2:
                CdCtrl(3, 0, 0);
                return;
            case 3:
                CdCtrl(1, 0, 0);
                return;
            case 4:
                CdCtrl(2, 0, 0);
                return;
            case 5:
                CdCtrl(4, 0, 0);
                return;
            case 6:
                CdCtrl(4, 1, 0);
                return;
            case 7:
                if (this.mBtnRpt.isSelected()) {
                    CdCtrl(5, 0, 0);
                    return;
                } else {
                    CdCtrl(5, 1, 0);
                    return;
                }
            case 8:
                if (this.mBtnRdm.isSelected()) {
                    CdCtrl(6, 0, 0);
                    return;
                } else {
                    CdCtrl(6, 1, 0);
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
        this.mInfoData = new CanDataInfo.ChrWcCDInfo();
        this.mCDIDData = new CanDataInfo.ChrWcCD_Id3();
        setBackgroundResource(R.drawable.can_jeep_ycsb_bg);
        this.mBtnFR = AddBtn(1, 123, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_down_up, R.drawable.can_jeep_ycsb_down_dn);
        this.mBtnPrev = AddBtn(2, 257, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_prv_up, R.drawable.can_jeep_ycsb_prv_dn);
        this.mBtnPlay = AddBtn(3, 391, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_play_up, R.drawable.can_jeep_ycsb_play_dn);
        this.mBtnPause = AddBtn(4, CanCameraUI.BTN_GEELY_YJX6_GJ, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_pause_up, R.drawable.can_jeep_ycsb_pause_dn);
        this.mBtnNext = AddBtn(5, 660, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_next_up, R.drawable.can_jeep_ycsb_next_dn);
        this.mBtnFF = AddBtn(6, KeyDef.SKEY_CHUP_1, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_speed_up, R.drawable.can_jeep_ycsb_speed_dn);
        this.mBtnRpt = AddBtn(7, 46, 25, R.drawable.can_jeep_ycsb_cycle_up, R.drawable.can_jeep_ycsb_cycle_dn);
        this.mBtnRdm = AddBtn(8, 180, 25, R.drawable.can_jeep_ycsb_random_up, R.drawable.can_jeep_ycsb_random_dn);
        getRelativeManager().AddImage(44, 127, R.drawable.can_jeep_ycsb_sjx);
        this.mSta = AddTxtLt(84, 124, 300, 36);
        addImage(44, 184, R.drawable.can_jeep_ycsb_music);
        addImage(44, Can.CAN_MZD_LUOMU, R.drawable.can_jeep_ycsb_disc);
        addImage(44, 299, R.drawable.can_jeep_ycsb_artist);
        this.mSong = AddTxtLt(84, 181, 300, 40);
        this.mAlbum = AddTxtLt(84, Can.CAN_GM_CAPTIVA_OD, 300, 40);
        this.mArtist = AddTxtLt(84, 296, 300, 40);
        this.mTrack = AddTxtLt(800, 33, 200, 35);
        this.mTime = AddTxtCenter(362, KeyDef.RKEY_res4, 300, 35);
    }

    public void ResetData(boolean check) {
        CanJni.ChryslerWcGetCDId3(this.mCDIDData);
        CanJni.ChryslerWcGetCDInfo(this.mInfoData);
        if (i2b(this.mInfoData.UpdateOnce) && (!check || i2b(this.mInfoData.Update))) {
            this.mInfoData.Update = 0;
            if (this.mInfoData.TrackVaild == 1) {
                UpdateInfo();
            } else {
                this.mTrack.setText(TXZResourceManager.STYLE_DEFAULT);
                this.mTime.setText(TXZResourceManager.STYLE_DEFAULT);
            }
            if (this.mInfoData.DiscVaild != 1) {
                this.mSta.setText(TXZResourceManager.STYLE_DEFAULT);
            } else if (this.mInfoData.DiscSta >= this.mStrSta.length) {
                this.mSta.setText("其他状态");
            } else {
                this.mSta.setText(this.mStrSta[this.mInfoData.DiscSta]);
            }
            if (this.mInfoData.PlayStaVaild == 1) {
                this.mBtnRdm.SetSel(this.mInfoData.PlayRdm);
                this.mBtnRpt.SetSel(this.mInfoData.PlayLoop);
            } else {
                this.mBtnRdm.SetSel(0);
                this.mBtnRpt.SetSel(0);
            }
        }
        if (!i2b(this.mCDIDData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCDIDData.Update)) {
            this.mCDIDData.Update = 0;
            if (this.mInfoData.CurTrackNum == this.mCDIDData.Track[2] && i2b(this.mCDIDData.AlbumUpdateOnce) && i2b(this.mCDIDData.AlbumUpdate)) {
                this.mCDIDData.AlbumUpdate = 0;
                this.mAlbum.setText(CanIF.byte2UnicodeStr(this.mCDIDData.AlbumId3));
            }
            if (this.mInfoData.CurTrackNum == this.mCDIDData.Track[0] && i2b(this.mCDIDData.NameUpdateOnce) && i2b(this.mCDIDData.NameUpdate)) {
                this.mCDIDData.NameUpdate = 0;
                this.mSong.setText(CanIF.byte2UnicodeStr(this.mCDIDData.NameId3));
            }
            if (this.mInfoData.CurTrackNum == this.mCDIDData.Track[1] && i2b(this.mCDIDData.SongerUpdateOnce) && i2b(this.mCDIDData.SongerUpdate)) {
                this.mCDIDData.SongerUpdate = 0;
                this.mArtist.setText(CanIF.byte2UnicodeStr(this.mCDIDData.SongerId3));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void UpdateInfo() {
        if (1 == this.mInfoData.DiscSta || 5 == this.mInfoData.DiscSta || 6 == this.mInfoData.DiscSta || 8 == this.mInfoData.DiscSta) {
            if (this.mInfoData.TotalCDTrack <= 0 || this.mInfoData.TotalCDTrack < this.mInfoData.CurTrackNum) {
                this.mTrack.setText(String.format("%d", new Object[]{Integer.valueOf(this.mInfoData.CurTrackNum)}));
            } else {
                this.mTrack.setText(String.format("%d/%d", new Object[]{Integer.valueOf(this.mInfoData.CurTrackNum), Integer.valueOf(this.mInfoData.TotalCDTrack)}));
            }
            if (this.mInfoData.CurTrackTime <= 0 || this.mInfoData.CurTrackTime < this.mInfoData.PlayTime) {
                this.mTime.setText(String.format("%02d:%02d", new Object[]{Integer.valueOf(this.mInfoData.PlayTime / 60), Integer.valueOf(this.mInfoData.PlayTime % 60)}));
                return;
            }
            this.mTime.setText(String.format("%02d:%02d/%02d:%02d", new Object[]{Integer.valueOf(this.mInfoData.PlayTime / 60), Integer.valueOf(this.mInfoData.PlayTime % 60), Integer.valueOf(this.mInfoData.CurTrackTime / 60), Integer.valueOf(this.mInfoData.CurTrackTime % 60)}));
            return;
        }
        this.mTrack.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mTime.setText(TXZResourceManager.STYLE_DEFAULT);
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
        temp.SetPixelSize(30);
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
    public void CdCtrl(int cmd, int part1, int part2) {
        CanJni.ChryslerWcCdCmd(cmd, part1, part2);
    }

    public void QueryData() {
        CanJni.ChryslerWcQuery(1, 174);
        CanJni.ChryslerWcQuery(1, 165);
    }

    public static void DealStatusChanged() {
        CanDataInfo.ChrWcCdSta mCdSta = new CanDataInfo.ChrWcCdSta();
        CanJni.ChryslerWcGetCDSta(mCdSta);
        int curStatus = mCdSta.Sta;
        System.out.println("curStatus = " + curStatus);
        if (curStatus != mOldSta) {
            mOldSta = curStatus;
            switch (curStatus) {
                case 2:
                    if (CanIF.IsExdMode()) {
                        CanJni.ChryslerWcCdCmd(1, 0, 0);
                        return;
                    } else if (CanFunc.IsCamMode() == 0) {
                        CanFunc.showCanActivity(CanCarInfoSub1Activity.class, -1);
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    }
}
