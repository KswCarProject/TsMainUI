package com.Tr.Tr.Tn;

import com.Tr.Tr.T;
import com.Tr.Tr.TE;
import com.Tr.Tr.Tr.Tr;
import com.Tr.Tr.Ty;
import java.util.Map;

/* compiled from: Proguard */
public final class Tu extends Ts {
    public Tr T(String contents, T format, int width, int height, Map<Ty, ?> hints) throws TE {
        if (format == T.UPC_E) {
            return super.T(contents, format, width, height, hints);
        }
        throw new IllegalArgumentException("Can only encode UPC_E, but got " + format);
    }

    public boolean[] T(String contents) {
        if (contents.length() != 8) {
            throw new IllegalArgumentException("Requested contents should be 8 digits long, but got " + contents.length());
        }
        int parities = TG.f278T[Integer.parseInt(contents.substring(7, 8))];
        boolean[] result = new boolean[51];
        int pos = Tr(result, 0, TO.Tr, true) + 0;
        for (int i = 1; i <= 6; i++) {
            int digit = Integer.parseInt(contents.substring(i, i + 1));
            if (((parities >> (6 - i)) & 1) == 1) {
                digit += 10;
            }
            pos += Tr(result, pos, TO.Tk[digit], false);
        }
        Tr(result, pos, TO.Tn, false);
        return result;
    }
}
