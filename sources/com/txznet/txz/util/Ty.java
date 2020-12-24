package com.txznet.txz.util;

/* compiled from: Proguard */
public class Ty {
    public static byte[] T(long l) {
        return T(l, new byte[8]);
    }

    public static byte[] T(long l, byte[] b) {
        b[7] = (byte) ((int) (l >>> 56));
        b[6] = (byte) ((int) (l >>> 48));
        b[5] = (byte) ((int) (l >>> 40));
        b[4] = (byte) ((int) (l >>> 32));
        b[3] = (byte) ((int) (l >>> 24));
        b[2] = (byte) ((int) (l >>> 16));
        b[1] = (byte) ((int) (l >>> 8));
        b[0] = (byte) ((int) l);
        return b;
    }
}
