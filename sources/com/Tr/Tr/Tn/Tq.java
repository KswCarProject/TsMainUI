package com.Tr.Tr.Tn;

import com.Tr.Tr.T;
import com.Tr.Tr.TE;
import com.Tr.Tr.Tr.Tr;
import com.Tr.Tr.Ty;
import java.util.Map;

/* compiled from: Proguard */
public final class Tq extends Tj {

    /* renamed from: T  reason: collision with root package name */
    private static final int[] f281T = {1, 1, 1, 1};
    private static final int[] Tr = {3, 1, 1};

    public Tr T(String contents, T format, int width, int height, Map<Ty, ?> hints) throws TE {
        if (format == T.ITF) {
            return super.T(contents, format, width, height, hints);
        }
        throw new IllegalArgumentException("Can only encode ITF, but got " + format);
    }

    public boolean[] T(String contents) {
        int length = contents.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("The length of the input should be even");
        } else if (length > 80) {
            throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + length);
        } else {
            boolean[] result = new boolean[((length * 9) + 9)];
            int pos = Tr(result, 0, f281T, true);
            for (int i = 0; i < length; i += 2) {
                int one = Character.digit(contents.charAt(i), 10);
                int two = Character.digit(contents.charAt(i + 1), 10);
                int[] encoding = new int[18];
                for (int j = 0; j < 5; j++) {
                    encoding[j * 2] = T6.f275T[one][j];
                    encoding[(j * 2) + 1] = T6.f275T[two][j];
                }
                pos += Tr(result, pos, encoding, true);
            }
            Tr(result, pos, Tr, true);
            return result;
        }
    }
}
