package com.google.zxing.pdf417.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.pdf417.PDF417Common;
import com.lgb.canmodule.Can;
import com.ts.can.CanCameraUI;
import com.ts.factoryset.AtcDisplaySettingsUtils;
import com.ts.main.common.KeyTouch;
import com.ts.main.common.MainLight;
import com.yyw.ts70xhw.KeyDef;

final class PDF417ErrorCorrection {
    private static final int[][] EC_COEFFICIENTS = {new int[]{27, 917}, new int[]{CanCameraUI.BTN_GEELY_YJX6_MODE3, 568, 723, KeyDef.SKEY_MODE_1}, new int[]{Can.CAN_NISSAN_RICH6_WC, 308, 436, 284, CanCameraUI.BTN_LANDWIND_3D_RIGHT_UP, 653, 428, 379}, new int[]{274, CanCameraUI.BTN_TRUMPCHI_GS7_MODE3, Can.CAN_FLAT_WC, 755, 599, CanCameraUI.BTN_GEELY_YJX6_ESC, 801, 132, 295, 116, 442, 428, 295, 42, 176, 65}, new int[]{361, CanCameraUI.BTN_CHANA_CS75_MODE6, 922, CanCameraUI.BTN_GEELY_YJX6_3D, 176, 586, CanCameraUI.BTN_LANDWIND_2D3D, KeyDef.RKEY_MEDIA_PROG, 536, 742, 677, 742, 687, 284, 193, 517, 273, 494, 263, 147, 593, 800, CanCameraUI.BTN_CHANA_CS75_MODE2, KeyDef.RKEY_MEDIA_SLOW, 803, 133, Can.CAN_BYD_S6_S7, 390, 685, KeyDef.RKEY_RDS_TA, 63, 410}, new int[]{539, 422, 6, 93, 862, KeyDef.SKEY_POWEWR_3, 453, 106, CanCameraUI.BTN_CCH9_MODE1, 287, 107, CanCameraUI.BTN_HMS7_HELP_LINE, 733, 877, 381, CanCameraUI.BTN_CCH9_MODE3, 723, 476, 462, 172, 430, 609, 858, KeyDef.SKEY_CALLDN_4, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST4, 376, CanCameraUI.BTN_YG9_XBS_MODE2, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 672, 762, 283, 184, 440, 35, 519, 31, 460, 594, Can.CAN_BENZ_SMART_OD, 535, 517, 352, 605, Can.CAN_BJ20_WC, 651, 201, 488, CanCameraUI.BTN_HMS7_MODE3, CanCameraUI.BTN_LANDWIND_3D_REAR, 733, 717, 83, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, 97, 280, KeyDef.SKEY_POWEWR_3, KeyDef.SKEY_RETURN_2, 629, 4, 381, KeyDef.SKEY_RETURN_5, CanCameraUI.BTN_CCH9_MODE14, 264, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST4}, new int[]{CanCameraUI.BTN_GEELY_YJX6_MODE2, 310, 864, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST8, 858, CanCameraUI.BTN_CAMERY_2018_MODE1, 296, 379, 53, KeyDef.SKEY_VOLDN_1, 897, 444, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 925, 749, 415, KeyDef.SKEY_CALLDN_4, 93, 217, 208, PDF417Common.MAX_CODEWORDS_IN_BARCODE, Can.CAN_BYD_M6_DJ, 583, 620, Can.CAN_CHRYSLER_TXB, 148, 447, CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE2, 292, 908, 490, 704, 516, 258, 457, 907, 594, 723, 674, 292, 272, 96, 684, 432, 686, 606, 860, 569, 193, 219, 129, 186, Can.CAN_CHRYSLER_ONE_HC, 287, 192, KeyDef.SKEY_VOLUP_2, 278, 173, 40, 379, 712, MainLight.VOL_BAR_SIZEX, CanCameraUI.BTN_LANDWIND_3D_RIGHT_UP, KeyDef.SKEY_VOLUP_3, 171, 491, 297, 763, Can.CAN_HONDA_WC, 732, 95, 270, 447, 90, 507, 48, Can.CAN_TEANA_OLD_DJ, KeyDef.SKEY_CALLDN_3, KeyDef.SKEY_MUTE_5, 898, KeyDef.SKEY_SEEKUP_1, 663, 627, 378, 382, 262, 380, CanCameraUI.BTN_GOLF_WC_MODE3, 754, KeyDef.RKEY_AVIN, 89, CanCameraUI.BTN_CCH9_MODE5, 87, 432, 670, CanCameraUI.BTN_CCH9_MODE7, Can.CAN_FORD_WC, 374, Can.CAN_MZD_LUOMU, 726, 600, 269, 375, 898, KeyDef.SKEY_SPEECH_2, 454, 354, 130, KeyDef.SKEY_CALLUP_1, 587, KeyDef.SKEY_MUTE_1, 34, 211, KeyDef.RKEY_RDS_TA, 539, 297, KeyDef.SKEY_PP_4, 865, 37, 517, KeyDef.SKEY_HOME_1, KeyDef.RKEY_EJECT, 550, 86, 801, 4, 108, 539}, new int[]{CanCameraUI.BTN_GEELY_YJX6_ESC, 894, 75, 766, 882, 857, 74, 204, 82, 586, 708, Can.CAN_NISSAN_XFY, 905, KeyDef.SKEY_SEEKUP_3, 138, 720, 858, 194, 311, 913, 275, 190, 375, 850, 438, 733, 194, 280, 201, 280, KeyDef.SKEY_PP_5, 757, 710, KeyDef.SKEY_CALLUP_1, 919, 89, 68, 569, 11, 204, KeyDef.SKEY_CHUP_3, 605, 540, 913, 801, CanCameraUI.BTN_CC_WC_DIRECTION1, KeyDef.SKEY_CHDN_1, 137, 439, 418, CanCameraUI.BTN_VW_WC_MODE3, 668, 353, 859, AtcDisplaySettingsUtils.SPECIFIC_Y_SMALL2, 694, KeyDef.RKEY_RADIO_3S, Can.CAN_VOLKS_XP, 216, 257, 284, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST10, 209, 884, KeyDef.RKEY_EJECT, 70, KeyDef.RKEY_RDS_AF, KeyDef.SKEY_SEEKDN_5, 490, 274, 877, 162, 749, 812, 684, 461, KeyDef.RKEY_EJECT_L, 376, 849, CanCameraUI.BTN_GEELY_YJX6_MODE2, 307, 291, 803, 712, 19, 358, 399, 908, 103, CanCameraUI.BTN_YG9_XBS_MODE2, 51, 8, 517, Can.CAN_BENZ_SMART_OD, 289, 470, 637, 731, 66, 255, 917, 269, MainLight.VOL_BAR_SIZEX, KeyDef.SKEY_NAVI_2, 730, 433, KeyDef.SKEY_SPEECH_5, 585, 136, 538, 906, 90, 2, 290, 743, 199, 655, 903, KeyDef.RKEY_RDS_AF, 49, 802, CanCameraUI.BTN_CAMERY_2018_MODE1, 355, 588, 188, 462, 10, 134, 628, KeyDef.RKEY_MEDIA_SLOW, 479, 130, 739, 71, 263, KeyDef.RKEY_ANGLEDN, 374, CanCameraUI.BTN_GOLF_WC_MODE2, 192, 605, 142, 673, 687, Can.CAN_LIEBAO_WC, 722, 384, 177, 752, 607, CanCameraUI.BTN_LANDWIND_2D3D, 455, 193, 689, 707, KeyDef.SKEY_MUTE_2, CanCameraUI.BTN_LANDWIND_2D_FRONT, 48, 60, 732, 621, 895, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST5, 261, 852, 655, 309, 697, 755, 756, 60, Can.CAN_BYD_S6_S7, KeyDef.SKEY_POWEWR_5, 434, 421, 726, 528, CanCameraUI.BTN_HMS7_MODE4, 118, 49, KeyDef.SKEY_CHUP_2, 32, 144, 500, Can.CAN_SE_DX7_RZC, KeyDef.SKEY_HOME_3, 394, 280, CanCameraUI.BTN_TRUMPCHI_GS7_MODE7, KeyDef.RKEY_MEDIA_STOP, 9, CanCameraUI.BTN_LANDWIND_3D_RIGHT_DOWN, 550, 73, 914, KeyDef.RKEY_res5, 126, 32, 681, KeyDef.RKEY_RDS_PTY, KeyDef.SKEY_SEEKDN_4, 620, 60, 609, 441, 180, KeyDef.SKEY_SEEKDN_3, 893, 754, 605, 383, Can.CAN_TEANA_OLD_DJ, 749, 760, 213, 54, 297, 134, 54, KeyDef.SKEY_HOME_1, 299, 922, 191, 910, CanCameraUI.BTN_CHANA_ALSVINV7_MODE3, 609, KeyDef.SKEY_NAVI_1, 189, 20, 167, 29, 872, 449, 83, CanCameraUI.BTN_TRUMPCHI_GS4_MODE3, 41, 656, CanCameraUI.BTN_HMS7_HELP_LINE, 579, 481, 173, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, Can.CAN_MG_ZS_WC, 688, 95, 497, 555, CanCameraUI.BTN_LANDWIND_2D_REAR, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST4, 307, Can.CAN_DF_WC, 924, 558, CanCameraUI.BTN_LANDWIND_3D_REAR, 55, 497, 10}, new int[]{352, 77, 373, CanCameraUI.BTN_HMS7_ESC, 35, 599, 428, Can.CAN_LEXUS_ZMYT, 409, CanCameraUI.BTN_CHANA_CS75_MODE5, 118, 498, 285, 380, 350, 492, 197, 265, 920, Can.CAN_DFFG_S560, 914, 299, Can.CAN_VOLVO_XFY, CanCameraUI.BTN_LANDWIND_2D_LEFT, 294, 871, 306, 88, 87, 193, 352, KeyDef.SKEY_VOLDN_3, KeyDef.SKEY_SPEECH_3, 75, KeyDef.RKEY_RADIO_5S, CanCameraUI.BTN_GEELY_YJX6_MODE1, 435, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST4, 203, 666, Can.CAN_LUXGEN_WC, 346, KeyDef.SKEY_VOLDN_3, 621, CanCameraUI.BTN_LANDWIND_2D3D, 268, KeyDef.SKEY_CHUP_1, 534, 539, KeyDef.SKEY_VOLDN_3, 408, 390, CanCameraUI.BTN_LANDWIND_2D_RIGHT, 102, 476, 499, 290, CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE3, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6, 37, 858, 916, 552, 41, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST3, 289, 122, 272, 383, 800, 485, 98, 752, 472, 761, 107, KeyDef.SKEY_SEEKUP_1, 860, 658, 741, 290, 204, 681, 407, 855, 85, 99, 62, 482, 180, 20, 297, 451, 593, 913, 142, KeyDef.SKEY_MUTE_5, 684, 287, 536, CanCameraUI.BTN_TRUMPCHI_GS7_MODE2, 76, 653, 899, 729, CanCameraUI.BTN_TRUMPCHI_GS7_MODE8, 744, 390, 513, 192, 516, 258, Can.CAN_VOLKS_XP, 518, KeyDef.SKEY_CHUP_1, 395, 768, KeyDef.SKEY_SPEECH_5, 51, CanCameraUI.BTN_CCH9_MODE1, 384, 168, 190, KeyDef.SKEY_PP_3, KeyDef.RKEY_RADIO_6S, 596, KeyDef.SKEY_SEEKUP_3, 303, CanCameraUI.BTN_CHANA_CS75_MODE1, 381, 415, CanCameraUI.BTN_LANDWIND_2D_FRONT, Can.CAN_HONDA_WC, Can.CAN_NISSAN_RICH6_WC, 151, 429, CanCameraUI.BTN_CHANA_ALSVINV7_MODE2, Can.CAN_LEXUS_ZMYT, 676, 710, 89, 168, 304, CanCameraUI.BTN_TRUMPCHI_GS4_MODE3, 40, 708, CanCameraUI.BTN_CHANA_CS75_MODE6, 162, 864, Can.CAN_VOLVO_XFY, 65, 861, KeyDef.SKEY_RETURN_3, 512, 164, 477, 221, 92, 358, KeyDef.SKEY_SEEKUP_2, 288, 357, 850, KeyDef.SKEY_HOME_3, KeyDef.SKEY_PP_4, 736, 707, 94, 8, 494, 114, CanCameraUI.BTN_GEELY_YJX6_MODE2, 2, 499, 851, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST4, 152, 729, KeyDef.SKEY_POWEWR_3, 95, Can.CAN_RENAUL_KOLEOS_XFY, 361, 578, KeyDef.RKEY_RADIO_1S, 856, KeyDef.SKEY_CHUP_4, 289, 51, 684, 466, 533, KeyDef.SKEY_CALLDN_2, 669, 45, 902, 452, 167, KeyDef.RKEY_res5, Can.CAN_BYD_M6_DJ, 173, 35, MainLight.VOL_BAR_SIZEX, 651, 51, 699, CanCameraUI.BTN_VW_WC_MODE2, 452, 578, 37, 124, 298, KeyDef.RKEY_POWER_ON, 552, 43, 427, 119, 662, KeyDef.SKEY_VOLUP_4, 475, 850, 764, 364, 578, 911, 283, 711, 472, 420, Can.CAN_FORD_MONDEO_XFY, 288, 594, 394, CanCameraUI.BTN_YG9_XBS_MODE2, KeyDef.RKEY_RADIO_5S, 589, KeyDef.SKEY_VOLUP_4, 699, 688, 43, 408, KeyDef.SKEY_RETURN_4, 383, 721, CanCameraUI.BTN_GEELY_YJX6_MODE2, CanCameraUI.BTN_TRUMPCHI_GS7_MODE1, CanCameraUI.BTN_LANDWIND_2D_RIGHT, 714, 559, 62, 145, 873, 663, 713, Can.CAN_DF_WC, 672, 729, 624, 59, 193, 417, Can.CAN_BJ20_WC, 209, CanCameraUI.BTN_TRUMPCHI_GS7_MODE4, CanCameraUI.BTN_TRUMPCHI_GS7_MODE5, 343, 693, 109, 608, CanCameraUI.BTN_TRUMPCHI_GS7_MODE4, 365, 181, KeyDef.SKEY_POWEWR_4, 677, 310, Can.CAN_RENAUL_KOLEOS_XFY, 353, 708, 410, 579, 870, CanCameraUI.BTN_CCH9_MODE8, KeyDef.SKEY_RETURN_3, CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE3, 860, 289, 536, 35, KeyDef.SKEY_VOLUP_4, CanCameraUI.BTN_CCH9_MODE9, 586, 424, KeyDef.SKEY_NAVI_5, 77, 597, 346, 269, 757, CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE3, 695, 751, KeyDef.RKEY_RDS_PTY, Can.CAN_FORD_EDGE_XFY, 184, 45, KeyDef.SKEY_SEEKUP_4, 680, 18, 66, 407, 369, 54, 492, Can.CAN_TEANA_OLD_DJ, CanCameraUI.BTN_CCH9_MODE4, KeyDef.SKEY_NAVI_2, 922, 437, 519, CanCameraUI.BTN_LANDWIND_2D_RIGHT, 905, KeyDef.SKEY_SEEKDN_1, 420, 305, 441, Can.CAN_LEXUS_ZMYT, 300, 892, KeyDef.SKEY_PP_4, 141, 537, 381, 662, 513, 56, Can.CAN_TOYOTA_SP_XP, KeyDef.RKEY_res4, Can.CAN_MZD_LUOMU, KeyDef.SKEY_CHUP_4, KeyDef.SKEY_HOME_5, KeyDef.SKEY_HOME_4, 720, Can.CAN_ZH_H530, 307, CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE2, 61, 87, CanCameraUI.BTN_TRUMPCHI_GS7_MODE1, 310, 756, 665, 397, KeyDef.SKEY_MUTE_5, 851, 309, 473, KeyDef.SKEY_CHUP_2, 378, 31, CanCameraUI.BTN_LANDWIND_3D_RIGHT_DOWN, 915, 459, KeyDef.SKEY_MUTE_3, CanCameraUI.BTN_VW_WC_MODE1, 731, 425, 216, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST9, Can.CAN_LUXGEN_WC, KeyDef.RKEY_MEDIA_PROG, 881, 699, 535, 673, KeyDef.SKEY_VOLDN_4, 210, KeyDef.SKEY_CALLUP_2, 905, 303, KeyDef.SKEY_RETURN_5, 922, 281, 73, 469, KeyDef.SKEY_SEEKDN_3, 660, 162, 498, 308, Can.CAN_DFFG_S560, 422, 907, KeyDef.SKEY_CALLUP_4, 187, 62, 16, 425, 535, KeyDef.RKEY_AVIN, 286, 437, 375, 273, CanCameraUI.BTN_CCH9_MODE1, 296, 183, 923, 116, 667, 751, 353, 62, KeyTouch.GAMMA_MAX_NUM, 691, 379, 687, KeyDef.SKEY_RETURN_4, 37, 357, 720, 742, KeyDef.RKEY_RDS_TA, 5, 39, 923, 311, 424, Can.CAN_MZD_LUOMU, 749, KeyDef.RKEY_MEDIA_PROG, 54, 669, KeyDef.RKEY_OPEN, KeyDef.RKEY_res5, 299, 534, 105, 667, 488, CanCameraUI.BTN_LANDWIND_2D3D, 672, 576, 540, KeyDef.RKEY_OPEN, 486, 721, CanCameraUI.BTN_CCH9_MODE1, 46, 656, 447, 171, CanCameraUI.BTN_CCH9_MODE7, 464, 190, CanCameraUI.BTN_CHANA_ALSVINV7_MODE2, 297, KeyDef.RKEY_MEDIA_PROG, 762, 752, 533, 175, 134, 14, 381, 433, 717, 45, 111, 20, 596, 284, 736, 138, CanCameraUI.BTN_LANDWIND_3D_RIGHT_UP, 411, 877, 669, 141, 919, 45, KeyDef.SKEY_VOLDN_2, 407, 164, KeyDef.RKEY_POWER_ON, 899, 165, 726, 600, KeyDef.RKEY_RADIO_3S, 498, 655, 357, 752, 768, Can.CAN_X80_RZC, 849, CanCameraUI.BTN_LANDWIND_3D_RIGHT_DOWN, 63, 310, 863, Can.CAN_MG_ZS_WC, KeyTouch.GAMMA_MAX_NUM, 304, 282, 738, 675, 410, 389, Can.CAN_BYD_M6_DJ, 31, 121, 303, 263}};

