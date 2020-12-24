package com.Tr.Tr.Tk;

import com.Tr.Tr.TE;
import com.Tr.Tr.TZ;
import com.Tr.Tr.Tk.Tr.Tk;
import com.Tr.Tr.Tr.Tr;
import com.Tr.Tr.Ty;
import java.util.Map;

/* compiled from: Proguard */
public final class T implements TZ {
    public Tr T(String contents, com.Tr.Tr.T format, int width, int height, Map<Ty, ?> hints) throws TE {
        if (contents.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (format != com.Tr.Tr.T.QR_CODE) {
            throw new IllegalArgumentException("Can only encode QR_CODE, but got " + format);
        } else if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + width + 'x' + height);
        } else {
            com.Tr.Tr.Tk.T.T errorCorrectionLevel = com.Tr.Tr.Tk.T.T.L;
            int quietZone = 4;
            if (hints != null) {
                if (hints.containsKey(Ty.ERROR_CORRECTION)) {
                    errorCorrectionLevel = com.Tr.Tr.Tk.T.T.valueOf(hints.get(Ty.ERROR_CORRECTION).toString());
                }
                if (hints.containsKey(Ty.MARGIN)) {
                    quietZone = Integer.parseInt(hints.get(Ty.MARGIN).toString());
                }
            }
            return T(com.Tr.Tr.Tk.Tr.Ty.T(contents, errorCorrectionLevel, hints), width, height, quietZone);
        }
    }

    private static Tr T(Tk code, int width, int height, int quietZone) {
        com.Tr.Tr.Tk.Tr.Tr input = code.T();
        if (input == null) {
            throw new IllegalStateException();
        }
        int inputWidth = input.Tr();
        int inputHeight = input.T();
        int qrWidth = inputWidth + (quietZone << 1);
        int qrHeight = inputHeight + (quietZone << 1);
        int outputWidth = Math.max(width, qrWidth);
        int outputHeight = Math.max(height, qrHeight);
        int multiple = Math.min(outputWidth / qrWidth, outputHeight / qrHeight);
        int leftPadding = (outputWidth - (inputWidth * multiple)) / 2;
        Tr output = new Tr(outputWidth, outputHeight);
        int inputY = 0;
        int outputY = (outputHeight - (inputHeight * multiple)) / 2;
        while (inputY < inputHeight) {
            int inputX = 0;
            int outputX = leftPadding;
            while (inputX < inputWidth) {
                if (input.T(inputX, inputY) == 1) {
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
