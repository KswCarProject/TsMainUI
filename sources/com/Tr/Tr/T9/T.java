package com.Tr.Tr.T9;

import com.Tr.Tr.T9.T.T9;
import com.Tr.Tr.T9.T.Tn;
import com.Tr.Tr.TE;
import com.Tr.Tr.TZ;
import com.Tr.Tr.Tr.Tr;
import com.Tr.Tr.Ty;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Map;

/* compiled from: Proguard */
public final class T implements TZ {
    public Tr T(String contents, com.Tr.Tr.T format, int width, int height, Map<Ty, ?> hints) throws TE {
        if (format != com.Tr.Tr.T.PDF_417) {
            throw new IllegalArgumentException("Can only encode PDF_417, but got " + format);
        }
        T9 encoder = new T9();
        int margin = 30;
        int errorCorrectionLevel = 2;
        if (hints != null) {
            if (hints.containsKey(Ty.PDF417_COMPACT)) {
                encoder.T(Boolean.valueOf(hints.get(Ty.PDF417_COMPACT).toString()).booleanValue());
            }
            if (hints.containsKey(Ty.PDF417_COMPACTION)) {
                encoder.T(com.Tr.Tr.T9.T.Ty.valueOf(hints.get(Ty.PDF417_COMPACTION).toString()));
            }
            if (hints.containsKey(Ty.PDF417_DIMENSIONS)) {
                Tn dimensions = (Tn) hints.get(Ty.PDF417_DIMENSIONS);
                encoder.T(dimensions.Tr(), dimensions.T(), dimensions.Tn(), dimensions.Ty());
            }
            if (hints.containsKey(Ty.MARGIN)) {
                margin = Integer.parseInt(hints.get(Ty.MARGIN).toString());
            }
            if (hints.containsKey(Ty.ERROR_CORRECTION)) {
                errorCorrectionLevel = Integer.parseInt(hints.get(Ty.ERROR_CORRECTION).toString());
            }
            if (hints.containsKey(Ty.CHARACTER_SET)) {
                encoder.T(Charset.forName(hints.get(Ty.CHARACTER_SET).toString()));
            }
        }
        return T(encoder, contents, errorCorrectionLevel, width, height, margin);
    }

    private static Tr T(T9 encoder, String contents, int errorCorrectionLevel, int width, int height, int margin) throws TE {
        boolean z;
        int scale;
        encoder.T(contents, errorCorrectionLevel);
        byte[][] originalScale = encoder.T().T(1, 4);
        boolean rotated = false;
        boolean z2 = height > width;
        if (originalScale[0].length < originalScale.length) {
            z = true;
        } else {
            z = false;
        }
        if (z2 ^ z) {
            originalScale = T(originalScale);
            rotated = true;
        }
        int scaleX = width / originalScale[0].length;
        int scaleY = height / originalScale.length;
        if (scaleX < scaleY) {
            scale = scaleX;
        } else {
            scale = scaleY;
        }
        if (scale <= 1) {
            return T(originalScale, margin);
        }
        byte[][] scaledMatrix = encoder.T().T(scale, scale << 2);
        if (rotated) {
            scaledMatrix = T(scaledMatrix);
        }
        return T(scaledMatrix, margin);
    }

    private static Tr T(byte[][] input, int margin) {
        Tr output = new Tr(input[0].length + (margin * 2), input.length + (margin * 2));
        output.T();
        int y = 0;
        int yOutput = (output.Ty() - margin) - 1;
        while (y < input.length) {
            for (int x = 0; x < input[0].length; x++) {
                if (input[y][x] == 1) {
                    output.Tr(x + margin, yOutput);
                }
            }
            y++;
            yOutput--;
        }
        return output;
    }

    private static byte[][] T(byte[][] bitarray) {
        byte[][] temp = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{bitarray[0].length, bitarray.length});
        for (int ii = 0; ii < bitarray.length; ii++) {
            int inverseii = (bitarray.length - ii) - 1;
            for (int jj = 0; jj < bitarray[0].length; jj++) {
                temp[jj][inverseii] = bitarray[ii][jj];
            }
        }
        return temp;
    }
}
