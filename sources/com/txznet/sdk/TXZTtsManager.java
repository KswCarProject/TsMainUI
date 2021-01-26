package com.txznet.sdk;

import android.text.TextUtils;
import com.android.SdkConstants;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Tr.Tr.Th;
import com.txznet.comm.Tr.Tr.Tr;
import com.txznet.sdk.TXZService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
public class TXZTtsManager {
    public static final int INVALID_TTS_TASK_ID = 0;
    private static TXZTtsManager Tr = new TXZTtsManager();

    /* renamed from: T  reason: collision with root package name */
    TtsTool f843T = null;
    private Boolean T5 = null;
    private String T9 = null;
    private Boolean TE = null;
    private String TZ = null;
    private Integer Tk = null;
    private Integer Tn = null;
    private String Tv = null;
    private Integer Ty = null;

    /* compiled from: Proguard */
    public static abstract class ITtsCallback extends Th.T {
    }

    /* compiled from: Proguard */
    public enum PreemptType {
        PREEMPT_TYPE_NONE,
        PREEMPT_TYPE_IMMEADIATELY,
        PREEMPT_TYPE_NEXT,
        PREEMPT_TYPE_FLUSH,
        PREEMPT_TYPE_IMMEADIATELY_WITHOUT_CANCLE
    }

    /* compiled from: Proguard */
    public interface TtsCallback {
        void onCancel();

        void onError();

        void onSuccess();
    }

    /* compiled from: Proguard */
    public static class TtsOption {
    }

    /* compiled from: Proguard */
    public interface TtsTool {
        void cancel();

        void setOption(TtsOption ttsOption);

        void start(int i, String str, TtsCallback ttsCallback);
    }

    private TXZTtsManager() {
    }

    public static TXZTtsManager getInstance() {
        return Tr;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        Th.Tr();
        if (this.Ty != null) {
            setDefaultAudioStream(this.Ty.intValue());
        }
        if (this.Tn != null) {
            setVoiceSpeed(this.Tn.intValue());
        }
        if (this.T9 != null) {
            setTtsModel(this.T9);
        }
        if (this.f843T != null) {
            setTtsTool(this.f843T);
        }
        if (this.Tk != null) {
            setBufferTime(this.Tk.intValue());
        }
        if (this.TZ != null) {
            setBeepResources(this.TZ);
        }
        if (this.TE != null) {
            enableDownVolumeWhenNav(this.TE.booleanValue());
        }
        if (this.Tv != null) {
            T(this.Tv);
        }
        if (this.T5 != null) {
            forceShowTTSChoiceView(this.T5.booleanValue());
        }
    }

    /* compiled from: Proguard */
    public static class VoiceTask extends Th.T9 {

        /* compiled from: Proguard */
        public enum TaskType {
            TEXT,
            LOCAL_URL,
            BEEP,
            QUIET
        }

        public VoiceTask(TaskType type) {
            if (type == null) {
                throw new NullPointerException("VoiceTask.TaskType == null");
            }
            switch (type) {
                case TEXT:
                    this.type = Th.T9.T.TEXT;
                    return;
                case LOCAL_URL:
                    this.type = Th.T9.T.LOCAL_URL;
                    return;
                case BEEP:
                    this.type = Th.T9.T.BEEP;
                    return;
                case QUIET:
                    this.type = Th.T9.T.QUIET;
                    return;
                default:
                    return;
            }
        }

        public VoiceTask setText(String text) {
            this.text = text;
            return this;
        }

        public VoiceTask setUrl(String url) {
            this.url = url;
            return this;
        }

        public VoiceTask setDuration(long duration) {
            this.duration = duration;
            return this;
        }
    }

    public int speakText(int streamType, String text, PreemptType type, ITtsCallback callback) {
        Th.Tr pt = Th.Tr.PREEMPT_TYPE_NONE;
        switch (type) {
            case PREEMPT_TYPE_FLUSH:
                pt = Th.Tr.PREEMPT_TYPE_FLUSH;
                break;
            case PREEMPT_TYPE_IMMEADIATELY:
                pt = Th.Tr.PREEMPT_TYPE_IMMEADIATELY;
                break;
            case PREEMPT_TYPE_NEXT:
                pt = Th.Tr.PREEMPT_TYPE_NEXT;
                break;
            case PREEMPT_TYPE_NONE:
                pt = Th.Tr.PREEMPT_TYPE_NONE;
                break;
            case PREEMPT_TYPE_IMMEADIATELY_WITHOUT_CANCLE:
                pt = Th.Tr.PREEMPT_TYPE_IMMEADIATELY_WITHOUT_CANCLE;
                break;
        }
        return Th.T(streamType, text, pt, (Th.T) callback);
    }

