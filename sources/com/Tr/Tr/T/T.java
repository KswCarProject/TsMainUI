package com.Tr.Tr.T;

import com.Tr.Tr.TZ;
import com.Tr.Tr.Tr.Tr;
import com.Tr.Tr.Ty;
import java.nio.charset.Charset;
import java.util.Map;

/* compiled from: Proguard */
public final class T implements TZ {

    /* renamed from: T  reason: collision with root package name */
    private static final Charset f246T = Charset.forName("ISO-8859-1");

    public Tr T(String contents, com.Tr.Tr.T format, int width, int height, Map<Ty, ?> hints) {
        Charset charset = f246T;
        int eccPercent = 33;
        int layers = 0;
        if (hints != null) {
            if (hints.containsKey(Ty.CHARACTER_SET)) {
                charset = Charset.forName(hints.get(Ty.CHARACTER_SET).toString());
            }
            if (hints.containsKey(Ty.ERROR_CORRECTION)) {
                eccPercent = Integer.parseInt(hints.get(Ty.ERROR_CORRECTION).toString());
            }
            if (hints.containsKey(Ty.AZTEC_LAYERS)) {
                layers = Integer.parseInt(hints.get(Ty.AZTEC_LAYERS).toString());
            }
        }
        return T(contents, format, width, height, charset, eccPercent, layers);
    }

    private static Tr T(String contents, com.Tr.Tr.T format, int width, int height, Charset charset, int eccPercent, int layers) {
        if (format == com.Tr.Tr.T.AZTEC) {
            return T(com.Tr.Tr.T.T.Ty.T(contents.getBytes(charset), eccPercent, layers), width, height);
        }
        throw new IllegalArgumentException("Can only encode AZTEC, but got " + format);
    }

    private static Tr T(com.Tr.Tr.T.T.T code, int width, int height) {
        Tr input = code.T();
        if (input == null) {
            throw new IllegalStateException();
        }
        int inputWidth = input.Tr();
        int inputHeight = input.Ty();
        int outputWidth = Math.max(width, inputWidth);
        int outputHeight = Math.max(height, inputHeight);
        int multiple = Math.min(outputWidth / inputWidth, outputHeight / inputHeight);
        int leftPadding = (outputWidth - (inputWidth * multiple)) / 2;
        Tr output = new Tr(outputWidth, outputHeight);
        int inputY = 0;
        int outputY = (outputHeight - (inputHeight * multiple)) / 2;
        while (inputY < inputHeight) {
            int inputX = 0;
            int outputX = leftPadding;
            while (inputX < inputWidth) {
                if (input.T(inputX, inputY)) {
                    output.T(outputX, outputY, multiple, multiple);
                }
                inputX++;
                outputX += multiple;
            }
            inputY++;
            outputY += multiple;
        }
        return output;
    }
}
