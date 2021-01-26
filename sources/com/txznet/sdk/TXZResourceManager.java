package com.txznet.sdk;

import android.os.SystemClock;
import android.text.TextUtils;
import com.Tn.Tr.TE.T;
import com.android.SdkConstants;
import com.ts.can.bmw.mini.CanBMWMiniServiceDetailActivity;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.TXZService;
import com.txznet.sdk.bean.Poi;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
public class TXZResourceManager {
    public static final String STYLE_DEFAULT = "";
    public static final String STYLE_KING = "KING";
    private static int Tv = 1;
    private static TXZResourceManager Ty = new TXZResourceManager();

    /* renamed from: T  reason: collision with root package name */
    String f797T = null;
    /* access modifiers changed from: private */
    public Map<Long, Runnable> T5 = new HashMap();
    private RecordWin T9 = null;
    private Boolean TE;
    private HelpWin TZ;
    private boolean Tk = false;
    private boolean Tn = false;
    boolean Tr;
    public String mAllResourceData = null;
    public String mAllResourceFile = null;
    public String mUpdateResourceData = null;

    /* compiled from: Proguard */
    public enum AsrScene {
        PoiScene,
        CallScene,
        MusicScene
    }

    /* compiled from: Proguard */
    public enum AsrSence {
        PoiSence,
        CallSence
    }

    /* compiled from: Proguard */
    public interface HelpWin {
        void close();

        void show();
    }

    /* compiled from: Proguard */
    public interface RecordWin {

        /* compiled from: Proguard */
        public enum RecordStatus {
            STATUS_IDLE,
            STATUS_RECORDING,
            STATUS_RECOGONIZING
        }

        /* compiled from: Proguard */
        public interface RecordWinOperateListener {

            /* compiled from: Proguard */
            public enum ClickType {
                PREPAGE,
                NEXTPAGE
            }

            /* compiled from: Proguard */
            public enum ListType {
                ContactList,
                PoiList,
                WxContactList,
                AudioList,
                CommList
            }

            void onClickCancel();

            void onClickHelpIcon();

            void onClickSure();

            void onClose();

            void onDisplayLvOnTouchListener(int i);

            void onDisplayPageClick(ListType listType, ClickType clickType);

            void onScreenSupportCount(int i);

            void onSelectContact(int i);

            void onSelectItemRight(int i);

            void onSelectListItem(ListType listType, int i, String str);

            void onSelectWxContact(int i);

            void onTouch();

            void useDefaultSelector(boolean z);
        }

        void close();

        void onProgressChanged(int i);

        void onStatusChange(RecordStatus recordStatus);

        void onVolumeChange(int i);

        void open();

        void setOperateListener(RecordWinOperateListener recordWinOperateListener);

        void showAddressChoice(String str);

        void showAudioChoice(String str);

        void showContactChoice(String str);

        void showData(String str);

        void showListChoice(int i, String str);

        void showStockInfo(String str);

        void showSysText(String str);

        void showUsrPartText(String str);

        void showUsrText(String str);

        void showWheatherInfo(String str);

        void showWxContactChoice(String str);

        void snapPager(boolean z);
    }

    /* compiled from: Proguard */
    public interface WinConfirmAsrListener {
        void onClickCancel();

        void onClickOk();
    }

    private TXZResourceManager() {
    }

    public static TXZResourceManager getInstance() {
        return Ty;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        if (this.f797T != null) {
            setVoiceStyle(this.f797T);
        }
        if (this.mAllResourceFile != null) {
            Tn.Tr().T("com.txznet.txz", "txz.resource.replaceResourceFile", this.mAllResourceFile.getBytes(), (Tn.Tr) null);
        }
        if (this.mAllResourceData != null) {
            Tn.Tr().T("com.txznet.txz", "txz.resource.replaceResource", this.mAllResourceData.getBytes(), (Tn.Tr) null);
        }
        if (this.mUpdateResourceData != null) {
            Tn.Tr().T("com.txznet.txz", "txz.resource.updateResource", this.mUpdateResourceData.getBytes(), (Tn.Tr) null);
        }
        if (this.Tn) {
            if (this.T9 == null) {
                Tn.Tr().T("com.txznet.txz", "txz.record.win.clear", (byte[]) null, (Tn.Tr) null);
            } else {
                com.txznet.comm.Tr.Tr.Tn.T("mHasSetHudRecordWin:" + this.Tr);
                if (this.Tr) {
                    Tn.Tr().T("com.txznet.txz", "txz.record.win.prepare.hud", SdkConstants.VALUE_TRUE.getBytes(), (Tn.Tr) null);
                } else {
                    Tn.Tr().T("com.txznet.txz", "txz.record.win.prepare.hud", SdkConstants.VALUE_FALSE.getBytes(), (Tn.Tr) null);
                }
                setRecordWin(this.T9);
            }
        }
        if (this.Tk) {
            setHelpWin(this.TZ);
        }
        if (this.TE != null) {
            setRecordWin2PoiNoResultMsgType(this.TE.booleanValue());
        }
    }

