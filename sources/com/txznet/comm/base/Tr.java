package com.txznet.comm.base;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Environment;
import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.android.SdkConstants;
import com.ts.main.common.MainUI;
import com.txznet.sdk.TXZResourceManager;
import com.txznet.txz.util.T9;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.CRC32;

/* compiled from: Proguard */
public class Tr extends Application {

    /* renamed from: T  reason: collision with root package name */
    public static long f420T = 180000;
    static String T5;
    static boolean T6 = false;
    public static String T9 = (Environment.getExternalStorageDirectory().getPath() + "/.txz/loader");
    static ApplicationInfo TB;
    static String TE;
    static AssetManager TF;
    static Resources.Theme TK;
    static String TZ;
    static Object Te = null;
    static Resources Tj;
    static String Tk;
    public static int Tn = 10;
    static ClassLoader Tq;
    public static long Tr = 300000;
    static Boolean Tv = null;
    public static int Ty = 5;
    private boolean TO;
    SharedPreferences Th;

    private void Ty() {
        Tk = null;
        TZ = null;
        T5 = null;
        TE = null;
        Tq = null;
        Te = null;
        TF = null;
        Tj = null;
        TK = null;
    }

    public byte[] T(String archiveFilePath) {
        try {
            return super.getPackageManager().getPackageArchiveInfo(archiveFilePath, 64).signatures[0].toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] Tr(String packName) {
        try {
            return super.getPackageManager().getPackageInfo(packName, 64).signatures[0].toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String T() {
        int pid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo processInfo : ((ActivityManager) getSystemService(SdkConstants.TAG_ACTIVITY)).getRunningAppProcesses()) {
            if (processInfo.pid == pid) {
                return processInfo.processName;
            }
        }
        return TXZResourceManager.STYLE_DEFAULT;
    }

    public boolean Tr() {
        if (Tv == null) {
            Tv = Boolean.valueOf(T().equals(getApplicationInfo().packageName));
        }
        return Tv.booleanValue();
    }

    private void T(FileInputStream f, byte[] bs) throws IOException {
        int t = 0;
        while (t < bs.length) {
            t += f.read(bs, t, bs.length - t);
        }
    }

    /* compiled from: Proguard */
    private class T extends RuntimeException {
        private Throwable Tr;

        public T(String info, Throwable ex) {
            super("Rollback excepiton: " + info);
            this.Tr = ex;
        }

        public String getLocalizedMessage() {
            if (this.Tr != null) {
                return super.getLocalizedMessage() + this.Tr.getLocalizedMessage();
            }
            return super.getLocalizedMessage();
        }

        public Throwable getCause() {
            if (this.Tr != null) {
                return this.Tr.getCause();
            }
            return super.getCause();
        }

        public String getMessage() {
            if (this.Tr != null) {
                return super.getMessage() + this.Tr.getMessage();
            }
            return super.getMessage();
        }

        public StackTraceElement[] getStackTrace() {
            if (this.Tr != null) {
                return this.Tr.getStackTrace();
            }
            return super.getStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void T(String info, Throwable ex) {
        Log.w("TXZAppLoader1.0", info);
        Ty.T(this, Environment.getExternalStorageDirectory().getPath() + "/txz/report/", (Thread) null, new T(info, ex));
    }

    private boolean Tn() {
        Ty();
        if (Tr()) {
            String mLoaderApkVer = this.Th.getString("loader_ver", TXZResourceManager.STYLE_DEFAULT);
            long mLoaderApkLen = this.Th.getLong("loader_len", 0);
            long mLoaderApkCrc = this.Th.getLong("loader_crc", 0);
            try {
                String mLoaderApkVer2 = com.txznet.T.T.Ty();
                File file = new File(getApplicationInfo().sourceDir);
                FileInputStream fileInputStream = new FileInputStream(file);
                long mLoaderApkLen2 = file.length();
                CRC32 crc32 = new CRC32();
                byte[] bs = new byte[100];
                T(fileInputStream, bs);
                crc32.update(bs);
                fileInputStream.skip(file.length() / 4);
                T(fileInputStream, bs);
                crc32.update(bs);
                fileInputStream.skip(file.length() / 4);
                T(fileInputStream, bs);
                crc32.update(bs);
                fileInputStream.skip(file.length() / 4);
                T(fileInputStream, bs);
                crc32.update(bs);
                fileInputStream.close();
                long mLoaderApkCrc2 = crc32.getValue();
                if (!(mLoaderApkLen == mLoaderApkLen2 && mLoaderApkCrc == mLoaderApkCrc2 && mLoaderApkVer.equals(mLoaderApkVer2))) {
                    SharedPreferences.Editor e = this.Th.edit();
                    e.putString("loader_ver", mLoaderApkVer2);
                    e.putLong("loader_len", mLoaderApkLen2);
                    e.putLong("loader_crc", mLoaderApkCrc2);
                    e.commit();
                    Log.w("TXZAppLoader1.0", "load outter failed: not match loader config: len[" + mLoaderApkLen + "/" + mLoaderApkLen2 + "], crc[" + mLoaderApkCrc + "/" + mLoaderApkCrc2 + "], ver[" + mLoaderApkVer + "/" + mLoaderApkVer2 + "]");
                    TZ();
                    return false;
                }
            } catch (Exception e2) {
            }
        }
        TZ = getApplicationInfo().dataDir + "/dex";
        new File(TZ).mkdirs();
        Tk = this.Th.getString(SdkConstants.EXT_ANDROID_PACKAGE, (String) null);
        if (getApplicationInfo().sourceDir.equals(Tk)) {
            Log.w("TXZAppLoader1.0", "load outter failed: same source apk");
            return false;
        } else if (TextUtils.isEmpty(Tk)) {
            Log.w("TXZAppLoader1.0", "load outter failed: no outter data setting");
            return false;
        } else {
            File fApk = new File(Tk);
            if (!fApk.exists()) {
                File fApkDir = fApk.getParentFile();
                fApkDir.mkdirs();
                if (fApkDir.getTotalSpace() <= 0) {
                    Log.w("TXZAppLoader1.0", "load outter failed: partition is not ready");
                    SharedPreferences.Editor editor = this.Th.edit();
                    editor.remove("launchTimes");
                    editor.commit();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e3) {
                    }
                    com.txznet.T.T.Tv();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e4) {
                    }
                    return true;
                }
                T("load outter failed: check data failed - not exist " + fApk.getAbsolutePath(), (Throwable) null);
                return false;
            }
            long l1 = fApk.length();
            long l2 = this.Th.getLong("size", -1);
            if (l1 != l2) {
                T("load outter failed: check data failed - length not match " + l1 + "/" + l2, (Throwable) null);
                return false;
            }
            if (fApk.lastModified() != this.Th.getLong(MainUI.NET_TIME_, -1)) {
            }
            TE = getApplicationInfo().dataDir + "/solibs";
            new File(TE).mkdirs();
            T5 = getApplicationInfo().dataDir + "/dexfiles";
            new File(T5).mkdirs();
            ClassLoader loader = new com.txznet.T.Tr(Tk, T5, TZ, TE, ClassLoader.getSystemClassLoader());
            try {
                loader.loadClass("com.txznet.loader.ApkLoader").getDeclaredMethod("process", new Class[]{Application.class, ClassLoader.class}).invoke((Object) null, new Object[]{this, loader});
                final String apkPath = Tk;
                if (Tr()) {
                    new Thread() {
                        /* access modifiers changed from: package-private */
                        public boolean T() {
                            try {
                                byte[] signApp = Tr.this.Tr(this.getPackageName());
                                byte[] signApk = Tr.this.T(apkPath);
                                if (signApp == null || signApk == null) {
                                    Log.d("TXZAppLoader1.0", "load outter warning: get sign failed " + signApk);
                                    com.txznet.T.T.TE();
                                    return true;
                                } else if (signApp == null || signApk == null || signApp.length != signApk.length) {
                                    Tr.this.T("load outter failed: check sign length failed", (Throwable) null);
                                    return false;
                                } else {
                                    for (int i = 0; i < signApk.length; i++) {
                                        if (signApp[i] != signApk[i]) {
                                            Tr.this.T("load outter failed: check sign data failed", (Throwable) null);
                                            return false;
                                        }
                                    }
                                    Log.d("TXZAppLoader1.0", "load outter success: check sign success");
                                    return true;
                                }
                            } catch (Exception e) {
                                Tr.this.T("load outter failed: check sign exception failed" + e.getClass().getName() + "#" + e.getMessage(), (Throwable) e);
                                return false;
                            }
                        }

                        public void run() {
                            try {
                                Thread.sleep(20000);
                            } catch (InterruptedException e) {
                            }
                            if (!T()) {
                                com.txznet.comm.update.T.T((Context) this);
                                com.txznet.T.T.TE();
                            }
                        }
                    }.start();
                }
                return true;
            } catch (Exception e5) {
                e5.printStackTrace();
                T("load outter failed: process failed " + e5.getClass().getName() + "#" + e5.getMessage(), (Throwable) e5);
                return false;
            }
        }
    }

    private boolean Tn(String apkPath) {
        Ty();
        Log.w("TXZAppLoader1.0", "begin load  inner from apk: " + apkPath);
        TZ = getApplicationInfo().dataDir + "/dex";
        new File(TZ).mkdirs();
        getApplicationInfo().sourceDir = apkPath;
        Tk = apkPath;
        if (TextUtils.isEmpty(Tk)) {
            Log.w("TXZAppLoader1.0", "load inner from apk failed");
            return false;
        }
        File fApk = new File(Tk);
        if (!fApk.exists()) {
            Log.w("TXZAppLoader1.0", "load inner from apk failed: check data failed - not exist " + fApk.getAbsolutePath());
            return false;
        } else if (!T9.T(Tk, SdkConstants.FN_APK_CLASSES_DEX)) {
            Log.w("TXZAppLoader1.0", "load inner from apk failed: classes.dex not found - " + fApk.getAbsolutePath());
            return false;
        } else {
            TE = getApplicationInfo().dataDir + "/solibs";
            new File(TE).mkdirs();
            T5 = getApplicationInfo().dataDir + "/dexfiles";
            new File(T5).mkdirs();
            ClassLoader loader = new com.txznet.T.Tr(Tk, T5, TZ, TE, ClassLoader.getSystemClassLoader());
            try {
                loader.loadClass("com.txznet.loader.ApkLoader").getDeclaredMethod("process", new Class[]{Application.class, ClassLoader.class}).invoke((Object) null, new Object[]{this, loader});
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                Log.w("TXZAppLoader1.0", "load  inner from apk failed: process failed");
                return false;
            }
        }
    }

    private boolean T9() {
        Class<?> clsAppLogic;
        boolean z;
        Ty();
        TZ = getApplicationInfo().dataDir + "/dex";
        new File(TZ).mkdirs();
        TE = getApplicationInfo().dataDir + "/solibs";
        new File(TE).mkdirs();
        T5 = getApplicationInfo().dataDir + "/dexfiles";
        new File(T5).mkdirs();
        Tk = getApplicationInfo().sourceDir;
        try {
            ApplicationInfo appInfo = super.getPackageManager().getApplicationInfo(super.getApplicationInfo().packageName, 0);
            if (!super.getApplicationInfo().sourceDir.equals(appInfo.sourceDir)) {
                StringBuilder append = new StringBuilder().append("load inner failed: source dir not match ").append(super.getApplicationInfo().sourceDir).append("|").append(appInfo.sourceDir).append(", UPDATED=");
                if ((super.getApplicationInfo().flags & 128) != 0) {
                    z = true;
                } else {
                    z = false;
                }
                Log.w("TXZAppLoader1.0", append.append(z).toString());
                com.txznet.comm.update.T.T(30000);
                return Tn(appInfo.sourceDir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        T9.T(getClassLoader(), com.txznet.T.Tr.T(Tk, T5, TZ, TE, getClassLoader()), TE);
        try {
            clsAppLogic = getClassLoader().loadClass("com.txznet.loader.AppLogic");
        } catch (ClassNotFoundException e2) {
            try {
                clsAppLogic = getClassLoader().loadClass("com.txznet.loader.AppLogicDefault");
            } catch (ClassNotFoundException e3) {
                Log.w("TXZAppLoader1.0", "load inner failed: load logic class failed");
                return false;
            }
        }
        try {
            Te = clsAppLogic.newInstance();
            Tq = clsAppLogic.getClassLoader();
            try {
                PackageInfo info = super.getPackageManager().getPackageInfo(getPackageName(), 0);
                com.txznet.comm.Tn.T.f357T = info.versionCode;
                com.txznet.comm.Tn.T.Tr = info.versionName;
            } catch (Exception e4) {
            }
            return true;
        } catch (Exception e5) {
            Log.w("TXZAppLoader1.0", "load inner failed: create logic instance failed");
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00d1 A[SYNTHETIC, Splitter:B:21:0x00d1] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00d6 A[Catch:{ Exception -> 0x019a }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00db A[Catch:{ Exception -> 0x019a }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0176 A[SYNTHETIC, Splitter:B:41:0x0176] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x017b A[Catch:{ Exception -> 0x0194 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0180 A[Catch:{ Exception -> 0x0194 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean Tk() {
        /*
            r20 = this;
            r20.Ty()
            android.content.pm.ApplicationInfo r2 = r20.getApplicationInfo()
            java.lang.String r0 = r2.packageName
            r18 = r0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            android.content.pm.ApplicationInfo r3 = r20.getApplicationInfo()
            java.lang.String r3 = r3.dataDir
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = "/dex"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            TZ = r2
            java.io.File r2 = new java.io.File
            java.lang.String r3 = TZ
            r2.<init>(r3)
            r2.mkdirs()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = T9
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = "/tmp"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r19 = r2.toString()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r0 = r19
            java.lang.StringBuilder r2 = r2.append(r0)
            java.lang.String r3 = "/"
            java.lang.StringBuilder r2 = r2.append(r3)
            r0 = r18
            java.lang.StringBuilder r2 = r2.append(r0)
            java.lang.String r3 = ".apk"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            Tk = r2
            java.io.File r10 = new java.io.File
            java.lang.String r2 = Tk
            r10.<init>(r2)
            r11 = 0
            r12 = 0
            r16 = 0
            android.content.res.AssetManager r2 = r20.getAssets()     // Catch:{ Exception -> 0x019d }
            java.lang.String r3 = "txz.jet"
            android.content.res.AssetFileDescriptor r11 = r2.openFd(r3)     // Catch:{ Exception -> 0x019d }
            r15 = 1
            boolean r2 = r10.exists()     // Catch:{ Exception -> 0x019d }
            if (r2 == 0) goto L_0x0096
            long r2 = r10.length()     // Catch:{ Exception -> 0x019d }
            long r4 = r11.getLength()     // Catch:{ Exception -> 0x019d }
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x0096
            r15 = 0
        L_0x0096:
            if (r15 == 0) goto L_0x00e1
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x019d }
            r0 = r19
            r2.<init>(r0)     // Catch:{ Exception -> 0x019d }
            r2.mkdirs()     // Catch:{ Exception -> 0x019d }
            java.io.FileInputStream r12 = r11.createInputStream()     // Catch:{ Exception -> 0x019d }
            java.io.FileOutputStream r17 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x019d }
            r0 = r17
            r0.<init>(r10)     // Catch:{ Exception -> 0x019d }
            r13 = 0
            r2 = 1048576(0x100000, float:1.469368E-39)
            byte[] r8 = new byte[r2]     // Catch:{ Exception -> 0x00bf, all -> 0x0196 }
        L_0x00b2:
            int r13 = r12.read(r8)     // Catch:{ Exception -> 0x00bf, all -> 0x0196 }
            if (r13 <= 0) goto L_0x00df
            r2 = 0
            r0 = r17
            r0.write(r8, r2, r13)     // Catch:{ Exception -> 0x00bf, all -> 0x0196 }
            goto L_0x00b2
        L_0x00bf:
            r9 = move-exception
            r16 = r17
        L_0x00c2:
            r9.printStackTrace()     // Catch:{ all -> 0x0173 }
            java.lang.String r2 = "TXZAppLoader1.0"
            java.lang.String r3 = "load assets failed: unzip assets data failed"
            android.util.Log.w(r2, r3)     // Catch:{ all -> 0x0173 }
            r2 = 0
            if (r16 == 0) goto L_0x00d4
            r16.close()     // Catch:{ Exception -> 0x019a }
        L_0x00d4:
            if (r12 == 0) goto L_0x00d9
            r12.close()     // Catch:{ Exception -> 0x019a }
        L_0x00d9:
            if (r11 == 0) goto L_0x00de
            r11.close()     // Catch:{ Exception -> 0x019a }
        L_0x00de:
            return r2
        L_0x00df:
            r16 = r17
        L_0x00e1:
            if (r16 == 0) goto L_0x00e6
            r16.close()     // Catch:{ Exception -> 0x01a0 }
        L_0x00e6:
            if (r12 == 0) goto L_0x00eb
            r12.close()     // Catch:{ Exception -> 0x01a0 }
        L_0x00eb:
            if (r11 == 0) goto L_0x00f0
            r11.close()     // Catch:{ Exception -> 0x01a0 }
        L_0x00f0:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            android.content.pm.ApplicationInfo r3 = r20.getApplicationInfo()
            java.lang.String r3 = r3.dataDir
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = "/solibs"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            TE = r2
            java.io.File r2 = new java.io.File
            java.lang.String r3 = TE
            r2.<init>(r3)
            r2.mkdirs()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            android.content.pm.ApplicationInfo r3 = r20.getApplicationInfo()
            java.lang.String r3 = r3.dataDir
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = "/dexfiles"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            T5 = r2
            java.io.File r2 = new java.io.File
            java.lang.String r3 = T5
            r2.<init>(r3)
            r2.mkdirs()
            com.txznet.T.Tr r1 = new com.txznet.T.Tr
            java.lang.String r2 = Tk
            java.lang.String r3 = T5
            java.lang.String r4 = TZ
            java.lang.String r5 = TE
            java.lang.ClassLoader r6 = super.getClassLoader()
            r1.<init>(r2, r3, r4, r5, r6)
            java.lang.String r2 = "com.txznet.loader.ApkLoader"
            java.lang.Class r7 = r1.loadClass(r2)     // Catch:{ Exception -> 0x0184 }
            java.lang.String r2 = "process"
            r3 = 1
            java.lang.Class[] r3 = new java.lang.Class[r3]     // Catch:{ Exception -> 0x0184 }
            r4 = 0
            java.lang.Class<android.app.Application> r5 = android.app.Application.class
            r3[r4] = r5     // Catch:{ Exception -> 0x0184 }
            java.lang.reflect.Method r14 = r7.getDeclaredMethod(r2, r3)     // Catch:{ Exception -> 0x0184 }
            r2 = 0
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x0184 }
            r4 = 0
            r3[r4] = r20     // Catch:{ Exception -> 0x0184 }
            r4 = 1
            r3[r4] = r1     // Catch:{ Exception -> 0x0184 }
            r14.invoke(r2, r3)     // Catch:{ Exception -> 0x0184 }
            r2 = 1
            goto L_0x00de
        L_0x0173:
            r2 = move-exception
        L_0x0174:
            if (r16 == 0) goto L_0x0179
            r16.close()     // Catch:{ Exception -> 0x0194 }
        L_0x0179:
            if (r12 == 0) goto L_0x017e
            r12.close()     // Catch:{ Exception -> 0x0194 }
        L_0x017e:
            if (r11 == 0) goto L_0x0183
            r11.close()     // Catch:{ Exception -> 0x0194 }
        L_0x0183:
            throw r2
        L_0x0184:
            r9 = move-exception
            r9.printStackTrace()
            java.lang.String r2 = "TXZAppLoader1.0"
            java.lang.String r3 = "load assets failed: proccess assets data failed"
            android.util.Log.w(r2, r3)
            r2 = 0
            goto L_0x00de
        L_0x0194:
            r3 = move-exception
            goto L_0x0183
        L_0x0196:
            r2 = move-exception
            r16 = r17
            goto L_0x0174
        L_0x019a:
            r3 = move-exception
            goto L_0x00de
        L_0x019d:
            r9 = move-exception
            goto L_0x00c2
        L_0x01a0:
            r2 = move-exception
            goto L_0x00f0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.txznet.comm.base.Tr.Tk():boolean");
    }

    private void T9(String path) {
        try {
            File d = new File(path);
            for (File f : d.listFiles()) {
                if (f.isDirectory()) {
                    T9(f.getPath());
                } else {
                    f.delete();
                }
            }
            d.delete();
        } catch (Exception e) {
        }
    }

    private void TZ() {
        Log.w("TXZAppLoader1.0", "application need reset upzip data");
        T9(getApplicationInfo().dataDir + "/solibs");
        T9(getApplicationInfo().dataDir + "/data");
        T9(getApplicationInfo().dataDir + "/dexfiles");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x015f, code lost:
        if (T9() != false) goto L_0x0161;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0161, code lost:
        T("onCreate", android.app.Application.class, r18);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x016b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0174, code lost:
        if (Tn() != false) goto L_0x0161;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0186, code lost:
        if (Tk() == false) goto L_0x0188;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void TE() {
        /*
            r18 = this;
            com.txznet.comm.Tr.T.T(r18)
            android.content.pm.ApplicationInfo r14 = r18.getApplicationInfo()
            java.lang.String r9 = r14.packageName
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.StringBuilder r14 = r14.append(r9)
            java.lang.String r15 = ".ApkLoader"
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            r15 = 0
            r0 = r18
            android.content.SharedPreferences r14 = r0.getSharedPreferences(r14, r15)
            r0 = r18
            r0.Th = r14
            r0 = r18
            android.content.SharedPreferences r14 = r0.Th
            java.lang.String r15 = "WORK_SPACE"
            java.lang.String r16 = T9
            java.lang.String r14 = r14.getString(r15, r16)
            T9 = r14
            r0 = r18
            android.content.SharedPreferences r14 = r0.Th
            java.lang.String r15 = "MIN_RUN_TIME"
            long r16 = Tr
            long r14 = r14.getLong(r15, r16)
            Tr = r14
            r0 = r18
            android.content.SharedPreferences r14 = r0.Th
            java.lang.String r15 = "MIN_RESET_COUNT"
            int r16 = Ty
            int r14 = r14.getInt(r15, r16)
            Ty = r14
            r0 = r18
            android.content.SharedPreferences r14 = r0.Th
            java.lang.String r15 = "MIN_ROLLBACK_COUNT"
            int r16 = Tn
            int r14 = r14.getInt(r15, r16)
            Tn = r14
            r14 = 0
            r0 = r18
            r0.TO = r14
            r1 = 0
            boolean r14 = r18.Tr()
            if (r14 == 0) goto L_0x012a
            r0 = r18
            android.content.SharedPreferences r14 = r0.Th
            java.lang.String r15 = "launchTimes"
            java.lang.String r16 = ""
            java.lang.String r7 = r14.getString(r15, r16)
            java.lang.String r14 = ";"
            java.lang.String[] r8 = r7.split(r14)
            if (r8 == 0) goto L_0x00b2
            int r14 = r8.length
            int r15 = Ty
            if (r14 < r15) goto L_0x00b2
            r14 = 1
            r0 = r18
            r0.TO = r14
            int r14 = r8.length
            int r6 = r14 + -1
        L_0x0094:
            int r14 = r8.length
            int r15 = Ty
            int r14 = r14 - r15
            if (r6 < r14) goto L_0x00b2
            r12 = 0
            r14 = r8[r6]     // Catch:{ Exception -> 0x0196 }
            long r12 = java.lang.Long.parseLong(r14)     // Catch:{ Exception -> 0x0196 }
        L_0x00a2:
            long r14 = java.lang.System.currentTimeMillis()
            long r14 = r14 - r12
            long r16 = Tr
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 <= 0) goto L_0x00f1
            r14 = 0
            r0 = r18
            r0.TO = r14
        L_0x00b2:
            long r2 = java.lang.System.currentTimeMillis()
            if (r8 == 0) goto L_0x00d5
            int r14 = r8.length
            int r15 = Tn
            if (r14 < r15) goto L_0x00d5
            r1 = 1
            int r14 = r8.length
            int r6 = r14 + -1
        L_0x00c1:
            int r14 = r8.length
            int r15 = Tn
            int r14 = r14 - r15
            if (r6 < r14) goto L_0x00d5
            r12 = 0
            r14 = r8[r6]     // Catch:{ Exception -> 0x0193 }
            long r12 = java.lang.Long.parseLong(r14)     // Catch:{ Exception -> 0x0193 }
        L_0x00cf:
            int r14 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r14 >= 0) goto L_0x00f4
            r8 = 0
            r1 = 0
        L_0x00d5:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            int r11 = Tn
            if (r8 != 0) goto L_0x0101
            r11 = 0
        L_0x00df:
            r6 = r11
        L_0x00e0:
            if (r6 <= 0) goto L_0x0108
            int r14 = r8.length
            int r14 = r14 - r6
            r14 = r8[r14]
            r10.append(r14)
            r14 = 59
            r10.append(r14)
            int r6 = r6 + -1
            goto L_0x00e0
        L_0x00f1:
            int r6 = r6 + -1
            goto L_0x0094
        L_0x00f4:
            long r14 = r2 - r12
            long r16 = Tr
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 <= 0) goto L_0x00fe
            r1 = 0
            goto L_0x00d5
        L_0x00fe:
            int r6 = r6 + -1
            goto L_0x00c1
        L_0x0101:
            int r14 = r8.length
            int r15 = Tn
            if (r14 >= r15) goto L_0x00df
            int r11 = r8.length
            goto L_0x00df
        L_0x0108:
            long r14 = android.os.SystemClock.elapsedRealtime()
            long r16 = f420T
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 <= 0) goto L_0x016c
            r10.append(r2)
            r0 = r18
            android.content.SharedPreferences r14 = r0.Th
            android.content.SharedPreferences$Editor r5 = r14.edit()
            java.lang.String r14 = "launchTimes"
            java.lang.String r15 = r10.toString()
            r5.putString(r14, r15)
            r5.commit()
        L_0x012a:
            r0 = r18
            boolean r14 = r0.TO
            if (r14 == 0) goto L_0x0133
            r18.TZ()
        L_0x0133:
            if (r1 == 0) goto L_0x0170
            java.lang.String r14 = "TXZAppLoader1.0"
            java.lang.String r15 = "application need rollback"
            android.util.Log.w(r14, r15)
            r0 = r18
            android.content.SharedPreferences r14 = r0.Th
            android.content.SharedPreferences$Editor r5 = r14.edit()
            java.lang.String r14 = "launchTimes"
            r5.remove(r14)
            r5.commit()
            java.lang.String r14 = "restart too many times at few minutes"
            r15 = 0
            r0 = r18
            r0.T((java.lang.String) r14, (java.lang.Throwable) r15)
        L_0x0158:
            com.txznet.comm.update.T.T((android.content.Context) r18)
            boolean r14 = r18.T9()     // Catch:{ Exception -> 0x0181 }
            if (r14 == 0) goto L_0x0182
        L_0x0161:
            java.lang.String r14 = "onCreate"
            java.lang.Class<android.app.Application> r15 = android.app.Application.class
            r0 = r18
            T((java.lang.String) r14, r15, r0)
            return
        L_0x016c:
            com.txznet.T.T.T5()
            goto L_0x012a
        L_0x0170:
            boolean r14 = r18.Tn()     // Catch:{ Exception -> 0x0177 }
            if (r14 == 0) goto L_0x0158
            goto L_0x0161
        L_0x0177:
            r4 = move-exception
            java.lang.String r14 = "load outer exception"
            r0 = r18
            r0.T((java.lang.String) r14, (java.lang.Throwable) r4)
            goto L_0x0158
        L_0x0181:
            r14 = move-exception
        L_0x0182:
            boolean r14 = r18.Tk()     // Catch:{ Exception -> 0x0191 }
            if (r14 != 0) goto L_0x0161
        L_0x0188:
            java.lang.RuntimeException r14 = new java.lang.RuntimeException
            java.lang.String r15 = "load application failed"
            r14.<init>(r15)
            throw r14
        L_0x0191:
            r14 = move-exception
            goto L_0x0188
        L_0x0193:
            r14 = move-exception
            goto L_0x00cf
        L_0x0196:
            r14 = move-exception
            goto L_0x00a2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.txznet.comm.base.Tr.TE():void");
    }

    public void onCreate() {
        super.onCreate();
        Log.i("TXZAppLoader1.0", "begin create application: main=" + Tr());
        if (T6) {
            Log.w("TXZAppLoader1.0", "already created application");
            return;
        }
        T6 = true;
        TE();
    }

    public void onLowMemory() {
        Log.w("TXZAppLoader1.0", "application onLowMemory");
        Ty("onLowMemory");
        super.onLowMemory();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        T("onConfigurationChanged", Configuration.class, newConfig);
    }

    public void onTerminate() {
        Log.w("TXZAppLoader1.0", "application onTerminate");
        Ty("onTerminate");
        super.onTerminate();
    }

    @TargetApi(14)
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        T("onTrimMemory", Integer.TYPE, Integer.valueOf(level));
    }

    public static void Ty(String name) {
        try {
            Tq.loadClass("com.txznet.loader.AppLogicBase").getDeclaredMethod(name, new Class[0]).invoke(Te, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> void T(String name, Class<T> clazz, T param) {
        try {
            Tq.loadClass("com.txznet.loader.AppLogicBase").getDeclaredMethod(name, new Class[]{clazz}).invoke(Te, new Object[]{param});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ClassLoader getClassLoader() {
        if (Tq != null) {
            return Tq;
        }
        return super.getClassLoader();
    }

    public AssetManager getAssets() {
        if (TF != null) {
            return TF;
        }
        return super.getAssets();
    }

    public Resources getResources() {
        if (Tj != null) {
            return Tj;
        }
        return super.getResources();
    }

    public ApplicationInfo getApplicationInfo() {
        if (TB != null) {
            return TB;
        }
        return super.getApplicationInfo();
    }

    public Resources.Theme getTheme() {
        if (TK != null) {
            return TK;
        }
        return super.getTheme();
    }

    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        com.txznet.T.T.T(receiver);
        return super.registerReceiver(receiver, filter);
    }

    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, String broadcastPermission, Handler scheduler) {
        com.txznet.T.T.T(receiver);
        return super.registerReceiver(receiver, filter, broadcastPermission, scheduler);
    }

    public void unregisterReceiver(BroadcastReceiver receiver) {
        if (!com.txznet.T.T.Tr(receiver)) {
            Log.w("TXZAppLoader1.0", "Receiver: " + receiver + "not register");
        } else {
            super.unregisterReceiver(receiver);
        }
    }
}
