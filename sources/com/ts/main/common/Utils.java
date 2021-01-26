package com.ts.main.common;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static final String ACTION_QB_POWEROFF = "autochips.intent.action.QB_POWEROFF";
    public static final String ACTION_QB_POWERON = "autochips.intent.action.QB_POWERON";
    public static final String ACTION_QB_SERVICE = "autochips.intent.action.QB_SERVICE";
    public static final String CONFIG_PATH = "etc/qb_list.xml";
    private static final boolean DEBUG = true;
    private static boolean LIST_GOT = false;
    public static final String PREF_VALUE_3RD = "3RD";
    public static final String PREF_VALUE_SYS = "sys";
    private static final boolean RDEBUG = true;
    private static final String TAG = "QuickBootManager";
    public static final String WL_PREF_FILE = "added_white_list";
    public static Map<String, String> WhiteList = new HashMap();
    public static final String YL_PREF_FILE = "added_yellow_list";
    public static Map<String, String> YellowList = new HashMap();
    private static Object objToSync = new Object();

    public static void IF_LOG_OUT(String msg) {
        Log.i(TAG, msg);
    }

    public static void IF_RLOG_OUT(String msg) {
        Log.i(TAG, msg);
    }

    private static void checkInit() {
        synchronized (objToSync) {
            if (LIST_GOT) {
                IF_LOG_OUT("list already got.");
                return;
            }
            readList(Environment.buildPath(Environment.getVendorDirectory(), new String[]{CONFIG_PATH}));
        }
    }

    public static Map<String, String> getWhiteList(Context ctx) {
        checkInit();
        return WhiteList;
    }

    public static Map<String, String> getYellowList(Context ctx) {
        checkInit();
        return YellowList;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void readList(java.io.File r11) {
        /*
            r10 = 1
            r9 = 0
            r2 = 0
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ FileNotFoundException -> 0x0019 }
            r3.<init>(r11)     // Catch:{ FileNotFoundException -> 0x0019 }
            org.xmlpull.v1.XmlPullParser r5 = android.util.Xml.newPullParser()     // Catch:{ Exception -> 0x0056 }
            r5.setInput(r3)     // Catch:{ Exception -> 0x0056 }
            int r1 = r5.getEventType()     // Catch:{ Exception -> 0x0056 }
        L_0x0013:
            if (r1 != r10) goto L_0x0030
            LIST_GOT = r10
            r2 = r3
        L_0x0018:
            return
        L_0x0019:
            r0 = move-exception
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "InitConfig Not found config: "
            r6.<init>(r7)
            java.lang.StringBuilder r6 = r6.append(r11)
            java.lang.String r6 = r6.toString()
            IF_RLOG_OUT(r6)
            LIST_GOT = r9
            goto L_0x0018
        L_0x0030:
            switch(r1) {
                case 0: goto L_0x0033;
                case 1: goto L_0x0033;
                case 2: goto L_0x0038;
                default: goto L_0x0033;
            }
        L_0x0033:
            int r1 = r5.next()     // Catch:{ Exception -> 0x0056 }
            goto L_0x0013
        L_0x0038:
            java.lang.String r4 = r5.getName()     // Catch:{ Exception -> 0x0056 }
            java.lang.String r6 = "app_whitelist"
            boolean r6 = r4.equals(r6)     // Catch:{ Exception -> 0x0056 }
            if (r6 == 0) goto L_0x005e
            java.util.Map<java.lang.String, java.lang.String> r6 = WhiteList     // Catch:{ Exception -> 0x0056 }
            r7 = 0
            java.lang.String r8 = "name"
            java.lang.String r7 = r5.getAttributeValue(r7, r8)     // Catch:{ Exception -> 0x0056 }
            java.lang.String r8 = "sys"
            r6.put(r7, r8)     // Catch:{ Exception -> 0x0056 }
            goto L_0x0033
        L_0x0056:
            r0 = move-exception
            r0.printStackTrace()
            LIST_GOT = r9
            r2 = r3
            goto L_0x0018
        L_0x005e:
            java.lang.String r6 = "app_yellowlist"
            boolean r6 = r4.equals(r6)     // Catch:{ Exception -> 0x0056 }
            if (r6 == 0) goto L_0x0033
            java.util.Map<java.lang.String, java.lang.String> r6 = YellowList     // Catch:{ Exception -> 0x0056 }
            r7 = 0
            java.lang.String r8 = "name"
            java.lang.String r7 = r5.getAttributeValue(r7, r8)     // Catch:{ Exception -> 0x0056 }
            java.lang.String r8 = "sys"
            r6.put(r7, r8)     // Catch:{ Exception -> 0x0056 }
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.main.common.Utils.readList(java.io.File):void");
    }
}
