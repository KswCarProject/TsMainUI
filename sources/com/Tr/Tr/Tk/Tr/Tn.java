package com.Tr.Tr.Tk.Tr;

/* compiled from: Proguard */
final class Tn {
    static int T(Tr matrix) {
        return T(matrix, true) + T(matrix, false);
    }

    static int Tr(Tr matrix) {
        int penalty = 0;
        byte[][] array = matrix.Ty();
        int width = matrix.Tr();
        int height = matrix.T();
        for (int y = 0; y < height - 1; y++) {
            for (int x = 0; x < width - 1; x++) {
                byte value = array[y][x];
                if (value == array[y][x + 1] && value == array[y + 1][x] && value == array[y + 1][x + 1]) {
                    penalty++;
                }
            }
        }
        return penalty * 3;
    }

    static int Ty(Tr matrix) {
        int numPenalties = 0;
        byte[][] array = matrix.Ty();
        int width = matrix.Tr();
        int height = matrix.T();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                byte[] arrayY = array[y];
                if (x + 6 < width && arrayY[x] == 1 && arrayY[x + 1] == 0 && arrayY[x + 2] == 1 && arrayY[x + 3] == 1 && arrayY[x + 4] == 1 && arrayY[x + 5] == 0 && arrayY[x + 6] == 1 && (T(arrayY, x - 4, x) || T(arrayY, x + 7, x + 11))) {
                    numPenalties++;
                }
                if (y + 6 < height && array[y][x] == 1 && array[y + 1][x] == 0 && array[y + 2][x] == 1 && array[y + 3][x] == 1 && array[y + 4][x] == 1 && array[y + 5][x] == 0 && array[y + 6][x] == 1 && (T(array, x, y - 4, y) || T(array, x, y + 7, y + 11))) {
                    numPenalties++;
                }
            }
        }
        return numPenalties * 40;
    }

    private static boolean T(byte[] rowArray, int from, int to) {
        int from2 = Math.max(from, 0);
        int to2 = Math.min(to, rowArray.length);
        for (int i = from2; i < to2; i++) {
            if (rowArray[i] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean T(byte[][] array, int col, int from, int to) {
        int from2 = Math.max(from, 0);
        int to2 = Math.min(to, array.length);
        for (int i = from2; i < to2; i++) {
            if (array[i][col] == 1) {
                return false;
            }
        }
        return true;
    }

    static int Tn(Tr matrix) {
        int numDarkCells = 0;
        byte[][] array = matrix.Ty();
        int width = matrix.Tr();
        int height = matrix.T();
        for (int y = 0; y < height; y++) {
            byte[] arrayY = array[y];
            for (int x = 0; x < width; x++) {
                if (arrayY[x] == 1) {
                    numDarkCells++;
                }
            }
        }
        int numTotalCells = matrix.T() * matrix.Tr();
        return ((Math.abs((numDarkCells << 1) - numTotalCells) * 10) / numTotalCells) * 10;
    }

    static boolean T(int maskPattern, int x, int y) {
        int intermediate;
        switch (maskPattern) {
            case 0:
                intermediate = (y + x) & 1;
                break;
            case 1:
                intermediate = y & 1;
                break;
            case 2:
                intermediate = x % 3;
                break;
            case 3:
                intermediate = (y + x) % 3;
                break;
            case 4:
                intermediate = ((y / 2) + (x / 3)) & 1;
                break;
            case 5:
                int temp = y * x;
                intermediate = (temp & 1) + (temp % 3);
                break;
            case 6:
                int temp2 = y * x;
                intermediate = ((temp2 & 1) + (temp2 % 3)) & 1;
                break;
            case 7:
                intermediate = (((y * x) % 3) + ((y + x) & 1)) & 1;
                break;
            default:
                throw new IllegalArgumentException("Invalid mask pattern: " + maskPattern);
        }
        if (intermediate == 0) {
            return true;
        }
        return false;
    }

    private static int T(Tr matrix, boolean isHorizontal) {
        int penalty = 0;
        int iLimit = isHorizontal ? matrix.T() : matrix.Tr();
        int jLimit = isHorizontal ? matrix.Tr() : matrix.T();
        byte[][] array = matrix.Ty();
        for (int i = 0; i < iLimit; i++) {
            int numSameBitCells = 0;
            int prevBit = -1;
            for (int j = 0; j < jLimit; j++) {
                int bit = isHorizontal ? array[i][j] : array[j][i];
                if (bit == prevBit) {
                    numSameBitCells++;
                } else {
                    if (numSameBitCells >= 5) {
                        penalty += (numSameBitCells - 5) + 3;
                    }
                    numSameBitCells = 1;
                    prevBit = bit;
                }
            }
            if (numSameBitCells >= 5) {
                penalty += (numSameBitCells - 5) + 3;
            }
        }
        return penalty;
    }
}
