package com.Tr.T.T;

import java.io.IOException;

/* compiled from: Proguard */
public final class TZ {

    /* renamed from: T  reason: collision with root package name */
    public static final int[] f242T = new int[0];
    public static final boolean[] T9 = new boolean[0];
    public static final byte[] TE = new byte[0];
    public static final byte[][] TZ = new byte[0][];
    public static final String[] Tk = new String[0];
    public static final double[] Tn = new double[0];
    public static final long[] Tr = new long[0];
    public static final float[] Ty = new float[0];

    static int T(int i) {
        return i & 7;
    }

    public static int Tr(int i) {
        return i >>> 3;
    }

    static int T(int i, int i2) {
        return (i << 3) | i2;
    }

    public static boolean T(T t, int i) throws IOException {
        return t.Tr(i);
    }

    public static final int Tr(T t, int i) throws IOException {
        int i2 = 1;
        int TB = t.TB();
        t.Tr(i);
        while (t.TF() > 0 && t.T() == i) {
            t.Tr(i);
            i2++;
        }
        t.T9(TB);
        return i2;
    }
}