    public int speakText(int streamType, String text, long delay, PreemptType type, ITtsCallback callback) {
        Th.Tr pt = Th.Tr.PREEMPT_TYPE_NONE;
        switch (type) {
            case PREEMPT_TYPE_FLUSH:
                pt = Th.Tr.PREEMPT_TYPE_FLUSH;
                break;
            case PREEMPT_TYPE_IMMEADIATELY:
                pt = Th.Tr.PREEMPT_TYPE_IMMEADIATELY;
                break;
            case PREEMPT_TYPE_NEXT:
                pt = Th.Tr.PREEMPT_TYPE_NEXT;
                break;
            case PREEMPT_TYPE_NONE:
                pt = Th.Tr.PREEMPT_TYPE_NONE;
                break;
            case PREEMPT_TYPE_IMMEADIATELY_WITHOUT_CANCLE:
                pt = Th.Tr.PREEMPT_TYPE_IMMEADIATELY_WITHOUT_CANCLE;
                break;
        }
        return Th.T(streamType, text, delay, pt, (Th.T) callback);
    }

    public int speakText(String text, PreemptType type, ITtsCallback callback) {
        return speakText(-1, text, type, callback);
    }

    public int speakText(String text, long delay, PreemptType type, ITtsCallback callback) {
        return speakText(-1, text, delay, type, callback);
    }

    public int speakText(String text, ITtsCallback callback) {
        return speakText(text, PreemptType.PREEMPT_TYPE_NONE, callback);
    }

    public int speakText(String text) {
        return speakText(text, PreemptType.PREEMPT_TYPE_NONE, (ITtsCallback) null);
    }

    public int speakRes(String resId, String text) {
        return speakRes(resId, (String[]) null, text);
    }

    public int speakRes(String resId, String[] resArgs, String text) {
        return Th.T(resId, resArgs, text, (Th.T) null);
    }

    public int speakRes(String resId, String[] resArgs, String text, PreemptType type, ITtsCallback onRun) {
        Th.Tr pt = Th.Tr.PREEMPT_TYPE_NONE;
        switch (type) {
            case PREEMPT_TYPE_FLUSH:
                pt = Th.Tr.PREEMPT_TYPE_FLUSH;
                break;
            case PREEMPT_TYPE_IMMEADIATELY:
                pt = Th.Tr.PREEMPT_TYPE_IMMEADIATELY;
                break;
            case PREEMPT_TYPE_NEXT:
                pt = Th.Tr.PREEMPT_TYPE_NEXT;
                break;
            case PREEMPT_TYPE_NONE:
                pt = Th.Tr.PREEMPT_TYPE_NONE;
                break;
            case PREEMPT_TYPE_IMMEADIATELY_WITHOUT_CANCLE:
                pt = Th.Tr.PREEMPT_TYPE_IMMEADIATELY_WITHOUT_CANCLE;
                break;
        }
        return Th.T(-1, resId, resArgs, text, pt, (Th.T) onRun);
    }

    public int speakVoiceTask(VoiceTask[] voiceTasks, ITtsCallback oRun) {
        return speakVoiceTask(-1, PreemptType.PREEMPT_TYPE_NONE, voiceTasks, oRun);
    }

    public int speakVoiceTask(int iStream, VoiceTask[] voiceTasks, ITtsCallback oRun) {
        return speakVoiceTask(iStream, PreemptType.PREEMPT_TYPE_NONE, voiceTasks, oRun);
    }

    public int speakVoiceTask(PreemptType bPreempt, VoiceTask[] voiceTasks, ITtsCallback oRun) {
        return speakVoiceTask(-1, bPreempt, voiceTasks, oRun);
    }

    public int speakVoiceTask(int iStream, PreemptType type, VoiceTask[] voiceTasks, ITtsCallback oRun) {
        Th.Tr pt = Th.Tr.PREEMPT_TYPE_NONE;
        switch (type) {
            case PREEMPT_TYPE_FLUSH:
                pt = Th.Tr.PREEMPT_TYPE_FLUSH;
                break;
            case PREEMPT_TYPE_IMMEADIATELY:
                pt = Th.Tr.PREEMPT_TYPE_IMMEADIATELY;
                break;
            case PREEMPT_TYPE_NEXT:
                pt = Th.Tr.PREEMPT_TYPE_NEXT;
                break;
            case PREEMPT_TYPE_NONE:
                pt = Th.Tr.PREEMPT_TYPE_NONE;
                break;
            case PREEMPT_TYPE_IMMEADIATELY_WITHOUT_CANCLE:
                pt = Th.Tr.PREEMPT_TYPE_IMMEADIATELY_WITHOUT_CANCLE;
                break;
        }
        return Th.T(iStream, pt, (Th.T9[]) voiceTasks, (Th.T) oRun);
    }

