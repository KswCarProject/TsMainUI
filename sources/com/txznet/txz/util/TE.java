package com.txznet.txz.util;

import android.text.TextUtils;
import com.txznet.comm.Ty.T;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* compiled from: Proguard */
public class TE {

    /* renamed from: T  reason: collision with root package name */
    private static final String f901T = TE.class.getSimpleName();
    private static boolean Tr = false;
    private static HashMap<String, String> Ty;

    private TE() {
    }

    public static HashMap<String, String> T(String... configKeys) {
        return T(Arrays.asList(configKeys));
    }

    private static void T() {
        if (!Tr) {
            if (T.T5 == null || T.TE == null) {
                T.T5 = com.txznet.comm.Tr.T.Tr().getPackageName() + ".cfg";
                T.TE = com.txznet.comm.Tr.T.Tr().getApplicationInfo().dataDir + "/cfg/";
            }
            synchronized (TE.class) {
                if (!Tr) {
                    List<String> filePathList = new ArrayList<>();
                    filePathList.add(T.TE + T.T5);
                    filePathList.add(T.TE + "comm.txz.cfg");
                    filePathList.add("/system/txz/" + T.T5);
                    filePathList.add("/system/txz/comm.txz.cfg");
                    filePathList.add("/system/app/" + T.T5);
                    filePathList.add("/system/app/comm.txz.cfg");
                    filePathList.add("/etc/txz/" + T.T5);
                    filePathList.add("/etc/txz/comm.txz.cfg");
                    filePathList.add("/custom/etc/" + T.T5);
                    filePathList.add("/custom/etc/comm.txz.cfg");
                    filePathList.add("/vendor/txz/" + T.T5);
                    filePathList.add("/vendor/txz/comm.txz.cfg");
                    filePathList.add(T.Tk + T.T5);
                    filePathList.add(T.Tk + "comm.txz.cfg");
                    for (String filePath : filePathList) {
                        File file = new File(filePath);
                        if (file.exists()) {
                            T(file);
                        }
                    }
                    Tr = true;
                }
            }
        }
    }

    public static String T(String config) {
        T();
        if (Ty == null || Ty.size() == 0) {
            return null;
        }
        return Ty.get(config);
    }

