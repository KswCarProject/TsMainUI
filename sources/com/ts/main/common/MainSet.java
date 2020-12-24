package com.ts.main.common;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import com.autochips.storage.EnvironmentATC;
import com.hongfans.carmedia.Constant;
import com.lgb.canmodule.CanJni;
import com.qiner.appinfo.AppListUtil;
import com.ts.MainUI.AuthServer;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.MainUI.TsFile;
import com.ts.bt.BtExe;
import com.ts.can.CanCameraUI;
import com.ts.can.CanIF;
import com.ts.dvdplayer.definition.MediaDef;
import com.ts.factoryset.FsBaseActivity;
import com.ts.main.auth.FcTestMode;
import com.ts.main.auth.FileUtils;
import com.ts.main.common.ShellUtils;
import com.ts.main.txz.Cyb;
import com.ts.main.txz.TxzReg;
import com.ts.main.txz.Wrc;
import com.txznet.sdk.TXZPoiSearchManager;
import com.txznet.sdk.tongting.IConstantData;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;
import com.yyw.ts70xhw.Radio;
import com.yyw.ts70xhw.StSet;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class MainSet {
    public static final String ACTION_MAINUI_MCUSTATE = "com.ts.main.mcustate";
    public static final String ACTION_SET_TITLE = "android.intent.action.SET_STATUSBAR_TITLE";
    public static final int AV_V = 2;
    public static final int BACK_V = 0;
    public static final String BASIC_TIME_STRING = "2016-10-01 00:00:00";
    public static final int CORE3561 = 1;
    public static final int CORE8227L = 0;
    public static final int COREBOARD = 1;
    private static final String CURRENT_MODE_KEY = "CURRENT_MODE";
    public static final int DAB_NEXT = 46;
    public static final int DAB_PREV = 45;
    public static final int DTV_V = 1;
    private static char[] Dspversiono = new char[32];
    private static final String FT_SET_DIR = "/mnt/sdcard/Iconfig";
    public static final int HD_V = 4;
    private static final String MODE_CHANGING_ACTION = "com.android.settings.location.MODE_CHANGING";
    private static final String NEW_MODE_KEY = "NEW_MODE";
    public static final int PLAYER_DDH = 1;
    public static final int PLAYER_KW = 0;
    public static final int PlAYER_CYB = 2;
    static final String RLF_KOREA_MOUDLENAME = "Finedrive A1";
    public static final int SCREEN_1024X480 = 4;
    public static final int SCREEN_1024X600 = 1;
    public static final int SCREEN_1280X480 = 5;
    public static final int SCREEN_1366X768 = 7;
    public static final int SCREEN_1920x720 = 9;
    public static final int SCREEN_768X1024 = 3;
    public static final int SCREEN_800X1280 = 6;
    public static final int SCREEN_800X480 = 2;
    public static final int SCREEN_800x600 = 8;
    public static final int SIM_OWNER_FF = 3;
    public static final int SIM_OWNER_MG = 1;
    public static final int SIM_OWNER_TXZ = 2;
    public static final int SIM_OWNER_UNKNOWN = 0;
    public static final String SP_FLKJ = "8";
    public static final String SP_FXCARPLAY = "9";
    public static final String SP_KS_QOROS = "4";
    public static final String SP_LM_WR = "5";
    public static final String SP_RLF_KORON = "2";
    public static final String SP_TW_CJW = "7";
    public static final String SP_XH_DMAX = "3";
    public static final String SP_XH_FORD = "10";
    public static final String SP_XPH5 = "1";
    public static final String SP_YSJ_QP = "6";
    private static final String TAG = "MainSet";
    private static final String TESTMODE_Txt = "TsTestMode.txt";
    static final String TSKJ_MOUDLENAME = "";
    static final String TS_DEMO_FILE = "/demo";
    static final String TS_DEMO_PATH = "/mnt/sdcard";
    static final String TS_OEM_PATH = "/mnt/sdcard/tsoem";
    public static FcTestMode Testmode;
    public static boolean bCheckNave = true;
    public static boolean bKeyBroad = false;
    public static boolean bShowDlg = false;
    public static Context mContext = null;
    private static MainSet mMainSet = null;
    static int nCoreNum = 0;
    public static int nFcamTime = 0;
    public static int nPlayer = 0;
    public static int nShowScreen = 0;
    public static int nSimOwner = 0;
    public static String seiid = "TSKJ";
    private char[] Dspversion = new char[32];
    EnvironmentATC EnvATC = null;
    boolean IsTestMode = false;
    List<PackageInfo> Mypacks = null;
    public boolean bCeleb = false;
    boolean bHaveUpdate = false;
    boolean bHaveXfapp = false;
    boolean bShowold = false;
    boolean bWrcInint = false;
    private int forbiddenSpeed = -1;
    private ActivityManager mActivityManager = null;
    private String mCustom = null;
    private char[] mModelName = new char[32];
    int nBtnShow = -1;
    int nDelayConnectWrc = 0;
    public int nKeyNum = 0;
    int nRet = -1;
    int nTaskTime = 0;
    int nturnOn = 0;
    private String toastString = null;

    public interface DealPasswordResult {
        void OnOK();
    }

    public static MainSet GetInstance() {
        if (mMainSet == null) {
            mMainSet = new MainSet();
        }
        return mMainSet;
    }

    /* access modifiers changed from: package-private */
    public void startRechargeService() {
        Intent intent = new Intent();
        intent.setPackage("com.ts.rechargecenter");
        intent.setAction("android.intent.action.SHOW_MESSAGE");
        mContext.startService(intent);
    }

    /* access modifiers changed from: package-private */
    public void SetStatusBarState() {
    }

    /* access modifiers changed from: package-private */
    public void StartMgService(String packagen, String name) {
        if (mContext != null) {
            ComponentName cn = new ComponentName(packagen, name);
            Intent intent = new Intent();
            intent.setComponent(cn);
            mContext.startService(intent);
        }
    }

    /* access modifiers changed from: package-private */
    public void SendMgAccState(int nOn) {
        if (mContext == null) {
            return;
        }
        if (nOn == 1) {
            Intent tmpIntent = new Intent("com.android.ecar.recv");
            tmpIntent.putExtra("ecarSendKey", "ACC_ON");
            tmpIntent.putExtra("cmdType", "standCMD");
            tmpIntent.putExtra("keySet", "");
            mContext.sendBroadcast(tmpIntent);
            return;
        }
        Intent tmpIntent2 = new Intent("com.android.ecar.recv");
        tmpIntent2.putExtra("ecarSendKey", "ACC_OFF");
        tmpIntent2.putExtra("cmdType", "standCMD");
        tmpIntent2.putExtra("keySet", "");
        mContext.sendBroadcast(tmpIntent2);
    }

    public void Inint(int nBatFirst) {
        this.mCustom = mContext.getResources().getString(R.string.custom_num);
        Log.d("wcb", "com.ts.main.lkss.CarComputerActivity state ===== " + mContext.getPackageManager().getComponentEnabledSetting(new ComponentName("com.ts.MainUI", "com.ts.main.lkss.CarComputerActivity")));
        if (mContext.getResources().getString(R.string.custom_control_gismusic_vol).equals(SP_XPH5)) {
            Evc.nNaviHunyin = 1;
        }
        if (IsYSJQP()) {
            startRechargeService();
        }
        if (IsTwcjw() || FtSet.GetExUart() == 1) {
            Iop.SetAuxHoldEx(3);
        }
        if (nBatFirst == 1 && IsTwcjw()) {
            Evc.GetInstance().evol_navivol_set(50);
        }
        new Thread() {
            public void run() {
                if (TsFile.fileIsExists("/system/bin/start_usbmuxd.sh")) {
                    ShellUtils.execCommand(new String[]{"start_usbmuxd.sh & "}, true);
                }
            }
        }.start();
        if (GetInstance().IsHaveApk("com.aispeech.lyra.daemon")) {
            MainUI.bSupportFoucus = true;
        }
        if (nBatFirst == 1) {
            Evc.GetInstance().AddNaviWhileList("com.aispeech.lyra.daemon");
            Evc.GetInstance().AddNaviWhileList("com.ts.tts_touch");
            Evc.GetInstance().AddNaviWhileList(MainUI.KAIYI_PNAME);
            Evc.GetInstance().AddNaviWhileList("com.google.android.apps.maps");
            Evc.GetInstance().AddNaviWhileList("com.atlan");
            Evc.GetInstance().AddNaviWhileList("com.autonavi.amapauto");
            Evc.GetInstance().AddNaviWhileList("com.google.android.googlequicksearchbox");
        }
        if (MainUI.nMcuRet == 1) {
            writeFtsetLauncher(false);
        }
        if (GetSerid().contains("RLFA")) {
            SystemProperties.set("forfan.serial.number", GetProid());
        }
        if (IsRxfKoren()) {
            SystemProperties.set("forfan.device.model", RLF_KOREA_MOUDLENAME);
            SystemProperties.set("forfan.serial.number", GetProid());
            if (nBatFirst == 1) {
                Evc.GetInstance().evol_mediavol_set(8);
                Evc.GetInstance().evol_navivol_set(40);
                Evc.GetInstance().evol_btvol_set(10);
                Evc.GetInstance().evol_ringvol_set(50);
                Evc.GetInstance().evol_Alarmvol_set(8);
                Evc.GetInstance().evol_systemvol_set(50);
                if (!BtExe.getBtInstance().isAutoConnect()) {
                    BtExe.getBtInstance().autoConnectSw();
                }
                StSet.Setmvwns(100);
                ((AlarmManager) mContext.getSystemService("alarm")).setTimeZone("Asia/Seoul");
                if (!Settings.Secure.isLocationProviderEnabled(mContext.getContentResolver(), "gps")) {
                    Settings.Secure.setLocationProviderEnabled(mContext.getContentResolver(), "gps", true);
                }
                Settings.Global.putInt(mContext.getContentResolver(), "auto_time", 0);
                Settings.Global.putInt(mContext.getContentResolver(), "auto_time_gps", 1);
                Settings.Global.putInt(mContext.getContentResolver(), "auto_time_zone", 1);
                int currentMode = Settings.Secure.getInt(mContext.getContentResolver(), "location_mode", 0);
                Intent intent = new Intent(MODE_CHANGING_ACTION);
                intent.putExtra(CURRENT_MODE_KEY, currentMode);
                intent.putExtra(NEW_MODE_KEY, 3);
                mContext.sendBroadcast(intent, "android.permission.WRITE_SECURE_SETTINGS");
                Settings.Secure.putInt(mContext.getContentResolver(), "location_mode", 3);
            }
        } else if (GetModelName() != null) {
            if (GetModelName().equals("")) {
                Log.i(TAG, "GetModelName==null" + GetModelName());
            } else {
                Log.i(TAG, "GetModelName==" + GetModelName());
                SystemProperties.set("forfan.device.model", GetModelName());
            }
        }
        if (IsHaveApk("com.ecar.AppManager")) {
            StartMgService("com.ecar.AppManager", "com.ecar.AppManager.AppManagerService");
            StartMgService("com.ecar.assistantnew", "com.ecar.assistantnew.service.BootService");
        }
        SendIntent("com.cusc.action.ACC_ON");
        SendIntent(MainUI.ACTION_MAINUI_ACCON);
        SendMgAccState(1);
        if (FtSet.GetTouchBall() == 1) {
            Intent intent2 = new Intent();
            intent2.setAction("com.ts.mytouch.TouchService");
            mContext.startService(intent2);
        } else {
            Intent intent3 = new Intent();
            intent3.setAction("com.ts.mytouch.TouchService");
            mContext.stopService(intent3);
        }
        if ((IsCustom("LEMA") || GetSerid().contains("LEMA")) && nBatFirst == 1 && !BtExe.getBtInstance().isAutoConnect()) {
            BtExe.getBtInstance().autoConnectSw();
        }
        if (IsVSUI() || IsBmwX1()) {
            TsDisplay.GetInstance().nSetTcon = 0;
            Log.i(TAG, "******************WmInit mDisplay.nSetTcon = " + TsDisplay.GetInstance().nSetTcon);
        }
        if (isZh() && nBatFirst == 1) {
            AlarmManager mAlarmManager = (AlarmManager) mContext.getSystemService("alarm");
            if (isrTwZh()) {
                mAlarmManager.setTimeZone("Asia/Taipei");
            } else {
                mAlarmManager.setTimeZone("Asia/Shanghai");
            }
        }
        if (GetInstance().IsLocal()) {
            FtSet.SetStandByTime(0);
            Evc.GetInstance().AddNaviWhileList("com.ts.tts_touch");
        }
        if (FtSet.GetOptionSW() == 0) {
            SystemProperties.set("forfan.swc.enable", "0");
        } else {
            SystemProperties.set("forfan.swc.enable", SP_XPH5);
        }
        if (!IsHaveBt()) {
            SystemProperties.set("forfan.mainnbt.enable", "0");
        }
        if (GetInstance().IsHaveApk("com.glsx.ddbox")) {
            nPlayer = 1;
        } else if (GetInstance().IsHaveApk(Constant.PACKAGE_NAME)) {
            Cyb.GetInstance().Inint(mContext);
            nPlayer = 2;
        } else {
            nPlayer = 0;
        }
        Log.i(TAG, "MainSet.nPlayer = " + nPlayer);
        if (IsHaveApk("com.baidu.input")) {
            SetDefaultInput("com.baidu.input/.ImeService");
        } else if (IsHaveApk("com.iflytek.inputmethod")) {
            SetDefaultInput("com.iflytek.inputmethod/.FlyIME");
        } else if (IsHaveApk("com.google.android.apps.inputmethod.zhuyin")) {
            SetDefaultInput("com.google.android.apps.inputmethod.zhuyin/.ZhuyinInputMethodService");
        }
        if (nBatFirst == 1) {
            Evc.GetInstance().SetMicGain();
        }
        if (IsQOROS()) {
            if (FtSet.GetApkForbid() == 1) {
                SystemProperties.set("forfan.notallow.install", SP_XPH5);
            }
            SystemProperties.set("forfan.skip.mysettings", SP_XPH5);
            StSet.SetBLNight(2);
            if (nBatFirst == 1) {
                if (!BtExe.getBtInstance().isAutoConnect()) {
                    BtExe.getBtInstance().autoConnectSw();
                }
                Settings.System.putString(mContext.getContentResolver(), "time_12_24", "24");
            }
            SystemProperties.set("forfan.maincommon.enable", "0");
            SystemProperties.set("forfan.mainshow.enable", "0");
            SystemProperties.set("forfan.mainvol.enable", "0");
            SystemProperties.set("forfan.maineq.enable", "0");
            SystemProperties.set("forfan.mainvideo.enable", "0");
            SystemProperties.set("forfan.mainnavi.enable", "0");
            SystemProperties.set("forfan.mainnbt.enable", "0");
            SystemProperties.set("forfan.swc.enable", "0");
            SystemProperties.set("forfan.mainversion.enable", "0");
        }
        if (IsFlkj()) {
            SetStatusBarState();
            StSet.SetBLNight(0);
            StSet.SetBLDay(0);
            Evc.GetInstance().evol_vol_set(0);
            Mcu.SendXKey(21);
            SystemProperties.set("forfan.mainvideo.enable", "0");
            SystemProperties.set("forfan.mainnavi.enable", "0");
            SystemProperties.set("forfan.mainnbt.enable", "0");
            SystemProperties.set("forfan.swc.enable", "0");
            SystemProperties.set("forfan.mainvol.enable", "0");
            SystemProperties.set("forfan.maineq.enable", "0");
            SystemProperties.set("forfan.mainshow.enable", "0");
        }
        if (IsMkz()) {
            SystemProperties.set("forfan.maineq.enable", "0");
        }
        if (!mContext.getResources().getString(R.string.custom_hide_eq).equals("0")) {
            SystemProperties.set("forfan.maineq.enable", "0");
        }
        int id = mContext.getResources().getIdentifier("setting_eq_enable", "string", mContext.getPackageName());
        if (id != 0) {
            try {
                if (!Boolean.parseBoolean(mContext.getResources().getString(id))) {
                    SystemProperties.set("forfan.maineq.enable", "0");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!mContext.getResources().getString(R.string.custom_poweroff_show_screen).equals("0")) {
            nShowScreen = 1;
        }
        if (IsXPH5()) {
            Iop.EqExtend(0, 1);
            if (nBatFirst == 1) {
                if (FtSet.GetRearVideoOutput() == 0) {
                    FtSet.SetRearVideoOutput(1);
                }
                if (FtSet.GetExAmp() == 0) {
                    FtSet.SetExAmp(1);
                }
            }
        }
        if ((IsXPH5() || IsXuhuiDmax() || IsXuhuiford()) && FtSet.GetApkForbid() == 1) {
            SystemProperties.set("forfan.notallow.install", SP_XPH5);
        }
        if (mContext.getResources().getString(R.string.custom_forbiden_install).equals(SP_XPH5)) {
            SystemProperties.set("forfan.notallow.install", SP_XPH5);
        }
        if (IsShowBrughtness()) {
            SendIntent("android.intent.action.SHOW_MYBRIGHTNESS");
        }
        if (GetInstance().IsXT5()) {
            SystemProperties.set("forfan.maineq.enable", "0");
            SystemProperties.set("forfan.mainshow.enable", "0");
            Evc.nNaviHunyin = 1;
            if (nBatFirst == 1) {
                Evc.GetInstance().evol_mediavol_set(15);
                Evc.GetInstance().evol_navivol_set(50);
                Evc.GetInstance().evol_btvol_set(30);
                StSet.Setmvwns(0);
            }
        }
        if (CanJni.GetCanFsTp() == 115 && nBatFirst == 1) {
            Evc.GetInstance().evol_mediavol_set(25);
            Evc.GetInstance().evol_navivol_set(80);
        }
        if (IsMkz() && nBatFirst == 1) {
            Evc.GetInstance().evol_mediavol_set(25);
            Evc.GetInstance().evol_navivol_set(80);
        }
        Evc.GetInstance();
        Evc.bNaviVol = true;
        AppListUtil.getInstance().init(mContext);
        SetToFDD();
        if (nBatFirst == 1 && Iop.DspSupport() == 1 && this.mCustom.equals("ZHST")) {
            Iop.DspSetEqm(2);
        }
    }

    public int[] GetCanEnable() {
        if (!IsQOROS()) {
            return null;
        }
        return new int[]{132};
    }

    /* access modifiers changed from: package-private */
    public void SetGpsMode() {
        Settings.Secure.putString(mContext.getContentResolver(), "assisted_gps_enabled", "location_toggle");
        Settings.Global.putInt(mContext.getContentResolver(), "assisted_gps_enabled", 1);
        Settings.Secure.setLocationProviderEnabled(mContext.getContentResolver(), "gps", true);
        Settings.Secure.setLocationProviderEnabled(mContext.getContentResolver(), "network", true);
    }

    /* access modifiers changed from: package-private */
    public void SetDefaultInput(String Input) {
        String defaultIme = Settings.Secure.getString(mContext.getContentResolver(), "default_input_method");
        Log.i(TAG, defaultIme);
        if (defaultIme != null && defaultIme.equalsIgnoreCase("com.android.inputmethod.latin/.LatinIME")) {
            Settings.Secure.putString(mContext.getContentResolver(), "enabled_input_methods", Input);
            Settings.Secure.putString(mContext.getContentResolver(), "default_input_method", Input);
        }
    }

    public void TwShowTitle(String Str) {
        Bundle bundle = new Bundle();
        bundle.putInt("mode", 1);
        bundle.putString(IConstantData.KEY_TITLE, Str);
        Intent intent = new Intent();
        intent.setAction(ACTION_SET_TITLE);
        intent.putExtras(bundle);
        mContext.sendBroadcast(intent);
    }

    public static void SendIntent(String Str) {
        Intent intent = new Intent();
        intent.setAction(Str);
        mContext.sendBroadcast(intent);
    }

    public static void DABControl(int nCode) {
        Intent intent = new Intent();
        intent.setAction("com.microntek.irkeyDown");
        intent.putExtra("keyCode", nCode);
        mContext.sendBroadcast(intent);
    }

    public static void SendMcuState(int nState) {
        Intent intent = new Intent();
        intent.setAction(ACTION_MAINUI_MCUSTATE);
        intent.putExtra("mcustate", nState);
        mContext.sendBroadcast(intent);
    }

    public static void SendVrStateToAutoKing(int nState) {
        if (GetInstance().IsLocal()) {
            Intent Aintent = new Intent();
            Aintent.setAction("com.globalconstant.BROADCAST_CAR_SEND_CMD");
            Aintent.putExtra("domain", "autoking");
            Aintent.putExtra("action", "set_vr_status");
            Aintent.putExtra("Mode", nState);
            mContext.sendBroadcast(Aintent);
        }
    }

    public String[] GetMountedStorage() {
        if (this.EnvATC != null) {
            return this.EnvATC.getStorageMountedPaths();
        }
        this.EnvATC = new EnvironmentATC(mContext);
        return this.EnvATC.getStorageMountedPaths();
    }

    public String GetSerid() {
        return seiid;
    }

    public String GetProid() {
        byte[] mcuid = new byte[32];
        FtSet.GetProId(mcuid, 32);
        return CanIF.byte2String(mcuid);
    }

    /* access modifiers changed from: package-private */
    public String GetModelName() {
        FtSet.GetModelName(this.mModelName);
        String strbName = new String();
        int j = 0;
        while (j < this.mModelName.length && this.mModelName[j] != 0) {
            strbName = String.valueOf(strbName) + this.mModelName[j];
            j++;
        }
        return strbName;
    }

    public boolean IsMathToMcu() {
        if (!this.bHaveXfapp && !IsLocal()) {
            this.bHaveXfapp = IsHaveApk("com.forfan.xfapp");
            if (!this.bHaveXfapp) {
                return false;
            }
        }
        if (GetSerid().contains(mContext.getResources().getString(R.string.custom_num))) {
            return true;
        }
        String str = mContext.getResources().getString(R.string.custom_num);
        if ((GetSerid().contains("SHEN") || GetSerid().contains("YONG") || GetSerid().contains("YSJ")) && str.equals("YONG")) {
            return true;
        }
        if (str.equals("TSKJ") || (GetSerid().contains("TSKJ") && !GetSerid().contains("LOCA"))) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean bIsHaveTestFile() {
        this.nKeyNum = 0;
        boolean bRet = false;
        String[] mCtouchPath = GetInstance().GetMountedStorage();
        if (mCtouchPath == null) {
            return false;
        }
        for (int i = 0; i < mCtouchPath.length; i++) {
            if (TsFile.fileIsExists(String.valueOf(mCtouchPath[i]) + "/" + TESTMODE_Txt)) {
                Testmode = FileUtils.getFcTestMode(String.valueOf(mCtouchPath[i]) + "/" + TESTMODE_Txt);
                if (Testmode.HardType == 1) {
                    bKeyBroad = false;
                    Toast.makeText(mContext, "进入核心板测试模式", 0).show();
                } else {
                    bKeyBroad = true;
                    Toast.makeText(mContext, "进入主板测试模式", 0).show();
                    FtSet.SetRadioIc(Testmode.RadioIC);
                }
                bRet = true;
            }
        }
        if (!bRet) {
            Testmode = new FcTestMode();
        }
        return bRet;
    }

    /* access modifiers changed from: package-private */
    public void CheckTestMode(boolean bEnter) {
        if (!this.IsTestMode) {
            this.IsTestMode = bIsHaveTestFile();
            if (mMainSet.IsTestMode() && bEnter) {
                WinShow.show("com.ts.MainUI", "com.ts.main.auth.FactoryMainActivity");
            }
        }
    }

    public boolean Is3561() {
        return true;
    }

    public boolean bIs502() {
        return false;
    }

    public boolean bIsEnableCan() {
        return true;
    }

    public boolean IsTestMode() {
        if (mContext.getResources().getString(R.string.custom_num).equals("TEST")) {
            return true;
        }
        return this.IsTestMode;
    }

    public boolean IsMAIXIN() {
        if (mContext.getResources().getString(R.string.custom_num).equals("MAIX")) {
            return true;
        }
        return false;
    }

    public boolean IsDIAO() {
        if (mContext.getResources().getString(R.string.custom_num).equals("DIAO")) {
            return true;
        }
        return false;
    }

    public boolean IsSMSI() {
        if (mContext.getResources().getString(R.string.custom_num).equals("SMSI")) {
            return true;
        }
        return false;
    }

    public boolean IsCustom(String str2) {
        if (mContext.getResources().getString(R.string.custom_num).equals(str2)) {
            return true;
        }
        return false;
    }

    static String GetSpesilString() {
        return mContext.getResources().getString(R.string.custom_spesail_install);
    }

    public static boolean IsXPH5() {
        if (GetSpesilString().equals(SP_XPH5)) {
            return true;
        }
        return false;
    }

    public static boolean IsRxfKoren() {
        if (GetSpesilString().equals(SP_RLF_KORON)) {
            return true;
        }
        return false;
    }

    public boolean IsXuhuiDmax() {
        if (GetSpesilString().equals(SP_XH_DMAX)) {
            return true;
        }
        return false;
    }

    public boolean IsXuhuiford() {
        if (GetSpesilString().equals(SP_XH_FORD)) {
            return true;
        }
        return false;
    }

    public static boolean IsFlkj() {
        if (GetSpesilString().equals(SP_FLKJ)) {
            return true;
        }
        return false;
    }

    public static boolean IsFxCarplay() {
        if (GetSpesilString().equals(SP_FXCARPLAY)) {
            return true;
        }
        return false;
    }

    public boolean IsTwcjw() {
        if (GetSpesilString().equals(SP_TW_CJW)) {
            return true;
        }
        return false;
    }

    public static boolean IsYSJQP() {
        if (GetSpesilString().equals(SP_YSJ_QP)) {
            return true;
        }
        return false;
    }

    public static boolean IsQOROS() {
        if (GetSpesilString().equals(SP_KS_QOROS)) {
            return true;
        }
        return false;
    }

    public boolean IsYsjWifiMode() {
        if (TsFile.fileIsExists("/system/nowifi")) {
            return true;
        }
        if (mContext == null || !mContext.getResources().getString(R.string.custom_nowifi_num).equals(SP_XPH5)) {
            return false;
        }
        return true;
    }

    public boolean bAutoAuth() {
        if (mContext == null || !mContext.getResources().getString(R.string.custom_support_autoAuth).equals(SP_XPH5)) {
            return false;
        }
        return true;
    }

    public boolean IsMcuId(String str2) {
        String string = GetSerid();
        if (string != null) {
            return string.contains(str2);
        }
        return false;
    }

    public boolean IsEkeyTurnEnable() {
        if (mContext.getResources().getString(R.string.custom_ekeytunr_enable).equals(SP_XPH5)) {
            return true;
        }
        return false;
    }

    public boolean IsBMWVer() {
        if (mContext.getResources().getString(R.string.custom_num_show).equals("BMW")) {
            return true;
        }
        return false;
    }

    public boolean IsHaveFcam() {
        if (mContext.getResources().getString(R.string.custom_have_front_cam_set).equals(SP_XPH5)) {
            return true;
        }
        return false;
    }

    public boolean IsXT5() {
        if (mContext.getResources().getString(R.string.custom_num_show).equals("XT5")) {
            return true;
        }
        return false;
    }

    public static boolean IsXPH5_HZ() {
        if (mContext.getResources().getString(R.string.custom_xp5_hou_zhuang).equals(SP_XPH5)) {
            return true;
        }
        return false;
    }

    public static boolean IsShowBrughtness() {
        if (mContext.getResources().getString(R.string.custom_show_brightness).equals(SP_XPH5)) {
            return true;
        }
        return false;
    }

    public boolean IsHaveBt() {
        if (FtSet.IsIconExist(118) == 1) {
            return true;
        }
        return false;
    }

    public boolean IsXXGD() {
        if (mContext.getResources().getString(R.string.custom_num_show).equals("XXGD")) {
            return true;
        }
        return false;
    }

    public Boolean IsGLSXLP() {
        if (TsFile.fileIsExists("system/glsx.ini")) {
            return true;
        }
        return false;
    }

    public static boolean IsGLlastmem() {
        return FtSet.GetLastMemory() == 0;
    }

    public static Boolean IsGLSXVer() {
        String str = mContext.getResources().getString(R.string.custom_num_show);
        if (str.equals("GLSX") || str.equals("GLQW") || str.equals("TSYC") || str.equals("TSXH") || str.equals("GLHG") || str.equals("GLAX")) {
            return true;
        }
        return false;
    }

    public Boolean IsSZTB() {
        if (mContext.getResources().getString(R.string.custom_num_show).equals("SZTB")) {
            return true;
        }
        return false;
    }

    public static boolean IsVSUI() {
        if (GetScreenType() == 3 || GetScreenType() == 6) {
            return true;
        }
        return false;
    }

    public static boolean IsMkz() {
        if (CanJni.GetCanType() == 88 && (CanJni.GetSubType() == 4 || CanJni.GetSubType() == 5)) {
            return true;
        }
        return false;
    }

    public static boolean IsBmwX1() {
        if (mContext.getResources().getString(R.string.custom_num_show).equals("BMWX")) {
            return true;
        }
        return false;
    }

    public void DeleteTempFile() {
        String[] DeleteValidStr = {"txz", "AmapAutoLog", "Baidu", "DCIM", "Download", "kwmusiccar", "mtklog", "TsCrash", "UsbCamera"};
        for (int i = 0; i < DeleteValidStr.length; i++) {
            TsFile.deleteFile(new File("/mnt/sdcard/" + DeleteValidStr[i]));
            new File("/mnt/sdcard/" + DeleteValidStr[i]).delete();
        }
        if (IsFlkj()) {
            TsFile.deleteFile(new File("/mnt/sdcard/amapauto8"));
            new File("/mnt/sdcard/amapauto8").delete();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean CheckMcuUpdateFile() {
        String[] mCtouchPath = GetMountedStorage();
        if (mCtouchPath == null) {
            return false;
        }
        for (int i = 0; i < mCtouchPath.length; i++) {
            if (TsFile.fileIsExists(String.valueOf(mCtouchPath[i]) + "/" + "ts70.bin")) {
                WinShow.GotoWin(18, 0);
                return true;
            }
        }
        return false;
    }

    public boolean bIsLowMemory() {
        if (!IsFxCarplay() && MainUI.GetInstance().GetToalMemory() < 1024) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void CopyTsManual() {
        if (TsFile.fileIsExists("system/tsoem/TsManual.pdf")) {
            TsFile.copyFile("system/tsoem/TsManual.pdf", "/mnt/sdcard/TsManual.pdf");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean bCheckFist() {
        if (mContext != null) {
            String path = mContext.getFilesDir().getAbsolutePath();
            if (!TsFile.fileIsExists(String.valueOf(path) + "/ceshi.txt")) {
                DeleteTempFile();
                CopyTsManual();
                DeleteFtSetFile();
                int ret = FtSet.LoadFromSd();
                if (ret > 0) {
                    if (ret <= 2) {
                        Toast.makeText(mContext, mContext.getResources().getString(R.string.str_fs_sd_import_reset), 0).show();
                    } else {
                        UpdateSysLanguage();
                        GetInstance().Inint(1);
                        Toast.makeText(mContext, mContext.getResources().getString(R.string.str_fs_usb_import_reset), 0).show();
                    }
                }
                if (!GetInstance().IsTestMode()) {
                    ShellUtils.execCommand(new String[]{"rm -rf /data/nvram/media"}, true);
                }
                try {
                    TsFile.writeFileSdcardFile(String.valueOf(path) + "/ceshi.txt", "ceshi");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (IsTwcjw() && !mContext.getResources().getConfiguration().locale.getDisplayName().equals("中文 (台湾)")) {
                    LanguageUtils.updateLanguage(Locale.TAIWAN);
                }
                if (IsFxCarplay()) {
                    FsBaseActivity.setUsbMode(1);
                    FtSet.Save(0);
                }
                if (IsFlkj() && FtSet.GetTouchBall() == 0) {
                    FtSet.SetTouchBall(1);
                    FtSet.Save(0);
                }
                if (IsGLSXVer().booleanValue() && !TsFile.fileIsExists("system/3561hd.upd")) {
                    FtSet.SetNoiseDown(1);
                    FtSet.Save(0);
                    try {
                        ((WallpaperManager) mContext.getSystemService("wallpaper")).setResource(R.drawable.glsxwallpaper);
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return true;
    }

    public boolean IsLocal() {
        if (mContext.getResources().getString(R.string.custom_num_show).equals("LOCAL")) {
            return true;
        }
        return false;
    }

    public static void SetTouchBall(boolean bShow) {
        if (bShow) {
            SystemProperties.set("persist.mytouch.enable", SP_XPH5);
        } else {
            SystemProperties.set("persist.mytouch.enable", "0");
        }
    }

    public static boolean isZh() {
        if (mContext.getResources().getConfiguration().locale.getLanguage().endsWith("zh")) {
            return true;
        }
        return false;
    }

    public boolean bIsLowTxz() {
        if (TsFile.fileIsExists("system/vendor/operator/app/TXZlow")) {
            return true;
        }
        return false;
    }

    public static boolean isrTwZh() {
        if (mContext.getResources().getConfiguration().locale.getLanguage().endsWith("zh-rTW")) {
            return true;
        }
        return false;
    }

    public static boolean isrSimpZh() {
        if (mContext.getResources().getConfiguration().locale.getLanguage().endsWith("zh-rCH")) {
            return true;
        }
        return false;
    }

    public static int GetScreenType() {
        String str = mContext.getResources().getString(R.string.custom_screen_show);
        if (str.equals("1280x480")) {
            return 5;
        }
        if (str.equals("768x1024")) {
            return 3;
        }
        if (str.equals("800x1280")) {
            return 6;
        }
        if (str.equals("1366x768")) {
            return 7;
        }
        if (str.equals("800x600")) {
            return 8;
        }
        if (str.equals("1920x720")) {
            return 9;
        }
        return 1;
    }

    public static Date GetApkLastModifiedTime(String PackName) {
        List<PackageInfo> packs = mContext.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if (p.versionName != null) {
                p.applicationInfo.loadLabel(mContext.getPackageManager());
                if (p.packageName.equals(PackName)) {
                    return new Date(new File(p.applicationInfo.publicSourceDir).lastModified());
                }
            }
        }
        return null;
    }

    public boolean IsHaveService(String ServiceName) {
        if (mContext != null) {
            for (ActivityManager.RunningServiceInfo service : ((ActivityManager) mContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE)) {
                if (ServiceName.equals(service.service.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean IsHaveApk(String PackName) {
        if (this.Mypacks == null) {
            this.Mypacks = mContext.getPackageManager().getInstalledPackages(0);
        }
        for (int i = 0; i < this.Mypacks.size(); i++) {
            PackageInfo p = this.Mypacks.get(i);
            if (p.versionName != null && p.packageName.equals(PackName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsAvalidPackName(String str) {
        String[] inValidStr = {"com.ts.", "com.mediatek.", "com.autochips.", "com.tencent.", "com.antutu.", "com.example.mywidget", "com.anwensoft.cardvr", "com.mxtech.videoplayer.pro", "com.pachira.tsp.assist", "com.txznet.music", "com.baidu.appsearch", "com.estrongs.android.pop", "com.baidu.browser.apps", "com.baidu.input", "com.kugou", "net.easyconn", "com.nd.assistance", "com.txznet.webchat", "com.android.calculator", "com.android.calendar", "com.android.deskclock", "com.android.providers.downloads", "com.android.settings", "com.android.browser", "com.android.chrome", "com.android.vending", "com.android.soundrecorder", "com.android.contacts", "com.android.mms", "com.android.gallery", "net.qihoo.launcher.widget.clockweather", "com.mapgoo.diruite", "com.glsx.ddbox", "net.mapgoo.m10010", "com.google.android.apps.plus", "com.google.android.gm", "com.google.android.gms", "com.google.android.googlequicksearchbox", "com.android.dialer", "cn.manstep.phonemirror", "com.edog.car", "com.example.specision", "es.voghdev.pdfviewpager", "RepliGo Reader", "cn.kuwo.kwmusiccar", Constant.PACKAGE_NAME, "com.ludashi.benchmark", MainUI.KAIYI_PNAME, "com.synmoon.usbcamera", "com.android.soundrecorder", "com.cerience.reader.app", "com.ex.dabplayer.pad", "com.iflytek.inputmethod", "com.forfan.wenavi", "com.cantho.roadtech", "com.google.android.youtube"};
        int i = 0;
        while (i < inValidStr.length) {
            if (mContext.getPackageManager().getLaunchIntentForPackage(str) == null) {
                return false;
            }
            if (!str.contains(inValidStr[i])) {
                i++;
            } else if ((str.contains("map") || str.contains("navi")) && !str.equals("net.mapgoo.m10010")) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public void openApplication(Context context, String packageName, String type, String info) {
    }

    public void openApplication(Context context, String packageName) {
        PackageInfo pi;
        try {
            pi = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            pi = null;
            e.printStackTrace();
        }
        if (pi != null) {
            Intent resolveIntent = new Intent("android.intent.action.MAIN", (Uri) null);
            resolveIntent.setPackage(pi.packageName);
            ResolveInfo ri = context.getPackageManager().queryIntentActivities(resolveIntent, 0).iterator().next();
            if (ri != null) {
                String packageName2 = ri.activityInfo.packageName;
                String className = ri.activityInfo.name;
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.setFlags(268435456);
                intent.setComponent(new ComponentName(packageName2, className));
                context.startActivity(intent);
            }
        }
    }

    public static boolean IsAvalidAPPName(String str) {
        if (str.equals("Android 系统")) {
            return false;
        }
        return true;
    }

    public static int getNumCores() {
        if (nCoreNum != 0) {
            return nCoreNum;
        }
        try {
            File[] files = new File("/sys/devices/system/cpu/").listFiles(new FileFilter() {
                public boolean accept(File pathname) {
                    if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                        return true;
                    }
                    return false;
                }
            });
            Log.d(TAG, "CPU Count: " + files.length);
            nCoreNum = files.length;
            return files.length;
        } catch (Exception e) {
            Log.d(TAG, "CPU Count: Failed.");
            e.printStackTrace();
            return 1;
        }
    }

    public static String GetHMIVersion() {
        String StrVal;
        String str = SystemProperties.get("ro.forfan.modem");
        String StrVal2 = "." + (getNumCores() / 4) + ".";
        if (Iop.DspSupport() == 1) {
            StrVal = String.valueOf(StrVal2) + "D.";
        } else {
            StrVal = String.valueOf(StrVal2) + "C.";
        }
        if (str.equalsIgnoreCase("modem_ch")) {
            if (!IsGLSXVer().booleanValue()) {
                StrVal = String.valueOf(StrVal) + "F.";
            } else if (TsFile.fileIsExists("system/3561hd.upd")) {
                StrVal = String.valueOf(StrVal) + "HD.";
            } else {
                StrVal = String.valueOf(StrVal) + "F.";
            }
        } else if (str.equalsIgnoreCase("modem_fo")) {
            StrVal = String.valueOf(StrVal) + "G.";
        }
        if (mContext != null) {
            StrVal = String.valueOf(StrVal) + mContext.getResources().getString(R.string.custom_ver_);
        }
        return String.valueOf(StrVal) + MainVerSion.HMIVER;
    }

    public static String GetMediaVersion() {
        if (Iop.DspSupport() == 1 && Iop.GetDspVer(Dspversiono) == 1) {
            return String.valueOf(MainVerSion.MMP_VERSION) + "(" + new String(Dspversiono) + ")";
        }
        return MainVerSion.MMP_VERSION;
    }

    public void FtDestroy() {
        this.nRet = -1;
    }

    public int FtSetInint() {
        if (this.nRet == -1) {
            this.nRet = FtSet.Init();
        }
        return this.nRet;
    }

    /* access modifiers changed from: package-private */
    public void FirbidenWinShow(boolean bShow) {
        if (bShow && !this.bShowold) {
            this.bShowold = true;
            MainVolume.GetInstance().InintForbidenScreen();
        } else if (!bShow && this.bShowold) {
            this.bShowold = false;
            if (MainUI.IsCameraMode() == 0) {
                MainVolume.GetInstance().Destroy();
            }
        }
    }

    public int DealTask(int nParat) {
        if (FtSet.IsIconExist(129) == 1) {
            if (!this.bWrcInint && BtExe.getBtInstance().isBtEnabled()) {
                Wrc.GetInstance().Inint(mContext);
                Wrc.GetInstance().startScan();
                this.bWrcInint = true;
            }
            if (this.bWrcInint) {
                this.nDelayConnectWrc++;
                if (this.nDelayConnectWrc % MediaDef.PROGRESS_MAX == 0) {
                    Log.i(TAG, "Wrc.GetInstance().isConnectWrc()==" + Wrc.GetInstance().isConnectWrc());
                    if (!Wrc.GetInstance().isConnectWrc()) {
                        Wrc.GetInstance().startScan();
                    }
                }
            }
        }
        this.nTaskTime++;
        if (this.nTaskTime % 30 == 0) {
            if (IsXuhuiDmax() && MainUI.GetInstance().GetMcuState() == 3 && !WinShow.getTopPackName().equals("com.ts.tsclock")) {
                openApplication(mContext, "com.ts.tsclock");
            }
            if (IsGLSXVer().booleanValue()) {
                if (WinShow.getTopPackName().equals("com.glsx.launcher") || MainUI.IsCameraMode() == 1) {
                    if (this.nBtnShow == -1 || this.nBtnShow == 1) {
                        TxzReg.GetInstance().SetRecordBtnShow(false);
                        this.nBtnShow = 0;
                    }
                } else if ((FtSet.GetSpeechMode() == 0 || FtSet.GetSpeechMode() == 2) && TxzReg.GetInstance().bInintOK && (this.nBtnShow == -1 || this.nBtnShow == 0)) {
                    TxzReg.GetInstance().SetRecordBtnShow(true);
                    this.nBtnShow = 1;
                }
            }
            if (IsvideoForbiden() && Mcu.BklisOn() == 1 && MainUI.IsCameraMode() == 0) {
                String Toppckname = WinShow.getTopPackName();
                Log.i(TAG, "Toppckname=" + Toppckname);
                Log.i(TAG, "Toppckname=" + WinShow.getTopActivityName());
                if (!bIsForbidenWhiteList(Toppckname)) {
                    FirbidenWinShow(true);
                } else {
                    FirbidenWinShow(false);
                }
            } else {
                FirbidenWinShow(false);
            }
            this.nTaskTime = 0;
        }
        if (this.toastString != null) {
            Toast.makeText(mContext, this.toastString, 1).show();
            this.toastString = null;
        }
        if (Mcu.BklisOn() != this.nturnOn) {
            this.nturnOn = Mcu.BklisOn();
            if (this.nturnOn == 1) {
                if (IsXPH5()) {
                    MainAlterwin.GetInstance().HidenPoweroffWin();
                } else {
                    MainAlterwin.GetInstance().HidenPoweroffWin();
                }
                SendIntent("intent.action.mapgoo.screenon");
                if (IsFlkj()) {
                    Mcu.SetMutestate((byte) 0);
                }
            } else {
                if (IsXPH5()) {
                    MainAlterwin.GetInstance().ShowPowerOffWin();
                } else {
                    MainAlterwin.GetInstance().ShowPowerOffWin(-16777216);
                }
                SendIntent("intent.action.mapgoo.screenoff");
                if (IsFlkj()) {
                    Mcu.SetMutestate((byte) 1);
                }
            }
        }
        MainLight.GetInstance().DealTask();
        return 1;
    }

    public boolean IsvideoForbiden() {
        if (this.forbiddenSpeed == -1 && mContext != null) {
            try {
                this.forbiddenSpeed = Integer.parseInt(mContext.getResources().getString(R.string.custom_forbidden_speed));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if (this.forbiddenSpeed <= 0) {
            if (StSet.GetDriveVideo() == 1 && Mcu.GetBrake() == 0) {
                return true;
            }
            return false;
        } else if (StSet.GetDriveVideo() != 1) {
            return false;
        } else {
            if (Mcu.GetBrake() == 1) {
                return false;
            }
            if (MainUI.GetInstance().GpsSpeed < ((float) this.forbiddenSpeed)) {
                return false;
            }
            return true;
        }
    }

    public boolean IsBlackList(String Str) {
        List<String> pMybList = AppListUtil.getInstance().updateAppList();
        if (pMybList.size() <= 0 || !pMybList.contains(Str)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean bIsForbidenWhiteList(String Str) {
        if (IsQOROS() || Str.contains("map") || Str.contains("navi")) {
            return true;
        }
        String[] ValidStr = {"com.glsx.ddbox", "com.glsx.launcher", "com.android.launcher", "com.android.launcher3", "com.ts.MainUI", "com.android.settings", "com.ts.dvdplayer", "com.android.calculator", "com.android.calendar", "com.ex.dabplayer.pad", "com.android.soundrecorder", "com.android.dialer", "com.example.specision", "es.voghdev.pdfviewpager", "net.easyconn", "com.cerience.reader.app", "android", "com.android.wallpaper.livepicker", "com.android.packageinstaller", "com.finedigital.a10.homescreen", "com.waze", "com.ts.tsclock", "com.google.android.googlequicksearchbox", "iot.chinamobile.messagebox", "com.ximalaya.ting.android.car", "net.easyconn", "cn.kuwo.kwmusiccar"};
        int i = 0;
        while (i < ValidStr.length) {
            if (!Str.equals(ValidStr[i])) {
                i++;
            } else if (!Str.equals("com.ts.dvdplayer")) {
                return true;
            } else {
                if (Evc.GetInstance().GetWorkMode() != 3 && Evc.GetInstance().GetWorkMode() != 2) {
                    return true;
                }
                String nameString = WinShow.getTopActivityName();
                if (nameString != null) {
                    if (nameString.equals("com.ts.dvdplayer.DvdSdBoxActivity") || nameString.equals("com.ts.dvdplayer.SDActivity") || nameString.equals("com.ts.dvdplayer.dvd.DVDAudioActivity")) {
                        return true;
                    }
                    if (IsQOROS() && nameString.equals("com.ts.dvdplayer.USBActivity")) {
                        return true;
                    }
                }
                return false;
            }
        }
        byte[] byteNavipath = new byte[128];
        StSet.GetNaviPack(byteNavipath);
        String NaviPath = CanIF.byte2String(byteNavipath);
        if (NaviPath != null && NaviPath.equals(Str)) {
            return true;
        }
        if (mContext.getResources().getString(R.string.custom_use_video_forbiden).equals("0")) {
            return false;
        }
        if (IsBlackList(Str)) {
            return false;
        }
        if (Str.equals("com.android.sdvdplayer")) {
            return false;
        }
        return true;
    }

    public String GetMenSize() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        if (this.mActivityManager == null) {
            this.mActivityManager = (ActivityManager) mContext.getSystemService("activity");
        }
        this.mActivityManager.getMemoryInfo(memoryInfo);
        long nSize = (memoryInfo.totalMem / 1024) / 1024;
        if (nSize < 1024) {
            if (nSize < 512) {
                return "512MB";
            }
            if (nSize < 768) {
                return "768MB";
            }
            return "1G";
        } else if (nSize <= 1024 || nSize >= 2048) {
            if (nSize > 2048 && nSize < 3072) {
                return "3G";
            }
            if (nSize <= 3072 || nSize >= 4096) {
                return "8G";
            }
            return "4G";
        } else if (nSize < 1539) {
            return "1.5G";
        } else {
            return "2G";
        }
    }

    /* access modifiers changed from: package-private */
    public long readSDCard() {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return 0;
        }
        StatFs sf = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long blockSize = (long) sf.getBlockSize();
        long blockCount = (long) sf.getBlockCount();
        long availCount = (long) sf.getAvailableBlocks();
        Log.d(TAG, "block大小:" + blockSize + ",block数目:" + blockCount + ",总大小:" + ((blockSize * blockCount) / 1024) + "KB");
        Log.d(TAG, "可用的block数目：:" + availCount + ",剩余空间:" + ((availCount * blockSize) / 1024) + "KB");
        return blockSize * blockCount;
    }

    public String GetEmmcSize() {
        long tSize = ((readSDCard() + readSystem()) / 1024) / 1024;
        if (tSize <= 16384) {
            return "16GB";
        }
        if (tSize > 16384 && tSize < 32768) {
            return "32GB";
        }
        if (tSize <= 32768 || tSize >= 65536) {
            return "128GB";
        }
        return "64GB";
    }

    /* access modifiers changed from: package-private */
    public long readSystem() {
        StatFs sf = new StatFs(Environment.getRootDirectory().getPath());
        long blockSize = (long) sf.getBlockSize();
        long blockCount = (long) sf.getBlockCount();
        long availCount = (long) sf.getAvailableBlocks();
        Log.d(TAG, "block大小:" + blockSize + ",block数目:" + blockCount + ",总大小:" + ((blockSize * blockCount) / 1024) + "KB");
        Log.d(TAG, "可用的block数目：:" + availCount + ",可用大小:" + ((availCount * blockSize) / 1024) + "KB");
        return blockSize * blockCount;
    }

    public boolean IsHaveCamSignal() {
        return true;
    }

    public void CheckCamSignal(boolean bCamera) {
    }

    public void SetVideoChannel(int nChannel) {
        if (!IsFlkj()) {
            Iop.SetVideoChannel(nChannel);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isNeedPassword(int setOption, Context context) {
        try {
            return ((Boolean) Class.forName("com.ts.main.common.ExtraPassword").getDeclaredMethod("isNeedPassword", new Class[]{Integer.class, Context.class}).invoke((Object) null, new Object[]{Integer.valueOf(setOption), context})).booleanValue();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (IllegalArgumentException e4) {
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
        }
        return false;
    }

    public void showPasswordDialog(Context context, int setOption, DealPasswordResult pswDeal) {
        try {
            Class.forName("com.ts.main.common.ExtraPassword").getDeclaredMethod("showPasswordDialog", new Class[]{Context.class, Integer.TYPE, DealPasswordResult.class}).invoke((Object) null, new Object[]{context, Integer.valueOf(setOption), pswDeal});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (IllegalArgumentException e4) {
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
        }
    }

    public boolean DealExtraPassword(String val, Context context) {
        try {
            return ((Boolean) Class.forName("com.ts.main.common.ExtraPassword").getDeclaredMethod("DealExtraPassword", new Class[]{String.class, Context.class}).invoke((Object) null, new Object[]{val, context})).booleanValue();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (IllegalArgumentException e4) {
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
        }
        return false;
    }

    private void startCanDataService(Context context) {
        try {
            Class<?> cls = Class.forName("com.ts.can.CanDataLogService");
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(context, cls));
            context.startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DealFactorySec(String val, Context context) {
        if (val.equals(mContext.getResources().getString(R.string.custom_mcuupdate_num))) {
            WinShow.GotoWin(18, 0);
        } else {
            if (val.equals(mContext.getResources().getString(R.string.custom_csudy_num))) {
                WinShow.GotoWin(19, 0);
            } else {
                if (val.equals(mContext.getResources().getString(R.string.custom_setvcom_num))) {
                    new MainVcomWithAcddDialog().createDlg(context, FtSet.GetLCDvcom(), FtSet.GetLCDavdd());
                } else {
                    if (val.equals(mContext.getResources().getString(R.string.custom_getid_num))) {
                        String sMcuid = GetSerid();
                        new MainShowDialog().createDlg(context, sMcuid, "http://xfapp.forfan.com.cn/xfapp/product.php?device=" + sMcuid + "&sn=" + md5(String.valueOf(sMcuid) + "forfan_product"));
                    } else {
                        if (val.equals(mContext.getResources().getString(R.string.custom_getccid_num))) {
                            String str = ((TelephonyManager) mContext.getSystemService("phone")).getSimSerialNumber();
                            if (str == null || str.length() <= 10) {
                                Toast.makeText(mContext, "SIM卡读取失败", 0).show();
                            } else {
                                new MainShowDialog().createDlg(context, str.toUpperCase(), str.toUpperCase());
                            }
                        } else {
                            if (val.equals(mContext.getResources().getString(R.string.custom_es_file_explorer))) {
                                Intent intent = new Intent();
                                intent.setComponent(new ComponentName("com.estrongs.android.pop", "com.estrongs.android.pop.view.FileExplorerActivity"));
                                intent.addFlags(268435456);
                                mContext.startActivity(intent);
                            } else {
                                if (val.equals(mContext.getResources().getString(R.string.custom_powerreset_num))) {
                                    Mcu.SendXKey(20);
                                } else {
                                    if (val.equals(mContext.getResources().getString(R.string.custom_reset_naviwhitelist))) {
                                        Evc.GetInstance().DelNaviWhileList("ResetDefault");
                                        Toast.makeText(mContext, "GisWhiteList ResetDefault", 0).show();
                                    } else {
                                        if (val.equals(mContext.getResources().getString(R.string.custom_toauth_num))) {
                                            WinShow.GotoWin(16, 0);
                                        } else {
                                            if (val.equals(mContext.getResources().getString(R.string.custom_radom_btname))) {
                                                FtSet.SetBtId(TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT);
                                                FtSet.Save(0);
                                                Toast.makeText(mContext, "蓝牙名称已经修改，重启生效", 0).show();
                                            } else {
                                                if (val.equals(mContext.getResources().getString(R.string.custom_getproid_num))) {
                                                    String str2 = GetProid();
                                                    if (str2 != null) {
                                                        new MainShowDialog().createDlg(context, str2, str2);
                                                    }
                                                } else {
                                                    if (val.equals(mContext.getResources().getString(R.string.custom_can_data_num))) {
                                                        startCanDataService(mContext);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (val.equals("12")) {
            MainSerialNumerDialog mainSerialNumerDialog = new MainSerialNumerDialog();
            if (IsRxfKoren()) {
                mainSerialNumerDialog.createDlg(context, GetInstance().GetProid(), RLF_KOREA_MOUDLENAME);
            } else {
                mainSerialNumerDialog.createDlg(context, GetInstance().GetProid(), "");
            }
        }
        if (!val.equals(mContext.getResources().getString(R.string.custom_bootlogo_num))) {
            if (val.equals(mContext.getResources().getString(R.string.custom_fatctory_num))) {
                WinShow.GotoWin(15, 0);
            } else {
                if (val.equals(mContext.getResources().getString(R.string.custom_filecopy_num))) {
                    new Thread() {
                        public void run() {
                            try {
                                MainSet.this.CopyFile(MainSet.TS_DEMO_FILE);
                            } catch (Exception e) {
                                Log.e("Exception when sendPointerSync", e.toString());
                            }
                        }
                    }.start();
                } else {
                    if (!val.equals(mContext.getResources().getString(R.string.custom_screenshot_num))) {
                        if (val.equals(mContext.getResources().getString(R.string.custom_radio_reset_num))) {
                            Radio.TuneTask(8);
                        } else {
                            if (!val.equals(mContext.getResources().getString(R.string.custom_destroy_id_num))) {
                                if (val.equals(mContext.getResources().getString(R.string.custom_reboot_id_num))) {
                                    SystemReboot();
                                } else {
                                    if (val.equals(mContext.getResources().getString(R.string.custom_testmode_id_num))) {
                                        WinShow.show("com.ts.MainUI", "com.ts.main.auth.FactoryMainActivity");
                                    } else {
                                        if (val.equals(mContext.getResources().getString(R.string.custom_destroy_id_root_num))) {
                                            Toast.makeText(mContext, "注册码清除", 0).show();
                                            AuthServer.GetInstance().DestroyID();
                                        } else {
                                            if (val.equals(mContext.getResources().getString(R.string.custom_install_id_num))) {
                                                new Thread() {
                                                    public void run() {
                                                        try {
                                                            MainSet.this.CopyandInstallAPK(true);
                                                        } catch (Exception e) {
                                                            Log.e("Exception when sendPointerSync", e.toString());
                                                        }
                                                    }
                                                }.start();
                                            } else {
                                                if (val.equals(mContext.getResources().getString(R.string.custom_install2_id_num))) {
                                                    new Thread() {
                                                        public void run() {
                                                            try {
                                                                MainSet.this.CopyandInstallAPK(false);
                                                            } catch (Exception e) {
                                                                Log.e("Exception when sendPointerSync", e.toString());
                                                            }
                                                        }
                                                    }.start();
                                                } else {
                                                    if (!val.equals(mContext.getResources().getString(R.string.custom_vin_reset))) {
                                                        if (val.equals(mContext.getResources().getString(R.string.custom_vin_input))) {
                                                            if (IsQOROS()) {
                                                                WinShow.show("com.ts.MainUI", "com.ts.gz.activity.VinActivity");
                                                            }
                                                        } else if (val.equals("7788")) {
                                                            WinShow.show("com.mediatek.engineermode", "com.mediatek.engineermode.EngineerMode");
                                                        } else if (val.equals("7789")) {
                                                            openApplication(mContext, "com.android.settings");
                                                        } else if (val.equals("1111")) {
                                                            Intent intent2 = new Intent();
                                                            intent2.setPackage("com.ts.phonestate");
                                                            intent2.setAction("com.ts.phonestate.PhoneStateService");
                                                            mContext.startService(intent2);
                                                        } else {
                                                            if (val.equals(mContext.getResources().getString(R.string.custom_apk_install_pwd)) && mContext.getResources().getString(R.string.custom_forbiden_install).equals(SP_XPH5)) {
                                                                SystemProperties.set("forfan.notallow.install", "0");
                                                                Toast.makeText(mContext, "允许应用安装", 0).show();
                                                            }
                                                        }
                                                    } else if (IsQOROS()) {
                                                        byte[] naviname2 = new byte[32];
                                                        for (int i = 0; i < naviname2.length; i++) {
                                                            naviname2[i] = 0;
                                                        }
                                                        FtSet.SetProId(naviname2, 32);
                                                        FtSet.Save(0);
                                                        Toast.makeText(mContext, "VIN码已清除", 0).show();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else if (!GetInstance().IsMathToMcu()) {
                                Toast.makeText(mContext, "注册码清除", 0).show();
                                AuthServer.GetInstance().DestroyID();
                            }
                        }
                    } else if (MainUI.bIsScreenMode) {
                        Toast.makeText(mContext, "关闭截屏模式", 0).show();
                        MainUI.bIsScreenMode = false;
                    } else {
                        Toast.makeText(mContext, "双击屏幕截屏", 0).show();
                        MainUI.bIsScreenMode = true;
                    }
                }
            }
        } else if (FtSet.GetOptionSW() == 1) {
            WinShow.GotoWin(17, 0);
        }
        if (val.equals(mContext.getResources().getString(R.string.custom_appinfo_id_num))) {
            WinShow.GotoWin(24, 0);
        }
        if (val.equals(mContext.getResources().getString(R.string.custom_update_apk))) {
            Mcu.SendXKey(21);
            GetInstance().openApplication(mContext, "com.ts.tscanupdate");
            return;
        }
        if (val.equals(mContext.getResources().getString(R.string.custom_mounkey_num))) {
            tool.GetInstance().MounKeyTest();
            return;
        }
        if (val.equals(mContext.getResources().getString(R.string.custom_check_dsp_secret)) && Iop.DspSupport() == 1 && Iop.GetDspVer(this.Dspversion) == 1) {
            new AlertDialog.Builder(context).setTitle(new String(this.Dspversion)).show();
        }
    }

    /* access modifiers changed from: package-private */
    public void ScanStorage(String path) {
        if (mContext != null) {
            String path2 = Environment.getExternalStorageDirectory().getPath();
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(Uri.fromFile(new File(path)));
            mContext.sendBroadcast(intent);
        }
    }

    /* access modifiers changed from: package-private */
    public void SystemReboot() {
        Mcu.SendXKey(21);
        Reboot();
    }

    public void SystemClear() {
        Mcu.SendXKey(21);
        mContext.sendBroadcast(new Intent("android.intent.action.MASTER_CLEAR"));
    }

    public void Reboot() {
        tool.GetInstance().DealSu("reboot");
    }

    /* access modifiers changed from: package-private */
    public void CopyFile(String StrPath) {
        String[] strSDMountedPath = this.EnvATC.getStorageMountedPaths();
        Log.i(TAG, "strSDMountedPath.length ==" + strSDMountedPath.length);
        GetInstance().toastString = "开始拷贝";
        for (int i = 0; i < strSDMountedPath.length; i++) {
            Log.i(TAG, "strSDMountedPath[i] ==" + strSDMountedPath[i]);
            if (!strSDMountedPath[i].contains("emulated") && !strSDMountedPath[i].contains("ctfs") && TsFile.fileIsExists(String.valueOf(strSDMountedPath[i]) + StrPath)) {
                TsFile.copyFolder(String.valueOf(strSDMountedPath[i]) + StrPath, TS_DEMO_PATH);
            }
        }
        GetInstance().toastString = "拷贝完成";
    }

    /* access modifiers changed from: package-private */
    public void CopyandInstallAPK(boolean bcopy) {
        File myfile;
        String installPath = "";
        String[] strSDMountedPath = this.EnvATC.getStorageMountedPaths();
        Log.i(TAG, "strSDMountedPath.length ==" + strSDMountedPath.length);
        for (int i = 0; i < strSDMountedPath.length; i++) {
            Log.i(TAG, "strSDMountedPath[i] ==" + strSDMountedPath[i]);
            if (!strSDMountedPath[i].contains("emulated") && !strSDMountedPath[i].contains("ctfs") && TsFile.fileIsExists(String.valueOf(strSDMountedPath[i]) + "/tsoem")) {
                installPath = String.valueOf(strSDMountedPath[i]) + "/tsoem";
                if (bcopy) {
                    TsFile.deleteFile(new File(TS_OEM_PATH));
                    GetInstance().toastString = "开始拷贝";
                    TsFile.copyFolder(String.valueOf(strSDMountedPath[i]) + "/tsoem", TS_OEM_PATH);
                    GetInstance().toastString = "拷贝完成";
                }
            }
        }
        if (bcopy) {
            myfile = new File(TS_OEM_PATH);
        } else {
            myfile = new File(installPath);
        }
        if (myfile.isDirectory()) {
            GetInstance().toastString = "开始安装";
            File[] lfileFiles = myfile.listFiles();
            for (int i2 = 0; i2 < lfileFiles.length; i2++) {
                Log.i(TAG, " lfileFiles[i].pathSeparator;" + lfileFiles[i2].getPath());
                GetInstance().toastString = "安装:" + lfileFiles[i2].getName();
                String[] commands = {"pm install -r "};
                commands[0] = String.valueOf(commands[0]) + lfileFiles[i2].getPath();
                ShellUtils.CommandResult result = ShellUtils.execCommand(commands, true);
                if (result.successMsg != null) {
                    Log.v(TAG, result.successMsg);
                }
                if (result.errorMsg != null) {
                    Log.v(TAG, result.errorMsg);
                }
            }
            GetInstance().toastString = "安装完成";
            return;
        }
        GetInstance().toastString = "无可安装文件";
    }

    public boolean StartKw() {
        if (!IsHaveApk("cn.kuwo.kwmusiccar")) {
            return false;
        }
        openApplication(mContext, "cn.kuwo.kwmusiccar");
        return true;
    }

    public boolean StartXMLY() {
        if (!IsHaveApk("com.ximalaya.ting.android.car")) {
            return false;
        }
        openApplication(mContext, "com.ximalaya.ting.android.car");
        return true;
    }

    public void StartMboilApp() {
        Log.i(TAG, "StartMboilApp() ==");
        WinShow.show("com.mgo.gad", "com.mgo.gad.HolderActivity");
    }

    public String tranalateIntoString(int freq) {
        String text = new StringBuilder(String.valueOf(freq)).toString();
        StringBuilder sb = new StringBuilder(text);
        if (text.length() < 3) {
            return "";
        }
        sb.insert(text.length() - 2, ".");
        return sb.toString();
    }

    public void SetToFDD() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.setdefaultPhone");
        intent.setPackage("com.ts.forfan");
        mContext.startService(intent);
    }

    /* access modifiers changed from: package-private */
    public void CheckMG() {
        Intent intent = new Intent("intent.action.mapgoo.simtool.info");
        intent.putExtra("getCache", 0);
        mContext.sendBroadcast(intent);
    }

    public void CheckSimOwner() {
        Log.i(TAG, "CheckSimOwner() ==");
        CheckMG();
    }

    public int GetXYRange() {
        int mScrW = SystemProperties.getInt("ro.forfan.hardware.width", 0);
        int mScrH = SystemProperties.getInt("ro.forfan.hardware.height", 0);
        Log.i(TAG, "mScrW==" + mScrW);
        Log.i(TAG, "mScrH==" + mScrH);
        if (mScrW == 800 && mScrH == 480) {
            mScrW = 1024;
            mScrH = CanCameraUI.BTN_GOLF_WC_MODE1;
        }
        if (mScrW == 800 && mScrH == 600) {
            mScrW = 1024;
            mScrH = CanCameraUI.BTN_GOLF_WC_MODE1;
        } else if (mScrW == 800 && mScrH == 1280 && IsFlkj()) {
            mScrW = 1280;
            mScrH = 800;
        }
        int nTouchX = SystemProperties.getInt("ro.forfan.touchwidth", 0);
        int nTouchY = SystemProperties.getInt("ro.forfan.touchheight", 0);
        Log.i(TAG, "nTouchX==" + nTouchX);
        Log.i(TAG, "nTouchY==" + nTouchY);
        if (nTouchX > mScrW || nTouchY > mScrH || (nTouchX == 0 && nTouchY == 0)) {
            return (65536 * mScrW) + mScrH;
        }
        return 0;
    }

    private static long getAvailMemory(Context context) {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(mi);
        return mi.availMem;
    }

    public static String md5(String str) {
        try {
            return bytes2hex02(MessageDigest.getInstance("md5").digest(str.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String bytes2hex02(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            String tmp = Integer.toHexString(b & 255);
            if (tmp.length() == 1) {
                tmp = "0" + tmp;
            }
            sb.append(tmp);
        }
        return sb.toString();
    }

    private static void DeleteFtSetFile() {
        File file2 = new File("/mnt/sdcard/Iconfig/Iconfig.ini");
        if (file2.exists()) {
            file2.delete();
        }
    }

    public static void UpdateSysLanguage() {
        switch (FtSet.GetLangDef()) {
            case 0:
                LanguageUtils.updateLanguage(Locale.SIMPLIFIED_CHINESE);
                return;
            case 1:
                LanguageUtils.updateLanguage(Locale.TAIWAN);
                return;
            case 2:
                LanguageUtils.updateLanguage(Locale.ENGLISH);
                return;
            case 3:
                LanguageUtils.updateLanguage(new Locale("tr", "TR"));
                return;
            case 4:
                LanguageUtils.updateLanguage(new Locale("ru", "RU"));
                return;
            case 5:
                LanguageUtils.updateLanguage(new Locale("ar"));
                return;
            case 6:
                LanguageUtils.updateLanguage(new Locale("iw", "IL"));
                return;
            case 7:
                LanguageUtils.updateLanguage(new Locale("th", "TH"));
                return;
            case 8:
                LanguageUtils.updateLanguage(new Locale("de"));
                return;
            case 9:
                LanguageUtils.updateLanguage(new Locale("fr", "FR"));
                return;
            case 10:
                LanguageUtils.updateLanguage(new Locale("pt", "PT"));
                return;
            case 11:
                LanguageUtils.updateLanguage(new Locale("ja", "JP"));
                return;
            case 12:
                LanguageUtils.updateLanguage(new Locale("es", "ES"));
                return;
            case 13:
                LanguageUtils.updateLanguage(new Locale("it", "IT"));
                return;
            case 14:
                LanguageUtils.updateLanguage(new Locale("pl", "PL"));
                return;
            case 15:
                LanguageUtils.updateLanguage(new Locale("fa", "IR"));
                return;
            case 16:
                LanguageUtils.updateLanguage(new Locale("ko", "KR"));
                return;
            case 17:
                LanguageUtils.updateLanguage(new Locale("pt", "BR"));
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:33:0x00c0=Splitter:B:33:0x00c0, B:40:0x00d5=Splitter:B:40:0x00d5} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void writeFtsetLauncher(boolean r15) {
        /*
            r12 = 50
            r13 = 1
            int[] r9 = new int[r12]
            int[] r4 = new int[r12]
            if (r15 == 0) goto L_0x000c
            DeleteFtSetFile()
        L_0x000c:
            java.io.File r3 = new java.io.File
            java.lang.String r12 = "/mnt/sdcard/Iconfig/Iconfig.ini"
            r3.<init>(r12)
            boolean r12 = r3.exists()
            if (r12 == 0) goto L_0x001a
        L_0x0019:
            return
        L_0x001a:
            int r0 = com.yyw.ts70xhw.FtSet.GetIcon(r9)
            r8 = 0
        L_0x001f:
            int r12 = r9.length
            if (r8 < r12) goto L_0x0050
            java.io.File r2 = new java.io.File
            java.lang.String r12 = "/mnt/sdcard/Iconfig"
            r2.<init>(r12)
            boolean r12 = r2.exists()
            if (r12 != 0) goto L_0x0032
            r2.mkdirs()
        L_0x0032:
            r6 = 0
            r10 = 0
            java.io.FileWriter r7 = new java.io.FileWriter     // Catch:{ FileNotFoundException -> 0x00bf, IOException -> 0x00d4 }
            r7.<init>(r3)     // Catch:{ FileNotFoundException -> 0x00bf, IOException -> 0x00d4 }
            java.io.PrintWriter r11 = new java.io.PrintWriter     // Catch:{ FileNotFoundException -> 0x010f, IOException -> 0x0108, all -> 0x0101 }
            r11.<init>(r7)     // Catch:{ FileNotFoundException -> 0x010f, IOException -> 0x0108, all -> 0x0101 }
            r8 = 0
        L_0x003f:
            java.lang.String[][] r12 = com.ts.main.common.MainIconDef.packageStr     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            int r12 = r12.length     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            if (r8 < r12) goto L_0x0061
            r11.close()     // Catch:{ IOException -> 0x00f9 }
            r7.close()     // Catch:{ IOException -> 0x00f9 }
            r11.flush()     // Catch:{ IOException -> 0x00f9 }
            r10 = r11
            r6 = r7
            goto L_0x0019
        L_0x0050:
            r12 = r9[r8]
            if (r12 == 0) goto L_0x005e
            r12 = r9[r8]
            if (r12 <= 0) goto L_0x005e
            r12 = r9[r8]
            int r12 = r12 + -1
            r4[r12] = r13
        L_0x005e:
            int r8 = r8 + 1
            goto L_0x001f
        L_0x0061:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            java.lang.String[][] r13 = com.ts.main.common.MainIconDef.packageStr     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            r13 = r13[r8]     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            r14 = 0
            r13 = r13[r14]     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            java.lang.String r13 = java.lang.String.valueOf(r13)     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            r12.<init>(r13)     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            java.lang.String r13 = ","
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            java.lang.String[][] r13 = com.ts.main.common.MainIconDef.packageStr     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            r13 = r13[r8]     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            r14 = 1
            r13 = r13[r14]     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            java.lang.String r13 = ","
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            r13 = r4[r8]     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            java.lang.String r5 = r12.toString()     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            java.lang.String r12 = "lh"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            java.lang.String r14 = "ftSetString = "
            r13.<init>(r14)     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            java.lang.StringBuilder r13 = r13.append(r5)     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            java.lang.String r13 = r13.toString()     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            android.util.Log.d(r12, r13)     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            java.lang.String r13 = java.lang.String.valueOf(r5)     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            r12.<init>(r13)     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            java.lang.String r13 = "\r\n"
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            java.lang.String r12 = r12.toString()     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            r11.append(r12)     // Catch:{ FileNotFoundException -> 0x0112, IOException -> 0x010b, all -> 0x0104 }
            int r8 = r8 + 1
            goto L_0x003f
        L_0x00bf:
            r1 = move-exception
        L_0x00c0:
            r1.printStackTrace()     // Catch:{ all -> 0x00e9 }
            r10.close()     // Catch:{ IOException -> 0x00ce }
            r6.close()     // Catch:{ IOException -> 0x00ce }
            r10.flush()     // Catch:{ IOException -> 0x00ce }
            goto L_0x0019
        L_0x00ce:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0019
        L_0x00d4:
            r1 = move-exception
        L_0x00d5:
            r1.printStackTrace()     // Catch:{ all -> 0x00e9 }
            r10.close()     // Catch:{ IOException -> 0x00e3 }
            r6.close()     // Catch:{ IOException -> 0x00e3 }
            r10.flush()     // Catch:{ IOException -> 0x00e3 }
            goto L_0x0019
        L_0x00e3:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0019
        L_0x00e9:
            r12 = move-exception
        L_0x00ea:
            r10.close()     // Catch:{ IOException -> 0x00f4 }
            r6.close()     // Catch:{ IOException -> 0x00f4 }
            r10.flush()     // Catch:{ IOException -> 0x00f4 }
        L_0x00f3:
            throw r12
        L_0x00f4:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00f3
        L_0x00f9:
            r1 = move-exception
            r1.printStackTrace()
            r10 = r11
            r6 = r7
            goto L_0x0019
        L_0x0101:
            r12 = move-exception
            r6 = r7
            goto L_0x00ea
        L_0x0104:
            r12 = move-exception
            r10 = r11
            r6 = r7
            goto L_0x00ea
        L_0x0108:
            r1 = move-exception
            r6 = r7
            goto L_0x00d5
        L_0x010b:
            r1 = move-exception
            r10 = r11
            r6 = r7
            goto L_0x00d5
        L_0x010f:
            r1 = move-exception
            r6 = r7
            goto L_0x00c0
        L_0x0112:
            r1 = move-exception
            r10 = r11
            r6 = r7
            goto L_0x00c0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.main.common.MainSet.writeFtsetLauncher(boolean):void");
    }

    public static boolean isHaveAMBand() {
        switch (FtSet.GetRadioIc()) {
            case 2:
            case 3:
                return false;
            default:
                return true;
        }
    }

    public void SetFlyMode(boolean enable) {
        int i = 1;
        if (Build.VERSION.SDK_INT <= 16) {
            ContentResolver contentResolver = mContext.getContentResolver();
            if (!enable) {
                i = 0;
            }
            Settings.System.putInt(contentResolver, "airplane_mode_on", i);
        } else {
            ContentResolver contentResolver2 = mContext.getContentResolver();
            if (!enable) {
                i = 0;
            }
            Settings.Global.putInt(contentResolver2, "airplane_mode_on", i);
        }
        Intent intent = new Intent("android.intent.action.AIRPLANE_MODE");
        intent.putExtra(IConstantData.KEY_STATE, enable);
        mContext.sendBroadcast(intent);
    }

    public boolean bIsTimeOK() {
        Date d1 = null;
        try {
            d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-10-01 00:00:00");
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        long diff = System.currentTimeMillis() - d1.getTime();
        Log.i(TAG, "bIsTimeOK diff = " + diff);
        if (diff > 9216000) {
            return true;
        }
        return false;
    }

    public String GetCustom() {
        return mContext.getResources().getString(R.string.custom_num);
    }

    public static String getNetworkTime() {
        if (mContext == null || !mContext.getResources().getConfiguration().locale.getCountry().equals("CN")) {
            return "";
        }
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    URLConnection conn = new URL("http://www.baidu.com").openConnection();
                    conn.connect();
                    SystemClock.setCurrentTimeMillis(conn.getDate());
                } catch (MalformedURLException e2) {
                    e2.printStackTrace();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }.start();
        return "";
    }
}
