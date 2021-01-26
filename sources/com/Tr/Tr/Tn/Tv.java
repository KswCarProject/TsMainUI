package com.Tr.Tr.Tn;

import com.Tr.Tr.T;
import com.Tr.Tr.TE;
import com.Tr.Tr.Tn;
import com.Tr.Tr.Tr.Tr;
import com.Tr.Tr.Ty;
import java.util.Map;

/* compiled from: Proguard */
public final class Tv extends Ts {
    public Tr T(String contents, T format, int width, int height, Map<Ty, ?> hints) throws TE {
        if (format == T.EAN_13) {
            return super.T(contents, format, width, height, hints);
        }
        throw new IllegalArgumentException("Can only encode EAN_13, but got " + format);
    }

    public boolean[] T(String contents) {
        if (contents.length() != 13) {
            throw new IllegalArgumentException("Requested contents should be 13 digits long, but got " + contents.length());
        }
        try {
            if (!TO.T(contents)) {
                throw new IllegalArgumentException("Contents do not pass checksum");
            }
            int parities = T5.f274T[Integer.parseInt(contents.substring(0, 1))];
            boolean[] result = new boolean[95];
            int pos = Tr(result, 0, TO.Tr, true) + 0;
            for (int i = 1; i <= 6; i++) {
                int digit = Integer.parseInt(contents.substring(i, i + 1));
                if (((parities >> (6 - i)) & 1) == 1) {
                    digit += 10;
                }
                pos += Tr(result, pos, TO.Tk[digit], false);
            }
            int pos2 = pos + Tr(result, pos, TO.Ty, false);
            for (int i2 = 7; i2 <= 12; i2++) {
                pos2 += Tr(result, pos2, TO.T9[Integer.parseInt(contents.substring(i2, i2 + 1))], true);
            }
            Tr(result, pos2, TO.Tr, true);
            return result;
        } catch (Tn e) {
            throw new IllegalArgumentException("Illegal contents");
        }
    }
}
