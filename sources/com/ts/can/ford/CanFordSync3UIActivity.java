package com.ts.can.ford;

import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.KeyDef;

public class CanFordSync3UIActivity extends CanFordBaseActivity implements UserCallBack, View.OnClickListener {
    public static final int ITEM_APP = 3;
    public static final int ITEM_INFO = 0;
    public static final int ITEM_KEY_BOARD = 5;
    public static final int ITEM_MUSIC = 1;
    public static final int ITEM_PHONE = 4;
    public static final int ITEM_RT_BTN = 10;
    public static final int ITEM_SETTINGS = 2;
    public static final int ITEM_SPEAK = 16;
    public static final String TAG = "CanFordSync3UIActivity";
    public static final int[] mIcoBatArr = {R.drawable.can_sync_wc_battery_0, R.drawable.can_sync_wc_battery_1, R.drawable.can_sync_wc_battery_2, R.drawable.can_sync_wc_battery_3, R.drawable.can_sync_wc_battery_4, R.drawable.can_sync_wc_battery_5};
    public static final int[] mIcoSigArr = {R.drawable.can_sync_wc_signal_0, R.drawable.can_sync_wc_signal_1, R.drawable.can_sync_wc_signal_2, R.drawable.can_sync_wc_signal_3, R.drawable.can_sync_wc_signal_4, R.drawable.can_sync_wc_signal_5};
    public static boolean mIsJump = false;
    public static boolean mIsNeedFinish = false;
    public static boolean mIsSyncWin = false;
    protected ParamButton mBtnCall;
    protected ParamButton mBtnDn;
    protected ParamButton mBtnHang;
    protected ParamButton mBtnLeft;
    protected ParamButton mBtnLtApp;
    protected ParamButton mBtnLtInfo;
    protected ParamButton mBtnLtKeyboard;
    protected ParamButton mBtnLtMusic;
    protected ParamButton mBtnLtPhone;
    protected ParamButton mBtnLtSettings;
    protected ParamButton mBtnLtSpeak;
    protected ParamButton mBtnNext;
    protected ParamButton[] mBtnNum = new ParamButton[10];
    protected ParamButton mBtnNumJ;
    protected ParamButton mBtnNumX;
    protected ParamButton mBtnOK;
    protected ParamButton mBtnPP;
    protected ParamButton mBtnPrev;
    protected ParamButton mBtnRight;
    protected ParamButton mBtnRnd;
    protected ParamButton mBtnRpt;
    protected ParamButton mBtnUp;
    protected AutoFitTextureView mCameraView;
    protected RelativeLayoutManager mManager;
    protected CanDataInfo.SyncStatus mStatusData = new CanDataInfo.SyncStatus();
    protected CanDataInfo.FordVedioData mVideoData = new CanDataInfo.FordVedioData();
    protected boolean mfgShowKb;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (226 == CanJni.GetCanFsTp()) {
            setContentView(R.layout.activity_can_comm_relative);
            InitUI();
            return;
        }
        Log.d(TAG, "-----NOT FORD PROTOCAL-----");
        finish();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        mIsJump = false;
        mIsNeedFinish = false;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        onCreate((Bundle) null);
        MainTask.GetInstance().SetUserCallBack(this);
        Evc.GetInstance().evol_workmode_set(12);
        ResetData(false);
        mIsSyncWin = true;
        mIsNeedFinish = false;
        TsDisplay.GetInstance().SetDispParat(-1);
        MainSet.GetInstance().SetVideoChannel(0);
        BackcarService.getInstance().StartCamera(this.mCameraView, true);
        Log.d(TAG, "-----Is jump = " + mIsJump + "-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        BackcarService.getInstance().StopCamera();
        mIsSyncWin = false;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new RelativeLayoutManager((RelativeLayout) findViewById(R.id.can_comm_layout));
        initCommonScreen();
        ShowRtBtn();
        this.mCameraView = new AutoFitTextureView(this, (AttributeSet) null);
        this.mManager.AddView(this.mCameraView, 43, 16, 624, 408);
    }

