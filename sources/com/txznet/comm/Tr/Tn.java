package com.txznet.comm.Tr;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.DeadObjectException;
import android.os.Environment;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.android.SdkConstants;
import com.txznet.sdk.TXZResourceManager;
import com.txznet.txz.Tr.T;
import com.txznet.txz.util.T5;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* compiled from: Proguard */
public class Tn {
    /* access modifiers changed from: private */
    public static int TE;
    static Tn TZ = new Tn();

    /* renamed from: T  reason: collision with root package name */
    HandlerThread f367T = new HandlerThread("ServiceManagerThread");
    private int T5 = 1;
    public boolean T9 = false;
    /* access modifiers changed from: private */
    public Set<T> Th = new HashSet();
    Map<String, C0014Tn> Tk = new HashMap();
    String Tn = null;
    T5 Tr;
    /* access modifiers changed from: private */
    public long Tv = 0;
    T9 Ty = null;

    /* compiled from: Proguard */
    public interface T {
        void T(String str);

        void Tr(String str);
    }

    static {
        int r;
        TE = 1000;
        try {
            File f = new File("/etc/txz/txz_service.json");
            FileInputStream in = new FileInputStream(f);
            byte[] bs = new byte[((int) f.length())];
            int t = 0;
            while (t < bs.length && (r = in.read(bs, t, bs.length - t)) >= 0) {
                t += r;
            }
            in.close();
            TE = ((Integer) new com.txznet.comm.Ty.Tr(bs).T("DEFAULT_RECONNECT_DELAY", Integer.class, 1000)).intValue();
        } catch (Exception e) {
        }
        Log.d("txz_service", "DEFAULT_RECONNECT_DELAY=" + TE);
    }

    public void T(Runnable runnable) {
        this.Tr.Tr(runnable);
    }

    public void T(Runnable runnable, int delay) {
        this.Tr.T(runnable, (long) delay);
    }

    /* compiled from: Proguard */
    public static class Ty {

        /* renamed from: T  reason: collision with root package name */
        byte[] f383T;

        Ty(byte[] data) {
            this.f383T = data;
        }

        public String T() {
            try {
                return new String(this.f383T);
            } catch (Exception e) {
                return null;
            }
        }

        public byte[] Tr() {
            return this.f383T;
        }

        public Integer Ty() {
            try {
                return Integer.valueOf(Integer.parseInt(new String(this.f383T)));
            } catch (Exception e) {
                return null;
            }
        }

        public Double Tn() {
            try {
                return Double.valueOf(Double.parseDouble(new String(this.f383T)));
            } catch (Exception e) {
                return null;
            }
        }

        public Boolean T9() {
            try {
                return Boolean.valueOf(Boolean.parseBoolean(new String(this.f383T)));
            } catch (Exception e) {
                return null;
            }
        }

        public JSONObject Tk() {
            try {
                return new JSONObject(new String(this.f383T));
            } catch (Exception e) {
                return null;
            }
        }
    }

    /* compiled from: Proguard */
    public static abstract class Tr {
        boolean Tn;
        int Ty;

        public abstract void T(Ty ty);

        public boolean T() {
            return this.Tn;
        }

        public int Tr() {
            return this.Ty;
        }
    }

    /* compiled from: Proguard */
    class T9 {

        /* renamed from: T  reason: collision with root package name */
        int f376T;
        long T9;
        long Tk;
        Tr Tn;
        String Tr;
        byte[] Ty;

        T9() {
        }
    }

    /* renamed from: com.txznet.comm.Tr.Tn$Tn  reason: collision with other inner class name */
    /* compiled from: Proguard */
    class C0014Tn {

        /* renamed from: T  reason: collision with root package name */
        Tk f379T;
        List<T9> T9 = new ArrayList();
        Runnable TZ = new Runnable() {
            public void run() {
                if (C0014Tn.this.Tk < 10000) {
                    C0014Tn.this.Tk += 1000;
                }
                C0014Tn.this.Ty();
            }
        };
        int Tk = Tn.TE;
        boolean Tn = true;
        String Tr;
        com.txznet.txz.Tr.T Ty;

        public C0014Tn(String serviceName) {
            this.Tr = serviceName;
            this.f379T = new Tk(serviceName);
        }

