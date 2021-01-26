package com.ts.can.mzd.cx4;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import com.android.SdkConstants;
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
import com.ts.main.common.MainSet;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;
import java.util.Arrays;

public class CanMzdCx4CDActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener {
    public static final int ITEM_NEXT = 4;
    public static final int ITEM_PAUSE = 3;
    public static final int ITEM_PLAY = 2;
    public static final int ITEM_PREV = 1;
    public static final int ITEM_RDM = 6;
    public static final int ITEM_RPT = 5;
    protected static int mOldSta = -1;
    protected CustomTextView mAlbum;
    protected CustomTextView mArtist;
    protected ParamButton mBtnNext;
    protected ParamButton mBtnPause;
    protected ParamButton mBtnPlay;
    protected ParamButton mBtnPrev;
    protected ParamButton mBtnRdm;
    protected ParamButton mBtnRpt;
    protected CanDataInfo.Cx4_Cd_Id3 mCdId = new CanDataInfo.Cx4_Cd_Id3();
    protected CanDataInfo.Cx4_Dev_Info mDevInfo = new CanDataInfo.Cx4_Dev_Info();
    protected RelativeLayoutManager mManager;
    protected CanDataInfo.Cx4_Cd_PlayInfo mPlayInfo = new CanDataInfo.Cx4_Cd_PlayInfo();
    protected CustomTextView mSong;
    protected CustomTextView mSta;
    protected int mStartCount = 0;
    protected String[] mStrDiscSta;
    protected String[] mStrSta;
    protected CustomTextView mTime;
    protected CustomTextView mTrack;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_relative);
        if (MainSet.GetScreenType() == 5) {
            InitUI_1280x480();
        } else {
            InitUI();
        }
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        if (mDisplayMetrics.heightPixels == 400) {
            this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
            lp.width = 1024;
            lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
            lp.gravity = 17;
            this.mManager.GetLayout().setLayoutParams(lp);
            InitUI();
            this.mManager.GetLayout().setScaleY(0.66f);
        }
        this.mStrSta = getResources().getStringArray(R.array.can_mzd_car_play_sta);
        this.mStrDiscSta = getResources().getStringArray(R.array.can_mzd_car_disc_sta);
    }

    private void InitUI() {
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_jeep_ycsb_bg);
        this.mBtnPrev = AddBtn(1, 257, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_prv_up, R.drawable.can_jeep_ycsb_prv_dn);
        this.mBtnPlay = AddBtn(2, 391, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_play_up, R.drawable.can_jeep_ycsb_play_dn);
        this.mBtnPause = AddBtn(3, CanCameraUI.BTN_GEELY_YJX6_GJ, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_pause_up, R.drawable.can_jeep_ycsb_pause_dn);
        this.mBtnNext = AddBtn(4, 660, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_next_up, R.drawable.can_jeep_ycsb_next_dn);
        this.mBtnRpt = AddBtn(5, 46, 25, R.drawable.can_jeep_ycsb_cycle_up, R.drawable.can_jeep_ycsb_cycle_dn);
        this.mBtnRdm = AddBtn(6, 180, 25, R.drawable.can_jeep_ycsb_random_up, R.drawable.can_jeep_ycsb_random_dn);
        this.mManager.AddImage(44, 127, R.drawable.can_jeep_ycsb_sjx);
        this.mManager.AddImage(44, 184, R.drawable.can_jeep_ycsb_music);
        this.mManager.AddImage(44, 299, R.drawable.can_jeep_ycsb_artist);
        this.mManager.AddImage(44, Can.CAN_MZD_LUOMU, R.drawable.can_jeep_ycsb_disc);
        this.mSta = AddLeftText(84, 119, 300, 42);
        this.mSong = AddLeftText(84, 176, 300, 42);
        this.mAlbum = AddLeftText(84, Can.CAN_LIEBAO_WC, 300, 42);
        this.mArtist = AddLeftText(84, 291, 300, 42);
        this.mTrack = AddLeftText(430, 23, Can.CAN_NISSAN_XFY, 55);
        this.mTrack.SetPixelSize(40);
        this.mTime = AddCenterText(362, KeyDef.RKEY_res4, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 35);
    }

    private void InitUI_1280x480() {
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_hpjeep_ycsb_bg);
        this.mBtnPrev = AddBtn(1, 384, KeyDef.RKEY_ANGLEDN, 90, 95, R.drawable.can_jeep_ycsb_prv_up, R.drawable.can_jeep_ycsb_prv_dn);
        this.mBtnPlay = AddBtn(2, 534, KeyDef.RKEY_ANGLEDN, 90, 95, R.drawable.can_jeep_ycsb_play_up, R.drawable.can_jeep_ycsb_play_dn);
        this.mBtnPause = AddBtn(3, 685, KeyDef.RKEY_ANGLEDN, 90, 95, R.drawable.can_jeep_ycsb_pause_up, R.drawable.can_jeep_ycsb_pause_dn);
        this.mBtnNext = AddBtn(4, KeyDef.SKEY_HOME_2, KeyDef.RKEY_ANGLEDN, 90, 95, R.drawable.can_jeep_ycsb_next_up, R.drawable.can_jeep_ycsb_next_dn);
        this.mBtnRpt = AddBtn(5, 18, 10, 103, 56, R.drawable.can_jeep_ycsb_cycle_up, R.drawable.can_jeep_ycsb_cycle_dn);
        this.mBtnRdm = AddBtn(6, 126, 10, 103, 56, R.drawable.can_jeep_ycsb_random_up, R.drawable.can_jeep_ycsb_random_dn);
        this.mManager.AddImage(18, 80, R.drawable.can_jeep_ycsb_sjx);
        this.mManager.AddImage(18, 137, R.drawable.can_jeep_ycsb_music);
        this.mManager.AddImage(18, 195, R.drawable.can_jeep_ycsb_artist);
        this.mManager.AddImage(18, Can.CAN_CHRYSLER_TXB, R.drawable.can_jeep_ycsb_disc);
        this.mSta = AddLeftText(58, 72, 300, 42);
        this.mSong = AddLeftText(58, 129, 300, 42);
        this.mAlbum = AddLeftText(58, 187, 300, 42);
        this.mArtist = AddLeftText(58, Can.CAN_SE_DX7_RZC, 300, 42);
        this.mTrack = AddLeftText(558, 17, Can.CAN_NISSAN_XFY, 55);
        this.mTrack.SetPixelSize(40);
        this.mTime = AddCenterText(440, 268, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 35);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Evc.GetInstance().evol_workmode_set(12);
        Query();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.MzdCx4GetDevInfo(this.mDevInfo);
        CanJni.MzdCx4GetCdPlayInfo(this.mPlayInfo);
        CanJni.MzdCx4GetCdId3(this.mCdId);
        if (i2b(this.mDevInfo.UpdateOnce) && (!check || i2b(this.mDevInfo.Update))) {
            this.mDevInfo.Update = 0;
            if (!i2b(this.mDevInfo.fgCd)) {
                this.mSta.setText(this.mStrDiscSta[2]);
                resetText();
            } else if (this.mDevInfo.CdState != 0) {
                this.mSta.setText(this.mStrDiscSta[0]);
                resetText();
            } else {
                this.mSta.setText(this.mStrDiscSta[1]);
            }
        }
        if (i2b(this.mPlayInfo.UpdateOnce) && (!check || i2b(this.mPlayInfo.Update))) {
            this.mPlayInfo.Update = 0;
            if (this.mDevInfo.CdState == 0) {
                if (this.mPlayInfo.WorkMode >= this.mStrSta.length) {
                    this.mSta.setText(" ");
                } else {
                    this.mSta.setText(this.mStrSta[this.mPlayInfo.WorkMode]);
                }
                if (this.mPlayInfo.WorkMode == 1 || this.mPlayInfo.WorkMode == 2) {
                    String trackTime = formatTime(this.mPlayInfo.TrackTime);
                    String totalTime = formatTime(this.mPlayInfo.TotalTime);
                    this.mTrack.setText(String.format("TRACK %d", new Object[]{Integer.valueOf(this.mPlayInfo.CurTrack)}));
                    this.mTime.setText(String.valueOf(totalTime) + "/" + trackTime);
                } else {
                    resetText();
                }
                this.mBtnRpt.SetSel(this.mPlayInfo.fgRpt);
                this.mBtnRdm.SetSel(this.mPlayInfo.fgRdm);
            }
        }
        if (!i2b(this.mCdId.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCdId.Update)) {
            this.mCdId.Update = 0;
            String encode = TXZResourceManager.STYLE_DEFAULT;
            switch (this.mCdId.EncoderMode) {
                case 0:
                    encode = "GBK";
                    break;
                case 1:
                    encode = "UTF-16";
                    break;
                case 2:
                    encode = "UTF-16";
                    break;
                case 3:
                    encode = SdkConstants.INI_CHARSET;
                    break;
            }
            if (this.mDevInfo.CdState == 0) {
                this.mSong.setText(byte2String(this.mCdId.szTitle, encode));
                this.mAlbum.setText(byte2String(this.mCdId.szDisc, encode));
                this.mArtist.setText(byte2String(this.mCdId.szArtist, encode));
            }
        }
    }

    private void resetText() {
        this.mSong.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mAlbum.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mArtist.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mTrack.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mTime.setText(TXZResourceManager.STYLE_DEFAULT);
    }

    private String formatTime(int time) {
        int hour = time / 3600;
        return String.format("%02d : %02d : %02d", new Object[]{Integer.valueOf(hour), Integer.valueOf((time - (hour * 3600)) / 60), Integer.valueOf(time % 60)});
    }

    public String byte2String(byte[] b, String encode) {
        int Datalen = b.length;
        int i = 0;
        while (true) {
            if (i >= b.length) {
                break;
            } else if (b[i] == 0) {
                Datalen = i;
                break;
            } else {
                i++;
            }
        }
        if (Datalen == 0) {
            return TXZResourceManager.STYLE_DEFAULT;
        }
        try {
            if (Datalen == b.length) {
                return new String(b, encode);
            }
            return new String(Arrays.copyOf(b, Datalen), encode);
        } catch (Exception e) {
            e.printStackTrace();
            return TXZResourceManager.STYLE_DEFAULT;
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
        CanJni.MzdCx4Query(4, 0);
        Sleep(1);
        CanJni.MzdCx4Query(5, 0);
        Sleep(1);
        CanJni.MzdCx4Query(6, 0);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.MzdCx4CdCmd(5);
                return;
            case 2:
                CanJni.MzdCx4CdCmd(0);
                return;
            case 3:
                CanJni.MzdCx4CdCmd(1);
                return;
            case 4:
                CanJni.MzdCx4CdCmd(4);
                return;
            case 5:
                CanJni.MzdCx4CdCmd(204);
                return;
            case 6:
                CanJni.MzdCx4CdCmd(187);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddLeftText(int x, int y, int w, int h) {
        CustomTextView item = this.mManager.AddCusText(x, y, w, h);
        item.SetPixelSize(30);
        item.setGravity(19);
        return item;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddCenterText(int x, int y, int w, int h) {
        CustomTextView item = AddLeftText(x, y, w, h);
        item.setGravity(17);
        return item;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    public static void DealStatusChanged() {
        int curStatus = CanJni.MzdCx4GetCdSta();
        System.out.println("curStatus = " + curStatus);
        if (curStatus != mOldSta) {
            mOldSta = curStatus;
            switch (curStatus) {
                case 3:
                    CanJni.MzdCx4CdCmd(15);
                    return;
                case 4:
                    if (CanIF.IsExdMode()) {
                        CanJni.MzdCx4CdCmd(14);
                        return;
                    } else if (CanFunc.IsCamMode() == 0) {
                        CanFunc.showCanActivity(CanMzdCx4CDActivity.class);
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
