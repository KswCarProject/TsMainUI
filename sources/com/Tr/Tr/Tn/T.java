package com.Tr.Tr.Tn;

/* compiled from: Proguard */
public final class T extends TF {

    /* renamed from: T  reason: collision with root package name */
    static final char[] f273T = "0123456789-$:/.+ABCD".toCharArray();
    static final int[] Tr = {3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14};
    private static final char[] Ty = {'A', 'B', 'C', 'D'};

    static boolean T(char[] array, char key) {
        if (array == null) {
            return false;
        }
        for (char c : array) {
            if (c == key) {
                return true;
            }
        }
        return false;
    }
}
