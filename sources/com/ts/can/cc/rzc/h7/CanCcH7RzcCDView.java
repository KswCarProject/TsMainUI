package com.ts.can.cc.rzc.h7;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCarDeviceActivity;
import com.ts.can.CanFunc;
import com.ts.can.CanIF;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanCcH7RzcCDView extends CanRelativeCarInfoView {
    public static final int ITEM_FF = 8;
    public static final int ITEM_FR = 7;
    public static final int ITEM_NEXT = 4;
    public static final int ITEM_PAUSE = 3;
    public static final int ITEM_PLAY = 2;
    public static final int ITEM_PREV = 1;
    public static final int ITEM_RDM = 6;
    public static final int ITEM_RPT = 5;
    public static boolean mfgShow = false;
    protected CustomTextView mArtist;
    protected CanDataInfo.CcRzcCdText mArtistData = new CanDataInfo.CcRzcCdText();
    protected ParamButton mBtnFf;
    protected ParamButton mBtnFr;
    protected ParamButton mBtnNext;
    protected ParamButton mBtnPause;
    protected ParamButton mBtnPlay;
    protected ParamButton mBtnPrev;
    protected ParamButton mBtnRdm;
    protected ParamButton mBtnRpt;
    private CanDataInfo.CcRzcCdInfo mCdInfo;
    protected CustomImgView mImgSta;
    protected RelativeLayoutManager mManager;
    protected CanDataInfo.CcRzcCdText mNameData = new CanDataInfo.CcRzcCdText();
    protected CustomTextView mSong;
    protected CustomTextView mSrc;
    protected CustomTextView mSta;
    protected String[] mStrSrc = {"OFF", "CD Disc", "AUX", "Misc"};
    protected String[] mStrSta;
    protected CustomTextView mTime;
    protected CustomTextView mTrack;

    public CanCcH7RzcCDView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View view, MotionEvent event) {
        int action = event.getAction();
        int intValue = ((Integer) view.getTag()).intValue();
        if (action != 0) {
        }
        return false;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.CcHfH7CdCmd(2, 0, 0);
                return;
            case 2:
                CanJni.CcHfH7CdCmd(19, 0, 0);
                return;
            case 3:
                CanJni.CcHfH7CdCmd(20, 0, 0);
                return;
            case 4:
                CanJni.CcHfH7CdCmd(1, 0, 0);
                return;
            case 5:
                CanJni.CcHfH7CdCmd(17, 0, 0);
                return;
            case 6:
                CanJni.CcHfH7CdCmd(8, 0, 0);
                return;
            case 7:
                CanJni.CcHfH7CdCmd(3, 0, 0);
                return;
            case 8:
                CanJni.CcHfH7CdCmd(4, 0, 0);
                return;
            default:
                return;
        }
    }

    public void doOnResume() {
        super.doOnResume();
        mfgShow = true;
        Evc.GetInstance().evol_workmode_set(12);
    }

    public void doOnPause() {
        mfgShow = false;
        super.doOnPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = getRelativeManager();
        this.mCdInfo = new CanDataInfo.CcRzcCdInfo();
        this.mStrSta = getStringArray(R.array.can_cc_h7_cd_sta);
        BaseUI();
    }

    private void BaseUI() {
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_jeep_ycsb_bg);
        this.mBtnRpt = AddBtn(5, 46, 25, R.drawable.can_jeep_ycsb_cycle_up, R.drawable.can_jeep_ycsb_cycle_dn);
        this.mBtnRdm = AddBtn(6, 180, 25, R.drawable.can_jeep_ycsb_random_up, R.drawable.can_jeep_ycsb_random_dn);
        this.mManager.AddImage(44, 184, R.drawable.can_jeep_ycsb_music);
        this.mManager.AddImage(44, Can.CAN_MZD_LUOMU, R.drawable.can_jeep_ycsb_disc);
        this.mManager.AddImage(44, 299, R.drawable.can_jeep_ycsb_artist);
        this.mSong = AddLeftText(84, 181, 600, 36);
        this.mArtist = AddLeftText(84, 296, 600, 36);
        this.mImgSta = this.mManager.AddImage(44, 127, R.drawable.can_jeep_ycsb_sjx);
        this.mSta = AddLeftText(84, 119, 300, 42);
        this.mTrack = AddCenterText(KeyDef.RKEY_res3, 23, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 55);
        this.mTrack.SetPixelSize(40);
        this.mTime = AddCenterText(KeyDef.RKEY_res3, KeyDef.RKEY_res4, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 35);
        this.mSrc = AddCenterText(KeyDef.RKEY_res3, 270, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 55);
        this.mSrc.SetPixelSize(50);
        this.mSrc.setText(" ");
        this.mBtnFf = AddBtn(7, 137, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_down_up, R.drawable.can_jeep_ycsb_down_dn);
        this.mBtnPrev = AddBtn(1, 271, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.original_car_seekup_up, R.drawable.original_car_seekup_dn);
        this.mBtnPlay = AddBtn(2, 406, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.original_car_play_up, R.drawable.original_car_play_dn);
        this.mBtnPause = AddBtn(3, 540, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.original_car_pause_up, R.drawable.original_car_pause_dn);
        this.mBtnNext = AddBtn(4, 674, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.original_car_seekdn_up, R.drawable.original_car_seekdn_dn);
        this.mBtnFr = AddBtn(7, KeyDef.SKEY_MUTE_5, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_speed_up, R.drawable.can_jeep_ycsb_speed_dn);
    }

    public void ResetData(boolean check) {
        int i;
        CanJni.CcHfH9GetCdInfo(this.mCdInfo);
        if (i2b(this.mCdInfo.UpdateOnce) && (!check || i2b(this.mCdInfo.Update))) {
            this.mCdInfo.Update = 0;
            if (this.mCdInfo.Src < this.mStrSrc.length) {
                this.mSrc.setText(this.mStrSrc[this.mCdInfo.Src]);
            }
            this.mBtnRdm.SetSel(this.mCdInfo.PlayMode == 1 ? 1 : 0);
            ParamButton paramButton = this.mBtnRpt;
            if (this.mCdInfo.PlayMode == 2) {
                i = 1;
            } else {
                i = 0;
            }
            paramButton.SetSel(i);
            if (this.mCdInfo.WorkMode >= this.mStrSta.length) {
                this.mSta.setText(" ");
            } else {
                this.mSta.setText(this.mStrSta[this.mCdInfo.WorkMode]);
            }
            this.mTrack.setText(String.format("TRACK %d/%d", new Object[]{Integer.valueOf(this.mCdInfo.TotalNum), Integer.valueOf(this.mCdInfo.CurNum)}));
            this.mTime.setText(String.format("%02d:%02d/%02d:%02d", new Object[]{Integer.valueOf(this.mCdInfo.TotalTime / 60), Integer.valueOf(this.mCdInfo.TotalTime % 60), Integer.valueOf(this.mCdInfo.CurTime / 60), Integer.valueOf(this.mCdInfo.CurTime % 60)}));
        }
        CanJni.CcHfH9GetCdId3Info(0, this.mNameData);
        if (i2b(this.mNameData.UpdateOnce) && (!check || i2b(this.mNameData.Update))) {
            this.mNameData.Update = 0;
            this.mSong.setText(CanIF.Utf82String(this.mNameData.szText));
        }
        CanJni.CcHfH9GetCdId3Info(1, this.mArtistData);
        if (!i2b(this.mArtistData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mArtistData.Update)) {
            this.mArtistData.Update = 0;
            this.mArtist.setText(CanIF.Utf82String(this.mArtistData.szText));
        }
    }

    public void QueryData() {
        CanJni.CCCarQueryRzc(56, 0);
        Sleep(10);
        CanJni.CCCarQueryRzc(57, 0);
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
        btn.setOnTouchListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    public static void showWin() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanCarDeviceActivity.class);
        }
    }

    public static boolean IsWin() {
        return mfgShow;
    }
}
