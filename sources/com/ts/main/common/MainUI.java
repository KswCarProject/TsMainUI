package com.ts.main.common;

import android.app.ActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.icu.util.Calendar;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.storage.IMountService;
import android.os.storage.StorageEventListener;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;
import com.adasplus.ts.IAdasBinderInterface;
import com.hongfans.carmedia.Constant;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.mediatek.miravision.setting.MiraVisionJni;
import com.ts.MainUI.AuthServer;
import com.ts.MainUI.CstTv;
import com.ts.MainUI.Evc;
import com.ts.MainUI.EvcCallBack;
import com.ts.MainUI.GetID;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.StTpms;
import com.ts.MainUI.TaskCallBack;
import com.ts.MainUI.TsDisplay;
import com.ts.MainUI.TsFile;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.BackcarService;
import com.ts.bt.BtExe;
import com.ts.bt.BtServiceBinder;
import com.ts.bt.ITsBtService;
import com.ts.can.CanCameraUI;
import com.ts.can.CanIF;
import com.ts.dvdplayer.ITsPlayerService;
import com.ts.dvdplayer.definition.MediaDef;
import com.ts.factoryset.FsBaseActivity;
import com.ts.main.auth.FactoryTestWin;
import com.ts.main.common.ITsCommon;
import com.ts.main.ecar.ECarCommunication;
import com.ts.main.radio.RadioServiceBinder;
import com.ts.main.txz.AmapAuto;
import com.ts.main.txz.Glsx;
import com.ts.main.txz.TxzReg;
import com.txznet.sdk.TXZCallManager;
import com.txznet.sdk.bean.Poi;
import com.txznet.sdk.media.InvokeConstants;
import com.txznet.sdk.tongting.IConstantData;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;
import com.yyw.ts70xhw.Radio;
import com.yyw.ts70xhw.StSet;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainUI extends Service implements TaskCallBack, EvcCallBack {
    public static final String ACTION_MAINUI_ACCOFF = "com.ts.main.uiaccoff";
    public static final String ACTION_MAINUI_ACCON = "com.ts.main.uiaccon";
    public static final String ACTION_RECOGNIZE_CMD = "com.globalconstant.BROADCAST_SEND_CMD";
    public static final String ACTION_RECOGNIZE_CMD_B = "com.globalconstant.BROADCAST_CAR_SEND_CMD";
    public static final String ACTION_RECOGNIZE_CMD_MIC_CLICK = "com.globalconstant.BROADCAST_MIC_CLICK";
    public static final String ACTION_RECOGNIZE_CMD_REGISTER_S = "com.globalconstant.BROADCAST_register_status";
    public static final String ACTION_RECOGNIZE_CMD_VOLUME = "com.globalconstant.BROADCAST_SEND_VOLUME";
    public static final String ACTION_RECOGNIZE_TTSINFO_CMD = "com.globalconstant.BROADCAST_TTS_INFO";
    static final String ADASLICENSE_FILE = "/mnt/sdcard/EasyConnected/adas.bmp";
    public static final String BASIC_TIME_STRING = "2016-10-01 00:00:00";
    public static final String BROADCAST_ADAS_LIGHT_STATE = "forfan.adas.light_state";
    public static final String BROADCAST_ADAS_TURNLIGHT_STATE = "forfan.adas.turnlight_state";
    public static final String BROADCAST_APP_QUIT = "net.easyconn.app.quit";
    public static final String BROADCAST_AUTOKING_START_UPDATE_SYSTEM = "com.autoking.start.update.system";
    public static final String BROADCAST_AUTOKING_UPDATE_SYSTEM_SUCCESS = "com.autoking.update.system.success";
    public static final String BROADCAST_BT_A2DP_ACQUIRE = "net.easyconn.a2dp.acquire";
    public static final String BROADCAST_BT_A2DP_RELEASE = "net.easyconn.a2dp.release";
    public static final String BROADCAST_BT_CHECKSTATUS = "net.easyconn.bt.checkstatus";
    public static final String BROADCAST_BT_CONNECT = "net.easyconn.bt.connect";
    public static final String BROADCAST_BT_CONNECTED = "net.easyconn.bt.connected";
    public static final String BROADCAST_BT_NOT_CONNECTED = "net.easyconn.bt.notconnected";
    public static final String BROADCAST_BT_OPENED = "net.easyconn.bt.opened";
    public static final String BROADCAST_CAMERA_SWITCH_STRING = "forfan.camera.switch";
    public static final String BROADCAST_CLOCK_SCREEN_MUTE = "android.intent.action.CLOCK_SCREEN_MUTE";
    public static final String BROADCAST_CONNECTION_BREAK = "net.easyconn.connection.break";
    public static final String BROADCAST_GLSX_ACC_OFF = "com.glsx.boot.ACCOFF";
    public static final String BROADCAST_GLSX_ACC_ON = "com.glsx.boot.ACCON";
    public static final String BROADCAST_GLSX_VOICE = "com.glsx.ddbox.action.VOICE";
    public static final String BROADCAST_LANCHER_FUNC_ACCELERATION = "forfan.intent.action.ACCELERATION";
    public static final String BROADCAST_LANCHER_FUNC_BLUETOOTH = "forfan.intent.action.BLUETOOTH";
    public static final String BROADCAST_LANCHER_FUNC_BRIGHTNESS = "forfan.intent.action.BRIGHTNESS";
    public static final String BROADCAST_LANCHER_FUNC_MUTE = "forfan.intent.action.MUTE";
    public static final String BROADCAST_LANCHER_FUNC_NOWMODE = "forfan.intent.action.MODE";
    public static final String BROADCAST_LANCHER_FUNC_REBOOT = "forfan.intent.actionREBOOT";
    public static final String BROADCAST_LANCHER_FUNC_SCREENOFF = "forfan.intent.action.SCREENOFF";
    public static final String BROADCAST_LANCHER_FUNC_VOLUME = "forfan.intent.action.VOLUME";
    public static final String BROADCAST_LANCHER_FUNC_VOLUMEADD = "forfan.intent.action.VOLUMEUP";
    public static final String BROADCAST_LANCHER_FUNC_VOLUMEDEC = "forfan.intent.action.VOLUMEDN";
    public static final String BROADCAST_MEDIA_LISTUPDATE = "com.ts.media.listupdate";
    public static final String BROADCAST_NET_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
    public static final String BROADCAST_RIGHT_CAMERA_SWITCH_STRING = "forfan.right.camera.switch";
    private static final String CTOUCH_CELE = "3561万能触摸矫正.cfg";
    static final String EASYLICENSE_FILE = "/mnt/sdcard/EasyConnected/License.ini";
    static final String EASYLICENSE_PATH = "/mnt/sdcard/EasyConnected";
    public static final String KAIYI_PNAME = "com.adasplus.ts";
    public static final int RUN_ACCOFF = 2;
    public static final int RUN_BATOFF = 4;
    public static final int RUN_COMMON = 0;
    public static final int RUN_POWEROFF = 3;
    static final String SIM_SCCID_FILE = "/mnt/sdcard/EasyConnected/.sccid";
    public static final int SYNC_ERROR = 0;
    public static final int SYNC_OK = 1;
    private static final String TAG = "MainUI";
    public static final String TXZ_GET_WWATHER_DATA = "txz_get_weather_data";
    public static final int VOICE_DELAY = 500;
    public static final int VOICE_DELAYGL = 300;
    static boolean bBtConnect = false;
    public static boolean bEnterMode = false;
    public static boolean bFrontCam = false;
    static boolean bHaveCall = false;
    public static boolean bIsInCamera = false;
    public static boolean bIsInRight = false;
    public static boolean bIsScreenMode = false;
    static boolean bSimConnect;
    public static boolean bSupportFoucus = false;
    public static boolean bSupportMute = true;
    public static CanInterface mCanInterface = null;
    /* access modifiers changed from: private */
    public static MainUI mMainUI = null;
    public static int mScrH = CanCameraUI.BTN_GOLF_WC_MODE1;
    public static int mScrW = 1024;
    public static int nBklCanOn = 0;
    static int nCamVport = 0;
    static long nCheckNum = 0;
    private static int nCheckSimState = 0;
    public static int nDelayToCamera = 30;
    static int nFirst = 1;
    public static int nMcuRet = 0;
    /* access modifiers changed from: private */
    public static int nSimOwner = 0;
    static int nTickNum = 0;
    static int nnUM = 0;
    public boolean CheckDisk = false;
    long ClockTime = 0;
    /* access modifiers changed from: private */
    public float GpsCog = 0.0f;
    public double GpsLang = 0.0d;
    public double GpsLat = 0.0d;
    float GpsSpeed = 0.0f;
    private final String LAUNCHER_TO_ACTIVITY = "ActivityWarm_Closed";
    private final String LAUNCHER_TO_ACTIVITY_REQUEST = "ActivityWarm_Closed_request";
    String MyoldState = "00";
    long TimeTicket = 0;
    int TotalSize = 0;
    int antennaPower = -1;
    AudioManager audioManager;
    private boolean bCaninit = false;
    boolean bShutDown = false;
    public boolean bSupportECar = false;
    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            MainUI.this.GpsSpeed = (float) (((double) location.getSpeed()) * 3.6d);
            MainUI.this.GpsCog = location.getBearing();
            MainUI.this.GpsLat = location.getLatitude();
            MainUI.this.GpsLang = location.getLongitude();
            AmapAuto.GetInstance().SetGpsInfo((int) MainUI.this.GpsCog, (int) MainUI.this.GpsSpeed);
            if (MainUI.this.nUpdateTime == 0) {
                MainUI.this.nUpdateTime = 1;
                SystemClock.setCurrentTimeMillis(location.getTime());
            }
        }

        public void onProviderDisabled(String arg0) {
        }

        public void onProviderEnabled(String arg0) {
        }

        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
        }
    };
    private ActivityManager mActivityManager = null;
    private AdasServiceConnection mAdasServiceConnection = new AdasServiceConnection();
    /* access modifiers changed from: private */
    public ITsBtService mBTCommon = new BtServiceBinder();
    private TsDisplay mDisplay = TsDisplay.GetInstance();
    easyConnectRecevie mEasyConnextReceiver;
    /* access modifiers changed from: private */
    public Evc mEvc = Evc.GetInstance();
    public IAdasBinderInterface mIAdasBinderInterface = null;
    /* access modifiers changed from: private */
    public KeyTouch mKeyTouch = KeyTouch.GetInstance();
    private MainCScreen mMainCScreen = MainCScreen.GetInstance();
    MainSet mMainSet = MainSet.GetInstance();
    /* access modifiers changed from: private */
    public MainVolume mMainVolume = MainVolume.GetInstance();
    /* access modifiers changed from: private */
    public CanDataInfo.CAN_OutTmp mOutTemp = new CanDataInfo.CAN_OutTmp();
    /* access modifiers changed from: private */
    public ITsRadioCommon mRadioCommon = new RadioServiceBinder();
    private DvpServiceConnection mServiceConnection = new DvpServiceConnection();
    private CanDataInfo.CAN_Speed mSpeed = new CanDataInfo.CAN_Speed();
    StTpms mStTpms = StTpms.GetInstance();
    StorageEventListener mStorageListener = new StorageEventListener() {
        public void onStorageStateChanged(String path, String oldState, String newState) {
            Log.i(MainUI.TAG, "onStorageStateChanged" + path + ":" + oldState + "--->" + newState);
            Log.i(MainUI.TAG, "MyoldState" + MainUI.this.MyoldState);
            if (newState.equals("mounted")) {
                Log.i(MainUI.TAG, String.valueOf(path) + ":" + oldState + "--->" + newState);
                if (MainSet.IsRxfKoren()) {
                    SystemProperties.set("forfan.mediascaner.enable", MainSet.SP_XPH5);
                }
                if (MainSet.IsFlkj()) {
                    if (TsFile.fileIsExists(String.valueOf(path) + "/flkj.test")) {
                        FactoryTestWin.GetInstance().Inint(MainUI.this.getApplication(), MainUI.this.getApplicationContext());
                        FactoryTestWin.GetInstance().ShowMainWin();
                    } else if (TsFile.fileIsExists(String.valueOf(path) + "/setting.test")) {
                        MainSet.GetInstance().openApplication(MainUI.this.getApplication(), "com.android.settings");
                    }
                }
                if (FtSet.IsIconExist(2) == 1 && path.contains("cdfs")) {
                    if (Iop.DiscIsInsert() == 1) {
                        Log.i(MainUI.TAG, "Iop.DiscIsInsert()==1");
                    } else {
                        Log.i(MainUI.TAG, "Iop.DiscIsInsert()==0");
                        if (Iop.GetWorkMode() != 2) {
                            new Thread() {
                                public void run() {
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Iop.DiscStop();
                                }
                            }.start();
                        }
                    }
                }
                MainSet.GetInstance().CheckTestMode(true);
                tool.GetInstance().CheckMapData();
                MainUI.this.mKeyTouch.SetTouConfig();
                if (Iop.DspSupport() == 1) {
                    tool.GetInstance().checkDspUpdate(path);
                }
                int mScrW = SystemProperties.getInt("ro.forfan.hardware.width", 0);
                int mScrH = SystemProperties.getInt("ro.forfan.hardware.height", 0);
                Log.i(MainUI.TAG, "mScrW==" + mScrW);
                Log.i(MainUI.TAG, "mScrH==" + mScrH);
                if (mScrW == 800 && mScrH == 480) {
                    mScrW = 1024;
                    mScrH = CanCameraUI.BTN_GOLF_WC_MODE1;
                }
                if (mScrW == 800 && mScrH == 600) {
                    mScrW = 1024;
                    mScrH = CanCameraUI.BTN_GOLF_WC_MODE1;
                }
                if (FtSet.LoadTouchXY(mScrW, mScrH, SystemProperties.getInt("ro.forfan.touchwidth", 0), SystemProperties.getInt("ro.forfan.touchheight", 0)) == 1) {
                    Toast.makeText(MainUI.this.getApplicationContext(), "电容屏参数导入成功", 0).show();
                }
            }
        }
    };
    private ITsCom mTsCom = new ITsCom();
    public ITsPlayerService mTsPlayerService = null;
    ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
    public int nAutoToNavi = 0;
    int nBatFirst = 0;
    int nCameraForbiden = 0;
    long nCheckTemp = 0;
    int nDelayToGetVerSion = 50;
    public int nDelayToVoice = 500;
    int nDelayWakeUp = 0;
    int nDvdMode = 0;
    public int nDvrDelayTime = 300;
    int nErrorNum = 0;
    private int nFsok = 0;
    int nHomeKey_enable = 1;
    private int nILL = 255;
    public int nKeyBeep = 0;
    int nMode = 0;
    int nMute = 1;
    public int nNawKey = 0;
    public int nNetIsConnect = 0;
    public int nOldConnect = 0;
    int nOldMode = 255;
    private int nOldPowerMode = 0;
    int nOldWorkMode = 255;
    int nPaunseFlag = 0;
    /* access modifiers changed from: private */
    public int nPowerMode = 0;
    int nPowerOldWorkMode = 255;
    private int nPowerState = 0;
    int nSetDaPINTime = 3;
    int nTempWork = -1;
    public int nTpmsOnce = 0;
    public int nTurnonLight = 0;
    int nUpdateTime = 0;
    byte[] naviPacketName = new byte[128];
    SoundPool soundPoolbeep = new SoundPool(3, 1, 0);

    /* access modifiers changed from: package-private */
    public void CheckSYS() {
        if (MainSet.GetInstance().bIsLowMemory()) {
            this.ClockTime = SystemClock.uptimeMillis();
            if (this.ClockTime - this.nCheckTemp > 10000) {
                if (this.nCheckTemp == 0) {
                    tool.GetInstance().KillNavi();
                } else {
                    Log.i(TAG, "*******Main task is run Slow=" + (this.ClockTime - this.nCheckTemp));
                }
            }
            this.nCheckTemp = this.ClockTime;
            if (this.ClockTime - nCheckNum > 5000) {
                if (IsCameraMode() == 0) {
                    int nVolmem = GetAviMemory();
                    if (nVolmem < 100) {
                        tool.GetInstance().killAllProcess(2);
                        Log.i(TAG, "*******Main task is lowmem 1=" + nVolmem);
                    } else if (nVolmem < 120) {
                        tool.GetInstance().killAllProcess(1);
                        Log.i(TAG, "*******Main task is lowmem 2=" + nVolmem);
                    } else if (nVolmem < 250) {
                        tool.GetInstance().killAllProcess(0);
                        Log.i(TAG, "*******Main task is lowmem 3=" + nVolmem);
                    }
                }
                nCheckNum = this.ClockTime;
            }
        }
    }

    private void GetMemory() {
        CheckSYS();
        nTickNum++;
        if (nTickNum >= 300) {
            if (this.nSetDaPINTime > 0) {
                this.nSetDaPINTime--;
                SetDAPinIO();
                MainSet.GetInstance().CheckTestMode(true);
            }
            if (this.mActivityManager == null) {
                this.mActivityManager = (ActivityManager) getSystemService("activity");
            }
            Log.i(TAG, "*******Main task is run ==" + GetToalMemory());
            if (!MainSet.GetInstance().IsHaveService("com.forfan.xfapp.CoreService")) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.forfan.xfapp", "com.forfan.xfapp.CoreService"));
                startService(intent);
            }
            nTickNum = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public void CheckDiscState() {
        new Thread() {
            public void run() {
                Boolean newStateBoolean;
                try {
                    sleep(10000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                Boolean bDiscSate = Boolean.valueOf(TsFile.fileIsExists("/dev/block/sr0"));
                while (true) {
                    if (MainUI.IsCameraMode() == 0 && bDiscSate != (newStateBoolean = Boolean.valueOf(TsFile.fileIsExists("/dev/block/sr0")))) {
                        bDiscSate = newStateBoolean;
                        if (bDiscSate.booleanValue() && MainUI.this.CheckDisk && !MainSet.GetInstance().IsCustom("LEMA")) {
                            WinShow.GotoWin(3, 0);
                        }
                    }
                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    public void CheckSccidState() {
        new Thread() {
            public void run() {
                String string;
                TelephonyManager telMgr = (TelephonyManager) MainUI.this.getSystemService("phone");
                Log.w(MainUI.TAG, "MainSet.GetInstance().seiid" + MainSet.GetInstance().GetSerid());
                Log.w(MainUI.TAG, "telMgr.getSimSerialNumber()" + telMgr.getSimSerialNumber());
                Log.w(MainUI.TAG, "telMgr.getDeviceId()" + telMgr.getDeviceId());
                if (MainSet.IsYSJQP()) {
                    string = GetID.getInstance().GetIccidStateqp(MainSet.GetInstance().GetSerid(), telMgr.getSimSerialNumber(), telMgr.getDeviceId());
                } else {
                    string = GetID.getInstance().GetSccidState(MainSet.GetInstance().GetSerid(), telMgr.getSimSerialNumber(), telMgr.getDeviceId());
                }
                Log.w(MainUI.TAG, "string" + string);
                if (string == null) {
                    return;
                }
                if (string.equals("0") || string.equals("-2") || string.equals("-1") || string.equals("-3")) {
                    try {
                        TsFile.writeFileSdcardFile(MainUI.SIM_SCCID_FILE, String.valueOf(telMgr.getSimSerialNumber()) + "#");
                        MainUI.nSimOwner = 1;
                        MainUI.this.SetNetConState(true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        TsFile.writeFileSdcardFile(MainUI.SIM_SCCID_FILE, String.valueOf(telMgr.getSimSerialNumber()) + "*");
                        MainUI.nSimOwner = 2;
                        MainUI.this.SetNetConState(false);
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static void SetCanCallBack(CanInterface cb) {
        mCanInterface = cb;
    }

    private long getSystemAvaialbeMemorySize() {
        ActivityManager.MemoryInfo memoryInfo2 = new ActivityManager.MemoryInfo();
        this.mActivityManager.getMemoryInfo(memoryInfo2);
        return memoryInfo2.availMem;
    }

    private boolean IsBatOff() {
        if ((Mcu.GetPowState() & 16) != 0) {
            return true;
        }
        return false;
    }

    public int GetMcuState() {
        int nState = Mcu.GetPowState();
        if ((nState & 2) != 0) {
            return 2;
        }
        if ((nState & 8) == 0) {
            return 3;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void EnterCamera(int nMode2) {
        if (MainSet.GetInstance().IsMathToMcu()) {
            AmapAuto.SetBackStateToWeNavi(nMode2);
            if (nMode2 == 1) {
                if (MainSet.IsMkz()) {
                    Evc.GetInstance().SetMusicMute(true);
                }
                MainSet.SendIntent("com.autokingvr.FORCE_CLOSE_VR_CMD");
                TxzReg.GetInstance().SetBtState(1, (TXZCallManager.Contact) null);
                TxzReg.GetInstance().SetTXZState(3);
                TxzReg.GetInstance().TxzWinHide();
                try {
                    if (this.mIAdasBinderInterface != null) {
                        this.mIAdasBinderInterface.setAdasEnable(0);
                        this.mIAdasBinderInterface.setBackSignal(1);
                    }
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                if (Evc.GetInstance().GetWorkMode() == 2 && this.mTsPlayerService != null) {
                    try {
                        this.mTsPlayerService.pause();
                        this.nPaunseFlag = 1;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                if (!MainSet.GetInstance().IsXT5() && !CanIF.RvsEntExdMode()) {
                    WinShow.show("com.ts.MainUI", "com.ts.backcar.BackcarMainActivity");
                    if (GetInstance().GetMcuState() != 3) {
                        TsDisplay.GetInstance().SetSrcMute(12);
                        if (Mcu.BklisOn() == 0) {
                            Mcu.BklTurn();
                        }
                    }
                } else if (mCanInterface != null) {
                    mCanInterface.EnterCamera(1);
                }
                if (GetInstance().GetMcuState() == 3) {
                    this.nTurnonLight = 12;
                    return;
                }
                return;
            }
            MainSet.GetInstance();
            if (MainSet.IsMkz()) {
                Evc.GetInstance().SetMusicMute(false);
            }
            MainSet.SendIntent("com.autoking.START_VR_SERVICE");
            if (this.mIAdasBinderInterface != null) {
                try {
                    this.mIAdasBinderInterface.setAdasEnable(1);
                    this.mIAdasBinderInterface.setBackSignal(0);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
            if (this.nPaunseFlag == 1) {
                if (Evc.GetInstance().GetWorkMode() == 2) {
                    try {
                        this.mTsPlayerService.play();
                    } catch (RemoteException e3) {
                        e3.printStackTrace();
                    }
                }
                this.nPaunseFlag = 0;
            }
            if (!BtExe.getBtInstance().isHaveCall()) {
                TxzReg.GetInstance().SetBtState(3, (TXZCallManager.Contact) null);
            }
            TxzReg.GetInstance().SetTXZState(FtSet.GetSpeechMode());
            if (this.nCameraForbiden == 1) {
                this.nCameraForbiden = 2;
                WinShow.TsEnterMode(Iop.GetWorkMode());
            }
            if ((MainSet.GetInstance().IsXT5() || CanIF.RvsEntExdMode()) && mCanInterface != null) {
                mCanInterface.EnterCamera(0);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void EnterRightCamera(int nMode2) {
        if (nMode2 == 1) {
            WinShow.show("com.ts.MainUI", "com.ts.backcar.RightCameraActivity");
            return;
        }
        Intent intent = new Intent();
        intent.setAction(BROADCAST_RIGHT_CAMERA_SWITCH_STRING);
        intent.putExtra("switch", "off");
        sendBroadcast(intent);
    }

    public static int SetGpsMode(int nMode2) {
        String GetVal = SystemProperties.get("persist.radio.gnssopmode.prop");
        Log.i(TAG, "persist.radio.gnssopmode.prop = " + GetVal);
        Log.i(TAG, "SetGpsMode = " + nMode2);
        switch (nMode2) {
            case 0:
                if (!GetVal.equals(MainSet.SP_XPH5)) {
                    SystemProperties.set("persist.radio.gnssopmode.prop", MainSet.SP_XPH5);
                    break;
                }
                break;
            case 1:
                if (!GetVal.equals("0")) {
                    SystemProperties.set("persist.radio.gnssopmode.prop", "0");
                    break;
                }
                break;
            case 2:
                if (!GetVal.equals(MainSet.SP_RLF_KORON)) {
                    SystemProperties.set("persist.radio.gnssopmode.prop", MainSet.SP_RLF_KORON);
                    break;
                }
                break;
            case 3:
                if (!GetVal.equals(MainSet.SP_XH_DMAX)) {
                    SystemProperties.set("persist.radio.gnssopmode.prop", MainSet.SP_XH_DMAX);
                    break;
                }
                break;
            default:
                SystemProperties.set("persist.radio.gnssopmode.prop", "0");
                break;
        }
        Log.i(TAG, "persist.radio.gnssopmode.prop 22 = " + SystemProperties.get("persist.radio.gnssopmode.prop"));
        return 1;
    }

    public static int IsCamVPort() {
        if (nCamVport == 0) {
            return 1;
        }
        return 0;
    }

    public static MainUI GetInstance() {
        return mMainUI;
    }

    public void GetNaviInfo() {
        AmapAuto.GetInstance().GetNaviInfo();
    }

    public void GetMediaInfo() {
        CanIF.mMediaInfo.Avalid = 0;
        CanIF.mMediaInfo.MediaType = 0;
        CanIF.mMediaInfo.fgNumAvalid = 0;
        CanIF.mMediaInfo.CurNum = 0;
        CanIF.mMediaInfo.TotalNum = 0;
        CanIF.mMediaInfo.fgTimeAvalid = 0;
        CanIF.mMediaInfo.CurHour = 0;
        CanIF.mMediaInfo.CurMin = 0;
        CanIF.mMediaInfo.CurSec = 0;
        CanIF.mMediaInfo.TotalHour = 0;
        CanIF.mMediaInfo.TotalMin = 0;
        CanIF.mMediaInfo.TotalSec = 0;
        CanIF.mMediaInfo.CurTime = 0;
        CanIF.mMediaInfo.TotalTime = 0;
        CanIF.mMediaInfo.fgPause = 0;
        CanIF.mMediaInfo.fgLoading = 0;
        if (this.mTsPlayerService == null) {
            CanIF.mMediaInfo.Avalid = 0;
            CanIF.mMediaInfo.fgNumAvalid = 0;
            CanIF.mMediaInfo.fgTimeAvalid = 0;
            return;
        }
        CanIF.mMediaInfo.Avalid = 1;
        try {
            switch (this.mTsPlayerService.getMediaDevice()) {
                case 1:
                    CanIF.mMediaInfo.MediaType = 4;
                    CanIF.mMediaInfo.CurNum = this.mTsPlayerService.getPlayId();
                    CanIF.mMediaInfo.TotalNum = this.mTsPlayerService.getMediaCnt();
                    CanIF.mMediaInfo.fgNumAvalid = 1;
                    break;
                case 2:
                    CanIF.mMediaInfo.MediaType = 3;
                    CanIF.mMediaInfo.CurNum = this.mTsPlayerService.getPlayId();
                    CanIF.mMediaInfo.TotalNum = this.mTsPlayerService.getMediaCnt();
                    CanIF.mMediaInfo.fgNumAvalid = 1;
                    break;
                case 4:
                case 5:
                    CanIF.mMediaInfo.MediaType = 2;
                    CanIF.mMediaInfo.CurNum = this.mTsPlayerService.getPlayId();
                    CanIF.mMediaInfo.TotalNum = this.mTsPlayerService.getMediaCnt();
                    CanIF.mMediaInfo.fgNumAvalid = 1;
                    break;
                default:
                    CanIF.mMediaInfo.MediaType = 0;
                    return;
            }
            if (this.mTsPlayerService.getPlayStatus() == 2) {
                CanIF.mMediaInfo.fgPause = 1;
            }
            CanIF.mMediaInfo.CurHour = (((int) this.mTsPlayerService.getCurrentTime()) / MediaDef.PROGRESS_MAX) / 3600;
            CanIF.mMediaInfo.CurMin = ((((int) this.mTsPlayerService.getCurrentTime()) / MediaDef.PROGRESS_MAX) / 60) % 60;
            CanIF.mMediaInfo.CurSec = (((int) this.mTsPlayerService.getCurrentTime()) / MediaDef.PROGRESS_MAX) % 60;
            CanIF.mMediaInfo.TotalHour = (((int) this.mTsPlayerService.getTotoalTime()) / MediaDef.PROGRESS_MAX) / 3600;
            CanIF.mMediaInfo.TotalMin = ((((int) this.mTsPlayerService.getTotoalTime()) / MediaDef.PROGRESS_MAX) / 60) % 60;
            CanIF.mMediaInfo.TotalSec = (((int) this.mTsPlayerService.getTotoalTime()) / MediaDef.PROGRESS_MAX) % 60;
            CanIF.mMediaInfo.CurTime = ((int) this.mTsPlayerService.getCurrentTime()) / MediaDef.PROGRESS_MAX;
            CanIF.mMediaInfo.TotalTime = ((int) this.mTsPlayerService.getTotoalTime()) / MediaDef.PROGRESS_MAX;
            CanIF.mMediaInfo.fgTimeAvalid = 1;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void GetCanId3() {
        if (this.mTsPlayerService != null) {
            try {
                CanIF.mID3.sAlbum = this.mTsPlayerService.getId3AlbumName();
                CanIF.mID3.sArtist = this.mTsPlayerService.getId3Artist();
                CanIF.mID3.sName = this.mTsPlayerService.getPlayName();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            CanIF.mID3.sAlbum = "";
            CanIF.mID3.sArtist = "";
            CanIF.mID3.sName = "";
        }
    }

    public void GetMediaStatus() {
        CanIF.mMediaSta.PlaySta = 0;
        CanIF.mMediaSta.fgRnd = 0;
        CanIF.mMediaSta.fgRpt = 0;
        if (this.mTsPlayerService == null) {
            CanIF.mMediaSta.PlaySta = 0;
            CanIF.mMediaSta.fgRnd = 0;
            CanIF.mMediaSta.fgRpt = 0;
            return;
        }
        try {
            switch (this.mTsPlayerService.getPlayStatus()) {
                case 1:
                case 3:
                case 4:
                    CanIF.mMediaSta.PlaySta = 1;
                    break;
                case 2:
                    CanIF.mMediaSta.PlaySta = 2;
                    break;
                default:
                    CanIF.mMediaSta.PlaySta = 0;
                    break;
            }
            if (this.mTsPlayerService.getShuffleMode() == 1) {
                CanIF.mMediaSta.fgRnd = 1;
                CanIF.mMediaSta.fgRpt = 0;
                return;
            }
            CanIF.mMediaSta.fgRnd = 0;
            CanIF.mMediaSta.fgRpt = 1;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public void TransToCamera() {
        EnterCamera(1);
        if (this.nDelayToVoice > 0) {
            this.nDelayToVoice = 500;
            if (MainSet.IsGLSXVer().booleanValue()) {
                this.nDelayToVoice = 300;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void BackToLauncher() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(268435456);
        startActivity(intent);
    }

    /* access modifiers changed from: package-private */
    public void InintKeyBeep() {
        if (MainSet.IsXPH5()) {
            Evc.GetInstance().evol_systemvol_set(50);
        }
        new HashMap().put(1, Integer.valueOf(this.soundPoolbeep.load(this, R.raw.keybeep, 1)));
        this.soundPoolbeep.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void KeyArmBeep() {
        if (this.nKeyBeep == 0) {
            Iop.NaviSpeaking(2);
            this.nKeyBeep = 5;
        }
    }

    /* access modifiers changed from: package-private */
    public void SendClock() {
        if (SystemClock.uptimeMillis() - this.TimeTicket > 1000) {
            this.TimeTicket = SystemClock.uptimeMillis();
            ContentResolver mResolver = getContentResolver();
            Calendar instance = Calendar.getInstance();
            if (mResolver != null) {
                String timeFormat = Settings.System.getString(mResolver, "time_12_24");
                Time t = new Time();
                t.setToNow();
                int Format = 1;
                if (timeFormat != null) {
                    Log.d(TAG, "timeFormat = " + timeFormat + "t.minute=" + t.minute);
                    if (timeFormat.equals("24")) {
                        Format = 0;
                    }
                }
                Mcu.SendClock(t.hour, t.minute, Format);
            }
        }
    }

    public void DealTask() {
        String nVal;
        if (Evc.mSystemState < 10) {
            this.nPowerState = 0;
            WmInint();
            Log.d(TAG, "DealTask poweroff state  = " + Evc.mSystemState);
        } else if (Evc.mSystemState >= 20) {
            Mcu.mcutask();
            StringBuilder sb = new StringBuilder("## Acc Off = ");
            int i = Evc.mSystemState;
            Evc.mSystemState = i + 1;
            Log.d(TAG, sb.append(i).append(" !").append("GetMcuState()==").append(GetMcuState()).toString());
            if (GetMcuState() == 0 || GetMcuState() == 3) {
                if (this.nDelayWakeUp > 0) {
                    this.nDelayWakeUp--;
                    return;
                }
                Evc.mSystemState = 1;
                this.nPowerState = 0;
                if (MainSet.IsXPH5()) {
                    MainAlterwin.GetInstance().HidenPoweroffWin();
                } else {
                    MainAlterwin.GetInstance().HidenPoweroffWin();
                }
            } else if (IsBatOff() && !this.bShutDown) {
                MainSet.SendIntent("com.cusc.action.ACC_OFF");
                MainSet.GetInstance().SendMgAccState(0);
                AmapAuto.SetACCOFF();
                AmapAuto.QuiteNavi();
                Log.d(TAG, "## RUN_BATOFF START = " + GetMcuState());
                MainSet.GetInstance().Reboot();
                Log.d(TAG, "## RUN_BATOFF END = " + GetMcuState());
                this.bShutDown = true;
            }
        } else {
            if (BtExe.getBtInstance().isConnected() && (nVal = SystemProperties.get("forfan.tsbt.mute")) != null && (nVal.equals(MainSet.SP_XPH5) || nVal.equals(MainSet.SP_RLF_KORON))) {
                if (Iop.DspSupport() == 0 || nVal.equals(MainSet.SP_XPH5)) {
                    Log.d(TAG, "call is comming " + nVal);
                    Iop.PopMuteDelay(50);
                }
                SystemProperties.set("forfan.tsbt.mute", "0");
            }
            if (bSupportMute) {
                String nVal2 = SystemProperties.get("forfan.track.active");
                if (nVal2 != null) {
                    if (nVal2.equals("0")) {
                        if (!MainSet.IsGLSXVer().booleanValue()) {
                            Mcu.SendXKey(240);
                        } else if (BtExe.getBtInstance().isHaveCall()) {
                            Mcu.SendXKey(241);
                        } else {
                            Mcu.SendXKey(240);
                        }
                    } else if (nVal2.equals(MainSet.SP_XPH5)) {
                        Mcu.SendXKey(241);
                    }
                }
            } else {
                Mcu.SendXKey(241);
            }
            Mcu.mcutask();
            AmapAuto.GetInstance().Task();
            SendClock();
            GetMemory();
            if (this.nTurnonLight > 0) {
                this.nTurnonLight--;
                if (this.nTurnonLight == 0) {
                    Mcu.BklTurnCan(1);
                    nBklCanOn = 1;
                }
            }
            this.nPowerMode = GetMcuState();
            if (this.nPowerMode != 0) {
                Log.d(TAG, "## nPowerMode = " + this.nPowerMode + " !");
            }
            if (this.nPowerMode != this.nOldPowerMode) {
                Log.d(TAG, "## nPowerMode222 = " + this.nPowerMode + " !");
                MainSet.SendMcuState(this.nPowerMode);
                if (this.nPowerMode == 3) {
                    Log.d(TAG, "mEvc.Evol.workmode = " + Iop.GetWorkMode() + " !");
                    MainSet.SendIntent(BROADCAST_GLSX_ACC_OFF);
                    if (Iop.GetWorkMode() == 2 || Iop.GetWorkMode() == 4 || Iop.GetWorkMode() == 3) {
                        this.nPowerOldWorkMode = Iop.GetWorkMode();
                        BackToLauncher();
                        if (this.mTsPlayerService != null) {
                            try {
                                this.mTsPlayerService.enterMedia(0);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                            this.mEvc.evol_workmode_set(0);
                        }
                    }
                    if (MainSet.nShowScreen == 1) {
                        Mcu.BklTurnCan(1);
                        nBklCanOn = 1;
                        MainSet.GetInstance().openApplication(this, "com.ts.tsclock");
                    } else {
                        if (nBklCanOn == 1) {
                            Mcu.BklTurnCan(0);
                            nBklCanOn = 0;
                        }
                        if (MainSet.IsXPH5()) {
                            MainAlterwin.GetInstance().ShowPowerOffWin();
                        } else {
                            MainAlterwin.GetInstance().ShowPowerOffWin(-16777216);
                        }
                    }
                } else if (this.nOldPowerMode == 3 && this.nPowerMode == 0) {
                    MainSet.SendIntent(BROADCAST_GLSX_ACC_ON);
                    if (MainSet.IsXPH5()) {
                        MainAlterwin.GetInstance().HidenPoweroffWin();
                    } else {
                        MainAlterwin.GetInstance().HidenPoweroffWin();
                    }
                    if (MainSet.GetInstance().IsHaveApk("com.ts.tsclock")) {
                        tool.GetInstance().killProcess("com.ts.tsclock");
                    }
                    if (this.nPowerOldWorkMode != 255) {
                        WinShow.TsEnterMode(this.nPowerOldWorkMode);
                    } else {
                        this.mEvc.evol_workmode_set(Iop.GetWorkMode());
                    }
                    this.nPowerOldWorkMode = 255;
                } else if (this.nOldPowerMode == 3 && this.nPowerMode == 2) {
                    if (MainSet.IsXPH5()) {
                        MainAlterwin.GetInstance().HidenPoweroffWin();
                    } else {
                        MainAlterwin.GetInstance().HidenPoweroffWin();
                    }
                    if (this.nPowerOldWorkMode != 255) {
                        this.mEvc.evol_workmode_set(this.nPowerOldWorkMode);
                    }
                    this.nPowerOldWorkMode = 255;
                }
                this.nOldPowerMode = this.nPowerMode;
            }
            if (nMcuRet == 1 && this.nPowerState == 255 && this.nFsok == 1) {
                SetVoiceState(false);
                AdjustMcuTime();
                MainSet.SendIntent(ACTION_MAINUI_ACCOFF);
                if (this.mTsPlayerService != null) {
                    unbindService(this.mServiceConnection);
                }
                Iop.tsxhwSleep();
                MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
                EnterCamera(0);
                MainSet.GetInstance().FtDestroy();
                BtExe.getBtInstance().disconnect();
                AmapAuto.GetInstance().WriteMem((String) null);
                BackToLauncher();
                this.mMainVolume.Destroy();
                Evc.mSystemState = 20;
                Log.w(TAG, "######## Acc Off !");
                this.mTsPlayerService = null;
                this.nDelayWakeUp = 20;
                Log.i(TAG, "**************Power off ********************nPowerMode==" + this.nPowerMode);
                return;
            }
            this.nPowerState = 255;
            if (!bSimConnect) {
                int i2 = nnUM;
                nnUM = i2 + 1;
                if (i2 % 30 == 0) {
                    CheckSIMState();
                }
            }
            if (nMcuRet == 1 && this.nFsok == 1) {
                if (nCheckSimState == 1) {
                    nCheckSimState = 2;
                    CheckSccidState();
                }
                KeyTouch.GetInstance().DealTask();
                if (!MainSet.GetInstance().IsTestMode()) {
                    if (nDelayToCamera > 0) {
                        nDelayToCamera--;
                        Log.i(TAG, "nDelayToCamera-- nDelayToCamera=" + nDelayToCamera);
                    } else if (IsCameraMode() == 1 && !bIsInCamera) {
                        bIsInCamera = true;
                        Log.i(TAG, "IsCameraMode() && !bIsInCamera ");
                        SetCameraVPort(0);
                        TransToCamera();
                    } else if (IsRightCamMode() == 1 && !bIsInRight) {
                        Log.i(TAG, "IsRightCamMode() && !bIsInRight ");
                        bIsInRight = true;
                        SetCameraVPort(1);
                        EnterRightCamera(1);
                    } else if (IsCameraMode() == 0 && IsRightCamMode() == 0 && (bIsInCamera || bIsInRight)) {
                        nDelayToCamera = 30;
                        if (MainSet.GetInstance().IsXT5() || CanIF.RvsEntExdMode()) {
                            bIsInCamera = false;
                        }
                        this.nTurnonLight = 0;
                        if (nBklCanOn == 1 && MainSet.nShowScreen == 0) {
                            Mcu.BklTurnCan(0);
                            nBklCanOn = 0;
                        }
                        Log.i(TAG, "!IsCameraMode() && bIsInCamera ");
                        EnterCamera(0);
                        EnterRightCamera(0);
                        if (bFrontCam) {
                            WinShow.GotoWin(14, 0);
                        }
                        Log.i(TAG, "******forfan.backcar.cmd stop********");
                    }
                }
                if (IsCameraMode() == 0) {
                    if (this.nAutoToNavi > 0) {
                        this.nAutoToNavi--;
                        if (this.nAutoToNavi == 0) {
                            WinShow.GotoWin(1, 0);
                        }
                    }
                    if (this.nDelayToVoice > 0) {
                        this.nDelayToVoice--;
                        if (this.nDelayToVoice == 0 && !MainSet.IsGLSXVer().booleanValue() && MainSet.isZh() && FtSet.GetSpeechMode() != 3) {
                            TxzReg.GetInstance().Inint0(this);
                        }
                    }
                }
                MainSet.GetInstance().DealTask(this.nPowerMode);
                this.mDisplay.DealTask(this.nPowerMode);
                try {
                    CheckMediaDevice();
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
                WinShow.DealTask();
                TxzReg.GetInstance().Task(this.nPowerMode);
                if (this.nKeyBeep > 0) {
                    this.nKeyBeep--;
                    if (this.nKeyBeep == 3) {
                        this.soundPoolbeep.play(1, 1.0f, 1.0f, 100, 0, 1.0f);
                    } else if (this.nKeyBeep == 0 && Evc.nNaviSpeeking == 0) {
                        Iop.NaviSpeaking(0);
                    }
                }
                if (FtSet.IsIconExist(123) == 1 && !MainSet.GetInstance().IsTestMode()) {
                    this.mStTpms.MainFunc(this.nPowerMode);
                    if (this.mStTpms.tpmsSave.nOnOffFlag == 2 && this.nTpmsOnce > 0) {
                        this.nTpmsOnce--;
                    }
                    if (this.nTpmsOnce == 0 && this.mStTpms.CheckError() > 0) {
                        this.nTpmsOnce = Can.CAN_NISSAN_XFY;
                        SoundPool soundPool = new SoundPool(3, 3, 0);
                        new HashMap().put(1, Integer.valueOf(soundPool.load(this, R.raw.waring, 1)));
                        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                                soundPool.play(1, 1.0f, 1.0f, 100, 3, 1.0f);
                            }
                        });
                        WinShow.show("com.ts.MainUI", "com.ts.main.tpms.TPMSMainActivity");
                    }
                }
                this.mMainVolume.CheckVol();
                this.mMainCScreen.DealTask(0);
                if (this.mEvc.task(this.nPowerMode) != 255) {
                    if (this.nPowerMode == 2) {
                        Log.i(TAG, "mEvc  PowerOff Err ");
                    }
                    this.nPowerState = 0;
                }
                if (this.mTsPlayerService != null) {
                    try {
                        if (this.mTsPlayerService.mediaTask(this.nPowerMode) != 255) {
                            if (this.nPowerMode == 2) {
                                Log.i(TAG, "Media  PowerOff Err ");
                            }
                            this.nPowerState = 0;
                        }
                    } catch (RemoteException e3) {
                        e3.printStackTrace();
                    }
                }
                SetAdasLightState();
                if (MainSet.GetInstance().IsHaveBt()) {
                    BtExe.getBtInstance().timerCall(this.nPowerMode);
                }
                ScreenSet.GetInstance().Task(this.nPowerMode);
                if ((!MainSet.GetInstance().IsTestMode() || MainSet.bKeyBroad) && Radio.TuneTask(this.nPowerMode) != 255) {
                    if (this.nPowerMode == 2) {
                        Log.i(TAG, "mRadio  PowerOff Err ");
                    }
                    this.nPowerState = 0;
                }
                if (CstTv.GetInstance().Tv_Main(this.nPowerMode, true) != 255) {
                    if (this.nPowerMode == 2) {
                        Log.i(TAG, "CstTv  PowerOff Err ");
                    }
                    this.nPowerState = 0;
                }
                if (this.bSupportECar && ECarCommunication.getInstance().ECarTask(this.nPowerMode) != 255) {
                    if (this.nPowerMode == 2) {
                        Log.i(TAG, "ECarCommunication  PowerOff Err ");
                    }
                    this.nPowerState = 0;
                }
                if (mCanInterface != null) {
                    if (!this.bCaninit) {
                        mCanInterface.CanInit(getApplicationContext());
                        this.bCaninit = true;
                    }
                    if (nDelayToCamera == 0 && mCanInterface.CanTask(this.nPowerMode) != 255) {
                        if (this.nPowerMode == 2) {
                            Log.i(TAG, "CanFunc  PowerOff Err ");
                        }
                        this.nPowerState = 0;
                    }
                }
                if (this.nPowerMode == 2 && this.nPowerState == 255) {
                    Mcu.SendByeBye();
                }
            }
        }
    }

    private int IsMediaMode() {
        if (Iop.GetWorkMode() == 3 || Iop.GetWorkMode() == 4 || Iop.GetWorkMode() == 2 || Iop.GetWorkMode() == 15) {
            return 1;
        }
        return 0;
    }

    private void CheckMediaDevice() throws RemoteException {
        if (this.mTsPlayerService != null && this.nDelayToGetVerSion > 0) {
            this.nDelayToGetVerSion--;
            if (this.nDelayToGetVerSion == 0) {
                MainVerSion.ROM_VERSION = this.mTsPlayerService.getDvdVersion();
                MainVerSion.MMP_VERSION = this.mTsPlayerService.getMediaVersion();
                if (!(MainVerSion.ROM_VERSION == null || MainVerSion.MMP_VERSION == null)) {
                    this.nDelayToGetVerSion = 0;
                }
            }
        }
        if (bBtConnect != BtExe.getBtInstance().isConnected()) {
            bBtConnect = BtExe.getBtInstance().isConnected();
            if (bBtConnect) {
                MainSet.SendIntent(BROADCAST_BT_CONNECTED);
            }
        }
        if (bHaveCall != BtExe.getBtInstance().isHaveCall()) {
            bHaveCall = BtExe.getBtInstance().isHaveCall();
            if (bHaveCall) {
                if (this.mTsPlayerService != null) {
                    this.mTsPlayerService.dealBTCall(1);
                }
                if (Mcu.BklisOn() == 0) {
                    Mcu.BklTurn();
                }
                if ((Mcu.GetPowState() & 8) == 0) {
                    Mcu.SetCkey(70);
                }
            } else if (this.mTsPlayerService != null) {
                this.mTsPlayerService.dealBTCall(0);
            }
        }
    }

    public ITsPlayerService getITsPlayerService() {
        return this.mTsPlayerService;
    }

    private String getTopPackageName() {
        return ((ActivityManager) getSystemService("activity")).getRunningTasks(1).get(0).baseActivity.getPackageName();
    }

    /* access modifiers changed from: package-private */
    public boolean bEnableEkey() {
        int id = getResources().getIdentifier("ekey_transfer", "string", getPackageName());
        if ((id != 0 && getResources().getString(id) == "true") || !MainSet.GetInstance().IsCustom("SZKS")) {
            return true;
        }
        String Toppckname = WinShow.getTopPackName();
        String topactivityString = WinShow.getTopActivityName();
        if (Toppckname != null && (Toppckname.startsWith("com.android.launcher") || Toppckname.startsWith("com.ts.dvdplayer"))) {
            return true;
        }
        if (topactivityString == null || (!topactivityString.startsWith("com.ts.main.radio") && !topactivityString.startsWith("com.ts.main.bt"))) {
            return false;
        }
        return true;
    }

    public void StartGoogleVoice() {
        Intent intent2 = new Intent();
        intent2.setAction("android.intent.action.VOICE_ASSIST");
        intent2.setPackage("com.google.android.googlequicksearchbox");
        intent2.addFlags(337641472);
        startActivity(intent2);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void DealKey() {
        /*
            r12 = this;
            int r7 = com.yyw.ts70xhw.Mcu.GetPkey()
            if (r7 != 0) goto L_0x0016
            int r7 = com.yyw.ts70xhw.Mcu.GetEkey()
            if (r7 != 0) goto L_0x0016
            int r7 = com.yyw.ts70xhw.Mcu.GetRkey()
            if (r7 != 0) goto L_0x0016
            int r7 = com.yyw.ts70xhw.Mcu.GetSkey()
        L_0x0016:
            int r8 = r12.GetMcuState()
            r9 = 3
            if (r8 == r9) goto L_0x0021
            boolean r8 = bEnterMode
            if (r8 == 0) goto L_0x004c
        L_0x0021:
            r8 = 81
            if (r7 == r8) goto L_0x004c
            r8 = 332(0x14c, float:4.65E-43)
            if (r7 == r8) goto L_0x004c
            r8 = 769(0x301, float:1.078E-42)
            if (r7 == r8) goto L_0x004c
            r8 = 70
            if (r7 == r8) goto L_0x004c
            r8 = 314(0x13a, float:4.4E-43)
            if (r7 == r8) goto L_0x004c
            if (r7 == 0) goto L_0x004c
            java.lang.String r8 = "MainUI"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "Now is Power off so don't deal the key  = "
            r9.<init>(r10)
            java.lang.StringBuilder r9 = r9.append(r7)
            java.lang.String r9 = r9.toString()
            android.util.Log.d(r8, r9)
        L_0x004b:
            return
        L_0x004c:
            r8 = 92
            if (r7 == r8) goto L_0x0054
            r8 = 97
            if (r7 != r8) goto L_0x0058
        L_0x0054:
            com.ts.can.CanIF.DealCam360Key(r7)
            r7 = 0
        L_0x0058:
            r8 = 6
            if (r7 == r8) goto L_0x005f
            r8 = 24
            if (r7 != r8) goto L_0x006c
        L_0x005f:
            boolean r8 = com.ts.main.common.MainSet.IsXPH5()
            if (r8 != 0) goto L_0x006c
            boolean r8 = com.ts.can.CanIF.Deal360CameraKey()
            if (r8 == 0) goto L_0x006c
            r7 = 0
        L_0x006c:
            int r8 = IsCameraMode()
            r9 = 1
            if (r8 == r9) goto L_0x007f
            int r8 = com.ts.MainUI.Evc.mSystemState
            r9 = 10
            if (r8 < r9) goto L_0x007f
            int r8 = com.ts.MainUI.Evc.mSystemState
            r9 = 20
            if (r8 < r9) goto L_0x0081
        L_0x007f:
            r7 = 0
            goto L_0x004b
        L_0x0081:
            if (r7 <= 0) goto L_0x009a
            java.lang.String r8 = "MainUI"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "nDealPkey = = "
            r9.<init>(r10)
            java.lang.StringBuilder r9 = r9.append(r7)
            java.lang.String r9 = r9.toString()
            android.util.Log.i(r8, r9)
            switch(r7) {
                case 1: goto L_0x02b6;
                case 2: goto L_0x0298;
                case 3: goto L_0x02a4;
                case 4: goto L_0x02ad;
                case 5: goto L_0x02bf;
                case 6: goto L_0x02dd;
                case 7: goto L_0x030f;
                case 8: goto L_0x031f;
                case 9: goto L_0x0319;
                case 10: goto L_0x036f;
                case 11: goto L_0x04f6;
                case 12: goto L_0x0538;
                case 13: goto L_0x0540;
                case 14: goto L_0x0548;
                case 15: goto L_0x0550;
                case 16: goto L_0x05e6;
                case 17: goto L_0x069d;
                case 18: goto L_0x06e3;
                case 19: goto L_0x0729;
                case 20: goto L_0x0745;
                case 21: goto L_0x021a;
                case 22: goto L_0x0761;
                case 23: goto L_0x076a;
                case 24: goto L_0x02dd;
                case 25: goto L_0x0772;
                case 26: goto L_0x077b;
                case 27: goto L_0x0454;
                case 28: goto L_0x0558;
                case 29: goto L_0x039a;
                case 30: goto L_0x040b;
                case 41: goto L_0x01e7;
                case 42: goto L_0x01d8;
                case 43: goto L_0x06eb;
                case 51: goto L_0x0714;
                case 52: goto L_0x0714;
                case 53: goto L_0x0714;
                case 68: goto L_0x026e;
                case 69: goto L_0x0244;
                case 70: goto L_0x07a1;
                case 71: goto L_0x07c5;
                case 81: goto L_0x0799;
                case 82: goto L_0x0784;
                case 83: goto L_0x07c5;
                case 87: goto L_0x02d1;
                case 88: goto L_0x02d7;
                case 90: goto L_0x01f6;
                case 95: goto L_0x0476;
                case 96: goto L_0x0476;
                case 99: goto L_0x0365;
                case 100: goto L_0x012e;
                case 102: goto L_0x0435;
                case 103: goto L_0x043e;
                case 104: goto L_0x0449;
                case 257: goto L_0x030f;
                case 258: goto L_0x02dd;
                case 259: goto L_0x031f;
                case 261: goto L_0x036f;
                case 262: goto L_0x04f6;
                case 263: goto L_0x06eb;
                case 264: goto L_0x0540;
                case 265: goto L_0x0548;
                case 266: goto L_0x0550;
                case 267: goto L_0x0621;
                case 268: goto L_0x069d;
                case 270: goto L_0x0729;
                case 271: goto L_0x0745;
                case 272: goto L_0x0761;
                case 273: goto L_0x039a;
                case 274: goto L_0x040b;
                case 314: goto L_0x07a1;
                case 315: goto L_0x07c5;
                case 332: goto L_0x0799;
                case 333: goto L_0x0784;
                case 334: goto L_0x07c5;
                case 513: goto L_0x0745;
                case 514: goto L_0x0729;
                case 515: goto L_0x019d;
                case 516: goto L_0x0162;
                case 769: goto L_0x07a1;
                case 774: goto L_0x0729;
                case 776: goto L_0x0729;
                case 777: goto L_0x0729;
                case 779: goto L_0x0745;
                case 781: goto L_0x0745;
                case 782: goto L_0x0745;
                case 793: goto L_0x0698;
                case 794: goto L_0x040b;
                case 799: goto L_0x0375;
                case 803: goto L_0x0698;
                case 805: goto L_0x062a;
                case 808: goto L_0x066a;
                case 809: goto L_0x036f;
                case 814: goto L_0x039a;
                case 819: goto L_0x040b;
                case 829: goto L_0x04f6;
                case 834: goto L_0x031f;
                case 839: goto L_0x0761;
                case 844: goto L_0x04b2;
                default: goto L_0x009a;
            }
        L_0x009a:
            if (r7 <= 0) goto L_0x004b
            java.lang.String r8 = "MainUI"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "mEvc.Evol.workmode = "
            r9.<init>(r10)
            int r10 = com.yyw.ts70xhw.Iop.GetWorkMode()
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r9 = r9.toString()
            android.util.Log.i(r8, r9)
            android.content.Context r8 = r12.getApplicationContext()
            android.content.res.Resources r8 = r8.getResources()
            int r9 = com.ts.MainUI.R.string.custom_num_show
            java.lang.String r8 = r8.getString(r9)
            java.lang.String r9 = "Benz"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x0124
            android.content.Context r8 = r12.getApplicationContext()
            android.content.res.Resources r8 = r8.getResources()
            java.lang.String r9 = "ekey_transfer"
            java.lang.String r10 = "string"
            java.lang.String r11 = r12.getPackageName()
            int r5 = r8.getIdentifier(r9, r10, r11)
            if (r5 == 0) goto L_0x0124
            android.content.Context r8 = r12.getApplicationContext()
            android.content.res.Resources r8 = r8.getResources()
            java.lang.String r8 = r8.getString(r5)
            java.lang.String r9 = "true"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x0124
            java.lang.String r8 = "activity"
            java.lang.Object r1 = r12.getSystemService(r8)
            android.app.ActivityManager r1 = (android.app.ActivityManager) r1
            r8 = 1
            java.util.List r8 = r1.getRunningTasks(r8)
            r9 = 0
            java.lang.Object r8 = r8.get(r9)
            android.app.ActivityManager$RunningTaskInfo r8 = (android.app.ActivityManager.RunningTaskInfo) r8
            android.content.ComponentName r2 = r8.baseActivity
            java.lang.String r8 = r2.getClassName()
            java.lang.String r9 = "com.ts.bt"
            boolean r8 = r8.startsWith(r9)
            if (r8 == 0) goto L_0x0124
            r8 = 44
            if (r7 != r8) goto L_0x07d0
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 22
            r8.sendKeyClick(r9)
            r7 = 0
        L_0x0122:
            if (r7 == 0) goto L_0x004b
        L_0x0124:
            int r8 = com.yyw.ts70xhw.Iop.GetWorkMode()
            switch(r8) {
                case 0: goto L_0x07f8;
                case 1: goto L_0x07de;
                case 2: goto L_0x085a;
                case 3: goto L_0x085a;
                case 4: goto L_0x085a;
                case 5: goto L_0x07eb;
                case 6: goto L_0x0889;
                case 7: goto L_0x012b;
                case 8: goto L_0x012b;
                case 9: goto L_0x0878;
                case 10: goto L_0x012b;
                case 11: goto L_0x012b;
                case 12: goto L_0x0899;
                case 13: goto L_0x012b;
                case 14: goto L_0x012b;
                case 15: goto L_0x085a;
                default: goto L_0x012b;
            }
        L_0x012b:
            r7 = 0
            goto L_0x004b
        L_0x012e:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r9 = "ZHYC"
            boolean r8 = r8.IsCustom(r9)
            if (r8 == 0) goto L_0x0143
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 187(0xbb, float:2.62E-43)
            r8.sendKeyClick(r9)
            goto L_0x009a
        L_0x0143:
            int r8 = r12.nNawKey
            if (r8 <= 0) goto L_0x015d
            java.lang.String r8 = "MainUI"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "Last key is not deal = = "
            r9.<init>(r10)
            int r10 = r12.nNawKey
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r9 = r9.toString()
            android.util.Log.i(r8, r9)
        L_0x015d:
            r12.nNawKey = r7
            r7 = 0
            goto L_0x009a
        L_0x0162:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            boolean r8 = r8.IsBMWVer()
            if (r8 != 0) goto L_0x017c
            boolean r8 = com.ts.main.common.MainSet.IsBmwX1()
            if (r8 != 0) goto L_0x017c
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            boolean r8 = r8.IsEkeyTurnEnable()
            if (r8 == 0) goto L_0x009a
        L_0x017c:
            java.lang.String r8 = r12.getTopPackageName()
            java.lang.String r9 = "com.autonavi.amapauto"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x018f
            r8 = 1
            com.ts.main.txz.AmapAuto.SetMapScal(r8)
        L_0x018c:
            r7 = 0
            goto L_0x009a
        L_0x018f:
            boolean r8 = r12.bEnableEkey()
            if (r8 == 0) goto L_0x018c
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 20
            r8.sendKeyClick(r9)
            goto L_0x018c
        L_0x019d:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            boolean r8 = r8.IsBMWVer()
            if (r8 != 0) goto L_0x01b7
            boolean r8 = com.ts.main.common.MainSet.IsBmwX1()
            if (r8 != 0) goto L_0x01b7
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            boolean r8 = r8.IsEkeyTurnEnable()
            if (r8 == 0) goto L_0x009a
        L_0x01b7:
            java.lang.String r8 = r12.getTopPackageName()
            java.lang.String r9 = "com.autonavi.amapauto"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x01ca
            r8 = 0
            com.ts.main.txz.AmapAuto.SetMapScal(r8)
        L_0x01c7:
            r7 = 0
            goto L_0x009a
        L_0x01ca:
            boolean r8 = r12.bEnableEkey()
            if (r8 == 0) goto L_0x01c7
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 19
            r8.sendKeyClick(r9)
            goto L_0x01c7
        L_0x01d8:
            boolean r8 = r12.bEnableEkey()
            if (r8 == 0) goto L_0x009a
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 21
            r8.sendKeyClick(r9)
            goto L_0x009a
        L_0x01e7:
            boolean r8 = r12.bEnableEkey()
            if (r8 == 0) goto L_0x009a
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 22
            r8.sendKeyClick(r9)
            goto L_0x009a
        L_0x01f6:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r9 = "SZKS"
            boolean r8 = r8.IsCustom(r9)
            if (r8 == 0) goto L_0x009a
            int r8 = com.lgb.canmodule.CanJni.GetCanFsTp()
            r9 = 69
            if (r8 != r9) goto L_0x009a
            boolean r8 = r12.bEnableEkey()
            if (r8 == 0) goto L_0x0217
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 23
            r8.sendKeyClick(r9)
        L_0x0217:
            r7 = 0
            goto L_0x009a
        L_0x021a:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            boolean r8 = r8.IsBMWVer()
            if (r8 != 0) goto L_0x0234
            boolean r8 = com.ts.main.common.MainSet.IsBmwX1()
            if (r8 != 0) goto L_0x0234
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            boolean r8 = r8.IsEkeyTurnEnable()
            if (r8 == 0) goto L_0x009a
        L_0x0234:
            boolean r8 = r12.bEnableEkey()
            if (r8 == 0) goto L_0x0241
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 23
            r8.sendKeyClick(r9)
        L_0x0241:
            r7 = 0
            goto L_0x009a
        L_0x0244:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            boolean r8 = r8.IsBMWVer()
            if (r8 != 0) goto L_0x025e
            boolean r8 = com.ts.main.common.MainSet.IsBmwX1()
            if (r8 != 0) goto L_0x025e
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            boolean r8 = r8.IsEkeyTurnEnable()
            if (r8 == 0) goto L_0x009a
        L_0x025e:
            boolean r8 = r12.bEnableEkey()
            if (r8 == 0) goto L_0x026b
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 22
            r8.sendKeyClick(r9)
        L_0x026b:
            r7 = 0
            goto L_0x009a
        L_0x026e:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            boolean r8 = r8.IsBMWVer()
            if (r8 != 0) goto L_0x0288
            boolean r8 = com.ts.main.common.MainSet.IsBmwX1()
            if (r8 != 0) goto L_0x0288
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            boolean r8 = r8.IsEkeyTurnEnable()
            if (r8 == 0) goto L_0x009a
        L_0x0288:
            boolean r8 = r12.bEnableEkey()
            if (r8 == 0) goto L_0x0295
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 21
            r8.sendKeyClick(r9)
        L_0x0295:
            r7 = 0
            goto L_0x009a
        L_0x0298:
            r8 = 10
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            r12.KeyBeep()
            r7 = 0
            goto L_0x009a
        L_0x02a4:
            r8 = 10
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            r7 = 0
            goto L_0x009a
        L_0x02ad:
            r8 = 23
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            r7 = 0
            goto L_0x009a
        L_0x02b6:
            r8 = 8
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            r7 = 0
            goto L_0x009a
        L_0x02bf:
            android.content.Intent r6 = new android.content.Intent
            java.lang.String r8 = "android.settings.DATE_SETTINGS"
            r6.<init>(r8)
            r8 = 337641472(0x14200000, float:8.077936E-27)
            r6.addFlags(r8)
            r12.startActivity(r6)
            r7 = 0
            goto L_0x009a
        L_0x02d1:
            r8 = 1
            r12.DealKeyDim(r8)
            goto L_0x009a
        L_0x02d7:
            r8 = 0
            r12.DealKeyDim(r8)
            goto L_0x009a
        L_0x02dd:
            boolean r8 = com.ts.main.common.MainSet.IsXPH5()
            if (r8 != 0) goto L_0x02ec
            boolean r8 = com.ts.can.CanIF.Deal360CameraKey()
            if (r8 == 0) goto L_0x02ec
            r7 = 0
            goto L_0x009a
        L_0x02ec:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            boolean r8 = r8.IsXuhuiDmax()
            if (r8 == 0) goto L_0x0301
            r8 = 16
            com.yyw.ts70xhw.Mcu.SendXKey(r8)
            r8 = 1
            com.ts.main.common.MainSet.nShowScreen = r8
        L_0x02fe:
            r7 = 0
            goto L_0x009a
        L_0x0301:
            boolean r8 = com.ts.main.common.MainSet.IsFlkj()
            if (r8 == 0) goto L_0x030b
            r12.DealKeyDim()
            goto L_0x02fe
        L_0x030b:
            com.yyw.ts70xhw.Mcu.BklTurn()
            goto L_0x02fe
        L_0x030f:
            r8 = 11
            r9 = 98
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            r7 = 0
            goto L_0x009a
        L_0x0319:
            r8 = 0
            r12.SetVoiceState(r8)
            goto L_0x009a
        L_0x031f:
            boolean r8 = bIsScreenMode
            if (r8 == 0) goto L_0x032f
            com.ts.main.common.KeyTouch r8 = com.ts.main.common.KeyTouch.GetInstance()
            java.lang.String r9 = ""
            r8.takeScreenShot(r9)
        L_0x032c:
            r7 = 0
            goto L_0x009a
        L_0x032f:
            int r8 = com.yyw.ts70xhw.Mcu.BklisOn()
            if (r8 != 0) goto L_0x0338
            com.yyw.ts70xhw.Mcu.BklTurn()
        L_0x0338:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            boolean r8 = r8.IsXT5()
            if (r8 == 0) goto L_0x0359
            java.lang.String r8 = com.ts.main.common.WinShow.getTopActivityName()
            java.lang.String r9 = "com.ts.can.gm.xt5.CanCadillacXt5ExdActivity"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x0352
            com.ts.can.CanIF.DealXt5Key()
            goto L_0x032c
        L_0x0352:
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 3
            r8.sendKeyClick(r9)
            goto L_0x032c
        L_0x0359:
            int r8 = r12.nHomeKey_enable
            r9 = 1
            if (r8 != r9) goto L_0x032c
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 3
            r8.sendKeyClick(r9)
            goto L_0x032c
        L_0x0365:
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 82
            r8.sendKeyClick(r9)
            r7 = 0
            goto L_0x009a
        L_0x036f:
            com.ts.main.common.WinShow.DealModeKey()
            r7 = 0
            goto L_0x009a
        L_0x0375:
            int r8 = com.ts.bt.BtExe.mCallSta
            r9 = 3
            if (r8 != r9) goto L_0x0384
            com.ts.bt.BtExe r8 = com.ts.bt.BtExe.getBtInstance()
            r8.answer()
            r7 = 0
            goto L_0x009a
        L_0x0384:
            com.ts.MainUI.Evc r8 = com.ts.MainUI.Evc.GetInstance()
            int r8 = r8.PhoneState
            r9 = 1
            if (r8 != r9) goto L_0x009a
            java.lang.String r8 = "phone"
            java.lang.Object r8 = r12.getSystemService(r8)
            android.telephony.TelephonyManager r8 = (android.telephony.TelephonyManager) r8
            com.ts.main.common.PhoneUtils.answerCall(r8)
            goto L_0x009a
        L_0x039a:
            int r8 = com.ts.bt.BtExe.mCallSta
            r9 = 3
            if (r8 != r9) goto L_0x03a9
            com.ts.bt.BtExe r8 = com.ts.bt.BtExe.getBtInstance()
            r8.answer()
        L_0x03a6:
            r7 = 0
            goto L_0x009a
        L_0x03a9:
            com.ts.MainUI.Evc r8 = com.ts.MainUI.Evc.GetInstance()
            int r8 = r8.PhoneState
            r9 = 1
            if (r8 != r9) goto L_0x03be
            java.lang.String r8 = "phone"
            java.lang.Object r8 = r12.getSystemService(r8)
            android.telephony.TelephonyManager r8 = (android.telephony.TelephonyManager) r8
            com.ts.main.common.PhoneUtils.answerCall(r8)
            goto L_0x03a6
        L_0x03be:
            com.ts.MainUI.Evc r8 = com.ts.MainUI.Evc.GetInstance()
            int r8 = r8.PhoneState
            r9 = 2
            if (r8 != r9) goto L_0x03d3
            java.lang.String r8 = "phone"
            java.lang.Object r8 = r12.getSystemService(r8)
            android.telephony.TelephonyManager r8 = (android.telephony.TelephonyManager) r8
            com.ts.main.common.PhoneUtils.rejectCall(r8)
            goto L_0x03a6
        L_0x03d3:
            com.ts.bt.BtExe r8 = com.ts.bt.BtExe.getBtInstance()
            boolean r8 = r8.isHaveCall()
            if (r8 == 0) goto L_0x03e5
            com.ts.bt.BtExe r8 = com.ts.bt.BtExe.getBtInstance()
            r8.hangup()
            goto L_0x03a6
        L_0x03e5:
            com.ts.bt.BtExe r8 = com.ts.bt.BtExe.getBtInstance()
            boolean r8 = r8.isConnected()
            if (r8 != 0) goto L_0x03f5
            r8 = 7
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            goto L_0x03a6
        L_0x03f5:
            com.ts.bt.BtExe r8 = com.ts.bt.BtExe.getBtInstance()
            boolean r8 = r8.isHaveCall()
            if (r8 != 0) goto L_0x03a6
            int r8 = com.ts.bt.BtFunc.dealBtCallKey(r7)
            if (r8 != 0) goto L_0x03a6
            r8 = 7
            r9 = 3
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            goto L_0x03a6
        L_0x040b:
            com.ts.bt.BtExe r8 = com.ts.bt.BtExe.getBtInstance()
            boolean r8 = r8.isHaveCall()
            if (r8 == 0) goto L_0x041f
            com.ts.bt.BtExe r8 = com.ts.bt.BtExe.getBtInstance()
            r8.hangup()
            r7 = 0
            goto L_0x009a
        L_0x041f:
            com.ts.MainUI.Evc r8 = com.ts.MainUI.Evc.GetInstance()
            int r8 = r8.PhoneState
            r9 = 1
            if (r8 < r9) goto L_0x009a
            java.lang.String r8 = "phone"
            java.lang.Object r8 = r12.getSystemService(r8)
            android.telephony.TelephonyManager r8 = (android.telephony.TelephonyManager) r8
            com.ts.main.common.PhoneUtils.rejectCall(r8)
            goto L_0x009a
        L_0x0435:
            java.lang.String r8 = "com.ts.MainUI"
            java.lang.String r9 = "com.ts.main.tpms.TPMSMainActivity"
            com.ts.main.common.WinShow.show(r8, r9)
            goto L_0x009a
        L_0x043e:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r9 = "cn.manstep.phonemirrorBox"
            r8.openApplication(r12, r9)
            goto L_0x009a
        L_0x0449:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r9 = "com.android.dialer"
            r8.openApplication(r12, r9)
            goto L_0x009a
        L_0x0454:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r9 = "com.williexing.android.apps.xcdvr1"
            boolean r8 = r8.IsHaveApk(r9)
            if (r8 == 0) goto L_0x046b
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r9 = "com.williexing.android.apps.xcdvr1"
            r8.openApplication(r12, r9)
            goto L_0x009a
        L_0x046b:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r9 = "com.synmoon.usbcamera"
            r8.openApplication(r12, r9)
            goto L_0x009a
        L_0x0476:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r9 = "com.txznet.txz"
            boolean r8 = r8.IsHaveApk(r9)
            if (r8 == 0) goto L_0x049b
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            boolean r8 = r8.IsTwcjw()
            if (r8 == 0) goto L_0x0492
            r12.StartGoogleVoice()
        L_0x048f:
            r7 = 0
            goto L_0x009a
        L_0x0492:
            com.ts.main.txz.TxzReg r8 = com.ts.main.txz.TxzReg.GetInstance()
            r9 = 0
            r8.TxzStartMic(r9)
            goto L_0x048f
        L_0x049b:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r9 = "ZHYC"
            boolean r8 = r8.IsCustom(r9)
            if (r8 == 0) goto L_0x04ab
            r12.StartGoogleVoice()
            goto L_0x048f
        L_0x04ab:
            com.ts.MainUI.Evc r8 = r12.mEvc
            r9 = 2
            r8.evol_mut_set(r9)
            goto L_0x048f
        L_0x04b2:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r9 = "com.cantho.roadtech"
            boolean r8 = r8.IsHaveApk(r9)
            if (r8 == 0) goto L_0x04ca
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r9 = "com.cantho.roadtech"
            r8.openApplication(r12, r9)
        L_0x04c7:
            r7 = 0
            goto L_0x009a
        L_0x04ca:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r9 = "com.google.android.googlequicksearchbox"
            boolean r8 = r8.IsHaveApk(r9)
            if (r8 == 0) goto L_0x04da
            r12.StartGoogleVoice()
            goto L_0x04c7
        L_0x04da:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r9 = "com.txznet.txz"
            boolean r8 = r8.IsHaveApk(r9)
            if (r8 == 0) goto L_0x04ef
            com.ts.main.txz.TxzReg r8 = com.ts.main.txz.TxzReg.GetInstance()
            r9 = 0
            r8.TxzStartMic(r9)
            goto L_0x04c7
        L_0x04ef:
            com.ts.MainUI.Evc r8 = r12.mEvc
            r9 = 2
            r8.evol_mut_set(r9)
            goto L_0x04c7
        L_0x04f6:
            boolean r8 = bIsScreenMode
            if (r8 == 0) goto L_0x0506
            com.ts.main.common.KeyTouch r8 = com.ts.main.common.KeyTouch.GetInstance()
            java.lang.String r9 = ""
            r8.takeScreenShot(r9)
        L_0x0503:
            r7 = 0
            goto L_0x009a
        L_0x0506:
            r8 = 128(0x80, float:1.794E-43)
            byte[] r3 = new byte[r8]
            com.yyw.ts70xhw.StSet.GetNaviPack(r3)
            java.lang.String r0 = com.ts.can.CanIF.byte2String(r3)
            java.lang.String r8 = r12.getTopPackageName()
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0532
            int r8 = com.yyw.ts70xhw.Iop.GetWorkMode()
            if (r8 != 0) goto L_0x052a
            com.ts.main.common.KeyTouch r8 = com.ts.main.common.KeyTouch.GetInstance()
            r9 = 3
            r8.sendKeyClick(r9)
            goto L_0x0503
        L_0x052a:
            int r8 = com.yyw.ts70xhw.Iop.GetWorkMode()
            com.ts.main.common.WinShow.TsEnterMode(r8)
            goto L_0x0503
        L_0x0532:
            r8 = 1
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            goto L_0x0503
        L_0x0538:
            r8 = 2
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            r7 = 0
            goto L_0x009a
        L_0x0540:
            r8 = 3
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            r7 = 0
            goto L_0x009a
        L_0x0548:
            r8 = 4
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            r7 = 0
            goto L_0x009a
        L_0x0550:
            r8 = 6
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            r7 = 0
            goto L_0x009a
        L_0x0558:
            int r8 = com.yyw.ts70xhw.Iop.GetWorkMode()
            switch(r8) {
                case 2: goto L_0x05aa;
                case 3: goto L_0x058c;
                case 4: goto L_0x056e;
                default: goto L_0x055f;
            }
        L_0x055f:
            r8 = 4
            int r8 = com.yyw.ts70xhw.FtSet.IsIconExist(r8)
            r9 = 1
            if (r8 != r9) goto L_0x05c8
            r8 = 6
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            goto L_0x009a
        L_0x056e:
            r8 = 3
            int r8 = com.yyw.ts70xhw.FtSet.IsIconExist(r8)
            r9 = 1
            if (r8 != r9) goto L_0x057d
            r8 = 4
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            goto L_0x009a
        L_0x057d:
            r8 = 2
            int r8 = com.yyw.ts70xhw.FtSet.IsIconExist(r8)
            r9 = 1
            if (r8 != r9) goto L_0x009a
            r8 = 3
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            goto L_0x009a
        L_0x058c:
            r8 = 2
            int r8 = com.yyw.ts70xhw.FtSet.IsIconExist(r8)
            r9 = 1
            if (r8 != r9) goto L_0x059b
            r8 = 3
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            goto L_0x009a
        L_0x059b:
            r8 = 4
            int r8 = com.yyw.ts70xhw.FtSet.IsIconExist(r8)
            r9 = 1
            if (r8 != r9) goto L_0x009a
            r8 = 6
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            goto L_0x009a
        L_0x05aa:
            r8 = 4
            int r8 = com.yyw.ts70xhw.FtSet.IsIconExist(r8)
            r9 = 1
            if (r8 != r9) goto L_0x05b9
            r8 = 6
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            goto L_0x009a
        L_0x05b9:
            r8 = 3
            int r8 = com.yyw.ts70xhw.FtSet.IsIconExist(r8)
            r9 = 1
            if (r8 != r9) goto L_0x009a
            r8 = 4
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            goto L_0x009a
        L_0x05c8:
            r8 = 3
            int r8 = com.yyw.ts70xhw.FtSet.IsIconExist(r8)
            r9 = 1
            if (r8 != r9) goto L_0x05d7
            r8 = 4
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            goto L_0x009a
        L_0x05d7:
            r8 = 2
            int r8 = com.yyw.ts70xhw.FtSet.IsIconExist(r8)
            r9 = 1
            if (r8 != r9) goto L_0x009a
            r8 = 3
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            goto L_0x009a
        L_0x05e6:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r9 = "KDLK"
            boolean r8 = r8.IsCustom(r9)
            if (r8 == 0) goto L_0x0621
            android.content.Context r8 = r12.getApplicationContext()
            android.content.res.Resources r8 = r8.getResources()
            int r9 = com.ts.MainUI.R.string.custom_num_show
            java.lang.String r8 = r8.getString(r9)
            java.lang.String r9 = "AKW"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x0621
            java.lang.String r8 = com.ts.main.common.WinShow.getTopActivityName()
            java.lang.String r9 = "com.ts.can.gm.xt5.CanCadillacXt5ExdActivity"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x0619
            com.ts.can.CanIF.DealXt5Key()
            goto L_0x009a
        L_0x0619:
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 3
            r8.sendKeyClick(r9)
            goto L_0x009a
        L_0x0621:
            com.ts.MainUI.Evc r8 = r12.mEvc
            r9 = 2
            r8.evol_mut_set(r9)
            r7 = 0
            goto L_0x009a
        L_0x062a:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            boolean r8 = r8.IsLocal()
            if (r8 == 0) goto L_0x0642
            r12.StartMic()
            com.ts.main.txz.TxzReg r8 = com.ts.main.txz.TxzReg.GetInstance()
            r9 = 0
            r8.TxzStartMic(r9)
        L_0x063f:
            r7 = 0
            goto L_0x009a
        L_0x0642:
            int r8 = com.lgb.canmodule.CanJni.GetCanType()
            r9 = 88
            if (r8 != r9) goto L_0x0663
            com.ts.bt.BtExe r8 = com.ts.bt.BtExe.getBtInstance()
            boolean r8 = r8.isHaveCall()
            if (r8 == 0) goto L_0x065c
            com.ts.bt.BtExe r8 = com.ts.bt.BtExe.getBtInstance()
            r8.hangup()
            goto L_0x063f
        L_0x065c:
            com.ts.MainUI.Evc r8 = r12.mEvc
            r9 = 2
            r8.evol_mut_set(r9)
            goto L_0x063f
        L_0x0663:
            com.ts.MainUI.Evc r8 = r12.mEvc
            r9 = 2
            r8.evol_mut_set(r9)
            goto L_0x063f
        L_0x066a:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            boolean r8 = r8.IsLocal()
            if (r8 == 0) goto L_0x067c
            com.ts.MainUI.Evc r8 = r12.mEvc
            r9 = 2
            r8.evol_mut_set(r9)
            goto L_0x009a
        L_0x067c:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            boolean r8 = r8.IsTwcjw()
            if (r8 == 0) goto L_0x068b
            r12.StartGoogleVoice()
            goto L_0x009a
        L_0x068b:
            r12.StartMic()
            com.ts.main.txz.TxzReg r8 = com.ts.main.txz.TxzReg.GetInstance()
            r9 = 0
            r8.TxzStartMic(r9)
            goto L_0x009a
        L_0x0698:
            r12.StartGoogleVoice()
            goto L_0x009a
        L_0x069d:
            java.lang.String r8 = "MainUI"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "PKEY_EQ = "
            r9.<init>(r10)
            java.lang.String r10 = com.ts.main.common.WinShow.getTopActivityName()
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r9 = r9.toString()
            android.util.Log.i(r8, r9)
            java.lang.String r8 = com.ts.main.common.WinShow.getTopActivityName()
            java.lang.String r9 = "com.ts.set.SettingSoundActivity"
            boolean r8 = r8.equals(r9)
            if (r8 != 0) goto L_0x06c7
            com.ts.main.common.WinShow.TurnToEq()
        L_0x06c4:
            r7 = 0
            goto L_0x009a
        L_0x06c7:
            int r8 = com.yyw.ts70xhw.Iop.GetEqm()
            r12.nMode = r8
            int r8 = r12.nMode
            int r8 = r8 + 1
            r12.nMode = r8
            int r8 = r12.nMode
            r9 = 5
            if (r8 <= r9) goto L_0x06db
            r8 = 0
            r12.nMode = r8
        L_0x06db:
            com.ts.MainUI.Evc r8 = r12.mEvc
            int r9 = r12.nMode
            r8.evol_eqm_set(r9)
            goto L_0x06c4
        L_0x06e3:
            com.ts.MainUI.Evc r8 = r12.mEvc
            r9 = 0
            r8.evol_mut_set(r9)
            goto L_0x009a
        L_0x06eb:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r9 = "ZHYC"
            boolean r8 = r8.IsCustom(r9)
            if (r8 == 0) goto L_0x06ff
            r8 = 2
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            r7 = 0
            goto L_0x009a
        L_0x06ff:
            int r8 = com.yyw.ts70xhw.Iop.GetWorkMode()
            r9 = 1
            if (r8 == r9) goto L_0x009a
            boolean r8 = com.ts.main.common.WinShow.IsRadioActivity()
            if (r8 != 0) goto L_0x009a
            r8 = 2
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            r7 = 0
            goto L_0x009a
        L_0x0714:
            int r8 = com.yyw.ts70xhw.Iop.GetWorkMode()
            r9 = 1
            if (r8 == r9) goto L_0x009a
            boolean r8 = com.ts.main.common.WinShow.IsRadioActivity()
            if (r8 != 0) goto L_0x009a
            r8 = 2
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            r7 = 0
            goto L_0x009a
        L_0x0729:
            int r8 = com.yyw.ts70xhw.Mcu.BklisOn()
            if (r8 != 0) goto L_0x0732
            com.yyw.ts70xhw.Mcu.BklTurn()
        L_0x0732:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            boolean r8 = r8.IsMathToMcu()
            if (r8 == 0) goto L_0x009a
            com.ts.MainUI.Evc r8 = r12.mEvc
            r9 = 1
            r8.Evol_vol_tune(r9)
            r7 = 0
            goto L_0x009a
        L_0x0745:
            int r8 = com.yyw.ts70xhw.Mcu.BklisOn()
            if (r8 != 0) goto L_0x074e
            com.yyw.ts70xhw.Mcu.BklTurn()
        L_0x074e:
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            boolean r8 = r8.IsMathToMcu()
            if (r8 == 0) goto L_0x009a
            com.ts.MainUI.Evc r8 = r12.mEvc
            r9 = 0
            r8.Evol_vol_tune(r9)
            r7 = 0
            goto L_0x009a
        L_0x0761:
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 4
            r8.sendKeyClick(r9)
            r7 = 0
            goto L_0x009a
        L_0x076a:
            r8 = 7
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            r7 = 0
            goto L_0x009a
        L_0x0772:
            r8 = 13
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            r7 = 0
            goto L_0x009a
        L_0x077b:
            r8 = 14
            r9 = 0
            com.ts.main.common.WinShow.GotoWin(r8, r9)
            r7 = 0
            goto L_0x009a
        L_0x0784:
            r8 = 16
            com.yyw.ts70xhw.Mcu.SendXKey(r8)
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            boolean r8 = r8.IsXuhuiDmax()
            if (r8 == 0) goto L_0x0796
            r8 = 1
            com.ts.main.common.MainSet.nShowScreen = r8
        L_0x0796:
            r7 = 0
            goto L_0x009a
        L_0x0799:
            r8 = 17
            com.yyw.ts70xhw.Mcu.SendXKey(r8)
            r7 = 0
            goto L_0x009a
        L_0x07a1:
            int r8 = r12.GetMcuState()
            r9 = 3
            if (r8 != r9) goto L_0x07b0
            r8 = 17
            com.yyw.ts70xhw.Mcu.SendXKey(r8)
            r7 = 0
            goto L_0x009a
        L_0x07b0:
            r8 = 16
            com.yyw.ts70xhw.Mcu.SendXKey(r8)
            com.ts.main.common.MainSet r8 = com.ts.main.common.MainSet.GetInstance()
            boolean r8 = r8.IsXuhuiDmax()
            if (r8 == 0) goto L_0x07c2
            r8 = 1
            com.ts.main.common.MainSet.nShowScreen = r8
        L_0x07c2:
            r7 = 0
            goto L_0x009a
        L_0x07c5:
            com.ts.main.common.MainUI$7 r8 = new com.ts.main.common.MainUI$7
            r8.<init>()
            r8.start()
            r7 = 0
            goto L_0x009a
        L_0x07d0:
            r8 = 45
            if (r7 != r8) goto L_0x0122
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 21
            r8.sendKeyClick(r9)
            r7 = 0
            goto L_0x0122
        L_0x07de:
            int r8 = com.ts.main.radio.RadioFunc.DealKey(r7)
            r9 = 1
            if (r8 != r9) goto L_0x012b
            r7 = 0
            r12.KeyBeep()
            goto L_0x012b
        L_0x07eb:
            int r8 = com.ts.bt.BtFunc.DealKey(r7)
            r9 = 1
            if (r8 != r9) goto L_0x012b
            r7 = 0
            r12.KeyBeep()
            goto L_0x012b
        L_0x07f8:
            switch(r7) {
                case 44: goto L_0x07fd;
                case 45: goto L_0x0839;
                case 46: goto L_0x07fd;
                case 47: goto L_0x0839;
                case 56: goto L_0x07fd;
                case 57: goto L_0x0839;
                case 60: goto L_0x0822;
                case 90: goto L_0x0819;
                case 91: goto L_0x0810;
                case 291: goto L_0x07fd;
                case 292: goto L_0x0839;
                case 299: goto L_0x0822;
                case 515: goto L_0x0839;
                case 516: goto L_0x07fd;
                case 784: goto L_0x07fd;
                case 789: goto L_0x0839;
                case 794: goto L_0x07fd;
                case 799: goto L_0x0839;
                case 824: goto L_0x0822;
                default: goto L_0x07fb;
            }
        L_0x07fb:
            goto L_0x012b
        L_0x07fd:
            java.lang.String r8 = r12.getTopPackageName()
            java.lang.String r9 = "com.ex.dabplayer.pad"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x082b
            r8 = 46
            com.ts.main.common.MainSet.DABControl(r8)
            goto L_0x012b
        L_0x0810:
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 127(0x7f, float:1.78E-43)
            r8.sendKeyClick(r9)
            goto L_0x012b
        L_0x0819:
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 126(0x7e, float:1.77E-43)
            r8.sendKeyClick(r9)
            goto L_0x012b
        L_0x0822:
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 85
            r8.sendKeyClick(r9)
            goto L_0x012b
        L_0x082b:
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 87
            r8.sendKeyClick(r9)
            r8 = 46
            com.ts.main.common.MainSet.DABControl(r8)
            goto L_0x012b
        L_0x0839:
            java.lang.String r8 = r12.getTopPackageName()
            java.lang.String r9 = "com.ex.dabplayer.pad"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x084c
            r8 = 45
            com.ts.main.common.MainSet.DABControl(r8)
            goto L_0x012b
        L_0x084c:
            com.ts.main.common.KeyTouch r8 = r12.mKeyTouch
            r9 = 88
            r8.sendKeyClick(r9)
            r8 = 45
            com.ts.main.common.MainSet.DABControl(r8)
            goto L_0x012b
        L_0x085a:
            r8 = 46
            if (r7 != r8) goto L_0x0871
            r7 = 44
        L_0x0860:
            com.ts.dvdplayer.ITsPlayerService r8 = r12.mTsPlayerService     // Catch:{ RemoteException -> 0x086b }
            if (r8 == 0) goto L_0x012b
            com.ts.dvdplayer.ITsPlayerService r8 = r12.mTsPlayerService     // Catch:{ RemoteException -> 0x086b }
            r8.nDealMediaKey(r7)     // Catch:{ RemoteException -> 0x086b }
            goto L_0x012b
        L_0x086b:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x012b
        L_0x0871:
            r8 = 47
            if (r7 != r8) goto L_0x0860
            r7 = 45
            goto L_0x0860
        L_0x0878:
            int r8 = com.yyw.ts70xhw.FtSet.GetDtvType()
            r9 = 99
            if (r8 != r9) goto L_0x012b
            com.ts.MainUI.Cmmb r8 = com.ts.MainUI.Cmmb.GetInstance()
            r8.DealCmmbKey(r7)
            goto L_0x012b
        L_0x0889:
            com.ts.MainUI.Cmmb r8 = com.ts.MainUI.Cmmb.GetInstance()
            int r8 = r8.DealCmmbKey(r7)
            r9 = 1
            if (r8 != r9) goto L_0x012b
            r12.KeyBeep()
            goto L_0x012b
        L_0x0899:
            int r8 = com.ts.can.CanIF.DealExdKey(r7)
            r9 = 1
            if (r8 != r9) goto L_0x012b
            r12.KeyBeep()
            goto L_0x012b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.main.common.MainUI.DealKey():void");
    }

    public void StartMic() {
        Log.i(TAG, "ACTION_RECOGNIZE_CMD StartMic==");
        MainSet.SendIntent(ACTION_RECOGNIZE_CMD_MIC_CLICK);
    }

    public void Settts_info(String strinfo, boolean bShow) {
        Intent Aintent = new Intent();
        Aintent.setAction(ACTION_RECOGNIZE_TTSINFO_CMD);
        Aintent.putExtra("info_tts", strinfo);
        Aintent.putExtra("info_play", bShow);
        if (bShow) {
            Aintent.putExtra("info_show", strinfo);
        }
        sendBroadcast(Aintent);
    }

    public void SetVoiceState(boolean bState) {
        Intent Aintent = new Intent();
        Aintent.setAction("com.globalconstant.BROADCAST_CAR_SEND_CMD");
        Aintent.putExtra("domain", "awaken");
        if (bState) {
            Aintent.putExtra("action", "RECORDER_START");
        } else {
            Aintent.putExtra("action", "RECORDER_STOP");
        }
        sendBroadcast(Aintent);
    }

    /* access modifiers changed from: package-private */
    public void DealKeyDim() {
        if (Mcu.GetIll() == 1 && StSet.GetBLIll() == 1) {
            if (Mcu.BklisOn() == 0) {
                Mcu.BklTurn();
                return;
            }
            int nNight = StSet.GetBLNight();
            if (nNight > 3) {
                StSet.SetBLNight(3);
            } else if (nNight > 0) {
                StSet.SetBLNight(0);
            } else if (Mcu.BklisOn() == 1) {
                Mcu.BklTurn();
                StSet.SetBLNight(6);
            }
        } else if (Mcu.BklisOn() == 0) {
            Mcu.BklTurn();
        } else {
            int nLight = StSet.GetBLDay();
            if (nLight > 3) {
                StSet.SetBLDay(3);
            } else if (nLight > 0) {
                StSet.SetBLDay(0);
            } else {
                Log.d(TAG, "Mcu.BklisOn() = " + Mcu.BklisOn());
                if (Mcu.BklisOn() == 1) {
                    Mcu.BklTurn();
                    StSet.SetBLDay(6);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int nGetDim() {
        if (Mcu.GetIll() == 1 && StSet.GetBLIll() == 1) {
            return StSet.GetBLNight();
        }
        return StSet.GetBLDay();
    }

    /* access modifiers changed from: package-private */
    public void SetDim(int nVal) {
        if (Mcu.GetIll() == 1 && StSet.GetBLIll() == 1) {
            StSet.SetBLNight(nVal);
        } else {
            StSet.SetBLDay(nVal);
        }
    }

    /* access modifiers changed from: package-private */
    public void DealKeyDim(int nUp) {
        if (nUp == 0) {
            int val = nGetDim();
            if (val > 0) {
                val--;
            }
            SetDim(val);
            return;
        }
        int val2 = nGetDim();
        if (val2 < 6) {
            val2++;
        }
        SetDim(val2);
    }

    public class easyConnectRecevie extends BroadcastReceiver {
        public easyConnectRecevie() {
        }

        public void onReceive(Context ctx, Intent intent) {
            Intent it;
            String City;
            String action = intent.getAction();
            if (MainUI.TXZ_GET_WWATHER_DATA.equalsIgnoreCase(action) && (City = intent.getExtras().getString("City")) != null) {
                TxzReg.GetInstance().GetWeatherData(City);
            }
            if (MainUI.BROADCAST_GLSX_VOICE.equals(action)) {
                TxzReg.GetInstance().TxzStartMic((String) null);
            }
            if (MainUI.BROADCAST_BT_CHECKSTATUS.equalsIgnoreCase(action)) {
                if (BtExe.getBtInstance().isConnected()) {
                    MainSet.SendIntent(MainUI.BROADCAST_BT_CONNECTED);
                } else {
                    MainSet.SendIntent("net.easyconn.bt.opened@name=" + BtExe.getBtInstance().getDevName() + "@pin=" + BtExe.getBtInstance().getDevPin());
                }
            } else if (MainUI.BROADCAST_BT_A2DP_ACQUIRE.equalsIgnoreCase(action)) {
                MainUI.this.mEvc.evol_workmode_set(5);
            } else if (!MainUI.BROADCAST_BT_A2DP_RELEASE.equalsIgnoreCase(action) && !MainUI.BROADCAST_APP_QUIT.equalsIgnoreCase(action)) {
                if (MainUI.ACTION_RECOGNIZE_CMD_REGISTER_S.equalsIgnoreCase(action)) {
                    int strStatus = intent.getExtras().getInt("register_status");
                    Log.i(MainUI.TAG, "register_status==" + strStatus);
                    if (strStatus == 1 || strStatus != 0) {
                    }
                } else if (MainUI.ACTION_RECOGNIZE_CMD.equalsIgnoreCase(action)) {
                    String strDomin = intent.getExtras().getString("domain");
                    Log.i(MainUI.TAG, "ACTION_RECOGNIZE_CMD==" + strDomin);
                    if (strDomin == null) {
                        return;
                    }
                    if (strDomin.equalsIgnoreCase("music")) {
                        String Straction = intent.getExtras().getString("action");
                        Log.i(MainUI.TAG, "ACTION_RECOGNIZE_CMD==" + Straction);
                        if (Straction.equalsIgnoreCase(InvokeConstants.INVOKE_PLAY)) {
                            return;
                        }
                        if (Straction.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                            WinShow.show("com.ts.dvdplayer", "com.ts.dvdplayer.SDActivity");
                        } else if (Straction.equalsIgnoreCase("random")) {
                            Mcu.SetCkey(90);
                        } else if (Straction.equalsIgnoreCase("next")) {
                            Mcu.SetCkey(44);
                        } else if (Straction.equalsIgnoreCase(InvokeConstants.INVOKE_PREV)) {
                            Mcu.SetCkey(45);
                        } else if (Straction.equalsIgnoreCase("resume")) {
                            Mcu.SetCkey(90);
                        } else if (Straction.equalsIgnoreCase("pause")) {
                            Mcu.SetCkey(91);
                        }
                    } else if (strDomin.equalsIgnoreCase("app")) {
                        if (intent.getExtras().getString("action").equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                            String pckName = intent.getExtras().getString("pck");
                            if (!TextUtils.isEmpty(pckName) && (it = MainUI.this.getPackageManager().getLaunchIntentForPackage(pckName)) != null) {
                                MainUI.this.startActivity(it);
                            }
                        }
                    } else if (strDomin.equalsIgnoreCase("volume")) {
                        String Straction2 = intent.getExtras().getString("action");
                        Log.i(MainUI.TAG, "volume ==" + Straction2);
                        if (Straction2.equalsIgnoreCase("up")) {
                            for (int i = 0; i < 3; i++) {
                                MainUI.this.mEvc.Evol_vol_tune(1);
                            }
                        } else if (Straction2.equalsIgnoreCase("down")) {
                            for (int i2 = 0; i2 < 3; i2++) {
                                MainUI.this.mEvc.Evol_vol_tune(0);
                            }
                        } else if (Straction2.equalsIgnoreCase("max")) {
                            MainUI.this.mEvc.evol_vol_set(MainUI.this.mEvc.volume_max);
                        } else if (Straction2.equalsIgnoreCase("min")) {
                            MainUI.this.mEvc.evol_vol_set(0);
                        } else if (Straction2.equalsIgnoreCase("mute_on")) {
                            MainUI.this.mEvc.evol_mut_set(1);
                        } else if (Straction2.equalsIgnoreCase("mute_off")) {
                            MainUI.this.mEvc.evol_mut_set(0);
                        }
                    } else if (strDomin.equalsIgnoreCase("radio")) {
                        String Straction3 = intent.getExtras().getString("action");
                        if (!Straction3.equalsIgnoreCase("radio")) {
                            if (Straction3.equalsIgnoreCase("forword")) {
                                Radio.TuneSearch(1);
                            } else if (Straction3.equalsIgnoreCase("back")) {
                                Radio.TuneSearch(0);
                            } else if (Straction3.equalsIgnoreCase(InvokeConstants.INVOKE_PREV)) {
                                Radio.TuneMprev();
                            } else if (Straction3.equalsIgnoreCase("next")) {
                                Radio.TuneMnext();
                            }
                        }
                        String StrBand = intent.getExtras().getString("channelStyle");
                        String StrValue = intent.getExtras().getString("channelValue");
                        if (StrBand != null && StrBand.length() > 0 && StrValue != null && StrValue.length() > 0) {
                            if (Iop.GetWorkMode() != 1) {
                                WinShow.GotoWin(2, 0);
                            }
                            if (StrBand.equalsIgnoreCase("FM")) {
                                float fm = Float.parseFloat(StrValue);
                                Log.i(MainUI.TAG, "ACTION_RECOGNIZE_CMD fm ==" + fm);
                                int nFm = (int) (100.0f * fm);
                                Log.i(MainUI.TAG, "ACTION_RECOGNIZE_CMD nFm ==" + nFm);
                                Radio.TuneBandFq(0, nFm);
                            } else if (StrBand.equalsIgnoreCase("AM")) {
                                float am = Float.parseFloat(StrValue);
                                Log.i(MainUI.TAG, "ACTION_RECOGNIZE_CMD fm ==" + am);
                                int nAm = (int) am;
                                Log.i(MainUI.TAG, "ACTION_RECOGNIZE_CMD nFm ==" + nAm);
                                Radio.TuneBandFq(4, nAm);
                            }
                        }
                    } else if (strDomin.equalsIgnoreCase("navigation")) {
                        String Straction4 = intent.getExtras().getString("action");
                        Log.i(MainUI.TAG, "ACTION_RECOGNIZE_CMD action==" + Straction4);
                        if (Straction4.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                            WinShow.GotoWin(1, 0);
                        } else if (!Straction4.equalsIgnoreCase("close") && Straction4.equalsIgnoreCase("getNaviAppPck")) {
                            byte[] byteNavipath = new byte[128];
                            StSet.GetNaviPack(byteNavipath);
                            String NaviPath = CanIF.byte2String(byteNavipath);
                            Intent Aintent = new Intent();
                            Aintent.setAction("com.globalconstant.BROADCAST_CAR_SEND_CMD");
                            Aintent.putExtra("domain", "navigation");
                            Aintent.putExtra("action", "sendNaviAppPck");
                            if (TextUtils.isEmpty(NaviPath)) {
                                Aintent.putExtra("naviAppPck", "com.autonavi.amapauto");
                            } else {
                                Aintent.putExtra("naviAppPck", NaviPath);
                            }
                            Log.i(MainUI.TAG, "ACTION_RECOGNIZE_CMD NaviPath==" + NaviPath);
                            MainUI.this.sendBroadcast(Aintent);
                        }
                    } else if (strDomin.equalsIgnoreCase("device")) {
                        String StrDevName = intent.getExtras().getString("device_name");
                        Log.i(MainUI.TAG, "ACTION_RECOGNIZE_CMD device_name==" + StrDevName);
                        String Straction5 = intent.getExtras().getString("action");
                        Log.i(MainUI.TAG, "ACTION_RECOGNIZE_CMD action ==" + Straction5);
                        if (StrDevName.equalsIgnoreCase("光盘")) {
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                WinShow.GotoWin(3, 0);
                            } else {
                                Straction5.equalsIgnoreCase("close");
                            }
                        } else if (StrDevName.equalsIgnoreCase("蓝牙")) {
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                Mcu.SetCkey(29);
                            } else {
                                Straction5.equalsIgnoreCase("close");
                            }
                        } else if (StrDevName.equalsIgnoreCase("收音机")) {
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                WinShow.GotoWin(2, 0);
                            }
                        } else if (StrDevName.equalsIgnoreCase("视频")) {
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                WinShow.ShowVideoWin();
                            }
                        } else if (StrDevName.equalsIgnoreCase("IPOD")) {
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                WinShow.GotoWin(13, 0);
                            }
                        } else if (StrDevName.equalsIgnoreCase("bluetooth")) {
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                WinShow.GotoWin(7, 4);
                            } else if (Straction5.equalsIgnoreCase("close") && Iop.GetWorkMode() == 5) {
                                MainUI.this.BackToLauncher();
                                MainUI.this.mEvc.evol_workmode_set(0);
                            }
                        } else if (StrDevName.equalsIgnoreCase("phone")) {
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                WinShow.GotoWin(7, 3);
                            } else if (Straction5.equalsIgnoreCase("close")) {
                                MainUI.this.BackToLauncher();
                            }
                        } else if (StrDevName.equalsIgnoreCase("radio")) {
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                MainUI.this.Settts_info(MainUI.this.getResources().getString(R.string.txz_open_radio), false);
                                WinShow.GotoWin(2, 0);
                            } else if (Straction5.equalsIgnoreCase("close") && Iop.GetWorkMode() == 1) {
                                MainUI.this.Settts_info(MainUI.this.getResources().getString(R.string.txz_close_radio), false);
                                MainUI.this.BackToLauncher();
                                MainUI.this.mEvc.evol_workmode_set(0);
                            }
                        } else if (StrDevName.equalsIgnoreCase("movie")) {
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                MainUI.this.Settts_info(MainUI.this.getResources().getString(R.string.txz_open_video), false);
                                WinShow.ShowVideoWin();
                            } else if (Straction5.equalsIgnoreCase("close") && Iop.GetWorkMode() == 3) {
                                MainUI.this.Settts_info(MainUI.this.getResources().getString(R.string.txz_close_video), false);
                                MainUI.this.BackToLauncher();
                                MainUI.this.mEvc.evol_workmode_set(0);
                            }
                        } else if (StrDevName.equalsIgnoreCase("music")) {
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                MainUI.this.Settts_info(MainUI.this.getResources().getString(R.string.txz_open_music), false);
                                WinShow.show("com.ts.dvdplayer", "com.ts.dvdplayer.SDActivity");
                            } else if (Straction5.equalsIgnoreCase("close") && Iop.GetWorkMode() == 4) {
                                MainUI.this.Settts_info(MainUI.this.getResources().getString(R.string.txz_close_music), false);
                                MainUI.this.BackToLauncher();
                                MainUI.this.mEvc.evol_workmode_set(0);
                            }
                        } else if (StrDevName.equalsIgnoreCase("device_setting")) {
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                WinShow.GotoWin(11, 98);
                            } else if (Straction5.equalsIgnoreCase("close")) {
                                MainUI.this.BackToLauncher();
                            }
                        } else if (StrDevName.equalsIgnoreCase("avin")) {
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                WinShow.GotoWin(10, 0);
                            } else if (Straction5.equalsIgnoreCase("close") && Iop.GetWorkMode() == 9) {
                                MainUI.this.BackToLauncher();
                                MainUI.this.mEvc.evol_workmode_set(0);
                            }
                        } else if (StrDevName.equalsIgnoreCase("driving_record")) {
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                WinShow.show("com.ts.MainUI", "com.ts.main.dvr.DvrMainActivity");
                            } else if (Straction5.equalsIgnoreCase("close")) {
                                MainUI.this.BackToLauncher();
                            }
                        } else if (StrDevName.equalsIgnoreCase("tpms")) {
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                WinShow.show("com.ts.MainUI", "com.ts.main.tpms.TPMSMainActivity");
                            } else if (Straction5.equalsIgnoreCase("close")) {
                                MainUI.this.BackToLauncher();
                            }
                        } else if (StrDevName.equalsIgnoreCase("driving_assistance")) {
                            if (FtSet.IsIconExist(127) != 1) {
                                return;
                            }
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                WinShow.show("com.ts.MainUI", "com.ts.main.Media.ADASMainActivity");
                            } else if (Straction5.equalsIgnoreCase("close")) {
                                MainUI.this.BackToLauncher();
                            }
                        } else if (StrDevName.equalsIgnoreCase("driving_message")) {
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                WinShow.show("com.ts.MainUI", "com.ts.can.CanMainActivity");
                            } else if (Straction5.equalsIgnoreCase("close")) {
                                MainUI.this.BackToLauncher();
                            }
                        } else if (StrDevName.equalsIgnoreCase("dvd")) {
                            if (FtSet.IsIconExist(2) != 1) {
                                MainUI.this.Settts_info("对不起，本机无DVD", false);
                            } else if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                MainUI.this.Settts_info(MainUI.this.getResources().getString(R.string.txz_open_dvd), false);
                                WinShow.show("com.android.sdvdplayer", "com.android.sdvdplayer.SDVDPlayer");
                            } else if (Straction5.equalsIgnoreCase("close") && Iop.GetWorkMode() == 2) {
                                MainUI.this.Settts_info(MainUI.this.getResources().getString(R.string.txz_close_dvd), false);
                                MainUI.this.mEvc.evol_workmode_set(0);
                                MainUI.this.BackToLauncher();
                            }
                        } else if (StrDevName.equalsIgnoreCase("tv")) {
                            if (FtSet.IsIconExist(6) != 1) {
                                return;
                            }
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                WinShow.GotoWin(8, 0);
                            } else if (Straction5.equalsIgnoreCase("close") && Iop.GetWorkMode() == 6) {
                                MainUI.this.BackToLauncher();
                                MainUI.this.mEvc.evol_workmode_set(0);
                            }
                        } else if (StrDevName.equalsIgnoreCase("bluebooth")) {
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                WinShow.GotoWin(7, 4);
                            } else if (Straction5.equalsIgnoreCase("close") && Iop.GetWorkMode() == 5) {
                                MainUI.this.BackToLauncher();
                                MainUI.this.mEvc.evol_workmode_set(0);
                            }
                        } else if (StrDevName.equalsIgnoreCase("cd")) {
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                WinShow.show("com.ts.MainUI", "com.ts.can.CanCarDeviceActivity");
                            } else if (Straction5.equalsIgnoreCase("close") && Iop.GetWorkMode() == 12) {
                                MainUI.this.BackToLauncher();
                                MainUI.this.mEvc.evol_workmode_set(0);
                            }
                        } else if (StrDevName.equalsIgnoreCase("car_device")) {
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                if (FtSet.IsIconExist(15) == 1) {
                                    WinShow.show("com.ts.MainUI", "com.ts.can.CanCarDeviceActivity");
                                }
                            } else if (Straction5.equalsIgnoreCase("close") && Iop.GetWorkMode() == 12) {
                                MainUI.this.BackToLauncher();
                                MainUI.this.mEvc.evol_workmode_set(0);
                            }
                        } else if (StrDevName.equalsIgnoreCase("car_recog")) {
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                WinShow.show("com.ts.MainUI", "com.ts.can.CanCarDeviceActivity");
                            } else if (Straction5.equalsIgnoreCase("close") && Iop.GetWorkMode() == 12) {
                                MainUI.this.BackToLauncher();
                                MainUI.this.mEvc.evol_workmode_set(0);
                            }
                        } else if (!StrDevName.equalsIgnoreCase("sync")) {
                        } else {
                            if (Straction5.equalsIgnoreCase(InvokeConstants.INVOKE_OPEN)) {
                                WinShow.show("com.ts.MainUI", "com.ts.can.ford.CanFordSyncActivity");
                            } else if (Straction5.equalsIgnoreCase("close") && Iop.GetWorkMode() == 12) {
                                MainUI.this.BackToLauncher();
                                MainUI.this.mEvc.evol_workmode_set(0);
                            }
                        }
                    } else if (strDomin.equalsIgnoreCase("autoking")) {
                        String Straction6 = intent.getExtras().getString("action");
                        if (Straction6 != null) {
                            Log.i(MainUI.TAG, "ACTION_RECOGNIZE_CMD action ==" + Straction6);
                            if (!MainSet.GetInstance().IsLocal()) {
                                return;
                            }
                            if (Straction6.equalsIgnoreCase("factory_reset")) {
                                MainUI.this.sendBroadcast(new Intent("android.intent.action.MASTER_CLEAR"));
                            } else if (Straction6.equalsIgnoreCase("homekey_enable")) {
                                MainUI.this.nHomeKey_enable = 1;
                            } else if (Straction6.equalsIgnoreCase("homekey_disable")) {
                                MainUI.this.nHomeKey_enable = 0;
                            } else if (Straction6.equalsIgnoreCase("ready_for_acc_off")) {
                            } else {
                                if (Straction6.equalsIgnoreCase("reboot_system")) {
                                    Mcu.SendXKey(20);
                                } else if (Straction6.equalsIgnoreCase("request_vr_status")) {
                                    MainSet.SendVrStateToAutoKing(FtSet.GetSpeechMode());
                                }
                            }
                        }
                    } else if (strDomin.equalsIgnoreCase("phone")) {
                        String Straction7 = intent.getExtras().getString("action");
                        if (Straction7.equalsIgnoreCase("call")) {
                            BtExe.getBtInstance().dial(intent.getExtras().getString("number"));
                        } else if (Straction7.equalsIgnoreCase("incomingcall")) {
                            String StranswerIntent = intent.getExtras().getString("answerIntent");
                            if (StranswerIntent.equalsIgnoreCase("reject")) {
                                if (BtExe.getBtInstance().isHaveCall()) {
                                    BtExe.getBtInstance().hangup();
                                }
                            } else if (StranswerIntent.equalsIgnoreCase("accept") && BtExe.getBtInstance().isHaveCall()) {
                                BtExe.getBtInstance().answer();
                            }
                        } else if (Straction7.equalsIgnoreCase("requestphoneList")) {
                            BtExe.getBtInstance().UpdatePbMap();
                        }
                    }
                } else if (MainUI.ACTION_RECOGNIZE_CMD_VOLUME.equalsIgnoreCase(action)) {
                    int naction = intent.getExtras().getInt("mute");
                    Log.i(MainUI.TAG, "ACTION_RECOGNIZE_CMD mute==" + naction);
                    if (naction == 1) {
                        MainUI.this.mEvc.evol_popmute_set(Iop.GetWorkMode());
                        Log.i(MainUI.TAG, "ACTION_RECOGNIZE_CMD setmute==" + naction);
                    } else if (naction == 2) {
                        MainUI.this.mEvc.evol_popmute_clr(Iop.GetWorkMode());
                        Log.i(MainUI.TAG, "ACTION_RECOGNIZE_CMD clrmute==" + naction);
                    }
                } else if (MainUI.BROADCAST_NET_CHANGE.equalsIgnoreCase(action)) {
                    ConnectivityManager manager = (ConnectivityManager) ctx.getSystemService("connectivity");
                    NetworkInfo gprs = manager.getNetworkInfo(0);
                    NetworkInfo wifi = manager.getNetworkInfo(1);
                    if (gprs.isConnected() || wifi.isConnected()) {
                        Log.w(MainUI.TAG, "net is connect");
                        if (MainUI.nMcuRet == 1 && MainUI.this.nNetIsConnect == 0) {
                            MainUI.this.nNetIsConnect = 1;
                            if (!AuthServer.GetInstance().IsAuthOk() && !MainSet.GetInstance().IsTestMode()) {
                                WinShow.GotoWin(16, 0);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    Log.w(MainUI.TAG, "net is unconnect");
                    MainUI.this.nNetIsConnect = 0;
                    MainUI.this.nOldConnect = 0;
                } else if (MainUI.BROADCAST_LANCHER_FUNC_ACCELERATION.equalsIgnoreCase(action)) {
                    Log.w(MainUI.TAG, "BROADCAST_LANCHER_FUNC_ACCELERATION");
                } else if (MainUI.BROADCAST_LANCHER_FUNC_REBOOT.equalsIgnoreCase(action)) {
                    if (MainUI.nMcuRet == 1) {
                        MainSet.GetInstance().SystemReboot();
                    }
                } else if (MainUI.BROADCAST_LANCHER_FUNC_MUTE.equalsIgnoreCase(action)) {
                    Log.w(MainUI.TAG, "BROADCAST_LANCHER_FUNC_MUTE");
                    Mcu.SetCkey(16);
                } else if (MainUI.BROADCAST_LANCHER_FUNC_VOLUME.equalsIgnoreCase(action)) {
                    Log.w(MainUI.TAG, "BROADCAST_LANCHER_FUNC_VOLUME");
                    MainUI.this.mMainVolume.VolWinShow();
                } else if (MainUI.BROADCAST_LANCHER_FUNC_VOLUMEADD.equalsIgnoreCase(action)) {
                    Log.w(MainUI.TAG, "BROADCAST_LANCHER_FUNC_VOLUMEADD");
                    MainUI.this.mEvc.Evol_vol_tune(1);
                } else if (MainUI.BROADCAST_LANCHER_FUNC_VOLUMEDEC.equalsIgnoreCase(action)) {
                    Log.w(MainUI.TAG, "BROADCAST_LANCHER_FUNC_VOLUMEDEC");
                    MainUI.this.mEvc.Evol_vol_tune(0);
                } else if (MainUI.BROADCAST_LANCHER_FUNC_SCREENOFF.equalsIgnoreCase(action)) {
                    Mcu.BklTurn();
                    Log.w(MainUI.TAG, "BROADCAST_LANCHER_FUNC_SCREENOFF");
                } else if (MainUI.BROADCAST_LANCHER_FUNC_NOWMODE.equalsIgnoreCase(action)) {
                    Log.w(MainUI.TAG, "BROADCAST_LANCHER_FUNC_NOWMODE");
                    if (Iop.GetWorkMode() != 0) {
                        WinShow.TsEnterMode(Iop.GetWorkMode());
                    } else if (MainSet.GetInstance().IsHaveApk("cn.kuwo.kwmusiccar")) {
                        MainSet.GetInstance().openApplication(MainUI.mMainUI, "cn.kuwo.kwmusiccar");
                    } else if (MainSet.GetInstance().IsHaveApk(Constant.PACKAGE_NAME)) {
                        MainSet.GetInstance().openApplication(MainUI.mMainUI, Constant.PACKAGE_NAME);
                    } else {
                        WinShow.TsEnterMode(4);
                    }
                } else if (MainUI.BROADCAST_LANCHER_FUNC_BRIGHTNESS.equalsIgnoreCase(action)) {
                    Log.w(MainUI.TAG, "BROADCAST_LANCHER_FUNC_BRIGHTNESS");
                    WinShow.show("com.ts.MainUI", "com.ts.set.SettingDisplayActivity");
                } else if (MainUI.BROADCAST_LANCHER_FUNC_BLUETOOTH.equalsIgnoreCase(action)) {
                    Log.w(MainUI.TAG, "BROADCAST_LANCHER_FUNC_BLUETOOTH");
                    if (BtExe.getBtInstance().isConnected()) {
                        WinShow.GotoWin(7, 3);
                    } else {
                        WinShow.GotoWin(7, 0);
                    }
                } else if (MainUI.BROADCAST_MEDIA_LISTUPDATE.equalsIgnoreCase(action)) {
                    Log.i(MainUI.TAG, "playlisname==com.ts.media.listupdate");
                    if (MainUI.this.mTsPlayerService != null) {
                        try {
                            TxzReg.GetInstance().AddMusicList(MainUI.this.mTsPlayerService.getMediaList());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                } else if ("ActivityWarm_Closed".equalsIgnoreCase(action)) {
                    if (MainUI.bEnterMode) {
                        WinShow.TsEnterMode(Iop.GetWorkMode());
                        MainUI.bEnterMode = false;
                    }
                } else if (MainUI.BROADCAST_AUTOKING_START_UPDATE_SYSTEM.equalsIgnoreCase(action)) {
                    Log.i(MainUI.TAG, "receive update message set 10 min");
                    FtSet.SetStandByTime(1);
                } else if (MainUI.BROADCAST_AUTOKING_UPDATE_SYSTEM_SUCCESS.equalsIgnoreCase(action)) {
                    Log.i(MainUI.TAG, "receive update success message now to reset");
                    FtSet.SetStandByTime(0);
                    Mcu.SendXKey(19);
                }
            }
        }
    }

    private void UpLoadTheLocation() {
        LocationManager loctionManager = (LocationManager) getSystemService("location");
        Location location = loctionManager.getLastKnownLocation("gps");
        if (location != null) {
            Log.i(TAG, "location.getLatitude()" + location.getLatitude());
            Log.i(TAG, "location.getAltitude()" + location.getAltitude());
        }
        loctionManager.requestLocationUpdates("gps", 1000, 0.0f, this.locationListener);
    }

    public void EnterMyDvdMode(int nMode2) {
        if (this.mTsPlayerService != null) {
            try {
                this.mTsPlayerService.enterMedia(nMode2);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean DvdLoadOK() {
        if (this.mTsPlayerService != null) {
            try {
                return this.mTsPlayerService.isDvdLoadOK();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void KeyBeep() {
        Mcu.SendBeep(1);
    }

    public static int IsCameraMode() {
        if (FtSet.GetCamFix() == 0) {
            return 0;
        }
        return Mcu.GetReverse();
    }

    public static int IsRightCamMode() {
        if (CanIF.GetOtherCamMode() == 0) {
            return 0;
        }
        return 1;
    }

    private void SetCameraVPort(int nPort) {
        Log.i(TAG, "SetCameraVPort = " + nPort);
        nCamVport = nPort;
        if (nPort == 0) {
            SystemProperties.set("forfan.backcar.port", MainSet.SP_XH_DMAX);
        } else {
            SystemProperties.set("forfan.backcar.port", MainSet.SP_KS_QOROS);
        }
    }

    class ITsCom extends ITsCommon.Stub {
        ITsCom() {
        }

        public void EnterMode(int nMode) throws RemoteException {
            Log.i(MainUI.TAG, "ITsCom = " + nMode);
            if (nMode <= 15) {
                MainUI.this.mEvc.evol_workmode_set(nMode);
            }
        }

        public void Mute() throws RemoteException {
            MainUI.this.mEvc.evol_mut_set(1);
        }

        public void VolInc() throws RemoteException {
            MainUI.this.mEvc.Evol_vol_tune(1);
            MainUI.this.KeyBeep();
        }

        public void VolDec() throws RemoteException {
            MainUI.this.mEvc.Evol_vol_tune(0);
            MainUI.this.KeyBeep();
        }

        public void VolSet(int nVol) throws RemoteException {
            MainUI.this.mEvc.evol_vol_set(nVol);
        }

        public void GotoEq() throws RemoteException {
            WinShow.TurnToEq();
        }

        public int GetReverState() throws RemoteException {
            return MainUI.IsCameraMode();
        }

        public int IsHaveDisc() throws RemoteException {
            if (MainUI.nMcuRet != 1) {
                return 2;
            }
            if (!WinShow.IsHaveDvd()) {
                return 0;
            }
            return 1;
        }

        public int GetBrakeState() throws RemoteException {
            if (StSet.GetDriveVideo() == 1 && Mcu.GetBrake() == 0) {
                return 1;
            }
            return 0;
        }

        public void SendMcuKey(int nKey) throws RemoteException {
            Mcu.SetCkey(nKey);
        }

        public int nGetWorkMode() throws RemoteException {
            return Iop.GetWorkMode();
        }

        public boolean BtIsConnect() throws RemoteException {
            return BtExe.getBtInstance().isConnected();
        }

        public boolean IsMute() throws RemoteException {
            return Iop.GetMute() == 1;
        }

        public boolean IsNightMode() throws RemoteException {
            return Mcu.GetIll() == 1;
        }

        public void TsVolumeShow() throws RemoteException {
            MainVolume.GetInstance().nAidlVolumeShow = 1;
        }

        public void BklTurn() throws RemoteException {
            Mcu.BklTurn();
        }

        public boolean IsTconAdj() throws RemoteException {
            if (FtSet.GetTconAdj() == 1) {
                return true;
            }
            return false;
        }

        public void TconSetHide() throws RemoteException {
            ScreenSet.GetInstance().nAidlHide = 1;
        }

        public void TconDvdSetShow() throws RemoteException {
        }

        public void TconVideoSetShow() throws RemoteException {
        }

        public boolean IsLastMemory() throws RemoteException {
            if (FtSet.GetLastMemory() == 1) {
                return true;
            }
            return false;
        }

        public float GetSpeed() throws RemoteException {
            return MainUI.this.GpsSpeed;
        }

        public String GetTemp() throws RemoteException {
            String StrTemp;
            MainUI.this.mOutTemp = Can.mOutTemp;
            if (MainUI.this.mOutTemp.UpdateOnce == 0) {
                return null;
            }
            if (MainUI.this.mOutTemp.DW == 0) {
                StrTemp = String.valueOf(MainUI.this.getResources().getString(R.string.Laucher_out_temp)) + MainUI.this.mOutTemp.Val + MainUI.this.getResources().getString(R.string.Laucher_out_temp_dw1);
            } else {
                StrTemp = String.valueOf(MainUI.this.getResources().getString(R.string.Laucher_out_temp)) + MainUI.this.mOutTemp.Val + MainUI.this.getResources().getString(R.string.Laucher_out_temp_dw2);
            }
            return StrTemp;
        }

        public float GetCog() throws RemoteException {
            return MainUI.this.GpsCog;
        }

        public int GetCurTime() throws RemoteException {
            if (MainUI.this.GetMcuState() == 2 || MainUI.this.mTsPlayerService == null) {
                return 0;
            }
            return (int) MainUI.this.mTsPlayerService.getCurrentTime();
        }

        public int GetTotalTime() throws RemoteException {
            if (MainUI.this.GetMcuState() == 2 || MainUI.this.mTsPlayerService == null) {
                return 0;
            }
            return (int) MainUI.this.mTsPlayerService.getTotoalTime();
        }

        public int GetPlayState() throws RemoteException {
            if (!(MainUI.this.GetMcuState() == 2 || MainUI.this.mTsPlayerService == null)) {
                if (MainUI.this.mTsPlayerService.getPlayStatus() == 2) {
                    return 1;
                }
                if (MainUI.this.mTsPlayerService.getPlayStatus() != 5) {
                    return 0;
                }
                return 2;
            }
            return 0;
        }

        public String GetSongName() throws RemoteException {
            if (Iop.GetWorkMode() == 5) {
                return BtExe.mStrId3Name;
            }
            if (MainUI.this.GetMcuState() == 2 || MainUI.this.mTsPlayerService == null) {
                return " ";
            }
            return MainUI.this.mTsPlayerService.getPlayName();
        }

        public String GetId3Album() throws RemoteException {
            if (Iop.GetWorkMode() == 5) {
                BtExe.getBtInstance();
                return BtExe.mStrId3Album;
            } else if (MainUI.this.mTsPlayerService != null) {
                return MainUI.this.mTsPlayerService.getId3AlbumName();
            } else {
                return " ";
            }
        }

        public String GetId3Artist() throws RemoteException {
            if (Iop.GetWorkMode() == 5) {
                BtExe.getBtInstance();
                return BtExe.mStrId3Artist;
            } else if (MainUI.this.mTsPlayerService != null) {
                return MainUI.this.mTsPlayerService.getId3Artist();
            } else {
                return " ";
            }
        }

        public String GetId3Title() throws RemoteException {
            if (MainUI.this.mTsPlayerService != null) {
                return MainUI.this.mTsPlayerService.getId3Title();
            }
            return " ";
        }

        public String GetBand() throws RemoteException {
            if (Radio.GetDisp(2) >= 4) {
                return "AM";
            }
            return "FM";
        }

        public String GetFreq() throws RemoteException {
            int curFreq = Radio.GetDisp(1);
            if (Radio.GetDisp(2) >= 4) {
                return String.format("%d", new Object[]{Integer.valueOf(curFreq)});
            }
            return String.format("%.2f", new Object[]{Float.valueOf(((float) curFreq) / 100.0f)});
        }

        public void TconSet(int nMode) throws RemoteException {
            TsDisplay.GetInstance().SetDisp(nMode);
        }

        public int GetSDCard() throws RemoteException {
            return FtSet.GetSdSwap();
        }

        public void EnterActivity(int nMode) throws RemoteException {
            if (nMode == -1) {
                WinShow.TsEnterMode(Iop.GetWorkMode());
            } else {
                WinShow.TsEnterMode(nMode);
            }
        }

        public Map GetListBt() throws RemoteException {
            return null;
        }

        public Map GetListMedia() throws RemoteException {
            if (MainUI.this.mTsPlayerService != null) {
                return MainUI.this.mTsPlayerService.getMediaList();
            }
            return null;
        }

        public void PlayByPath(String path) throws RemoteException {
            if (MainUI.this.mTsPlayerService != null) {
                MainUI.this.mTsPlayerService.playByPath(path);
            }
        }

        public void BtDail(String Number) throws RemoteException {
            if (Number != null) {
                BtExe.getBtInstance().dial(Number);
            }
        }

        public void PopMuteSet(int nMode) throws RemoteException {
            if (nMode == -1) {
                MainUI.this.mEvc.evol_popmute_set(Iop.GetWorkMode());
            } else {
                MainUI.this.mEvc.evol_popmute_set(nMode);
            }
        }

        public void PopMuteClear(int nMode) throws RemoteException {
            if (nMode == -1) {
                MainUI.this.mEvc.evol_popmute_clr(Iop.GetWorkMode());
            } else {
                MainUI.this.mEvc.evol_popmute_clr(nMode);
            }
        }

        public int GetRadio_N_Step() throws RemoteException {
            return Radio.GetDisp(5);
        }

        public int GetRadio_T_Step() throws RemoteException {
            return Radio.GetDisp(4);
        }

        public int StepToFreq(int nStep) throws RemoteException {
            return Radio.StepToFreq(nStep);
        }

        public int nGetKey() throws RemoteException {
            int nTEMP = MainUI.this.nNawKey;
            MainUI.this.nNawKey = 0;
            return nTEMP;
        }

        public int getRepeatMode() throws RemoteException {
            if (MainUI.this.mTsPlayerService != null) {
                return MainUI.this.mTsPlayerService.getRepeatMode();
            }
            return 0;
        }

        public int getShuffleMode() throws RemoteException {
            if (MainUI.this.mTsPlayerService != null) {
                return MainUI.this.mTsPlayerService.getShuffleMode();
            }
            return 0;
        }

        public boolean GetRadioSTState() throws RemoteException {
            return (Radio.GetDispFlag() & 1) != 0;
        }

        public boolean GetRadioSTSwitch() throws RemoteException {
            return (Radio.GetDispFlag() & 2) != 0;
        }

        public String GetIMEI() throws RemoteException {
            if (MainUI.nMcuRet == 1) {
                return MainSet.GetInstance().GetSerid();
            }
            return " ";
        }

        public String GetIMSI() throws RemoteException {
            if (MainSet.GetInstance().IsLocal()) {
                return "123456789456";
            }
            if (MainUI.nMcuRet != 1 || (Mcu.GetPowState() & 1) == 0) {
                return "0";
            }
            return MainSet.SP_XPH5;
        }

        public String GetDeviceID() throws RemoteException {
            if (MainUI.nMcuRet != 1 || !AuthServer.GetInstance().IsAuthOk()) {
                return null;
            }
            return MainSet.GetInstance().GetSerid();
        }

        public int GetMcuPowerState() throws RemoteException {
            if (Mcu.GetGsensorState() == 1) {
                return 2;
            }
            if (MainUI.this.GetMcuState() == 0) {
                return 0;
            }
            if (MainUI.this.GetMcuState() == 3) {
                return 17;
            }
            return MainUI.this.GetMcuState() == 2 ? 16 : 0;
        }

        public IBinder getSpecialBinder(String name) throws RemoteException {
            if (name.equalsIgnoreCase("Radio")) {
                return (IBinder) MainUI.this.mRadioCommon;
            }
            if (name.equalsIgnoreCase("Bt")) {
                return (IBinder) MainUI.this.mBTCommon;
            }
            return null;
        }
    }

    public IBinder onBind(Intent intent) {
        return this.mTsCom;
    }

    class AdasServiceConnection implements ServiceConnection {
        AdasServiceConnection() {
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            MainUI.this.mIAdasBinderInterface = IAdasBinderInterface.Stub.asInterface(service);
            Log.i("adas", "AdasServiceConnection");
            if (MainUI.IsCameraMode() == 0 && MainUI.this.mIAdasBinderInterface != null) {
                try {
                    MainUI.this.mIAdasBinderInterface.setBackSignal(0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        public void onServiceDisconnected(ComponentName arg0) {
            MainUI.this.mIAdasBinderInterface = null;
        }
    }

    class DvpServiceConnection implements ServiceConnection {
        DvpServiceConnection() {
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            MainUI.this.mTsPlayerService = ITsPlayerService.Stub.asInterface(service);
            if (MainUI.this.nBatFirst == 1) {
                try {
                    MainUI.this.mTsPlayerService.mediaTask(MainUI.this.nBatFirst);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            MainUI.this.mTsPlayerService = null;
            if (MainUI.this.nPowerMode != 2) {
                MainUI.this.InintDvpInterface();
            }
        }
    }

    public void VoiceBtnShow(boolean bShow) {
        if (bShow) {
            startService(new Intent("com.ts.tts_touch.TouchService"));
        } else {
            stopService(new Intent("com.ts.tts_touch.TouchService"));
        }
    }

    /* access modifiers changed from: package-private */
    public void InintDvpInterface() {
        Intent intent = new Intent();
        intent.setAction("com.ts.dvdplayer.MediaPlayerService");
        intent.setPackage("com.ts.dvdplayer");
        bindService(intent, this.mServiceConnection, 1);
    }

    /* access modifiers changed from: package-private */
    public void InintAdasInterface() {
        Intent intent = new Intent();
        if (MainSet.GetInstance().IsHaveApk(KAIYI_PNAME)) {
            intent.setPackage(KAIYI_PNAME);
            intent.setAction("com.adasplus.ts.AdasRemoteSettingService");
            bindService(intent, this.mAdasServiceConnection, 1);
            intent = new Intent();
            intent.setPackage(KAIYI_PNAME);
            intent.setAction("com.adasplus.ts.CameraService");
        } else {
            intent.setAction("android.intent.action.START_ADAS");
        }
        startService(intent);
    }

    /* access modifiers changed from: package-private */
    public void InintVoiceInterface() {
        if (FtSet.GetSpeechMode() != 0 || !MainSet.isZh()) {
            VoiceBtnShow(false);
        } else {
            VoiceBtnShow(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void InintDvrServer() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.DVR_SERVICE");
        startService(intent);
    }

    /* access modifiers changed from: package-private */
    public void InintAdasServer() {
    }

    public void InintSbcServer() {
        Intent intent2 = new Intent();
        intent2.setAction("com.chinatsp.sendcar.ls.service");
        startService(intent2);
    }

    /* access modifiers changed from: package-private */
    public void SetAdasLightState() {
        if (this.nILL != Mcu.GetIll()) {
            this.nILL = Mcu.GetIll();
            if (this.nILL == 1) {
                SystemProperties.set(BROADCAST_ADAS_LIGHT_STATE, "on");
                Log.i(TAG, "BROADCAST_ADAS_LIGHT_STATE   on ");
                AmapAuto.SetHeadLight(0);
                AmapAuto.GetInstance().SendCarInfordata();
                try {
                    if (this.mIAdasBinderInterface != null) {
                        this.mIAdasBinderInterface.setHeadLight(0);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                SystemProperties.set(BROADCAST_ADAS_LIGHT_STATE, "off");
                Log.i(TAG, "BROADCAST_ADAS_LIGHT_STATE   off ");
                AmapAuto.SetHeadLight(1);
                AmapAuto.GetInstance().SendCarInfordata();
                try {
                    if (this.mIAdasBinderInterface != null) {
                        this.mIAdasBinderInterface.setHeadLight(1);
                    }
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void InintParat() {
        Evc.mSystemState = 5;
        this.nErrorNum = 0;
        this.nDvdMode = 0;
        this.nDelayToGetVerSion = 100;
        bIsInCamera = false;
        bIsInRight = false;
        nCamVport = 0;
        bBtConnect = false;
        this.nNetIsConnect = 0;
        this.nOldConnect = 0;
        this.nUpdateTime = 0;
        this.nAutoToNavi = 0;
        bIsScreenMode = false;
        this.nTpmsOnce = 0;
        this.nDvrDelayTime = 300;
        this.nOldPowerMode = 0;
        this.nILL = 255;
        nMcuRet = 0;
        this.nHomeKey_enable = 1;
        if (MainSet.IsGLSXVer().booleanValue()) {
            this.nDelayToVoice = 300;
        }
    }

    /* access modifiers changed from: package-private */
    public void AdjustMcuTime() {
        Date d1 = null;
        try {
            d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-10-01 00:00:00");
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        long diff = System.currentTimeMillis() - d1.getTime();
        Log.i(TAG, "AdjustMcuTime diff = " + diff);
        if (diff > 9216000) {
            Mcu.SendXKey((int) ((diff / 1000) / 60));
        }
    }

    /* access modifiers changed from: package-private */
    public void AdjustArmLocalTime() {
        long Time = (long) Mcu.GetMcuVer(new char[32]);
        Log.i(TAG, "AdjustArmLocalTime Time = " + Time);
        if (Time > 1440) {
            Date d1 = null;
            try {
                d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-10-01 00:00:00");
                if (System.currentTimeMillis() > d1.getTime()) {
                    return;
                }
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            SystemClock.setCurrentTimeMillis(d1.getTime() + (60 * Time * 1000));
        }
    }

    /* access modifiers changed from: package-private */
    public void WriteLicenceToFile() {
        if (!TsFile.fileIsExists(EASYLICENSE_FILE)) {
            Log.i(TAG, "fiel not exit ");
            TsFile.NewDir(EASYLICENSE_PATH);
            try {
                TsFile.writeFileSdcardFile(EASYLICENSE_FILE, MainSet.GetInstance().GetSerid());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            if (!TsFile.readFileSdcardFile(EASYLICENSE_FILE).equals(MainSet.GetInstance().GetSerid())) {
                TsFile.writeFileSdcardFile(EASYLICENSE_FILE, MainSet.GetInstance().GetSerid());
                Log.i(TAG, "fiel read ok and not equals= ");
                return;
            }
            Log.i(TAG, "fiel read ok and  equals= ");
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public void SetNetConState(boolean mobileDataEnabled) {
        TelephonyManager telephonyService = (TelephonyManager) getSystemService("phone");
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

    /* access modifiers changed from: package-private */
    public boolean CheckSimSccid(String Sccid) {
        Log.w(TAG, "CheckSimSccid==" + Sccid);
        if (Sccid == null || Sccid.length() < 0) {
            return false;
        }
        if (!TsFile.fileIsExists(SIM_SCCID_FILE)) {
            Log.w(TAG, "SIM_SCCID_FILE NOT==" + Sccid);
            return false;
        }
        try {
            Log.w(TAG, "SIM_SCCID_FILE HAVE==" + Sccid);
            String myidString = TsFile.readFileSdcardFile(SIM_SCCID_FILE);
            Log.w(TAG, "myidString==" + myidString);
            String mySccid = myidString.substring(0, myidString.length() - 1);
            Log.w(TAG, "mySccid==" + mySccid);
            if (mySccid == null || !mySccid.equals(Sccid)) {
                return false;
            }
            String subsString = myidString.substring(myidString.length() - 1, myidString.length());
            Log.w(TAG, "subsString==" + subsString);
            if (subsString.equals("*")) {
                return false;
            }
            if (subsString.equals("#")) {
                return true;
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public void CheckSIMState() {
        switch (((TelephonyManager) getSystemService("phone")).getSimState()) {
            case 1:
                bSimConnect = false;
                return;
            case 5:
                if (!bSimConnect) {
                    bSimConnect = true;
                    SetNetConState(true);
                    MainSet.GetInstance().CheckSimOwner();
                    return;
                }
                return;
            default:
                return;
        }
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

    public int GetToalMemory() {
        if (this.mActivityManager == null) {
            this.mActivityManager = (ActivityManager) getSystemService("activity");
        }
        if (this.TotalSize == 0) {
            this.mActivityManager.getMemoryInfo(this.memoryInfo);
            this.TotalSize = (int) ((this.memoryInfo.totalMem / 1024) / 1024);
        }
        return this.TotalSize;
    }

    public int GetAviMemory() {
        if (this.mActivityManager == null) {
            this.mActivityManager = (ActivityManager) getSystemService("activity");
        }
        this.mActivityManager.getMemoryInfo(this.memoryInfo);
        return (int) ((this.memoryInfo.availMem / 1024) / 1024);
    }

    /* access modifiers changed from: package-private */
    public void SetMemInfo() {
        int version = FtSet.GetSdSwap();
        if (version == 1) {
            int id = getResources().getIdentifier("system_ver_num", "string", getPackageName());
            if (id != 0) {
                SystemProperties.set("forfan.version.info", getResources().getString(id));
            } else {
                SystemProperties.set("forfan.version.info", "8.1");
            }
        } else if (version > 1) {
            if (version > 50) {
                SystemProperties.set("forfan.version.info", String.format(Locale.ENGLISH, "%.1f", new Object[]{Float.valueOf(((float) version) / 10.0f)}));
            } else {
                SystemProperties.set("forfan.version.info", version + 5 >= 9 ? new StringBuilder(String.valueOf(version + 5)).toString() : String.valueOf(version + 5) + ".1");
            }
        }
        if (FtSet.GetRom() == 0) {
            SystemProperties.set("forfan.storage.total", MainSet.GetInstance().GetEmmcSize());
        } else {
            SystemProperties.set("forfan.storage.total", String.valueOf(FtSet.GetRom() * 16) + "GB");
        }
        int TotalSize2 = GetToalMemory();
        if (FtSet.GetRam() == 0) {
            if (TotalSize2 > 1539 && TotalSize2 < 2048) {
                SystemProperties.set("forfan.totalRam.M", "2048");
            } else if (TotalSize2 > 1024 && TotalSize2 < 1539) {
                SystemProperties.set("forfan.totalRam.M", "1960");
            }
            if (TotalSize2 >= 1024 || TotalSize2 <= 768) {
                if (TotalSize2 < 768) {
                    SystemProperties.set("forfan.totalRam.M", "1024");
                }
            } else if (MainSet.IsYSJQP()) {
                SystemProperties.set("forfan.totalRam.M", "1536");
            } else {
                SystemProperties.set("forfan.totalRam.M", "1024");
            }
        } else {
            SystemProperties.set("forfan.totalRam.M", new StringBuilder().append(FtSet.GetRam() * 1024).toString());
        }
    }

    /* access modifiers changed from: package-private */
    public void SetDAPinIO() {
        tool.GetInstance().DealSu("echo -wmode 78 4 > /sys/class/misc/mtgpio/pin");
        tool.GetInstance().DealSu("echo -wmode 79 4 > /sys/class/misc/mtgpio/pin");
        tool.GetInstance().DealSu("echo -wmode 80 4 > /sys/class/misc/mtgpio/pin");
    }

    /* access modifiers changed from: package-private */
    public void CheckPermision() {
        new Thread() {
            public void run() {
                tool.GetInstance().CheckAllPermision();
                tool.GetInstance().DealSu("setprop ril.external.md 0");
            }
        }.start();
    }

    public void WmInint() {
        if (Evc.mSystemState < 5) {
            Log.w(TAG, "######## WmInit = " + Evc.mSystemState + " !");
            if (!BackcarService.getInstance().bIninOK) {
                Log.i(TAG, "BackcarService.nStep = " + BackcarService.nStep + " !");
                if (MainSet.GetInstance().IsXT5()) {
                    Mcu.SendXKey(32);
                    return;
                }
                return;
            }
            this.nFsok = MainSet.GetInstance().FtSetInint();
            if (MainSet.IsFxCarplay() && FtSet.GetUsbHost() == 0) {
                FsBaseActivity.setUsbMode(1);
                FtSet.Save(0);
            }
            if (MainSet.GetInstance().IsXT5() && FtSet.GetVolSafe() < 26) {
                FtSet.SetVolSafe(26);
                FtSet.Save(0);
            }
            SetMemInfo();
            InintParat();
            mScrW = SystemProperties.getInt("ro.forfan.hardware.width", 1024);
            mScrH = SystemProperties.getInt("ro.forfan.hardware.height", CanCameraUI.BTN_GOLF_WC_MODE1);
            Log.w(TAG, "######## WmInit = mScrW = " + mScrW + " !");
            Log.w(TAG, "######## WmInit = mScrH = " + mScrH + " !");
            if (mScrW == 800 && mScrH == 480) {
                mScrW = 1024;
                mScrH = CanCameraUI.BTN_GOLF_WC_MODE1;
                Iop.tsxhwStart(1);
            } else if (mScrW == 800 && mScrH == 600) {
                mScrW = 1024;
                mScrH = CanCameraUI.BTN_GOLF_WC_MODE1;
                Iop.tsxhwStart(3);
            } else if (MainSet.GetScreenType() == 7) {
                Iop.tsxhwStart(2);
            } else {
                Iop.tsxhwStart(0);
            }
            nMcuRet = Mcu.mcutask();
            MainSet.GetInstance().CheckTestMode(false);
            if (this.mMainSet.IsTestMode()) {
                this.nFsok = 1;
            } else {
                switch (this.nFsok) {
                    case 0:
                        WinShow.GotoWin(15, 0);
                        Evc.mSystemState = 12;
                        MainSet.GetInstance().bCheckFist();
                        Log.w(TAG, "######## WmInitFactory = " + Evc.mSystemState + " !");
                        return;
                }
            }
            if (mCanInterface != null) {
                mCanInterface.CanInit(getApplicationContext());
                this.bCaninit = true;
            }
            Mcu.mcunext();
        }
        if (nMcuRet != 1) {
            nMcuRet = Mcu.mcutask();
            if (nMcuRet == 0) {
                this.nErrorNum++;
            }
            Log.i(TAG, "nMcuRet = 0==" + this.nErrorNum);
            return;
        }
        byte[] mcuid = new byte[14];
        Mcu.GetSerialId(mcuid);
        MainSet.seiid = CanIF.byte2String(mcuid);
        if (nMcuRet == 0) {
            Evc.mSystemState = 13;
            Log.w(TAG, "######## WmInitSyncErr = " + Evc.mSystemState + " !");
            Log.e(TAG, "******************WmInit error MCU SYSNC ERROR*****************");
            return;
        }
        int nBatFirst2 = Mcu.GetPowState() & 1;
        int nSetRet = StSet.SetInit();
        if (!MainSet.isZh()) {
            byte[] byteNavipath = new byte[128];
            StSet.GetNaviPack(byteNavipath);
            if (CanIF.byte2String(byteNavipath).equals("com.autonavi.amapauto")) {
                if (MainSet.GetInstance().IsCustom("LEMA")) {
                    StSet.SetNaviName(StrToByte128("iGO"));
                    StSet.SetNaviPack(StrToByte128("com.navngo.igo.javaclient"));
                    StSet.SetSave();
                } else if (MainSet.GetInstance().IsHaveApk("com.google.android.apps.maps")) {
                    StSet.SetNaviName(StrToByte128("Maps"));
                    StSet.SetNaviPack(StrToByte128("com.google.android.apps.maps"));
                    StSet.SetSave();
                } else if (!MainSet.GetInstance().IsCustom("RLFA")) {
                    StSet.SetNaviName(StrToByte128("  "));
                    StSet.SetNaviPack(StrToByte128("  "));
                    StSet.SetSave();
                } else if (MainSet.GetInstance().IsHaveApk("com.atlan")) {
                    StSet.SetNaviName(StrToByte128("Atlan"));
                    StSet.SetNaviPack(StrToByte128("com.atlan"));
                    StSet.SetSave();
                }
            }
        } else if (MainSet.GetInstance().IsLocal()) {
            StSet.SetNaviName(StrToByte128("樂客導航王A5i 3D"));
            StSet.SetNaviPack(StrToByte128("com.kingwaytek.naviking3d"));
            StSet.SetSave();
        }
        if (nBatFirst2 == 1) {
            Evc.mSystemState = 10;
            Log.w(TAG, "######## WmInitSyncB+ = " + Evc.mSystemState + " !");
        } else {
            Evc.mSystemState = 11;
            Log.w(TAG, "######## WmInitSyncAcc = " + Evc.mSystemState + " !");
        }
        AdjustArmLocalTime();
        InintDvpInterface();
        if (!MainSet.GetInstance().IsXuhuiford()) {
            BtExe.getBtInstance().Init();
        } else if (MainSet.GetInstance().IsHaveBt()) {
            BtExe.getBtInstance().Init();
            Log.w(TAG, "is have bt ");
            BtExe.getBtInstance().setBluetoothEnabled(true);
        } else {
            BtExe.getBtInstance().setBluetoothEnabled(false);
        }
        if (!MainSet.GetInstance().IsTestMode() || MainSet.bKeyBroad) {
            Radio.TuneTask(nBatFirst2);
        }
        this.mStTpms.Inint(nBatFirst2);
        if (MainSet.GetInstance().IsXT5()) {
            Iop.BD3702Disable();
        }
        if (getResources().getString(R.string.support_ten_eqband).equals(MainSet.SP_XPH5)) {
            Iop.EqExtend(1, 0);
        }
        Iop.EvolTask(nBatFirst2);
        if (MainSet.GetInstance().IsGLSXLP().booleanValue()) {
            this.mEvc.nIsGlsx = 1;
        }
        Evc.GetInstance().nWorkModeMute = 0;
        this.mEvc.InintEvc(this);
        this.mEvc.SetEvcCallBack(this);
        ScreenSet.GetInstance().Inint(this);
        this.mDisplay.Inint();
        MainVolume.nBklisOn = Mcu.BklisOn();
        CstTv.GetInstance().Inint();
        if (!this.mMainSet.IsTestMode() && this.nFsok == 1 && AuthServer.GetInstance().IsAuthOk()) {
            WriteLicenceToFile();
            Log.i(TAG, "mEvc.Evol.workmode = " + Iop.GetWorkMode());
            if (Iop.GetWorkMode() == 5) {
                Iop.SetWorkMode(1);
            }
            if (MainSet.GetInstance().IsCustom("GLAX") && Iop.GetWorkMode() == 3) {
                Iop.SetWorkMode(0);
            }
            if (MainSet.IsGLSXVer().booleanValue() && !MainSet.IsGLlastmem()) {
                Iop.SetWorkMode(0);
            }
            if (MainSet.GetInstance().IsLocal()) {
                Evc.GetInstance().evol_workmode_set(0);
            } else if (!MainSet.IsXPH5() || MainSet.IsXPH5_HZ()) {
                if (MainSet.IsMkz()) {
                    WinShow.TsEnterMode(12);
                } else if (MainSet.IsQOROS()) {
                    String proid = MainSet.GetInstance().GetProid();
                    Log.i(TAG, "proid = " + proid);
                    if (proid == null || proid.length() != 17) {
                        WinShow.show("com.ts.MainUI", "com.ts.gz.activity.VinActivity");
                        Evc.GetInstance().evol_workmode_set(0);
                    } else {
                        WinShow.TsEnterMode(Iop.GetWorkMode());
                    }
                } else if (MainSet.IsFlkj()) {
                    Evc.GetInstance().evol_workmode_set(0);
                } else {
                    WinShow.TsEnterMode(Iop.GetWorkMode());
                }
                if (StSet.GetAutoNavi() == 1) {
                    this.nAutoToNavi = 100;
                }
            } else {
                bEnterMode = true;
            }
        } else if (this.mMainSet.IsTestMode()) {
            WinShow.show("com.ts.MainUI", "com.ts.main.auth.FactoryMainActivity");
        } else if (!AuthServer.GetInstance().IsAuthOk()) {
            WinShow.GotoWin(16, 0);
        }
        tool.GetInstance().Inint(getApplicationContext());
        this.mMainVolume.Inint(getApplication(), getApplicationContext());
        MainLight.GetInstance().Inint(getApplication(), getApplicationContext());
        MainAlterwin.GetInstance().Inint(getApplication(), getApplicationContext());
        this.mKeyTouch.Inint(getApplicationContext());
        UpLoadTheLocation();
        AuthServer.GetInstance().UpLoadTheLocation(getApplicationContext());
        Glsx.GetInstance().Inint(getApplicationContext());
        AmapAuto.GetInstance().Inint(getApplicationContext());
        long time = SystemClock.uptimeMillis();
        if (nSetRet == 0) {
            MainSet.GetInstance().Inint(1);
        } else {
            MainSet.GetInstance().Inint(nBatFirst2);
        }
        MainAlterwin.GetInstance().Inint(getApplication(), getApplicationContext());
        Log.i(TAG, "********time******=" + ((int) (SystemClock.uptimeMillis() - time)));
        InintKeyBeep();
        MainSet.GetInstance().bCheckFist();
        Log.i(TAG, "InintVolBar ok");
        Log.i(TAG, "******************WmInit*****************");
        startCardataServiceIfExist();
        if (FtSet.IsIconExist(2) == 1) {
            CheckDiscState();
        }
        if (MainSet.IsRxfKoren()) {
            bSupportMute = false;
        } else if (FtSet.GetNoiseDown() == 0) {
            bSupportMute = false;
        }
        if (MainSet.IsGLSXVer().booleanValue()) {
            if (FtSet.GetSpeechMode() != 3) {
                TxzReg.GetInstance().Inint0(this);
            }
            try {
                startGlsxDdhBoxService();
            } catch (Exception e) {
                e.printStackTrace();
            }
            MainSet.SendIntent(BROADCAST_GLSX_ACC_ON);
        }
        SetDAPinIO();
        if (MainSet.GetInstance().IsHaveApk("com.android.vending")) {
            CheckPermision();
        }
    }

    /* access modifiers changed from: package-private */
    public void startGlsxDdhBoxService() throws Exception {
        Log.d("lh", "startGlsxDdhBoxService");
        Intent intent = new Intent();
        intent.setPackage("com.ts.MainUI");
        intent.setAction("com.ts.glsx.ddhbox.TsGlsxDdhBoxService");
        startService(intent);
    }

    private void startCardataServiceIfExist() {
        Log.d("lh", "startCardataServiceIfExist");
        try {
            Class<?> cls = Class.forName("com.ts.gz.TsCarDataService");
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.ts.MainUI", "com.ts.gz.TsCarDataService"));
            startService(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Intent intent2 = new Intent();
        intent2.setComponent(new ComponentName("com.ts.gz.cardata", "com.ts.gz.cardata.CardataService"));
        startService(intent2);
    }

    public void onDestroy() {
        Log.w(TAG, "######## Service Destroy !");
        super.onDestroy();
    }

    public void onCreate() {
        Log.w(TAG, "######## onCreate = " + Evc.mSystemState + " !");
        File file = new File("/mnt/sdcard/record.amr");
        if (file.exists()) {
            file.delete();
        }
        if (MainSet.IsFxCarplay()) {
            MainSet.GetInstance().openApplication(this, "com.baidu.carlife");
        }
        mMainUI = this;
        if (MainSet.GetScreenType() == 7) {
            SystemProperties.set("forfan.mediascaner.enable", MainSet.SP_XPH5);
        }
        boolean provisioned = Settings.Global.getInt(getContentResolver(), "device_provisioned", 0) != 0;
        boolean setupComplete = Settings.Secure.getInt(getContentResolver(), "user_setup_complete", 0) != 0;
        if (!provisioned || !setupComplete) {
            Settings.Global.putInt(getContentResolver(), "device_provisioned", 1);
            Settings.Secure.putInt(getContentResolver(), "user_setup_complete", 1);
        }
        BackcarService.getInstance().InintCamera(getApplicationContext());
        this.audioManager = (AudioManager) getSystemService(Poi.PoiAction.ACTION_AUDIO);
        MainTask.GetInstance().SetTaskCallBack(this);
        ((StorageManager) getSystemService("storage")).registerListener(this.mStorageListener);
        IMountService asInterface = IMountService.Stub.asInterface(ServiceManager.getService("mount"));
        Log.w(TAG, "######## onCreate = " + Evc.mSystemState + " !");
        if (!MainSet.IsXPH5() && !MainSet.GetInstance().IsCustom("CTEJ")) {
            MiraVisionJni.nativeSetPictureMode(1);
        }
        IntentFilter filter = new IntentFilter(BROADCAST_BT_CHECKSTATUS);
        filter.addAction(BROADCAST_BT_A2DP_ACQUIRE);
        filter.addAction(BROADCAST_BT_A2DP_RELEASE);
        filter.addAction(BROADCAST_NET_CHANGE);
        filter.addAction(ACTION_RECOGNIZE_CMD);
        filter.addAction(ACTION_RECOGNIZE_CMD_VOLUME);
        filter.addAction(BROADCAST_LANCHER_FUNC_ACCELERATION);
        filter.addAction(BROADCAST_LANCHER_FUNC_MUTE);
        filter.addAction(BROADCAST_LANCHER_FUNC_VOLUME);
        filter.addAction(BROADCAST_LANCHER_FUNC_BRIGHTNESS);
        filter.addAction(BROADCAST_LANCHER_FUNC_BLUETOOTH);
        filter.addAction(BROADCAST_LANCHER_FUNC_VOLUMEADD);
        filter.addAction(BROADCAST_LANCHER_FUNC_VOLUMEDEC);
        filter.addAction(BROADCAST_LANCHER_FUNC_SCREENOFF);
        filter.addAction(BROADCAST_LANCHER_FUNC_NOWMODE);
        filter.addAction(ACTION_RECOGNIZE_CMD_REGISTER_S);
        filter.addAction(BROADCAST_MEDIA_LISTUPDATE);
        filter.addAction(BROADCAST_AUTOKING_START_UPDATE_SYSTEM);
        filter.addAction(BROADCAST_AUTOKING_UPDATE_SYSTEM_SUCCESS);
        filter.addAction("ActivityWarm_Closed");
        filter.addAction(TXZ_GET_WWATHER_DATA);
        filter.addAction(BROADCAST_GLSX_VOICE);
        filter.addCategory("android.intent.category.DEFAULT");
        this.mEasyConnextReceiver = new easyConnectRecevie();
        registerReceiver(this.mEasyConnextReceiver, filter);
        Intent intentService = new Intent();
        intentService.setAction("com.ts.MainUI.NaviBarService");
        startService(intentService);
        SoundPool soundPool = new SoundPool(3, 3, 0);
        new HashMap().put(1, Integer.valueOf(soundPool.load(this, R.raw.ring, 1)));
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(1, 1.0f, 1.0f, 100, 0, 1.0f);
            }
        });
        int supportEcarId = getResources().getIdentifier("support_ecar", "string", getPackageName());
        if (supportEcarId != 0 && getResources().getString(supportEcarId).equals("true")) {
            this.bSupportECar = true;
        }
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Deprecated
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    /* access modifiers changed from: package-private */
    public int CheckMode(int workmode) {
        if (workmode == 1 || workmode == 8 || workmode == 9) {
            return 1;
        }
        if (workmode == 4 || workmode == 3 || workmode == 2) {
            return 2;
        }
        if (workmode != 5) {
            return 0;
        }
        return 3;
    }

    public void AudioFocusTRANSIENT(int workmode, boolean bGet) {
        if (bSupportFoucus) {
            if (bGet) {
                if (workmode == 0 && CheckMode(this.nTempWork) == 1) {
                    Evc.GetInstance().evol_workmode_set(this.nTempWork);
                } else if (CheckMode(this.nTempWork) == 2 && workmode == this.nTempWork) {
                    if (this.mTsPlayerService != null) {
                        try {
                            if (this.nPaunseFlag == 1) {
                                this.mTsPlayerService.play();
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (CheckMode(this.nTempWork) == 3 && workmode == this.nTempWork && this.nPaunseFlag == 1) {
                    BtExe.getBtInstance().musicPlay();
                }
                this.nTempWork = -1;
                this.nPaunseFlag = 0;
                return;
            }
            this.nTempWork = workmode;
            if (CheckMode(this.nTempWork) == 1) {
                Evc.GetInstance().evol_workmode_set(0);
            } else if (CheckMode(this.nTempWork) == 2) {
                if (this.mTsPlayerService != null) {
                    try {
                        if (this.mTsPlayerService.getPlayStatus() == 1) {
                            this.mTsPlayerService.pause();
                            this.nPaunseFlag = 1;
                        }
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
            } else if (CheckMode(this.nTempWork) == 3 && BtExe.getBtInstance().getMusicState() == 1) {
                BtExe.getBtInstance().musicPause();
                this.nPaunseFlag = 1;
            }
        }
    }

    public void DealWorkMode(int oldMode, int newWorkmode) {
        if (MainSet.GetInstance().bIsLowMemory() && newWorkmode == 1) {
            tool.GetInstance().KillOther("com.ts.dvdplayer");
        }
        if (newWorkmode != 0) {
            AmapAuto.GetInstance().WriteMem("test");
        }
        if (this.nOldWorkMode != Iop.GetWorkMode()) {
            Log.i(TAG, "Dvd  workmode nOldWorkMode==" + this.nOldWorkMode);
            Log.i(TAG, "Dvd  workmode mEvc.Evol.workmode==" + Iop.GetWorkMode());
            SetDAPinIO();
            switch (this.nOldWorkMode) {
                case 2:
                case 3:
                case 4:
                case 15:
                    if (IsMediaMode() == 0 && this.mTsPlayerService != null) {
                        try {
                            this.mTsPlayerService.enterMedia(0);
                            break;
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                case 5:
                    BtExe.getBtInstance().musicPause();
                    break;
                case 6:
                    Mcu.SetCmmbstate((byte) 0);
                    break;
            }
            if (this.antennaPower == -1) {
                if (getResources().getIdentifier("antenna_power", "string", getPackageName()) == 0) {
                    this.antennaPower = 0;
                } else {
                    this.antennaPower = 1;
                }
            }
            if (this.antennaPower > 0) {
                if (oldMode == 1) {
                    FtSet.SetExAmp(0);
                } else if (newWorkmode == 1) {
                    FtSet.SetExAmp(1);
                }
            }
            this.nOldWorkMode = Iop.GetWorkMode();
        }
    }

    public static void openAirplaneModeOn(Context context, boolean enabling) {
        Settings.Global.putInt(context.getContentResolver(), "airplane_mode_on", enabling ? 1 : 0);
        Intent intent = new Intent("android.intent.action.AIRPLANE_MODE");
        intent.putExtra(IConstantData.KEY_STATE, enabling);
        context.sendBroadcast(intent);
    }
}
