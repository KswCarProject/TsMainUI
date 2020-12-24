package com.txznet.comm.Ty;

import android.os.Environment;
import com.txznet.comm.Tr.Tr.Tn;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: Proguard */
public class T {

    /* renamed from: T  reason: collision with root package name */
    public static final String f408T = (Environment.getExternalStorageDirectory() + "/txz/");
    public static String T5 = null;
    public static final String T6 = (f408T + "apk/TXZSetting.apk");
    public static final String T9 = (f408T + "theme.cfg");
    public static final String TB = (Tj + File.separator + "help.txt");
    public static String TE;
    public static final String TF = (Tq + File.separator + "help.txt");
    public static final String TG = ("/system/app/" + File.separator + "help" + File.separator + "help");
    public static final String TK = (Te + File.separator + "help.zip");
    public static final String TN = ("/etc/txz/" + File.separator + "help" + File.separator + "help");
    public static final String TO = (Te + File.separator + "helpbak");
    public static final String TZ = f408T;
    public static final String Te = (f408T + "help");
    public static final String Th = (f408T + "tts_role/");
    public static final String Tj = (Te + File.separator + "help");
    public static final String Tk = f408T;
    public static String Tn = null;
    public static final String Tq = (Te + File.separator + "helptmp");
    public static final String Tr = (f408T + "resource/ResHolder.apk");
    public static final String Ts = ("/system/txz/" + File.separator + "help" + File.separator + "help");
    public static final String Tt = ("/vendor/txz/" + File.separator + "help" + File.separator + "help");
    public static final String Tu = ("/custom/etc/" + File.separator + "help" + File.separator + "help");
    public static String Tv = null;
    public static String Ty;

    public static String T() {
        if (new File(Tr).exists()) {
            return Tr;
        }
        if (Tn != null) {
            if (new File(Tn).exists()) {
                return Tn;
            }
            Tn.Ty("resApkPath:" + Tn + " is set but file not exist!");
        }
        List<String> userFilePaths = new ArrayList<>();
        userFilePaths.add("/etc/txz/resource/ResHolder.apk");
        userFilePaths.add("/system/txz/resource/ResHolder.apk");
        userFilePaths.add("/system/app/resource/ResHolder.apk");
        userFilePaths.add("/custom/etc/resource/ResHolder.apk");
        userFilePaths.add("/vendor/txz/resource/ResHolder.apk");
        for (String userFilePath : userFilePaths) {
            if (new File(userFilePath).exists()) {
                return userFilePath;
            }
        }
        Ty = com.txznet.comm.Tr.T.Tr().getApplicationInfo().dataDir + "/data/ResHolder.apk";
        return Ty;
    }
}
