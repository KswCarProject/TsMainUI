package com.txznet.comm.Tr.T;

import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Log;
import com.txznet.comm.Tr.T.Tn;
import com.txznet.comm.Tr.T.Ty;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.T9;
import com.txznet.comm.Ty.Tr;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/* compiled from: Proguard */
public class T {

    /* renamed from: T  reason: collision with root package name */
    private static T f355T = new T();
    private static final String TE = (Environment.getExternalStorageDirectory() + "/txz/udp_port.txz");
    private Ty.T T5;
    /* access modifiers changed from: private */
    public List<Tn.T> T6 = new ArrayList();
    /* access modifiers changed from: private */
    public Handler T9;
    private Handler TZ;
    /* access modifiers changed from: private */
    public Runnable Th = new Runnable() {
        public void run() {
            T.this.T9.removeCallbacks(T.this.Th);
            boolean unused = T.this.Tk();
            boolean unused2 = T.this.TZ();
            T.this.T9.postDelayed(T.this.Th, 5000);
        }
    };
    private HandlerThread Tk;
    private HandlerThread Tn;
    /* access modifiers changed from: private */
    public boolean Tr = false;
    private int Tv = 0;
    private Tr Ty;

    private T() {
    }

    public static T T() {
        return f355T;
    }

    public void Tr() {
        Tn();
    }

    private void Tn() {
        this.Tn = new HandlerThread("udpCheckConnection");
        this.Tn.start();
        this.T9 = new Handler(this.Tn.getLooper());
        this.T9.post(this.Th);
        this.Tk = new HandlerThread("udpProcess");
        this.Tk.start();
        this.TZ = new Handler(this.Tk.getLooper());
        com.txznet.comm.Tr.Tn.Tr().T((Tn.T) new Tn.T() {
            public void Tr(String serviceName) {
                com.txznet.comm.Tr.Tr.Tn.T("TXZUdpClient onConnected :" + serviceName);
                if ("com.txznet.txz".equals(serviceName)) {
                    T.this.T9.post(T.this.Th);
                    Tr jsonBuilder = new Tr();
                    jsonBuilder.T("processName", (Object) com.txznet.T.T.Tk());
                    com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.udp.init", jsonBuilder.Ty(), (Tn.Tr) null);
                }
            }

            public void T(String serviceName) {
                com.txznet.comm.Tr.Tr.Tn.T("TXZUdpClient onDisconnected :" + serviceName);
                if ("com.txznet.txz".equals(serviceName)) {
                    boolean unused = T.this.Tr = false;
                }
            }
        });
    }

    public boolean Ty() {
        return this.Tr;
    }

    /* access modifiers changed from: private */
    public void T9() {
        if (!this.Tr) {
            this.T9.post(this.Th);
            return;
        }
        while (0 < this.T6.size()) {
            if (this.T6.get(0) == null) {
                this.T6.remove(0);
            } else {
                this.Ty.T(this.T6.get(0), this.T5);
                this.T6.remove(0);
            }
        }
    }

    public int T(int invokeMethod, byte[] data) {
        return T("com.txznet.txz", invokeMethod, data);
    }

    public int T(String packageName, int invokeMethod, byte[] data) {
        this.TZ.post(new com.txznet.txz.util.T.T<Tn.T>(new Tn.T(this.Tv, 2, invokeMethod, data)) {
            public void run() {
                T.this.T6.add(this.Ty);
                T.this.T9();
            }
        });
        return 0;
    }

    /* access modifiers changed from: private */
    public boolean Tk() {
        int r;
        File file = new File(TE);
        if (file.exists()) {
            try {
                FileInputStream in = new FileInputStream(file);
                byte[] bs = new byte[((int) file.length())];
                int t = 0;
                while (t < bs.length && (r = in.read(bs, t, bs.length - t)) >= 0) {
                    t += r;
                }
                in.close();
                String info = new String(bs);
                if (TextUtils.isEmpty(info)) {
                    return false;
                }
                String[] tmp1 = info.split("=");
                if (tmp1.length != 2) {
                    return false;
                }
                String[] tmp2 = tmp1[1].split(":");
                this.T5 = new Ty.T(T9.Tr(tmp2[0]), Integer.parseInt(T9.Tr(tmp2[1])));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.Tr = false;
        return false;
    }

    /* access modifiers changed from: private */
    public boolean TZ() {
        if (this.Ty == null) {
            this.Ty = new Tr();
            this.Ty.T();
        }
        if (this.T5 == null) {
            return false;
        }
        try {
            Tn.T resp = this.Ty.T(new Tn.T(this.Tv, 1, 1, com.txznet.T.T.Tk().getBytes()), this.T5);
            if (resp != null) {
                try {
                    int udpId = ((Integer) new Tr(resp.T9).T("udpId", Integer.class)).intValue();
                    if (!this.Tr || this.Tv == 0 || udpId != this.Tv) {
                        this.Tv = udpId;
                        Log.i("yangtong", "udp in connection udpId:" + this.Tv);
                        this.Tr = true;
                    }
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.Tr = false;
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            this.Tr = false;
            return false;
        }
    }
}
