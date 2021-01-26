package com.txznet.sdk;

import android.text.TextUtils;
import com.android.SdkConstants;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Tr.Tr.T;
import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.TXZSceneManager;
import com.txznet.sdk.TXZService;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Proguard */
public class TXZAsrManager {
    private static TXZAsrManager T9 = new TXZAsrManager();

    /* renamed from: T  reason: collision with root package name */
    Boolean f697T = null;
    private HashMap<String, String> T5;
    private Float T6 = null;
    private Integer TB = null;
    private Integer TE = null;
    private Integer TF = null;
    private Integer TK = null;
    /* access modifiers changed from: private */
    public T.T9 TZ = new T.T9() {
        public void T(String cmd, byte[] data) {
            for (CommandListener listener : TXZAsrManager.this.Tk) {
                if (listener != null) {
                    listener.onCommand(cmd, new String(data));
                }
            }
        }
    };
    private Float Te = null;
    private boolean Th = false;
    private float[] Tj = null;
    /* access modifiers changed from: private */
    public Set<CommandListener> Tk = new HashSet();
    AsrTool Tn = null;
    private Integer Tq = null;
    Boolean Tr;
    private boolean Tv = false;
    boolean Ty = false;

    /* compiled from: Proguard */
    public interface AsrCallback {
        void onAbort();

        void onBeginRecord();

        void onBeginSpeech();

        void onCancel();

        void onEndRecord();

        void onEndSpeech();

        void onError(int i, String str, String str2);

        void onSceneResult(TXZSceneManager.SceneType sceneType, String str);

        void onTextResult(String str);

        void onVolume(int i);
    }

    /* compiled from: Proguard */
    public interface AsrTool {
        void cancel();

        void start(AsrOption asrOption, AsrCallback asrCallback);

        void stop();
    }

    /* compiled from: Proguard */
    public interface CommandListener {
        void onCommand(String str, String str2);
    }

    private TXZAsrManager() {
    }

    public static TXZAsrManager getInstance() {
        return T9;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        if (!(!this.Tv || this.Te == null || this.T6 == null)) {
            if (this.Tj != null) {
                T(this.T6.floatValue(), this.Te.floatValue(), this.Tj);
            } else {
                T(this.T6.floatValue(), this.Te.floatValue());
            }
        }
        if (!(!this.Th || this.TF == null || this.Tq == null)) {
            T(this.Tq.intValue(), this.TF.intValue());
        }
        if (this.Ty) {
            setAsrTool(this.Tn);
        }
        if (this.TB != null) {
            setBOS(this.TB.intValue());
        }
        if (this.TK != null) {
            setEOS(this.TK.intValue());
        }
        if (this.Tr != null) {
            setCloseWinWhenEndCmd(this.Tr.booleanValue());
        }
        if (this.f697T != null) {
            enableFMOnlineCmds(this.f697T.booleanValue());
        }
        if (this.TE != null) {
            setAsrDelayAfterBeep(this.TE.intValue());
        }
        if (this.T5 != null && this.T5.size() > 0) {
            JSONArray realJry = new JSONArray();
            try {
                for (Map.Entry<String, String> entry : this.T5.entrySet()) {
                    JSONObject job = new JSONObject();
                    job.put("real", entry.getValue());
                    job.put("fictitious", entry.getKey());
                    realJry.put(job);
                    Tn.Tr().T("com.txznet.txz", "comm.asr.setRealFictitiousCmds", realJry.toString().getBytes(), (Tn.Tr) null);
                }
            } catch (Exception e) {
            }
        }
    }

    public void triggerRecordButton() {
        Tn.Tr().T("com.txznet.txz", "comm.asr.triggerRecordButton", (byte[]) null, (Tn.Tr) null);
    }

