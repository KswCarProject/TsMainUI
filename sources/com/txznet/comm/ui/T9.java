package com.txznet.comm.ui;

import android.text.TextUtils;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.ui.Tk.T;
import com.txznet.sdk.music.MusicInvokeConstants;

/* compiled from: Proguard */
public class T9 {

    /* renamed from: T  reason: collision with root package name */
    private static T9 f504T = new T9();
    private String Tr = null;
    private int Ty = 0;

    private T9() {
    }

    public static T9 T() {
        return f504T;
    }

    public void Tr() {
        Tn.T("UIVersionManager init");
        try {
            this.Tr = T.Tr().TZ(MusicInvokeConstants.KEY_PUSH_VERSION);
            Tn.T("UIVersionManager " + this.Tr);
        } catch (Exception e) {
        }
        if (TextUtils.isEmpty(this.Tr)) {
            this.Tr = "0.9";
        }
        this.Ty = T(this.Tr);
    }

    public int T(String version) {
        if (TextUtils.isEmpty(version)) {
            return 9;
        }
        String[] subStrings = version.split("\\.");
        int weight = 1;
        int versionCode = 0;
        for (int i = subStrings.length - 1; i >= 0; i--) {
            try {
                versionCode += Integer.parseInt(subStrings[i]) * weight;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            weight *= 100;
        }
        return versionCode;
    }
}
