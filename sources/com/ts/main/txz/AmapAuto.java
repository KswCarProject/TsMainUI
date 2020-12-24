package com.ts.main.txz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import com.hongfans.carmedia.Constant;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.MainUI.StTpms;
import com.ts.MainUI.TsDisplay;
import com.ts.MainUI.TsFile;
import com.ts.bt.BtExe;
import com.ts.can.CanIF;
import com.ts.dvdplayer.definition.MediaDef;
import com.ts.main.common.KeyTouch;
import com.ts.main.common.MainLight;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.ts.main.common.MainVerSion;
import com.ts.main.common.PhoneUtils;
import com.ts.main.common.WinShow;
import com.ts.main.common.tool;
import com.txznet.sdk.TXZPoiSearchManager;
import com.txznet.sdk.tongting.IConstantData;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;
import java.io.IOException;

public class AmapAuto {
    private static final String ACTION_LIFE_CYCLE_CHANGE = "com.android.activity.lifecyclechange";
    public static final String ARRIVE_STATUS = "ARRIVE_STATUS";
    public static final int AUTO_BOX_MODE_ANDROIDAUTO_WORK_START = 1;
    public static final int AUTO_BOX_MODE_CARPLAY_WORK_START = 5;
    public static final String AUTO_BOX_MODE_CHANGE_DATA = "cn.manstep.phonemirrorBox.AUTO_BOX_MODE_CHANGE_DATA";
    public static final String AUTO_BOX_MODE_CHANGE_EVT = "cn.manstep.phonemirrorBox.AUTO_BOX_MODE_CHANGE_EVT";
    public static final int AUTO_BOX_MODE_PHONECALL_START = 3;
    public static final int AUTO_BOX_MODE_PHONECALL_STOP = 4;
    public static final int AUTO_BOX_MODE_WORK_STOP = 2;
    public static final String BRAODCAST_ADAS_STATE = "com.adasplus.adas.warning";
    public static final String BRAODCAST_MEDIA_CHANGE = "com.ts.dvdplayer.MEDIA_CHANGE";
    public static final String BRAODCAST_MG_INFO = "intent.action.mapgoo.simtool.info.triffic.v2";
    public static final String BRAODCAST_MUSIC_METACHAGED = "com.android.music.playstatechanged";
    public static final String BRAODCAST_ZHIWANG_ANSWER = "com.cusc.action.phone.answer";
    public static final String BRAODCAST_ZHIWANG_ENDCALL = "com.cusc.action.phone.endcall";
    public static final String BROADCAST_AMAP_REV = "AUTONAVI_STANDARD_BROADCAST_RECV";
    public static final String BROADCAST_AMAP_SEND = "AUTONAVI_STANDARD_BROADCAST_SEND";
    public static final String BROADCAST_DVR_STATE = "check_cardvr.RESP_USB_CAMERA";
    public static final String BROADCAST_EASYCONNTED_IPHONE_IN = "net.easyconn.iphone.inner.mirror.in";
    public static final String BROADCAST_EASYCONNTED_IPHONE_OUT = "net.easyconn.iphone.inner.mirror.out";
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
    public static final String CMDNAME = "command";
    public static final String CMDNEXT = "next";
    public static final String CMDPAUSE = "pause";
    public static final String CMDPREVIOUS = "previous";
    public static final String CMDSTOP = "stop";
    public static final String CMDTOGGLEPAUSE = "togglepause";
    public static final String CUR_POINT_NUM = "CUR_POINT_NUM";
    public static final String CUR_ROAD_NAME = "CUR_ROAD_NAME";
    public static final String CUR_SEG_NUM = "CUR_SEG_NUM";
    public static final String CUR_SPEED = "CUR_SPEED";
    private static final String DOWNLOAD_SUCCESS = "DownloadSuccessACtion";
    public static final String ICON = "ICON";
    public static final String LIMITED_SPEED = "LIMITED_SPEED";
    static final String MeM_FILE = "/mnt/sdcard/mem.ini";
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
    public static final String SERVICECMD = "com.android.music.musicservicecommand";
    private static final String TAG = "AmapAuto";
    public static final String TRAFFIC_LIGHT_NUM = "TRAFFIC_LIGHT_NUM";
    public static final String TYPE = "TYPE";
    private static final String UPDATE_BOOTANIMATION = "Update_bootanimation";
    public static boolean bKill = false;
    public static boolean bNaviState = false;
    static AmapAuto m_Amap = null;
    /* access modifiers changed from: private */
    public static Context myContext = null;
    String[] ForceSendPlayCmd = {"com.google.android.youtube", "com.ex.dabplayer.pad", "com.spotify.music"};
    String[] ForceToWorkmodeNone = {"com.qiyi.video.pad", "com.zbx.ct.tvzhibo", "mbinc12.mb32", "com.ximalaya.ting.android.car", "cn.kuwo.kwmusiccar", "com.mxtech.videoplayer.pro", "com.tencent.qqlive", "cn.cntv", "cn.cntvhd", "com.kugou.android", "com.pbi.liveitv", "tv.fourgtv.fourgtv", "com.vooco.yashi", "com.skysoft.kkbox.android", "com.google.android.youtube", "com.ex.dabplayer.pad", "com.spotify.music", "com.edog.car", "com.aspiro.tidal", "com.infolink.limeiptv"};
    String[] KillinValidStr = {"com.didi365.miudrive.navi"};
    CanIF.NaviInfo MyNaviInfo = new CanIF.NaviInfo();
    /* access modifiers changed from: private */
    public String OnPauseStr = " ";
    /* access modifiers changed from: private */
    public String ResumeStr = " ";
    boolean bSrcMute = false;
    AmapConnectRecevie mAmapConnectRecevie;
    char[] mcuVer = new char[32];
    String memString = "test";
    public int nGoogleVoiceDelay = 0;
    public int nPlayKeyNumDelay = 0;
    public int nSetVolumeDelay = 0;

