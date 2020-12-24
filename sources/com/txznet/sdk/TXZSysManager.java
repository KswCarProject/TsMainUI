package com.txznet.sdk;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.hongfans.carmedia.BuildConfig;
import com.txznet.comm.Tr.T;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.TXZService;
import com.txznet.sdk.tongting.IConstantData;
import org.json.JSONObject;

/* compiled from: Proguard */
public class TXZSysManager {

    /* renamed from: T  reason: collision with root package name */
    private static TXZSysManager f822T;
    /* access modifiers changed from: private */
    public WakeLockTool T5 = null;
    private byte[] T6;
    /* access modifiers changed from: private */
    public VolumeMgrTool T9 = null;
    private boolean TE = false;
    private boolean TF = false;
    /* access modifiers changed from: private */
    public MuteAllTool TZ = null;
    private boolean Te = false;
    /* access modifiers changed from: private */
    public AppMgrTool Th = null;
    /* access modifiers changed from: private */
    public ScreenLightTool Tj;
    private boolean Tk = false;
    private boolean Tn = false;
    /* access modifiers changed from: private */
    public ScreenSleepTool Tq = null;
    private Integer Tr;
    private boolean Tv = false;
    private Integer Ty;

    /* compiled from: Proguard */
    public static class AppInfo {
        public String strAppName;
        public String strPackageName;
    }

    /* compiled from: Proguard */
    public interface MuteAllTool {
        void mute(boolean z);
    }

    /* compiled from: Proguard */
    public interface ScreenLightTool {
        void decLight();

        void incLight();

        boolean isMaxLight();

        boolean isMinLight();

        void maxLight();

        void minLight();
    }

    /* compiled from: Proguard */
    public interface ScreenSleepTool {
        void goToSleep();
    }

    /* compiled from: Proguard */
    public interface VolumeMgrTool {
        void decVolume();

        boolean decVolume(int i);

        void incVolume();

        boolean incVolume(int i);

        boolean isMaxVolume();

        boolean isMinVolume();

        void maxVolume();

        void minVolume();

        void mute(boolean z);

        boolean setVolume(int i);
    }

    /* compiled from: Proguard */
    public interface VolumeSettingCallBack {
        void onError(int i);

        void onOperateResult(boolean z);
    }

    /* compiled from: Proguard */
    public interface WakeLockTool {
        void acquire();

        void release();
    }

    private TXZSysManager() {
    }

    public static TXZSysManager getInstance() {
        if (f822T == null) {
            synchronized (TXZSysManager.class) {
                if (f822T == null) {
                    f822T = new TXZSysManager();
                }
            }
        }
        return f822T;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        if (this.Tn) {
            if (this.T9 == null) {
                Tn.Tr().T("com.txznet.txz", "txz.sys.volume.cleartool", (byte[]) null, (Tn.Tr) null);
            } else {
                Tn.Tr().T("com.txznet.txz", "txz.sys.volume.settool", (byte[]) null, (Tn.Tr) null);
                if (!(this.Ty == null || this.Tr == null)) {
                    setVolumeDistance(this.Ty.intValue(), this.Tr.intValue());
                }
            }
        }
        if (this.TE) {
            if (this.T5 == null) {
                Tn.Tr().T("com.txznet.txz", "txz.sys.wakelock.cleartool", (byte[]) null, (Tn.Tr) null);
            } else {
                Tn.Tr().T("com.txznet.txz", "txz.sys.wakelock.settool", (byte[]) null, (Tn.Tr) null);
            }
        }
        if (this.Tv) {
            if (this.Th == null) {
                Tn.Tr().T("com.txznet.txz", "txz.sys.appmgr.cleartool", (byte[]) null, (Tn.Tr) null);
            } else {
                Tn.Tr().T("com.txznet.txz", "txz.sys.appmgr.settool", (byte[]) null, (Tn.Tr) null);
            }
        }
        if (this.Te) {
            if (this.Tq == null) {
                Tn.Tr().T("com.txznet.txz", "txz.sys.screensleep.cleartool", (byte[]) null, (Tn.Tr) null);
            } else {
                Tn.Tr().T("com.txznet.txz", "txz.sys.screensleep.settool", (byte[]) null, (Tn.Tr) null);
            }
        }
        if (this.Tk) {
            if (this.TZ == null) {
                Tn.Tr().T("com.txznet.txz", "txz.sys.muteall.cleartool", (byte[]) null, (Tn.Tr) null);
            } else {
                Tn.Tr().T("com.txznet.txz", "txz.sys.muteall.settool", (byte[]) null, (Tn.Tr) null);
            }
        }
        if (this.TF) {
            setScreenLightTool(this.Tj);
        }
        Tr();
    }