    public void cancelSpeak(int taskId) {
        Th.T(taskId);
    }

    public void setDefaultAudioStream(int stream) {
        this.Ty = Integer.valueOf(stream);
        Th.f394T = stream;
        Tn.Tr().T("com.txznet.txz", "comm.config.tts.setDefaultAudioStream", (TXZResourceManager.STYLE_DEFAULT + stream).getBytes(), (Tn.Tr) null);
    }

    public void setVoiceSpeed(int speed) {
        if (speed < 1) {
            speed = 1;
        } else if (speed > 100) {
            speed = 100;
        }
        this.Tn = Integer.valueOf(speed);
        Tn.Tr().T("com.txznet.txz", "comm.tts.set.voicespeed", (TXZResourceManager.STYLE_DEFAULT + speed).toString().getBytes(), (Tn.Tr) null);
        if (TXZConfigManager.getInstance().Tr != null) {
            TXZConfigManager.getInstance().Tr.setTtsVoiceSpeed(speed);
        }
        Tr.T(Integer.valueOf(speed));
    }

    public void setTtsModel(String ttsModelPath) {
        if (ttsModelPath == null) {
            ttsModelPath = TXZResourceManager.STYLE_DEFAULT;
        }
        this.T9 = ttsModelPath;
        Tn.Tr().T("com.txznet.txz", "comm.tts.set.modelrole", ttsModelPath.getBytes(), (Tn.Tr) null);
    }

    public void setTtsDelay(long delay) {
        Tn.Tr().T("com.txznet.txz", "comm.tts.set.ttsdelay", (delay + TXZResourceManager.STYLE_DEFAULT).toString().getBytes(), (Tn.Tr) null);
    }

    public void setBufferTime(int nTime) {
        this.Tk = Integer.valueOf(nTime);
        Tn.Tr().T("com.txznet.txz", "comm.tts.set.buffettime", (TXZResourceManager.STYLE_DEFAULT + nTime).toString().getBytes(), (Tn.Tr) null);
    }

    public void setBeepResources(String beepPath) {
        byte[] data = null;
        this.TZ = beepPath;
        if (!TextUtils.isEmpty(beepPath)) {
            data = this.TZ.getBytes();
        }
        Tn.Tr().T("com.txznet.txz", "comm.tts.set.beep", data, (Tn.Tr) null);
    }

