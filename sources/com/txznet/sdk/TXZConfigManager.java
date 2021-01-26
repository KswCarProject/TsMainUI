package com.txznet.sdk;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.Tr.Tr.Tr;
import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.TXZNavManager;
import com.txznet.sdk.TXZService;
import com.txznet.sdk.music.MusicInvokeConstants;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
public class TXZConfigManager {
    public static final int AEC_TYPE_MONO_BY_INNER = 4;
    public static final int AEC_TYPE_MONO_COMPARE_WITH_UDP = 2;
    public static final int AEC_TYPE_NONE = 0;
    public static final int AEC_TYPE_STERO_COMPARE_WITH_LEFT = 3;
    public static final int AEC_TYPE_STERO_COMPARE_WITH_RIGHT = 1;
    public static final int EXT_AUDIOSOURCE_TYPE_MSD = 0;
    public static final int EXT_AUDIOSOURCE_TYPE_TXZ = 1;
    public static final String FME_DELAY = "FMEDelay";
    public static final String FME_ENABLE = "FMEEnable";
    public static final int FT_POSITION_BOTTOM = -3;
    public static final int FT_POSITION_LEFT = -1;
    public static final int FT_POSITION_MIDDLE = -2;
    public static final int FT_POSITION_RIGHT = -3;
    public static final int FT_POSITION_TOP = -1;
    public static final String HAS_REF = "HasRefSignal";
    public static final int INIT_ERROR_ASR = 10001;
    public static final int INIT_ERROR_TTS = 10002;
    public static final int INIT_ERROR_WAKEUP = 10003;
    public static final int MAX_WAKEUP_KEYWORDS_COUNT = 10;
    public static final int MEM_MODE_NONE = 0;
    public static final int MEM_MODE_PREBUILD = 1;
    public static final int MEM_MODE_PREBUILD_MERGE = 2;
    private static TXZConfigManager Tf = new TXZConfigManager();
    public static final String VERSION = "20180731201905_53518";

