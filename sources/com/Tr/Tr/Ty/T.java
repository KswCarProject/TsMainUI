package com.Tr.Tr.Ty;

import com.Tr.Tr.TZ;
import com.Tr.Tr.Tr.Tr;
import com.Tr.Tr.Ty;
import com.Tr.Tr.Ty.T.T5;
import com.Tr.Tr.Ty.T.T6;
import com.Tr.Tr.Ty.T.T9;
import com.Tr.Tr.Ty.T.Th;
import com.Tr.Tr.Ty.T.Tv;
import java.util.Map;

/* compiled from: Proguard */
public final class T implements TZ {
    public Tr T(String contents, com.Tr.Tr.T format, int width, int height, Map<Ty, ?> hints) {
        if (contents.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (format != com.Tr.Tr.T.DATA_MATRIX) {
            throw new IllegalArgumentException("Can only encode DATA_MATRIX, but got " + format);
        } else if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + width + 'x' + height);
        } else {
            T6 shape = T6.FORCE_NONE;
            com.Tr.Tr.Tr minSize = null;
            com.Tr.Tr.Tr maxSize = null;
            if (hints != null) {
                T6 requestedShape = (T6) hints.get(Ty.DATA_MATRIX_SHAPE);
                if (requestedShape != null) {
                    shape = requestedShape;
                }
                com.Tr.Tr.Tr requestedMinSize = (com.Tr.Tr.Tr) hints.get(Ty.MIN_SIZE);
                if (requestedMinSize != null) {
                    minSize = requestedMinSize;
                }
                com.Tr.Tr.Tr requestedMaxSize = (com.Tr.Tr.Tr) hints.get(Ty.MAX_SIZE);
                if (requestedMaxSize != null) {
                    maxSize = requestedMaxSize;
                }
            }
            String encoded = Tv.T(contents, shape, minSize, maxSize);
            Th symbolInfo = Th.T(encoded.length(), shape, minSize, maxSize, true);
            T9 placement = new T9(T5.T(encoded, symbolInfo), symbolInfo.Tr(), symbolInfo.Ty());
            placement.T();
            return T(placement, symbolInfo);
        }
    }

    private static Tr T(T9 placement, Th symbolInfo) {
        boolean z;
        boolean z2;
        int symbolWidth = symbolInfo.Tr();
        int symbolHeight = symbolInfo.Ty();
        com.Tr.Tr.Tk.Tr.Tr matrix = new com.Tr.Tr.Tk.Tr.Tr(symbolInfo.Tn(), symbolInfo.T9());
        int matrixY = 0;
        for (int y = 0; y < symbolHeight; y++) {
            if (y % symbolInfo.Ty == 0) {
                int matrixX = 0;
                for (int x = 0; x < symbolInfo.Tn(); x++) {
                    if (x % 2 == 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    matrix.T(matrixX, matrixY, z2);
                    matrixX++;
                }
                matrixY++;
            }
            int matrixX2 = 0;
            for (int x2 = 0; x2 < symbolWidth; x2++) {
                if (x2 % symbolInfo.Tr == 0) {
                    matrix.T(matrixX2, matrixY, true);
                    matrixX2++;
                }
                matrix.T(matrixX2, matrixY, placement.T(x2, y));
                matrixX2++;
                if (x2 % symbolInfo.Tr == symbolInfo.Tr - 1) {
                    if (y % 2 == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    matrix.T(matrixX2, matrixY, z);
                    matrixX2++;
                }
            }
            matrixY++;
            if (y % symbolInfo.Ty == symbolInfo.Ty - 1) {
                int matrixX3 = 0;
                for (int x3 = 0; x3 < symbolInfo.Tn(); x3++) {
                    matrix.T(matrixX3, matrixY, true);
                    matrixX3++;
                }
                matrixY++;
            }
        }
        return T(matrix);
    }

    private static Tr T(com.Tr.Tr.Tk.Tr.Tr matrix) {
        int matrixWidgth = matrix.Tr();
        int matrixHeight = matrix.T();
        Tr output = new Tr(matrixWidgth, matrixHeight);
        output.T();
        for (int i = 0; i < matrixWidgth; i++) {
            for (int j = 0; j < matrixHeight; j++) {
                if (matrix.T(i, j) == 1) {
                    output.Tr(i, j);
                }
            }
        }
        return output;
    }
}
