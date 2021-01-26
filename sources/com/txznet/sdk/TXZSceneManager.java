package com.txznet.sdk;

import com.android.SdkConstants;
import com.txznet.comm.Tr.Tn;
import com.txznet.sdk.TXZService;
import com.txznet.sdk.bean.Poi;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: Proguard */
public class TXZSceneManager {

    /* renamed from: T  reason: collision with root package name */
    private static TXZSceneManager f810T = new TXZSceneManager();
    private Map<SceneType, SceneTool> Tr = new ConcurrentHashMap();
    private SceneTool Ty = new SceneTool() {
        public boolean process(SceneType type, String json) {
            return false;
        }
    };

    /* compiled from: Proguard */
    public interface SceneTool {
        boolean process(SceneType sceneType, String str);
    }

    /* compiled from: Proguard */
    public enum SceneType {
        SCENE_TYPE_ALL,
        SCENE_TYPE_WAKEUP,
        SCENE_TYPE_SET_USER_WAKEUP_KEYWORDS,
        SCENE_TYPE_COMMAND,
        SCENE_TYPE_APP,
        SCENE_TYPE_CALL,
        SCENE_TYPE_NAV,
        SCENE_TYPE_POI_CHOICE,
        SCENE_TYPE_MUSIC,
        SCENE_TYPE_AUDIO,
        SCENE_TYPE_WEATHER,
        SCENE_TYPE_STOCK,
        SCENE_TYPE_LOCATION,
        SCENE_TYPE_TRAFFIC,
        SCENE_TYPE_LIMIT_NUMBER,
        SCENE_TYPE_UNKNOW,
        SCENE_TYPE_UNSUPPORT,
        SCENE_TYPE_EMPTY,
        SCENE_TYPE_HELP,
        SCENE_TYPE_SELECTOR,
        SCENE_TYPE_MOVIE,
        SCENE_TYPE_WECHAT
    }

    private TXZSceneManager() {
    }

    public static TXZSceneManager getInstance() {
        return f810T;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        for (Map.Entry<SceneType, SceneTool> entry : this.Tr.entrySet()) {
            T(entry.getKey(), entry.getValue());
        }
    }

    public void setSceneTool(SceneType type, SceneTool tool) {
        if (tool == null) {
            this.Tr.put(type, this.Ty);
        } else {
            this.Tr.put(type, tool);
        }
        T(type, tool);
    }

    private void T(final SceneType type, final SceneTool tool) {
        String scene;
        String cmd;
        switch (type) {
            case SCENE_TYPE_ALL:
                scene = SdkConstants.SUPPRESS_ALL;
                break;
            case SCENE_TYPE_WAKEUP:
                scene = "wakeup";
                break;
            case SCENE_TYPE_CALL:
                scene = "call";
                break;
            case SCENE_TYPE_MUSIC:
                scene = "music";
                break;
            case SCENE_TYPE_NAV:
                scene = Poi.PoiAction.ACTION_NAVI;
                break;
            case SCENE_TYPE_EMPTY:
                scene = "empty";
                break;
            case SCENE_TYPE_UNKNOW:
                scene = "unknow";
                break;
            case SCENE_TYPE_UNSUPPORT:
                scene = "unsupport";
                break;
            case SCENE_TYPE_APP:
                scene = SdkConstants.APP_PREFIX;
                break;
            case SCENE_TYPE_COMMAND:
                scene = "command";
                break;
            case SCENE_TYPE_LIMIT_NUMBER:
                scene = "limit_number";
                break;
            case SCENE_TYPE_LOCATION:
                scene = "location";
                break;
            case SCENE_TYPE_POI_CHOICE:
                scene = "poi_choice";
                break;
            case SCENE_TYPE_SET_USER_WAKEUP_KEYWORDS:
                scene = "set_user_wakeup_keywords";
                break;
            case SCENE_TYPE_STOCK:
                scene = "stock";
                break;
            case SCENE_TYPE_TRAFFIC:
                scene = "traffic";
                break;
            case SCENE_TYPE_WEATHER:
                scene = "weather";
                break;
            case SCENE_TYPE_HELP:
                scene = "help";
                break;
            case SCENE_TYPE_AUDIO:
                scene = Poi.PoiAction.ACTION_AUDIO;
                break;
            case SCENE_TYPE_SELECTOR:
                scene = "selector";
                break;
            case SCENE_TYPE_MOVIE:
                scene = "movie";
                break;
            case SCENE_TYPE_WECHAT:
                scene = "wechat";
                break;
            default:
                return;
        }
        if (tool == null || this.Ty == tool) {
            cmd = "txz.sence.clear.";
        } else {
            cmd = "txz.sence.set.";
        }
        Tn.Tr().T("com.txznet.txz", cmd + scene, (byte[]) null, (Tn.Tr) null);
        TXZService.T("tool.sence." + scene, new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                return (TXZResourceManager.STYLE_DEFAULT + tool.process(type, new String(data))).getBytes();
            }
        });
    }

    public void triggerScene(SceneType type, String data) {
    }
}