    public static boolean T(String config, boolean defaultValue) {
        String configValue = T(config);
        if (TextUtils.isEmpty(configValue)) {
            return defaultValue;
        }
        try {
            return Boolean.parseBoolean(configValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static HashMap<String, String> T(List<String> configKeys) {
        if (configKeys == null || configKeys.size() == 0) {
            return null;
        }
        HashMap<String, String> cfgs = new HashMap<>();
        T();
        if (Ty == null || Ty.size() == 0) {
            return cfgs;
        }
        for (String key : configKeys) {
            if (!TextUtils.isEmpty(key)) {
                String value = Ty.get(key);
                if (!TextUtils.isEmpty(value)) {
                    cfgs.put(key, value);
                }
            }
        }
        return cfgs;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x008f A[SYNTHETIC, Splitter:B:25:0x008f] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0094 A[Catch:{ IOException -> 0x00fa }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0099 A[Catch:{ IOException -> 0x00fa }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c8 A[SYNTHETIC, Splitter:B:41:0x00c8] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00cd A[Catch:{ IOException -> 0x00d6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00d2 A[Catch:{ IOException -> 0x00d6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0104 A[SYNTHETIC, Splitter:B:63:0x0104] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0109 A[Catch:{ IOException -> 0x0112 }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x010e A[Catch:{ IOException -> 0x0112 }] */
    /* JADX WARNING: Removed duplicated region for block: B:94:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:95:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:38:0x00c1=Splitter:B:38:0x00c1, B:22:0x0088=Splitter:B:22:0x0088} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void T(java.io.File r12) {
        /*
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "loadConfigFromFile:"
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.StringBuilder r9 = r9.append(r12)
            java.lang.String r9 = r9.toString()
            com.txznet.comm.Tr.Tr.Tn.T((java.lang.String) r9)
            r3 = 0
            r6 = 0
            r0 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x012e, IOException -> 0x0125 }
            r4.<init>(r12)     // Catch:{ FileNotFoundException -> 0x012e, IOException -> 0x0125 }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ FileNotFoundException -> 0x0131, IOException -> 0x0127, all -> 0x0119 }
            r7.<init>(r4)     // Catch:{ FileNotFoundException -> 0x0131, IOException -> 0x0127, all -> 0x0119 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x0135, IOException -> 0x012a, all -> 0x011c }
            r1.<init>(r7)     // Catch:{ FileNotFoundException -> 0x0135, IOException -> 0x012a, all -> 0x011c }
            r5 = 0
        L_0x0029:
            java.lang.String r5 = r1.readLine()     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            if (r5 == 0) goto L_0x00dd
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            r9.<init>()     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            java.lang.String r10 = "readLine:"
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            java.lang.StringBuilder r9 = r9.append(r5)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            java.lang.String r9 = r9.toString()     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            com.txznet.comm.Tr.Tr.Tn.T((java.lang.String) r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            boolean r9 = android.text.TextUtils.isEmpty(r5)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            if (r9 != 0) goto L_0x0029
            java.lang.String r9 = "="
            java.lang.String[] r8 = r5.split(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            int r9 = r8.length     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            r10 = 2
            if (r9 != r10) goto L_0x0067
            r9 = 0
            r9 = r8[r9]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            if (r9 != 0) goto L_0x0067
            r9 = 1
            r9 = r8[r9]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            if (r9 == 0) goto L_0x009d
        L_0x0067:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            r9.<init>()     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            java.lang.String r10 = f901T     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            java.lang.String r10 = "config format error:"
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            java.lang.StringBuilder r9 = r9.append(r5)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            java.lang.String r9 = r9.toString()     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            com.txznet.comm.Tr.Tr.Tn.Tn(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            goto L_0x0029
        L_0x0084:
            r2 = move-exception
            r0 = r1
            r6 = r7
            r3 = r4
        L_0x0088:
            java.lang.String r9 = f901T     // Catch:{ all -> 0x0101 }
            com.txznet.comm.Tr.Tr.Tn.T((java.lang.String) r9, (java.lang.Throwable) r2)     // Catch:{ all -> 0x0101 }
            if (r3 == 0) goto L_0x0092
            r3.close()     // Catch:{ IOException -> 0x00fa }
        L_0x0092:
            if (r6 == 0) goto L_0x0097
            r6.close()     // Catch:{ IOException -> 0x00fa }
        L_0x0097:
            if (r0 == 0) goto L_0x009c
            r0.close()     // Catch:{ IOException -> 0x00fa }
        L_0x009c:
            return
        L_0x009d:
            java.util.HashMap<java.lang.String, java.lang.String> r9 = Ty     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            if (r9 != 0) goto L_0x00a8
            java.util.HashMap r9 = new java.util.HashMap     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            r9.<init>()     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            Ty = r9     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
        L_0x00a8:
            java.util.HashMap<java.lang.String, java.lang.String> r9 = Ty     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            r10 = 0
            r10 = r8[r10]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            java.lang.String r10 = r10.trim()     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            r11 = 1
            r11 = r8[r11]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            java.lang.String r11 = r11.trim()     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            r9.put(r10, r11)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00bd, all -> 0x0120 }
            goto L_0x0029
        L_0x00bd:
            r2 = move-exception
            r0 = r1
            r6 = r7
            r3 = r4
        L_0x00c1:
            java.lang.String r9 = f901T     // Catch:{ all -> 0x0101 }
            com.txznet.comm.Tr.Tr.Tn.T((java.lang.String) r9, (java.lang.Throwable) r2)     // Catch:{ all -> 0x0101 }
            if (r3 == 0) goto L_0x00cb
            r3.close()     // Catch:{ IOException -> 0x00d6 }
        L_0x00cb:
            if (r6 == 0) goto L_0x00d0
            r6.close()     // Catch:{ IOException -> 0x00d6 }
        L_0x00d0:
            if (r0 == 0) goto L_0x009c
            r0.close()     // Catch:{ IOException -> 0x00d6 }
            goto L_0x009c
        L_0x00d6:
            r2 = move-exception
            java.lang.String r9 = f901T
            com.txznet.comm.Tr.Tr.Tn.T((java.lang.String) r9, (java.lang.Throwable) r2)
            goto L_0x009c
        L_0x00dd:
            if (r4 == 0) goto L_0x00e2
            r4.close()     // Catch:{ IOException -> 0x00f0 }
        L_0x00e2:
            if (r7 == 0) goto L_0x00e7
            r7.close()     // Catch:{ IOException -> 0x00f0 }
        L_0x00e7:
            if (r1 == 0) goto L_0x00ec
            r1.close()     // Catch:{ IOException -> 0x00f0 }
        L_0x00ec:
            r0 = r1
            r6 = r7
            r3 = r4
            goto L_0x009c
        L_0x00f0:
            r2 = move-exception
            java.lang.String r9 = f901T
            com.txznet.comm.Tr.Tr.Tn.T((java.lang.String) r9, (java.lang.Throwable) r2)
            r0 = r1
            r6 = r7
            r3 = r4
            goto L_0x009c
        L_0x00fa:
            r2 = move-exception
            java.lang.String r9 = f901T
            com.txznet.comm.Tr.Tr.Tn.T((java.lang.String) r9, (java.lang.Throwable) r2)
            goto L_0x009c
        L_0x0101:
            r9 = move-exception
        L_0x0102:
            if (r3 == 0) goto L_0x0107
            r3.close()     // Catch:{ IOException -> 0x0112 }
        L_0x0107:
            if (r6 == 0) goto L_0x010c
            r6.close()     // Catch:{ IOException -> 0x0112 }
        L_0x010c:
            if (r0 == 0) goto L_0x0111
            r0.close()     // Catch:{ IOException -> 0x0112 }
        L_0x0111:
            throw r9
        L_0x0112:
            r2 = move-exception
            java.lang.String r10 = f901T
            com.txznet.comm.Tr.Tr.Tn.T((java.lang.String) r10, (java.lang.Throwable) r2)
            goto L_0x0111
        L_0x0119:
            r9 = move-exception
            r3 = r4
            goto L_0x0102
        L_0x011c:
            r9 = move-exception
            r6 = r7
            r3 = r4
            goto L_0x0102
        L_0x0120:
            r9 = move-exception
            r0 = r1
            r6 = r7
            r3 = r4
            goto L_0x0102
        L_0x0125:
            r2 = move-exception
            goto L_0x00c1
        L_0x0127:
            r2 = move-exception
            r3 = r4
            goto L_0x00c1
        L_0x012a:
            r2 = move-exception
            r6 = r7
            r3 = r4
            goto L_0x00c1
        L_0x012e:
            r2 = move-exception
            goto L_0x0088
        L_0x0131:
            r2 = move-exception
            r3 = r4
            goto L_0x0088
        L_0x0135:
            r2 = move-exception
            r6 = r7
            r3 = r4
            goto L_0x0088
        */
        throw new UnsupportedOperationException("Method not decompiled: com.txznet.txz.util.TE.T(java.io.File):void");
    }
}
