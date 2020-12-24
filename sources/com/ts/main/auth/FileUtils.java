package com.ts.main.auth;

public class FileUtils {
    /* JADX WARNING: Removed duplicated region for block: B:107:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:109:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0086 A[SYNTHETIC, Splitter:B:25:0x0086] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a6 A[SYNTHETIC, Splitter:B:39:0x00a6] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00c5 A[SYNTHETIC, Splitter:B:51:0x00c5] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:22:0x0081=Splitter:B:22:0x0081, B:36:0x00a1=Splitter:B:36:0x00a1} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ts.main.auth.FcTestMode getFcTestMode(java.lang.String r11) {
        /*
            java.io.File r4 = new java.io.File
            r4.<init>(r11)
            boolean r8 = r4.exists()
            if (r8 != 0) goto L_0x000d
            r3 = 0
        L_0x000c:
            return r3
        L_0x000d:
            r0 = 0
            com.ts.main.auth.FcTestMode r3 = new com.ts.main.auth.FcTestMode
            r3.<init>()
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x016c, IOException -> 0x0169 }
            java.io.FileReader r8 = new java.io.FileReader     // Catch:{ FileNotFoundException -> 0x016c, IOException -> 0x0169 }
            r8.<init>(r4)     // Catch:{ FileNotFoundException -> 0x016c, IOException -> 0x0169 }
            r1.<init>(r8)     // Catch:{ FileNotFoundException -> 0x016c, IOException -> 0x0169 }
            r6 = 0
            r8 = 2
            java.lang.String[] r5 = new java.lang.String[r8]     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
        L_0x0021:
            java.lang.String r6 = r1.readLine()     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            if (r6 != 0) goto L_0x002e
            if (r1 == 0) goto L_0x0163
            r1.close()     // Catch:{ IOException -> 0x015f }
            r0 = r1
            goto L_0x000c
        L_0x002e:
            java.lang.String r8 = ";"
            java.lang.String r9 = ""
            java.lang.String r6 = r6.replace(r8, r9)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.lang.String r8 = "="
            java.lang.String[] r5 = r6.split(r8)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.io.PrintStream r8 = java.lang.System.out     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            r8.println(r6)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.io.PrintStream r8 = java.lang.System.out     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            r9 = 0
            r9 = r5[r9]     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            r8.println(r9)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.io.PrintStream r8 = java.lang.System.out     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            r9 = 1
            r9 = r5[r9]     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            r8.println(r9)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            r8 = 1
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            int r7 = r8.intValue()     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.lang.String r9 = "coreType"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            if (r8 == 0) goto L_0x0090
            java.io.PrintStream r8 = java.lang.System.out     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.lang.String r10 = "value"
            r9.<init>(r10)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.lang.StringBuilder r9 = r9.append(r7)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.lang.String r9 = r9.toString()     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            r8.println(r9)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            r3.setCoreType(r7)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            goto L_0x0021
        L_0x007f:
            r2 = move-exception
            r0 = r1
        L_0x0081:
            r2.printStackTrace()     // Catch:{ all -> 0x0166 }
            if (r0 == 0) goto L_0x000c
            r0.close()     // Catch:{ IOException -> 0x008a }
            goto L_0x000c
        L_0x008a:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x000c
        L_0x0090:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.lang.String r9 = "harType"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            if (r8 == 0) goto L_0x00b1
            r3.setHardType(r7)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            goto L_0x0021
        L_0x009f:
            r2 = move-exception
            r0 = r1
        L_0x00a1:
            r2.printStackTrace()     // Catch:{ all -> 0x0166 }
            if (r0 == 0) goto L_0x000c
            r0.close()     // Catch:{ IOException -> 0x00ab }
            goto L_0x000c
        L_0x00ab:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x000c
        L_0x00b1:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.lang.String r9 = "keyType"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            if (r8 == 0) goto L_0x00c9
            r3.setKeyType(r7)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            goto L_0x0021
        L_0x00c1:
            r8 = move-exception
            r0 = r1
        L_0x00c3:
            if (r0 == 0) goto L_0x00c8
            r0.close()     // Catch:{ IOException -> 0x0159 }
        L_0x00c8:
            throw r8
        L_0x00c9:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.lang.String r9 = "ram"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            if (r8 == 0) goto L_0x00d9
            r3.setRam(r7)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            goto L_0x0021
        L_0x00d9:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.lang.String r9 = "rom"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            if (r8 == 0) goto L_0x00e9
            r3.setRom(r7)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            goto L_0x0021
        L_0x00e9:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.lang.String r9 = "sdcard"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            if (r8 == 0) goto L_0x00f9
            r3.setbSD(r7)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            goto L_0x0021
        L_0x00f9:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.lang.String r9 = "imei"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            if (r8 == 0) goto L_0x0109
            r3.setbIMEI(r7)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            goto L_0x0021
        L_0x0109:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.lang.String r9 = "sim"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            if (r8 == 0) goto L_0x0119
            r3.setbSim(r7)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            goto L_0x0021
        L_0x0119:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.lang.String r9 = "gps"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            if (r8 == 0) goto L_0x0129
            r3.setbGps(r7)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            goto L_0x0021
        L_0x0129:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.lang.String r9 = "wifi"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            if (r8 == 0) goto L_0x0139
            r3.setbWifi(r7)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            goto L_0x0021
        L_0x0139:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.lang.String r9 = "usbPort"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            if (r8 == 0) goto L_0x0149
            r3.setUSB_PORT(r7)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            goto L_0x0021
        L_0x0149:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            java.lang.String r9 = "radioType"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            if (r8 == 0) goto L_0x0021
            r3.setRadioIC(r7)     // Catch:{ FileNotFoundException -> 0x007f, IOException -> 0x009f, all -> 0x00c1 }
            goto L_0x0021
        L_0x0159:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x00c8
        L_0x015f:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0163:
            r0 = r1
            goto L_0x000c
        L_0x0166:
            r8 = move-exception
            goto L_0x00c3
        L_0x0169:
            r2 = move-exception
            goto L_0x00a1
        L_0x016c:
            r2 = move-exception
            goto L_0x0081
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.main.auth.FileUtils.getFcTestMode(java.lang.String):com.ts.main.auth.FcTestMode");
    }
}
