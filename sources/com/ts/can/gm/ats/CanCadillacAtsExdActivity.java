package com.ts.can.gm.ats;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.can.CanIF;
import com.ts.can.gm.xt5.CanCadillacXt5CarFuncActivity;
import com.ts.factoryset.AtcDisplaySettingsUtils;
import com.ts.main.common.MainSet;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;

public class CanCadillacAtsExdActivity extends CanBaseActivity implements UserCallBack, View.OnTouchListener, View.OnClickListener {
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
    public static final String TAG = "CanCadillacAtsExdActivity";
    public static int mBtCnt = 0;
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
        setContentView(R.layout.activity_can_cadillax_withcd_base);
        this.mManager = new RelativeLayoutManager(this, R.id.can_cadillax_withcd_layout);
        if (CanJni.GetSubType() == 6) {
            this.mManager.GetLayout().setBackgroundResource(R.drawable.cad_ac_bg01);
            for (int i = 0; i < 5; i++) {
                this.mBtnRadioNum[i] = addButton((i * 80) + 144, 176, 80, 90, 0, 0, i + 12);
            }
        } else {
            this.mManager.GetLayout().setOnTouchListener(this);
            this.mManager.GetLayout().setClickable(true);
        }
        if (CanFunc.getInstance().IsCore() == 1) {
            this.mManager.GetLayout().setTranslationY(119.0f);
        }
        this.mBtnFav = addButton(1, 133, R.drawable.cad_ac_fav_up, R.drawable.cad_ac_fav_dn, 0);
        this.mBtnHome = addButton(673, 133, R.drawable.cad_ac_home_up, R.drawable.cad_ac_home_dn, 1);
        this.mBtnVolDec = addButton(1, 275, R.drawable.cad_ac_up_up, R.drawable.cad_ac_up_dn, 2);
        this.mBtnVolInc = addButton(190, 275, R.drawable.cad_ac_dn_up, R.drawable.cad_ac_dn_dn, 3);
        this.mBtnPre = addButton(Can.CAN_FORD_ESCORT_LY, 275, R.drawable.cad_ac_prve_up, R.drawable.cad_ac_prve_dn, 4);
        this.mBtnNext = addButton(443, 275, R.drawable.cad_ac_next_up, R.drawable.cad_ac_next_dn, 5);
        this.mBtnSelInc = addButton(705, 275, R.drawable.cad_ac_dn_up, R.drawable.cad_ac_dn_dn, 6);
        this.mBtnSelDec = addButton(516, 275, R.drawable.cad_ac_up_up, R.drawable.cad_ac_up_dn, 7);
        this.mBtnRadio = addButton(163, AtcDisplaySettingsUtils.SPECIFIC_Y_SMALL2, R.drawable.cad_ac_radio_up, R.drawable.cad_ac_radio_dn, 8);
        this.mBtnMedia = addButton(274, AtcDisplaySettingsUtils.SPECIFIC_Y_SMALL2, R.drawable.cad_ac_media_up, R.drawable.cad_ac_media_dn, 9);
        this.mBtnPhone = addButton(385, AtcDisplaySettingsUtils.SPECIFIC_Y_SMALL2, R.drawable.cad_ac_phone_up, R.drawable.cad_ac_phone_dn, 10);
        this.mBtnBack = addButton(496, AtcDisplaySettingsUtils.SPECIFIC_Y_SMALL2, R.drawable.cad_ac_back_up, R.drawable.cad_ac_back_dn, 11);
        this.mBtnPower = addButton(76, 268, R.drawable.cad_ac_shut_up, R.drawable.cad_ac_shut_dn, 18);
        this.mBtnMenu = addButton(CanCameraUI.BTN_VW_WC_MODE2, 268, R.drawable.cad_ac_menu_up, R.drawable.cad_ac_menu_dn, 19);
    }

    public ParamButton addButton(int x, int y, int normal, int selected, int id) {
        ParamButton button = this.mManager.AddButton(x, y);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        if (CanJni.GetSubType() != 6) {
            button.Show(false);
        }
        return button;
    }

    public ParamButton addButton(int x, int y, int w, int h, int normal, int selected, int id) {
        ParamButton button = this.mManager.AddButton(x, y, w, h);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        if (CanJni.GetSubType() != 6) {
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
        MainSet.GetInstance().SetVideoChannel(2);
        if (CanFunc.getInstance().IsCore() == 1) {
            BackcarService.getInstance().SetSource(1);
        }
        this.mCameraView = (AutoFitTextureView) findViewById(R.id.DevtextureView);
        BackcarService.getInstance().StartCamera(this.mCameraView, false);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-1, -1);
        if (CanJni.GetSubType() == 6) {
            lp.width = 480;
            lp.height = Can.CAN_LIEBAO_WC;
            lp.leftMargin = 144;
            lp.topMargin = 6;
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
            CanFunc.showCanActivity(CanCadillacAtsExdActivity.class);
        }
    }

    public static void entCadillacAtsMode() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanCadillacAtsExdActivity.class);
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
        if (CanJni.GetSubType() == 6) {
            int id = ((Integer) arg0.getTag()).intValue();
            int action = event.getAction();
            if (action != 0) {
                if (action == 1) {
                    switch (id) {
                        case 0:
                            CanJni.GmSbCarKeyCtl(23, 0);
                            break;
                        case 1:
                            CanJni.GmSbCarKeyCtl(11, 0);
                            break;
                        case 2:
                            CanJni.GmSbCarKeyCtl(17, 0);
                            break;
                        case 3:
                            CanJni.GmSbCarKeyCtl(16, 0);
                            break;
                        case 4:
                            CanJni.GmSbCarKeyCtl(9, 0);
                            break;
                        case 5:
                            CanJni.GmSbCarKeyCtl(10, 0);
                            break;
                        case 6:
                            CanJni.GmSbCarKeyCtl(18, 0);
                            break;
                        case 7:
                            CanJni.GmSbCarKeyCtl(19, 0);
                            break;
                        case 8:
                            CanJni.GmSbCarKeyCtl(8, 0);
                            break;
                        case 9:
                            CanJni.GmSbCarKeyCtl(12, 0);
                            break;
                        case 10:
                            CanJni.GmSbCarKeyCtl(22, 0);
                            break;
                        case 11:
                            CanJni.GmSbCarKeyCtl(14, 0);
                            break;
                        case 12:
                            CanJni.GmSbCarKeyCtl(3, 0);
                            break;
                        case 13:
                            CanJni.GmSbCarKeyCtl(4, 0);
                            break;
                        case 14:
                            CanJni.GmSbCarKeyCtl(5, 0);
                            break;
                        case 15:
                            CanJni.GmSbCarKeyCtl(6, 0);
                            break;
                        case 16:
                            CanJni.GmSbCarKeyCtl(7, 0);
                            break;
                        case 18:
                            CanJni.GmSbCarKeyCtl(20, 0);
                            break;
                        case 19:
                            CanJni.GmSbCarKeyCtl(21, 0);
                            break;
                    }
                }
            } else {
                Log.d(TAG, "MotionEvent.ACTION_DOWN  id= " + id);
                switch (id) {
                    case 0:
                        CanJni.GmSbCarKeyCtl(23, 1);
                        break;
                    case 1:
                        CanJni.GmSbCarKeyCtl(11, 1);
                        break;
                    case 2:
                        CanJni.GmSbCarKeyCtl(17, 1);
                        break;
                    case 3:
                        CanJni.GmSbCarKeyCtl(16, 1);
                        break;
                    case 4:
                        CanJni.GmSbCarKeyCtl(9, 1);
                        break;
                    case 5:
                        CanJni.GmSbCarKeyCtl(10, 1);
                        break;
                    case 6:
                        CanJni.GmSbCarKeyCtl(18, 1);
                        break;
                    case 7:
                        CanJni.GmSbCarKeyCtl(19, 1);
                        break;
                    case 8:
                        CanJni.GmSbCarKeyCtl(8, 1);
                        break;
                    case 9:
                        CanJni.GmSbCarKeyCtl(12, 1);
                        break;
                    case 10:
                        CanJni.GmSbCarKeyCtl(22, 1);
                        break;
                    case 11:
                        CanJni.GmSbCarKeyCtl(14, 1);
                        break;
                    case 12:
                        CanJni.GmSbCarKeyCtl(3, 1);
                        break;
                    case 13:
                        CanJni.GmSbCarKeyCtl(4, 1);
                        break;
                    case 14:
                        CanJni.GmSbCarKeyCtl(5, 1);
                        break;
                    case 15:
                        CanJni.GmSbCarKeyCtl(6, 1);
                        break;
                    case 16:
                        CanJni.GmSbCarKeyCtl(7, 1);
                        break;
                    case 18:
                        CanJni.GmSbCarKeyCtl(20, 1);
                        break;
                    case 19:
                        CanJni.GmSbCarKeyCtl(21, 1);
                        break;
                }
            }
        } else {
            int x = (int) ((event.getX() * 800.0f) / 768.0f);
            int y = (int) ((event.getY() * 480.0f) / 432.0f);
            if (x >= 0 && x <= 800 && y >= 0 && y <= 480) {
                if (event.getAction() == 0 || event.getAction() == 2) {
                    Log.d(TAG, "onTouch ACTION_DOWN event.getX() = " + x);
                    Log.d(TAG, "onTouch ACTION_DOWN event.getY() = " + y);
                    CanJni.GmSbCarTouchCtl(x, y, 1);
                } else if (event.getAction() == 1) {
                    Log.d(TAG, "onTouch ACTION_UP event.getX() = " + x);
                    Log.d(TAG, "onTouch ACTION_UP event.getY() = " + y);
                    CanJni.GmSbCarTouchCtl(x, y, 0);
                }
            }
        }
        return false;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public static void Init() {
        if (FtSet.Getlgb1() == 1) {
            Mcu.SendXKey(33);
        } else if (FtSet.Getlgb1() == 2) {
            Mcu.SendXKey(35);
        } else if (FtSet.Getlgb1() == 3) {
            Mcu.SendXKey(36);
        } else {
            Mcu.SendXKey(34);
        }
        if (CanFunc.getInstance().IsCore() == 1) {
            if (FtSet.Getlgb2() != 1 || CanCadillacXt5CarFuncActivity.RvsMode() == 2) {
                Mcu.SendXKey(40);
            } else {
                Mcu.SendXKey(41);
            }
        } else if (FtSet.Getlgb2() == 1) {
            Mcu.SendXKey(41);
        } else {
            Mcu.SendXKey(40);
        }
        if (CanFunc.getInstance().IsCore() == 1) {
            Mcu.SendXKey(CanCadillacXt5CarFuncActivity.HostRes() + 50);
        } else if (CanJni.GetSubType() == 7 || CanJni.GetSubType() == 9 || CanJni.GetSubType() == 8) {
            Mcu.SendXKey(51);
        } else if (CanJni.GetSubType() == 11) {
            Mcu.SendXKey(52);
        } else {
            Mcu.SendXKey(50);
        }
        CanCadillacXt5CarFuncActivity.SetCamType(0, 0, 0);
        byte[] fileMsg = new byte[8];
        int sta = CanFunc.GetFileData(CanFunc.Can_Factory_Set_fileName, fileMsg);
        if (!(sta != 0 && fileMsg[0] == 88 && fileMsg[1] == CanJni.GetSubType())) {
            fileMsg[0] = (byte) FtSet.GetCanTp();
            if (FtSet.GetCanSubT() == 6) {
                fileMsg[1] = 0;
            } else if (FtSet.GetCanSubT() == 7 || FtSet.GetCanSubT() == 11) {
                fileMsg[1] = 1;
            } else if (FtSet.GetCanSubT() == 8) {
                fileMsg[1] = 2;
            } else if (FtSet.GetCanSubT() == 9) {
                fileMsg[1] = 3;
            }
            CanFunc.sendFileCarInfo(fileMsg, CanFunc.Can_Factory_Set_fileName);
        }
        Log.d(TAG, "Can_Factory_Set_fileMsg=" + String.format("%d,%d,%d", new Object[]{Integer.valueOf(sta), Byte.valueOf(fileMsg[0]), Byte.valueOf(fileMsg[1])}));
        if (FtSet.IsIconExist(1) == 0) {
            Iop.RstPort(1);
        }
    }

    public static void DealDevUseAll() {
        if (FtSet.IsIconExist(1) == 0 && CanFunc.getInstance().IsCore() == 1) {
            switch (mOldBtSta) {
                case 0:
                    if (Iop.GetMediaOrBlue() > 0) {
                        mOldBtSta = 1;
                        mBtCnt = 10;
                        break;
                    }
                    break;
                case 1:
                    if (mBtCnt <= 0) {
                        mOldBtSta = 2;
                        Log.d(TAG, "Bt call on ");
                        Iop.RstPort(0);
                        CanJni.GmSbCarMoudleCtl(2, 1);
                        break;
                    } else {
                        mBtCnt--;
                        break;
                    }
                case 2:
                    if (Iop.GetMediaOrBlue() == 0) {
                        Log.d(TAG, "Bt call of ");
                        Iop.RstPort(1);
                        CanJni.GmSbCarMoudleCtl(2, 0);
                        mOldBtSta = 0;
                        break;
                    }
                    break;
            }
        }
        if (CanIF.mGpsVoiceDelay > 0) {
            CanIF.mGpsVoiceDelay--;
            if (CanIF.mGpsVoiceDelay == 0) {
                Log.d(TAG, "GpsVoice off ");
                Iop.RstPort(1);
            }
        }
    }

    public static void DealDevEvent() {
        CanDataInfo.GM_OnStarSta mStaData = new CanDataInfo.GM_OnStarSta();
        CanJni.GMGetOnStar(mStaData, new CanDataInfo.GM_PhoneInfo());
        if (mStaData.Sta >= 1 && FtSet.IsIconExist(1) == 0) {
            Iop.RstPort(1);
        }
        if (CanFunc.IsCamMode() == 0) {
            showCadillacAtsWin();
        }
    }
}
