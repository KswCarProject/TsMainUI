package com.ts.main.common;

import android.app.ActivityManager;
import android.content.Context;
import android.os.FileObserver;
import android.text.TextUtils;
import android.util.Log;
import com.android.SdkConstants;
import com.ts.MainUI.TsFile;
import com.ts.bt.ContactInfo;
import com.txznet.sdk.TXZResourceManager;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TsLogger {
    private static final String ANR_TRACE = "ANR_TRACE";
    /* access modifiers changed from: private */
    public static String ANR_TRACE_FILEPATH = "/data/anr/traces.txt";
    private static final String CRASH_REPORTER_EXTENSION = ".txt";
    static final String Crash_PATH = "/mnt/sdcard/TsLog/";
    static TsLogger MyTsLogger = null;
    private static final String STACK_TRACE = "STACK_TRACE";
    private static final String TAG = "TsLogger";
    public static Context mContext = null;
    private long lastTimes = 0;
    private Properties mDeviceCrashInfo = new Properties();
    ExecutorService mExecutorService = Executors.newSingleThreadExecutor();

    public static TsLogger GetInstance() {
        if (MyTsLogger == null) {
            MyTsLogger = new TsLogger();
        }
        return MyTsLogger;
    }

    public void CatlogToSys(final String Path, final int nNum) {
        if (!TsFile.fileIsExists(Path)) {
            tool.GetInstance().DealSu("mkdir " + Path);
            tool.GetInstance().ChangePathToRoot(Path);
        }
        new Thread() {
            public void run() {
                tool.GetInstance().DealSu("logcat -v threadtime -r 9216 -n " + nNum + " -f " + Path + "/logcat_main.log");
            }
        }.start();
    }

    public void CatlogToSd(final String Path, final int nNum) {
        if (!TsFile.fileIsExists(Path)) {
            TsFile.NewDir(Path);
        }
        new Thread() {
            public void run() {
                tool.GetInstance().DealSu("logcat -v threadtime -r 9216 -n " + nNum + " -f " + Path + "/logcat_main.log");
            }
        }.start();
        startANRListener(Path);
    }

    /* access modifiers changed from: protected */
    public void startANRListener(final String SPath) {
        Log.d(TAG, "startANRListener: =" + SPath);
        try {
            new FileObserver("/data/anr", 8) {
                public void onEvent(int event, String path) {
                    Log.d(TsLogger.TAG, "onEvent: " + path);
                    if (path != null) {
                        tool.GetInstance().RootSystem();
                        tool.GetInstance().DealSu("mkdir " + SPath + "/anr");
                        tool.GetInstance().DealSu("cp -r /data/anr " + SPath);
                        TsLogger.ANR_TRACE_FILEPATH = "/data/anr/" + path;
                        TsLogger.this.mExecutorService.execute(new Runnable() {
                            public void run() {
                                TsLogger.this.filiterANR();
                            }
                        });
                    }
                }
            }.startWatching();
            Log.d(TAG, "start anr monitor!");
        } catch (Throwable th) {
            Log.d(TAG, "start anr monitor failed!");
        }
    }

    public void Init(Context MyContext) {
        mContext = MyContext;
    }

    /* access modifiers changed from: private */
    public void filiterANR() {
        try {
            long nowTime = System.currentTimeMillis();
            if (nowTime - this.lastTimes < 10000) {
                Log.d(TAG, "should not process ANR too Fre in 10000");
                return;
            }
            this.lastTimes = nowTime;
            ActivityManager.ProcessErrorStateInfo errorStateInfo = findError(mContext, 10000);
            if (errorStateInfo == null) {
                Log.d(TAG, "proc state is unvisiable!");
                return;
            }
            Log.d(TAG, "not mind proc!" + errorStateInfo.processName);
            saveANRInfoToFile("Found ANR in !" + errorStateInfo.processName + ":\r\n " + errorStateInfo.longMsg + "\n\n");
        } catch (Throwable throwable) {
            Log.d(TAG, "handle anr error  " + throwable.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public ActivityManager.ProcessErrorStateInfo findError(Context context, long time) {
        if (time < 0) {
            time = 0;
        }
        ActivityManager var4 = (ActivityManager) context.getSystemService(SdkConstants.TAG_ACTIVITY);
        long var5 = time;
        int index = 0;
        while (true) {
            Log.d(TAG, "waiting!");
            List<ActivityManager.ProcessErrorStateInfo> errorStateInfoList = var4.getProcessesInErrorState();
            if (errorStateInfoList != null) {
                for (ActivityManager.ProcessErrorStateInfo errorStateInfo : errorStateInfoList) {
                    if (errorStateInfo.condition == 2) {
                        Log.d(TAG, "a: found!" + errorStateInfo.processName + ContactInfo.COMBINATION_SEPERATOR + errorStateInfo.shortMsg + ContactInfo.COMBINATION_SEPERATOR + errorStateInfo.longMsg + ContactInfo.COMBINATION_SEPERATOR);
                        return errorStateInfo;
                    }
                }
            }
            int index2 = index + 1;
            if (((long) index) >= var5) {
                Log.d(TAG, "end!");
                int i = index2;
                return null;
            }
            index = index2;
        }
    }

    private String saveANRInfoToFile(String anrmsg) {
        Log.d(TAG, "path = " + ANR_TRACE_FILEPATH);
        this.mDeviceCrashInfo.put(STACK_TRACE, anrmsg);
        StringBuilder anrlog = readFile(ANR_TRACE_FILEPATH);
        if (anrlog != null && !TextUtils.isEmpty(anrlog.toString())) {
            this.mDeviceCrashInfo.put(ANR_TRACE, anrlog.toString());
        }
        try {
            String fileName = "crash-" + new SimpleDateFormat("yyyy-MM-dd_HHmmss", Locale.getDefault()).format(new Date(System.currentTimeMillis())) + ".txt";
            File file = new File("/mnt/sdcard/TsLog/ANR", fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            FileOutputStream trace = new FileOutputStream(file);
            Log.d(TAG, "saveANRInfoToFile: 3");
            this.mDeviceCrashInfo.storeToXML(trace, "crashLog");
            Log.d(TAG, "saveANRInfoToFile: 4");
            trace.flush();
            trace.close();
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing report file..." + TXZResourceManager.STYLE_DEFAULT, e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x007f A[SYNTHETIC, Splitter:B:31:0x007f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.StringBuilder readFile(java.lang.String r10) {
        /*
            java.io.File r1 = new java.io.File
            r1.<init>(r10)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r7 = ""
            r2.<init>(r7)
            if (r1 == 0) goto L_0x0015
            boolean r7 = r1.isFile()
            if (r7 != 0) goto L_0x0017
        L_0x0015:
            r2 = 0
        L_0x0016:
            return r2
        L_0x0017:
            r5 = 0
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0090 }
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0090 }
            r7.<init>(r1)     // Catch:{ IOException -> 0x0090 }
            java.lang.String r8 = "UTF-8"
            r3.<init>(r7, r8)     // Catch:{ IOException -> 0x0090 }
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0090 }
            r6.<init>(r3)     // Catch:{ IOException -> 0x0090 }
            r4 = 0
        L_0x002b:
            java.lang.String r4 = r6.readLine()     // Catch:{ IOException -> 0x0071, all -> 0x008d }
            if (r4 != 0) goto L_0x005a
            r6.close()     // Catch:{ IOException -> 0x0071, all -> 0x008d }
            java.lang.String r7 = "TsLogger"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0071, all -> 0x008d }
            java.lang.String r9 = "readFile: "
            r8.<init>(r9)     // Catch:{ IOException -> 0x0071, all -> 0x008d }
            java.lang.StringBuilder r8 = r8.append(r2)     // Catch:{ IOException -> 0x0071, all -> 0x008d }
            java.lang.String r8 = r8.toString()     // Catch:{ IOException -> 0x0071, all -> 0x008d }
            android.util.Log.d(r7, r8)     // Catch:{ IOException -> 0x0071, all -> 0x008d }
            if (r6 == 0) goto L_0x0016
            r6.close()     // Catch:{ IOException -> 0x0050 }
            goto L_0x0016
        L_0x0050:
            r0 = move-exception
            java.lang.RuntimeException r7 = new java.lang.RuntimeException
            java.lang.String r8 = "IOException occurred. "
            r7.<init>(r8, r0)
            throw r7
        L_0x005a:
            java.lang.String r7 = r2.toString()     // Catch:{ IOException -> 0x0071, all -> 0x008d }
            java.lang.String r8 = ""
            boolean r7 = r7.equals(r8)     // Catch:{ IOException -> 0x0071, all -> 0x008d }
            if (r7 != 0) goto L_0x006d
            java.lang.String r7 = "\r\n"
            r2.append(r7)     // Catch:{ IOException -> 0x0071, all -> 0x008d }
        L_0x006d:
            r2.append(r4)     // Catch:{ IOException -> 0x0071, all -> 0x008d }
            goto L_0x002b
        L_0x0071:
            r0 = move-exception
            r5 = r6
        L_0x0073:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException     // Catch:{ all -> 0x007c }
            java.lang.String r8 = "IOException occurred. "
            r7.<init>(r8, r0)     // Catch:{ all -> 0x007c }
            throw r7     // Catch:{ all -> 0x007c }
        L_0x007c:
            r7 = move-exception
        L_0x007d:
            if (r5 == 0) goto L_0x0082
            r5.close()     // Catch:{ IOException -> 0x0083 }
        L_0x0082:
            throw r7
        L_0x0083:
            r0 = move-exception
            java.lang.RuntimeException r7 = new java.lang.RuntimeException
            java.lang.String r8 = "IOException occurred. "
            r7.<init>(r8, r0)
            throw r7
        L_0x008d:
            r7 = move-exception
            r5 = r6
            goto L_0x007d
        L_0x0090:
            r0 = move-exception
            goto L_0x0073
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.main.common.TsLogger.readFile(java.lang.String):java.lang.StringBuilder");
    }
}