    public String ReadMem() {
        if (TsFile.fileIsExists(MeM_FILE)) {
            try {
                this.memString = TsFile.readFileSdcardFile(MeM_FILE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.memString;
    }

    public void WriteMem(String str) {
        if (str != null) {
            try {
                this.memString = str;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            TsFile.writeFileSdcardFile(MeM_FILE, this.memString);
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

    public class AmapConnectRecevie extends BroadcastReceiver {
        String[] KillWhilteStr = {"com.android.launcher", "com.android.launcher3", "com.ts.MainUI", "com.ts.dvdplayer", "com.autonavi.amapauto", "com.android.sdvdplayer", "com.android.settings", "com.mxtech.videoplayer.pro", "net.easyconn", "cn.kuwo.kwmusiccar", "com.didi365.miudrive.navi", "net.easyconn.netlink", "com.txznet.music.close.app", Constant.PACKAGE_NAME, "com.kugou.android", "com.tencent.qqmusic", "com.estrongs.android.pop", "com.txznet.txz", "com.into.stability"};

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
                                AmapAuto.this.MyNaviInfo.DestDis /= MediaDef.PROGRESS_MAX;
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
                                tool.GetInstance().KillNavi();
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
                } else if (AmapAuto.BROADCAST_DVR_STATE.equalsIgnoreCase(action)) {
                    intent.getBooleanExtra("exists", false);
                } else if (AmapAuto.BROADCAST_REQUEST_TOP_ACTIVITY.equalsIgnoreCase(action)) {
                    Log.i(AmapAuto.TAG, "BROADCAST_TOP_ACTIVITY = ");
                    Intent intent1 = new Intent();
                    intent1.setAction(AmapAuto.BROADCAST_SEND_TOP_ACTIVITY);
                    intent1.putExtra("Topname", WinShow.getTopActivityName());
                    AmapAuto.myContext.sendBroadcast(intent1);
                } else if (AmapAuto.BROADCAST_REQUEST_TPMS_STATE.equalsIgnoreCase(action)) {
                    Log.i(AmapAuto.TAG, "BROADCAST_REQUEST_TPMS_STATE = ");
                    if (CanJni.GetCanFsTp() == 88) {
                        updateGMTpmsInfo();
                    } else {
                        AmapAuto.this.UpdateTpmsInfo();
                    }
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
                    TxzReg.bWinShow = true;
                } else if (AmapAuto.BROADCAST_TXZ_WIN_DISMIS.equalsIgnoreCase(action)) {
                    Log.i(AmapAuto.TAG, "BROADCAST_TXZ_WIN_DISMIS= ");
                    TxzReg.bWinShow = false;
                } else if (AmapAuto.BRAODCAST_ADAS_STATE.equalsIgnoreCase(action)) {
                    int TYPE = intent.getExtras().getInt(IConstantData.KEY_TYPE);
                    int VALUE = intent.getExtras().getInt("value");
                    Log.i(AmapAuto.TAG, "TYPE= " + TYPE);
                    Log.i(AmapAuto.TAG, "VALUE= " + VALUE);
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
                        MainUI.GetInstance().CheckDisk = true;
                    } else {
                        MainUI.GetInstance().CheckDisk = false;
                    }
                    Log.i(AmapAuto.TAG, "USB DEV BroadcastReceiver vendorId " + vendorId + ", productId " + productId);
                } else if ("android.intent.action.LOCALE_CHANGED".equals(action)) {
                    new Thread() {
                        public void run() {
                            try {
                                sleep(10000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            tool.GetInstance().DealSu("sync");
                        }
                    }.start();
                } else if (AmapAuto.BRAODCAST_MG_INFO.equals(action)) {
                    int CardType = intent.getExtras().getInt("card_type");
                    Log.i(AmapAuto.TAG, "CardType=" + CardType);
                    if (CardType != 0) {
                        MainSet.nSimOwner = 1;
                    }
                } else if (AmapAuto.BRAODCAST_MEDIA_CHANGE.equals(action)) {
                    Iop.PopMuteDelay(50);
                } else if (AmapAuto.AUTO_BOX_MODE_CHANGE_EVT.equals(action)) {
                    switch (intent.getExtras().getInt(AmapAuto.AUTO_BOX_MODE_CHANGE_DATA)) {
                        case 1:
                            BtExe.getBtInstance().setAutoPause(false);
                            return;
                        case 2:
                            BtExe.getBtInstance().setAutoDisconnect(false);
                            return;
                        case 3:
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
                } else if (AmapAuto.BRAODCAST_ZHIWANG_ANSWER.equals(action)) {
                    PhoneUtils.answerCall((TelephonyManager) AmapAuto.myContext.getSystemService("phone"));
                } else if (AmapAuto.BRAODCAST_ZHIWANG_ENDCALL.equals(action)) {
                } else {
                    if (AmapAuto.BROADCAST_SCREEN_SET.equals(action)) {
                        MainLight.GetInstance().WinShow();
                    } else if (AmapAuto.BROADCAST_WWLINK_SHOW.equals(action)) {
                        if (!BtExe.getBtInstance().isConnected() && AmapAuto.myContext != null) {
                            Toast.makeText(AmapAuto.myContext, AmapAuto.myContext.getResources().getString(R.string.bt_connect_first_msg), 0).show();
                        }
                        Evc.GetInstance().evol_workmode_set(5);
                    } else if (AmapAuto.ACTION_LIFE_CYCLE_CHANGE.equals(action)) {
                        String lifecycle = intent.getStringExtra("lifecycle");
                        String packageName = intent.getStringExtra("packagename");
                        String stringExtra = intent.getStringExtra("classname");
                        Log.i(AmapAuto.TAG, "lifecycle = " + lifecycle);
                        Log.i(AmapAuto.TAG, "packagename = " + packageName);
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
                            } else if (AmapAuto.this.OnPauseStr.equalsIgnoreCase("com.google.android.googlequicksearchbox")) {
                                Evc.GetInstance().evol_popmute_clr(Iop.GetWorkMode());
                                Evc.GetInstance().bGoogle = false;
                                AmapAuto.this.nGoogleVoiceDelay = 0;
                            }
                        } else if (lifecycle.equals("onResume")) {
                            AmapAuto.this.ResumeStr = packageName;
                            if (MainSet.GetInstance().bIsLowMemory()) {
                                tool.GetInstance().KillOther(AmapAuto.this.ResumeStr);
                            }
                            if (AmapAuto.this.ResumeStr.equals("net.easyconn")) {
                                AmapAuto.this.bSrcMute = true;
                            } else if (AmapAuto.this.bSrcMute) {
                                TsDisplay.GetInstance().SetSrcMute(20);
                                AmapAuto.this.bSrcMute = false;
                            }
                            for (int j = 0; j < AmapAuto.this.ForceToWorkmodeNone.length; j++) {
                                if (packageName.equalsIgnoreCase(AmapAuto.this.ForceToWorkmodeNone[j])) {
                                    Evc.GetInstance().evol_workmode_set(0);
                                    AmapAuto.this.WriteMem(AmapAuto.this.ForceToWorkmodeNone[j]);
                                }
                            }
                            for (int k2 = 0; k2 < AmapAuto.this.ForceSendPlayCmd.length; k2++) {
                                if (AmapAuto.this.ResumeStr.equalsIgnoreCase(AmapAuto.this.ForceSendPlayCmd[k2])) {
                                    AmapAuto.this.nPlayKeyNumDelay = 30;
                                }
                            }
                            if (AmapAuto.this.ResumeStr.equalsIgnoreCase("tv.fourgtv.fourgtv")) {
                                Evc.GetInstance().SetMusicVolume(Iop.GetVolume(0));
                                AmapAuto.this.nSetVolumeDelay = 30;
                            } else if (AmapAuto.this.ResumeStr.equalsIgnoreCase("com.google.android.googlequicksearchbox")) {
                                Evc.GetInstance().bGoogle = true;
                                AmapAuto.this.nGoogleVoiceDelay = 20;
                            }
                        }
                        if (packageName.equalsIgnoreCase("net.easyconn")) {
                            if (lifecycle.equals("onResume")) {
                                BtExe.getBtInstance().setAutoPause(false);
                            } else if (lifecycle.equals("onPause")) {
                                BtExe.getBtInstance().setAutoPause(true);
                            }
                        }
                        for (int j2 = 0; j2 < AmapAuto.this.ForceToWorkmodeNone.length; j2++) {
                            if (packageName.equalsIgnoreCase(AmapAuto.this.ForceToWorkmodeNone[j2]) && lifecycle.equals("onResume")) {
                                Evc.GetInstance().evol_workmode_set(0);
                            }
                        }
                    } else if (AmapAuto.UPDATE_BOOTANIMATION.equals(action)) {
                        String path = intent.getExtras().getString("filepath");
                        tool.GetInstance().RootSystem();
                        tool.GetInstance().DealSu("cp  " + path + " " + "system/media");
                        tool.GetInstance().DealSu("chmod 0755 -R system/media/bootanimation.zip");
                        tool.GetInstance().DealSu("chcon -R u:object_r:system_file:s0 system/media/bootanimation.zip");
                        tool.GetInstance().UnRootSystem();
                        Toast.makeText(AmapAuto.myContext, "开机动画更新完成", 0).show();
                    }
                }
            }
        }

        private void updateGMTpmsInfo() {
            StTpms GetInstance = StTpms.GetInstance();
            String[] StrErr = {"LF_ERR", "RF_ERR", "LR_ERR", "RR_ERR"};
            String[] strArr = {"LF_TEP", "RF_TEP", "LR_TEP", "RR_TEP"};
            String[] StrTpms = {"LF_TPMS", "RF_TPMS", "LR_TPMS", "RR_TPMS"};
            String[] stringArray = AmapAuto.myContext.getResources().getStringArray(R.array.tpms_general_error);
            Intent intent = new Intent();
            intent.setAction(AmapAuto.BROADCAST_SEND_TPMS_STATE);
            CanIF.UpdateCarInfo();
            int i = 0;
            while (i < CanIF.mCarInfo.Tpms.length && i < StrTpms.length) {
                if (CanIF.mCarInfo.Tpms[i] != 0) {
                    intent.putExtra(StrTpms[i], String.valueOf(CanIF.mCarInfo.Tpms[i]) + " KPa");
                } else {
                    intent.putExtra(StrTpms[i], new StringBuilder(String.valueOf(CanIF.mCarInfo.Tpms[i])).toString());
                }
                i++;
            }
            for (String error : StrErr) {
                intent.putExtra(error, "ERR_NONE");
            }
            AmapAuto.myContext.sendBroadcast(intent);
        }
    }

    public void SearchGasStation() {
        if (myContext == null) {
            return;
        }
        if (MainUI.GetInstance().GpsLat <= 0.0d || MainUI.GetInstance().GpsLang <= 0.0d) {
            Toast.makeText(myContext, "未定位", 0).show();
            return;
        }
        Intent intent = new Intent();
        intent.setAction(BROADCAST_AMAP_REV);
        intent.putExtra("KEY_TYPE", 10037);
        intent.putExtra("KEYWORDS", "加油站");
        intent.putExtra("LAT", MainUI.GetInstance().GpsLat);
        intent.putExtra("LON", MainUI.GetInstance().GpsLang);
        intent.putExtra("DEV", 0);
        intent.putExtra("SOURCE_APP", "Third App");
        myContext.sendBroadcast(intent);
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
            int i = mStTpms.TpmsDisp.info[nNum].nTemp;
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
            filter.addAction(BROADCAST_DVR_STATE);
            filter.addAction(BROADCAST_REQUEST_TOP_ACTIVITY);
            filter.addAction(BROADCAST_REQUEST_TPMS_STATE);
            filter.addAction(BROADCAST_REQUEST_CAR_INFO);
            filter.addAction(BROADCAST_REQUEST_INFO_STATE);
            filter.addAction(BROADCAST_LOCAT_UPDATE_COM);
            filter.addAction(BROADCAST_TXZ_WIN_CREATE);
            filter.addAction(BROADCAST_TXZ_WIN_DISMIS);
            filter.addAction(BRAODCAST_ADAS_STATE);
            filter.addAction(BRAODCAST_MUSIC_METACHAGED);
            filter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
            filter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
            filter.addAction("android.intent.action.LOCALE_CHANGED");
            filter.addAction(BRAODCAST_MG_INFO);
            filter.addAction(BRAODCAST_MEDIA_CHANGE);
            filter.addAction(BRAODCAST_ZHIWANG_ANSWER);
            filter.addAction(BRAODCAST_ZHIWANG_ENDCALL);
            filter.addAction(AUTO_BOX_MODE_CHANGE_EVT);
            filter.addAction(UPDATE_BOOTANIMATION);
            filter.addAction(BROADCAST_WWLINK_SHOW);
            filter.addAction(BROADCAST_SCREEN_SET);
            filter.addAction(ACTION_LIFE_CYCLE_CHANGE);
            filter.addCategory("android.intent.category.DEFAULT");
            this.mAmapConnectRecevie = new AmapConnectRecevie();
            myContext.registerReceiver(this.mAmapConnectRecevie, filter);
        }
    }

    public static void CheckKFRDvrDevice() {
        myContext.sendBroadcast(new Intent("check_cardvr.REQ_USB_CAMERA"));
        Intent service = new Intent("com.ankai.cs.ICoreService");
        service.setPackage("com.ankai.cardvr");
        myContext.startService(service);
        Intent intent = new Intent();
        intent.setAction("intent.mapgoo.service.communicationService");
        myContext.startService(intent);
        Intent service1 = new Intent("intent.action.mapgoo.data.sync");
        service1.setPackage("com.mapgoo.diruite");
        myContext.startService(service1);
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

    public static void QuiteNavi() {
        if (myContext != null) {
            Intent intent = new Intent();
            intent.setAction(BROADCAST_AMAP_REV);
            intent.putExtra("KEY_TYPE", 10021);
            myContext.sendBroadcast(intent);
        }
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