    public void setAsrDelayAfterBeep(int delay) {
        if (delay >= 0) {
            this.TE = Integer.valueOf(delay);
            Tn.Tr().T("com.txznet.txz", "comm.asr.set.asrDelayAfterBeep", (TXZResourceManager.STYLE_DEFAULT + delay).getBytes(), (Tn.Tr) null);
        }
    }

    public void start(String hint) {
        T.T(hint);
    }

    public void start() {
        T.T();
    }

    public void restart(String hint) {
        if (hint == null || hint.length() == 0) {
            hint = TXZResourceManager.STYLE_DEFAULT;
        }
        Tn.Tr().T("com.txznet.txz", "comm.asr.restartWithRecordWin", hint.getBytes(), (Tn.Tr) null);
    }

    public void startWithRawText(String rawText) {
        if (rawText != null) {
            Tn.Tr().T("com.txznet.txz", "comm.asr.startWithRawText", rawText.getBytes(), (Tn.Tr) null);
        }
    }

    public void stop() {
        T.Tr();
    }

    public void cancel() {
        T.Ty();
    }

    public boolean regCommand(String[] cmds, String data) {
        T.T(cmds, data, this.TZ);
        return true;
    }

    public boolean setRealFictitiousCmds(String real, String... fictitious) {
        if (TextUtils.isEmpty(real) || fictitious == null || fictitious.length == 0) {
            return false;
        }
        if (this.T5 == null) {
            synchronized (TXZAsrManager.class) {
                if (this.T5 == null) {
                    this.T5 = new HashMap<>();
                }
            }
        }
        JSONArray realJry = new JSONArray();
        try {
            for (String str : fictitious) {
                JSONObject job = new JSONObject();
                job.put("real", real);
                job.put("fictitious", str);
                this.T5.put(str, real);
                realJry.put(job);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Tn.Tr().T("com.txznet.txz", "comm.asr.setRealFictitiousCmds", realJry.toString().getBytes(), (Tn.Tr) null);
        return true;
    }

    public boolean removeRealFictitiousCmds(String... fictitious) {
        if (fictitious == null || fictitious.length == 0) {
            return false;
        }
        JSONArray jry = new JSONArray();
        for (String str : fictitious) {
            jry.put(str);
            if (this.T5 != null && this.T5.size() > 0) {
                this.T5.remove(str);
            }
        }
        Tn.Tr().T("com.txznet.txz", "comm.asr.removeRealFictitiousCmds", jry.toString().getBytes(), (Tn.Tr) null);
        return true;
    }

    public boolean regCommand(Collection<String> cmds, String data) {
        int count;
        if (cmds == null || (count = cmds.size()) <= 0) {
            return false;
        }
        String[] cmdArray = new String[count];
        cmds.toArray(cmdArray);
        T.T(cmdArray, data, this.TZ);
        return true;
    }

    private boolean T(float minVal, float maxVal) {
        if (minVal <= 0.0f || maxVal <= minVal) {
            return false;
        }
        this.T6 = Float.valueOf(minVal);
        this.Te = Float.valueOf(maxVal);
        Tr json = new Tr();
        json.T("minVal", (Object) this.T6);
        json.T("maxVal", (Object) this.Te);
        Tn.Tr().T("com.txznet.txz", "txz.fm.setdistance", json.toString().getBytes(), (Tn.Tr) null);
        return true;
    }

    private boolean T(int minVal, int maxVal) {
        if (minVal <= 0 || maxVal <= minVal) {
            return false;
        }
        this.Tq = Integer.valueOf(minVal);
        this.TF = Integer.valueOf(maxVal);
        Tr json = new Tr();
        json.T("minVal", (Object) this.Tq);
        json.T("maxVal", (Object) this.TF);
        Tn.Tr().T("com.txznet.txz", "txz.am.setdistance", json.toString().getBytes(), (Tn.Tr) null);
        return true;
    }

    private boolean T(float minVal, float maxVal, float[] jumps) {
        if (jumps == null) {
            T(minVal, maxVal);
            return true;
        } else if (minVal <= 0.0f || maxVal <= minVal) {
            return false;
        } else {
            this.T6 = Float.valueOf(minVal);
            this.Te = Float.valueOf(maxVal);
            this.Tj = jumps;
            Tr json = new Tr();
            json.T("minVal", (Object) this.T6);
            json.T("maxVal", (Object) this.Te);
            json.T("hasJump", (Object) true);
            for (int i = 0; i < jumps.length; i++) {
                json.T("jump" + i, (Object) Float.valueOf(jumps[i]));
            }
            Tn.Tr().T("com.txznet.txz", "txz.fm.setdistance", json.toString().getBytes(), (Tn.Tr) null);
            return true;
        }
    }

    public void setBOS(int val) {
        this.TB = Integer.valueOf(val);
        Tn.Tr().T("com.txznet.txz", "comm.asr.set.bos", (TXZResourceManager.STYLE_DEFAULT + val).toString().getBytes(), (Tn.Tr) null);
    }

    public void setEOS(int val) {
        this.TK = Integer.valueOf(val);
        Tn.Tr().T("com.txznet.txz", "comm.asr.set.eos", (TXZResourceManager.STYLE_DEFAULT + val).toString().getBytes(), (Tn.Tr) null);
    }

    public void enableFMOnlineCmds(boolean enable) {
        this.f697T = Boolean.valueOf(enable);
        Tn.Tr().T("com.txznet.txz", "comm.asr.enableFMOnlineCmds", (TXZResourceManager.STYLE_DEFAULT + this.f697T).getBytes(), (Tn.Tr) null);
    }

    public boolean regCommandForFM(float from, float to, final String callback_data) {
        if (from > to || callback_data == null) {
            return false;
        }
        this.Tv = true;
        TXZService.T("tool.fm.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if (command.equals("toFmFreq")) {
                    try {
                        float freq = ((Float) new Tr(new String(data)).T("freqValue", Float.class)).floatValue();
                        if (TXZAsrManager.this.TZ != null) {
                            TXZAsrManager.this.TZ.T("调频到" + freq, (callback_data + "#" + freq).getBytes());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });
        T(from, to);
        return true;
    }

    public boolean regCommandForAM(int from, int to, final String callback_data) {
        if (from > to || callback_data == null) {
            return false;
        }
        this.Th = true;
        TXZService.T("tool.am.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if (command.equals("toAmFreq")) {
                    try {
                        int freq = ((Integer) new Tr(new String(data)).T("freqValue", Integer.class)).intValue();
                        if (TXZAsrManager.this.TZ != null) {
                            TXZAsrManager.this.TZ.T("调幅到" + freq, (callback_data + "#" + freq).getBytes());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });
        T(from, to);
        return true;
    }

    public boolean regCommandFmWithJumpPoint(float from, float to, float[] jumps, final String callback_data) {
        if (from > to || callback_data == null) {
            return false;
        }
        this.Tv = true;
        TXZService.T("tool.fm.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if (command.equals("toFmFreq")) {
                    try {
                        float freq = ((Float) new Tr(new String(data)).T("freqValue", Float.class)).floatValue();
                        if (TXZAsrManager.this.TZ != null) {
                            TXZAsrManager.this.TZ.T("调频到" + freq, (callback_data + "#" + freq).getBytes());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });
        T(from, to, jumps);
        return true;
    }

    public boolean regCommand(String cmd, String data) {
        T.T(new String[]{cmd}, data, this.TZ);
        return true;
    }

    public boolean unregCommand(String[] cmds) {
        T.T(cmds);
        return true;
    }

    public boolean unregCommand(Collection<String> cmds) {
        int count;
        if (cmds == null || (count = cmds.size()) <= 0) {
            return false;
        }
        String[] cmdArray = new String[count];
        cmds.toArray(cmdArray);
        T.T(cmdArray);
        return true;
    }

    public boolean unregCommand(String cmd) {
        T.T(new String[]{cmd});
        return true;
    }

    public void setCloseWinWhenEndCmd(boolean isClose) {
        this.Tr = Boolean.valueOf(isClose);
        Tn.Tr().T("com.txznet.txz", "txz.config.end.close", (isClose + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }

    public void addCommandListener(CommandListener listener) {
        Tn.Tr().T((Runnable) new com.txznet.txz.util.T.T<CommandListener>(listener) {
            public void run() {
                TXZAsrManager.this.Tk.add(this.Ty);
            }
        }, 0);
    }

    public void removeCommandListener(CommandListener listener) {
        Tn.Tr().T((Runnable) new com.txznet.txz.util.T.T<CommandListener>(listener) {
            public void run() {
                TXZAsrManager.this.Tk.remove(this.Ty);
            }
        }, 0);
    }

    /* compiled from: Proguard */
    public static abstract class AsrComplexSelectCallback extends T.C0015T {
        public abstract String getTaskId();

        public abstract boolean needAsrState();

        public AsrComplexSelectCallback addCommand(String type, String... cmds) {
            super.addCommand(type, cmds);
            return this;
        }

        public AsrComplexSelectCallback addIndex(int index, String... cmds) {
            super.addIndex(index, cmds);
            return this;
        }

        public void onCommandSelected(String type, String command) {
            super.onCommandSelected(type, command);
        }

        public void onIndexSelected(List<Integer> indexs, String command) {
            super.onIndexSelected(indexs, command);
        }
    }

    public void useWakeupAsAsr(AsrComplexSelectCallback callback) {
        T.T((T.Tk) callback);
    }

    public void recoverWakeupFromAsr(String taskId) {
        T.TZ(taskId);
    }

    /* compiled from: Proguard */
    public static class AsrOption {

        /* renamed from: T  reason: collision with root package name */
        Integer f706T = null;
        Boolean Tn = null;
        Integer Tr = null;
        Integer Ty = null;

        public AsrOption setBOS(int bos) {
            this.f706T = Integer.valueOf(bos);
            return this;
        }

        public AsrOption setEOS(int eos) {
            this.Tr = Integer.valueOf(eos);
            return this;
        }

        public AsrOption setKeySpeechTimeout(int keySpeechTimeout) {
            this.Ty = Integer.valueOf(keySpeechTimeout);
            return this;
        }

        public AsrOption setManual(boolean manual) {
            this.Tn = Boolean.valueOf(manual);
            return this;
        }
    }

    public void setAsrTool(AsrTool asrTool) {
        this.Ty = true;
        this.Tn = asrTool;
        if (this.Tn == null) {
            Tn.Tr().T("com.txznet.txz", "comm.asr.clearAsrTool", (byte[]) null, (Tn.Tr) null);
            TXZService.T("tool.asr.", (TXZService.T) null);
            return;
        }
        Tn.Tr().T("com.txznet.txz", "comm.asr.setAsrTool", (byte[]) null, (Tn.Tr) null);
        TXZService.T("tool.asr.", new TXZService.T() {
            public byte[] T(String packageName, String command, final byte[] data) {
                if ("stop".equals(command)) {
                    com.txznet.comm.Tr.Tr.Tn.T("asr tool stop");
                    TXZAsrManager.this.Tn.stop();
                } else if ("cancel".equals(command)) {
                    com.txznet.comm.Tr.Tr.Tn.T("asr tool cancel");
                    TXZAsrManager.this.Tn.cancel();
                } else if ("start".equals(command)) {
                    final Tr json = new Tr(data);
                    AsrOption option = new AsrOption();
                    option.f706T = (Integer) json.T("BOS", Integer.class);
                    option.Tr = (Integer) json.T("EOS", Integer.class);
                    option.Ty = (Integer) json.T("KeySpeechTimeout", Integer.class);
                    option.Tn = (Boolean) json.T("Manual", Boolean.class);
                    com.txznet.comm.Tr.Tr.Tn.T("asr tool start: " + option.Tn);
                    TXZAsrManager.this.Tn.start(option, new AsrCallback() {
                        public void onVolume(int volume) {
                            json.T("volume", (Object) Integer.valueOf(volume));
                            Tn.Tr().T("com.txznet.txz", "txz.tool.asr.onVolume", json.Ty(), (Tn.Tr) null);
                        }

                        public void onSceneResult(TXZSceneManager.SceneType sceneType, String sceneData) {
                            com.txznet.comm.Tr.Tr.Tn.T("asr tool onSenceResult: SenceType=" + sceneType.name() + ", data: \n" + sceneData);
                            json.T("volume");
                            json.T("data", (Object) sceneData);
                            Tn.Tr().T("com.txznet.txz", "txz.tool.asr.onSenceResult", json.Ty(), (Tn.Tr) null);
                        }

                        public void onEndSpeech() {
                            com.txznet.comm.Tr.Tr.Tn.T("asr tool onEndSpeech");
                            Tn.Tr().T("com.txznet.txz", "txz.tool.asr.onEndSpeech", data, (Tn.Tr) null);
                        }

                        public void onEndRecord() {
                            com.txznet.comm.Tr.Tr.Tn.T("asr tool onEndRecord");
                            Tn.Tr().T("com.txznet.txz", "txz.tool.asr.onEndRecord", data, (Tn.Tr) null);
                        }

                        public void onCancel() {
                            com.txznet.comm.Tr.Tr.Tn.T("asr tool onCancel");
                            Tn.Tr().T("com.txznet.txz", "txz.tool.asr.onCancel", data, (Tn.Tr) null);
                        }

                        public void onBeginSpeech() {
                            com.txznet.comm.Tr.Tr.Tn.T("asr tool onBeginSpeech");
                            Tn.Tr().T("com.txznet.txz", "txz.tool.asr.onBeginSpeech", data, (Tn.Tr) null);
                        }

                        public void onBeginRecord() {
                            com.txznet.comm.Tr.Tr.Tn.T("asr tool onBeginRecord");
                            Tn.Tr().T("com.txznet.txz", "txz.tool.asr.onBeginRecord", data, (Tn.Tr) null);
                        }

                        public void onAbort() {
                            com.txznet.comm.Tr.Tr.Tn.T("asr tool onAbort");
                            Tn.Tr().T("com.txznet.txz", "txz.tool.asr.onAbort", data, (Tn.Tr) null);
                        }

                        public void onError(int errCode, String errDesc, String errHint) {
                            com.txznet.comm.Tr.Tr.Tn.T("asr tool onError: errCode=" + errCode + ", errDesc=" + errDesc);
                            json.T("volume");
                            json.T(TXZCameraManager.REMOTE_NAME_ERROR_CODE, (Object) Integer.valueOf(errCode));
                            json.T("errDesc", (Object) errDesc);
                            json.T("errHint", (Object) errHint);
                            Tn.Tr().T("com.txznet.txz", "txz.tool.asr.onError", json.Ty(), (Tn.Tr) null);
                        }

                        public void onTextResult(String text) {
                            Tr json = new Tr();
                            json.T("scene", (Object) "_raw_online");
                            json.T(SdkConstants.ATTR_TEXT, (Object) text);
                            onSceneResult(TXZSceneManager.SceneType.SCENE_TYPE_UNKNOW, json.toString());
                        }
                    });
                }
                return null;
            }
        });
    }

    public void setAsrPcmFile(String path) {
        Tr json = new Tr();
        json.T("audioSourcePath", (Object) path);
        Tn.Tr().T("com.txznet.txz", "comm.asr.set.rawaudio", json.Ty(), (Tn.Tr) null);
    }
}
