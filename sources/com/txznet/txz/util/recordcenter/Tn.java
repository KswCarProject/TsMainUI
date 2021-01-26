package com.txznet.txz.util.recordcenter;

import com.ts.can.CanCameraUI;
import com.txznet.txz.util.T5;
import com.txznet.txz.util.recordcenter.T.Tr;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/* compiled from: Proguard */
public class Tn {

    /* renamed from: T  reason: collision with root package name */
    public static final File f913T = new File(com.txznet.comm.Tr.T.Tr().getApplicationInfo().dataDir, "../com.txznet.txz/RecorderCenter.port");
    private static byte[] T5 = null;
    private static int T6 = 0;
    static Selector T9 = null;
    private static Thread TB = null;
    private static byte[] TE = null;
    private static int TF = 0;
    private static com.txznet.txz.util.recordcenter.T.T TG = new com.txznet.txz.util.recordcenter.T.T() {
        public int T(byte[] data, int offset, int len) {
            Tn.Tr(5, data, offset, len);
            return len;
        }
    };
    private static Thread TK = null;
    private static com.txznet.txz.util.recordcenter.T.T TN = new com.txznet.txz.util.recordcenter.T.T() {
        public int T(byte[] data, int offset, int len) {
            if (!Tn.Ty(len)) {
                Tn.Tr(2, data, offset, len);
            }
            return len;
        }
    };
    private static Tr TO = null;
    /* access modifiers changed from: private */
    public static boolean TZ = false;
    private static T[] Te = new T[20];
    private static FileInputStream Th = null;
    private static int Tj = 0;
    /* access modifiers changed from: private */
    public static boolean Tk = false;
    static ServerSocketChannel Tn = null;
    private static int Tq = 0;
    public static int Tr = 0;
    private static com.txznet.txz.util.recordcenter.T.T Ts = new com.txznet.txz.util.recordcenter.T.T() {
        public int T(byte[] data, int offset, int len) {
            if (!Tn.Ty(len)) {
                if (Tn.Tk || Tn.TZ) {
                    Tn.Ty.T(data, offset, len);
                }
                Tn.Tr(3, data, offset, len);
            }
            return len;
        }
    };
    private static T Tu = null;
    private static File Tv = null;
    public static com.txznet.txz.util.recordcenter.T.Tn Ty = new com.txznet.txz.util.recordcenter.T.Tn(160000);

