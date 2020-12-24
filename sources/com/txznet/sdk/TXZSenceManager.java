package com.txznet.sdk;

import com.ts.main.txz.AmapAuto;
import com.txznet.comm.Tr.Tn;
import com.txznet.sdk.TXZService;
import com.txznet.sdk.bean.Poi;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
/* compiled from: Proguard */
public class TXZSenceManager {

    /* renamed from: T  reason: collision with root package name */
    private static TXZSenceManager f811T = new TXZSenceManager();
    private Map<SenceType, SenceTool> Tr = new ConcurrentHashMap();
    private SenceTool Ty = new SenceTool() {
        public boolean process(SenceType type, String json) {
            return false;
        }
    };

    /* compiled from: Proguard */
    public interface SenceTool {
        boolean process(SenceType senceType, String str);
    }

    /* compiled from: Proguard */
    public enum SenceType {
        SENCE_TYPE_ALL,
        SENCE_TYPE_WAKEUP,
        SENCE_TYPE_SET_USER_WAKEUP_KEYWORDS,
        SENCE_TYPE_COMMAND,
        SENCE_TYPE_APP,
        SENCE_TYPE_CALL,
        SENCE_TYPE_NAV,
        SENCE_TYPE_POI_CHOICE,
        SENCE_TYPE_MUSIC,
        SENCE_TYPE_AUDIO,
        SENCE_TYPE_WEATHER,
        SENCE_TYPE_STOCK,
        SENCE_TYPE_LOCATION,
        SENCE_TYPE_TRAFFIC,
        SENCE_TYPE_LIMIT_NUMBER,
        SENCE_TYPE_UNKNOW,
        SENCE_TYPE_UNSUPPORT,
        SENCE_TYPE_EMPTY,
        SENCE_TYPE_HELP,
        SENCE_TYPE_SELECTOR,
        SENCE_TYPE_MOVIE
    }

    private TXZSenceManager() {
    }

    public static TXZSenceManager getInstance() {
        return f811T;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        for (Map.Entry<SenceType, SenceTool> entry : this.Tr.entrySet()) {
            T(entry.getKey(), entry.getValue());
        }
    }

    public void setSenceTool(SenceType type, SenceTool tool) {
        if (tool == null) {
            this.Tr.put(type, this.Ty);
        } else {
            this.Tr.put(type, tool);
        }
        T(type, tool);
    }

    private void T(final SenceType type, final SenceTool tool) {
        String sence;
        String cmd;
        switch (type) {
            case SENCE_TYPE_ALL:
                sence = "all";
                break;
            case SENCE_TYPE_WAKEUP:
                sence = "wakeup";
                break;
            case SENCE_TYPE_CALL:
                sence = "call";
                break;
            case SENCE_TYPE_MUSIC:
                sence = "music";
                break;
            case SENCE_TYPE_NAV:
                sence = Poi.PoiAction.ACTION_NAVI;
                break;
            case SENCE_TYPE_EMPTY:
                sence = "empty";
                break;
            case SENCE_TYPE_UNKNOW:
                sence = "unknow";
                break;
            case SENCE_TYPE_UNSUPPORT:
                sence = "unsupport";
                break;
            case SENCE_TYPE_APP:
                sence = "app";
                break;
            case SENCE_TYPE_COMMAND:
                sence = AmapAuto.CMDNAME;
                break;
            case SENCE_TYPE_LIMIT_NUMBER:
                sence = "limit_number";
                break;
            case SENCE_TYPE_LOCATION:
                sence = "location";
                break;
            case SENCE_TYPE_POI_CHOICE:
                sence = "poi_choice";
                break;
            case SENCE_TYPE_SET_USER_WAKEUP_KEYWORDS:
                sence = "set_user_wakeup_keywords";
                break;
            case SENCE_TYPE_STOCK:
                sence = "stock";
                break;
            case SENCE_TYPE_TRAFFIC:
                sence = "traffic";
                break;
            case SENCE_TYPE_WEATHER:
                sence = "weather";
                break;
            case SENCE_TYPE_HELP:
                sence = "help";
                break;
            case SENCE_TYPE_AUDIO:
                sence = Poi.PoiAction.ACTION_AUDIO;
                break;
            case SENCE_TYPE_SELECTOR:
                sence = "selector";
                break;
            case SENCE_TYPE_MOVIE:
                sence = "movie";
                break;
            default:
                return;
        }
        if (tool == null || this.Ty == tool) {
            cmd = "txz.sence.clear.";
        } else {
            cmd = "txz.sence.set.";
        }
        Tn.Tr().T("com.txznet.txz", cmd + sence, (byte[]) null, (Tn.Tr) null);
        TXZService.T("tool.sence." + sence, new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                return ("" + tool.process(type, new String(data))).getBytes();
            }
        });
    }

    public void triggerSence(SenceType type, String data) {
    }
}
