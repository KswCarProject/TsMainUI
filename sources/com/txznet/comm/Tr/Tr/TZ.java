package com.txznet.comm.Tr.Tr;

import android.util.Log;
import com.android.SdkConstants;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.Tr;

/* compiled from: Proguard */
public class TZ {

    /* renamed from: T  reason: collision with root package name */
    private static T f393T = null;

    /* compiled from: Proguard */
    public static abstract class T {
        public abstract void T();

        public abstract void T(int i);

        public abstract void T(int i, String str, String str2);

        public abstract void T(byte[] bArr);

        public abstract void Tn();

        public abstract void Tn(int i);

        public abstract void Tr();

        public abstract void Tr(int i);

        public abstract void Ty();

        public abstract void Ty(int i);
    }

    public static void T() {
        Tn.Tr().T("com.txznet.txz", "comm.record.cancel", (byte[]) null, (Tn.Tr) null);
    }

    public static void T(String event, byte[] data) {
        if (f393T == null) {
            Log.i("RecorderUtil", "recordCallBack == null");
        } else if (event.equals("end")) {
            f393T.T(((Integer) new Tr(new String(data)).T("length", Integer.class)).intValue());
        } else if (event.equals("parse")) {
            Tr json = new Tr(new String(data));
            f393T.T(((Integer) json.T("length", Integer.class)).intValue(), (String) json.T(SdkConstants.ATTR_TEXT, String.class), (String) json.T("url", String.class));
        } else if (event.equals("cancel")) {
            f393T.Tn();
        } else if (event.equals("begin")) {
            f393T.T();
        } else if (event.equals("mute")) {
            int time = 0;
            try {
                time = Integer.parseInt(new String(data));
            } catch (NumberFormatException e) {
                Log.e("RecorderUtil", "convert string to int error");
            }
            f393T.Tn(time);
        } else if (event.equals("mutetimeout")) {
            f393T.Ty();
        } else if (event.equals("speechtimeout")) {
            f393T.Tr();
        } else if (event.equals("volume")) {
            int vol = 0;
            try {
                vol = Integer.parseInt(new String(data));
            } catch (NumberFormatException e2) {
                Log.e("RecorderUtil", "convert string to int error");
            }
            f393T.Ty(vol);
        } else if (event.equals("error")) {
            int err = 0;
            try {
                err = Integer.parseInt(new String(data));
            } catch (NumberFormatException e3) {
                Log.e("RecorderUtil", "convert string to int error");
            }
            f393T.Tr(err);
        } else if (event.equals("mp3buf")) {
            f393T.T(data);
        }
    }
}
