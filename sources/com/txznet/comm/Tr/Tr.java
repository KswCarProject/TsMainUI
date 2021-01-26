package com.txznet.comm.Tr;

import android.os.Binder;
import android.os.Process;
import android.text.TextUtils;
import com.ts.bt.ContactInfo;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.txz.util.TE;
import java.util.HashSet;

/* compiled from: Proguard */
public class Tr {

    /* renamed from: T  reason: collision with root package name */
    private static int[] f384T = new int[20];
    private static boolean Tr = false;
    private static HashSet<String> Ty;

    public static boolean T(int uid, String packageName) {
        if (Process.myUid() == uid) {
            return true;
        }
        boolean max = true;
        int i = 0;
        while (true) {
            if (i < f384T.length) {
                if (f384T[i] == uid) {
                    return true;
                }
                if (f384T[i] == 0) {
                    max = false;
                    break;
                }
                i++;
            }
        }
        try {
            if (Ty != null && !Ty.contains(packageName)) {
                return false;
            }
            if (uid != T.Tr().getPackageManager().getApplicationInfo(packageName, 0).uid) {
                return false;
            }
            synchronized (f384T) {
                if (max) {
                    for (int i2 = f384T.length - 1; i2 >= 0; i2--) {
                        f384T[i2] = 0;
                    }
                }
                int i3 = 0;
                while (true) {
                    if (i3 >= f384T.length || f384T[i3] == uid) {
                        break;
                    } else if (f384T[i3] == 0) {
                        f384T[i3] = uid;
                        break;
                    } else {
                        i3++;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void T() {
        if (!Tr) {
            synchronized (Tr.class) {
                if (!Tr) {
                    String whiteList = TE.T("invokeWhiteList");
                    if (!TextUtils.isEmpty(whiteList)) {
                        Ty = new HashSet<>();
                        for (String name : whiteList.split(ContactInfo.COMBINATION_SEPERATOR)) {
                            Ty.add(name);
                        }
                    }
                    Tr = true;
                }
            }
        }
    }

    public static boolean T(String packageName, String command, byte[] data) {
        T();
        boolean ret = T(Binder.getCallingUid(), packageName);
        if (!ret) {
            Tn.Ty("not allow to invoke [" + command + "] from " + packageName + "/" + Binder.getCallingPid() + "/" + Binder.getCallingUid());
        }
        return ret;
    }
}
