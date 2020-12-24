package com.google.zxing.datamatrix.encoder;

import com.lgb.canmodule.Can;

public final class ErrorCorrection {
    private static final int[] ALOG = new int[255];
    private static final int[][] FACTORS = {new int[]{Can.CAN_TEANA_OLD_DJ, 48, 15, 111, 62}, new int[]{23, 68, 144, 134, 240, 92, Can.CAN_FLAT_RZC}, new int[]{28, 24, 185, 166, Can.CAN_X80_RZC, Can.CAN_RENAUL_KOLEOS_XFY, 116, 255, 110, 61}, new int[]{175, 138, Can.CAN_LEXUS_IZ, 12, 194, 168, 39, Can.CAN_FORD_MONDEO_XFY, 60, 97, 120}, new int[]{41, Can.CAN_HYUNDAI_WC, Can.CAN_BJ20_WC, 91, 61, 42, 142, 213, 97, 178, 100, Can.CAN_MZD_LUOMU}, new int[]{Can.CAN_HONDA_WC, 97, 192, Can.CAN_TOYOTA_SP_XP, 95, 9, Can.CAN_FORD_WC, 119, 138, 45, 18, 186, 83, 185}, new int[]{83, 195, 100, 39, 188, 75, 66, 61, 241, 213, 109, 129, 94, Can.CAN_FLAT_RZC, Can.CAN_BENZ_SMART_OD, 48, 90, 188}, new int[]{15, 195, Can.CAN_BYD_M6_DJ, 9, Can.CAN_SGMW_WC, 71, 168, 2, 188, Can.CAN_CHANA_CS75_WC, Can.CAN_HYUNDAI_WC, 145, Can.CAN_FORD_ESCORT_LY, 79, 108, 82, 27, 174, 186, 172}, new int[]{52, 190, 88, Can.CAN_LEXUS_IZ, 109, 39, 176, 21, Can.CAN_DFFG_S560, 197, Can.CAN_MG_ZS_WC, Can.CAN_X80_RZC, Can.CAN_DFFG_S560, 21, 5, 172, Can.CAN_FLAT_RZC, 124, 12, 181, 184, 96, 50, 193}, new int[]{211, Can.CAN_BYD_S6_S7, 43, 97, 71, 96, 103, 174, 37, 151, 170, 53, 75, 34, Can.CAN_LUXGEN_WC, 121, 17, 138, 110, 213, 141, 136, 120, 151, Can.CAN_SGMW_WC, 168, 93, 255}, new int[]{Can.CAN_FORD_MONDEO_XFY, 127, Can.CAN_MZD_LUOMU, 218, 130, Can.CAN_NISSAN_XFY, 162, 181, 102, 120, 84, 179, 220, Can.CAN_MG_ZS_WC, 80, 182, Can.CAN_VOLVO_XFY, 18, 2, 4, 68, 33, 101, 137, 95, 119, 115, 44, 175, 184, 59, 25, Can.CAN_BENZ_SMART_OD, 98, 81, 112}, new int[]{77, 193, 137, 31, 19, 38, 22, Can.CAN_HYUNDAI_WC, Can.CAN_FORD_EDGE_XFY, 105, 122, 2, Can.CAN_FORD_MONDEO_XFY, 133, Can.CAN_MZD_LUOMU, 8, 175, 95, 100, 9, 167, 105, 214, 111, 57, 121, 21, 1, Can.CAN_FORD_ESCORT_LY, 57, 54, 101, Can.CAN_RENAUL_KOLEOS_XFY, 202, 69, 50, Can.CAN_JAC_REFINE_OD, 177, Can.CAN_FORD_SYNC3, 5, 9, 5}, new int[]{Can.CAN_FORD_MONDEO_XFY, 132, 172, Can.CAN_X80_RZC, 96, 32, 117, 22, Can.CAN_SE_DX7_RZC, 133, Can.CAN_SE_DX7_RZC, Can.CAN_BYD_S6_S7, Can.CAN_LEXUS_IZ, 188, Can.CAN_NISSAN_RICH6_WC, 87, 191, 106, 16, 147, 118, 23, 37, 90, 170, Can.CAN_LEXUS_IZ, 131, 88, 120, 100, 66, 138, 186, 240, 82, 44, 176, 87, 187, 147, Can.CAN_CHANA_CS75_WC, 175, 69, 213, 92, Can.CAN_FORD_ESCORT_LY, Can.CAN_BENZ_SMART_OD, 19}, new int[]{175, 9, Can.CAN_X80_RZC, Can.CAN_SE_DX7_RZC, 12, 17, 220, 208, 100, 29, 175, 170, Can.CAN_CC_HF_DJ, 192, 215, Can.CAN_ZH_WC, Can.CAN_JAC_REFINE_OD, Can.CAN_DF_WC, 36, Can.CAN_X80_RZC, 38, 200, 132, 54, Can.CAN_TEANA_OLD_DJ, 146, 218, Can.CAN_LIEBAO_WC, 117, 203, 29, Can.CAN_FLAT_WC, 144, Can.CAN_SE_DX7_RZC, 22, Can.CAN_JAC_REFINE_OD, 201, 117, 62, Can.CAN_LEXUS_ZMYT, 164, 13, 137, Can.CAN_FORD_MONDEO_XFY, 127, 67, Can.CAN_FORD_EDGE_XFY, 28, Can.CAN_DFFG_S560, 43, 203, 107, Can.CAN_SGMW_WC, 53, 143, 46}, new int[]{Can.CAN_MZD_LUOMU, 93, 169, 50, 144, 210, 39, 118, 202, 188, 201, 189, 143, 108, 196, 37, 185, 112, 134, Can.CAN_CC_HF_DJ, Can.CAN_FORD_MONDEO_XFY, 63, 197, 190, Can.CAN_NISSAN_XFY, 106, 185, 221, 175, 64, 114, 71, 161, 44, 147, 6, 27, 218, 51, 63, 87, 10, 40, 130, 188, 17, 163, 31, 176, 170, 4, 107, Can.CAN_FLAT_WC, 7, 94, 166, Can.CAN_ZH_H530, 124, 86, 47, 11, 204}, new int[]{220, Can.CAN_TEANA_OLD_DJ, 173, 89, Can.CAN_MG_ZS_WC, 149, Can.CAN_DF_WC, 56, 89, 33, 147, Can.CAN_BYD_M6_DJ, Can.CAN_CC_WC, 36, 73, 127, 213, 136, Can.CAN_RENAUL_KOLEOS_XFY, 180, Can.CAN_LIEBAO_WC, 197, Can.CAN_BJ20_WC, 177, 68, 122, 93, 213, 15, Can.CAN_CHANA_CS75_WC, Can.CAN_JIANGLING_MYX, Can.CAN_CHRYSLER_ONE_HC, 66, 139, Can.CAN_HYUNDAI_WC, 185, 202, 167, 179, 25, 220, Can.CAN_FLAT_WC, 96, 210, Can.CAN_BYD_S6_S7, 136, Can.CAN_X80_RZC, Can.CAN_GM_CAPTIVA_OD, 181, 241, 59, 52, 172, 25, 49, Can.CAN_FLAT_WC, 211, 189, 64, 54, 108, Can.CAN_HYUNDAI_WC, 132, 63, 96, 103, 82, 186}};
    private static final int[] FACTOR_SETS = {5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68};
    private static final int[] LOG = new int[256];
    private static final int MODULO_VALUE = 301;