        public void T() {
            Tn.this.Tr.T((Runnable) new Runnable() {
                public void run() {
                    if (C0014Tn.this.Ty != null) {
                        for (T listener : Tn.this.Th) {
                            listener.T(C0014Tn.this.Tr);
                        }
                        C0014Tn.this.Ty = null;
                        C0014Tn.this.Tk = Tn.TE;
                        if (C0014Tn.this.Tk > 0) {
                            Tn.Tr().T(C0014Tn.this.TZ);
                            Tn.Tr().T(C0014Tn.this.TZ, C0014Tn.this.Tk);
                        }
                        C0014Tn.this.Tr();
                    }
                }
            });
        }

        /* access modifiers changed from: private */
        public void Ty() {
            if (!Tn.this.T9 && this.Tn && this.Ty == null) {
                Intent intent = new Intent(this.Tr + ".service.TXZService");
                intent.setPackage(this.Tr);
                try {
                    Intent intentStart = new Intent(this.Tr + ".startTXZService");
                    intentStart.addFlags(32);
                    intentStart.setPackage(this.Tr);
                    if (Tn.this.Tv == 0 || Tn.this.Tv - System.currentTimeMillis() >= ((long) Tn.TE)) {
                        T.Tr().sendBroadcast(intentStart);
                        long unused = Tn.this.Tv = SystemClock.elapsedRealtime();
                    }
                    T.Tr().startService(intent);
                    T.Tr().bindService(intent, this.f379T, 65);
                } catch (Exception e) {
                }
                if (this.Ty != null || T(this.Tr)) {
                    Tn.Tr().T(this.TZ);
                    Tn.Tr().T(this.TZ, this.Tk);
                    return;
                }
                Log.d("ServiceManager", "target package not exist, set flag");
                this.Tn = false;
            }
        }

