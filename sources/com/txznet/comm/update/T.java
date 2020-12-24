package com.txznet.comm.update;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.ui.dialog2.WinConfirm;
import com.txznet.comm.ui.dialog2.WinMessageBox;
import com.txznet.comm.ui.dialog2.WinNotice;
import java.io.File;

/* compiled from: Proguard */
public class T {
    public static void T(Context context) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences(context.getApplicationInfo().packageName + ".ApkLoader", 0);
        String oldApk = mSharedPreferences.getString("apk", "");
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        File f = new File(oldApk);
        if (f.exists()) {
            f.delete();
        }
        editor.remove("apk");
        editor.remove("size");
        editor.remove("time");
        editor.commit();
    }

    public static void T(final long time) {
        com.txznet.T.T.Ty(new Runnable() {

            /* renamed from: T  reason: collision with root package name */
            long f659T = 0;

            public void run() {
                Tn.T("hint upgrde reboot");
                Toast.makeText(com.txznet.comm.Tr.T.Tr(), "语音助手已升级成功\n建议您手动重启设备", 1).show();
                this.f659T += 3000;
                if (time <= 0 || this.f659T < time) {
                    com.txznet.T.T.Ty(this, 3000);
                }
            }
        }, 0);
    }

    public static void T(String newApk) {
        SharedPreferences mSharedPreferences = com.txznet.comm.Tr.T.Tr().getSharedPreferences(com.txznet.comm.Tr.T.Tr().getApplicationInfo().packageName + ".ApkLoader", 0);
        File fApk = new File(newApk);
        long size = fApk.length();
        long time = fApk.lastModified();
        String oldApk = mSharedPreferences.getString("apk", "");
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (!oldApk.equals(newApk)) {
            File f = new File(oldApk);
            if (f.exists()) {
                f.delete();
            }
        }
        editor.remove("launchTimes");
        editor.putLong("size", size);
        editor.putLong("time", time);
        editor.putString("apk", newApk);
        editor.commit();
        fApk.setLastModified(time);
    }

    /* renamed from: com.txznet.comm.update.T$2  reason: invalid class name */
    /* compiled from: Proguard */
    static class AnonymousClass2 implements Runnable {

        /* renamed from: T  reason: collision with root package name */
        final /* synthetic */ boolean f660T;
        final /* synthetic */ String Tr;
        final /* synthetic */ String Ty;

        public void run() {
            WinMessageBox dlg;
            if (this.f660T) {
                T.T(this.Tr);
                WinNotice.T buildData = new WinNotice.T();
                buildData.TZ(this.Ty);
                buildData.Ty("重启");
                buildData.T(true);
                dlg = new UpdateCenter$2$1(this, buildData);
            } else {
                WinConfirm.T buildData2 = new WinConfirm.T();
                buildData2.TZ(this.Ty);
                buildData2.Tr("重启");
                buildData2.T(true);
                dlg = new UpdateCenter$2$2(this, buildData2);
            }
            dlg.show();
        }
    }
}
