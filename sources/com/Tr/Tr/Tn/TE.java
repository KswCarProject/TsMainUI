package com.Tr.Tr.Tn;

import com.Tr.Tr.T;
import com.Tr.Tr.Tr.Tr;
import com.Tr.Tr.Ty;
import java.util.Map;

/* compiled from: Proguard */
public class TE extends Tj {
    public Tr T(String contents, T format, int width, int height, Map<Ty, ?> hints) throws com.Tr.Tr.TE {
        if (format == T.CODE_93) {
            return super.T(contents, format, width, height, hints);
        }
        throw new IllegalArgumentException("Can only encode CODE_93, but got " + format);
    }

    public boolean[] T(String contents) {
        int length = contents.length();
        if (length > 80) {
            throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + length);
        }
        int[] widths = new int[9];
        boolean[] result = new boolean[(((contents.length() + 2 + 2) * 9) + 1)];
        T(TZ.f276T[47], widths);
        int pos = T(result, 0, widths, true);
        for (int i = 0; i < length; i++) {
            T(TZ.f276T["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(contents.charAt(i))], widths);
            pos += T(result, pos, widths, true);
        }
        int check1 = T(contents, 20);
        T(TZ.f276T[check1], widths);
        int pos2 = pos + T(result, pos, widths, true);
        T(TZ.f276T[T(contents + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".charAt(check1), 15)], widths);
        int pos3 = pos2 + T(result, pos2, widths, true);
        T(TZ.f276T[47], widths);
        result[pos3 + T(result, pos3, widths, true)] = true;
        return result;
    }

    private static void T(int a, int[] toReturn) {
        int i;
        for (int i2 = 0; i2 < 9; i2++) {
            if ((a & (1 << (8 - i2))) == 0) {
                i = 0;
            } else {
                i = 1;
            }
            toReturn[i2] = i;
        }
    }

    protected static int T(boolean[] target, int pos, int[] pattern, boolean startColor) {
        boolean z;
        int length = pattern.length;
        int i = 0;
        int pos2 = pos;
        while (i < length) {
            int pos3 = pos2 + 1;
            if (pattern[i] != 0) {
                z = true;
            } else {
                z = false;
            }
            target[pos2] = z;
            i++;
            pos2 = pos3;
        }
        return 9;
    }

    private static int T(String contents, int maxWeight) {
        int weight = 1;
        int total = 0;
        for (int i = contents.length() - 1; i >= 0; i--) {
            total += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(contents.charAt(i)) * weight;
            weight++;
            if (weight > maxWeight) {
                weight = 1;
            }
        }
        return total % 47;
    }
}