        private boolean T(String pkg) {
            try {
                ApplicationInfo info = T.Tr().getPackageManager().getApplicationInfo(pkg, 0);
                if (info == null || TextUtils.isEmpty(info.packageName)) {
                    Log.d("ServiceManager", "ApplicationInfo is null: " + pkg);
                    return false;
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.d("ServiceManager", "target package not found: " + pkg);
                return false;
            } catch (Exception e2) {
                Log.d("ServiceManager", "checkTargetService encountered error: " + e2.toString());
            }
            return true;
        }

        /* renamed from: com.txznet.comm.Tr.Tn$Tn$T */
        /* compiled from: Proguard */
        private class T extends RuntimeException {
            private long T9;
            private String Tn;
            private String Tr;
            private String Ty;

            public T(String from, String to, String command, long time) {
                super("TXZInvokeTimeoutException");
                this.Tr = from;
                this.Ty = to;
                this.Tn = command;
                this.T9 = time;
            }

            public void printStackTrace(PrintWriter err) {
                err.println("TXZInvokeTimeoutException");
                err.println("From=" + this.Tr);
                err.println("To=" + this.Ty);
                err.println("Command=" + this.Tn);
                err.println("CostTime=" + this.T9);
            }
        }

        public void Tr() {
            Tn.this.T(this.Tr, this.T9, this.Tn);
            if (this.Ty == null) {
                Ty();
                return;
            }
            while (0 < this.T9.size()) {
                T9 request = this.T9.get(0);
                if (this.Ty != null) {
                    long beginTime = SystemClock.elapsedRealtime();
                    Tn.this.Ty = request;
                    Tn.this.Tn = this.Tr;
                    byte[] remoteResp = (byte[]) Tn.this.T(this.Ty, this.Tr, request.Tr, request.Ty, T(request));
                    Tn.this.Ty = null;
                    Tn.this.Tn = null;
                    long costTime = SystemClock.elapsedRealtime() - beginTime;
                    if (costTime >= 50 && !request.Tr.equals("comm.log")) {
                        String from = T.Tr().getApplicationInfo().packageName;
                        com.txznet.comm.Tr.Tr.Tn.Ty("command[" + request.Tr + "] from [" + from + "] to [" + this.Tr + "] cost too much time: " + costTime);
                        if (T(request, costTime)) {
                            Log.e("ServiceManager", "request timed out, command = " + request.Tr + ", async = " + T(request));
                            com.txznet.comm.base.Ty.T(T.Tr(), Environment.getExternalStorageDirectory().getPath() + "/txz/report/", (Thread) null, new T(from, this.Tr, request.Tr, costTime));
                        }
                    }
                    if (request.Tn != null) {
                        try {
                            request.Tn.T(new Ty(remoteResp));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                this.T9.remove(0);
            }
        }

        private boolean T(T9 request) {
            return request.Tn == null;
        }

        private boolean T(T9 request, long costTime) {
            if (T(request)) {
                if (costTime >= 1000) {
                    return true;
                }
                return false;
            } else if (costTime < 2000) {
                return false;
            } else {
                return true;
            }
        }
    }

    /* access modifiers changed from: private */
    public void T(String serverName, List<T9> requestQueue, boolean needLog) {
        long now = SystemClock.elapsedRealtime();
        int i = 0;
        while (i < requestQueue.size()) {
            T9 request = requestQueue.get(i);
            if (now >= request.T9) {
                if (request.Tn != null) {
                    request.Tn.Tn = true;
                    request.Tn.T((Ty) null);
                }
                if (!request.Tr.equals("comm.log") && needLog) {
                    com.txznet.comm.Tr.Tr.Tn.Tn("[FROM=" + T.Tr().getApplicationInfo().packageName + ",TO=" + serverName + ",CMD=" + request.Tr + "] timeout=" + request.Tk);
                }
                requestQueue.remove(i);
                i--;
            }
            i++;
        }
    }

    public void T() {
        synchronized (this.Tk) {
            for (String str : this.Tk.keySet()) {
                C0014Tn record = this.Tk.get(str);
                if (!"com.txznet.txz".equals(record.Tr) && record.f379T != null) {
                    try {
                        record.T();
                        T.Tr().unbindService(record.f379T);
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    private Tn() {
        this.f367T.start();
        this.Tr = new T5(this.f367T.getLooper()) {
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PACKAGE_ADDED");
        filter.addDataScheme(SdkConstants.ATTR_PACKAGE);
        T.Tr().registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String packageName = intent.getDataString().substring(8);
                Log.d("logServiceManager", "package installed: " + packageName);
                Tn.this.Tr(packageName);
            }
        }, filter);
    }

    /* access modifiers changed from: private */
    public void Tr(String pkgName) {
        if (this.Tk != null) {
            synchronized (this.Tk) {
                C0014Tn record = this.Tk.get(pkgName);
                if (record != null) {
                    Log.d("logServiceManager", "reset ServiceRecord: " + pkgName);
                    record.Tn = true;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Object T(com.txznet.txz.Tr.T connectionInterface, String serviceName, String command, byte[] data, boolean async) {
        if (async) {
            try {
                Log.i("ServiceManager", "using oneway flag for command: " + command + ", target = " + serviceName);
                return T(connectionInterface, command, data);
            } catch (NullPointerException e) {
                Log.e("ServiceManager", "[FROM=" + T.Tr().getApplicationInfo().packageName + ",TO=" + serviceName + ",CMD=" + command + "] NullPointerException");
                return null;
            } catch (DeadObjectException e2) {
                Log.e("ServiceManager", "[FROM=" + T.Tr().getApplicationInfo().packageName + ",TO=" + serviceName + ",CMD=" + command + "] DeadObjectException");
                return null;
            } catch (Throwable e3) {
                Log.e("ServiceManager", "[FROM=" + T.Tr().getApplicationInfo().packageName + ",TO=" + serviceName + ",CMD=" + command + "] Throwable");
                e3.printStackTrace();
                return null;
            }
        } else {
            Log.i("ServiceManager", "using normal flag for command: " + command + ", target = " + serviceName);
            return connectionInterface.T(T.Tr().getApplicationInfo().packageName, command, data);
        }
    }

    private Object T(com.txznet.txz.Tr.T connectionInterface, String command, byte[] data) throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        try {
            _data.writeInterfaceToken("com.txznet.txz.service.IService");
            _data.writeString(T.Tr().getApplicationInfo().packageName);
            _data.writeString(command);
            _data.writeByteArray(data);
            connectionInterface.asBinder().transact(1, _data, _reply, 1);
            _reply.readException();
            return _reply.createByteArray();
        } finally {
            _reply.recycle();
            _data.recycle();
        }
    }

    public void T(final T listener) {
        T((Runnable) new Runnable() {
            public void run() {
                Tn.this.Th.add(listener);
            }
        }, 0);
    }

    /* compiled from: Proguard */
    class Tk implements ServiceConnection {

        /* renamed from: T  reason: collision with root package name */
        String f377T;

        public Tk(String serviceName) {
            this.f377T = serviceName;
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            com.txznet.comm.Tr.Tr.Tn.T("onServiceConnected ComponentName=" + name);
            Tn.this.Tr.T((Runnable) new com.txznet.txz.util.T.T<IBinder>(service) {
                public void run() {
                    C0014Tn rec;
                    for (T listener : Tn.this.Th) {
                        listener.Tr(Tk.this.f377T);
                    }
                    IBinder service = (IBinder) this.Ty;
                    synchronized (Tn.this.Tk) {
                        rec = Tn.this.Tk.get(Tk.this.f377T);
                    }
                    rec.Tk = Tn.TE;
                    Tn.Tr().T(rec.TZ);
                    rec.Ty = T.C0021T.T(service);
                    rec.Tr = Tk.this.f377T;
                    rec.Tr();
                }
            });
        }

        public void T() {
            C0014Tn rec = null;
            synchronized (Tn.this.Tk) {
                if (Tn.this.Tk.containsKey(this.f377T)) {
                    rec = Tn.this.Tk.get(this.f377T);
                }
            }
            if (rec != null) {
                rec.T();
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            com.txznet.comm.Tr.Tr.Tn.T("onServiceDisconnected ComponentName=" + name);
            T();
        }
    }

    public static Tn Tr() {
        return TZ;
    }

    public Ty T(String serviceName, String command, byte[] data) {
        com.txznet.txz.Tr.T service;
        if ((this.T9 && !command.startsWith("txz.camera.") && !command.startsWith("tool.camera.")) || (service = T(serviceName)) == null) {
            return null;
        }
        try {
            return new Ty(service.T(T.Tr().getApplicationInfo().packageName, command, data));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int T(String servicename, String command, byte[] data, Tr callback) {
        return T(servicename, command, data, callback, 30000);
    }

    public int T(final String serviceName, String command, byte[] data, Tr callback, long timeout) {
        if (this.T9 && !command.startsWith("txz.camera.")) {
            return -1;
        }
        T9 req = new T9();
        int i = this.T5;
        this.T5 = i + 1;
        req.f376T = i;
        req.Tr = command;
        req.Ty = data;
        req.Tn = callback;
        req.Tk = timeout;
        req.T9 = SystemClock.elapsedRealtime() + timeout;
        if (req.Tn != null) {
            req.Tn.Ty = req.f376T;
        }
        this.Tr.T((Runnable) new com.txznet.txz.util.T.Tr<String, T9>(req, serviceName) {
            public void run() {
                C0014Tn rec;
                synchronized (Tn.this.Tk) {
                    if (Tn.this.Tk.containsKey(this.Ty)) {
                        rec = Tn.this.Tk.get(this.Ty);
                    } else {
                        rec = new C0014Tn(serviceName);
                        Tn.this.Tk.put(this.Ty, rec);
                    }
                }
                rec.T9.add(this.Tn);
                rec.Tr();
                Tn.this.Tr.T(new com.txznet.txz.util.T.T<C0014Tn>(rec) {
                    public void run() {
                        ((C0014Tn) this.Ty).Tr();
                    }
                }, ((T9) this.Tn).Tk);
            }
        });
        return req.f376T;
    }

    public com.txznet.txz.Tr.T T(String serviceName) {
        synchronized (this.Tk) {
            for (String str : this.Tk.keySet()) {
                C0014Tn record = this.Tk.get(str);
                if (record.f379T != null && record.Tr != null && record.Tr.equals(serviceName)) {
                    com.txznet.txz.Tr.T t = record.Ty;
                    return t;
                }
            }
            return null;
        }
    }

    public byte[] T(String command, byte[] data) {
        Ty sendInvokeSync = T("com.txznet.txz", command, data);
        if (sendInvokeSync != null) {
            return sendInvokeSync.Tr();
        }
        com.txznet.comm.Tr.Tr.Tn.Tn("请先初始化语音声控引擎");
        return null;
    }

    public void T(final String remoteServiceName, final Runnable onConnected) {
        final Tr callback = new Tr() {
            public void T(Ty data) {
                if (data != null) {
                    Tn.this.T(onConnected);
                    Tn.this.T(onConnected, 200);
                }
            }
        };
        T((T) new T() {
            public void T(String serviceName) {
                if (serviceName.equals(remoteServiceName)) {
                    Tn.Tr().T(remoteServiceName, TXZResourceManager.STYLE_DEFAULT, (byte[]) null, callback);
                }
            }

            public void Tr(String serviceName) {
                if (remoteServiceName.equals(serviceName)) {
                    Tn.this.T(onConnected);
                    Tn.this.T(onConnected, 200);
                }
            }
        });
        T.Tr().registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                Tn.Tr().T(remoteServiceName, TXZResourceManager.STYLE_DEFAULT, (byte[]) null, callback);
            }
        }, new IntentFilter(remoteServiceName + ".onCreateApp"));
        Tr().T(remoteServiceName, TXZResourceManager.STYLE_DEFAULT, (byte[]) null, callback);
    }
}
