package com.txznet.comm.Tr.Tr;

import android.os.Environment;
import com.Tn.Tr.Tn.T;
import com.android.SdkConstants;
import com.txznet.comm.Tr.Tn;
import com.txznet.txz.T.Ty;
import java.util.HashSet;
import java.util.Set;

/* compiled from: Proguard */
public class Tk {

    /* renamed from: T  reason: collision with root package name */
    private static final String f405T = (Environment.getExternalStorageDirectory().getPath() + "/txz/plugin/");
    private static boolean Tr = false;

    public static void T(byte[] data) {
        try {
            T.C0006T pluginFile = T.C0006T.T(data);
            T(pluginFile);
            if (pluginFile.T5.intValue() != 2) {
                HashSet<String> files = new HashSet<>();
                files.add(T(pluginFile, SdkConstants.DOT_JAR));
                com.txznet.T.T.T(0, (Set<String>) files);
            }
            switch (pluginFile.T5.intValue()) {
                case 1:
                    if (pluginFile.TE.booleanValue()) {
                        Tn.Tr().T(pluginFile.Tr, "comm.update.restart", (byte[]) null, (Tn.Tr) null);
                        return;
                    }
                    return;
                case 2:
                case 3:
                    Ty.T(T(pluginFile, SdkConstants.DOT_JAR), pluginFile.Ty, pluginFile.TZ);
                    return;
                default:
                    return;
            }
        } catch (com.Tr.T.T.Tn e) {
            e.printStackTrace();
            Tn.T("Plugin:download plugin error", (Throwable) e);
        }
        e.printStackTrace();
        Tn.T("Plugin:download plugin error", (Throwable) e);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0060 A[SYNTHETIC, Splitter:B:15:0x0060] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0069 A[SYNTHETIC, Splitter:B:20:0x0069] */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void T(com.Tn.Tr.Tn.T.C0006T r9) {
        /*
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "string"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r7 = new java.lang.String
            byte[] r8 = r9.Tn
            r7.<init>(r8)
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r7 = r9.Tr
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.Integer r7 = r9.Tk
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r7 = com.txznet.comm.Tn.T.Tr
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r2 = r6.toString()
            java.lang.String r0 = com.txznet.txz.util.Tn.T((java.lang.String) r2)
            java.lang.String r6 = ".chk"
            java.lang.String r1 = T(r9, r6)
            r4 = 0
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0054 }
            java.io.File r6 = new java.io.File     // Catch:{ IOException -> 0x0054 }
            r6.<init>(r1)     // Catch:{ IOException -> 0x0054 }
            r5.<init>(r6)     // Catch:{ IOException -> 0x0054 }
            byte[] r6 = r0.getBytes()     // Catch:{ IOException -> 0x0072, all -> 0x006f }
            r5.write(r6)     // Catch:{ IOException -> 0x0072, all -> 0x006f }
            if (r5 == 0) goto L_0x004f
            r5.close()     // Catch:{ IOException -> 0x0051 }
        L_0x004f:
            r4 = r5
        L_0x0050:
            return
        L_0x0051:
            r6 = move-exception
            r4 = r5
            goto L_0x0050
        L_0x0054:
            r3 = move-exception
        L_0x0055:
            r3.printStackTrace()     // Catch:{ all -> 0x0066 }
            java.lang.String r6 = "Plugin:create check file failed"
            com.txznet.comm.Tr.Tr.Tn.T((java.lang.String) r6, (java.lang.Throwable) r3)     // Catch:{ all -> 0x0066 }
            if (r4 == 0) goto L_0x0050
            r4.close()     // Catch:{ IOException -> 0x0064 }
            goto L_0x0050
        L_0x0064:
            r6 = move-exception
            goto L_0x0050
        L_0x0066:
            r6 = move-exception
        L_0x0067:
            if (r4 == 0) goto L_0x006c
            r4.close()     // Catch:{ IOException -> 0x006d }
        L_0x006c:
            throw r6
        L_0x006d:
            r7 = move-exception
            goto L_0x006c
        L_0x006f:
            r6 = move-exception
            r4 = r5
            goto L_0x0067
        L_0x0072:
            r3 = move-exception
            r4 = r5
            goto L_0x0055
        */
        throw new UnsupportedOperationException("Method not decompiled: com.txznet.comm.Tr.Tr.Tk.T(com.Tn.Tr.Tn.T$T):void");
    }

    private static String T(T.C0006T pluginFile, String suffix) {
        if (pluginFile.T5.intValue() == 2) {
            return f405T + pluginFile.Tr + "/current/" + pluginFile.Ty + suffix;
        }
        return f405T + pluginFile.Tr + "/" + pluginFile.Ty + suffix;
    }
}
