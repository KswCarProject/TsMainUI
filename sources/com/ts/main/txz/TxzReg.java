package com.ts.main.txz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.AuthServer;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.bt.BtExe;
import com.ts.bt.ContactInfo;
import com.ts.can.CanCameraUI;
import com.ts.can.CanIF;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.ts.main.common.WinShow;
import com.ts.main.common.tool;
import com.ts.main.txz.hongfan.HFMusicAndRadioTool;
import com.txznet.sdk.TXZAsrManager;
import com.txznet.sdk.TXZCallManager;
import com.txznet.sdk.TXZConfigManager;
import com.txznet.sdk.TXZMusicManager;
import com.txznet.sdk.TXZNavManager;
import com.txznet.sdk.TXZNetDataProvider;
import com.txznet.sdk.TXZPoiSearchManager;
import com.txznet.sdk.TXZPowerManager;
import com.txznet.sdk.TXZRecordWinManager;
import com.txznet.sdk.TXZResourceManager;
import com.txznet.sdk.TXZSceneManager;
import com.txznet.sdk.TXZSenceManager;
import com.txznet.sdk.TXZStatusManager;
import com.txznet.sdk.TXZSysManager;
import com.txznet.sdk.TXZTtsManager;
import com.txznet.sdk.bean.Poi;
import com.txznet.sdk.bean.WeatherData;
import com.txznet.sdk.media.InvokeConstants;
import com.txznet.sdk.tongting.IConstantData;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;
import com.yyw.ts70xhw.Radio;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class TxzReg {
    public static final int BT_MAKECALL = 2;
    public static final int BT_ONIDLE = 3;
    public static final int BT_ONINCOMING = 0;
    public static final int BT_ONOFFHOOK = 1;
    private static final String TAG = "TxzReg";
    static boolean bWinShow = false;
    static TxzReg m_TxzReg = null;
    private final String DDBOX_CLOSE_XMLY = "CLOSE_XMLY";
    private final String DDBOX_OPEN = "DDBOX_OPEN";
    private final String DDBOX_OPEN_CZDH = "OPEN_CZDH";
    private final String DDBOX_OPEN_DDBX = "DDBOX_OPEN_DDBX";
    private final String DDBOX_OPEN_DLJY = "DDBOX_OPEN_DDDLJY";
    private final String DDBOX_OPEN_HCZ = "DDBOX_OPEN_DDHCZ";
    private final String DDBOX_OPEN_HUMNZVI = "DDBOX_OPEN_HUMNAVI";
    private final String DDBOX_OPEN_LK = "DDBOX_OPEN_LK";
    private final String DDBOX_OPEN_MUSIC = "DDBOX_OPEN_MUSIC";
    private final String DDBOX_OPEN_QFLK = "DDBOX_CHECK_QFLK";
    private final String DDBOX_OPEN_WZCX = "CLOSE_WZCX";
    private final String DDBOX_OPEN_XMLY = "OPEN_XMLY";
    String[] SQACCmdStrings = {"AC_POWER_ON", "AC_POWER_OFF", "AC_TEMP_INC", "AC_TEMP_DEC", "AC_TEMP_MAX", "AC_TEMP_MIN", "AC_WIND_INC", "AC_WIND_DEC", "AC_WIND_MAX", "AC_WIND_MIN", "AC_OPEN_SKY_WIND", "AC_CLOSE_SKY_WIND", "AC_OPEN_HOSTWIND", "AC_CLOSE_HOSTWIND", "AC_OPEN_SLAVEWIND", "AC_CLOSE_SLAVEWIND"};
    int[] SQACStringID = {R.array.ac_power_on, R.array.ac_power_off, R.array.ac_temp_inc, R.array.ac_temp_dec, R.array.ac_temp_max, R.array.ac_temp_min, R.array.ac_wind_inc, R.array.ac_wind_dec, R.array.ac_wind_max, R.array.ac_wind_min, R.array.ac_open_skywid, R.array.ac_close_skywid, R.array.ac_open_hostwid, R.array.ac_close_hostwid, R.array.ac_open_copwid, R.array.ac_close_copwid};
    private final String TW_OPEN_GOOGLE = "TWOPEN_GOOGLE";
    private final String TW_OPEN_GOOGLEMAP = "TWOPEN_GOOGLEMAP";
    private final String TW_OPEN_KKBOX = "TWOPEN_KK_BOX";
    private final String TW_OPEN_LTV = "TWOPEN_LTV";
    private final String TW_OPEN_MISERBOX = "TWOPEN_MISER_BOX";
    private final String TW_OPEN_SJTV = "TWOPEN_SJTV";
    private final String TW_OPEN_TPMS = "TWOPEN_TPMS";
    private final String TW_OPEN_YOUTUBE = "TWOPEN_YOUTUBE";
    private final String TXZ_AM_FREQ_CMD = "OPEN_AM_FREQ";
    private final String TXZ_CLOSE_AUDIO_CMD = "CLOSE_AUDIO";
    private final String TXZ_CLOSE_BT_CMD = "CLOSE_BT";
    private final String TXZ_CLOSE_DTV_CMD = "CLOSE_DTV";
    private final String TXZ_CLOSE_DVD_CMD = "CLOSE_DVD";
    private final String TXZ_CLOSE_DVR_CMD = "CLOSE_DVR";
    private final String TXZ_CLOSE_RADIO_CMD = "CLOSE_RADIO";
    private final String TXZ_CLOSE_VIDEO_CMD = "CLOSE_VIDEO";
    private final String TXZ_FM_FREQ_CMD = "OPEN_FM_FREQ";
    private final String TXZ_OPEN_AUDIO_CMD = "OPEN_AUDIO";
    private final String TXZ_OPEN_BT_CMD = "OPEN_BT";
    private final String TXZ_OPEN_DTV_CMD = "OPEN_DTV";
    private final String TXZ_OPEN_DVD_CMD = "OPEN_DVD";
    private final String TXZ_OPEN_DVR_CMD = "OPEN_DVR";
    private final String TXZ_OPEN_NAVI_CMD = "OPEN_NAVI";
    private final String TXZ_OPEN_RADIO_CMD = "OPEN_RADIO";
    private final String TXZ_OPEN_VIDEO_CMD = "OPEN_VIDEO";
    private final String TXZ_PLAY_LIST_CMD = "OPEN_PALY_LSIT";
    private final String TXZ_PLAY_MODE_CANCEL_MUSIC_FAVOURITE = "CANCEL_MUSIC_FAVOURITE";
    private final String TXZ_PLAY_MODE_LOOP_ALL = "LOOP_ALL";
    private final String TXZ_PLAY_MODE_MUSIC_FAVOURITE = "MUSIC_FAVOURITE";
    private final String TXZ_PLAY_MODE_RANDOM_ALL = "RANDOM_LOOP";
    private final String TXZ_PLAY_MODE_SIGNLE_LOOP = "SINGLE_LOOP";
    private final String TXZ_SEND_WWATHER_DATA = "txz_send_weather_data";
    private final String TXZ_SQ_AC_CLOSE_HOSTWIND = "AC_CLOSE_HOSTWIND";
    private final String TXZ_SQ_AC_CLOSE_SKYWIND = "AC_CLOSE_SKY_WIND";
    private final String TXZ_SQ_AC_CLOSE_SLAVEWIND = "AC_CLOSE_SLAVEWIND";
    private final String TXZ_SQ_AC_OPEN_HOSTWIND = "AC_OPEN_HOSTWIND";
    private final String TXZ_SQ_AC_OPEN_SKYWIND = "AC_OPEN_SKY_WIND";
    private final String TXZ_SQ_AC_OPEN_SLAVEWIND = "AC_OPEN_SLAVEWIND";
    private final String TXZ_SQ_AC_POWER_OFF = "AC_POWER_OFF";
    private final String TXZ_SQ_AC_POWER_ON = "AC_POWER_ON";
    private final String TXZ_SQ_AC_TEMP_DEC = "AC_TEMP_DEC";
    private final String TXZ_SQ_AC_TEMP_DEC_SET = "AC_TEMP_DEC_SET";
    private final String TXZ_SQ_AC_TEMP_INC = "AC_TEMP_INC";
    private final String TXZ_SQ_AC_TEMP_INC_SET = "AC_TEMP_INC_SET";
    private final String TXZ_SQ_AC_TEMP_MAX = "AC_TEMP_MAX";
    private final String TXZ_SQ_AC_TEMP_MIN = "AC_TEMP_MIN";
    private final String TXZ_SQ_AC_TEMP_SET = "AC_TEMP_SET";
    private final String TXZ_SQ_AC_WIND_DEC = "AC_WIND_DEC";
    private final String TXZ_SQ_AC_WIND_INC = "AC_WIND_INC";
    private final String TXZ_SQ_AC_WIND_MAX = "AC_WIND_MAX";
    private final String TXZ_SQ_AC_WIND_MIN = "AC_WIND_MIN";
    private final String WENAVI_OPEN_WENAVI = "OPEN_WENAVI";
    private final String WENAVI_TO_AIM = "WENAVI_TO_AIM";
    private final String WENAVI_TO_SHARE = "WENAVI_TO_SHARE";
    int[] WeNaviStringID = {R.array.open_wenavi, R.array.navi_to_aim, R.array.navi_to_sharer};
    public boolean bInintOK = false;
    boolean bIsConnect = false;
    boolean bSync = false;
    float fhz = 0.0f;
    /* access modifiers changed from: private */
    public TXZCallManager.CallToolStatusListener mCallToolStatusListener;
    private TXZSenceManager.SenceTool mCommandSenceTool = new TXZSenceManager.SenceTool() {
        public boolean process(TXZSenceManager.SenceType type, String data) {
            Log.e(TxzReg.TAG, "data : " + data);
            if (type.equals(TXZSenceManager.SenceType.SENCE_TYPE_APP)) {
                try {
                    JSONObject json = new JSONObject(data);
                    String action = json.optString("action");
                    if (action != null && !action.isEmpty()) {
                        if (action.equals("close")) {
                            Log.d(TxzReg.TAG, "close");
                            if (json.optString(IConstantData.KEY_NAME).equals("同听")) {
                                MainSet.GetInstance();
                                MainSet.SendIntent("com.txznet.music.close.app");
                            }
                            TXZResourceManager.getInstance().speakTextOnRecordWin("将为您执行该操作", true, new Runnable() {
                                public void run() {
                                    TxzReg.this.BackToLaucher();
                                    TXZAsrManager.getInstance().cancel();
                                }
                            });
                            return true;
                        } else if (action.equals(InvokeConstants.INVOKE_OPEN)) {
                            Log.d(TxzReg.TAG, InvokeConstants.INVOKE_OPEN);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    };
    Evc mEvc = Evc.GetInstance();
    int mHz = 0;
    /* access modifiers changed from: private */
    public TXZMusicManager.MusicToolStatusListener mMusicToolStatusListener;
    BtExe m_BtExe = BtExe.getBtInstance();
    TXZAsrManager m_Txz = TXZAsrManager.getInstance();
    Map<String, String> map = new HashMap();
    /* access modifiers changed from: private */
    public Context myContext = null;
    TXZNavManager.NavToolStatusListener myNavStatusListener = null;
    int nIndex = 0;
    int nKeyDelay = 0;
    int nKeyNum = 0;
    int nNaviType = 1;
    int nPowerState = -1;
    int nState = -1;
    int nVolDn = 0;
    int nVolUp = 0;
    TXZSceneManager.SceneTool sceneTool = new TXZSceneManager.SceneTool() {
        public boolean process(TXZSceneManager.SceneType type, String data) {
            try {
                String action = new JSONObject(data).optString("action");
                Log.e("Prisoner", "action " + action);
                if ("next".equals(action)) {
                    Mcu.SetCkey(44);
                    TXZAsrManager.getInstance().cancel();
                    return true;
                } else if (!InvokeConstants.INVOKE_PREV.equals(action)) {
                    return false;
                } else {
                    Mcu.SetCkey(45);
                    TXZAsrManager.getInstance().cancel();
                    return true;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }
    };
    String[] tWVoiceCmdStrings = {"TWOPEN_KK_BOX", "TWOPEN_MISER_BOX", "TWOPEN_YOUTUBE", "TWOPEN_LTV", "TWOPEN_TPMS", "TWOPEN_SJTV", "TWOPEN_GOOGLE", "TWOPEN_GOOGLEMAP"};
    int[] tWVoiceCmdStringsID = {R.array.tw_open_kkbox, R.array.tw_open_mixer, R.array.tw_open_youtube, R.array.tw_open_ltv, R.array.tw_open_tpms, R.array.tw_open_sijitv, R.array.tw_open_google, R.array.tw_open_googlemap};
    Class<?> txzExtraCmdClass;
    Method txzExtraCmdDeal;
    Map<String, Integer> txzExtraCmdMap;
    String[] weNaviCmdStrings = {"OPEN_WENAVI", "WENAVI_TO_AIM", "WENAVI_TO_SHARE"};

    public static TxzReg GetInstance() {
        if (m_TxzReg == null) {
            m_TxzReg = new TxzReg();
        }
        return m_TxzReg;
    }

    public void TxzStartMic(String str) {
        if (!this.bInintOK) {
            return;
        }
        if (bWinShow) {
            TXZAsrManager.getInstance().cancel();
        } else {
            TXZAsrManager.getInstance().triggerRecordButton();
        }
    }

    public void TxzWinHide() {
        if (this.bInintOK) {
            TXZAsrManager.getInstance().cancel();
        }
    }

    /* access modifiers changed from: package-private */
    public void SetTXZToSleep() {
        Log.i(TAG, "SetTXZToSleep");
        TXZPowerManager.getInstance().notifyPowerAction(TXZPowerManager.PowerAction.POWER_ACTION_BEFORE_SLEEP);
        TXZPowerManager.getInstance().releaseTXZ();
    }

    /* access modifiers changed from: package-private */
    public void SetTXZWakeUp() {
        Log.i(TAG, "SetTXZWakeUp");
        TXZPowerManager.getInstance().reinitTXZ(new Runnable() {
            public void run() {
                FtSet.GetSpeechMode();
                Log.i(TxzReg.TAG, "txz reinitTXZ ok");
                TxzReg.this.bInintOK = true;
                TxzReg.this.bIsConnect = false;
                TxzReg.this.SetBtState(3, (TXZCallManager.Contact) null);
                TxzReg.this.SetTXZState(FtSet.GetSpeechMode());
            }
        });
        TXZPowerManager.getInstance().notifyPowerAction(TXZPowerManager.PowerAction.POWER_ACTION_WAKEUP);
    }

    public void SetNaviType(String PackName) {
        if (PackName != null && this.bInintOK) {
            if (PackName.contains("com.baidu.BaiduMap")) {
                TXZNavManager.getInstance().setNavTool(TXZNavManager.NavToolType.NAV_TOOL_BAIDU_MAP);
            } else if (PackName.contains("com.baidu.navi.hd")) {
                TXZNavManager.getInstance().setNavTool(TXZNavManager.NavToolType.NAV_TOOL_BAIDU_NAV_HD);
            } else if (PackName.contains("com.baidu.navi")) {
                TXZNavManager.getInstance().setNavTool(TXZNavManager.NavToolType.NAV_TOOL_BAIDU_NAV);
            } else if (PackName.contains("com.autonavi.amapauto")) {
                TXZNavManager.getInstance().setNavTool(TXZNavManager.NavToolType.NAV_TOOL_GAODE_MAP_CAR);
            } else if (PackName.contains("com.autonavi.minimap")) {
                TXZNavManager.getInstance().setNavTool(TXZNavManager.NavToolType.NAV_TOOL_GAODE_MAP);
            } else if (PackName.contains("com.autonavi.xmgd.navigator")) {
                TXZNavManager.getInstance().setNavTool(TXZNavManager.NavToolType.NAV_TOOL_GAODE_NAV);
            } else if (PackName.contains("cld.navi")) {
                TXZNavManager.getInstance().setNavTool(TXZNavManager.NavToolType.NAV_TOOL_KAILIDE_NAV);
            }
        }
    }

    public void SetTXZState(int nState2) {
        if (this.bInintOK) {
            switch (nState2) {
                case 1:
                    SetKeyWordsWakeUp(true);
                    SetRecordBtnShow(false);
                    return;
                case 2:
                    SetKeyWordsWakeUp(false);
                    SetRecordBtnShow(true);
                    return;
                case 3:
                    SetKeyWordsWakeUp(false);
                    SetRecordBtnShow(false);
                    return;
                default:
                    SetKeyWordsWakeUp(true);
                    SetRecordBtnShow(true);
                    return;
            }
        }
    }

    public static void closeWakeup() {
        TXZConfigManager.getInstance().setWakeupKeywordsNew(new String[0]);
        TXZConfigManager.getInstance().enableChangeWakeupKeywords(false);
    }

    public static void openWakeup() {
        TXZConfigManager.getInstance().setWakeupKeywordsNew("填入自定义的唤醒词");
        TXZConfigManager.getInstance().enableChangeWakeupKeywords(true);
    }

    /* access modifiers changed from: package-private */
    public void SetKeyWordsWakeUp(boolean bWake) {
        if (!this.bInintOK) {
            return;
        }
        if (!bWake) {
            TXZConfigManager.getInstance().setWakeupKeywordsNew(new String[0]);
            TXZConfigManager.getInstance().enableChangeWakeupKeywords(false);
            TXZConfigManager.getInstance().enableWakeup(false);
        } else if (this.myContext != null) {
            TXZConfigManager.getInstance().setWakeupKeywordsNew(this.myContext.getResources().getStringArray(R.array.txz_sdk_init_wakeup_keywords));
            TXZConfigManager.getInstance().enableWakeup(true);
            TXZConfigManager.getInstance().enableChangeWakeupKeywords(true);
        }
    }

    public void SetRecordBtnShow(boolean bShow) {
        if (this.myContext != null) {
            if (bShow) {
                Intent intent = new Intent();
                intent.setAction("com.ts.myvoice.VoiceService");
                this.myContext.startService(intent);
            } else {
                Intent intent2 = new Intent();
                intent2.setAction("com.ts.myvoice.VoiceService");
                this.myContext.stopService(intent2);
            }
            TXZConfigManager.getInstance().showFloatTool(TXZConfigManager.FloatToolType.FLOAT_NONE);
        }
    }

    public void AddMusicList(Map<String, String> map2) {
        Log.i(TAG, "AddMusicList");
    }

    public int Task(int nParat) {
        if (this.myContext == null || !this.bInintOK) {
            return 0;
        }
        if (this.nVolUp > 0) {
            this.mEvc.Evol_vol_tune(1);
            this.nVolUp--;
        }
        if (this.nVolDn > 0) {
            this.mEvc.Evol_vol_tune(0);
            this.nVolDn--;
        }
        if (this.nKeyDelay > 0) {
            this.nKeyDelay--;
            if (this.nKeyDelay == 0) {
                Mcu.SetCkey(this.nKeyNum);
            }
        }
        if (!this.bInintOK || this.bIsConnect == this.m_BtExe.isConnected()) {
            return 1;
        }
        Log.i(TAG, "bIsConnect ===" + this.bIsConnect);
        Log.i(TAG, "mCallToolStatusListener ===" + this.mCallToolStatusListener);
        this.bIsConnect = this.m_BtExe.isConnected();
        if (!this.m_BtExe.isConnected()) {
            MainSet.GetInstance();
            if (MainSet.IsGLSXVer().booleanValue()) {
                if (this.mCallToolStatusListener == null) {
                    return 1;
                }
                this.mCallToolStatusListener.onEnabled();
                return 1;
            } else if (this.mCallToolStatusListener == null) {
                return 1;
            } else {
                this.mCallToolStatusListener.onDisabled("请先连接蓝牙");
                return 1;
            }
        } else if (this.mCallToolStatusListener == null) {
            return 1;
        } else {
            this.mCallToolStatusListener.onEnabled();
            return 1;
        }
    }

    public void SetBtState(int nState2, TXZCallManager.Contact con) {
        if (this.bInintOK && this.mCallToolStatusListener != null) {
            switch (nState2) {
                case 0:
                    this.mCallToolStatusListener.onIncoming(con, false, false);
                    return;
                case 1:
                    this.mCallToolStatusListener.onOffhook();
                    return;
                case 2:
                    this.mCallToolStatusListener.onMakeCall(con);
                    return;
                case 3:
                    this.mCallToolStatusListener.onIdle();
                    return;
                default:
                    return;
            }
        }
    }

    public void SyncBlueToothContacts(List<TXZCallManager.Contact> lst2) {
        if (this.bInintOK) {
            TXZCallManager.getInstance().syncContacts(lst2);
            Log.i(TAG, "SyncBlueToothContacts ok");
            this.bSync = true;
            return;
        }
        Log.i(TAG, "SyncBlueToothContacts false");
    }

    public void GetWeatherData(String strCity) {
        TXZNetDataProvider.getInstance().getWeatherInfo(strCity, new TXZNetDataProvider.NetDataCallback<WeatherData>() {
            public void onResult(WeatherData data) {
                for (int i = 0; i < data.weatherDays.length; i++) {
                    Log.i(TxzReg.TAG, "Weather==" + data.weatherDays[i].weather);
                }
                Intent intent = new Intent();
                Bundle bdBundle = new Bundle();
                bdBundle.putSerializable("weather", data);
                intent.putExtras(bdBundle);
                intent.setAction("txz_send_weather_data");
                TxzReg.this.myContext.sendBroadcast(intent);
            }

            public void onError(int error) {
                Log.i(TxzReg.TAG, "onError=" + error);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void InintBT() {
        TXZCallManager.getInstance().setCallTool(new TXZCallManager.CallTool() {
            public void setStatusListener(TXZCallManager.CallToolStatusListener listener) {
                Log.i(TxzReg.TAG, "setStatusListener");
                TxzReg.this.mCallToolStatusListener = listener;
            }

            public boolean acceptIncoming() {
                TxzReg.this.m_BtExe.answer();
                Log.i(TxzReg.TAG, "acceptIncoming");
                return false;
            }

            public TXZCallManager.CallTool.CallStatus getStatus() {
                Log.i(TxzReg.TAG, "BT getStatus");
                return TXZCallManager.CallTool.CallStatus.CALL_STATUS_IDLE;
            }

            public boolean hangupCall() {
                TxzReg.this.m_BtExe.hangup();
                Log.i(TxzReg.TAG, "hangupCall");
                return false;
            }

            public boolean makeCall(TXZCallManager.Contact contact) {
                if (BtExe.getBtInstance().isConnected()) {
                    TxzReg.this.m_BtExe.dial(contact.getNumber());
                } else {
                    Glsx.GetInstance().Dail(contact);
                }
                Log.i(TxzReg.TAG, "makeCall");
                return false;
            }

            public boolean rejectIncoming() {
                TxzReg.this.m_BtExe.hangup();
                Log.i(TxzReg.TAG, "rejectIncoming");
                return false;
            }
        });
    }

    /* access modifiers changed from: private */
    public void InintRadio() {
        TXZAsrManager.getInstance().regCommandForFM(87.5f, 108.0f, "OPEN_FM_FREQ");
        TXZAsrManager.getInstance().regCommandForAM(CanCameraUI.BTN_GEELY_YJX6_MODE3, CanCameraUI.BTN_MG_2D, "OPEN_AM_FREQ");
    }

    private void InintDDHPlayIndex(String data) {
        List<String> playlist = new ArrayList<>();
        for (int i = 0; i <= 30; i++) {
            playlist.add("播放第" + i + "首");
            playlist.add("听第" + i + "首");
        }
        TXZAsrManager.getInstance().regCommand((Collection<String>) playlist, data);
        playlist.clear();
    }

    private void initMusicTool() {
        TXZMusicManager.getInstance().setMusicTool((TXZMusicManager.MusicTool) new TXZMusicManager.MusicTool() {
            public void unfavourMusic() {
                TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_no_support_this_cmd), true, new Runnable() {
                    public void run() {
                    }
                });
                Log.i(TxzReg.TAG, "unfavourMusic==");
            }

            public void switchSong() {
                Log.i(TxzReg.TAG, "switchSong");
                Mcu.SetCkey(44);
                Glsx.GetInstance().PlayCmd(3);
            }

            public void switchModeRandom() {
                TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_no_support_this_cmd), true, new Runnable() {
                    public void run() {
                    }
                });
                Log.i(TxzReg.TAG, "switchModeRandom==");
            }

            public void switchModeLoopOne() {
                TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_no_support_this_cmd), true, new Runnable() {
                    public void run() {
                    }
                });
                Log.i(TxzReg.TAG, "switchModeLoopOne==");
            }

            public void switchModeLoopAll() {
                TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_no_support_this_cmd), true, new Runnable() {
                    public void run() {
                    }
                });
                Log.i(TxzReg.TAG, "switchModeLoopAll==");
            }

            public void setStatusListener(TXZMusicManager.MusicToolStatusListener listener) {
                TxzReg.this.mMusicToolStatusListener = listener;
            }

            public void prev() {
                Mcu.SetCkey(45);
                Log.i(TxzReg.TAG, InvokeConstants.INVOKE_PREV);
                Glsx.GetInstance().PlayCmd(2);
            }

            public void playRandom() {
                WinShow.GotoWin(6, 0);
            }

            private String toArrayString(String[] ss) {
                if (ss == null || ss.length == 0) {
                    return null;
                }
                StringBuilder s = new StringBuilder();
                for (String t : ss) {
                    if (s.length() > 0) {
                        s.append(',');
                    }
                    s.append(t);
                }
                return s.toString();
            }

            public void playMusic(TXZMusicManager.MusicModel model) {
                boolean bPlay = false;
                Log.i(TxzReg.TAG, "model.getTitle()==" + model.getTitle());
                Log.i(TxzReg.TAG, "model.getAlbum()==" + model.getAlbum());
                Log.i(TxzReg.TAG, "model.getArtist()==" + model.getArtist());
                if (MainSet.nPlayer == 2) {
                    Cyb.GetInstance().playMusic(String.valueOf(model.getArtist()[0]) + model.getTitle());
                    return;
                }
                if (!(TxzReg.this.map == null || model.getTitle() == null)) {
                    for (String key : TxzReg.this.map.keySet()) {
                        Log.i(TxzReg.TAG, "playlisname map.filename()==" + key);
                        if (key.contains(model.getTitle())) {
                            Log.i(TxzReg.TAG, "playlisname map.data==" + TxzReg.this.map.get(key));
                            if (MainUI.GetInstance().mTsPlayerService != null) {
                                try {
                                    MainUI.GetInstance().mTsPlayerService.playByPath(TxzReg.this.map.get(key));
                                    bPlay = true;
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
                if (!bPlay) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_local_not_have_this_song), true, new Runnable() {
                        public void run() {
                        }
                    });
                }
            }

            public void playFavourMusic() {
                TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_no_support_this_cmd), true, new Runnable() {
                    public void run() {
                    }
                });
            }

            public void play() {
                if (MainSet.nPlayer == 2) {
                    Cyb.GetInstance().playResume();
                    return;
                }
                WinShow.GotoWin(6, 0);
                Mcu.SetCkey(90);
            }

            public void pause() {
                Mcu.SetCkey(91);
            }

            public void exit() {
                TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_close_audio), true, new Runnable() {
                    public void run() {
                        if (Iop.GetWorkMode() == 4) {
                            TxzReg.this.BackToLaucher();
                            TxzReg.this.mEvc.evol_workmode_set(0);
                        }
                    }
                });
            }

            public void next() {
                TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_play_next), true, new Runnable() {
                    public void run() {
                        Mcu.SetCkey(44);
                    }
                });
            }

            public boolean isPlaying() {
                return false;
            }

            public TXZMusicManager.MusicModel getCurrentMusicModel() {
                return null;
            }

            public void favourMusic() {
                TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_no_support_this_cmd), true, new Runnable() {
                    public void run() {
                    }
                });
            }

            public void continuePlay() {
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void RegTwCommand() {
        for (int i = 0; i < this.tWVoiceCmdStrings.length; i++) {
            this.m_Txz.regCommand(this.myContext.getResources().getStringArray(this.tWVoiceCmdStringsID[i]), this.tWVoiceCmdStrings[i]);
        }
    }

    /* access modifiers changed from: package-private */
    public void RegSQCommond() {
        for (int i = 0; i < this.SQACCmdStrings.length; i++) {
            this.m_Txz.regCommand(this.myContext.getResources().getStringArray(this.SQACStringID[i]), this.SQACCmdStrings[i]);
        }
        List<String> TempIncSet = new ArrayList<>();
        for (int i2 = 1; i2 <= 8; i2++) {
            TempIncSet.add("帮我把空调温度调高" + i2 + "度");
            TempIncSet.add("我想把温度升高" + i2 + "度");
            TempIncSet.add("增加" + i2 + "度");
        }
        TXZAsrManager.getInstance().regCommand((Collection<String>) TempIncSet, "AC_TEMP_INC_SET");
        TempIncSet.clear();
        List<String> TempDecSet = new ArrayList<>();
        for (int i3 = 1; i3 <= 8; i3++) {
            TempDecSet.add("帮我把空调温度降低" + i3 + "度");
            TempDecSet.add("我想把温度减少" + i3 + "度");
            TempDecSet.add("调低" + i3 + "度");
        }
        TXZAsrManager.getInstance().regCommand((Collection<String>) TempDecSet, "AC_TEMP_DEC_SET");
        TempDecSet.clear();
        List<String> TempSet = new ArrayList<>();
        for (int i4 = 16; i4 <= 31; i4++) {
            TempSet.add("把空调温度调成" + i4 + "度");
            TempSet.add("温度" + i4 + "度");
            TempSet.add("空调设置为" + i4 + "度");
            TempSet.add("空调升高成" + i4 + "度");
            TempSet.add("温度降低到" + i4 + "度");
        }
        TXZAsrManager.getInstance().regCommand((Collection<String>) TempSet, "AC_TEMP_SET");
        TempSet.clear();
    }

    /* access modifiers changed from: package-private */
    public void DealTwCommond(String cmd, String data) {
        for (int i = 0; i < this.tWVoiceCmdStrings.length; i++) {
            if (data.equals(this.tWVoiceCmdStrings[i])) {
                switch (i) {
                    case 0:
                        MainSet.GetInstance().openApplication(this.myContext, "com.skysoft.kkbox.android");
                        break;
                    case 1:
                        MainSet.GetInstance().openApplication(this.myContext, "mbinc12.mb32");
                        break;
                    case 2:
                        MainSet.GetInstance().openApplication(this.myContext, "com.google.android.youtube");
                        break;
                    case 3:
                        MainSet.GetInstance().openApplication(this.myContext, "com.litv.mobile.gp.litv");
                        break;
                    case 4:
                        WinShow.show("com.ts.MainUI", "com.ts.main.tpms.TPMSMainActivity");
                        break;
                    case 5:
                        MainSet.GetInstance().openApplication(this.myContext, "tv.fourgtv.fourgtv");
                        break;
                    case 6:
                        MainSet.GetInstance().openApplication(this.myContext, "com.google.android.googlequicksearchbox");
                        break;
                    case 7:
                        MainSet.GetInstance().openApplication(this.myContext, "com.google.android.apps.maps");
                        break;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void DealSQCommond(String cmd, String data) {
        Log.i(TAG, "DealSQCommond===" + cmd + " " + data);
        if (data.equals("AC_TEMP_INC_SET")) {
            int index = cmd.length() - 1;
            String strList = cmd.substring(index - 1, index);
            Log.i(TAG, "index===" + strList);
            this.nIndex = Integer.parseInt(strList);
            if (this.nIndex > 0 && this.nIndex <= 8) {
                TXZResourceManager.getInstance().speakTextOnRecordWin("将为您调高" + this.nIndex + "度", true, new Runnable() {
                    public void run() {
                        CanIF.DealMgAcSpeadkSet(5, TxzReg.this.nIndex);
                    }
                });
            }
        } else if (data.equals("AC_TEMP_DEC_SET")) {
            int index2 = cmd.length() - 1;
            String strList2 = cmd.substring(index2 - 1, index2);
            Log.i(TAG, "index===" + strList2);
            this.nIndex = Integer.parseInt(strList2);
            if (this.nIndex > 0 && this.nIndex <= 8) {
                TXZResourceManager.getInstance().speakTextOnRecordWin("将为您调低" + this.nIndex + "度", true, new Runnable() {
                    public void run() {
                        CanIF.DealMgAcSpeadkSet(6, TxzReg.this.nIndex);
                    }
                });
            }
        } else if (data.equals("AC_TEMP_SET")) {
            int index3 = cmd.length() - 1;
            String strList3 = cmd.substring(index3 - 2, index3);
            Log.i(TAG, "index===" + strList3);
            this.nIndex = Integer.parseInt(strList3);
            if (this.nIndex > 0) {
                TXZResourceManager.getInstance().speakTextOnRecordWin("将为您调节到" + this.nIndex + "度", true, new Runnable() {
                    public void run() {
                        CanIF.DealMgAcSpeadkSet(7, TxzReg.this.nIndex);
                    }
                });
            }
        }
        for (int i = 0; i < this.SQACCmdStrings.length; i++) {
            if (this.SQACCmdStrings[i].equals(data)) {
                switch (i) {
                    case 0:
                        TXZResourceManager.getInstance().speakTextOnRecordWin("将为您打开空调", true, new Runnable() {
                            public void run() {
                                CanIF.DealMgAcSpeadkSet(1, 0);
                            }
                        });
                        break;
                    case 1:
                        TXZResourceManager.getInstance().speakTextOnRecordWin("将为您关闭空调", true, new Runnable() {
                            public void run() {
                                CanIF.DealMgAcSpeadkSet(2, 0);
                            }
                        });
                        break;
                    case 2:
                        TXZResourceManager.getInstance().speakTextOnRecordWin("将为您调高温度", true, new Runnable() {
                            public void run() {
                                CanIF.DealMgAcSpeadkSet(3, 0);
                            }
                        });
                        break;
                    case 3:
                        TXZResourceManager.getInstance().speakTextOnRecordWin("将为您降低温度", true, new Runnable() {
                            public void run() {
                                CanIF.DealMgAcSpeadkSet(4, 0);
                            }
                        });
                        break;
                    case 4:
                        TXZResourceManager.getInstance().speakTextOnRecordWin("为您将温度调节到最高", true, new Runnable() {
                            public void run() {
                                CanIF.DealMgAcSpeadkSet(8, 0);
                            }
                        });
                        break;
                    case 5:
                        TXZResourceManager.getInstance().speakTextOnRecordWin("为您将温度调节到最低", true, new Runnable() {
                            public void run() {
                                CanIF.DealMgAcSpeadkSet(9, 0);
                            }
                        });
                        break;
                    case 6:
                        TXZResourceManager.getInstance().speakTextOnRecordWin("将为您增加风量", true, new Runnable() {
                            public void run() {
                                CanIF.DealMgAcSpeadkSet(10, 0);
                            }
                        });
                        break;
                    case 7:
                        TXZResourceManager.getInstance().speakTextOnRecordWin("将为您减小风量", true, new Runnable() {
                            public void run() {
                                CanIF.DealMgAcSpeadkSet(11, 0);
                            }
                        });
                        break;
                    case 8:
                        TXZResourceManager.getInstance().speakTextOnRecordWin("为您将风量调到最大", true, new Runnable() {
                            public void run() {
                                CanIF.DealMgAcSpeadkSet(12, 0);
                            }
                        });
                        break;
                    case 9:
                        TXZResourceManager.getInstance().speakTextOnRecordWin("为您将风量调到最小", true, new Runnable() {
                            public void run() {
                                CanIF.DealMgAcSpeadkSet(13, 0);
                            }
                        });
                        break;
                    case 10:
                        TXZResourceManager.getInstance().speakTextOnRecordWin("打开天窗", true, new Runnable() {
                            public void run() {
                                CanIF.DealMgAcSpeadkSet(14, 0);
                            }
                        });
                        break;
                    case 11:
                        TXZResourceManager.getInstance().speakTextOnRecordWin("关闭天窗", true, new Runnable() {
                            public void run() {
                                CanIF.DealMgAcSpeadkSet(15, 0);
                            }
                        });
                        break;
                    case 12:
                        TXZResourceManager.getInstance().speakTextOnRecordWin("将为您打开主驾驶车窗", true, new Runnable() {
                            public void run() {
                                CanIF.DealMgAcSpeadkSet(16, 0);
                            }
                        });
                        break;
                    case 13:
                        TXZResourceManager.getInstance().speakTextOnRecordWin("将为您关闭主驾驶车窗", true, new Runnable() {
                            public void run() {
                                CanIF.DealMgAcSpeadkSet(18, 0);
                            }
                        });
                        break;
                    case 14:
                        TXZResourceManager.getInstance().speakTextOnRecordWin("将为您打开副驾驶车窗", true, new Runnable() {
                            public void run() {
                                CanIF.DealMgAcSpeadkSet(17, 0);
                            }
                        });
                        break;
                    case 15:
                        TXZResourceManager.getInstance().speakTextOnRecordWin("将为您关闭副驾驶车窗", true, new Runnable() {
                            public void run() {
                                CanIF.DealMgAcSpeadkSet(19, 0);
                            }
                        });
                        break;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void SetUserCommand() {
        if (this.myContext != null) {
            for (int i = 0; i < this.weNaviCmdStrings.length; i++) {
                this.m_Txz.regCommand(this.myContext.getResources().getStringArray(this.WeNaviStringID[i]), this.weNaviCmdStrings[i]);
            }
            if (26 == CanJni.GetCanType()) {
                RegSQCommond();
            }
            if (MainSet.GetInstance().IsTwcjw()) {
                RegTwCommand();
            }
            MainSet.GetInstance();
            if (MainSet.IsGLSXVer().booleanValue()) {
                this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.open_glsx_ddbox), "DDBOX_OPEN");
                this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.open_glsx_ddbox_lk), "DDBOX_OPEN_LK");
                this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.open_czdh), "OPEN_CZDH");
                this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.open_wzch), "CLOSE_WZCX");
                this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.open_glsx_ddbox_HumNavi), "DDBOX_OPEN_HUMNAVI");
                this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.open_glsx_ddbox_ddbx), "DDBOX_OPEN_DDBX");
                this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.open_glsx_ddbox_hcz), "DDBOX_OPEN_DDHCZ");
                this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.open_glsx_ddbox_dljy), "DDBOX_OPEN_DDDLJY");
                this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.glsx_ddbox_qflk), "DDBOX_CHECK_QFLK");
                this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.open_xmly), "OPEN_XMLY");
                this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.close_xmly), "CLOSE_XMLY");
            }
            this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.open_navi), "OPEN_NAVI");
            if (FtSet.IsIconExist(1) == 1) {
                this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.open_radio), "OPEN_RADIO");
                this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.close_radio), "CLOSE_RADIO");
            }
            this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.open_bt), "OPEN_BT");
            this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.close_bt), "CLOSE_BT");
            this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.open_cmmb), "OPEN_DTV");
            this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.close_cmmb), "CLOSE_DTV");
            this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.open_audio), "OPEN_AUDIO");
            this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.close_audio), "CLOSE_AUDIO");
            this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.open_video), "OPEN_VIDEO");
            this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.close_video), "CLOSE_VIDEO");
            this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.open_dvd), "OPEN_DVD");
            this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.close_dvd), "CLOSE_DVD");
            this.m_Txz.regCommand(this.myContext.getResources().getStringArray(R.array.open_dvr), "OPEN_DVR");
            int id = this.myContext.getResources().getIdentifier("txz_extra_cmd", "string", this.myContext.getPackageName());
            if (id != 0 && this.myContext.getResources().getString(id).equals("true")) {
                try {
                    this.txzExtraCmdClass = Class.forName("com.ts.txz.TxzExtraCmd");
                    this.txzExtraCmdDeal = this.txzExtraCmdClass.getDeclaredMethod("dealTxzExtraCmd", new Class[]{String.class});
                    this.txzExtraCmdMap = (Map) this.txzExtraCmdClass.getDeclaredMethod("getTxzExtraCmdMap", (Class[]) null).invoke((Object) null, (Object[]) null);
                    if (this.txzExtraCmdMap != null) {
                        for (Map.Entry<String, Integer> entry : this.txzExtraCmdMap.entrySet()) {
                            this.m_Txz.regCommand(this.myContext.getResources().getStringArray(entry.getValue().intValue()), entry.getKey());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void BackToLaucher() {
        if (this.myContext != null) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            intent.setFlags(268435456);
            this.myContext.startActivity(intent);
        }
    }

    /* access modifiers changed from: package-private */
    public String GetResString(int nID) {
        if (this.myContext != null) {
            return this.myContext.getResources().getString(nID);
        }
        return "";
    }

    /* access modifiers changed from: package-private */
    public void SetWeNaviCmd(int Value) {
        Intent intent = new Intent();
        intent.putExtra("value", Value);
        intent.setAction("com.forfan.speech_action");
        this.myContext.sendBroadcast(intent);
    }

    /* access modifiers changed from: package-private */
    public void AddListenser() {
        this.m_Txz.addCommandListener(new TXZAsrManager.CommandListener() {
            public void onCommand(String cmd, String data) {
                Log.i(TxzReg.TAG, "onCommand===" + cmd + "  " + data);
                if (data.equals("OPEN_WENAVI")) {
                    TxzReg.this.SetWeNaviCmd(TXZConfigManager.INIT_ERROR_ASR);
                } else if (data.equals("WENAVI_TO_AIM")) {
                    TxzReg.this.SetWeNaviCmd(TXZConfigManager.INIT_ERROR_TTS);
                } else if (data.equals("WENAVI_TO_SHARE")) {
                    TxzReg.this.SetWeNaviCmd(TXZConfigManager.INIT_ERROR_WAKEUP);
                }
                if (26 == CanJni.GetCanType()) {
                    TxzReg.this.DealSQCommond(cmd, data);
                }
                if (MainSet.GetInstance().IsTwcjw()) {
                    TxzReg.this.DealTwCommond(cmd, data);
                }
                if (data.equals("DDBOX_OPEN")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.ddbox_open), true, new Runnable() {
                        public void run() {
                            Glsx.GetInstance().OpenDDBOX();
                        }
                    });
                } else if (data.equals("DDBOX_OPEN_MUSIC")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.ddbox_open_music), true, new Runnable() {
                        public void run() {
                            MainSet.GetInstance().StartKw();
                        }
                    });
                } else if (data.equals("DDBOX_OPEN_LK")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.ddbox_open_lk), true, new Runnable() {
                        public void run() {
                            Glsx.GetInstance().OpenApp(30);
                        }
                    });
                } else if (data.equals("OPEN_CZDH")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.ddbox_open_czdh), true, new Runnable() {
                        public void run() {
                            Glsx.GetInstance().OpenApp(50);
                        }
                    });
                } else if (data.equals("CLOSE_WZCX")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.ddbox_open_wzcx), true, new Runnable() {
                        public void run() {
                            Glsx.GetInstance().OpenApp(25);
                        }
                    });
                } else if (data.equals("DDBOX_OPEN_HUMNAVI")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.ddbox_open_humnavi), true, new Runnable() {
                        public void run() {
                            Glsx.GetInstance().OpenApp(5);
                        }
                    });
                } else if (data.equals("DDBOX_OPEN_DDBX")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.ddbox_open_ddbx), true, new Runnable() {
                        public void run() {
                            Glsx.GetInstance().OpenApp(32);
                        }
                    });
                } else if (data.equals("DDBOX_OPEN_DDHCZ")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.ddbox_open_hcz), true, new Runnable() {
                        public void run() {
                            Glsx.GetInstance().OpenApp(40);
                        }
                    });
                } else if (data.equals("DDBOX_OPEN_DDDLJY")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.ddbox_open_ddjy), true, new Runnable() {
                        public void run() {
                            Glsx.GetInstance().OpenApp(12);
                        }
                    });
                } else if (data.equals("DDBOX_CHECK_QFLK")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.ddbox_check_qflk), true, new Runnable() {
                        public void run() {
                            Glsx.GetInstance().CheckLk(0);
                        }
                    });
                }
                if (data.equals("OPEN_RADIO")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_open_radio), true, new Runnable() {
                        public void run() {
                            WinShow.GotoWin(2, 0);
                        }
                    });
                } else if (data.equals("OPEN_NAVI")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_open_navi), true, new Runnable() {
                        public void run() {
                            WinShow.GotoWin(1, 0);
                        }
                    });
                } else if (data.equals("CLOSE_RADIO")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_close_radio), true, new Runnable() {
                        public void run() {
                            if (Iop.GetWorkMode() == 1) {
                                TxzReg.this.BackToLaucher();
                                TxzReg.this.mEvc.evol_workmode_set(0);
                            }
                        }
                    });
                } else if (data.equals("OPEN_AUDIO")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_open_music), true, new Runnable() {
                        public void run() {
                            MainSet.GetInstance();
                            if (!MainSet.IsGLSXVer().booleanValue() || !MainSet.GetInstance().StartKw()) {
                                WinShow.GotoWin(6, 0);
                            }
                        }
                    });
                } else if (data.equals("CLOSE_AUDIO")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_close_music), true, new Runnable() {
                        public void run() {
                            if (Iop.GetWorkMode() == 4 || Iop.GetWorkMode() == 0) {
                                TxzReg.this.BackToLaucher();
                                TxzReg.this.mEvc.evol_workmode_set(0);
                            }
                        }
                    });
                } else if (data.equals("OPEN_VIDEO")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_open_video), true, new Runnable() {
                        public void run() {
                            WinShow.GotoWin(4, 0);
                        }
                    });
                } else if (data.equals("CLOSE_VIDEO")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_close_video), true, new Runnable() {
                        public void run() {
                            if (Iop.GetWorkMode() == 3) {
                                TxzReg.this.mEvc.evol_workmode_set(0);
                                TxzReg.this.BackToLaucher();
                            } else if (Iop.GetWorkMode() == 0 && MainSet.GetInstance().IsHaveApk("com.mxtech.videoplayer.pro")) {
                                tool.GetInstance().killProcess("com.mxtech.videoplayer.pro");
                            }
                        }
                    });
                } else if (data.equals("OPEN_DTV")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_open_cmmb), true, new Runnable() {
                        public void run() {
                            WinShow.GotoWin(8, 0);
                        }
                    });
                } else if (data.equals("CLOSE_DTV")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_close_cmmb), true, new Runnable() {
                        public void run() {
                            if (Iop.GetWorkMode() == 6) {
                                TxzReg.this.BackToLaucher();
                                TxzReg.this.mEvc.evol_workmode_set(0);
                            }
                        }
                    });
                } else if (data.equals("OPEN_DVD")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_open_dvd), true, new Runnable() {
                        public void run() {
                            WinShow.GotoWin(3, 0);
                        }
                    });
                } else if (data.equals("CLOSE_DVD")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_close_dvd), true, new Runnable() {
                        public void run() {
                            if (Iop.GetWorkMode() == 2) {
                                TxzReg.this.mEvc.evol_workmode_set(0);
                                TxzReg.this.BackToLaucher();
                            }
                        }
                    });
                } else if (data.equals("OPEN_DVR")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_open_dvr), true, new Runnable() {
                        public void run() {
                            WinShow.show("com.ts.MainUI", "com.ts.main.dvr.DvrMainActivity");
                        }
                    });
                } else if (data.equals("OPEN_XMLY")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_open_xmly), true, new Runnable() {
                        public void run() {
                            MainSet.GetInstance().StartXMLY();
                        }
                    });
                } else if (data.equals("CLOSE_XMLY")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_close_xmly), true, new Runnable() {
                        public void run() {
                            if (Iop.GetWorkMode() == 0) {
                                TxzReg.this.mEvc.evol_workmode_set(0);
                                TxzReg.this.BackToLaucher();
                            }
                        }
                    });
                } else if (data.equals("OPEN_BT")) {
                    if (BtExe.getBtInstance().isConnected()) {
                        TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_open_bt_music), true, new Runnable() {
                            public void run() {
                                WinShow.GotoWin(7, 3);
                            }
                        });
                        return;
                    }
                    TXZTtsManager.getInstance().speakText("请先连接蓝牙");
                    WinShow.GotoWin(7, 0);
                } else if (data.equals("SINGLE_LOOP")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_no_support_this_cmd), true, new Runnable() {
                        public void run() {
                        }
                    });
                } else if (data.equals("LOOP_ALL")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_no_support_this_cmd), true, new Runnable() {
                        public void run() {
                        }
                    });
                } else if (data.equals("RANDOM_LOOP")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_no_support_this_cmd), true, new Runnable() {
                        public void run() {
                        }
                    });
                } else if (data.equals("MUSIC_FAVOURITE")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_no_support_this_cmd), true, new Runnable() {
                        public void run() {
                        }
                    });
                } else if (data.equals("CANCEL_MUSIC_FAVOURITE")) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin(TxzReg.this.GetResString(R.string.txz_no_support_this_cmd), true, new Runnable() {
                        public void run() {
                        }
                    });
                } else if (data.startsWith("OPEN_PALY_LSIT")) {
                    String strList = cmd.substring(cmd.indexOf("第") + 1, cmd.indexOf("首"));
                    Log.i(TxzReg.TAG, "strList===" + strList);
                    TxzReg.this.nIndex = Integer.parseInt(strList);
                    if (TxzReg.this.nIndex > 0 && TxzReg.this.nIndex <= 30) {
                        TXZResourceManager.getInstance().speakTextOnRecordWin("将为您播放第" + TxzReg.this.nIndex + "首", true, new Runnable() {
                            public void run() {
                                Glsx.GetInstance().PlayIndex(TxzReg.this.nIndex);
                            }
                        });
                    }
                } else if (data.startsWith("OPEN_FM_FREQ#")) {
                    float hz = Float.parseFloat(data.substring("OPEN_FM_FREQ#".length()));
                    if (Iop.GetWorkMode() != 1) {
                        WinShow.GotoWin(2, 0);
                    }
                    int nFm = (int) (100.0f * hz);
                    Log.i(TxzReg.TAG, "ACTION_RECOGNIZE_CMD nFm ==" + nFm);
                    Radio.TuneBandFq(0, nFm);
                } else if (data.startsWith("OPEN_FM_FREQ")) {
                    int index = cmd.indexOf("M");
                    if (index == -1) {
                        index = cmd.indexOf("频");
                    }
                    float hz2 = 87.5f;
                    try {
                        hz2 = Float.parseFloat(cmd.substring(index + 1, cmd.length()).replace("点", "."));
                    } catch (Exception e) {
                        Log.e(TxzReg.TAG, "parseFloat error!");
                    }
                    if (((double) hz2) < 87.5d) {
                        hz2 = 87.5f;
                    }
                    if (hz2 > 108.0f) {
                        hz2 = 108.0f;
                    }
                    TxzReg.this.fhz = hz2;
                    TXZResourceManager.getInstance().speakTextOnRecordWin("将为您调频到FM" + hz2, true, new Runnable() {
                        public void run() {
                            if (Iop.GetWorkMode() != 1) {
                                WinShow.GotoWin(2, 0);
                            }
                            int nFm = (int) (TxzReg.this.fhz * 100.0f);
                            Log.i(TxzReg.TAG, "ACTION_RECOGNIZE_CMD nFm ==" + nFm);
                            Radio.TuneBandFq(0, nFm);
                        }
                    });
                    Log.i(TxzReg.TAG, "OPEN_FM_FREQ===" + data.substring("OPEN_FM_FREQ#".length()) + "  " + hz2);
                } else if (data.startsWith("OPEN_AM_FREQ")) {
                    int index2 = cmd.indexOf("M");
                    if (index2 == -1) {
                        index2 = cmd.indexOf("幅");
                    }
                    String strHz = cmd.substring(index2 + 1, cmd.length());
                    int hz3 = CanCameraUI.BTN_GEELY_YJX6_MODE3;
                    try {
                        hz3 = Integer.parseInt(strHz);
                    } catch (Exception e2) {
                        Log.e(TxzReg.TAG, "parseFloat error!");
                    }
                    if (hz3 < 522) {
                        hz3 = CanCameraUI.BTN_GEELY_YJX6_MODE3;
                    }
                    if (hz3 > 1611) {
                        hz3 = CanCameraUI.BTN_MGZS_WC_MODE2;
                    }
                    TxzReg.this.mHz = hz3;
                    TXZResourceManager.getInstance().speakTextOnRecordWin("将为您调幅到AM" + hz3, true, new Runnable() {
                        public void run() {
                            Radio.TuneBandFq(4, TxzReg.this.mHz);
                        }
                    });
                    Log.i(TxzReg.TAG, "OPEN_AM_FREQ===" + data.substring("OPEN_AM_FREQ#".length()) + "  " + hz3);
                } else if (TxzReg.this.txzExtraCmdDeal != null) {
                    try {
                        TxzReg.this.txzExtraCmdDeal.invoke((Object) null, new Object[]{data});
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void SetVoiceState() {
        TXZSysManager.getInstance().setVolumeMgrTool(new TXZSysManager.VolumeMgrTool() {
            public void mute(boolean bMute) {
                Log.i(TxzReg.TAG, "mute==" + bMute);
                TxzReg.this.mEvc.evol_mut_set(bMute ? 1 : 0);
            }

            public void minVolume() {
                Log.i(TxzReg.TAG, "minVolume");
                TxzReg.this.mEvc.evol_vol_set(0);
            }

            public void maxVolume() {
                Log.i(TxzReg.TAG, "maxVolume");
                TxzReg.this.mEvc.evol_vol_set(TxzReg.this.mEvc.volume_max);
            }

            public void incVolume() {
                Log.i(TxzReg.TAG, "incVolume");
                for (int i = 0; i < 5; i++) {
                    TxzReg.this.mEvc.Evol_vol_tune(1);
                }
            }

            public void decVolume() {
                Log.i(TxzReg.TAG, "decVolume");
                for (int i = 0; i < 5; i++) {
                    TxzReg.this.mEvc.Evol_vol_tune(0);
                }
            }

            public boolean decVolume(int arg0) {
                return false;
            }

            public boolean incVolume(int arg0) {
                return false;
            }

            public boolean isMaxVolume() {
                return false;
            }

            public boolean isMinVolume() {
                return false;
            }

            public boolean setVolume(int arg0) {
                return false;
            }
        });
        TXZSysManager.getInstance().setMuteAllTool(new TXZSysManager.MuteAllTool() {
            public void mute(boolean arg0) {
                Log.i(TxzReg.TAG, "onEndAsr bmute=" + arg0);
                if (arg0) {
                    if (MainSet.GetInstance().IsXT5()) {
                        Evc.GetInstance().evol_navi_set(1, false);
                        return;
                    }
                    MainSet.GetInstance();
                    if (MainSet.IsMkz()) {
                        CanIF.DealSpeakVoice(1);
                    } else {
                        TxzReg.this.mEvc.evol_popmute_set(Iop.GetWorkMode());
                    }
                } else if (!MainSet.GetInstance().IsXT5()) {
                    MainSet.GetInstance();
                    if (MainSet.IsMkz()) {
                        CanIF.DealSpeakVoice(0);
                    } else if ((Radio.GetDispFlag() & 8) == 0 || Iop.GetWorkMode() != 1) {
                        TxzReg.this.mEvc.evol_popmute_clr(Iop.GetWorkMode());
                        Log.i(TxzReg.TAG, "onEndAsr");
                    }
                } else if (Evc.nNaviSpeeking == 0) {
                    Evc.GetInstance().evol_navi_set(0, false);
                }
            }
        });
    }

    public void InintLinMin() {
        if (this.bInintOK) {
            switch (FtSet.GetXuNiDisc()) {
                case 0:
                    TXZConfigManager.getInstance().setWakeupThreshhold(-2.7f);
                    return;
                case 1:
                    TXZConfigManager.getInstance().setWakeupThreshhold(-2.9f);
                    return;
                case 2:
                    TXZConfigManager.getInstance().setWakeupThreshhold(-3.1f);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean bSupportOkGoole() {
        if (this.myContext.getResources().getString(R.string.custom_okgoogle_cmd).equals(MainSet.SP_XPH5)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void RegistUserDefault() {
        TXZAsrManager.AsrComplexSelectCallback myCallback = new TXZAsrManager.AsrComplexSelectCallback() {
            public String getTaskId() {
                return "WAKEUP_TASK";
            }

            public boolean needAsrState() {
                return false;
            }

            public void onCommandSelected(String type, String command) {
                Log.i(TxzReg.TAG, "type= " + type);
                Log.i(TxzReg.TAG, "command= " + command);
                if ("TURNUP_VOL".equals(type)) {
                    TxzReg.this.nVolUp = 5;
                } else if ("TURNDN_VOL".equals(type)) {
                    TxzReg.this.nVolDn = 5;
                } else if ("TURN_MUTE".equals(type)) {
                    TxzReg.this.mEvc.evol_mut_set(1);
                } else if ("PLAY_PREV".equals(type)) {
                    if (Iop.GetWorkMode() != 0) {
                        Mcu.SetCkey(45);
                    } else if (MainSet.nPlayer == 2) {
                        Cyb.GetInstance().playPrevious();
                    } else {
                        TxzReg.this.nKeyDelay = 5;
                        TxzReg.this.nKeyNum = 45;
                    }
                } else if ("PLAY_NEXT".equals(type)) {
                    if (Iop.GetWorkMode() != 0) {
                        Mcu.SetCkey(44);
                    } else if (MainSet.nPlayer == 2) {
                        Cyb.GetInstance().playNext();
                    } else {
                        TxzReg.this.nKeyDelay = 5;
                        TxzReg.this.nKeyNum = 44;
                    }
                } else if ("PLAY_SONG".equals(type)) {
                    TxzReg.this.nKeyDelay = 5;
                    TxzReg.this.nKeyNum = 90;
                } else if ("PAUSE_SONG".equals(type)) {
                    TxzReg.this.nKeyDelay = 5;
                    TxzReg.this.nKeyNum = 91;
                } else if ("TOUCH_CEL".equals(type)) {
                    WinShow.show("com.ts.MainUI", "com.ts.main.touch.TouchActivity");
                } else if (!"DDH_TO_LAUNCHER".equals(type)) {
                    if ("TURN_DTM".equals(type)) {
                        if (Mcu.BklisOn() == 0) {
                            Mcu.BklTurn();
                        }
                    } else if ("TURNOFF_DTM".equals(type)) {
                        if (Mcu.BklisOn() == 1) {
                            Mcu.BklTurn();
                        }
                    } else if ("TURNONGOOGLE".equals(type)) {
                        if (MainSet.GetInstance().IsTwcjw() || TxzReg.this.bSupportOkGoole()) {
                            MainUI.GetInstance().StartGoogleVoice();
                        }
                    } else if ("TURNONVOICE".equals(type)) {
                        MainSet.GetInstance();
                        MainSet.SendIntent("com.kingwaytek.naviking3d.BROADCAST_START_VR");
                    } else if ("TURN_ON_RADIO".equals(type)) {
                        if (FtSet.IsIconExist(1) == 1) {
                            WinShow.GotoWin(2, 0);
                        }
                    } else if ("TURN_OFF_RADIO".equals(type)) {
                        if (Iop.GetWorkMode() == 1) {
                            TxzReg.this.BackToLaucher();
                            TxzReg.this.mEvc.evol_workmode_set(0);
                        }
                    } else if ("TURN_ON_NAVI".equals(type)) {
                        WinShow.GotoWin(1, 0);
                    } else if ("TURN_ON_MUSIC".equals(type)) {
                        MainSet.GetInstance();
                        if (MainSet.IsGLSXVer().booleanValue()) {
                            if (TxzReg.this.myContext != null) {
                                MainSet.GetInstance().openApplication(TxzReg.this.myContext, "cn.kuwo.kwmusiccar");
                            }
                        } else if (FtSet.IsIconExist(4) == 1) {
                            WinShow.GotoWin(6, 0);
                        }
                    } else if ("TURN_OFF_MUSIC".equals(type)) {
                        if (MainSet.IsGLSXVer().booleanValue()) {
                            tool.GetInstance().killProcess("cn.kuwo.kwmusiccar");
                        } else if (Iop.GetWorkMode() == 4 || Iop.GetWorkMode() == 0) {
                            TxzReg.this.BackToLaucher();
                            TxzReg.this.mEvc.evol_workmode_set(0);
                        }
                    } else if ("TURN_ON_KWMUSIC".equals(type)) {
                        if (FtSet.IsIconExist(4) == 1) {
                            WinShow.GotoWin(6, 0);
                        }
                    } else if ("TURN_OFF_KWMUSIC".equals(type)) {
                        if (Iop.GetWorkMode() == 4 || Iop.GetWorkMode() == 0) {
                            TxzReg.this.BackToLaucher();
                            TxzReg.this.mEvc.evol_workmode_set(0);
                        }
                    } else if ("TURN_OFF_NAVI".equals(type)) {
                        AmapAuto.QuiteNavi();
                    } else if ("TURN_ON_VIDEO".equals(type)) {
                        WinShow.GotoWin(4, 0);
                    } else if ("TURN_OFF_VIDEO".equals(type)) {
                        if (Iop.GetWorkMode() == 3) {
                            TxzReg.this.mEvc.evol_workmode_set(0);
                            TxzReg.this.BackToLaucher();
                        } else if (MainSet.GetInstance().IsHaveApk("com.mxtech.videoplayer.pro")) {
                            tool.GetInstance().killProcess("com.mxtech.videoplayer.pro");
                        }
                    } else if ("TURN_TO_MAIN".equals(type)) {
                        Mcu.SetCkey(8);
                    }
                }
                super.onCommandSelected(type, command);
                TXZAsrManager.getInstance().cancel();
            }
        };
        if (bSupportOkGoole()) {
            myCallback.addCommand("TURNONGOOGLE", new String[]{"OK Google", "OK骨狗", "OK酷狗"});
        }
        if (MainSet.GetInstance().IsTwcjw()) {
            myCallback.addCommand("TURNONGOOGLE", new String[]{"OK Google", "OK骨狗", "OK酷狗"});
            myCallback.addCommand("TURNONVOICE", new String[]{"打开声控导航"});
            myCallback.addCommand("TURN_DTM", new String[]{"打开屏幕"});
            myCallback.addCommand("TURNOFF_DTM", new String[]{"关闭屏幕"});
        } else {
            myCallback.addCommand("TURNUP_VOL", new String[]{"调大音量", "调高音量"});
            myCallback.addCommand("TURNDN_VOL", new String[]{"调小音量", "调低音量"});
            if (MainSet.IsGLSXVer().booleanValue()) {
                myCallback.addCommand("PLAY_PREV", new String[]{"上一首", "上一台", "上一曲"});
                myCallback.addCommand("PLAY_NEXT", new String[]{"下一首", "下一台", "下一曲"});
            } else {
                myCallback.addCommand("PLAY_PREV", new String[]{"上一首", "上一台"});
                myCallback.addCommand("PLAY_NEXT", new String[]{"下一首", "下一台"});
            }
            myCallback.addCommand("PLAY_SONG", new String[]{"开始播放"});
            myCallback.addCommand("PAUSE_SONG", new String[]{"暂停播放"});
            myCallback.addCommand("TURN_DTM", new String[]{"打开屏幕"});
            myCallback.addCommand("TURNOFF_DTM", new String[]{"关闭屏幕"});
            myCallback.addCommand("TURN_ON_NAVI", new String[]{"打开导航"});
            myCallback.addCommand("TURN_OFF_NAVI", new String[]{"关闭导航", "退出导航"});
            myCallback.addCommand("TURN_ON_RADIO", new String[]{"打开收音机"});
            myCallback.addCommand("TURN_OFF_RADIO", new String[]{"关闭收音机", "退出收音机"});
            myCallback.addCommand("TURN_ON_MUSIC", new String[]{"打开音乐"});
            myCallback.addCommand("TURN_OFF_MUSIC", new String[]{"关闭音乐", "退出音乐"});
            if (MainSet.IsGLSXVer().booleanValue()) {
                myCallback.addCommand("TURN_ON_KWMUSIC", new String[]{"打开本地音乐"});
                myCallback.addCommand("TURN_OFF_KWMUSIC", new String[]{"关闭本地音乐", "退出本地音乐"});
            }
            myCallback.addCommand("TURN_ON_VIDEO", new String[]{"打开视频"});
            myCallback.addCommand("TURN_OFF_VIDEO", new String[]{"关闭视频", "退出视频"});
            myCallback.addCommand("TURN_TO_MAIN", new String[]{"返回主页"});
        }
        TXZAsrManager.getInstance().useWakeupAsAsr(myCallback);
    }

    /* access modifiers changed from: private */
    public void InitSenceTool() {
        TXZSenceManager.getInstance().setSenceTool(TXZSenceManager.SenceType.SENCE_TYPE_APP, this.mCommandSenceTool);
    }

    public void Inint0(Context m_Context) {
        if (!this.bInintOK && MainSet.GetInstance().IsHaveApk("com.txznet.txz") && AuthServer.GetInstance().IsAuthOk()) {
            byte[] appid = new byte[32];
            Mcu.Getid32(appid);
            byte[] token = new byte[40];
            Mcu.Getid40(token);
            Log.i(TAG, "fiel not exit == " + CanIF.byte2String(appid));
            Log.i(TAG, "fiel not exit ==" + CanIF.byte2String(token));
            if (MainSet.GetInstance().IsCustom("YONG")) {
                Inint(m_Context, MainSet.GetInstance().GetSerid(), "9308b09cf2378c115003ba434585d052", "44585821cb0cb30c77aefd3f920a56b930385271");
            } else {
                Inint(m_Context, MainSet.GetInstance().GetSerid(), CanIF.byte2String(appid), CanIF.byte2String(token));
            }
        }
    }

    public void openGoogleNavi(Context context, String lat, String lng) {
        Intent i = new Intent("android.intent.action.VIEW", Uri.parse(new StringBuffer("google.navigation:q=").append(lat).append(ContactInfo.COMBINATION_SEPERATOR).append(lng).append("&mode=d").toString()));
        i.setPackage("com.google.android.apps.maps");
        this.myContext.startActivity(i);
    }

    /* access modifiers changed from: private */
    public void openGoogleNavi(Poi MyPoi) {
        Log.i(TAG, "openGoogleNavi==" + MyPoi.getLng());
        Intent i = new Intent("android.intent.action.VIEW", Uri.parse(new StringBuffer("google.navigation:q=").append(new StringBuilder().append(MyPoi.getLat()).toString()).append(ContactInfo.COMBINATION_SEPERATOR).append(new StringBuilder().append(MyPoi.getLng()).toString()).append("&mode=d").toString()));
        i.setPackage("com.google.android.apps.maps");
        i.setFlags(268435456);
        this.myContext.startActivity(i);
    }

    /* access modifiers changed from: package-private */
    public void PapaGoNaviToPoi(Poi MyPoi) {
        Log.i(TAG, "MyPoi.getName()==" + MyPoi.getName());
        Log.i(TAG, "MyPoi.getLat()==" + MyPoi.getLat());
        Log.i(TAG, "MyPoi.getLng()==" + MyPoi.getLng());
        Intent intent = new Intent();
        intent.setAction("PAPAGO_BROADCAST_RECV");
        intent.putExtra("KEY_TYPE", 10038);
        intent.putExtra("POINAME", MyPoi.getName());
        intent.putExtra("LAT", MyPoi.getLat());
        intent.putExtra("LON", MyPoi.getLng());
        intent.putExtra("DEV", 0);
        intent.putExtra("STYLE", 0);
        intent.putExtra("SOURCE_APP", "Third App");
        if (this.myContext != null) {
            this.myContext.sendBroadcast(intent);
        }
    }

    /* access modifiers changed from: package-private */
    public void InintNaviManage() {
        Log.i(TAG, "InintNaviManage==");
        TXZNavManager.getInstance().setNavTool((TXZNavManager.NavTool) new TXZNavManager.NavTool() {
            public void setStatusListener(TXZNavManager.NavToolStatusListener Listener) {
                TxzReg.this.myNavStatusListener = Listener;
            }

            public void setHomeLoc(Poi arg0) {
                if (MainSet.GetInstance().IsHaveApk("com.google.android.apps.maps")) {
                    TxzReg.this.openGoogleNavi(arg0);
                    return;
                }
                MainSet.GetInstance().openApplication(TxzReg.this.myContext, "com.papago.s1OBU");
                TxzReg.this.PapaGoNaviToPoi(arg0);
            }

            public void setCompanyLoc(Poi arg0) {
                if (MainSet.GetInstance().IsHaveApk("com.google.android.apps.maps")) {
                    TxzReg.this.openGoogleNavi(arg0);
                    return;
                }
                MainSet.GetInstance().openApplication(TxzReg.this.myContext, "com.papago.s1OBU");
                TxzReg.this.PapaGoNaviToPoi(arg0);
            }

            public void navToLoc(Poi arg0) {
                if (MainSet.GetInstance().IsHaveApk("com.google.android.apps.maps")) {
                    TxzReg.this.openGoogleNavi(arg0);
                    return;
                }
                MainSet.GetInstance().openApplication(TxzReg.this.myContext, "com.papago.s1OBU");
                TxzReg.this.PapaGoNaviToPoi(arg0);
            }

            @Deprecated
            public void navHome() {
            }

            @Deprecated
            public void navCompany() {
            }

            public boolean isInNav() {
                return false;
            }

            public void exitNav() {
                Intent intent = new Intent();
                intent.setAction("PAPAGO_BROADCAST_RECV");
                intent.putExtra("KEY_TYPE", 10010);
                if (TxzReg.this.myContext != null) {
                    TxzReg.this.myContext.sendBroadcast(intent);
                }
            }

            public void enterNav() {
                MainSet.GetInstance().openApplication(TxzReg.this.myContext, "com.papago.s1OBU");
            }

            public void setStatusListener(TXZNavManager.NavToolStatusHighListener arg0) {
            }

            public void speakLimitSpeed() {
            }
        });
    }

    public void Inint(Context m_Context, String mcuid, String app_id, String toke) {
        if (mcuid != null) {
            Log.i(TAG, "txz initialize start==" + mcuid.substring(0, 4));
            Evc.GetInstance().AddNaviWhileList("com.txznet.txz");
            Evc.GetInstance().AddNaviWhileList("com.iflytek.speechcloud");
            Evc.GetInstance().AddNaviWhileList("com.iflytek.vflynote");
            this.myContext = m_Context;
            TXZConfigManager.getInstance().initialize(m_Context, new TXZConfigManager.InitParam(app_id, toke).setAppCustomId(mcuid.substring(0, 4)).setUUID(mcuid).setEnableServiceContact(false).setWakeupKeywordsNew(m_Context.getResources().getStringArray(R.array.txz_sdk_init_wakeup_keywords)).setTtsType(TXZConfigManager.TtsEngineType.TTS_YUNZHISHENG).setAsrType(TXZConfigManager.AsrEngineType.ASR_YUNZHISHENG).setFloatToolType(TXZConfigManager.FloatToolType.FLOAT_NONE), new TXZConfigManager.InitListener() {
                public void onSuccess() {
                    TXZConfigManager.getInstance().setUseHQualityWakeupModel(true);
                    TXZPoiSearchManager.getInstance().setMapPoiViewEnable(false);
                    TXZPoiSearchManager.getInstance().setPoiSearchResultList(true);
                    TXZPoiSearchManager.getInstance().setPoiPlayTipTts(false);
                    TXZMusicManager.getInstance().setShortPlayEnable(false);
                    Log.i(TxzReg.TAG, "txz initialize ok");
                    if (MainSet.GetScreenType() == 3) {
                        TXZRecordWinManager.getInstance().enableFullScreen(false);
                    } else if (MainSet.IsQOROS()) {
                        TXZRecordWinManager.getInstance().enableFullScreen(false);
                    } else {
                        TXZRecordWinManager.getInstance().enableFullScreen(true);
                    }
                    if (TxzReg.this.myContext != null) {
                        if (TxzReg.this.myContext.getResources().getString(R.string.custom_txz_forbiden_wakeup_cmd).equals("0")) {
                            if (MainSet.GetScreenType() != 7) {
                                TxzReg.this.RegistUserDefault();
                            }
                            TXZSceneManager.getInstance().setSceneTool(TXZSceneManager.SceneType.SCENE_TYPE_MUSIC, TxzReg.this.sceneTool);
                        } else {
                            TXZSceneManager.getInstance().setSceneTool(TXZSceneManager.SceneType.SCENE_TYPE_MUSIC, TxzReg.this.sceneTool);
                        }
                    }
                    if (MainSet.GetInstance().IsHaveApk("com.papago.s1OBU") || MainSet.GetInstance().IsHaveApk("com.google.android.apps.maps")) {
                        TxzReg.this.InintNaviManage();
                    }
                    TxzReg.this.bIsConnect = false;
                    if (FtSet.IsIconExist(1) == 1 && MainSet.GetInstance().IsTwcjw()) {
                        TxzReg.this.InintRadio();
                    }
                    TxzReg.this.InintBT();
                    TxzReg.this.SetUserCommand();
                    TXZMusicManager.getInstance().setNotOpenAppPName(new String[]{"com.txznet.music"});
                    if (MainSet.nPlayer == 2) {
                        HFMusicAndRadioTool.getInstance().init(TxzReg.this.myContext, Cyb.GetInstance().mMediaAPI);
                    }
                    TxzReg.this.InitSenceTool();
                    TxzReg.this.SetVoiceState();
                    TxzReg.this.AddListenser();
                    TXZResourceManager.getInstance().setTextResourceString("RS_VOICE_UNKNOW_LOCAL", "识别不了该指令");
                    TxzReg.this.InintLinMin();
                    Log.i(TxzReg.TAG, "txz FtSet.GetXuNiDisc()==" + FtSet.GetXuNiDisc());
                    TXZStatusManager.getInstance().setAudioFocusLogic(new Runnable() {
                        public void run() {
                        }
                    }, new Runnable() {
                        public void run() {
                        }
                    });
                    TxzReg.this.bInintOK = true;
                    BtExe.getBtInstance().UpdatePbMap();
                    if (MainUI.IsCameraMode() == 0) {
                        TxzReg.this.SetTXZState(FtSet.GetSpeechMode());
                    }
                }

                public void onError(int errCode, String errDesc) {
                    MainUI.GetInstance().nDelayToVoice = 200;
                    TxzReg.this.bInintOK = false;
                    Log.i(TxzReg.TAG, "txz initialize faile" + errCode);
                }
            });
        }
    }

    private boolean regCommandForFM(float from, float to, String data) {
        if (from > to) {
            return false;
        }
        float from2 = (float) (Math.floor((double) (10.0f * from)) / 10.0d);
        float to2 = (float) (Math.floor((double) (10.0f * to)) / 10.0d);
        List<String> fres = new ArrayList<>();
        for (float i = from2; ((double) i) <= ((double) to2) + 0.1d; i = (float) (((double) i) + 0.1d)) {
            String str = String.valueOf("调频") + ((float) (Math.floor((double) (10.0f * i)) / 10.0d));
            fres.add(str);
            fres.add(str.replace(".", "点"));
        }
        TXZAsrManager.getInstance().regCommand((Collection<String>) fres, data);
        fres.clear();
        for (int i2 = 88; i2 <= 108; i2++) {
            fres.add(String.valueOf("调频") + i2);
        }
        TXZAsrManager.getInstance().regCommand((Collection<String>) fres, data);
        return true;
    }

    private boolean regCommandForAM(int from, int to, String data) {
        if (from > to) {
            return false;
        }
        List<String> fres = new ArrayList<>();
        for (int i = from; i <= to + 9; i += 9) {
            fres.add(String.valueOf("调幅") + i);
            fres.add(String.valueOf("AM") + i);
        }
        TXZAsrManager.getInstance().regCommand((Collection<String>) fres, data);
        fres.clear();
        return true;
    }

    public void SpeakText(String str) {
        if (this.bInintOK && MainSet.isZh() && str != null) {
            TXZTtsManager.getInstance().speakText(str);
        }
    }
}
