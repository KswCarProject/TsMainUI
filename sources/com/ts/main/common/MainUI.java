package com.ts.main.common;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.IActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.location.Criteria;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.storage.StorageEventListener;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.format.Time;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import com.android.SdkConstants;
import com.autochips.ipodplayer.ipodaidl.ITsIPodAidl;
import com.incall.proxy.binder.service.AppBinder;
import com.incall.proxy.binder.service.IAppInterface;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.ts.HideActivity;
import com.ts.MainUI.AuthServer;
import com.ts.MainUI.CstTv;
import com.ts.MainUI.Evc;
import com.ts.MainUI.EvcCallBack;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.StTpms;
import com.ts.MainUI.TaskCallBack;
import com.ts.MainUI.TsDisplay;
import com.ts.MainUI.TsFile;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.BackCarSound;
import com.ts.backcar.BackcarService;
import com.ts.backcar.TsDvrService;
import com.ts.bt.BtExe;
import com.ts.bt.BtFunc;
import com.ts.bt.BtInCallService;
import com.ts.bt.BtServiceBinder;
import com.ts.bt.ITsBtService;
import com.ts.can.CanIF;
import com.ts.dvdplayer.ITsPlayerService;
import com.ts.factoryset.AtcDisplaySettingsUtils;
import com.ts.factoryset.FsBaseActivity;
import com.ts.main.common.ITsCommon;
import com.ts.main.radio.RadioServiceBinder;
import com.ts.main.skin.utils.SkinUtils;
import com.ts.main.txz.AmapAuto;
import com.ts.main.txz.TxzReg;
import com.ts.t3.T3WeakJoinUtils;
import com.ts.wireless.carplay.WirelessCarplay;
import com.txznet.sdk.TXZPoiSearchManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.KeyDef;
import com.yyw.ts70xhw.Mcu;
import com.yyw.ts70xhw.Radio;
import com.yyw.ts70xhw.StSet;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainUI extends Service implements TaskCallBack, EvcCallBack {
    public static final String ACTION_AVM_RESET_ALL = "com.autochips.avmplayer.ACTION_RESET_ALL";
    private static final String ACTION_AVM_SIGNAL_STATUS = "com.autochips.avmplayer.signal_status";
    public static final String ACTION_MAINUI_ACCOFF = "com.ts.main.uiaccoff";
    public static final String ACTION_MAINUI_ACCON = "com.ts.main.uiaccon";
    public static final String ACTION_MAINUI_REMOTE_WAKEUP = "com.ts.main.uiremote_wake_up";
    public static final String ACTION_MAINUI_WORKMODE = "com.ts.main.workmode";
    public static final String ACTION_PREQB_OFF = "autochips.intent.action.PREQB_POWEROFF";
    public static final String ACTION_QB_OFF = "autochips.intent.action.QB_POWEROFF";
    public static final String ACTION_QB_ON = "autochips.intent.action.QB_POWERON";
    public static final String ACTION_REMOTE_SLEEP = "com.ts.car.client.ACTION_REMOTE_SLEEP";
    public static final String ACTION_REMOTE_WAKEUP = "com.ts.car.client.ACTION_REMOTE_WAKEUP";
    public static final String ACTION_SHOW_USB_DISPLAY = "com.ts.dvdplayer.SHOW_USB_DISPLAY";
    public static int AVMChaneleA = 0;
    public static int AVMChaneleB = 0;
    public static int AVMChaneleC = 0;
    public static int AVMChaneleD = 0;
    public static final String BASIC_TIME_STRING = "2016-10-01 00:00:00";
    public static final String BROADCAST_ADAS_LIGHT_STATE = "forfan_light_state";
    public static final String BROADCAST_ADAS_TURNLIGHT_STATE = "forfan.adas.turnlight_state";
    public static final String BROADCAST_APP_QUIT = "net.easyconn.app.quit";
    public static final String BROADCAST_BT_A2DP_ACQUIRE = "net.easyconn.a2dp.acquire";
    public static final String BROADCAST_BT_A2DP_RELEASE = "net.easyconn.a2dp.release";
    public static final String BROADCAST_BT_CHECKSTATUS = "net.easyconn.bt.checkstatus";
    public static final String BROADCAST_BT_CONNECT = "net.easyconn.bt.connect";
    public static final String BROADCAST_BT_CONNECTED = "net.easyconn.bt.connected";
    public static final String BROADCAST_BT_NOT_CONNECTED = "net.easyconn.bt.notconnected";
    public static final String BROADCAST_BT_OPENED = "net.easyconn.bt.opened";
    public static final String BROADCAST_CLOCK_SCREEN_MUTE = "android.intent.action.CLOCK_SCREEN_MUTE";
    public static final String BROADCAST_CONNECTION_BREAK = "net.easyconn.connection.break";
    public static final String BROADCAST_DRIVEMODE_CHECKSTATUS = "net.easyconn.drivemode.checkstatus";
    public static final String BROADCAST_GLSX_VOICE = "com.glsx.ddbox.action.VOICE";
    public static final String BROADCAST_LANCHER_FUNC_ACCELERATION = "forfan.intent.action.ACCELERATION";
    public static final String BROADCAST_LANCHER_FUNC_BLUETOOTH = "forfan.intent.action.BLUETOOTH";
    public static final String BROADCAST_LANCHER_FUNC_BRIGHTNESS = "forfan.intent.action.BRIGHTNESS";
    public static final String BROADCAST_LANCHER_FUNC_CLOSEMEDIA = "forfan.intent.action.CLOSEMEDIA";
    public static final String BROADCAST_LANCHER_FUNC_EQ = "forfan.intent.action.EQ";
    public static final String BROADCAST_LANCHER_FUNC_MUTE = "forfan.intent.action.MUTE";
    public static final String BROADCAST_LANCHER_FUNC_NOWMODE = "forfan.intent.action.MODE";
    public static final String BROADCAST_LANCHER_FUNC_REBOOT = "forfan.intent.actionREBOOT";
    public static final String BROADCAST_LANCHER_FUNC_SCREENOFF = "forfan.intent.action.SCREENOFF";
    public static final String BROADCAST_LANCHER_FUNC_VOLUME = "forfan.intent.action.VOLUME";
    public static final String BROADCAST_LANCHER_FUNC_VOLUMEADD = "forfan.intent.action.VOLUMEUP";
    public static final String BROADCAST_LANCHER_FUNC_VOLUMEDEC = "forfan.intent.action.VOLUMEDN";
    public static final String BROADCAST_MEDIA_LISTUPDATE = "com.ts.media.listupdate";
    public static final String BROADCAST_NET_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
    public static final String BROADCAST_SET_PRODUCTID = "forfan.intent.action.set_productid";
    public static final String BT_A2DP_ACQUIRE = "com.didi365.showscreen.a2dp.acquire";
    public static final String BT_A2DP_RELEASE = "com.didi365.showscreen.a2dp.release";
    public static final int CAMERA360_DELAY = 60;
    public static final int CAMERA_DELAY = 30;
    public static final int CARPLAY_ENABLE_DELAY = 120;
    static int Checknum = 0;
    public static int DispSrcH = 600;
    public static int DispSrcW = 1024;
    static final String EASYLICENSE_FILE = "/mnt/sdcard/EasyConnected/License.ini";
    static final String EASYLICENSE_PATH = "/mnt/sdcard/EasyConnected";
    private static final String FT_SET_DIR = "/mnt/sdcard/Iconfig";
    static int FirsChangeToBt = 0;
    public static final String GOOGLE_ASSISANT_PNAME = "com.google.android.apps.googleassistant";
    public static final String GOOGLE_SPEECH_PNAME = "com.google.android.googlequicksearchbox";
    public static float GpsSpeed = 0.0f;
    public static final String NET_TIME_ = "time";
    public static final String NET_TIME_CHANGE = "forfan.intent.time_change";
    static final String PROID_FILE = "/mnt/sdcard/Iconfig/Proid.ini";
    public static final int RUN_ACCOFF = 2;
    public static final int RUN_BATOFF = 4;
    public static final int RUN_COMMON = 0;
    public static final int RUN_POWEROFF = 3;
    public static final String SET_PRODUCTID = "set_proid";
    public static final String SHOW_USB_DISPLAY = "show_usb_display";
    public static final int SYNC_ERROR = 0;
    public static final int SYNC_OK = 1;
    private static final String TAG = "MainUI";
    public static final String TS_CARPLAY_PNAME = "com.ts.carplayapp";
    public static final String TXZ_GET_WWATHER_DATA = "txz_get_weather_data";
    public static int UpdateCheckTIme = 30;
    public static final int VOICE_TXZ_DELAY = 60;
    static boolean bBtConnect = false;
    public static boolean bDeNoise = false;
    public static boolean bEnableSleepLog = true;
    public static boolean bEnterMode = false;
    public static boolean bForceScal = false;
    static boolean bHaveCall = false;
    public static boolean bIsInCamera;
    public static boolean bIsInRight;
    public static boolean bIsScreenMode = false;
    public static boolean bRemoteWakeUp = false;
    public static int mBackCarDirect = 0;
    public static ExecutorService mBackgroundExecutor = Executors.newFixedThreadPool(3);
    public static CanInterface mCanInterface = null;
    private static BtFunc.DealBtCallKey mDealBtCallKey = null;
    /* access modifiers changed from: private */
    public static MainUI mMainUI = null;
    public static int mScrH = 600;
    public static int mScrW = 1024;
    public static int mUIDirect = 0;
    private static IUserKeyHandler mUserKeyHandler = null;
    public static int nBklCanOn = 0;
    static long nCheckNum = 0;
    public static int nHasGetTime = 0;
    public static int nMcuRet = 0;
    static long nTime = 0;
    int CarplayEnableDelay = 120;
    public boolean CheckDisk = false;
    /* access modifiers changed from: private */
    public float GpsCog = 0.0f;
    private String LAUNCHER_KEY_APPS = "com.android.launcher.key_apps";
    private final String LAUNCHER_TO_ACTIVITY = "ActivityWarm_Closed";
    String MyoldState = "00";
    public int OldCamStateDelay = 15;
    long TimeTicket = 0;
    int TotalSize = 0;
    int WakeUpDelayTime = 1;
    private int antennaPower = -1;
    public boolean b360Preview = false;
    boolean bBtPauseFlag = false;
    private boolean bCaninit = false;
    boolean bCheckOver = false;
    /* access modifiers changed from: private */
    public boolean bIpodConnected = false;
    boolean bIsRear = false;
    boolean bQuickBootOn = false;
    boolean bSend3dRound = false;
    boolean bSetStateToMedia = false;
    boolean bShutDown = false;
    boolean bStartAvm = false;
    private String customName = null;
    private TimerTask dismissDlgTask = new TimerTask() {
        public void run() {
            if (MainUI.this.mAutoDismissDlg.isShowing()) {
                MainUI.this.mAutoDismissDlg.dismiss();
            }
        }
    };
    private GpsStatus.Listener gpsStatusListener = new GpsStatus.Listener() {
        public void onGpsStatusChanged(int event) {
            GpsStatus gpsStatus = MainUI.this.loctionManager.getGpsStatus((GpsStatus) null);
            switch (event) {
                case 3:
                    int timeToFirstFix = gpsStatus.getTimeToFirstFix();
                    return;
                case 4:
                    int nNUM = 0;
                    for (GpsSatellite satellite : gpsStatus.getSatellites()) {
                        if (satellite.getSnr() > 0.0f) {
                            nNUM++;
                        }
                    }
                    if (nNUM == 0) {
                        MainUI.GpsSpeed = 0.0f;
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            MainUI.GpsSpeed = (float) (((double) location.getSpeed()) * 3.6d);
            MainUI.this.GpsCog = location.getBearing();
            AmapAuto.GetInstance().SetGpsInfo((int) MainUI.this.GpsCog, (int) MainUI.GpsSpeed);
            if (MainUI.this.nUpdateTime == 0) {
                MainUI.this.nUpdateTime = 1;
                SystemClock.setCurrentTimeMillis(location.getTime());
            }
        }

        public void onProviderDisabled(String arg0) {
            if (FtSet.GetUsbHost() == 0) {
                MainUI.this.openGPS();
            }
        }

        public void onProviderEnabled(String arg0) {
        }

        public void onStatusChanged(String arg0, int status, Bundle arg2) {
            switch (status) {
                case 0:
                    Log.i(MainUI.TAG, "OUT_OF_SERVICE");
                    return;
                case 1:
                    Log.i(MainUI.TAG, "TEMPORARILY_UNAVAILABLE");
                    return;
                default:
                    return;
            }
        }
    };
    LocationManager loctionManager;
    private String loundString = null;
    private ActivityManager mActivityManager = null;
    /* access modifiers changed from: private */
    public IAppInterface mAppInterface = new AppBinder();
    /* access modifiers changed from: private */
    public AlertDialog mAutoDismissDlg = null;
    /* access modifiers changed from: private */
    public ITsBtService mBTCommon = new BtServiceBinder();
    Calendar mCalendar = Calendar.getInstance();
    private Timer mDismissCounter = new Timer();
    private TsDisplay mDisplay = TsDisplay.GetInstance();
    easyConnectRecevie mEasyConnextReceiver;
    /* access modifiers changed from: private */
    public Evc mEvc = Evc.GetInstance();
    private ServiceConnection mIpodConn = new ServiceConnection() {
        public void onServiceDisconnected(ComponentName binder) {
            MainUI.this.mIpodService = null;
            MainUI.this.bIpodConnected = false;
        }

        public void onServiceConnected(ComponentName arg0, IBinder binder) {
            MainUI.this.mIpodService = ITsIPodAidl.Stub.asInterface(binder);
        }
    };
    /* access modifiers changed from: private */
    public ITsIPodAidl mIpodService;
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
    ContentResolver mResolver = null;
    private DvpServiceConnection mServiceConnection = new DvpServiceConnection();
    StTpms mStTpms = StTpms.GetInstance();
    StorageEventListener mStorageListener = new StorageEventListener() {
        public void onStorageStateChanged(String path, String oldState, String newState) {
            Log.i(MainUI.TAG, "onStorageStateChanged" + path + ":" + oldState + "--->" + newState);
            Log.i(MainUI.TAG, "MyoldState" + MainUI.this.MyoldState);
            if (!path.contains("emulated/0") && newState.equals("mounted")) {
                Log.i(MainUI.TAG, String.valueOf(path) + ":" + oldState + "--->" + newState);
                MainUI.this.DiscStop(path);
                if (!MainUI.this.mMainSet.IsTestMode()) {
                    MainSet.GetInstance().CheckTestMode();
                    if (MainUI.this.mMainSet.IsTestMode()) {
                        Process.killProcess(Process.myPid());
                    }
                }
                if (FtSet.IsIconExist(2) == 1) {
                    tool.GetInstance().checkDiscUpdate(path);
                }
                if (Iop.DspSupport() == 1) {
                    tool.GetInstance().checkDspUpdate(path);
                }
                tool.GetInstance().checkLogoFileUpdate(path);
                MainUI.UpdateCheckTIme = 30;
                tool.GetInstance().CheckMapData();
                if (MainUI.this.getResources().getString(R.string.support_autocompress_updatefile).equals("1")) {
                    tool.GetInstance().SearchUpdateZipFile();
                }
                MainUI.this.mKeyTouch.SetTouConfig();
                int nTouchX = MainUI.GetTouchX();
                int nTouchY = MainUI.GetTouchY();
                if (FtSet.LoadTouchXY(MainUI.mScrW, MainUI.mScrH, nTouchX, nTouchY) == 1) {
                    Toast.makeText(MainUI.this.getApplicationContext(), "原点导入成功", 0).show();
                } else if (FtSet.LoadTouchXY(MainUI.mScrW, MainUI.mScrH, nTouchX, nTouchY) == 2) {
                    Toast.makeText(MainUI.this.getApplicationContext(), "原点相同,未导入", 0).show();
                }
            }
        }
    };
    private ITsCom mTsCom = new ITsCom();
    public ITsPlayerService mTsPlayerService = null;
    ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
    public int nAutoToNavi = 0;
    int nBatFirst = 0;
    public int nCmmbPoweroff = 0;
    int nDelayToGetVerSion = 50;
    public int nDelayToStartTxz = 0;
    int nDirTem = 0;
    int nDvdMode = 0;
    int nError = 0;
    int nErrorNum = 0;
    private int nFsok = 0;
    int nGDirect = 255;
    int nHomeKey_enable = 1;
    private int nILL = 255;
    int nKillNum = 0;
    int nMode = 0;
    int nMute = 1;
    public int nNawKey = 0;
    public int nNetIsConnect = 0;
    int nOldMode = 255;
    private int nOldPowerMode = 0;
    int nOldWorkMode = 255;
    int nPaunseFlag = 0;
    /* access modifiers changed from: private */
    public int nPowerMode = 0;
    int nPowerOldWorkMode = 255;
    private int nPowerState = 0;
    int nQuickNum = 0;
    int nRotatetionEorror = 0;
    int nStep = 0;
    int nTEST = 0;
    int nTempWork = -1;
    int nTickNum = 0;
    public int nTpmsOnce = 0;
    public int nTurnonLight = 0;
    /* access modifiers changed from: private */
    public int nUpdateTime = 0;
    public int nWakeTime = 0;
    int ntemo = 0;
    private List<GpsSatellite> satelliteList;
    private String strOFF = null;
    private String strON = null;
    String timeFormat;
    long timeTick = 0;

    public interface IUserKeyHandler {
        boolean onKey(int i);
    }

    private void GetMemory() {
        CarplayControl.task();
        this.nTickNum++;
        if (this.nTickNum % 300 == 0) {
            if (T3WeakJoinUtils.bIsT3WeakPrj) {
                tool.GetInstance().ChangePathToRoot("/data/logger");
                tool.GetInstance().ChangePathToRoot("/data/anr");
            }
            Log.i(TAG, "*******Main task is run " + GetToalMemory() + "MB" + "---nTickNum=" + this.nTickNum + "Language==" + Locale.getDefault().getCountry());
        }
        if (MainSet.GetInstance().IsDiDi125()) {
            MainSet.GetInstance().CheckSysBrightness();
        }
    }

    public void SetCheckDisc(boolean bEn) {
        this.CheckDisk = bEn;
    }

    /* access modifiers changed from: package-private */
    public void CheckDiscState() {
        new Thread() {
            public void run() {
                Boolean newStateBoolean;
                try {
                    sleep(12000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                Boolean bDiscSate = Boolean.valueOf(Evc.GetInstance().IsHaveDisk());
                MainUI.this.bCheckOver = false;
                while (!MainUI.this.bCheckOver) {
                    if (MainUI.IsCameraMode() == 0 && bDiscSate != (newStateBoolean = Boolean.valueOf(Evc.GetInstance().IsHaveDisk()))) {
                        bDiscSate = newStateBoolean;
                        if (bDiscSate.booleanValue() && MainUI.this.CheckDisk) {
                            WinShow.GotoWin(3, 0);
                            MainUI.this.CheckDisk = false;
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

    public static void SetCanCallBack(CanInterface cb) {
        mCanInterface = cb;
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
    public void ForceScreenDelayTime(int nTime2) {
        this.nTurnonLight = nTime2;
    }

    /* access modifiers changed from: package-private */
    public void EnterCamera(int nMode2) {
        Log.i(TAG, "EnterCamera==" + nMode2);
        AmapAuto.SetBackStateToWeNavi(nMode2);
        WirelessCarplay.setAutoPlayBackStatus(nMode2 == 1);
        if (nMode2 == 1) {
            if (CanIF.RvsEntExdMode()) {
                if (mCanInterface != null) {
                    mCanInterface.EnterCamera(1);
                }
            } else if (BackcarService.getInstance().bIsAvm360()) {
                BackcarService.getInstance().StartAvmBack(1);
            } else {
                WinShow.show("com.ts.MainUI", "com.ts.backcar.BackcarMainActivity");
                if (GetInstance().GetMcuState() != 3) {
                    TsDisplay.GetInstance().SetSrcMute(25);
                    if (Mcu.BklisOn() == 0) {
                        Mcu.BklTurn();
                    }
                }
            }
            if (GetMcuState() == 3) {
                ForceScreenDelayTime(12);
            }
        } else if (CanIF.RvsEntExdMode()) {
            if (mCanInterface != null) {
                mCanInterface.EnterCamera(0);
            }
        } else if (BackcarService.getInstance().bIsAvm360()) {
            BackcarService.getInstance().StopAvm();
        }
    }

    /* access modifiers changed from: package-private */
    public void EnterRightCamera(int nMode2) {
        if (nMode2 == 1) {
            WinShow.show("com.ts.MainUI", "com.ts.backcar.RightCameraActivity");
        }
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
                    if (Iop.GetWorkMode() == 2) {
                        CanIF.mMediaInfo.MediaType = 2;
                        CanIF.mMediaInfo.CurNum = this.mTsPlayerService.getPlayId() - 1;
                        CanIF.mMediaInfo.TotalNum = this.mTsPlayerService.getMediaCnt();
                        break;
                    } else {
                        return;
                    }
            }
            if (this.mTsPlayerService.getPlayStatus() == 2) {
                CanIF.mMediaInfo.fgPause = 1;
            }
            int timeInfo = (int) this.mTsPlayerService.getCurrentTime();
            CanIF.mMediaInfo.CurHour = (timeInfo / 1000) / 3600;
            CanIF.mMediaInfo.CurMin = ((timeInfo / 1000) / 60) % 60;
            CanIF.mMediaInfo.CurSec = (timeInfo / 1000) % 60;
            CanIF.mMediaInfo.CurTime = timeInfo / 1000;
            int timeInfo2 = (int) this.mTsPlayerService.getTotoalTime();
            CanIF.mMediaInfo.TotalHour = (timeInfo2 / 1000) / 3600;
            CanIF.mMediaInfo.TotalMin = ((timeInfo2 / 1000) / 60) % 60;
            CanIF.mMediaInfo.TotalSec = (timeInfo2 / 1000) % 60;
            CanIF.mMediaInfo.TotalTime = timeInfo2 / 1000;
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
            CanIF.mID3.sAlbum = TXZResourceManager.STYLE_DEFAULT;
            CanIF.mID3.sArtist = TXZResourceManager.STYLE_DEFAULT;
            CanIF.mID3.sName = TXZResourceManager.STYLE_DEFAULT;
        }
    }

    public int UIGetMediaStatus() {
        if (this.mTsPlayerService != null) {
            try {
                return this.mTsPlayerService.getPlayStatus();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
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

    public void BackToLauncher() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(268435456);
        startActivity(intent);
        Log.d(TAG, "BackToLauncher ");
    }

    /* access modifiers changed from: package-private */
    public void KeyArmBeep() {
        Iop.NaviSpeaking(2);
    }

    /* access modifiers changed from: package-private */
    public void SendClock() {
        if (SystemClock.uptimeMillis() - this.TimeTicket > 1000) {
            this.TimeTicket = SystemClock.uptimeMillis();
            ContentResolver mResolver2 = getContentResolver();
            if (mResolver2 != null) {
                Time t = new Time();
                t.setToNow();
                this.timeFormat = Settings.System.getString(mResolver2, "time_12_24");
                int Format = 1;
                if (this.timeFormat != null && this.timeFormat.equals("24")) {
                    Format = 0;
                }
                Mcu.SendClock(t.hour, t.minute, Format);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void DealNoise() {
        if (bDeNoise) {
            String nVal = SystemProperties.get("forfan.track.active");
            if (nVal == null) {
                return;
            }
            if (nVal.equals("0")) {
                Mcu.SendXKey(Can.CAN_VOLKS_XP);
            } else if (nVal.equals("1")) {
                Mcu.SendXKey(Can.CAN_SITECHDEV_CW);
            }
        } else {
            Mcu.SendXKey(Can.CAN_SITECHDEV_CW);
        }
    }

    /* access modifiers changed from: package-private */
    public void DealMainUITaskNoCamera() {
        SetAdasLightState();
        if (this.nCmmbPoweroff > 0) {
            this.nCmmbPoweroff--;
            if (this.nCmmbPoweroff == 0) {
                Mcu.SetCmmbstate((byte) 0);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void CheckUpdateFile() {
        if (UpdateCheckTIme > 0) {
            UpdateCheckTIme--;
            if (UpdateCheckTIme == 0) {
                MainSet.GetInstance().LoadFtSetFile();
                MainSet.GetInstance().DeleteTheAutoDateFile();
                if (!MainSet.GetInstance().bCheckUpdateFile(false)) {
                    MainSet.GetInstance().CopyDemoFile();
                    MainSet.GetInstance().CheckMcuUpdateFile();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void DealMainUITask() {
        String nVal;
        if (BtExe.getBtInstance().isConnected() && (nVal = SystemProperties.get("forfan.tsbt.mute")) != null && (nVal.equals("1") || nVal.equals("2"))) {
            if (Iop.DspSupport() == 0 || nVal.equals("1")) {
                Log.d(TAG, "call is comming " + nVal);
                Iop.PopMuteDelay(50);
            }
            SystemProperties.set("forfan.tsbt.mute", "0");
        }
        DealNoise();
        if (this.nTurnonLight > 0) {
            this.nTurnonLight--;
            if (this.nTurnonLight == 0) {
                Mcu.BklTurnCan(1);
                nBklCanOn = 1;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void CheckPoweroffState() {
        this.nPowerMode = GetMcuState();
        if (this.nPowerMode != 0) {
            Log.d(TAG, "## nPowerMode = " + this.nPowerMode + " !");
        }
        if (this.nPowerMode != this.nOldPowerMode) {
            tool.WriteLog("GetMcuState()=" + GetMcuState());
            Log.d(TAG, "## nPowerMode222 = " + this.nPowerMode + " !");
            MainSet.SendMcuState(this.nPowerMode);
            this.bSetStateToMedia = true;
            if (this.nPowerMode == 3) {
                BtExe.getBtInstance().disconnect();
                if (!bRemoteWakeUp) {
                    MainSet.SendIntent(ACTION_MAINUI_ACCOFF, (String) null);
                }
                AmapAuto.GetInstance();
                AmapAuto.SendAccStateToSuding(0);
                ShowRearView(false);
                Log.d(TAG, "mEvc.Evol.workmode = " + Iop.GetWorkMode() + " !");
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
                } else if (Iop.GetWorkMode() == 12) {
                    this.nPowerOldWorkMode = Iop.GetWorkMode();
                    BackToLauncher();
                } else {
                    if (nBklCanOn == 1) {
                        Mcu.BklTurnCan(0);
                        nBklCanOn = 0;
                    }
                    if (MainSet.nShowScreen == 1) {
                        ForceScreenDelayTime(40);
                        MainSet.GetInstance().openApplication(this, "com.ts.tsclock");
                        MainVolume.GetInstance().Destroy();
                    } else {
                        MainAlterwin.GetInstance().ShowPowerOffWin();
                    }
                }
            } else if (this.nOldPowerMode == 3 && this.nPowerMode == 0) {
                BtExe.getBtInstance().connect();
                ShowRearView(true);
                MainAlterwin.GetInstance().HidenPoweroffWin();
                MainSet.SendIntent(ACTION_MAINUI_ACCON, (String) null);
                bRemoteWakeUp = false;
                AmapAuto.GetInstance();
                AmapAuto.SendAccStateToSuding(1);
                if (nBklCanOn == 1) {
                    Mcu.BklTurnCan(0);
                    nBklCanOn = 0;
                }
                if (MainSet.GetInstance().IsHaveApk("com.ts.tsclock")) {
                    tool.GetInstance().killProcess("com.ts.tsclock");
                }
                if (this.nPowerOldWorkMode != 255) {
                    WinShow.TsEnterMode(this.nPowerOldWorkMode);
                } else if (MainSet.nShowScreen == 1) {
                    WinShow.TsEnterMode(Iop.GetWorkMode());
                } else {
                    this.mEvc.evol_workmode_set(Iop.GetWorkMode());
                }
                this.nPowerOldWorkMode = 255;
            } else if (this.nOldPowerMode == 3 && this.nPowerMode == 2) {
                ShowRearView(false);
                MainAlterwin.GetInstance().HidenPoweroffWin();
                if (this.nPowerOldWorkMode != 255) {
                    this.mEvc.evol_workmode_set(this.nPowerOldWorkMode);
                }
                this.nPowerOldWorkMode = 255;
            }
            this.nOldPowerMode = this.nPowerMode;
        }
    }

    public void removeTask(final boolean Show) {
        this.nKillNum = 0;
        mBackgroundExecutor.submit(new Runnable() {
            public void run() {
                try {
                    IActivityManager am = ActivityManager.getService();
                    List<ActivityManager.RecentTaskInfo> tasks = am.getRecentTasks(100, 3, MainUI.this.getUserId()).getList();
                    Log.d(MainUI.TAG, "tasks.size() === " + tasks.size());
                    for (int i = tasks.size() - 1; i >= 0; i--) {
                        ActivityManager.RecentTaskInfo info = tasks.get(i);
                        String classname = info.baseIntent.getComponent().getClassName();
                        if (classname != null && !classname.equals("com.ts.HideActivity") && !classname.contains(MainUI.TS_CARPLAY_PNAME) && !classname.contains("com.ligo.") && !classname.contains("com.ts")) {
                            am.removeTask(info.persistentId);
                            Log.d(MainUI.TAG, "remove " + i + " task= " + info.baseIntent.getComponent().getClassName());
                            MainUI.this.nKillNum++;
                        }
                    }
                } catch (RemoteException e) {
                    Log.w(MainUI.TAG, "Failed to remove task= ", e);
                }
                if (!Show) {
                    return;
                }
                if (MainUI.this.nKillNum <= 0) {
                    MainSet.GetInstance().toastString = MainUI.this.getResources().getString(R.string.message_acceleration_no);
                } else if (MainUI.this.nKillNum == 1) {
                    MainSet.GetInstance().toastString = String.valueOf(MainUI.this.getResources().getString(R.string.message_acceleration_s)) + MainUI.this.nKillNum + MainUI.this.getResources().getString(R.string.message_acceleration_d);
                } else {
                    MainSet.GetInstance().toastString = String.valueOf(MainUI.this.getResources().getString(R.string.message_acceleration_s)) + MainUI.this.nKillNum + MainUI.this.getResources().getString(R.string.message_acceleration_ds);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public boolean bAccoffOK() {
        if (nMcuRet != 1 || this.nPowerState != 255 || this.nFsok != 1) {
            return false;
        }
        if (MainSet.GetInstance().IsSupprotRemotecontrol()) {
            MainSet.GetInstance().SetFlyMode(false);
        } else {
            MainSet.GetInstance().SetFlyMode(true);
        }
        TxzReg.GetInstance().SetTXZToSleep();
        AdjustMcuTime();
        if (this.mTsPlayerService != null) {
            unbindService(this.mServiceConnection);
            this.mTsPlayerService = null;
        }
        Evc.GetInstance().WriteMem((String) null);
        Iop.tsxhwSleep();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        BackcarService.getInstance().ExitCamera();
        EnterCamera(0);
        BackToLauncher();
        BtExe.getBtInstance().disconnect();
        CarplayControl.SetEnable(false);
        removeTask(false);
        this.mMainVolume.Destroy();
        AmapAuto.SetACCOFF();
        startHideActivity();
        Log.i(TAG, "**************Power off ********************nPowerMode==" + this.nPowerMode);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void CheckBackCamera() {
        if (MainSet.GetInstance().IsTestMode()) {
            return;
        }
        if (!BackcarService.getInstance().bIninOK) {
            Log.i(TAG, "BackcarService.bIninOK==" + BackcarService.getInstance().bIninOK);
        } else if (this.OldCamStateDelay > 0) {
            this.OldCamStateDelay--;
            Log.i(TAG, "OldCamStateDelay==" + this.OldCamStateDelay);
            if (this.bSend3dRound) {
                if (IsCameraMode() == 0) {
                    BackcarService.getInstance().Start3DRound();
                }
                this.bSend3dRound = false;
            }
            if (this.OldCamStateDelay == 0 && BackcarService.getInstance().bIsAvm360() && !this.bStartAvm) {
                if (IsCameraMode() == 1) {
                    bIsInCamera = true;
                    this.OldCamStateDelay = 30;
                    Log.i(TAG, "is camera delayStartAvmService ==" + this.OldCamStateDelay);
                } else if (GetMcuState() != 0) {
                    Log.i(TAG, "GetMcuState ==" + GetMcuState() + " so donot start avm");
                } else if (!this.bStartAvm) {
                    Log.i(TAG, "StartAvmService ==" + this.OldCamStateDelay);
                    if (!bRemoteWakeUp) {
                        MainSet.GetInstance().StartAvmService();
                    }
                    this.bStartAvm = true;
                }
            }
        } else if (IsCameraMode() == 1 && !bIsInCamera) {
            bIsInCamera = true;
            EnterCamera(1);
            Log.i(TAG, "IsCameraMode() && !bIsInCamera ");
        } else if (IsRightCamMode() == 1 && !bIsInRight) {
            bIsInRight = true;
            EnterRightCamera(1);
            Log.i(TAG, "IsRightCamMode() == 1 && !bIsInRight");
        } else if (IsCameraMode() != 0 || IsRightCamMode() != 0) {
        } else {
            if (bIsInCamera || bIsInRight) {
                this.OldCamStateDelay = 30;
                if (CanIF.RvsEntExdMode() || BackcarService.getInstance().bIsAvm360()) {
                    bIsInCamera = false;
                }
                this.nTurnonLight = 0;
                if (nBklCanOn == 1 && MainSet.nShowScreen == 0) {
                    Mcu.BklTurnCan(0);
                    nBklCanOn = 0;
                }
                EnterCamera(0);
                EnterRightCamera(0);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void GotoSleep() {
        ((PowerManager) getSystemService("power")).goToSleep(SystemClock.uptimeMillis());
    }

    /* access modifiers changed from: package-private */
    public void StartTxz() {
        if (this.nDelayToStartTxz > 0) {
            this.nDelayToStartTxz--;
            if (this.nDelayToStartTxz == 0 && MainSet.isZh() && MainSet.GetInstance().IsHaveApk("com.txznet.txz")) {
                if (GetMcuState() == 0) {
                    TxzReg.GetInstance().Inint0(this);
                } else {
                    this.nDelayToStartTxz = 60;
                }
            }
        }
    }

    public void DealTask() {
        if (Evc.mSystemState < 10) {
            this.nPowerState = 0;
            if (this.bQuickBootOn) {
                WakeUpInint();
            } else {
                WmInint();
            }
            Log.d(TAG, "DealTask poweroff state  = " + Evc.mSystemState);
        } else if (Evc.mSystemState >= 20) {
            if (Evc.mSystemState < 40) {
                if (!this.bShutDown) {
                    Log.d(TAG, "Sleep");
                    this.bShutDown = true;
                    tool.WriteLog("send bye bye ");
                    Mcu.SendByeBye();
                    bRemoteWakeUp = false;
                    this.timeTick = SystemClock.uptimeMillis();
                }
                Mcu.mcutask();
            } else if (Evc.mSystemState > 200) {
                if (bRemoteWakeUp) {
                    Mcu.mcutask();
                }
                if (Evc.mSystemState % Can.CAN_NISSAN_XFY == 0) {
                    tool.dumpWakelock();
                    tool.WriteLog("sleep error  kill app timeTick =" + (SystemClock.uptimeMillis() - this.timeTick));
                } else if (Evc.mSystemState % 30 == 0) {
                    tool.GetInstance().KillAppBeforeSleep();
                    Log.d(TAG, "kill app ");
                }
            }
            StringBuilder sb = new StringBuilder("## Acc Off = ");
            int i = Evc.mSystemState;
            Evc.mSystemState = i + 1;
            Log.d(TAG, sb.append(i).append(" !").append("GetMcuState()==").append(GetMcuState()).append("time = ").append(SystemClock.uptimeMillis() - this.timeTick).toString());
        } else if (bAccoffOK()) {
            Evc.mSystemState = 20;
            MainSet.SendIntent("com.cusc.action.ACC_OFF", (String) null);
            AmapAuto.QuiteNavi();
            Log.w(TAG, "######## Acc Off !");
            this.bShutDown = false;
        } else {
            if (this.nPowerMode == 2) {
                this.nPowerState = 255;
            } else {
                this.nPowerState = 0;
            }
            DealMainUITask();
            MainAlterwin.GetInstance().task();
            Mcu.mcutask();
            BackcarService.getInstance().Task();
            SendClock();
            GetMemory();
            CheckPoweroffState();
            KeyTouch.GetInstance().DealTask();
            if (nMcuRet == 1 && this.nFsok == 1) {
                if (GetMcuState() != 2) {
                    CheckBackCamera();
                }
                if (IsCameraMode() == 0) {
                    StartTxz();
                    DealMainUITaskNoCamera();
                    CheckUpdateFile();
                    DynamicCheckRotation();
                }
                MainSet.GetInstance().DealTask(this.nPowerMode);
                this.mDisplay.DealTask(this.nPowerMode);
                try {
                    CheckMediaDevice();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                AmapAuto.GetInstance().Task();
                WinShow.DealTask();
                TxzReg.GetInstance().Task(this.nPowerMode);
                this.mMainVolume.CheckVol();
                this.mMainCScreen.DealTask(0);
                ScreenSet.GetInstance().Task(this.nPowerMode);
                BtExe.getBtInstance().timerCall(this.nPowerMode);
                if (FtSet.IsIconExist(123) == 1 && !MainSet.GetInstance().IsTestMode()) {
                    this.mStTpms.MainFunc(this.nPowerMode);
                    if (this.mStTpms.tpmsSave.nOnOffFlag == 2 && this.nTpmsOnce > 0) {
                        this.nTpmsOnce--;
                    }
                    if (this.nTpmsOnce == 0 && this.mStTpms.tpmsSave.nOnOffFlag > 0 && this.mStTpms.CheckError() > 0) {
                        this.nTpmsOnce = Can.CAN_NISSAN_XFY;
                        BackCarSound.GetInstance().TpmsWaring();
                        WinShow.show("com.ts.MainUI", "com.ts.main.tpms.TPMSMainActivity");
                    }
                }
                if (this.mEvc.task(this.nPowerMode) != 255) {
                    if (this.nPowerMode == 2) {
                        Log.i(TAG, "mEvc  PowerOff Err ");
                    }
                    this.nPowerState = 0;
                }
                if (this.mTsPlayerService != null && this.bSetStateToMedia) {
                    try {
                        this.mTsPlayerService.mediaTask(this.nPowerMode);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                    this.bSetStateToMedia = false;
                }
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
                if (mCanInterface.CanTask(this.nPowerMode) != 255) {
                    if (this.nPowerMode == 2) {
                        Log.i(TAG, "CanFunc  PowerOff Err ");
                    }
                    this.nPowerState = 0;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void DealPlayUnderCall(boolean bCall) {
        if (bCall) {
            if (Iop.GetWorkMode() == 2 || Iop.GetWorkMode() == 4 || Iop.GetWorkMode() == 3 || Iop.GetWorkMode() == 15) {
                try {
                    if (this.mTsPlayerService != null) {
                        this.mTsPlayerService.nDealMediaKey(91);
                        this.bBtPauseFlag = true;
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        } else if (this.bBtPauseFlag) {
            if (this.mTsPlayerService != null) {
                try {
                    this.mTsPlayerService.nDealMediaKey(90);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
            this.bBtPauseFlag = false;
        }
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
                MainSet.SendIntent(BROADCAST_BT_CONNECTED, (String) null);
            }
        }
        if (bHaveCall != BtExe.getBtInstance().isHaveCall()) {
            bHaveCall = BtExe.getBtInstance().isHaveCall();
            DealPlayUnderCall(bHaveCall);
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

    private String getTopPackageName() {
        return ((ActivityManager) getSystemService(SdkConstants.TAG_ACTIVITY)).getRunningTasks(1).get(0).baseActivity.getPackageName();
    }

    public void WakeUpGoogleAssiant() {
        MainSet.GetInstance().openApplication(getApplicationContext(), GOOGLE_ASSISANT_PNAME);
    }

    public void WakeUpGoogle() {
        try {
            Intent intent2 = new Intent();
            intent2.setAction("android.intent.action.VOICE_ASSIST");
            intent2.setPackage(GOOGLE_SPEECH_PNAME);
            intent2.addFlags(337641472);
            startActivity(intent2);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void showAutoDismissDlg(String message, int dismissTime) {
        if (this.mAutoDismissDlg == null) {
            this.mAutoDismissDlg = new AlertDialog.Builder(getApplicationContext()).setTitle(TXZResourceManager.STYLE_DEFAULT).setMessage(message).create();
            WindowManager.LayoutParams params = this.mAutoDismissDlg.getWindow().getAttributes();
            params.type = 2003;
            this.mAutoDismissDlg.getWindow().setAttributes(params);
        }
        this.mAutoDismissDlg.setMessage(message);
        if (!this.mAutoDismissDlg.isShowing()) {
            this.mAutoDismissDlg.show();
            WindowManager.LayoutParams params2 = this.mAutoDismissDlg.getWindow().getAttributes();
            params2.width = 300;
            params2.height = -2;
            this.mAutoDismissDlg.getWindow().setAttributes(params2);
        }
        this.dismissDlgTask.cancel();
        this.dismissDlgTask = new TimerTask() {
            public void run() {
                if (MainUI.this.mAutoDismissDlg.isShowing()) {
                    MainUI.this.mAutoDismissDlg.dismiss();
                }
            }
        };
        this.mDismissCounter.schedule(this.dismissDlgTask, (long) dismissTime);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x01d8, code lost:
        if (r15.customName.equals("FYDZ") != false) goto L_0x01da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x01ec, code lost:
        if (r15.customName.equals("FYDZ") != false) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0200, code lost:
        if (r15.customName.equals("FYDZ") != false) goto L_0x0202;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void DealKey() {
        /*
            r15 = this;
            int r6 = com.yyw.ts70xhw.Mcu.GetPkey()
            if (r6 != 0) goto L_0x0016
            int r6 = com.yyw.ts70xhw.Mcu.GetEkey()
            if (r6 != 0) goto L_0x0016
            int r6 = com.yyw.ts70xhw.Mcu.GetRkey()
            if (r6 != 0) goto L_0x0016
            int r6 = com.yyw.ts70xhw.Mcu.GetSkey()
        L_0x0016:
            int r11 = r15.GetMcuState()
            r12 = 3
            if (r11 != r12) goto L_0x004a
            r11 = 81
            if (r6 == r11) goto L_0x004a
            r11 = 332(0x14c, float:4.65E-43)
            if (r6 == r11) goto L_0x004a
            r11 = 769(0x301, float:1.078E-42)
            if (r6 == r11) goto L_0x004a
            r11 = 70
            if (r6 == r11) goto L_0x004a
            r11 = 314(0x13a, float:4.4E-43)
            if (r6 == r11) goto L_0x004a
            if (r6 == 0) goto L_0x004a
            java.lang.String r11 = "MainUI"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "Now is Power off so don't deal the key  = "
            r12.<init>(r13)
            java.lang.StringBuilder r12 = r12.append(r6)
            java.lang.String r12 = r12.toString()
            android.util.Log.d(r11, r12)
        L_0x0049:
            return
        L_0x004a:
            if (r6 <= 0) goto L_0x005c
            com.ts.main.common.ScreenSaver.clearTimeCount()
            com.ts.main.common.MainUI$IUserKeyHandler r11 = mUserKeyHandler
            if (r11 == 0) goto L_0x005c
            com.ts.main.common.MainUI$IUserKeyHandler r11 = mUserKeyHandler
            boolean r11 = r11.onKey(r6)
            if (r11 == 0) goto L_0x005c
            r6 = 0
        L_0x005c:
            r8 = -1
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r12 = "FYDZ"
            boolean r11 = r11.IsCustom(r12)
            if (r11 == 0) goto L_0x0083
            r11 = -1
            if (r8 != r11) goto L_0x0083
            android.app.Application r11 = r15.getApplication()
            android.content.res.Resources r11 = r11.getResources()
            java.lang.String r12 = "special_keytype_fy"
            java.lang.String r13 = "string"
            java.lang.String r14 = r15.getPackageName()
            int r8 = r11.getIdentifier(r12, r13, r14)
        L_0x0083:
            r11 = 263(0x107, float:3.69E-43)
            if (r6 != r11) goto L_0x00a6
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r12 = "FYDZ"
            boolean r11 = r11.IsCustom(r12)
            if (r11 == 0) goto L_0x00a6
            com.ts.bt.BtExe r11 = com.ts.bt.BtExe.getBtInstance()
            boolean r11 = r11.isHaveCall()
            if (r11 != 0) goto L_0x00a4
            boolean r11 = com.ts.main.common.CarplayControl.IsHaveCall()
            if (r11 == 0) goto L_0x00a6
        L_0x00a4:
            r6 = 30
        L_0x00a6:
            if (r6 <= 0) goto L_0x00c5
            boolean r11 = com.ts.wireless.carplay.WirelessCarplay.AutoPlayDealKey(r6)
            if (r11 == 0) goto L_0x00c5
            java.lang.String r11 = "AirCarplay"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "WirelessCarplay DealKey Code == "
            r12.<init>(r13)
            java.lang.StringBuilder r12 = r12.append(r6)
            java.lang.String r12 = r12.toString()
            android.util.Log.d(r11, r12)
            r6 = 0
        L_0x00c5:
            com.ts.bt.BtFunc$DealBtCallKey r11 = mDealBtCallKey
            if (r11 == 0) goto L_0x0108
            boolean r11 = com.ts.main.common.CarplayControl.IsHaveCall()
            if (r11 != 0) goto L_0x0108
            r11 = 29
            if (r6 == r11) goto L_0x00ff
            r11 = 273(0x111, float:3.83E-43)
            if (r6 == r11) goto L_0x00ff
            r11 = 30
            if (r6 == r11) goto L_0x00ff
            r11 = 274(0x112, float:3.84E-43)
            if (r6 == r11) goto L_0x00ff
            r11 = 335(0x14f, float:4.7E-43)
            if (r6 == r11) goto L_0x00ff
            r11 = 109(0x6d, float:1.53E-43)
            if (r6 == r11) goto L_0x00ff
            r11 = 41
            if (r6 == r11) goto L_0x00ff
            r11 = 285(0x11d, float:4.0E-43)
            if (r6 == r11) goto L_0x00ff
            r11 = 31
            if (r6 < r11) goto L_0x00f7
            r11 = 42
            if (r6 <= r11) goto L_0x00ff
        L_0x00f7:
            r11 = 275(0x113, float:3.85E-43)
            if (r6 < r11) goto L_0x0108
            r11 = 286(0x11e, float:4.01E-43)
            if (r6 > r11) goto L_0x0108
        L_0x00ff:
            com.ts.bt.BtFunc$DealBtCallKey r11 = mDealBtCallKey
            int r7 = r11.dealBtCallKey(r6)
            if (r7 == 0) goto L_0x0108
            r6 = 0
        L_0x0108:
            r11 = 92
            if (r6 == r11) goto L_0x0110
            r11 = 97
            if (r6 != r11) goto L_0x0114
        L_0x0110:
            com.ts.can.CanIF.DealCam360Key(r6)
            r6 = 0
        L_0x0114:
            r11 = 6
            if (r6 == r11) goto L_0x011b
            r11 = 24
            if (r6 != r11) goto L_0x0122
        L_0x011b:
            boolean r11 = com.ts.can.CanIF.Deal360CameraKey()
            if (r11 == 0) goto L_0x0122
            r6 = 0
        L_0x0122:
            int r11 = IsCameraMode()
            r12 = 1
            if (r11 == r12) goto L_0x0135
            int r11 = com.ts.MainUI.Evc.mSystemState
            r12 = 10
            if (r11 < r12) goto L_0x0135
            int r11 = com.ts.MainUI.Evc.mSystemState
            r12 = 20
            if (r11 < r12) goto L_0x0138
        L_0x0135:
            r6 = 0
            goto L_0x0049
        L_0x0138:
            if (r6 <= 0) goto L_0x017c
            java.lang.String r11 = r15.customName
            if (r11 == 0) goto L_0x017c
            java.lang.String r11 = r15.customName
            java.lang.String r12 = "MCXI"
            boolean r11 = r11.equals(r12)
            if (r11 != 0) goto L_0x016a
            java.lang.String r11 = r15.customName
            java.lang.String r12 = "FYDZ"
            boolean r11 = r11.equals(r12)
            if (r11 != 0) goto L_0x016a
            java.lang.String r11 = r15.customName
            java.lang.String r12 = "TSKJ"
            boolean r11 = r11.equals(r12)
            if (r11 != 0) goto L_0x016a
            java.lang.String r11 = r15.customName
            java.lang.String r12 = "LTKJ"
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x017c
        L_0x016a:
            java.lang.String r10 = com.ts.main.common.WinShow.getTopPackName()
            if (r10 == 0) goto L_0x017c
            java.lang.String r11 = "com.ts.dvdplayer"
            boolean r11 = r10.equals(r11)
            if (r11 == 0) goto L_0x017c
            switch(r6) {
                case 21: goto L_0x01f7;
                case 287: goto L_0x01c6;
                case 288: goto L_0x01bd;
                case 289: goto L_0x01da;
                case 290: goto L_0x01ee;
                case 304: goto L_0x0202;
                case 515: goto L_0x01cf;
                case 516: goto L_0x01e3;
                default: goto L_0x017c;
            }
        L_0x017c:
            if (r6 <= 0) goto L_0x0197
            java.lang.String r11 = "MainUI"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "nDealPkey = = "
            r12.<init>(r13)
            java.lang.StringBuilder r12 = r12.append(r6)
            java.lang.String r12 = r12.toString()
            android.util.Log.i(r11, r12)
            switch(r6) {
                case 1: goto L_0x040a;
                case 2: goto L_0x03ec;
                case 3: goto L_0x03f8;
                case 4: goto L_0x0401;
                case 5: goto L_0x0413;
                case 6: goto L_0x0426;
                case 7: goto L_0x0462;
                case 8: goto L_0x046c;
                case 9: goto L_0x0197;
                case 10: goto L_0x049c;
                case 11: goto L_0x05ea;
                case 12: goto L_0x062a;
                case 13: goto L_0x0632;
                case 14: goto L_0x063a;
                case 15: goto L_0x0642;
                case 16: goto L_0x06d8;
                case 17: goto L_0x0746;
                case 18: goto L_0x078f;
                case 19: goto L_0x0893;
                case 20: goto L_0x08b6;
                case 21: goto L_0x037f;
                case 22: goto L_0x08d9;
                case 23: goto L_0x08e2;
                case 24: goto L_0x0445;
                case 25: goto L_0x08ea;
                case 26: goto L_0x08f3;
                case 27: goto L_0x059d;
                case 28: goto L_0x064a;
                case 29: goto L_0x04f6;
                case 30: goto L_0x0541;
                case 41: goto L_0x035a;
                case 42: goto L_0x0335;
                case 43: goto L_0x083c;
                case 51: goto L_0x087e;
                case 52: goto L_0x087e;
                case 53: goto L_0x087e;
                case 68: goto L_0x03aa;
                case 69: goto L_0x03cb;
                case 70: goto L_0x090c;
                case 71: goto L_0x0923;
                case 81: goto L_0x0904;
                case 82: goto L_0x08fc;
                case 83: goto L_0x0923;
                case 95: goto L_0x05a9;
                case 96: goto L_0x05a9;
                case 99: goto L_0x0492;
                case 100: goto L_0x0246;
                case 102: goto L_0x0561;
                case 103: goto L_0x056c;
                case 104: goto L_0x0591;
                case 105: goto L_0x092e;
                case 106: goto L_0x0934;
                case 193: goto L_0x020c;
                case 195: goto L_0x0213;
                case 197: goto L_0x023f;
                case 199: goto L_0x021b;
                case 200: goto L_0x022d;
                case 257: goto L_0x0462;
                case 258: goto L_0x0426;
                case 259: goto L_0x046c;
                case 261: goto L_0x04cb;
                case 262: goto L_0x05ea;
                case 263: goto L_0x087e;
                case 264: goto L_0x0632;
                case 265: goto L_0x063a;
                case 266: goto L_0x0642;
                case 267: goto L_0x06d8;
                case 268: goto L_0x0746;
                case 269: goto L_0x078f;
                case 270: goto L_0x0893;
                case 271: goto L_0x08b6;
                case 272: goto L_0x08d9;
                case 273: goto L_0x04f6;
                case 274: goto L_0x0541;
                case 314: goto L_0x090c;
                case 315: goto L_0x0923;
                case 332: goto L_0x0904;
                case 333: goto L_0x08fc;
                case 334: goto L_0x0923;
                case 513: goto L_0x08b6;
                case 514: goto L_0x0893;
                case 515: goto L_0x02eb;
                case 516: goto L_0x02a1;
                case 769: goto L_0x090c;
                case 774: goto L_0x0893;
                case 776: goto L_0x0893;
                case 777: goto L_0x0893;
                case 779: goto L_0x08b6;
                case 781: goto L_0x08b6;
                case 782: goto L_0x08b6;
                case 794: goto L_0x0541;
                case 799: goto L_0x04db;
                case 805: goto L_0x06e1;
                case 808: goto L_0x070b;
                case 809: goto L_0x04cb;
                case 814: goto L_0x04f6;
                case 819: goto L_0x0541;
                case 829: goto L_0x05ea;
                case 834: goto L_0x046c;
                case 839: goto L_0x08d9;
                case 844: goto L_0x05a9;
                default: goto L_0x0197;
            }
        L_0x0197:
            if (r6 <= 0) goto L_0x0049
            java.lang.String r11 = "MainUI"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "mEvc.Evol.workmode = "
            r12.<init>(r13)
            int r13 = com.yyw.ts70xhw.Iop.GetWorkMode()
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r12 = r12.toString()
            android.util.Log.i(r11, r12)
            int r11 = com.yyw.ts70xhw.Iop.GetWorkMode()
            switch(r11) {
                case 0: goto L_0x0977;
                case 1: goto L_0x0940;
                case 2: goto L_0x09ea;
                case 3: goto L_0x09ef;
                case 4: goto L_0x09ef;
                case 5: goto L_0x096a;
                case 6: goto L_0x0a0f;
                case 7: goto L_0x01ba;
                case 8: goto L_0x01ba;
                case 9: goto L_0x01ba;
                case 10: goto L_0x095e;
                case 11: goto L_0x01ba;
                case 12: goto L_0x0a1f;
                case 13: goto L_0x01ba;
                case 14: goto L_0x01ba;
                case 15: goto L_0x09ef;
                default: goto L_0x01ba;
            }
        L_0x01ba:
            r6 = 0
            goto L_0x0049
        L_0x01bd:
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 21
            r11.sendKeyClick(r12)
            r6 = 0
            goto L_0x017c
        L_0x01c6:
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 22
            r11.sendKeyClick(r12)
            r6 = 0
            goto L_0x017c
        L_0x01cf:
            java.lang.String r11 = r15.customName
            java.lang.String r12 = "FYDZ"
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x017c
        L_0x01da:
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 19
            r11.sendKeyClick(r12)
            r6 = 0
            goto L_0x017c
        L_0x01e3:
            java.lang.String r11 = r15.customName
            java.lang.String r12 = "FYDZ"
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x017c
        L_0x01ee:
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 20
            r11.sendKeyClick(r12)
            r6 = 0
            goto L_0x017c
        L_0x01f7:
            java.lang.String r11 = r15.customName
            java.lang.String r12 = "FYDZ"
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x017c
        L_0x0202:
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 23
            r11.sendKeyClick(r12)
            r6 = 0
            goto L_0x017c
        L_0x020c:
            r11 = 44
            com.yyw.ts70xhw.Mcu.SetCkey(r11)
            r6 = 0
            goto L_0x0197
        L_0x0213:
            r11 = 45
            com.yyw.ts70xhw.Mcu.SetCkey(r11)
            r6 = 0
            goto L_0x0197
        L_0x021b:
            r3 = 0
        L_0x021c:
            r11 = 3
            if (r3 < r11) goto L_0x0222
            r6 = 0
            goto L_0x0197
        L_0x0222:
            com.ts.MainUI.Evc r11 = com.ts.MainUI.Evc.GetInstance()
            r12 = 1
            r11.Evol_vol_tune(r12)
            int r3 = r3 + 1
            goto L_0x021c
        L_0x022d:
            r3 = 0
        L_0x022e:
            r11 = 3
            if (r3 < r11) goto L_0x0234
            r6 = 0
            goto L_0x0197
        L_0x0234:
            com.ts.MainUI.Evc r11 = com.ts.MainUI.Evc.GetInstance()
            r12 = 0
            r11.Evol_vol_tune(r12)
            int r3 = r3 + 1
            goto L_0x022e
        L_0x023f:
            r11 = 29
            com.yyw.ts70xhw.Mcu.SetCkey(r11)
            goto L_0x0197
        L_0x0246:
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r12 = "ZHYC"
            boolean r11 = r11.IsCustom(r12)
            if (r11 == 0) goto L_0x025c
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 187(0xbb, float:2.62E-43)
            r11.sendKeyClick(r12)
            goto L_0x0197
        L_0x025c:
            int r11 = r15.nNawKey
            if (r11 <= 0) goto L_0x0278
            java.lang.String r11 = "MainUI"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "Last key is not deal = = "
            r12.<init>(r13)
            int r13 = r15.nNawKey
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r12 = r12.toString()
            android.util.Log.i(r11, r12)
        L_0x0278:
            java.lang.String r9 = com.ts.main.common.WinShow.getTopActivityName()
            if (r9 == 0) goto L_0x029a
            java.lang.String r11 = "com.android.laucher"
            boolean r11 = r9.startsWith(r11)
            if (r11 == 0) goto L_0x029a
            android.content.Intent r4 = new android.content.Intent
            java.lang.String r11 = r15.LAUNCHER_KEY_APPS
            r4.<init>(r11)
            android.content.Context r11 = r15.getApplicationContext()
            r11.sendBroadcast(r4)
        L_0x0295:
            r15.nNawKey = r6
            r6 = 0
            goto L_0x0197
        L_0x029a:
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 3
            r11.sendKeyClick(r12)
            goto L_0x0295
        L_0x02a1:
            boolean r11 = com.ts.main.common.MainSet.IsBmwX1()
            if (r11 != 0) goto L_0x02b1
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            boolean r11 = r11.IsEkeyTurnEnable()
            if (r11 == 0) goto L_0x0197
        L_0x02b1:
            java.lang.String r11 = r15.getTopPackageName()
            java.lang.String r12 = "com.autonavi.amapauto"
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x02c5
            r11 = 1
            com.ts.main.txz.AmapAuto.SetMapScal(r11)
        L_0x02c2:
            r6 = 0
            goto L_0x0197
        L_0x02c5:
            java.lang.String r11 = r15.getTopPackageName()
            java.lang.String r12 = "com.ts.carplayapp"
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x02d6
            com.ts.main.common.CarplayControl.KeyClockWise()
            goto L_0x02c2
        L_0x02d6:
            java.lang.String r11 = r15.getTopPackageName()
            java.lang.String r12 = "com.mxtech.videoplayer"
            boolean r11 = r11.startsWith(r12)
            if (r11 != 0) goto L_0x02c2
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 20
            r11.sendKeyClick(r12)
            goto L_0x02c2
        L_0x02eb:
            boolean r11 = com.ts.main.common.MainSet.IsBmwX1()
            if (r11 != 0) goto L_0x02fb
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            boolean r11 = r11.IsEkeyTurnEnable()
            if (r11 == 0) goto L_0x0197
        L_0x02fb:
            java.lang.String r11 = r15.getTopPackageName()
            java.lang.String r12 = "com.autonavi.amapauto"
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x030f
            r11 = 0
            com.ts.main.txz.AmapAuto.SetMapScal(r11)
        L_0x030c:
            r6 = 0
            goto L_0x0197
        L_0x030f:
            java.lang.String r11 = r15.getTopPackageName()
            java.lang.String r12 = "com.ts.carplayapp"
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x0320
            com.ts.main.common.CarplayControl.KeyantiClockWise()
            goto L_0x030c
        L_0x0320:
            java.lang.String r11 = r15.getTopPackageName()
            java.lang.String r12 = "com.mxtech.videoplayer"
            boolean r11 = r11.startsWith(r12)
            if (r11 != 0) goto L_0x030c
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 19
            r11.sendKeyClick(r12)
            goto L_0x030c
        L_0x0335:
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            boolean r11 = r11.IsEkeyTurnEnable()
            if (r11 == 0) goto L_0x0197
            java.lang.String r11 = r15.getTopPackageName()
            java.lang.String r12 = "com.ts.carplayapp"
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x0351
            com.ts.main.common.CarplayControl.KeyLeft()
            goto L_0x0197
        L_0x0351:
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 21
            r11.sendKeyClick(r12)
            goto L_0x0197
        L_0x035a:
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            boolean r11 = r11.IsEkeyTurnEnable()
            if (r11 == 0) goto L_0x0197
            java.lang.String r11 = r15.getTopPackageName()
            java.lang.String r12 = "com.ts.carplayapp"
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x0376
            com.ts.main.common.CarplayControl.KeyRight()
            goto L_0x0197
        L_0x0376:
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 22
            r11.sendKeyClick(r12)
            goto L_0x0197
        L_0x037f:
            boolean r11 = com.ts.main.common.MainSet.IsBmwX1()
            if (r11 != 0) goto L_0x038f
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            boolean r11 = r11.IsEkeyTurnEnable()
            if (r11 == 0) goto L_0x0197
        L_0x038f:
            java.lang.String r11 = r15.getTopPackageName()
            java.lang.String r12 = "com.ts.carplayapp"
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x03a2
            com.ts.main.common.CarplayControl.KeyEnter()
        L_0x039f:
            r6 = 0
            goto L_0x0197
        L_0x03a2:
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 23
            r11.sendKeyClick(r12)
            goto L_0x039f
        L_0x03aa:
            boolean r11 = com.ts.main.common.MainSet.IsBmwX1()
            if (r11 == 0) goto L_0x0197
            java.lang.String r11 = r15.getTopPackageName()
            java.lang.String r12 = "com.ts.carplayapp"
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x03c3
            com.ts.main.common.CarplayControl.KeyLeft()
        L_0x03c0:
            r6 = 0
            goto L_0x0197
        L_0x03c3:
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 21
            r11.sendKeyClick(r12)
            goto L_0x03c0
        L_0x03cb:
            boolean r11 = com.ts.main.common.MainSet.IsBmwX1()
            if (r11 == 0) goto L_0x0197
            java.lang.String r11 = r15.getTopPackageName()
            java.lang.String r12 = "com.ts.carplayapp"
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x03e4
            com.ts.main.common.CarplayControl.KeyRight()
        L_0x03e1:
            r6 = 0
            goto L_0x0197
        L_0x03e4:
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 22
            r11.sendKeyClick(r12)
            goto L_0x03e1
        L_0x03ec:
            r11 = 10
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            r15.KeyBeep()
            r6 = 0
            goto L_0x0197
        L_0x03f8:
            r11 = 10
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            r6 = 0
            goto L_0x0197
        L_0x0401:
            r11 = 23
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            r6 = 0
            goto L_0x0197
        L_0x040a:
            r11 = 8
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            r6 = 0
            goto L_0x0197
        L_0x0413:
            android.content.Intent r4 = new android.content.Intent
            java.lang.String r11 = "android.settings.DATE_SETTINGS"
            r4.<init>(r11)
            r11 = 337641472(0x14200000, float:8.077936E-27)
            r4.addFlags(r11)
            r15.startActivity(r4)
            r6 = 0
            goto L_0x0197
        L_0x0426:
            boolean r11 = com.ts.can.CanIF.Deal360CameraKey()
            if (r11 == 0) goto L_0x042f
            r6 = 0
            goto L_0x0197
        L_0x042f:
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            boolean r11 = r11.IsXuhuiDmax()
            if (r11 == 0) goto L_0x0441
            r11 = 16
            com.yyw.ts70xhw.Mcu.SendXKey(r11)
        L_0x043e:
            r6 = 0
            goto L_0x0197
        L_0x0441:
            com.yyw.ts70xhw.Mcu.BklTurn()
            goto L_0x043e
        L_0x0445:
            boolean r11 = com.ts.can.CanIF.Deal360CameraKey()
            if (r11 == 0) goto L_0x044e
            r6 = 0
            goto L_0x0197
        L_0x044e:
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            boolean r11 = r11.IsSupportDim()
            if (r11 == 0) goto L_0x045e
            r15.DealKeyDim()
        L_0x045b:
            r6 = 0
            goto L_0x0197
        L_0x045e:
            com.yyw.ts70xhw.Mcu.BklTurn()
            goto L_0x045b
        L_0x0462:
            r11 = 11
            r12 = 98
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            r6 = 0
            goto L_0x0197
        L_0x046c:
            boolean r11 = bIsScreenMode
            if (r11 == 0) goto L_0x047d
            com.ts.main.common.KeyTouch r11 = com.ts.main.common.KeyTouch.GetInstance()
            java.lang.String r12 = ""
            r11.takeScreenShot(r12)
        L_0x047a:
            r6 = 0
            goto L_0x0197
        L_0x047d:
            int r11 = com.yyw.ts70xhw.Mcu.BklisOn()
            if (r11 != 0) goto L_0x0486
            com.yyw.ts70xhw.Mcu.BklTurn()
        L_0x0486:
            int r11 = r15.nHomeKey_enable
            r12 = 1
            if (r11 != r12) goto L_0x047a
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 3
            r11.sendKeyClick(r12)
            goto L_0x047a
        L_0x0492:
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 82
            r11.sendKeyClick(r12)
            r6 = 0
            goto L_0x0197
        L_0x049c:
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r12 = "MCXI"
            boolean r11 = r11.IsCustom(r12)
            if (r11 == 0) goto L_0x04bd
            com.ts.bt.BtExe r11 = com.ts.bt.BtExe.getBtInstance()
            boolean r11 = r11.isHaveCall()
            if (r11 == 0) goto L_0x04bd
            com.ts.bt.BtExe r11 = com.ts.bt.BtExe.getBtInstance()
            r11.hangup()
        L_0x04ba:
            r6 = 0
            goto L_0x0197
        L_0x04bd:
            com.ts.bt.BtExe r11 = com.ts.bt.BtExe.getBtInstance()
            boolean r11 = r11.isHaveCall()
            if (r11 != 0) goto L_0x04ba
            com.ts.main.common.WinShow.DealModeKey()
            goto L_0x04ba
        L_0x04cb:
            com.ts.bt.BtExe r11 = com.ts.bt.BtExe.getBtInstance()
            boolean r11 = r11.isHaveCall()
            if (r11 != 0) goto L_0x04d8
            com.ts.main.common.WinShow.DealModeKey()
        L_0x04d8:
            r6 = 0
            goto L_0x0197
        L_0x04db:
            int r11 = com.ts.bt.BtExe.mCallSta
            r12 = 3
            if (r11 != r12) goto L_0x04ea
            com.ts.bt.BtExe r11 = com.ts.bt.BtExe.getBtInstance()
            r11.answer()
            r6 = 0
            goto L_0x0197
        L_0x04ea:
            boolean r11 = com.ts.main.common.CarplayControl.IsHaveCall()
            if (r11 == 0) goto L_0x0197
            com.ts.main.common.CarplayControl.ReceiveCall()
            r6 = 0
            goto L_0x0197
        L_0x04f6:
            int r11 = com.ts.bt.BtExe.mCallSta
            r12 = 3
            if (r11 != r12) goto L_0x0505
            com.ts.bt.BtExe r11 = com.ts.bt.BtExe.getBtInstance()
            r11.answer()
        L_0x0502:
            r6 = 0
            goto L_0x0197
        L_0x0505:
            com.ts.bt.BtExe r11 = com.ts.bt.BtExe.getBtInstance()
            boolean r11 = r11.isHaveCall()
            if (r11 == 0) goto L_0x0517
            com.ts.bt.BtExe r11 = com.ts.bt.BtExe.getBtInstance()
            r11.hangup()
            goto L_0x0502
        L_0x0517:
            boolean r11 = com.ts.main.common.CarplayControl.IsHaveCall()
            if (r11 == 0) goto L_0x0521
            com.ts.main.common.CarplayControl.ReceiveCall()
            goto L_0x0502
        L_0x0521:
            com.ts.bt.BtExe r11 = com.ts.bt.BtExe.getBtInstance()
            boolean r11 = r11.isConnected()
            if (r11 != 0) goto L_0x0531
            r11 = 7
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            goto L_0x0502
        L_0x0531:
            com.ts.bt.BtExe r11 = com.ts.bt.BtExe.getBtInstance()
            boolean r11 = r11.isHaveCall()
            if (r11 != 0) goto L_0x0502
            r11 = 7
            r12 = 3
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            goto L_0x0502
        L_0x0541:
            com.ts.bt.BtExe r11 = com.ts.bt.BtExe.getBtInstance()
            boolean r11 = r11.isHaveCall()
            if (r11 == 0) goto L_0x0555
            com.ts.bt.BtExe r11 = com.ts.bt.BtExe.getBtInstance()
            r11.hangup()
            r6 = 0
            goto L_0x0197
        L_0x0555:
            boolean r11 = com.ts.main.common.CarplayControl.IsHaveCall()
            if (r11 == 0) goto L_0x0197
            com.ts.main.common.CarplayControl.HangUp()
            r6 = 0
            goto L_0x0197
        L_0x0561:
            java.lang.String r11 = "com.ts.MainUI"
            java.lang.String r12 = "com.ts.main.tpms.TPMSMainActivity"
            com.ts.main.common.WinShow.show(r11, r12)
            goto L_0x0197
        L_0x056c:
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r12 = "com.ts.carplayapp"
            boolean r11 = r11.IsHaveApk(r12)
            if (r11 == 0) goto L_0x0585
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r12 = "com.ts.carplayapp"
            r11.openApplication(r15, r12)
            goto L_0x0197
        L_0x0585:
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r12 = "cn.manstep.phonemirrorBox"
            r11.openApplication(r15, r12)
            goto L_0x0197
        L_0x0591:
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r12 = "com.android.dialer"
            r11.openApplication(r15, r12)
            goto L_0x0197
        L_0x059d:
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r12 = "com.synmoon.usbcamera"
            r11.openApplication(r15, r12)
            goto L_0x0197
        L_0x05a9:
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r12 = "com.google.android.apps.googleassistant"
            boolean r11 = r11.IsHaveApk(r12)
            if (r11 == 0) goto L_0x05bc
            r15.WakeUpGoogleAssiant()
        L_0x05b9:
            r6 = 0
            goto L_0x0197
        L_0x05bc:
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r12 = "com.google.android.googlequicksearchbox"
            boolean r11 = r11.IsHaveApk(r12)
            if (r11 == 0) goto L_0x05cd
            r15.WakeUpGoogle()
            goto L_0x05b9
        L_0x05cd:
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r12 = "com.txznet.txz"
            boolean r11 = r11.IsHaveApk(r12)
            if (r11 == 0) goto L_0x05e3
            com.ts.main.txz.TxzReg r11 = com.ts.main.txz.TxzReg.GetInstance()
            r12 = 0
            r11.TxzStartMic(r12)
            goto L_0x05b9
        L_0x05e3:
            com.ts.MainUI.Evc r11 = r15.mEvc
            r12 = 2
            r11.evol_mut_set(r12)
            goto L_0x05b9
        L_0x05ea:
            boolean r11 = bIsScreenMode
            if (r11 == 0) goto L_0x05fb
            com.ts.main.common.KeyTouch r11 = com.ts.main.common.KeyTouch.GetInstance()
            java.lang.String r12 = ""
            r11.takeScreenShot(r12)
        L_0x05f8:
            r6 = 0
            goto L_0x0197
        L_0x05fb:
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r0 = r11.GetNaviPath()
            java.lang.String r11 = r15.getTopPackageName()
            boolean r11 = r11.equals(r0)
            if (r11 == 0) goto L_0x0624
            int r11 = com.yyw.ts70xhw.Iop.GetWorkMode()
            if (r11 != 0) goto L_0x061c
            com.ts.main.common.KeyTouch r11 = com.ts.main.common.KeyTouch.GetInstance()
            r12 = 3
            r11.sendKeyClick(r12)
            goto L_0x05f8
        L_0x061c:
            int r11 = com.yyw.ts70xhw.Iop.GetWorkMode()
            com.ts.main.common.WinShow.TsEnterMode(r11)
            goto L_0x05f8
        L_0x0624:
            r11 = 1
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            goto L_0x05f8
        L_0x062a:
            r11 = 2
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            r6 = 0
            goto L_0x0197
        L_0x0632:
            r11 = 3
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            r6 = 0
            goto L_0x0197
        L_0x063a:
            r11 = 4
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            r6 = 0
            goto L_0x0197
        L_0x0642:
            r11 = 6
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            r6 = 0
            goto L_0x0197
        L_0x064a:
            int r11 = com.yyw.ts70xhw.Iop.GetWorkMode()
            switch(r11) {
                case 2: goto L_0x069c;
                case 3: goto L_0x067e;
                case 4: goto L_0x0660;
                default: goto L_0x0651;
            }
        L_0x0651:
            r11 = 4
            int r11 = com.yyw.ts70xhw.FtSet.IsIconExist(r11)
            r12 = 1
            if (r11 != r12) goto L_0x06ba
            r11 = 6
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            goto L_0x0197
        L_0x0660:
            r11 = 3
            int r11 = com.yyw.ts70xhw.FtSet.IsIconExist(r11)
            r12 = 1
            if (r11 != r12) goto L_0x066f
            r11 = 4
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            goto L_0x0197
        L_0x066f:
            r11 = 2
            int r11 = com.yyw.ts70xhw.FtSet.IsIconExist(r11)
            r12 = 1
            if (r11 != r12) goto L_0x0197
            r11 = 3
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            goto L_0x0197
        L_0x067e:
            r11 = 2
            int r11 = com.yyw.ts70xhw.FtSet.IsIconExist(r11)
            r12 = 1
            if (r11 != r12) goto L_0x068d
            r11 = 3
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            goto L_0x0197
        L_0x068d:
            r11 = 4
            int r11 = com.yyw.ts70xhw.FtSet.IsIconExist(r11)
            r12 = 1
            if (r11 != r12) goto L_0x0197
            r11 = 6
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            goto L_0x0197
        L_0x069c:
            r11 = 4
            int r11 = com.yyw.ts70xhw.FtSet.IsIconExist(r11)
            r12 = 1
            if (r11 != r12) goto L_0x06ab
            r11 = 6
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            goto L_0x0197
        L_0x06ab:
            r11 = 3
            int r11 = com.yyw.ts70xhw.FtSet.IsIconExist(r11)
            r12 = 1
            if (r11 != r12) goto L_0x0197
            r11 = 4
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            goto L_0x0197
        L_0x06ba:
            r11 = 3
            int r11 = com.yyw.ts70xhw.FtSet.IsIconExist(r11)
            r12 = 1
            if (r11 != r12) goto L_0x06c9
            r11 = 4
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            goto L_0x0197
        L_0x06c9:
            r11 = 2
            int r11 = com.yyw.ts70xhw.FtSet.IsIconExist(r11)
            r12 = 1
            if (r11 != r12) goto L_0x0197
            r11 = 3
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            goto L_0x0197
        L_0x06d8:
            com.ts.MainUI.Evc r11 = r15.mEvc
            r12 = 2
            r11.evol_mut_set(r12)
            r6 = 0
            goto L_0x0197
        L_0x06e1:
            int r11 = com.lgb.canmodule.CanJni.GetCanType()
            r12 = 88
            if (r11 != r12) goto L_0x0704
            com.ts.bt.BtExe r11 = com.ts.bt.BtExe.getBtInstance()
            boolean r11 = r11.isHaveCall()
            if (r11 == 0) goto L_0x06fd
            com.ts.bt.BtExe r11 = com.ts.bt.BtExe.getBtInstance()
            r11.hangup()
        L_0x06fa:
            r6 = 0
            goto L_0x0197
        L_0x06fd:
            com.ts.MainUI.Evc r11 = r15.mEvc
            r12 = 2
            r11.evol_mut_set(r12)
            goto L_0x06fa
        L_0x0704:
            com.ts.MainUI.Evc r11 = r15.mEvc
            r12 = 2
            r11.evol_mut_set(r12)
            goto L_0x06fa
        L_0x070b:
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r12 = "com.google.android.apps.googleassistant"
            boolean r11 = r11.IsHaveApk(r12)
            if (r11 == 0) goto L_0x071d
            r15.WakeUpGoogleAssiant()
            goto L_0x0197
        L_0x071d:
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r12 = "com.google.android.googlequicksearchbox"
            boolean r11 = r11.IsHaveApk(r12)
            if (r11 == 0) goto L_0x072f
            r15.WakeUpGoogle()
            goto L_0x0197
        L_0x072f:
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r12 = "com.txznet.txz"
            boolean r11 = r11.IsHaveApk(r12)
            if (r11 == 0) goto L_0x0197
            com.ts.main.txz.TxzReg r11 = com.ts.main.txz.TxzReg.GetInstance()
            r12 = 0
            r11.TxzStartMic(r12)
            goto L_0x0197
        L_0x0746:
            java.lang.String r11 = "MainUI"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "PKEY_EQ = "
            r12.<init>(r13)
            java.lang.String r13 = com.ts.main.common.WinShow.getTopActivityName()
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r12 = r12.toString()
            android.util.Log.i(r11, r12)
            java.lang.String r11 = com.ts.main.common.WinShow.getTopActivityName()
            java.lang.String r12 = "com.ts.set.SettingSoundActivity"
            boolean r11 = r11.equals(r12)
            if (r11 != 0) goto L_0x0773
            com.ts.main.common.WinShow.TurnToEq()
        L_0x0770:
            r6 = 0
            goto L_0x0197
        L_0x0773:
            int r11 = com.yyw.ts70xhw.Iop.GetEqm()
            r15.nMode = r11
            int r11 = r15.nMode
            int r11 = r11 + 1
            r15.nMode = r11
            int r11 = r15.nMode
            r12 = 5
            if (r11 <= r12) goto L_0x0787
            r11 = 0
            r15.nMode = r11
        L_0x0787:
            com.ts.MainUI.Evc r11 = r15.mEvc
            int r12 = r15.nMode
            r11.evol_eqm_set(r12)
            goto L_0x0770
        L_0x078f:
            java.lang.String r11 = r15.loundString
            if (r11 != 0) goto L_0x07cb
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            android.content.res.Resources r12 = r15.getResources()
            int r13 = com.ts.MainUI.R.string.set_balance_loud
            java.lang.String r12 = r12.getString(r13)
            java.lang.String r12 = java.lang.String.valueOf(r12)
            r11.<init>(r12)
            java.lang.String r12 = ":"
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            r15.loundString = r11
            android.content.res.Resources r11 = r15.getResources()
            int r12 = com.ts.MainUI.R.string.set_common_switch_off
            java.lang.String r11 = r11.getString(r12)
            r15.strOFF = r11
            android.content.res.Resources r11 = r15.getResources()
            int r12 = com.ts.MainUI.R.string.set_common_switch_on
            java.lang.String r11 = r11.getString(r12)
            r15.strON = r11
        L_0x07cb:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = r15.loundString
            java.lang.String r12 = java.lang.String.valueOf(r12)
            r11.<init>(r12)
            java.lang.String r12 = r15.strOFF
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r5 = r11.toString()
            int r11 = com.yyw.ts70xhw.Iop.DspSupport()
            r12 = 1
            if (r11 != r12) goto L_0x0816
            r11 = 0
            int r11 = com.yyw.ts70xhw.Iop.DspGetLoud(r11)
            r12 = 1
            if (r11 != r12) goto L_0x07fb
            r11 = 0
            r12 = 0
            com.yyw.ts70xhw.Iop.DspSetLoud(r11, r12)
        L_0x07f4:
            r11 = 1000(0x3e8, float:1.401E-42)
            r15.showAutoDismissDlg(r5, r11)
            goto L_0x0197
        L_0x07fb:
            r11 = 0
            r12 = 1
            com.yyw.ts70xhw.Iop.DspSetLoud(r11, r12)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = r15.loundString
            java.lang.String r12 = java.lang.String.valueOf(r12)
            r11.<init>(r12)
            java.lang.String r12 = r15.strON
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r5 = r11.toString()
            goto L_0x07f4
        L_0x0816:
            int r11 = com.yyw.ts70xhw.Iop.GetLud()
            r12 = 1
            if (r11 != r12) goto L_0x0822
            r11 = 0
            com.yyw.ts70xhw.Iop.LudSet(r11)
            goto L_0x07f4
        L_0x0822:
            r11 = 1
            com.yyw.ts70xhw.Iop.LudSet(r11)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = r15.loundString
            java.lang.String r12 = java.lang.String.valueOf(r12)
            r11.<init>(r12)
            java.lang.String r12 = r15.strON
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r5 = r11.toString()
            goto L_0x07f4
        L_0x083c:
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            java.lang.String r12 = "MCXI"
            boolean r11 = r11.IsCustom(r12)
            if (r11 == 0) goto L_0x085c
            com.ts.bt.BtExe r11 = com.ts.bt.BtExe.getBtInstance()
            boolean r11 = r11.isHaveCall()
            if (r11 == 0) goto L_0x085c
            com.ts.bt.BtExe r11 = com.ts.bt.BtExe.getBtInstance()
            r11.answer()
            goto L_0x0197
        L_0x085c:
            int r11 = com.yyw.ts70xhw.Iop.GetWorkMode()
            r12 = 1
            if (r11 != r12) goto L_0x0870
            java.lang.String r11 = com.ts.main.common.MainSet.GetSerid()
            java.lang.String r12 = "ZHYC"
            boolean r11 = r11.contains(r12)
            if (r11 == 0) goto L_0x0197
        L_0x0870:
            boolean r11 = com.ts.main.common.WinShow.IsRadioActivity()
            if (r11 != 0) goto L_0x0197
            r11 = 2
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            r6 = 0
            goto L_0x0197
        L_0x087e:
            int r11 = com.yyw.ts70xhw.Iop.GetWorkMode()
            r12 = 1
            if (r11 == r12) goto L_0x0197
            boolean r11 = com.ts.main.common.WinShow.IsRadioActivity()
            if (r11 != 0) goto L_0x0197
            r11 = 2
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            r6 = 0
            goto L_0x0197
        L_0x0893:
            int r11 = com.yyw.ts70xhw.Mcu.BklisOn()
            if (r11 != 0) goto L_0x089c
            com.yyw.ts70xhw.Mcu.BklTurn()
        L_0x089c:
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            boolean r11 = r11.IsMathToMcu()
            if (r11 == 0) goto L_0x0197
            com.ts.MainUI.Evc r11 = r15.mEvc
            r12 = 1
            r11.Evol_vol_tune(r12)
            r6 = 0
            com.ts.main.common.MainVolume r11 = com.ts.main.common.MainVolume.GetInstance()
            r12 = 1
            r11.nAidlVolumeShow = r12
            goto L_0x0197
        L_0x08b6:
            int r11 = com.yyw.ts70xhw.Mcu.BklisOn()
            if (r11 != 0) goto L_0x08bf
            com.yyw.ts70xhw.Mcu.BklTurn()
        L_0x08bf:
            com.ts.main.common.MainSet r11 = com.ts.main.common.MainSet.GetInstance()
            boolean r11 = r11.IsMathToMcu()
            if (r11 == 0) goto L_0x0197
            com.ts.MainUI.Evc r11 = r15.mEvc
            r12 = 0
            r11.Evol_vol_tune(r12)
            r6 = 0
            com.ts.main.common.MainVolume r11 = com.ts.main.common.MainVolume.GetInstance()
            r12 = 1
            r11.nAidlVolumeShow = r12
            goto L_0x0197
        L_0x08d9:
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 4
            r11.sendKeyClick(r12)
            r6 = 0
            goto L_0x0197
        L_0x08e2:
            r11 = 7
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            r6 = 0
            goto L_0x0197
        L_0x08ea:
            r11 = 13
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            r6 = 0
            goto L_0x0197
        L_0x08f3:
            r11 = 14
            r12 = 0
            com.ts.main.common.WinShow.GotoWin(r11, r12)
            r6 = 0
            goto L_0x0197
        L_0x08fc:
            r11 = 16
            com.yyw.ts70xhw.Mcu.SendXKey(r11)
            r6 = 0
            goto L_0x0197
        L_0x0904:
            r11 = 17
            com.yyw.ts70xhw.Mcu.SendXKey(r11)
            r6 = 0
            goto L_0x0197
        L_0x090c:
            int r11 = r15.GetMcuState()
            r12 = 3
            if (r11 != r12) goto L_0x091b
            r11 = 17
            com.yyw.ts70xhw.Mcu.SendXKey(r11)
            r6 = 0
            goto L_0x0197
        L_0x091b:
            r11 = 16
            com.yyw.ts70xhw.Mcu.SendXKey(r11)
            r6 = 0
            goto L_0x0197
        L_0x0923:
            com.ts.main.common.MainUI$9 r11 = new com.ts.main.common.MainUI$9
            r11.<init>()
            r11.start()
            r6 = 0
            goto L_0x0197
        L_0x092e:
            com.ts.can.CanIF.GotoAcWin()
            r6 = 0
            goto L_0x0197
        L_0x0934:
            java.lang.String r11 = "com.ts.MainUI"
            java.lang.String r12 = "com.ts.can.CanMainActivity"
            com.ts.main.common.WinShow.show(r11, r12)
            r6 = 0
            goto L_0x0197
        L_0x0940:
            if (r8 <= 0) goto L_0x0945
            switch(r6) {
                case 44: goto L_0x0958;
                case 45: goto L_0x095b;
                case 515: goto L_0x0955;
                case 516: goto L_0x0952;
                default: goto L_0x0945;
            }
        L_0x0945:
            int r11 = com.ts.main.radio.RadioFunc.DealKey(r6)
            r12 = 1
            if (r11 != r12) goto L_0x01ba
            r6 = 0
            r15.KeyBeep()
            goto L_0x01ba
        L_0x0952:
            r6 = 44
            goto L_0x0945
        L_0x0955:
            r6 = 45
            goto L_0x0945
        L_0x0958:
            r6 = 46
            goto L_0x0945
        L_0x095b:
            r6 = 47
            goto L_0x0945
        L_0x095e:
            boolean r1 = r15.dealIpodKey(r6)
            if (r1 == 0) goto L_0x01ba
            r6 = 0
            r15.KeyBeep()
            goto L_0x01ba
        L_0x096a:
            int r11 = com.ts.bt.BtFunc.DealKey(r6)
            r12 = 1
            if (r11 != r12) goto L_0x01ba
            r6 = 0
            r15.KeyBeep()
            goto L_0x01ba
        L_0x0977:
            switch(r6) {
                case 44: goto L_0x097c;
                case 45: goto L_0x09c5;
                case 46: goto L_0x097c;
                case 47: goto L_0x09c5;
                case 56: goto L_0x097c;
                case 57: goto L_0x09c5;
                case 60: goto L_0x09a8;
                case 90: goto L_0x099c;
                case 91: goto L_0x0990;
                case 291: goto L_0x097c;
                case 292: goto L_0x09c5;
                case 299: goto L_0x09a8;
                case 515: goto L_0x09c5;
                case 516: goto L_0x097c;
                case 784: goto L_0x097c;
                case 789: goto L_0x09c5;
                case 794: goto L_0x097c;
                case 799: goto L_0x09c5;
                case 824: goto L_0x09a8;
                default: goto L_0x097a;
            }
        L_0x097a:
            goto L_0x01ba
        L_0x097c:
            java.lang.String r11 = r15.getTopPackageName()
            java.lang.String r12 = "com.ex.dabplayer.pad"
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x09b4
            r11 = 46
            com.ts.main.common.MainSet.DABControl(r11)
            goto L_0x01ba
        L_0x0990:
            com.ts.main.common.CarplayControl.Pause()
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 127(0x7f, float:1.78E-43)
            r11.sendKeyClick(r12)
            goto L_0x01ba
        L_0x099c:
            com.ts.main.common.CarplayControl.Play()
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 126(0x7e, float:1.77E-43)
            r11.sendKeyClick(r12)
            goto L_0x01ba
        L_0x09a8:
            com.ts.main.common.CarplayControl.Playpp()
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 85
            r11.sendKeyClick(r12)
            goto L_0x01ba
        L_0x09b4:
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 87
            r11.sendKeyClick(r12)
            r11 = 46
            com.ts.main.common.MainSet.DABControl(r11)
            com.ts.main.common.CarplayControl.PlayNext()
            goto L_0x01ba
        L_0x09c5:
            java.lang.String r11 = r15.getTopPackageName()
            java.lang.String r12 = "com.ex.dabplayer.pad"
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x09d9
            r11 = 45
            com.ts.main.common.MainSet.DABControl(r11)
            goto L_0x01ba
        L_0x09d9:
            com.ts.main.common.KeyTouch r11 = r15.mKeyTouch
            r12 = 88
            r11.sendKeyClick(r12)
            r11 = 45
            com.ts.main.common.MainSet.DABControl(r11)
            com.ts.main.common.CarplayControl.PlayPrev()
            goto L_0x01ba
        L_0x09ea:
            if (r8 <= 0) goto L_0x09ef
            switch(r6) {
                case 21: goto L_0x0a06;
                case 515: goto L_0x0a06;
                case 516: goto L_0x0a06;
                default: goto L_0x09ef;
            }
        L_0x09ef:
            r11 = 46
            if (r6 != r11) goto L_0x0a08
            r6 = 44
        L_0x09f5:
            com.ts.dvdplayer.ITsPlayerService r11 = r15.mTsPlayerService     // Catch:{ RemoteException -> 0x0a00 }
            if (r11 == 0) goto L_0x01ba
            com.ts.dvdplayer.ITsPlayerService r11 = r15.mTsPlayerService     // Catch:{ RemoteException -> 0x0a00 }
            r11.nDealMediaKey(r6)     // Catch:{ RemoteException -> 0x0a00 }
            goto L_0x01ba
        L_0x0a00:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x01ba
        L_0x0a06:
            r6 = 0
            goto L_0x09ef
        L_0x0a08:
            r11 = 47
            if (r6 != r11) goto L_0x09f5
            r6 = 45
            goto L_0x09f5
        L_0x0a0f:
            com.ts.MainUI.Cmmb r11 = com.ts.MainUI.Cmmb.GetInstance()
            int r11 = r11.DealCmmbKey(r6)
            r12 = 1
            if (r11 != r12) goto L_0x01ba
            r15.KeyBeep()
            goto L_0x01ba
        L_0x0a1f:
            int r11 = com.ts.can.CanIF.DealExdKey(r6)
            r12 = 1
            if (r11 != r12) goto L_0x01ba
            r15.KeyBeep()
            goto L_0x01ba
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.main.common.MainUI.DealKey():void");
    }

    private boolean dealIpodKey(int nKeyCode) {
        if (this.mIpodService == null) {
            return false;
        }
        switch (nKeyCode) {
            case 44:
            case 56:
            case 291:
            case 516:
            case KeyDef.SKEY_SEEKUP_1 /*784*/:
            case KeyDef.SKEY_CHUP_1 /*794*/:
                try {
                    this.mIpodService.ipodNext();
                    return true;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return false;
                }
            case 45:
            case 57:
            case 292:
            case 515:
            case KeyDef.SKEY_SEEKDN_1 /*789*/:
            case KeyDef.SKEY_CHDN_1 /*799*/:
                this.mIpodService.ipodPrev();
                return true;
            case 60:
            case 299:
            case KeyDef.SKEY_PP_1 /*824*/:
                this.mIpodService.ipodPlaypause();
                return true;
            case 90:
                this.mIpodService.ipodPlay();
                return true;
            case 91:
                this.mIpodService.ipodPause();
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void CheckAuth() {
        if (nMcuRet == 1 && !AuthServer.GetInstance().IsAuthOk() && !MainSet.GetInstance().IsTestMode()) {
            WinShow.GotoWin(16, 0);
        }
    }

    public class easyConnectRecevie extends BroadcastReceiver {
        public easyConnectRecevie() {
        }

        public void onReceive(Context context, Intent intent) {
            String City;
            String action = intent.getAction();
            if (MainUI.ACTION_SHOW_USB_DISPLAY.equals(action)) {
                Log.i(MainUI.TAG, "SHOW_USB_DISPLAY==" + intent.getIntExtra(MainUI.SHOW_USB_DISPLAY, 0));
            }
            if (MainUI.TXZ_GET_WWATHER_DATA.equalsIgnoreCase(action) && (City = intent.getStringExtra("City")) != null) {
                TxzReg.GetInstance().GetWeatherData(City);
            }
            if (MainUI.BROADCAST_GLSX_VOICE.equals(action)) {
                if (MainSet.GetInstance().IsHaveApk("com.txznet.txz")) {
                    TxzReg.GetInstance().TxzStartMic((String) null);
                }
            } else if (MainUI.ACTION_AVM_SIGNAL_STATUS.equals(action)) {
                int nChannel = intent.getExtras().getInt("channel");
                int nStatus = intent.getExtras().getInt("status");
                Log.i(MainUI.TAG, "channel==" + nChannel + "  status=" + nStatus);
                switch (nChannel) {
                    case 0:
                        MainUI.AVMChaneleA = nStatus;
                        break;
                    case 1:
                        MainUI.AVMChaneleB = nStatus;
                        break;
                    case 2:
                        MainUI.AVMChaneleC = nStatus;
                        break;
                    case 3:
                        MainUI.AVMChaneleD = nStatus;
                        break;
                }
            } else if (MainUI.ACTION_REMOTE_WAKEUP.equals(action)) {
                Log.i(MainUI.TAG, "ACTION_REMOTE_WAKEUP==");
                MainUI.bRemoteWakeUp = true;
                MainUI.this.McuWakeUp();
                if (MainSet.GetInstance().IsSupprotRemotecontrol()) {
                    tool.GetInstance().killProcess("com.autochips.avmplayer");
                }
            } else if (MainUI.ACTION_REMOTE_SLEEP.equals(action)) {
                Log.i(MainUI.TAG, "ACTION_RE OTE_SLEEP==");
                MainUI.this.McuSleep();
            } else if (MainUI.ACTION_AVM_RESET_ALL.equals(action)) {
                MainSet.GetInstance().AvmSetFile(true);
            }
            if (MainUI.BROADCAST_BT_CHECKSTATUS.equalsIgnoreCase(action)) {
                Log.i(MainUI.TAG, "BROADCAST_BT_CHECKSTATUS==net.easyconn.bt.checkstatus");
                if (BtExe.getBtInstance().isConnected()) {
                    MainSet.SendIntent(MainUI.BROADCAST_BT_CONNECTED, (String) null);
                    Log.i(MainUI.TAG, "send to easy connect");
                    return;
                }
                Intent intent2 = new Intent();
                intent2.setAction(MainUI.BROADCAST_BT_OPENED);
                intent2.putExtra("name", BtExe.getBtInstance().getDevName());
                intent2.putExtra("pin", BtExe.getBtInstance().getDevPin());
                intent2.putExtra("mac", BtExe.getBtInstance().getDevName());
                MainUI.this.sendBroadcast(intent2);
            } else if (MainUI.BROADCAST_BT_A2DP_ACQUIRE.equalsIgnoreCase(action)) {
                MainUI.this.mEvc.evol_workmode_set(5);
                if (BtExe.getBtInstance().isConnected() && MainUI.FirsChangeToBt == 0) {
                    BtExe.getBtInstance().setPlayState(true);
                    MainUI.FirsChangeToBt = 1;
                }
                Log.i(MainUI.TAG, "BROADCAST_BT_A2DP_ACQUIRE==");
            } else if (MainUI.BT_A2DP_ACQUIRE.equalsIgnoreCase(action)) {
                MainUI.this.mEvc.evol_workmode_set(5);
                Log.i(MainUI.TAG, "BT_A2DP_ACQUIRE==");
            } else if (!MainUI.BROADCAST_BT_A2DP_RELEASE.equalsIgnoreCase(action) && !MainUI.BROADCAST_APP_QUIT.equalsIgnoreCase(action)) {
                if (MainUI.BROADCAST_DRIVEMODE_CHECKSTATUS.equalsIgnoreCase(action)) {
                    Log.i(MainUI.TAG, "BROADCAST_DRIVEMODE_CHECKSTATUS==");
                } else if (MainUI.BROADCAST_LANCHER_FUNC_ACCELERATION.equalsIgnoreCase(action)) {
                    Log.w(MainUI.TAG, "BROADCAST_LANCHER_FUNC_ACCELERATION");
                    MainUI.this.removeTask(true);
                } else if (MainUI.BROADCAST_LANCHER_FUNC_REBOOT.equalsIgnoreCase(action)) {
                    Log.w(MainUI.TAG, "BROADCAST_LANCHER_FUNC_REBOOT");
                    if (MainUI.nMcuRet == 1) {
                        MainSet.GetInstance().SystemReboot();
                    }
                } else if (MainUI.BROADCAST_LANCHER_FUNC_MUTE.equalsIgnoreCase(action)) {
                    if (FtSet.GetUsbHost() == 0) {
                        Log.w(MainUI.TAG, "BROADCAST_LANCHER_FUNC_MUTE");
                        Mcu.SetCkey(16);
                        return;
                    }
                    MainUI.this.openGPS();
                } else if (MainUI.BROADCAST_LANCHER_FUNC_VOLUME.equalsIgnoreCase(action)) {
                    Log.w(MainUI.TAG, "BROADCAST_LANCHER_FUNC_VOLUME");
                    MainUI.this.mMainVolume.VolWinShow();
                } else if (MainUI.BROADCAST_LANCHER_FUNC_EQ.equalsIgnoreCase(action)) {
                    WinShow.TurnToEq();
                } else if (MainUI.BROADCAST_LANCHER_FUNC_VOLUMEADD.equalsIgnoreCase(action)) {
                    Log.w(MainUI.TAG, "BROADCAST_LANCHER_FUNC_VOLUMEADD");
                    if (FtSet.GetUsbHost() == 0) {
                        MainUI.this.mEvc.Evol_vol_tune(1);
                    } else {
                        tool.GetInstance().SetScreenH();
                    }
                } else if (MainUI.BROADCAST_LANCHER_FUNC_VOLUMEDEC.equalsIgnoreCase(action)) {
                    Log.w(MainUI.TAG, "BROADCAST_LANCHER_FUNC_VOLUMEDEC");
                    if (FtSet.GetUsbHost() == 0) {
                        MainUI.this.mEvc.Evol_vol_tune(0);
                    } else {
                        tool.GetInstance().SetScreenV();
                    }
                } else if (MainUI.BROADCAST_LANCHER_FUNC_SCREENOFF.equalsIgnoreCase(action)) {
                    if (FtSet.GetUsbHost() == 0) {
                        Mcu.BklTurn();
                    } else {
                        MainUI.this.CloseGps();
                    }
                    Log.w(MainUI.TAG, "BROADCAST_LANCHER_FUNC_SCREENOFF");
                } else if (MainUI.BROADCAST_LANCHER_FUNC_NOWMODE.equalsIgnoreCase(action)) {
                    Log.w(MainUI.TAG, "BROADCAST_LANCHER_FUNC_NOWMODE");
                    if (Iop.GetWorkMode() != 0) {
                        WinShow.TsEnterMode(Iop.GetWorkMode());
                    } else if (MainSet.GetInstance().IsHaveApk("cn.kuwo.kwmusiccar")) {
                        MainSet.GetInstance().openApplication(MainUI.mMainUI, "cn.kuwo.kwmusiccar");
                    } else if (MainSet.GetInstance().IsHaveApk("com.hongfans.rearview")) {
                        MainSet.GetInstance().openApplication(MainUI.mMainUI, "com.hongfans.rearview");
                    } else {
                        WinShow.TsEnterMode(4);
                    }
                } else if ("forfan.intent.action.set_productid".equalsIgnoreCase(action)) {
                    String nVal = intent.getStringExtra("set_proid");
                    if (nVal != null && nVal.length() <= 32) {
                        FtSet.SetProId(MainUI.this.StrToByte32(nVal), 32);
                        FtSet.Save(0);
                        MainUI.WriteProductFile();
                    }
                } else if ("forfan.intent.action.CLOSEMEDIA".equalsIgnoreCase(action)) {
                    if (intent.getIntExtra("mode", -1) != 1) {
                        MainUI.this.BackToLauncher();
                    } else if (WinShow.IsRadioActivity()) {
                        MainUI.this.BackToLauncher();
                    }
                    MainUI.this.mEvc.evol_workmode_set(0);
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
                } else if ("ActivityWarm_Closed".equalsIgnoreCase(action)) {
                    if (MainUI.bEnterMode) {
                        WinShow.TsEnterMode(Iop.GetWorkMode());
                        MainUI.bEnterMode = false;
                    }
                } else if (MainUI.NET_TIME_CHANGE.equalsIgnoreCase(action)) {
                    long nVal2 = intent.getLongExtra(MainUI.NET_TIME_, 0);
                    Log.i(MainUI.TAG, "NET_TIME_CHANGE==" + nVal2);
                    SystemClock.setCurrentTimeMillis(nVal2);
                    MainUI.nHasGetTime = 1;
                } else if (MainUI.BROADCAST_NET_CHANGE.equalsIgnoreCase(action)) {
                    ConnectivityManager manager = (ConnectivityManager) context.getSystemService("connectivity");
                    NetworkInfo gprs = manager.getNetworkInfo(0);
                    NetworkInfo wifi = manager.getNetworkInfo(1);
                    Log.i(MainUI.TAG, "BROADCAST_NET_CHANGE==wifi.isConnected()==+" + wifi.isConnected());
                    Log.i(MainUI.TAG, "BROADCAST_NET_CHANGE==gprs.isConnected()==+" + gprs.isConnected());
                    if (wifi.isConnected() || gprs.isConnected()) {
                        Log.w(MainUI.TAG, "net is connect");
                        MainUI.this.CheckAuth();
                        if (MainUI.nHasGetTime == 0) {
                            MainSet.GetInstance().GetNetTime();
                            return;
                        }
                        return;
                    }
                    Log.w(MainUI.TAG, "net is unconnect");
                } else if ("autochips.intent.action.QB_POWERON".equalsIgnoreCase(action)) {
                    tool.WriteLog("*********************nQuickNum=" + MainUI.this.nQuickNum + "**********************");
                    MainUI.this.nQuickNum++;
                    tool.WriteLog("ACTION_QB_ON");
                    Log.i(MainUI.TAG, "ACTION_QB_ON");
                    MainUI.this.DealQB_ON();
                } else if ("autochips.intent.action.QB_POWEROFF".equalsIgnoreCase(action)) {
                    Log.i(MainUI.TAG, "ACTION_QB_OFF");
                    tool.WriteLog("ACTION_QB_OFF");
                    MainUI.this.DealQB_Off();
                } else if (MainUI.ACTION_PREQB_OFF.equalsIgnoreCase(action)) {
                    Log.i(MainUI.TAG, "ACTION_PREQB_OFF");
                    tool.WriteLog("ACTION_PREQB_OFF");
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public byte[] StrToByte32(String str) {
        byte[] naviname2 = new byte[32];
        byte[] naviname = str.getBytes();
        for (int i = 0; i < naviname.length; i++) {
            naviname2[i] = naviname[i];
        }
        return naviname2;
    }

    private void setLocationMode(int mode) {
        Settings.Secure.putInt(getContentResolver(), "location_mode", mode);
    }

    public void openGPS() {
        setLocationMode(3);
    }

    public void CloseGps() {
        setLocationMode(0);
    }

    private Criteria getLocationCriteria() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        criteria.setSpeedRequired(true);
        criteria.setCostAllowed(false);
        criteria.setBearingRequired(true);
        criteria.setAltitudeRequired(false);
        criteria.setPowerRequirement(1);
        return criteria;
    }

    private void UpLoadTheLocation() {
        this.loctionManager = (LocationManager) getSystemService("location");
        Location location = this.loctionManager.getLastKnownLocation(this.loctionManager.getBestProvider(getLocationCriteria(), true));
        if (location != null) {
            Log.i(TAG, "location.getLatitude()" + location.getLatitude());
            Log.i(TAG, "location.getAltitude()" + location.getAltitude());
        }
        this.loctionManager.requestLocationUpdates("gps", 1000, 0.0f, this.locationListener);
        this.loctionManager.addGpsStatusListener(this.gpsStatusListener);
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
            return MainUI.GpsSpeed;
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
            if (MainUI.this.GetMcuState() != 2) {
                if (Evc.GetInstance().GetWorkMode() == 10) {
                    if (MainUI.this.mIpodService != null) {
                        return MainUI.this.mIpodService.getCurrentTime();
                    }
                } else if (MainUI.this.mTsPlayerService != null) {
                    return (int) MainUI.this.mTsPlayerService.getCurrentTime();
                }
            }
            return 0;
        }

        public int GetTotalTime() throws RemoteException {
            if (MainUI.this.GetMcuState() != 2) {
                if (Evc.GetInstance().GetWorkMode() == 10) {
                    if (MainUI.this.mIpodService != null) {
                        return MainUI.this.mIpodService.getTotalTime();
                    }
                } else if (MainUI.this.mTsPlayerService != null) {
                    return (int) MainUI.this.mTsPlayerService.getTotoalTime();
                }
            }
            return 0;
        }

        public int GetPlayState() throws RemoteException {
            if (MainUI.this.GetMcuState() != 2) {
                if (Evc.GetInstance().GetWorkMode() == 10) {
                    if (MainUI.this.mIpodService == null || MainUI.this.mIpodService.getPlayStatu() != 0) {
                        return 0;
                    }
                    return 1;
                } else if (MainUI.this.mTsPlayerService != null) {
                    if (MainUI.this.mTsPlayerService.getPlayStatus() == 2) {
                        return 1;
                    }
                    if (MainUI.this.mTsPlayerService.getPlayStatus() == 5) {
                        return 2;
                    }
                }
            }
            return 0;
        }

        public String GetSongName() throws RemoteException {
            if (Iop.GetWorkMode() == 5) {
                BtExe.getBtInstance();
                return BtExe.mStrId3Name;
            }
            if (Iop.GetWorkMode() == 10) {
                if (!(MainUI.this.GetMcuState() == 2 || MainUI.this.mIpodService == null)) {
                    return MainUI.this.mIpodService.getId3Title();
                }
            } else if (!(MainUI.this.GetMcuState() == 2 || MainUI.this.mTsPlayerService == null)) {
                return MainUI.this.mTsPlayerService.getPlayName();
            }
            return " ";
        }

        public String GetId3Album() throws RemoteException {
            if (Iop.GetWorkMode() == 5) {
                BtExe.getBtInstance();
                return BtExe.mStrId3Album;
            }
            if (Iop.GetWorkMode() == 10) {
                if (!(MainUI.this.GetMcuState() == 2 || MainUI.this.mIpodService == null)) {
                    return MainUI.this.mIpodService.getId3Album();
                }
            } else if (MainUI.this.mTsPlayerService != null) {
                return MainUI.this.mTsPlayerService.getId3AlbumName();
            }
            return " ";
        }

        public String GetId3Artist() throws RemoteException {
            if (Iop.GetWorkMode() == 5) {
                return BtExe.mStrId3Artist;
            }
            if (Iop.GetWorkMode() == 10) {
                if (!(MainUI.this.GetMcuState() == 2 || MainUI.this.mIpodService == null)) {
                    return MainUI.this.mIpodService.getId3Artist();
                }
            } else if (MainUI.this.mTsPlayerService != null) {
                return MainUI.this.mTsPlayerService.getId3Artist();
            }
            return " ";
        }

        public String GetId3Title() throws RemoteException {
            if (Iop.GetWorkMode() == 5) {
                return BtExe.mStrId3Name;
            }
            if (Iop.GetWorkMode() == 10) {
                if (!(MainUI.this.GetMcuState() == 2 || MainUI.this.mIpodService == null)) {
                    return MainUI.this.mIpodService.getId3Title();
                }
            } else if (MainUI.this.mTsPlayerService != null) {
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
            if (MainUI.this.mEvc.GetWorkMode() == 10) {
                return MainUI.this.mIpodService.getRepeatMode();
            }
            if (MainUI.this.mTsPlayerService != null) {
                return MainUI.this.mTsPlayerService.getRepeatMode();
            }
            return 0;
        }

        public int getShuffleMode() throws RemoteException {
            if (MainUI.this.mEvc.GetWorkMode() == 10) {
                return MainUI.this.mIpodService.getRandomMode();
            }
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
            if (MainUI.nMcuRet != 1) {
                return " ";
            }
            MainSet.GetInstance();
            return MainSet.GetSerid();
        }

        public String GetIMSI() throws RemoteException {
            if (MainUI.nMcuRet != 1 || (Mcu.GetPowState() & 1) == 0) {
                return "0";
            }
            return "1";
        }

        public String GetDeviceID() throws RemoteException {
            if (MainUI.nMcuRet != 1 || !AuthServer.GetInstance().IsAuthOk()) {
                return null;
            }
            return MainSet.GetSerid();
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
            if (name.equalsIgnoreCase(BtInCallService.TAG)) {
                return (IBinder) MainUI.this.mBTCommon;
            }
            if (!name.startsWith("Tbox") || !name.equals("Tbox_App")) {
                return null;
            }
            return (IBinder) MainUI.this.mAppInterface;
        }
    }

    public ITsCom getITsCom() {
        return this.mTsCom;
    }

    public IBinder onBind(Intent intent) {
        return this.mTsCom;
    }

    private void connectIPodService() {
        if (FtSet.IsIconExist(112) == 1) {
            Intent intent = new Intent();
            intent.setClassName("com.ts.ipodplayer", "com.autochips.ipodplayer.ipodserver.service.IPodService");
            startService(intent);
            this.bIpodConnected = bindService(intent, this.mIpodConn, 1);
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
            Intent intent = new Intent("com.ts.tts_touch.TouchService");
            intent.setPackage("com.ts.tts_touch");
            startService(intent);
            return;
        }
        Intent intent2 = new Intent("com.ts.tts_touch.TouchService");
        intent2.setPackage("com.ts.tts_touch");
        stopService(intent2);
    }

    /* access modifiers changed from: package-private */
    public void InintDvpInterface() {
        Intent intent = new Intent();
        intent.setAction("com.ts.dvdplayer.MediaPlayerService");
        intent.setPackage("com.ts.dvdplayer");
        bindService(intent, this.mServiceConnection, 1);
    }

    /* access modifiers changed from: package-private */
    public void SetAdasLightState() {
        if (this.nILL != Mcu.GetIll()) {
            this.nILL = Mcu.GetIll();
            SkinUtils.getInstance().IllStateChange(this.nILL);
            if (this.nILL == 1) {
                SystemProperties.set(BROADCAST_ADAS_LIGHT_STATE, "on");
                Log.i(TAG, "BROADCAST_ADAS_LIGHT_STATE   on ");
                AmapAuto.SetHeadLight(0);
                AmapAuto.GetInstance().SendCarInfordata();
                CarplayControl.SetNightMode(true);
                WirelessCarplay.setNightMode(true);
                return;
            }
            SystemProperties.set(BROADCAST_ADAS_LIGHT_STATE, "off");
            Log.i(TAG, "BROADCAST_ADAS_LIGHT_STATE   off ");
            AmapAuto.SetHeadLight(1);
            AmapAuto.GetInstance().SendCarInfordata();
            CarplayControl.SetNightMode(false);
            WirelessCarplay.setNightMode(false);
        }
    }

    /* access modifiers changed from: package-private */
    public void InintParat() {
        this.nErrorNum = 0;
        this.nPowerMode = 0;
        this.nDvdMode = 0;
        this.nDelayToGetVerSion = 100;
        bBtConnect = false;
        this.nUpdateTime = 0;
        this.nAutoToNavi = 0;
        bIsScreenMode = false;
        this.nTpmsOnce = 0;
        this.nOldPowerMode = 0;
        this.nILL = 255;
        this.nHomeKey_enable = 1;
        UpdateCheckTIme = 30;
        this.bShutDown = false;
        this.nError = 0;
        nHasGetTime = 0;
        if (BackcarService.getInstance().bIsAvm360()) {
            this.OldCamStateDelay = 60;
        }
        this.nDelayToStartTxz = 60;
    }

    /* access modifiers changed from: package-private */
    public void AdjustMcuTime() {
        Date d1 = null;
        try {
            d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(BASIC_TIME_STRING);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        long diff = System.currentTimeMillis() - d1.getTime();
        Log.i(TAG, "AdjustMcuTime diff = " + diff);
        if (diff > 0) {
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
                d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(BASIC_TIME_STRING);
                if (System.currentTimeMillis() > d1.getTime()) {
                    return;
                }
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            SystemClock.setCurrentTimeMillis(d1.getTime() + (60 * Time * 1000));
        }
    }

    public static void WriteProductFile() {
        String Str = MainSet.GetInstance().GetProid();
        if (!TsFile.fileIsExists(PROID_FILE)) {
            Log.i(TAG, "WriteProductFile fiel not exit ");
            TsFile.NewDir(FT_SET_DIR);
            try {
                TsFile.writeFileSdcardFile(PROID_FILE, Str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            if (!TsFile.readFileSdcardFile(PROID_FILE).equals(Str)) {
                TsFile.writeFileSdcardFile(PROID_FILE, Str);
                Log.i(TAG, "WriteProductFile fiel read ok and not equals= ");
                return;
            }
            Log.i(TAG, "WriteProductFile fiel read ok and  equals= ");
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static void WriteEasyConnectFile() {
        if (Checknum <= 0) {
            Checknum++;
            if (AuthServer.GetInstance().GetIDType() > 0) {
                byte[] mcuid = new byte[14];
                Mcu.GetSerialId(mcuid);
                if (!TsFile.fileIsExists(EASYLICENSE_FILE)) {
                    Log.i(TAG, "fiel not exit ");
                    TsFile.NewDir(EASYLICENSE_PATH);
                    try {
                        TsFile.writeFileSdcardFile(EASYLICENSE_FILE, CanIF.byte2String(mcuid));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    if (!TsFile.readFileSdcardFile(EASYLICENSE_FILE).equals(CanIF.byte2String(mcuid))) {
                        TsFile.writeFileSdcardFile(EASYLICENSE_FILE, CanIF.byte2String(mcuid));
                        Log.i(TAG, "fiel read ok and not equals= ");
                        return;
                    }
                    Log.i(TAG, "fiel read ok and  equals= ");
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public static int GetSrcW() {
        return SystemProperties.getInt("ro.forfan.hardware.width", 1024);
    }

    public static int GetSrcH() {
        return SystemProperties.getInt("ro.forfan.hardware.height", 600);
    }

    public static int GetDispW() {
        if (SystemProperties.getInt("ro.forfan.adaptany.enable", 0) == 1) {
            return SystemProperties.getInt("ro.forfan.adaptany.twidth", 1024);
        }
        return SystemProperties.getInt("ro.forfan.hardware.width", 1024);
    }

    public static int GetDispH() {
        if (SystemProperties.getInt("ro.forfan.adaptany.enable", 0) == 1) {
            Log.w(TAG, "GetDispH enable==1 ");
            return SystemProperties.getInt("ro.forfan.adaptany.theight", 600);
        }
        Log.w(TAG, "GetDispH enable==0 ");
        return SystemProperties.getInt("ro.forfan.hardware.height", 600);
    }

    public static int GetTouchX() {
        return SystemProperties.getInt("ro.forfan.touchwidth", 0);
    }

    public static int GetTouchY() {
        return SystemProperties.getInt("ro.forfan.touchheight", 0);
    }

    public int GetToalMemory() {
        if (this.mActivityManager == null) {
            this.mActivityManager = (ActivityManager) getSystemService(SdkConstants.TAG_ACTIVITY);
        }
        if (this.TotalSize == 0) {
            this.mActivityManager.getMemoryInfo(this.memoryInfo);
            this.TotalSize = (int) ((this.memoryInfo.totalMem / 1024) / 1024);
        }
        return this.TotalSize;
    }

    public int GetAviMemory() {
        if (this.mActivityManager == null) {
            this.mActivityManager = (ActivityManager) getSystemService(SdkConstants.TAG_ACTIVITY);
        }
        this.mActivityManager.getMemoryInfo(this.memoryInfo);
        return (int) ((this.memoryInfo.availMem / 1024) / 1024);
    }

    /* access modifiers changed from: package-private */
    public void EnterLastMode() {
        if (getResources().getIdentifier("launcher_forbidden", SdkConstants.TAG_STRING, getPackageName()) > 0) {
            bEnterMode = true;
        } else if (!T3WeakJoinUtils.bIsT3WeakPrj || !T3WeakJoinUtils.binTestMode) {
            int id = getResources().getIdentifier("boot_on_package_name", SdkConstants.TAG_STRING, getPackageName());
            if (id > 0) {
                String packageName = getResources().getString(id);
                int id2 = getResources().getIdentifier("boot_on_activity_name", SdkConstants.TAG_STRING, getPackageName());
                if (id2 > 0) {
                    WinShow.show(packageName, getResources().getString(id2));
                } else {
                    MainSet.GetInstance().openApplication(this, packageName);
                }
                Evc.GetInstance().evol_workmode_set(0);
            } else {
                WinShow.TsEnterMode(Iop.GetWorkMode());
            }
            if (!MainSet.GetInstance().IsTwcjw() && StSet.GetAutoNavi() == 1) {
                this.nAutoToNavi = 100;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void DynamicCheckRotation() {
        if (MainSet.GetInstance().IsSupportRotation()) {
            this.nDirTem = Iop.GetGdirect();
            if (this.nGDirect != this.nDirTem) {
                Log.i(TAG, "nGDirect@@@@@  == " + this.nGDirect);
                this.nGDirect = this.nDirTem;
                if (this.nGDirect == 1) {
                    tool.GetInstance().SetScreenH();
                    Log.i(TAG, "nGDirect@@@@@  SET TO H== " + this.nGDirect);
                } else if (this.nGDirect == 2) {
                    tool.GetInstance().SetScreenV();
                    Log.i(TAG, "nGDirect@@@@@  SET TO V== " + this.nGDirect);
                } else if (this.nGDirect == -1) {
                    Log.i(TAG, "nGDirect@@@@@  ERROR== " + this.nGDirect);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void CheckScreenRotation(int mScrW2, int mScrH2) {
        Log.i(TAG, "CheckScreenRotation mScrW  == " + mScrW2);
        Log.i(TAG, "CheckScreenRotation mScrH  == " + mScrH2);
        mUIDirect = AtcDisplaySettingsUtils.getInstance().readForceDirection();
        mBackCarDirect = AtcDisplaySettingsUtils.getInstance().readRotation();
        Log.i(TAG, "CheckScreenRotation mUIDirect  == " + mUIDirect);
        Log.i(TAG, "CheckScreenRotation mBackCarDirect  == " + mBackCarDirect);
        if (!MainSet.GetInstance().IsSupportRotation()) {
            int nX = AtcDisplaySettingsUtils.getInstance().GetBackCarW();
            int nY = AtcDisplaySettingsUtils.getInstance().GetBackCarH();
            switch (FtSet.GetLcdUpDown()) {
                case 0:
                    if (nX != mScrW2 || nY != mScrH2 || mBackCarDirect != 0) {
                        AtcDisplaySettingsUtils.getInstance().setScreenOrientation(0, mScrW2, mScrH2);
                        FsBaseActivity.ResetMdp();
                        return;
                    }
                    return;
                case 1:
                    if (nX != mScrH2 || nY != mScrW2 || mBackCarDirect != 90) {
                        AtcDisplaySettingsUtils.getInstance().setScreenOrientation(2, mScrW2, mScrH2);
                        FsBaseActivity.ResetMdp();
                        return;
                    }
                    return;
                case 2:
                    if (nX != mScrW2 || nY != mScrH2 || mBackCarDirect != 180) {
                        AtcDisplaySettingsUtils.getInstance().setScreenOrientation(3, mScrW2, mScrH2);
                        FsBaseActivity.ResetMdp();
                        return;
                    }
                    return;
                case 3:
                    if (nX != mScrH2 || nY != mScrW2 || mBackCarDirect != 270) {
                        AtcDisplaySettingsUtils.getInstance().setScreenOrientation(4, mScrW2, mScrH2);
                        FsBaseActivity.ResetMdp();
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else if ((mScrW2 == 1280 && mScrH2 == 720) || (mScrW2 == 1920 && mScrH2 == 1080)) {
            tool.GetInstance().DealSu("wm size reset");
        }
    }

    /* access modifiers changed from: package-private */
    public void SetMemInfo() {
        if (FtSet.GetRom() == 0) {
            SystemProperties.set("forfan.storage.total", tool.GetInstance().GetEmmcSize());
        } else {
            SystemProperties.set("forfan.storage.total", String.valueOf(FtSet.GetRom() * 16) + "GB");
        }
        if (FtSet.GetRam() != 0) {
            SystemProperties.set("forfan.totalRam.M", new StringBuilder().append(FtSet.GetRam() * 950).toString());
        }
        int version = FtSet.GetSdSwap();
        if (version >= 1) {
            if (version > 50) {
                SystemProperties.set("forfan.version.info", String.format(Locale.ENGLISH, "%.1f", new Object[]{Float.valueOf(((float) version) / 10.0f)}));
            } else {
                SystemProperties.set("forfan.version.info", version + 5 >= 9 ? new StringBuilder(String.valueOf(version + 5)).toString() : String.valueOf(version + 5) + ".1");
            }
            if (version >= 4) {
                SystemProperties.set("forfan.enable.eggs", "0");
            }
        }
        if (FtSet.GetBlueSync() == 0) {
            SystemProperties.set("persist.bluetooth.atc.volumesync", "disable");
            SystemProperties.set("persist.bluetooth.atc.scovolumesync", "disable");
        } else {
            SystemProperties.set("persist.bluetooth.atc.volumesync", "enable");
            SystemProperties.set("persist.bluetooth.atc.scovolumesync", "enable");
        }
        SystemProperties.set("persist.bluetooth.a2dpaudiofocus", "disable");
        CheckScreenRotation(GetSrcW(), GetSrcH());
        Log.i(TAG, "ARM2 CAMERA_SOURCE == " + AtcDisplaySettingsUtils.getInstance().getBackcarSourceMode());
        Log.i(TAG, "ARM2 CAMERA_TYPE == " + AtcDisplaySettingsUtils.getInstance().getAHDCameraMode());
        Log.i(TAG, "ARM2 CAMERA_MIPI_X == " + AtcDisplaySettingsUtils.getInstance().GetMipiUserScrX());
        Log.i(TAG, "ARM2 CAMERA_MIPI_Y == " + AtcDisplaySettingsUtils.getInstance().GetMipiUserScrY());
        mScrW = GetDispW();
        mScrH = GetDispH();
        Log.w(TAG, "######## WmInit = mScrW = " + mScrW + " !");
        Log.w(TAG, "######## WmInit = mScrH = " + mScrH + " !");
        if (BackcarService.getInstance().bIsAvm360()) {
            tool.GetInstance().DealSu("chown system /avm/algo_config.xml");
            tool.GetInstance().DealSu("chgrp system /avm/algo_config.xml");
        }
    }

    public boolean IsSyncOK() {
        return nMcuRet == 1;
    }

    /* access modifiers changed from: package-private */
    public void ShowRearView(boolean bShow) {
    }

    /* access modifiers changed from: package-private */
    public void DealQB_ON() {
        Evc.mSystemState = 1;
        this.bQuickBootOn = true;
        BackcarService.getInstance().ResetCamera();
    }

    /* access modifiers changed from: package-private */
    public void DealQB_Off() {
        this.bCheckOver = true;
    }

    /* access modifiers changed from: package-private */
    public void SendSceenSize(boolean bWakeUp) {
        int nX = GetSrcW();
        int nY = GetSrcH();
        if (bWakeUp) {
            Iop.tsxhwStart(((65536 * nX) + nY) | ExploreByTouchHelper.INVALID_ID);
        } else {
            Iop.tsxhwStart((65536 * nX) + nY);
        }
    }

    /* access modifiers changed from: package-private */
    public void WakeUpInintParat() {
        MainSet.GetInstance().bFirstStart = false;
        MainSet.bIsFrontCam = false;
        MainSet.nFcamTime = 0;
        this.nGDirect = 255;
    }

    /* access modifiers changed from: package-private */
    public void McuWakeUp() {
        Mcu.SendXKey(24);
    }

    /* access modifiers changed from: package-private */
    public void McuSleep() {
        Mcu.SendXKey(25);
    }

    public void WakeUpInint() {
        if (Evc.mSystemState < 5) {
            tool.WriteLog("WakeUpInint");
            Log.i(TAG, "WakeUpInint");
            MainSet.GetInstance().SetFlyMode(false);
            InintParat();
            WakeUpInintParat();
            MainAlterwin.GetInstance().HidenPoweroffWin();
            AmapAuto.GetInstance();
            AmapAuto.SendAccStateToSuding(1);
            MainSet.GetInstance().StartCarClientService();
            if (BackcarService.getInstance().bIsAvm360() && !bRemoteWakeUp) {
                MainSet.GetInstance().StartAvmService();
                this.bSend3dRound = true;
                this.bStartAvm = true;
            }
            if (!bRemoteWakeUp) {
                MainSet.SendIntent(ACTION_MAINUI_ACCON, (String) null);
            } else {
                MainSet.SendIntent(ACTION_MAINUI_REMOTE_WAKEUP, (String) null);
            }
            if (MainSet.GetInstance().IsTwcjw()) {
                MainSet.GetInstance().StartNaviKing();
            }
            MainSet.GetInstance().StartArDvrService();
            Log.i(TAG, "WakeUpInint txz killed");
            if (!TxzReg.GetInstance().SetTXZWakeUp()) {
                this.nDelayToStartTxz = 60;
            }
            Log.i(TAG, "mEvc.Evol.workmode = " + Iop.GetWorkMode());
            MainSet.GetInstance().SetTouchState(false);
            InintDvpInterface();
            connectIPodService();
            Iop.EvolTask(0);
            Iop.EvolTask(0);
            this.mEvc.InintEvc(this);
            MainVolume.GetInstance().ResetVolState();
            this.nErrorNum = 0;
            this.WakeUpDelayTime = 30;
            CarplayControl.SetTransDelaytime();
            CarplayControl.SetCarplayState();
            SendSceenSize(true);
            if (FtSet.IsIconExist(2) == 1) {
                CheckDiscState();
            }
            Evc.mSystemState = 5;
        }
        Mcu.mcutask();
        if (GetMcuState() == 2) {
            this.nErrorNum++;
            if (this.nErrorNum > 100 && Iop.GetErrorCode() == 1 && this.nError == 0) {
                this.nError = 1;
                MainAlterwin.GetInstance().Inint(getApplication(), getApplicationContext());
                MainAlterwin.GetInstance().ShowMessageWin("System optimization, about to restart");
                Log.w(TAG, "******QUICKBOOT ON  UART ERROR  !");
                DelayToReboot(TXZPoiSearchManager.DEFAULT_NEARBY_RADIUS);
            }
            if (this.nErrorNum > 300 && this.nErrorNum % 100 == 0) {
                Toast.makeText(getApplicationContext(), "***ErrorCode:002***", 0).show();
                tool.WriteLog("WakeUp error nErrorNum=" + this.nErrorNum);
            }
        } else if (this.WakeUpDelayTime > 0) {
            this.WakeUpDelayTime--;
            if (this.WakeUpDelayTime == 0) {
                Evc.mSystemState = 11;
                tool.WriteLog("WakeUpInint ok");
                Log.i(TAG, "WakeUpInint == ok " + GetMcuState());
                EnterLastMode();
                if (MainSet.GetInstance().IsSupprotRemotecontrol()) {
                    this.OldCamStateDelay = 60;
                } else {
                    this.OldCamStateDelay = 30;
                }
            }
        }
        Log.i(TAG, "WakeUpInint ==  " + GetMcuState());
    }

    /* access modifiers changed from: package-private */
    public void DelayToReboot(final int Time) {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep((long) Time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (FtSet.GetUsbHost() == 0) {
                    MainSet.GetInstance().SystemReboot();
                }
            }
        }.start();
    }

    public void WmInint() {
        if (Evc.mSystemState < 5) {
            Log.w(TAG, "######## WmInit = " + Evc.mSystemState + " !");
            if (!BackcarService.getInstance().bIninOK) {
                StringBuilder sb = new StringBuilder("######## WmInit exit arm2= ");
                int i = this.nStep;
                this.nStep = i + 1;
                Log.w(TAG, sb.append(i).append(" !").toString());
                return;
            } else if (!TsFile.fileIsExists("/mnt/sdcard/")) {
                StringBuilder sb2 = new StringBuilder("######## WmInit exit FILE SYSTEM= ");
                int i2 = this.nStep;
                this.nStep = i2 + 1;
                Log.w(TAG, sb2.append(i2).append(" !").toString());
                return;
            } else {
                TsSysinfo.GetInstance().GetInfo();
                this.nFsok = MainSet.GetInstance().FtSetInint();
                BackcarService.getInstance().InintParat();
                tool.WriteLog("WmInit");
                SendSceenSize(false);
                WriteProductFile();
                InintParat();
                SetMemInfo();
                nMcuRet = Mcu.mcutask();
                Log.w(TAG, "Mcu.mcutask() = 11");
                MainSet.GetInstance().CheckTestMode();
                Log.w(TAG, "CheckTestMode");
                if (!this.mMainSet.IsTestMode()) {
                    switch (this.nFsok) {
                        case 0:
                            FtSet.Save(1);
                            break;
                    }
                } else {
                    this.nFsok = 1;
                }
                Log.w(TAG, "mCanInterface.CanInit start");
                if (mCanInterface != null) {
                    mCanInterface.CanInit(getApplicationContext());
                    this.bCaninit = true;
                }
                Log.w(TAG, "mCanInterface.CanInit end");
                Mcu.mcunext();
                Evc.mSystemState = 5;
                TsLogger.GetInstance().Init(getApplicationContext());
                if (TsFile.fileIsExists(MainSet.TS_LOG_FILE)) {
                    TsLogger.GetInstance().CatlogToSd(MainSet.TS_LOG_FILE, 99);
                }
            }
        }
        if (nMcuRet != 1) {
            nMcuRet = Mcu.mcutask();
            if (nMcuRet == 0) {
                this.nErrorNum++;
                if (this.nErrorNum > 100 && Iop.GetErrorCode() == 1 && this.nError == 0) {
                    this.nError = 1;
                    Log.w(TAG, "******ACC ON  UART ERROR  !");
                    MainAlterwin.GetInstance().Inint(getApplication(), getApplicationContext());
                    MainAlterwin.GetInstance().ShowMessageWin("System optimization, about to restart");
                    DelayToReboot(TXZPoiSearchManager.DEFAULT_NEARBY_RADIUS);
                }
                if (this.nErrorNum > 600 && this.nErrorNum % 100 == 0) {
                    Toast.makeText(getApplicationContext(), "***ErrorCode:001***", 0).show();
                }
            }
            Log.i(TAG, "nMcuRet = 0==" + this.nErrorNum);
        } else if (nMcuRet == 0) {
            Evc.mSystemState = 13;
            Log.w(TAG, "######## WmInitSyncErr = " + Evc.mSystemState + " !");
            Log.e(TAG, "******************WmInit error MCU SYSNC ERROR*****************");
        } else {
            switch (Evc.mSystemState) {
                case 5:
                    AdjustArmLocalTime();
                    byte[] mcuid = new byte[14];
                    Mcu.GetSerialId(mcuid);
                    MainSet.seiid = CanIF.byte2String(mcuid);
                    tool.WriteLog("sync ok");
                    WriteEasyConnectFile();
                    this.nBatFirst = Mcu.GetPowState() & 1;
                    MainSet.SendIntent(ACTION_MAINUI_ACCON, (String) null);
                    MainSet.GetInstance().StSetInint();
                    if (this.mMainSet.IsTestMode() && !MainSet.bKeyBroad) {
                        Iop.BD3702Disable();
                    }
                    MainSet.GetInstance().ResetMapPath();
                    Evc.mSystemState = 6;
                    Log.w(TAG, "wmint  5  !");
                    return;
                case 6:
                    InintDvpInterface();
                    BtExe.getBtInstance().Init();
                    if (!MainSet.GetInstance().IsTestMode() || MainSet.bKeyBroad) {
                        Radio.TuneTask(this.nBatFirst);
                    }
                    if (!getResources().getString(R.string.support_tw_tpms).equals("0")) {
                        this.mStTpms.SetTpmsType(1);
                    }
                    this.mStTpms.Inint(this.nBatFirst);
                    Iop.EvolTask(this.nBatFirst);
                    if (MainSet.GetInstance().IsPCBAVol()) {
                        Evc.bNaviToLow = true;
                    }
                    Log.w(TAG, "wmint 6  !");
                    Evc.mSystemState = 7;
                    return;
                case 7:
                    this.mEvc.InintEvc(this);
                    this.mEvc.SetEvcCallBack(this);
                    ScreenSet.GetInstance().Inint(this);
                    this.mDisplay.Inint();
                    MainVolume.nBklisOn = Mcu.BklisOn();
                    CstTv.GetInstance().Inint();
                    MainSet.GetInstance().Inint(this.nBatFirst);
                    Log.i(TAG, "MainSet ok");
                    tool.GetInstance().Inint(getApplicationContext());
                    Log.w(TAG, "wmint 7  !");
                    Evc.mSystemState = 8;
                    return;
                case 8:
                    this.mMainVolume.Inint(getApplication(), getApplicationContext());
                    Log.i(TAG, "mMainVolume ok");
                    MainLight.GetInstance().Inint(getApplication(), getApplicationContext());
                    Log.i(TAG, "MainLight ok");
                    MainAlterwin.GetInstance().Inint(getApplication(), getApplicationContext());
                    Log.i(TAG, "MainAlterwin ok");
                    this.mKeyTouch.Inint(getApplicationContext());
                    Log.i(TAG, "mKeyTouch ok");
                    UpLoadTheLocation();
                    Log.i(TAG, "UpLoadTheLocation ok");
                    Log.i(TAG, "AuthServer ok");
                    AmapAuto.GetInstance().Inint(getApplicationContext());
                    AmapAuto.GetInstance();
                    AmapAuto.SendAccStateToSuding(1);
                    Log.i(TAG, "AmapAuto ok");
                    MainSet.GetInstance().bCheckFist();
                    if (MainSet.GetInstance().bFirstStart()) {
                        UpdateCheckTIme = 0;
                    }
                    Log.i(TAG, "bCheckFist ok");
                    Log.w(TAG, "wmint 8  !");
                    Evc.mSystemState = 9;
                    return;
                case 9:
                    MainSet.GetInstance().bindAuthorService();
                    if (FtSet.IsIconExist(2) == 1) {
                        CheckDiscState();
                    }
                    Log.i(TAG, "InintVolBar ok");
                    Log.i(TAG, "******************WmInit*****************");
                    if (FtSet.IsIconExist(112) == 1) {
                        connectIPodService();
                    }
                    if (getResources().getIdentifier("radio_no_preview", SdkConstants.TAG_STRING, getPackageName()) != 0) {
                        Radio.TuneMset(100);
                    }
                    if (!MainSet.GetInstance().IsMathToMcu() && AuthServer.GetInstance().GetIDType() != 0) {
                        MainAlterwin.GetInstance().ShowUnRegWin(1);
                    }
                    TxzReg.GetInstance().SetContext(getApplicationContext());
                    if (BackcarService.getInstance().bIsAvmFandR()) {
                        TsDvrService.getInstance().Init(getApplicationContext());
                    }
                    MainSet.GetInstance().StartCarplayService();
                    tool.WriteLog("wmint ok");
                    if (!this.mMainSet.IsTestMode() && this.nFsok == 1 && AuthServer.GetInstance().IsAuthOk()) {
                        Log.i(TAG, "mEvc.Evol.workmode = " + Iop.GetWorkMode());
                        EnterLastMode();
                    } else if (this.mMainSet.IsTestMode()) {
                        if (MainSet.Testmode.Support_360View == 1) {
                            AVMChaneleA = 0;
                            AVMChaneleB = 0;
                            AVMChaneleC = 0;
                            AVMChaneleD = 0;
                            BackcarService.getInstance().StartAvm(0);
                            new Thread() {
                                public void run() {
                                    try {
                                        sleep(2000);
                                    } catch (InterruptedException e1) {
                                        e1.printStackTrace();
                                    }
                                    WinShow.show("com.ts.MainUI", "com.ts.main.auth.FactoryMainActivity");
                                }
                            }.start();
                        } else {
                            WinShow.show("com.ts.MainUI", "com.ts.main.auth.FactoryMainActivity");
                        }
                    } else if (!AuthServer.GetInstance().IsAuthOk()) {
                        WinShow.GotoWin(16, 0);
                    }
                    if (this.nBatFirst == 1) {
                        Evc.mSystemState = 10;
                        Log.w(TAG, "######## WmInitSyncB+ =  !");
                    } else {
                        Evc.mSystemState = 11;
                        Log.w(TAG, "######## WmInitSyncAcc =  !");
                    }
                    if (T3WeakJoinUtils.bIsT3WeakPrj) {
                        T3WeakJoinUtils.startT3IVIApp(this);
                    }
                    if (MainSet.GetInstance().IsDiDi125()) {
                        StSet.SetBLIllSwitch(1);
                        MainSet.GetInstance().SyncBrightness();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        if (MainVolume.GetInstance().mtrackforbiden != null) {
            MainVolume.GetInstance().mtrackforbiden.setText(R.string.video_state_forbiden);
        }
        super.onConfigurationChanged(newConfig);
    }

    public void onDestroy() {
        Log.w(TAG, "######## Service Destroy !");
        super.onDestroy();
    }

    /* access modifiers changed from: package-private */
    public void DiscStop(String Path) {
        if (FtSet.IsIconExist(2) == 1 && Path.contains("cdfs") && Iop.GetWorkMode() != 2) {
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Evc.GetInstance().DiskStop();
                }
            }.start();
        }
    }

    public void onCreate() {
        Log.w(TAG, "######## onCreate = " + Evc.mSystemState + " !");
        mMainUI = this;
        tool.WriteLog("$$$$----MainUI onCreate--$$$$");
        MainSet.GetInstance().SetFlyMode(false);
        BackcarService.getInstance().InintCamera(getApplicationContext());
        MainTask.GetInstance().SetTaskCallBack(this);
        ((StorageManager) getSystemService("storage")).registerListener(this.mStorageListener);
        AuthServer.GetInstance().UpLoadTheLocation(getApplicationContext());
        IntentFilter filter = new IntentFilter(BROADCAST_BT_CHECKSTATUS);
        filter.addAction(BROADCAST_BT_A2DP_ACQUIRE);
        filter.addAction(BROADCAST_DRIVEMODE_CHECKSTATUS);
        filter.addAction(BROADCAST_BT_A2DP_RELEASE);
        filter.addAction(BT_A2DP_ACQUIRE);
        filter.addAction(BROADCAST_NET_CHANGE);
        filter.addAction(NET_TIME_CHANGE);
        filter.addAction(BROADCAST_LANCHER_FUNC_ACCELERATION);
        filter.addAction(BROADCAST_LANCHER_FUNC_MUTE);
        filter.addAction(BROADCAST_LANCHER_FUNC_EQ);
        filter.addAction(BROADCAST_LANCHER_FUNC_VOLUME);
        filter.addAction(BROADCAST_LANCHER_FUNC_BRIGHTNESS);
        filter.addAction(BROADCAST_LANCHER_FUNC_BLUETOOTH);
        filter.addAction(BROADCAST_LANCHER_FUNC_VOLUMEADD);
        filter.addAction(BROADCAST_LANCHER_FUNC_VOLUMEDEC);
        filter.addAction(BROADCAST_LANCHER_FUNC_SCREENOFF);
        filter.addAction(BROADCAST_LANCHER_FUNC_NOWMODE);
        filter.addAction("forfan.intent.action.set_productid");
        filter.addAction("forfan.intent.action.CLOSEMEDIA");
        filter.addAction(BROADCAST_MEDIA_LISTUPDATE);
        filter.addAction("ActivityWarm_Closed");
        filter.addAction(TXZ_GET_WWATHER_DATA);
        filter.addAction(ACTION_SHOW_USB_DISPLAY);
        filter.addAction(BROADCAST_GLSX_VOICE);
        filter.addAction(ACTION_AVM_SIGNAL_STATUS);
        filter.addAction(ACTION_REMOTE_WAKEUP);
        filter.addAction(ACTION_REMOTE_SLEEP);
        filter.addAction("autochips.intent.action.QB_POWERON");
        filter.addAction("autochips.intent.action.QB_POWEROFF");
        filter.addAction(ACTION_PREQB_OFF);
        filter.addAction(ACTION_AVM_RESET_ALL);
        if (getResources().getIdentifier("support_t3_weak", SdkConstants.TAG_STRING, getPackageName()) > 0) {
            T3WeakJoinUtils.bIsT3WeakPrj = true;
            T3WeakJoinUtils.init(this);
        }
        filter.addCategory("android.intent.category.DEFAULT");
        this.mEasyConnextReceiver = new easyConnectRecevie();
        registerReceiver(this.mEasyConnextReceiver, filter);
        Log.w(TAG, "######## onCreate registerReceiver !");
        BackCarSound.GetInstance().Inint(this);
        Log.w(TAG, "######## onCreate BackCarSound !");
        Log.w(TAG, "######## onCreate end=  !");
        super.onCreate();
    }

    private void startHideActivity() {
        Intent intent = new Intent(this, HideActivity.class);
        intent.addFlags(268435456);
        startActivity(intent);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Deprecated
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    public void DealWorkMode(int oldMode, int newWorkmode) {
        if (this.nOldWorkMode != Iop.GetWorkMode()) {
            Log.i(TAG, "Dvd  workmode nOldWorkMode==" + this.nOldWorkMode);
            Log.i(TAG, "Dvd  workmode mEvc.Evol.workmode==" + Iop.GetWorkMode());
            WinShow.SetWorkMode(newWorkmode);
            switch (this.nOldWorkMode) {
                case 2:
                case 3:
                case 4:
                case 15:
                    if (this.mTsPlayerService != null) {
                        try {
                            this.mTsPlayerService.enterMedia(0);
                            break;
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                    break;
                case 5:
                    BtExe.getBtInstance().musicPause();
                    break;
                case 6:
                    BackcarService.getInstance().HideRearView(false);
                    this.nCmmbPoweroff = 10;
                    break;
                case 9:
                    BackcarService.getInstance().HideRearView(false);
                    break;
            }
            if (this.antennaPower == -1) {
                if (getResources().getIdentifier("antenna_power", SdkConstants.TAG_STRING, getPackageName()) == 0) {
                    this.antennaPower = 0;
                } else {
                    this.antennaPower = 1;
                }
            }
            if (this.antennaPower > 0) {
                if (oldMode == 1) {
                    FtSet.SetExAmp(16);
                } else if (newWorkmode == 1) {
                    FtSet.SetExAmp(17);
                }
            }
            this.nOldWorkMode = Iop.GetWorkMode();
        }
    }

    public static void unregisterBtCallKey(BtFunc.DealBtCallKey mDealBtCall) {
        mDealBtCallKey = null;
    }

    public static void registerBtCallKey(BtFunc.DealBtCallKey mDealBtCall) {
        mDealBtCallKey = mDealBtCall;
    }

    public ITsPlayerService getITsPlayerService() {
        return this.mTsPlayerService;
    }

    public static void registerUserKeyHandler(IUserKeyHandler hanlder) {
        mUserKeyHandler = hanlder;
    }

    public static void unregisterUserKeyHandler() {
        mUserKeyHandler = null;
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
        if (MainSet.GetInstance().IsSupprotLossTranscient()) {
            Log.i(TAG, "AudioFocusTRANSIENT=" + workmode + "    bGet==" + bGet);
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
            } else if (CheckMode(this.nTempWork) == 3 && BtExe.getBtInstance().isBtMusicPlaying()) {
                BtExe.getBtInstance().musicPause();
                this.nPaunseFlag = 1;
            }
        }
    }
}