    public void setReplaceSpeakWord(String[] original, String[] replace) {
        if (original == null || original.length == 0 || replace == null || replace.length == 0) {
            com.txznet.comm.Tr.Tr.Tn.Tn("original or replace is empty");
        } else if (original.length != replace.length) {
            com.txznet.comm.Tr.Tr.Tn.Tn("original.length != replace.length");
        } else {
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < original.length; i++) {
                if (!TextUtils.isEmpty(original[i]) && replace[i] != null) {
                    JSONObject object = new JSONObject();
                    try {
                        object.put("original", original[i]);
                        object.put("replace", replace[i]);
                        jsonArray.put(object);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        com.txznet.comm.Tr.Tr.Tn.T("set replace word:", (Throwable) e);
                    }
                }
            }
            if (jsonArray.length() == 0) {
                com.txznet.comm.Tr.Tr.Tn.Tn("set replace word invalid argument");
            } else {
                T(jsonArray.toString());
            }
        }
    }

    private void T(String replaceJson) {
        this.Tv = replaceJson;
        if (this.Tv != null) {
            Tn.Tr().T("com.txznet.txz", "comm.tts.set.replaceword", this.Tv.getBytes(), (Tn.Tr) null);
        }
    }

    public void enableDownVolumeWhenNav(boolean enable) {
        this.TE = Boolean.valueOf(enable);
        Tn.Tr().T("com.txznet.txz", "comm.tts.set.enableDownVolume", (TXZResourceManager.STYLE_DEFAULT + enable).toString().getBytes(), (Tn.Tr) null);
    }

    public void forceShowTTSChoiceView(boolean enable) {
        this.T5 = Boolean.valueOf(enable);
        Tn.Tr().T("com.txznet.txz", "comm.tts.set.forceShowChoiceView", (TXZResourceManager.STYLE_DEFAULT + enable).toString().getBytes(), (Tn.Tr) null);
    }

    public void setTtsTool(TtsTool tool) {
        this.f843T = tool;
        if (tool == null) {
            TXZService.T("tool.tts.", (TXZService.T) null);
            Tn.Tr().T("com.txznet.txz", "txz.tool.tts.clearTool", (byte[]) null, (Tn.Tr) null);
            return;
        }
        TXZService.T("tool.tts.", new TXZService.T() {
            public byte[] T(String packageName, String command, final byte[] data) {
                if ("start".equals(command)) {
                    com.txznet.comm.Ty.Tr json = new com.txznet.comm.Ty.Tr(data);
                    int stream = ((Integer) json.T("stream", Integer.class, Integer.valueOf(Th.f394T))).intValue();
                    String text = (String) json.T(SdkConstants.ATTR_TEXT, String.class);
                    com.txznet.comm.Tr.Tr.Tn.T("tts tool start: stream=" + stream + ", text=" + text);
                    TXZTtsManager.this.f843T.start(stream, text, new TtsCallback() {
                        public void onSuccess() {
                            Tn.Tr().T("com.txznet.txz", "txz.tool.tts.onSuccess", data, (Tn.Tr) null);
                        }

                        public void onError() {
                            Tn.Tr().T("com.txznet.txz", "txz.tool.tts.onError", data, (Tn.Tr) null);
                        }

                        public void onCancel() {
                            Tn.Tr().T("com.txznet.txz", "txz.tool.tts.onCancel", data, (Tn.Tr) null);
                        }
                    });
                } else if ("cancel".equals(command)) {
                    com.txznet.comm.Tr.Tr.Tn.T("tts tool cancel");
                    TXZTtsManager.this.f843T.cancel();
                } else if ("setOption".equals(command)) {
                    TXZTtsManager.this.f843T.setOption(new TtsOption());
                }
                return null;
            }
        });
        Tn.Tr().T("com.txznet.txz", "txz.tool.tts.setTool", (byte[]) null, (Tn.Tr) null);
    }

    public void setTtsThmeme(TtsTheme ttsTheme) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("themeid", ttsTheme.mThemeId);
            jsonObject.put("themename", ttsTheme.mThemeName);
            Tn.Tr().T("com.txznet.txz", "comm.tts.set.ttstheme", jsonObject.toString().getBytes(), (Tn.Tr) null);
        } catch (JSONException e) {
            com.txznet.comm.Tr.Tr.Tn.Ty(e.getMessage());
            e.printStackTrace();
        }
    }

    public TtsTheme[] getTtsThemes() {
        Tn.Ty data = Tn.Tr().T("com.txznet.txz", "comm.tts.getTtsThemes", (byte[]) null);
        if (data == null) {
            return null;
        }
        return TtsTheme.Tr(data.T());
    }

    /* compiled from: Proguard */
    public static class TtsTheme {
        public boolean isUsed = false;
        public int mThemeId;
        public String mThemeName;

        public static JSONArray toJsonArray(TtsTheme[] ttsThemes) {
            if (ttsThemes == null) {
                return null;
            }
            JSONArray jsonArray = new JSONArray();
            for (TtsTheme ttsTheme : ttsThemes) {
                if (ttsTheme != null) {
                    jsonArray.put(ttsTheme.T());
                }
            }
            return jsonArray;
        }

        private JSONObject T() {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("themeId", this.mThemeId);
                jsonObject.put("themeName", this.mThemeName);
                jsonObject.put("isUsed", this.isUsed);
            } catch (JSONException e) {
                com.txznet.comm.Tr.Tr.Tn.Ty(e.getMessage());
                e.printStackTrace();
            }
            return jsonObject;
        }

        /* access modifiers changed from: private */
        public static TtsTheme[] Tr(String json) {
            JSONArray jsonArray;
            if (TextUtils.isEmpty(json)) {
                return null;
            }
            try {
                jsonArray = new JSONArray(json);
            } catch (JSONException e) {
                jsonArray = null;
                com.txznet.comm.Tr.Tr.Tn.Ty(e.getMessage());
                e.printStackTrace();
            }
            return T(jsonArray);
        }

        private static TtsTheme[] T(JSONArray jsonArray) {
            if (jsonArray == null) {
                return null;
            }
            int len = jsonArray.length();
            TtsTheme[] ttsThemes = new TtsTheme[len];
            for (int i = 0; i < len; i++) {
                ttsThemes[i] = T(jsonArray.optJSONObject(i));
            }
            return ttsThemes;
        }

        private static TtsTheme T(JSONObject jsonObject) {
            if (jsonObject == null) {
                return null;
            }
            TtsTheme ttsTheme = new TtsTheme();
            ttsTheme.mThemeId = jsonObject.optInt("themeId");
            ttsTheme.mThemeName = jsonObject.optString("themeName");
            ttsTheme.isUsed = jsonObject.optBoolean("isUsed");
            return ttsTheme;
        }
    }
}