    public void setVoiceStyle(String style) {
        if (style == null) {
            style = STYLE_DEFAULT;
        }
        Tn.Tr().T("com.txznet.txz", "txz.resource.setStyle", style.getBytes(), (Tn.Tr) null);
    }

    public void loadResourceFile(String path, boolean all) {
        if (all) {
            this.mAllResourceFile = path;
            this.mAllResourceData = null;
            this.mUpdateResourceData = null;
            Tn.Tr().T("com.txznet.txz", "txz.resource.replaceResourceFile", this.mAllResourceFile.getBytes(), (Tn.Tr) null);
            return;
        }
        try {
            File f = new File(path);
            FileInputStream in = new FileInputStream(path);
            byte[] bs = new byte[((int) f.length())];
            in.read(bs);
            in.close();
            loadResourceData(new String(bs), all);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadResourceData(JSONObject data, boolean all) {
        loadResourceData(data.toString(), all);
    }

    private void T(JSONObject tar, JSONObject data) {
        JSONObject n;
        try {
            Iterator<String> it = data.keys();
            if (it != null) {
                while (it.hasNext()) {
                    String k = it.next();
                    Object v = data.get(k);
                    if (v instanceof JSONObject) {
                        if (tar.has(k)) {
                            Object old = tar.get(k);
                            if (!(old instanceof JSONObject)) {
                                JSONObject t = new JSONObject();
                                t.put(STYLE_DEFAULT, old);
                                tar.put(k, t);
                            }
                            n = tar.getJSONObject(k);
                        } else {
                            n = new JSONObject();
                        }
                        T(n, (JSONObject) v);
                        tar.put(k, n);
                    } else {
                        tar.put(k, v);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public void loadResourceData(String data, boolean all) {
        if (all) {
            this.mAllResourceFile = null;
            this.mAllResourceData = data;
            this.mUpdateResourceData = null;
            Tn.Tr().T("com.txznet.txz", "txz.resource.replaceResource", this.mAllResourceData.getBytes(), (Tn.Tr) null);
            return;
        }
        Tr jsonOld = new Tr(this.mUpdateResourceData);
        T(jsonOld.T(), new Tr(data).T());
        this.mUpdateResourceData = jsonOld.toString();
        Tn.Tr().T("com.txznet.txz", "txz.resource.updateResource", this.mUpdateResourceData.getBytes(), (Tn.Tr) null);
    }

    public void setTextResourceString(String resId, String style, String data) {
        try {
            JSONObject json = new JSONObject();
            json.put(style, data);
            JSONObject j = new JSONObject();
            j.put(resId, json);
            loadResourceData(j.toString(), false);
        } catch (Exception e) {
        }
    }

    public void setTextResourceString(String resId, String style, String[] data) {
        try {
            Tr array = new Tr();
            array.T(style, (Object) data);
            JSONObject json = new JSONObject(array.toString());
            JSONObject j = new JSONObject();
            j.put(resId, json);
            loadResourceData(j.toString(), false);
        } catch (Exception e) {
        }
    }

    public void setTextResourceString(String resId, String data) {
        Tr json = new Tr();
        json.T(resId, (Object) data);
        loadResourceData(json.toString(), false);
    }

    public void setTextResourceString(String resId, String[] data) {
        Tr json = new Tr();
        json.T(resId, (Object) data);
        loadResourceData(json.toString(), false);
    }

    public void setTextResourceString(String jsonData) {
        loadResourceData(jsonData, false);
    }

    public void setRecordWin(RecordWin win) {
        setRecordWin(win, false);
    }

    public void setRecordWin(final RecordWin win, boolean reserveInner) {
        this.Tn = true;
        this.Tr = false;
        this.T9 = win;
        if (this.T9 == null) {
            Tn.Tr().T("com.txznet.txz", "txz.record.win.clear", (byte[]) null, (Tn.Tr) null);
            return;
        }
        win.setOperateListener(new RecordWin.RecordWinOperateListener() {
            /* access modifiers changed from: private */
            public boolean Tr = false;

            public void onTouch() {
                Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.button.pause", (byte[]) null, (Tn.Tr) null);
            }

            public void onSelectContact(int index) {
                Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.item.selected", new Tr().T(CanBMWMiniServiceDetailActivity.KEY_INDEX, (Object) Integer.valueOf(index)).T("type", (Object) 0).toString().getBytes(), (Tn.Tr) null);
            }

            public void onSelectWxContact(int index) {
                Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.item.selected", new Tr().T(CanBMWMiniServiceDetailActivity.KEY_INDEX, (Object) Integer.valueOf(index)).T("type", (Object) 1).toString().getBytes(), (Tn.Tr) null);
            }

            public void onClose() {
                this.Tr = false;
                Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.dismiss", (byte[]) null, (Tn.Tr) null);
            }

            public void onClickSure() {
                Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.button.ok", (byte[]) null, (Tn.Tr) null);
            }

            public void onClickCancel() {
                Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.button.cancel", new Tr().T("type", (Object) 0).toString().getBytes(), (Tn.Tr) null);
            }

            public void onClickHelpIcon() {
                this.Tr = true;
                Tn.Tr().T("com.txznet.txz", "txz.help.ui.detail.open", (byte[]) null, (Tn.Tr) new Tn.Tr() {
                    public void T(Tn.Ty data) {
                        boolean unused = AnonymousClass1.this.Tr = false;
                    }
                });
            }

            public void onSelectListItem(RecordWin.RecordWinOperateListener.ListType listType, int index, String speech) {
                int type = -1;
                switch (AnonymousClass6.f804T[listType.ordinal()]) {
                    case 1:
                        type = 0;
                        break;
                    case 2:
                        type = 1;
                        break;
                    case 3:
                        type = 4;
                        break;
                    case 4:
                        type = 5;
                        break;
                }
                if (type != -1) {
                    Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.item.selected", new Tr().T(CanBMWMiniServiceDetailActivity.KEY_INDEX, (Object) Integer.valueOf(index)).T("type", (Object) Integer.valueOf(type)).T("speech", (Object) speech).toString().getBytes(), (Tn.Tr) null);
                }
            }

            public void onScreenSupportCount(int count) {
                Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.display.count", (count + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
            }

            public void useDefaultSelector(boolean useDefault) {
                Tn.Tr().T("com.txznet.txz", "txz.selector.useNewSelector", (useDefault + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
            }

            public void onSelectItemRight(int position) {
                Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.item.right", (position + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
            }

            public void onDisplayLvOnTouchListener(int motionEventAction) {
                Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.list.ontouch", (motionEventAction + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
            }

            public void onDisplayPageClick(RecordWin.RecordWinOperateListener.ListType eventType, RecordWin.RecordWinOperateListener.ClickType type) {
                Tr jb = new Tr();
                if (eventType == RecordWin.RecordWinOperateListener.ListType.AudioList || eventType == RecordWin.RecordWinOperateListener.ListType.PoiList || eventType == RecordWin.RecordWinOperateListener.ListType.WxContactList || eventType == RecordWin.RecordWinOperateListener.ListType.CommList) {
                    jb.T("type", (Object) 1);
                }
                if (type == RecordWin.RecordWinOperateListener.ClickType.PREPAGE) {
                    jb.T("clicktype", (Object) 1);
                } else {
                    jb.T("clicktype", (Object) 2);
                }
                Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.display.page", jb.Ty(), (Tn.Tr) null);
            }
        });
        TXZService.T("win.record.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if (command.equals("show")) {
                    win.open();
                } else if (command.equals("dismiss")) {
                    win.close();
                } else if (command.equals("status")) {
                    Integer status = (Integer) new Tr(data).T("status", Integer.class);
                    if (status != null) {
                        if (status.intValue() == 1) {
                            win.onStatusChange(RecordWin.RecordStatus.STATUS_RECORDING);
                        } else if (status.intValue() == 2) {
                            win.onStatusChange(RecordWin.RecordStatus.STATUS_RECOGONIZING);
                        } else {
                            win.onStatusChange(RecordWin.RecordStatus.STATUS_IDLE);
                        }
                    }
                } else if (command.equals("volume")) {
                    Integer volume = (Integer) new Tr(data).T("volume", Integer.class);
                    if (volume != null) {
                        win.onVolumeChange(volume.intValue());
                    }
                } else if (command.equals("progress")) {
                    Integer progress = (Integer) new Tr(data).T("progress", Integer.class);
                    if (progress != null) {
                        win.onProgressChanged(progress.intValue());
                    }
                } else if (command.equals("chat.sys")) {
                    win.showSysText((String) new Tr(data).T(SdkConstants.ATTR_TEXT, String.class));
                } else if (command.equals("chat.usr")) {
                    win.showUsrText((String) new Tr(data).T(SdkConstants.ATTR_TEXT, String.class));
                } else if (command.equals("chat.usr_part")) {
                    win.showUsrPartText((String) new Tr(data).T(SdkConstants.ATTR_TEXT, String.class));
                } else if (command.equals("data")) {
                    if (data != null) {
                        win.showData(new String(data));
                    }
                } else if (command.equals("list")) {
                    if (data != null) {
                        try {
                            Integer type = (Integer) new Tr(data).T("type", Integer.class);
                            if (type == null || type.intValue() == 0) {
                                if (type != null && type.intValue() == 0) {
                                    win.showContactChoice(new String(data));
                                }
                            } else if (type.intValue() == 2) {
                                win.showAddressChoice(new String(data));
                                return null;
                            } else if (type.intValue() == 1) {
                                win.showWxContactChoice(new String(data));
                                return null;
                            } else if (type.intValue() == 4) {
                                win.showAudioChoice(new String(data));
                                return null;
                            } else {
                                win.showListChoice(type.intValue(), new String(data));
                                return null;
                            }
                        } catch (Exception e) {
                        }
                    }
                } else if (command.equals("list.pager")) {
                    win.snapPager(Boolean.valueOf(Boolean.parseBoolean(new String(data))).booleanValue());
                    return null;
                } else if (command.equals("stock")) {
                    if (data != null) {
                        try {
                            T.C0003T info = T.C0003T.T(data);
                            JSONObject jObj = new JSONObject();
                            jObj.put("strName", info.Tr);
                            jObj.put("strCode", info.Ty);
                            jObj.put("strUrl", info.Tn);
                            jObj.put("strCurrentPrice", info.T9);
                            jObj.put("strChangeAmount", info.Tk);
                            jObj.put("strChangeRate", info.TZ);
                            jObj.put("strHighestPrice", info.TE);
                            jObj.put("strLowestPrice", info.T5);
                            jObj.put("strTradingVolume", info.Tv);
                            jObj.put("strYestodayClosePrice", info.Th);
                            jObj.put("strTodayOpenPrice", info.T6);
                            jObj.put("strUpdateTime", info.Te);
                            win.showStockInfo(jObj.toString());
                        } catch (Exception e2) {
                        }
                    }
                } else if (command.equals("weather") && data != null) {
                    try {
                        T.Ty info2 = T.Ty.T(data);
                        JSONObject jObj2 = new JSONObject();
                        jObj2.put("strCityName", info2.Tr);
                        jObj2.put("uint32FocusIndex", info2.Ty);
                        T.Tr[] weatherDatas = info2.Tn;
                        JSONArray jWeatherArr = new JSONArray();
                        for (T.Tr weatherData : weatherDatas) {
                            JSONObject jWeather = new JSONObject();
                            jWeather.put("uint32Year", weatherData.Tr);
                            jWeather.put("uint32Month", weatherData.Ty);
                            jWeather.put("uint32Day", weatherData.Tn);
                            jWeather.put("uint32DayOfWeek", weatherData.T9);
                            jWeather.put("strWeather", weatherData.Tk);
                            jWeather.put("int32CurTemperature", weatherData.TZ);
                            jWeather.put("int32LowTemperature", weatherData.TE);
                            jWeather.put("int32HighTemperature", weatherData.T5);
                            jWeather.put("int32Pm25", weatherData.Tv);
                            jWeather.put("strAirQuality", weatherData.Th);
                            jWeather.put("strWind", weatherData.T6);
                            jWeather.put("strCarWashIndex", weatherData.Te);
                            jWeather.put("strCarWashIndexDesc", weatherData.Tq);
                            jWeather.put("strTravelIndex", weatherData.TF);
                            jWeather.put("strTravelIndexDesc", weatherData.Tj);
                            jWeather.put("strSportIndex", weatherData.TB);
                            jWeather.put("strSportIndexDesc", weatherData.TK);
                            jWeather.put("strSuggest", weatherData.TO);
                            jWeather.put("strComfortIndex", weatherData.TN);
                            jWeather.put("strComfortIndexDesc", weatherData.Ts);
                            jWeather.put("strColdIndex", weatherData.TG);
                            jWeather.put("strColdIndexDesc", weatherData.Tu);
                            jWeather.put("strMorningExerciseIndex", weatherData.Tt);
                            jWeather.put("strMorningExerciseIndexDesc", weatherData.TD);
                            jWeather.put("strDressIndex", weatherData.Tf);
                            jWeather.put("strDressIndexDesc", weatherData.TA);
                            jWeather.put("strUmbrellaIndex", weatherData.Tx);
                            jWeather.put("strUmbrellaIndexDesc", weatherData.T0);
                            jWeather.put("strSunBlockIndex", weatherData.TV);
                            jWeather.put("strSunBlockIndexDesc", weatherData.Tb);
                            jWeather.put("strDryingIndex", weatherData.TM);
                            jWeather.put("strDryingIndexDesc", weatherData.TX);
                            jWeather.put("strDatingIndex", weatherData.TP);
                            jWeather.put("strDatingIndexDesc", weatherData.TI);
                            jWeatherArr.put(jWeather);
                        }
                        jObj2.put("rptMsgWeather", jWeatherArr);
                        win.showWheatherInfo(jObj2.toString());
                    } catch (Exception e3) {
                    }
                }
                return null;
            }
        });
        Tr cfg = new Tr();
        cfg.T("reserveInner", (Object) Boolean.valueOf(reserveInner));
        Tn.Tr().T("com.txznet.txz", "txz.record.win.prepare", cfg.Ty(), (Tn.Tr) null);
        com.txznet.comm.Tr.Tr.Tn.T("txz.record.win.prepare.hud.false");
        Tn.Tr().T("com.txznet.txz", "txz.record.win.prepare.hud", SdkConstants.VALUE_FALSE.getBytes(), (Tn.Tr) null);
    }

    public void setHudRecordWin(RecordWin recordWin) {
        this.Tn = true;
        this.T9 = recordWin;
        setRecordWin(recordWin);
        this.Tr = true;
        com.txznet.comm.Tr.Tr.Tn.T("txz.record.win.prepare.hud.true");
        Tn.Tr().T("com.txznet.txz", "txz.record.win.prepare.hud", SdkConstants.VALUE_TRUE.getBytes(), (Tn.Tr) null);
    }

    public void setHelpWin(final HelpWin helpWin) {
        this.Tk = true;
        this.TZ = helpWin;
        if (this.TZ == null) {
            Tn.Tr().T("com.txznet.txz", "txz.help.win.clear", (byte[]) null, (Tn.Tr) null);
            return;
        }
        TXZService.T("help.win.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if ("show".equals(command)) {
                    helpWin.show();
                    return null;
                } else if (!"dismiss".equals(command)) {
                    return null;
                } else {
                    helpWin.close();
                    return null;
                }
            }
        });
        Tn.Tr().T("com.txznet.txz", "txz.help.win.set", (byte[]) null, (Tn.Tr) null);
    }

    public void cancelCloseRecordWin() {
        Tn.Tr().T("com.txznet.txz", "txz.record.win.cancelClose", (byte[]) null, (Tn.Tr) null);
    }

    public void enterSpecifyAsrSence(AsrSence asrSence) {
        int sence = 0;
        switch (asrSence) {
            case PoiSence:
                sence = 1;
                break;
            case CallSence:
                sence = 2;
                break;
        }
        Tn.Tr().T("com.txznet.txz", "txz.record.win.enterSpecifyAsrSence", (STYLE_DEFAULT + sence).getBytes(), (Tn.Tr) null);
    }

    /* renamed from: com.txznet.sdk.TXZResourceManager$6  reason: invalid class name */
    /* compiled from: Proguard */
    static /* synthetic */ class AnonymousClass6 {

        /* renamed from: T  reason: collision with root package name */
        static final /* synthetic */ int[] f804T = new int[RecordWin.RecordWinOperateListener.ListType.values().length];

        static {
            Ty = new int[AsrScene.values().length];
            try {
                Ty[AsrScene.PoiScene.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                Ty[AsrScene.CallScene.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                Ty[AsrScene.MusicScene.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            Tr = new int[AsrSence.values().length];
            try {
                Tr[AsrSence.PoiSence.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                Tr[AsrSence.CallSence.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f804T[RecordWin.RecordWinOperateListener.ListType.ContactList.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f804T[RecordWin.RecordWinOperateListener.ListType.WxContactList.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f804T[RecordWin.RecordWinOperateListener.ListType.AudioList.ordinal()] = 3;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f804T[RecordWin.RecordWinOperateListener.ListType.PoiList.ordinal()] = 4;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public void enterSpecifyAsrScene(AsrScene asrScene, String hintText, boolean keepScene, boolean needSpeak, String data) {
        int scene = 0;
        switch (asrScene) {
            case PoiScene:
                scene = 1;
                break;
            case CallScene:
                scene = 2;
                break;
            case MusicScene:
                scene = 3;
                break;
        }
        Tr jObj = new Tr();
        jObj.T("scene", (Object) Integer.valueOf(scene));
        jObj.T("hintText", (Object) hintText);
        jObj.T("keepScene", (Object) Boolean.valueOf(keepScene));
        jObj.T("needSpeak", (Object) Boolean.valueOf(needSpeak));
        jObj.T("data", (Object) data);
        Tn.Tr().T("com.txznet.txz", "txz.record.win.enterSpecifyAsrScene", jObj.Ty(), (Tn.Tr) null);
    }

    public void onPageInfoClick(int eventType, int clickType) {
        Tr jb = new Tr();
        jb.T("type", (Object) Integer.valueOf(eventType));
        jb.T("clicktype", (Object) Integer.valueOf(clickType));
        Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.display.page", jb.Ty(), (Tn.Tr) null);
    }

    public void dissmissRecordWin() {
        Tn.Tr().T("com.txznet.txz", "txz.record.win.dissmiss", (byte[]) null, (Tn.Tr) null);
    }

    public void dismissHelpWin() {
        Tn.Tr().T("com.txznet.txz", "txz.record.win.closeHelpWin", (byte[]) null, (Tn.Tr) null);
    }

    public void showSysText(String text) {
        Tn.Tr().T("com.txznet.txz", "txz.record.win.showSysText", text.getBytes(), (Tn.Tr) null);
    }

    public void showPoiList(List<Poi> pois, String city, String keywords) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", 1);
            jsonObject.put("city", city);
            jsonObject.put("keywords", keywords);
            if (pois != null && pois.size() > 0) {
                JSONArray jsonArray = new JSONArray();
                for (Poi poi : pois) {
                    jsonArray.put(poi.toJsonObject());
                }
                jsonObject.put("pois", jsonArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.ui.showList", jsonObject.toString().getBytes(), (Tn.Tr) null);
    }

    public void setRecordWin2PoiNoResultMsgType(boolean justText) {
        this.TE = Boolean.valueOf(justText);
        Tn.Tr().T("com.txznet.txz", "txz.record.ui.event.poinoresult", (justText + STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void speakTextOnRecordWin(String text, boolean close, Runnable endRunnable) {
        speakTextOnRecordWin(STYLE_DEFAULT, text, close, endRunnable);
    }

    public void speakTextOnRecordWin(String resId, String text, boolean close, Runnable oRun) {
        speakTextOnRecordWin(resId, text, close, true, oRun);
    }

    public void speakTextOnRecordWin(String resId, String text, boolean close, boolean isCancleExecute, final Runnable endRunnable) {
        long taskId = SystemClock.elapsedRealtime();
        this.T5.put(Long.valueOf(taskId), endRunnable);
        TXZService.T("sdk.record.win.speakTextOnRecordWin.end", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if (data != null) {
                    long taskId = Long.parseLong(new String(data));
                    if (!TXZResourceManager.this.T5.containsKey(Long.valueOf(taskId)) || TXZResourceManager.this.T5.get(Long.valueOf(taskId)) == null) {
                        return null;
                    }
                    ((Runnable) TXZResourceManager.this.T5.get(Long.valueOf(taskId))).run();
                    TXZResourceManager.this.T5.remove(Long.valueOf(taskId));
                    return null;
                }
                endRunnable.run();
                return null;
            }
        });
        Tr json = new Tr();
        json.T(SdkConstants.ATTR_TEXT, (Object) text);
        json.T("close", (Object) Boolean.valueOf(close));
        json.T("resId", (Object) resId);
        json.T(TXZCameraManager.REMOTE_NAME_TASK_ID, (Object) Long.valueOf(taskId));
        json.T("isCancleExecute", (Object) Boolean.valueOf(isCancleExecute));
        Tn.Tr().T("com.txznet.txz", "txz.record.win.speakTextOnRecordWin", json.Ty(), (Tn.Tr) null);
    }

    public int createWinConfirmAsr(String message, String sureText, String[] sureCmds, String cancelText, String[] cancelCmds, String hintText, WinConfirmAsrListener listener, Runnable ttsEndRunnable) {
        if (TextUtils.isEmpty(message) || TextUtils.isEmpty(sureText) || TextUtils.isEmpty(cancelText) || TextUtils.isEmpty(hintText) || sureCmds == null || cancelCmds == null) {
            return -1;
        }
        final WinConfirmAsrListener winConfirmAsrListener = listener;
        final Runnable runnable = ttsEndRunnable;
        TXZService.T("sdk.record.win.dialog", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if (data == null) {
                    return null;
                }
                String cmd = new String(data);
                if ("ok".equals(cmd) && winConfirmAsrListener != null) {
                    winConfirmAsrListener.onClickOk();
                }
                if ("cancel".equals(cmd) && winConfirmAsrListener != null) {
                    winConfirmAsrListener.onClickCancel();
                }
                if (!"runnable".equals(cmd) || runnable == null) {
                    return null;
                }
                runnable.run();
                return null;
            }
        });
        JSONObject job = new JSONObject();
        JSONArray jry = new JSONArray();
        int id = Tv;
        Tv = id + 1;
        try {
            job.put(TXZCameraManager.REMOTE_NAME_TASK_ID, id);
            job.put("message", message);
            job.put("sureText", sureText);
            for (String put : sureCmds) {
                jry.put(put);
            }
            job.put("sureCmds", jry.toString());
            job.put("cancelText", cancelText);
            JSONArray jry2 = new JSONArray();
            int i = 0;
            while (i < cancelCmds.length) {
                try {
                    jry2.put(cancelCmds[i]);
                    i++;
                } catch (Exception e) {
                    e = e;
                    JSONArray jSONArray = jry2;
                    e.printStackTrace();
                    Tn.Tr().T("com.txznet.txz", "txz.record.win.dialog", job.toString().getBytes(), (Tn.Tr) null);
                    return id;
                }
            }
            job.put("cancelCmds", jry2.toString());
            job.put("hintText", hintText);
            JSONArray jSONArray2 = jry2;
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            Tn.Tr().T("com.txznet.txz", "txz.record.win.dialog", job.toString().getBytes(), (Tn.Tr) null);
            return id;
        }
        Tn.Tr().T("com.txznet.txz", "txz.record.win.dialog", job.toString().getBytes(), (Tn.Tr) null);
        return id;
    }

    public void cancelDialog(int taskId) {
        Tn.Tr().T("com.txznet.txz", "txz.record.win.cancel.dialog", (taskId + STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }
}