    private void initCommonScreen() {
        this.mManager.AddImageEx(0, 432, 1024, 114, R.drawable.can_sync_left_bg);
        this.mManager.AddImageEx(690, 14, 296, 412, R.drawable.can_sync_right_box);
        this.mManager.AddImageEx(41, 14, 628, 412, R.drawable.can_sync_center_bg);
        this.mBtnLtApp = AddImgBtn(3, 23, 438, R.drawable.can_sync_app_up, R.drawable.can_sync_app_dn);
        this.mBtnLtInfo = AddImgBtn(0, 188, 438, R.drawable.can_sync_left_info_up, R.drawable.can_sync_left_info_dn);
        this.mBtnLtMusic = AddImgBtn(1, 355, 438, R.drawable.can_sync_left_medio_up, R.drawable.can_sync_left_medio_dn);
        this.mBtnLtSettings = AddImgBtn(2, CanCameraUI.BTN_GEELY_YJX6_MODE2, 438, R.drawable.can_sync_left_setting_up, R.drawable.can_sync_left_setting_dn);
        this.mBtnLtPhone = AddImgBtn(4, 686, 438, R.drawable.can_sync_left_phone_up, R.drawable.can_sync_left_phone_dn);
        this.mBtnLtSpeak = AddImgBtn(16, 850, 438, R.drawable.can_sync_yy_up, R.drawable.can_sync_yy_dn);
        this.mBtnUp = AddImgBtn(20, KeyDef.SKEY_SEEKDN_5, 22, R.drawable.can_sync_on_up, R.drawable.can_sync_on_dn);
        this.mBtnLeft = AddImgBtn(35, 695, 102, R.drawable.can_sync_left_up, R.drawable.can_sync_left_dn);
        this.mBtnOK = AddImgBtn(22, KeyDef.SKEY_SEEKDN_5, 102, R.drawable.can_sync_ok_up, R.drawable.can_sync_ok_dn);
        this.mBtnRight = AddImgBtn(36, 891, 102, R.drawable.can_sync_right_up, R.drawable.can_sync_right_dn);
        this.mBtnDn = AddImgBtn(21, KeyDef.SKEY_SEEKDN_5, 184, R.drawable.can_sync_dn_up, R.drawable.can_sync_dn_dn);
        this.mBtnPrev = AddImgBtn(18, 695, 266, R.drawable.can_sync_seekup_up, R.drawable.can_sync_seekup_dn);
        this.mBtnPP = AddImgBtn(44, KeyDef.SKEY_SEEKDN_5, 266, R.drawable.can_sync_play_up, R.drawable.can_sync_play_dn);
        this.mBtnNext = AddImgBtn(19, 891, 266, R.drawable.can_sync_seekdn_up, R.drawable.can_sync_seekdn_dn);
        this.mBtnRnd = AddImgBtn(17, 695, 347, R.drawable.can_sync_random_up, R.drawable.can_sync_random_dn);
        this.mBtnRpt = AddImgBtn(43, KeyDef.SKEY_SEEKDN_5, 347, R.drawable.can_sync_loop_up, R.drawable.can_sync_loop_dn);
        this.mBtnNum[1] = AddImgBtn(24, 695, 22, R.drawable.can_sync_num01_up, R.drawable.can_sync_num01_dn);
        this.mBtnNum[2] = AddImgBtn(25, KeyDef.SKEY_SEEKDN_5, 22, R.drawable.can_sync_num02_up, R.drawable.can_sync_num02_dn);
        this.mBtnNum[3] = AddImgBtn(26, 891, 22, R.drawable.can_sync_num03_up, R.drawable.can_sync_num03_dn);
        this.mBtnNum[4] = AddImgBtn(27, 695, 102, R.drawable.can_sync_num04_up, R.drawable.can_sync_num04_dn);
        this.mBtnNum[5] = AddImgBtn(28, KeyDef.SKEY_SEEKDN_5, 102, R.drawable.can_sync_num05_up, R.drawable.can_sync_num05_dn);
        this.mBtnNum[6] = AddImgBtn(29, 891, 102, R.drawable.can_sync_num06_up, R.drawable.can_sync_num06_dn);
        this.mBtnNum[7] = AddImgBtn(30, 695, 184, R.drawable.can_sync_num07_up, R.drawable.can_sync_num07_dn);
        this.mBtnNum[8] = AddImgBtn(31, KeyDef.SKEY_SEEKDN_5, 184, R.drawable.can_sync_num08_up, R.drawable.can_sync_num08_dn);
        this.mBtnNum[9] = AddImgBtn(32, 891, 184, R.drawable.can_sync_num09_up, R.drawable.can_sync_num09_dn);
        this.mBtnNumX = AddImgBtn(33, 695, 266, R.drawable.can_sync_aste_up, R.drawable.can_sync_aste_dn);
        this.mBtnNum[0] = AddImgBtn(23, KeyDef.SKEY_SEEKDN_5, 266, R.drawable.can_sync_num00_up, R.drawable.can_sync_num00_dn);
        this.mBtnNumJ = AddImgBtn(34, 891, 266, R.drawable.can_sync_well_up, R.drawable.can_sync_well_dn);
        this.mBtnCall = AddImgBtn(15, 695, 347, R.drawable.can_sync_dialout_up, R.drawable.can_sync_dialout_dn);
        this.mBtnHang = AddImgBtn(14, KeyDef.SKEY_SEEKDN_5, 347, R.drawable.can_sync_hangup_up, R.drawable.can_sync_hangup_dn);
        this.mBtnLtKeyboard = AddImgBtn(5, 891, 347, R.drawable.can_sync_tab_up, R.drawable.can_sync_tab_dn);
    }

