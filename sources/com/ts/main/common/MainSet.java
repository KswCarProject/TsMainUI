package com.ts.main.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.backup.BackupManager;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.android.SdkConstants;
import com.lgb.canmodule.CanJni;
import com.mediatek.galleryfeature.pq.filter.FilterWindow;
import com.mediatek.miravision.setting.MiraVisionJni;
import com.qiner.appinfo.AppListUtil;
import com.ts.MainUI.AuthServer;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.MainUI.TsFile;
import com.ts.activate.ITsAuthor;
import com.ts.backcar.BackcarService;
import com.ts.bt.BtExe;
import com.ts.can.CanIF;
import com.ts.factoryset.FsBaseActivity;
import com.ts.main.auth.AudioRecoderUtils;
import com.ts.main.auth.FcTestMode;
import com.ts.main.auth.FileUtils;
import com.ts.main.common.ShellUtils;
import com.ts.main.update.CountdownDialog;
import com.ts.t3.T3WeakJoinUtils;
import com.ts.wireless.carplay.WirelessCarplay;
import com.txznet.sdk.TXZPoiSearchManager;
import com.txznet.sdk.TXZResourceManager;
import com.txznet.sdk.tongting.IConstantData;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;
import com.yyw.ts70xhw.Radio;
import com.yyw.ts70xhw.StSet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MainSet {
    public static final String ACTION_MAINUI_MCUSTATE = "com.ts.main.mcustate";
    public static final String ATC_LOG_FILE = "/mnt/sdcard/atcLog";
    public static final String AUTOUPDATE_FLAG = "autoCompress.upd";
    public static final String AVM_CAMERA_MAP = "camera_map.txt";
    public static final String AVM_CAMERA_SELECT = "camera_select.txt";
    public static final String AVM_FOLD = "avm/";
    public static final int AV_V = 2;
    public static final int BACK_V = 0;
    public static final int CORE3561 = 1;
    public static final int CORE8227L = 0;
    public static final int CORE8259 = 2;
    public static final int COREBOARD = 1;
    public static final int DAB_NEXT = 46;
    public static final int DAB_PREV = 45;
    public static final int DTV_V = 1;
    private static char[] Dspversiono = new char[32];
    private static final String FT_SET_DIR = "/mnt/sdcard/Iconfig";
    private static final String Factory_name = "ts8259factory.bin";
    public static final int PLAYER_DDH = 1;
    public static final int PLAYER_KW = 0;
    public static final int PlAYER_CYB = 2;
    static final String RLF_KOREA_MOUDLENAME = "Finedrive A1";
    public static final int SCREEN_1024X480 = 4;
    public static final int SCREEN_1024X600 = 1;
    public static final int SCREEN_1280X480 = 5;
    public static final int SCREEN_1280x720 = 10;
    public static final int SCREEN_1366X768 = 7;
    public static final int SCREEN_1920x1080 = 11;
    public static final int SCREEN_1920x720 = 9;
    public static final int SCREEN_768X1024 = 3;
    public static final int SCREEN_800X1280 = 6;
    public static final int SCREEN_800X480 = 2;
    public static final int SCREEN_800x600 = 8;
    static final String SD_PATH = "/mnt/sdcard";
    public static final int SIM_OWNER_FF = 3;
    public static final int SIM_OWNER_MG = 1;
    public static final int SIM_OWNER_TXZ = 2;
    public static final int SIM_OWNER_UNKNOWN = 0;
    public static final String SP_BT_B30 = "10";
    public static final String SP_DD_125 = "11";
    public static final String SP_KS_QOROS = "4";
    public static final String SP_PCBA_VOL = "7";
    public static final String SP_RG_H5 = "9";
    public static final String SP_RG_T3 = "8";
    public static final String SP_RLF_KORON = "2";
    public static final String SP_TW_CJW = "5";
    public static final String SP_XH_DMAX = "3";
    public static final String SP_XPH5 = "1";
    public static final String SP_XS_DZ = "6";
    public static final String SYSTEM_AVM_FOLD = "system/app/AvmPlayer/avm/";
    private static final String TAG = "MainSet";
    private static final String TESTMODE_Txt = "FfTestMode.txt";
    public static final String TS_360AVM_PACKAGENAME = "com.autochips.avmplayer";
    public static final String TS_CLIENT_PACKAGENAME = "com.ts.car.client";
    static final String TS_DEMO1_PATH = "/mnt/sdcard";
    static final String TS_DEMO_FILE = "/demo";
    static final String TS_DEMO_PATH = "/mnt/sdcard/demo";
    public static final String TS_LOG_FILE = "/mnt/sdcard/TsLog";
    static final String TS_OEM_PATH = "/mnt/sdcard/tsoem";
    public static final String TS_T3_PACKAGENAME = "com.forfan.tsmqtt";
    public static FcTestMode Testmode = null;
    public static final String UPDATE_FILE = "8259.bin";
    public static final String UPDATE_FLAG = "8259.upd";
    static final String VERSION_FILE = "/system/priv-app/MainUI/Version.ini";
    static final String VERSION_PATH = "/system/priv-app/MainUI/";
    public static boolean bIsFrontCam = false;
    public static boolean bKeyBroad = false;
    public static boolean bMonkey = false;
    public static boolean bShowDlg = false;
    public static Context mContext = null;
    private static MainSet mMainSet = null;
    public static int nFcamTime = 0;
    public static int nPlayer = 0;
    public static int nShowScreen = 0;
    public static String seiid = "TSKJ0000000000";
    private static final String systeminfo_name = "tssysteminfo.txt";
    private static final String systemtimezoneinfo = "timezone";
    private static final String systemtimezonesw = "timezoneauto";
    String CustomStr = null;
    String CustomStrShow = null;
    private char[] Dspversion = new char[32];
    boolean IsTestMode = false;
    List<PackageInfo> Mypacks = null;
    private final String QB_AIRPLANE_MODE_ON = "qb_airplane_mode_on";
    int ShowBressTime = 30;
    private ServiceConnection authorServiceConn = new ServiceConnection() {
        public void onServiceDisconnected(ComponentName arg0) {
            MainSet.this.mAuthorServer = null;
        }

        public void onServiceConnected(ComponentName arg0, IBinder binder) {
            MainSet.this.mAuthorServer = ITsAuthor.Stub.asInterface(binder);
        }
    };
    String[] avm_must_file = {"0520.txt", "0820.txt", "0926.txt", "8255.txt", "jieda.bin", "Q7.bin", "skybox.bin", "cameraCfg.xml", "algo_config.xml"};
    public boolean bCeleb = false;
    public boolean bFirstStart = false;
    public boolean bIsForbiden = false;
    boolean bLastMode = false;
    boolean bShowold = false;
    /* access modifiers changed from: private */
    public ITsAuthor mAuthorServer = null;
    private char[] mModelName = new char[32];
    int nDestBrightness = 0;
    int nDevBrightness = 0;
    public int nKeyNum = 0;
    int nRet = -1;
    int nStSetRet = -1;
    int nSysBrightness = 0;
    int nTaskTime = 0;
    int nturnOn = 0;
    int ret = -1;
    public String toastString = null;

    public interface DealPasswordResult {
        void OnOK();
    }

    /* access modifiers changed from: package-private */
    public void PlayFile(String strPath) {
        MediaPlayer mp = new MediaPlayer();
        try {
            Log.i(TAG, "PlayFile==" + strPath);
            mp.setDataSource(strPath);
        } catch (IllegalArgumentException e) {
            Log.i(TAG, "IllegalArgumentException 111");
            e.printStackTrace();
        } catch (SecurityException e2) {
            Log.i(TAG, "IllegalArgumentException 222");
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            Log.i(TAG, "IllegalArgumentException 333");
            e3.printStackTrace();
        } catch (IOException e4) {
            Log.i(TAG, "IllegalArgumentException 444");
            e4.printStackTrace();
        }
        try {
            mp.prepare();
        } catch (IOException | IllegalStateException e5) {
            e5.printStackTrace();
        }
        mp.start();
    }

    public void StartRecord(final long nTime) {
        final AudioRecoderUtils mMyRecord = new AudioRecoderUtils("/mnt/sdcard/");
        Evc.GetInstance().evol_workmode_set(0);
        mMyRecord.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {
            public void onUpdate(double db, long time) {
                Log.i(MainSet.TAG, "db==" + db);
                Log.i(MainSet.TAG, "time==" + time);
                int fenbei = ((int) (3000.0d + ((6000.0d * db) / 100.0d))) / 100;
                if (time > nTime) {
                    mMyRecord.stopRecord();
                }
                MainAlterwin.GetInstance().ShowTv(String.valueOf(MainSet.mContext.getResources().getString(R.string.recording_continue)) + fenbei + "DB");
            }

            public void onStop(String filePath) {
                Log.i(MainSet.TAG, "filePath==" + filePath);
                MainAlterwin.GetInstance().HidenUnRegWin();
                MainSet.this.PlayFile(filePath);
            }
        });
        mMyRecord.startRecord();
        MainAlterwin.GetInstance().ShowUnRegWin(3);
    }

    public static MainSet GetInstance() {
        if (mMainSet == null) {
            mMainSet = new MainSet();
        }
        return mMainSet;
    }

    public boolean bIsEnableCan() {
        return true;
    }

    public boolean bNotReg() {
        if (AuthServer.GetInstance().GetIDType() == 0) {
            return true;
        }
        return false;
    }

    public boolean bAutoAuth() {
        if (mContext == null || !mContext.getResources().getString(R.string.custom_support_autoAuth).equals("1")) {
            return false;
        }
        return true;
    }

    public void ResetLaunguage() {
        if (mContext.getResources().getString(R.string.not_support_twlanguage).equals("1") && Locale.getDefault().getCountry().equals("TW")) {
            LanguageUtils.updateLanguage(Locale.SIMPLIFIED_CHINESE);
        }
        ResetBressTime();
    }

    public void StartzhstService() {
    }

    /* access modifiers changed from: package-private */
    public boolean bCheckAvmCam(String path) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String Result = TXZResourceManager.STYLE_DEFAULT;
            while (true) {
                try {
                    String s = bufferedReader.readLine();
                    if (s == null) {
                        break;
                    }
                    Result = String.valueOf(Result) + s;
                    Log.i(TAG, "Result==" + Result);
                } catch (IOException e) {
                    try {
                        e.printStackTrace();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                                BufferedReader bufferedReader2 = bufferedReader;
                                return false;
                            } catch (IOException e2) {
                                e2.printStackTrace();
                                BufferedReader bufferedReader3 = bufferedReader;
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        BufferedReader bufferedReader4 = bufferedReader;
                        e.printStackTrace();
                        return false;
                    }
                }
            }
            if (Result.equals("1302")) {
                BufferedReader bufferedReader5 = bufferedReader;
                return true;
            }
            return false;
        } catch (FileNotFoundException e4) {
            e = e4;
            e.printStackTrace();
            return false;
        }
    }

    public void StartNaviKing() {
        Intent intent = new Intent();
        intent.setPackage("com.kingwaytek.naviking4g");
        intent.setAction("com.kingwaytek.tmcService");
        mContext.startService(intent);
    }

    public void StartAvmService() {
        Log.i(TAG, "StartAvmService");
        try {
            Intent intent = new Intent();
            intent.setPackage("com.autochips.avmplayer");
            intent.setAction("com.autochips.avmplayer.AvmService");
            mContext.startService(intent);
        } catch (Exception e) {
        }
    }

    public void StartArDvrService() {
        Log.i(TAG, "StartArDvrService");
        if (IsHaveApk("com.ankai.ardvr")) {
            try {
                Intent intent = new Intent();
                intent.setPackage("com.ankai.ardvr");
                intent.setAction("com.ankai.ardvr.gdarcameraservice");
                mContext.startService(intent);
            } catch (Exception e) {
            }
        }
    }

    public void StartT3Service() {
        Log.i(TAG, "StartT3Service");
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(TS_T3_PACKAGENAME, "com.forfan.tsmqtt.TsService"));
            mContext.startService(intent);
        } catch (Exception e) {
        }
    }

    public void StartCarClientService() {
        Log.i(TAG, "StartCarClientService");
        try {
            Intent intent = new Intent();
            intent.setPackage(TS_CLIENT_PACKAGENAME);
            intent.setAction("com.ts.car.client.CarClientService");
            mContext.startService(intent);
        } catch (Exception e) {
        }
    }

    public void SetTouchState(boolean forceClose) {
        if (FtSet.GetTouchBall() != 1 || forceClose) {
            Intent intent = new Intent();
            intent.setPackage("com.ts.mytouch");
            intent.setAction("com.ts.mytouch.TouchService");
            mContext.stopService(intent);
            return;
        }
        Intent intent2 = new Intent();
        intent2.setPackage("com.ts.mytouch");
        intent2.setAction("com.ts.mytouch.TouchService");
        mContext.startService(intent2);
    }

    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    static void SetTimeZone(String strtimezone) {
        try {
            ((AlarmManager) mContext.getSystemService("alarm")).setTimeZone(strtimezone);
        } catch (Exception e) {
        }
    }

    public boolean IsSupprotRemotecontrol() {
        if (mContext.getResources().getString(R.string.support_remote_control).equals("0")) {
            return TsSysinfo.GetInstance().bRemoteControl;
        }
        return true;
    }

    public boolean Support_new_forbidden() {
        if (mContext.getResources().getString(R.string.support_new_forbidden).equals("1")) {
            return true;
        }
        return false;
    }

    public boolean IsSupprotLossTranscient() {
        if (mContext.getResources().getString(R.string.support_loss_transient_control).equals("0")) {
            return TsSysinfo.GetInstance().bLossTranclient;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean IsSupportFun(int id) {
        if (mContext.getResources().getString(id).equals("0")) {
            return false;
        }
        return true;
    }

    public void Inint(int nBatFirst) {
        if (Support_changeDpi()) {
            tool.GetInstance().DealSu("wm density 240");
        }
        TsFile.deleteFile(new File("/mnt/sdcard/record.amr"));
        if (getSystemVersion().equals("10")) {
            tool.GetInstance().DealSu("setprop ro.forfan.version.sdk 29");
            tool.GetInstance().DealSu("setprop ro.forfan.version.sdkstr 29");
        }
        StartCarClientService();
        StartzhstService();
        StartArDvrService();
        if (FtSet.GetCam8824() == 1) {
            Evc.GetInstance().nCheckCpu = 1;
        }
        if (FtSet.GetExUart() == 1) {
            Iop.SetAuxHoldEx(3);
        }
        if (FtSet.GetBlueEn() == 1) {
            SystemProperties.set("persist.bluetooth.disablessp", SdkConstants.VALUE_TRUE);
        } else {
            SystemProperties.set("persist.bluetooth.disablessp", SdkConstants.VALUE_FALSE);
        }
        if (IsTwcjw()) {
            SystemProperties.set("persist.bluetooth.callfilter", "disable");
        }
        if (GetModelName() != null) {
            if (GetModelName().equals(TXZResourceManager.STYLE_DEFAULT)) {
                Log.i(TAG, "GetModelName==null" + GetModelName());
            } else {
                Log.i(TAG, "GetModelName==" + GetModelName());
                SystemProperties.set("forfan.device.model", GetModelName());
            }
        }
        Log.i(TAG, "seiid==" + seiid);
        if (MainUI.GetInstance().IsSyncOK()) {
            writeFtsetLauncher(false);
        }
        SetTouchState(false);
        if (nBatFirst == 1) {
            if (isZh()) {
                Settings.System.putString(mContext.getContentResolver(), "time_12_24", "24");
                Log.i(TAG, "set to 24 = ");
                if (isrSimpZh()) {
                    SetTimeZone("Asia/Shanghai");
                } else {
                    SetTimeZone("Asia/Taipei");
                }
            }
            if (IsSupprotRemotecontrol()) {
                SetQBAirFly(false);
            } else {
                SetQBAirFly(true);
            }
            Settings.Global.putInt(mContext.getContentResolver(), "auto_time", 1);
            if (!BtExe.getBtInstance().isAutoConnect()) {
                BtExe.getBtInstance().autoConnectSw();
            }
        }
        if (IsVSUI() || IsBmwX1()) {
            TsDisplay.GetInstance().nSetTcon = 0;
            Log.i(TAG, "******************WmInit mDisplay.nSetTcon = " + TsDisplay.GetInstance().nSetTcon);
        }
        if (nBatFirst == 1) {
            UpdateSysInput();
        }
        if (!mContext.getResources().getString(R.string.custom_poweroff_show_screen).equals("0")) {
            nShowScreen = 1;
        }
        if (FtSet.GetApkForbid() == 1 || mContext.getResources().getString(R.string.custom_forbiden_install).equals("1")) {
            SystemProperties.set("forfan.notallow.install", "1");
        }
        Evc.bNaviVol = true;
        AppListUtil.getInstance().init(mContext);
        if (T3WeakJoinUtils.bIsT3WeakPrj) {
            TsLogger.GetInstance().CatlogToSys("/data/logger", 60);
        }
        new ScreenSaver(mContext);
        if (FtSet.GetUsbHost() == 0 && tool.getDirSize(new File("/sdcard/atclog")) > 1048576000) {
            TsFile.deleteFile(new File("/sdcard/atclog"));
        }
        FsBaseActivity.ReSetCamNum();
    }

    /* access modifiers changed from: package-private */
    public void ResetBressTime() {
        this.ShowBressTime = 30;
    }

    /* access modifiers changed from: package-private */
    public void ShowBrightnessBtn() {
        if (!mContext.getResources().getString(R.string.custom_show_brightness).equals("2")) {
            SendIntent("android.intent.action.SHOW_MYBRIGHTNESS", (String) null);
        } else {
            this.ShowBressTime = 0;
        }
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
    }

    public static void SendIntent(String Str, String Packname) {
        Intent intent = new Intent();
        if (Packname != null) {
            intent.setPackage(Packname);
        }
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

    public String[] GetMountedStorage() {
        File[] files;
        List<String> pathList = new ArrayList<>();
        File mountRoot = new File("/storage");
        if (mountRoot.exists() && mountRoot.isDirectory() && (files = mountRoot.listFiles()) != null) {
            for (File file : files) {
                if (!file.isHidden() && file.isDirectory() && file.canRead()) {
                    String path = file.getAbsolutePath();
                    if (!path.contains("cdfs")) {
                        pathList.add(path);
                    } else {
                        Log.d(TAG, "NOT ADD");
                    }
                }
            }
        }
        String[] MountedPath = new String[pathList.size()];
        pathList.toArray(MountedPath);
        return MountedPath;
    }

    public static String GetSerid() {
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

    /* access modifiers changed from: package-private */
    public boolean IsAutother() {
        if (mContext.getResources().getString(R.string.custom_auto_auth).equals("1")) {
            return true;
        }
        return false;
    }

    public static boolean bSupperID() {
        return GetSerid().contains("FFKJ");
    }

    public boolean IsMathToMcu() {
        if (GetSerid().contains(mContext.getResources().getString(R.string.custom_num))) {
            return true;
        }
        if (GetSerid().contains("FFKJ")) {
            return false;
        }
        if (mContext.getResources().getString(R.string.custom_num).equals("TSKJ") || GetSerid().contains("TSKJ")) {
            return true;
        }
        return false;
    }

    public static int GetCoreType() {
        return 2;
    }

    /* access modifiers changed from: package-private */
    public boolean bIsHaveTestFile() {
        this.nKeyNum = 0;
        boolean bRet = false;
        String[] mCtouchPath = GetMountedStorage();
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
                    if (Testmode.RadioIC == 5) {
                        FtSet.SetFMsd(18);
                        FtSet.SetAMsd(25);
                    }
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
    public void CheckTestMode() {
        this.IsTestMode = bIsHaveTestFile();
    }

    public boolean Is3561() {
        return false;
    }

    public boolean IsTestMode() {
        if (this.CustomStr == null) {
            this.CustomStr = mContext.getResources().getString(R.string.custom_num);
        }
        if (this.CustomStr.equals("TEST")) {
            return true;
        }
        return this.IsTestMode;
    }

    public boolean IsSupport1280x480() {
        if (mContext.getResources().getString(R.string.support_1280x480).equals("1")) {
            return true;
        }
        return false;
    }

    public boolean IsSupportRotation() {
        if (mContext.getResources().getString(R.string.support_rotation).equals("1")) {
            return true;
        }
        return false;
    }

    public boolean IsSupportDim() {
        if (mContext.getResources().getString(R.string.support_dim_control).equals("1")) {
            return true;
        }
        return false;
    }

    public boolean IsFCamSupportMir() {
        if (mContext.getResources().getString(R.string.custom_support_mirror).equals("1")) {
            return true;
        }
        return false;
    }

    public boolean IsFCamSupportChange() {
        if (mContext.getResources().getString(R.string.custom_support_frchange).equals("1")) {
            return true;
        }
        return false;
    }

    public boolean IsSupportopenAllCamera() {
        if (mContext.getResources().getString(R.string.custom_support_openallcamera).equals("1")) {
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

    public boolean IsCustom(String str2) {
        if (mContext.getResources().getString(R.string.custom_num).equals(str2)) {
            return true;
        }
        return false;
    }

    static String GetSpesilString() {
        return mContext.getResources().getString(R.string.custom_spesail_install);
    }

    public boolean IsXuhuiDmax() {
        if (GetSpesilString().equals("3")) {
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

    public boolean IsDiDi125() {
        if (GetSpesilString().equals("11")) {
            return true;
        }
        return false;
    }

    public boolean Support_changeDpi() {
        if (MainUI.GetDispW() == 1280 && MainUI.GetSrcH() == 480) {
            return TsSysinfo.GetInstance().bDyChangeDpi;
        }
        return false;
    }

    public boolean Support_lastMem() {
        if (FtSet.GetLastMemory() != 1 && !mContext.getResources().getString(R.string.support_none_lastmem).equals("1")) {
            return false;
        }
        return true;
    }

    public boolean SupportOKGoogle() {
        if (mContext.getResources().getString(R.string.support_okgoogle).equals("1")) {
            return true;
        }
        return false;
    }

    public boolean IsXsDz() {
        if (GetSpesilString().equals(SP_XS_DZ)) {
            return true;
        }
        return false;
    }

    public boolean IsPCBAVol() {
        if (GetSpesilString().equals(SP_PCBA_VOL)) {
            return true;
        }
        return false;
    }

    public void StartCarplayService() {
        CarplayControl.SetCarplayState();
        if (FtSet.IsIconExist(130) == 1) {
            CarplayControl.SetDefaultDisp(MainUI.GetDispW(), MainUI.GetDispH());
            Intent intent = new Intent();
            intent.setPackage(MainUI.TS_CARPLAY_PNAME);
            intent.setAction("com.autochips.carplay.START_CARPLAY_SERVICE");
            mContext.startService(intent);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x006a A[SYNTHETIC, Splitter:B:22:0x006a] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007f A[SYNTHETIC, Splitter:B:30:0x007f] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0091 A[SYNTHETIC, Splitter:B:36:0x0091] */
    /* JADX WARNING: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:19:0x0065=Splitter:B:19:0x0065, B:27:0x007a=Splitter:B:27:0x007a} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void writeFtSetInfo(boolean r10) {
        /*
            if (r10 == 0) goto L_0x0013
            java.io.File r3 = new java.io.File
            java.lang.String r8 = "/mnt/sdcard/Iconfig/ftsetinfo.ini"
            r3.<init>(r8)
            boolean r8 = r3.exists()
            if (r8 == 0) goto L_0x0013
            r3.delete()
        L_0x0013:
            java.io.File r2 = new java.io.File
            java.lang.String r8 = "/mnt/sdcard/Iconfig/ftsetinfo.ini"
            r2.<init>(r8)
            java.io.File r1 = new java.io.File
            java.lang.String r8 = "/mnt/sdcard/Iconfig"
            r1.<init>(r8)
            boolean r8 = r1.exists()
            if (r8 != 0) goto L_0x002c
            r1.mkdirs()
        L_0x002c:
            r4 = 0
            r6 = 0
            java.io.FileWriter r5 = new java.io.FileWriter     // Catch:{ FileNotFoundException -> 0x0064, IOException -> 0x0079 }
            r5.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0064, IOException -> 0x0079 }
            java.io.PrintWriter r7 = new java.io.PrintWriter     // Catch:{ FileNotFoundException -> 0x00b5, IOException -> 0x00ae, all -> 0x00a7 }
            r7.<init>(r5)     // Catch:{ FileNotFoundException -> 0x00b5, IOException -> 0x00ae, all -> 0x00a7 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x00b8, IOException -> 0x00b1, all -> 0x00aa }
            java.lang.String r9 = "FtSet.GetCamType="
            r8.<init>(r9)     // Catch:{ FileNotFoundException -> 0x00b8, IOException -> 0x00b1, all -> 0x00aa }
            int r9 = com.yyw.ts70xhw.FtSet.GetCamType()     // Catch:{ FileNotFoundException -> 0x00b8, IOException -> 0x00b1, all -> 0x00aa }
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ FileNotFoundException -> 0x00b8, IOException -> 0x00b1, all -> 0x00aa }
            java.lang.String r9 = "\r\n"
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ FileNotFoundException -> 0x00b8, IOException -> 0x00b1, all -> 0x00aa }
            java.lang.String r8 = r8.toString()     // Catch:{ FileNotFoundException -> 0x00b8, IOException -> 0x00b1, all -> 0x00aa }
            r7.append(r8)     // Catch:{ FileNotFoundException -> 0x00b8, IOException -> 0x00b1, all -> 0x00aa }
            if (r7 == 0) goto L_0x00a4
            r7.flush()     // Catch:{ Exception -> 0x00a0 }
            r7.close()     // Catch:{ Exception -> 0x00a0 }
            r5.close()     // Catch:{ Exception -> 0x00a0 }
            r6 = r7
            r4 = r5
        L_0x0063:
            return
        L_0x0064:
            r0 = move-exception
        L_0x0065:
            r0.printStackTrace()     // Catch:{ all -> 0x008e }
            if (r6 == 0) goto L_0x0063
            r6.flush()     // Catch:{ Exception -> 0x0074 }
            r6.close()     // Catch:{ Exception -> 0x0074 }
            r4.close()     // Catch:{ Exception -> 0x0074 }
            goto L_0x0063
        L_0x0074:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0063
        L_0x0079:
            r0 = move-exception
        L_0x007a:
            r0.printStackTrace()     // Catch:{ all -> 0x008e }
            if (r6 == 0) goto L_0x0063
            r6.flush()     // Catch:{ Exception -> 0x0089 }
            r6.close()     // Catch:{ Exception -> 0x0089 }
            r4.close()     // Catch:{ Exception -> 0x0089 }
            goto L_0x0063
        L_0x0089:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0063
        L_0x008e:
            r8 = move-exception
        L_0x008f:
            if (r6 == 0) goto L_0x009a
            r6.flush()     // Catch:{ Exception -> 0x009b }
            r6.close()     // Catch:{ Exception -> 0x009b }
            r4.close()     // Catch:{ Exception -> 0x009b }
        L_0x009a:
            throw r8
        L_0x009b:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x009a
        L_0x00a0:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00a4:
            r6 = r7
            r4 = r5
            goto L_0x0063
        L_0x00a7:
            r8 = move-exception
            r4 = r5
            goto L_0x008f
        L_0x00aa:
            r8 = move-exception
            r6 = r7
            r4 = r5
            goto L_0x008f
        L_0x00ae:
            r0 = move-exception
            r4 = r5
            goto L_0x007a
        L_0x00b1:
            r0 = move-exception
            r6 = r7
            r4 = r5
            goto L_0x007a
        L_0x00b5:
            r0 = move-exception
            r4 = r5
            goto L_0x0065
        L_0x00b8:
            r0 = move-exception
            r6 = r7
            r4 = r5
            goto L_0x0065
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.main.common.MainSet.writeFtSetInfo(boolean):void");
    }

    private static void DeleteFtSetFile() {
        File file2 = new File("/mnt/sdcard/Iconfig/Iconfig.ini");
        if (file2.exists()) {
            file2.delete();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d6 A[SYNTHETIC, Splitter:B:39:0x00d6] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ed A[SYNTHETIC, Splitter:B:47:0x00ed] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0101 A[SYNTHETIC, Splitter:B:53:0x0101] */
    /* JADX WARNING: Removed duplicated region for block: B:81:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:83:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:36:0x00d1=Splitter:B:36:0x00d1, B:44:0x00e8=Splitter:B:44:0x00e8} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void writeFtsetLauncher(boolean r14) {
        /*
            r11 = 50
            r13 = 1
            writeFtSetInfo(r14)
            int[] r8 = new int[r11]
            int[] r3 = new int[r11]
            if (r14 == 0) goto L_0x000f
            DeleteFtSetFile()
        L_0x000f:
            java.io.File r2 = new java.io.File
            java.lang.String r11 = "/mnt/sdcard/Iconfig/Iconfig.ini"
            r2.<init>(r11)
            boolean r11 = r2.exists()
            if (r11 == 0) goto L_0x001e
        L_0x001d:
            return
        L_0x001e:
            com.yyw.ts70xhw.FtSet.GetIcon(r8)
            r7 = 0
        L_0x0022:
            int r11 = r8.length
            if (r7 < r11) goto L_0x0056
            java.io.File r1 = new java.io.File
            java.lang.String r11 = "/mnt/sdcard/Iconfig"
            r1.<init>(r11)
            boolean r11 = r1.exists()
            if (r11 != 0) goto L_0x0036
            r1.mkdirs()
        L_0x0036:
            r5 = 0
            r9 = 0
            java.io.FileWriter r6 = new java.io.FileWriter     // Catch:{ FileNotFoundException -> 0x00d0, IOException -> 0x00e7 }
            r6.<init>(r2)     // Catch:{ FileNotFoundException -> 0x00d0, IOException -> 0x00e7 }
            java.io.PrintWriter r10 = new java.io.PrintWriter     // Catch:{ FileNotFoundException -> 0x0126, IOException -> 0x011f, all -> 0x0118 }
            r10.<init>(r6)     // Catch:{ FileNotFoundException -> 0x0126, IOException -> 0x011f, all -> 0x0118 }
            r7 = 0
        L_0x0043:
            java.lang.String[][] r11 = com.ts.main.common.MainIconDef.packageStr     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            int r11 = r11.length     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            if (r7 < r11) goto L_0x006c
            if (r10 == 0) goto L_0x0114
            r10.flush()     // Catch:{ Exception -> 0x0110 }
            r10.close()     // Catch:{ Exception -> 0x0110 }
            r6.close()     // Catch:{ Exception -> 0x0110 }
            r9 = r10
            r5 = r6
            goto L_0x001d
        L_0x0056:
            r11 = r8[r7]
            if (r11 == 0) goto L_0x0069
            r11 = r8[r7]
            if (r11 <= 0) goto L_0x0069
            r11 = r8[r7]
            int r12 = r3.length
            if (r11 > r12) goto L_0x0069
            r11 = r8[r7]
            int r11 = r11 + -1
            r3[r11] = r13
        L_0x0069:
            int r7 = r7 + 1
            goto L_0x0022
        L_0x006c:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            java.lang.String[][] r12 = com.ts.main.common.MainIconDef.packageStr     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            r12 = r12[r7]     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            r13 = 0
            r12 = r12[r13]     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            r11.<init>(r12)     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            java.lang.String r12 = ","
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            java.lang.String[][] r12 = com.ts.main.common.MainIconDef.packageStr     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            r12 = r12[r7]     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            r13 = 1
            r12 = r12[r13]     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            java.lang.String r12 = ","
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            r12 = r3[r7]     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            java.lang.String r4 = r11.toString()     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            java.lang.String r11 = "lh"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            java.lang.String r13 = "ftSetString = "
            r12.<init>(r13)     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            java.lang.StringBuilder r12 = r12.append(r4)     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            java.lang.String r12 = r12.toString()     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            android.util.Log.d(r11, r12)     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            java.lang.String r12 = java.lang.String.valueOf(r4)     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            r11.<init>(r12)     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            java.lang.String r12 = "\r\n"
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            java.lang.String r11 = r11.toString()     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            r10.append(r11)     // Catch:{ FileNotFoundException -> 0x0129, IOException -> 0x0122, all -> 0x011b }
            int r7 = r7 + 1
            goto L_0x0043
        L_0x00d0:
            r0 = move-exception
        L_0x00d1:
            r0.printStackTrace()     // Catch:{ all -> 0x00fe }
            if (r9 == 0) goto L_0x001d
            r9.flush()     // Catch:{ Exception -> 0x00e1 }
            r9.close()     // Catch:{ Exception -> 0x00e1 }
            r5.close()     // Catch:{ Exception -> 0x00e1 }
            goto L_0x001d
        L_0x00e1:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x001d
        L_0x00e7:
            r0 = move-exception
        L_0x00e8:
            r0.printStackTrace()     // Catch:{ all -> 0x00fe }
            if (r9 == 0) goto L_0x001d
            r9.flush()     // Catch:{ Exception -> 0x00f8 }
            r9.close()     // Catch:{ Exception -> 0x00f8 }
            r5.close()     // Catch:{ Exception -> 0x00f8 }
            goto L_0x001d
        L_0x00f8:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x001d
        L_0x00fe:
            r11 = move-exception
        L_0x00ff:
            if (r9 == 0) goto L_0x010a
            r9.flush()     // Catch:{ Exception -> 0x010b }
            r9.close()     // Catch:{ Exception -> 0x010b }
            r5.close()     // Catch:{ Exception -> 0x010b }
        L_0x010a:
            throw r11
        L_0x010b:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x010a
        L_0x0110:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0114:
            r9 = r10
            r5 = r6
            goto L_0x001d
        L_0x0118:
            r11 = move-exception
            r5 = r6
            goto L_0x00ff
        L_0x011b:
            r11 = move-exception
            r9 = r10
            r5 = r6
            goto L_0x00ff
        L_0x011f:
            r0 = move-exception
            r5 = r6
            goto L_0x00e8
        L_0x0122:
            r0 = move-exception
            r9 = r10
            r5 = r6
            goto L_0x00e8
        L_0x0126:
            r0 = move-exception
            r5 = r6
            goto L_0x00d1
        L_0x0129:
            r0 = move-exception
            r9 = r10
            r5 = r6
            goto L_0x00d1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.main.common.MainSet.writeFtsetLauncher(boolean):void");
    }

    public boolean IsMcuId(String str2) {
        String string = GetSerid();
        if (string != null) {
            return string.contains(str2);
        }
        return false;
    }

    public boolean IsEkeyTurnEnable() {
        int id = mContext.getResources().getIdentifier("custom_ekeytunr_enable", SdkConstants.TAG_STRING, mContext.getPackageName());
        if (id == 0 || !mContext.getResources().getString(id).equals("1")) {
            return false;
        }
        if (mContext.getResources().getIdentifier("ekey_trans_to_allapp", SdkConstants.TAG_STRING, mContext.getPackageName()) > 0) {
            return true;
        }
        String Toppckname = WinShow.getTopPackName();
        String topactivityString = WinShow.getTopActivityName();
        if (Toppckname != null && (Toppckname.startsWith("com.android.launcher") || Toppckname.startsWith("com.ts.dvdplayer") || Toppckname.startsWith(MainUI.TS_CARPLAY_PNAME) || Toppckname.startsWith("com.autonavi.amapauto"))) {
            return true;
        }
        if (topactivityString == null) {
            return false;
        }
        if (topactivityString.startsWith("com.ts.main.radio") || topactivityString.startsWith("com.ts.bt")) {
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
        if (!mContext.getResources().getString(R.string.custom_have_front_cam_set).equals("1") && FtSet.IsIconExist(125) != 1) {
            return false;
        }
        return true;
    }

    public boolean IsXT5() {
        if (mContext.getResources().getString(R.string.custom_num_show).equals("XT5")) {
            return true;
        }
        return false;
    }

    public static boolean IsXPH5_HZ() {
        if (mContext.getResources().getString(R.string.custom_xp5_hou_zhuang).equals("1")) {
            return true;
        }
        return false;
    }

    public static boolean IsXPH5() {
        if (mContext.getResources().getString(R.string.custom_spesail_install).equals("1")) {
            return true;
        }
        return false;
    }

    public static boolean IsRxfKoren() {
        if (mContext.getResources().getString(R.string.custom_spesail_install).equals("2")) {
            return true;
        }
        return false;
    }

    public static boolean IsShowBrughtness() {
        if (mContext.getResources().getString(R.string.custom_show_brightness).equals("1")) {
            return true;
        }
        return false;
    }

    public boolean IsXXGD() {
        if (this.CustomStrShow == null) {
            this.CustomStrShow = mContext.getResources().getString(R.string.custom_num_show);
        }
        if (this.CustomStrShow.equals("XXGD")) {
            return true;
        }
        return false;
    }

    public Boolean IsGLSXVer() {
        if (this.CustomStrShow == null) {
            this.CustomStrShow = mContext.getResources().getString(R.string.custom_num_show);
        }
        if (this.CustomStrShow.equals("GLSX")) {
            return true;
        }
        return false;
    }

    public Boolean IsSZTB() {
        if (this.CustomStrShow == null) {
            this.CustomStrShow = mContext.getResources().getString(R.string.custom_num_show);
        }
        if (this.CustomStrShow.equals("SZTB")) {
            return true;
        }
        return false;
    }

    public static boolean IsVSUI() {
        if (GetScreenType() == 3 || GetScreenType() == 6 || mContext.getResources().getString(R.string.use_vsui_volumebar).equals("1")) {
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

    public void InStalloemApk() {
        new Thread() {
            public void run() {
                try {
                    MainSet.this.CopyandInstallAPK(false);
                } catch (Exception e) {
                    Log.e("Exception when sendPointerSync", e.toString());
                }
            }
        }.start();
    }

    public boolean bCheckUpdateFile(boolean bForce) {
        String[] mCtouchPath;
        Log.d(TAG, "bCheckUpdateFile = " + bForce);
        if ((this.bFirstStart && !bForce) || (mCtouchPath = GetMountedStorage()) == null) {
            return false;
        }
        int i = 0;
        while (i < mCtouchPath.length) {
            if (!mCtouchPath[i].contains("udisk") || !TsFile.fileIsExists(String.valueOf(mCtouchPath[i]) + "/" + UPDATE_FLAG)) {
                i++;
            } else {
                if (!IsCustom("LOMU")) {
                    CountdownDialog.initWindow(mContext);
                    CountdownDialog.showView();
                } else if (!TsFile.fileIsExists(String.valueOf(mCtouchPath[i]) + "/" + "License.dat")) {
                    CountdownDialog.initWindow(mContext);
                    CountdownDialog.showView();
                }
                return true;
            }
        }
        return false;
    }

    public boolean CheckMcuUpdateFile() {
        Log.d(TAG, "CheckMcuUpdateFile = ");
        String[] mCtouchPath = GetMountedStorage();
        if (mCtouchPath == null) {
            return false;
        }
        for (int i = 0; i < mCtouchPath.length; i++) {
            if (TsFile.fileIsExists(String.valueOf(mCtouchPath[i]) + "/" + "ts59.bin") || TsFile.fileIsExists(String.valueOf(mCtouchPath[i]) + "/" + "ts59-b.bin")) {
                WinShow.GotoWin(18, 0);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void CopyTsManual() {
        if (TsFile.fileIsExists("/mnt/sdcard/TsManual.pdf")) {
            TsFile.deleteFile(new File("/mnt/sdcard/TsManual.pdf"));
        }
        if (TsFile.fileIsExists("system/tsoem/TsManual.pdf")) {
            TsFile.copyFile("system/tsoem/TsManual.pdf", "/mnt/sdcard/TsManual.pdf");
        }
    }

    public void DelCarplayFile() {
        TsFile.deleteFile(new File("/data/vendor/carplay/carplayaccessoryinfo.xml"));
    }

    /* access modifiers changed from: package-private */
    public boolean CheckAvmFileSize() {
        for (int i = 0; i < this.avm_must_file.length; i++) {
            Log.d(TAG, "CheckAvmFileSize = " + this.avm_must_file[i]);
            File avmfile = new File(AVM_FOLD + this.avm_must_file[i]);
            File avmfilesys = new File(SYSTEM_AVM_FOLD + this.avm_must_file[i]);
            Log.d(TAG, "CheckAvmFileSize avmfile= " + avmfile);
            Log.d(TAG, "CheckAvmFileSize avmfilesys= " + avmfilesys);
            if (avmfile == null || avmfilesys == null) {
                return false;
            }
            Log.d(TAG, "CheckAvmFileSize avmfile.length()= " + avmfile.length());
            Log.d(TAG, "CheckAvmFileSize avmfilesys.length()= " + avmfilesys.length());
            if (avmfile.length() != avmfilesys.length()) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void AvmSetFile(boolean force) {
        Log.d(TAG, "AvmSetFile = " + force);
        File myfile = new File(AVM_FOLD);
        if (myfile.isDirectory()) {
            File[] lfileFiles = myfile.listFiles();
            if (!CheckAvmFileSize() || force) {
                for (int i = 0; i < lfileFiles.length; i++) {
                    if (lfileFiles[i].getName().equals("params.txt")) {
                        Log.d(TAG, "params file  not delete ");
                    } else if (lfileFiles[i].getName().equals("params_new.txt")) {
                        Log.d(TAG, "params file  not delete ");
                    } else if (lfileFiles[i].getName().equals("licence_plate.png")) {
                        Log.d(TAG, "licence_plate file  not delete ");
                    } else {
                        Log.d(TAG, "delete file ==" + lfileFiles[i].getName());
                        lfileFiles[i].delete();
                    }
                }
                File myfilel = new File(SYSTEM_AVM_FOLD);
                if (myfilel.isDirectory()) {
                    File[] avmfile = myfilel.listFiles();
                    for (int j = 0; j < avmfile.length; j++) {
                        TsFile.copyFile(SYSTEM_AVM_FOLD + avmfile[j].getName(), AVM_FOLD + avmfile[j].getName());
                    }
                }
            }
        }
    }

    public void DeleteTempFile() {
        String[] DeleteValidStr = {"AmapAutoLog", "Baidu", "DCIM", "Download", "kwmusiccar", "atclog", "UsbCamera"};
        for (int i = 0; i < DeleteValidStr.length; i++) {
            TsFile.deleteFile(new File("/mnt/sdcard/" + DeleteValidStr[i]));
            new File("/mnt/sdcard/" + DeleteValidStr[i]).delete();
        }
    }

    public void LoadFtSetFile() {
        Log.d(TAG, "LoadFtSetFile = ");
        int ret2 = FtSet.LoadFromSd();
        if (ret2 > 0) {
            if (ret2 <= 2) {
                Toast.makeText(mContext, mContext.getResources().getString(R.string.str_fs_sd_import_reset), 0).show();
            } else {
                Toast.makeText(mContext, mContext.getResources().getString(R.string.str_fs_usb_import_reset), 0).show();
            }
            UpdateSysLanguage();
            writeFtsetLauncher(true);
            FsBaseActivity.SyncMetZoneData();
        }
    }

    public void ResetTheFirstFlag() {
        String path = mContext.getFilesDir().getAbsolutePath();
        if (TsFile.fileIsExists(String.valueOf(path) + "/ceshi.txt")) {
            TsFile.deleteFile(new File(String.valueOf(path) + "/ceshi.txt"));
        }
    }

    public boolean bFirstStart() {
        return this.bFirstStart;
    }

    /* access modifiers changed from: package-private */
    public void DeleteTheAutoDateFile() {
        String[] mCtouchPath = GetMountedStorage();
        if (mCtouchPath != null) {
            int i = 0;
            while (i < mCtouchPath.length) {
                if (mCtouchPath[i].contains("cdfs") || !TsFile.fileIsExists(String.valueOf(mCtouchPath[i]) + "/" + AUTOUPDATE_FLAG)) {
                    i++;
                } else {
                    TsFile.deleteFile(new File(String.valueOf(mCtouchPath[i]) + "/" + UPDATE_FLAG));
                    TsFile.deleteFile(new File(String.valueOf(mCtouchPath[i]) + "/" + UPDATE_FILE));
                    TsFile.deleteFile(new File(String.valueOf(mCtouchPath[i]) + "/" + AUTOUPDATE_FLAG));
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void SetDisappearApp() {
    }

    /* access modifiers changed from: package-private */
    public byte[] StrToByte128(String str) {
        byte[] naviname2 = new byte[128];
        byte[] naviname = str.getBytes();
        for (int i = 0; i < naviname.length; i++) {
            naviname2[i] = naviname[i];
        }
        return naviname2;
    }

    public void ResetMapPath() {
        if (GetNaviPath().equals("com.autonavi.amapauto") && !GetInstance().IsHaveApk("com.autonavi.amapauto")) {
            if (GetInstance().IsHaveApk("com.google.android.apps.maps")) {
                StSet.SetNaviName(StrToByte128("Maps"));
                StSet.SetNaviPack(StrToByte128("com.google.android.apps.maps"));
                StSet.SetSave();
                return;
            }
            StSet.SetNaviName(StrToByte128("  "));
            StSet.SetNaviPack(StrToByte128("  "));
            StSet.SetSave();
        }
    }

    public boolean bCheckFist() {
        if (mContext != null) {
            String path = mContext.getFilesDir().getAbsolutePath();
            if (!TsFile.fileIsExists(String.valueOf(path) + "/ceshi.txt")) {
                this.bFirstStart = true;
                MiraVisionJni.nativeSetPictureMode(1);
                CarplayControl.SetCarplayDiable();
                if (isZh()) {
                    Settings.System.putString(mContext.getContentResolver(), "time_12_24", "24");
                    Log.i(TAG, "set to 24 = ");
                    if (isrSimpZh()) {
                        SetTimeZone("Asia/Shanghai");
                    } else {
                        SetTimeZone("Asia/Taipei");
                    }
                }
                if (IsSupprotRemotecontrol()) {
                    SetQBAirFly(false);
                } else {
                    SetQBAirFly(true);
                }
                TsFile.deleteFile(new File("/mnt/sdcard/test.bin"));
                DeleteTempFile();
                CopyTsManual();
                AvmSetFile(false);
                DelCarplayFile();
                if (IsTwcjw()) {
                    LanguageUtils.updateLanguage(Locale.TAIWAN);
                    SetTimeZone("Asia/Taipei");
                } else {
                    UpdateSysLanguage();
                }
                try {
                    TsFile.writeFileSdcardFile(String.valueOf(path) + "/ceshi.txt", "ceshi");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FsBaseActivity.SyncMetZoneData();
                tool.GetInstance().DealSu("sync");
            }
            if (FtSet.GetUpdateReq() == 1) {
                FtSet.SetUpdateReq(0);
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

    public static boolean isZh() {
        if (Locale.getDefault().getLanguage().endsWith("zh")) {
            return true;
        }
        return false;
    }

    public static boolean isrTwZh() {
        if (Locale.getDefault().getCountry().equals("TW")) {
            return true;
        }
        return false;
    }

    public static boolean isrKoren() {
        if (Locale.getDefault().getCountry().equals("KR")) {
            return true;
        }
        return false;
    }

    public static boolean isrJp() {
        if (Locale.getDefault().getCountry().equals("JP")) {
            return true;
        }
        return false;
    }

    public static boolean isrSimpZh() {
        if (Locale.getDefault().getCountry().equals("CN")) {
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
        if (str.equals("1280x720")) {
            return 10;
        }
        if (str.equals("1920x1080")) {
            return 11;
        }
        return 1;
    }

    public boolean IsHaveService(String ServiceName) {
        if (mContext != null) {
            for (ActivityManager.RunningServiceInfo service : ((ActivityManager) mContext.getSystemService(SdkConstants.TAG_ACTIVITY)).getRunningServices(Integer.MAX_VALUE)) {
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
            if (p.packageName != null && p.packageName.equals(PackName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsAvalidPackName(String str) {
        if (mContext != null && mContext.getPackageManager().getLaunchIntentForPackage(str) == null) {
            return false;
        }
        String[] inValidStr = {"com.ts.", "com.mediatek.", "com.autochips.", "com.tencent.", "com.antutu.", "com.example.mywidget", "com.anwensoft.cardvr", "com.mxtech.videoplayer.pro", "com.pachira.tsp.assist", "com.txznet.music", "com.baidu.appsearch", "com.estrongs.android.pop", "com.baidu.browser.apps", "com.baidu.input", "com.kugou", "net.easyconn", "com.nd.assistance", "com.txznet.webchat", "com.android.calculator", "com.android.calendar", "com.android.deskclock", "com.android.providers.downloads", "com.android.settings", "com.android.browser", "com.android.chrome", "com.android.vending", "com.android.soundrecorder", "com.android.contacts", "com.android.mms", "com.android.gallery", "net.qihoo.launcher.widget.clockweather", "com.mapgoo.diruite", "com.glsx.ddbox", "net.mapgoo.m10010", "com.google.android.apps.plus", "com.google.android.gm", "com.google.android.gms", MainUI.GOOGLE_SPEECH_PNAME, "com.android.dialer", "cn.manstep.phonemirror", "com.edog.car", "com.example.specision", "RepliGo Reader", "cn.kuwo.kwmusiccar", "com.hongfans.rearview", "com.ludashi.benchmark", "com.adasplus.ts", "com.synmoon.usbcamera", "com.android.soundrecorder", "com.cerience.reader.app", "com.ex.dabplayer.pad", "com.iflytek.inputmethod", "com.forfan.wenavi", "es.voghdev.pdfviewpager", "com.didi365.miudrive.navi", "com.UCMobile", "com.android.documentsui", "com.android.email", "com.google.android.youtube", "com.ts.gpstest", MainUI.TS_CARPLAY_PNAME, "com.android.inputmethod.latin", "com.android.traceur"};
        for (String contains : inValidStr) {
            if (str.contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public void openApplication(Context context, String packageName, String extrakey, String extraval) {
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
                intent.putExtra(extrakey, extraval);
                intent.setComponent(new ComponentName(packageName2, className));
                context.startActivity(intent);
            }
        }
    }

    public Intent GetAppStartIntent(Context context, String packageName) {
        PackageInfo pi;
        try {
            pi = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            pi = null;
            e.printStackTrace();
        }
        if (pi == null) {
            return null;
        }
        Intent resolveIntent = new Intent("android.intent.action.MAIN", (Uri) null);
        resolveIntent.setPackage(pi.packageName);
        ResolveInfo ri = context.getPackageManager().queryIntentActivities(resolveIntent, 0).iterator().next();
        if (ri == null) {
            return null;
        }
        String packageName2 = ri.activityInfo.packageName;
        String className = ri.activityInfo.name;
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setFlags(268435456);
        intent.setComponent(new ComponentName(packageName2, className));
        Log.i(TAG, "GetAppStartIntent= " + className);
        return intent;
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

    public static String GetHMIVersionToClient() {
        if (mContext.getResources().getString(R.string.custom_num_show).equals("TSKJ")) {
            return String.valueOf(mContext.getResources().getString(R.string.custom_num)) + GetHMIVersion();
        }
        return String.valueOf(mContext.getResources().getString(R.string.custom_num_show)) + GetHMIVersion();
    }

    public static String GetHMIVersion() {
        String VersionStr;
        String VersionStr2;
        if (mContext == null) {
            return MainVerSion.HMIVER;
        }
        if (TsFile.fileIsExists(VERSION_FILE)) {
            try {
                return TsFile.readFileSdcardFile(VERSION_FILE);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            String VersionStr3 = String.valueOf(mContext.getResources().getString(R.string.custom_ver_)) + MainVerSion.HMIVER;
            if (GetInstance().IsHaveApk("com.android.vending")) {
                VersionStr = ".Q.F." + VersionStr3;
            } else {
                VersionStr = ".Q." + VersionStr3;
            }
            if (Iop.DspSupport() == 1) {
                VersionStr2 = ".D" + VersionStr;
            } else {
                VersionStr2 = ".C" + VersionStr;
            }
            return ".G5" + VersionStr2;
        }
    }

    public static String GetMediaVersion() {
        if (Iop.DspSupport() != 1 || Iop.GetDspVer(Dspversiono) < 1) {
            return MainVerSion.MMP_VERSION;
        }
        return String.valueOf(MainVerSion.MMP_VERSION) + "(" + new String(Dspversiono) + ")";
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

    public int StSetInint() {
        if (this.nStSetRet == -1) {
            this.nStSetRet = StSet.SetInit();
        }
        return this.nStSetRet;
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
        } else if (bShow && this.bShowold && MainVolume.GetInstance().mFloatLayoutNaw == null) {
            this.bShowold = false;
        }
    }

    public int GetSystemBrightness() {
        try {
            return Settings.System.getInt(mContext.getContentResolver(), "screen_brightness");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void SetSystemBrightness(int systemBrightness) {
        Settings.System.putInt(mContext.getContentResolver(), "screen_brightness", systemBrightness);
    }

    /* access modifiers changed from: package-private */
    public void CheckSysBrightness() {
        int nTemp;
        if (StSet.GetBLIll() == 1) {
            if (CanJni.GetCanType() == 311) {
                if (this.nDevBrightness != CanIF.GetMjbFlData()) {
                    this.nDevBrightness = CanIF.GetMjbFlData();
                    Log.i(TAG, "CheckSysBrightness nDevBrightness =" + this.nDevBrightness);
                }
                if (this.nDestBrightness > this.nDevBrightness) {
                    if (this.nDestBrightness - this.nDevBrightness > 10) {
                        this.nDestBrightness -= 10;
                    } else {
                        this.nDestBrightness = this.nDevBrightness;
                    }
                    MainAlterwin.GetInstance().UpdateBrightnessWin(this.nDestBrightness);
                    Log.i(TAG, "CheckSysBrightness nDestBrightness =" + this.nDestBrightness);
                } else if (this.nDestBrightness < this.nDevBrightness) {
                    if (this.nDevBrightness - this.nDestBrightness > 10) {
                        this.nDestBrightness += 10;
                    } else {
                        this.nDestBrightness = this.nDevBrightness;
                    }
                    MainAlterwin.GetInstance().UpdateBrightnessWin(this.nDestBrightness);
                    Log.i(TAG, "CheckSysBrightness nDestBrightness =" + this.nDestBrightness);
                }
            }
            if (this.nSysBrightness != GetSystemBrightness() && StSet.GetBLIll() == 1) {
                StSet.SetBLIllSwitch(0);
                Log.i(TAG, "CheckSysBrightness switch to SysBrightness =");
            }
        }
        if (StSet.GetBLIll() == 0 && this.nSysBrightness != (nTemp = GetSystemBrightness())) {
            this.nSysBrightness = nTemp;
            if (this.nSysBrightness <= 255 && this.nSysBrightness >= 0) {
                MainAlterwin.GetInstance().UpdateBrightnessWin(this.nSysBrightness);
            }
            Log.i(TAG, "CheckSysBrightness nSysBrightness =" + this.nSysBrightness);
        }
    }

    /* access modifiers changed from: package-private */
    public void SyncBrightness() {
        MainAlterwin.GetInstance().ShowBrightnessWin();
        if (StSet.GetBLIll() == 1) {
            this.nDevBrightness = CanIF.GetMjbFlData();
            MainAlterwin.GetInstance().UpdateBrightnessWin(this.nDevBrightness);
            this.nSysBrightness = GetSystemBrightness();
            this.nDestBrightness = CanIF.GetMjbFlData();
            return;
        }
        this.nSysBrightness = GetSystemBrightness();
        MainAlterwin.GetInstance().UpdateBrightnessWin(this.nSysBrightness);
    }

    public void CheckForbidden(String pName, String aName) {
        if (!IsvideoForbiden() || Mcu.BklisOn() != 1 || MainUI.IsCameraMode() != 0) {
            FirbidenWinShow(false);
        } else if (!bIsForbidenWhiteList(pName, aName)) {
            FirbidenWinShow(true);
        } else {
            FirbidenWinShow(false);
        }
    }

    public int DealTask(int nParat) {
        this.nTaskTime++;
        if (this.nTaskTime % 30 == 0) {
            MainUI.GetInstance();
            if (MainUI.IsCameraMode() == 0 && this.ShowBressTime > 0) {
                this.ShowBressTime--;
                if (this.ShowBressTime % 10 == 0) {
                    ShowBrightnessBtn();
                }
            }
            if (MainUI.IsCameraMode() == 0 && nShowScreen == 1 && MainUI.GetInstance().GetMcuState() == 3 && !WinShow.getTopPackName().equals("com.ts.tsclock")) {
                openApplication(mContext, "com.ts.tsclock");
            }
            if (this.bIsForbiden != IsvideoForbiden()) {
                this.bIsForbiden = IsvideoForbiden();
                if (this.bIsForbiden) {
                    SendIntent("net.easyconn.drivemode.opened", "net.easyconn");
                } else {
                    SendIntent("net.easyconn.drivemode.closed", "net.easyconn");
                }
            }
            if (!Support_new_forbidden()) {
                if (IsvideoForbiden() && Mcu.BklisOn() == 1 && MainUI.IsCameraMode() == 0) {
                    String Toppckname = WinShow.getTopPackName();
                    String Topactpckname = WinShow.getTopActivityName();
                    Log.i(TAG, "Toppckname=" + Toppckname);
                    Log.i(TAG, "Topactpckname=" + Topactpckname);
                    if (!bIsForbidenWhiteList(Toppckname, Topactpckname)) {
                        FirbidenWinShow(true);
                    } else {
                        FirbidenWinShow(false);
                    }
                } else {
                    FirbidenWinShow(false);
                }
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
                MainAlterwin.GetInstance().HidenPoweroffWin();
                SetTouchState(false);
            } else {
                MainAlterwin.GetInstance().ShowPowerOffWin();
                SetTouchState(true);
                if (bMonkey) {
                    Mcu.BklisOn();
                }
            }
        }
        MainLight.GetInstance().DealTask();
        return 1;
    }

    public boolean IsvideoForbiden() {
        return StSet.GetDriveVideo() == 1 && Mcu.GetBrake() == 0;
    }

    public boolean IsBlackList(String Str) {
        List<String> pMybList = AppListUtil.getInstance().updateAppList();
        if (pMybList.size() <= 0 || !pMybList.contains(Str)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean bIsForbidenWhiteList(String Pname, String Aname) {
        if (Pname.contains("map") || Pname.contains("navi") || Pname.contains("igo") || Pname.contains("sygic")) {
            return true;
        }
        if (Pname.contains("com.android.") || Pname.contains("com.google.android.")) {
            if (!Pname.contains("youtube") && !Pname.contains("sdvdplayer")) {
                return true;
            }
        } else if (Pname.startsWith("cn.manstep.phonemirrorBox")) {
            return true;
        }
        String[] ValidStr = {"com.ts.MainUI", "com.ts.dvdplayer", "com.android.sdvdplayer", "com.ex.dabplayer.pad", "com.example.specision", "net.easyconn", "com.cerience.reader.app", "android", "com.finedigital.a10.homescreen", "com.waze", "com.ts.tsclock", "com.ts.apk", "com.mediatek.filemanager", "com.ts.gpstest", "com.ts.logoset", "com.syt.tmps", MainUI.TS_CARPLAY_PNAME, "com.autochips.avmplayer", "com.xrross.xlauncher"};
        int i = 0;
        while (i < ValidStr.length) {
            if (!Pname.equals(ValidStr[i])) {
                i++;
            } else if (!Pname.equals("com.ts.dvdplayer") || !(Evc.GetInstance().GetWorkMode() == 3 || Evc.GetInstance().GetWorkMode() == 2)) {
                if (!Pname.equals("com.android.sdvdplayer")) {
                    return true;
                }
                if (Aname == null || !Aname.equals("com.android.sdvdplayer.SDVDPlayer")) {
                    return false;
                }
                return true;
            } else if (Aname == null || (!Aname.equals("com.ts.dvdplayer.DvdSdBoxActivity") && !Aname.equals("com.ts.dvdplayer.SDActivity") && !Aname.equals("com.ts.dvdplayer.dvd.DVDAudioActivity") && !Aname.equals("com.ts.dvdplayer.audio.AudioListActivity") && !Aname.equals("com.ts.dvdplayer.dvd.DVDListActivity"))) {
                return false;
            } else {
                return true;
            }
        }
        String NaviPath = GetNaviPath();
        if (NaviPath != null && NaviPath.equals(Pname)) {
            return true;
        }
        if (mContext.getResources().getString(R.string.custom_use_video_forbiden).equals("0")) {
            return false;
        }
        if (IsBlackList(Pname)) {
            return false;
        }
        if (Pname.equals("com.android.sdvdplayer")) {
            return false;
        }
        return true;
    }

    public boolean IsHaveCamSignal() {
        return BackcarService.getInstance().bSingnalOK();
    }

    public void CheckCamSignal(boolean bCamera) {
    }

    public void SetVideoChannel(int nChannel) {
        Iop.SetVideoChannel(nChannel);
    }

    public boolean bIsLowTxz() {
        return false;
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

    public boolean CopyDemoFile() {
        return tool.GetInstance().CopyDemoData(TS_DEMO_FILE, "/mnt/sdcard");
    }

    /* access modifiers changed from: package-private */
    public boolean CopyFile(String StrPath) {
        boolean bRet = false;
        String[] strSDMountedPath = GetInstance().GetMountedStorage();
        Log.i(TAG, "strSDMountedPath.length ==" + strSDMountedPath.length);
        for (int i = 0; i < strSDMountedPath.length; i++) {
            Log.i(TAG, "strSDMountedPath[i] ==" + strSDMountedPath[i]);
            if (!strSDMountedPath[i].contains("sdcard0") && !strSDMountedPath[i].contains("ctfs") && TsFile.fileIsExists(String.valueOf(strSDMountedPath[i]) + StrPath)) {
                TsFile.deleteFile(new File(TS_DEMO_PATH));
                GetInstance().toastString = "开始拷贝";
                TsFile.copyFolder(String.valueOf(strSDMountedPath[i]) + StrPath, "/mnt/sdcard");
                bRet = true;
            }
        }
        if (bRet) {
            GetInstance().toastString = "拷贝完成";
        }
        return bRet;
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
        } else if (val.equals(mContext.getResources().getString(R.string.custom_csudy_num))) {
            WinShow.GotoWin(19, 0);
        } else if (val.equals(mContext.getResources().getString(R.string.custom_setvcom_num))) {
            new MainVcomWithAcddDialog().createDlg(context, FtSet.GetLCDvcom(), FtSet.GetLCDavdd());
        } else if (val.equals(mContext.getResources().getString(R.string.custom_getid_num))) {
            String sMcuid = GetSerid();
            new MainShowDialog().createDlg(context, sMcuid, "http://xfapp.forfan.com.cn/xfapp/product.php?device=" + sMcuid + "&sn=" + md5(String.valueOf(sMcuid) + "forfan_product"));
        } else if (val.equals(mContext.getResources().getString(R.string.custom_getccid_num))) {
            String str = ((TelephonyManager) mContext.getSystemService("phone")).getSimSerialNumber();
            if (str == null || str.length() <= 10) {
                Toast.makeText(mContext, "SIM卡读取失败", 0).show();
            } else {
                new MainShowDialog().createDlg(context, str, str);
            }
        } else if (val.equals(mContext.getResources().getString(R.string.custom_powerreset_num))) {
            Mcu.SendXKey(20);
        } else if (val.equals(mContext.getResources().getString(R.string.custom_reset_naviwhitelist))) {
            Evc.GetInstance().DelNaviWhileList("ResetDefault");
            Toast.makeText(mContext, "GisWhiteList ResetDefault", 0).show();
        } else if (val.equals(mContext.getResources().getString(R.string.custom_toauth_num))) {
            WinShow.GotoWin(16, 0);
        } else if (val.equals(mContext.getResources().getString(R.string.custom_radom_btname))) {
            FtSet.SetBtId(TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT);
            FtSet.Save(0);
            Toast.makeText(mContext, "蓝牙名称已经修改，重启生效", 0).show();
        } else if (val.equals("12")) {
            if (T3WeakJoinUtils.bIsT3WeakPrj) {
                T3WeakJoinUtils.inputProductId(context);
            } else {
                new MainSerialNumerDialog().createDlg(context, GetInstance().GetProid(), RLF_KOREA_MOUDLENAME);
            }
        } else if (val.equals(mContext.getResources().getString(R.string.custom_bootlogo_num))) {
            if (FtSet.GetOptionSW() == 1) {
                WinShow.GotoWin(17, 0);
            }
        } else if (val.equals(mContext.getResources().getString(R.string.custom_fatctory_num))) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.ts.MainUI", "com.ts.factoryset.FsMainActivity"));
            intent.putExtra("factory", true);
            intent.addFlags(337641472);
            mContext.startActivity(intent);
        } else if (val.equals(mContext.getResources().getString(R.string.custom_shop_num))) {
            WinShow.GotoWin(15, 0);
        } else if (val.equals(mContext.getResources().getString(R.string.custom_filedelete_num))) {
            if (TsFile.fileIsExists(TS_DEMO_PATH)) {
                TsFile.deleteFile(new File(TS_DEMO_PATH));
                new File(TS_DEMO_PATH).delete();
                Toast.makeText(mContext, mContext.getResources().getString(R.string.delete_file_ok), 0).show();
            }
        } else if (val.equals(mContext.getResources().getString(R.string.custom_filecopy_num))) {
            new Thread() {
                public void run() {
                    try {
                        MainSet.this.CopyFile(MainSet.TS_DEMO_FILE);
                        MainSet.this.CopyandInstallAPK(false);
                    } catch (Exception e) {
                        Log.e("Exception when sendPointerSync", e.toString());
                    }
                }
            }.start();
        } else if (val.equals(mContext.getResources().getString(R.string.custom_screenshot_num))) {
            if (MainUI.bIsScreenMode) {
                Toast.makeText(mContext, "关闭截屏模式", 0).show();
                MainUI.bIsScreenMode = false;
                return;
            }
            Toast.makeText(mContext, "双击屏幕截屏", 0).show();
            MainUI.bIsScreenMode = true;
        } else if (val.equals(mContext.getResources().getString(R.string.custom_radio_reset_num))) {
            Radio.TuneTask(8);
        } else if (val.equals(mContext.getResources().getString(R.string.custom_destroy_id_num))) {
            if (!GetInstance().IsMathToMcu()) {
                Toast.makeText(mContext, "注册码清除", 0).show();
                AuthServer.GetInstance().DestroyID();
            }
        } else if (val.equals(mContext.getResources().getString(R.string.custom_reboot_id_num))) {
            SystemReboot();
        } else if (val.equals(mContext.getResources().getString(R.string.custom_display_id_num))) {
            if (mContext != null) {
                FilterWindow.getInstance().initWindow(mContext);
            }
            FilterWindow.getInstance().showView();
        } else if (val.equals(mContext.getResources().getString(R.string.custom_testmode_id_num))) {
            WinShow.show("com.ts.MainUI", "com.ts.main.auth.FactoryMainActivity");
        } else if (val.equals(mContext.getResources().getString(R.string.custom_destroy_id_root_num))) {
            Toast.makeText(mContext, "注册码清除", 0).show();
            AuthServer.GetInstance().DestroyID();
        } else if (val.equals(mContext.getResources().getString(R.string.custom_install_id_num))) {
            new Thread() {
                public void run() {
                    try {
                        MainSet.this.CopyandInstallAPK(true);
                    } catch (Exception e) {
                        Log.e("Exception when sendPointerSync", e.toString());
                    }
                }
            }.start();
        } else if (val.equals(mContext.getResources().getString(R.string.custom_install2_id_num))) {
            new Thread() {
                public void run() {
                    try {
                        MainSet.this.CopyandInstallAPK(false);
                    } catch (Exception e) {
                        Log.e("Exception when sendPointerSync", e.toString());
                    }
                }
            }.start();
        } else if (val.equals(mContext.getResources().getString(R.string.custom_atc_log))) {
            WinShow.show("com.mediatek.engineermode", "com.mediatek.engineermode.EngineerMode");
        } else if (val.equals(mContext.getResources().getString(R.string.custom_atc_factory))) {
            WinShow.show("com.android.settings", "com.autochips.atcsettings.mainui.MainActivity");
        } else if (val.equals(mContext.getResources().getString(R.string.custom_atc_dellog))) {
            CreateLog(false);
        } else if (val.equals(mContext.getResources().getString(R.string.custom_atc_clearlog))) {
            TsFile.deleteFile(new File(TS_LOG_FILE));
            TsFile.deleteFile(new File(ATC_LOG_FILE));
            Toast.makeText(mContext, "TsLog is cleared", 0).show();
        } else if (val.equals(mContext.getResources().getString(R.string.custom_atc_createlog))) {
            CreateLog(true);
        } else if (val.equals(mContext.getResources().getString(R.string.custom_check_update_file))) {
            Log.e(TAG, "com.forfan.systemupgrade 111");
            if (IsHaveApk("com.forfan.systemupgrade")) {
                Log.e(TAG, "com.forfan.systemupgrade 2222");
                if (mContext.getResources().getString(R.string.custom_num_show).equals("TSKJ")) {
                    String str2 = String.valueOf(mContext.getResources().getString(R.string.custom_num)) + GetHMIVersion();
                } else {
                    String str3 = String.valueOf(mContext.getResources().getString(R.string.custom_num_show)) + GetHMIVersion();
                }
                openApplication(mContext, "com.forfan.systemupgrade", "HMI", tool.GetInstance().GetBranchVerSion());
            }
        } else if (val.equals(mContext.getResources().getString(R.string.custom_appinfo_id_num))) {
            WinShow.GotoWin(24, 0);
        } else if (val.equals(mContext.getResources().getString(R.string.custom_mounkey_num))) {
            tool.GetInstance().MounKeyTest();
            bMonkey = true;
        } else if (val.equals(mContext.getResources().getString(R.string.custom_check_state))) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.ts.phonestate");
            intent2.setAction("com.ts.phonestate.PhoneStateService");
            mContext.startService(intent2);
        } else if (val.equals(mContext.getResources().getString(R.string.custom_check_dsp_secret))) {
            if (Iop.DspSupport() == 1 && Iop.GetDspVer(this.Dspversion) == 1) {
                new AlertDialog.Builder(context).setTitle(new String(this.Dspversion)).show();
            }
        } else if (val.equals("0128")) {
            WinShow.show("com.ts.MainUI", "com.ts.can.CanAllUpdateActivity");
        } else if (val.equals(mContext.getResources().getString(R.string.custom_can_data_num))) {
            startCanDataService(mContext);
        } else if (val.equals(mContext.getResources().getString(R.string.custom_mmp_mode))) {
            ComponentName componetName = new ComponentName("com.ts.MainUI", "com.ts.factoryset.FsMPModeActivity");
            Intent intent3 = new Intent();
            intent3.setComponent(componetName);
            intent3.setFlags(268435456);
            mContext.startActivity(intent3);
        } else if (val.equals(mContext.getResources().getString(R.string.custom_enter_disc_update))) {
            tool.GetInstance().checkDiscUpdate((String) null);
        } else if (val.equals(mContext.getResources().getString(R.string.custom_copy_avm_file))) {
            Toast.makeText(mContext, "copy avm file", 0).show();
            GetInstance().AvmSetFile(true);
        } else if (val.equals(mContext.getResources().getString(R.string.custom_getbrance_num))) {
            MainAlterwin.GetInstance().ShowMessageWin(tool.GetInstance().GetBranchVerSion());
        }
    }

    public void CreateLog(boolean bCreate) {
        if (!bCreate) {
            TsFile.deleteFile(new File(TS_LOG_FILE));
            if (new File(TS_LOG_FILE) != null) {
                new File(TS_LOG_FILE).delete();
            }
        } else if (!TsFile.fileIsExists(TS_LOG_FILE)) {
            TsFile.NewDir(TS_LOG_FILE);
            TsLogger.GetInstance().CatlogToSd(TS_LOG_FILE, 99);
            Toast.makeText(mContext, "TsLog is open", 0).show();
        } else {
            Toast.makeText(mContext, "TsLog is opened", 0).show();
        }
    }

    public void SystemReboot() {
        Mcu.SendXKey(21);
        ((PowerManager) mContext.getSystemService("power")).reboot(TXZResourceManager.STYLE_DEFAULT);
    }

    public void SystemReset() {
        SendIntent("CLEAR_LAUNCHER_DATA", (String) null);
        ResetTheFirstFlag();
    }

    public void SystemClear() {
        Log.i(TAG, "SystemClear");
        Evc.GetInstance().evol_workmode_set(0);
        Mcu.SendXKey(21);
        Intent intent = new Intent("android.intent.action.FACTORY_RESET");
        intent.setPackage("android");
        intent.addFlags(268435456);
        intent.putExtra("android.intent.extra.REASON", "MasterClearConfirm");
        intent.putExtra("android.intent.extra.WIPE_EXTERNAL_STORAGE", 0);
        intent.putExtra("com.android.internal.intent.extra.WIPE_ESIMS", 0);
        mContext.sendBroadcast(intent);
    }

    /* access modifiers changed from: package-private */
    public void CopyandInstallAPK(boolean bcopy) {
        File myfile;
        String installPath = TXZResourceManager.STYLE_DEFAULT;
        String[] strSDMountedPath = GetInstance().GetMountedStorage();
        Log.i(TAG, "strSDMountedPath.length ==" + strSDMountedPath.length);
        for (int i = 0; i < strSDMountedPath.length; i++) {
            Log.i(TAG, "strSDMountedPath[i] ==" + strSDMountedPath[i]);
            if (!strSDMountedPath[i].contains("sdcard0") && !strSDMountedPath[i].contains("ctfs") && TsFile.fileIsExists(String.valueOf(strSDMountedPath[i]) + "/tsoem")) {
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
        }
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

    public String tranalateIntoString(int freq) {
        String text = new StringBuilder(String.valueOf(freq)).toString();
        StringBuilder sb = new StringBuilder(text);
        if (text.length() < 3) {
            return TXZResourceManager.STYLE_DEFAULT;
        }
        sb.insert(text.length() - 2, ".");
        return sb.toString();
    }

    public int GetXYRange() {
        int mScrW = MainUI.GetSrcW();
        int mScrH = MainUI.GetSrcH();
        int nTouchX = MainUI.GetTouchX();
        int nTouchY = MainUI.GetTouchY();
        Log.i(TAG, "mScrW==" + mScrW);
        Log.i(TAG, "mScrH==" + mScrH);
        Log.i(TAG, "nTouchX==" + nTouchX);
        Log.i(TAG, "nTouchY==" + nTouchY);
        if (mScrW == 800 && mScrH == 480 && nTouchX != 800 && nTouchY != 480) {
            mScrW = 1024;
            mScrH = 600;
        }
        if (mScrW == 800 && mScrH == 600 && nTouchX != 800 && nTouchY != 600) {
            mScrW = 1024;
            mScrH = 768;
        }
        if (nTouchX <= mScrW && nTouchY <= mScrH && (nTouchX != 0 || nTouchY != 0)) {
            return 0;
        }
        if (GetScreenType() == 3) {
            if (nTouchY < mScrH) {
                mScrH = nTouchY;
            }
            if (nTouchX < mScrW) {
                mScrW = nTouchX;
            }
        }
        return (65536 * mScrW) + mScrH;
    }

    public static int IsSysapk(String packName) {
        if (mContext == null) {
            return 0;
        }
        int appFlags = 0;
        try {
            appFlags = mContext.getPackageManager().getApplicationInfo(packName, 0).flags;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appFlags & 1;
    }

    private static long getAvailMemory(Context context) {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService(SdkConstants.TAG_ACTIVITY)).getMemoryInfo(mi);
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

    public int[] GetCanEnable() {
        return null;
    }

    public static void PushActivityForMul(int WordMode, boolean bMul) {
        Log.i(TAG, "PushActivityForMul: WordMode==" + WordMode + "bMul==" + bMul);
    }

    public static boolean isHaveAMBand() {
        switch (FtSet.GetRadioIc()) {
            case 2:
            case 3:
                return false;
            default:
                if (FtSet.GetMWnum() != 0) {
                    return true;
                }
                return false;
        }
    }

    public void ExportSysInfo() {
        String fileName = null;
        String[] mCtouchPath = GetInstance().GetMountedStorage();
        if (mCtouchPath == null) {
            Log.i(TAG, "ExportSysInfo==no storage");
            return;
        }
        for (int i = 0; i < mCtouchPath.length; i++) {
            if (TsFile.fileIsExists(String.valueOf(mCtouchPath[i]) + "/" + Factory_name)) {
                Log.i(TAG, "ExportSysInfo get  storage==" + mCtouchPath[i]);
                fileName = mCtouchPath[i];
            }
        }
        if (fileName != null) {
            String fileName2 = String.valueOf(fileName) + "/tssysteminfo.txt";
            if (TsFile.fileIsExists(fileName2)) {
                TsFile.deleteFile(new File(fileName2));
            }
            BufferedWriter out = null;
            try {
                BufferedWriter out2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName2, true)));
                try {
                    if (Settings.Global.getInt(mContext.getContentResolver(), "auto_time_zone") != 0) {
                        try {
                            out2.write("timezoneauto=1\r\n");
                            out = out2;
                        } catch (IOException e) {
                            e.printStackTrace();
                            out = out2;
                        }
                        try {
                            out.write("timezone=" + TimeZone.getDefault().getID() + "\r\n");
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        Log.i(TAG, "getDisplayName(true, TimeZone.SHORT)==" + TimeZone.getDefault().getDisplayName(true, 0));
                        Log.i(TAG, "getTimeZone==" + TimeZone.getDefault().getID());
                    }
                    try {
                        out2.write("timezoneauto=0\r\n");
                        out = out2;
                    } catch (IOException e4) {
                        e4.printStackTrace();
                        out = out2;
                    }
                    out.write("timezone=" + TimeZone.getDefault().getID() + "\r\n");
                    out.close();
                    Log.i(TAG, "getDisplayName(true, TimeZone.SHORT)==" + TimeZone.getDefault().getDisplayName(true, 0));
                    Log.i(TAG, "getTimeZone==" + TimeZone.getDefault().getID());
                } catch (Settings.SettingNotFoundException e5) {
                    try {
                        e5.printStackTrace();
                        out = out2;
                    } catch (FileNotFoundException e6) {
                        e = e6;
                        out = out2;
                        e.printStackTrace();
                        out.write("timezone=" + TimeZone.getDefault().getID() + "\r\n");
                        out.close();
                        Log.i(TAG, "getDisplayName(true, TimeZone.SHORT)==" + TimeZone.getDefault().getDisplayName(true, 0));
                        Log.i(TAG, "getTimeZone==" + TimeZone.getDefault().getID());
                    }
                }
            } catch (FileNotFoundException e7) {
                e = e7;
                e.printStackTrace();
                out.write("timezone=" + TimeZone.getDefault().getID() + "\r\n");
                out.close();
                Log.i(TAG, "getDisplayName(true, TimeZone.SHORT)==" + TimeZone.getDefault().getDisplayName(true, 0));
                Log.i(TAG, "getTimeZone==" + TimeZone.getDefault().getID());
            }
        }
    }

    public void ReSetTimeZone() {
        String[] mCtouchPath = GetInstance().GetMountedStorage();
        if (mCtouchPath == null) {
            Log.i(TAG, "ReSetTimeZone==no storage");
            return;
        }
        for (int i = 0; i < mCtouchPath.length; i++) {
            if (TsFile.fileIsExists(String.valueOf(mCtouchPath[i]) + "/" + systeminfo_name)) {
                Log.i(TAG, "ReSetTimeZone get  storage==" + mCtouchPath[i]);
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(mCtouchPath[i]) + "/" + systeminfo_name));
                    try {
                        String[] strArr = new String[2];
                        while (true) {
                            try {
                                String s = bufferedReader.readLine();
                                if (s == null) {
                                    break;
                                }
                                String[] p = s.split("=");
                                if (p[0].equals(systemtimezonesw)) {
                                    Log.i(TAG, "ReSetTimeZone timezoneauto ==" + p[1]);
                                    if (p[1].equals("1")) {
                                        Settings.Global.putInt(mContext.getContentResolver(), "auto_time_zone", 1);
                                    } else {
                                        Settings.Global.putInt(mContext.getContentResolver(), "auto_time_zone", 0);
                                    }
                                } else if (p[0].equals(systemtimezoneinfo)) {
                                    Log.i(TAG, "ReSetTimeZone timezone ==" + p[1]);
                                    SetTimeZone(p[1]);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        BackupManager.dataChanged("com.android.providers.settings");
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        BufferedReader bufferedReader2 = bufferedReader;
                    }
                } catch (FileNotFoundException e4) {
                    e = e4;
                    e.printStackTrace();
                }
            }
        }
    }

    public void UpdateSysInput() {
        if (isrSimpZh()) {
            if (IsHaveApk("com.baidu.input")) {
                SetDefaultInput("com.baidu.input/.ImeService");
            } else if (IsHaveApk("com.iflytek.inputmethod")) {
                SetDefaultInput("com.iflytek.inputmethod/.FlyIME");
            } else if (IsHaveApk("com.sohu.inputmethod.sogou")) {
                SetDefaultInput("com.sohu.inputmethod.sogou/.SogouIME");
            }
        } else if (isrTwZh()) {
            if (IsHaveApk("com.google.android.apps.inputmethod.zhuyin")) {
                SetDefaultInput("com.google.android.apps.inputmethod.zhuyin/.ZhuyinInputMethodService");
            }
        } else if (isrKoren()) {
            if (IsHaveApk("com.google.android.inputmethod.korean")) {
                SetDefaultInput("com.google.android.inputmethod.korean/.KoreanIme");
            }
        } else if (isrJp()) {
            if (IsHaveApk("com.google.android.inputmethod.japanese")) {
                SetDefaultInput("com.google.android.inputmethod.japanese/.MozcService");
            }
        } else if (IsHaveApk("com.android.inputmethod.latin")) {
            SetDefaultInput("com.android.inputmethod.latin/.LatinIME");
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
                LanguageUtils.updateLanguage(new Locale(SdkConstants.UNIT_PT, "PT"));
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
                LanguageUtils.updateLanguage(new Locale(SdkConstants.UNIT_PT, "BR"));
                return;
            case 18:
                LanguageUtils.updateLanguage(new Locale("vi", "VN"));
                return;
            case 19:
                LanguageUtils.updateLanguage(new Locale("uk", "UA"));
                return;
            case 20:
                LanguageUtils.updateLanguage(new Locale("el", "GR"));
                return;
            default:
                return;
        }
    }

    public String GetNaviPath() {
        GetInstance().StSetInint();
        byte[] byteNavipath = new byte[128];
        StSet.GetNaviPack(byteNavipath);
        return CanIF.byte2String(byteNavipath);
    }

    public boolean bIsValid() {
        return this.ret == 0;
    }

    public void NetCheck() {
        this.ret = -1;
        new Thread() {
            public void run() {
                try {
                    Process p = Runtime.getRuntime().exec("ping -c 3 www.baidu.com");
                    MainSet.this.ret = p.waitFor();
                    Log.i(MainSet.TAG, "Process:" + MainSet.this.ret);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void RefreshDialog(Activity act, AlertDialog dialog) {
        Window dialogWindow = dialog.getWindow();
        Display d = act.getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.width = (int) (((double) d.getWidth()) * 0.5d);
        p.gravity = 17;
        dialogWindow.setAttributes(p);
    }

    /* access modifiers changed from: package-private */
    public void SetQBAirFly(boolean enabling) {
        Settings.Global.putInt(mContext.getContentResolver(), "qb_airplane_mode_on", enabling ? 1 : 0);
    }

    public void SetFlyMode(boolean enable) {
        Settings.Global.putInt(mContext.getContentResolver(), "airplane_mode_on", enable ? 1 : 0);
        Intent intent = new Intent("android.intent.action.AIRPLANE_MODE");
        intent.putExtra(IConstantData.KEY_STATE, enable);
        mContext.sendBroadcast(intent);
    }

    public void SwitchWiress(boolean bEn) {
        SetFlyMode(!bEn);
    }

    public void SetWifiMode(boolean bEn) {
        WifiManager wifiManager = (WifiManager) mContext.getSystemService("wifi");
        if (wifiManager == null) {
            return;
        }
        if (bEn && !wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        } else if (!bEn && wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
    }

    /* access modifiers changed from: package-private */
    public void SetNetConState(boolean mobileDataEnabled) {
        TelephonyManager telephonyService = (TelephonyManager) mContext.getSystemService("phone");
        Method setMobileDataEnabledMethod = null;
        try {
            setMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("setDataEnabled", new Class[]{Boolean.TYPE});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (setMobileDataEnabledMethod != null) {
            try {
                setMobileDataEnabledMethod.invoke(telephonyService, new Object[]{Boolean.valueOf(mobileDataEnabled)});
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (IllegalArgumentException e3) {
                e3.printStackTrace();
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
            }
        }
    }

    public void GetNetTime() {
        if (Locale.getDefault().getCountry().equals("CN") && this.mAuthorServer != null) {
            try {
                this.mAuthorServer.sendCmd("getnettime===888");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void bindAuthorService() {
        Intent intent = new Intent("com.ts.AuthorService");
        intent.setPackage("com.ts.activate");
        mContext.bindService(intent, this.authorServiceConn, 1);
    }

    public static String getNetworkTime() {
        if (!Locale.getDefault().getCountry().equals("CN")) {
            return TXZResourceManager.STYLE_DEFAULT;
        }
        Log.w(TAG, "net is unconnect");
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
        return TXZResourceManager.STYLE_DEFAULT;
    }

    public int GetCamType() {
        return FtSet.GetCamType();
    }

    public void onBtStateChange(BluetoothDevice device, int state) {
        WirelessCarplay.getInstance().onBtStateChange(device, state);
    }

    public void onApplicationCreate(Context context) {
        WirelessCarplay.getInstance().onApplicationCreate(context);
    }
}
