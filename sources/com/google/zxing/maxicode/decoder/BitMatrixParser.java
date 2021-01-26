package com.google.zxing.maxicode.decoder;

import com.google.zxing.common.BitMatrix;
import com.lgb.canmodule.Can;
import com.ts.can.CanCameraUI;
import com.ts.factoryset.AtcDisplaySettingsUtils;
import com.ts.main.common.KeyTouch;
import com.ts.main.common.MainLight;
import com.yyw.ts70xhw.KeyDef;

final class BitMatrixParser {
    private static final int[][] BITNR = {new int[]{121, 120, 127, 126, 133, 132, 139, 138, 145, 144, 151, 150, Can.CAN_FORD_WC, Can.CAN_HONDA_WC, 163, 162, 169, 168, 175, 174, 181, 180, 187, 186, 193, 192, 199, 198, -2, -2}, new int[]{123, 122, 129, 128, 135, 134, 141, Can.CAN_BENC_ZMYT, 147, 146, 153, 152, Can.CAN_DF_WC, Can.CAN_BJ20_WC, 165, 164, 171, 170, 177, 176, 183, 182, 189, 188, 195, 194, 201, 200, KeyDef.SKEY_CALLUP_3, -3}, new int[]{125, 124, 131, 130, 137, 136, 143, 142, 149, 148, Can.CAN_DFFG_S560, Can.CAN_CC_WC, 161, 160, 167, 166, 173, 172, 179, 178, 185, 184, 191, 190, 197, 196, 203, 202, KeyDef.SKEY_CALLUP_5, KeyDef.SKEY_CALLUP_4}, new int[]{283, 282, 277, 276, 271, 270, 265, 264, 259, 258, Can.CAN_FORD_ESCORT_LY, Can.CAN_TOYOTA_SP_XP, Can.CAN_FORD_EDGE_XFY, Can.CAN_CHRYSLER_TXB, Can.CAN_SITECHDEV_CW, Can.CAN_VOLKS_XP, Can.CAN_ZH_WC, Can.CAN_LIEBAO_WC, Can.CAN_VOLVO_XFY, Can.CAN_TEANA_OLD_DJ, Can.CAN_X80_RZC, 222, 217, 216, 211, 210, Can.CAN_LEXUS_IZ, 204, KeyDef.SKEY_CALLDN_1, -3}, new int[]{285, 284, 279, 278, 273, 272, 267, 266, 261, 260, 255, Can.CAN_FLAT_RZC, Can.CAN_LUXGEN_WC, Can.CAN_RENAUL_KOLEOS_XFY, Can.CAN_MZD_TXB, Can.CAN_MZD_LUOMU, Can.CAN_NISSAN_RICH6_WC, Can.CAN_CHRYSLER_ONE_HC, Can.CAN_BYD_S6_S7, Can.CAN_CC_HF_DJ, Can.CAN_BENZ_SMART_OD, Can.CAN_ZH_H530, 219, 218, 213, 212, Can.CAN_LEXUS_ZMYT, Can.CAN_SAIL_RW550_MG6_WC, KeyDef.SKEY_CALLDN_3, KeyDef.SKEY_CALLDN_2}, new int[]{287, 286, 281, 280, 275, 274, 269, 268, 263, 262, 257, 256, Can.CAN_MG_ZS_WC, Can.CAN_NISSAN_XFY, Can.CAN_FORD_MONDEO_XFY, Can.CAN_BYD_M6_DJ, Can.CAN_GM_CAPTIVA_OD, Can.CAN_SE_DX7_RZC, Can.CAN_SGMW_WC, Can.CAN_FLAT_WC, Can.CAN_JIANGLING_MYX, Can.CAN_FORD_SYNC3, 221, 220, 215, 214, 209, 208, KeyDef.SKEY_CALLDN_4, -3}, new int[]{289, 288, 295, 294, 301, 300, 307, 306, KeyDef.RKEY_CMMB_PBC, KeyDef.RKEY_MEDIA_OSD, KeyDef.RKEY_MEDIA_STOP, KeyDef.RKEY_ANGLEDN, KeyDef.RKEY_RADIO_3S, KeyDef.RKEY_RADIO_2S, KeyDef.RKEY_RDS_PTY, KeyDef.RKEY_RDS_TA, KeyDef.RKEY_BT, KeyDef.RKEY_AVIN, 343, KeyDef.RKEY_res5, 349, 348, 355, 354, 361, 360, 367, KeyTouch.GAMMA_MAX_NUM, KeyDef.SKEY_PP_1, KeyDef.SKEY_CALLDN_5}, new int[]{291, 290, 297, 296, 303, 302, 309, 308, KeyDef.RKEY_EJECT, KeyDef.RKEY_POWER, KeyDef.RKEY_MEDIA_PROG, KeyDef.RKEY_MEDIA_SLOW, KeyDef.RKEY_RADIO_5S, KeyDef.RKEY_RADIO_4S, KeyDef.RKEY_POWER_OFF, KeyDef.RKEY_POWER_ON, KeyDef.RKEY_res2, KeyDef.RKEY_res1, 345, 344, 351, 350, 357, 356, 363, 362, 369, 368, KeyDef.SKEY_PP_2, -3}, new int[]{293, 292, 299, 298, 305, 304, 311, 310, KeyDef.RKEY_ANGLEUP, KeyDef.RKEY_OPEN, KeyDef.RKEY_RADIO_1S, KeyDef.RKEY_MEDIA_10, KeyDef.RKEY_RDS_AF, KeyDef.RKEY_RADIO_6S, KeyDef.RKEY_DEL, KeyDef.RKEY_EJECT_L, KeyDef.RKEY_res4, KeyDef.RKEY_res3, 347, 346, 353, 352, 359, 358, 365, 364, 371, AtcDisplaySettingsUtils.SPECIFIC_Y_SMALL2, KeyDef.SKEY_PP_4, KeyDef.SKEY_PP_3}, new int[]{409, 408, CanCameraUI.BTN_TRUMPCHI_GS4_MODE4, CanCameraUI.BTN_TRUMPCHI_GS4_MODE3, 397, 396, 391, 390, 79, 78, -2, -2, 13, 12, 37, 36, 2, -1, 44, 43, 109, 108, 385, 384, 379, 378, 373, 372, KeyDef.SKEY_PP_5, -3}, new int[]{411, 410, 405, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, 399, 398, 393, 392, 81, 80, 40, -2, 15, 14, 39, 38, 3, -1, -1, 45, 111, 110, 387, 386, 381, 380, 375, 374, KeyDef.SKEY_NAVI_2, KeyDef.SKEY_NAVI_1}, new int[]{413, 412, 407, 406, CanCameraUI.BTN_TRUMPCHI_GS4_MODE2, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 395, 394, 83, 82, 41, -3, -3, -3, -3, -3, 5, 4, 47, 46, 113, 112, 389, 388, 383, 382, 377, 376, KeyDef.SKEY_NAVI_3, -3}, new int[]{415, 414, 421, 420, 427, 426, 103, 102, 55, 54, 16, -3, -3, -3, -3, -3, -3, -3, 20, 19, 85, 84, 433, 432, 439, 438, 445, 444, KeyDef.SKEY_NAVI_5, KeyDef.SKEY_NAVI_4}, new int[]{417, 416, 423, 422, 429, 428, 105, 104, 57, 56, -3, -3, -3, -3, -3, -3, -3, -3, 22, 21, 87, 86, 435, 434, 441, 440, 447, 446, KeyDef.SKEY_HOME_1, -3}, new int[]{419, 418, 425, 424, 431, 430, 107, 106, 59, 58, -3, -3, -3, -3, -3, -3, -3, -3, -3, 23, 89, 88, 437, 436, 443, 442, 449, 448, KeyDef.SKEY_HOME_3, KeyDef.SKEY_HOME_2}, new int[]{481, 480, 475, 474, 469, 468, 48, -2, 30, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, 0, 53, 52, MainLight.VOL_BAR_SIZEX, 462, 457, 456, 451, 450, KeyDef.SKEY_HOME_4, -3}, new int[]{483, 482, 477, 476, 471, 470, 49, -1, -2, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -2, -1, 465, 464, 459, 458, 453, 452, KeyDef.SKEY_RETURN_1, KeyDef.SKEY_HOME_5}, new int[]{485, 484, 479, 478, 473, 472, 51, 50, 31, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, 1, -2, 42, 467, 466, 461, 460, 455, 454, KeyDef.SKEY_RETURN_2, -3}, new int[]{487, 486, 493, 492, 499, 498, 97, 96, 61, 60, -3, -3, -3, -3, -3, -3, -3, -3, -3, 26, 91, 90, CanCameraUI.BTN_HMS7_HELP_LINE, CanCameraUI.BTN_HMS7_ESC, CanCameraUI.BTN_YG9_XBS_MODE2, CanCameraUI.BTN_YG9_XBS_MODE1, 517, 516, KeyDef.SKEY_RETURN_4, KeyDef.SKEY_RETURN_3}, new int[]{489, 488, 495, 494, CanCameraUI.BTN_HMS7_MODE2, 500, 99, 98, 63, 62, -3, -3, -3, -3, -3, -3, -3, -3, 28, 27, 93, 92, 507, 506, 513, 512, 519, 518, KeyDef.SKEY_RETURN_5, -3}, new int[]{491, 490, 497, 496, CanCameraUI.BTN_HMS7_MODE4, CanCameraUI.BTN_HMS7_MODE3, 101, 100, 65, 64, 17, -3, -3, -3, -3, -3, -3, -3, 18, 29, 95, 94, 509, 508, 515, 514, CanCameraUI.BTN_GEELY_YJX6_MODE2, CanCameraUI.BTN_GEELY_YJX6_MODE1, KeyDef.SKEY_SPEECH_2, KeyDef.SKEY_SPEECH_1}, new int[]{559, 558, CanCameraUI.BTN_BLSU_T5_FULL, 552, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST8, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST7, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST2, 540, 73, 72, 32, -3, -3, -3, -3, -3, -3, 10, 67, 66, 115, 114, 535, 534, 529, 528, CanCameraUI.BTN_GEELY_YJX6_MODE4, CanCameraUI.BTN_GEELY_YJX6_MODE3, KeyDef.SKEY_SPEECH_3, -3}, new int[]{CanCameraUI.BTN_TRUMPCHI_GS7_MODE2, CanCameraUI.BTN_TRUMPCHI_GS7_MODE1, 555, 554, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST10, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST9, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST4, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST3, 75, 74, -2, -1, 7, 6, 35, 34, 11, -2, 69, 68, 117, 116, 537, 536, CanCameraUI.BTN_CHANA_ALSVINV7_MODE2, CanCameraUI.BTN_CHANA_ALSVINV7_MODE1, CanCameraUI.BTN_GEELY_YJX6_3D, CanCameraUI.BTN_GEELY_YJX6_ESC, KeyDef.SKEY_SPEECH_5, KeyDef.SKEY_SPEECH_4}, new int[]{CanCameraUI.BTN_TRUMPCHI_GS7_MODE4, CanCameraUI.BTN_TRUMPCHI_GS7_MODE3, 557, 556, 551, 550, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST5, 77, 76, -2, 33, 9, 8, 25, 24, -1, -2, 71, 70, 119, 118, 539, 538, 533, CanCameraUI.BTN_CHANA_ALSVINV7_MODE3, CanCameraUI.BTN_GEELY_YJX6_FXP, CanCameraUI.BTN_GEELY_YJX6_GJ, 849, -3}, new int[]{CanCameraUI.BTN_TRUMPCHI_GS7_MODE6, CanCameraUI.BTN_TRUMPCHI_GS7_MODE5, CanCameraUI.BTN_CHANA_CS75_MODE2, CanCameraUI.BTN_CHANA_CS75_MODE1, 577, 576, 583, 582, 589, 588, 595, 594, CanCameraUI.BTN_GOLF_WC_MODE2, 600, 607, 606, CanCameraUI.BTN_CCH9_MODE4, CanCameraUI.BTN_CCH9_MODE3, CanCameraUI.BTN_CCH9_MODE10, CanCameraUI.BTN_CCH9_MODE9, 625, 624, CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE2, CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE1, 637, 636, CanCameraUI.BTN_LANDWIND_2D_LEFT, CanCameraUI.BTN_LANDWIND_2D_REAR, 851, 850}, new int[]{CanCameraUI.BTN_TRUMPCHI_GS7_MODE8, CanCameraUI.BTN_TRUMPCHI_GS7_MODE7, CanCameraUI.BTN_CHANA_CS75_MODE4, CanCameraUI.BTN_CHANA_CS75_MODE3, 579, 578, 585, 584, CanCameraUI.BTN_VW_WC_MODE2, CanCameraUI.BTN_VW_WC_MODE1, 597, 596, CanCameraUI.BTN_GOLF_WC_MODE4, CanCameraUI.BTN_GOLF_WC_MODE3, 609, 608, CanCameraUI.BTN_CCH9_MODE6, CanCameraUI.BTN_CCH9_MODE5, 621, 620, 627, 626, CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE4, CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE3, 639, 638, CanCameraUI.BTN_LANDWIND_3D_FRONT, CanCameraUI.BTN_LANDWIND_2D_RIGHT, 852, -3}, new int[]{569, 568, CanCameraUI.BTN_CHANA_CS75_MODE6, CanCameraUI.BTN_CHANA_CS75_MODE5, CanCameraUI.BTN_CAMERY_2018_MODE2, CanCameraUI.BTN_CAMERY_2018_MODE1, 587, 586, 593, CanCameraUI.BTN_VW_WC_MODE3, 599, 598, 605, 604, CanCameraUI.BTN_CCH9_MODE2, CanCameraUI.BTN_CCH9_MODE1, CanCameraUI.BTN_CCH9_MODE8, CanCameraUI.BTN_CCH9_MODE7, CanCameraUI.BTN_CCH9_MODE14, 622, 629, 628, 635, CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE5, CanCameraUI.BTN_LANDWIND_2D_FRONT, CanCameraUI.BTN_LANDWIND_2D3D, CanCameraUI.BTN_LANDWIND_3D_RIGHT_DOWN, CanCameraUI.BTN_LANDWIND_3D_RIGHT_UP, 854, 853}, new int[]{727, 726, 721, 720, 715, 714, 709, 708, CanCameraUI.BTN_CC_WC_DIRECTION4, CanCameraUI.BTN_CC_WC_DIRECTION3, 697, 696, 691, 690, 685, 684, 679, 678, 673, 672, 667, 666, 661, 660, 655, 654, CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, CanCameraUI.BTN_LANDWIND_3D_REAR, 855, -3}, new int[]{729, 728, 723, 722, 717, 716, 711, 710, 705, 704, 699, 698, 693, 692, 687, 686, 681, 680, 675, 674, 669, 668, 663, 662, 657, 656, 651, CanCameraUI.BTN_LANDWIND_3D_LEFT_UP, 857, 856}, new int[]{731, 730, 725, 724, 719, 718, 713, 712, 707, 706, CanCameraUI.BTN_CC_WC_DIRECTION2, CanCameraUI.BTN_CC_WC_DIRECTION1, 695, 694, 689, 688, 683, 682, 677, 676, 671, 670, 665, 664, 659, 658, 653, 652, 858, -3}, new int[]{733, 732, 739, 738, 745, 744, 751, 750, 757, 756, 763, 762, KeyDef.SKEY_POWEWR_1, 768, KeyDef.SKEY_VOLUP_2, KeyDef.SKEY_VOLUP_1, KeyDef.SKEY_VOLDN_3, KeyDef.SKEY_VOLDN_2, KeyDef.SKEY_SEEKUP_4, KeyDef.SKEY_SEEKUP_3, KeyDef.SKEY_SEEKDN_5, KeyDef.SKEY_SEEKDN_4, KeyDef.SKEY_CHDN_1, KeyDef.SKEY_CHUP_5, KeyDef.SKEY_MUTE_2, KeyDef.SKEY_MUTE_1, 811, 810, 860, 859}, new int[]{735, 734, 741, 740, 747, 746, 753, 752, 759, 758, 765, 764, KeyDef.SKEY_POWEWR_3, KeyDef.SKEY_POWEWR_2, KeyDef.SKEY_VOLUP_4, KeyDef.SKEY_VOLUP_3, KeyDef.SKEY_VOLDN_5, KeyDef.SKEY_VOLDN_4, KeyDef.SKEY_SEEKDN_1, KeyDef.SKEY_SEEKUP_5, KeyDef.SKEY_CHUP_2, KeyDef.SKEY_CHUP_1, 801, 800, KeyDef.SKEY_MUTE_4, KeyDef.SKEY_MUTE_3, KeyDef.SKEY_MODE_5, 812, 861, -3}, new int[]{737, 736, 743, 742, 749, 748, 755, 754, 761, 760, 767, 766, KeyDef.SKEY_POWEWR_5, KeyDef.SKEY_POWEWR_4, KeyDef.SKEY_VOLDN_1, KeyDef.SKEY_VOLUP_5, KeyDef.SKEY_SEEKUP_2, KeyDef.SKEY_SEEKUP_1, KeyDef.SKEY_SEEKDN_3, KeyDef.SKEY_SEEKDN_2, KeyDef.SKEY_CHUP_4, KeyDef.SKEY_CHUP_3, 803, 802, KeyDef.SKEY_MODE_1, KeyDef.SKEY_MUTE_5, KeyDef.SKEY_CALLUP_2, KeyDef.SKEY_CALLUP_1, 863, 862}};
    private final BitMatrix bitMatrix;

    BitMatrixParser(BitMatrix bitMatrix2) {
        this.bitMatrix = bitMatrix2;
    }

    /* access modifiers changed from: package-private */
    public byte[] readCodewords() {
        byte[] result = new byte[144];
        int height = this.bitMatrix.getHeight();
        int width = this.bitMatrix.getWidth();
        for (int y = 0; y < height; y++) {
            int[] bitnrRow = BITNR[y];
            for (int x = 0; x < width; x++) {
                int bit = bitnrRow[x];
                if (bit >= 0 && this.bitMatrix.get(x, y)) {
                    int i = bit / 6;
                    result[i] = (byte) (result[i] | ((byte) (1 << (5 - (bit % 6)))));
                }
            }
        }
        return result;
    }
}
