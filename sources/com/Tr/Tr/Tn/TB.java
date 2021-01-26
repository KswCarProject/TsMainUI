package com.Tr.Tr.Tn;

import com.Tr.Tr.T;
import com.Tr.Tr.TE;
import com.Tr.Tr.TZ;
import com.Tr.Tr.Tr.Tr;
import com.Tr.Tr.Ty;
import java.util.Map;

/* compiled from: Proguard */
public final class TB implements TZ {

    /* renamed from: T  reason: collision with root package name */
    private final Tv f277T = new Tv();

    public Tr T(String contents, T format, int width, int height, Map<Ty, ?> hints) throws TE {
        if (format == T.UPC_A) {
            return this.f277T.T(T(contents), T.EAN_13, width, height, hints);
        }
        throw new IllegalArgumentException("Can only encode UPC-A, but got " + format);
    }

    private static String T(String contents) {
        int length = contents.length();
        if (length == 11) {
            int sum = 0;
            for (int i = 0; i < 11; i++) {
                sum += (i % 2 == 0 ? 3 : 1) * (contents.charAt(i) - '0');
            }
            contents = contents + ((1000 - sum) % 10);
        } else if (length != 12) {
            throw new IllegalArgumentException("Requested contents should be 11 or 12 digits long, but got " + contents.length());
        }
        return "0" + contents;
    }
}