    static {
        int p = 1;
        for (int i = 0; i < 255; i++) {
            ALOG[i] = p;
            LOG[p] = i;
            p *= 2;
            if (p >= 256) {
                p ^= 301;
            }
        }
    }

    private ErrorCorrection() {
    }

    public static String encodeECC200(String codewords, SymbolInfo symbolInfo) {
        if (codewords.length() != symbolInfo.getDataCapacity()) {
            throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
        }
        StringBuilder sb = new StringBuilder(symbolInfo.getDataCapacity() + symbolInfo.getErrorCodewords());
        sb.append(codewords);
        int blockCount = symbolInfo.getInterleavedBlockCount();
        if (blockCount == 1) {
            sb.append(createECCBlock(codewords, symbolInfo.getErrorCodewords()));
        } else {
            sb.setLength(sb.capacity());
            int[] dataSizes = new int[blockCount];
            int[] errorSizes = new int[blockCount];
            int[] startPos = new int[blockCount];
            for (int i = 0; i < blockCount; i++) {
                dataSizes[i] = symbolInfo.getDataLengthForInterleavedBlock(i + 1);
                errorSizes[i] = symbolInfo.getErrorLengthForInterleavedBlock(i + 1);
                startPos[i] = 0;
                if (i > 0) {
                    startPos[i] = startPos[i - 1] + dataSizes[i];
                }
            }
            for (int block = 0; block < blockCount; block++) {
                StringBuilder temp = new StringBuilder(dataSizes[block]);
                for (int d = block; d < symbolInfo.getDataCapacity(); d += blockCount) {
                    temp.append(codewords.charAt(d));
                }
                String ecc = createECCBlock(temp.toString(), errorSizes[block]);
                int pos = 0;
                int e = block;
                while (e < errorSizes[block] * blockCount) {
                    sb.setCharAt(symbolInfo.getDataCapacity() + e, ecc.charAt(pos));
                    e += blockCount;
                    pos++;
                }
            }
        }
        return sb.toString();
    }

    private static String createECCBlock(CharSequence codewords, int numECWords) {
        return createECCBlock(codewords, 0, codewords.length(), numECWords);
    }

    private static String createECCBlock(CharSequence codewords, int start, int len, int numECWords) {
        int table = -1;
        int i = 0;
        while (true) {
            if (i >= FACTOR_SETS.length) {
                break;
            } else if (FACTOR_SETS[i] == numECWords) {
                table = i;
                break;
            } else {
                i++;
            }
        }
        if (table < 0) {
            throw new IllegalArgumentException("Illegal number of error correction codewords specified: " + numECWords);
        }
        int[] poly = FACTORS[table];
        char[] ecc = new char[numECWords];
        for (int i2 = 0; i2 < numECWords; i2++) {
            ecc[i2] = 0;
        }
        for (int i3 = start; i3 < start + len; i3++) {
            int m = ecc[numECWords - 1] ^ codewords.charAt(i3);
            for (int k = numECWords - 1; k > 0; k--) {
                if (m == 0 || poly[k] == 0) {
                    ecc[k] = ecc[k - 1];
                } else {
                    ecc[k] = (char) (ecc[k - 1] ^ ALOG[(LOG[m] + LOG[poly[k]]) % 255]);
                }
            }
            if (m == 0 || poly[0] == 0) {
                ecc[0] = 0;
            } else {
                ecc[0] = (char) ALOG[(LOG[m] + LOG[poly[0]]) % 255];
            }
        }
        char[] eccReversed = new char[numECWords];
        for (int i4 = 0; i4 < numECWords; i4++) {
            eccReversed[i4] = ecc[(numECWords - i4) - 1];
        }
        return String.valueOf(eccReversed);
    }
}
