package com.txznet.comm.Tr.Tr;

import com.txznet.comm.Tr.Tn;
import com.txznet.sdk.TXZConfigManager;
import com.txznet.sdk.TXZResourceManager;
import com.txznet.sdk.TXZTtsManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
public class Tr {

    /* renamed from: T  reason: collision with root package name */
    static Boolean f407T = null;
    private static JSONObject T5 = null;
    private static List<T> T6 = new ArrayList();
    static Float T9;
    public static final JSONObject TE = new JSONObject();
    private static String TF;
    static Integer TZ;
    private static Boolean Te = true;
    private static List<C0016Tr> Th = new ArrayList();
    static String[] Tk;
    static Boolean Tn = null;
    private static String Tq = "FLOAT_NORMAL";
    static Boolean Tr = null;
    private static Boolean Tv = null;
    static Boolean Ty = null;

    /* compiled from: Proguard */
    public interface T {
        void onConfigChanged(String str);
    }

    /* renamed from: com.txznet.comm.Tr.Tr.Tr$Tr  reason: collision with other inner class name */
    /* compiled from: Proguard */
    public interface C0016Tr {
        void T(int i, boolean z);
    }

    static {
        try {
            TE.put("wakeupThreshold", -3.0999999046325684d);
            TE.put("voiceSpeed", 70);
            JSONArray array = new JSONArray();
            for (String str : new String[0]) {
                array.put(str);
            }
            TE.put("wakeupKeywords", array);
            TE.put("wakeupSound", true);
            TE.put("floatTool", "FLOAT_TOP");
            TE.put("coverDefaultKeywords", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void T(JSONObject mDefaultDoc) {
        T5 = mDefaultDoc;
        Tn.Tr().T("com.txznet.txz", "txz.config.default.set", (byte[]) null, (Tn.Tr) null);
    }

    public static void T(boolean showHelpInfos) {
        f407T = Boolean.valueOf(showHelpInfos);
        T(1, showHelpInfos);
    }

    public static void Tr(boolean showSettings) {
        Tr = Boolean.valueOf(showSettings);
        T(3, showSettings);
    }

    public static void Ty(boolean showClose) {
        Ty = Boolean.valueOf(showClose);
        T(2, showClose);
    }

    public static void Tn(boolean showHelpNewTag) {
        if (Tn == null || Tn.booleanValue() != showHelpNewTag) {
            Tn = Boolean.valueOf(showHelpNewTag);
            T(4, showHelpNewTag);
        }
    }

    public static void T() {
        if (f407T != null) {
            Tn.Tr().T("com.txznet.txz", "comm.config.showHelpInfos", (TXZResourceManager.STYLE_DEFAULT + f407T).getBytes(), (Tn.Tr) null);
            Tn.Tr().T("com.txznet.record", "comm.config.showHelpInfos", (TXZResourceManager.STYLE_DEFAULT + f407T).getBytes(), (Tn.Tr) null);
        }
        if (Tr != null) {
            Tn.Tr().T("com.txznet.txz", "comm.config.showSettings", (TXZResourceManager.STYLE_DEFAULT + Tr).getBytes(), (Tn.Tr) null);
            Tn.Tr().T("com.txznet.record", "comm.config.showSettings", (TXZResourceManager.STYLE_DEFAULT + Tr).getBytes(), (Tn.Tr) null);
        }
        if (Ty != null) {
            Tn.Tr().T("com.txznet.txz", "comm.config.showCloseIcon", (TXZResourceManager.STYLE_DEFAULT + Ty).getBytes(), (Tn.Tr) null);
            Tn.Tr().T("com.txznet.record", "comm.config.showCloseIcon", (TXZResourceManager.STYLE_DEFAULT + Ty).getBytes(), (Tn.Tr) null);
        }
        if (T5 != null) {
            T(T5);
        }
    }

    public static void T(int type, boolean enable) {
        synchronized (Th) {
            for (int i = 0; i < Th.size(); i++) {
                if (Th.get(i) != null) {
                    Th.get(i).T(type, enable);
                }
            }
        }
    }

    public static void Tr() {
        Tn.Tr().T("com.txznet.txz", "txz.config.requestSync", (byte[]) null, (Tn.Tr) null);
    }

    public static void T(T listener) {
        synchronized (T6) {
            T6.add(listener);
        }
    }

    public static void Tr(T listener) {
        synchronized (T6) {
            T6.remove(listener);
        }
    }

    public static void T(Integer voiceSpeed) {
        TZ = voiceSpeed;
    }

    public static Float Ty() {
        return T9;
    }

    public static String[] Tn() {
        return Tk;
    }

    public static Integer T9() {
        return TZ;
    }

    public static void T(String data) {
        if (Tr(data)) {
            synchronized (T6) {
                for (int i = 0; i < T6.size(); i++) {
                    T6.get(i).onConfigChanged(data);
                }
            }
        }
    }

    public static Boolean Tk() {
        return Te;
    }

    public static String TZ() {
        return Tq;
    }

    private static boolean Tr(String data) {
        boolean hasChanged = true;
        if (!(data == null || TF == null || !data.equals(TF))) {
            hasChanged = false;
        }
        try {
            JSONObject doc = new JSONObject(data);
            if (doc.has("wakeupThreshold")) {
                T9 = Float.valueOf((float) doc.getDouble("wakeupThreshold"));
            }
            if (doc.has("voiceSpeed")) {
                TZ = Integer.valueOf(doc.getInt("voiceSpeed"));
            }
            if (doc.has("wakeupKeywords")) {
                JSONArray jKeywords = doc.getJSONArray("wakeupKeywords");
                Tk = new String[jKeywords.length()];
                for (int j = 0; j < jKeywords.length(); j++) {
                    Tk[j] = jKeywords.getString(j);
                }
            }
            if (doc.has("wakeupSound")) {
                Te = Boolean.valueOf(doc.getBoolean("wakeupSound"));
            }
            if (doc.has("floatTool")) {
                Tq = doc.getString("floatTool");
            }
            if (doc.has("coverDefaultKeywords")) {
                Tv = Boolean.valueOf(doc.getBoolean("coverDefaultKeywords"));
            }
        } catch (Exception e) {
        }
        TF = data;
        return hasChanged;
    }

    public static void TE() {
        if (T5 != null) {
            Float wakeupThreshhold = Ty(T5);
            if (wakeupThreshhold != null) {
                TXZConfigManager.getInstance().setWakeupThreshhold(wakeupThreshhold.floatValue());
            }
            Integer speedVoice = Tn(T5);
            if (speedVoice != null) {
                TXZTtsManager.getInstance().setVoiceSpeed(speedVoice.intValue());
            }
            String[] wakeupKeywords = Tk(T5);
            if (wakeupKeywords != null) {
                TXZConfigManager.getInstance().setWakeupKeywordsNew(wakeupKeywords);
            }
            Boolean wakeupSound = T9(T5);
            if (wakeupSound != null) {
                TXZConfigManager.getInstance().enableWakeup(wakeupSound.booleanValue());
            }
            Boolean coverDefaultKeywords = Tr(T5);
            if (coverDefaultKeywords != null) {
                TXZConfigManager.getInstance().enableCoverDefaultKeywords(coverDefaultKeywords.booleanValue());
            }
        }
    }

    public static Boolean Tr(JSONObject config) {
        if (config.has("coverDefaultKeywords")) {
            try {
                return Boolean.valueOf(config.getBoolean("coverDefaultKeywords"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Float Ty(JSONObject config) {
        if (config.has("wakeupThreshold")) {
            try {
                return Float.valueOf((float) config.getDouble("wakeupThreshold"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Integer Tn(JSONObject config) {
        if (config.has("voiceSpeed")) {
            try {
                return Integer.valueOf(config.getInt("voiceSpeed"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Boolean T9(JSONObject config) {
        if (config.has("wakeupSound")) {
            try {
                return Boolean.valueOf(config.getBoolean("wakeupSound"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String[] Tk(JSONObject config) {
        if (config.has("wakeupKeywords")) {
            try {
                JSONArray jWakeupKW = config.getJSONArray("wakeupKeywords");
                String[] wakeupKW = new String[jWakeupKW.length()];
                for (int i = 0; i < jWakeupKW.length(); i++) {
                    wakeupKW[i] = jWakeupKW.getString(i);
                }
                return wakeupKW;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
