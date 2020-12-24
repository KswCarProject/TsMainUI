package com.ts.can.cadillac.withcd;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.bt.BtExe;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.can.CanIF;
import com.ts.main.common.MainSet;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.KeyDef;
import com.yyw.ts70xhw.Mcu;

public class CanCadillacWithCDExdActivity extends CanBaseActivity implements UserCallBack, View.OnTouchListener, View.OnClickListener {
    private static final int BTN_BACK = 11;
    private static final int BTN_FAV = 0;
    private static final int BTN_HOME = 1;
    private static final int BTN_MEDIA = 9;
    private static final int BTN_MENU = 19;
    private static final int BTN_NEXT = 5;
    private static final int BTN_PHONE = 10;
    private static final int BTN_POWER = 18;
    private static final int BTN_PRE = 4;
    private static final int BTN_RADIO = 8;
    private static final int BTN_RADIO_NUM1 = 12;
    private static final int BTN_RADIO_NUM2 = 13;
    private static final int BTN_RADIO_NUM3 = 14;
    private static final int BTN_RADIO_NUM4 = 15;
    private static final int BTN_RADIO_NUM5 = 16;
    private static final int BTN_RADIO_NUM6 = 17;
    private static final int BTN_SEL_DEC = 7;
    private static final int BTN_SEL_INC = 6;
    private static final int BTN_VOL_DEC = 2;
    private static final int BTN_VOL_INC = 3;
    public static final String TAG = "CanCadillacWithCDExdActivity";
    public static int mOldBtSta = 0;
    public static boolean mfgAutoEnt = false;
    public static boolean mfgFinish = false;
    private static boolean mfgSendFunc = false;
    public static boolean mfgShow = false;
    private ParamButton mBtnBack;
    private ParamButton mBtnFav;
    private ParamButton mBtnHome;
    private ParamButton mBtnMedia;
    private ParamButton mBtnMenu;
    private ParamButton mBtnNext;
    private ParamButton mBtnPhone;
    private ParamButton mBtnPower;
    private ParamButton mBtnPre;
    private ParamButton mBtnRadio;
    private ParamButton[] mBtnRadioNum = new ParamButton[6];
    private ParamButton mBtnSelDec;
    private ParamButton mBtnSelInc;
    private ParamButton mBtnVolDec;
    private ParamButton mBtnVolInc;
    private AutoFitTextureView mCameraView;
    private RelativeLayoutManager mManager;
    private WindowManager wManager;
    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        if (CanJni.GetSubType() == 4 || CanJni.GetSubType() == 5 || CanJni.GetSubType() == 6 || CanJni.GetSubType() == 8) {
            InitUI_1024x600();
        } else {
            InitUI_1024x768();
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI_1024x768() {
        setContentView(R.layout.activity_can_cadillax_withcd_base);
        this.mManager = new RelativeLayoutManager(this, R.id.can_cadillax_withcd_layout);
        if (CanJni.GetSubType() == 0 || CanJni.GetSubType() == 9) {
            this.mManager.GetLayout().setBackgroundResource(R.drawable.cad_ac_bg01);
        } else {
            this.mManager.GetLayout().setOnTouchListener(this);
            this.mManager.GetLayout().setClickable(true);
        }
        this.mBtnFav = addButton(1, 133, R.drawable.cad_ac_fav_up, R.drawable.cad_ac_fav_dn, 0);
        this.mBtnHome = addButton(673, 133, R.drawable.cad_ac_home_up, R.drawable.cad_ac_home_dn, 1);
        this.mBtnVolDec = addButton(1, 275, R.drawable.cad_ac_up_up, R.drawable.cad_ac_up_dn, 2);
        this.mBtnVolInc = addButton(190, 275, R.drawable.cad_ac_dn_up, R.drawable.cad_ac_dn_dn, 3);
        this.mBtnPre = addButton(Can.CAN_FORD_ESCORT_LY, 275, R.drawable.cad_ac_prve_up, R.drawable.cad_ac_prve_dn, 4);
        this.mBtnNext = addButton(443, 275, R.drawable.cad_ac_next_up, R.drawable.cad_ac_next_dn, 5);
        this.mBtnSelInc = addButton(705, 275, R.drawable.cad_ac_dn_up, R.drawable.cad_ac_dn_dn, 6);
        this.mBtnSelDec = addButton(516, 275, R.drawable.cad_ac_up_up, R.drawable.cad_ac_up_dn, 7);
        this.mBtnRadio = addButton(163, 370, R.drawable.cad_ac_radio_up, R.drawable.cad_ac_radio_dn, 8);
        this.mBtnMedia = addButton(274, 370, R.drawable.cad_ac_media_up, R.drawable.cad_ac_media_dn, 9);
        this.mBtnPhone = addButton(385, 370, R.drawable.cad_ac_phone_up, R.drawable.cad_ac_phone_dn, 10);
        this.mBtnBack = addButton(496, 370, R.drawable.cad_ac_back_up, R.drawable.cad_ac_back_dn, 11);
        this.mBtnPower = addButton(76, 268, R.drawable.cad_ac_shut_up, R.drawable.cad_ac_shut_dn, 18);
        this.mBtnMenu = addButton(CanCameraUI.BTN_VW_WC_MODE2, 268, R.drawable.cad_ac_menu_up, R.drawable.cad_ac_menu_dn, 19);
        for (int i = 0; i < 6; i++) {
            this.mBtnRadioNum[i] = addButton((i * 80) + 144, 176, 80, 90, 0, 0, i + 12);
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d(TAG, "onWindowFocusChanged =" + hasFocus);
        if (!hasFocus) {
            return;
        }
        if (CanJni.GetSubType() == 5 || CanJni.GetSubType() == 6) {
            getWindow().getDecorView().setSystemUiVisibility(4612);
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI_1024x600() {
        setContentView(R.layout.activity_can_cadillax_ats_base);
        this.mManager = new RelativeLayoutManager(this, R.id.can_cadillax_ats_layout);
        if (CanJni.GetSubType() == 4 || CanJni.GetSubType() == 8) {
            this.mManager.GetLayout().setBackgroundResource(R.drawable.xjr_kdlk_bg);
        } else {
            this.mManager.GetLayout().setOnTouchListener(this);
            this.mManager.GetLayout().setClickable(true);
        }
        this.mBtnFav = addButton(16, 8, R.drawable.xjr_kdlk_fav_up, R.drawable.xjr_kdlk_fav_dn, 0);
        this.mBtnVolDec = addButton(16, 280, R.drawable.xjr_kdlk_down_up, R.drawable.xjr_kdlk_down_dn, 2);
        this.mBtnVolInc = addButton(16, 129, R.drawable.xjr_kdlk_up_up, R.drawable.xjr_kdlk_up_dn, 3);
        this.mBtnPower = addButton(16, Can.CAN_LEXUS_IZ, R.drawable.xjr_kdlk_close_up, R.drawable.xjr_kdlk_close_dn, 18);
        this.mBtnPre = addButton(16, 384, R.drawable.xjr_kdlk_prev_up, R.drawable.xjr_kdlk_prev_dn, 4);
        this.mBtnRadio = addButton(148, 460, R.drawable.xjr_kdlk_radio_up, R.drawable.xjr_kdlk_radio_dn, 8);
        this.mBtnMedia = addButton(356, 460, R.drawable.xjr_kdlk_media_up, R.drawable.xjr_kdlk_media_dn, 9);
        this.mBtnPhone = addButton(CanCameraUI.BTN_TRUMPCHI_GS7_MODE4, 460, R.drawable.xjr_kdlk_phone_up, R.drawable.xjr_kdlk_phone_dn, 10);
        this.mBtnBack = addButton(767, 460, R.drawable.xjr_kdlk_back_up, R.drawable.xjr_kdlk_back_dn, 11);
        this.mBtnHome = addButton(913, 8, R.drawable.xjr_kdlk_home_up, R.drawable.xjr_kdlk_home_dn, 1);
        this.mBtnMenu = addButton(913, Can.CAN_LEXUS_IZ, R.drawable.xjr_kdlk_menu_up, R.drawable.xjr_kdlk_menu_dn, 19);
        this.mBtnNext = addButton(913, 384, R.drawable.xjr_kdlk_next_up, R.drawable.xjr_kdlk_next_dn, 5);
        this.mBtnSelInc = addButton(913, 129, R.drawable.xjr_kdlk_up_up, R.drawable.xjr_kdlk_up_dn, 6);
        this.mBtnSelDec = addButton(913, 280, R.drawable.xjr_kdlk_down_up, R.drawable.xjr_kdlk_down_dn, 7);
        for (int i = 0; i < 5; i++) {
            this.mBtnRadioNum[i] = addButton((i * Can.CAN_JAC_REFINE_OD) + 144, KeyDef.RKEY_EJECT_L, 149, 85, 0, 0, i + 12);
        }
    }

    public ParamButton addButton(int x, int y, int normal, int selected, int id) {
        ParamButton button = this.mManager.AddButton(x, y);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        if (!(CanJni.GetSubType() == 0 || CanJni.GetSubType() == 4 || CanJni.GetSubType() == 8 || CanJni.GetSubType() == 9)) {
            button.Show(false);
        }
        return button;
    }

    public ParamButton addButton(int x, int y, int w, int h, int normal, int selected, int id) {
        ParamButton button = this.mManager.AddButton(x, y, w, h);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        if (!(CanJni.GetSubType() == 0 || CanJni.GetSubType() == 4 || CanJni.GetSubType() == 8 || CanJni.GetSubType() == 9)) {
            button.Show(false);
        }
        return button;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        if (!mfgAutoEnt) {
            Evc.GetInstance().evol_workmode_set(12);
            Log.d(TAG, "WORKMODE_EXD");
        }
        mfgShow = true;
        mfgFinish = false;
        Log.d(TAG, "onResume");
        TsDisplay.GetInstance().SetDispParat(-1);
        if (CanJni.GetSubType() == 4 || CanJni.GetSubType() == 5 || CanJni.GetSubType() == 6 || CanJni.GetSubType() == 8) {
            MainSet.GetInstance().SetVideoChannel(2);
        } else if (FtSet.Getlgb3() == 0) {
            MainSet.GetInstance().SetVideoChannel(2);
        } else {
            MainSet.GetInstance().SetVideoChannel(0);
        }
        this.mCameraView = (AutoFitTextureView) findViewById(R.id.DevtextureView);
        BackcarService.getInstance().StartCamera(this.mCameraView, false);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-1, -1);
        if (CanJni.GetSubType() == 0 || CanJni.GetSubType() == 9) {
            lp.width = 480;
            lp.height = Can.CAN_LIEBAO_WC;
            lp.leftMargin = 144;
            lp.topMargin = 6;
        } else if (CanJni.GetSubType() == 4 || CanJni.GetSubType() == 8) {
            lp.width = 748;
            lp.height = 365;
            lp.leftMargin = 138;
            lp.topMargin = 12;
        } else if (CanJni.GetSubType() == 5 || CanJni.GetSubType() == 6) {
            getWindow().getDecorView().setSystemUiVisibility(4612);
            lp.width = -1;
            lp.height = -1;
        } else {
            lp.width = -1;
            lp.height = -1;
        }
        this.mCameraView.setLayoutParams(lp);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        mfgShow = false;
        mfgAutoEnt = false;
        Log.d(TAG, "onPause");
        BackcarService.getInstance().StopCamera();
    }

    public static void showCadillacAtsWin() {
        if (!mfgShow) {
            mfgAutoEnt = true;
            CanFunc.showCanActivity(CanCadillacWithCDExdActivity.class);
        }
    }

    public static void entCadillacAtsMode() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanCadillacWithCDExdActivity.class);
        }
    }

