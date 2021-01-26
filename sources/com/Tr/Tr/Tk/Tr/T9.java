package com.Tr.Tr.Tk.Tr;

import com.Tr.Tr.TE;
import com.Tr.Tr.Tk.T.Ty;
import com.Tr.Tr.Tr.T;
import com.lgb.canmodule.Can;

/* compiled from: Proguard */
final class T9 {

    /* renamed from: T  reason: collision with root package name */
    private static final int[][] f268T = {new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};
    private static final int[][] Tn = {new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};
    private static final int[][] Tr = {new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};
    private static final int[][] Ty = {new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, 122, -1}, new int[]{6, 30, 54, 78, 102, 126, -1}, new int[]{6, 26, 52, 78, 104, 130, -1}, new int[]{6, 30, 56, 82, 108, 134, -1}, new int[]{6, 34, 60, 86, 112, 138, -1}, new int[]{6, 30, 58, 86, 114, 142, -1}, new int[]{6, 34, 62, 90, 118, 146, -1}, new int[]{6, 30, 54, 78, 102, 126, 150}, new int[]{6, 24, 50, 76, 102, 128, Can.CAN_CC_WC}, new int[]{6, 28, 54, 80, 106, 132, Can.CAN_BJ20_WC}, new int[]{6, 32, 58, 84, 110, 136, 162}, new int[]{6, 26, 54, 82, 110, 138, 166}, new int[]{6, 30, 58, 86, 114, 142, 170}};

    static void T(Tr matrix) {
        matrix.T((byte) -1);
    }

    static void T(T dataBits, com.Tr.Tr.Tk.T.T ecLevel, Ty version, int maskPattern, Tr matrix) throws TE {
        T(matrix);
        T(version, matrix);
        T(ecLevel, maskPattern, matrix);
        Tr(version, matrix);
        T(dataBits, maskPattern, matrix);
    }

    static void T(Ty version, Tr matrix) throws TE {
        Tn(matrix);
        Ty(matrix);
        Ty(version, matrix);
        Tr(matrix);
    }

    static void T(com.Tr.Tr.Tk.T.T ecLevel, int maskPattern, Tr matrix) throws TE {
        T typeInfoBits = new T();
        T(ecLevel, maskPattern, typeInfoBits);
        for (int i = 0; i < typeInfoBits.T(); i++) {
            boolean bit = typeInfoBits.T((typeInfoBits.T() - 1) - i);
            matrix.T(Tn[i][0], Tn[i][1], bit);
            if (i < 8) {
                matrix.T((matrix.Tr() - i) - 1, 8, bit);
            } else {
                matrix.T(8, (matrix.T() - 7) + (i - 8), bit);
            }
        }
    }

    static void Tr(Ty version, Tr matrix) throws TE {
        if (version.T() >= 7) {
            T versionInfoBits = new T();
            T(version, versionInfoBits);
            int bitIndex = 17;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    boolean bit = versionInfoBits.T(bitIndex);
                    bitIndex--;
                    matrix.T(i, (matrix.T() - 11) + j, bit);
                    matrix.T((matrix.T() - 11) + j, i, bit);
                }
            }
        }
    }

    static void T(T dataBits, int maskPattern, Tr matrix) throws TE {
        boolean bit;
        int bitIndex = 0;
        int direction = -1;
        int x = matrix.Tr() - 1;
        int y = matrix.T() - 1;
        while (x > 0) {
            if (x == 6) {
                x--;
            }
            while (y >= 0 && y < matrix.T()) {
                for (int i = 0; i < 2; i++) {
                    int xx = x - i;
                    if (Tr((int) matrix.T(xx, y))) {
                        if (bitIndex < dataBits.T()) {
                            bit = dataBits.T(bitIndex);
                            bitIndex++;
                        } else {
                            bit = false;
                        }
                        if (maskPattern != -1 && Tn.T(maskPattern, xx, y)) {
                            bit = !bit;
                        }
                        matrix.T(xx, y, bit);
                    }
                }
                y += direction;
            }
            direction = -direction;
            y += direction;
            x -= 2;
        }
        if (bitIndex != dataBits.T()) {
            throw new TE("Not all bits consumed: " + bitIndex + '/' + dataBits.T());
        }
    }

    static int T(int value) {
        return 32 - Integer.numberOfLeadingZeros(value);
    }

    static int T(int value, int poly) {
        if (poly == 0) {
            throw new IllegalArgumentException("0 polynomial");
        }
        int msbSetInPoly = T(poly);
        int value2 = value << (msbSetInPoly - 1);
        while (T(value2) >= msbSetInPoly) {
            value2 ^= poly << (T(value2) - msbSetInPoly);
        }
        return value2;
    }

    static void T(com.Tr.Tr.Tk.T.T ecLevel, int maskPattern, T bits) throws TE {
        if (!Tk.Tr(maskPattern)) {
            throw new TE("Invalid mask pattern");
        }
        int typeInfo = (ecLevel.T() << 3) | maskPattern;
        bits.T(typeInfo, 5);
        bits.T(T(typeInfo, 1335), 10);
        T maskBits = new T();
        maskBits.T(21522, 15);
        bits.Tr(maskBits);
        if (bits.T() != 15) {
            throw new TE("should not happen but we got: " + bits.T());
        }
    }

    static void T(Ty version, T bits) throws TE {
        bits.T(version.T(), 6);
        bits.T(T(version.T(), 7973), 12);
        if (bits.T() != 18) {
            throw new TE("should not happen but we got: " + bits.T());
        }
    }

    private static boolean Tr(int value) {
        return value == -1;
    }

    private static void Tr(Tr matrix) {
        for (int i = 8; i < matrix.Tr() - 8; i++) {
            int bit = (i + 1) % 2;
            if (Tr((int) matrix.T(i, 6))) {
                matrix.T(i, 6, bit);
            }
            if (Tr((int) matrix.T(6, i))) {
                matrix.T(6, i, bit);
            }
        }
    }

    private static void Ty(Tr matrix) throws TE {
        if (matrix.T(8, matrix.T() - 8) == 0) {
            throw new TE();
        }
        matrix.T(8, matrix.T() - 8, 1);
    }

    private static void T(int xStart, int yStart, Tr matrix) throws TE {
        for (int x = 0; x < 8; x++) {
            if (!Tr((int) matrix.T(xStart + x, yStart))) {
                throw new TE();
            }
            matrix.T(xStart + x, yStart, 0);
        }
    }

    private static void Tr(int xStart, int yStart, Tr matrix) throws TE {
        for (int y = 0; y < 7; y++) {
            if (!Tr((int) matrix.T(xStart, yStart + y))) {
                throw new TE();
            }
            matrix.T(xStart, yStart + y, 0);
        }
    }

    private static void Ty(int xStart, int yStart, Tr matrix) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                matrix.T(xStart + x, yStart + y, Tr[y][x]);
            }
        }
    }

    private static void Tn(int xStart, int yStart, Tr matrix) {
        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {
                matrix.T(xStart + x, yStart + y, f268T[y][x]);
            }
        }
    }

    private static void Tn(Tr matrix) throws TE {
        int pdpWidth = f268T[0].length;
        Tn(0, 0, matrix);
        Tn(matrix.Tr() - pdpWidth, 0, matrix);
        Tn(0, matrix.Tr() - pdpWidth, matrix);
        T(0, 7, matrix);
        T(matrix.Tr() - 8, 7, matrix);
        T(0, matrix.Tr() - 8, matrix);
        Tr(7, 0, matrix);
        Tr((matrix.T() - 7) - 1, 0, matrix);
        Tr(7, matrix.T() - 7, matrix);
    }

    private static void Ty(Ty version, Tr matrix) {
        if (version.T() >= 2) {
            int index = version.T() - 1;
            int[] coordinates = Ty[index];
            int numCoordinates = Ty[index].length;
            for (int i = 0; i < numCoordinates; i++) {
                for (int j = 0; j < numCoordinates; j++) {
                    int y = coordinates[i];
                    int x = coordinates[j];
                    if (!(x == -1 || y == -1 || !Tr((int) matrix.T(x, y)))) {
                        Ty(x - 2, y - 2, matrix);
                    }
                }
            }
        }
    }
}
