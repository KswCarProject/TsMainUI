package com.txznet.comm.Tr.Tr;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseArray;
import com.txznet.comm.Tr.Tn;
import com.txznet.sdk.tongting.IConstantData;
import com.txznet.txz.T.Tn;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Proguard */
public class Th {

    /* renamed from: T  reason: collision with root package name */
    public static int f390T = 4;
    /* access modifiers changed from: private */
    public static SparseArray<Ty> T9 = new SparseArray<>();
    /* access modifiers changed from: private */
    public static Boolean TZ = null;
    private static SparseArray<ArrayList<Tn>> Tk = new SparseArray<>();
    private static final Tr Tn = Tr.PREEMPT_TYPE_NONE;
    static Runnable Tr = null;
    private static final T Ty = null;

    /* compiled from: Proguard */
    public enum Tr {
        PREEMPT_TYPE_NONE,
        PREEMPT_TYPE_IMMEADIATELY,
        PREEMPT_TYPE_NEXT,
        PREEMPT_TYPE_FLUSH,
        PREEMPT_TYPE_IMMEADIATELY_WITHOUT_CANCLE
    }

    static {
        Ty();
    }

    /* compiled from: Proguard */
    public static class T9 {
        public long duration;
        public String text = "";
        public T type;
        public String url = "";

        /* compiled from: Proguard */
        public enum T {
            TEXT,
            LOCAL_URL,
            NET_URL,
            BEEP,
            QUIET,
            ALERT
        }

        protected T9() {
        }

        public T9(T type2) {
            if (type2 == null) {
                throw new NullPointerException("VoiceTask.VoiceTaskType == null");
            }
            this.type = type2;
        }

        public T9 setText(String text2) {
            this.text = text2;
            return this;
        }

        public T9 setUrl(String url2) {
            this.url = url2;
            return this;
        }

