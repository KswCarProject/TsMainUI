package com.lgb.pymatch;

public class PyMatch {
    public static native int GetMatchVal(String str);

    public static native void SetSubstr(String str);

    static {
        System.loadLibrary("pymatch");
    }
}
