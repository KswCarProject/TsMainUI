package com.Tr.Tr.T.T;

import com.Tr.Tr.Tr.T;
import com.Tr.Tr.Tr.Tr;

/* compiled from: Proguard */
public final class Ty {

    /* renamed from: T  reason: collision with root package name */
    private static final int[] f249T = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    public static T T(byte[] data, int minECCPercent, int userSpecifiedLayers) {
        int wordSize;
        int totalBitsInLayer;
        T stuffedBits;
        int layers;
        boolean compact;
        int matrixSize;
        T bits = new Tn(data).T();
        int eccBits = ((bits.T() * minECCPercent) / 100) + 11;
        int totalSizeBits = bits.T() + eccBits;
        if (userSpecifiedLayers != 0) {
            compact = userSpecifiedLayers < 0;
            layers = Math.abs(userSpecifiedLayers);
            if (layers > (compact ? 4 : 32)) {
                throw new IllegalArgumentException(String.format("Illegal value %s for layers", new Object[]{Integer.valueOf(userSpecifiedLayers)}));
            }
            totalBitsInLayer = T(layers, compact);
            wordSize = f249T[layers];
            int usableBitsInLayers = totalBitsInLayer - (totalBitsInLayer % wordSize);
            stuffedBits = T(bits, wordSize);
            if (stuffedBits.T() + eccBits > usableBitsInLayers) {
                throw new IllegalArgumentException("Data to large for user specified layer");
            } else if (compact && stuffedBits.T() > (wordSize << 6)) {
                throw new IllegalArgumentException("Data to large for user specified layer");
            }
        } else {
            wordSize = 0;
            stuffedBits = null;
            int i = 0;
            while (i <= 32) {
                compact = i <= 3;
                if (compact) {
                    layers = i + 1;
                } else {
                    layers = i;
                }
                totalBitsInLayer = T(layers, compact);
                if (totalSizeBits <= totalBitsInLayer) {
                    if (wordSize != f249T[layers]) {
                        wordSize = f249T[layers];
                        stuffedBits = T(bits, wordSize);
                    }
                    int usableBitsInLayers2 = totalBitsInLayer - (totalBitsInLayer % wordSize);
                    if ((!compact || stuffedBits.T() <= (wordSize << 6)) && stuffedBits.T() + eccBits <= usableBitsInLayers2) {
                    }
                }
                i++;
            }
            throw new IllegalArgumentException("Data too large for an Aztec code");
        }
        T messageBits = T(stuffedBits, totalBitsInLayer, wordSize);
        int messageSizeInWords = stuffedBits.T() / wordSize;
        T modeMessage = T(compact, layers, messageSizeInWords);
        int baseMatrixSize = (compact ? 11 : 14) + (layers << 2);
        int[] alignmentMap = new int[baseMatrixSize];
        if (compact) {
            matrixSize = baseMatrixSize;
            for (int i2 = 0; i2 < alignmentMap.length; i2++) {
                alignmentMap[i2] = i2;
            }
        } else {
            matrixSize = baseMatrixSize + 1 + ((((baseMatrixSize / 2) - 1) / 15) * 2);
            int origCenter = baseMatrixSize / 2;
            int center = matrixSize / 2;
            for (int i3 = 0; i3 < origCenter; i3++) {
                int newOffset = i3 + (i3 / 15);
                alignmentMap[(origCenter - i3) - 1] = (center - newOffset) - 1;
                alignmentMap[origCenter + i3] = center + newOffset + 1;
            }
        }
        Tr matrix = new Tr(matrixSize);
        int rowOffset = 0;
        for (int i4 = 0; i4 < layers; i4++) {
            int rowSize = ((layers - i4) << 2) + (compact ? 9 : 12);
            for (int j = 0; j < rowSize; j++) {
                int columnOffset = j << 1;
                for (int k = 0; k < 2; k++) {
                    if (messageBits.T(rowOffset + columnOffset + k)) {
                        matrix.Tr(alignmentMap[(i4 << 1) + k], alignmentMap[(i4 << 1) + j]);
                    }
                    if (messageBits.T((rowSize << 1) + rowOffset + columnOffset + k)) {
                        matrix.Tr(alignmentMap[(i4 << 1) + j], alignmentMap[((baseMatrixSize - 1) - (i4 << 1)) - k]);
                    }
                    if (messageBits.T((rowSize << 2) + rowOffset + columnOffset + k)) {
                        matrix.Tr(alignmentMap[((baseMatrixSize - 1) - (i4 << 1)) - k], alignmentMap[((baseMatrixSize - 1) - (i4 << 1)) - j]);
                    }
                    if (messageBits.T((rowSize * 6) + rowOffset + columnOffset + k)) {
                        matrix.Tr(alignmentMap[((baseMatrixSize - 1) - (i4 << 1)) - j], alignmentMap[(i4 << 1) + k]);
                    }
                }
            }
            rowOffset += rowSize << 3;
        }
        T(matrix, compact, matrixSize, modeMessage);
        if (compact) {
            T(matrix, matrixSize / 2, 5);
        } else {
            T(matrix, matrixSize / 2, 7);
            int i5 = 0;
            int j2 = 0;
            while (i5 < (baseMatrixSize / 2) - 1) {
                for (int k2 = (matrixSize / 2) & 1; k2 < matrixSize; k2 += 2) {
                    matrix.Tr((matrixSize / 2) - j2, k2);
                    matrix.Tr((matrixSize / 2) + j2, k2);
                    matrix.Tr(k2, (matrixSize / 2) - j2);
                    matrix.Tr(k2, (matrixSize / 2) + j2);
                }
                i5 += 15;
                j2 += 16;
            }
        }
        T aztec = new T();
        aztec.T(compact);
        aztec.T(matrixSize);
        aztec.Tr(layers);
        aztec.Ty(messageSizeInWords);
        aztec.T(matrix);
        return aztec;
    }

