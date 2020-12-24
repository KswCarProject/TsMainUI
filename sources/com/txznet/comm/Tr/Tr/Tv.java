package com.txznet.comm.Tr.Tr;

/* compiled from: Proguard */
public class Tv {

    /* renamed from: T  reason: collision with root package name */
    private static T f404T;

    /* compiled from: Proguard */
    public static abstract class T {
        public void T(String jsonResult) {
        }

        public void T(int errorCode) {
        }
    }

    public static void T(String cmd, byte[] data) {
        if (cmd.equals("result") && f404T != null) {
            if (data != null) {
                f404T.T(new String(data));
            } else {
                return;
            }
        }
        if (cmd.equals("error") && data != null) {
            f404T.T(T(new String(data), -1));
        }
    }

    private static int T(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