    /* access modifiers changed from: protected */
    public void ShowRtBtn() {
        boolean showMedia = !this.mfgShowKb;
        this.mBtnUp.Show(showMedia);
        this.mBtnDn.Show(showMedia);
        this.mBtnLeft.Show(showMedia);
        this.mBtnRight.Show(showMedia);
        this.mBtnOK.Show(showMedia);
        this.mBtnPrev.Show(showMedia);
        this.mBtnPP.Show(showMedia);
        this.mBtnNext.Show(showMedia);
        this.mBtnRnd.Show(showMedia);
        this.mBtnRpt.Show(showMedia);
        for (int i = 0; i < 10; i++) {
            this.mBtnNum[i].Show(this.mfgShowKb);
        }
        this.mBtnNumX.Show(this.mfgShowKb);
        this.mBtnNumJ.Show(this.mfgShowKb);
        this.mBtnCall.Show(this.mfgShowKb);
        this.mBtnHang.Show(this.mfgShowKb);
    }

    private void UpdateStatus(boolean check) {
    }

    private void updateVideoSize(boolean isFullScreen) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) this.mCameraView.getLayoutParams();
        if (isFullScreen) {
            lp.width = -1;
            lp.height = -1;
            lp.leftMargin = 0;
            lp.topMargin = 0;
        } else {
            lp.width = 624;
            lp.height = 396;
            lp.leftMargin = 43;
            lp.topMargin = 16;
        }
        this.mCameraView.setLayoutParams(lp);
    }

    private void ResetData(boolean check) {
        UpdateStatus(check);
    }

    protected static void MediaClick() {
        CanJni.FordSyncCtrl(129);
    }

    public void onClick(View v) {
        boolean z;
        int id = ((Integer) v.getTag()).intValue();
        Log.d(TAG, "onClick item  = " + id);
        switch (id) {
            case 0:
                CanJni.FordSyncCtrl(6);
                return;
            case 1:
                MediaClick();
                return;
            case 2:
                CanJni.FordSyncCtrl(2);
                return;
            case 3:
                CanJni.FordSyncCtrl(32);
                return;
            case 4:
                CanJni.FordSyncCtrl(3);
                return;
            case 5:
                if (this.mfgShowKb) {
                    z = false;
                } else {
                    z = true;
                }
                this.mfgShowKb = z;
                ShowRtBtn();
                return;
            case 16:
                CanJni.FordSyncCtrl(1);
                return;
            default:
                if (id >= 10) {
                    CanJni.FordSyncCtrl(id - 10);
                    return;
                }
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
        if (mIsNeedFinish) {
            mIsNeedFinish = false;
            mIsJump = false;
            finish();
        }
    }

    public static void ShowSync() {
        if (!mIsSyncWin) {
            mIsJump = true;
            CanFunc.showCanActivity(CanFordSync3UIActivity.class);
        }
    }

    public static void ShowSyncClick() {
        if (Iop.GetWorkMode() != 12) {
            MediaClick();
        }
        if (!mIsSyncWin) {
            mIsJump = false;
            CanFunc.showCanActivity(CanFordSync3UIActivity.class);
        }
    }

    public static void DealCallEnd() {
        if (mIsJump && mIsSyncWin) {
            mIsNeedFinish = true;
        }
    }

    public static void DealVoiceEnd() {
        if (mIsJump && mIsSyncWin) {
            mIsNeedFinish = true;
        }
    }

    /* access modifiers changed from: protected */
    public ParamButton AddImgBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setStateUpDn(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddImgBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setStateUpDn(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public CustomImgView AddIco(int x, int up, int sel) {
        CustomImgView ico = this.mManager.AddImage(x + 416, 20, 33, 33);
        if (-1 != sel) {
            ico.setStateDrawable(up, sel);
        }
        return ico;
    }
}