    private PDF417ErrorCorrection() {
    }

    static int getErrorCorrectionCodewordCount(int errorCorrectionLevel) {
        if (errorCorrectionLevel >= 0 && errorCorrectionLevel <= 8) {
            return 1 << (errorCorrectionLevel + 1);
        }
        throw new IllegalArgumentException("Error correction level must be between 0 and 8!");
    }

    static int getRecommendedMinimumErrorCorrectionLevel(int n) throws WriterException {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be > 0");
        } else if (n <= 40) {
            return 2;
        } else {
            if (n <= 160) {
                return 3;
            }
            if (n <= 320) {
                return 4;
            }
            if (n <= 863) {
                return 5;
            }
            throw new WriterException("No recommendation possible");
        }
    }

    static String generateErrorCorrection(CharSequence dataCodewords, int errorCorrectionLevel) {
        int k = getErrorCorrectionCodewordCount(errorCorrectionLevel);
        char[] e = new char[k];
        int sld = dataCodewords.length();
        for (int i = 0; i < sld; i++) {
            int t1 = (dataCodewords.charAt(i) + e[e.length - 1]) % PDF417Common.NUMBER_OF_CODEWORDS;
            for (int j = k - 1; j >= 1; j--) {
                e[j] = (char) ((e[j - 1] + (929 - ((EC_COEFFICIENTS[errorCorrectionLevel][j] * t1) % PDF417Common.NUMBER_OF_CODEWORDS))) % PDF417Common.NUMBER_OF_CODEWORDS);
            }
            e[0] = (char) ((929 - ((EC_COEFFICIENTS[errorCorrectionLevel][0] * t1) % PDF417Common.NUMBER_OF_CODEWORDS)) % PDF417Common.NUMBER_OF_CODEWORDS);
        }
        StringBuilder sb = new StringBuilder(k);
        for (int j2 = k - 1; j2 >= 0; j2--) {
            if (e[j2] != 0) {
                e[j2] = (char) (929 - e[j2]);
            }
            sb.append(e[j2]);
        }
        return sb.toString();
    }
}
