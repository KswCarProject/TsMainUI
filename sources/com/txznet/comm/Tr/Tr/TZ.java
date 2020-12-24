package com.txznet.comm.Tr.Tr;

import android.util.Log;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.Tr;

/* compiled from: Proguard */
public class TZ {

    /* renamed from: T  reason: collision with root package name */
    private static T f389T = null;

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
        if (f389T == null) {
            Log.i("RecorderUtil", "recordCallBack == null");
        } else if (event.equals("end")) {
            f389T.T(((Integer) new Tr(new String(data)).T("length", Integer.class)).intValue());
        } else if (event.equals("parse")) {
            Tr json = new Tr(new String(data));
            f389T.T(((Integer) json.T("length", Integer.class)).intValue(), (String) json.T("text", String.class), (String) json.T("url", String.class));
        } else if (event.equals("cancel")) {
            f389T.Tn();
        } else if (event.equals("begin")) {
            f389T.T();
        } else if (event.equals("mute")) {
            int time = 0;
            try {
                time = Integer.parseInt(new String(data));
            } catch (NumberFormatException e) {
                Log.e("RecorderUtil", "convert string to int error");
            }
            f389T.Tn(time);
        } else if (event.equals("mutetimeout")) {
            f389T.Ty();
        } else if (event.equals("speechtimeout")) {
            f389T.Tr();
        } else if (event.equals("volume")) {
            int vol = 0;
            try {
                vol = Integer.parseInt(new String(data));
            } catch (NumberFormatException e2) {
                Log.e("RecorderUtil", "convert string to int error");
            }
            f389T.Ty(vol);
        } else if (event.equals("error")) {
            int err = 0;
            try {
                err = Integer.parseInt(new String(data));
            } catch (NumberFormatException e3) {
                Log.e("RecorderUtil", "convert string to int error");
            }
            f389T.Tr(err);
        } else if (event.equals("mp3buf")) {
            f389T.T(data);
        }
    }
}
