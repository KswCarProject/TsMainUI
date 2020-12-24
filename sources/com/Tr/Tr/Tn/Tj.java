package com.Tr.Tr.Tn;

import com.Tr.Tr.T;
import com.Tr.Tr.TE;
import com.Tr.Tr.TZ;
import com.Tr.Tr.Tr.Tr;
import com.Tr.Tr.Ty;
import java.util.Map;

/* compiled from: Proguard */
public abstract class Tj implements TZ {
    public abstract boolean[] T(String str);

    public Tr T(String contents, T format, int width, int height, Map<Ty, ?> hints) throws TE {
        if (contents.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Negative size is not allowed. Input: " + width + 'x' + height);
        } else {
            int sidesMargin = T();
            if (hints != null && hints.containsKey(Ty.MARGIN)) {
                sidesMargin = Integer.parseInt(hints.get(Ty.MARGIN).toString());
            }
            return T(T(contents), width, height, sidesMargin);
        }
    }

    private static Tr T(boolean[] code, int width, int height, int sidesMargin) {
        int inputWidth = code.length;
        int fullWidth = inputWidth + sidesMargin;
        int outputWidth = Math.max(width, fullWidth);
        int outputHeight = Math.max(1, height);
        int multiple = outputWidth / fullWidth;
        Tr output = new Tr(outputWidth, outputHeight);
        int inputX = 0;
        int outputX = (outputWidth - (inputWidth * multiple)) / 2;
        while (inputX < inputWidth) {
            if (code[inputX]) {
                output.T(outputX, 0, multiple, outputHeight);
            }
            inputX++;
            outputX += multiple;
        }
        return output;
    }

    protected static int Tr(boolean[] target, int pos, int[] pattern, boolean startColor) {
        boolean color = startColor;
        int numAdded = 0;
        int length = pattern.length;
        int i = 0;
        while (i < length) {
            int len = pattern[i];
            int j = 0;
            int pos2 = pos;
            while (j < len) {
                target[pos2] = color;
                j++;
                pos2++;
            }
            numAdded += len;
            color = !color;
            i++;
            pos = pos2;
        }
        return numAdded;
    }

    public int T() {
        return 10;
    }
}
