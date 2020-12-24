package com.txznet.sdk;

import android.os.SystemClock;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.bean.FlowInfo;
import com.txznet.sdk.bean.TrafficControlData;
import com.txznet.sdk.bean.WeatherData;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: Proguard */
public class TXZNetDataProvider {

    /* renamed from: T  reason: collision with root package name */
    private static TXZNetDataProvider f768T;
    /* access modifiers changed from: private */
    public Map<Integer, T> Tr = new HashMap();

    /* compiled from: Proguard */
    public interface NetDataCallback<T> {
        void onError(int i);

        void onResult(T t);
    }

    private TXZNetDataProvider() {
    }

    public static TXZNetDataProvider getInstance() {
        if (f768T == null) {
            synchronized (TXZNetDataProvider.class) {
                if (f768T == null) {
                    f768T = new TXZNetDataProvider();
                }
            }
        }
        return f768T;
    }

    /* compiled from: Proguard */
    static class T {

        /* renamed from: T  reason: collision with root package name */
        int f775T = -1;
        NetDataCallback Tr;
        long Ty = 0;

        T() {
        }
    }

    public void getWeatherInfo(NetDataCallback<WeatherData> callback) {
        getWeatherInfo("cur", callback);
    }

    public void getWeatherInfo(String city, final NetDataCallback<WeatherData> callback) {
        Tr doc = new Tr();
        doc.T("city", (Object) city);
        int localTaskId = Tn.Tr().T("com.txznet.txz", "comm.netdata.req.weather", doc.Ty(), (Tn.Tr) new Tn.Tr() {
            public void T(Tn.Ty data) {
                if (data != null) {
                    synchronized (TXZNetDataProvider.this.Tr) {
                        T remoteTask = (T) TXZNetDataProvider.this.Tr.get(Integer.valueOf(Tr()));
                        if (remoteTask != null) {
                            remoteTask.f775T = data.Ty().intValue();
                        }
                    }
                }
                if (callback != null && T()) {
                    callback.onError(1);
                    synchronized (TXZNetDataProvider.this.Tr) {
                        TXZNetDataProvider.this.Tr.remove(Integer.valueOf(Tr()));
                    }
                }
            }
        });
        if (callback != null) {
            addTask(localTaskId, callback);
        }
    }

    public void getFlowInfo(final NetDataCallback<FlowInfo> callback) {
        int localTaskId = Tn.Tr().T("com.txznet.txz", "comm.netdata.req.flowInfo", (byte[]) null, (Tn.Tr) new Tn.Tr() {
            public void T(Tn.Ty data) {
                if (data != null) {
                    synchronized (TXZNetDataProvider.this.Tr) {
                        T remoteTask = (T) TXZNetDataProvider.this.Tr.get(Integer.valueOf(Tr()));
                        if (remoteTask != null) {
                            remoteTask.f775T = data.Ty().intValue();
                        }
                    }
                }
                if (callback != null && T()) {
                    callback.onError(1);
                    synchronized (TXZNetDataProvider.this.Tr) {
                        TXZNetDataProvider.this.Tr.remove(Integer.valueOf(Tr()));
                    }
                }
            }
        });
        if (callback != null) {
            addTask(localTaskId, callback);
        }
    }

    public void addTask(int localTaskId, NetDataCallback callback) {
        addTask(localTaskId, callback, 60000);
    }

    public void addTask(int localTaskId, NetDataCallback callback, int timeout) {
        synchronized (this.Tr) {
            long now = SystemClock.elapsedRealtime();
            T(now);
            T remoteTask = new T();
            remoteTask.Tr = callback;
            remoteTask.Ty = ((long) timeout) + now;
            this.Tr.put(Integer.valueOf(localTaskId), remoteTask);
        }
    }

