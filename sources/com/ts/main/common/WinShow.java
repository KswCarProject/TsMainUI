package com.ts.main.common;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.SystemProperties;
import android.util.Log;
import com.android.SdkConstants;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.bt.BtExe;
import com.ts.can.CanIF;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;
import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WinShow {
    public static int NewOldMode = 255;
    private static final String TAG = "WinShow";
    public static final int WIN_ATV = 23;
    public static final int WIN_AUTH = 16;
    public static final int WIN_AUX = 9;
    public static final int WIN_AVIN = 10;
    public static final int WIN_BLACK_APP = 24;
    public static final int WIN_BOOT_LOGO = 17;
    public static final int WIN_BT = 7;
    public static final int WIN_CMMB = 8;
    public static final int WIN_C_STUDY = 19;
    public static final int WIN_DVD = 3;
    public static final int WIN_DVD_BOX = 21;
    public static final int WIN_FACTORY = 15;
    public static final int WIN_FCAM = 14;
    public static final int WIN_FILE_COPY = 20;
    public static final int WIN_IPOD = 13;
    public static final int WIN_MAIN = 0;
    public static final int WIN_MCU_UPDATE = 18;
    public static final int WIN_NAVI = 1;
    public static final int WIN_RADIO = 2;
    public static final int WIN_RECOGNIZE = 22;
    public static final int WIN_SD = 6;
    public static final int WIN_SETUP = 11;
    public static final int WIN_TRAFFICRECORD = 12;
    public static final int WIN_USB1 = 4;
    public static final int WIN_USB2 = 5;
    private static int cmmbtoAux = -2;
    public static Context mContext = null;
    private static int nDelayTime = 300;
    private static int nDelaynewTime = 30;
    public static int nOldMode = 255;
    static int oldState = 255;

    private static int getFirstNumber(String s) {
        Matcher m = Pattern.compile("\\d+").matcher(s);
        if (m.find()) {
            return Integer.parseInt(m.group());
        }
        return -1;
    }

    public static int show(String sPackage, String sActivity) {
        String version;
        int logov;
        Intent intent1 = new Intent();
        intent1.setClassName(sPackage, sActivity);
        if (mContext.getPackageManager().resolveActivity(intent1, 0) == null) {
            return 0;
        }
        if (sPackage.equals(mContext.getPackageName())) {
            try {
                Class.forName(sActivity);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return -1;
            }
        }
        if (mContext == null) {
            return 0;
        }
        ComponentName componetName = new ComponentName(sPackage, sActivity);
        Intent intent = new Intent();
        intent.setComponent(componetName);
        if (sActivity.equals("com.ts.logoset.LogoSetMainActivity") && (version = SystemProperties.get("forfan.version.info")) != null) {
            try {
                if (version.length() == 0) {
                    logov = getFirstNumber(MainSet.getSystemVersion());
                } else {
                    logov = getFirstNumber(version);
                    int buildv = getFirstNumber(MainSet.getSystemVersion());
                    if (logov < buildv) {
                        logov = buildv;
                    }
                }
                intent.putExtra("android_version", logov);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        intent.addFlags(337641472);
        mContext.startActivity(intent);
        return 1;
    }

    public static int ShowBtWin(String sPackage, String sActivity, int nPos) {
        Intent intent1 = new Intent();
        intent1.setClassName(sPackage, sActivity);
        if (mContext.getPackageManager().resolveActivity(intent1, 0) == null) {
            return 0;
        }
        if (sPackage.equals(mContext.getPackageName())) {
            try {
                Class.forName(sActivity);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return -1;
            }
        }
        if (mContext == null) {
            return 0;
        }
        ComponentName componetName = new ComponentName(sPackage, sActivity);
        Intent intent = new Intent();
        intent.setComponent(componetName);
        intent.putExtra("pos", nPos);
        intent.addFlags(337641472);
        mContext.startActivity(intent);
        return 1;
    }

    public static String getTopPackName() {
        List<ActivityManager.RunningTaskInfo> taskInfos = ((ActivityManager) mContext.getSystemService(SdkConstants.TAG_ACTIVITY)).getRunningTasks(1);
        if (!taskInfos.isEmpty()) {
            return taskInfos.get(0).topActivity.getPackageName();
        }
        return TXZResourceManager.STYLE_DEFAULT;
    }

    public static String getTopActivityName() {
        List<ActivityManager.RunningTaskInfo> taskInfos = ((ActivityManager) mContext.getSystemService(SdkConstants.TAG_ACTIVITY)).getRunningTasks(1);
        if (!taskInfos.isEmpty()) {
            return taskInfos.get(0).topActivity.getClassName();
        }
        return TXZResourceManager.STYLE_DEFAULT;
    }

    public static boolean IsRadioActivity() {
        if (getTopActivityName().equals("com.ts.main.radio.RadioMainActivity") || CanIF.IsCurExdRadioWin(getTopActivityName())) {
            return true;
        }
        return false;
    }

    public static void TurnToEq() {
        GotoWin(11, 3);
    }

    public static boolean IsHaveDvd() {
        return new File("/storage/cdfs").exists();
    }

    static boolean IsHaveExtUsb() {
        String[] strSDMountedPath = MainSet.GetInstance().GetMountedStorage();
        Log.i(TAG, "IsHaveExtUsb.length  ==" + strSDMountedPath.length);
        for (int i = 0; i < strSDMountedPath.length; i++) {
            if (!strSDMountedPath[i].contains("sdcard0") && !strSDMountedPath[i].contains("cdfs")) {
                return true;
            }
        }
        return false;
    }

    private static boolean IsModeValid(int nMode) {
        if (nMode != 12 && FtSet.IsIconExist(nMode) == 0) {
            return false;
        }
        switch (nMode) {
            case 1:
            case 6:
            case 7:
            case 15:
                if (FtSet.IsIconExist(nMode) == 1) {
                    return true;
                }
                return false;
            case 2:
                return IsHaveDvd();
            case 3:
                if (FtSet.IsIconExist(nMode) == 0 || MainSet.GetInstance().IsHaveApk("com.mxtech.videoplayer.pro")) {
                    return false;
                }
                if (MainSet.GetInstance().IsCustom("FYDZ")) {
                    if (FtSet.IsIconExist(nMode) != 1 || !IsHaveExtUsb()) {
                        return false;
                    }
                    return true;
                } else if (FtSet.IsIconExist(nMode) == 1) {
                    return true;
                } else {
                    return false;
                }
            case 4:
                if (MainSet.GetInstance().IsCustom("FYDZ")) {
                    if (FtSet.IsIconExist(nMode) != 1 || !IsHaveExtUsb()) {
                        return false;
                    }
                    return true;
                } else if (FtSet.IsIconExist(nMode) == 1) {
                    return true;
                } else {
                    return false;
                }
            case 5:
                if (MainSet.GetInstance().IsCustom("FYDZ") || MainSet.GetInstance().IsCustom("TSKJ")) {
                    return true;
                }
                return BtExe.getBtInstance().isConnected();
            case 8:
            case 10:
            case 11:
            case 13:
            case 14:
                if ((!MainSet.IsXPH5() || MainSet.IsXPH5_HZ()) && mContext.getResources().getIdentifier("aux_mode_enabled", SdkConstants.TAG_STRING, mContext.getPackageName()) <= 0) {
                    return false;
                }
                return true;
            case 9:
                if ((!MainSet.IsXPH5() || MainSet.IsXPH5_HZ()) && FtSet.IsIconExist(nMode) == 1) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    public static void DealTask() {
        if (nDelayTime > 0) {
            nDelayTime--;
            if (nOldMode != Iop.GetWorkMode()) {
                nDelayTime = 0;
            }
            if (nDelayTime == 0) {
                nOldMode = 255;
            }
        }
        if (nDelaynewTime > 0) {
            nDelaynewTime--;
        }
    }

    public static void SetWorkMode(int mode) {
        NewOldMode = mode;
    }

    public static void DealModeKey() {
        if (MainSet.GetInstance().IsCustom("FYDZ") || MainSet.GetInstance().IsCustom("TSKJ")) {
            Log.i(TAG, "nOldMode = " + nOldMode);
            int n = NewOldMode;
            if (nDelaynewTime <= 0) {
                for (int i = 0; i <= 15; i++) {
                    n++;
                    if (n > 15) {
                        n = 1;
                    }
                    if (IsModeValid(n)) {
                        NewOldMode = n;
                        TsEnterMode(n);
                        nDelaynewTime = 25;
                        return;
                    }
                }
                return;
            }
            return;
        }
        Log.i(TAG, "nOldMode = " + nOldMode);
        Log.i(TAG, "Evc.GetInstance().Evol.workmode = " + Iop.GetWorkMode());
        Log.i(TAG, "nDelayTime = " + nDelayTime);
        if (nOldMode == Iop.GetWorkMode() || nDelayTime >= 280) {
            Log.i(TAG, "DealModeKey = " + nOldMode + "but workmode have not change");
            return;
        }
        nDelayTime = 300;
        nOldMode = Iop.GetWorkMode();
        int n2 = Iop.GetWorkMode();
        for (int i2 = 0; i2 <= 15; i2++) {
            n2++;
            if (n2 > 15) {
                n2 = 1;
            }
            if (IsModeValid(n2)) {
                Log.i(TAG, "DealModeKey = " + n2 + "nOldMode==" + nOldMode);
                TsEnterMode(n2);
                return;
            }
        }
    }

    public static int TsEnterMode(int nMode) {
        if (cmmbtoAux == -2) {
            cmmbtoAux = mContext.getResources().getIdentifier("cmmb_transto_aux", SdkConstants.TAG_STRING, mContext.getPackageName());
        }
        switch (nMode) {
            case 0:
                Evc.GetInstance().evol_workmode_set(0);
                int nBatFirst = Mcu.GetPowState() & 1;
                String MemStr = Evc.GetInstance().ReadMem();
                if (MemStr == null || nBatFirst != 0 || (!MainSet.GetInstance().IsTwcjw() && !MainSet.GetInstance().Support_lastMem())) {
                    MainUI.GetInstance().BackToLauncher();
                } else if (MainSet.GetInstance().GetNaviPath().equals(MemStr) || "com.ts.MainUI".equals(MemStr)) {
                    MainUI.GetInstance().BackToLauncher();
                } else if (mContext != null) {
                    if (MainSet.GetInstance().Support_lastMem()) {
                        String[] setOptions = mContext.getResources().getStringArray(R.array.supprot_last_memory_apps);
                        int i = 0;
                        while (true) {
                            if (i < setOptions.length) {
                                if (!setOptions[i].equals(MemStr)) {
                                    i++;
                                } else if (mContext.getPackageManager().getLaunchIntentForPackage(MemStr) != null) {
                                    MainSet.GetInstance().openApplication(mContext, MemStr);
                                } else {
                                    MainUI.GetInstance().BackToLauncher();
                                }
                            }
                        }
                    } else if (mContext.getPackageManager().getLaunchIntentForPackage(MemStr) != null) {
                        MainSet.GetInstance().openApplication(mContext, MemStr);
                    } else {
                        MainUI.GetInstance().BackToLauncher();
                    }
                }
                Evc.GetInstance().WriteMem("test");
                return 1;
            case 1:
                GotoWin(2, 0);
                return 1;
            case 2:
                GotoWin(3, 0);
                return 1;
            case 3:
                GotoWin(4, 0);
                return 1;
            case 4:
                GotoWin(6, 0);
                return 1;
            case 5:
                if (BtExe.getBtInstance().isConnected()) {
                    GotoWin(7, 4);
                    return 1;
                }
                GotoWin(7, 0);
                Evc.GetInstance().evol_workmode_set(5);
                return 1;
            case 6:
                if (118 == CanJni.GetCanFsTp()) {
                    show("com.ts.MainUI", "com.ts.main.dvr.DvrMainActivity");
                    return 1;
                } else if (242 == CanJni.GetCanFsTp()) {
                    CanIF.GotoExdMode();
                    return 1;
                } else if (cmmbtoAux > 0) {
                    GotoWin(9, 0);
                    return 1;
                } else {
                    GotoWin(8, 0);
                    return 1;
                }
            case 7:
                GotoWin(23, 0);
                return 1;
            case 8:
                GotoWin(9, 0);
                return 1;
            case 9:
                GotoWin(10, 0);
                return 1;
            case 10:
                GotoWin(13, 0);
                return 1;
            case 12:
                CanIF.GotoExdMode();
                return 1;
            case 15:
                GotoWin(21, 0);
                return 1;
            default:
                GotoWin(2, 0);
                return 1;
        }
    }

    public static void ShowVideoWin() {
        SetVideoShowParat(2);
        show("com.ts.dvdplayer", "com.ts.dvdplayer.USBActivity");
    }

    public static void ShowGoogleWin() {
        show("com.ts.MainUI", "com.ts.main.googleVoice.GoogleVoiceActivity");
    }

    public static void SetVideoShowParat(int state) {
        if ((MainSet.GetInstance().IsCustom("XPH") || MainSet.GetSerid().contains("XPH")) && oldState != state) {
            TsDisplay.GetInstance().SetSrcMute(10);
            Iop.SetGamma(state);
            Log.i(TAG, " SetVideoShowParat===" + state);
            oldState = state;
        }
    }

    public static void GotoWin(int nWin, int nParat1) {
        switch (nWin) {
            case 1:
                show("com.ts.MainUI", "com.ts.main.navi.NaviMainActivity");
                return;
            case 2:
                if (!CanIF.GotoExdRadWin()) {
                    show("com.ts.MainUI", "com.ts.main.radio.RadioMainActivity");
                    return;
                }
                return;
            case 3:
                show("com.android.sdvdplayer", "com.android.sdvdplayer.SDVDPlayer");
                return;
            case 4:
                if (!MainSet.GetInstance().IsHaveApk("com.mxtech.videoplayer.pro") || mContext.getResources().getString(R.string.custom_num).equals("MCXI")) {
                    SetVideoShowParat(2);
                    show("com.ts.dvdplayer", "com.ts.dvdplayer.USBActivity");
                    return;
                }
                MainSet.GetInstance().openApplication(mContext, "com.mxtech.videoplayer.pro");
                return;
            case 6:
                show("com.ts.dvdplayer", "com.ts.dvdplayer.SDActivity");
                return;
            case 7:
                switch (nParat1) {
                    case 0:
                        show("com.ts.MainUI", "com.ts.bt.BtConnectActivity");
                        return;
                    case 1:
                        show("com.ts.MainUI", "com.ts.bt.BtCallingActivity");
                        return;
                    case 2:
                        if (show("com.ts.MainUI", "com.ts.bt.BtPhoneBookActivity") == -1) {
                            ShowBtWin("com.ts.MainUI", "com.ts.bt.BtConnectActivity", 2);
                            return;
                        }
                        return;
                    case 3:
                        if (show("com.ts.MainUI", "com.ts.bt.BtDialActivity") == -1) {
                            ShowBtWin("com.ts.MainUI", "com.ts.bt.BtConnectActivity", 1);
                            return;
                        }
                        return;
                    case 4:
                        if (show("com.ts.MainUI", "com.ts.bt.BtMusicActivity") == -1) {
                            ShowBtWin("com.ts.MainUI", "com.ts.bt.BtConnectActivity", 4);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            case 8:
                show("com.ts.MainUI", "com.ts.main.cmmb.CmmbMainActivity");
                return;
            case 9:
                show("com.ts.MainUI", "com.ts.main.avin2.AuxMainActivity");
                return;
            case 10:
                show("com.ts.MainUI", "com.ts.main.avin.AvinMainActivity");
                return;
            case 11:
                switch (nParat1) {
                    case 0:
                        show("com.ts.MainUI", "com.ts.set.SettingGerenalActivity");
                        return;
                    case 1:
                        show("com.ts.MainUI", "com.ts.set.SettingDisplayActivity");
                        return;
                    case 2:
                        show("com.ts.MainUI", "com.ts.set.SetVolumeActivity");
                        return;
                    case 3:
                        if (!getTopActivityName().equals("com.ts.set.dsp.SetDspMainActivity") && !getTopActivityName().equals("com.ts.set.SettingSoundActivity")) {
                            show("com.ts.MainUI", "com.ts.set.SettingSoundActivity");
                            return;
                        }
                        return;
                    case 4:
                        show("com.ts.MainUI", "com.ts.set.SettingVideoActivity");
                        return;
                    case 5:
                        show("com.ts.MainUI", "com.ts.set.SettingGpsActivity");
                        return;
                    case 6:
                        show("com.ts.MainUI", "com.ts.set.SettingBtActivity");
                        return;
                    case 7:
                        show("com.ts.MainUI", "com.ts.set.SettingWheelActivity");
                        return;
                    case 8:
                        show("com.ts.MainUI", "com.ts.set.SettingEquipmentActivity");
                        return;
                    case 98:
                        show("com.ts.MainUI", "com.ts.set.SetMainActivity");
                        return;
                    case 99:
                        show("com.ts.MainUI", "com.ts.set.SettingGpsPathActivity");
                        return;
                    default:
                        return;
                }
            case 13:
                show("com.ts.ipodplayer", "com.autochips.ipodplayer.ipodclient.ipodview.IPodActivity");
                Evc.GetInstance().evol_workmode_set(10);
                return;
            case 14:
                show("com.ts.MainUI", "com.ts.main.fcamera.FcameraMainActivity");
                return;
            case 15:
                show("com.ts.MainUI", "com.ts.factoryset.FsMainActivity");
                return;
            case 16:
                show("com.ts.MainUI", "com.ts.main.auth.AuthActivity");
                return;
            case 17:
                show("com.ts.logoset", "com.ts.logoset.LogoSetMainActivity");
                return;
            case 18:
                show("com.ts.MainUI", "com.ts.can.CanMcuUpdateActivity");
                return;
            case 19:
                show("com.ts.MainUI", "com.ts.main.CStudy.CStudyMainActivity");
                return;
            case 20:
                show("com.ts.tsfilecopy", "com.ts.tsfilecopy.FileCopyActivity");
                return;
            case 21:
                show("com.ts.dvdplayer", "com.ts.dvdplayer.DvdBoxActivity");
                return;
            case 22:
                show("com.example.tsspeekrecognize", "com.example.tsspeekrecognize.MainActivity");
                return;
            case 23:
                show("com.ts.MainUI", "com.ts.main.cmmb.AtvMainActivity");
                return;
            case 24:
                show("com.ts.MainUI", "com.qiner.appinfo.AppListActivity");
                return;
            default:
                return;
        }
    }
}
