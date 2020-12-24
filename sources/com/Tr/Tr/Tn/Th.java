package com.Tr.Tr.Tn;

import com.Tr.Tr.T;
import com.Tr.Tr.TE;
import com.Tr.Tr.Tr.Tr;
import com.Tr.Tr.Ty;
import java.util.Map;

/* compiled from: Proguard */
public final class Th extends Ts {
    public Tr T(String contents, T format, int width, int height, Map<Ty, ?> hints) throws TE {
        if (format == T.EAN_8) {
            return super.T(contents, format, width, height, hints);
        }
        throw new IllegalArgumentException("Can only encode EAN_8, but got " + format);
    }

    public boolean[] T(String contents) {
        if (contents.length() != 8) {
            throw new IllegalArgumentException("Requested contents should be 8 digits long, but got " + contents.length());
        }
        boolean[] result = new boolean[67];
        int pos = Tr(result, 0, TO.Tr, true) + 0;
        for (int i = 0; i <= 3; i++) {
            pos += Tr(result, pos, TO.T9[Integer.parseInt(contents.substring(i, i + 1))], false);
        }
        int pos2 = pos + Tr(result, pos, TO.Ty, false);
        for (int i2 = 4; i2 <= 7; i2++) {
            pos2 += Tr(result, pos2, TO.T9[Integer.parseInt(contents.substring(i2, i2 + 1))], true);
        }
        Tr(result, pos2, TO.Tr, true);
        return result;
    }
}
