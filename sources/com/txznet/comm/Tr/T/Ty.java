package com.txznet.comm.Tr.T;

import android.os.Environment;
import android.support.v4.view.accessibility.AccessibilityEventCompat;

/* compiled from: Proguard */
public class Ty {

    /* renamed from: T  reason: collision with root package name */
    public static final String f361T = (Environment.getExternalStorageDirectory() + "/txz/udp_port.txz");
    private static Ty Tr = new Ty();

    private Ty() {
    }

    public static Ty T() {
        return Tr;
    }

    public int Tr() {
        return 262120;
    }

    public int Ty() {
        return AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START;
    }

    public int Tn() {
        return 24;
    }

    public int T(String packageName) {
        if ("com.txznet.music".equals(packageName)) {
            return 20100;
        }
        if ("com.txznet.webchat".equals(packageName)) {
            return 20200;
        }
        return 20000;
    }

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public String f362T;
        public int Tr;

        public T(String host, int port) {
            this.f362T = host;
            this.Tr = port;
        }
    }
}
