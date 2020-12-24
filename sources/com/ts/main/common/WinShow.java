package com.ts.main.common;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.bt.BtExe;
import com.ts.can.CanIF;
import com.ts.main.txz.AmapAuto;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;
import java.util.List;

public class WinShow {
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
    public static Context mContext = null;
    private static int nDelayTime = 300;
    public static int nOldMode = 255;

    public static int show(String sPackage, String sActivity) {
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
        List<ActivityManager.RunningTaskInfo> taskInfos = ((ActivityManager) mContext.getSystemService("activity")).getRunningTasks(1);
        if (taskInfos.isEmpty()) {
            return "";
        }
        ComponentName componentName = taskInfos.get(0).baseActivity;
        return taskInfos.get(0).topActivity.getPackageName();
    }

    public static String getTopActivityName() {
        List<ActivityManager.RunningTaskInfo> taskInfos = ((ActivityManager) mContext.getSystemService("activity")).getRunningTasks(1);
        if (!taskInfos.isEmpty()) {
            return taskInfos.get(0).topActivity.getClassName();
        }
        return "";
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
        if (Mcu.GetDisc() == 2) {
            return true;
        }
        return false;
    }

    private static boolean IsModeValid(int nMode) {
        if (nMode != 12 && FtSet.IsIconExist(nMode) == 0) {
            return false;
        }
        switch (nMode) {
            case 1:
            case 4:
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
                return true;
            case 5:
                if (BtExe.getBtInstance().isConnected()) {
                    return true;
                }
                Log.i(TAG, "isConnected = " + BtExe.getBtInstance().isConnected());
                return false;
            case 8:
            case 10:
            case 11:
            case 13:
            case 14:
                if (!MainSet.IsXPH5() || MainSet.IsXPH5_HZ()) {
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
    }

    public static void DealModeKey() {
        Log.i(TAG, "nOldMode = " + nOldMode);
        Log.i(TAG, "Evc.GetInstance().Evol.workmode = " + Iop.GetWorkMode());
        Log.i(TAG, "nDelayTime = " + nDelayTime);
        if (nOldMode == Iop.GetWorkMode() || nDelayTime >= 280) {
            Log.i(TAG, "DealModeKey = " + nOldMode + "but workmode have not change");
            return;
        }
        nDelayTime = 300;
        nOldMode = Iop.GetWorkMode();
        int n = Iop.GetWorkMode();
        for (int i = 0; i <= 15; i++) {
            n++;
            if (n > 15) {
                n = 1;
            }
            if (IsModeValid(n)) {
                Log.i(TAG, "DealModeKey = " + n + "nOldMode==" + nOldMode);
                TsEnterMode(n);
                return;
            }
        }
    }

    public static int TsEnterMode(int nMode) {
        if (MainSet.IsGLSXVer().booleanValue() && nMode != 0 && MainSet.GetInstance().IsHaveApk("com.glsx.ddbox")) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.glsx.ddbox", "com.glsx.launcher.service.LauncherService"));
            mContext.startService(intent);
        }
        switch (nMode) {
            case 0:
                Evc.GetInstance().evol_workmode_set(0);
                if (!MainSet.IsGLSXVer().booleanValue() || !MainSet.IsGLlastmem() || !MainSet.GetInstance().IsHaveApk(AmapAuto.GetInstance().ReadMem())) {
                    return 1;
                }
                MainSet.GetInstance().openApplication(mContext, AmapAuto.GetInstance().ReadMem());
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
                return 1;
            case 6:
                if (118 == CanJni.GetCanFsTp()) {
                    show("com.ts.MainUI", "com.ts.main.dvr.DvrMainActivity");
                    return 1;
                } else if (242 == CanJni.GetCanFsTp()) {
                    CanIF.GotoExdMode();
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
        show("com.ts.dvdplayer", "com.ts.dvdplayer.USBActivity");
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
                if (!MainSet.GetInstance().IsHaveApk("com.mxtech.videoplayer.pro") || mContext.getResources().getString(R.string.custom_num_show).equals("MCX")) {
                    show("com.ts.dvdplayer", "com.ts.dvdplayer.USBActivity");
                    return;
                } else {
                    MainSet.GetInstance().openApplication(mContext, "com.mxtech.videoplayer.pro");
                    return;
                }
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
                        show("com.ts.MainUI", "com.ts.set.SettingSoundActivity");
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
                show("com.ts.ipodplayer", "com.ts.ipodplayer.activity.IpodMainActivity");
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
                if (MainSet.GetScreenType() != 3 && MainSet.GetScreenType() != 3) {
                    show("com.ts.logoset", "com.ts.logoset.LogoSetMainActivity");
                    return;
                }
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
