package com.txznet.comm.Tr.Tr;

import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.txznet.comm.Tr.Tn;
import com.txznet.sdk.TXZCameraManager;
import com.txznet.sdk.tongting.IConstantData;
import com.txznet.txz.T.Tn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: Proguard */
public class T {

    /* renamed from: T  reason: collision with root package name */
    public static final String[] f381T = {"第一个", "第二个", "第三个", "第四个", "第五个", "第六个", "第七个", "第八个", "第九个", "第十个"};
    /* access modifiers changed from: private */
    public static Map<String, String> T5 = new HashMap();
    static Map<String, Tk> T9 = new ConcurrentHashMap();
    private static T9 TE = null;
    private static Ty TZ = null;
    private static Runnable Th = new Runnable() {
        public void run() {
            synchronized (T.T5) {
                for (Map.Entry<String, String> entry : T.T5.entrySet()) {
                    com.txznet.comm.Ty.Tr json = new com.txznet.comm.Ty.Tr();
                    json.T("cmds", (Object) new String[]{entry.getKey()});
                    json.T(IConstantData.KEY_DATA, (Object) entry.getValue());
                    com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "comm.asr.regcmd", json.toString().getBytes(), (Tn.Tr) null);
                }
            }
            synchronized (T.T9) {
                for (Map.Entry<String, Tk> entry2 : T.T9.entrySet()) {
                    T.T(entry2.getValue());
                }
            }
        }
    };
    private static Tn Tk = null;
    public static final String[] Tn = {"最后一个", "最后那个"};
    public static final String[] Tr = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};
    private static Runnable Tv = null;
    public static final String[] Ty = {"最前面那个"};

    /* compiled from: Proguard */
    public static abstract class T9 {
        public abstract void T(String str, byte[] bArr);
    }

    /* compiled from: Proguard */
    public static abstract class Tn {
        public abstract void T();

        public abstract void T(int i);

        public abstract void T(String str);

        public abstract void Tr();

        public abstract void Tr(int i);

        public abstract void Ty();

        public abstract void Ty(int i);
    }

    /* compiled from: Proguard */
    public static class Ty {
    }

    public static void T(String hint) {
        if (TextUtils.isEmpty(hint)) {
            hint = "";
        }
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "comm.asr.startWithRecordWin", hint.getBytes(), (Tn.Tr) null);
    }

    public static void T() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "comm.asr.start", (byte[]) null, (Tn.Tr) null);
    }

    public static void Tr() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "comm.asr.stop", (byte[]) null, (Tn.Tr) null);
    }

    public static void Ty() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "comm.asr.cancel", (byte[]) null, (Tn.Tr) null);
    }

    public static void T(String[] cmds, String data, T9 callBack) {
        synchronized (T5) {
            for (String cmd : cmds) {
                T5.put(cmd, data);
            }
        }
        TE = callBack;
        com.txznet.comm.Ty.Tr json = new com.txznet.comm.Ty.Tr();
        json.T("cmds", (Object) cmds);
        json.T(IConstantData.KEY_DATA, (Object) data);
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "comm.asr.regcmd", json.toString().getBytes(), (Tn.Tr) null);
        if (Tv == null) {
            Tv = new Runnable() {
                public void run() {
                    T.Tn();
                }
            };
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", Tv);
        }
    }

    public static void T(String[] cmds) {
        synchronized (T5) {
            for (String cmd : cmds) {
                T5.remove(cmd);
            }
        }
        com.txznet.comm.Ty.Tr json = new com.txznet.comm.Ty.Tr();
        json.T("cmds", (Object) cmds);
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "comm.asr.unregcmd", json.toString().getBytes(), (Tn.Tr) null);
    }

    public static void Tn() {
        com.txznet.comm.Tr.Tn.Tr().T(Th);
        com.txznet.comm.Tr.Tn.Tr().T(Th, 100);
    }

    public static void T(String event, byte[] data) {
        if (event.equals("regnotify")) {
            if (TE != null) {
                com.txznet.comm.Ty.Tr json = new com.txznet.comm.Ty.Tr(data);
                TE.T((String) json.T("cmd", String.class), ((String) json.T(IConstantData.KEY_DATA, String.class)).getBytes());
            }
        } else if (Tk == null) {
            Log.i("AsrUtil", "mAsrCallBack == null");
        } else if (event.equals("success")) {
            Tk.T(new String(data));
        } else if (event.equals("cancel")) {
            Tk.T();
        } else if (event.equals("start")) {
            Tk.Tr();
        } else if (event.equals("end")) {
            Tk.Ty();
        } else if (event.equals("abort")) {
            Tk.Tr(-1);
        } else if (event.equals("error")) {
            int err = 0;
            try {
                err = Integer.parseInt(new String(data));
            } catch (NumberFormatException e) {
                Log.e("AsrUtil", "convert string to int error");
            }
            Tk.T(err);
        } else if (event.equals("volume")) {
            int volume = 0;
            try {
                volume = Integer.parseInt(new String(data));
            } catch (NumberFormatException e2) {
                Log.e("AsrUtil", "convert string to int error");
            }
            Tk.Ty(volume);
        }
    }

    public static String[] T(int n, String[] suffix, String[] ext1, String[] ext2, String[] ext3, String[] ext4) {
        int x;
        int x2;
        if (n > 10) {
            n = 10;
        }
        if (suffix == null) {
            suffix = new String[0];
        }
        if (ext1 == null) {
            ext1 = new String[0];
        }
        if (ext2 == null) {
            ext2 = new String[0];
        }
        if (ext3 == null) {
            ext3 = new String[0];
        }
        if (ext4 == null) {
            ext4 = new String[0];
        }
        String[] ret = new String[(((suffix.length + 2) * n) + ext1.length + ext2.length + ext3.length + ext4.length)];
        int i = 0;
        int x3 = 0;
        while (i < n) {
            int x4 = x3 + 1;
            ret[x3] = f381T[i];
            int x5 = x4 + 1;
            ret[x4] = "UI_SELECT_" + (i + 1);
            int j = 0;
            while (true) {
                x2 = x5;
                if (j >= suffix.length) {
                    break;
                }
                x5 = x2 + 1;
                ret[x2] = f381T[i] + suffix[j];
                j++;
            }
            i++;
            x3 = x2;
        }
        int i2 = 0;
        while (true) {
            x = x3;
            if (i2 >= ext1.length) {
                break;
            }
            x3 = x + 1;
            ret[x] = ext1[i2];
            i2++;
        }
        int i3 = 0;
        while (i3 < ext2.length) {
            ret[x] = ext2[i3];
            i3++;
            x++;
        }
        int i4 = 0;
        while (i4 < ext3.length) {
            ret[x] = ext3[i4];
            i4++;
            x++;
        }
        int i5 = 0;
        while (i5 < ext4.length) {
            ret[x] = ext4[i5];
            i5++;
            x++;
        }
        return ret;
    }

    /* compiled from: Proguard */
    public static abstract class Tk {
        boolean Tn = true;
        boolean Ty = true;

        public abstract String getTaskId();

        public abstract boolean needAsrState();

        public void setIsFromCore(boolean fromCore) {
            this.Tn = fromCore;
        }

        public boolean isFromCore() {
            return this.Tn;
        }

        public void setIsWakeupResult(boolean b) {
            this.Ty = b;
        }

        public boolean isWakeupResult() {
            return this.Ty;
        }

        public void onVolume(int volume) {
        }

        public void onSpeechBegin() {
        }

        public void onSpeechEnd() {
        }

        public boolean onAsrResult(String text) {
            return false;
        }

        public String[] genKeywords() {
            return new String[0];
        }

        public String needTts() {
            return null;
        }

        public void onTtsBegin() {
        }

        public void onTtsEnd() {
        }

        public int getPriority() {
            return 0;
        }
    }

    /* compiled from: Proguard */
    public static abstract class Tr extends Tk {

        /* renamed from: T  reason: collision with root package name */
        String[] f383T = new String[0];
        String[] Tr = new String[0];

        public abstract void T();

        public abstract void Tr();

        public Tr(String[] mSureCmds, String[] mCancelCmds) {
            this.f383T = mSureCmds;
            this.Tr = mCancelCmds;
        }

        public boolean onAsrResult(String text) {
            for (String equals : this.f383T) {
                if (text.equals(equals)) {
                    T();
                    return true;
                }
            }
            for (String equals2 : this.Tr) {
                if (text.equals(equals2)) {
                    Tr();
                    return true;
                }
            }
            return false;
        }

        public String[] genKeywords() {
            return T.T(0, (String[]) null, this.f383T, this.Tr, (String[]) null, (String[]) null);
        }
    }

    /* renamed from: com.txznet.comm.Tr.Tr.T$T  reason: collision with other inner class name */
    /* compiled from: Proguard */
    public static abstract class C0015T extends Tk {

        /* renamed from: T  reason: collision with root package name */
        private SparseArray<Set<String>> f382T = new SparseArray<>();
        private Map<String, Set<String>> Tr = new HashMap();

        private void T(Set<String> cmdSet, String cmd) {
            Tn.T("wakeup add asr command: " + cmd);
            if (!TextUtils.isDigitsOnly(cmd) || cmd.length() >= 3) {
                cmdSet.add(cmd);
            } else {
                Tn.T("skip the only digit wakeup add asr command: " + cmd);
            }
        }

        private void T(Set<String> cmdSet, String[] cmds) {
            for (String cmd : cmds) {
                T(cmdSet, cmd);
            }
        }

        public C0015T addCommand(String type, String... cmds) {
            Set<String> cmdSet = this.Tr.get(type);
            if (cmdSet == null) {
                Map<String, Set<String>> map = this.Tr;
                cmdSet = new HashSet<>();
                map.put(type, cmdSet);
            }
            T(cmdSet, cmds);
            return this;
        }

        public C0015T addIndex(int index, String... cmds) {
            Set<String> cmdSet = this.f382T.get(index);
            if (cmdSet == null) {
                SparseArray<Set<String>> sparseArray = this.f382T;
                cmdSet = new HashSet<>();
                sparseArray.append(index, cmdSet);
            }
            T(cmdSet, cmds);
            return this;
        }

        public boolean onAsrResult(String text) {
            for (Map.Entry<String, Set<String>> entry : this.Tr.entrySet()) {
                Iterator<String> it = entry.getValue().iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().equals(text)) {
                            onCommandSelected(entry.getKey(), text);
                            return true;
                        }
                    }
                }
            }
            List<Integer> indexs = new ArrayList<>();
            for (int i = 0; i < this.f382T.size(); i++) {
                Iterator<String> it2 = this.f382T.valueAt(i).iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (it2.next().equals(text)) {
                            indexs.add(Integer.valueOf(this.f382T.keyAt(i)));
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (indexs.isEmpty()) {
                return false;
            }
            onIndexSelected(indexs, text);
            return true;
        }

        public String[] genKeywords() {
            Set<String> setKeywords = new HashSet<>();
            for (Map.Entry<String, Set<String>> entry : this.Tr.entrySet()) {
                setKeywords.addAll(entry.getValue());
            }
            for (int i = 0; i < this.f382T.size(); i++) {
                setKeywords.addAll(this.f382T.valueAt(i));
            }
            String[] ret = new String[setKeywords.size()];
            setKeywords.toArray(ret);
            return ret;
        }

        public void onIndexSelected(List<Integer> list, String command) {
            Tn.Tn("onIndexSelected no implement");
        }

        public void onCommandSelected(String type, String command) {
            Tn.Tn("onCommandSelected no implement");
        }
    }

    public static void Tr(String data) {
        com.txznet.comm.Ty.Tr json = new com.txznet.comm.Ty.Tr(data);
        Tk cb = T9.get((String) json.T(TXZCameraManager.REMOTE_NAME_TASK_ID, String.class));
        if (cb != null) {
            cb.setIsFromCore(false);
            cb.setIsWakeupResult(((Boolean) json.T("isWakeupResult", Boolean.class, true)).booleanValue());
            cb.onAsrResult((String) json.T("text", String.class));
        }
    }

    public static void Ty(String data) {
        Tk cb = T9.get((String) new com.txznet.comm.Ty.Tr(data).T(TXZCameraManager.REMOTE_NAME_TASK_ID, String.class));
        if (cb != null) {
            cb.onTtsBegin();
        }
    }

    public static void Tn(String data) {
        Tk cb = T9.get((String) new com.txznet.comm.Ty.Tr(data).T(TXZCameraManager.REMOTE_NAME_TASK_ID, String.class));
        if (cb != null) {
            cb.onTtsEnd();
        }
    }

    public static void T9(String data) {
        Tk cb = T9.get((String) new com.txznet.comm.Ty.Tr(data).T(TXZCameraManager.REMOTE_NAME_TASK_ID, String.class));
        if (cb != null) {
            cb.onSpeechBegin();
        }
    }

    public static void Tk(String data) {
        Tk cb = T9.get((String) new com.txznet.comm.Ty.Tr(data).T(TXZCameraManager.REMOTE_NAME_TASK_ID, String.class));
        if (cb != null) {
            cb.onSpeechEnd();
        }
    }

    public static void T(Tk callback) {
        synchronized (T9) {
            T9.put(callback.getTaskId(), callback);
        }
        com.txznet.comm.Ty.Tr json = new com.txznet.comm.Ty.Tr();
        json.T("cmds", (Object) callback.genKeywords());
        String tts = callback.needTts();
        if (tts != null) {
            json.T("tts", (Object) tts);
        }
        json.T(IConstantData.KEY_STATE, (Object) Boolean.valueOf(callback.needAsrState()));
        json.T(TXZCameraManager.REMOTE_NAME_TASK_ID, (Object) callback.getTaskId());
        json.T("priority", (Object) Integer.valueOf(callback.getPriority()));
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "comm.asr.useWakeupAsAsr", json.Ty(), (Tn.Tr) null);
    }

    public static void TZ(String taskId) {
        synchronized (T9) {
            T9.remove(taskId);
        }
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "comm.asr.recoverWakeupFromAsr", taskId.getBytes(), (Tn.Tr) null);
    }

    public static void T9() {
    }

    public static void Tk() {
        com.txznet.txz.T.Tn.T("comm.asr.", new Tn.T() {
        });
    }
}
