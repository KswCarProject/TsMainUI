package com.ts.main.auth;

public class FileUtils {
    /* JADX WARNING: Removed duplicated region for block: B:123:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:125:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008b A[SYNTHETIC, Splitter:B:25:0x008b] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ae A[SYNTHETIC, Splitter:B:39:0x00ae] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00ce A[SYNTHETIC, Splitter:B:51:0x00ce] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:22:0x0086=Splitter:B:22:0x0086, B:36:0x00a9=Splitter:B:36:0x00a9} */
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
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x01c2, IOException -> 0x01bf }
            java.io.FileReader r8 = new java.io.FileReader     // Catch:{ FileNotFoundException -> 0x01c2, IOException -> 0x01bf }
            r8.<init>(r4)     // Catch:{ FileNotFoundException -> 0x01c2, IOException -> 0x01bf }
            r1.<init>(r8)     // Catch:{ FileNotFoundException -> 0x01c2, IOException -> 0x01bf }
            r6 = 0
            r8 = 2
            java.lang.String[] r5 = new java.lang.String[r8]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
        L_0x0021:
            java.lang.String r6 = r1.readLine()     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            if (r6 != 0) goto L_0x002e
            if (r1 == 0) goto L_0x01b9
            r1.close()     // Catch:{ IOException -> 0x01b5 }
            r0 = r1
            goto L_0x000c
        L_0x002e:
            java.lang.String r8 = ";"
            java.lang.String r9 = ""
            java.lang.String r6 = r6.replace(r8, r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.String r8 = "="
            java.lang.String[] r5 = r6.split(r8)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.io.PrintStream r8 = java.lang.System.out     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            r8.println(r6)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.io.PrintStream r8 = java.lang.System.out     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            r9 = 0
            r9 = r5[r9]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            r8.println(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.io.PrintStream r8 = java.lang.System.out     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            r9 = 1
            r9 = r5[r9]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            r8.println(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            r8 = 1
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            int r7 = r8.intValue()     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.String r9 = "coreType"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            if (r8 == 0) goto L_0x0096
            java.io.PrintStream r8 = java.lang.System.out     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.String r10 = "value"
            r9.<init>(r10)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.StringBuilder r9 = r9.append(r7)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.String r9 = r9.toString()     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            r8.println(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            r3.setCoreType(r7)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            goto L_0x0021
        L_0x0084:
            r2 = move-exception
            r0 = r1
        L_0x0086:
            r2.printStackTrace()     // Catch:{ all -> 0x01bc }
            if (r0 == 0) goto L_0x000c
            r0.close()     // Catch:{ IOException -> 0x0090 }
            goto L_0x000c
        L_0x0090:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x000c
        L_0x0096:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.String r9 = "harType"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            if (r8 == 0) goto L_0x00b9
            r3.setHardType(r7)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            goto L_0x0021
        L_0x00a7:
            r2 = move-exception
            r0 = r1
        L_0x00a9:
            r2.printStackTrace()     // Catch:{ all -> 0x01bc }
            if (r0 == 0) goto L_0x000c
            r0.close()     // Catch:{ IOException -> 0x00b3 }
            goto L_0x000c
        L_0x00b3:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x000c
        L_0x00b9:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.String r9 = "keyType"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            if (r8 == 0) goto L_0x00d2
            r3.setKeyType(r7)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            goto L_0x0021
        L_0x00ca:
            r8 = move-exception
            r0 = r1
        L_0x00cc:
            if (r0 == 0) goto L_0x00d1
            r0.close()     // Catch:{ IOException -> 0x01af }
        L_0x00d1:
            throw r8
        L_0x00d2:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.String r9 = "ram"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            if (r8 == 0) goto L_0x00e3
            r3.setRam(r7)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            goto L_0x0021
        L_0x00e3:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.String r9 = "rom"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            if (r8 == 0) goto L_0x00f4
            r3.setRom(r7)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            goto L_0x0021
        L_0x00f4:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.String r9 = "sdcard"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            if (r8 == 0) goto L_0x0105
            r3.setbSD(r7)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            goto L_0x0021
        L_0x0105:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.String r9 = "imei"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            if (r8 == 0) goto L_0x0116
            r3.setbIMEI(r7)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            goto L_0x0021
        L_0x0116:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.String r9 = "sim"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            if (r8 == 0) goto L_0x0127
            r3.setbSim(r7)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            goto L_0x0021
        L_0x0127:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.String r9 = "gps"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            if (r8 == 0) goto L_0x0138
            r3.setbGps(r7)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            goto L_0x0021
        L_0x0138:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.String r9 = "wifi"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            if (r8 == 0) goto L_0x0149
            r3.setbWifi(r7)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            goto L_0x0021
        L_0x0149:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.String r9 = "usbPort"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            if (r8 == 0) goto L_0x015a
            r3.setUSB_PORT(r7)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            goto L_0x0021
        L_0x015a:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.String r9 = "radioType"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            if (r8 == 0) goto L_0x016b
            r3.setRadioIC(r7)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            goto L_0x0021
        L_0x016b:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.String r9 = "audio"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            if (r8 == 0) goto L_0x017c
            r3.SetAutoAudio(r7)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            goto L_0x0021
        L_0x017c:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.String r9 = "carPlay"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            if (r8 == 0) goto L_0x018d
            r3.SetCarplay(r7)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            goto L_0x0021
        L_0x018d:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.String r9 = "is360"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            if (r8 == 0) goto L_0x019e
            r3.Set360View(r7)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            goto L_0x0021
        L_0x019e:
            r8 = 0
            r8 = r5[r8]     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            java.lang.String r9 = "ahd"
            boolean r8 = r8.equals(r9)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            if (r8 == 0) goto L_0x0021
            r3.SetAhd(r7)     // Catch:{ FileNotFoundException -> 0x0084, IOException -> 0x00a7, all -> 0x00ca }
            goto L_0x0021
        L_0x01af:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x00d1
        L_0x01b5:
            r2 = move-exception
            r2.printStackTrace()
        L_0x01b9:
            r0 = r1
            goto L_0x000c
        L_0x01bc:
            r8 = move-exception
            goto L_0x00cc
        L_0x01bf:
            r2 = move-exception
            goto L_0x00a9
        L_0x01c2:
            r2 = move-exception
            goto L_0x0086
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.main.auth.FileUtils.getFcTestMode(java.lang.String):com.ts.main.auth.FcTestMode");
    }
}
