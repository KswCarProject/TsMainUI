package com.Tr.Tr.Tn;

import com.Tr.Tr.Tn;

/* compiled from: Proguard */
public abstract class TO extends TF {
    static final int[][] T9 = {new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};
    static final int[][] Tk = new int[20][];
    static final int[] Tn = {1, 1, 1, 1, 1, 1};
    static final int[] Tr = {1, 1, 1};
    static final int[] Ty = {1, 1, 1, 1, 1};

    static {
        System.arraycopy(T9, 0, Tk, 0, 10);
        for (int i = 10; i < 20; i++) {
            int[] widths = T9[i - 10];
            int[] reversedWidths = new int[widths.length];
            for (int j = 0; j < widths.length; j++) {
                reversedWidths[j] = widths[(widths.length - j) - 1];
            }
            Tk[i] = reversedWidths;
        }
    }

    static boolean T(CharSequence s) throws Tn {
        int length = s.length();
        if (length == 0) {
            return false;
        }
        int sum = 0;
        for (int i = length - 2; i >= 0; i -= 2) {
            int digit = s.charAt(i) - '0';
            if (digit < 0 || digit > 9) {
                throw Tn.T();
            }
            sum += digit;
        }
        int sum2 = sum * 3;
        for (int i2 = length - 1; i2 >= 0; i2 -= 2) {
            int digit2 = s.charAt(i2) - '0';
            if (digit2 < 0 || digit2 > 9) {
                throw Tn.T();
            }
            sum2 += digit2;
        }
        if (sum2 % 10 == 0) {
            return true;
        }
        return false;
    }
}
