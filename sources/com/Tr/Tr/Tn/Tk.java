package com.Tr.Tr.Tn;

import com.Tr.Tr.T;
import com.Tr.Tr.TE;
import com.Tr.Tr.Tr.Tr;
import com.Tr.Tr.Ty;
import java.util.Map;

/* compiled from: Proguard */
public final class Tk extends Tj {
    public Tr T(String contents, T format, int width, int height, Map<Ty, ?> hints) throws TE {
        if (format == T.CODE_39) {
            return super.T(contents, format, width, height, hints);
        }
        throw new IllegalArgumentException("Can only encode CODE_39, but got " + format);
    }

    public boolean[] T(String contents) {
        int length = contents.length();
        if (length > 80) {
            throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + length);
        }
        int[] widths = new int[9];
        int codeWidth = length + 25;
        for (int i = 0; i < length; i++) {
            int indexInString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(contents.charAt(i));
            if (indexInString < 0) {
                throw new IllegalArgumentException("Bad contents: " + contents);
            }
            T(T9.f273T[indexInString], widths);
            for (int i2 = 0; i2 < 9; i2++) {
                codeWidth += widths[i2];
            }
        }
        boolean[] result = new boolean[codeWidth];
        T(T9.Tr, widths);
        int pos = Tr(result, 0, widths, true);
        int[] narrowWhite = {1};
        int pos2 = pos + Tr(result, pos, narrowWhite, false);
        for (int i3 = 0; i3 < length; i3++) {
            T(T9.f273T["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(contents.charAt(i3))], widths);
            int pos3 = pos2 + Tr(result, pos2, widths, true);
            pos2 = pos3 + Tr(result, pos3, narrowWhite, false);
        }
        T(T9.Tr, widths);
        Tr(result, pos2, widths, true);
        return result;
    }

    private static void T(int a, int[] toReturn) {
        for (int i = 0; i < 9; i++) {
            toReturn[i] = (a & (1 << (8 - i))) == 0 ? 1 : 2;
        }
    }
}
