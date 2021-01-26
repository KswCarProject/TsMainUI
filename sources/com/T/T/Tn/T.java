package com.T.T.Tn;

import java.util.Arrays;

/* compiled from: Proguard */
public class T {

    /* renamed from: T  reason: collision with root package name */
    public static final char[] f132T = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    public static final int[] Tr = new int[256];

    static {
        Arrays.fill(Tr, -1);
        int iS = f132T.length;
        for (int i = 0; i < iS; i++) {
            Tr[f132T[i]] = i;
        }
        Tr[61] = 0;
    }

    public static final byte[] T(String chars, int offset, int charsLen) {
        int sepCnt;
        int sIx;
        if (charsLen == 0) {
            return new byte[0];
        }
        int sIx2 = offset;
        int eIx = (offset + charsLen) - 1;
        while (sIx2 < eIx && Tr[chars.charAt(sIx2)] < 0) {
            sIx2++;
        }
        while (eIx > 0 && Tr[chars.charAt(eIx)] < 0) {
            eIx--;
        }
        int pad = chars.charAt(eIx) == '=' ? chars.charAt(eIx + -1) == '=' ? 2 : 1 : 0;
        int cCnt = (eIx - sIx2) + 1;
        if (charsLen > 76) {
            sepCnt = (chars.charAt(76) == 13 ? cCnt / 78 : 0) << 1;
        } else {
            sepCnt = 0;
        }
        int len = (((cCnt - sepCnt) * 6) >> 3) - pad;
        byte[] bytes = new byte[len];
        int cc = 0;
        int eLen = (len / 3) * 3;
        int d = 0;
        int sIx3 = sIx2;
        while (d < eLen) {
            int sIx4 = sIx3 + 1;
            int sIx5 = sIx4 + 1;
            int sIx6 = sIx5 + 1;
            int sIx7 = sIx6 + 1;
            int i = (Tr[chars.charAt(sIx3)] << 18) | (Tr[chars.charAt(sIx4)] << 12) | (Tr[chars.charAt(sIx5)] << 6) | Tr[chars.charAt(sIx6)];
            int d2 = d + 1;
            bytes[d] = (byte) (i >> 16);
            int d3 = d2 + 1;
            bytes[d2] = (byte) (i >> 8);
            int d4 = d3 + 1;
            bytes[d3] = (byte) i;
            if (sepCnt <= 0 || (cc = cc + 1) != 19) {
                sIx = sIx7;
            } else {
                sIx = sIx7 + 2;
                cc = 0;
            }
            d = d4;
            sIx3 = sIx;
        }
        if (d < len) {
            int i2 = 0;
            int j = 0;
            while (sIx3 <= eIx - pad) {
                i2 |= Tr[chars.charAt(sIx3)] << (18 - (j * 6));
                j++;
                sIx3++;
            }
            int r = 16;
            while (d < len) {
                bytes[d] = (byte) (i2 >> r);
                r -= 8;
                d++;
            }
        }
        int i3 = d;
        int i4 = sIx3;
        return bytes;
    }
}
