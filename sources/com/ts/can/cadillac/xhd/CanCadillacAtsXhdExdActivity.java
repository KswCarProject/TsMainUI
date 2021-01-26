package com.ts.can.cadillac.xhd;

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
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.KeyDef;
import com.yyw.ts70xhw.Mcu;

public class CanCadillacAtsXhdExdActivity extends CanBaseActivity implements UserCallBack, View.OnTouchListener, View.OnClickListener {
    private static final int BTN_BACK = 11;
    private static final int BTN_FAV = 0;
    private static final int BTN_MEDIA = 9;
    private static final int BTN_MENU = 19;
    private static final int BTN_NEXT = 5;
    private static final int BTN_OK = 1;
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
    public static final String TAG = "CanCadillacAtsXhdExdActivity";
    public static int mOldBtSta = 0;
    public static boolean mfgAutoEnt = false;
    public static boolean mfgFinish = false;
    private static boolean mfgSendFunc = false;
    public static boolean mfgShow = false;
    private ParamButton mBtnBack;
    private ParamButton mBtnFav;
    private ParamButton mBtnMedia;
    private ParamButton mBtnMenu;
    private ParamButton mBtnNext;
    private ParamButton mBtnOk;
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
    private CustomImgView[] mLine = new CustomImgView[7];
    private RelativeLayoutManager mManager;
    private WindowManager wManager;
    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_cadillax_ats_base);
        this.mManager = new RelativeLayoutManager(this, R.id.can_cadillax_ats_layout);
        if (CanJni.GetSubType() == 0) {
            this.mManager.GetLayout().setBackgroundResource(R.drawable.cadillac_ats_bg01);
            for (int i = 0; i < 6; i++) {
                this.mLine[i] = this.mManager.AddImage((i * 150) + 134, 399, R.drawable.cadillac_ats_line01);
            }
        } else {
            this.mManager.GetLayout().setOnTouchListener(this);
            this.mManager.GetLayout().setClickable(true);
        }
        this.mBtnFav = addButton(CanCameraUI.BTN_CCH9_MODE6, 465, R.drawable.cadillac_ats_fav_up, R.drawable.cadillac_ats_fav_dn, 0);
        this.mBtnOk = addButton(886, Can.CAN_CC_WC, R.drawable.cadillac_ats_ok_up, R.drawable.cadillac_ats_ok_dn, 1);
        this.mBtnVolDec = addButton(0, 259, R.drawable.cadillac_ats_vol_r_up, R.drawable.cadillac_ats_vol_r_dn, 2);
        this.mBtnVolInc = addButton(0, 48, R.drawable.cadillac_ats_vol_plus_up, R.drawable.cadillac_ats_vol_plus_dn, 3);
        this.mBtnPre = addButton(0, 374, R.drawable.cadillac_ats_prev_up, R.drawable.cadillac_ats_prev_dn, 4);
        this.mBtnNext = addButton(886, 375, R.drawable.cadillac_ats_next_up, R.drawable.cadillac_ats_next_dn, 5);
        this.mBtnSelInc = addButton(886, 259, R.drawable.cadillac_ats_dn_up, R.drawable.cadillac_ats_dn_dn, 6);
        this.mBtnSelDec = addButton(886, 48, R.drawable.cadillac_ats_up_up, R.drawable.cadillac_ats_up_dn, 7);
        this.mBtnRadio = addButton(70, 465, R.drawable.cadillac_ats_radio_up, R.drawable.cadillac_ats_radio_dn, 8);
        this.mBtnMedia = addButton(KeyDef.SKEY_CHUP_5, 465, R.drawable.cadillac_ats_media_up, R.drawable.cadillac_ats_media_dn, 9);
        this.mBtnBack = addButton(434, 465, R.drawable.cadillac_ats_back_up, R.drawable.cadillac_ats_back_dn, 11);
        this.mBtnPower = addButton(0, Can.CAN_CC_WC, R.drawable.cadillac_ats_mute_up, R.drawable.cadillac_ats_mute_dn, 18);
        this.mBtnMenu = addButton(Can.CAN_FORD_ESCORT_LY, 465, R.drawable.cadillac_ats_menu_up, R.drawable.cadillac_ats_menu_dn, 19);
        for (int i2 = 0; i2 < 5; i2++) {
            this.mBtnRadioNum[i2] = addButton((i2 * 150) + Can.CAN_CC_WC, 399, R.drawable.cadillac_ats_arrow_up, R.drawable.cadillac_ats_arrow_dn, i2 + 12);
        }
    }

    public ParamButton addButton(int x, int y, int normal, int selected, int id) {
        ParamButton button = this.mManager.AddButton(x, y);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        if (CanJni.GetSubType() != 0) {
            button.Show(false);
        }
        return button;
    }

    public ParamButton addButton(int x, int y, int w, int h, int normal, int selected, int id) {
        ParamButton button = this.mManager.AddButton(x, y, w, h);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
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
        MainSet.GetInstance().SetVideoChannel(2);
        this.mCameraView = (AutoFitTextureView) findViewById(R.id.DevtextureView);
        BackcarService.getInstance().StartCamera(this.mCameraView, false);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-1, -1);
        if (CanJni.GetSubType() == 0) {
            lp.width = 748;
            lp.height = 365;
            lp.leftMargin = 138;
            lp.topMargin = 22;
        } else {
            getWindow().getDecorView().setSystemUiVisibility(4612);
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
            CanFunc.showCanActivity(CanCadillacAtsXhdExdActivity.class);
        }
    }

    public static void entCadillacAtsMode() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanCadillacAtsXhdExdActivity.class);
        }
    }

    public static void finishCadillacAtsWin() {
        if (mfgShow && mfgAutoEnt) {
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
        if (CanJni.GetSubType() == 0) {
            int id = ((Integer) arg0.getTag()).intValue();
            int action = event.getAction();
            if (action == 0) {
                Log.d(TAG, "MotionEvent.ACTION_DOWN  id= " + id);
                switch (id) {
                    case 0:
                        CanJni.CadillacWithCDSendKey(21, 1);
                        break;
                    case 1:
                        CanJni.CadillacWithCDSendKey(55, 1);
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
                        CanJni.CadillacWithCDSendKey(40, 1);
                        break;
                }
            } else if (action == 1) {
                CanJni.CadillacWithCDSendKey(0, 0);
            }
        } else {
            int x = (int) ((event.getX() * 800.0f) / 1024.0f);
            int y = (int) ((event.getY() * 480.0f) / 600.0f);
            if (x >= 0 && x <= 800 && y >= 0 && y <= 480) {
                if (event.getAction() == 0 || event.getAction() == 2) {
                    Log.d(TAG, "onTouch ACTION_DOWN event.getX() = " + x);
                    Log.d(TAG, "onTouch ACTION_DOWN event.getY() = " + y);
                    CanJni.CadillacWithCDSendTouch(1, x, y);
                } else if (event.getAction() == 1) {
                    Log.d(TAG, "onTouch ACTION_UP event.getX() = " + x);
                    Log.d(TAG, "onTouch ACTION_UP event.getY() = " + y);
                    CanJni.CadillacWithCDSendTouch(0, x, y);
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
        if (CanJni.GetSubType() == 1) {
            Mcu.SendXKey(51);
        } else if (CanJni.GetSubType() == 2) {
            Mcu.SendXKey(52);
        } else {
            Mcu.SendXKey(50);
        }
        if (FtSet.IsIconExist(1) == 0) {
            Iop.RstPort(0);
        }
    }

    public static void DealDevEvent() {
        BtExe bt = BtExe.getBtInstance();
        if (mOldBtSta != bt.getSta() && FtSet.IsIconExist(1) == 0) {
            mOldBtSta = bt.getSta();
            if ((mOldBtSta == 3 || mOldBtSta == 2 || mOldBtSta == 4) && CanIF.IsExdMode()) {
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