    private void T(long now) {
        Iterator<Integer> iterator = this.Tr.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            T task = this.Tr.get(key);
            if (now > task.Ty) {
                if (task.Tr != null) {
                    task.Tr.onError(2);
                }
                com.txznet.comm.Tr.Tr.Tn.T("task(" + key + ") process timeout clean");
                iterator.remove();
            }
        }
    }

    public void getTrafficControlInfo(NetDataCallback<TrafficControlData> callback) {
        getTrafficControlInfo("cur", callback);
    }

    public void getTrafficControlInfo(String city, final NetDataCallback<TrafficControlData> callback) {
        Tr doc = new Tr();
        doc.T("city", (Object) city);
        int localTaskId = Tn.Tr().T("com.txznet.txz", "comm.netdata.req.traffic", doc.Ty(), (Tn.Tr) new Tn.Tr() {
            public void T(Tn.Ty data) {
                if (data != null) {
                    synchronized (TXZNetDataProvider.this.Tr) {
                        T remoteTask = (T) TXZNetDataProvider.this.Tr.get(Integer.valueOf(Tr()));
                        if (remoteTask != null) {
                            remoteTask.f775T = data.Ty().intValue();
                        }
                    }
                }
                if (callback != null && T()) {
                    callback.onError(1);
                    synchronized (TXZNetDataProvider.this.Tr) {
                        TXZNetDataProvider.this.Tr.remove(Integer.valueOf(Tr()));
                    }
                }
            }
        });
        if (callback != null) {
            addTask(localTaskId, callback);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004c, code lost:
        if (r4 != 0) goto L_0x00b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0054, code lost:
        if ("weather".equals(r12) == false) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0056, code lost:
        r2 = (java.lang.String) r0.T(com.txznet.sdk.tongting.IConstantData.KEY_DATA, java.lang.String.class);
        com.txznet.T.T.Ty(new com.txznet.sdk.TXZNetDataProvider.AnonymousClass4(r11), 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x006a, code lost:
        r11.Tr.remove(java.lang.Integer.valueOf(r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x007c, code lost:
        if ("traffic".equals(r12) == false) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007e, code lost:
        r2 = (java.lang.String) r0.T(com.txznet.sdk.tongting.IConstantData.KEY_DATA, java.lang.String.class);
        com.txznet.T.T.Ty(new com.txznet.sdk.TXZNetDataProvider.AnonymousClass5(r11), 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x009c, code lost:
        if ("flowInfo".equals(r12) == false) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x009e, code lost:
        r2 = (java.lang.String) r0.T(com.txznet.sdk.tongting.IConstantData.KEY_DATA, java.lang.String.class);
        com.txznet.T.T.Ty(new com.txznet.sdk.TXZNetDataProvider.AnonymousClass6(r11), 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b3, code lost:
        r5.Tr.onError(((java.lang.Integer) r0.T(com.txznet.sdk.tongting.IConstantData.KEY_ERRORCODE, java.lang.Integer.class)).intValue());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] notifyCallback(java.lang.String r12, byte[] r13) {
        /*
            r11 = this;
            java.util.Map<java.lang.Integer, com.txznet.sdk.TXZNetDataProvider$T> r8 = r11.Tr
            monitor-enter(r8)
            java.util.Map<java.lang.Integer, com.txznet.sdk.TXZNetDataProvider$T> r7 = r11.Tr     // Catch:{ all -> 0x0093 }
            java.util.Set r7 = r7.keySet()     // Catch:{ all -> 0x0093 }
            java.util.Iterator r9 = r7.iterator()     // Catch:{ all -> 0x0093 }
        L_0x000d:
            boolean r7 = r9.hasNext()     // Catch:{ all -> 0x0093 }
            if (r7 == 0) goto L_0x0073
            java.lang.Object r3 = r9.next()     // Catch:{ all -> 0x0093 }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ all -> 0x0093 }
            java.util.Map<java.lang.Integer, com.txznet.sdk.TXZNetDataProvider$T> r7 = r11.Tr     // Catch:{ all -> 0x0093 }
            java.lang.Object r5 = r7.get(r3)     // Catch:{ all -> 0x0093 }
            com.txznet.sdk.TXZNetDataProvider$T r5 = (com.txznet.sdk.TXZNetDataProvider.T) r5     // Catch:{ all -> 0x0093 }
            if (r5 == 0) goto L_0x000d
            com.txznet.sdk.TXZNetDataProvider$NetDataCallback r7 = r5.Tr     // Catch:{ all -> 0x0093 }
            if (r7 == 0) goto L_0x000d
            com.txznet.comm.Ty.Tr r0 = new com.txznet.comm.Ty.Tr     // Catch:{ all -> 0x0093 }
            r0.<init>((byte[]) r13)     // Catch:{ all -> 0x0093 }
            java.lang.String r7 = "rc"
            java.lang.Class<java.lang.Integer> r10 = java.lang.Integer.class
            java.lang.Object r7 = r0.T((java.lang.String) r7, r10)     // Catch:{ all -> 0x0093 }
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch:{ all -> 0x0093 }
            int r4 = r7.intValue()     // Catch:{ all -> 0x0093 }
            java.lang.String r7 = "taskId"
            java.lang.Class<java.lang.Integer> r10 = java.lang.Integer.class
            java.lang.Object r7 = r0.T((java.lang.String) r7, r10)     // Catch:{ all -> 0x0093 }
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch:{ all -> 0x0093 }
            int r6 = r7.intValue()     // Catch:{ all -> 0x0093 }
            int r7 = r5.f775T     // Catch:{ all -> 0x0093 }
            if (r7 != r6) goto L_0x000d
            if (r4 != 0) goto L_0x00b3
            java.lang.String r7 = "weather"
            boolean r7 = r7.equals(r12)     // Catch:{ all -> 0x0093 }
            if (r7 == 0) goto L_0x0076
            java.lang.String r7 = "data"
            java.lang.Class<java.lang.String> r9 = java.lang.String.class
            java.lang.Object r2 = r0.T((java.lang.String) r7, r9)     // Catch:{ all -> 0x0093 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x0093 }
            com.txznet.sdk.TXZNetDataProvider$4 r7 = new com.txznet.sdk.TXZNetDataProvider$4     // Catch:{ all -> 0x0093 }
            r7.<init>(r5, r2)     // Catch:{ all -> 0x0093 }
            r9 = 0
            com.txznet.T.T.Ty(r7, r9)     // Catch:{ all -> 0x0093 }
        L_0x006a:
            java.util.Map<java.lang.Integer, com.txznet.sdk.TXZNetDataProvider$T> r7 = r11.Tr     // Catch:{ all -> 0x0093 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0093 }
            r7.remove(r9)     // Catch:{ all -> 0x0093 }
        L_0x0073:
            monitor-exit(r8)     // Catch:{ all -> 0x0093 }
            r7 = 0
            return r7
        L_0x0076:
            java.lang.String r7 = "traffic"
            boolean r7 = r7.equals(r12)     // Catch:{ all -> 0x0093 }
            if (r7 == 0) goto L_0x0096
            java.lang.String r7 = "data"
            java.lang.Class<java.lang.String> r9 = java.lang.String.class
            java.lang.Object r2 = r0.T((java.lang.String) r7, r9)     // Catch:{ all -> 0x0093 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x0093 }
            com.txznet.sdk.TXZNetDataProvider$5 r7 = new com.txznet.sdk.TXZNetDataProvider$5     // Catch:{ all -> 0x0093 }
            r7.<init>(r5, r2)     // Catch:{ all -> 0x0093 }
            r9 = 0
            com.txznet.T.T.Ty(r7, r9)     // Catch:{ all -> 0x0093 }
            goto L_0x006a
        L_0x0093:
            r7 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x0093 }
            throw r7
        L_0x0096:
            java.lang.String r7 = "flowInfo"
            boolean r7 = r7.equals(r12)     // Catch:{ all -> 0x0093 }
            if (r7 == 0) goto L_0x006a
            java.lang.String r7 = "data"
            java.lang.Class<java.lang.String> r9 = java.lang.String.class
            java.lang.Object r2 = r0.T((java.lang.String) r7, r9)     // Catch:{ all -> 0x0093 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x0093 }
            com.txznet.sdk.TXZNetDataProvider$6 r7 = new com.txznet.sdk.TXZNetDataProvider$6     // Catch:{ all -> 0x0093 }
            r7.<init>(r5, r2)     // Catch:{ all -> 0x0093 }
            r9 = 0
            com.txznet.T.T.Ty(r7, r9)     // Catch:{ all -> 0x0093 }
            goto L_0x006a
        L_0x00b3:
            java.lang.String r7 = "errorCode"
            java.lang.Class<java.lang.Integer> r9 = java.lang.Integer.class
            java.lang.Object r7 = r0.T((java.lang.String) r7, r9)     // Catch:{ all -> 0x0093 }
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch:{ all -> 0x0093 }
            int r1 = r7.intValue()     // Catch:{ all -> 0x0093 }
            com.txznet.sdk.TXZNetDataProvider$NetDataCallback r7 = r5.Tr     // Catch:{ all -> 0x0093 }
            r7.onError(r1)     // Catch:{ all -> 0x0093 }
            goto L_0x006a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.txznet.sdk.TXZNetDataProvider.notifyCallback(java.lang.String, byte[]):byte[]");
    }
}