    public static void finishCadillacAtsWin() {
        if (mfgShow && mfgAutoEnt) {
            mfgFinish = true;
        }
    }

    public static void ForefinishCadillacAtsWin() {
        if (mfgShow) {
            mfgFinish = true;
        }
    }

    public static boolean IsCadillacAtsWin() {
        return mfgShow;
    }

    private void ResetData(boolean check) {
    }

    public void UserAll() {
        ResetData(true);
        if (mfgShow && mfgFinish) {
            mfgFinish = false;
            mfgShow = false;
            finish();
        }
    }

    public boolean onTouch(View arg0, MotionEvent event) {
        int y;
        int x;
        if (CanJni.GetSubType() == 0 || CanJni.GetSubType() == 4 || CanJni.GetSubType() == 8 || CanJni.GetSubType() == 9) {
            int id = ((Integer) arg0.getTag()).intValue();
            int action = event.getAction();
            if (action == 0) {
                Log.d(TAG, "MotionEvent.ACTION_DOWN  id= " + id);
                switch (id) {
                    case 0:
                        CanJni.CadillacWithCDSendKey(21, 1);
                        break;
                    case 1:
                        CanJni.CadillacWithCDSendKey(38, 1);
                        break;
                    case 2:
                        CanJni.CadillacWithCDSendKey(48, 1);
                        break;
                    case 3:
                        CanJni.CadillacWithCDSendKey(49, 1);
                        break;
                    case 4:
                        CanJni.CadillacWithCDSendKey(46, 1);
                        break;
                    case 5:
                        CanJni.CadillacWithCDSendKey(47, 1);
                        break;
                    case 6:
                        CanJni.CadillacWithCDSendKey(51, 1);
                        break;
                    case 7:
                        CanJni.CadillacWithCDSendKey(50, 1);
                        break;
                    case 8:
                        CanJni.CadillacWithCDSendKey(44, 1);
                        break;
                    case 9:
                        CanJni.CadillacWithCDSendKey(43, 1);
                        break;
                    case 10:
                        CanJni.CadillacWithCDSendKey(41, 1);
                        break;
                    case 11:
                        CanJni.CadillacWithCDSendKey(45, 1);
                        break;
                    case 12:
                        CanJni.CadillacWithCDSendKey(32, 1);
                        break;
                    case 13:
                        CanJni.CadillacWithCDSendKey(33, 1);
                        break;
                    case 14:
                        CanJni.CadillacWithCDSendKey(34, 1);
                        break;
                    case 15:
                        CanJni.CadillacWithCDSendKey(35, 1);
                        break;
                    case 16:
                        CanJni.CadillacWithCDSendKey(36, 1);
                        break;
                    case 17:
                        CanJni.CadillacWithCDSendKey(37, 1);
                        break;
                    case 18:
                        CanJni.CadillacWithCDSendKey(39, 1);
                        break;
                    case 19:
                        CanJni.CadillacWithCDSendKey(55, 1);
                        break;
                }
            } else if (action == 1) {
                CanJni.CadillacWithCDSendKey(0, 0);
            }
        } else {
            if (CanJni.GetSubType() == 5 || CanJni.GetSubType() == 6) {
                x = (int) ((event.getX() * 800.0f) / 1024.0f);
                y = (int) ((event.getY() * 480.0f) / 600.0f);
            } else {
                x = (int) ((event.getX() * 800.0f) / 768.0f);
                y = (int) ((event.getY() * 480.0f) / 432.0f);
            }
            int y2 = y + 20;
            if (y2 > 480) {
                y2 = 480;
            }
            if (x >= 0 && x <= 800 && y2 >= 0) {
                if (event.getAction() == 0 || event.getAction() == 2) {
                    Log.d(TAG, "onTouch ACTION_DOWN event.getX() = " + x);
                    Log.d(TAG, "onTouch ACTION_DOWN event.getY() = " + y2);
                    CanJni.CadillacWithCDSendTouch(1, x, y2);
                } else if (event.getAction() == 1) {
                    Log.d(TAG, "onTouch ACTION_UP event.getX() = " + x);
                    Log.d(TAG, "onTouch ACTION_UP event.getY() = " + y2);
                    CanJni.CadillacWithCDSendTouch(0, x, y2);
                }
            }
        }
        return false;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public static void Init() {
        if (FtSet.Getlgb1() != 0) {
            Mcu.SendXKey(33);
        } else {
            Mcu.SendXKey(34);
        }
        if (FtSet.Getlgb2() == 1) {
            Mcu.SendXKey(41);
        } else {
            Mcu.SendXKey(40);
        }
        if (CanJni.GetSubType() == 1 || CanJni.GetSubType() == 3 || CanJni.GetSubType() == 2 || CanJni.GetSubType() == 5) {
            Mcu.SendXKey(51);
        } else if (CanJni.GetSubType() == 6 || CanJni.GetSubType() == 7) {
            Mcu.SendXKey(52);
        } else if (CanJni.GetSubType() == 8 || CanJni.GetSubType() == 9) {
            Mcu.SendXKey(53);
        } else {
            Mcu.SendXKey(50);
        }
        if (FtSet.IsIconExist(1) == 0) {
            Iop.RstPort(0);
        }
    }

    public static void DealDevEvent() {
        BtExe bt = BtExe.getBtInstance();
        if (mOldBtSta != bt.getSta()) {
            mOldBtSta = bt.getSta();
            if (mOldBtSta == 3 || mOldBtSta == 2 || mOldBtSta == 4) {
                Log.d(TAG, "Bt call on ");
                Iop.RstPort(1);
            } else {
                Log.d(TAG, "Bt call of ");
                Iop.RstPort(0);
            }
        }
        if (CanIF.mGpsVoiceDelay > 0) {
            CanIF.mGpsVoiceDelay--;
            if (CanIF.mGpsVoiceDelay == 0) {
                Log.d(TAG, "GpsVoice on ");
                Iop.RstPort(1);
            }
        }
    }
}