    public boolean setVolumeDistance(int minVolumeValue, int maxVolumeValue) {
        if (minVolumeValue < 0 || maxVolumeValue <= minVolumeValue) {
            return false;
        }
        this.Tr = Integer.valueOf(maxVolumeValue);
        this.Ty = Integer.valueOf(minVolumeValue);
        Tr json = new Tr();
        json.T("maxVal", (Object) this.Tr);
        json.T("minVal", (Object) this.Ty);
        Tn.Tr().T("com.txznet.txz", "txz.sys.volume.setvolumedistance", json.toString().getBytes(), (Tn.Tr) null);
        return true;
    }

    public void setVolumeMgrTool(VolumeMgrTool volumeMgrTool) {
        this.Tn = true;
        this.T9 = volumeMgrTool;
        if (this.T9 == null) {
            Tn.Tr().T("com.txznet.txz", "txz.sys.volume.cleartool", (byte[]) null, (Tn.Tr) null);
            return;
        }
        TXZService.T("tool.volume.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if (command.equals("decVolume")) {
                    if (data != null && data.length > 0) {
                        return (TXZSysManager.this.T9.decVolume(((Integer) new Tr(data).T(IConstantData.KEY_DATA, Integer.class, 0)).intValue()) + "").getBytes();
                    }
                    TXZSysManager.this.T9.decVolume();
                    return data;
                } else if (command.equals("incVolume")) {
                    if (data != null && data.length > 0) {
                        return (TXZSysManager.this.T9.incVolume(((Integer) new Tr(data).T(IConstantData.KEY_DATA, Integer.class, 0)).intValue()) + "").getBytes();
                    }
                    TXZSysManager.this.T9.incVolume();
                    return data;
                } else if (command.equals("maxVolume")) {
                    TXZSysManager.this.T9.maxVolume();
                    return data;
                } else if (command.equals("minVolume")) {
                    TXZSysManager.this.T9.minVolume();
                    return data;
                } else if (command.equals("isMaxVolume")) {
                    return (TXZSysManager.this.T9.isMaxVolume() + "").getBytes();
                } else {
                    if (command.equals("isMinVolume")) {
                        return (TXZSysManager.this.T9.isMinVolume() + "").getBytes();
                    }
                    if (command.equals("mute")) {
                        try {
                            TXZSysManager.this.T9.mute(new JSONObject(new String(data)).getBoolean("enable"));
                            return data;
                        } catch (Exception e) {
                            return data;
                        }
                    } else if (!command.equals("setVolume") || data == null || data.length <= 0) {
                        return data;
                    } else {
                        return (TXZSysManager.this.T9.setVolume(((Integer) new Tr(data).T(IConstantData.KEY_DATA, Integer.class, 0)).intValue()) + "").getBytes();
                    }
                }
            }
        });
        Tn.Tr().T("com.txznet.txz", "txz.sys.volume.settool", (byte[]) null, (Tn.Tr) null);
    }

    public void setMuteAllTool(MuteAllTool muteAllTool) {
        this.Tk = true;
        this.TZ = muteAllTool;
        if (this.TZ == null) {
            Tn.Tr().T("com.txznet.txz", "txz.sys.muteall.cleartool", (byte[]) null, (Tn.Tr) null);
            return;
        }
        TXZService.T("tool.muteall.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if (command.equals("mute")) {
                    TXZSysManager.this.TZ.mute(true);
                    return null;
                } else if (!command.equals("unmute")) {
                    return null;
                } else {
                    TXZSysManager.this.TZ.mute(false);
                    return null;
                }
            }
        });
        Tn.Tr().T("com.txznet.txz", "txz.sys.muteall.settool", (byte[]) null, (Tn.Tr) null);
    }

    public void setWakeLockTool(WakeLockTool wakeLockTool) {
        this.TE = true;
        this.T5 = wakeLockTool;
        if (this.T5 == null) {
            Tn.Tr().T("com.txznet.txz", "txz.sys.wakelock.cleartool", (byte[]) null, (Tn.Tr) null);
            return;
        }
        TXZService.T("tool.wakelock.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if (command.equals("acquire")) {
                    TXZSysManager.this.T5.acquire();
                    return "true".getBytes();
                } else if (!command.equals(BuildConfig.BUILD_TYPE)) {
                    return null;
                } else {
                    TXZSysManager.this.T5.release();
                    return "true".getBytes();
                }
            }
        });
        Tn.Tr().T("com.txznet.txz", "txz.sys.wakelock.settool", (byte[]) null, (Tn.Tr) null);
    }

    /* compiled from: Proguard */
    public static abstract class AppMgrTool {
        public void openApp(String packageName) {
            Intent in;
            if (T.Tr() != null && (in = T.Tr().getPackageManager().getLaunchIntentForPackage(packageName)) != null) {
                in.addFlags(270532608);
                T.Tr().startActivity(in);
            }
        }

        public void closeApp(final String packageName) {
            if (T.Tr() != null) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

                    /* renamed from: T  reason: collision with root package name */
                    int f829T = 0;

                    public void run() {
                        if (TXZSysManager.Tr(T.Tr(), packageName)) {
                            try {
                                this.f829T++;
                                ((ActivityManager) T.Tr().getSystemService("activity")).killBackgroundProcesses(packageName);
                            } catch (Exception e) {
                            }
                            if (this.f829T < 50) {
                                new Handler(Looper.getMainLooper()).postDelayed(this, 0);
                            }
                        } else if (this.f829T > 0) {
                            this.f829T = 0;
                            new Handler(Looper.getMainLooper()).postDelayed(this, 2000);
                        }
                    }
                }, 0);
            }
        }
    }

    /* access modifiers changed from: private */
    public static boolean Tr(Context context, String packageName) {
        try {
            for (ActivityManager.RunningAppProcessInfo rapi : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (rapi.processName.equals(packageName)) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void setAppMgrTool(AppMgrTool appMgrTool) {
        this.Tv = true;
        this.Th = appMgrTool;
        if (this.Th == null) {
            Tn.Tr().T("com.txznet.txz", "txz.sys.appmgr.cleartool", (byte[]) null, (Tn.Tr) null);
            return;
        }
        TXZService.T("tool.appmgr.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if (command.equals("closeApp")) {
                    try {
                        TXZSysManager.this.Th.closeApp(new JSONObject(new String(data)).getString("pkgName"));
                        return null;
                    } catch (Exception e) {
                        return null;
                    }
                } else if (!command.equals("openApp")) {
                    return null;
                } else {
                    try {
                        TXZSysManager.this.Th.openApp(new JSONObject(new String(data)).getString("pkgName"));
                        return null;
                    } catch (Exception e2) {
                        return null;
                    }
                }
            }
        });
        Tn.Tr().T("com.txznet.txz", "txz.sys.appmgr.settool", (byte[]) null, (Tn.Tr) null);
    }

    public void syncAppInfoList(AppInfo[] appInfos) {
        try {
            com.T.T.Tr jInfos = new com.T.T.Tr();
            for (AppInfo info : appInfos) {
                jInfos.add(new Tr().T("strAppName", (Object) info.strAppName).T("strPackageName", (Object) info.strPackageName).Tr());
            }
            com.txznet.comm.Tr.Tr.Tn.T("syncAppInfoList list=" + jInfos);
            this.T6 = new Tr().T("infos", (Object) jInfos).Ty();
            Tr();
        } catch (Exception e) {
        }
    }

    private void Tr() {
        if (this.T6 != null) {
            Tn.Tr().T("com.txznet.txz", "txz.sys.pkg.sync", this.T6, (Tn.Tr) null);
        }
    }

    public void setScreenSleepTool(ScreenSleepTool screenSleepTool) {
        this.Te = true;
        this.Tq = screenSleepTool;
        if (this.Tq == null) {
            Tn.Tr().T("com.txznet.txz", "txz.sys.screensleep.cleartool", (byte[]) null, (Tn.Tr) null);
            return;
        }
        TXZService.T("tool.screensleep.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if (!command.equals("goToSleep")) {
                    return null;
                }
                TXZSysManager.this.Tq.goToSleep();
                return null;
            }
        });
        Tn.Tr().T("com.txznet.txz", "txz.sys.screensleep.settool", (byte[]) null, (Tn.Tr) null);
    }

    public void setScreenLightTool(ScreenLightTool tool) {
        this.TF = true;
        this.Tj = tool;
        if (this.Tj == null) {
            Tn.Tr().T("com.txznet.txz", "txz.sys.screenlight.cleartool", (byte[]) null, (Tn.Tr) null);
            return;
        }
        TXZService.T("tool.light.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if (command.equals("light_up")) {
                    TXZSysManager.this.Tj.incLight();
                    return data;
                } else if (command.equals("light_down")) {
                    TXZSysManager.this.Tj.decLight();
                    return data;
                } else if (command.equals("light_max")) {
                    TXZSysManager.this.Tj.maxLight();
                    return data;
                } else if (command.equals("light_min")) {
                    TXZSysManager.this.Tj.minLight();
                    return data;
                } else if (command.equals("isMaxLight")) {
                    return (TXZSysManager.this.Tj.isMaxLight() + "").getBytes();
                } else {
                    if (command.equals("isMinLight")) {
                        return (TXZSysManager.this.Tj.isMinLight() + "").getBytes();
                    }
                    return data;
                }
            }
        });
        Tn.Tr().T("com.txznet.txz", "txz.sys.screenlight.settool", (byte[]) null, (Tn.Tr) null);
    }
}
