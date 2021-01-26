package com.ts.MainUI;

public class ThermalInfoUtil {
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0025 A[SYNTHETIC, Splitter:B:14:0x0025] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0034 A[SYNTHETIC, Splitter:B:22:0x0034] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0040 A[SYNTHETIC, Splitter:B:28:0x0040] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long GetCputemp() {
        /*
            r0 = 0
            r3 = 0
            r4 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x0022, Exception -> 0x002e }
            java.io.FileReader r6 = new java.io.FileReader     // Catch:{ FileNotFoundException -> 0x0022, Exception -> 0x002e }
            java.lang.String r7 = "/sys/class/thermal/thermal_zone1/temp"
            r6.<init>(r7)     // Catch:{ FileNotFoundException -> 0x0022, Exception -> 0x002e }
            r1.<init>(r6)     // Catch:{ FileNotFoundException -> 0x0022, Exception -> 0x002e }
            java.lang.String r3 = r1.readLine()     // Catch:{ FileNotFoundException -> 0x0055, Exception -> 0x0052, all -> 0x004f }
            if (r3 == 0) goto L_0x001b
            long r4 = java.lang.Long.parseLong(r3)     // Catch:{ FileNotFoundException -> 0x0055, Exception -> 0x0052, all -> 0x004f }
        L_0x001b:
            if (r1 == 0) goto L_0x004d
            r1.close()     // Catch:{ IOException -> 0x0049 }
            r0 = r1
        L_0x0021:
            return r4
        L_0x0022:
            r6 = move-exception
        L_0x0023:
            if (r0 == 0) goto L_0x0021
            r0.close()     // Catch:{ IOException -> 0x0029 }
            goto L_0x0021
        L_0x0029:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0021
        L_0x002e:
            r2 = move-exception
        L_0x002f:
            r2.printStackTrace()     // Catch:{ all -> 0x003d }
            if (r0 == 0) goto L_0x0021
            r0.close()     // Catch:{ IOException -> 0x0038 }
            goto L_0x0021
        L_0x0038:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0021
        L_0x003d:
            r6 = move-exception
        L_0x003e:
            if (r0 == 0) goto L_0x0043
            r0.close()     // Catch:{ IOException -> 0x0044 }
        L_0x0043:
            throw r6
        L_0x0044:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0043
        L_0x0049:
            r2 = move-exception
            r2.printStackTrace()
        L_0x004d:
            r0 = r1
            goto L_0x0021
        L_0x004f:
            r6 = move-exception
            r0 = r1
            goto L_0x003e
        L_0x0052:
            r2 = move-exception
            r0 = r1
            goto L_0x002f
        L_0x0055:
            r6 = move-exception
            r0 = r1
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.MainUI.ThermalInfoUtil.GetCputemp():long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00cc A[SYNTHETIC, Splitter:B:31:0x00cc] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00dd A[SYNTHETIC, Splitter:B:39:0x00dd] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00eb A[SYNTHETIC, Splitter:B:45:0x00eb] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:28:0x00c3=Splitter:B:28:0x00c3, B:36:0x00d8=Splitter:B:36:0x00d8} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<java.lang.String> getThermalInfo() {
        /*
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r1 = 0
            java.io.File r3 = new java.io.File     // Catch:{ FileNotFoundException -> 0x00c2, Exception -> 0x00d7 }
            java.lang.String r13 = "/sys/class/thermal/"
            r3.<init>(r13)     // Catch:{ FileNotFoundException -> 0x00c2, Exception -> 0x00d7 }
            com.ts.MainUI.ThermalInfoUtil$1 r13 = new com.ts.MainUI.ThermalInfoUtil$1     // Catch:{ FileNotFoundException -> 0x00c2, Exception -> 0x00d7 }
            r13.<init>()     // Catch:{ FileNotFoundException -> 0x00c2, Exception -> 0x00d7 }
            java.io.File[] r5 = r3.listFiles(r13)     // Catch:{ FileNotFoundException -> 0x00c2, Exception -> 0x00d7 }
            int r0 = r5.length     // Catch:{ FileNotFoundException -> 0x00c2, Exception -> 0x00d7 }
            r7 = 0
            r12 = 0
            r9 = 0
            r6 = 0
            r2 = r1
        L_0x001d:
            if (r6 < r0) goto L_0x0029
            r2.close()     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            if (r2 == 0) goto L_0x00f8
            r2.close()     // Catch:{ IOException -> 0x00f4 }
            r1 = r2
        L_0x0028:
            return r8
        L_0x0029:
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            java.io.FileReader r13 = new java.io.FileReader     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            java.lang.String r15 = "/sys/class/thermal/thermal_zone"
            r14.<init>(r15)     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            java.lang.StringBuilder r14 = r14.append(r6)     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            java.lang.String r15 = "/type"
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            java.lang.String r14 = r14.toString()     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            r13.<init>(r14)     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            r1.<init>(r13)     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            java.lang.String r7 = r1.readLine()     // Catch:{ FileNotFoundException -> 0x00c2, Exception -> 0x00d7 }
            if (r7 == 0) goto L_0x0051
            r12 = r7
        L_0x0051:
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x00c2, Exception -> 0x00d7 }
            java.io.FileReader r13 = new java.io.FileReader     // Catch:{ FileNotFoundException -> 0x00c2, Exception -> 0x00d7 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x00c2, Exception -> 0x00d7 }
            java.lang.String r15 = "/sys/class/thermal/thermal_zone"
            r14.<init>(r15)     // Catch:{ FileNotFoundException -> 0x00c2, Exception -> 0x00d7 }
            java.lang.StringBuilder r14 = r14.append(r6)     // Catch:{ FileNotFoundException -> 0x00c2, Exception -> 0x00d7 }
            java.lang.String r15 = "/temp"
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ FileNotFoundException -> 0x00c2, Exception -> 0x00d7 }
            java.lang.String r14 = r14.toString()     // Catch:{ FileNotFoundException -> 0x00c2, Exception -> 0x00d7 }
            r13.<init>(r14)     // Catch:{ FileNotFoundException -> 0x00c2, Exception -> 0x00d7 }
            r2.<init>(r13)     // Catch:{ FileNotFoundException -> 0x00c2, Exception -> 0x00d7 }
            java.lang.String r7 = r2.readLine()     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            if (r7 == 0) goto L_0x0085
            long r10 = java.lang.Long.parseLong(r7)     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            r13 = 0
            int r13 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r13 >= 0) goto L_0x00a4
            java.lang.String r9 = "Unknow"
        L_0x0085:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            java.lang.String r14 = java.lang.String.valueOf(r12)     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            r13.<init>(r14)     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            java.lang.String r14 = " : "
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            java.lang.StringBuilder r13 = r13.append(r9)     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            java.lang.String r13 = r13.toString()     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            r8.add(r13)     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            int r6 = r6 + 1
            goto L_0x001d
        L_0x00a4:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            double r14 = (double) r10     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            r16 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r14 = r14 / r16
            float r14 = (float) r14     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            java.lang.String r14 = java.lang.String.valueOf(r14)     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            r13.<init>(r14)     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            java.lang.String r14 = "°C"
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            java.lang.String r9 = r13.toString()     // Catch:{ FileNotFoundException -> 0x0101, Exception -> 0x00fe, all -> 0x00fb }
            goto L_0x0085
        L_0x00c2:
            r4 = move-exception
        L_0x00c3:
            java.lang.String r13 = r4.toString()     // Catch:{ all -> 0x00e8 }
            r8.add(r13)     // Catch:{ all -> 0x00e8 }
            if (r1 == 0) goto L_0x0028
            r1.close()     // Catch:{ IOException -> 0x00d1 }
            goto L_0x0028
        L_0x00d1:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x0028
        L_0x00d7:
            r4 = move-exception
        L_0x00d8:
            r4.printStackTrace()     // Catch:{ all -> 0x00e8 }
            if (r1 == 0) goto L_0x0028
            r1.close()     // Catch:{ IOException -> 0x00e2 }
            goto L_0x0028
        L_0x00e2:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x0028
        L_0x00e8:
            r13 = move-exception
        L_0x00e9:
            if (r1 == 0) goto L_0x00ee
            r1.close()     // Catch:{ IOException -> 0x00ef }
        L_0x00ee:
            throw r13
        L_0x00ef:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x00ee
        L_0x00f4:
            r4 = move-exception
            r4.printStackTrace()
        L_0x00f8:
            r1 = r2
            goto L_0x0028
        L_0x00fb:
            r13 = move-exception
            r1 = r2
            goto L_0x00e9
        L_0x00fe:
            r4 = move-exception
            r1 = r2
            goto L_0x00d8
        L_0x0101:
            r4 = move-exception
            r1 = r2
            goto L_0x00c3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.MainUI.ThermalInfoUtil.getThermalInfo():java.util.List");
    }
}