        public T9 setDuration(long duration2) {
            this.duration = duration2;
            return this;
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private org.json.JSONObject T() {
            /*
                r5 = this;
                org.json.JSONObject r1 = new org.json.JSONObject
                r1.<init>()
                java.lang.String r2 = "type"
                com.txznet.comm.Tr.Tr.Th$T9$T r3 = r5.type     // Catch:{ Exception -> 0x0026 }
                java.lang.String r3 = r3.name()     // Catch:{ Exception -> 0x0026 }
                r1.put(r2, r3)     // Catch:{ Exception -> 0x0026 }
                int[] r2 = com.txznet.comm.Tr.Tr.Th.AnonymousClass8.f396T     // Catch:{ Exception -> 0x0026 }
                com.txznet.comm.Tr.Tr.Th$T9$T r3 = r5.type     // Catch:{ Exception -> 0x0026 }
                int r3 = r3.ordinal()     // Catch:{ Exception -> 0x0026 }
                r2 = r2[r3]     // Catch:{ Exception -> 0x0026 }
                switch(r2) {
                    case 1: goto L_0x001e;
                    case 2: goto L_0x0032;
                    case 3: goto L_0x0032;
                    case 4: goto L_0x003a;
                    case 5: goto L_0x003a;
                    case 6: goto L_0x001d;
                    default: goto L_0x001d;
                }     // Catch:{ Exception -> 0x0026 }
            L_0x001d:
                return r1
            L_0x001e:
                java.lang.String r2 = "text"
                java.lang.String r3 = r5.text     // Catch:{ Exception -> 0x0026 }
                r1.put(r2, r3)     // Catch:{ Exception -> 0x0026 }
                goto L_0x001d
            L_0x0026:
                r0 = move-exception
                java.lang.String r2 = r0.getMessage()
                com.txznet.comm.Tr.Tr.Tn.Ty((java.lang.String) r2)
                r0.printStackTrace()
                goto L_0x001d
            L_0x0032:
                java.lang.String r2 = "url"
                java.lang.String r3 = r5.url     // Catch:{ Exception -> 0x0026 }
                r1.put(r2, r3)     // Catch:{ Exception -> 0x0026 }
                goto L_0x001d
            L_0x003a:
                java.lang.String r2 = "duration"
                long r3 = r5.duration     // Catch:{ Exception -> 0x0026 }
                r1.put(r2, r3)     // Catch:{ Exception -> 0x0026 }
                goto L_0x001d
            */
            throw new UnsupportedOperationException("Method not decompiled: com.txznet.comm.Tr.Tr.Th.T9.T():org.json.JSONObject");
        }

        public static JSONArray toJsonArray(T9[] voiceTasks) {
            if (voiceTasks == null) {
                return null;
            }
            JSONArray jsonArray = new JSONArray();
            for (T9 voiceTask : voiceTasks) {
                if (voiceTask != null) {
                    jsonArray.put(voiceTask.T());
                } else {
                    jsonArray.put((Object) null);
                }
            }
            return jsonArray;
        }

        private static T9 T(JSONObject jsonObject) {
            String type2;
            if (jsonObject == null || (type2 = jsonObject.optString(IConstantData.KEY_TYPE, (String) null)) == null) {
                return null;
            }
            T9 voiceTask = new T9(T.valueOf(type2));
            switch (voiceTask.type) {
                case TEXT:
                    voiceTask.setText(jsonObject.optString("text", ""));
                    return voiceTask;
                case LOCAL_URL:
                case NET_URL:
                    voiceTask.setUrl(jsonObject.optString("url", ""));
                    return voiceTask;
                case QUIET:
                case ALERT:
                    voiceTask.setDuration(jsonObject.optLong(IConstantData.KEY_DURATION, 0));
                    return voiceTask;
                case BEEP:
                    return voiceTask;
                default:
                    return null;
            }
        }

        public static T9[] parseJsonArray(JSONArray jsonArray) {
            if (jsonArray == null) {
                return null;
            }
            int len = jsonArray.length();
            T9[] voiceTasks = new T9[len];
            for (int i = 0; i < len; i++) {
                voiceTasks[i] = T(jsonArray.optJSONObject(i));
            }
            return voiceTasks;
        }

        public static String toText(T9[] voiceTasks) {
            if (voiceTasks == null || voiceTasks.length == 0) {
                return null;
            }
            String string = "";
            for (T9 voiceTask : voiceTasks) {
                if (!(voiceTask == null || voiceTask.type != T.TEXT || voiceTask.text == null)) {
                    string = string + voiceTask.text;
                }
            }
            return string;
        }

        public static String[] toUrls(T9[] voiceTasks) {
            if (voiceTasks == null || voiceTasks.length == 0) {
                return null;
            }
            ArrayList<String> urls = new ArrayList<>();
            for (T9 voiceTask : voiceTasks) {
                if (voiceTask != null) {
                    String string = "";
                    switch (voiceTask.type) {
                        case LOCAL_URL:
                        case NET_URL:
                            string = voiceTask.url;
                            break;
                        case BEEP:
                            string = "$BEEP";
                            break;
                    }
                    if (!TextUtils.isEmpty(string)) {
                        urls.add(string);
                    }
                }
            }
            return (String[]) urls.toArray(new String[urls.size()]);
        }

        public String toString() {
            switch (this.type) {
                case TEXT:
                    return this.type.toString() + ":" + this.text;
                case LOCAL_URL:
                case NET_URL:
                    return this.type.toString() + ":" + this.url;
                case QUIET:
                case ALERT:
                    return this.type.toString() + ":" + this.duration;
                case BEEP:
                    return this.type.toString();
                default:
                    return "unkown type";
            }
        }
    }

    /* compiled from: Proguard */
    public static abstract class T {
        protected Object T9;
        protected int Tk = 0;

        public void onBegin() {
        }

        public void onEnd() {
        }

        public void onCancel() {
        }

        public void onSuccess() {
        }

        public void onError(int iError) {
        }

        public T setData(Object d) {
            this.T9 = d;
            return this;
        }

        public T setTaskId(int taskId) {
            this.Tk = taskId;
            return this;
        }

        public boolean isNeedStartAsr() {
            return false;
        }
    }

    /* compiled from: Proguard */
    private static class Ty {

        /* renamed from: T  reason: collision with root package name */
        int f400T;
        boolean Tr;
        T Ty;

        private Ty() {
            this.f400T = -1;
        }

        public int hashCode() {
            return this.f400T + 31;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            if (this.f400T != ((Ty) obj).f400T) {
                return false;
            }
            return true;
        }

        public String toString() {
            return "RemoteTtsTask [remoteId=" + this.f400T + ", isCanceled=" + this.Tr + ", callback=" + this.Ty + "]";
        }
    }

    /* compiled from: Proguard */
    private static class Tn {

