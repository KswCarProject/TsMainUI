package com.ts.main.txz;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.util.Log;
import android.widget.Toast;
import com.android.SdkConstants;
import com.suding.mirror.sdk.MirrorAdapterManager;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.MainUI.StTpms;
import com.ts.bt.BtExe;
import com.ts.can.CanIF;
import com.ts.main.common.CarplayControl;
import com.ts.main.common.KeyTouch;
import com.ts.main.common.MainLight;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.ts.main.common.MainVerSion;
import com.ts.main.common.ShellUtils;
import com.ts.main.common.WinShow;
import com.ts.main.common.tool;
import com.ts.main.txz.AudioFocusManager;
import com.txznet.sdk.TXZPoiSearchManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;

public class AmapAuto {
    public static final String ACTION_CHANGED_LOG_STATE = "com.ts.car.client.ACTION_CHANGED_LOG_STATE";
    private static final String ACTION_LIFE_CYCLE_CHANGE = "com.android.activity.lifecyclechange";
    private static final String ACTION_NAVIGATION_AUDIO = "android.media.GIS_AUDIO_STATUS_ACTION";
    private static final String APK_DOWNLOAD_SUCCESS = "com.ts.main.DownloadSuccessACtion";
    public static final String ARRIVE_STATUS = "ARRIVE_STATUS";
    public static final int AUTO_BOX_MODE_ANDROIDAUTO_WORK_START = 1;
    public static final int AUTO_BOX_MODE_CARPLAY_WORK_START = 5;
    public static final String AUTO_BOX_MODE_CHANGE_DATA = "cn.manstep.phonemirrorBox.AUTO_BOX_MODE_CHANGE_DATA";
    public static final String AUTO_BOX_MODE_CHANGE_EVT = "cn.manstep.phonemirrorBox.AUTO_BOX_MODE_CHANGE_EVT";
    public static final int AUTO_BOX_MODE_PHONECALL_START = 3;
    public static final int AUTO_BOX_MODE_PHONECALL_STOP = 4;
    public static final int AUTO_BOX_MODE_WORK_STOP = 2;
    public static final String AUTO_TS_MODE_CHANGE_EVT = "com.ts.carplayapp.AUTO_BOX_MODE_CHANGE_EVT";
    public static final String BRAODCAST_EASY_NETLINK_STATUS = "net.easyconn.usb.netlink.status";
    public static final String BRAODCAST_MEDIA_CHANGE = "com.ts.dvdplayer.MEDIA_CHANGE";
    public static final String BRAODCAST_MUSIC_METACHAGED = "com.android.music.playstatechanged";
    public static final String BROADCAST_AMAP_REV = "AUTONAVI_STANDARD_BROADCAST_RECV";
    public static final String BROADCAST_AMAP_SEND = "AUTONAVI_STANDARD_BROADCAST_SEND";
    public static final String BROADCAST_LOCAT_UPDATE_COM = "SYSTEM_VERSION_UPDATE_COMPLETED";
    public static final String BROADCAST_REQUEST_CAR_INFO = "broadcast_request_carinfo";
    public static final String BROADCAST_REQUEST_INFO_STATE = "broadcast_local_request_state";
    public static final String BROADCAST_REQUEST_TOP_ACTIVITY = "broadcast_request_top_activity";
    public static final String BROADCAST_REQUEST_TPMS_STATE = "broadcast_request_tpms_state";
    public static final String BROADCAST_SCREEN_SET = "android.intent.action.SET_MYBRIGHTNESS";
    public static final String BROADCAST_SEND_CAR_INFO = "broadcast_send_carinfo";
    public static final String BROADCAST_SEND_INFO_STATE = "broadcast_local_send_state";
    public static final String BROADCAST_SEND_TOP_ACTIVITY = "broadcast_send_top_activity";
    public static final String BROADCAST_SEND_TPMS_STATE = "broadcast_send_tpms_state";
    public static final String BROADCAST_SUDING_SPEEDPLAY = "com.suding.speedplay";
    public static final String BROADCAST_TXZ_WIN_CREATE = "com.txznet.txz.record.show";
    public static final String BROADCAST_TXZ_WIN_DISMIS = "com.txznet.txz.record.dismiss";
    public static final String BROADCAST_WWLINK_SHOW = "WM_AMLINK_STS_SHOW";
    public static final String CAMERA_DIST = "CAMERA_DIST";
    public static final String CAMERA_INDEX = "CAMERA_INDEX";
    public static final String CAMERA_SPEED = "CAMERA_SPEED";
    public static final String CAMERA_TYPE = "CAMERA_TYPE";
    public static final String CAR_DIRECTION = "CAR_DIRECTION";
    public static final String CAR_LATITUDE = "CAR_LATITUDE";
    public static final String CAR_LONGITUDE = "CAR_LONGITUDE";
    public static final int CAR_PLAY_DISCONNECTED = 6;
    public static final String CUR_POINT_NUM = "CUR_POINT_NUM";
    public static final String CUR_ROAD_NAME = "CUR_ROAD_NAME";
    public static final String CUR_SEG_NUM = "CUR_SEG_NUM";
    public static final String CUR_SPEED = "CUR_SPEED";
    public static final String ICON = "ICON";
    public static final String IPOD_WORKMODE_EXIT = "com.ts.ipodplayer.EXIT_IPOD_WORKMODE";
    public static final String KEY_OPEN_TSLOG = "open";
    public static final String LIMITED_SPEED = "LIMITED_SPEED";
    public static final String NEXT_ROAD_NAME = "NEXT_ROAD_NAME";
    public static final String ROAD_TYPE = "ROAD_TYPE";
    public static final String ROUND_ABOUT_NUM = "ROUNG_ABOUT_NUM";
    public static final String ROUTE_ALL_DIS = "ROUTE_ALL_DIS";
    public static final String ROUTE_ALL_TIME = "ROUTE_ALL_TIME";
    public static final String ROUTE_REMAIN_DIS = "ROUTE_REMAIN_DIS";
    public static final String ROUTE_REMAIN_TIME = "ROUTE_REMAIN_TIME";
    public static final String SAPA_DIST = "SAPA_DIST";
    public static final String SAPA_NAME = "SAPA_NAME";
    public static final String SAPA_NUM = "SAPA_NUM";
    public static final String SAPA_TYPE = "SAPA_TYPE";
    public static final String SEG_REMAIN_DIS = "SEG_REMAIN_DIS";
    public static final String SEG_REMAIN_TIME = "SEG_REMAIN_TIME";
    private static final String TAG = "AmapAuto";
    public static final String TRAFFIC_LIGHT_NUM = "TRAFFIC_LIGHT_NUM";
    public static final String TYPE = "TYPE";
    public static final String UPDATE_FILE_DOWNLOAD_SUCCESS = "com.forfan.systemupgrade.UNZIP_SUCCESSED";
    public static boolean bNaviState = false;
    static AmapAuto m_Amap = null;
    /* access modifiers changed from: private */
    public static Context myContext = null;
    String[] ForceSendPlayCmd = {"com.google.android.youtube", "com.ex.dabplayer.pad", "com.spotify.music", "cn.kuwo.kwmusiccar"};
    String LastStr = TXZResourceManager.STYLE_DEFAULT;
    CanIF.NaviInfo MyNaviInfo = new CanIF.NaviInfo();
    /* access modifiers changed from: private */
    public String OnPauseStr = " ";
    /* access modifiers changed from: private */
    public String ResumeStr = " ";
    AudioFocusManager audioFocusManager = new AudioFocusManager();
    public boolean bChangeDpi = false;
    boolean bIsCarPlayPhone = false;
    boolean bSud_auto = false;
    AmapConnectRecevie mAmapConnectRecevie;
    BroadcastReceiver mPackageChangeReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
                String packageName = intent.getData().getSchemeSpecificPart();
                if (packageName.equals(MainSet.TS_CLIENT_PACKAGENAME)) {
                    MainSet.GetInstance().StartCarClientService();
                } else if (packageName.equals(MainSet.TS_T3_PACKAGENAME)) {
                    MainSet.GetInstance().StartT3Service();
                }
                Log.i(AmapAuto.TAG, "ADDED===" + packageName);
            } else if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                Log.i(AmapAuto.TAG, "REMOVED===" + intent.getData().getSchemeSpecificPart());
            } else if (intent.getAction().equals("android.intent.action.PACKAGE_REPLACED")) {
                Log.i(AmapAuto.TAG, "REPLACED===" + intent.getData().getSchemeSpecificPart());
            }
        }
    };
    char[] mcuVer = new char[32];
    public int nGoogleVoiceDelay = 0;
    public int nPlayKeyNumDelay = 0;
    public int nSetVolumeDelay = 0;

    public void Task() {
        if (this.nPlayKeyNumDelay > 0) {
            Log.i(TAG, "nPlayKeyNumDelay = " + this.nPlayKeyNumDelay);
            this.nPlayKeyNumDelay--;
            if (this.nPlayKeyNumDelay == 0) {
                KeyTouch.GetInstance().sendKeyClick(126);
            }
        }
        if (this.nSetVolumeDelay > 0) {
            this.nSetVolumeDelay--;
            if (this.nSetVolumeDelay == 0) {
                Evc.GetInstance().SetMusicVolume(Iop.GetVolume(0));
            }
        }
        if (this.nGoogleVoiceDelay > 0) {
            this.nGoogleVoiceDelay--;
            if (this.nGoogleVoiceDelay == 0 && WinShow.getTopActivityName().equals("com.google.android.apps.gsa.queryentry.QueryEntryActivity")) {
                Evc.GetInstance().evol_popmute_set(Iop.GetWorkMode());
            }
        }
    }

    public void GetNaviInfo() {
        CanIF.mNaviInfo.Avalid = 0;
        CanIF.mNaviInfo = this.MyNaviInfo;
        CanIF.mNaviInfo.Avalid = 1;
    }

    public void SetGpsInfo(int nAngle, int nSpeed) {
        if (!bNaviState) {
            this.MyNaviInfo.Avalid = 0;
            this.MyNaviInfo.Sta = 0;
            this.MyNaviInfo.GpsAngle = nAngle;
            this.MyNaviInfo.Speed = nSpeed;
            this.MyNaviInfo.MsgC = 0;
            this.MyNaviInfo.DestDisDw = 0;
            this.MyNaviInfo.MsgDis = 0;
            this.MyNaviInfo.DestDis = 0;
            this.MyNaviInfo.RoadDirInfo = 0;
            return;
        }
        this.MyNaviInfo.GpsAngle = nAngle;
        this.MyNaviInfo.Speed = nSpeed;
    }

    public class AmapConnectRecevie extends BroadcastReceiver {
        String[] ForceToWorkmodeNone = {"com.qiyi.video.pad", "com.zbx.ct.tvzhibo", "mbinc12.mb32", "com.ximalaya.ting.android.car", "cn.kuwo.kwmusiccar", "com.mxtech.videoplayer.pro", "com.tencent.qqlive", "cn.cntv", "cn.cntvhd", "com.kugou.android", "com.pbi.liveitv", "tv.fourgtv.fourgtv", "com.vooco.yashi", "com.skysoft.kkbox.android", "com.google.android.youtube", "com.ex.dabplayer.pad", "com.spotify.music", "com.kugou.playerHD", "com.pplive.androidpad", "com.tencent.qqmusiccar", AmapAuto.BROADCAST_SUDING_SPEEDPLAY, MainUI.TS_CARPLAY_PNAME};

        public AmapConnectRecevie() {
        }

        public void onReceive(Context ctx, Intent intent) {
            String action = intent.getAction();
            if (action != null) {
                if (AmapAuto.BROADCAST_AMAP_SEND.equalsIgnoreCase(action)) {
                    int KeyType = intent.getExtras().getInt("KEY_TYPE");
                    Log.i(AmapAuto.TAG, "KeyType = " + KeyType);
                    if (KeyType == 10001) {
                        AmapAuto.this.MyNaviInfo.Avalid = 0;
                        if (AmapAuto.bNaviState) {
                            AmapAuto.this.MyNaviInfo.Sta = 1;
                            AmapAuto.this.MyNaviInfo.MsgDis = intent.getExtras().getInt(AmapAuto.SEG_REMAIN_DIS) * 10;
                            if (AmapAuto.this.MyNaviInfo.MsgDis > 5000) {
                                AmapAuto.this.MyNaviInfo.MsgDis /= TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT;
                                AmapAuto.this.MyNaviInfo.MsgC = 1;
                            } else if (AmapAuto.this.MyNaviInfo.MsgDis <= 3000 || AmapAuto.this.MyNaviInfo.MsgDis >= 5000) {
                                AmapAuto.this.MyNaviInfo.MsgC = 5;
                                AmapAuto.this.MyNaviInfo.MsgDis = (intent.getExtras().getInt(AmapAuto.SEG_REMAIN_DIS) * 255) / 300;
                            } else {
                                AmapAuto.this.MyNaviInfo.MsgC = 0;
                            }
                            AmapAuto.this.MyNaviInfo.DestDis = intent.getExtras().getInt(AmapAuto.ROUTE_REMAIN_DIS) * 10;
                            if (AmapAuto.this.MyNaviInfo.DestDis > 5000) {
                                AmapAuto.this.MyNaviInfo.DestDisDw = 1;
                                AmapAuto.this.MyNaviInfo.DestDis /= 1000;
                            } else {
                                AmapAuto.this.MyNaviInfo.DestDisDw = 0;
                            }
                            AmapAuto.this.MyNaviInfo.RoadDirInfo = 0;
                            AmapAuto.this.MyNaviInfo.TurnIcon = intent.getExtras().getInt(AmapAuto.ICON);
                            switch (intent.getExtras().getInt(AmapAuto.ICON)) {
                                case 2:
                                    AmapAuto.this.MyNaviInfo.Para = 3;
                                    break;
                                case 3:
                                    AmapAuto.this.MyNaviInfo.Para = 7;
                                    break;
                                case 4:
                                    AmapAuto.this.MyNaviInfo.Para = 2;
                                    break;
                                case 5:
                                    AmapAuto.this.MyNaviInfo.Para = 8;
                                    break;
                                case 6:
                                    AmapAuto.this.MyNaviInfo.Para = 4;
                                    break;
                                case 7:
                                    AmapAuto.this.MyNaviInfo.Para = 6;
                                    break;
                                case 8:
                                    AmapAuto.this.MyNaviInfo.Para = 5;
                                    break;
                                default:
                                    AmapAuto.this.MyNaviInfo.Para = 1;
                                    break;
                            }
                            AmapAuto.this.MyNaviInfo.LastTime = intent.getExtras().getInt(AmapAuto.ROUTE_REMAIN_TIME);
                            AmapAuto.this.MyNaviInfo.DestTime = intent.getExtras().getInt(AmapAuto.ROUTE_ALL_TIME);
                            AmapAuto.this.MyNaviInfo.sRoadName = intent.getExtras().getString(AmapAuto.CUR_ROAD_NAME);
                            AmapAuto.this.MyNaviInfo.sNextRoadName = intent.getExtras().getString(AmapAuto.NEXT_ROAD_NAME);
                            AmapAuto.this.MyNaviInfo.DestDisM = intent.getExtras().getInt(AmapAuto.ROUTE_REMAIN_DIS);
                            AmapAuto.this.MyNaviInfo.MsgDisM = intent.getExtras().getInt(AmapAuto.SEG_REMAIN_DIS);
                        }
                        String curRoad = intent.getExtras().getString(AmapAuto.CUR_ROAD_NAME);
                        String NextRoad = intent.getExtras().getString(AmapAuto.NEXT_ROAD_NAME);
                        Log.i(AmapAuto.TAG, "curRoad = " + curRoad);
                        Log.i(AmapAuto.TAG, "NextRoad = " + NextRoad);
                        Log.i(AmapAuto.TAG, "CanIF.mNaviInfo.MsgDis = " + AmapAuto.this.MyNaviInfo.MsgDis);
                        Log.i(AmapAuto.TAG, "CanIF.mNaviInfo.DestDis = " + AmapAuto.this.MyNaviInfo.DestDis);
                    } else if (KeyType == 10019) {
                        int nEXTRA_STATE = intent.getExtras().getInt("EXTRA_STATE");
                        Log.i(AmapAuto.TAG, "EXTRA_STATE = " + nEXTRA_STATE);
                        switch (nEXTRA_STATE) {
                            case 2:
                                AmapAuto.bNaviState = false;
                                AmapAuto.this.MyNaviInfo.Sta = 0;
                                return;
                            case 8:
                            case 10:
                                AmapAuto.bNaviState = true;
                                return;
                            case 9:
                            case 12:
                                AmapAuto.bNaviState = false;
                                AmapAuto.this.MyNaviInfo.Sta = 0;
                                return;
                            case 13:
                                Evc.GetInstance().evol_navi_set_force(1);
                                return;
                            case 14:
                                Evc.GetInstance().evol_navi_set_force(0);
                                return;
                            default:
                                return;
                        }
                    } else if (KeyType == 10016) {
                        AmapAuto.SetHeadLight(Mcu.GetIll() == 0 ? 1 : 0);
                    }
                } else if (AmapAuto.BROADCAST_REQUEST_TOP_ACTIVITY.equalsIgnoreCase(action)) {
                    Log.i(AmapAuto.TAG, "BROADCAST_TOP_ACTIVITY = ");
                    Intent intent1 = new Intent();
                    intent1.setAction(AmapAuto.BROADCAST_SEND_TOP_ACTIVITY);
                    intent1.putExtra("Topname", WinShow.getTopActivityName());
                    AmapAuto.myContext.sendBroadcast(intent1);
                } else if (AmapAuto.BROADCAST_REQUEST_TPMS_STATE.equalsIgnoreCase(action)) {
                    Log.i(AmapAuto.TAG, "BROADCAST_REQUEST_TPMS_STATE = ");
                    AmapAuto.this.UpdateTpmsInfo();
                } else if (AmapAuto.BROADCAST_LOCAT_UPDATE_COM.equalsIgnoreCase(action)) {
                    if (MainUI.GetInstance().GetMcuState() != 2) {
                        Mcu.SendXKey(19);
                    } else {
                        Mcu.SendXKey(22);
                    }
                } else if (AmapAuto.BROADCAST_REQUEST_INFO_STATE.equalsIgnoreCase(action)) {
                    int Gsensor = intent.getExtras().getInt("SET_GSENSOR");
                    if (Gsensor > 0 && Gsensor != FtSet.GetGsensor()) {
                        FtSet.SetGsensor(Gsensor);
                    }
                    String message = intent.getExtras().getString("GET_MESSAGE");
                    Log.i(AmapAuto.TAG, "message= " + message);
                    Log.i(AmapAuto.TAG, "message2= " + message);
                    Intent intent2 = new Intent();
                    if (message != null) {
                        intent2.setAction(AmapAuto.BROADCAST_SEND_INFO_STATE);
                        if (message.equalsIgnoreCase("HMI_INFO")) {
                            intent2.putExtra("HMI_INFO", String.valueOf(AmapAuto.myContext.getResources().getString(R.string.custom_num_show)) + MainVerSion.HMIVER);
                        } else if (message.equalsIgnoreCase("MCU_INFO")) {
                            Mcu.GetMcuVer(AmapAuto.this.mcuVer);
                            intent2.putExtra("MCU_INFO", String.valueOf(AmapAuto.this.mcuVer).substring(0, 12));
                            Log.i(AmapAuto.TAG, "mcuVer= " + String.valueOf(AmapAuto.this.mcuVer).substring(0, 12));
                        } else if (message.equalsIgnoreCase("MEDIA_INFO")) {
                            intent2.putExtra("MEDIA_INFO", MainVerSion.MMP_VERSION);
                        } else if (message.equalsIgnoreCase("BT_INFO")) {
                            intent2.putExtra("BT_INFO", BtExe.getBtInstance().getVersion());
                        } else if (message.equalsIgnoreCase("GET_GSENSOR")) {
                            intent2.putExtra("GET_GSENSOR", FtSet.GetGsensor());
                        }
                    }
                    AmapAuto.myContext.sendBroadcast(intent2);
                } else if (AmapAuto.BROADCAST_REQUEST_CAR_INFO.equalsIgnoreCase(action)) {
                    AmapAuto.this.SendCarInfordata();
                } else if (AmapAuto.BROADCAST_TXZ_WIN_CREATE.equalsIgnoreCase(action)) {
                    Log.i(AmapAuto.TAG, "BROADCAST_TXZ_WIN_CREATE= ");
                    TxzReg.SetWinShow(true);
                    Intent intent3 = new Intent("com.android.InputMethodService.showhide");
                    intent3.putExtra("showHide", "show");
                    AmapAuto.myContext.sendBroadcast(intent3);
                } else if (AmapAuto.BROADCAST_TXZ_WIN_DISMIS.equalsIgnoreCase(action)) {
                    Log.i(AmapAuto.TAG, "BROADCAST_TXZ_WIN_DISMIS= ");
                    TxzReg.SetWinShow(false);
                    Intent intent4 = new Intent("com.android.InputMethodService.showhide");
                    intent4.putExtra("showHide", "hide");
                    AmapAuto.myContext.sendBroadcast(intent4);
                } else if (AmapAuto.BRAODCAST_MUSIC_METACHAGED.equalsIgnoreCase(action)) {
                    Log.i(AmapAuto.TAG, "BRAODCAST_MUSIC_METACHAGED");
                    String artist = intent.getStringExtra("artist");
                    Log.d(AmapAuto.TAG, String.valueOf(artist) + ":" + intent.getStringExtra("album") + ":" + intent.getStringExtra("track"));
                } else if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(action)) {
                    Log.i(AmapAuto.TAG, "USB DEV BroadcastReceiver, device attached");
                    UsbDevice device = (UsbDevice) intent.getParcelableExtra("device");
                    int vendorId = device.getVendorId();
                    int productId = device.getProductId();
                    if (vendorId == 1276 && productId == 369) {
                        MainUI.GetInstance().SetCheckDisc(true);
                        if (MainUI.GetInstance().GetMcuState() == 3) {
                            Mcu.SendXKey(17);
                        }
                    }
                    Log.i(AmapAuto.TAG, "USB DEV BroadcastReceiver vendorId " + vendorId + ", productId " + productId);
                } else if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(action)) {
                    Log.i(AmapAuto.TAG, "USB DEV BroadcastReceiver, device detech");
                    UsbDevice device2 = (UsbDevice) intent.getParcelableExtra("device");
                    int vendorId2 = device2.getVendorId();
                    int productId2 = device2.getProductId();
                    if (vendorId2 == 1276 && productId2 == 369) {
                        MainUI.GetInstance().SetCheckDisc(false);
                    }
                    Log.i(AmapAuto.TAG, "USB detechDEV BroadcastReceiver vendorId " + vendorId2 + ", productId " + productId2);
                } else if ("android.intent.action.LOCALE_CHANGED".equals(action)) {
                    MainSet.GetInstance().UpdateSysInput();
                    MainSet.GetInstance().ResetLaunguage();
                    new Thread() {
                        public void run() {
                            tool.GetInstance().DealSu("sync");
                        }
                    }.start();
                } else if (AmapAuto.BRAODCAST_MEDIA_CHANGE.equals(action)) {
                    Iop.PopMuteDelay(50);
                } else if (AmapAuto.AUTO_TS_MODE_CHANGE_EVT.equals(action)) {
                    switch (intent.getExtras().getInt(AmapAuto.AUTO_BOX_MODE_CHANGE_DATA)) {
                        case 1:
                            BtExe.getBtInstance().setAutoPause(false);
                            return;
                        case 2:
                            BtExe.getBtInstance().setAutoDisconnect(false);
                            Evc.GetInstance().evol_blue_set(0);
                            BtExe.getBtInstance().setAutoConnect(true);
                            CarplayControl.SetCallState(false);
                            CarplayControl.SetConnectState(false);
                            return;
                        case 3:
                            BtExe.getBtInstance().setAutoDisconnect(true);
                            if (BtExe.getBtInstance().isConnected()) {
                                BtExe.getBtInstance().disconnect();
                            }
                            BtExe.getBtInstance().setAutoConnect(false);
                            if (FtSet.IsIconExist(130) == 1) {
                                if (!WinShow.getTopPackName().equals(MainUI.TS_CARPLAY_PNAME)) {
                                    AmapAuto.this.bIsCarPlayPhone = true;
                                    MainUI.GetInstance();
                                    if (MainUI.IsCameraMode() == 0) {
                                        MainSet.GetInstance().openApplication(AmapAuto.myContext, MainUI.TS_CARPLAY_PNAME);
                                    }
                                }
                                Evc.GetInstance().evol_blue_set(1);
                                CarplayControl.SetCallState(true);
                                Log.i(AmapAuto.TAG, "AUTO_BOX_MODE_PHONECALL_START ");
                                return;
                            }
                            return;
                        case 4:
                            if (FtSet.IsIconExist(130) == 1) {
                                if (AmapAuto.this.bIsCarPlayPhone) {
                                    AmapAuto.this.bIsCarPlayPhone = false;
                                    Mcu.SetCkey(22);
                                }
                                Evc.GetInstance().evol_blue_set(0);
                                CarplayControl.SetCallState(false);
                                Log.i(AmapAuto.TAG, "AUTO_BOX_MODE_PHONECALL_STOP ");
                                return;
                            }
                            return;
                        case 5:
                            BtExe.getBtInstance().setAutoDisconnect(true);
                            if (BtExe.getBtInstance().isConnected()) {
                                BtExe.getBtInstance().disconnect();
                            }
                            BtExe.getBtInstance().setAutoConnect(false);
                            StringBuilder sb = new StringBuilder("AUTO_BOX_MODE_CARPLAY_WORK_START MainUI.GetInstance().IsCameraMode()==");
                            MainUI.GetInstance();
                            Log.i(AmapAuto.TAG, sb.append(MainUI.IsCameraMode()).toString());
                            if (FtSet.IsIconExist(130) == 1) {
                                if (CarplayControl.bTranse) {
                                    if (!WinShow.getTopPackName().equals(MainUI.TS_CARPLAY_PNAME)) {
                                        MainUI.GetInstance();
                                        if (MainUI.IsCameraMode() == 0) {
                                            MainSet.GetInstance().openApplication(AmapAuto.myContext, MainUI.TS_CARPLAY_PNAME);
                                        }
                                    }
                                    Log.i(AmapAuto.TAG, "AUTO_BOX_MODE_CARPLAY_WORK_START ");
                                }
                                CarplayControl.SetConnectState(true);
                                return;
                            }
                            return;
                        case 6:
                            CarplayControl.Reset(false);
                            CarplayControl.SetConnectState(false);
                            return;
                        default:
                            return;
                    }
                } else if (AmapAuto.AUTO_BOX_MODE_CHANGE_EVT.equals(action)) {
                    switch (intent.getExtras().getInt(AmapAuto.AUTO_BOX_MODE_CHANGE_DATA)) {
                        case 1:
                            BtExe.getBtInstance().setAutoPause(false);
                            return;
                        case 2:
                            BtExe.getBtInstance().setAutoDisconnect(false);
                            return;
                        case 3:
                            BtExe.getBtInstance().setAutoDisconnect(true);
                            if (BtExe.getBtInstance().isConnected()) {
                                BtExe.getBtInstance().disconnect();
                                return;
                            }
                            return;
                        case 5:
                            BtExe.getBtInstance().setAutoDisconnect(true);
                            if (BtExe.getBtInstance().isConnected()) {
                                BtExe.getBtInstance().disconnect();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                } else if (AmapAuto.BROADCAST_SCREEN_SET.equals(action)) {
                    Log.d(AmapAuto.TAG, "BROADCAST_SCREEN_SET");
                    if (MainSet.IsShowBrughtness()) {
                        MainLight.GetInstance().WinShow();
                    } else {
                        Mcu.BklTurn();
                    }
                } else if (AmapAuto.BROADCAST_WWLINK_SHOW.equals(action)) {
                    if (!BtExe.getBtInstance().isConnected() && AmapAuto.myContext != null) {
                        Toast.makeText(AmapAuto.myContext, AmapAuto.myContext.getResources().getString(R.string.bt_connect_first_msg), 0).show();
                    }
                    Evc.GetInstance().evol_workmode_set(5);
                } else if (AmapAuto.APK_DOWNLOAD_SUCCESS.equals(action)) {
                    String[] commands = {"pm install -r -d "};
                    commands[0] = String.valueOf(commands[0]) + "\"" + intent.getExtras().getString("filepath") + "/" + intent.getExtras().getString("filename") + "\"";
                    ShellUtils.execCommand(commands, true);
                } else if (AmapAuto.BROADCAST_SUDING_SPEEDPLAY.equals(action)) {
                    String TsStatus = intent.getStringExtra("status");
                    String TsPhMode = intent.getStringExtra("phoneMode");
                    if (TsStatus != null && TsPhMode != null) {
                        Log.d(AmapAuto.TAG, "BROADCAST_SUDING_SPEEDPLAY TsStatus=" + TsStatus);
                        Log.d(AmapAuto.TAG, "BROADCAST_SUDING_SPEEDPLAY TsPhMode=" + TsPhMode);
                        if (TsStatus.equals("CONNECTED") && TsPhMode.equals(MirrorAdapterManager.PHONE_MODE_AUTO_WIRED)) {
                            AmapAuto.this.bSud_auto = true;
                            BtExe.getBtInstance().setAutoPause(false);
                        } else if (TsStatus.equals("DISCONNECT") && TsPhMode.equals(MirrorAdapterManager.PHONE_MODE_AUTO_WIRED)) {
                            AmapAuto.this.bSud_auto = false;
                        }
                    }
                } else if ("com.android.activity.lifecyclechange".equals(action)) {
                    String lifecycle = intent.getStringExtra("lifecycle");
                    String packageName = intent.getStringExtra("packagename");
                    String className = intent.getStringExtra("classname");
                    Log.d(AmapAuto.TAG, "lifecycle change ==" + packageName + " --------> " + lifecycle);
                    if (lifecycle.equals("onPause")) {
                        AmapAuto.this.OnPauseStr = packageName;
                        for (int k = 0; k < AmapAuto.this.ForceSendPlayCmd.length; k++) {
                            if (AmapAuto.this.OnPauseStr.equalsIgnoreCase(AmapAuto.this.ForceSendPlayCmd[k])) {
                                AmapAuto.this.nPlayKeyNumDelay = 0;
                            }
                        }
                        if (AmapAuto.this.OnPauseStr.equalsIgnoreCase("tv.fourgtv.fourgtv")) {
                            Evc.GetInstance().SetMusicVolume(Iop.GetVolume(0));
                            AmapAuto.this.nSetVolumeDelay = 30;
                        } else if (AmapAuto.this.OnPauseStr.equalsIgnoreCase(MainUI.GOOGLE_SPEECH_PNAME)) {
                            Evc.GetInstance().evol_popmute_clr(Iop.GetWorkMode());
                            Evc.GetInstance().bGoogle = false;
                            AmapAuto.this.nGoogleVoiceDelay = 0;
                        }
                        if (MainSet.GetScreenType() != 3 && AmapAuto.this.OnPauseStr.equalsIgnoreCase("com.ts.dvdplayer")) {
                            Log.i(AmapAuto.TAG, " DISP_GAM_ARM className===" + className);
                            if (MainUI.IsCameraMode() != 0) {
                                return;
                            }
                            if (className.equals("com.ts.dvdplayer.USBActivity") || className.equals("com.ts.dvdplayer.dvd.DVDVideoActivity")) {
                                WinShow.SetVideoShowParat(0);
                            }
                        }
                    } else if (lifecycle.equals("onResume")) {
                        AmapAuto.this.ResumeStr = packageName;
                        Evc.GetInstance().WriteMem(AmapAuto.this.ResumeStr);
                        if (MainSet.GetInstance().Support_new_forbidden()) {
                            MainSet.GetInstance().CheckForbidden(AmapAuto.this.ResumeStr, className);
                        }
                        if (MainSet.GetScreenType() != 3 && packageName.equalsIgnoreCase("com.ts.dvdplayer") && (className.equals("com.ts.dvdplayer.USBActivity") || className.equals("com.ts.dvdplayer.dvd.DVDVideoActivity"))) {
                            WinShow.SetVideoShowParat(2);
                        }
                        if (AmapAuto.this.ResumeStr.equalsIgnoreCase("com.autochips.avmplayer")) {
                            MainSet.GetInstance().SetVideoChannel(0);
                        }
                        if (AmapAuto.this.ResumeStr.equalsIgnoreCase("net.easyconn")) {
                            Log.i(AmapAuto.TAG, "net.easyconn===false");
                            BtExe.getBtInstance().setAutoPause(false);
                            if (MainSet.GetInstance().Support_changeDpi() && !AmapAuto.this.bChangeDpi) {
                                AmapAuto.this.bChangeDpi = true;
                                tool.GetInstance().DealSu("wm density 200");
                            }
                        } else if (AmapAuto.this.ResumeStr.equalsIgnoreCase(AmapAuto.BROADCAST_SUDING_SPEEDPLAY)) {
                            BtExe.getBtInstance().setAutoPause(false);
                        } else {
                            if (!AmapAuto.this.bSud_auto) {
                                Log.i(AmapAuto.TAG, "net.easyconn===true");
                                BtExe.getBtInstance().setAutoPause(true);
                            }
                            if (MainSet.GetInstance().Support_changeDpi() && ((AmapAuto.this.ResumeStr.equalsIgnoreCase("com.android.launcher") || AmapAuto.this.ResumeStr.equalsIgnoreCase("com.android.launcher2") || AmapAuto.this.ResumeStr.equalsIgnoreCase("com.android.launcher3") || AmapAuto.this.ResumeStr.equalsIgnoreCase("com.ts.MainUI")) && AmapAuto.this.bChangeDpi)) {
                                AmapAuto.this.bChangeDpi = false;
                                tool.GetInstance().DealSu("wm density 240");
                            }
                        }
                        if (!AmapAuto.this.ResumeStr.equalsIgnoreCase(MainUI.TS_CARPLAY_PNAME)) {
                            AmapAuto.this.bIsCarPlayPhone = false;
                        }
                        for (int j = 0; j < this.ForceToWorkmodeNone.length; j++) {
                            if (packageName.equalsIgnoreCase(this.ForceToWorkmodeNone[j]) && (!AmapAuto.this.bIsCarPlayPhone || !packageName.equalsIgnoreCase(MainUI.TS_CARPLAY_PNAME))) {
                                if (!packageName.equalsIgnoreCase("com.tencent.qqlive")) {
                                    Evc.GetInstance().evol_workmode_set(0);
                                } else if (Iop.GetWorkMode() != 0) {
                                    Evc.GetInstance().evol_workmode_set(0);
                                } else if (!(AmapAuto.myContext == null || AmapAuto.this.audioFocusManager == null || AmapAuto.this.audioFocusManager == null)) {
                                    AmapAuto.this.audioFocusManager.requestTheAudioFocus(AmapAuto.myContext, new AudioFocusManager.AudioListener() {
                                        public void start() {
                                            Log.i(AmapAuto.TAG, "com.tencent.qqlive start ");
                                        }

                                        public void pause() {
                                            Log.i(AmapAuto.TAG, "com.tencent.qqlive end ");
                                        }
                                    });
                                }
                            }
                        }
                        if (AmapAuto.this.ResumeStr.equalsIgnoreCase("tv.fourgtv.fourgtv")) {
                            Evc.GetInstance().SetMusicVolume(Iop.GetVolume(0));
                            AmapAuto.this.nSetVolumeDelay = 30;
                        }
                        for (int k2 = 0; k2 < AmapAuto.this.ForceSendPlayCmd.length; k2++) {
                            if (AmapAuto.this.ResumeStr.equalsIgnoreCase(AmapAuto.this.ForceSendPlayCmd[k2])) {
                                AmapAuto.this.nPlayKeyNumDelay = 30;
                            }
                        }
                        if (AmapAuto.this.ResumeStr.equalsIgnoreCase(MainUI.GOOGLE_SPEECH_PNAME)) {
                            Evc.GetInstance().bGoogle = true;
                            AmapAuto.this.nGoogleVoiceDelay = 20;
                        }
                    }
                } else if (AmapAuto.IPOD_WORKMODE_EXIT.equals(action)) {
                    Evc.GetInstance().evol_workmode_set(0);
                } else if (AmapAuto.UPDATE_FILE_DOWNLOAD_SUCCESS.equals(action)) {
                    MainSet.GetInstance().bCheckUpdateFile(true);
                } else if (AmapAuto.ACTION_CHANGED_LOG_STATE.equals(action)) {
                    boolean bCreate = intent.getExtras().getBoolean("open");
                    Log.i(AmapAuto.TAG, "ACTION_CHANGED_LOG_STATE==" + bCreate);
                    MainSet.GetInstance().CreateLog(bCreate);
                }
            }
        }

        private void updateGMTpmsInfo() {
            String[] StrTpms = {"LF_TPMS", "RF_TPMS", "LR_TPMS", "RR_TPMS"};
            Intent intent = new Intent();
            intent.setAction(AmapAuto.BROADCAST_SEND_TPMS_STATE);
            CanIF.UpdateCarInfo();
            int i = 0;
            while (i < CanIF.mCarInfo.Tpms.length && i < StrTpms.length) {
                intent.putExtra(StrTpms[i], String.valueOf(CanIF.mCarInfo.Tpms[i]) + " KPa");
                i++;
            }
            AmapAuto.myContext.sendBroadcast(intent);
        }
    }

    public void SendCarInfordata() {
        CanIF.UpdateDoorUI();
        Intent intent1 = new Intent();
        intent1.setAction(BROADCAST_SEND_CAR_INFO);
        intent1.putExtra("LF_LIGHT", CanIF.mDoorInfo.LFOpen);
        intent1.putExtra("RF_LIGHT", CanIF.mDoorInfo.RFOpen);
        intent1.putExtra("LR_LIGHT", CanIF.mDoorInfo.LROpen);
        intent1.putExtra("RR_LIGHT", CanIF.mDoorInfo.RROpen);
        intent1.putExtra("REAR_DOOR", CanIF.mDoorInfo.RearOpen);
        intent1.putExtra("FRONT_DOOR", CanIF.mDoorInfo.HeadOpen);
        intent1.putExtra("ILL_LIGHT", Mcu.GetIll());
        myContext.sendBroadcast(intent1);
    }

    /* access modifiers changed from: package-private */
    public void UpdateTpmsInfo() {
        StTpms mStTpms = StTpms.GetInstance();
        String[] StrErr = {"LF_ERR", "RF_ERR", "LR_ERR", "RR_ERR"};
        String[] StrTemp = {"LF_TEP", "RF_TEP", "LR_TEP", "RR_TEP"};
        String[] StrTpms = {"LF_TPMS", "RF_TPMS", "LR_TPMS", "RR_TPMS"};
        String[] TpmsOptions = myContext.getResources().getStringArray(R.array.tpms_general_error);
        Intent intent = new Intent();
        intent.setAction(BROADCAST_SEND_TPMS_STATE);
        for (int nNum = 0; nNum < 4; nNum++) {
            int nnMytemp = (int) mStTpms.TpmsDisp.info[nNum].npa;
            if (mStTpms.TpmsDisp.info[nNum].nState == 1) {
                intent.putExtra(StrErr[nNum], TpmsOptions[5]);
            } else if (mStTpms.TpmsDisp.info[nNum].nState == 2) {
                intent.putExtra(StrErr[nNum], TpmsOptions[6]);
            } else if (mStTpms.TpmsDisp.info[nNum].nWarnS > 0) {
                intent.putExtra(StrErr[nNum], TpmsOptions[2]);
            } else if (mStTpms.TpmsDisp.info[nNum].nWarnP > 0) {
                intent.putExtra(StrErr[nNum], TpmsOptions[0]);
            } else if (mStTpms.TpmsDisp.info[nNum].nWarnV > 0) {
                intent.putExtra(StrErr[nNum], TpmsOptions[1]);
            } else if (nnMytemp < mStTpms.tpmsSave.nPskLow) {
                intent.putExtra(StrErr[nNum], TpmsOptions[3]);
            } else if (nnMytemp > mStTpms.tpmsSave.nPskHigh) {
                intent.putExtra(StrErr[nNum], TpmsOptions[4]);
            } else {
                intent.putExtra(StrErr[nNum], "ERR_NONE");
            }
            if (mStTpms.tpmsSave.nPskDW == 0) {
                intent.putExtra(StrTpms[nNum], String.format("%.1f Bar", new Object[]{Double.valueOf(mStTpms.TpmsDisp.info[nNum].npa / 100.0d)}));
            } else {
                intent.putExtra(StrTpms[nNum], String.format("%.1f Psi", new Object[]{Double.valueOf(mStTpms.TpmsDisp.info[nNum].npa * 0.14504d)}));
            }
            if (mStTpms.tpmsSave.nTempDW == 1) {
                intent.putExtra(StrTemp[nNum], String.format("%d℉", new Object[]{Integer.valueOf((int) (32.0d + (((double) mStTpms.TpmsDisp.info[nNum].nTemp) * 1.8d)))}));
            } else {
                intent.putExtra(StrTemp[nNum], String.format("%d℃", new Object[]{Integer.valueOf(mStTpms.TpmsDisp.info[nNum].nTemp)}));
            }
        }
        myContext.sendBroadcast(intent);
    }

    public static void SetBackStateToWeNavi(int nMode) {
        if (myContext != null) {
            Intent intent = new Intent();
            intent.putExtra("operate", nMode);
            intent.setPackage("com.ts.MainUI");
            intent.setAction("com.forfan.operator_reversing");
            myContext.sendBroadcast(intent);
        }
    }

    public static AmapAuto GetInstance() {
        if (m_Amap == null) {
            m_Amap = new AmapAuto();
        }
        return m_Amap;
    }

    public void Inint(Context m_Context) {
        if (myContext == null) {
            myContext = m_Context;
            IntentFilter filter = new IntentFilter(BROADCAST_AMAP_SEND);
            filter.addAction(BROADCAST_REQUEST_TOP_ACTIVITY);
            filter.addAction(BROADCAST_REQUEST_TPMS_STATE);
            filter.addAction(BROADCAST_REQUEST_CAR_INFO);
            filter.addAction(BROADCAST_REQUEST_INFO_STATE);
            filter.addAction(BROADCAST_LOCAT_UPDATE_COM);
            filter.addAction(BROADCAST_TXZ_WIN_CREATE);
            filter.addAction(BROADCAST_TXZ_WIN_DISMIS);
            filter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
            filter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
            filter.addAction("android.intent.action.LOCALE_CHANGED");
            filter.addAction(BRAODCAST_MEDIA_CHANGE);
            filter.addAction(AUTO_BOX_MODE_CHANGE_EVT);
            filter.addAction(AUTO_TS_MODE_CHANGE_EVT);
            filter.addAction(BROADCAST_SCREEN_SET);
            filter.addAction(BROADCAST_SUDING_SPEEDPLAY);
            filter.addAction("com.android.activity.lifecyclechange");
            filter.addAction(APK_DOWNLOAD_SUCCESS);
            filter.addAction(IPOD_WORKMODE_EXIT);
            filter.addAction(UPDATE_FILE_DOWNLOAD_SUCCESS);
            filter.addAction(ACTION_CHANGED_LOG_STATE);
            filter.addCategory("android.intent.category.DEFAULT");
            this.mAmapConnectRecevie = new AmapConnectRecevie();
            myContext.registerReceiver(this.mAmapConnectRecevie, filter);
            registerPackageBroadcast();
        }
    }

    private void registerPackageBroadcast() {
        if (myContext != null) {
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.intent.action.PACKAGE_ADDED");
            filter.addAction("android.intent.action.PACKAGE_REMOVED");
            filter.addAction("android.intent.action.PACKAGE_REPLACED");
            filter.addDataScheme(SdkConstants.ATTR_PACKAGE);
            myContext.registerReceiver(this.mPackageChangeReceiver, filter);
        }
    }

    public static void SetHeadLight(int nState) {
        Intent intent = new Intent();
        intent.setAction(BROADCAST_AMAP_REV);
        intent.putExtra("KEY_TYPE", 10017);
        intent.putExtra("EXTRA_HEADLIGHT_STATE", nState);
        myContext.sendBroadcast(intent);
    }

    public static void SendMuteState(int nState) {
        Intent intent = new Intent();
        intent.setAction("BROADCAST_MAINUI_MUTE_STATE");
        intent.putExtra("State", nState);
        myContext.sendBroadcast(intent);
    }

    public static void SetACCOFF() {
        Intent intent = new Intent();
        intent.setAction(BROADCAST_AMAP_REV);
        intent.putExtra("KEY_TYPE", 10018);
        myContext.sendBroadcast(intent);
    }

    public static void SendAccStateToSuding(int nAcc) {
        Intent intent = new Intent();
        intent.setAction("com.suding.speedplay.receive");
        intent.setComponent(new ComponentName(BROADCAST_SUDING_SPEEDPLAY, "com.texustek.speedplay.broadcast.VendorBroadcastReceiver"));
        if (nAcc == 1) {
            intent.putExtra("command", "ACTION_ENTER");
        } else {
            intent.putExtra("command", "ACTION_EXIT");
        }
        myContext.sendBroadcast(intent);
    }

    public static void QuiteNavi() {
        Intent intent = new Intent();
        intent.setAction(BROADCAST_AMAP_REV);
        intent.putExtra("KEY_TYPE", 10021);
        myContext.sendBroadcast(intent);
    }

    public static void SearchNawAdd() {
        Intent intent = new Intent();
        intent.setAction(BROADCAST_AMAP_REV);
        intent.putExtra("KEY_TYPE", 10029);
        myContext.sendBroadcast(intent);
    }

    public static void SetDayLightMode(int nMode) {
        Intent intent = new Intent();
        intent.setAction(BROADCAST_AMAP_REV);
        intent.putExtra("KEY_TYPE", 10048);
        intent.putExtra("EXTRA_DAY_NIGHT_MODE", nMode);
        myContext.sendBroadcast(intent);
    }

    public void SearchGasStation() {
    }

    public static void SetMapScal(boolean bBig) {
        Intent intent = new Intent();
        intent.setAction(BROADCAST_AMAP_REV);
        intent.putExtra("KEY_TYPE", 10027);
        intent.putExtra("EXTRA_TYPE", 1);
        if (bBig) {
            intent.putExtra("EXTRA_OPERA", 0);
        } else {
            intent.putExtra("EXTRA_OPERA", 1);
        }
        if (myContext != null) {
            myContext.sendBroadcast(intent);
        }
    }
}