    /* renamed from: T  reason: collision with root package name */
    ConnectListener f726T = null;
    /* access modifiers changed from: private */
    public ActiveListener T0;
    T T5 = new T();
    String T6;
    Runnable T9 = new Runnable() {
        public void run() {
            Tn.Tr("sdk init connected with txz");
        }
    };
    private Tn.T TA = new Tn.T() {
        public void T(String serviceName) {
            if ("com.txznet.txz".equals(serviceName)) {
                com.txznet.comm.Tr.Tr.Tn.T("txz disconnected");
                boolean unused = TXZConfigManager.this.TV = false;
                if (TXZConfigManager.this.f726T != null) {
                    if (!TXZService.f820T) {
                        TXZConfigManager.this.f726T.onExcepiton();
                    }
                    TXZConfigManager.this.f726T.onDisconnect();
                }
            }
        }

        public void Tr(String serviceName) {
            if ("com.txznet.txz".equals(serviceName)) {
                com.txznet.comm.Tr.Tr.Tn.T("txz connected: initializeSDK");
                TXZConfigManager.this.Ty();
                if (TXZConfigManager.this.f726T != null) {
                    TXZConfigManager.this.f726T.onConnect();
                }
            }
        }
    };
    long TB;
    Boolean TD = null;
    Boolean TE = null;
    long TF;
    Integer TG = null;
    private Map<String, Integer> TI = new ConcurrentHashMap();
    int TK;
    private boolean TL = true;
    /* access modifiers changed from: private */
    public TXZService.T TM = new TXZService.T() {
        public byte[] T(String packageName, String command, byte[] data) {
            if (command.equals("actived")) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        if (TXZConfigManager.this.T0 != null) {
                            TXZConfigManager.this.T0.onFirstActived();
                        }
                    }
                });
            } else if (command.equals("success")) {
                Boolean unused = TXZConfigManager.this.Tb = true;
                TXZConfigManager.this.T();
                TXZPowerManager.getInstance().Tr();
                if (TXZConfigManager.this.Tn != 0 || !TXZConfigManager.this.Ty) {
                    TXZConfigManager.this.Ty = true;
                    TXZConfigManager.this.Tn = 0;
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            if (TXZConfigManager.this.Tx != null) {
                                TXZConfigManager.this.Tx.onSuccess();
                            }
                        }
                    });
                }
            } else if (command.equals("error.asr")) {
                Boolean unused2 = TXZConfigManager.this.Tb = false;
                if (TXZConfigManager.this.Tn != -1 || !TXZConfigManager.this.Ty) {
                    TXZConfigManager.this.Ty = true;
                    TXZConfigManager.this.Tn = -1;
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            if (TXZConfigManager.this.Tx != null) {
                                TXZConfigManager.this.Tx.onError(TXZConfigManager.INIT_ERROR_ASR, "语音识别初始化发生异常");
                            }
                        }
                    });
                }
            } else if (command.equals("error.tts")) {
                Boolean unused3 = TXZConfigManager.this.Tb = false;
                if (TXZConfigManager.this.Tn != -1 || !TXZConfigManager.this.Ty) {
                    TXZConfigManager.this.Ty = true;
                    TXZConfigManager.this.Tn = -1;
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            if (TXZConfigManager.this.Tx != null) {
                                TXZConfigManager.this.Tx.onError(TXZConfigManager.INIT_ERROR_TTS, "语音播报初始化发生异常");
                            }
                        }
                    });
                }
            } else if (command.equals("error.wakeup")) {
                Boolean unused4 = TXZConfigManager.this.Tb = false;
                if (TXZConfigManager.this.Tn != -1 || !TXZConfigManager.this.Ty) {
                    TXZConfigManager.this.Ty = true;
                    TXZConfigManager.this.Tn = -1;
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            if (TXZConfigManager.this.Tx != null) {
                                TXZConfigManager.this.Tx.onError(TXZConfigManager.INIT_ERROR_WAKEUP, "语音唤醒初始化发生异常");
                            }
                        }
                    });
                }
            }
            return null;
        }
    };
    int TN;
    int TO;
    private UIConfigListener TP;
    /* access modifiers changed from: private */
    public boolean TV = false;
    private Runnable TX = new Runnable() {
        public void run() {
            if (!TXZService.f820T || !(TXZPowerManager.f788T == null || TXZPowerManager.f788T.booleanValue())) {
                TXZService.T("sdk.init.", TXZConfigManager.this.TM);
                byte[] param = null;
                if (TXZConfigManager.this.Tr != null) {
                    Tr doc = new Tr();
                    doc.T(MusicInvokeConstants.KEY_PUSH_VERSION, (Object) TXZConfigManager.VERSION);
                    if (TXZConfigManager.this.Tr.f743T != null) {
                        doc.T("appId", (Object) TXZConfigManager.this.Tr.f743T);
                    }
                    if (TXZConfigManager.this.Tr.Tr != null) {
                        doc.T("appToken", (Object) TXZConfigManager.this.Tr.Tr);
                    }
                    if (TXZConfigManager.this.Tr.Ty != null) {
                        doc.T("appCustomId", (Object) TXZConfigManager.this.Tr.Ty);
                    }
                    if (TXZConfigManager.this.Tr.Tn != null) {
                        doc.T("uuid", (Object) TXZConfigManager.this.Tr.Tn);
                    }
                    if (TXZConfigManager.this.Tr.T9 != null) {
                        doc.T("neverFormatRoot", (Object) TXZConfigManager.this.Tr.T9);
                    }
                    if (TXZConfigManager.this.Tr.TE != null) {
                        doc.T("ftType", (Object) TXZConfigManager.this.Tr.TE.name());
                    }
                    if (TXZConfigManager.this.Tr.T5 != null) {
                        doc.T("ftUrl_N", (Object) TXZConfigManager.this.Tr.T5);
                    }
                    if (TXZConfigManager.this.Tr.Tv != null) {
                        doc.T("ftUrl_P", (Object) TXZConfigManager.this.Tr.Tv);
                    }
                    if (TXZConfigManager.this.Tr.Th != null) {
                        doc.T("ftInterval", (Object) TXZConfigManager.this.Tr.Th);
                    }
                    if (TXZConfigManager.this.Tr.T6 != null) {
                        doc.T("ftX", (Object) TXZConfigManager.this.Tr.T6);
                    }
                    if (TXZConfigManager.this.Tr.Te != null) {
                        doc.T("ftY", (Object) TXZConfigManager.this.Tr.Te);
                    }
                    if (TXZConfigManager.this.Tr.TZ != null) {
                        doc.T("asrType", (Object) TXZConfigManager.this.Tr.TZ.name());
                    }
                    if (TXZConfigManager.this.Tr.Tk != null) {
                        doc.T("ttsType", (Object) TXZConfigManager.this.Tr.Tk.name());
                    }
                    if (TXZConfigManager.this.Tr.Tq != null) {
                        doc.T("wakeupKeywords", (Object) TXZConfigManager.this.Tr.Tq);
                    }
                    if (TXZConfigManager.this.Tr.TF != null) {
                        doc.T("jsonScoreKws", (Object) TXZConfigManager.this.Tr.TF);
                    }
                    if (TXZConfigManager.this.Tr.Tj != null) {
                        doc.T("enableInstantAsr", (Object) TXZConfigManager.this.Tr.Tj);
                    }
                    if (TXZConfigManager.this.Tr.TB != null) {
                        doc.T("enableServiceContact", (Object) TXZConfigManager.this.Tr.TB);
                    }
                    if (TXZConfigManager.this.Tr.TK != null) {
                        doc.T("fixCallFunction", (Object) TXZConfigManager.this.Tr.TK);
                    }
                    if (TXZConfigManager.this.Tr.TO != null) {
                        doc.T("defaultNavTool", (Object) TXZConfigManager.this.Tr.TO);
                    }
                    if (TXZConfigManager.this.Tr.TN != null) {
                        doc.T("asrMode", (Object) TXZConfigManager.this.Tr.TN.name());
                    }
                    if (TXZConfigManager.this.Tr.Ts != null) {
                        doc.T("coexistAsrAndWakeup", (Object) TXZConfigManager.this.Tr.Ts);
                    }
                    if (TXZConfigManager.this.Tr.TG != null) {
                        doc.T("wakeupThreshHold", (Object) TXZConfigManager.this.Tr.TG);
                    }
                    if (TXZConfigManager.this.Tr.Tu != null) {
                        doc.T("asrWakeupThreshHold", (Object) TXZConfigManager.this.Tr.Tu);
                    }
                    if (TXZConfigManager.this.Tr.Tt != null) {
                        doc.T("beepTimeOut", (Object) TXZConfigManager.this.Tr.Tt);
                    }
                    if (TXZConfigManager.this.Tr.TD != null) {
                        doc.T("filterNoiseType", (Object) TXZConfigManager.this.Tr.TD);
                    }
                    if (TXZConfigManager.this.Tr.Tf != null) {
                        doc.T("asrServiceMode", (Object) TXZConfigManager.this.Tr.Tf.name());
                    }
                    if (TXZConfigManager.this.Tr.Ta != null) {
                        doc.T("addDefaultMusicType", (Object) TXZConfigManager.this.Tr.Ta);
                    }
                    if (TXZConfigManager.this.Tr.TA != null) {
                        doc.T("ttsVoiceSpeed", (Object) TXZConfigManager.this.Tr.TA);
                    }
                    if (TXZConfigManager.this.Tr.T0 != null) {
                        doc.T("maxAsrRecordTime", (Object) TXZConfigManager.this.Tr.T0);
                    }
                    if (TXZConfigManager.this.Tr.TV != null) {
                        doc.T("zeroVolToast", (Object) TXZConfigManager.this.Tr.TV);
                    }
                    if (TXZConfigManager.this.Tr.Tb != null) {
                        doc.T("txzStream", (Object) TXZConfigManager.this.Tr.Tb);
                    }
                    if (TXZConfigManager.this.Tr.TM != null) {
                        doc.T("useExternalAudioSource", (Object) TXZConfigManager.this.Tr.TM);
                    }
                    if (TXZConfigManager.this.Tr.TX != null) {
                        doc.T("enableBlackHole", (Object) TXZConfigManager.this.Tr.TX);
                    }
                    if (TXZConfigManager.this.Tr.TL != null) {
                        doc.T("audioSourceForRecord", (Object) TXZConfigManager.this.Tr.TL);
                    }
                    if (TXZConfigManager.this.Tr.TP != null) {
                        doc.T("forceStopWkWhenTts", (Object) TXZConfigManager.this.Tr.TP);
                    }
                    if (TXZConfigManager.this.Tr.TI != null) {
                        doc.T("enableProtectWakeup", (Object) TXZConfigManager.this.Tr.TI);
                    }
                    if (TXZConfigManager.this.Tr.Tw != null) {
                        doc.T("extAudioSourceType", (Object) TXZConfigManager.this.Tr.Tw);
                    }
                    if (TXZConfigManager.this.Tr.TC != null) {
                        doc.T("useHQualityWakeupModel", (Object) TXZConfigManager.this.Tr.TC);
                    }
                    if (TXZConfigManager.this.Tr.To != null) {
                        doc.T("extAudioSourcePkg", (Object) TXZConfigManager.this.Tr.To);
                    }
                    if (TXZConfigManager.this.Tr.TQ != null) {
                        doc.T("winType", (Object) TXZConfigManager.this.Tr.TQ);
                    }
                    if (TXZConfigManager.this.Tr.Td != null) {
                        doc.T("dialogTimeout", (Object) TXZConfigManager.this.Tr.Td);
                    }
                    if (TXZConfigManager.this.Tr.Tl != null) {
                        doc.T("resApkPath", (Object) TXZConfigManager.this.Tr.Tl);
                    }
                    if (TXZConfigManager.this.Tr.TT != null) {
                        doc.T("forceUseUI1", (Object) TXZConfigManager.this.Tr.TT);
                    }
                    if (TXZConfigManager.this.Tr.T8 != null) {
                        doc.T("interruptTTSType", (Object) TXZConfigManager.this.Tr.T8.name());
                    }
                    if (TXZConfigManager.this.Tr.TR != null) {
                        doc.T("winBgAlpha", (Object) TXZConfigManager.this.Tr.TR);
                    }
                    if (TXZConfigManager.this.Tr.Tm != null) {
                        doc.T("useLocalNetAsr", (Object) TXZConfigManager.this.Tr.Tm);
                    }
                    if (TXZConfigManager.this.Tr.Ti != null) {
                        doc.T("winRecordImpl", (Object) TXZConfigManager.this.Tr.Ti);
                    }
                    if (!(TXZConfigManager.this.Tr.TU == null || TXZConfigManager.this.Tr.Tg == null)) {
                        doc.T("floatToolWidth", (Object) TXZConfigManager.this.Tr.TU);
                        doc.T("floatToolHeight", (Object) TXZConfigManager.this.Tr.Tg);
                    }
                    if (TXZConfigManager.this.Tr.T2 != null) {
                        doc.T("cancelable", (Object) TXZConfigManager.this.Tr.T2);
                    }
                    if (TXZConfigManager.this.Tr.Tz != null) {
                        doc.T("settingPackageName", (Object) TXZConfigManager.this.Tr.Tz);
                    }
                    if (TXZConfigManager.this.Tr.Tp != null) {
                        doc.T("enableFullScreen", (Object) TXZConfigManager.this.Tr.Tp);
                    }
                    if (TXZConfigManager.this.Tr.TY != null) {
                        doc.T("useRadioAsAudio", (Object) TXZConfigManager.this.Tr.TY);
                    }
                    if (TXZConfigManager.this.Tr.Tc != null) {
                        doc.T("netModule", (Object) TXZConfigManager.this.Tr.Tc);
                    }
                    if (TXZConfigManager.this.Tr.TH != null) {
                        doc.T("aecPreventFalseWakeup", (Object) TXZConfigManager.this.Tr.TH);
                    }
                    if (TXZConfigManager.this.Tr.TS != null) {
                        doc.T("messageDialogType", (Object) TXZConfigManager.this.Tr.TS);
                    }
                    if (TXZConfigManager.this.Tr.T4 != null) {
                        doc.T("needSpeechStateTaskId", (Object) TXZConfigManager.this.Tr.T4);
                    }
                    if (TXZConfigManager.this.Tr.TW != null) {
                        doc.T("memMode", (Object) TXZConfigManager.this.Tr.TW);
                    }
                    if (TXZConfigManager.this.Tr.TJ != null) {
                        doc.T("useTypingEffect", (Object) TXZConfigManager.this.Tr.TJ);
                    }
                    param = doc.Ty();
                }
                com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.sdk.init", param, (Tn.Tr) new Tn.Tr() {
                    public void T(Tn.Ty data) {
                        if (data != null) {
                            boolean unused = TXZConfigManager.this.TV = true;
                            com.txznet.comm.Tr.Tn.Tr().T(TXZConfigManager.this.T9);
                            com.txznet.comm.Tr.Tn.Tr().T(TXZConfigManager.this.T9, 100);
                        }
                    }
                });
                TXZConfigManager.this.T();
            }
        }
    };
    Boolean TZ = null;
    private Boolean Ta = null;
    /* access modifiers changed from: private */
    public Boolean Tb = null;
    ConfigJsonKey[] Te;
    Boolean Th = null;
    long Tj = -1;
    Boolean Tk = null;
    int Tn = -2;
    Object[] Tq;
    InitParam Tr;
    Boolean Ts = null;
    Set<String> Tt = new HashSet();
    Integer Tu = null;
    Boolean Tv = null;
    private String Tw = null;
    /* access modifiers changed from: private */
    public InitListener Tx;
    boolean Ty = false;
    public Integer mHideSettingOptions = null;
    public Boolean mSettingWkWordsEditable = null;

    /* compiled from: Proguard */
    public interface ActiveListener {
        void onFirstActived();
    }

    /* compiled from: Proguard */
    public enum AsrEngineType {
        NONE,
        ASR_IFLY,
        ASR_YUNZHISHENG
    }

    /* compiled from: Proguard */
    public enum AsrMode {
        ASR_MODE_CHAT,
        ASR_MODE_SINGLE,
        ASR_MODE_CONTINUE
    }

    /* compiled from: Proguard */
    public enum AsrServiceMode {
        ASR_SVR_MODE_MIX,
        ASR_SVR_MODE_LOCAL,
        ASR_SVR_MODE_NET,
        ASR_SVR_MODE_AUTO
    }

    /* compiled from: Proguard */
    public enum ConfigJsonKey {
        needPoiMap,
        autoPlayKuwo,
        asrThreshold,
        changeGpsStyle,
        needResetWav,
        showOnWindowManager,
        wmType
    }

    /* compiled from: Proguard */
    public interface ConnectListener {
        void onConnect();

        void onDisconnect();

        void onExcepiton();
    }

    /* compiled from: Proguard */
    public enum FloatToolType {
        FLOAT_TOP,
        FLOAT_NORMAL,
        FLOAT_NONE
    }

    /* compiled from: Proguard */
    public interface InitListener {
        void onError(int i, String str);

        void onSuccess();
    }

    /* compiled from: Proguard */
    public enum InterruptMode {
        INTERRUPT_MODE_DEFAULT,
        INTERRUPT_MODE_ORDER
    }

    /* compiled from: Proguard */
    public enum TtsEngineType {
        NONE,
        TTS_IFLY,
        TTS_YUNZHISHENG,
        TTS_SYSTEM
    }

    /* compiled from: Proguard */
    public interface UIConfigListener extends Tr.T {
        void onConfigChanged(String str);
    }

    /* compiled from: Proguard */
    public interface UserConfigListener {
        void onChangeCommunicationStyle(String str);

        void onChangeWakeupKeywords(String[] strArr);
    }

    /* compiled from: Proguard */
    public interface UserKeywordsCallback {
        void result(String[] strArr);
    }

    private TXZConfigManager() {
    }

    public static TXZConfigManager getInstance() {
        return Tf;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        boolean z;
        boolean z2 = true;
        if (this.Tb != null && this.Tb.booleanValue()) {
            if (this.TE != null) {
                enableWakeup(this.TE.booleanValue());
            }
            if (this.Tv != null) {
                enableChangeWakeupKeywords(this.Tv.booleanValue());
            }
            if (this.TF != 0) {
                setPoiSearchActivityFinishDelay(this.TF);
            }
            if (this.Tj >= 0) {
                setPoiSearchActivityStartNavFinishDelay(this.Tj);
            }
            if (this.TB != 0) {
                setConfirAsrWinDismissDelay(this.TB);
            }
            if (this.TK > 0) {
                setPoiSearchCount(this.TK);
            }
            if (this.Ts != null) {
                setBanSelectListAsr(this.Ts.booleanValue());
            }
            if (this.TO > 0) {
                setPagingBenchmarkCount(this.TO);
            }
            if (this.TN > 0) {
                setMoviePagingBenchmarkCount(this.TN);
            }
            if (!TextUtils.isEmpty(this.T6)) {
                setVersionConfig(this.T6);
            }
            if (this.Te != null) {
                try {
                    setPreferenceConfig(this.Tq, this.Te);
                } catch (IllegalAccessException e) {
                    com.txznet.comm.Tr.Tr.Tn.Tn(e.toString());
                }
            }
            if (this.TZ != null) {
                enableCoverDefaultKeywords(this.TZ.booleanValue());
            }
            if (this.Tk != null) {
                enableWinAnim(this.Tk.booleanValue());
            }
            for (Map.Entry<String, Integer> entry : this.TI.entrySet()) {
                setLogLevel(entry.getKey(), entry.getValue().intValue());
            }
            disableChangeWakeupKeywordsStyle((String) null);
            if (this.TD != null) {
                setStyleBindWithWakeupKeywords(this.TD.booleanValue());
            }
            if (this.TG != null) {
                setChatMaxEmpty(this.TG.intValue());
            }
            if (this.Tu != null) {
                setChatMaxUnknow(this.Tu.intValue());
            }
            if (this.mHideSettingOptions != null) {
                boolean z3 = this.mHideSettingOptions.intValue() % 2 == 1;
                boolean z4 = (this.mHideSettingOptions.intValue() / 2) % 2 == 1;
                boolean z5 = (this.mHideSettingOptions.intValue() / 4) % 2 == 1;
                if ((this.mHideSettingOptions.intValue() / 8) % 2 == 1) {
                    z = true;
                } else {
                    z = false;
                }
                boolean z6 = (this.mHideSettingOptions.intValue() / 16) % 2 == 1;
                if ((this.mHideSettingOptions.intValue() / 32) % 2 != 1) {
                    z2 = false;
                }
                hideSettingOptions(z3, z4, z5, z, z6, z2);
            }
            if (this.mSettingWkWordsEditable != null) {
                enableSettingWkWordsEditable(this.mSettingWkWordsEditable.booleanValue());
            }
            if (!this.TL) {
                setEnableRecording(this.TL);
            }
            if (this.Th != null) {
                enableQueryTrafficTicket(this.Th.booleanValue());
            }
            if (this.Tw != null) {
                setInterruptTips(this.Tw);
            }
            if (this.Ta != null) {
                setNeedHelpFloat(this.Ta.booleanValue());
            }
            com.txznet.comm.Tr.Tr.Tr.T();
            com.txznet.comm.Tr.Tr.Tr.Tr();
            TXZAsrManager.getInstance().T();
            TXZCallManager.getInstance().T();
            TXZCameraManager.getInstance().T();
            TXZLocationManager.getInstance().T();
            TXZMusicManager.getInstance().T();
            TXZAudioManager.getInstance().T();
            TXZNavManager.getInstance().T();
            TXZPoiSearchManager.getInstance().T();
            TXZSenceManager.getInstance().T();
            TXZSceneManager.getInstance().T();
            TXZStatusManager.getInstance().T();
            TXZTtsManager.getInstance().T();
            TXZResourceManager.getInstance().T();
            TXZSysManager.getInstance().T();
            com.txznet.comm.Tr.Tr.T.Tn();
            TXZPowerManager.getInstance().T();
            TXZWechatManager.getInstance().T();
            TXZAsrKeyManager.getInstance().T();
            TXZRecordWinManager.getInstance().T();
            TXZSimManager.getInstance().onReconnectTXZ();
            TXZWheelControlManager.getInstance().T();
            TXZCarControlManager.getInstance().T();
            TXZUpgradeManager.getInstance().T();
            if (com.txznet.T.T.Tr() != null) {
                com.txznet.T.T.Tr().Th();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void Tr() {
        Ty();
    }

    /* compiled from: Proguard */
    public static class InitParam {
        public static final int MESSAGE_DIALOG_TYPE_NORMAL = 0;
        public static final int MESSAGE_DIALOG_TYPE_SMALL = 1;
        public static final int NET_MOUDLE_2G = 3;
        public static final int NET_MOUDLE_3G = 4;
        public static final int NET_MOUDLE_4G = 5;
        public static final int NET_MOUDLE_NONE = 2;
        public static final int WIN_RECORD_IMPL_ACTIVITY = 3;
        public static final int WIN_RECORD_IMPL_LOW_MEMORY = 2;
        public static final int WIN_RECORD_IMPL_NORMAL = 1;
        public static final int WIN_RECORD_IMPL_YIDONG = 4;

        /* renamed from: T  reason: collision with root package name */
        String f743T = null;
        Integer T0 = null;
        Boolean T2 = null;
        String T4 = null;
        String T5 = null;
        Integer T6 = null;
        InterruptMode T8 = null;
        String T9 = null;
        Integer TA = null;
        Boolean TB = null;
        Boolean TC = null;
        Integer TD = null;
        FloatToolType TE = null;
        String TF = null;
        Float TG = null;
        Boolean TH = null;
        Boolean TI = null;
        Boolean TJ = null;
        Boolean TK = null;
        Integer TL = null;
        Boolean TM = null;
        AsrMode TN = null;
        String TO = null;
        Boolean TP = null;
        Integer TQ;
        Float TR = null;
        Integer TS = null;
        Boolean TT = null;
        Integer TU = null;
        Boolean TV = null;
        Integer TW = null;
        Boolean TX = null;
        Boolean TY = null;
        AsrEngineType TZ = null;
        Boolean Ta = null;
        Integer Tb = null;
        Integer Tc = null;
        Integer Td = null;
        Integer Te = null;
        AsrServiceMode Tf = null;
        Integer Tg = null;
        Long Th = null;
        Integer Ti = null;
        Boolean Tj = null;
        TtsEngineType Tk = null;
        String Tl = null;
        Boolean Tm = null;
        String Tn = null;
        String To = null;
        Boolean Tp = null;
        String[] Tq = null;
        String Tr = null;
        Boolean Ts = null;
        Integer Tt = null;
        Float Tu = null;
        String Tv = null;
        Integer Tw = null;
        String Tx = null;
        String Ty = null;
        String Tz;

        public InitParam(String appId, String appToken) {
            this.f743T = appId;
            this.Tr = appToken;
        }

        public InitParam setAppId(String appId) {
            this.f743T = appId;
            return this;
        }

        public InitParam setAppToken(String appToken) {
            this.Tr = appToken;
            return this;
        }

        public InitParam setAppCustomId(String appCustomId) {
            this.Ty = appCustomId;
            return this;
        }

        public InitParam forceUseUI1(boolean use) {
            this.TT = Boolean.valueOf(use);
            return this;
        }

        public InitParam setWinBgAlpha(float alpha) {
            this.TR = Float.valueOf(alpha);
            return this;
        }

        public InitParam enableFullScreen(boolean fullScreen) {
            this.Tp = Boolean.valueOf(fullScreen);
            return this;
        }

        public InitParam setUUID(String uuid) {
            this.Tn = uuid;
            return this;
        }

        public InitParam setNeverFormatRoot(String root) {
            this.T9 = root;
            return this;
        }

        public InitParam setTtsType(TtsEngineType ttsType) {
            this.Tk = ttsType;
            return this;
        }

        @Deprecated
        public InitParam setAsrType(AsrEngineType asrType) {
            this.TZ = asrType;
            return this;
        }

        public InitParam setMessageDialogType(int type) {
            this.TS = Integer.valueOf(type);
            return this;
        }

        public InitParam setFloatToolType(FloatToolType ftType) {
            this.TE = ftType;
            return this;
        }

        public InitParam setFloatToolSize(int width, int height) {
            this.TU = Integer.valueOf(width);
            this.Tg = Integer.valueOf(height);
            return this;
        }

        public InitParam setFloatToolPosition(int x, int y) {
            this.T6 = Integer.valueOf(x);
            this.Te = Integer.valueOf(y);
            return this;
        }

        public InitParam setFloatToolIcon(String ftUrl_N, String ftUrl_P) {
            this.T5 = ftUrl_N;
            this.Tv = ftUrl_P;
            return this;
        }

        public InitParam setFloatToolClickInterval(long interval) {
            this.Th = Long.valueOf(interval);
            return this;
        }

        public InitParam setCancelable(boolean flag) {
            this.T2 = Boolean.valueOf(flag);
            return this;
        }

        public InitParam setWinRecordImpl(int type) {
            this.Ti = Integer.valueOf(type);
            return this;
        }

        public InitParam setWakeupKeywordsNew(String... wakeupKeywords) {
            if (wakeupKeywords == null) {
                wakeupKeywords = new String[0];
            }
            Set<String> setKws = new HashSet<>();
            for (String kw : wakeupKeywords) {
                if (!TextUtils.isEmpty(kw)) {
                    setKws.add(kw);
                }
            }
            if (setKws.size() <= 10) {
                this.Tq = (String[]) setKws.toArray(new String[setKws.size()]);
            }
            return this;
        }

        public InitParam setInstantAsrEnabled(boolean enable) {
            this.Tj = Boolean.valueOf(enable);
            return this;
        }

        public InitParam setWakeupKeyWordsThreshold(String jsonScoreKws) {
            if (jsonScoreKws == null) {
                jsonScoreKws = TXZResourceManager.STYLE_DEFAULT;
            }
            this.TF = jsonScoreKws;
            return this;
        }

        public InitParam setFixCallFunction(boolean fix) {
            this.TK = Boolean.valueOf(fix);
            return this;
        }

        public InitParam setDefaultNavTool(TXZNavManager.NavToolType toolType) {
            this.TO = TXZNavManager.T(toolType);
            return this;
        }

        public InitParam setEnableServiceContact(boolean en) {
            this.TB = Boolean.valueOf(en);
            return this;
        }

        public InitParam setAsrMode(AsrMode mode) {
            this.TN = mode;
            return this;
        }

        public InitParam setCoexistAsrAndWakeup(boolean b) {
            this.Ts = Boolean.valueOf(b);
            return this;
        }

        public InitParam setWakeupThreshhold(float threshHold) {
            this.TG = Float.valueOf(threshHold);
            return this;
        }

        public InitParam setAsrWakeupThreshhold(float threshHold) {
            this.Tu = Float.valueOf(threshHold);
            return this;
        }

        public InitParam setTtsVoiceSpeed(int ttsVoiceSpeed) {
            if (ttsVoiceSpeed < 20) {
                ttsVoiceSpeed = 20;
            } else if (ttsVoiceSpeed > 100) {
                ttsVoiceSpeed = 100;
            }
            this.TA = Integer.valueOf(ttsVoiceSpeed);
            return this;
        }

        public InitParam setDefaultConfig(String mDefaultDoc) {
            if (mDefaultDoc != null) {
                this.Tx = mDefaultDoc;
            }
            return this;
        }

        public InitParam setBeepTimeOut(int timeOut) {
            this.Tt = Integer.valueOf(timeOut);
            return this;
        }

        public InitParam setFilterNoiseType(int filterNoiseType) {
            this.TD = Integer.valueOf(filterNoiseType);
            return this;
        }

        public InitParam setAsrServiceMode(AsrServiceMode asrServiceMode) {
            this.Tf = asrServiceMode;
            return this;
        }

        public InitParam setAddDefaultMusicType(Boolean defaultMusicType) {
            this.Ta = defaultMusicType;
            return this;
        }

        public InitParam setMaxAsrRecordTime(int maxTime) {
            this.T0 = Integer.valueOf(maxTime);
            return this;
        }

        public InitParam enableZeroVolToast(boolean enable) {
            this.TV = Boolean.valueOf(enable);
            return this;
        }

        public InitParam setTxzStream(int stream) {
            this.Tb = Integer.valueOf(stream);
            return this;
        }

        public InitParam setResApkPath(String path) {
            this.Tl = path;
            return this;
        }

        public InitParam useExternalAudioSource(boolean enable) {
            this.TM = Boolean.valueOf(enable);
            return this;
        }

        public InitParam setExtAudioSourceType(int type) {
            this.Tw = Integer.valueOf(type);
            return this;
        }

        public InitParam enableBlackHole(boolean enable) {
            this.TX = Boolean.valueOf(enable);
            return this;
        }

        public InitParam forceStopWkWhenTts(boolean force) {
            this.TP = Boolean.valueOf(force);
            return this;
        }

        public InitParam enableProtectWakeup(boolean enable) {
            this.TI = Boolean.valueOf(enable);
            return this;
        }

        public InitParam setAudioSourceForRecord(int audioSource) {
            this.TL = Integer.valueOf(audioSource);
            return this;
        }

        public InitParam setUseHQualityWakeupModel(boolean useHQualityWakeupModel) {
            this.TC = Boolean.valueOf(useHQualityWakeupModel);
            return this;
        }

        public InitParam setExtAudioSourcePkg(String pkgName) {
            this.To = pkgName;
            return this;
        }

        public InitParam setWinType(Integer winType) {
            this.TQ = winType;
            return this;
        }

        public InitParam setDialogTimeOut(Integer timeout) {
            this.Td = timeout;
            return this;
        }

        public InitParam setInterruptMode(InterruptMode interruptMode) {
            this.T8 = interruptMode;
            return this;
        }

        public InitParam setNetAsr(boolean bLocal) {
            this.Tm = Boolean.valueOf(bLocal);
            return this;
        }

        public InitParam setSettingPackageName(String settingPackageName) {
            this.Tz = settingPackageName;
            return this;
        }

        public InitParam setUseRadioAsAudio(Boolean useRadioAsAudio) {
            this.TY = useRadioAsAudio;
            return this;
        }

        public InitParam setNetModule(int module) {
            this.Tc = Integer.valueOf(module);
            return this;
        }

        public InitParam setAECPreventFalseWakeup(boolean aecPreventFalseWakeup) {
            this.TH = Boolean.valueOf(aecPreventFalseWakeup);
            return this;
        }

        public InitParam setNeedSpeechStateTaskId(String taskId) {
            this.T4 = taskId;
            return this;
        }

        public InitParam setMemMode(int mode) {
            this.TW = Integer.valueOf(mode);
            return this;
        }

        public InitParam setUseTypingEffect(Boolean useTypingEffect) {
            this.TJ = useTypingEffect;
            return this;
        }
    }

    public void setConnectListener(ConnectListener listener) {
        this.f726T = listener;
    }

    public boolean isInitedSuccess() {
        return this.Tb != null && this.Tb.booleanValue();
    }

    public void initialize(Context context, InitListener listener) {
        initialize(context, (InitParam) null, listener);
    }

    public void initialize(Context context, InitParam param, InitListener listener) {
        initialize(context, param, listener, (ActiveListener) null);
    }

    public void initialize(Context context, InitParam param, InitListener listener, ActiveListener activeListener) {
        if (this.Tx == null) {
            this.Tr = param;
            this.Tx = listener;
            this.T0 = activeListener;
            com.txznet.comm.Tr.T.T(context);
            context.startService(new Intent(context, TXZService.class));
            com.txznet.comm.Tr.Tn.Tr().T(this.TA);
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", (Runnable) new Runnable() {
                public void run() {
                    TXZConfigManager.this.Tr();
                }
            });
            if (com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz") != null) {
                Ty();
                return;
            }
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", TXZResourceManager.STYLE_DEFAULT, (byte[]) null, (Tn.Tr) null);
            com.txznet.comm.Tr.T.T.T().Tr();
            com.txznet.comm.Tr.Tr.Tr.Tr();
        }
    }

    /* access modifiers changed from: package-private */
    public void Ty() {
        if (!TXZService.f820T || !(TXZPowerManager.f788T == null || TXZPowerManager.f788T.booleanValue())) {
            com.txznet.comm.Tr.Tn.Tr().T(this.TX);
            com.txznet.comm.Tr.Tn.Tr().T(this.TX, 0);
        }
    }

    public void showFloatTool(FloatToolType type) {
        if (this.Tr != null) {
            this.Tr.setFloatToolType(type);
        }
        com.txznet.comm.Ty.Tr doc = new com.txznet.comm.Ty.Tr();
        doc.T("floatToolType", (Object) type.name());
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.sdk.ft.status.type", doc.toString().getBytes(), (Tn.Tr) null);
    }

    public void setFloatToolClickInterval(long interval) {
        if (this.Tr != null) {
            this.Tr.setFloatToolClickInterval(interval);
        }
        com.txznet.comm.Ty.Tr doc = new com.txznet.comm.Ty.Tr();
        doc.T("ftInterval", (Object) Long.valueOf(interval));
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.sdk.ft.status.interval", doc.toString().getBytes(), (Tn.Tr) null);
    }

    public void setFloatToolIcon(String ftUrl_N, String ftUrl_P) {
        if (this.Tr != null) {
            this.Tr.setFloatToolIcon(ftUrl_N, ftUrl_P);
        }
        com.txznet.comm.Ty.Tr doc = new com.txznet.comm.Ty.Tr();
        doc.T("floatToolUrl_N", (Object) ftUrl_N);
        doc.T("floatToolUrl_P", (Object) ftUrl_P);
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.sdk.ft.status.icon", doc.toString().getBytes(), (Tn.Tr) null);
    }

    @Deprecated
    public void enableSettings(boolean enable) {
        com.txznet.comm.Tr.Tr.Tn.Tn("Deprecated method TXZConfigManager::enableSettings");
    }

    public void enableWinAnim(boolean enable) {
        com.txznet.comm.Tr.Tr.Tn.T("enableWinAnim enable:" + enable);
        this.Tk = Boolean.valueOf(enable);
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.win.enableAnim", (TXZResourceManager.STYLE_DEFAULT + enable).getBytes(), (Tn.Tr) null);
    }

    public void enableCoverDefaultKeywords(boolean enable) {
        com.txznet.comm.Tr.Tr.Tn.T("TXZConfigManager::enableCoverDefaultKeywords, enable=" + enable);
        this.TZ = Boolean.valueOf(enable);
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.wakeup.enableCoverDefaultKeywords", (TXZResourceManager.STYLE_DEFAULT + enable).toString().getBytes(), (Tn.Tr) null);
    }

    public void setWakeupKeywordsNew(String... keywords) {
        if (keywords == null) {
            keywords = new String[0];
        }
        if (this.Tr != null) {
            this.Tr.setWakeupKeywordsNew(keywords);
        }
        try {
            JSONArray json = new JSONArray();
            for (String kw : keywords) {
                if (!TextUtils.isEmpty(kw)) {
                    json.put(kw);
                }
            }
            if (json.length() <= 10) {
                com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.wakeup.update", json.toString().getBytes(), (Tn.Tr) null);
            }
        } catch (Exception e) {
        }
    }

    public void setWakeupKeyWordsThreshold(String jsonScoreKws) {
        if (this.Tr != null) {
            this.Tr.setWakeupKeyWordsThreshold(jsonScoreKws);
        }
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.wakeup.setKwsThreshold", jsonScoreKws.getBytes(), (Tn.Tr) null);
    }

    public void setInstantAsrEnabled(boolean enable) {
        if (this.Tr != null) {
            this.Tr.setInstantAsrEnabled(enable);
        }
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.wakeup.setInstantAsrEnable", String.valueOf(enable).getBytes(), (Tn.Tr) null);
    }

    public void enableWakeup(boolean enable) {
        this.TE = Boolean.valueOf(enable);
        if (enable) {
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.wakeup.start", (byte[]) null, (Tn.Tr) null);
        } else {
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.wakeup.stop", (byte[]) null, (Tn.Tr) null);
        }
    }

    public void setUIConfigListener(UIConfigListener listener) {
        if (this.TP != null) {
            com.txznet.comm.Tr.Tr.Tr.Tr((Tr.T) this.TP);
        }
        if (listener != null) {
            com.txznet.comm.Tr.Tr.Tr.T((Tr.T) listener);
        }
        this.TP = listener;
    }

    /* compiled from: Proguard */
    static class T implements TXZService.T {

        /* renamed from: T  reason: collision with root package name */
        public UserConfigListener f745T = null;

        T() {
        }

        public byte[] T(String packageName, String command, byte[] data) {
            try {
                if ("onChangeWakeupKeywords".equals(command)) {
                    if (this.f745T != null) {
                        this.f745T.onChangeWakeupKeywords((String[]) new com.txznet.comm.Ty.Tr(new String(data)).T("kws", String[].class));
                    }
                } else if ("onChangeCommunicationStyle".equals(command) && this.f745T != null) {
                    this.f745T.onChangeCommunicationStyle(new String(data));
                }
            } catch (Exception e) {
            }
            return null;
        }
    }

    public void setUserConfigListener(UserConfigListener listener) {
        TXZService.T p = null;
        this.T5.f745T = listener;
        if (listener != null) {
            p = this.T5;
        }
        TXZService.T("userconfig.", p);
    }

    public void enableChangeWakeupKeywords(boolean enable) {
        this.Tv = Boolean.valueOf(enable);
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.wakeup.enableChangeWakeupKeywords", (TXZResourceManager.STYLE_DEFAULT + enable).toString().getBytes(), (Tn.Tr) null);
    }

    public void enableQueryTrafficTicket(boolean enable) {
        this.Th = Boolean.valueOf(enable);
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.config.enable.ticket", (enable + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void setLogLevel(String packageName, int level) {
        this.TI.put(packageName, Integer.valueOf(level));
        com.txznet.comm.Tr.Tn.Tr().T(packageName, "comm.log.setConsoleLogLevel", (TXZResourceManager.STYLE_DEFAULT + level).getBytes(), (Tn.Tr) null);
    }

    public void setLogLevel(int level) {
        com.txznet.comm.Tr.Tr.Tn.T(level);
        setLogLevel("com.txznet.txz", level);
        setLogLevel("com.txznet.bluetooth", level);
        setLogLevel("com.txznet.nav", level);
        setLogLevel("com.txznet.music", level);
        setLogLevel("com.txznet.record", level);
        setLogLevel("com.txznet.webchat", level);
    }

    public void showHelpInfos(boolean show) {
        com.txznet.comm.Tr.Tr.Tr.T(show);
        com.txznet.comm.Tr.Tr.Tr.T();
    }

    public void enableCloseWin(boolean enable) {
        com.txznet.comm.Tr.Tr.Tn.T("TXZConfigManager::enableCloseWin, enable=" + enable);
        com.txznet.comm.Tr.Tr.Tr.Ty(enable);
        com.txznet.comm.Tr.Tr.Tr.T();
    }

    public void setWakeupThreshhold(float threshHold) {
        if (this.Tr != null) {
            this.Tr.setWakeupThreshhold(threshHold);
        }
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.wakeup.set.wkscore", (TXZResourceManager.STYLE_DEFAULT + threshHold).toString().getBytes(), (Tn.Tr) null);
    }

    public void setAsrWakeupThreshhold(float threshHold) {
        if (this.Tr != null) {
            this.Tr.setAsrWakeupThreshhold(threshHold);
        }
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.wakeup.set.asrwkscore", (TXZResourceManager.STYLE_DEFAULT + threshHold).toString().getBytes(), (Tn.Tr) null);
    }

    public boolean setDefaultConfig(String jsonConfig) {
        if (jsonConfig != null) {
            try {
                JSONObject data = new JSONObject(jsonConfig);
                if (!data.has("wakeupThreshold") || !data.has("voiceSpeed") || !data.has("wakeupKeywords")) {
                    throw new RuntimeException(jsonConfig + " is not a valid config msg");
                }
                com.txznet.comm.Tr.Tr.Tr.T(new JSONObject(jsonConfig));
                return true;
            } catch (JSONException e) {
                throw new RuntimeException(jsonConfig + " is not a valid config msg");
            }
        } else {
            com.txznet.comm.Tr.Tr.Tr.T((JSONObject) null);
            return true;
        }
    }

    @Deprecated
    public void setVersionConfig(String jsonConfig) {
        this.T6 = jsonConfig;
        if (!TextUtils.isEmpty(jsonConfig)) {
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.config.version.prefer", jsonConfig.getBytes(), (Tn.Tr) null);
        }
    }

    @Deprecated
    public void setPreferenceConfig(Object[] vals, ConfigJsonKey... keys) throws IllegalAccessException {
        if (vals == null || keys == null || vals.length != keys.length) {
            throw new IllegalAccessException("ConfigJsonKey and values should be same count ,or should not null!");
        }
        this.Te = keys;
        this.Tq = vals;
        if (this.Te != null) {
            com.txznet.comm.Ty.Tr jb = new com.txznet.comm.Ty.Tr();
            ConfigJsonKey[] configJsonKeyArr = this.Te;
            int length = configJsonKeyArr.length;
            int i = 0;
            int index = 0;
            while (i < length) {
                jb.T(configJsonKeyArr[i].name(), vals[index]);
                i++;
                index++;
            }
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.config.version.prefer", jb.Ty(), (Tn.Tr) null);
        }
    }

    @Deprecated
    public void setPoiSearchActivityFinishDelay(long finishDelay) {
        this.TF = finishDelay;
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.nav.poi.finish", (this.TF + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    @Deprecated
    public void setPoiSearchActivityStartNavFinishDelay(long finishDelay) {
        this.Tj = finishDelay;
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.nav.poi.afterStartNav.finish", (this.Tj + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void setSelectListTimeout(long delay) {
        this.TF = delay;
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.nav.poi.finish", (this.TF + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void setConfirAsrWinDismissDelay(long delay) {
        this.TB = delay;
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.nav.wx.dismiss", (this.TB + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    @Deprecated
    public void setDisplayLvCount(int count) {
        this.TK = count;
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.selector.show.count", (this.TK + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void setPoiSearchCount(int count) {
        this.TK = count;
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.selector.show.count", (this.TK + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void setPagingBenchmarkCount(int count) {
        this.TO = count;
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.setDisplayCount", (this.TO + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void setMoviePagingBenchmarkCount(int count) {
        this.TN = count;
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.setMovieDisplayCount", (this.TN + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void setBanSelectListAsr(boolean isBanAsr) {
        this.Ts = Boolean.valueOf(isBanAsr);
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.toggleWp", (isBanAsr + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void setBeepTimeOut(int timeOut) {
        if (this.Tr != null) {
            this.Tr.setBeepTimeOut(timeOut);
        }
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "comm.asr.set.beeptimeout", (TXZResourceManager.STYLE_DEFAULT + timeOut).toString().getBytes(), (Tn.Tr) null);
    }

    public void setChatMaxEmpty(int count) {
        this.TG = Integer.valueOf(count);
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "comm.asr.set.MaxEmpty", (TXZResourceManager.STYLE_DEFAULT + count).toString().getBytes(), (Tn.Tr) null);
    }

    public void setChatMaxUnknow(int count) {
        this.Tu = Integer.valueOf(count);
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "comm.asr.set.MaxUnknow", (TXZResourceManager.STYLE_DEFAULT + count).toString().getBytes(), (Tn.Tr) null);
    }

    public void setAsrServiceMode(AsrServiceMode asrServiceMode) {
        if (this.Tr != null) {
            this.Tr.setAsrServiceMode(asrServiceMode);
        }
        if (asrServiceMode == null) {
            asrServiceMode = AsrServiceMode.ASR_SVR_MODE_MIX;
        }
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "comm.asr.set.asrsrvmode", asrServiceMode.name().getBytes(), (Tn.Tr) null);
    }

    public void forceStopWkWhenTts(boolean force) {
        if (this.Tr != null) {
            this.Tr.forceStopWkWhenTts(force);
        }
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.wakeup.forceStopWkWhenTts", (TXZResourceManager.STYLE_DEFAULT + force).getBytes(), (Tn.Tr) null);
    }

    public void disableChangeWakeupKeywordsStyle(String style) {
        synchronized (this.Tt) {
            if (style != null) {
                this.Tt.add(style);
            }
            if (!this.Tt.isEmpty()) {
                String[] ss = (String[]) this.Tt.toArray(new String[this.Tt.size()]);
                com.txznet.comm.Ty.Tr json = new com.txznet.comm.Ty.Tr();
                json.T("style", (Object) ss);
                com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.config.disableChangeWakeupKeywordsStyle", json.Ty(), (Tn.Tr) null);
            }
        }
    }

    public void hideSettingOptions(boolean voiceWake, boolean floatTool, boolean wakeUpWords, boolean sensitivity, boolean ttsSpeed, boolean reset) {
        this.mHideSettingOptions = 0;
        this.mHideSettingOptions = Integer.valueOf(voiceWake ? this.mHideSettingOptions.intValue() | 1 : this.mHideSettingOptions.intValue());
        this.mHideSettingOptions = Integer.valueOf(floatTool ? this.mHideSettingOptions.intValue() | 2 : this.mHideSettingOptions.intValue());
        this.mHideSettingOptions = Integer.valueOf(wakeUpWords ? this.mHideSettingOptions.intValue() | 4 : this.mHideSettingOptions.intValue());
        this.mHideSettingOptions = Integer.valueOf(sensitivity ? this.mHideSettingOptions.intValue() | 8 : this.mHideSettingOptions.intValue());
        this.mHideSettingOptions = Integer.valueOf(ttsSpeed ? this.mHideSettingOptions.intValue() | 16 : this.mHideSettingOptions.intValue());
        this.mHideSettingOptions = Integer.valueOf(reset ? this.mHideSettingOptions.intValue() | 32 : this.mHideSettingOptions.intValue());
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.config.setting.hideOptions", (TXZResourceManager.STYLE_DEFAULT + this.mHideSettingOptions).getBytes(), (Tn.Tr) null);
    }

    public void enableSettingWkWordsEditable(boolean editable) {
        this.mSettingWkWordsEditable = Boolean.valueOf(editable);
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.config.setting.wkwordsEditable", (TXZResourceManager.STYLE_DEFAULT + this.mSettingWkWordsEditable).getBytes(), (Tn.Tr) null);
    }

    public void setStyleBindWithWakeupKeywords(boolean bind) {
        this.TD = Boolean.valueOf(bind);
        com.txznet.comm.Ty.Tr json = new com.txznet.comm.Ty.Tr();
        json.T("bind", (Object) Boolean.valueOf(bind));
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.config.setStyleBindWithWakeupKeywords", json.Ty(), (Tn.Tr) null);
    }

    public void setUseHQualityWakeupModel(boolean useHQualityWakeupModel) {
        if (this.Tr != null) {
            this.Tr.setUseHQualityWakeupModel(useHQualityWakeupModel);
        }
        com.txznet.comm.Ty.Tr json = new com.txznet.comm.Ty.Tr();
        json.T("useHQualityWakeupModel", (Object) Boolean.valueOf(useHQualityWakeupModel));
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "comm.asr.set.useHQualityWakeupModel", json.Ty(), (Tn.Tr) null);
    }

    public void getUserWakeupKeywords(UserKeywordsCallback callback) {
        Tn.Ty data;
        if (callback != null && (data = com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.wakeup.getkeywords", (byte[]) null)) != null) {
            String json = data.T();
            if (TextUtils.isEmpty(json)) {
                callback.result((String[]) null);
                return;
            }
            try {
                JSONObject job = new JSONObject(json);
                if (job.has("keywords")) {
                    String keywords = job.getString("keywords");
                    JSONArray jry = new JSONArray(keywords);
                    String[] strKeywords = new String[jry.length()];
                    for (int i = 0; i < strKeywords.length; i++) {
                        strKeywords[i] = jry.getString(i);
                    }
                    com.txznet.comm.Tr.Tr.Tn.T("leng keywords json::" + keywords);
                    callback.result(strKeywords);
                    return;
                }
            } catch (JSONException e) {
            }
            callback.result((String[]) null);
        }
    }

    public void setEnableRecording(boolean enable) {
        this.TL = enable;
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.config.enableRecording", (TXZResourceManager.STYLE_DEFAULT + this.TL).getBytes(), (Tn.Tr) null);
    }

    public void setInterruptTips(String text) {
        if (!TextUtils.isEmpty(text)) {
            this.Tw = text;
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.config.setInterruptTips", text.getBytes(), (Tn.Tr) null);
        }
    }

    public void setNeedHelpFloat(boolean needFloat) {
        this.Ta = Boolean.valueOf(needFloat);
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.config.setNeedHelpFloat", (needFloat + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }
}