        /* renamed from: T  reason: collision with root package name */
        String f398T;
        Integer Tr;
        long Ty;

        private Tn() {
        }
    }

    public static void T() {
        com.txznet.txz.T.Tn.T("comm.tts.", new Tn.T() {
        });
    }

    public static void Tr() {
        synchronized (T9) {
            for (int i = 0; i < T9.size(); i++) {
                Ty task = T9.valueAt(i);
                if (!(task == null || task.Ty == null)) {
                    task.Ty.onError(-1024);
                }
            }
            T9.clear();
        }
    }

    public static int T(String sText, Tr bPreempt, T oRun) {
        return T(-1, sText, bPreempt, oRun);
    }

    public static int T(String sText) {
        return T(-1, sText, Tn, Ty);
    }

    public static int T(int iStream, String sText, Tr bPreempt, T oRun) {
        return T(iStream, sText, (String[]) null, bPreempt, oRun);
    }

    public static int T(int iStream, String sText, long delay, Tr bPreempt, T oRun) {
        return T(iStream, sText, (String[]) null, delay, bPreempt, oRun);
    }

    public static int T(String resId, String[] resArgs, String defaultText, T oRun) {
        return T(-1, resId, resArgs, defaultText, (String[]) null, Tn, oRun);
    }

    public static int T(int iStream, String resId, String[] resArgs, String defaultText, Tr bPreempt, T oRun) {
        return T(iStream, resId, resArgs, defaultText, (String[]) null, bPreempt, oRun);
    }

    public static int T(int iStream, String resId, String[] resArgs, String defaultText, String[] voiceUrls, Tr preempt, T oRun) {
        return T(iStream, resId, resArgs, defaultText, voiceUrls, 0, preempt, oRun);
    }

    public static int Tr(String voiceUrl, Tr bPreempt, T oRun) {
        return T("", voiceUrl, bPreempt, oRun);
    }

    public static int T(String sText, String voiceUrl, Tr bPreempt, T oRun) {
        if (voiceUrl == null) {
            return T(-1, sText, new String[0], bPreempt, oRun);
        }
        return T(-1, sText, new String[]{voiceUrl}, bPreempt, oRun);
    }

    public static int T(int iStream, String sText, String[] voiceUrls, Tr bPreempt, T oRun) {
        return T(iStream, "", (String[]) null, sText, voiceUrls, 0, bPreempt, oRun);
    }

    public static int T(int iStream, String sText, String[] voiceUrls, long delay, Tr bPreempt, T oRun) {
        return T(iStream, "", (String[]) null, sText, voiceUrls, delay, bPreempt, oRun);
    }

    public static int T(int iStream, Tr bPreempt, T9[] voiceTasks, T oRun) {
        return T(iStream, "", (String[]) null, "", (String[]) null, 0, bPreempt, voiceTasks, oRun);
    }

