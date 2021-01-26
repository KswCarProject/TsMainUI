package com.Tr.Tr.Tk.Tr;

import com.Tr.Tr.TE;
import com.Tr.Tr.Tk.T.T;
import com.Tr.Tr.Tk.T.Tr;
import com.lgb.canmodule.Can;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

/* compiled from: Proguard */
public final class Ty {

    /* renamed from: T  reason: collision with root package name */
    private static final int[] f271T = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    private static int T(Tr matrix) {
        return Tn.T(matrix) + Tn.Tr(matrix) + Tn.Ty(matrix) + Tn.Tn(matrix);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00e6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.Tr.Tr.Tk.Tr.Tk T(java.lang.String r17, com.Tr.Tr.Tk.T.T r18, java.util.Map<com.Tr.Tr.Ty, ?> r19) throws com.Tr.Tr.TE {
        /*
            java.lang.String r5 = "ISO-8859-1"
            if (r19 == 0) goto L_0x001b
            com.Tr.Tr.Ty r15 = com.Tr.Tr.Ty.CHARACTER_SET
            r0 = r19
            boolean r15 = r0.containsKey(r15)
            if (r15 == 0) goto L_0x001b
            com.Tr.Tr.Ty r15 = com.Tr.Tr.Ty.CHARACTER_SET
            r0 = r19
            java.lang.Object r15 = r0.get(r15)
            java.lang.String r5 = r15.toString()
        L_0x001b:
            r0 = r17
            com.Tr.Tr.Tk.T.Tr r11 = T((java.lang.String) r0, (java.lang.String) r5)
            com.Tr.Tr.Tr.T r8 = new com.Tr.Tr.Tr.T
            r8.<init>()
            com.Tr.Tr.Tk.T.Tr r15 = com.Tr.Tr.Tk.T.Tr.BYTE
            if (r11 != r15) goto L_0x003c
            java.lang.String r15 = "ISO-8859-1"
            boolean r15 = r15.equals(r5)
            if (r15 != 0) goto L_0x003c
            com.Tr.Tr.Tr.Ty r4 = com.Tr.Tr.Tr.Ty.T(r5)
            if (r4 == 0) goto L_0x003c
            T((com.Tr.Tr.Tr.Ty) r4, (com.Tr.Tr.Tr.T) r8)
        L_0x003c:
            T((com.Tr.Tr.Tk.T.Tr) r11, (com.Tr.Tr.Tr.T) r8)
            com.Tr.Tr.Tr.T r1 = new com.Tr.Tr.Tr.T
            r1.<init>()
            r0 = r17
            T((java.lang.String) r0, (com.Tr.Tr.Tk.T.Tr) r11, (com.Tr.Tr.Tr.T) r1, (java.lang.String) r5)
            if (r19 == 0) goto L_0x007e
            com.Tr.Tr.Ty r15 = com.Tr.Tr.Ty.QR_VERSION
            r0 = r19
            boolean r15 = r0.containsKey(r15)
            if (r15 == 0) goto L_0x007e
            com.Tr.Tr.Ty r15 = com.Tr.Tr.Ty.QR_VERSION
            r0 = r19
            java.lang.Object r15 = r0.get(r15)
            java.lang.String r15 = r15.toString()
            int r15 = java.lang.Integer.parseInt(r15)
            com.Tr.Tr.Tk.T.Ty r14 = com.Tr.Tr.Tk.T.Ty.T((int) r15)
            int r15 = T((com.Tr.Tr.Tk.T.Tr) r11, (com.Tr.Tr.Tr.T) r8, (com.Tr.Tr.Tr.T) r1, (com.Tr.Tr.Tk.T.Ty) r14)
            r0 = r18
            boolean r15 = T((int) r15, (com.Tr.Tr.Tk.T.Ty) r14, (com.Tr.Tr.Tk.T.T) r0)
            if (r15 != 0) goto L_0x0084
            com.Tr.Tr.TE r15 = new com.Tr.Tr.TE
            java.lang.String r16 = "Data too big for requested version"
            r15.<init>((java.lang.String) r16)
            throw r15
        L_0x007e:
            r0 = r18
            com.Tr.Tr.Tk.T.Ty r14 = T((com.Tr.Tr.Tk.T.T) r0, (com.Tr.Tr.Tk.T.Tr) r11, (com.Tr.Tr.Tr.T) r8, (com.Tr.Tr.Tr.T) r1)
        L_0x0084:
            com.Tr.Tr.Tr.T r7 = new com.Tr.Tr.Tr.T
            r7.<init>()
            r7.T((com.Tr.Tr.Tr.T) r8)
            com.Tr.Tr.Tk.T.Tr r15 = com.Tr.Tr.Tk.T.Tr.BYTE
            if (r11 != r15) goto L_0x00e6
            int r15 = r1.Tr()
        L_0x0094:
            T((int) r15, (com.Tr.Tr.Tk.T.Ty) r14, (com.Tr.Tr.Tk.T.Tr) r11, (com.Tr.Tr.Tr.T) r7)
            r7.T((com.Tr.Tr.Tr.T) r1)
            r0 = r18
            com.Tr.Tr.Tk.T.Ty$Tr r3 = r14.T((com.Tr.Tr.Tk.T.T) r0)
            int r15 = r14.Tr()
            int r16 = r3.Ty()
            int r12 = r15 - r16
            T((int) r12, (com.Tr.Tr.Tr.T) r7)
            int r15 = r14.Tr()
            int r16 = r3.Tr()
            r0 = r16
            com.Tr.Tr.Tr.T r6 = T((com.Tr.Tr.Tr.T) r7, (int) r15, (int) r12, (int) r0)
            com.Tr.Tr.Tk.Tr.Tk r13 = new com.Tr.Tr.Tk.Tr.Tk
            r13.<init>()
            r0 = r18
            r13.T((com.Tr.Tr.Tk.T.T) r0)
            r13.T((com.Tr.Tr.Tk.T.Tr) r11)
            r13.T((com.Tr.Tr.Tk.T.Ty) r14)
            int r2 = r14.Ty()
            com.Tr.Tr.Tk.Tr.Tr r10 = new com.Tr.Tr.Tk.Tr.Tr
            r10.<init>(r2, r2)
            r0 = r18
            int r9 = T((com.Tr.Tr.Tr.T) r6, (com.Tr.Tr.Tk.T.T) r0, (com.Tr.Tr.Tk.T.Ty) r14, (com.Tr.Tr.Tk.Tr.Tr) r10)
            r13.T((int) r9)
            r0 = r18
            com.Tr.Tr.Tk.Tr.T9.T(r6, r0, r14, r9, r10)
            r13.T((com.Tr.Tr.Tk.Tr.Tr) r10)
            return r13
        L_0x00e6:
            int r15 = r17.length()
            goto L_0x0094
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Tr.Tr.Tk.Tr.Ty.T(java.lang.String, com.Tr.Tr.Tk.T.T, java.util.Map):com.Tr.Tr.Tk.Tr.Tk");
    }

    private static com.Tr.Tr.Tk.T.Ty T(T ecLevel, Tr mode, com.Tr.Tr.Tr.T headerBits, com.Tr.Tr.Tr.T dataBits) throws TE {
        return T(T(mode, headerBits, dataBits, T(T(mode, headerBits, dataBits, com.Tr.Tr.Tk.T.Ty.T(1)), ecLevel)), ecLevel);
    }

    private static int T(Tr mode, com.Tr.Tr.Tr.T headerBits, com.Tr.Tr.Tr.T dataBits, com.Tr.Tr.Tk.T.Ty version) {
        return headerBits.T() + mode.T(version) + dataBits.T();
    }

    static int T(int code) {
        if (code < f271T.length) {
            return f271T[code];
        }
        return -1;
    }

    private static Tr T(String content, String encoding) {
        if ("Shift_JIS".equals(encoding) && T(content)) {
            return Tr.KANJI;
        }
        boolean hasNumeric = false;
        boolean hasAlphanumeric = false;
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c >= '0' && c <= '9') {
                hasNumeric = true;
            } else if (T((int) c) == -1) {
                return Tr.BYTE;
            } else {
                hasAlphanumeric = true;
            }
        }
        if (hasAlphanumeric) {
            return Tr.ALPHANUMERIC;
        }
        if (hasNumeric) {
            return Tr.NUMERIC;
        }
        return Tr.BYTE;
    }

    private static boolean T(String content) {
        try {
            byte[] bytes = content.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i = 0; i < length; i += 2) {
                int byte1 = bytes[i] & 255;
                if ((byte1 < 129 || byte1 > 159) && (byte1 < 224 || byte1 > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    private static int T(com.Tr.Tr.Tr.T bits, T ecLevel, com.Tr.Tr.Tk.T.Ty version, Tr matrix) throws TE {
        int minPenalty = Integer.MAX_VALUE;
        int bestMaskPattern = -1;
        for (int maskPattern = 0; maskPattern < 8; maskPattern++) {
            T9.T(bits, ecLevel, version, maskPattern, matrix);
            int penalty = T(matrix);
            if (penalty < minPenalty) {
                minPenalty = penalty;
                bestMaskPattern = maskPattern;
            }
        }
        return bestMaskPattern;
    }

    private static com.Tr.Tr.Tk.T.Ty T(int numInputBits, T ecLevel) throws TE {
        for (int versionNum = 1; versionNum <= 40; versionNum++) {
            com.Tr.Tr.Tk.T.Ty version = com.Tr.Tr.Tk.T.Ty.T(versionNum);
            if (T(numInputBits, version, ecLevel)) {
                return version;
            }
        }
        throw new TE("Data too big");
    }

    private static boolean T(int numInputBits, com.Tr.Tr.Tk.T.Ty version, T ecLevel) {
        return version.Tr() - version.T(ecLevel).Ty() >= (numInputBits + 7) / 8;
    }

    static void T(int numDataBytes, com.Tr.Tr.Tr.T bits) throws TE {
        int capacity = numDataBytes << 3;
        if (bits.T() > capacity) {
            throw new TE("data bits cannot fit in the QR Code" + bits.T() + " > " + capacity);
        }
        for (int i = 0; i < 4 && bits.T() < capacity; i++) {
            bits.T(false);
        }
        int numBitsInLastByte = bits.T() & 7;
        if (numBitsInLastByte > 0) {
            for (int i2 = numBitsInLastByte; i2 < 8; i2++) {
                bits.T(false);
            }
        }
        int numPaddingBytes = numDataBytes - bits.Tr();
        for (int i3 = 0; i3 < numPaddingBytes; i3++) {
            bits.T((i3 & 1) == 0 ? Can.CAN_CHRYSLER_ONE_HC : 17, 8);
        }
        if (bits.T() != capacity) {
            throw new TE("Bits size does not equal capacity");
        }
    }

    static void T(int numTotalBytes, int numDataBytes, int numRSBlocks, int blockID, int[] numDataBytesInBlock, int[] numECBytesInBlock) throws TE {
        if (blockID >= numRSBlocks) {
            throw new TE("Block ID too large");
        }
        int numRsBlocksInGroup2 = numTotalBytes % numRSBlocks;
        int numRsBlocksInGroup1 = numRSBlocks - numRsBlocksInGroup2;
        int numTotalBytesInGroup1 = numTotalBytes / numRSBlocks;
        int numDataBytesInGroup1 = numDataBytes / numRSBlocks;
        int numDataBytesInGroup2 = numDataBytesInGroup1 + 1;
        int numEcBytesInGroup1 = numTotalBytesInGroup1 - numDataBytesInGroup1;
        int numEcBytesInGroup2 = (numTotalBytesInGroup1 + 1) - numDataBytesInGroup2;
        if (numEcBytesInGroup1 != numEcBytesInGroup2) {
            throw new TE("EC bytes mismatch");
        } else if (numRSBlocks != numRsBlocksInGroup1 + numRsBlocksInGroup2) {
            throw new TE("RS blocks mismatch");
        } else if (numTotalBytes != ((numDataBytesInGroup1 + numEcBytesInGroup1) * numRsBlocksInGroup1) + ((numDataBytesInGroup2 + numEcBytesInGroup2) * numRsBlocksInGroup2)) {
            throw new TE("Total bytes mismatch");
        } else if (blockID < numRsBlocksInGroup1) {
            numDataBytesInBlock[0] = numDataBytesInGroup1;
            numECBytesInBlock[0] = numEcBytesInGroup1;
        } else {
            numDataBytesInBlock[0] = numDataBytesInGroup2;
            numECBytesInBlock[0] = numEcBytesInGroup2;
        }
    }

    static com.Tr.Tr.Tr.T T(com.Tr.Tr.Tr.T bits, int numTotalBytes, int numDataBytes, int numRSBlocks) throws TE {
        if (bits.Tr() != numDataBytes) {
            throw new TE("Number of bits and data bytes does not match");
        }
        int dataBytesOffset = 0;
        int maxNumDataBytes = 0;
        int maxNumEcBytes = 0;
        Collection<T> blocks = new ArrayList<>(numRSBlocks);
        for (int i = 0; i < numRSBlocks; i++) {
            int[] numDataBytesInBlock = new int[1];
            int[] numEcBytesInBlock = new int[1];
            T(numTotalBytes, numDataBytes, numRSBlocks, i, numDataBytesInBlock, numEcBytesInBlock);
            int size = numDataBytesInBlock[0];
            byte[] dataBytes = new byte[size];
            bits.T(dataBytesOffset << 3, dataBytes, 0, size);
            byte[] ecBytes = T(dataBytes, numEcBytesInBlock[0]);
            blocks.add(new T(dataBytes, ecBytes));
            maxNumDataBytes = Math.max(maxNumDataBytes, size);
            maxNumEcBytes = Math.max(maxNumEcBytes, ecBytes.length);
            dataBytesOffset += numDataBytesInBlock[0];
        }
        if (numDataBytes != dataBytesOffset) {
            throw new TE("Data bytes does not match offset");
        }
        com.Tr.Tr.Tr.T result = new com.Tr.Tr.Tr.T();
        for (int i2 = 0; i2 < maxNumDataBytes; i2++) {
            for (T T2 : blocks) {
                byte[] dataBytes2 = T2.T();
                if (i2 < dataBytes2.length) {
                    result.T(dataBytes2[i2], 8);
                }
            }
        }
        for (int i3 = 0; i3 < maxNumEcBytes; i3++) {
            for (T Tr : blocks) {
                byte[] ecBytes2 = Tr.Tr();
                if (i3 < ecBytes2.length) {
                    result.T(ecBytes2[i3], 8);
                }
            }
        }
        if (numTotalBytes == result.Tr()) {
            return result;
        }
        throw new TE("Interleaving error: " + numTotalBytes + " and " + result.Tr() + " differ.");
    }

    static byte[] T(byte[] dataBytes, int numEcBytesInBlock) {
        int numDataBytes = dataBytes.length;
        int[] toEncode = new int[(numDataBytes + numEcBytesInBlock)];
        for (int i = 0; i < numDataBytes; i++) {
            toEncode[i] = dataBytes[i] & 255;
        }
        new com.Tr.Tr.Tr.T.Ty(com.Tr.Tr.Tr.T.T.T9).T(toEncode, numEcBytesInBlock);
        byte[] ecBytes = new byte[numEcBytesInBlock];
        for (int i2 = 0; i2 < numEcBytesInBlock; i2++) {
            ecBytes[i2] = (byte) toEncode[numDataBytes + i2];
        }
        return ecBytes;
    }

    static void T(Tr mode, com.Tr.Tr.Tr.T bits) {
        bits.T(mode.T(), 4);
    }

    static void T(int numLetters, com.Tr.Tr.Tk.T.Ty version, Tr mode, com.Tr.Tr.Tr.T bits) throws TE {
        int numBits = mode.T(version);
        if (numLetters >= (1 << numBits)) {
            throw new TE(numLetters + " is bigger than " + ((1 << numBits) - 1));
        }
        bits.T(numLetters, numBits);
    }

    static void T(String content, Tr mode, com.Tr.Tr.Tr.T bits, String encoding) throws TE {
        switch (mode) {
            case NUMERIC:
                T((CharSequence) content, bits);
                return;
            case ALPHANUMERIC:
                Tr(content, bits);
                return;
            case BYTE:
                T(content, bits, encoding);
                return;
            case KANJI:
                T(content, bits);
                return;
            default:
                throw new TE("Invalid mode: " + mode);
        }
    }

    static void T(CharSequence content, com.Tr.Tr.Tr.T bits) {
        int length = content.length();
        int i = 0;
        while (i < length) {
            int num1 = content.charAt(i) - '0';
            if (i + 2 < length) {
                bits.T((num1 * 100) + ((content.charAt(i + 1) - '0') * 10) + (content.charAt(i + 2) - '0'), 10);
                i += 3;
            } else if (i + 1 < length) {
                bits.T((num1 * 10) + (content.charAt(i + 1) - '0'), 7);
                i += 2;
            } else {
                bits.T(num1, 4);
                i++;
            }
        }
    }

    static void Tr(CharSequence content, com.Tr.Tr.Tr.T bits) throws TE {
        int length = content.length();
        int i = 0;
        while (i < length) {
            int code1 = T((int) content.charAt(i));
            if (code1 == -1) {
                throw new TE();
            } else if (i + 1 < length) {
                int code2 = T((int) content.charAt(i + 1));
                if (code2 == -1) {
                    throw new TE();
                }
                bits.T((code1 * 45) + code2, 11);
                i += 2;
            } else {
                bits.T(code1, 6);
                i++;
            }
        }
    }

    static void T(String content, com.Tr.Tr.Tr.T bits, String encoding) throws TE {
        try {
            for (byte b : content.getBytes(encoding)) {
                bits.T(b, 8);
            }
        } catch (UnsupportedEncodingException uee) {
            throw new TE((Throwable) uee);
        }
    }

    static void T(String content, com.Tr.Tr.Tr.T bits) throws TE {
        try {
            byte[] bytes = content.getBytes("Shift_JIS");
            int length = bytes.length;
            for (int i = 0; i < length; i += 2) {
                int byte1 = bytes[i] & 255;
                int code = (byte1 << 8) | (bytes[i + 1] & 255);
                int subtracted = -1;
                if (code >= 33088 && code <= 40956) {
                    subtracted = code - 33088;
                } else if (code >= 57408 && code <= 60351) {
                    subtracted = code - 49472;
                }
                if (subtracted == -1) {
                    throw new TE("Invalid byte sequence");
                }
                bits.T(((subtracted >> 8) * 192) + (subtracted & 255), 13);
            }
        } catch (UnsupportedEncodingException uee) {
            throw new TE((Throwable) uee);
        }
    }

    private static void T(com.Tr.Tr.Tr.Ty eci, com.Tr.Tr.Tr.T bits) {
        bits.T(Tr.ECI.T(), 4);
        bits.T(eci.T(), 8);
    }
}