    private static void T(Tr matrix, int center, int size) {
        for (int i = 0; i < size; i += 2) {
            for (int j = center - i; j <= center + i; j++) {
                matrix.Tr(j, center - i);
                matrix.Tr(j, center + i);
                matrix.Tr(center - i, j);
                matrix.Tr(center + i, j);
            }
        }
        matrix.Tr(center - size, center - size);
        matrix.Tr((center - size) + 1, center - size);
        matrix.Tr(center - size, (center - size) + 1);
        matrix.Tr(center + size, center - size);
        matrix.Tr(center + size, (center - size) + 1);
        matrix.Tr(center + size, (center + size) - 1);
    }

    static T T(boolean compact, int layers, int messageSizeInWords) {
        T modeMessage = new T();
        if (compact) {
            modeMessage.T(layers - 1, 2);
            modeMessage.T(messageSizeInWords - 1, 6);
            return T(modeMessage, 28, 4);
        }
        modeMessage.T(layers - 1, 5);
        modeMessage.T(messageSizeInWords - 1, 11);
        return T(modeMessage, 40, 4);
    }

    private static void T(Tr matrix, boolean compact, int matrixSize, T modeMessage) {
        int center = matrixSize / 2;
        if (compact) {
            for (int i = 0; i < 7; i++) {
                int offset = (center - 3) + i;
                if (modeMessage.T(i)) {
                    matrix.Tr(offset, center - 5);
                }
                if (modeMessage.T(i + 7)) {
                    matrix.Tr(center + 5, offset);
                }
                if (modeMessage.T(20 - i)) {
                    matrix.Tr(offset, center + 5);
                }
                if (modeMessage.T(27 - i)) {
                    matrix.Tr(center - 5, offset);
                }
            }
            return;
        }
        for (int i2 = 0; i2 < 10; i2++) {
            int offset2 = (center - 5) + i2 + (i2 / 5);
            if (modeMessage.T(i2)) {
                matrix.Tr(offset2, center - 7);
            }
            if (modeMessage.T(i2 + 10)) {
                matrix.Tr(center + 7, offset2);
            }
            if (modeMessage.T(29 - i2)) {
                matrix.Tr(offset2, center + 7);
            }
            if (modeMessage.T(39 - i2)) {
                matrix.Tr(center - 7, offset2);
            }
        }
    }

    private static T T(T bitArray, int totalBits, int wordSize) {
        int messageSizeInWords = bitArray.T() / wordSize;
        com.Tr.Tr.Tr.T.Ty rs = new com.Tr.Tr.Tr.T.Ty(T(wordSize));
        int totalWords = totalBits / wordSize;
        int[] messageWords = Tr(bitArray, wordSize, totalWords);
        rs.T(messageWords, totalWords - messageSizeInWords);
        T messageBits = new T();
        messageBits.T(0, totalBits % wordSize);
        for (int messageWord : messageWords) {
            messageBits.T(messageWord, wordSize);
        }
        return messageBits;
    }

    private static int[] Tr(T stuffedBits, int wordSize, int totalWords) {
        int[] message = new int[totalWords];
        int n = stuffedBits.T() / wordSize;
        for (int i = 0; i < n; i++) {
            int value = 0;
            for (int j = 0; j < wordSize; j++) {
                value |= stuffedBits.T((i * wordSize) + j) ? 1 << ((wordSize - j) - 1) : 0;
            }
            message[i] = value;
        }
        return message;
    }

    private static com.Tr.Tr.Tr.T.T T(int wordSize) {
        switch (wordSize) {
            case 4:
                return com.Tr.Tr.Tr.T.T.Tn;
            case 6:
                return com.Tr.Tr.Tr.T.T.Ty;
            case 8:
                return com.Tr.Tr.Tr.T.T.TZ;
            case 10:
                return com.Tr.Tr.Tr.T.T.Tr;
            case 12:
                return com.Tr.Tr.Tr.T.T.f283T;
            default:
                throw new IllegalArgumentException("Unsupported word size " + wordSize);
        }
    }

    static T T(T bits, int wordSize) {
        T out = new T();
        int n = bits.T();
        int mask = (1 << wordSize) - 2;
        int i = 0;
        while (i < n) {
            int word = 0;
            for (int j = 0; j < wordSize; j++) {
                if (i + j >= n || bits.T(i + j)) {
                    word |= 1 << ((wordSize - 1) - j);
                }
            }
            if ((word & mask) == mask) {
                out.T(word & mask, wordSize);
                i--;
            } else if ((word & mask) == 0) {
                out.T(word | 1, wordSize);
                i--;
            } else {
                out.T(word, wordSize);
            }
            i += wordSize;
        }
        return out;
    }

    private static int T(int layers, boolean compact) {
        return ((compact ? 88 : 112) + (layers << 4)) * layers;
    }
}