    static {
        Ty();
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x009e A[SYNTHETIC, Splitter:B:23:0x009e] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00aa A[SYNTHETIC, Splitter:B:29:0x00aa] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void Ty() {
        /*
            r8 = 2
            android.content.IntentFilter r2 = new android.content.IntentFilter
            java.lang.String r6 = "com.txznet.txz.RecorderCenter.UpdatePort"
            r2.<init>(r6)
            android.content.Context r6 = com.txznet.comm.Tr.T.Tr()
            com.txznet.txz.util.recordcenter.Tn$1 r7 = new com.txznet.txz.util.recordcenter.Tn$1
            r7.<init>()
            r6.registerReceiver(r7, r2)
            r6 = 22342(0x5746, float:3.1308E-41)
            Tr = r6
            r3 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0080 }
            java.io.File r6 = f913T     // Catch:{ Exception -> 0x0080 }
            r4.<init>(r6)     // Catch:{ Exception -> 0x0080 }
            r6 = 2
            byte[] r0 = new byte[r6]     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            r6 = 0
            r7 = 2
            int r6 = r4.read(r0, r6, r7)     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            if (r8 != r6) goto L_0x005a
            r6 = 1
            byte r6 = r0[r6]     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            int r6 = r6 << 8
            r7 = 0
            byte r7 = r0[r7]     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            r6 = r6 | r7
            r7 = 65535(0xffff, float:9.1834E-41)
            r5 = r6 & r7
            if (r5 >= 0) goto L_0x003f
            r6 = 65536(0x10000, float:9.18355E-41)
            int r5 = r5 + r6
        L_0x003f:
            Tr = r5     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            r6.<init>()     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            java.lang.String r7 = "load record port: "
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            int r7 = Tr     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
            com.txznet.comm.Tr.Tr.Tn.T((java.lang.String) r6)     // Catch:{ Exception -> 0x00b6, all -> 0x00b3 }
        L_0x005a:
            if (r4 == 0) goto L_0x00b9
            r4.close()     // Catch:{ IOException -> 0x007a }
            r3 = r4
        L_0x0060:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "final record port: "
            java.lang.StringBuilder r6 = r6.append(r7)
            int r7 = Tr
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            com.txznet.comm.Tr.Tr.Tn.T((java.lang.String) r6)
            return
        L_0x007a:
            r1 = move-exception
            r1.printStackTrace()
            r3 = r4
            goto L_0x0060
        L_0x0080:
            r1 = move-exception
        L_0x0081:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a7 }
            r6.<init>()     // Catch:{ all -> 0x00a7 }
            java.lang.String r7 = "load record port exception: "
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x00a7 }
            java.lang.String r7 = r1.getMessage()     // Catch:{ all -> 0x00a7 }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x00a7 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00a7 }
            com.txznet.comm.Tr.Tr.Tn.T((java.lang.String) r6)     // Catch:{ all -> 0x00a7 }
            if (r3 == 0) goto L_0x0060
            r3.close()     // Catch:{ IOException -> 0x00a2 }
            goto L_0x0060
        L_0x00a2:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0060
        L_0x00a7:
            r6 = move-exception
        L_0x00a8:
            if (r3 == 0) goto L_0x00ad
            r3.close()     // Catch:{ IOException -> 0x00ae }
        L_0x00ad:
            throw r6
        L_0x00ae:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00ad
        L_0x00b3:
            r6 = move-exception
            r3 = r4
            goto L_0x00a8
        L_0x00b6:
            r1 = move-exception
            r3 = r4
            goto L_0x0081
        L_0x00b9:
            r3 = r4
            goto L_0x0060
        */
        throw new UnsupportedOperationException("Method not decompiled: com.txznet.txz.util.recordcenter.Tn.Ty():void");
    }

    public static byte[] T(int len) {
        if (TE == null || TE.length < len) {
            TE = new byte[((((len + CanCameraUI.BTN_CHANA_WC_MODE) - 1) / CanCameraUI.BTN_CHANA_WC_MODE) * CanCameraUI.BTN_CHANA_WC_MODE)];
            InputStream in = null;
            try {
                in = com.txznet.comm.Tr.T.Tr().getAssets().open("quite.pcm");
                in.read(TE);
                if (in != null) {
                    try {
                        in.close();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                if (in != null) {
                    try {
                        in.close();
                    } catch (Exception e3) {
                    }
                }
            } catch (Throwable th) {
                if (in != null) {
                    try {
                        in.close();
                    } catch (Exception e4) {
                    }
                }
                throw th;
            }
        }
        return TE;
    }

    /* access modifiers changed from: private */
    public static boolean Ty(int len) {
        int r;
        if (Th == null) {
            return false;
        }
        if (T5 == null || T5.length < len) {
            T5 = new byte[((((len + CanCameraUI.BTN_CHANA_WC_MODE) - 1) / CanCameraUI.BTN_CHANA_WC_MODE) * CanCameraUI.BTN_CHANA_WC_MODE)];
        }
        byte[] data = T(len);
        int i = len;
        try {
            r = Th.read(T5, 0, len);
            if (r < 0) {
                r = len;
            } else {
                data = T5;
            }
        } catch (Exception e) {
            r = len;
        }
        for (T rec : Te) {
            if (!(rec == null || rec.f914T == 1)) {
                rec.T(data, 0, r);
            }
        }
        return true;
    }

    /* compiled from: Proguard */
    private static class T {

        /* renamed from: T  reason: collision with root package name */
        int f914T;
        com.txznet.txz.util.recordcenter.T.T T9;
        private Runnable TZ;
        private Runnable Tk;
        Tr Tn;
        T5 Tr;
        long Ty;

        public void T(long startTime) {
            this.Ty = startTime;
        }

        public int T(byte[] data, int offset, int len) {
            int ret = this.Tn.T(data, offset, len);
            this.Tr.Tr(this.Tk);
            this.Tr.Tr(this.TZ);
            this.Tr.T(this.Tk);
            this.Tr.T(this.TZ);
            return ret;
        }
    }

    /* access modifiers changed from: private */
    public static void Tr(int state, byte[] data, int offset, int len) {
        for (T rec : Te) {
            if (!(rec == null || 1 == rec.f914T)) {
                if (4 == rec.f914T && 3 == state) {
                    if (Tk || TZ) {
                        if (rec.Ty > 0) {
                            try {
                                Ty.T(rec.T9, rec.Ty);
                            } catch (Exception e) {
                            }
                        }
                        rec.T(0);
                    }
                    rec.T(data, offset, len);
                } else if (rec.f914T == state) {
                    rec.T(data, offset, len);
                }
            }
        }
    }
}