    public static void Ty() {
        if (TZ == null) {
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "comm.tts.getFeatures", (byte[]) null, (Tn.Tr) new Tn.Tr() {
                public void T(final Tn.Ty data) {
                    if (data != null) {
                        com.txznet.comm.Tr.Tn.Tr().T((Runnable) new Runnable() {
                            public void run() {
                                synchronized (Th.T9) {
                                    JSONObject features = data.Tk();
                                    Boolean isSupportOnBeginOld = Th.TZ;
                                    if (features != null) {
                                        Boolean unused = Th.TZ = Boolean.valueOf(features.optBoolean("isSupportOnBegin", false));
                                    } else {
                                        Boolean unused2 = Th.TZ = false;
                                    }
                                    Tn.T("isSupportOnBegin: " + Th.TZ);
                                    if (isSupportOnBeginOld == null && !Th.TZ.booleanValue()) {
                                        for (int i = 0; i < Th.T9.size(); i++) {
                                            Ty task = (Ty) Th.T9.valueAt(i);
                                            if (!(task.f400T == -1 || task.Ty == null)) {
                                                task.Ty.onBegin();
                                            }
                                        }
                                    }
                                    Th.Tr((Integer) null);
                                }
                            }
                        }, 500);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void Tr(Integer remotetaskId) {
        synchronized (T9) {
            if (TZ != null) {
                int i = 0;
                while (i < Tk.size()) {
                    final int remoteId = Tk.keyAt(i);
                    final ArrayList<Tn> taskEventList = Tk.valueAt(i);
                    if (taskEventList.isEmpty()) {
                        Tk.removeAt(i);
                    } else if (SystemClock.elapsedRealtime() - taskEventList.get(taskEventList.size() - 1).Ty > 300000) {
                        Tk.removeAt(i);
                    } else if (remotetaskId == null || remoteId == remotetaskId.intValue()) {
                        com.txznet.comm.Tr.Tn.Tr().T((Runnable) new Runnable() {
                            public void run() {
                                Iterator it = taskEventList.iterator();
                                while (it.hasNext()) {
                                    Tn taskEvent = (Tn) it.next();
                                    Tn.Ty("process tts old event " + taskEvent.f398T + " for :" + remoteId);
                                    Th.T(taskEvent.f398T, remoteId, taskEvent.Tr);
                                }
                            }
                        }, 0);
                        Tk.removeAt(i);
                    } else {
                        i++;
                    }
                }
            }
        }
    }

    public static int T(int iStream, String resId, String[] resArgs, String sText, String[] voiceUrls, long delay, Tr bPreempt, T oRun) {
        return T(iStream, resId, resArgs, sText, voiceUrls, delay, bPreempt, (T9[]) null, oRun);
    }

    public static int T(int iStream, String resId, String[] resArgs, String sText, String[] voiceUrls, long delay, Tr bPreempt, T9[] voiceTasks, T oRun) {
        final T cb;
        int localTtsId;
        String data = T(iStream, sText, voiceUrls, bPreempt, resId, resArgs, delay, voiceTasks);
        if (oRun == null) {
            cb = null;
        } else {
            final T t = oRun;
            cb = new T() {

                /* renamed from: T  reason: collision with root package name */
                boolean f393T = false;
                boolean Tr = false;
                boolean Ty = false;

                public void onBegin() {
                    if (!this.f393T) {
                        this.f393T = true;
                        t.onBegin();
                    }
                }

                public void onEnd() {
                    if (!this.Ty) {
                        this.Ty = true;
                        t.onEnd();
                    }
                }

                public void onCancel() {
                    if (!this.Tr) {
                        this.Tr = true;
                        t.onCancel();
                        onEnd();
                    }
                }

                public void onSuccess() {
                    if (!this.f393T) {
                        Boolean unused = Th.TZ = false;
                        onBegin();
                    }
                    if (!this.Tr) {
                        this.Tr = true;
                        t.onSuccess();
                        onEnd();
                    }
                }

                public void onError(int iError) {
                    if (!this.Tr) {
                        this.Tr = true;
                        t.onError(iError);
                        onEnd();
                    }
                }
            };
        }
        synchronized (T9) {
            localTtsId = com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "comm.tts.speak", data.getBytes(), (Tn.Tr) new Tn.Tr() {
                /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
                    return;
                 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void T(com.txznet.comm.Tr.Tn.Ty r8) {
                    /*
                        r7 = this;
                        android.util.SparseArray r2 = com.txznet.comm.Tr.Tr.Th.T9
                        monitor-enter(r2)
                        android.util.SparseArray r1 = com.txznet.comm.Tr.Tr.Th.T9     // Catch:{ all -> 0x008c }
                        int r3 = r7.Tr()     // Catch:{ all -> 0x008c }
                        java.lang.Object r0 = r1.get(r3)     // Catch:{ all -> 0x008c }
                        com.txznet.comm.Tr.Tr.Th$Ty r0 = (com.txznet.comm.Tr.Tr.Th.Ty) r0     // Catch:{ all -> 0x008c }
                        if (r0 != 0) goto L_0x0031
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x008c }
                        r1.<init>()     // Catch:{ all -> 0x008c }
                        java.lang.String r3 = "find local task failed: "
                        java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ all -> 0x008c }
                        int r3 = r7.Tr()     // Catch:{ all -> 0x008c }
                        java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ all -> 0x008c }
                        java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x008c }
                        com.txznet.comm.Tr.Tr.Tn.Ty((java.lang.String) r1)     // Catch:{ all -> 0x008c }
                        monitor-exit(r2)     // Catch:{ all -> 0x008c }
                    L_0x0030:
                        return
                    L_0x0031:
                        java.lang.Boolean r1 = com.txznet.comm.Tr.Tr.Th.TZ     // Catch:{ all -> 0x008c }
                        if (r1 == 0) goto L_0x004a
                        java.lang.Boolean r1 = com.txznet.comm.Tr.Tr.Th.TZ     // Catch:{ all -> 0x008c }
                        boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x008c }
                        if (r1 != 0) goto L_0x004a
                        com.txznet.comm.Tr.Tr.Th$T r1 = r10     // Catch:{ all -> 0x008c }
                        if (r1 == 0) goto L_0x004a
                        com.txznet.comm.Tr.Tr.Th$T r1 = r10     // Catch:{ all -> 0x008c }
                        r1.onBegin()     // Catch:{ all -> 0x008c }
                    L_0x004a:
                        if (r8 == 0) goto L_0x009a
                        java.lang.Integer r1 = r8.Ty()     // Catch:{ all -> 0x008c }
                        int r1 = r1.intValue()     // Catch:{ all -> 0x008c }
                        r0.f400T = r1     // Catch:{ all -> 0x008c }
                        boolean r1 = r0.Tr     // Catch:{ all -> 0x008c }
                        if (r1 == 0) goto L_0x008f
                        com.txznet.comm.Tr.Tn r1 = com.txznet.comm.Tr.Tn.Tr()     // Catch:{ all -> 0x008c }
                        java.lang.String r3 = "com.txznet.txz"
                        java.lang.String r4 = "comm.tts.cancel"
                        java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x008c }
                        r5.<init>()     // Catch:{ all -> 0x008c }
                        java.lang.String r6 = ""
                        java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x008c }
                        int r6 = r0.f400T     // Catch:{ all -> 0x008c }
                        java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x008c }
                        java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x008c }
                        byte[] r5 = r5.getBytes()     // Catch:{ all -> 0x008c }
                        r6 = 0
                        r1.T((java.lang.String) r3, (java.lang.String) r4, (byte[]) r5, (com.txznet.comm.Tr.Tn.Tr) r6)     // Catch:{ all -> 0x008c }
                        android.util.SparseArray r1 = com.txznet.comm.Tr.Tr.Th.T9     // Catch:{ all -> 0x008c }
                        int r3 = r7.Tr()     // Catch:{ all -> 0x008c }
                        r1.remove(r3)     // Catch:{ all -> 0x008c }
                        monitor-exit(r2)     // Catch:{ all -> 0x008c }
                        goto L_0x0030
                    L_0x008c:
                        r1 = move-exception
                        monitor-exit(r2)     // Catch:{ all -> 0x008c }
                        throw r1
                    L_0x008f:
                        int r1 = r0.f400T     // Catch:{ all -> 0x008c }
                        java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x008c }
                        com.txznet.comm.Tr.Tr.Th.Tr(r1)     // Catch:{ all -> 0x008c }
                    L_0x0098:
                        monitor-exit(r2)     // Catch:{ all -> 0x008c }
                        goto L_0x0030
                    L_0x009a:
                        boolean r1 = r7.T()     // Catch:{ all -> 0x008c }
                        if (r1 == 0) goto L_0x0098
                        com.txznet.comm.Tr.Tr.Th$T r1 = r10     // Catch:{ all -> 0x008c }
                        if (r1 == 0) goto L_0x00af
                        com.txznet.comm.Tr.Tr.Th$T r1 = r10     // Catch:{ all -> 0x008c }
                        r1.onBegin()     // Catch:{ all -> 0x008c }
                        com.txznet.comm.Tr.Tr.Th$T r1 = r10     // Catch:{ all -> 0x008c }
                        r3 = 1
                        r1.onError(r3)     // Catch:{ all -> 0x008c }
                    L_0x00af:
                        android.util.SparseArray r1 = com.txznet.comm.Tr.Tr.Th.T9     // Catch:{ all -> 0x008c }
                        int r3 = r7.Tr()     // Catch:{ all -> 0x008c }
                        r1.remove(r3)     // Catch:{ all -> 0x008c }
                        goto L_0x0098
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.txznet.comm.Tr.Tr.Th.AnonymousClass5.T(com.txznet.comm.Tr.Tn$Ty):void");
                }
            });
            if (T9.get(localTtsId) != null) {
                Tn.Ty("already exist tts task id: " + localTtsId);
            }
            Ty remoteTtsTask = new Ty();
            remoteTtsTask.Ty = cb;
            T9.put(localTtsId, remoteTtsTask);
        }
        return localTtsId;
    }

    public static void T(int iTaskId) {
        synchronized (T9) {
            Ty task = T9.get(iTaskId);
            if (task != null) {
                if (task.Ty != null) {
                    com.txznet.comm.Tr.Tn.Tr().T((Runnable) new com.txznet.txz.util.T.Tr<Ty, Integer>(task, Integer.valueOf(iTaskId)) {
                        public void run() {
                            if (((Ty) this.Ty).Ty != null) {
                                ((Ty) this.Ty).Ty.onCancel();
                            }
                        }
                    }, 0);
                }
                if (task.f400T == -1) {
                    task.Tr = true;
                } else {
                    com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "comm.tts.cancel", ("" + task.f400T).getBytes(), (Tn.Tr) null);
                    if (task.Ty == null) {
                        T9.remove(iTaskId);
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void T(final java.lang.String r8, int r9, final java.lang.Integer r10) {
        /*
            android.util.SparseArray<com.txznet.comm.Tr.Tr.Th$Ty> r5 = T9
            monitor-enter(r5)
            java.lang.String r4 = "begin"
            boolean r4 = r4.equals(r8)     // Catch:{ all -> 0x0097 }
            if (r4 == 0) goto L_0x0012
            r4 = 1
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ all -> 0x0097 }
            TZ = r4     // Catch:{ all -> 0x0097 }
        L_0x0012:
            java.lang.Boolean r4 = TZ     // Catch:{ all -> 0x0097 }
            if (r4 == 0) goto L_0x009a
            r0 = 0
        L_0x0017:
            android.util.SparseArray<com.txznet.comm.Tr.Tr.Th$Ty> r4 = T9     // Catch:{ all -> 0x0097 }
            int r4 = r4.size()     // Catch:{ all -> 0x0097 }
            if (r0 >= r4) goto L_0x004e
            android.util.SparseArray<com.txznet.comm.Tr.Tr.Th$Ty> r4 = T9     // Catch:{ all -> 0x0097 }
            java.lang.Object r1 = r4.valueAt(r0)     // Catch:{ all -> 0x0097 }
            com.txznet.comm.Tr.Tr.Th$Ty r1 = (com.txznet.comm.Tr.Tr.Th.Ty) r1     // Catch:{ all -> 0x0097 }
            int r4 = r1.f400T     // Catch:{ all -> 0x0097 }
            if (r4 != r9) goto L_0x004b
            com.txznet.comm.Tr.Tr.Th$T r4 = r1.Ty     // Catch:{ all -> 0x0097 }
            if (r4 == 0) goto L_0x003c
            com.txznet.comm.Tr.Tn r4 = com.txznet.comm.Tr.Tn.Tr()     // Catch:{ all -> 0x0097 }
            com.txznet.comm.Tr.Tr.Th$7 r6 = new com.txznet.comm.Tr.Tr.Th$7     // Catch:{ all -> 0x0097 }
            r6.<init>(r8, r1, r10)     // Catch:{ all -> 0x0097 }
            r7 = 0
            r4.T((java.lang.Runnable) r6, (int) r7)     // Catch:{ all -> 0x0097 }
        L_0x003c:
            java.lang.String r4 = "begin"
            boolean r4 = r4.equals(r8)     // Catch:{ all -> 0x0097 }
            if (r4 != 0) goto L_0x0049
            android.util.SparseArray<com.txznet.comm.Tr.Tr.Th$Ty> r4 = T9     // Catch:{ all -> 0x0097 }
            r4.removeAt(r0)     // Catch:{ all -> 0x0097 }
        L_0x0049:
            monitor-exit(r5)     // Catch:{ all -> 0x0097 }
        L_0x004a:
            return
        L_0x004b:
            int r0 = r0 + 1
            goto L_0x0017
        L_0x004e:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0097 }
            r4.<init>()     // Catch:{ all -> 0x0097 }
            java.lang.String r6 = "can not found task: "
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x0097 }
            java.lang.StringBuilder r4 = r4.append(r9)     // Catch:{ all -> 0x0097 }
            java.lang.String r6 = " for event "
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x0097 }
            java.lang.StringBuilder r4 = r4.append(r8)     // Catch:{ all -> 0x0097 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0097 }
            com.txznet.comm.Tr.Tr.Tn.Ty((java.lang.String) r4)     // Catch:{ all -> 0x0097 }
        L_0x006e:
            android.util.SparseArray<java.util.ArrayList<com.txznet.comm.Tr.Tr.Th$Tn>> r4 = Tk     // Catch:{ all -> 0x0097 }
            java.lang.Object r3 = r4.get(r9)     // Catch:{ all -> 0x0097 }
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch:{ all -> 0x0097 }
            if (r3 != 0) goto L_0x0082
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x0097 }
            r3.<init>()     // Catch:{ all -> 0x0097 }
            android.util.SparseArray<java.util.ArrayList<com.txznet.comm.Tr.Tr.Th$Tn>> r4 = Tk     // Catch:{ all -> 0x0097 }
            r4.put(r9, r3)     // Catch:{ all -> 0x0097 }
        L_0x0082:
            com.txznet.comm.Tr.Tr.Th$Tn r2 = new com.txznet.comm.Tr.Tr.Th$Tn     // Catch:{ all -> 0x0097 }
            r4 = 0
            r2.<init>()     // Catch:{ all -> 0x0097 }
            r2.f398T = r8     // Catch:{ all -> 0x0097 }
            long r6 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x0097 }
            r2.Ty = r6     // Catch:{ all -> 0x0097 }
            r2.Tr = r10     // Catch:{ all -> 0x0097 }
            r3.add(r2)     // Catch:{ all -> 0x0097 }
            monitor-exit(r5)     // Catch:{ all -> 0x0097 }
            goto L_0x004a
        L_0x0097:
            r4 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0097 }
            throw r4
        L_0x009a:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0097 }
            r4.<init>()     // Catch:{ all -> 0x0097 }
            java.lang.String r6 = "need sync feature: "
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x0097 }
            java.lang.StringBuilder r4 = r4.append(r9)     // Catch:{ all -> 0x0097 }
            java.lang.String r6 = " for event "
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x0097 }
            java.lang.StringBuilder r4 = r4.append(r8)     // Catch:{ all -> 0x0097 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0097 }
            com.txznet.comm.Tr.Tr.Tn.Ty((java.lang.String) r4)     // Catch:{ all -> 0x0097 }
            goto L_0x006e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.txznet.comm.Tr.Tr.Th.T(java.lang.String, int, java.lang.Integer):void");
    }

    public static byte[] T(String packageName, String command, byte[] data) {
        if (!command.equals("speakTextOnRecordWin.end")) {
            com.txznet.comm.Ty.Tr jsonDoc = new com.txznet.comm.Ty.Tr(new String(data));
            int ttsId = ((Integer) jsonDoc.T("ttsId", Integer.class)).intValue();
            if (command.equals("begin")) {
                T("begin", ttsId, (Integer) null);
            } else if (command.equals("success")) {
                T("success", ttsId, (Integer) null);
            } else if (command.equals("cancel")) {
                T("cancel", ttsId, (Integer) null);
            } else if (command.equals("error")) {
                T("success", ttsId, Integer.valueOf(((Integer) jsonDoc.T("error", Integer.class)).intValue()));
            }
        } else if (Tr != null) {
            Tr.run();
        }
        return null;
    }

    private static String T(int iStream, String sText, String[] voiceUrls, Tr bPreempt, String resId, String[] resArgs, long delay, T9[] voiceTasks) {
        com.txznet.comm.Ty.Tr builder = new com.txznet.comm.Ty.Tr();
        builder.T("iStream", (Object) Integer.valueOf(iStream));
        builder.T("bPreempt", (Object) bPreempt.toString());
        builder.T("sText", (Object) sText);
        builder.T("voiceUrls", (Object) voiceUrls);
        if (!TextUtils.isEmpty(resId)) {
            builder.T("resId", (Object) resId);
            if (resArgs != null) {
                builder.T("resArgs", (Object) resArgs);
            }
        }
        builder.T("delay", (Object) Long.valueOf(delay));
        if (voiceTasks != null && voiceTasks.length > 0) {
            builder.T("voiceTask", (Object) T9.toJsonArray(voiceTasks));
            String text = T9.toText(voiceTasks);
            if (!TextUtils.isEmpty(text)) {
                builder.T("sText", (Object) text);
            }
            String[] urls = T9.toUrls(voiceTasks);
            if (urls != null && urls.length > 0) {
                builder.T("voiceUrls", (Object) urls);
            }
        }
        return builder.toString();
    }
}
