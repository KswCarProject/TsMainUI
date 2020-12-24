package com.autochips.android.backcar;

import android.util.Log;

public class Backcar_GPIO {
    public static final int BACKCAR_ERROR = 2;
    public static final int BACKCAR_START = 1;
    public static final int BACKCAR_STOP = 0;
    private static final int DIRECTION = 5;
    private static final int GPIO_HIGH = 1;
    private static final int GPIO_LOW = 0;
    private static final int INPUT_VALUE = 2;
    private static final int MODE = 0;
    private static final String TAG = "BackcarGPIO";
    private static int mLastGPIOValue = 0;
    private static final String path = "/sys/devices/platform/caci/cmd";

    private static native void notify_Arm2_Android_System_Ready();

    static {
        System.loadLibrary("backcar_jni");
    }

    public Backcar_GPIO() {
        Log.i(TAG, "construct");
    }

    public static void takeoverfromArm2() {
        Log.i(TAG, "taveoverfromArm2 enter");
        notify_Arm2_Android_System_Ready();
        Log.i(TAG, "taveoverfromArm2 leave");
    }
}
