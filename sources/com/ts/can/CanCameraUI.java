package com.ts.can;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.internal.view.SupportMenu;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackCarSound;
import com.ts.backcar.BackcarService;
import com.ts.can.RadarUIView;
import com.ts.can.audi.lz.CanAudiLzWithCDCarDevView;
import com.ts.can.audi.xbs.CanAudiXbsWithCDCarDevView;
import com.ts.can.audi.xhd.CanAudiWithCDExdActivity;
import com.ts.can.benc.withcd.CanBencWithCDCarFuncActivity;
import com.ts.can.benc.withcd.CanBencWithCDExdActivity;
import com.ts.can.bmw.withcd.CanBmwWithCdCarCvbsDevView;
import com.ts.can.bmw.zmyt.CanBmwZmytWithCDExdActivity;
import com.ts.can.cadillac.withcd.CanCadillacWithCDExdActivity;
import com.ts.can.cadillac.xhd.CanCadillacAtsXhdExdActivity;
import com.ts.can.gm.ats.CanCadillacAtsExdActivity;
import com.ts.can.gm.mkc.CanLincsMkcExdActivity;
import com.ts.can.gm.xt5.CanCadillacXt5ExdActivity;
import com.ts.can.landrover.zmyt.CanLandRoverZmytCarDevView;
import com.ts.can.lexus.zmyt.CanLexusZMYTCarDevView;
import com.ts.can.lexus.zmyt.h.CanLexushZmytCarDevView;
import com.ts.can.volvo.xfy.CanVolvoXfyCarDeviceActivity;
import com.ts.canview.CanVerticalBar;
import com.ts.factoryset.AtcDisplaySettingsUtils;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.ts.main.common.MainVolume;
import com.ts.main.common.ScreenSet;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.KeyDef;
import com.yyw.ts70xhw.Mcu;
import java.util.ArrayList;

public class CanCameraUI implements CustomImgView.onPaint, UserCallBack, View.OnClickListener, View.OnTouchListener {
    public static final int BTN_ACCORD9_WC_BZ = 1151;
    public static final int BTN_ACCORD9_WC_FJ = 1152;
    public static final int BTN_ACCORD9_WC_GJ = 1150;
    public static final int BTN_BAIC_HSS6_FDJ = 1160;
    public static final int BTN_BAIC_HSS6_QJQH = 1161;
    public static final int BTN_BLSU_OD_2D = 1405;
    public static final int BTN_BLSU_OD_3D = 1404;
    public static final int BTN_BLSU_OD_MODE1 = 1400;
    public static final int BTN_BLSU_OD_MODE2 = 1401;
    public static final int BTN_BLSU_OD_MODE3 = 1402;
    public static final int BTN_BLSU_OD_MODE4 = 1403;
    public static final int BTN_BLSU_RZC_T5_2D = 1670;
    public static final int BTN_BLSU_RZC_T5_3D = 1671;
    public static final int BTN_BLSU_RZC_T5_DN = 1675;
    public static final int BTN_BLSU_RZC_T5_FULL = 1673;
    public static final int BTN_BLSU_RZC_T5_L = 1676;
    public static final int BTN_BLSU_RZC_T5_R = 1677;
    public static final int BTN_BLSU_RZC_T5_RETURN = 1672;
    public static final int BTN_BLSU_RZC_T5_UP = 1674;
    public static final int BTN_BLSU_T5_2D = 550;
    public static final int BTN_BLSU_T5_3D = 551;
    public static final int BTN_BLSU_T5_FULL = 553;
    public static final int BTN_BLSU_T5_RETURN = 552;
    public static final int BTN_BYD_DJ_RADAR = 1190;
    public static final int BTN_BZ = 101;
    public static final int BTN_CAMERY_2018_MODE1 = 580;
    public static final int BTN_CAMERY_2018_MODE2 = 581;
    public static final int BTN_CCH2WC_MODE1 = 1110;
    public static final int BTN_CCH2WC_MODE2 = 1111;
    public static final int BTN_CCH2WC_MODE3 = 1112;
    public static final int BTN_CCH2_ESC = 180;
    public static final int BTN_CCH2_MODE1 = 181;
    public static final int BTN_CCH2_MODE2 = 182;
    public static final int BTN_CCH2_MODE3 = 183;
    public static final int BTN_CCH9_MODE1 = 610;
    public static final int BTN_CCH9_MODE10 = 619;
    public static final int BTN_CCH9_MODE11 = 620;
    public static final int BTN_CCH9_MODE12 = 621;
    public static final int BTN_CCH9_MODE13 = 622;
    public static final int BTN_CCH9_MODE14 = 623;
    public static final int BTN_CCH9_MODE2 = 611;
    public static final int BTN_CCH9_MODE3 = 612;
    public static final int BTN_CCH9_MODE4 = 613;
    public static final int BTN_CCH9_MODE5 = 614;
    public static final int BTN_CCH9_MODE6 = 615;
    public static final int BTN_CCH9_MODE7 = 616;
    public static final int BTN_CCH9_MODE8 = 617;
    public static final int BTN_CCH9_MODE9 = 618;
    public static final int BTN_CCHfDj_MODE1 = 1360;
    public static final int BTN_CCHfDj_MODE2 = 1361;
    public static final int BTN_CCHfDj_MODE3 = 1362;
    public static final int BTN_CCHfDj_MODE4 = 1363;
    public static final int BTN_CCHfDj_MODE5 = 1364;
    public static final int BTN_CCHfDj_MODE6 = 1365;
    public static final int BTN_CCHfDj_MODE7 = 1366;
    public static final int BTN_CC_H6_WC_DIRECTION1 = 1390;
    public static final int BTN_CC_H6_WC_DIRECTION2 = 1391;
    public static final int BTN_CC_H6_WC_DIRECTION3 = 1392;
    public static final int BTN_CC_H6_WC_DIRECTION4 = 1393;
    public static final int BTN_CC_H6_WC_DIRECTION5 = 1394;
    public static final int BTN_CC_WC_DIRECTION1 = 700;
    public static final int BTN_CC_WC_DIRECTION2 = 701;
    public static final int BTN_CC_WC_DIRECTION3 = 702;
    public static final int BTN_CC_WC_DIRECTION4 = 703;
    public static final int BTN_CHANA_ALSVINV7_MODE1 = 530;
    public static final int BTN_CHANA_ALSVINV7_MODE2 = 531;
    public static final int BTN_CHANA_ALSVINV7_MODE3 = 532;
    public static final int BTN_CHANA_COS1_WC_MODE1 = 1480;
    public static final int BTN_CHANA_COS1_WC_MODE2 = 1481;
    public static final int BTN_CHANA_COS1_WC_MODE3 = 1482;
    public static final int BTN_CHANA_CS75_MODE1 = 570;
    public static final int BTN_CHANA_CS75_MODE2 = 571;
    public static final int BTN_CHANA_CS75_MODE3 = 572;
    public static final int BTN_CHANA_CS75_MODE4 = 573;
    public static final int BTN_CHANA_CS75_MODE5 = 574;
    public static final int BTN_CHANA_CS75_MODE6 = 575;
    public static final int BTN_CHANA_CS75_MODE7 = 576;
    public static final int BTN_CHANA_OD_MODE = 1530;
    public static final int BTN_CHANA_WC_MODE = 1200;
    public static final int BTN_CHERY_WC_MODE1 = 1340;
    public static final int BTN_CHERY_WC_MODE2 = 1341;
    public static final int BTN_CHERY_WC_MODE3 = 1342;
    public static final int BTN_CHERY_WC_MODE4 = 1343;
    public static final int BTN_CS75_MODE = 151;
    public static final int BTN_CS75_WC_MODE = 900;
    public static final int BTN_DFFG_DIRECTION1 = 800;
    public static final int BTN_DFFG_DIRECTION2 = 801;
    public static final int BTN_DFFG_DIRECTION3 = 802;
    public static final int BTN_DFFG_DIRECTION4 = 803;
    public static final int BTN_DFFG_RZC_FRONT = 1370;
    public static final int BTN_DFFG_RZC_LEFT = 1372;
    public static final int BTN_DFFG_RZC_OFF = 1374;
    public static final int BTN_DFFG_RZC_REAR = 1371;
    public static final int BTN_DFFG_RZC_RIGHT = 1373;
    public static final int BTN_DISPLAY_SET = 103;
    public static final int BTN_FENGSHEN_AX7_MODE_DN = 1603;
    public static final int BTN_FENGSHEN_AX7_MODE_EXIT = 1605;
    public static final int BTN_FENGSHEN_AX7_MODE_FULL = 1604;
    public static final int BTN_FENGSHEN_AX7_MODE_LEFT = 1601;
    public static final int BTN_FENGSHEN_AX7_MODE_RIGHT = 1602;
    public static final int BTN_FENGSHEN_AX7_MODE_UP = 1600;
    public static final int BTN_FJ = 102;
    public static final int BTN_FORD_RZC_CLOSE = 1540;
    public static final int BTN_FORD_RZC_CZBC = 1542;
    public static final int BTN_FORD_RZC_PXBC = 1541;
    public static final int BTN_FORD_RZC_PXCWBC = 1543;
    public static final int BTN_FORD_WC_FJ = 1221;
    public static final int BTN_FORD_WC_GJ = 1220;
    public static final int BTN_FR_CAMERA = 1450;
    public static final int BTN_GEELYBOY_ESC = 204;
    public static final int BTN_GEELYBOY_MODE1 = 200;
    public static final int BTN_GEELYBOY_MODE2 = 201;
    public static final int BTN_GEELYBOY_MODE3 = 202;
    public static final int BTN_GEELYBOY_MODE4 = 203;
    public static final int BTN_GEELY_YJX6_3D = 525;
    public static final int BTN_GEELY_YJX6_ESC = 524;
    public static final int BTN_GEELY_YJX6_FXP = 527;
    public static final int BTN_GEELY_YJX6_GJ = 526;
    public static final int BTN_GEELY_YJX6_MODE1 = 520;
    public static final int BTN_GEELY_YJX6_MODE2 = 521;
    public static final int BTN_GEELY_YJX6_MODE3 = 522;
    public static final int BTN_GEELY_YJX6_MODE4 = 523;
    public static final int BTN_GJ = 100;
    public static final int BTN_GOLF_WC_MODE1 = 600;
    public static final int BTN_GOLF_WC_MODE2 = 601;
    public static final int BTN_GOLF_WC_MODE3 = 602;
    public static final int BTN_GOLF_WC_MODE4 = 603;
    public static final int BTN_GS5_360_1 = 111;
    public static final int BTN_GS5_360_2 = 112;
    public static final int BTN_GS5_360_3 = 113;
    public static final int BTN_GS5_360_4 = 114;
    public static final int BTN_GS5_360_5 = 115;
    public static final int BTN_GS5_MODE = 105;
    public static final int BTN_GST_BZ = 121;
    public static final int BTN_GST_FJ = 122;
    public static final int BTN_HMS7_ESC = 504;
    public static final int BTN_HMS7_HELP_LINE = 505;
    public static final int BTN_HMS7_MODE1 = 500;
    public static final int BTN_HMS7_MODE2 = 501;
    public static final int BTN_HMS7_MODE3 = 502;
    public static final int BTN_HMS7_MODE4 = 503;
    public static final int BTN_HONDAWC_BZ = 811;
    public static final int BTN_HONDAWC_FJ = 812;
    public static final int BTN_HONDAWC_GJ = 810;
    public static final int BTN_HYUNDAI_RZC_MODE1 = 1430;
    public static final int BTN_HYUNDAI_RZC_MODE10 = 1439;
    public static final int BTN_HYUNDAI_RZC_MODE2 = 1431;
    public static final int BTN_HYUNDAI_RZC_MODE3 = 1432;
    public static final int BTN_HYUNDAI_RZC_MODE4 = 1433;
    public static final int BTN_HYUNDAI_RZC_MODE5 = 1434;
    public static final int BTN_HYUNDAI_RZC_MODE6 = 1435;
    public static final int BTN_HYUNDAI_RZC_MODE7 = 1436;
    public static final int BTN_HYUNDAI_RZC_MODE8 = 1437;
    public static final int BTN_HYUNDAI_RZC_MODE9 = 1438;
    public static final int BTN_HYUNDAI_RZC_MODE_BTN = 1440;
    public static final int BTN_HYUNDAI_WC_MODE1 = 1410;
    public static final int BTN_HYUNDAI_WC_MODE10 = 1419;
    public static final int BTN_HYUNDAI_WC_MODE2 = 1411;
    public static final int BTN_HYUNDAI_WC_MODE3 = 1412;
    public static final int BTN_HYUNDAI_WC_MODE4 = 1413;
    public static final int BTN_HYUNDAI_WC_MODE5 = 1414;
    public static final int BTN_HYUNDAI_WC_MODE6 = 1415;
    public static final int BTN_HYUNDAI_WC_MODE7 = 1416;
    public static final int BTN_HYUNDAI_WC_MODE8 = 1417;
    public static final int BTN_HYUNDAI_WC_MODE9 = 1418;
    public static final int BTN_HYUNDAI_XP_FRONT_MODE1 = 1170;
    public static final int BTN_HYUNDAI_XP_FRONT_MODE2 = 1171;
    public static final int BTN_HYUNDAI_XP_FRONT_MODE3 = 1172;
    public static final int BTN_HYUNDAI_XP_FRONT_MODE4 = 1173;
    public static final int BTN_HYUNDAI_XP_REAR_MODE1 = 1180;
    public static final int BTN_HYUNDAI_XP_REAR_MODE2 = 1181;
    public static final int BTN_HYUNDAI_XP_REAR_MODE3 = 1182;
    public static final int BTN_HYUNDAI_XP_REAR_MODE4 = 1183;
    public static final int BTN_JAC_REFINE_2D = 1105;
    public static final int BTN_JAC_REFINE_3D = 1104;
    public static final int BTN_JAC_REFINE_ESC = 1107;
    public static final int BTN_JAC_REFINE_FULL = 1106;
    public static final int BTN_JAC_REFINE_MODE1 = 1100;
    public static final int BTN_JAC_REFINE_MODE2 = 1101;
    public static final int BTN_JAC_REFINE_MODE3 = 1102;
    public static final int BTN_JAC_REFINE_MODE4 = 1103;
    public static final int BTN_JAC_REFINE_WC_2D = 1705;
    public static final int BTN_JAC_REFINE_WC_3D = 1704;
    public static final int BTN_JAC_REFINE_WC_ESC = 1707;
    public static final int BTN_JAC_REFINE_WC_FULL = 1706;
    public static final int BTN_JAC_REFINE_WC_MODE1 = 1700;
    public static final int BTN_JAC_REFINE_WC_MODE2 = 1701;
    public static final int BTN_JAC_REFINE_WC_MODE3 = 1702;
    public static final int BTN_JAC_REFINE_WC_MODE4 = 1703;
    public static final int BTN_LANDWINDOD_2D3D = 1560;
    public static final int BTN_LANDWINDOD_2D_ALL = 1561;
    public static final int BTN_LANDWINDOD_2D_FRONT = 1562;
    public static final int BTN_LANDWINDOD_2D_LEFT = 1563;
    public static final int BTN_LANDWINDOD_2D_REAR = 1565;
    public static final int BTN_LANDWINDOD_2D_RIGHT = 1564;
    public static final int BTN_LANDWINDOD_3D_ALL = 1566;
    public static final int BTN_LANDWINDOD_3D_FRONT = 1567;
    public static final int BTN_LANDWINDOD_3D_LEFT_DOWN = 1570;
    public static final int BTN_LANDWINDOD_3D_LEFT_UP = 1568;
    public static final int BTN_LANDWINDOD_3D_REAR = 1572;
    public static final int BTN_LANDWINDOD_3D_RIGHT_DOWN = 1571;
    public static final int BTN_LANDWINDOD_3D_RIGHT_UP = 1569;
    public static final int BTN_LANDWIND_2D3D = 640;
    public static final int BTN_LANDWIND_2D_FRONT = 641;
    public static final int BTN_LANDWIND_2D_LEFT = 643;
    public static final int BTN_LANDWIND_2D_REAR = 642;
    public static final int BTN_LANDWIND_2D_RIGHT = 644;
    public static final int BTN_LANDWIND_3D_FRONT = 645;
    public static final int BTN_LANDWIND_3D_LEFT_DOWN = 649;
    public static final int BTN_LANDWIND_3D_LEFT_UP = 650;
    public static final int BTN_LANDWIND_3D_REAR = 648;
    public static final int BTN_LANDWIND_3D_RIGHT_DOWN = 647;
    public static final int BTN_LANDWIND_3D_RIGHT_UP = 646;
    public static final int BTN_LIFAN_MODE_1 = 171;
    public static final int BTN_LIFAN_MODE_2 = 172;
    public static final int BTN_MAGOTEN_MODE_1 = 141;
    public static final int BTN_MAGOTEN_MODE_2 = 142;
    public static final int BTN_MAGOTEN_MODE_3 = 143;
    public static final int BTN_MAGOTEN_MODE_4 = 144;
    public static final int BTN_MGZS_WC_MODE1 = 1610;
    public static final int BTN_MGZS_WC_MODE2 = 1611;
    public static final int BTN_MGZS_WC_MODE3 = 1612;
    public static final int BTN_MGZS_WC_MODE4 = 1613;
    public static final int BTN_MGZS_WC_MODE5 = 1614;
    public static final int BTN_MG_2D = 1620;
    public static final int BTN_MG_3D = 1621;
    public static final int BTN_MG_ESC = 1630;
    public static final int BTN_MG_HS = 1626;
    public static final int BTN_MG_HYS = 1627;
    public static final int BTN_MG_HZS = 1625;
    public static final int BTN_MG_QS = 1622;
    public static final int BTN_MG_QYS = 1629;
    public static final int BTN_MG_QZS = 1623;
    public static final int BTN_MG_YS = 1628;
    public static final int BTN_MG_ZS = 1624;
    public static final int BTN_MITSUBISHI_RZC_CAMERA = 1460;
    public static final int BTN_MZD_RZC_MODE = 1680;
    public static final int BTN_NISSAN_XTRAL_RVS_ASSIST1 = 540;
    public static final int BTN_NISSAN_XTRAL_RVS_ASSIST10 = 549;
    public static final int BTN_NISSAN_XTRAL_RVS_ASSIST11 = 550;
    public static final int BTN_NISSAN_XTRAL_RVS_ASSIST12 = 551;
    public static final int BTN_NISSAN_XTRAL_RVS_ASSIST13 = 552;
    public static final int BTN_NISSAN_XTRAL_RVS_ASSIST2 = 541;
    public static final int BTN_NISSAN_XTRAL_RVS_ASSIST3 = 542;
    public static final int BTN_NISSAN_XTRAL_RVS_ASSIST4 = 543;
    public static final int BTN_NISSAN_XTRAL_RVS_ASSIST5 = 544;
    public static final int BTN_NISSAN_XTRAL_RVS_ASSIST6 = 545;
    public static final int BTN_NISSAN_XTRAL_RVS_ASSIST7 = 546;
    public static final int BTN_NISSAN_XTRAL_RVS_ASSIST8 = 547;
    public static final int BTN_NISSAN_XTRAL_RVS_ASSIST9 = 548;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST1 = 1640;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST10 = 1649;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST11 = 1650;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST12 = 1651;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST13 = 1652;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST14 = 1653;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST15 = 1654;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST16 = 1655;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST17 = 1656;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST18 = 1657;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST19 = 1658;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST2 = 1641;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST20 = 1659;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST21 = 1660;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST22 = 1661;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST23 = 1662;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST3 = 1642;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST4 = 1643;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST5 = 1644;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST6 = 1645;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST7 = 1646;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST8 = 1647;
    public static final int BTN_NISSAN_XTRAL_RZC_RVS_ASSIST9 = 1648;
    public static final int BTN_PORSCHE_LZ_CAR = 1500;
    public static final int BTN_PORSCHE_LZ_DOWN = 1497;
    public static final int BTN_PORSCHE_LZ_ESC = 1502;
    public static final int BTN_PORSCHE_LZ_FRONT = 1496;
    public static final int BTN_PORSCHE_LZ_LEFT = 1498;
    public static final int BTN_PORSCHE_LZ_MODE1 = 1490;
    public static final int BTN_PORSCHE_LZ_MODE2 = 1491;
    public static final int BTN_PORSCHE_LZ_MODE3 = 1492;
    public static final int BTN_PORSCHE_LZ_MODE4 = 1493;
    public static final int BTN_PORSCHE_LZ_MODE5 = 1494;
    public static final int BTN_PORSCHE_LZ_MODE6 = 1495;
    public static final int BTN_PORSCHE_LZ_RIGHT = 1499;
    public static final int BTN_PORSCHE_LZ_SHOWCAR = 1501;
    public static final int BTN_PORSCHE_OD_MODE1 = 1690;
    public static final int BTN_PORSCHE_OD_MODE2 = 1691;
    public static final int BTN_PORSCHE_OD_MODE3 = 1692;
    public static final int BTN_PORSCHE_OD_MODE4 = 1693;
    public static final int BTN_PORSCHE_OD_MODE5 = 1694;
    public static final int BTN_PORSCHE_OD_MODE6 = 1695;
    public static final int BTN_PORSCHE_OD_MODE7 = 1696;
    public static final int BTN_PRODOC_MODE1 = 1350;
    public static final int BTN_PRODOC_MODE2 = 1351;
    public static final int BTN_PRODOC_MODE3 = 1352;
    public static final int BTN_PRODOC_MODE4 = 1353;
    public static final int BTN_PSA_RZC_ESC = 1381;
    public static final int BTN_PSA_RZC_MODE = 1380;
    public static final int BTN_RAV4_360_1 = 131;
    public static final int BTN_RAV4_360_2 = 132;
    public static final int BTN_RAV4_360_3 = 133;
    public static final int BTN_RENAULT_WC_MODE1 = 1510;
    public static final int BTN_RENAULT_WC_MODE2 = 1511;
    public static final int BTN_RENAULT_WC_MODE3 = 1512;
    public static final int BTN_RENAULT_WC_MODE4 = 1513;
    public static final int BTN_RENAULT_XP_MODE1 = 1470;
    public static final int BTN_RENAULT_XP_MODE2 = 1471;
    public static final int BTN_RENAULT_XP_MODE3 = 1472;
    public static final int BTN_RENAULT_XP_MODE4 = 1473;
    public static final int BTN_SENOVA_OD_MODE = 1330;
    public static final int BTN_SENOVA_RZC_MODE1 = 1520;
    public static final int BTN_SENOVA_RZC_MODE2 = 1521;
    public static final int BTN_SENOVA_RZC_MODE3 = 1522;
    public static final int BTN_SENOVA_RZC_MODE4 = 1523;
    public static final int BTN_SENOVA_RZC_MODE5 = 1524;
    public static final int BTN_SENOVA_RZC_MODE6 = 1525;
    public static final int BTN_SENOVA_SUB_BJ40_MODE1 = 630;
    public static final int BTN_SENOVA_SUB_BJ40_MODE2 = 631;
    public static final int BTN_SENOVA_SUB_BJ40_MODE3 = 632;
    public static final int BTN_SENOVA_SUB_BJ40_MODE4 = 633;
    public static final int BTN_SENOVA_SUB_BJ40_MODE5 = 634;
    public static final int BTN_TIGGER7_MODE_1 = 161;
    public static final int BTN_TIGGER7_MODE_2 = 162;
    public static final int BTN_TIGGER7_MODE_3 = 163;
    public static final int BTN_TIGGER7_MODE_4 = 164;
    public static final int BTN_TRUMPCHI_GS4_MODE1 = 400;
    public static final int BTN_TRUMPCHI_GS4_MODE2 = 401;
    public static final int BTN_TRUMPCHI_GS4_MODE3 = 402;
    public static final int BTN_TRUMPCHI_GS4_MODE4 = 403;
    public static final int BTN_TRUMPCHI_GS4_MODE5 = 404;
    public static final int BTN_TRUMPCHI_GS7_MODE1 = 560;
    public static final int BTN_TRUMPCHI_GS7_MODE2 = 561;
    public static final int BTN_TRUMPCHI_GS7_MODE3 = 562;
    public static final int BTN_TRUMPCHI_GS7_MODE4 = 563;
    public static final int BTN_TRUMPCHI_GS7_MODE5 = 564;
    public static final int BTN_TRUMPCHI_GS7_MODE6 = 565;
    public static final int BTN_TRUMPCHI_GS7_MODE7 = 566;
    public static final int BTN_TRUMPCHI_GS7_MODE8 = 567;
    public static final int BTN_TRUMPCHI_WC_MODE1 = 1001;
    public static final int BTN_TRUMPCHI_WC_MODE2 = 1002;
    public static final int BTN_TRUMPCHI_WC_MODE3 = 1003;
    public static final int BTN_TRUMPCHI_WC_MODE4 = 1004;
    public static final int BTN_TRUMPCHI_WC_MODE5 = 1005;
    public static final int BTN_TRUMPCHI_WC_MODE6 = 1006;
    public static final int BTN_TRUMPCHI_WC_MODE7 = 1007;
    public static final int BTN_VENUCIA_T70_ESC = 304;
    public static final int BTN_VENUCIA_T70_MODE1 = 300;
    public static final int BTN_VENUCIA_T70_MODE2 = 301;
    public static final int BTN_VENUCIA_T70_MODE3 = 302;
    public static final int BTN_VENUCIA_T70_MODE4 = 303;
    public static final int BTN_VENUCIA_WC_M50_MODE1 = 1210;
    public static final int BTN_VENUCIA_WC_M50_MODE2 = 1211;
    public static final int BTN_VENUCIA_WC_M50_MODE3 = 1212;
    public static final int BTN_VENUCIA_WC_M50_MODE4 = 1213;
    public static final int BTN_VW_WC_MODE1 = 590;
    public static final int BTN_VW_WC_MODE2 = 591;
    public static final int BTN_VW_WC_MODE3 = 592;
    public static final int BTN_WC1 = 620;
    public static final int BTN_WC2 = 621;
    public static final int BTN_WC3 = 622;
    public static final int BTN_X80_2017_MODE1 = 1130;
    public static final int BTN_X80_2017_MODE2 = 1131;
    public static final int BTN_X80_2017_MODE3 = 1132;
    public static final int BTN_X80_2017_MODE4 = 1133;
    public static final int BTN_X80_MODE1 = 190;
    public static final int BTN_X80_MODE2 = 191;
    public static final int BTN_X80_MODE3 = 192;
    public static final int BTN_X80_MODE4 = 193;
    public static final int BTN_YG9_XBS_MODE1 = 510;
    public static final int BTN_YG9_XBS_MODE2 = 511;
    public static final int BTN_YG9_XBS_MODE3 = 512;
    public static final int BTN_ZOOM = 104;
    public static final int BTN_ZOYTEESC = 1125;
    public static final int BTN_ZOYTEX5_WC_ESC = 1140;
    public static final int BTN_ZOYTEX5_WC_MODE1 = 1141;
    public static final int BTN_ZOYTEX5_WC_MODE2 = 1142;
    public static final int BTN_ZOYTEX5_WC_MODE3 = 1143;
    public static final int BTN_ZOYTEX5_WC_MODE4 = 1144;
    public static final int BTN_ZOYTE_MODE1 = 1120;
    public static final int BTN_ZOYTE_MODE2 = 1121;
    public static final int BTN_ZOYTE_MODE3 = 1122;
    public static final int BTN_ZOYTE_MODE4 = 1123;
    public static final int BTN_ZOYTE_MODE5 = 1124;
    public static final int EPS_DT = 46;
    public static final int EPS_MAX_VAL = 1080;
    public static final int ITEM_LAYOUT = 99;
    public static final String TAG = "CanCameraActivity";
    private static CanDataInfo.CAN_Msg mCarInfo = new CanDataInfo.CAN_Msg();
    protected static Context mContext;
    static CanCameraUI mInstance;
    private View.OnClickListener cameraClick = new View.OnClickListener() {
        public void onClick(View v) {
            boolean z = true;
            boolean z2 = false;
            int id = v.getId();
            if (id == R.id.can_eps_show_rtradar) {
                CanCameraUI canCameraUI = CanCameraUI.this;
                if (!CanCameraUI.this.mfgFullScr) {
                    z2 = true;
                }
                canCameraUI.mfgFullScr = z2;
                FtSet.SetRadarDis(CanFunc.b2i(CanCameraUI.this.mfgFullScr));
                CanCameraUI.this.updateEps();
                CanCameraUI.this.updateCameraSize();
            } else if (id == R.id.can_eps_show_line) {
                CanCameraUI canCameraUI2 = CanCameraUI.this;
                if (CanCameraUI.this.mBtnShowLine.isSelected()) {
                    z = false;
                }
                canCameraUI2.mfgShowLine = z;
                if (CanCameraUI.this.mfgShowLine) {
                    CanCameraUI.this.mfgShowTrack = false;
                }
                CanCameraUI.this.mBtnShowLine.setSelected(CanCameraUI.this.mfgShowLine);
                CanCameraUI.this.mBtnShowTrack.setSelected(CanCameraUI.this.mfgShowTrack);
                CanCameraUI.this.updateEps();
            } else if (id == R.id.can_eps_show_track) {
                CanCameraUI canCameraUI3 = CanCameraUI.this;
                if (CanCameraUI.this.mBtnShowTrack.isSelected()) {
                    z = false;
                }
                canCameraUI3.mfgShowTrack = z;
                if (CanCameraUI.this.mfgShowTrack) {
                    CanCameraUI.this.mfgShowLine = false;
                }
                CanCameraUI.this.mBtnShowLine.setSelected(CanCameraUI.this.mfgShowLine);
                CanCameraUI.this.mBtnShowTrack.setSelected(CanCameraUI.this.mfgShowTrack);
                CanCameraUI.this.updateEps();
            }
        }
    };
    private boolean isAutoParking = false;
    private CanDataInfo.FordActivePark mActivePark;
    private int mAvmIconTick = 10;
    private CanDataInfo.BlsuOdT5_CamSta mBlsuOdAvmData;
    private CanDataInfo.BlsuT5_CamSta mBlsuT5CamSta;
    private ParamButton mBtnBYDDJRadar;
    private ParamButton[] mBtnBaicHSS6WcMode;
    private ParamButton[] mBtnBlsuOdMode;
    private ParamButton[] mBtnBlsuOdStats;
    private ParamButton[] mBtnBlsuRzcT5;
    private ParamButton[] mBtnBlsuRzcT5Dir;
    private ParamButton[] mBtnBlsuT5;
    private ParamButton mBtnCCH2Esc;
    private ParamButton[] mBtnCCH2Mode;
    private ParamButton[] mBtnCCH2WCMode;
    private ParamButton[] mBtnCCH6WcMode;
    private ParamButton[] mBtnCCH9Bot;
    private ParamButton mBtnCCH9Car;
    private ParamButton[] mBtnCCH9Direction;
    private ParamButton[] mBtnCCH9Mode;
    private ParamButton[] mBtnCCHfDjDirection;
    private ParamButton[] mBtnCCHfDjMode;
    private ParamButton[] mBtnCCWcMode;
    private ParamButton[] mBtnCameryMode;
    private ParamButton[] mBtnChanAAlsvinV76Mode;
    private ParamButton[] mBtnChanaCos1WcMode;
    private ParamButton mBtnChanaODMode;
    private ParamButton mBtnChanaWcCamMode;
    private ParamButton[] mBtnCheryWcMode;
    private ParamButton[] mBtnCs75AvmMode;
    private ParamButton mBtnCs75Mode;
    private ParamButton mBtnCs75WcMode;
    private ParamButton[] mBtnDFFGMode;
    private ParamButton[] mBtnDFFGRzcMode;
    private Button mBtnDisplaySet;
    private ParamButton[] mBtnFengShenAx7Mode;
    private ParamButton[] mBtnFordRzcMode;
    private ParamButton[] mBtnFordWcMode;
    private ParamButton mBtnFrCamera;
    private ParamButton[] mBtnGS5Cam360;
    private ParamButton mBtnGS5Mode;
    private ParamButton mBtnGeelyBoyEsc;
    private ParamButton[] mBtnGeelyBoyMode;
    private ParamButton mBtnGeelyYjX63D;
    private ParamButton mBtnGeelyYjX6Esc;
    private ParamButton mBtnGeelyYjX6FXP;
    private ParamButton mBtnGeelyYjX6GJ;
    private ParamButton[] mBtnGeelyYjX6Mode;
    private ParamButton[] mBtnGolfWcMode;
    private CanDataInfo.H6CarSet mBtnHCarSet;
    private ParamButton mBtnHmS7Esc;
    private ParamButton mBtnHmS7Line;
    private ParamButton[] mBtnHmS7Mode;
    private ParamButton[] mBtnHyundaiRzcMode;
    private ParamButton[] mBtnHyundaiWcMode;
    private ParamButton[] mBtnHyundaiXpFrontMode;
    private ParamButton[] mBtnHyundaiXpRearMode;
    private ParamButton mBtnJacRefineEsc;
    private ParamButton[] mBtnJacRefineMode;
    private ParamButton[] mBtnJacRefineStats;
    private ParamButton mBtnJacRefineWcEsc;
    private ParamButton[] mBtnJacRefineWcMode;
    private ParamButton[] mBtnJacRefineWcStats;
    private ParamButton[] mBtnLandWind;
    private ParamButton[] mBtnLandWindOd;
    private ParamButton[] mBtnLiFanCamMode;
    private ParamButton[] mBtnMagotenMode;
    private ParamButton[] mBtnMgGs;
    private ParamButton[] mBtnMgZsWcMode;
    private ParamButton mBtnMitSubshiRzcMode;
    private ParamButton mBtnMode_HyRzc;
    private ParamButton mBtnMzdRzcMode;
    private ParamButton[] mBtnNissanRzcRvs;
    private ParamButton[] mBtnPorscheLZCar;
    private ParamButton[] mBtnPorscheLZMode;
    private ParamButton[] mBtnPraDoCam360;
    private ParamButton[] mBtnProscheOdMode;
    private ParamButton mBtnPsaRzcEsc;
    private ParamButton[] mBtnPsaRzcMode;
    private ParamButton[] mBtnRav4Cam360;
    private ParamButton[] mBtnRenaultWcMode;
    private ParamButton[] mBtnRenaultXpCamMode;
    private Button mBtnRtShowRadar;
    private ParamButton mBtnSenovaOdMode;
    private ParamButton[] mBtnSenovaRzcMode;
    private ParamButton[] mBtnSenovaSubBJ40;
    /* access modifiers changed from: private */
    public Button mBtnShowLine;
    /* access modifiers changed from: private */
    public Button mBtnShowTrack;
    private ParamButton[] mBtnTigger7Cam;
    private ParamButton[] mBtnTrumpchiGs4Mode;
    private ParamButton[] mBtnTrumpchiGs7Mode;
    private ParamButton[] mBtnTrumpchiWcMode;
    private ParamButton mBtnVenuciaEsc;
    private ParamButton[] mBtnVenuciaMode;
    private ParamButton[] mBtnVenuciaWcM50;
    private ParamButton mBtnViewBz;
    private ParamButton mBtnViewFj;
    private ParamButton mBtnViewGj;
    private ParamButton mBtnViewWC1;
    private ParamButton mBtnViewWC2;
    private ParamButton mBtnViewWC3;
    private ParamButton[] mBtnVwWcMode;
    private ParamButton[] mBtnX80CamMode;
    private ParamButton[] mBtnX80WcMode;
    private ParamButton[] mBtnXtralRvs;
    private ParamButton[] mBtnYg9Mode;
    private ParamButton mBtnZoom;
    private ParamButton mBtnZotyeEsc;
    private ParamButton[] mBtnZotyeMode;
    private ParamButton mBtnZotyeX5WcEsc;
    private ParamButton[] mBtnZotyeX5WcMode;
    private CanDataInfo.BydDjRadar mBydDjRadar;
    private CanDataInfo.GolfCarRvsCameraMode mCameraMode;
    private SurfaceView mCameraSurfaceView;
    private TextView mCameraText;
    private AutoFitTextureView mCameraView;
    private CanVerticalBar mCanVerticalBar;
    private CustomImgView mCarBg;
    private CanDataInfo.CcH2WcCamera mCcH2WcCamera;
    private CanDataInfo.CcH6WcCamerSta mCcH6WcCamerSta;
    private CanDataInfo.Cos1WcCameraSta mChanaCos1WcCameraData;
    private CanDataInfo.ChanAWcCameraSta mChanaWcCamera;
    private CanDataInfo.Cs75WcCameraSta mCs75CameraSta;
    private CanDataInfo.Dffg_RzcAvm mDffg_RzcAvm;
    private RelativeLayout mDialogLayout;
    private int mEnterCamSta = 0;
    private CameraUIView mEpsLineView;
    private CanDataInfo.FengshenAvm mFengShenAvm;
    private CanDataInfo.FordWcCameraSet mFordWcCameraData;
    private int mFrCamera = 0;
    private CanDataInfo.GM_AutoPark mGMActivePark;
    private CanDataInfo.GS5Cam mGS5CamData;
    private CanDataInfo.Geely_Camera mGeelyCameraSta;
    private CanDataInfo.GolfWcCameraSta mGolfWcCamSta;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            CanCameraUI.this.updateProgress(CanCameraUI.this.mProgressBar.getProgress() + 1);
        }
    };
    private CanDataInfo.HmS7_CarSet mHmS7Set;
    private CanDataInfo.HondaWcCameraSta mHondawcCamera = new CanDataInfo.HondaWcCameraSta();
    private CanDataInfo.HyundaiAssist mHyundaiAssist;
    private CanDataInfo.HyundaiCamera360 mHyundaiCamera;
    private CanDataInfo.HyCarAvm mHyundaiRzcData;
    private CanDataInfo.HyundaiWcAvmData mHyundaiWcAvmData;
    private ImageView mIvParkIcon;
    private CustomImgView[] mIvSubuarAids;
    private ImageView mIvWarnning;
    private CanDataInfo.JAC_AVM_DATA mJacAvmData;
    private CanDataInfo.JacWc_AvmInfo mJacWcAvmData;
    private CanDataInfo.LandWindOd_CarSet mLandWindOdCarSet = new CanDataInfo.LandWindOd_CarSet();
    private CanDataInfo.LandWind_Avm mLandwindAvm = new CanDataInfo.LandWind_Avm();
    private RelativeLayout mLayout;
    private RadarUIView mLeftSideRadar;
    private CanDataInfo.Lifan_CamMode mLiFanCamMode;
    private ArrayList<View> mListBotView = new ArrayList<>();
    private CanDataInfo.MG_GS_AVMDATA mMgGsAvmdata;
    private CanDataInfo.MgZsWc_Avm mMgZsWcAvm;
    private CustomTextView[] mNissanRzcDeclare;
    private CustomTextView[] mNissanRzcDeclare2;
    private String[] mNissanRzcDeclareArr;
    private CanDataInfo.CanTeanaAvmData mNissanRzcRvsAss;
    private CustomTextView mNissanRzcTsy;
    private String[] mNissanRzcTsyArr;
    private CustomImgView[] mNissanRzcView;
    private int mOldCamPort = -1;
    private int mOldFrCamera = -1;
    private boolean mOldXt5UI = false;
    private CustomImgView mPorscheAvmView;
    private CanDataInfo.CanPorCheLz_AvmData mPorscheLZData;
    private CanDataInfo.PorscheOdAvmData mPorscheOdData;
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;
    public int mRadarVoice = 0;
    private CanDataInfo.RenaulWcAvm mRenaultWcAvmData;
    private CanDataInfo.RenaulXpAvm mRensultXpAvmData;
    private RadarUIView mRightForeRadar;
    private LinearLayout mRightRadarLayout;
    private RadarUIView mRightRearRadar;
    private RadarUIView mRightSideRadar;
    private int mRotate = 2;
    private CanDataInfo.MgZsWc_Rvs mRvsData;
    private ScaleCameraUIView mScaleEpsLineView;
    private CanDataInfo.SenovaOd_CameraSta mSenovaOdCamera;
    private CanDataInfo.SenovaRzc_AvmData mSenovaRzcAvmData;
    private String[] mStatusValues;
    private CanDataInfo.SubuarXp_DrivingAids mSubuarAids;
    private long mTickNow;
    private CanDataInfo.GCWcCameraSta mTrumpchiWcCamera;
    private TextView mTvDialogStatus;
    private CustomImgView[] mTvHyundaiAssist;
    private TextView mTvParkStatus;
    private TextView mTvTakeCare;
    private TextView mTvTime;
    private CustomTextView mTxtMGZSrvs;
    private int mUpdateCount = 0;
    private TextView mVedioStaText;
    private CanDataInfo.VenuciaCamState mVenuciaCamState;
    private CanDataInfo.VenuciaWc_M50vBaseInfo mVenuciaWc_M50BaseInfo;
    private CanDataInfo.VenuciaWc_M50vCamera mVenuciaWc_M50vCamera;
    private CanDataInfo.VwWcAssistSet mVwWcCamera;
    private CanDataInfo.X80_Camera mX80Camera;
    private CanDataInfo.X80Wc_CamSta mX80WcCamera;
    private CustomTextView[] mXtralDeclare;
    private String[] mXtralDeclareArr;
    private CanDataInfo.CanTeanaAvmData mXtralRvsAss;
    private CustomTextView mXtralTsy;
    private String[] mXtralTsyArr;
    private CustomImgView[] mXtralView;
    private CanDataInfo.NissanWcAvmUI mXtralWcRvsAss;
    private CanDataInfo.AccordXbsCamMode mYg9Set;
    private CanDataInfo.ZtCameraSta mZtCamera;
    private CanDataInfo.PsaRzcAvm m_PsaRzcAvm;
    private boolean mbResume = false;
    /* access modifiers changed from: private */
    public boolean mfgFullScr = true;
    private boolean mfgShowCanBtn = false;
    /* access modifiers changed from: private */
    public boolean mfgShowLine = false;
    /* access modifiers changed from: private */
    public boolean mfgShowTrack = false;
    int nCurSignalSta;
    int nDelayCheck = 0;
    public int nLayoutReLoad = 0;
    int nSignalSta = 255;

    protected CanCameraUI() {
    }

    public static CanCameraUI GetInstance() {
        if (mInstance == null) {
            mInstance = new CanCameraUI();
        }
        return mInstance;
    }

    public RelativeLayout getLayout() {
        return this.mLayout;
    }

    /* access modifiers changed from: protected */
    public View findViewById(int id) {
        return this.mLayout.findViewById(id);
    }

    protected static boolean int2Bool(int val) {
        if (val == 0) {
            return false;
        }
        return true;
    }

    private void InitChanaOD(Context context) {
        this.mBtnChanaODMode = new ParamButton(context);
        this.mBtnChanaODMode.setTag(Integer.valueOf(BTN_CHANA_OD_MODE));
        this.mBtnChanaODMode.setStateUpDn(R.drawable.can_camera_mode_up, R.drawable.can_camera_mode_dn);
        this.mBtnChanaODMode.setOnClickListener(this);
        setViewPos(this.mBtnChanaODMode, 100, 465, 150, 60);
    }

    private void InitPorcheLz(Context context) {
        this.mPorscheLZData = new CanDataInfo.CanPorCheLz_AvmData();
        this.mBtnPorscheLZMode = new ParamButton[8];
        for (int i = 0; i < this.mBtnPorscheLZMode.length; i++) {
            this.mBtnPorscheLZMode[i] = new ParamButton(context);
            this.mBtnPorscheLZMode[i].setTag(Integer.valueOf(i + BTN_PORSCHE_LZ_MODE1));
            this.mBtnPorscheLZMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnPorscheLZMode[i]);
        }
        this.mBtnPorscheLZMode[0].setStateDrawable(R.drawable.can_gs5_bot_22_up, R.drawable.can_gs5_bot_22_dn, R.drawable.can_gs5_bot_22_dn);
        setViewPosBottom(this.mBtnPorscheLZMode[0], 150, 18, 82, 72);
        this.mBtnPorscheLZMode[1].setStateDrawable(R.drawable.can_gs5_bot_10_up, R.drawable.can_gs5_bot_10_dn, R.drawable.can_gs5_bot_10_dn);
        setViewPosBottom(this.mBtnPorscheLZMode[1], Can.CAN_NISSAN_XFY, 18, 82, 72);
        this.mBtnPorscheLZMode[2].setStateDrawable(R.drawable.can_gs5_bot_21_up, R.drawable.can_gs5_bot_21_dn, R.drawable.can_gs5_bot_21_dn);
        setViewPosBottom(this.mBtnPorscheLZMode[2], 350, 18, 82, 72);
        this.mBtnPorscheLZMode[3].setStateDrawable(R.drawable.can_camera_line_up, R.drawable.can_camera_line_dn, R.drawable.can_camera_line_dn);
        setViewPosBottom(this.mBtnPorscheLZMode[3], 450, 18, 82, 72);
        this.mBtnPorscheLZMode[4].setStateDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn, R.drawable.can_gs5_bot_01_dn);
        setViewPosBottom(this.mBtnPorscheLZMode[4], 550, 18, 82, 72);
        this.mBtnPorscheLZMode[5].setStateDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn, R.drawable.can_gs5_bot_02_dn);
        setViewPosBottom(this.mBtnPorscheLZMode[5], BTN_LANDWIND_3D_LEFT_UP, 18, 82, 72);
        this.mBtnPorscheLZMode[6].setStateDrawable(R.drawable.can_gs5_bot_06_up, R.drawable.can_gs5_bot_06_dn, R.drawable.can_gs5_bot_06_dn);
        this.mBtnPorscheLZMode[6].setTag(Integer.valueOf(BTN_PORSCHE_LZ_SHOWCAR));
        setViewPosBottom(this.mBtnPorscheLZMode[6], 50, 18, 82, 72);
        this.mBtnPorscheLZMode[7].setStateDrawable(R.drawable.can_camera_track_esc_up, R.drawable.can_camera_track_esc_dn, R.drawable.can_camera_track_esc_dn);
        this.mBtnPorscheLZMode[7].setTag(Integer.valueOf(BTN_PORSCHE_LZ_ESC));
        setViewPosBottom(this.mBtnPorscheLZMode[7], 909, 0, 115, 60);
        this.mCarBg = new CustomImgView(context);
        this.mCarBg.setBackgroundColor(Color.rgb(32, 32, 32));
        this.mCarBg.setVisibility(8);
        setViewPos(this.mCarBg, 0, 95, 309, 410);
        this.mBtnPorscheLZCar = new ParamButton[5];
        for (int i2 = 0; i2 < this.mBtnPorscheLZCar.length; i2++) {
            this.mBtnPorscheLZCar[i2] = new ParamButton(context);
            this.mBtnPorscheLZCar[i2].setTag(Integer.valueOf(i2 + BTN_PORSCHE_LZ_FRONT));
            this.mBtnPorscheLZCar[i2].setOnClickListener(this);
            this.mBtnPorscheLZCar[i2].setVisibility(8);
            this.mListBotView.add(this.mBtnPorscheLZCar[i2]);
        }
        this.mBtnPorscheLZCar[0].setStateDrawable(R.drawable.can_gl18_ac_icon_sh_up, R.drawable.can_gl18_ac_icon_sh_dn, R.drawable.can_gl18_ac_icon_sh_dn);
        setViewPos(this.mBtnPorscheLZCar[0], 100, 95, 108, 99);
        this.mBtnPorscheLZCar[0].setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                CanJni.PorscheLzAvmSet(12);
                return true;
            }
        });
        this.mBtnPorscheLZCar[1].setStateDrawable(R.drawable.can_gl18_ac_icon_xia_up, R.drawable.can_gl18_ac_icon_xia_dn, R.drawable.can_gl18_ac_icon_xia_dn);
        setViewPos(this.mBtnPorscheLZCar[1], 100, 406, 108, 99);
        this.mBtnPorscheLZCar[1].setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                CanJni.PorscheLzAvmSet(13);
                return true;
            }
        });
        this.mBtnPorscheLZCar[2].setStateDrawable(R.drawable.can_gl18_ac_prv_up, R.drawable.can_gl18_ac_prv_dn, R.drawable.can_gl18_ac_prv_dn);
        setViewPos(this.mBtnPorscheLZCar[2], 0, Can.CAN_NISSAN_XFY, 108, 99);
        this.mBtnPorscheLZCar[3].setStateDrawable(R.drawable.can_gl18_ac_next_up, R.drawable.can_gl18_ac_next_dn, R.drawable.can_gl18_ac_next_dn);
        setViewPos(this.mBtnPorscheLZCar[3], 201, Can.CAN_NISSAN_XFY, 108, 99);
        this.mBtnPorscheLZCar[4].setStateDrawable(R.drawable.can_radar_set_car, R.drawable.can_radar_set_car, R.drawable.can_radar_set_car);
        setViewPos(this.mBtnPorscheLZCar[4], 108, 194, 93, 212);
        if (CanJni.GetSubType() == 1) {
            this.mPorscheAvmView = new CustomImgView(context);
            this.mListBotView.add(this.mPorscheAvmView);
            this.mPorscheAvmView.setBackgroundResource(R.drawable.can_porsche_avm_car);
            setViewPos(this.mPorscheAvmView, 75, 112, Can.CAN_BYD_M6_DJ, 508);
        }
    }

    private void InitPorcheOd(Context context) {
        this.mPorscheOdData = new CanDataInfo.PorscheOdAvmData();
        this.mBtnProscheOdMode = new ParamButton[7];
        for (int i = 0; i < this.mBtnProscheOdMode.length; i++) {
            this.mBtnProscheOdMode[i] = new ParamButton(context);
            this.mBtnProscheOdMode[i].setTag(Integer.valueOf(i + BTN_PORSCHE_OD_MODE1));
            this.mBtnProscheOdMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnProscheOdMode[i]);
        }
        this.mBtnProscheOdMode[0].setStateDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn, R.drawable.can_gs5_bot_01_dn);
        setViewPosBottom(this.mBtnProscheOdMode[0], 150, 18, 82, 72);
        this.mBtnProscheOdMode[1].setStateDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn, R.drawable.can_gs5_bot_02_dn);
        setViewPosBottom(this.mBtnProscheOdMode[1], Can.CAN_NISSAN_XFY, 18, 82, 72);
        this.mBtnProscheOdMode[2].setStateDrawable(R.drawable.can_gs5_bot_19_up, R.drawable.can_gs5_bot_19_dn, R.drawable.can_gs5_bot_19_dn);
        setViewPosBottom(this.mBtnProscheOdMode[2], 350, 18, 82, 72);
        this.mBtnProscheOdMode[3].setStateDrawable(R.drawable.can_gs5_bot_11_up, R.drawable.can_gs5_bot_11_dn, R.drawable.can_gs5_bot_11_dn);
        setViewPosBottom(this.mBtnProscheOdMode[3], 450, 18, 82, 72);
        this.mBtnProscheOdMode[4].setStateDrawable(R.drawable.can_gs5_bot_16_up, R.drawable.can_gs5_bot_16_dn, R.drawable.can_gs5_bot_16_dn);
        setViewPosBottom(this.mBtnProscheOdMode[4], 550, 18, 82, 72);
        this.mBtnProscheOdMode[5].setStateDrawable(R.drawable.can_gs5_bot_10_up, R.drawable.can_gs5_bot_10_dn, R.drawable.can_gs5_bot_10_dn);
        setViewPosBottom(this.mBtnProscheOdMode[5], BTN_LANDWIND_3D_LEFT_UP, 18, 82, 72);
        this.mBtnProscheOdMode[6].setStateDrawable(R.drawable.can_camera_track_esc_up, R.drawable.can_camera_track_esc_dn, R.drawable.can_camera_track_esc_dn);
        setViewPosBottom(this.mBtnProscheOdMode[6], 909, 0, 115, 60);
    }

    private void InitChanaCos1Wc(Context context) {
        this.mChanaCos1WcCameraData = new CanDataInfo.Cos1WcCameraSta();
        this.mBtnChanaCos1WcMode = new ParamButton[3];
        for (int i = 0; i < this.mBtnChanaCos1WcMode.length; i++) {
            this.mBtnChanaCos1WcMode[i] = new ParamButton(context);
            this.mBtnChanaCos1WcMode[i].setTag(Integer.valueOf(i + BTN_CHANA_COS1_WC_MODE1));
            this.mBtnChanaCos1WcMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnChanaCos1WcMode[i]);
            this.mBtnChanaCos1WcMode[i].setVisibility(8);
            this.mBtnChanaCos1WcMode[i].setStateDrawable(R.drawable.can_oj_rect_up, R.drawable.can_oj_rect_dn, R.drawable.can_oj_rect_dn);
        }
        this.mBtnChanaCos1WcMode[0].setText(R.string.can_ptms);
        setViewPos(this.mBtnChanaCos1WcMode[0], 56, 500, 161, 91);
        this.mBtnChanaCos1WcMode[1].setText(R.string.can_pxms);
        setViewPos(this.mBtnChanaCos1WcMode[1], Can.CAN_SGMW_WC, 500, 161, 91);
        this.mBtnChanaCos1WcMode[2].setText(R.string.can_fzxgb);
        setViewPos(this.mBtnChanaCos1WcMode[2], 409, 500, 161, 91);
    }

    private void InitBlsuOd(Context context) {
        this.mBlsuOdAvmData = new CanDataInfo.BlsuOdT5_CamSta();
        this.mBtnBlsuOdMode = new ParamButton[4];
        this.mBtnBlsuOdStats = new ParamButton[2];
        for (int i = 0; i < this.mBtnBlsuOdMode.length; i++) {
            this.mBtnBlsuOdMode[i] = new ParamButton(context);
            this.mBtnBlsuOdMode[i].setTag(Integer.valueOf(i + BTN_BLSU_OD_MODE1));
            this.mBtnBlsuOdMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnBlsuOdMode[i]);
        }
        for (int i2 = 0; i2 < this.mBtnBlsuOdStats.length; i2++) {
            this.mBtnBlsuOdStats[i2] = new ParamButton(context);
            this.mBtnBlsuOdStats[i2].setTag(Integer.valueOf(i2 + BTN_BLSU_OD_3D));
            this.mBtnBlsuOdStats[i2].setOnClickListener(this);
            this.mListBotView.add(this.mBtnBlsuOdStats[i2]);
        }
        this.mBtnBlsuOdStats[0].setDrawable(R.drawable.can_rh7_3d_up, R.drawable.can_rh7_3d_dn);
        setViewPos(this.mBtnBlsuOdStats[0], 41, 496, 90, 90);
        this.mBtnBlsuOdStats[1].setDrawable(R.drawable.can_rh7_2d_up, R.drawable.can_rh7_2d_dn);
        setViewPos(this.mBtnBlsuOdStats[1], 162, 496, 90, 90);
        this.mBtnBlsuOdMode[0].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnBlsuOdMode[0], 422, 508, 82, 72);
        this.mBtnBlsuOdMode[1].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnBlsuOdMode[1], BTN_NISSAN_XTRAL_RVS_ASSIST3, 508, 82, 72);
        this.mBtnBlsuOdMode[2].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnBlsuOdMode[2], 662, 508, 82, 72);
        this.mBtnBlsuOdMode[3].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnBlsuOdMode[3], KeyDef.SKEY_VOLDN_4, 508, 82, 72);
    }

    private void InitHyundaiWc(Context context) {
        this.mHyundaiWcAvmData = new CanDataInfo.HyundaiWcAvmData();
        this.mBtnHyundaiWcMode = new ParamButton[10];
        for (int i = 0; i < this.mBtnHyundaiWcMode.length; i++) {
            this.mBtnHyundaiWcMode[i] = new ParamButton(context);
            this.mBtnHyundaiWcMode[i].setTag(Integer.valueOf(i + BTN_HYUNDAI_WC_MODE1));
            this.mBtnHyundaiWcMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnHyundaiWcMode[i]);
            this.mBtnHyundaiWcMode[i].setVisibility(8);
        }
        this.mBtnHyundaiWcMode[0].setDrawable(R.drawable.can_gs5_bot_19_up, R.drawable.can_gs5_bot_19_dn);
        setViewPos(this.mBtnHyundaiWcMode[0], 31, 508, 82, 72);
        this.mBtnHyundaiWcMode[1].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnHyundaiWcMode[1], 151, 508, 82, 72);
        this.mBtnHyundaiWcMode[2].setDrawable(R.drawable.can_gs5_bot_20_up, R.drawable.can_gs5_bot_20_dn);
        setViewPos(this.mBtnHyundaiWcMode[2], 271, 508, 82, 72);
        this.mBtnHyundaiWcMode[3].setDrawable(R.drawable.can_gs5_bot_03_up, R.drawable.can_gs5_bot_03_dn);
        setViewPos(this.mBtnHyundaiWcMode[3], 391, 508, 82, 72);
        this.mBtnHyundaiWcMode[4].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnHyundaiWcMode[4], BTN_YG9_XBS_MODE2, 508, 82, 72);
        this.mBtnHyundaiWcMode[5].setDrawable(R.drawable.can_gs5_bot_16_up, R.drawable.can_gs5_bot_16_dn);
        setViewPos(this.mBtnHyundaiWcMode[5], 31, 508, 82, 72);
        this.mBtnHyundaiWcMode[6].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnHyundaiWcMode[6], 151, 508, 82, 72);
        this.mBtnHyundaiWcMode[7].setDrawable(R.drawable.can_gs5_bot_17_up, R.drawable.can_gs5_bot_17_dn);
        setViewPos(this.mBtnHyundaiWcMode[7], 271, 508, 82, 72);
        this.mBtnHyundaiWcMode[8].setDrawable(R.drawable.can_gs5_bot_18_up, R.drawable.can_gs5_bot_18_dn);
        setViewPos(this.mBtnHyundaiWcMode[8], 391, 508, 82, 72);
        this.mBtnHyundaiWcMode[9].setDrawable(R.drawable.can_gs5_bot_05_up, R.drawable.can_gs5_bot_05_dn);
        setViewPos(this.mBtnHyundaiWcMode[9], BTN_YG9_XBS_MODE2, 508, 82, 72);
    }

    private void InitHyundaiRzc(Context context) {
        this.mHyundaiRzcData = new CanDataInfo.HyCarAvm();
        this.mBtnHyundaiRzcMode = new ParamButton[10];
        for (int i = 0; i < this.mBtnHyundaiRzcMode.length; i++) {
            this.mBtnHyundaiRzcMode[i] = new ParamButton(context);
            this.mBtnHyundaiRzcMode[i].setTag(Integer.valueOf(i + BTN_HYUNDAI_RZC_MODE1));
            this.mBtnHyundaiRzcMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnHyundaiRzcMode[i]);
            this.mBtnHyundaiRzcMode[i].setVisibility(8);
        }
        this.mBtnHyundaiRzcMode[0].setDrawable(R.drawable.can_gs5_bot_19_up, R.drawable.can_gs5_bot_19_dn);
        setViewPos(this.mBtnHyundaiRzcMode[0], 31, 508, 82, 72);
        this.mBtnHyundaiRzcMode[1].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnHyundaiRzcMode[1], 151, 508, 82, 72);
        this.mBtnHyundaiRzcMode[2].setDrawable(R.drawable.can_gs5_bot_20_up, R.drawable.can_gs5_bot_20_dn);
        setViewPos(this.mBtnHyundaiRzcMode[2], 271, 508, 82, 72);
        this.mBtnHyundaiRzcMode[3].setDrawable(R.drawable.can_gs5_bot_03_up, R.drawable.can_gs5_bot_03_dn);
        setViewPos(this.mBtnHyundaiRzcMode[3], 391, 508, 82, 72);
        this.mBtnHyundaiRzcMode[4].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnHyundaiRzcMode[4], BTN_YG9_XBS_MODE2, 508, 82, 72);
        this.mBtnHyundaiRzcMode[5].setDrawable(R.drawable.can_gs5_bot_16_up, R.drawable.can_gs5_bot_16_dn);
        setViewPos(this.mBtnHyundaiRzcMode[5], 31, 508, 82, 72);
        this.mBtnHyundaiRzcMode[6].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnHyundaiRzcMode[6], 151, 508, 82, 72);
        this.mBtnHyundaiRzcMode[7].setDrawable(R.drawable.can_gs5_bot_17_up, R.drawable.can_gs5_bot_17_dn);
        setViewPos(this.mBtnHyundaiRzcMode[7], 271, 508, 82, 72);
        this.mBtnHyundaiRzcMode[8].setDrawable(R.drawable.can_gs5_bot_18_up, R.drawable.can_gs5_bot_18_dn);
        setViewPos(this.mBtnHyundaiRzcMode[8], 391, 508, 82, 72);
        this.mBtnHyundaiRzcMode[9].setDrawable(R.drawable.can_gs5_bot_05_up, R.drawable.can_gs5_bot_05_dn);
        setViewPos(this.mBtnHyundaiRzcMode[9], BTN_YG9_XBS_MODE2, 508, 82, 72);
    }

    private void InitBydDJRadar(Context context) {
        this.mBydDjRadar = new CanDataInfo.BydDjRadar();
        this.mBtnBYDDJRadar = new ParamButton(context);
        this.mBtnBYDDJRadar.setOnTouchListener(this);
        this.mBtnBYDDJRadar.setTag(Integer.valueOf(BTN_BYD_DJ_RADAR));
        this.mBtnBYDDJRadar.setDrawable(R.drawable.can_eps_park_up, R.drawable.can_eps_park_dn);
        setViewPos(this.mBtnBYDDJRadar, 111, 500, 82, 72);
        this.mListBotView.add(this.mBtnBYDDJRadar);
    }

    private void InitGolfWcMode(Context context) {
        this.mBtnGolfWcMode = new ParamButton[4];
        this.mGolfWcCamSta = new CanDataInfo.GolfWcCameraSta();
        for (int i = 0; i < this.mBtnGolfWcMode.length; i++) {
            this.mBtnGolfWcMode[i] = new ParamButton(context);
            this.mBtnGolfWcMode[i].setOnClickListener(this);
            this.mBtnGolfWcMode[i].setTag(Integer.valueOf(i + 600));
            this.mListBotView.add(this.mBtnGolfWcMode[i]);
        }
        this.mBtnGolfWcMode[0].setDrawable(R.drawable.can_gs5_bot_05_up, R.drawable.can_gs5_bot_05_dn);
        setViewPos(this.mBtnGolfWcMode[0], 111, 500, 82, 72);
        this.mBtnGolfWcMode[1].setDrawable(R.drawable.can_gs5_bot_04_up, R.drawable.can_gs5_bot_04_dn);
        setViewPos(this.mBtnGolfWcMode[1], Can.CAN_FLAT_WC, 500, 82, 72);
        this.mBtnGolfWcMode[2].setDrawable(R.drawable.can_gs5_bot_06_up, R.drawable.can_gs5_bot_06_dn);
        setViewPos(this.mBtnGolfWcMode[2], 352, 500, 82, 72);
        this.mBtnGolfWcMode[3].setDrawable(R.drawable.can_gs5_bot_07_up, R.drawable.can_gs5_bot_07_dn);
        setViewPos(this.mBtnGolfWcMode[3], 472, 500, 82, 72);
    }

    public void InitHondaWc(Context context) {
        this.mBtnViewGj = new ParamButton(context);
        this.mBtnViewGj.setTag(810);
        this.mBtnViewGj.setStateDrawable(R.drawable.can_view_gj_up, R.drawable.can_view_gj_dn, R.drawable.can_view_gj_dn);
        this.mBtnViewGj.setOnClickListener(this);
        this.mBtnViewBz = new ParamButton(context);
        this.mBtnViewBz.setTag(811);
        this.mBtnViewBz.setStateDrawable(R.drawable.can_view_bz_up, R.drawable.can_view_bz_dn, R.drawable.can_view_bz_dn);
        this.mBtnViewBz.setOnClickListener(this);
        this.mBtnViewFj = new ParamButton(context);
        this.mBtnViewFj.setTag(812);
        this.mBtnViewFj.setStateDrawable(R.drawable.can_view_fj_up, R.drawable.can_view_fj_dn, R.drawable.can_view_fj_dn);
        this.mBtnViewFj.setOnClickListener(this);
        setViewPos(this.mBtnViewGj, 47, 440, 110, 104);
        setViewPos(this.mBtnViewBz, 164, 440, 110, 104);
        setViewPos(this.mBtnViewFj, 281, 440, 110, 104);
    }

    public void InitHondaAccord9Wc(Context context) {
        this.mBtnViewGj = new ParamButton(context);
        this.mBtnViewGj.setTag(Integer.valueOf(BTN_ACCORD9_WC_GJ));
        this.mBtnViewGj.setStateDrawable(R.drawable.can_view_gj_up, R.drawable.can_view_gj_dn, R.drawable.can_view_gj_dn);
        this.mBtnViewGj.setOnClickListener(this);
        this.mBtnViewBz = new ParamButton(context);
        this.mBtnViewBz.setTag(Integer.valueOf(BTN_ACCORD9_WC_BZ));
        this.mBtnViewBz.setStateDrawable(R.drawable.can_view_bz_up, R.drawable.can_view_bz_dn, R.drawable.can_view_bz_dn);
        this.mBtnViewBz.setOnClickListener(this);
        this.mBtnViewFj = new ParamButton(context);
        this.mBtnViewFj.setTag(Integer.valueOf(BTN_ACCORD9_WC_FJ));
        this.mBtnViewFj.setStateDrawable(R.drawable.can_view_fj_up, R.drawable.can_view_fj_dn, R.drawable.can_view_fj_dn);
        this.mBtnViewFj.setOnClickListener(this);
        setViewPos(this.mBtnViewGj, 47, 440, 110, 104);
        setViewPos(this.mBtnViewBz, 164, 440, 110, 104);
        setViewPos(this.mBtnViewFj, 281, 440, 110, 104);
    }

    public void InitHondaDA(Context context) {
        this.mBtnViewGj = new ParamButton(context);
        this.mBtnViewGj.setTag(100);
        this.mBtnViewGj.setStateUpDn(R.drawable.can_view_gj_up, R.drawable.can_view_gj_dn);
        this.mBtnViewGj.setOnClickListener(this);
        this.mBtnViewBz = new ParamButton(context);
        this.mBtnViewBz.setTag(101);
        this.mBtnViewBz.setStateUpDn(R.drawable.can_view_bz_up, R.drawable.can_view_bz_dn);
        this.mBtnViewBz.setOnClickListener(this);
        this.mBtnViewFj = new ParamButton(context);
        this.mBtnViewFj.setTag(102);
        this.mBtnViewFj.setStateUpDn(R.drawable.can_view_fj_up, R.drawable.can_view_fj_dn);
        this.mBtnViewFj.setOnClickListener(this);
        setViewPos(this.mBtnViewGj, 47, 440, 110, 104);
        setViewPos(this.mBtnViewBz, 164, 440, 110, 104);
        setViewPos(this.mBtnViewFj, 281, 440, 110, 104);
    }

    public void InitToyotaWC(Context context) {
        this.mBtnViewWC1 = new ParamButton(context);
        this.mBtnViewWC1.setTag(620);
        this.mBtnViewWC1.setBackgroundColor(0);
        this.mBtnViewWC1.setOnTouchListener(this);
        this.mBtnViewWC2 = new ParamButton(context);
        this.mBtnViewWC2.setTag(621);
        this.mBtnViewWC2.setBackgroundColor(0);
        this.mBtnViewWC2.setOnTouchListener(this);
        this.mBtnViewWC3 = new ParamButton(context);
        this.mBtnViewWC3.setTag(622);
        this.mBtnViewWC3.setBackgroundColor(0);
        this.mBtnViewWC3.setOnTouchListener(this);
        if (MainSet.GetScreenType() == 10) {
            setViewPos(this.mBtnViewWC1, 91, 588, 138, 125);
            setViewPos(this.mBtnViewWC2, 731, 588, 138, 125);
            setViewPos(this.mBtnViewWC3, 1051, 588, 138, 125);
            return;
        }
        setViewPos(this.mBtnViewWC1, 73, 490, 110, 104);
        setViewPos(this.mBtnViewWC2, 585, 490, 110, 104);
        setViewPos(this.mBtnViewWC3, KeyDef.SKEY_RETURN_3, 490, 110, 104);
    }

    public void InitFord(Context context) {
        this.mBtnZoom = new ParamButton(context);
        this.mBtnZoom.setTag(104);
        this.mBtnZoom.setStateUpDn(R.drawable.can_jnh_search_up, R.drawable.can_jnh_search_dn);
        this.mBtnZoom.setOnClickListener(this);
        setViewPos(this.mBtnZoom, 83, 440, 112, 112);
    }

    public void InitGS5Mode(Context context) {
        this.mBtnGS5Mode = new ParamButton(context);
        this.mBtnGS5Mode.setTag(105);
        this.mBtnGS5Mode.setStateUpDn(R.drawable.can_camera_mode_up, R.drawable.can_camera_mode_dn);
        this.mBtnGS5Mode.setOnClickListener(this);
        setViewPos(this.mBtnGS5Mode, 100, 465, Can.CAN_LEXUS_ZMYT, 79);
    }

    public void InitGS5Super(Context context) {
        this.mBtnGS5Cam360 = new ParamButton[5];
        this.mGS5CamData = new CanDataInfo.GS5Cam();
        for (int i = 0; i < 5; i++) {
            this.mBtnGS5Cam360[i] = new ParamButton(context);
            this.mBtnGS5Cam360[i].setTag(Integer.valueOf(i + 111));
            this.mBtnGS5Cam360[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnGS5Cam360[i]);
        }
        this.mBtnGS5Cam360[0].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnGS5Cam360[0], 11, 508, 82, 72);
        this.mBtnGS5Cam360[1].setDrawable(R.drawable.can_gs5_bot_03_up, R.drawable.can_gs5_bot_03_dn);
        setViewPos(this.mBtnGS5Cam360[1], 131, 508, 82, 72);
        this.mBtnGS5Cam360[2].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnGS5Cam360[2], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnGS5Cam360[3].setDrawable(R.drawable.can_gs5_bot_04_up, R.drawable.can_gs5_bot_04_dn);
        setViewPos(this.mBtnGS5Cam360[3], 372, 508, 82, 72);
        this.mBtnGS5Cam360[4].setDrawable(R.drawable.can_gs5_bot_05_up, R.drawable.can_gs5_bot_05_dn);
        setViewPos(this.mBtnGS5Cam360[4], 492, 508, 82, 72);
    }

    public void InitCrosstour(Context context) {
        this.mBtnViewBz = new ParamButton(context);
        this.mBtnViewBz.setTag(121);
        this.mBtnViewBz.setStateUpDn(R.drawable.can_view_bz_up, R.drawable.can_view_bz_dn);
        this.mBtnViewBz.setOnClickListener(this);
        this.mBtnViewFj = new ParamButton(context);
        this.mBtnViewFj.setTag(122);
        this.mBtnViewFj.setStateUpDn(R.drawable.can_view_fj_up, R.drawable.can_view_fj_dn);
        this.mBtnViewFj.setOnClickListener(this);
        setViewPos(this.mBtnViewBz, 47, 440, 110, 104);
        setViewPos(this.mBtnViewFj, 164, 440, 110, 104);
    }

    public void InitRav4(Context context) {
        this.mBtnRav4Cam360 = new ParamButton[3];
        this.mBtnRav4Cam360[0] = new ParamButton(context);
        this.mBtnRav4Cam360[0].setTag(131);
        this.mBtnRav4Cam360[0].setBackgroundColor(0);
        this.mBtnRav4Cam360[0].setOnClickListener(this);
        this.mBtnRav4Cam360[1] = new ParamButton(context);
        this.mBtnRav4Cam360[1].setTag(132);
        this.mBtnRav4Cam360[1].setBackgroundColor(0);
        this.mBtnRav4Cam360[1].setOnClickListener(this);
        this.mBtnRav4Cam360[2] = new ParamButton(context);
        this.mBtnRav4Cam360[2].setTag(133);
        this.mBtnRav4Cam360[2].setBackgroundColor(0);
        this.mBtnRav4Cam360[2].setOnClickListener(this);
        if (MainSet.GetScreenType() == 3) {
            setViewPos(this.mBtnRav4Cam360[0], 32, 440, 196, 111);
            setViewPos(this.mBtnRav4Cam360[1], 286, 440, 196, 111);
            setViewPos(this.mBtnRav4Cam360[2], BTN_CHANA_CS75_MODE3, 440, 196, 111);
        } else if (MainSet.GetScreenType() == 10) {
            setViewPos(this.mBtnRav4Cam360[0], 40, 578, Can.CAN_FORD_MONDEO_XFY, 133);
            setViewPos(this.mBtnRav4Cam360[1], 678, 578, Can.CAN_FORD_MONDEO_XFY, 133);
            setViewPos(this.mBtnRav4Cam360[2], BTN_TRUMPCHI_WC_MODE3, 578, Can.CAN_FORD_MONDEO_XFY, 133);
        } else {
            setViewPos(this.mBtnRav4Cam360[0], 32, 482, 196, 111);
            setViewPos(this.mBtnRav4Cam360[1], BTN_NISSAN_XTRAL_RVS_ASSIST3, 482, 196, 111);
            setViewPos(this.mBtnRav4Cam360[2], 802, 482, 196, 111);
        }
    }

    public void InitPraDo(Context context) {
        this.mBtnPraDoCam360 = new ParamButton[4];
        this.mBtnPraDoCam360[0] = new ParamButton(context);
        this.mBtnPraDoCam360[0].setTag(Integer.valueOf(BTN_PRODOC_MODE1));
        this.mBtnPraDoCam360[0].setBackgroundColor(0);
        this.mBtnPraDoCam360[0].setOnClickListener(this);
        this.mBtnPraDoCam360[1] = new ParamButton(context);
        this.mBtnPraDoCam360[1].setTag(Integer.valueOf(BTN_PRODOC_MODE2));
        this.mBtnPraDoCam360[1].setBackgroundColor(0);
        this.mBtnPraDoCam360[1].setOnClickListener(this);
        this.mBtnPraDoCam360[2] = new ParamButton(context);
        this.mBtnPraDoCam360[2].setTag(Integer.valueOf(BTN_PRODOC_MODE3));
        this.mBtnPraDoCam360[2].setBackgroundColor(0);
        this.mBtnPraDoCam360[2].setOnClickListener(this);
        this.mBtnPraDoCam360[3] = new ParamButton(context);
        this.mBtnPraDoCam360[3].setTag(Integer.valueOf(BTN_PRODOC_MODE4));
        this.mBtnPraDoCam360[3].setBackgroundColor(0);
        this.mBtnPraDoCam360[3].setOnClickListener(this);
        setViewPos(this.mBtnPraDoCam360[0], 20, 20, 196, 111);
        if (MainSet.GetScreenType() == 3) {
            setViewPos(this.mBtnPraDoCam360[1], 20, 440, 196, 111);
            setViewPos(this.mBtnPraDoCam360[2], BTN_CHANA_CS75_MODE3, 440, 196, 111);
            setViewPos(this.mBtnPraDoCam360[3], BTN_CHANA_CS75_MODE3, 20, 196, 111);
        } else if (MainSet.GetScreenType() == 10) {
            setViewPos(this.mBtnPraDoCam360[1], 25, 578, Can.CAN_FORD_MONDEO_XFY, 133);
            setViewPos(this.mBtnPraDoCam360[2], BTN_TRUMPCHI_WC_MODE3, 578, Can.CAN_FORD_MONDEO_XFY, 133);
            setViewPos(this.mBtnPraDoCam360[3], BTN_TRUMPCHI_WC_MODE3, 24, Can.CAN_FORD_MONDEO_XFY, 133);
        } else {
            setViewPos(this.mBtnPraDoCam360[1], 20, 482, 196, 111);
            setViewPos(this.mBtnPraDoCam360[2], 802, 482, 196, 111);
            setViewPos(this.mBtnPraDoCam360[3], 802, 20, 196, 111);
        }
    }

    public void InitGolfMagoten(Context context) {
        this.mBtnMagotenMode = new ParamButton[4];
        this.mCameraMode = new CanDataInfo.GolfCarRvsCameraMode();
        for (int i = 0; i < this.mBtnMagotenMode.length; i++) {
            this.mBtnMagotenMode[i] = new ParamButton(context);
            this.mBtnMagotenMode[i].setTag(Integer.valueOf(i + 141));
            this.mBtnMagotenMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnMagotenMode[i]);
        }
        this.mBtnMagotenMode[0].setDrawable(R.drawable.can_gs5_bot_05_up, R.drawable.can_gs5_bot_05_dn);
        setViewPos(this.mBtnMagotenMode[0], 131, 508, 82, 72);
        this.mBtnMagotenMode[1].setDrawable(R.drawable.can_gs5_bot_04_up, R.drawable.can_gs5_bot_04_dn);
        setViewPos(this.mBtnMagotenMode[1], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnMagotenMode[2].setDrawable(R.drawable.can_gs5_bot_06_up, R.drawable.can_gs5_bot_06_dn);
        setViewPos(this.mBtnMagotenMode[2], 372, 508, 82, 72);
        this.mBtnMagotenMode[3].setDrawable(R.drawable.can_gs5_bot_07_up, R.drawable.can_gs5_bot_07_dn);
        setViewPos(this.mBtnMagotenMode[3], 492, 508, 82, 72);
    }

    public void InitZoyte(Context context) {
        this.mBtnZotyeMode = new ParamButton[5];
        this.mZtCamera = new CanDataInfo.ZtCameraSta();
        this.mBtnZotyeEsc = new ParamButton(context);
        this.mBtnZotyeEsc.setTag(Integer.valueOf(BTN_ZOYTEESC));
        this.mBtnZotyeEsc.setOnClickListener(this);
        this.mBtnZotyeEsc.setDrawable(R.drawable.can_camera_track_esc_up, R.drawable.can_camera_track_esc_dn);
        this.mListBotView.add(this.mBtnZotyeEsc);
        setViewPos(this.mBtnZotyeEsc, 909, 540, 115, 60);
        for (int i = 0; i < this.mBtnZotyeMode.length; i++) {
            this.mBtnZotyeMode[i] = new ParamButton(context);
            this.mBtnZotyeMode[i].setTag(Integer.valueOf(i + BTN_ZOYTE_MODE1));
            this.mBtnZotyeMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnZotyeMode[i]);
        }
        this.mBtnZotyeMode[0].setDrawable(R.drawable.can_gs5_bot_10_up, R.drawable.can_gs5_bot_10_dn);
        setViewPos(this.mBtnZotyeMode[0], 11, 508, 82, 72);
        this.mBtnZotyeMode[1].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnZotyeMode[1], 131, 508, 82, 72);
        this.mBtnZotyeMode[2].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnZotyeMode[2], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnZotyeMode[3].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnZotyeMode[3], 372, 508, 82, 72);
        this.mBtnZotyeMode[4].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnZotyeMode[4], 492, 508, 82, 72);
    }

    public void InitHyundaiXp(Context context) {
        this.mBtnHyundaiXpFrontMode = new ParamButton[4];
        this.mBtnHyundaiXpRearMode = new ParamButton[4];
        this.mTvHyundaiAssist = new CustomImgView[4];
        this.mHyundaiCamera = new CanDataInfo.HyundaiCamera360();
        this.mHyundaiAssist = new CanDataInfo.HyundaiAssist();
        for (int i = 0; i < this.mBtnHyundaiXpFrontMode.length; i++) {
            this.mBtnHyundaiXpFrontMode[i] = new ParamButton(context);
            this.mBtnHyundaiXpFrontMode[i].setTag(Integer.valueOf(i + 1170));
            this.mBtnHyundaiXpFrontMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnHyundaiXpFrontMode[i]);
        }
        for (int i2 = 0; i2 < this.mBtnHyundaiXpRearMode.length; i2++) {
            this.mBtnHyundaiXpRearMode[i2] = new ParamButton(context);
            this.mBtnHyundaiXpRearMode[i2].setTag(Integer.valueOf(i2 + BTN_HYUNDAI_XP_REAR_MODE1));
            this.mBtnHyundaiXpRearMode[i2].setOnClickListener(this);
            this.mListBotView.add(this.mBtnHyundaiXpRearMode[i2]);
        }
        this.mBtnHyundaiXpFrontMode[0].setDrawable(R.drawable.can_gs5_bot_19_up, R.drawable.can_gs5_bot_19_dn);
        setViewPos(this.mBtnHyundaiXpFrontMode[0], 11, 508, 82, 72);
        this.mBtnHyundaiXpFrontMode[1].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnHyundaiXpFrontMode[1], 131, 508, 82, 72);
        this.mBtnHyundaiXpFrontMode[2].setDrawable(R.drawable.can_gs5_bot_20_up, R.drawable.can_gs5_bot_20_dn);
        setViewPos(this.mBtnHyundaiXpFrontMode[2], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnHyundaiXpFrontMode[3].setDrawable(R.drawable.can_gs5_bot_03_up, R.drawable.can_gs5_bot_03_dn);
        setViewPos(this.mBtnHyundaiXpFrontMode[3], 372, 508, 82, 72);
        this.mBtnHyundaiXpRearMode[0].setDrawable(R.drawable.can_gs5_bot_16_up, R.drawable.can_gs5_bot_16_dn);
        setViewPos(this.mBtnHyundaiXpRearMode[0], 11, 508, 82, 72);
        this.mBtnHyundaiXpRearMode[1].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnHyundaiXpRearMode[1], 131, 508, 82, 72);
        this.mBtnHyundaiXpRearMode[2].setDrawable(R.drawable.can_gs5_bot_17_up, R.drawable.can_gs5_bot_17_dn);
        setViewPos(this.mBtnHyundaiXpRearMode[2], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnHyundaiXpRearMode[3].setDrawable(R.drawable.can_gs5_bot_18_up, R.drawable.can_gs5_bot_18_dn);
        setViewPos(this.mBtnHyundaiXpRearMode[3], 372, 508, 82, 72);
        for (int i3 = 0; i3 < this.mTvHyundaiAssist.length; i3++) {
            this.mTvHyundaiAssist[i3] = new CustomImgView(context);
            this.mListBotView.add(this.mTvHyundaiAssist[i3]);
        }
        this.mTvHyundaiAssist[0].setBackgroundResource(R.drawable.can_zhuyi_up);
        this.mTvHyundaiAssist[0].Show(false);
        setViewPos(this.mTvHyundaiAssist[0], 34, 181, 104, 104);
        this.mTvHyundaiAssist[1].setBackgroundResource(R.drawable.can_zhuyi02_up);
        this.mTvHyundaiAssist[1].Show(false);
        setViewPos(this.mTvHyundaiAssist[1], 32, 286, 109, 48);
        this.mTvHyundaiAssist[2].setBackgroundResource(R.drawable.can_zhuyi_up);
        this.mTvHyundaiAssist[2].Show(false);
        setViewPos(this.mTvHyundaiAssist[2], 886, 181, 104, 104);
        this.mTvHyundaiAssist[3].setBackgroundResource(R.drawable.can_zhuyi03_up);
        this.mTvHyundaiAssist[3].Show(false);
        setViewPos(this.mTvHyundaiAssist[3], 914, 286, 78, 48);
    }

    public void InitZoyteDMX5Wc(Context context) {
        this.mBtnZotyeX5WcMode = new ParamButton[4];
        this.mBtnZotyeX5WcEsc = new ParamButton(context);
        this.mBtnZotyeX5WcEsc.setTag(Integer.valueOf(BTN_ZOYTEX5_WC_ESC));
        this.mBtnZotyeX5WcEsc.setOnClickListener(this);
        this.mBtnZotyeX5WcEsc.setDrawable(R.drawable.can_camera_track_esc_up, R.drawable.can_camera_track_esc_dn);
        this.mListBotView.add(this.mBtnZotyeX5WcEsc);
        setViewPos(this.mBtnZotyeX5WcEsc, 909, 540, 115, 60);
        for (int i = 0; i < this.mBtnZotyeX5WcMode.length; i++) {
            this.mBtnZotyeX5WcMode[i] = new ParamButton(context);
            this.mBtnZotyeX5WcMode[i].setTag(Integer.valueOf(i + BTN_ZOYTEX5_WC_MODE1));
            this.mBtnZotyeX5WcMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnZotyeX5WcMode[i]);
        }
        this.mBtnZotyeX5WcMode[0].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnZotyeX5WcMode[0], 11, 508, 82, 72);
        this.mBtnZotyeX5WcMode[1].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnZotyeX5WcMode[1], 131, 508, 82, 72);
        this.mBtnZotyeX5WcMode[2].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnZotyeX5WcMode[2], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnZotyeX5WcMode[3].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnZotyeX5WcMode[3], 372, 508, 82, 72);
    }

    public void InitBaicHSS6Wc(Context context) {
        this.mBtnBaicHSS6WcMode = new ParamButton[2];
        for (int i = 0; i < this.mBtnBaicHSS6WcMode.length; i++) {
            this.mBtnBaicHSS6WcMode[i] = new ParamButton(context);
            this.mBtnBaicHSS6WcMode[i].setTag(Integer.valueOf(i + BTN_BAIC_HSS6_FDJ));
            this.mBtnBaicHSS6WcMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnBaicHSS6WcMode[i]);
        }
        this.mBtnBaicHSS6WcMode[0].setDrawable(R.drawable.can_radar_fd_up, R.drawable.can_radar_fd_dn);
        setViewPos(this.mBtnBaicHSS6WcMode[0], 11, 508, 94, 94);
        this.mBtnBaicHSS6WcMode[1].setDrawable(R.drawable.can_radar_qj_up, R.drawable.can_radar_qj_dn);
        setViewPos(this.mBtnBaicHSS6WcMode[1], 131, 508, 94, 94);
    }

    public void InitX802017Wc(Context context) {
        this.mBtnX80WcMode = new ParamButton[4];
        this.mX80WcCamera = new CanDataInfo.X80Wc_CamSta();
        for (int i = 0; i < this.mBtnX80WcMode.length; i++) {
            this.mBtnX80WcMode[i] = new ParamButton(context);
            this.mBtnX80WcMode[i].setTag(Integer.valueOf(i + BTN_X80_2017_MODE1));
            this.mBtnX80WcMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnX80WcMode[i]);
        }
        this.mBtnX80WcMode[0].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnX80WcMode[0], 11, 508, 82, 72);
        this.mBtnX80WcMode[1].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnX80WcMode[1], 131, 508, 82, 72);
        this.mBtnX80WcMode[2].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnX80WcMode[2], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnX80WcMode[3].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnX80WcMode[3], 372, 508, 82, 72);
    }

    public void InitCs75(Context context) {
        this.mBtnCs75Mode = new ParamButton(context);
        this.mBtnCs75Mode.setTag(151);
        this.mBtnCs75Mode.setStateUpDn(R.drawable.can_camera_mode_up, R.drawable.can_camera_mode_dn);
        this.mBtnCs75Mode.setOnClickListener(this);
        setViewPos(this.mBtnCs75Mode, 100, 465, 150, 60);
    }

    public void InitCs75Avm(Context context) {
        this.mBtnCs75AvmMode = new ParamButton[7];
        for (int i = 0; i < this.mBtnCs75AvmMode.length; i++) {
            this.mBtnCs75AvmMode[i] = new ParamButton(context);
            this.mBtnCs75AvmMode[i].setTag(Integer.valueOf(i + BTN_CHANA_CS75_MODE1));
            this.mBtnCs75AvmMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnCs75AvmMode[i]);
        }
        this.mBtnCs75AvmMode[0].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnCs75AvmMode[0], 11, 508, 82, 72);
        this.mBtnCs75AvmMode[1].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnCs75AvmMode[1], 131, 508, 82, 72);
        this.mBtnCs75AvmMode[2].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnCs75AvmMode[2], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnCs75AvmMode[3].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnCs75AvmMode[3], 372, 508, 82, 72);
        this.mBtnCs75AvmMode[4].setDrawable(R.drawable.can_gs5_bot_11_up, R.drawable.can_gs5_bot_11_dn);
        setViewPos(this.mBtnCs75AvmMode[4], 492, 508, 82, 72);
        this.mBtnCs75AvmMode[5].setDrawable(R.drawable.can_gs5_bot_10_up, R.drawable.can_gs5_bot_10_dn);
        setViewPos(this.mBtnCs75AvmMode[5], BTN_CCH9_MODE3, 508, 82, 72);
        this.mBtnCs75AvmMode[6].setStateUpDn(R.drawable.can_camera_line_up, R.drawable.can_camera_line_dn);
        setViewPos(this.mBtnCs75AvmMode[6], 732, 508, 82, 72);
    }

    public void InitTigger7(Context context) {
        this.mBtnTigger7Cam = new ParamButton[4];
        for (int i = 0; i < this.mBtnTigger7Cam.length; i++) {
            this.mBtnTigger7Cam[i] = new ParamButton(context);
            this.mBtnTigger7Cam[i].setTag(Integer.valueOf(i + 161));
            this.mBtnTigger7Cam[i].setBackgroundColor(0);
            this.mBtnTigger7Cam[i].setOnClickListener(this);
        }
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(outMetrics);
        int radius = (int) Math.min((((float) outMetrics.widthPixels) * 1.0f) / 1024.0f, (((float) outMetrics.heightPixels) * 1.0f) / 600.0f);
        setViewPos(this.mBtnTigger7Cam[0], radius * BTN_LANDWIND_3D_LEFT_UP, 0, radius * 374, radius * 120);
        setViewPos(this.mBtnTigger7Cam[1], radius * BTN_LANDWIND_3D_LEFT_UP, radius * 480, radius * 374, radius * 120);
        setViewPos(this.mBtnTigger7Cam[2], radius * BTN_LANDWIND_3D_LEFT_UP, radius * 120, radius * 120, radius * 360);
        setViewPos(this.mBtnTigger7Cam[3], radius * 904, radius * 120, radius * 120, radius * 360);
    }

    public void InitLiFan(Context context) {
        this.mBtnLiFanCamMode = new ParamButton[2];
        this.mLiFanCamMode = new CanDataInfo.Lifan_CamMode();
        for (int i = 0; i < this.mBtnLiFanCamMode.length; i++) {
            this.mBtnLiFanCamMode[i] = new ParamButton(context);
            this.mBtnLiFanCamMode[i].setTag(Integer.valueOf(i + 171));
            this.mBtnLiFanCamMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnLiFanCamMode[i]);
        }
        this.mBtnLiFanCamMode[0].setDrawable(R.drawable.can_gs5_bot_05_up, R.drawable.can_gs5_bot_05_dn);
        setViewPos(this.mBtnLiFanCamMode[0], 40, 500, 82, 72);
        this.mBtnLiFanCamMode[1].setDrawable(R.drawable.can_gs5_bot_04_up, R.drawable.can_gs5_bot_04_dn);
        setViewPos(this.mBtnLiFanCamMode[1], 160, 500, 82, 72);
    }

    private void InitCCH2(Context context) {
        this.mBtnCCH2Esc = new ParamButton(context);
        this.mBtnCCH2Esc.setTag(180);
        this.mBtnCCH2Esc.setOnClickListener(this);
        this.mBtnCCH2Esc.setDrawable(R.drawable.can_camera_track_esc_up, R.drawable.can_camera_track_esc_dn);
        this.mListBotView.add(this.mBtnCCH2Esc);
        setViewPos(this.mBtnCCH2Esc, 909, 540, 115, 60);
        this.mBtnCCH2Mode = new ParamButton[3];
        for (int i = 0; i < this.mBtnCCH2Mode.length; i++) {
            this.mBtnCCH2Mode[i] = new ParamButton(context);
            this.mBtnCCH2Mode[i].setTag(Integer.valueOf(i + 181));
            this.mBtnCCH2Mode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnCCH2Mode[i]);
        }
        this.mBtnCCH2Mode[0].setDrawable(R.drawable.can_eps_park_up, R.drawable.can_eps_park_dn);
        setViewPos(this.mBtnCCH2Mode[0], 20, 212, 115, 115);
        this.mBtnCCH2Mode[1].setDrawable(R.drawable.can_eps_park_side_up, R.drawable.can_eps_park_side_dn);
        setViewPos(this.mBtnCCH2Mode[1], 20, 347, 115, 115);
        this.mBtnCCH2Mode[2].setDrawable(R.drawable.can_eps_park_right_up, R.drawable.can_eps_park_right_dn);
        setViewPos(this.mBtnCCH2Mode[2], 20, 482, 115, 115);
    }

    private void InitCCH2WC(Context context) {
        this.mBtnCCH2WCMode = new ParamButton[3];
        this.mCcH2WcCamera = new CanDataInfo.CcH2WcCamera();
        for (int i = 0; i < this.mBtnCCH2WCMode.length; i++) {
            this.mBtnCCH2WCMode[i] = new ParamButton(context);
            this.mBtnCCH2WCMode[i].setTag(Integer.valueOf(i + BTN_CCH2WC_MODE1));
            this.mBtnCCH2WCMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnCCH2WCMode[i]);
        }
        this.mBtnCCH2WCMode[0].setDrawable(R.drawable.can_eps_park_up, R.drawable.can_eps_park_dn);
        setViewPos(this.mBtnCCH2WCMode[0], 20, 212, 115, 115);
        this.mBtnCCH2WCMode[1].setDrawable(R.drawable.can_eps_park_side_up, R.drawable.can_eps_park_side_dn);
        setViewPos(this.mBtnCCH2WCMode[1], 20, 347, 115, 115);
        this.mBtnCCH2WCMode[2].setDrawable(R.drawable.can_eps_park_right_up, R.drawable.can_eps_park_right_dn);
        setViewPos(this.mBtnCCH2WCMode[2], 20, 482, 115, 115);
    }

    private void InitCCH9(Context context) {
        this.mBtnHCarSet = new CanDataInfo.H6CarSet();
        this.mBtnCCH9Mode = new ParamButton[6];
        for (int i = 0; i < this.mBtnCCH9Mode.length; i++) {
            this.mBtnCCH9Mode[i] = new ParamButton(context);
            this.mBtnCCH9Mode[i].setTag(Integer.valueOf(i + BTN_CCH9_MODE1));
            this.mBtnCCH9Mode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnCCH9Mode[i]);
        }
        this.mBtnCCH9Mode[0].setDrawable(R.drawable.can_eps_park_01_up, R.drawable.can_eps_park_01_dn);
        setViewPos(this.mBtnCCH9Mode[0], KeyDef.RKEY_DEL, 450, 115, 115);
        this.mBtnCCH9Mode[1].setDrawable(R.drawable.can_eps_park_02_up, R.drawable.can_eps_park_02_dn);
        setViewPos(this.mBtnCCH9Mode[1], 475, 450, 115, 115);
        this.mBtnCCH9Mode[2].setDrawable(R.drawable.can_eps_park_05_up, R.drawable.can_eps_park_05_dn);
        setViewPos(this.mBtnCCH9Mode[2], BTN_CCH9_MODE1, 450, 115, 115);
        this.mBtnCCH9Mode[3].setDrawable(R.drawable.can_eps_park_03_up, R.drawable.can_eps_park_03_dn);
        setViewPos(this.mBtnCCH9Mode[3], KeyDef.RKEY_DEL, 450, 115, 115);
        this.mBtnCCH9Mode[4].setDrawable(R.drawable.can_eps_park_gj_up, R.drawable.can_eps_park_gj_dn);
        setViewPos(this.mBtnCCH9Mode[4], 475, 450, 115, 115);
        this.mBtnCCH9Mode[5].setDrawable(R.drawable.can_eps_park_sound_up, R.drawable.can_eps_park_sound_dn);
        setViewPos(this.mBtnCCH9Mode[5], BTN_CCH9_MODE1, 450, 115, 115);
        this.mBtnCCH9Direction = new ParamButton[4];
        for (int i2 = 0; i2 < this.mBtnCCH9Direction.length; i2++) {
            this.mBtnCCH9Direction[i2] = new ParamButton(context);
            this.mBtnCCH9Direction[i2].setTag(Integer.valueOf(i2 + BTN_CCH9_MODE7));
            this.mBtnCCH9Direction[i2].setOnClickListener(this);
            this.mListBotView.add(this.mBtnCCH9Direction[i2]);
        }
        this.mBtnCCH9Direction[0].setDrawable(R.drawable.can_eps_park_fx01_up, R.drawable.can_eps_park_fx01_dn);
        setViewPos(this.mBtnCCH9Direction[0], 694, 170, 59, 59);
        this.mBtnCCH9Direction[1].setDrawable(R.drawable.can_eps_park_fx02_up, R.drawable.can_eps_park_fx02_dn);
        setViewPos(this.mBtnCCH9Direction[1], 694, 311, 59, 59);
        this.mBtnCCH9Direction[2].setDrawable(R.drawable.can_eps_park_fx03_up, R.drawable.can_eps_park_fx03_dn);
        setViewPos(this.mBtnCCH9Direction[2], 624, Can.CAN_VOLKS_XP, 59, 59);
        this.mBtnCCH9Direction[3].setDrawable(R.drawable.can_eps_park_fx04_up, R.drawable.can_eps_park_fx04_dn);
        setViewPos(this.mBtnCCH9Direction[3], 765, Can.CAN_VOLKS_XP, 59, 59);
        this.mBtnCCH9Bot = new ParamButton[4];
        for (int i3 = 0; i3 < this.mBtnCCH9Bot.length; i3++) {
            this.mBtnCCH9Bot[i3] = new ParamButton(context);
            this.mBtnCCH9Bot[i3].setTag(Integer.valueOf(i3 + 620));
            this.mBtnCCH9Bot[i3].setOnClickListener(this);
            this.mListBotView.add(this.mBtnCCH9Bot[i3]);
        }
        this.mBtnCCH9Bot[0].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnCCH9Bot[0], BTN_CS75_WC_MODE, 130, 82, 72);
        this.mBtnCCH9Bot[1].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnCCH9Bot[1], BTN_CS75_WC_MODE, Can.CAN_FLAT_WC, 82, 72);
        this.mBtnCCH9Bot[2].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnCCH9Bot[2], BTN_CS75_WC_MODE, KeyDef.RKEY_EJECT_L, 82, 72);
        this.mBtnCCH9Bot[3].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnCCH9Bot[3], BTN_CS75_WC_MODE, 436, 82, 72);
    }

    private void InitB50_X80(Context context) {
        this.mBtnX80CamMode = new ParamButton[4];
        this.mX80Camera = new CanDataInfo.X80_Camera();
        for (int i = 0; i < this.mBtnX80CamMode.length; i++) {
            this.mBtnX80CamMode[i] = new ParamButton(context);
            this.mBtnX80CamMode[i].setOnClickListener(this);
            this.mBtnX80CamMode[i].setTag(Integer.valueOf(i + 190));
            this.mListBotView.add(this.mBtnX80CamMode[i]);
        }
        this.mBtnX80CamMode[0].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnX80CamMode[0], 111, 500, 82, 72);
        this.mBtnX80CamMode[1].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnX80CamMode[1], Can.CAN_FLAT_WC, 500, 82, 72);
        this.mBtnX80CamMode[2].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnX80CamMode[2], 352, 500, 82, 72);
        this.mBtnX80CamMode[3].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnX80CamMode[3], 472, 500, 82, 72);
    }

    private void InitGeelyBoy(Context context) {
        this.mBtnGeelyBoyMode = new ParamButton[4];
        this.mBtnGeelyBoyEsc = new ParamButton(context);
        this.mBtnGeelyBoyEsc.setOnClickListener(this);
        this.mBtnGeelyBoyEsc.setTag(204);
        this.mBtnGeelyBoyEsc.setDrawable(R.drawable.can_camera_track_esc_up, R.drawable.can_camera_track_esc_dn);
        setViewPos(this.mBtnGeelyBoyEsc, BTN_VW_WC_MODE3, 500, 115, 60);
        this.mListBotView.add(this.mBtnGeelyBoyEsc);
        for (int i = 0; i < this.mBtnGeelyBoyMode.length; i++) {
            this.mBtnGeelyBoyMode[i] = new ParamButton(context);
            this.mBtnGeelyBoyMode[i].setOnClickListener(this);
            this.mBtnGeelyBoyMode[i].setTag(Integer.valueOf(i + 200));
            this.mListBotView.add(this.mBtnGeelyBoyMode[i]);
        }
        this.mBtnGeelyBoyMode[0].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnGeelyBoyMode[0], 111, 500, 82, 72);
        this.mBtnGeelyBoyMode[1].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnGeelyBoyMode[1], Can.CAN_FLAT_WC, 500, 82, 72);
        this.mBtnGeelyBoyMode[2].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnGeelyBoyMode[2], 352, 500, 82, 72);
        this.mBtnGeelyBoyMode[3].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnGeelyBoyMode[3], 472, 500, 82, 72);
    }

    private void InitVenuciaT70(Context context) {
        this.mBtnVenuciaMode = new ParamButton[4];
        this.mBtnVenuciaEsc = new ParamButton(context);
        this.mVenuciaCamState = new CanDataInfo.VenuciaCamState();
        this.mBtnVenuciaEsc.setOnClickListener(this);
        this.mBtnVenuciaEsc.setTag(304);
        this.mBtnVenuciaEsc.setDrawable(R.drawable.can_camera_track_esc_up, R.drawable.can_camera_track_esc_dn);
        setViewPos(this.mBtnVenuciaEsc, 35, 40, 115, 60);
        this.mListBotView.add(this.mBtnVenuciaEsc);
        for (int i = 0; i < this.mBtnVenuciaMode.length; i++) {
            this.mBtnVenuciaMode[i] = new ParamButton(context);
            this.mBtnVenuciaMode[i].setOnClickListener(this);
            this.mBtnVenuciaMode[i].setTag(Integer.valueOf(i + 300));
            this.mListBotView.add(this.mBtnVenuciaMode[i]);
        }
        this.mBtnVenuciaMode[0].setDrawable(R.drawable.can_gs5_bot_10_up, R.drawable.can_gs5_bot_10_dn);
        setViewPos(this.mBtnVenuciaMode[0], 45, 150, 82, 72);
        this.mBtnVenuciaMode[1].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnVenuciaMode[1], 45, Can.CAN_NISSAN_XFY, 82, 72);
        this.mBtnVenuciaMode[2].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnVenuciaMode[2], 45, 350, 82, 72);
        this.mBtnVenuciaMode[3].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnVenuciaMode[3], 45, 450, 82, 72);
    }

    private void Init16GS4Mode(Context context) {
        this.mBtnTrumpchiGs4Mode = new ParamButton[5];
        for (int i = 0; i < this.mBtnTrumpchiGs4Mode.length; i++) {
            this.mBtnTrumpchiGs4Mode[i] = new ParamButton(context);
            this.mBtnTrumpchiGs4Mode[i].setOnClickListener(this);
            this.mBtnTrumpchiGs4Mode[i].setTag(Integer.valueOf(i + BTN_TRUMPCHI_GS4_MODE1));
            this.mListBotView.add(this.mBtnTrumpchiGs4Mode[i]);
        }
        this.mBtnTrumpchiGs4Mode[0].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnTrumpchiGs4Mode[0], 111, 500, 82, 72);
        this.mBtnTrumpchiGs4Mode[1].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnTrumpchiGs4Mode[1], Can.CAN_FLAT_WC, 500, 82, 72);
        this.mBtnTrumpchiGs4Mode[2].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnTrumpchiGs4Mode[2], 352, 500, 82, 72);
        this.mBtnTrumpchiGs4Mode[3].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnTrumpchiGs4Mode[3], 472, 500, 82, 72);
        this.mBtnTrumpchiGs4Mode[4].setDrawable(R.drawable.can_camera_track_esc_up, R.drawable.can_camera_track_esc_dn);
        setViewPosBottom(this.mBtnTrumpchiGs4Mode[4], 909, 0, 115, 60);
    }

    private void InitHMS7(Context context) {
        this.mBtnHmS7Mode = new ParamButton[4];
        this.mBtnHmS7Esc = new ParamButton(context);
        this.mBtnHmS7Line = new ParamButton(context);
        this.mHmS7Set = new CanDataInfo.HmS7_CarSet();
        this.mBtnHmS7Line.setOnClickListener(this);
        this.mBtnHmS7Line.setTag(Integer.valueOf(BTN_HMS7_HELP_LINE));
        this.mBtnHmS7Line.setDrawable(R.drawable.can_camera_tarack_line_up, R.drawable.can_camera_tarack_line_dn);
        setViewPos(this.mBtnHmS7Line, 600, 500, 128, 64);
        this.mListBotView.add(this.mBtnHmS7Line);
        this.mBtnHmS7Esc.setOnClickListener(this);
        this.mBtnHmS7Esc.setTag(Integer.valueOf(BTN_HMS7_ESC));
        this.mBtnHmS7Esc.setDrawable(R.drawable.can_camera_track_esc_up, R.drawable.can_camera_track_esc_dn);
        setViewPos(this.mBtnHmS7Esc, 750, 500, 128, 64);
        this.mListBotView.add(this.mBtnHmS7Esc);
        for (int i = 0; i < this.mBtnHmS7Mode.length; i++) {
            this.mBtnHmS7Mode[i] = new ParamButton(context);
            this.mBtnHmS7Mode[i].setOnClickListener(this);
            this.mBtnHmS7Mode[i].setTag(Integer.valueOf(i + 500));
            this.mListBotView.add(this.mBtnHmS7Mode[i]);
        }
        this.mBtnHmS7Mode[0].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnHmS7Mode[0], 111, 500, 82, 72);
        this.mBtnHmS7Mode[1].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnHmS7Mode[1], Can.CAN_FLAT_WC, 500, 82, 72);
        this.mBtnHmS7Mode[2].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnHmS7Mode[2], 352, 500, 82, 72);
        this.mBtnHmS7Mode[3].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnHmS7Mode[3], 472, 500, 82, 72);
    }

    private void InitYg9(Context context) {
        this.mBtnYg9Mode = new ParamButton[4];
        this.mYg9Set = new CanDataInfo.AccordXbsCamMode();
        for (int i = 0; i < this.mBtnYg9Mode.length; i++) {
            this.mBtnYg9Mode[i] = new ParamButton(context);
            this.mBtnYg9Mode[i].setOnClickListener(this);
            this.mBtnYg9Mode[i].setTag(Integer.valueOf(i + BTN_YG9_XBS_MODE1));
            this.mListBotView.add(this.mBtnYg9Mode[i]);
        }
        this.mBtnYg9Mode[0].setDrawable(R.drawable.can_view_bz_up, R.drawable.can_view_bz_dn);
        setViewPos(this.mBtnYg9Mode[0], 111, 500, 82, 75);
        this.mBtnYg9Mode[1].setDrawable(R.drawable.can_view_fj_up, R.drawable.can_view_fj_dn);
        setViewPos(this.mBtnYg9Mode[1], Can.CAN_FLAT_WC, 500, 82, 75);
        this.mBtnYg9Mode[2].setDrawable(R.drawable.can_view_gj_up, R.drawable.can_view_gj_dn);
        setViewPos(this.mBtnYg9Mode[2], 352, 500, 82, 75);
    }

    private void InitGeelyYjX6(Context context) {
        this.mGeelyCameraSta = new CanDataInfo.Geely_Camera();
        this.mBtnGeelyYjX6Mode = new ParamButton[4];
        this.mBtnGeelyYjX6Esc = new ParamButton(context);
        this.mBtnGeelyYjX63D = new ParamButton(context);
        this.mBtnGeelyYjX6GJ = new ParamButton(context);
        this.mBtnGeelyYjX6FXP = new ParamButton(context);
        this.mBtnGeelyYjX6Esc.setOnClickListener(this);
        this.mBtnGeelyYjX6Esc.setTag(Integer.valueOf(BTN_GEELY_YJX6_ESC));
        this.mBtnGeelyYjX6Esc.setDrawable(R.drawable.can_camera_track_esc_up, R.drawable.can_camera_track_esc_dn);
        setViewPos(this.mBtnGeelyYjX6Esc, 909, 540, 115, 60);
        this.mListBotView.add(this.mBtnGeelyYjX6Esc);
        this.mBtnGeelyYjX6GJ.setOnClickListener(this);
        this.mBtnGeelyYjX6GJ.setTag(Integer.valueOf(BTN_GEELY_YJX6_GJ));
        this.mBtnGeelyYjX6GJ.setDrawable(R.drawable.can_camera_tarack_line_up, R.drawable.can_camera_tarack_line_dn);
        setViewPos(this.mBtnGeelyYjX6GJ, 0, 540, 115, 60);
        this.mListBotView.add(this.mBtnGeelyYjX6GJ);
        this.mBtnGeelyYjX6FXP.setOnClickListener(this);
        this.mBtnGeelyYjX6FXP.setTag(Integer.valueOf(BTN_GEELY_YJX6_FXP));
        this.mBtnGeelyYjX6FXP.setDrawable(R.drawable.can_camera_track_fxp_up, R.drawable.can_camera_track_fxp_dn);
        setViewPos(this.mBtnGeelyYjX6FXP, 0, 470, 115, 60);
        this.mListBotView.add(this.mBtnGeelyYjX6FXP);
        for (int i = 0; i < this.mBtnGeelyYjX6Mode.length; i++) {
            this.mBtnGeelyYjX6Mode[i] = new ParamButton(context);
            this.mBtnGeelyYjX6Mode[i].setOnClickListener(this);
            this.mBtnGeelyYjX6Mode[i].setTag(Integer.valueOf(i + BTN_GEELY_YJX6_MODE1));
            this.mListBotView.add(this.mBtnGeelyYjX6Mode[i]);
        }
        this.mBtnGeelyYjX6Mode[0].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnGeelyYjX6Mode[0], 161, 500, 82, 75);
        this.mBtnGeelyYjX6Mode[1].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnGeelyYjX6Mode[1], 282, 500, 82, 75);
        this.mBtnGeelyYjX6Mode[2].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnGeelyYjX6Mode[2], BTN_TRUMPCHI_GS4_MODE3, 500, 82, 75);
        this.mBtnGeelyYjX6Mode[3].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnGeelyYjX6Mode[3], BTN_GEELY_YJX6_MODE3, 500, 82, 75);
        this.mBtnGeelyYjX63D.setOnClickListener(this);
        this.mBtnGeelyYjX63D.setTag(Integer.valueOf(BTN_GEELY_YJX6_3D));
        this.mBtnGeelyYjX63D.setDrawable(R.drawable.can_gs5_bot_10_up, R.drawable.can_gs5_bot_10_dn);
        setViewPos(this.mBtnGeelyYjX63D, BTN_LANDWIND_2D_REAR, 500, 82, 75);
        this.mListBotView.add(this.mBtnGeelyYjX63D);
    }

    private void InitChanAAlsvinV7(Context context) {
        this.mBtnChanAAlsvinV76Mode = new ParamButton[3];
        for (int i = 0; i < this.mBtnChanAAlsvinV76Mode.length; i++) {
            this.mBtnChanAAlsvinV76Mode[i] = new ParamButton(context);
            this.mBtnChanAAlsvinV76Mode[i].setOnClickListener(this);
            this.mBtnChanAAlsvinV76Mode[i].setTag(Integer.valueOf(i + BTN_CHANA_ALSVINV7_MODE1));
            this.mListBotView.add(this.mBtnChanAAlsvinV76Mode[i]);
        }
        this.mBtnChanAAlsvinV76Mode[0].setDrawable(R.drawable.can_gs5_bot_05_up, R.drawable.can_gs5_bot_05_dn);
        setViewPos(this.mBtnChanAAlsvinV76Mode[0], 111, 500, 82, 75);
        this.mBtnChanAAlsvinV76Mode[1].setDrawable(R.drawable.can_gs5_bot_04_up, R.drawable.can_gs5_bot_04_dn);
        setViewPos(this.mBtnChanAAlsvinV76Mode[1], Can.CAN_FLAT_WC, 500, 82, 75);
        this.mBtnChanAAlsvinV76Mode[2].setDrawable(R.drawable.can_camera_line_up, R.drawable.can_camera_line_dn);
        setViewPos(this.mBtnChanAAlsvinV76Mode[2], 352, 500, 82, 75);
    }

    private void InitXtralRvsAssist768x1024(Context context) {
        this.mXtralRvsAss = new CanDataInfo.CanTeanaAvmData();
        this.mXtralWcRvsAss = new CanDataInfo.NissanWcAvmUI();
        this.mBtnXtralRvs = new ParamButton[13];
        for (int i = 0; i < this.mBtnXtralRvs.length; i++) {
            this.mBtnXtralRvs[i] = new ParamButton(context);
            if (CanJni.GetCanFsTp() == 149) {
                this.mBtnXtralRvs[i].setOnTouchListener(this);
            } else {
                this.mBtnXtralRvs[i].setOnClickListener(this);
            }
            this.mBtnXtralRvs[i].setTag(Integer.valueOf(i + 540));
            this.mBtnXtralRvs[i].Show(false);
            this.mListBotView.add(this.mBtnXtralRvs[i]);
        }
        this.mBtnXtralRvs[0].setDrawable(R.drawable.can_oj_left_big_up, R.drawable.can_oj_left_big_dn);
        setViewPos(this.mBtnXtralRvs[0], 106, 215, 95, 160);
        this.mBtnXtralRvs[1].setDrawable(R.drawable.can_oj_up_up, R.drawable.can_oj_up_dn);
        setViewPos(this.mBtnXtralRvs[1], KeyDef.RKEY_EJECT, 168, 96, 68);
        this.mBtnXtralRvs[2].setDrawable(R.drawable.can_oj_down_up, R.drawable.can_oj_down_dn);
        setViewPos(this.mBtnXtralRvs[2], KeyDef.RKEY_EJECT, 352, 96, 68);
        this.mBtnXtralRvs[3].setDrawable(R.drawable.can_oj_left_up, R.drawable.can_oj_left_dn);
        setViewPos(this.mBtnXtralRvs[3], 218, 259, 96, 68);
        this.mBtnXtralRvs[4].setDrawable(R.drawable.can_oj_right_up, R.drawable.can_oj_right_dn);
        setViewPos(this.mBtnXtralRvs[4], 412, 259, 96, 68);
        this.mBtnXtralRvs[5].setDrawable(R.drawable.can_oj_right_big_up, R.drawable.can_oj_right_big_dn);
        setViewPos(this.mBtnXtralRvs[5], BTN_GEELY_YJX6_FXP, 215, 95, 160);
        this.mBtnXtralRvs[6].setDrawable(R.drawable.can_oj_car_up, R.drawable.can_oj_car_dn);
        setViewPos(this.mBtnXtralRvs[6], 459, 450, 161, 91);
        this.mBtnXtralRvs[7].setDrawable(R.drawable.can_qj_sjx_up, R.drawable.can_qj_sjx_dn);
        setViewPos(this.mBtnXtralRvs[7], 750, 450, 161, 91);
        this.mBtnXtralRvs[8].setDrawable(R.drawable.can_oj_pa_up, R.drawable.can_oj_pa_dn);
        setViewPos(this.mBtnXtralRvs[8], 459, 450, 161, 91);
        this.mBtnXtralRvs[9].setDrawable(R.drawable.can_oj_park_up, R.drawable.can_oj_park_dn);
        setViewPos(this.mBtnXtralRvs[9], 283, 450, 161, 91);
        this.mBtnXtralRvs[10].setDrawable(R.drawable.can_oj_rect_up, R.drawable.can_oj_rect_dn);
        setViewPos(this.mBtnXtralRvs[10], 106, 450, 161, 91);
        this.mBtnXtralRvs[10].setText(R.string.can_assist_cancel);
        this.mBtnXtralRvs[10].setTextSize(0, 27.0f);
        this.mBtnXtralRvs[11].setDrawable(R.drawable.can_oj_rect_up, R.drawable.can_oj_rect_dn);
        setViewPos(this.mBtnXtralRvs[11], 283, 450, 161, 91);
        this.mBtnXtralRvs[11].setText(R.string.can_assist_start);
        this.mBtnXtralRvs[11].setTextSize(0, 27.0f);
        this.mBtnXtralRvs[12].setDrawable(R.drawable.can_oj_rect_up, R.drawable.can_oj_rect_dn);
        setViewPos(this.mBtnXtralRvs[12], 459, 450, 161, 91);
        this.mBtnXtralRvs[12].setText(R.string.can_assist_back);
        this.mBtnXtralRvs[12].setTextSize(0, 27.0f);
        this.mXtralView = new CustomImgView[2];
        for (int i2 = 0; i2 < this.mXtralView.length; i2++) {
            this.mXtralView[i2] = new CustomImgView(context);
            this.mListBotView.add(this.mXtralView[i2]);
        }
        this.mXtralView[0].setStateDrawable(R.drawable.can_oj_center, R.drawable.can_oj_center);
        this.mXtralView[0].Show(false);
        setViewPos(this.mXtralView[0], KeyDef.RKEY_EJECT_L, 263, 59, 60);
        this.mXtralView[1].setStateDrawable(R.drawable.can_oj_car, R.drawable.can_oj_car02);
        setViewPos(this.mXtralView[1], 18, 469, 75, 82);
        if (CanJni.GetCanFsTp() == 149) {
            this.mXtralTsyArr = context.getResources().getStringArray(R.array.can_nissan_wc_xtral_rvs_tsy);
        } else {
            this.mXtralTsyArr = context.getResources().getStringArray(R.array.can_nissan_xtral_rvs_tsy);
        }
        this.mXtralTsy = new CustomTextView(context);
        this.mXtralTsy.setTextSize(0, 33.0f);
        this.mXtralTsy.setGravity(1);
        this.mXtralTsy.setTextColor(-16711936);
        setViewPos(this.mXtralTsy, 0, 15, 620, 120);
        this.mXtralDeclareArr = context.getResources().getStringArray(R.array.can_nissan_xtral_rvs_declare);
        this.mXtralDeclare = new CustomTextView[7];
        for (int i3 = 0; i3 < this.mXtralDeclare.length; i3++) {
            this.mXtralDeclare[i3] = new CustomTextView(context);
            this.mListBotView.add(this.mXtralDeclare[i3]);
            this.mXtralDeclare[i3].ShowGone(false);
        }
        this.mXtralDeclare[0].setTextSize(0, 42.0f);
        this.mXtralDeclare[0].setGravity(3);
        this.mXtralDeclare[0].setTextColor(-1);
        this.mXtralDeclare[0].setText(this.mXtralDeclareArr[0]);
        setViewPos(this.mXtralDeclare[0], 0, 55, 768, 60);
        for (int i4 = 1; i4 < 4; i4++) {
            this.mXtralDeclare[i4].setTextSize(0, 38.0f);
            this.mXtralDeclare[i4].setGravity(3);
            this.mXtralDeclare[i4].setTextColor(-1);
            this.mXtralDeclare[i4].setText(this.mXtralDeclareArr[i4]);
            setViewPos(this.mXtralDeclare[i4], 0, (i4 * 60) + 90, 768, 50);
        }
        for (int i5 = 4; i5 < 7; i5++) {
            this.mXtralDeclare[i5].setTextSize(0, 30.0f);
            this.mXtralDeclare[i5].setGravity(3);
            this.mXtralDeclare[i5].setTextColor(-256);
            this.mXtralDeclare[i5].setText(this.mXtralDeclareArr[i5]);
            setViewPos(this.mXtralDeclare[i5], 0, (i5 * 40) + 190, 768, 50);
        }
    }

    private void InitXtralRvsAssist(Context context) {
        int dy = 0;
        if (MainSet.GetScreenType() == 10) {
            dy = 120;
        }
        this.mXtralRvsAss = new CanDataInfo.CanTeanaAvmData();
        this.mXtralWcRvsAss = new CanDataInfo.NissanWcAvmUI();
        this.mBtnXtralRvs = new ParamButton[13];
        for (int i = 0; i < this.mBtnXtralRvs.length; i++) {
            this.mBtnXtralRvs[i] = new ParamButton(context);
            if (CanJni.GetCanFsTp() == 149) {
                this.mBtnXtralRvs[i].setOnTouchListener(this);
            } else {
                this.mBtnXtralRvs[i].setOnClickListener(this);
            }
            this.mBtnXtralRvs[i].setTag(Integer.valueOf(i + 540));
            this.mBtnXtralRvs[i].Show(false);
            this.mListBotView.add(this.mBtnXtralRvs[i]);
        }
        this.mBtnXtralRvs[0].setDrawable(R.drawable.can_oj_left_big_up, R.drawable.can_oj_left_big_dn);
        setViewPos(this.mBtnXtralRvs[0], 106, 215, 95, 160);
        this.mBtnXtralRvs[1].setDrawable(R.drawable.can_oj_up_up, R.drawable.can_oj_up_dn);
        setViewPos(this.mBtnXtralRvs[1], KeyDef.RKEY_EJECT, 168, 96, 68);
        this.mBtnXtralRvs[2].setDrawable(R.drawable.can_oj_down_up, R.drawable.can_oj_down_dn);
        setViewPos(this.mBtnXtralRvs[2], KeyDef.RKEY_EJECT, 352, 96, 68);
        this.mBtnXtralRvs[3].setDrawable(R.drawable.can_oj_left_up, R.drawable.can_oj_left_dn);
        setViewPos(this.mBtnXtralRvs[3], 218, 259, 96, 68);
        this.mBtnXtralRvs[4].setDrawable(R.drawable.can_oj_right_up, R.drawable.can_oj_right_dn);
        setViewPos(this.mBtnXtralRvs[4], 412, 259, 96, 68);
        this.mBtnXtralRvs[5].setDrawable(R.drawable.can_oj_right_big_up, R.drawable.can_oj_right_big_dn);
        setViewPos(this.mBtnXtralRvs[5], BTN_GEELY_YJX6_FXP, 215, 95, 160);
        this.mBtnXtralRvs[6].setDrawable(R.drawable.can_oj_car_up, R.drawable.can_oj_car_dn);
        setViewPos(this.mBtnXtralRvs[6], 459, dy + 500, 161, 91);
        this.mBtnXtralRvs[7].setDrawable(R.drawable.can_qj_sjx_up, R.drawable.can_qj_sjx_dn);
        setViewPos(this.mBtnXtralRvs[7], 750, dy + 500, 161, 91);
        this.mBtnXtralRvs[8].setDrawable(R.drawable.can_oj_pa_up, R.drawable.can_oj_pa_dn);
        setViewPos(this.mBtnXtralRvs[8], 459, dy + 500, 161, 91);
        this.mBtnXtralRvs[9].setDrawable(R.drawable.can_oj_park_up, R.drawable.can_oj_park_dn);
        setViewPos(this.mBtnXtralRvs[9], 283, dy + 500, 161, 91);
        this.mBtnXtralRvs[10].setDrawable(R.drawable.can_oj_rect_up, R.drawable.can_oj_rect_dn);
        setViewPos(this.mBtnXtralRvs[10], 106, dy + 500, 161, 91);
        this.mBtnXtralRvs[10].setText(R.string.can_assist_cancel);
        this.mBtnXtralRvs[10].setTextSize(0, 27.0f);
        this.mBtnXtralRvs[11].setDrawable(R.drawable.can_oj_rect_up, R.drawable.can_oj_rect_dn);
        setViewPos(this.mBtnXtralRvs[11], 283, dy + 500, 161, 91);
        this.mBtnXtralRvs[11].setText(R.string.can_assist_start);
        this.mBtnXtralRvs[11].setTextSize(0, 27.0f);
        this.mBtnXtralRvs[12].setDrawable(R.drawable.can_oj_rect_up, R.drawable.can_oj_rect_dn);
        setViewPos(this.mBtnXtralRvs[12], 459, dy + 500, 161, 91);
        this.mBtnXtralRvs[12].setText(R.string.can_assist_back);
        this.mBtnXtralRvs[12].setTextSize(0, 27.0f);
        this.mXtralView = new CustomImgView[2];
        for (int i2 = 0; i2 < this.mXtralView.length; i2++) {
            this.mXtralView[i2] = new CustomImgView(context);
            this.mListBotView.add(this.mXtralView[i2]);
        }
        this.mXtralView[0].setStateDrawable(R.drawable.can_oj_center, R.drawable.can_oj_center);
        this.mXtralView[0].Show(false);
        setViewPos(this.mXtralView[0], KeyDef.RKEY_EJECT_L, 263, 59, 60);
        this.mXtralView[1].setStateDrawable(R.drawable.can_oj_car, R.drawable.can_oj_car02);
        setViewPos(this.mXtralView[1], 18, dy + BTN_HMS7_ESC, 75, 82);
        if (CanJni.GetCanFsTp() == 149) {
            this.mXtralTsyArr = context.getResources().getStringArray(R.array.can_nissan_wc_xtral_rvs_tsy);
        } else {
            this.mXtralTsyArr = context.getResources().getStringArray(R.array.can_nissan_xtral_rvs_tsy);
        }
        this.mXtralTsy = new CustomTextView(context);
        this.mXtralTsy.setTextSize(0, 33.0f);
        this.mXtralTsy.setGravity(1);
        this.mXtralTsy.setTextColor(-16711936);
        setViewPos(this.mXtralTsy, 0, 15, BTN_CS75_WC_MODE, 120);
        this.mXtralDeclareArr = context.getResources().getStringArray(R.array.can_nissan_xtral_rvs_declare);
        this.mXtralDeclare = new CustomTextView[7];
        for (int i3 = 0; i3 < this.mXtralDeclare.length; i3++) {
            this.mXtralDeclare[i3] = new CustomTextView(context);
            this.mListBotView.add(this.mXtralDeclare[i3]);
            this.mXtralDeclare[i3].ShowGone(false);
        }
        this.mXtralDeclare[0].setTextSize(0, 42.0f);
        this.mXtralDeclare[0].setGravity(3);
        this.mXtralDeclare[0].setTextColor(-1);
        this.mXtralDeclare[0].setText(this.mXtralDeclareArr[0]);
        setViewPos(this.mXtralDeclare[0], 0, 55, 1024, 60);
        for (int i4 = 1; i4 < 4; i4++) {
            this.mXtralDeclare[i4].setTextSize(0, 38.0f);
            this.mXtralDeclare[i4].setGravity(3);
            this.mXtralDeclare[i4].setTextColor(-1);
            this.mXtralDeclare[i4].setText(this.mXtralDeclareArr[i4]);
            setViewPos(this.mXtralDeclare[i4], 0, (i4 * 60) + 90, 1024, 50);
        }
        for (int i5 = 4; i5 < 7; i5++) {
            this.mXtralDeclare[i5].setTextSize(0, 30.0f);
            this.mXtralDeclare[i5].setGravity(3);
            this.mXtralDeclare[i5].setTextColor(-256);
            this.mXtralDeclare[i5].setText(this.mXtralDeclareArr[i5]);
            setViewPos(this.mXtralDeclare[i5], 0, (i5 * 40) + 190, 1024, 50);
        }
    }

    private void InitNissanRzcRvsAssist(Context context) {
        int dy = 0;
        if (MainSet.GetScreenType() == 10) {
            dy = 120;
        }
        this.mNissanRzcRvsAss = new CanDataInfo.CanTeanaAvmData();
        this.mBtnNissanRzcRvs = new ParamButton[23];
        for (int i = 0; i < this.mBtnNissanRzcRvs.length; i++) {
            this.mBtnNissanRzcRvs[i] = new ParamButton(context);
            this.mBtnNissanRzcRvs[i].setOnClickListener(this);
            this.mBtnNissanRzcRvs[i].setTag(Integer.valueOf(i + BTN_NISSAN_XTRAL_RZC_RVS_ASSIST1));
            this.mBtnNissanRzcRvs[i].Show(false);
            this.mListBotView.add(this.mBtnNissanRzcRvs[i]);
        }
        this.mBtnNissanRzcRvs[0].setDrawable(R.drawable.can_oj_left_big_up, R.drawable.can_oj_left_big_dn);
        setViewPos(this.mBtnNissanRzcRvs[0], 106, 215, 95, 160);
        this.mBtnNissanRzcRvs[1].setDrawable(R.drawable.can_oj_up_up, R.drawable.can_oj_up_dn);
        setViewPos(this.mBtnNissanRzcRvs[1], KeyDef.RKEY_EJECT, 168, 96, 68);
        this.mBtnNissanRzcRvs[2].setDrawable(R.drawable.can_oj_down_up, R.drawable.can_oj_down_dn);
        setViewPos(this.mBtnNissanRzcRvs[2], KeyDef.RKEY_EJECT, 352, 96, 68);
        this.mBtnNissanRzcRvs[3].setDrawable(R.drawable.can_oj_left_up, R.drawable.can_oj_left_dn);
        setViewPos(this.mBtnNissanRzcRvs[3], 218, 259, 96, 68);
        this.mBtnNissanRzcRvs[4].setDrawable(R.drawable.can_oj_right_up, R.drawable.can_oj_right_dn);
        setViewPos(this.mBtnNissanRzcRvs[4], 412, 259, 96, 68);
        this.mBtnNissanRzcRvs[5].setDrawable(R.drawable.can_oj_right_big_up, R.drawable.can_oj_right_big_dn);
        setViewPos(this.mBtnNissanRzcRvs[5], BTN_GEELY_YJX6_FXP, 215, 95, 160);
        this.mBtnNissanRzcRvs[6].setDrawable(R.drawable.can_oj_car_up, R.drawable.can_oj_car_dn);
        setViewPos(this.mBtnNissanRzcRvs[6], 459, dy + 500, 161, 91);
        this.mBtnNissanRzcRvs[7].setDrawable(R.drawable.can_qj_sjx_up, R.drawable.can_qj_sjx_dn);
        setViewPos(this.mBtnNissanRzcRvs[7], 750, dy + 500, 161, 91);
        this.mBtnNissanRzcRvs[8].setDrawable(R.drawable.can_oj_pa_up, R.drawable.can_oj_pa_dn);
        setViewPos(this.mBtnNissanRzcRvs[8], 459, dy + 500, 161, 91);
        this.mBtnNissanRzcRvs[9].setDrawable(R.drawable.can_oj_park_up, R.drawable.can_oj_park_dn);
        setViewPos(this.mBtnNissanRzcRvs[9], 283, dy + 500, 161, 91);
        this.mBtnNissanRzcRvs[10].setDrawable(R.drawable.can_oj_rect_up, R.drawable.can_oj_rect_dn);
        setViewPos(this.mBtnNissanRzcRvs[10], 106, dy + 500, 161, 91);
        this.mBtnNissanRzcRvs[10].setText(R.string.can_assist_cancel);
        this.mBtnNissanRzcRvs[10].setTextSize(0, 27.0f);
        this.mBtnNissanRzcRvs[11].setDrawable(R.drawable.can_oj_rect_up, R.drawable.can_oj_rect_dn);
        setViewPos(this.mBtnNissanRzcRvs[11], 283, dy + 500, 161, 91);
        this.mBtnNissanRzcRvs[11].setText(R.string.can_assist_start);
        this.mBtnNissanRzcRvs[11].setTextSize(0, 27.0f);
        this.mBtnNissanRzcRvs[12].setDrawable(R.drawable.can_oj_rect_up, R.drawable.can_oj_rect_dn);
        setViewPos(this.mBtnNissanRzcRvs[12], 459, dy + 500, 161, 91);
        this.mBtnNissanRzcRvs[12].setText(R.string.can_assist_back);
        this.mBtnNissanRzcRvs[12].setTextSize(0, 27.0f);
        this.mBtnNissanRzcRvs[13].setDrawable(R.drawable.can_oj_rect_up, R.drawable.can_oj_rect_dn);
        setViewPos(this.mBtnNissanRzcRvs[13], 459, dy + 500, 161, 91);
        this.mBtnNissanRzcRvs[13].setText("Hint");
        this.mBtnNissanRzcRvs[13].setTextSize(0, 27.0f);
        this.mBtnNissanRzcRvs[14].setDrawable(R.drawable.can_teana_aid_czs01_up, R.drawable.can_teana_aid_czs01_dn);
        setViewPos(this.mBtnNissanRzcRvs[14], 52, 110, 214, 128);
        this.mBtnNissanRzcRvs[15].setDrawable(R.drawable.can_teana_aid_czs02_up, R.drawable.can_teana_aid_czs02_dn);
        setViewPos(this.mBtnNissanRzcRvs[15], 277, 110, 214, 128);
        this.mBtnNissanRzcRvs[16].setDrawable(R.drawable.can_teana_aid_pxs01_up, R.drawable.can_teana_aid_pxs01_dn);
        setViewPos(this.mBtnNissanRzcRvs[16], 52, 291, 214, 128);
        this.mBtnNissanRzcRvs[17].setDrawable(R.drawable.can_teana_aid_pxs02_up, R.drawable.can_teana_aid_pxs02_dn);
        setViewPos(this.mBtnNissanRzcRvs[17], 277, 291, 214, 128);
        this.mBtnNissanRzcRvs[18].setDrawable(R.drawable.can_teana_aid_xls01_up, R.drawable.can_teana_aid_xls01_dn);
        setViewPos(this.mBtnNissanRzcRvs[18], 52, 110, 214, 128);
        this.mBtnNissanRzcRvs[19].setDrawable(R.drawable.can_teana_aid_xls02_up, R.drawable.can_teana_aid_xls02_dn);
        setViewPos(this.mBtnNissanRzcRvs[19], 277, 110, 214, 128);
        this.mBtnNissanRzcRvs[20].setDrawable(R.drawable.can_teana_aid_pulldown_up, R.drawable.can_teana_aid_pulldown_dn);
        setViewPos(this.mBtnNissanRzcRvs[20], 496, 344, 80, 75);
        this.mBtnNissanRzcRvs[21].setDrawable(R.drawable.can_teana_aid_pullup_up, R.drawable.can_teana_aid_pullup_dn);
        setViewPos(this.mBtnNissanRzcRvs[21], 496, 110, 80, 75);
        this.mNissanRzcView = new CustomImgView[2];
        for (int i2 = 0; i2 < this.mNissanRzcView.length; i2++) {
            this.mNissanRzcView[i2] = new CustomImgView(context);
            this.mListBotView.add(this.mNissanRzcView[i2]);
        }
        this.mNissanRzcView[0].setStateDrawable(R.drawable.can_oj_center, R.drawable.can_oj_center);
        this.mNissanRzcView[0].Show(false);
        setViewPos(this.mNissanRzcView[0], KeyDef.RKEY_EJECT_L, 263, 59, 60);
        this.mNissanRzcView[1].setStateDrawable(R.drawable.can_oj_car, R.drawable.can_oj_car02);
        setViewPos(this.mNissanRzcView[1], 18, dy + BTN_HMS7_ESC, 75, 82);
        this.mNissanRzcTsyArr = context.getResources().getStringArray(R.array.can_nissan_xtral_rvs_tsy);
        this.mNissanRzcTsy = new CustomTextView(context);
        this.mNissanRzcTsy.setTextSize(0, 33.0f);
        this.mNissanRzcTsy.setGravity(1);
        this.mNissanRzcTsy.setTextColor(-16711936);
        setViewPos(this.mNissanRzcTsy, 0, 15, 620, 120);
        if (CanJni.GetSubType() == 8) {
            this.mNissanRzcDeclareArr = context.getResources().getStringArray(R.array.can_nissan_teana_rvs_declare);
        } else {
            this.mNissanRzcDeclareArr = context.getResources().getStringArray(R.array.can_nissan_xtral_rvs_declare);
        }
        this.mNissanRzcDeclare = new CustomTextView[7];
        for (int i3 = 0; i3 < this.mNissanRzcDeclare.length; i3++) {
            this.mNissanRzcDeclare[i3] = new CustomTextView(context);
            this.mListBotView.add(this.mNissanRzcDeclare[i3]);
            this.mNissanRzcDeclare[i3].ShowGone(false);
        }
        this.mNissanRzcDeclare[0].setTextSize(0, 42.0f);
        this.mNissanRzcDeclare[0].setGravity(3);
        this.mNissanRzcDeclare[0].setTextColor(-1);
        this.mNissanRzcDeclare[0].setText(this.mNissanRzcDeclareArr[0]);
        setViewPos(this.mNissanRzcDeclare[0], 0, 55, 1024, 60);
        for (int i4 = 1; i4 < 4; i4++) {
            this.mNissanRzcDeclare[i4].setTextSize(0, 38.0f);
            this.mNissanRzcDeclare[i4].setGravity(3);
            this.mNissanRzcDeclare[i4].setTextColor(-1);
            this.mNissanRzcDeclare[i4].setText(this.mNissanRzcDeclareArr[i4]);
            setViewPos(this.mNissanRzcDeclare[i4], 0, (i4 * 60) + 90, 1024, 50);
        }
        for (int i5 = 4; i5 < 7; i5++) {
            this.mNissanRzcDeclare[i5].setTextSize(0, 30.0f);
            this.mNissanRzcDeclare[i5].setGravity(3);
            this.mNissanRzcDeclare[i5].setTextColor(-256);
            this.mNissanRzcDeclare[i5].setText(this.mNissanRzcDeclareArr[i5]);
            setViewPos(this.mNissanRzcDeclare[i5], 0, (i5 * 40) + 190, 1024, 50);
        }
        this.mNissanRzcDeclare2 = new CustomTextView[3];
        for (int i6 = 0; i6 < this.mNissanRzcDeclare2.length; i6++) {
            this.mNissanRzcDeclare2[i6] = new CustomTextView(context);
            this.mListBotView.add(this.mNissanRzcDeclare2[i6]);
            this.mNissanRzcDeclare2[i6].ShowGone(false);
            this.mNissanRzcDeclare2[i6].setTextSize(0, 33.0f);
            this.mNissanRzcDeclare2[i6].setGravity(17);
            this.mNissanRzcDeclare2[i6].setTextColor(-1);
        }
        this.mNissanRzcDeclare2[0].setText(R.string.can_czstc);
        setViewPos(this.mNissanRzcDeclare2[0], 54, 64, 436, 44);
        this.mNissanRzcDeclare2[1].setText(R.string.can_pxstc);
        setViewPos(this.mNissanRzcDeclare2[1], 54, Can.CAN_FORD_MONDEO_XFY, 436, 44);
        this.mNissanRzcDeclare2[2].setText(R.string.can_xlstc);
        setViewPos(this.mNissanRzcDeclare2[2], 54, 64, 436, 44);
    }

    private void InitLandWind(Context context) {
        this.mBtnLandWind = new ParamButton[11];
        for (int i = 0; i < this.mBtnLandWind.length; i++) {
            this.mBtnLandWind[i] = new ParamButton(context);
            this.mBtnLandWind[i].setOnClickListener(this);
            this.mBtnLandWind[i].setTag(Integer.valueOf(i + BTN_LANDWIND_2D3D));
            this.mBtnLandWind[i].Show(false);
            this.mListBotView.add(this.mBtnLandWind[i]);
        }
        this.mBtnLandWind[0].setDrawable(R.drawable.can_rh7_2d_up, R.drawable.can_rh7_2d_dn);
        setViewPos(this.mBtnLandWind[0], 11, 110, 90, 90);
        this.mBtnLandWind[1].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnLandWind[1], 11, 508, 82, 72);
        this.mBtnLandWind[2].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnLandWind[2], 131, 508, 82, 72);
        this.mBtnLandWind[3].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnLandWind[3], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnLandWind[4].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnLandWind[4], 372, 508, 82, 72);
        this.mBtnLandWind[5].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnLandWind[5], 11, 508, 82, 72);
        this.mBtnLandWind[6].setDrawable(R.drawable.can_gs5_bot_13_up, R.drawable.can_gs5_bot_13_dn);
        setViewPos(this.mBtnLandWind[6], 131, 508, 82, 72);
        this.mBtnLandWind[7].setDrawable(R.drawable.can_gs5_bot_15_up, R.drawable.can_gs5_bot_15_dn);
        setViewPos(this.mBtnLandWind[7], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnLandWind[8].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnLandWind[8], 372, 508, 82, 72);
        this.mBtnLandWind[9].setDrawable(R.drawable.can_gs5_bot_12_up, R.drawable.can_gs5_bot_12_dn);
        setViewPos(this.mBtnLandWind[9], 492, 508, 82, 72);
        this.mBtnLandWind[10].setDrawable(R.drawable.can_gs5_bot_14_up, R.drawable.can_gs5_bot_14_dn);
        setViewPos(this.mBtnLandWind[10], BTN_CCH9_MODE3, 508, 82, 72);
        CanJni.LoadWindRzcQuery(40);
    }

    private void InitBlsuT5(Context context) {
        this.mBtnBlsuT5 = new ParamButton[4];
        for (int i = 0; i < this.mBtnBlsuT5.length; i++) {
            this.mBtnBlsuT5[i] = new ParamButton(context);
            this.mBtnBlsuT5[i].setOnTouchListener(this);
            this.mBtnBlsuT5[i].setTag(Integer.valueOf(i + 550));
            this.mBtnBlsuT5[i].Show(false);
            this.mListBotView.add(this.mBtnBlsuT5[i]);
        }
        this.mBtnBlsuT5[0].setDrawable(R.drawable.can_rh7_2d_up, R.drawable.can_rh7_2d_dn);
        setViewPos(this.mBtnBlsuT5[0], 111, 500, 90, 90);
        this.mBtnBlsuT5[1].setDrawable(R.drawable.can_rh7_3d_up, R.drawable.can_rh7_3d_dn);
        setViewPos(this.mBtnBlsuT5[1], 111, 500, 90, 90);
        this.mBtnBlsuT5[2].setDrawable(R.drawable.can_rh7_return_up, R.drawable.can_rh7_return_dn);
        setViewPos(this.mBtnBlsuT5[2], Can.CAN_FLAT_WC, 500, 90, 90);
        this.mBtnBlsuT5[3].setDrawable(R.drawable.can_rh7_qp_up, R.drawable.can_rh7_qp_dn);
        setViewPos(this.mBtnBlsuT5[3], Can.CAN_FLAT_WC, 500, 90, 90);
        this.mBlsuT5CamSta = new CanDataInfo.BlsuT5_CamSta();
    }

    private void InitBlsuT5Rzc(Context context) {
        this.mBtnBlsuRzcT5 = new ParamButton[4];
        for (int i = 0; i < this.mBtnBlsuRzcT5.length; i++) {
            this.mBtnBlsuRzcT5[i] = new ParamButton(context);
            this.mBtnBlsuRzcT5[i].setOnClickListener(this);
            this.mBtnBlsuRzcT5[i].setTag(Integer.valueOf(i + BTN_BLSU_RZC_T5_2D));
            this.mListBotView.add(this.mBtnBlsuRzcT5[i]);
            this.mBtnBlsuRzcT5[i].Show(false);
        }
        this.mBtnBlsuRzcT5[0].setDrawable(R.drawable.can_rh7_2d_up, R.drawable.can_rh7_2d_dn);
        setViewPos(this.mBtnBlsuRzcT5[0], BTN_TRUMPCHI_GS4_MODE1, 500, 90, 90);
        this.mBtnBlsuRzcT5[1].setDrawable(R.drawable.can_rh7_3d_up, R.drawable.can_rh7_3d_dn);
        setViewPos(this.mBtnBlsuRzcT5[1], 500, 500, 90, 90);
        this.mBtnBlsuRzcT5[2].setDrawable(R.drawable.can_rh7_return_up, R.drawable.can_rh7_return_dn);
        setViewPos(this.mBtnBlsuRzcT5[2], 600, 500, 90, 90);
        this.mBtnBlsuRzcT5[3].setDrawable(R.drawable.can_rh7_qp_up, R.drawable.can_rh7_qp_dn);
        setViewPos(this.mBtnBlsuRzcT5[3], BTN_CC_WC_DIRECTION1, 500, 90, 90);
        this.mBtnBlsuRzcT5Dir = new ParamButton[4];
        for (int i2 = 0; i2 < this.mBtnBlsuRzcT5Dir.length; i2++) {
            this.mBtnBlsuRzcT5Dir[i2] = new ParamButton(context);
            this.mBtnBlsuRzcT5Dir[i2].setTag(Integer.valueOf(i2 + BTN_BLSU_RZC_T5_UP));
            this.mBtnBlsuRzcT5Dir[i2].setBackgroundColor(0);
            this.mBtnBlsuRzcT5Dir[i2].setOnClickListener(this);
        }
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(outMetrics);
        int radius = (int) Math.min((((float) outMetrics.widthPixels) * 1.0f) / 1024.0f, (((float) outMetrics.heightPixels) * 1.0f) / 600.0f);
        setViewPos(this.mBtnBlsuRzcT5Dir[0], radius * 1, 0, radius * 374, radius * 120);
        setViewPos(this.mBtnBlsuRzcT5Dir[1], radius * 1, radius * 480, radius * 374, radius * 120);
        setViewPos(this.mBtnBlsuRzcT5Dir[2], radius * 1, radius * 120, radius * 120, radius * 360);
        setViewPos(this.mBtnBlsuRzcT5Dir[3], radius * 255, radius * 120, radius * 120, radius * 360);
    }

    public void InitTrumpchiGs7(Context context) {
        this.mBtnTrumpchiGs7Mode = new ParamButton[8];
        for (int i = 0; i < this.mBtnTrumpchiGs7Mode.length; i++) {
            this.mBtnTrumpchiGs7Mode[i] = new ParamButton(context);
            this.mBtnTrumpchiGs7Mode[i].setTag(Integer.valueOf(i + BTN_TRUMPCHI_GS7_MODE1));
            this.mBtnTrumpchiGs7Mode[i].setBackgroundColor(0);
            this.mBtnTrumpchiGs7Mode[i].setOnClickListener(this);
        }
        if (MainSet.GetScreenType() == 10) {
            setViewPos(this.mBtnTrumpchiGs7Mode[0], AtcDisplaySettingsUtils.SPECIFIC_Y_SMALL2, 199, 164, 101);
            setViewPos(this.mBtnTrumpchiGs7Mode[1], 178, 300, 188, 125);
            setViewPos(this.mBtnTrumpchiGs7Mode[2], 533, 300, 194, 125);
            setViewPos(this.mBtnTrumpchiGs7Mode[3], 369, 425, 163, Can.CAN_CC_WC);
            setViewPos(this.mBtnTrumpchiGs7Mode[4], 364, 300, 168, 125);
            setViewPos(this.mBtnTrumpchiGs7Mode[5], 108, BTN_CCH9_MODE5, 180, 106);
            setViewPos(this.mBtnTrumpchiGs7Mode[6], 360, BTN_CCH9_MODE5, 175, 106);
            setViewPos(this.mBtnTrumpchiGs7Mode[7], BTN_CCH9_MODE2, BTN_CCH9_MODE5, 165, 106);
            return;
        }
        setViewPos(this.mBtnTrumpchiGs7Mode[0], 296, 166, 131, 84);
        setViewPos(this.mBtnTrumpchiGs7Mode[1], 142, Can.CAN_NISSAN_XFY, 150, 104);
        setViewPos(this.mBtnTrumpchiGs7Mode[2], 426, Can.CAN_NISSAN_XFY, Can.CAN_DFFG_S560, 104);
        setViewPos(this.mBtnTrumpchiGs7Mode[3], 295, 354, 130, 128);
        setViewPos(this.mBtnTrumpchiGs7Mode[4], 291, Can.CAN_NISSAN_XFY, 134, 104);
        setViewPos(this.mBtnTrumpchiGs7Mode[5], 86, 512, 144, 88);
        setViewPos(this.mBtnTrumpchiGs7Mode[6], 288, 512, Can.CAN_BENC_ZMYT, 88);
        setViewPos(this.mBtnTrumpchiGs7Mode[7], 489, 512, 132, 88);
    }

    public void InitFordActivePark(Context context) {
        this.mActivePark = new CanDataInfo.FordActivePark();
        this.mStatusValues = context.getResources().getStringArray(R.array.can_ford_active_park);
        this.mTvParkStatus = new TextView(context);
        this.mTvParkStatus.setTextColor(-1);
        this.mTvParkStatus.setBackgroundColor(Color.parseColor("#cc414141"));
        this.mTvParkStatus.setAlpha(55.0f);
        this.mTvParkStatus.setGravity(17);
        this.mTvParkStatus.setPadding(20, 16, 20, 16);
        this.mTvParkStatus.setTextSize(0, 27.0f);
        this.mTvParkStatus.setVisibility(4);
        setViewPos(this.mTvParkStatus, KeyDef.RKEY_MEDIA_OSD, 50, BTN_TRUMPCHI_GS4_MODE1, -2);
        this.mIvParkIcon = new ImageView(context);
        this.mIvParkIcon.setVisibility(4);
        setViewPos(this.mIvParkIcon, 60, 60, -2, -2);
        this.mIvParkIcon.setBackgroundColor(Color.parseColor("#cc414141"));
        this.mIvParkIcon.setAlpha(Can.CAN_NISSAN_XFY);
        this.mDialogLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(360, 360);
        lp.addRule(13);
        this.mDialogLayout.addView(getDialogContent(context), lp);
        this.mDialogLayout.setVisibility(8);
        this.mDialogLayout.setBackgroundResource(R.drawable.can_ford_park_overlay);
        setViewPos(this.mDialogLayout, 0, 0, -1, -1);
        if (CanJni.GetCanType() == 146 && CanJni.GetSubType() == 6) {
            this.mBtnFordRzcMode = new ParamButton[4];
            for (int i = 0; i < this.mBtnFordRzcMode.length; i++) {
                this.mBtnFordRzcMode[i] = new ParamButton(context);
                this.mBtnFordRzcMode[i].setTag(Integer.valueOf(i + BTN_FORD_RZC_CLOSE));
                this.mBtnFordRzcMode[i].setOnClickListener(this);
            }
            this.mBtnFordRzcMode[0].setDrawable(R.drawable.can_gs5_bot_26_up, R.drawable.can_gs5_bot_26_dn);
            setViewPos(this.mBtnFordRzcMode[0], BTN_SENOVA_SUB_BJ40_MODE1, 498, 109, 82);
            this.mBtnFordRzcMode[1].setDrawable(R.drawable.can_gs5_bot_24_up, R.drawable.can_gs5_bot_24_dn);
            setViewPos(this.mBtnFordRzcMode[1], 390, 498, 109, 82);
            this.mBtnFordRzcMode[2].setDrawable(R.drawable.can_gs5_bot_23_up, R.drawable.can_gs5_bot_23_dn);
            setViewPos(this.mBtnFordRzcMode[2], 277, 498, 109, 82);
            this.mBtnFordRzcMode[3].setDrawable(R.drawable.can_gs5_bot_25_up, R.drawable.can_gs5_bot_25_dn);
            setViewPos(this.mBtnFordRzcMode[3], BTN_YG9_XBS_MODE1, 498, 109, 82);
        }
    }

    public void InitGMRzcActivePark(Context context) {
        this.mGMActivePark = new CanDataInfo.GM_AutoPark();
        this.mStatusValues = context.getResources().getStringArray(R.array.can_gmrzc_active_park);
        this.mTvParkStatus = new TextView(context);
        this.mTvParkStatus.setTextColor(-1);
        this.mTvParkStatus.setBackgroundColor(Color.parseColor("#cc414141"));
        this.mTvParkStatus.setAlpha(55.0f);
        this.mTvParkStatus.setGravity(17);
        this.mTvParkStatus.setPadding(20, 16, 20, 16);
        this.mTvParkStatus.setTextSize(0, 27.0f);
        this.mTvParkStatus.setVisibility(4);
        setViewPos(this.mTvParkStatus, KeyDef.RKEY_MEDIA_OSD, 50, BTN_TRUMPCHI_GS4_MODE1, -2);
        this.mCanVerticalBar = new CanVerticalBar(context, R.drawable.can_gm_park_20_dn, R.drawable.can_gm_park_20_up);
        setViewPos(this.mCanVerticalBar, 60, 60, 41, 73);
        this.mCanVerticalBar.setRotationX(180.0f);
        this.mCanVerticalBar.setMinMax(0.0f, 100.0f);
        this.mCanVerticalBar.setCurPos(75);
        this.mCanVerticalBar.setVisibility(8);
    }

    public void InitCamery(Context context) {
        this.mBtnCameryMode = new ParamButton[2];
        for (int i = 0; i < this.mBtnCameryMode.length; i++) {
            this.mBtnCameryMode[i] = new ParamButton(context);
            this.mBtnCameryMode[i].setTag(Integer.valueOf(i + BTN_CAMERY_2018_MODE1));
            this.mBtnCameryMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnCameryMode[i]);
        }
        this.mBtnCameryMode[0].setDrawable(R.drawable.can_camera_tarack_wan_up, R.drawable.can_camera_tarack_wan_dn);
        this.mBtnCameryMode[1].setDrawable(R.drawable.can_camera_tarack_line_up, R.drawable.can_camera_tarack_line_dn);
        if (MainSet.GetScreenType() == 3) {
            setViewPos(this.mBtnCameryMode[0], 101, 487, 128, 64);
            setViewPos(this.mBtnCameryMode[1], Can.CAN_NISSAN_RICH6_WC, 487, 128, 64);
            return;
        }
        setViewPos(this.mBtnCameryMode[0], 101, 500, 128, 64);
        setViewPos(this.mBtnCameryMode[1], Can.CAN_NISSAN_RICH6_WC, 500, 128, 64);
    }

    public void InitSenovaSubBJ40(Context context) {
        this.mBtnSenovaSubBJ40 = new ParamButton[4];
        for (int i = 0; i < this.mBtnSenovaSubBJ40.length; i++) {
            this.mBtnSenovaSubBJ40[i] = new ParamButton(context);
            this.mBtnSenovaSubBJ40[i].setTag(Integer.valueOf(i + BTN_SENOVA_SUB_BJ40_MODE2));
            this.mBtnSenovaSubBJ40[i].setOnTouchListener(this);
            this.mBtnSenovaSubBJ40[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnSenovaSubBJ40[i]);
        }
        this.mBtnSenovaSubBJ40[0].setDrawable(R.drawable.can_camera_mode_up, R.drawable.can_camera_mode_dn);
        setViewPos(this.mBtnSenovaSubBJ40[0], 286, 508, 162, 72);
        this.mBtnSenovaSubBJ40[1].setDrawable(R.drawable.can_yg_mode_bz_up, R.drawable.can_yg_mode_bz_dn);
        setViewPos(this.mBtnSenovaSubBJ40[1], 466, 508, 82, 72);
        this.mBtnSenovaSubBJ40[2].setDrawable(R.drawable.can_yg_mode_fj_up, R.drawable.can_yg_mode_fj_dn);
        setViewPos(this.mBtnSenovaSubBJ40[2], BTN_TRUMPCHI_GS7_MODE7, 508, 82, 72);
        this.mBtnSenovaSubBJ40[3].setDrawable(R.drawable.can_yg_mode_gj_up, R.drawable.can_yg_mode_gj_dn);
        setViewPos(this.mBtnSenovaSubBJ40[3], 666, 508, 82, 72);
    }

    public void InitVwWc(Context context) {
        this.mBtnVwWcMode = new ParamButton[3];
        this.mVwWcCamera = new CanDataInfo.VwWcAssistSet();
        for (int i = 0; i < this.mBtnVwWcMode.length; i++) {
            this.mBtnVwWcMode[i] = new ParamButton(context);
            this.mBtnVwWcMode[i].setTag(Integer.valueOf(i + BTN_VW_WC_MODE1));
            this.mBtnVwWcMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnVwWcMode[i]);
        }
        this.mBtnVwWcMode[0].setDrawable(R.drawable.can_gs5_bot_04_up, R.drawable.can_gs5_bot_04_dn);
        setViewPos(this.mBtnVwWcMode[0], 131, 508, 82, 72);
        this.mBtnVwWcMode[1].setDrawable(R.drawable.can_gs5_bot_05_up, R.drawable.can_gs5_bot_05_dn);
        setViewPos(this.mBtnVwWcMode[1], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnVwWcMode[2].setDrawable(R.drawable.can_golf_vol_up, R.drawable.can_golf_vol_dn);
        setViewPos(this.mBtnVwWcMode[2], 372, 508, 82, 72);
    }

    public void InitCCWc(Context context) {
        this.mBtnCCWcMode = new ParamButton[4];
        for (int i = 0; i < this.mBtnCCWcMode.length; i++) {
            this.mBtnCCWcMode[i] = new ParamButton(context);
            this.mBtnCCWcMode[i].setTag(Integer.valueOf(i + BTN_CC_WC_DIRECTION1));
            this.mBtnCCWcMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnCCWcMode[i]);
        }
        this.mBtnCCWcMode[0].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnCCWcMode[0], 131, 508, 82, 72);
        this.mBtnCCWcMode[1].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnCCWcMode[1], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnCCWcMode[2].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnCCWcMode[2], 372, 508, 82, 72);
        this.mBtnCCWcMode[3].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnCCWcMode[3], 492, 508, 82, 72);
    }

    public void InitCCH6Wc(Context context) {
        this.mBtnCCH6WcMode = new ParamButton[5];
        this.mCcH6WcCamerSta = new CanDataInfo.CcH6WcCamerSta();
        for (int i = 0; i < this.mBtnCCH6WcMode.length; i++) {
            this.mBtnCCH6WcMode[i] = new ParamButton(context);
            this.mBtnCCH6WcMode[i].setTag(Integer.valueOf(i + BTN_CC_H6_WC_DIRECTION1));
            this.mBtnCCH6WcMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnCCH6WcMode[i]);
        }
        this.mBtnCCH6WcMode[0].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnCCH6WcMode[0], 131, 508, 82, 72);
        this.mBtnCCH6WcMode[1].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnCCH6WcMode[1], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnCCH6WcMode[2].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnCCH6WcMode[2], 372, 508, 82, 72);
        this.mBtnCCH6WcMode[3].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnCCH6WcMode[3], 492, 508, 82, 72);
        this.mBtnCCH6WcMode[4].setDrawable(R.drawable.camera_track_up, R.drawable.camera_track_dn);
        setViewPos(this.mBtnCCH6WcMode[4], 917, BTN_NISSAN_XTRAL_RVS_ASSIST4, 107, 57);
    }

    public void InitDFFGRzc(Context context) {
        this.mBtnDFFGRzcMode = new ParamButton[5];
        this.mDffg_RzcAvm = new CanDataInfo.Dffg_RzcAvm();
        for (int i = 0; i < this.mBtnDFFGRzcMode.length; i++) {
            this.mBtnDFFGRzcMode[i] = new ParamButton(context);
            this.mBtnDFFGRzcMode[i].setTag(Integer.valueOf(i + BTN_DFFG_RZC_FRONT));
            this.mBtnDFFGRzcMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnDFFGRzcMode[i]);
        }
        this.mBtnDFFGRzcMode[0].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnDFFGRzcMode[0], 11, 508, 82, 72);
        this.mBtnDFFGRzcMode[1].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnDFFGRzcMode[1], 131, 508, 82, 72);
        this.mBtnDFFGRzcMode[2].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnDFFGRzcMode[2], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnDFFGRzcMode[3].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnDFFGRzcMode[3], 372, 508, 82, 72);
        this.mBtnDFFGRzcMode[4].setDrawable(R.drawable.can_camera_track_esc_up, R.drawable.can_camera_track_esc_dn);
        setViewPos(this.mBtnDFFGRzcMode[4], 909, 540, 115, 60);
    }

    public void InitDFFGS560(Context context) {
        this.mBtnDFFGMode = new ParamButton[4];
        for (int i = 0; i < this.mBtnDFFGMode.length; i++) {
            this.mBtnDFFGMode[i] = new ParamButton(context);
            this.mBtnDFFGMode[i].setTag(Integer.valueOf(i + 800));
            this.mBtnDFFGMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnDFFGMode[i]);
        }
        this.mBtnDFFGMode[0].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnDFFGMode[0], 131, 508, 82, 72);
        this.mBtnDFFGMode[1].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnDFFGMode[1], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnDFFGMode[2].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnDFFGMode[2], 372, 508, 82, 72);
        this.mBtnDFFGMode[3].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnDFFGMode[3], 492, 508, 82, 72);
    }

    public void InitCs75Wc(Context context) {
        this.mCs75CameraSta = new CanDataInfo.Cs75WcCameraSta();
        this.mBtnCs75WcMode = new ParamButton(context);
        this.mBtnCs75WcMode.setTag(Integer.valueOf(BTN_CS75_WC_MODE));
        this.mBtnCs75WcMode.setStateUpDn(R.drawable.can_camera_mode_up, R.drawable.can_camera_mode_dn);
        this.mBtnCs75WcMode.setOnClickListener(this);
        setViewPos(this.mBtnCs75WcMode, 100, 465, 150, 60);
        this.mListBotView.add(this.mBtnCs75WcMode);
    }

    public void InitTrumpchiWc(Context context) {
        this.mTrumpchiWcCamera = new CanDataInfo.GCWcCameraSta();
        this.mBtnTrumpchiWcMode = new ParamButton[7];
        for (int i = 0; i < this.mBtnTrumpchiWcMode.length; i++) {
            this.mBtnTrumpchiWcMode[i] = new ParamButton(context);
            this.mBtnTrumpchiWcMode[i].setTag(Integer.valueOf(i + 1001));
            this.mBtnTrumpchiWcMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnTrumpchiWcMode[i]);
        }
        this.mBtnTrumpchiWcMode[0].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnTrumpchiWcMode[0], 131, 508, 82, 72);
        this.mBtnTrumpchiWcMode[1].setDrawable(R.drawable.can_gs5_bot_03_up, R.drawable.can_gs5_bot_03_dn);
        setViewPos(this.mBtnTrumpchiWcMode[1], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnTrumpchiWcMode[2].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnTrumpchiWcMode[2], 372, 508, 82, 72);
        this.mBtnTrumpchiWcMode[3].setDrawable(R.drawable.can_gs5_bot_04_up, R.drawable.can_gs5_bot_04_dn);
        setViewPos(this.mBtnTrumpchiWcMode[3], 492, 508, 82, 72);
        this.mBtnTrumpchiWcMode[4].setDrawable(R.drawable.can_gs5_bot_05_up, R.drawable.can_gs5_bot_05_dn);
        setViewPos(this.mBtnTrumpchiWcMode[4], BTN_CCH9_MODE3, 508, 82, 72);
        this.mBtnTrumpchiWcMode[5].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnTrumpchiWcMode[5], 732, 508, 82, 72);
        this.mBtnTrumpchiWcMode[6].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnTrumpchiWcMode[6], 852, 508, 82, 72);
    }

    public void InitJacRefineWc(Context context) {
        this.mJacWcAvmData = new CanDataInfo.JacWc_AvmInfo();
        this.mBtnJacRefineWcMode = new ParamButton[4];
        this.mBtnJacRefineWcStats = new ParamButton[2];
        for (int i = 0; i < this.mBtnJacRefineWcMode.length; i++) {
            this.mBtnJacRefineWcMode[i] = new ParamButton(context);
            this.mBtnJacRefineWcMode[i].setTag(Integer.valueOf(i + BTN_JAC_REFINE_WC_MODE1));
            this.mBtnJacRefineWcMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnJacRefineWcMode[i]);
        }
        for (int i2 = 0; i2 < this.mBtnJacRefineWcStats.length; i2++) {
            this.mBtnJacRefineWcStats[i2] = new ParamButton(context);
            this.mBtnJacRefineWcStats[i2].setTag(Integer.valueOf(i2 + BTN_JAC_REFINE_WC_3D));
            this.mBtnJacRefineWcStats[i2].setOnClickListener(this);
            this.mListBotView.add(this.mBtnJacRefineWcStats[i2]);
        }
        this.mBtnJacRefineWcEsc = new ParamButton(context);
        this.mBtnJacRefineWcEsc.setTag(Integer.valueOf(BTN_JAC_REFINE_WC_ESC));
        this.mBtnJacRefineWcEsc.setOnClickListener(this);
        this.mBtnJacRefineWcEsc.setDrawable(R.drawable.can_camera_track_esc_up, R.drawable.can_camera_track_esc_dn);
        this.mListBotView.add(this.mBtnJacRefineWcEsc);
        setViewPos(this.mBtnJacRefineWcEsc, 909, 540, 115, 60);
        this.mBtnJacRefineWcStats[0].setDrawable(R.drawable.can_rh7_3d_up, R.drawable.can_rh7_3d_dn);
        setViewPos(this.mBtnJacRefineWcStats[0], 41, 496, 90, 90);
        this.mBtnJacRefineWcStats[1].setDrawable(R.drawable.can_rh7_2d_up, R.drawable.can_rh7_2d_dn);
        setViewPos(this.mBtnJacRefineWcStats[1], 162, 496, 90, 90);
        this.mBtnJacRefineWcMode[0].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnJacRefineWcMode[0], 422, 508, 82, 72);
        this.mBtnJacRefineWcMode[1].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnJacRefineWcMode[1], BTN_NISSAN_XTRAL_RVS_ASSIST3, 508, 82, 72);
        this.mBtnJacRefineWcMode[2].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnJacRefineWcMode[2], 662, 508, 82, 72);
        this.mBtnJacRefineWcMode[3].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnJacRefineWcMode[3], KeyDef.SKEY_VOLDN_4, 508, 82, 72);
    }

    public void InitJacRefine(Context context) {
        this.mJacAvmData = new CanDataInfo.JAC_AVM_DATA();
        this.mBtnJacRefineMode = new ParamButton[4];
        this.mBtnJacRefineStats = new ParamButton[3];
        for (int i = 0; i < this.mBtnJacRefineMode.length; i++) {
            this.mBtnJacRefineMode[i] = new ParamButton(context);
            this.mBtnJacRefineMode[i].setTag(Integer.valueOf(i + BTN_JAC_REFINE_MODE1));
            this.mBtnJacRefineMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnJacRefineMode[i]);
        }
        for (int i2 = 0; i2 < this.mBtnJacRefineStats.length; i2++) {
            this.mBtnJacRefineStats[i2] = new ParamButton(context);
            this.mBtnJacRefineStats[i2].setTag(Integer.valueOf(i2 + BTN_JAC_REFINE_3D));
            this.mBtnJacRefineStats[i2].setOnClickListener(this);
            this.mListBotView.add(this.mBtnJacRefineStats[i2]);
        }
        this.mBtnJacRefineEsc = new ParamButton(context);
        this.mBtnJacRefineEsc.setTag(Integer.valueOf(BTN_JAC_REFINE_ESC));
        this.mBtnJacRefineEsc.setOnClickListener(this);
        this.mBtnJacRefineEsc.setDrawable(R.drawable.can_camera_track_esc_up, R.drawable.can_camera_track_esc_dn);
        this.mListBotView.add(this.mBtnJacRefineEsc);
        setViewPos(this.mBtnJacRefineEsc, 909, 540, 115, 60);
        this.mBtnJacRefineStats[0].setDrawable(R.drawable.can_rh7_3d_up, R.drawable.can_rh7_3d_dn);
        setViewPos(this.mBtnJacRefineStats[0], 41, 496, 90, 90);
        this.mBtnJacRefineStats[1].setDrawable(R.drawable.can_rh7_2d_up, R.drawable.can_rh7_2d_dn);
        setViewPos(this.mBtnJacRefineStats[1], 162, 496, 90, 90);
        this.mBtnJacRefineStats[2].setDrawable(R.drawable.can_rh7_qp_up, R.drawable.can_rh7_qp_dn);
        setViewPos(this.mBtnJacRefineStats[2], 282, 496, 90, 90);
        this.mBtnJacRefineMode[0].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnJacRefineMode[0], 422, 508, 82, 72);
        this.mBtnJacRefineMode[1].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnJacRefineMode[1], BTN_NISSAN_XTRAL_RVS_ASSIST3, 508, 82, 72);
        this.mBtnJacRefineMode[2].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnJacRefineMode[2], 662, 508, 82, 72);
        this.mBtnJacRefineMode[3].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnJacRefineMode[3], KeyDef.SKEY_VOLDN_4, 508, 82, 72);
    }

    private View getDialogContent(Context context) {
        View view = View.inflate(context, R.layout.park_dialog_content, (ViewGroup) null);
        this.mIvWarnning = (ImageView) view.findViewById(R.id.iv_warnning);
        this.mTvTakeCare = (TextView) view.findViewById(R.id.tv_take_care);
        this.mTvDialogStatus = (TextView) view.findViewById(R.id.tv_status);
        this.mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        this.mTvTime = (TextView) view.findViewById(R.id.tv_time);
        return view;
    }

    private void InitChanA_Wc(Context context) {
        this.mChanaWcCamera = new CanDataInfo.ChanAWcCameraSta();
        this.mBtnChanaWcCamMode = new ParamButton(context);
        this.mBtnChanaWcCamMode.setTag(Integer.valueOf(BTN_CHANA_WC_MODE));
        this.mBtnChanaWcCamMode.setStateUpDn(R.drawable.can_camera_mode_up, R.drawable.can_camera_mode_dn);
        this.mBtnChanaWcCamMode.setOnClickListener(this);
        setViewPos(this.mBtnChanaWcCamMode, 100, 465, 150, 60);
        this.mListBotView.add(this.mBtnChanaWcCamMode);
    }

    private void InitVenuciaWcM50(Context context) {
        this.mVenuciaWc_M50BaseInfo = new CanDataInfo.VenuciaWc_M50vBaseInfo();
        this.mVenuciaWc_M50vCamera = new CanDataInfo.VenuciaWc_M50vCamera();
        this.mBtnVenuciaWcM50 = new ParamButton[4];
        for (int i = 0; i < this.mBtnVenuciaWcM50.length; i++) {
            this.mBtnVenuciaWcM50[i] = new ParamButton(context);
            this.mBtnVenuciaWcM50[i].setTag(Integer.valueOf(i + BTN_VENUCIA_WC_M50_MODE1));
            this.mBtnVenuciaWcM50[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnVenuciaWcM50[i]);
        }
        this.mBtnVenuciaWcM50[0].setDrawable(R.drawable.can_gs5_bot_10_up, R.drawable.can_gs5_bot_10_dn);
        setViewPos(this.mBtnVenuciaWcM50[0], 131, 508, 82, 72);
        this.mBtnVenuciaWcM50[1].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnVenuciaWcM50[1], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnVenuciaWcM50[2].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnVenuciaWcM50[2], 372, 508, 82, 72);
        this.mBtnVenuciaWcM50[3].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnVenuciaWcM50[3], 492, 508, 82, 72);
    }

    public void InitFordWc(Context context) {
        this.mFordWcCameraData = new CanDataInfo.FordWcCameraSet();
        this.mBtnFordWcMode = new ParamButton[2];
        this.mBtnFordWcMode[0] = new ParamButton(context);
        this.mBtnFordWcMode[0].setTag(Integer.valueOf(BTN_FORD_WC_GJ));
        this.mBtnFordWcMode[0].setStateDrawable(R.drawable.can_view_gj_up, R.drawable.can_view_gj_dn, R.drawable.can_view_gj_dn);
        this.mBtnFordWcMode[0].setOnClickListener(this);
        this.mBtnFordWcMode[1] = new ParamButton(context);
        this.mBtnFordWcMode[1].setTag(Integer.valueOf(BTN_FORD_WC_FJ));
        this.mBtnFordWcMode[1].setStateDrawable(R.drawable.can_view_fj_up, R.drawable.can_view_fj_dn, R.drawable.can_view_fj_dn);
        this.mBtnFordWcMode[1].setOnClickListener(this);
        setViewPos(this.mBtnFordWcMode[0], 47, 440, 110, 104);
        setViewPos(this.mBtnFordWcMode[1], 164, 440, 110, 104);
    }

    public void InitSenovaOd(Context context) {
        this.mSenovaOdCamera = new CanDataInfo.SenovaOd_CameraSta();
        this.mBtnSenovaOdMode = new ParamButton(context);
        this.mBtnSenovaOdMode.setTag(Integer.valueOf(BTN_SENOVA_OD_MODE));
        this.mBtnSenovaOdMode.setStateUpDn(R.drawable.can_camera_mode_up, R.drawable.can_camera_mode_dn);
        this.mBtnSenovaOdMode.setOnClickListener(this);
        setViewPos(this.mBtnSenovaOdMode, 100, 465, 150, 60);
        this.mListBotView.add(this.mBtnSenovaOdMode);
    }

    private void IniCheryWc(Context context) {
        this.mBtnCheryWcMode = new ParamButton[4];
        for (int i = 0; i < this.mBtnCheryWcMode.length; i++) {
            this.mBtnCheryWcMode[i] = new ParamButton(context);
            this.mBtnCheryWcMode[i].setTag(Integer.valueOf(i + BTN_CHERY_WC_MODE1));
            this.mBtnCheryWcMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnCheryWcMode[i]);
        }
        this.mBtnCheryWcMode[0].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnCheryWcMode[0], 131, 508, 82, 72);
        this.mBtnCheryWcMode[1].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnCheryWcMode[1], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnCheryWcMode[2].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnCheryWcMode[2], 372, 508, 82, 72);
        this.mBtnCheryWcMode[3].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnCheryWcMode[3], 492, 508, 82, 72);
    }

    private void InitCCHfDj(Context context) {
        this.mBtnCCHfDjMode = new ParamButton[3];
        for (int i = 0; i < this.mBtnCCHfDjMode.length; i++) {
            this.mBtnCCHfDjMode[i] = new ParamButton(context);
            this.mBtnCCHfDjMode[i].setTag(Integer.valueOf(i + BTN_CCHfDj_MODE1));
            this.mBtnCCHfDjMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnCCHfDjMode[i]);
        }
        this.mBtnCCHfDjMode[0].setDrawable(R.drawable.can_eps_park_up, R.drawable.can_eps_park_dn);
        setViewPos(this.mBtnCCHfDjMode[0], KeyDef.RKEY_DEL, 450, 115, 115);
        this.mBtnCCHfDjMode[1].setDrawable(R.drawable.can_eps_park_side_up, R.drawable.can_eps_park_side_dn);
        setViewPos(this.mBtnCCHfDjMode[1], 475, 450, 115, 115);
        this.mBtnCCHfDjMode[2].setDrawable(R.drawable.can_eps_park_right_up, R.drawable.can_eps_park_right_dn);
        setViewPos(this.mBtnCCHfDjMode[2], BTN_CCH9_MODE1, 450, 115, 115);
        if (CanJni.GetSubType() == 1) {
            this.mBtnCCHfDjDirection = new ParamButton[4];
            for (int i2 = 0; i2 < this.mBtnCCHfDjDirection.length; i2++) {
                this.mBtnCCHfDjDirection[i2] = new ParamButton(context);
                this.mBtnCCHfDjDirection[i2].setTag(Integer.valueOf(i2 + BTN_CCHfDj_MODE4));
                this.mBtnCCHfDjDirection[i2].setOnClickListener(this);
                this.mListBotView.add(this.mBtnCCHfDjDirection[i2]);
            }
            this.mBtnCCHfDjDirection[0].setDrawable(R.drawable.can_eps_park_fx01_up, R.drawable.can_eps_park_fx01_dn);
            setViewPos(this.mBtnCCHfDjDirection[0], 694, 170, 59, 59);
            this.mBtnCCHfDjDirection[1].setDrawable(R.drawable.can_eps_park_fx02_up, R.drawable.can_eps_park_fx02_dn);
            setViewPos(this.mBtnCCHfDjDirection[1], 694, 311, 59, 59);
            this.mBtnCCHfDjDirection[2].setDrawable(R.drawable.can_eps_park_fx03_up, R.drawable.can_eps_park_fx03_dn);
            setViewPos(this.mBtnCCHfDjDirection[2], 624, Can.CAN_VOLKS_XP, 59, 59);
            this.mBtnCCHfDjDirection[3].setDrawable(R.drawable.can_eps_park_fx04_up, R.drawable.can_eps_park_fx04_dn);
            setViewPos(this.mBtnCCHfDjDirection[3], 765, Can.CAN_VOLKS_XP, 59, 59);
        }
    }

    public void InitPsaRzc(Context context) {
        this.mBtnPsaRzcMode = new ParamButton[1];
        this.m_PsaRzcAvm = new CanDataInfo.PsaRzcAvm();
        this.mBtnPsaRzcEsc = new ParamButton(context);
        this.mBtnPsaRzcEsc.setTag(Integer.valueOf(BTN_PSA_RZC_ESC));
        this.mBtnPsaRzcEsc.setOnClickListener(this);
        this.mBtnPsaRzcEsc.setDrawable(R.drawable.can_camera_track_esc_up, R.drawable.can_camera_track_esc_dn);
        this.mListBotView.add(this.mBtnPsaRzcEsc);
        setViewPos(this.mBtnPsaRzcEsc, 909, 540, 115, 60);
        for (int i = 0; i < this.mBtnPsaRzcMode.length; i++) {
            this.mBtnPsaRzcMode[i] = new ParamButton(context);
            this.mBtnPsaRzcMode[i].setTag(Integer.valueOf(i + BTN_PSA_RZC_MODE));
            this.mBtnPsaRzcMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnPsaRzcMode[i]);
        }
        this.mBtnPsaRzcMode[0].setDrawable(R.drawable.can_camera_mode_up, R.drawable.can_camera_mode_dn);
        setViewPos(this.mBtnPsaRzcMode[0], 100, 465, Can.CAN_LEXUS_ZMYT, 79);
    }

    public void InitMGZSRvs(Context context) {
        this.mRvsData = new CanDataInfo.MgZsWc_Rvs();
        this.mTxtMGZSrvs = new CustomTextView(context);
        this.mTxtMGZSrvs.setTextSize(0, 33.0f);
        this.mTxtMGZSrvs.setGravity(1);
        this.mTxtMGZSrvs.setTextColor(-1);
        setViewPos(this.mTxtMGZSrvs, 850, 415, 100, 50);
    }

    public void InitMitSubishiRzc(Context context) {
        this.mBtnMitSubshiRzcMode = new ParamButton(context);
        this.mBtnMitSubshiRzcMode.setTag(Integer.valueOf(BTN_MITSUBISHI_RZC_CAMERA));
        this.mBtnMitSubshiRzcMode.setStateUpDn(R.drawable.can_camera_mode_up, R.drawable.can_camera_mode_dn);
        this.mBtnMitSubshiRzcMode.setOnClickListener(this);
        setViewPos(this.mBtnMitSubshiRzcMode, 100, 465, 150, 60);
        this.mListBotView.add(this.mBtnMitSubshiRzcMode);
    }

    public void InitRenaultXp(Context context) {
        this.mBtnRenaultXpCamMode = new ParamButton[4];
        this.mRensultXpAvmData = new CanDataInfo.RenaulXpAvm();
        for (int i = 0; i < 4; i++) {
            this.mBtnRenaultXpCamMode[i] = new ParamButton(context);
            this.mBtnRenaultXpCamMode[i].setTag(Integer.valueOf(i + BTN_RENAULT_XP_MODE1));
            this.mBtnRenaultXpCamMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnRenaultXpCamMode[i]);
        }
        this.mBtnRenaultXpCamMode[0].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnRenaultXpCamMode[0], 11, 508, 82, 72);
        this.mBtnRenaultXpCamMode[1].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnRenaultXpCamMode[1], 131, 508, 82, 72);
        this.mBtnRenaultXpCamMode[2].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnRenaultXpCamMode[2], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnRenaultXpCamMode[3].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnRenaultXpCamMode[3], 372, 508, 82, 72);
    }

    private void InitRenaultWc(Context context) {
        this.mRenaultWcAvmData = new CanDataInfo.RenaulWcAvm();
        this.mBtnRenaultWcMode = new ParamButton[4];
        for (int i = 0; i < this.mBtnRenaultWcMode.length; i++) {
            this.mBtnRenaultWcMode[i] = new ParamButton(context);
            this.mBtnRenaultWcMode[i].setTag(Integer.valueOf(i + BTN_RENAULT_WC_MODE1));
            this.mBtnRenaultWcMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnRenaultWcMode[i]);
        }
        this.mBtnRenaultWcMode[0].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPosBottom(this.mBtnRenaultWcMode[0], 150, 18, 82, 72);
        this.mBtnRenaultWcMode[1].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPosBottom(this.mBtnRenaultWcMode[1], Can.CAN_NISSAN_XFY, 18, 82, 72);
        this.mBtnRenaultWcMode[2].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPosBottom(this.mBtnRenaultWcMode[2], 350, 18, 82, 72);
        this.mBtnRenaultWcMode[3].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPosBottom(this.mBtnRenaultWcMode[3], 450, 18, 82, 72);
    }

    private void InitSenovaRzc(Context context) {
        this.mSenovaRzcAvmData = new CanDataInfo.SenovaRzc_AvmData();
        this.mBtnSenovaRzcMode = new ParamButton[6];
        for (int i = 0; i < this.mBtnSenovaRzcMode.length; i++) {
            this.mBtnSenovaRzcMode[i] = new ParamButton(context);
            this.mBtnSenovaRzcMode[i].setTag(Integer.valueOf(i + BTN_SENOVA_RZC_MODE1));
            this.mBtnSenovaRzcMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnSenovaRzcMode[i]);
        }
        this.mBtnSenovaRzcMode[0].setDrawable(R.drawable.can_rh7_3d_up, R.drawable.can_rh7_3d_dn);
        setViewPosBottom(this.mBtnSenovaRzcMode[0], BTN_LANDWIND_3D_LEFT_UP, 18, 90, 90);
        this.mBtnSenovaRzcMode[1].setBackgroundColor(0);
        this.mBtnSenovaRzcMode[2].setBackgroundColor(0);
        this.mBtnSenovaRzcMode[3].setBackgroundColor(0);
        this.mBtnSenovaRzcMode[4].setBackgroundColor(0);
        this.mBtnSenovaRzcMode[5].setBackgroundColor(0);
        setViewPos(this.mBtnSenovaRzcMode[1], 68, 29, KeyDef.RKEY_MEDIA_PROG, 137);
        setViewPos(this.mBtnSenovaRzcMode[2], 9, 190, 119, 222);
        setViewPos(this.mBtnSenovaRzcMode[3], KeyDef.RKEY_OPEN, 190, 119, 222);
        setViewPos(this.mBtnSenovaRzcMode[4], 68, 436, KeyDef.RKEY_MEDIA_PROG, 137);
        setViewPos(this.mBtnSenovaRzcMode[5], 554, 130, 376, 171);
    }

    public void InitMzdRzcAtez(Context context) {
        this.mBtnMzdRzcMode = new ParamButton(context);
        this.mBtnMzdRzcMode.setTag(Integer.valueOf(BTN_MZD_RZC_MODE));
        this.mBtnMzdRzcMode.setStateUpDn(R.drawable.can_camera_mode_up, R.drawable.can_camera_mode_dn);
        this.mBtnMzdRzcMode.setOnClickListener(this);
        setViewPos(this.mBtnMzdRzcMode, 100, 465, 150, 60);
    }

    private boolean isKoreaCameraUI() {
        MainSet.GetInstance();
        return MainSet.IsRxfKoren();
    }

    private void showLeftRightRadar(RadarUIView.Type type) {
        this.mLeftSideRadar.setVisibility(0);
        this.mRightSideRadar.setVisibility(0);
        this.mLeftSideRadar.setRadarType(type, RadarUIView.Kind.SIDE);
        this.mRightSideRadar.setRadarType(type, RadarUIView.Kind.SIDE);
        this.mLeftSideRadar.showSideRadar(false, true);
        this.mRightSideRadar.showSideRadar(false, false);
    }

    public void Init(Context context) {
        RadarUIView.Type type;
        if ((this.mLayout != null || context == null) && this.nLayoutReLoad == 0 && context != null) {
            if (this.mRotate == context.getResources().getConfiguration().orientation) {
                Log.d(TAG, "Already have instance");
                return;
            }
            Log.d(TAG, "rotate so init again");
        }
        this.nLayoutReLoad = 0;
        this.mRotate = context.getResources().getConfiguration().orientation;
        Log.d(TAG, "mRotate = " + this.mRotate);
        Log.d(TAG, "Init");
        mContext = context;
        this.mLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.activity_can_camera, (ViewGroup) null);
        this.mLayout.setTag(99);
        this.mLayout.setOnClickListener(this);
        this.mLayout.setOnTouchListener(this);
        if (FtSet.GetTconAdj() != 0 || !MainSet.GetInstance().Is3561()) {
            this.mBtnDisplaySet = (Button) findViewById(R.id.btn_display);
            this.mBtnDisplaySet.setVisibility(0);
            this.mBtnDisplaySet.setOnClickListener(this);
            this.mBtnDisplaySet.setTag(103);
        }
        this.mEpsLineView = (CameraUIView) findViewById(R.id.can_eps_line);
        if (CanJni.GetCanType() == 167) {
            this.mEpsLineView.setLineType(0);
            this.mEpsLineView.setHigherVer(true);
        } else {
            this.mEpsLineView.setLineType(FtSet.GetLineType());
        }
        if (isKoreaCameraUI()) {
            ((ViewStub) findViewById(R.id.can_scale_eps)).inflate();
            this.mScaleEpsLineView = (ScaleCameraUIView) findViewById(R.id.can_scale_eps_line);
            this.mScaleEpsLineView.setVisibility(0);
            this.mEpsLineView.setVisibility(8);
        } else {
            this.mEpsLineView.setVisibility(0);
        }
        this.mRightRadarLayout = (LinearLayout) findViewById(R.id.right_radar_layout);
        this.mRightForeRadar = (RadarUIView) findViewById(R.id.right_fore_radar);
        this.mRightRearRadar = (RadarUIView) findViewById(R.id.right_rear_radar);
        this.mLeftSideRadar = (RadarUIView) findViewById(R.id.left_side_radar);
        this.mRightSideRadar = (RadarUIView) findViewById(R.id.right_side_radar);
        int cantype = CanJni.GetCanType();
        switch (cantype) {
            case 2:
            case 122:
            case 198:
                type = RadarUIView.Type.COMMON;
                RadarUIView.Common.mBmpIds = new int[]{R.drawable.radar_shang_golf, R.drawable.radar_xia_golf, R.drawable.big_radar_shang_golf, R.drawable.big_radar_xia_golf};
                break;
            case 13:
            case 146:
                type = RadarUIView.Type.FORD;
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) this.mRightSideRadar.getLayoutParams();
                lp.rightMargin = 64;
                this.mRightSideRadar.setLayoutParams(lp);
                showLeftRightRadar(type);
                break;
            case 78:
                type = RadarUIView.Type.COMMON;
                if (CanJni.GetSubType() == 8) {
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                    layoutParams.addRule(9);
                    layoutParams.addRule(15);
                    layoutParams.leftMargin = 50;
                    this.mRightRadarLayout.setLayoutParams(layoutParams);
                    ImageView iv = (ImageView) this.mRightRadarLayout.findViewById(R.id.small_car);
                    iv.setLayoutParams(new LinearLayout.LayoutParams(103, KeyDef.RKEY_RADIO_2S));
                    iv.setImageResource(R.drawable.can_bradar_car_bg);
                    break;
                }
                break;
            case 110:
                type = RadarUIView.Type.COMMON;
                if (CanJni.GetSubType() != 2) {
                    RadarUIView.Common.mBmpIds = new int[]{R.drawable.radar_shang_golf, R.drawable.radar_xia_golf, R.drawable.big_radar_shang_golf, R.drawable.big_radar_xia_golf};
                    break;
                }
                break;
            default:
                type = RadarUIView.Type.COMMON;
                if (CanJni.IsHaveLrRadar()) {
                    showLeftRightRadar(type);
                    break;
                }
                break;
        }
        this.mRightForeRadar.setRadarType(type);
        this.mRightRearRadar.setRadarType(type);
        this.mRightForeRadar.showRadar(false, false);
        this.mRightRearRadar.showRadar(false, true);
        this.mBtnRtShowRadar = (Button) findViewById(R.id.can_eps_show_rtradar);
        this.mBtnShowLine = (Button) findViewById(R.id.can_eps_show_line);
        this.mBtnShowTrack = (Button) findViewById(R.id.can_eps_show_track);
        this.mBtnRtShowRadar.setOnClickListener(this.cameraClick);
        this.mBtnShowLine.setOnClickListener(this.cameraClick);
        this.mBtnShowTrack.setOnClickListener(this.cameraClick);
        this.mBtnShowTrack.setVisibility(4);
        this.mBtnShowLine.setVisibility(4);
        this.mVedioStaText = (TextView) this.mLayout.findViewById(R.id.avinvideoState);
        this.mVedioStaText.setText(R.string.video_state_none);
        this.mVedioStaText.setVisibility(8);
        this.mCameraText = (TextView) this.mLayout.findViewById(R.id.rear_camera_text);
        this.mCameraText.setText(R.string.can_rear_camera);
        if (MainSet.GetInstance().IsCustom("FYDZ")) {
            this.mCameraText.setVisibility(0);
            if (FtSet.IsIconExist(125) == 1) {
                this.mBtnFrCamera = new ParamButton(context);
                this.mBtnFrCamera.setTag(Integer.valueOf(BTN_FR_CAMERA));
                this.mBtnFrCamera.setOnTouchListener(this);
                this.mBtnFrCamera.setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
                setViewPos(this.mBtnFrCamera, 10, Can.CAN_CHRYSLER_ONE_HC, 82, 72);
            }
        } else {
            this.mCameraText.setVisibility(8);
        }
        int subType = CanJni.GetSubType();
        switch (cantype) {
            case 2:
                if (1 == subType || 2 == subType || 3 == subType || 4 == subType) {
                    InitGolfMagoten(context);
                    return;
                }
                return;
            case 3:
                if (CanJni.GetCanFsTp() == 7 || CanJni.GetCanFsTp() == 121) {
                    InitPraDo(context);
                    return;
                } else if (1 == subType) {
                    InitRav4(context);
                    return;
                } else if (2 == subType) {
                    InitCamery(context);
                    return;
                } else {
                    return;
                }
            case 5:
                if (CanJni.GetSubType() != 13) {
                    InitHondaDA(context);
                    return;
                }
                return;
            case 6:
                if (6 != subType && 5 != subType && 9 != subType) {
                    return;
                }
                if (MainSet.GetScreenType() == 3) {
                    InitXtralRvsAssist768x1024(context);
                    return;
                } else {
                    InitXtralRvsAssist(context);
                    return;
                }
            case 13:
                if (CanJni.GetCanFsTp() == 226) {
                    InitFord(context);
                } else if (6 == subType || 8 == subType) {
                    InitFord(context);
                }
                InitFordActivePark(context);
                return;
            case 14:
                InitHyundaiXp(context);
                return;
            case 17:
                switch (subType) {
                    case 2:
                        InitGS5Mode(context);
                        return;
                    case 3:
                        InitGS5Super(context);
                        return;
                    case 5:
                    case 9:
                        Init16GS4Mode(context);
                        return;
                    case 8:
                        InitTrumpchiGs7(context);
                        return;
                    default:
                        Init16GS4Mode(context);
                        return;
                }
            case 20:
                InitCrosstour(context);
                return;
            case 26:
                if (CanJni.GetSubType() == 8 || CanJni.GetSubType() == 9) {
                    InitMgGs(context);
                    return;
                }
                return;
            case 27:
                if (2 == CanJni.GetSubType() || 4 == CanJni.GetSubType()) {
                    InitJacRefine(context);
                    return;
                }
                return;
            case 47:
                if (1 == subType) {
                    InitVenuciaT70(context);
                    return;
                }
                return;
            case 49:
                if (subType == 4) {
                    InitSenovaSubBJ40(context);
                    return;
                } else if (subType == 5 || subType == 2) {
                    InitSenovaRzc(context);
                    return;
                } else {
                    return;
                }
            case 52:
                if (5 == CanJni.GetSubType()) {
                    InitFengShenAx7(context);
                    return;
                }
                return;
            case 57:
                if (1 == subType || 5 == subType || 6 == subType || 7 == subType || 9 == subType || 8 == subType) {
                    InitTigger7(context);
                    return;
                }
                return;
            case 60:
                if (4 == subType) {
                    InitZoyte(context);
                    return;
                }
                return;
            case 70:
                if (1 == CanJni.GetSubType()) {
                    InitDFFGRzc(context);
                    return;
                }
                return;
            case 71:
                InitB50_X80(context);
                return;
            case 72:
                if (1 == subType || 5 == subType || 18 == subType) {
                    InitGeelyYjX6(context);
                    return;
                }
                return;
            case 73:
                if (3 == subType) {
                    InitCs75Avm(context);
                    return;
                } else {
                    InitCs75(context);
                    return;
                }
            case 74:
                InitLiFan(context);
                return;
            case 78:
                if (8 == subType) {
                    InitCCH9(context);
                    return;
                } else {
                    InitCCH2(context);
                    return;
                }
            case 82:
                if (1 == subType || 2 == subType) {
                    InitHMS7(context);
                    return;
                }
                return;
            case 91:
                InitGeelyBoy(context);
                return;
            case 93:
                if (1 == subType) {
                    InitBlsuT5(context);
                    return;
                }
                return;
            case 96:
            case 100:
                InitYg9(context);
                return;
            case 97:
                if (3 == subType) {
                    InitBlsuT5Rzc(context);
                    return;
                }
                return;
            case 99:
                InitChanAAlsvinV7(context);
                return;
            case 102:
                if (9 == CanJni.GetSubType() || 11 == CanJni.GetSubType() || 12 == CanJni.GetSubType() || 14 == CanJni.GetSubType()) {
                    InitHyundaiRzc(context);
                    return;
                } else if (10 == CanJni.GetSubType()) {
                    this.mBtnMode_HyRzc = new ParamButton(context);
                    this.mBtnMode_HyRzc.setTag(Integer.valueOf(BTN_HYUNDAI_RZC_MODE_BTN));
                    this.mBtnMode_HyRzc.setOnClickListener(this);
                    this.mBtnMode_HyRzc.setDrawable(R.drawable.can_camera_mode_up, R.drawable.can_camera_mode_dn);
                    setViewPos(this.mBtnMode_HyRzc, 100, 465, Can.CAN_LEXUS_ZMYT, 79);
                    return;
                } else {
                    return;
                }
            case 110:
                if (CanJni.GetSubType() == 2 || CanJni.GetSubType() == 3) {
                    InitJacRefineWc(context);
                    return;
                }
                return;
            case 117:
                if (1 == CanJni.GetSubType()) {
                    InitRenaultXp(context);
                    return;
                }
                return;
            case 122:
                if (1 == subType || 2 == subType || 3 == subType) {
                    InitGolfMagoten(context);
                    return;
                }
                return;
            case 127:
                if (2 == CanJni.GetSubType()) {
                    InitPsaRzc(context);
                    return;
                }
                return;
            case 128:
                if (1 == subType) {
                    InitRav4(context);
                    return;
                }
                return;
            case 142:
                InitGolfWcMode(context);
                return;
            case 143:
                InitVwWc(context);
                return;
            case 144:
                if (2 == subType) {
                    InitToyotaWC(context);
                    return;
                }
                return;
            case 146:
                InitFord(context);
                InitFordActivePark(context);
                return;
            case 147:
                InitLandWind(context);
                return;
            case 149:
                if (4 != subType) {
                    return;
                }
                if (MainSet.GetScreenType() == 3) {
                    InitXtralRvsAssist768x1024(context);
                    return;
                } else {
                    InitXtralRvsAssist(context);
                    return;
                }
            case 153:
                if (17 == CanJni.GetSubType()) {
                    InitHyundaiWc(context);
                    return;
                }
                return;
            case Can.CAN_CC_WC:
                if (CanJni.GetSubType() == 6 || CanJni.GetSubType() == 6) {
                    InitCCWc(context);
                    return;
                }
                return;
            case Can.CAN_DFFG_S560:
                InitDFFGS560(context);
                return;
            case Can.CAN_HONDA_WC:
                if (4 != CanJni.GetSubType() && 5 != CanJni.GetSubType() && 10 != CanJni.GetSubType() && 11 != CanJni.GetSubType()) {
                    InitHondaWc(context);
                    return;
                }
                return;
            case Can.CAN_FORD_WC:
                InitFordWc(context);
                return;
            case 160:
                InitCs75Wc(context);
                return;
            case 161:
                InitTrumpchiWc(context);
                return;
            case 172:
                if (CanJni.GetSubType() == 5 || CanJni.GetSubType() == 6) {
                    InitCCH6Wc(context);
                    return;
                }
                return;
            case 174:
                InitCCH2WC(context);
                return;
            case 180:
                if (6 == CanJni.GetSubType() || 7 == CanJni.GetSubType()) {
                    IniCheryWc(context);
                    return;
                }
                return;
            case 190:
                InitX802017Wc(context);
                return;
            case 191:
                InitZoyteDMX5Wc(context);
                return;
            case 196:
                InitHondaAccord9Wc(context);
                return;
            case 197:
                InitBaicHSS6Wc(context);
                return;
            case 198:
                if (CanJni.GetSubType() == 1) {
                    InitPorcheOd(context);
                    return;
                }
                return;
            case 199:
                if (5 == subType || 8 == subType) {
                    InitNissanRzcRvsAssist(context);
                    return;
                }
                return;
            case 200:
                InitBydDJRadar(context);
                return;
            case 210:
            case 263:
                InitGMRzcActivePark(context);
                return;
            case 214:
                InitChanA_Wc(context);
                return;
            case 216:
                InitRenaultWc(context);
                return;
            case 218:
                InitVenuciaWcM50(context);
                return;
            case 221:
                InitPorcheLz(context);
                return;
            case 222:
                InitSenovaOd(context);
                return;
            case Can.CAN_CC_HF_DJ:
                InitCCHfDj(context);
                return;
            case Can.CAN_MG_ZS_WC:
                InitMGZSRvs(context);
                if (subType == 1) {
                    InitMgZsWc(context);
                    return;
                }
                return;
            case Can.CAN_TOYOTA_SP_XP:
                if (subType == 7) {
                    InitPraDo(context);
                    return;
                } else if (subType == 2) {
                    InitCamery(context);
                    return;
                } else {
                    return;
                }
            case 255:
                InitChanaCos1Wc(context);
                return;
            case 259:
                if (1 == CanJni.GetSubType()) {
                    InitBlsuOd(context);
                    return;
                }
                return;
            case 260:
                if (CanJni.GetSubType() == 5) {
                    InitMzdRzcAtez(context);
                    return;
                }
                return;
            case 265:
                InitMitSubishiRzc(context);
                return;
            case 269:
                InitChanaOD(context);
                return;
            case 275:
                InitLandWindOd(context);
                return;
            case 285:
                InitSubuarXp(context);
                return;
            case 288:
                if (CanJni.GetSubType() != 4) {
                    InitHondaDA(context);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void InitMgGs(Context context) {
        this.mMgGsAvmdata = new CanDataInfo.MG_GS_AVMDATA();
        this.mBtnMgGs = new ParamButton[11];
        for (int i = 0; i < this.mBtnMgGs.length; i++) {
            this.mBtnMgGs[i] = new ParamButton(context);
            this.mBtnMgGs[i].setTag(Integer.valueOf(i + BTN_MG_2D));
            this.mBtnMgGs[i].setOnClickListener(this);
            this.mBtnMgGs[i].Show(false);
            this.mListBotView.add(this.mBtnMgGs[i]);
        }
        this.mBtnMgGs[0].setStateDrawable(R.drawable.can_rh7_2d_up, R.drawable.can_rh7_2d_dn, R.drawable.can_rh7_2d_dn);
        setViewPos(this.mBtnMgGs[0], 150, 18, 90, 90);
        this.mBtnMgGs[1].setStateDrawable(R.drawable.can_rh7_3d_up, R.drawable.can_rh7_3d_dn, R.drawable.can_rh7_3d_dn);
        setViewPos(this.mBtnMgGs[1], Can.CAN_NISSAN_XFY, 18, 90, 90);
        this.mBtnMgGs[2].setStateDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn, R.drawable.can_gs5_bot_01_dn);
        setViewPosBottom(this.mBtnMgGs[2], 100, 18, 82, 72);
        this.mBtnMgGs[3].setStateDrawable(R.drawable.can_gs5_bot_12_up, R.drawable.can_gs5_bot_12_dn, R.drawable.can_gs5_bot_12_dn);
        setViewPosBottom(this.mBtnMgGs[3], 200, 18, 82, 72);
        this.mBtnMgGs[4].setStateDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn, R.drawable.can_gs5_bot_09_dn);
        setViewPosBottom(this.mBtnMgGs[4], 300, 18, 82, 72);
        this.mBtnMgGs[5].setStateDrawable(R.drawable.can_gs5_bot_14_up, R.drawable.can_gs5_bot_14_dn, R.drawable.can_gs5_bot_14_dn);
        setViewPosBottom(this.mBtnMgGs[5], BTN_TRUMPCHI_GS4_MODE1, 18, 82, 72);
        this.mBtnMgGs[6].setStateDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn, R.drawable.can_gs5_bot_02_dn);
        setViewPosBottom(this.mBtnMgGs[6], 500, 18, 82, 72);
        this.mBtnMgGs[7].setStateDrawable(R.drawable.can_gs5_bot_15_up, R.drawable.can_gs5_bot_15_dn, R.drawable.can_gs5_bot_15_dn);
        setViewPosBottom(this.mBtnMgGs[7], 600, 18, 82, 72);
        this.mBtnMgGs[8].setStateDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn, R.drawable.can_gs5_bot_08_dn);
        setViewPosBottom(this.mBtnMgGs[8], BTN_CC_WC_DIRECTION1, 18, 82, 72);
        this.mBtnMgGs[9].setStateDrawable(R.drawable.can_gs5_bot_13_up, R.drawable.can_gs5_bot_13_dn, R.drawable.can_gs5_bot_13_dn);
        setViewPosBottom(this.mBtnMgGs[9], 800, 18, 82, 72);
        this.mBtnMgGs[10].setStateDrawable(R.drawable.can_camera_track_esc_up, R.drawable.can_camera_track_esc_dn, R.drawable.can_camera_track_esc_dn);
        this.mBtnMgGs[10].setTag(Integer.valueOf(BTN_MG_ESC));
        setViewPosBottom(this.mBtnMgGs[10], 909, 0, 115, 60);
    }

    private void CheckMgGsMode(boolean check) {
        boolean z;
        boolean z2;
        boolean z3;
        if (this.mBtnMgGs != null) {
            CanJni.MGGSGetAvmData(this.mMgGsAvmdata);
            if (this.mMgGsAvmdata.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mMgGsAvmdata.Update != 0) {
                this.mMgGsAvmdata.Update = 0;
                if (int2Bool(this.mMgGsAvmdata.Func)) {
                    for (ParamButton btn : this.mBtnMgGs) {
                        btn.setSelected(false);
                    }
                    ParamButton paramButton = this.mBtnMgGs[0];
                    if (this.mMgGsAvmdata.Ms == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    paramButton.setSelected(z);
                    ParamButton paramButton2 = this.mBtnMgGs[1];
                    if (this.mMgGsAvmdata.Ms == 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    paramButton2.setSelected(z2);
                    if (this.mMgGsAvmdata.Ms == 1) {
                        this.mBtnMgGs[2].Show(true);
                        this.mBtnMgGs[3].Show(true);
                        this.mBtnMgGs[4].Show(true);
                        this.mBtnMgGs[5].Show(true);
                        this.mBtnMgGs[6].Show(true);
                        this.mBtnMgGs[7].Show(true);
                        this.mBtnMgGs[8].Show(true);
                        this.mBtnMgGs[9].Show(true);
                    } else if (this.mMgGsAvmdata.Ms == 0) {
                        this.mBtnMgGs[2].Show(true);
                        this.mBtnMgGs[3].Show(false);
                        this.mBtnMgGs[4].Show(true);
                        this.mBtnMgGs[5].Show(false);
                        this.mBtnMgGs[6].Show(true);
                        this.mBtnMgGs[7].Show(false);
                        this.mBtnMgGs[8].Show(true);
                        this.mBtnMgGs[9].Show(false);
                    }
                    int cameraSta = this.mMgGsAvmdata.CameraSta;
                    for (int i = 2; i < this.mBtnMgGs.length - 1; i++) {
                        ParamButton paramButton3 = this.mBtnMgGs[i];
                        if ((cameraSta - i) + 2 == 0) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        paramButton3.setSelected(z3);
                    }
                }
            }
        }
    }

    private void InitSubuarXp(Context context) {
        this.mIvSubuarAids = new CustomImgView[4];
        this.mSubuarAids = new CanDataInfo.SubuarXp_DrivingAids();
        for (int i = 0; i < this.mIvSubuarAids.length; i++) {
            this.mIvSubuarAids[i] = new CustomImgView(context);
            this.mListBotView.add(this.mIvSubuarAids[i]);
        }
        this.mIvSubuarAids[0].setBackgroundResource(R.drawable.can_zhuyi_up);
        this.mIvSubuarAids[0].Show(false);
        setViewPos(this.mIvSubuarAids[0], 34, 181, 104, 104);
        this.mIvSubuarAids[1].setBackgroundResource(R.drawable.can_zhuyi02_up);
        this.mIvSubuarAids[1].Show(false);
        setViewPos(this.mIvSubuarAids[1], 32, 286, 109, 48);
        this.mIvSubuarAids[2].setBackgroundResource(R.drawable.can_zhuyi_up);
        this.mIvSubuarAids[2].Show(false);
        setViewPos(this.mIvSubuarAids[2], 886, 181, 104, 104);
        this.mIvSubuarAids[3].setBackgroundResource(R.drawable.can_zhuyi03_up);
        this.mIvSubuarAids[3].Show(false);
        setViewPos(this.mIvSubuarAids[3], 914, 286, 78, 48);
    }

    private void InitFengShenAx7(Context context) {
        this.mBtnFengShenAx7Mode = new ParamButton[6];
        this.mFengShenAvm = new CanDataInfo.FengshenAvm();
        for (int i = 0; i < this.mBtnFengShenAx7Mode.length; i++) {
            this.mBtnFengShenAx7Mode[i] = new ParamButton(context);
            this.mBtnFengShenAx7Mode[i].setTag(Integer.valueOf(i + BTN_FENGSHEN_AX7_MODE_UP));
            this.mBtnFengShenAx7Mode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnFengShenAx7Mode[i]);
        }
        this.mBtnFengShenAx7Mode[0].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnFengShenAx7Mode[0], 11, 508, 82, 72);
        this.mBtnFengShenAx7Mode[1].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnFengShenAx7Mode[1], 131, 508, 82, 72);
        this.mBtnFengShenAx7Mode[2].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnFengShenAx7Mode[2], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnFengShenAx7Mode[3].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnFengShenAx7Mode[3], 372, 508, 82, 72);
        this.mBtnFengShenAx7Mode[4].setDrawable(R.drawable.can_gs5_bot_10_up, R.drawable.can_gs5_bot_10_dn);
        setViewPos(this.mBtnFengShenAx7Mode[4], 492, 508, 82, 72);
        this.mBtnFengShenAx7Mode[5].setDrawable(R.drawable.can_camera_track_esc_up, R.drawable.can_camera_track_esc_dn);
        setViewPos(this.mBtnFengShenAx7Mode[5], 909, 540, 115, 60);
    }

    private void InitMgZsWc(Context context) {
        this.mBtnMgZsWcMode = new ParamButton[5];
        this.mMgZsWcAvm = new CanDataInfo.MgZsWc_Avm();
        for (int i = 0; i < this.mBtnMgZsWcMode.length; i++) {
            this.mBtnMgZsWcMode[i] = new ParamButton(context);
            this.mBtnMgZsWcMode[i].setTag(Integer.valueOf(i + BTN_MGZS_WC_MODE1));
            this.mBtnMgZsWcMode[i].setOnClickListener(this);
            this.mListBotView.add(this.mBtnMgZsWcMode[i]);
        }
        this.mBtnMgZsWcMode[0].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnMgZsWcMode[0], 11, 508, 82, 72);
        this.mBtnMgZsWcMode[1].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnMgZsWcMode[1], 131, 508, 82, 72);
        this.mBtnMgZsWcMode[2].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnMgZsWcMode[2], Can.CAN_TOYOTA_SP_XP, 508, 82, 72);
        this.mBtnMgZsWcMode[3].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnMgZsWcMode[3], 372, 508, 82, 72);
        this.mBtnMgZsWcMode[4].setDrawable(R.drawable.can_gs5_bot_10_up, R.drawable.can_gs5_bot_10_dn);
        setViewPos(this.mBtnMgZsWcMode[4], 492, 508, 82, 72);
    }

    private void CheckSubuarXp(boolean check) {
        if (this.mSubuarAids != null) {
            CanJni.SubuarXpGetDrivingAids(this.mSubuarAids);
            if (this.mSubuarAids.UpdateOnce == 0) {
                return;
            }
            if (this.mSubuarAids.Update != 0 || !check) {
                this.mSubuarAids.Update = 0;
                this.mIvSubuarAids[0].Show(this.mSubuarAids.Zmqxtzt);
                this.mIvSubuarAids[1].Show(this.mSubuarAids.Zmqxtzt);
                this.mIvSubuarAids[2].Show(this.mSubuarAids.Ymqxtzt);
                this.mIvSubuarAids[3].Show(this.mSubuarAids.Ymqxtzt);
            }
        }
    }

    private void CheckFengShenAx7Mode(boolean check) {
        boolean z;
        boolean z2 = true;
        if (this.mFengShenAvm != null) {
            CanJni.DfFengShenRzcGetAvmSet(this.mFengShenAvm);
            Log.d("HAHA", "Sta = " + this.mFengShenAvm.Sta);
            if (this.mFengShenAvm.UpdateOnce == 0) {
                return;
            }
            if (this.mFengShenAvm.Update != 0 || !check) {
                this.mFengShenAvm.Update = 0;
                Log.d("HAHA", "Sta2 = " + this.mFengShenAvm.Sta);
                int sta = this.mFengShenAvm.Sta;
                for (int i = 0; i < 6; i++) {
                    this.mBtnFengShenAx7Mode[i].setSelected(false);
                }
                for (int i2 = 0; i2 < 5; i2++) {
                    ParamButton paramButton = this.mBtnFengShenAx7Mode[i2];
                    if (i2 == sta - 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    paramButton.setSelected(z);
                }
                ParamButton paramButton2 = this.mBtnFengShenAx7Mode[4];
                if (sta < 5 || sta > 8) {
                    z2 = false;
                }
                paramButton2.setSelected(z2);
            }
        }
    }

    private void CheckMgZsWcMode(boolean check) {
        boolean z;
        if (this.mMgZsWcAvm != null) {
            CanJni.MgZsWcGetAvmData(this.mMgZsWcAvm);
            if (this.mMgZsWcAvm.UpdateOnce == 0) {
                return;
            }
            if (this.mMgZsWcAvm.Update != 0 || !check) {
                this.mMgZsWcAvm.Update = 0;
                int sta = this.mMgZsWcAvm.Mode;
                for (ParamButton selected : this.mBtnMgZsWcMode) {
                    selected.setSelected(false);
                }
                if (sta >= 5 && sta <= 9) {
                    for (int i = 0; i < this.mBtnMgZsWcMode.length; i++) {
                        ParamButton paramButton = this.mBtnMgZsWcMode[i];
                        if (i == sta - 5) {
                            z = true;
                        } else {
                            z = false;
                        }
                        paramButton.setSelected(z);
                    }
                }
            }
        }
    }

    private void InitLandWindOd(Context context) {
        this.mBtnLandWindOd = new ParamButton[13];
        for (int i = 0; i < this.mBtnLandWindOd.length; i++) {
            this.mBtnLandWindOd[i] = new ParamButton(context);
            this.mBtnLandWindOd[i].setOnClickListener(this);
            this.mBtnLandWindOd[i].setTag(Integer.valueOf(i + BTN_LANDWINDOD_2D3D));
            this.mBtnLandWindOd[i].Show(false);
            this.mListBotView.add(this.mBtnLandWindOd[i]);
        }
        this.mBtnLandWindOd[0].setDrawable(R.drawable.can_rh7_2d_up, R.drawable.can_rh7_2d_dn);
        setViewPos(this.mBtnLandWindOd[0], 11, 110, 90, 90);
        this.mBtnLandWindOd[1].setDrawable(R.drawable.can_gs5_bot_10_up, R.drawable.can_gs5_bot_10_dn);
        setViewPos(this.mBtnLandWindOd[1], 11, 508, 82, 72);
        this.mBtnLandWindOd[2].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnLandWindOd[2], 131, 508, 82, 72);
        this.mBtnLandWindOd[3].setDrawable(R.drawable.can_gs5_bot_09_up, R.drawable.can_gs5_bot_09_dn);
        setViewPos(this.mBtnLandWindOd[3], Can.CAN_MG_ZS_WC, 508, 82, 72);
        this.mBtnLandWindOd[4].setDrawable(R.drawable.can_gs5_bot_08_up, R.drawable.can_gs5_bot_08_dn);
        setViewPos(this.mBtnLandWindOd[4], 371, 508, 82, 72);
        this.mBtnLandWindOd[5].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnLandWindOd[5], 491, 508, 82, 72);
        this.mBtnLandWindOd[6].setDrawable(R.drawable.can_gs5_bot_10_up, R.drawable.can_gs5_bot_10_dn);
        setViewPos(this.mBtnLandWindOd[6], 11, 508, 82, 72);
        this.mBtnLandWindOd[7].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
        setViewPos(this.mBtnLandWindOd[7], 131, 508, 82, 72);
        this.mBtnLandWindOd[8].setDrawable(R.drawable.can_gs5_bot_12_up, R.drawable.can_gs5_bot_12_dn);
        setViewPos(this.mBtnLandWindOd[8], Can.CAN_MG_ZS_WC, 508, 82, 72);
        this.mBtnLandWindOd[9].setDrawable(R.drawable.can_gs5_bot_13_up, R.drawable.can_gs5_bot_13_dn);
        setViewPos(this.mBtnLandWindOd[9], 371, 508, 82, 72);
        this.mBtnLandWindOd[10].setDrawable(R.drawable.can_gs5_bot_14_up, R.drawable.can_gs5_bot_14_dn);
        setViewPos(this.mBtnLandWindOd[10], 491, 508, 82, 72);
        this.mBtnLandWindOd[11].setDrawable(R.drawable.can_gs5_bot_15_up, R.drawable.can_gs5_bot_15_dn);
        setViewPos(this.mBtnLandWindOd[11], BTN_CCH9_MODE2, 508, 82, 72);
        this.mBtnLandWindOd[12].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        setViewPos(this.mBtnLandWindOd[12], 731, 508, 82, 72);
        CanJni.LandWindOdQuery(65);
    }

    private void CheckLandWindOdMode(boolean check) {
        boolean z = false;
        if (this.mBtnLandWindOd != null && this.mLandWindOdCarSet != null) {
            CanJni.LandWindOdGetCarSet(this.mLandWindOdCarSet);
            if (this.mLandWindOdCarSet.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mLandWindOdCarSet.Update != 0) {
                this.mLandWindOdCarSet.Update = 0;
                for (ParamButton btn : this.mBtnLandWindOd) {
                    btn.setSelected(false);
                }
                int ms = this.mLandWindOdCarSet.Avm;
                this.mBtnLandWindOd[0].Show(true);
                if (ms == 0) {
                    this.mBtnLandWindOd[0].setStateUpDn(R.drawable.can_rh7_3d_up, R.drawable.can_rh7_3d_dn);
                } else {
                    this.mBtnLandWindOd[0].setStateUpDn(R.drawable.can_rh7_2d_up, R.drawable.can_rh7_2d_dn);
                }
                if (ms != 0) {
                    z = true;
                }
                showLandwindOd23D(z);
                switch (this.mLandWindOdCarSet.AvmMode) {
                    case 0:
                        this.mBtnLandWindOd[1].setSelected(true);
                        this.mBtnLandWindOd[6].setSelected(true);
                        return;
                    case 1:
                        this.mBtnLandWindOd[2].setSelected(true);
                        this.mBtnLandWindOd[7].setSelected(true);
                        return;
                    case 2:
                        this.mBtnLandWindOd[3].setSelected(true);
                        this.mBtnLandWindOd[8].setSelected(true);
                        return;
                    case 3:
                        this.mBtnLandWindOd[4].setSelected(true);
                        this.mBtnLandWindOd[9].setSelected(true);
                        return;
                    case 4:
                        this.mBtnLandWindOd[5].setSelected(true);
                        this.mBtnLandWindOd[10].setSelected(true);
                        return;
                    case 5:
                        this.mBtnLandWindOd[11].setSelected(true);
                        return;
                    case 6:
                        this.mBtnLandWindOd[12].setSelected(true);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private void showLandwindOd23D(boolean is2D) {
        if (this.mBtnLandWindOd != null) {
            for (int i = 1; i < this.mBtnLandWindOd.length; i++) {
                if (i < 6) {
                    this.mBtnLandWindOd[i].Show(is2D);
                } else {
                    this.mBtnLandWindOd[i].Show(!is2D);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setViewPos(View view, int x, int y) {
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(-2, -2);
        rl.leftMargin = x;
        rl.topMargin = y;
        view.setLayoutParams(rl);
        this.mLayout.addView(view);
    }

    /* access modifiers changed from: protected */
    public void setViewPos(View view, int x, int y, int w, int h) {
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(w, h);
        rl.leftMargin = x;
        rl.topMargin = y;
        view.setLayoutParams(rl);
        this.mLayout.addView(view);
    }

    /* access modifiers changed from: protected */
    public void setViewPosBottom(View view, int x, int y, int w, int h) {
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(w, h);
        rl.leftMargin = x;
        rl.bottomMargin = y;
        rl.addRule(12, this.mLayout.getId());
        view.setLayoutParams(rl);
        this.mLayout.addView(view);
    }

    /* access modifiers changed from: protected */
    public void CheckBotBtn(boolean check) {
        if (this.mListBotView != null && this.mListBotView.size() >= 1) {
            if (!check) {
                this.mTickNow = CanFunc.getTickCount();
                this.mUpdateCount = 0;
            } else if (this.mUpdateCount < 50) {
                this.mTickNow = CanFunc.getTickCount();
                this.mUpdateCount++;
                for (int i = 0; i < this.mListBotView.size(); i++) {
                    this.mListBotView.get(i).invalidate();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CheckFordActivePark(check);
        CheckGMRzcActivePark(check);
        CheckGS5Btn(check);
        CheckBotBtn(check);
        CheckMagotenBtn(check);
        CheckLiFanBtn(check);
        CheckVenuciaT70Btn(check);
        CheckHmS7Mode(check);
        CheckYg9Mode(check);
        CheckXtralRvsAssist(check);
        CheckBlsuT5Mode(check);
        CheckVwWcBtn(check);
        CheckGolfWcMode(check);
        CheckLandWindMode(check);
        CheckHondaWcMode(check);
        CheckCs75WcMode(check);
        CheckTrumpchiWcMode(check);
        CheckJacRefineMode(check);
        CheckCCH2WCBtn(check);
        CheckZotyeBtn(check);
        CheckX80WcBtn(check);
        CheckHyundaiXpBtn(check);
        CheckBYDDJBtn(check);
        CheckVenuciaWcM50(check);
        CheckFordWcMode(check);
        CheckDFFGRzcBtn(check);
        CheckMGZSRvsPark(check);
        CheckCCH6WcBtn(check);
        CheckGEELYjXBtn(check);
        CheckBlsuOdBtn(check);
        CheckHyundaiWCBtn(check);
        CheckHyundaiRzcBtn(check);
        CheckFrCamera(check);
        CheckRenaultXpCameraMode(check);
        CheckChanaCos1WcBtn(check);
        CheckPorscheLZBtn(check);
        CheckRenaultWcBtn(check);
        CheckSenovaRzcMode(check);
        CheckLandWindOdMode(check);
        CheckFengShenAx7Mode(check);
        CheckSubuarXp(check);
        CheckMgZsWcMode(check);
        CheckMgGsMode(check);
        CheckNissanRzcRvsAssist(check);
        CheckPorcheOdAvm(check);
        CheckJacRefineWcMode(check);
    }

    /* access modifiers changed from: protected */
    public Boolean IsTuobuAvm() {
        if (CanJni.GetCanType() == 88 && FtSet.Getlgb1() == 2 && CanFunc.getInstance().IsCore() == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.mbResume = true;
        this.mFrCamera = 0;
        this.mfgShowCanBtn = false;
        ShowCanBtn(this.mfgShowCanBtn);
        this.mfgFullScr = CanFunc.i2b(FtSet.GetRadarDis());
        this.mfgShowTrack = CanFunc.i2b(FtSet.GetCamTrack());
        this.mfgShowLine = CanFunc.i2b(FtSet.GetCamLine());
        if (this.mfgShowTrack) {
            this.mfgShowLine = false;
        }
        initCamera();
        updateEps();
        ResetData(false);
        if (CanFunc.IsLincsMkc()) {
            CanJni.GmSbCarMoudleCtl(3, 1);
        }
        this.nSignalSta = 255;
        this.mAvmIconTick = 10;
        CanFunc.mbRadarUIUpdate = true;
        QueryData();
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        switch (CanJni.GetCanType()) {
            case 26:
                if (CanJni.GetSubType() == 8 || CanJni.GetSubType() == 9) {
                    CanJni.MGGSQuery(80);
                    return;
                }
                return;
            case 49:
                if (CanJni.GetSubType() == 5) {
                    CanJni.SenovaQuery(38, 0);
                    return;
                }
                return;
            case 57:
                if (CanJni.GetSubType() == 7) {
                    CanJni.Tigger7CarVedioSet(17);
                    return;
                }
                return;
            case Can.CAN_FORD_WC:
                CanJni.FordWcQuery(5, 1, Can.CAN_FLAT_WC);
                return;
            case 198:
                if (CanJni.GetSubType() == 1) {
                    CanJni.PorscheOdQuery(65, 0);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.mbResume = false;
        ScreenSet.GetInstance().Hide();
        if (this.mVedioStaText != null) {
            this.mVedioStaText.setVisibility(8);
        }
        if (CanFunc.IsLincsMkc()) {
            CanJni.GmSbCarMoudleCtl(3, 0);
        }
        if (MainUI.IsCameraMode() == 1 && IsTuobuAvm().booleanValue()) {
            Log.i(TAG, "TuobuAvmEsc");
            Mcu.SendXKey(80);
        }
        if (CanJni.GetSubType() == 8 && CanJni.GetCanFsTp() == 78) {
            this.isAutoParking = false;
            CanJni.CCH6CarSetRzc(20, 1, 0);
        }
        PauseData();
    }

    /* access modifiers changed from: protected */
    public void PauseData() {
        switch (CanJni.GetCanType()) {
            case 57:
                if (CanJni.GetSubType() == 7) {
                    CanJni.Tigger7CarVedioSet(16);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void initCamera() {
        boolean isHaveEps = CanJni.IsHaveEps();
        boolean isHaveRadar = CanJni.IsHaveRadar();
        if (FtSet.Getyw14() == 2) {
            isHaveRadar = false;
        }
        if (isHaveEps) {
            this.mBtnShowTrack.setVisibility(4);
        } else {
            this.mBtnShowTrack.setVisibility(4);
            this.mfgShowTrack = false;
        }
        if (MainSet.GetScreenType() == 5 && MainSet.GetInstance().IsCustom("SZKS")) {
            isHaveRadar = true;
        }
        if (CanJni.GetCanFsTp() == 140 && CanBencWithCDCarFuncActivity.RvsMode() == 2) {
            isHaveRadar = false;
        }
        if (isHaveRadar) {
            this.mBtnRtShowRadar.setVisibility(0);
        } else {
            this.mBtnRtShowRadar.setVisibility(4);
            this.mfgFullScr = true;
        }
        if (CanFunc.IsCadillacXt51280x480()) {
            this.mBtnRtShowRadar.setVisibility(4);
            this.mfgFullScr = false;
        }
        if (IsTuobuAvm().booleanValue()) {
            this.mBtnRtShowRadar.setVisibility(4);
            this.mfgFullScr = true;
        }
        if (this.mRotate == 1) {
            this.mfgFullScr = false;
        }
    }

    /* access modifiers changed from: protected */
    public void updateEps() {
        int i = 0;
        if (!this.mfgFullScr) {
            this.mRightRadarLayout.setVisibility(0);
        } else {
            this.mRightRadarLayout.setVisibility(4);
        }
        if (isKoreaCameraUI()) {
            if (this.mfgShowTrack) {
                this.mScaleEpsLineView.setVisibility(8);
            } else {
                ScaleCameraUIView scaleCameraUIView = this.mScaleEpsLineView;
                if (!this.mfgShowLine) {
                    i = 4;
                }
                scaleCameraUIView.setVisibility(i);
                this.mEpsLineView.setVisibility(8);
                return;
            }
        }
        Log.d("HAHA", "mfgShowLine = " + this.mfgShowLine);
        Log.d("HAHA", "mfgShowTrack = " + this.mfgShowTrack);
        if (this.mfgShowLine) {
            this.mEpsLineView.setVisibility(0);
            this.mEpsLineView.setCanTurnable(false);
            this.mEpsLineView.showCenterLine();
        } else if (this.mfgShowTrack) {
            this.mEpsLineView.setVisibility(0);
            this.mEpsLineView.setCanTurnable(true);
        } else {
            this.mEpsLineView.setVisibility(4);
            this.mEpsLineView.setCanTurnable(false);
            return;
        }
        if (CanJni.GetCanType() != 167) {
            int eps = Can.mEpsInfo.Eps;
            if (int2Bool(Can.mEpsInfo.UpdateOnce)) {
                System.out.println("eps = " + eps);
                if (eps >= -46 && eps <= 46) {
                    this.mEpsLineView.showCenterLine();
                } else if (eps > 46) {
                    if (eps > 1080) {
                        eps = 1080;
                    }
                    this.mEpsLineView.turnLeft(eps / 46);
                } else {
                    int eps2 = -eps;
                    if (eps2 > 1080) {
                        eps2 = 1080;
                    }
                    this.mEpsLineView.turnRight(eps2 / 46);
                }
            }
        }
    }

    public static boolean showEps(Context context) {
        Can.updateRadar();
        if (!Can.updateEps() && !int2Bool(Can.mRadarForeInfo.Update) && !int2Bool(Can.mRadarRearInfo.Update)) {
            return false;
        }
        Can.mRadarForeInfo.Update = 0;
        Can.mRadarRearInfo.Update = 0;
        Intent intent = new Intent();
        intent.setClass(context, CanCameraActivity.class);
        context.startActivity(intent);
        return true;
    }

    public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
        return false;
    }

    private void CheckPort() {
        int curMode = MainUI.IsCameraMode() | CanIF.mfgAvm;
        if (this.mOldCamPort != curMode) {
            this.mOldCamPort = curMode;
            if (curMode == 0) {
                this.mLayout.setVisibility(4);
            } else {
                this.mLayout.setVisibility(0);
            }
        }
    }

    public void EnterCamera(int sta) {
        boolean z;
        this.nDelayCheck = 0;
        if (sta != this.mEnterCamSta) {
            this.mEnterCamSta = sta;
            if (sta == 0) {
                if (CanFunc.IsCadillacXt51280x480() || (88 == CanJni.GetCanType() && CanJni.GetSubType() == 10 && CanIF.RvsEntExdMode())) {
                    TsDisplay.GetInstance().SetDispParat(-1);
                    if (this.mOldXt5UI) {
                        Log.d(TAG, "Xt5 Camera finish ");
                        this.mOldXt5UI = false;
                        CanCadillacXt5ExdActivity.finishCadillacXt5Win();
                    }
                } else if (CanFunc.IsLincsMkc() && CanIF.RvsEntExdMode()) {
                    TsDisplay.GetInstance().SetDispParat(-1);
                    CanJni.GmSbCarMoudleCtl(3, 0);
                    if (this.mOldXt5UI) {
                        Log.d(TAG, "Mkc Camera finish ");
                        this.mOldXt5UI = false;
                        CanLincsMkcExdActivity.finishLincsMkcWin();
                    }
                } else if (CanFunc.IsCadillacAts() && CanIF.RvsEntExdMode()) {
                    TsDisplay.GetInstance().SetDispParat(-1);
                    if (this.mOldXt5UI) {
                        Log.d(TAG, "Ats Camera finish ");
                        this.mOldXt5UI = false;
                        CanCadillacWithCDExdActivity.finishCadillacAtsWin();
                    }
                } else if (136 == CanJni.GetCanType() && CanIF.RvsEntExdMode()) {
                    TsDisplay.GetInstance().SetDispParat(-1);
                    if (this.mOldXt5UI) {
                        Log.d(TAG, "Ats Camera finish ");
                        this.mOldXt5UI = false;
                        CanCadillacAtsXhdExdActivity.finishCadillacAtsWin();
                    }
                } else if (88 == CanJni.GetCanType() && ((CanJni.GetSubType() == 6 || CanJni.GetSubType() == 7 || CanJni.GetSubType() == 8 || CanJni.GetSubType() == 9 || CanJni.GetSubType() == 11) && CanIF.RvsEntExdMode())) {
                    TsDisplay.GetInstance().SetDispParat(-1);
                    if (this.mOldXt5UI) {
                        Log.d(TAG, "Ats Camera finish ");
                        this.mOldXt5UI = false;
                        CanCadillacAtsExdActivity.finishCadillacAtsWin();
                    }
                } else if (140 == CanJni.GetCanType() && CanIF.RvsEntExdMode()) {
                    TsDisplay.GetInstance().SetDispParat(-1);
                    if (this.mOldXt5UI) {
                        Log.d(TAG, "CAN_BENC_ZMYT Camera finish ");
                        this.mOldXt5UI = false;
                        CanBencWithCDExdActivity.finishBencWithCDWin();
                    }
                } else if (152 == CanJni.GetCanType() && CanIF.RvsEntExdMode()) {
                    TsDisplay.GetInstance().SetDispParat(-1);
                    if (this.mOldXt5UI) {
                        Log.d(TAG, "CAN_AUDI_ZMYT Camera finish ");
                        this.mOldXt5UI = false;
                        CanAudiWithCDExdActivity.finishAudiWithCDWin();
                    }
                } else if (176 == CanJni.GetCanType() && CanIF.RvsEntExdMode()) {
                    TsDisplay.GetInstance().SetDispParat(-1);
                    if (this.mOldXt5UI) {
                        Log.d(TAG, "CAN_BMW_ZMYT Camera finish ");
                        this.mOldXt5UI = false;
                        CanBmwZmytWithCDExdActivity.finishBmwZmytWithCDWin();
                    }
                } else if (207 == CanJni.GetCanType() && CanIF.RvsEntExdMode()) {
                    TsDisplay.GetInstance().SetDispParat(-1);
                    if (this.mOldXt5UI) {
                        Log.d(TAG, "CAN_LEXUS_ZMYT Camera finish ");
                        this.mOldXt5UI = false;
                        CanLexusZMYTCarDevView.finishLexusZMYTWin();
                    }
                } else if (229 == CanJni.GetCanType() && CanIF.RvsEntExdMode()) {
                    TsDisplay.GetInstance().SetDispParat(-1);
                    if (this.mOldXt5UI) {
                        Log.d(TAG, "CAN_VOLVO_XFY Camera finish ");
                        this.mOldXt5UI = false;
                        CanVolvoXfyCarDeviceActivity.finishVolvoXfyWin();
                    }
                } else if (276 == CanJni.GetCanType() && CanIF.RvsEntExdMode()) {
                    TsDisplay.GetInstance().SetDispParat(-1);
                    if (this.mOldXt5UI) {
                        Log.d(TAG, "CAN_LEXUSH_ZMYT Camera finish ");
                        this.mOldXt5UI = false;
                        CanLexushZmytCarDevView.finishWin();
                    }
                } else if (289 == CanJni.GetCanType() && CanIF.RvsEntExdMode()) {
                    TsDisplay.GetInstance().SetDispParat(-1);
                    if (this.mOldXt5UI) {
                        Log.d(TAG, "CAN_LANDROVER_ZMYT Camera finish ");
                        this.mOldXt5UI = false;
                        CanLandRoverZmytCarDevView.finishWin();
                    }
                } else if (298 == CanJni.GetCanType() && CanIF.RvsEntExdMode()) {
                    TsDisplay.GetInstance().SetDispParat(-1);
                    if (this.mOldXt5UI) {
                        Log.d(TAG, "CAN_AUDI_WITHCD_LZ Camera finish ");
                        this.mOldXt5UI = false;
                        CanAudiLzWithCDCarDevView.finishWin();
                    }
                } else if (303 == CanJni.GetCanType() && CanIF.RvsEntExdMode()) {
                    TsDisplay.GetInstance().SetDispParat(-1);
                    if (this.mOldXt5UI) {
                        Log.d(TAG, "CAN_AUDI_WITHCD_XBS Camera finish ");
                        this.mOldXt5UI = false;
                        CanAudiXbsWithCDCarDevView.finishWin();
                    }
                } else if (138 == CanJni.GetCanType() && 1 == CanJni.GetSubType() && CanIF.RvsEntExdMode()) {
                    TsDisplay.GetInstance().SetDispParat(-1);
                    if (this.mOldXt5UI) {
                        Log.d(TAG, "CAN_BMW_WITHCD Camera finish ");
                        this.mOldXt5UI = false;
                        CanBmwWithCdCarCvbsDevView.finishWin();
                    }
                } else {
                    this.mOldCamPort = -1;
                    onPause();
                    BackcarService.getInstance().StopCamera();
                    Log.d(TAG, "BackcarService.getInstance().StopCamera() ");
                    MainVolume.GetInstance().Destroy();
                }
            } else if (CanFunc.IsCadillacXt51280x480() || (88 == CanJni.GetCanType() && CanJni.GetSubType() == 10 && CanIF.RvsEntExdMode())) {
                TsDisplay.GetInstance().SetDispParat(0);
                if (!CanCadillacXt5ExdActivity.IsCadillacXt5Win()) {
                    this.mOldXt5UI = true;
                    Log.d(TAG, "Xt5 Camera ent ");
                    CanCadillacXt5ExdActivity.showCadillacXt5Win();
                }
            } else if (CanFunc.IsLincsMkc() && CanIF.RvsEntExdMode()) {
                TsDisplay.GetInstance().SetDispParat(0);
                CanJni.GmSbCarMoudleCtl(3, 1);
                if (!CanLincsMkcExdActivity.IsLincsMkcWin()) {
                    this.mOldXt5UI = true;
                    Log.d(TAG, "Mkc Camera ent ");
                    CanLincsMkcExdActivity.showLincsMkc5Win();
                }
            } else if (CanFunc.IsCadillacAts() && CanIF.RvsEntExdMode()) {
                TsDisplay.GetInstance().SetDispParat(0);
                if (!CanCadillacWithCDExdActivity.IsCadillacAtsWin()) {
                    this.mOldXt5UI = true;
                    Log.d(TAG, "Ats Camera ent ");
                    CanCadillacWithCDExdActivity.showCadillacAtsWin();
                }
            } else if (136 == CanJni.GetCanType() && CanIF.RvsEntExdMode()) {
                TsDisplay.GetInstance().SetDispParat(0);
                if (!CanCadillacAtsXhdExdActivity.IsCadillacAtsWin()) {
                    this.mOldXt5UI = true;
                    Log.d(TAG, "Ats Camera ent ");
                    CanCadillacAtsXhdExdActivity.showCadillacAtsWin();
                }
            } else if (88 == CanJni.GetCanType() && ((CanJni.GetSubType() == 6 || CanJni.GetSubType() == 7 || CanJni.GetSubType() == 8 || CanJni.GetSubType() == 9 || CanJni.GetSubType() == 11) && CanIF.RvsEntExdMode())) {
                TsDisplay.GetInstance().SetDispParat(0);
                if (!CanCadillacAtsExdActivity.IsCadillacAtsWin()) {
                    this.mOldXt5UI = true;
                    Log.d(TAG, "Ats Camera ent ");
                    CanCadillacAtsExdActivity.showCadillacAtsWin();
                }
            } else if (140 == CanJni.GetCanType() && CanIF.RvsEntExdMode()) {
                TsDisplay.GetInstance().SetDispParat(0);
                if (!CanBencWithCDExdActivity.IsBencWithCDWin()) {
                    this.mOldXt5UI = true;
                    Log.d(TAG, "CAN_BENC_ZMYT Camera ent ");
                    CanBencWithCDExdActivity.showBencWithCDWin();
                }
            } else if (152 == CanJni.GetCanType() && CanIF.RvsEntExdMode()) {
                TsDisplay.GetInstance().SetDispParat(0);
                if (!CanAudiWithCDExdActivity.IsAudiWithCDWin()) {
                    this.mOldXt5UI = true;
                    Log.d(TAG, "CAN_BENC_ZMYT Camera ent ");
                    CanAudiWithCDExdActivity.showAudiWithCDWin();
                }
            } else if (176 == CanJni.GetCanType() && CanIF.RvsEntExdMode()) {
                TsDisplay.GetInstance().SetDispParat(0);
                if (!CanBmwZmytWithCDExdActivity.IsBmwZmytWithCDWin()) {
                    this.mOldXt5UI = true;
                    Log.d(TAG, "CAN_BMW_ZMYT Camera ent ");
                    CanBmwZmytWithCDExdActivity.showBmwZmytWithCDWin();
                }
            } else if (207 == CanJni.GetCanType() && CanIF.RvsEntExdMode()) {
                TsDisplay.GetInstance().SetDispParat(0);
                if (!CanLexusZMYTCarDevView.IsLexusZMYTWin()) {
                    this.mOldXt5UI = true;
                    Log.d(TAG, "CAN_LEXUS_ZMYT Camera ent ");
                    CanLexusZMYTCarDevView.showLexusZMYTWin();
                }
            } else if (229 == CanJni.GetCanType() && CanIF.RvsEntExdMode()) {
                TsDisplay.GetInstance().SetDispParat(0);
                if (!CanVolvoXfyCarDeviceActivity.IsVolvoXfyWin()) {
                    this.mOldXt5UI = true;
                    Log.d(TAG, "CAN_VOLVO_XFY Camera ent ");
                    CanVolvoXfyCarDeviceActivity.showVolvoXfyWin();
                }
            } else if (276 == CanJni.GetCanType() && CanIF.RvsEntExdMode()) {
                TsDisplay.GetInstance().SetDispParat(0);
                if (!CanLexushZmytCarDevView.IsWin()) {
                    this.mOldXt5UI = true;
                    Log.d(TAG, "CAN_LEXUSH_ZMYT Camera ent ");
                    CanLexushZmytCarDevView.showWin();
                }
            } else if (289 == CanJni.GetCanType() && CanIF.RvsEntExdMode()) {
                TsDisplay.GetInstance().SetDispParat(0);
                if (!CanLandRoverZmytCarDevView.IsWin()) {
                    this.mOldXt5UI = true;
                    Log.d(TAG, "CAN_LANDROVER_ZMYT Camera ent ");
                    CanLexushZmytCarDevView.showWin();
                }
            } else if (298 == CanJni.GetCanType() && CanIF.RvsEntExdMode()) {
                TsDisplay.GetInstance().SetDispParat(0);
                if (!CanAudiLzWithCDCarDevView.IsWin()) {
                    this.mOldXt5UI = true;
                    Log.d(TAG, "CAN_AUDI_WITHCD_LZ Camera ent ");
                    CanAudiLzWithCDCarDevView.showWin();
                }
            } else if (303 == CanJni.GetCanType() && CanIF.RvsEntExdMode()) {
                TsDisplay.GetInstance().SetDispParat(0);
                if (!CanAudiXbsWithCDCarDevView.IsWin()) {
                    this.mOldXt5UI = true;
                    Log.d(TAG, "CAN_AUDI_WITHCD_XBS Camera ent ");
                    CanAudiXbsWithCDCarDevView.showWin();
                }
            } else if (138 == CanJni.GetCanType() && 1 == CanJni.GetSubType() && CanIF.RvsEntExdMode()) {
                TsDisplay.GetInstance().SetDispParat(0);
                if (!CanBmwWithCdCarCvbsDevView.IsWin()) {
                    this.mOldXt5UI = true;
                    Log.d(TAG, "CAN_BMW_WITHCD Camera ent ");
                    CanBmwWithCdCarCvbsDevView.showWin();
                }
            } else if (65 != CanJni.GetCanType() || MainSet.GetInstance().IsCustom("MINI")) {
                Init(CanFunc.mContext);
                this.mCameraView = (AutoFitTextureView) findViewById(R.id.CameratextureView);
                if ((Mcu.GetPowState() & 4) > 0) {
                    z = false;
                } else {
                    z = true;
                }
                Boolean nDI = Boolean.valueOf(z);
                BackcarService.getInstance().StartCamera(this.mCameraView, nDI.booleanValue());
                Log.d(TAG, "DI STA =  " + nDI);
                CheckPort();
                onResume();
                updateCameraSize();
                MainVolume.GetInstance().InintScreen(getLayout());
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateCameraSize() {
        if (MainSet.GetScreenType() == 5 && !MainSet.GetInstance().IsXT5()) {
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-1, -1);
            RelativeLayout.LayoutParams radarParams = (RelativeLayout.LayoutParams) this.mRightRadarLayout.getLayoutParams();
            if (this.mfgFullScr) {
                lp.width = -1;
                lp.height = -1;
                this.mEpsLineView.setTranslationX(10.0f);
                this.mEpsLineView.setScaleX(1.0f);
                radarParams.rightMargin = 30;
            } else {
                lp.width = 1000;
                lp.height = 480;
                this.mEpsLineView.setTranslationX(-140.0f);
                this.mEpsLineView.setScaleX(0.8f);
                radarParams.rightMargin = 30;
            }
            this.mCameraView.setLayoutParams(lp);
            this.mRightRadarLayout.setLayoutParams(radarParams);
        } else if (MainSet.GetScreenType() == 9) {
            RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(-1, -1);
            RelativeLayout.LayoutParams radarParams2 = (RelativeLayout.LayoutParams) this.mRightRadarLayout.getLayoutParams();
            if (this.mfgFullScr) {
                lp2.width = -1;
                lp2.height = -1;
                this.mEpsLineView.setTranslationX(10.0f);
            } else {
                lp2.width = BTN_PORSCHE_LZ_CAR;
                lp2.height = 720;
                this.mEpsLineView.setTranslationX(-200.0f);
                radarParams2.rightMargin = 100;
            }
            this.mCameraView.setLayoutParams(lp2);
            this.mRightRadarLayout.setLayoutParams(radarParams2);
            this.mRightRadarLayout.setScaleX(1.5f);
            this.mRightRadarLayout.setScaleY(1.5f);
            RelativeLayout.LayoutParams lp3 = (RelativeLayout.LayoutParams) this.mBtnRtShowRadar.getLayoutParams();
            lp3.width = 100;
            lp3.height = 100;
            this.mBtnRtShowRadar.setLayoutParams(lp3);
            this.mEpsLineView.setScaleX(1.2f);
            this.mEpsLineView.setScaleY(1.2f);
        }
        if (CanFunc.getInstance().VsUI() == 1 && this.mRotate == 2) {
            this.mEpsLineView.setTranslationY(150.0f);
        }
    }

    private void CheckPorscheLZBtn(boolean check) {
        boolean z;
        if (this.mPorscheLZData != null && this.mBtnPorscheLZMode != null) {
            CanJni.PorscheLzGetCarAvmData(this.mPorscheLZData);
            if (this.mPorscheLZData.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mPorscheLZData.Update != 0) {
                this.mPorscheLZData.Update = 0;
                for (int i = 0; i < this.mBtnPorscheLZMode.length; i++) {
                    ParamButton paramButton = this.mBtnPorscheLZMode[i];
                    if (this.mPorscheLZData.Mode - 1 == i) {
                        z = true;
                    } else {
                        z = false;
                    }
                    paramButton.setSelected(z);
                }
            }
        }
    }

    private void CheckRenaultWcBtn(boolean check) {
        boolean z;
        if (this.mRenaultWcAvmData != null && this.mBtnRenaultWcMode != null) {
            CanJni.RenaultWcGetAvmData(this.mRenaultWcAvmData);
            if (this.mRenaultWcAvmData.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mRenaultWcAvmData.Update != 0) {
                this.mRenaultWcAvmData.Update = 0;
                for (int i = 0; i < this.mBtnRenaultWcMode.length; i++) {
                    ParamButton paramButton = this.mBtnRenaultWcMode[i];
                    if (this.mRenaultWcAvmData.Mode - 5 == i) {
                        z = true;
                    } else {
                        z = false;
                    }
                    paramButton.setSelected(z);
                }
            }
        }
    }

    private void CheckChanaCos1WcBtn(boolean check) {
        boolean z;
        if (this.mChanaCos1WcCameraData != null && this.mBtnChanaCos1WcMode != null) {
            CanJni.ChanAWcCos1GetCameraSta(this.mChanaCos1WcCameraData);
            if (this.mChanaCos1WcCameraData.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mChanaCos1WcCameraData.Update != 0) {
                this.mChanaCos1WcCameraData.Update = 0;
                for (int i = 0; i < this.mBtnChanaCos1WcMode.length; i++) {
                    ParamButton paramButton = this.mBtnChanaCos1WcMode[i];
                    if (this.mChanaCos1WcCameraData.Sta == i) {
                        z = true;
                    } else {
                        z = false;
                    }
                    paramButton.setSelected(z);
                }
            }
        }
    }

    private void CheckHyundaiRzcBtn(boolean check) {
        boolean z;
        boolean z2;
        if (this.mHyundaiRzcData != null && this.mBtnHyundaiRzcMode != null) {
            CanJni.HyundaiRzcGetAvmData(this.mHyundaiRzcData);
            if (this.mHyundaiRzcData.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mHyundaiRzcData.Update != 0) {
                this.mHyundaiRzcData.Update = 0;
                for (ParamButton btn : this.mBtnHyundaiRzcMode) {
                    btn.setVisibility(8);
                }
                if (this.mHyundaiRzcData.FrView == 1) {
                    for (int i = 0; i < 5; i++) {
                        this.mBtnHyundaiRzcMode[i].setVisibility(0);
                        ParamButton paramButton = this.mBtnHyundaiRzcMode[i];
                        if (this.mHyundaiRzcData.AvmView - 1 == i) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        paramButton.setSelected(z2);
                    }
                } else if (this.mHyundaiRzcData.FrView == 2) {
                    for (int i2 = 5; i2 < 10; i2++) {
                        this.mBtnHyundaiRzcMode[i2].setVisibility(0);
                        ParamButton paramButton2 = this.mBtnHyundaiRzcMode[i2];
                        if (this.mHyundaiRzcData.AvmView - 1 == i2) {
                            z = true;
                        } else {
                            z = false;
                        }
                        paramButton2.setSelected(z);
                    }
                }
            }
        }
    }

    private void CheckHyundaiWCBtn(boolean check) {
        boolean z;
        boolean z2;
        if (this.mHyundaiWcAvmData != null && this.mBtnHyundaiWcMode != null) {
            CanJni.HyundaiWcGetAvmData(this.mHyundaiWcAvmData);
            if (this.mHyundaiWcAvmData.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mHyundaiWcAvmData.Update != 0) {
                this.mHyundaiWcAvmData.Update = 0;
                for (ParamButton btn : this.mBtnHyundaiWcMode) {
                    btn.setVisibility(8);
                }
                if (this.mHyundaiWcAvmData.Rev == 0) {
                    for (int i = 0; i < 5; i++) {
                        this.mBtnHyundaiWcMode[i].setVisibility(0);
                        ParamButton paramButton = this.mBtnHyundaiWcMode[i];
                        if (this.mHyundaiWcAvmData.Sta - 1 == i) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        paramButton.setSelected(z2);
                    }
                    return;
                }
                for (int i2 = 5; i2 < 10; i2++) {
                    this.mBtnHyundaiWcMode[i2].setVisibility(0);
                    ParamButton paramButton2 = this.mBtnHyundaiWcMode[i2];
                    if (this.mHyundaiWcAvmData.Sta - 1 == i2) {
                        z = true;
                    } else {
                        z = false;
                    }
                    paramButton2.setSelected(z);
                }
            }
        }
    }

    private void CheckBlsuOdBtn(boolean check) {
        if (this.mBlsuOdAvmData != null && this.mBtnBlsuOdStats != null) {
            CanJni.BlsuOdGetCameraSta(this.mBlsuOdAvmData);
            if (this.mBlsuOdAvmData.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mBlsuOdAvmData.Update != 0) {
                this.mBlsuOdAvmData.Update = 0;
                for (ParamButton btn : this.mBtnBlsuOdStats) {
                    btn.setSelected(false);
                }
                if (this.mBlsuOdAvmData.Sta == 1) {
                    this.mBtnBlsuOdStats[0].setSelected(true);
                } else if (this.mBlsuOdAvmData.Sta == 0) {
                    this.mBtnBlsuOdStats[1].setSelected(true);
                }
            }
        }
    }

    private void CheckVenuciaWcM50(boolean check) {
        boolean z;
        if (this.mBtnVenuciaWcM50 != null) {
            CanJni.VenucaiWcM50vGetCameraInfo(this.mVenuciaWc_M50vCamera);
            if (this.mVenuciaWc_M50vCamera.UpdateOnce != 0 && (!check || this.mVenuciaWc_M50vCamera.Update != 0)) {
                this.mVenuciaWc_M50vCamera.Update = 0;
                int mode = this.mVenuciaWc_M50vCamera.Mode;
                for (int i = 0; i < this.mBtnVenuciaWcM50.length; i++) {
                    ParamButton paramButton = this.mBtnVenuciaWcM50[i];
                    if (i == mode - 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    paramButton.setSelected(z);
                }
            }
            CanJni.VenucaiWcM50vGetBaseInfo(this.mVenuciaWc_M50BaseInfo);
            if (this.mVenuciaWc_M50BaseInfo.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mVenuciaWc_M50BaseInfo.Update != 0) {
                this.mVenuciaWc_M50BaseInfo.Update = 0;
                if (this.mVenuciaWc_M50BaseInfo.Rev == 1) {
                    this.mBtnVenuciaWcM50[3].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
                } else {
                    this.mBtnVenuciaWcM50[3].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
                }
            }
        }
    }

    private void CheckGS5Btn(boolean check) {
        boolean z;
        if (this.mBtnGS5Cam360 != null) {
            CanJni.GqcqGetCamMode(this.mGS5CamData);
            if (this.mGS5CamData.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mGS5CamData.Update != 0) {
                this.mGS5CamData.Update = 0;
                for (int i = 0; i < 5; i++) {
                    ParamButton paramButton = this.mBtnGS5Cam360[i];
                    if (i == this.mGS5CamData.Mode - 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    paramButton.setSelected(z);
                }
            }
        }
    }

    private void CheckMagotenBtn(boolean check) {
        boolean z;
        if (this.mBtnMagotenMode != null) {
            CanJni.MagotenGetRvsCameraMode(this.mCameraMode);
            if (this.mCameraMode.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mCameraMode.Update != 0) {
                this.mCameraMode.Update = 0;
                for (int i = 0; i < this.mBtnMagotenMode.length; i++) {
                    ParamButton paramButton = this.mBtnMagotenMode[i];
                    if (i == this.mCameraMode.RvsMode) {
                        z = true;
                    } else {
                        z = false;
                    }
                    paramButton.setSelected(z);
                }
            }
        }
    }

    private void CheckLiFanBtn(boolean check) {
        boolean z;
        if (this.mBtnLiFanCamMode != null) {
            CanJni.LifanGetCamMode(this.mLiFanCamMode);
            if (this.mLiFanCamMode.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mLiFanCamMode.Update != 0) {
                this.mLiFanCamMode.Update = 0;
                for (int i = 0; i < this.mBtnLiFanCamMode.length; i++) {
                    ParamButton paramButton = this.mBtnLiFanCamMode[i];
                    if (i == this.mLiFanCamMode.Mode) {
                        z = true;
                    } else {
                        z = false;
                    }
                    paramButton.setSelected(z);
                }
            }
        }
    }

    private void CheckHyundaiXpBtn(boolean check) {
        if (!(this.mBtnHyundaiXpFrontMode == null || this.mBtnHyundaiXpRearMode == null)) {
            CanJni.HyundaiXpGetCameraInfo(this.mHyundaiCamera);
            if (this.mHyundaiCamera.UpdateOnce != 0 && (!check || this.mHyundaiCamera.Update != 0)) {
                this.mHyundaiCamera.Update = 0;
                if (this.mHyundaiCamera.AvmSta == 0) {
                    for (ParamButton visibility : this.mBtnHyundaiXpFrontMode) {
                        visibility.setVisibility(8);
                    }
                    for (ParamButton visibility2 : this.mBtnHyundaiXpRearMode) {
                        visibility2.setVisibility(8);
                    }
                } else if (this.mHyundaiCamera.AvmSta == 1) {
                    for (ParamButton visibility3 : this.mBtnHyundaiXpFrontMode) {
                        visibility3.setVisibility(8);
                    }
                    for (int i = 0; i < this.mBtnHyundaiXpRearMode.length; i++) {
                        this.mBtnHyundaiXpRearMode[i].setVisibility(0);
                        this.mBtnHyundaiXpRearMode[i].setSelected(false);
                    }
                    switch (this.mHyundaiCamera.AvmMode) {
                        case 1:
                            this.mBtnHyundaiXpRearMode[0].setSelected(true);
                            break;
                        case 2:
                            this.mBtnHyundaiXpRearMode[1].setSelected(true);
                            break;
                        case 3:
                            this.mBtnHyundaiXpRearMode[2].setSelected(true);
                            break;
                        case 4:
                            this.mBtnHyundaiXpRearMode[3].setSelected(true);
                            break;
                    }
                } else if (this.mHyundaiCamera.AvmSta == 2) {
                    for (int i2 = 0; i2 < this.mBtnHyundaiXpFrontMode.length; i2++) {
                        this.mBtnHyundaiXpFrontMode[i2].setVisibility(0);
                        this.mBtnHyundaiXpFrontMode[i2].setSelected(false);
                    }
                    switch (this.mHyundaiCamera.AvmMode) {
                        case 5:
                            this.mBtnHyundaiXpFrontMode[0].setSelected(true);
                            break;
                        case 6:
                            this.mBtnHyundaiXpFrontMode[1].setSelected(true);
                            break;
                        case 7:
                            this.mBtnHyundaiXpFrontMode[2].setSelected(true);
                            break;
                        case 8:
                            this.mBtnHyundaiXpFrontMode[3].setSelected(true);
                            break;
                    }
                    for (ParamButton visibility4 : this.mBtnHyundaiXpRearMode) {
                        visibility4.setVisibility(8);
                    }
                }
            }
        }
        if (this.mTvHyundaiAssist != null) {
            CanJni.HyundaiXpGetAssistInfo(this.mHyundaiAssist);
            if (this.mHyundaiAssist.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mHyundaiAssist.Update != 0) {
                this.mHyundaiAssist.Update = 0;
                if (this.mHyundaiAssist.Zmqxtzt > 0) {
                    this.mTvHyundaiAssist[0].Show(true);
                    this.mTvHyundaiAssist[1].Show(true);
                } else {
                    this.mTvHyundaiAssist[0].Show(false);
                    this.mTvHyundaiAssist[1].Show(false);
                }
                if (this.mHyundaiAssist.Rmqxtzt > 0) {
                    this.mTvHyundaiAssist[2].Show(true);
                    this.mTvHyundaiAssist[3].Show(true);
                    return;
                }
                this.mTvHyundaiAssist[2].Show(false);
                this.mTvHyundaiAssist[3].Show(false);
            }
        }
    }

    private void CheckCCH2WCBtn(boolean check) {
        boolean z;
        if (this.mBtnCCH2WCMode != null) {
            CanJni.CcH2WcGetCameraSta(this.mCcH2WcCamera);
            if (this.mCcH2WcCamera.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mCcH2WcCamera.Update != 0) {
                this.mCcH2WcCamera.Update = 0;
                for (int i = 0; i < this.mBtnCCH2WCMode.length; i++) {
                    ParamButton paramButton = this.mBtnCCH2WCMode[i];
                    if (i == this.mCcH2WcCamera.Sta - 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    paramButton.setSelected(z);
                }
            }
        }
    }

    private void CheckZotyeBtn(boolean check) {
        boolean z;
        if (this.mBtnZotyeMode != null) {
            CanJni.ZotyeSr9GetCameraInfo(this.mZtCamera);
            if (this.mZtCamera.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mZtCamera.Update != 0) {
                this.mZtCamera.Update = 0;
                for (int i = 0; i < this.mBtnZotyeMode.length; i++) {
                    ParamButton paramButton = this.mBtnZotyeMode[i];
                    if (i == this.mZtCamera.Sta - 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    paramButton.setSelected(z);
                }
            }
        }
    }

    private void CheckBYDDJBtn(boolean check) {
        if (this.mBtnBYDDJRadar != null) {
            CanJni.BydDjGetRadarSet(this.mBydDjRadar);
            if (this.mBydDjRadar.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mBydDjRadar.Update != 0) {
                this.mBydDjRadar.Update = 0;
                this.mBtnBYDDJRadar.SetSel(this.mBydDjRadar.Sw);
            }
        }
    }

    private void CheckX80WcBtn(boolean check) {
        if (this.mBtnX80WcMode != null) {
            CanJni.X80WcGetCameraSet(this.mX80WcCamera);
            if (this.mX80WcCamera.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mX80WcCamera.Update != 0) {
                this.mX80WcCamera.Update = 0;
                this.mBtnX80WcMode[0].SetSel(this.mX80WcCamera.FrontSta);
                this.mBtnX80WcMode[1].SetSel(this.mX80WcCamera.RearSta);
                this.mBtnX80WcMode[2].SetSel(this.mX80WcCamera.LeftSta);
                this.mBtnX80WcMode[3].SetSel(this.mX80WcCamera.RightSta);
            }
        }
    }

    private void CheckX80Btn(boolean check) {
        if (this.mBtnX80CamMode != null) {
            CanJni.X80GetCameraMode(this.mX80Camera);
            if (this.mX80Camera.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mX80Camera.Update != 0) {
                this.mX80Camera.Update = 0;
                for (ParamButton selected : this.mBtnX80CamMode) {
                    selected.setSelected(false);
                }
                switch (this.mX80Camera.Mode) {
                    case 2:
                        this.mBtnX80CamMode[0].setSelected(true);
                        return;
                    case 3:
                        this.mBtnX80CamMode[3].setSelected(true);
                        return;
                    case 4:
                        this.mBtnX80CamMode[2].setSelected(true);
                        return;
                    case 5:
                        this.mBtnX80CamMode[1].setSelected(true);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private void CheckVenuciaT70Btn(boolean check) {
        if (this.mBtnVenuciaMode != null) {
            CanJni.VenuciaCarGetCamState(this.mVenuciaCamState);
            if (this.mVenuciaCamState.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mVenuciaCamState.Update != 0) {
                this.mVenuciaCamState.Update = 0;
                for (ParamButton mode : this.mBtnVenuciaMode) {
                    mode.setSelected(false);
                }
                int state = this.mVenuciaCamState.CamState;
                switch (state) {
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        this.mBtnVenuciaMode[3].setDrawable(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
                        this.mBtnVenuciaMode[state - 3].setSelected(true);
                        return;
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        this.mBtnVenuciaMode[3].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
                        this.mBtnVenuciaMode[state - 7].setSelected(true);
                        return;
                    default:
                        this.mBtnVenuciaMode[3].setDrawable(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
                        return;
                }
            }
        }
    }

    private void CheckHmS7Mode(boolean check) {
        boolean z;
        if (this.mBtnHmS7Mode != null && this.mHmS7Set != null) {
            CanJni.HmS7GetCarSet(this.mHmS7Set);
            if (this.mHmS7Set.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mHmS7Set.Update != 0) {
                this.mHmS7Set.Update = 0;
                this.mBtnHmS7Line.SetSel(this.mHmS7Set.Fzxzt);
                for (int i = 0; i < this.mBtnHmS7Mode.length; i++) {
                    ParamButton paramButton = this.mBtnHmS7Mode[i];
                    if (i == this.mHmS7Set.CamSta - 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    paramButton.setSelected(z);
                }
            }
        }
    }

    private void CheckYg9Mode(boolean check) {
        boolean z;
        if (this.mBtnYg9Mode != null && this.mYg9Set != null) {
            CanJni.Yg9XbsGetCamMode(this.mYg9Set);
            if (this.mYg9Set.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mYg9Set.Update != 0) {
                this.mYg9Set.Update = 0;
                for (int i = 0; i < this.mBtnYg9Mode.length; i++) {
                    ParamButton paramButton = this.mBtnYg9Mode[i];
                    if (i == this.mYg9Set.Mode - 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    paramButton.setSelected(z);
                }
            }
        }
    }

    private void CheckXtralRvsAssist(boolean check) {
        int i;
        if (this.mBtnXtralRvs != null && this.mXtralView != null && this.mXtralRvsAss != null) {
            if (CanJni.GetCanFsTp() == 149) {
                CanJni.NissanWcGetAvmUI(this.mXtralWcRvsAss);
                if (this.mXtralWcRvsAss.UpdateOnce == 0) {
                    return;
                }
                if (!check || this.mXtralWcRvsAss.Update != 0) {
                    this.mXtralWcRvsAss.Update = 0;
                    for (ParamButton Show : this.mBtnXtralRvs) {
                        Show.Show(false);
                    }
                    this.mXtralView[0].Show(false);
                    this.mXtralView[1].Show(true);
                    for (CustomTextView ShowGone : this.mXtralDeclare) {
                        ShowGone.ShowGone(false);
                    }
                    if (this.mXtralWcRvsAss.AutoParkSta == 1) {
                        this.mBtnXtralRvs[8].Show(true);
                    } else if (this.mXtralWcRvsAss.AutoParkSta == 2) {
                        this.mBtnXtralRvs[7].Show(true);
                        this.mBtnXtralRvs[6].Show(true);
                        this.mBtnXtralRvs[10].Show(true);
                        this.mBtnXtralRvs[11].Show(true);
                    } else if (this.mXtralWcRvsAss.AutoParkSta == 3) {
                        this.mBtnXtralRvs[9].Show(true);
                        this.mBtnXtralRvs[10].Show(true);
                    } else if (this.mXtralWcRvsAss.AutoParkSta == 4) {
                        for (int i2 = 0; i2 < 6; i2++) {
                            this.mBtnXtralRvs[i2].Show(true);
                        }
                        this.mBtnXtralRvs[10].Show(true);
                        this.mBtnXtralRvs[11].Show(true);
                        this.mBtnXtralRvs[12].Show(true);
                        this.mXtralView[0].Show(true);
                    } else if (this.mXtralWcRvsAss.AutoParkSta == 5) {
                        this.mBtnXtralRvs[10].Show(true);
                    } else if (this.mXtralWcRvsAss.AutoParkSta == 6) {
                        for (CustomTextView ShowGone2 : this.mXtralDeclare) {
                            ShowGone2.ShowGone(true);
                        }
                    }
                    CustomImgView customImgView = this.mXtralView[1];
                    if (this.mXtralWcRvsAss.Camera == 2) {
                        i = 0;
                    } else {
                        i = 1;
                    }
                    customImgView.SetSel(i);
                    if (this.mXtralWcRvsAss.DirValid[7] > 0) {
                        this.mBtnXtralRvs[1].setDrawable(R.drawable.can_oj_up_gray, R.drawable.can_oj_up_gray);
                    } else {
                        this.mBtnXtralRvs[1].setDrawable(R.drawable.can_oj_up_up, R.drawable.can_oj_up_dn);
                    }
                    if (this.mXtralWcRvsAss.DirValid[6] > 0) {
                        this.mBtnXtralRvs[2].setDrawable(R.drawable.can_oj_down_gray, R.drawable.can_oj_down_gray);
                    } else {
                        this.mBtnXtralRvs[2].setDrawable(R.drawable.can_oj_down_up, R.drawable.can_oj_down_dn);
                    }
                    if (this.mXtralWcRvsAss.DirValid[5] > 0) {
                        this.mBtnXtralRvs[3].setDrawable(R.drawable.can_oj_left_gray, R.drawable.can_oj_left_gray);
                    } else {
                        this.mBtnXtralRvs[3].setDrawable(R.drawable.can_oj_left_up, R.drawable.can_oj_left_dn);
                    }
                    if (this.mXtralWcRvsAss.DirValid[4] > 0) {
                        this.mBtnXtralRvs[4].setDrawable(R.drawable.can_oj_right_gray, R.drawable.can_oj_right_gray);
                    } else {
                        this.mBtnXtralRvs[4].setDrawable(R.drawable.can_oj_right_up, R.drawable.can_oj_right_dn);
                    }
                    if (this.mXtralWcRvsAss.DirValid[3] > 0) {
                        this.mBtnXtralRvs[5].setDrawable(R.drawable.can_oj_right_big_gray, R.drawable.can_oj_right_big_gray);
                    } else {
                        this.mBtnXtralRvs[5].setDrawable(R.drawable.can_oj_right_big_up, R.drawable.can_oj_right_big_dn);
                    }
                    if (this.mXtralWcRvsAss.DirValid[2] > 0) {
                        this.mBtnXtralRvs[0].setDrawable(R.drawable.can_oj_left_big_gray, R.drawable.can_oj_left_big_gray);
                    } else {
                        this.mBtnXtralRvs[0].setDrawable(R.drawable.can_oj_left_big_up, R.drawable.can_oj_left_big_dn);
                    }
                    if (this.mXtralTsyArr.length > this.mXtralWcRvsAss.AutoParkTsy) {
                        this.mXtralTsy.setText(this.mXtralTsyArr[this.mXtralWcRvsAss.AutoParkTsy]);
                        return;
                    }
                    return;
                }
                return;
            }
            CanJni.NissanGetRvsAssis(this.mXtralRvsAss);
            if (this.mXtralRvsAss.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mXtralRvsAss.Update != 0) {
                this.mXtralRvsAss.Update = 0;
                for (ParamButton Show2 : this.mBtnXtralRvs) {
                    Show2.Show(false);
                }
                this.mXtralView[0].Show(false);
                this.mXtralView[1].Show(true);
                for (CustomTextView ShowGone3 : this.mXtralDeclare) {
                    ShowGone3.ShowGone(false);
                }
                if (this.mXtralRvsAss.AutoParkSta == 1) {
                    this.mBtnXtralRvs[8].Show(true);
                } else if (this.mXtralRvsAss.AutoParkSta == 2) {
                    this.mBtnXtralRvs[9].Show(true);
                    this.mBtnXtralRvs[10].Show(true);
                } else if (this.mXtralRvsAss.AutoParkSta == 3) {
                    this.mBtnXtralRvs[7].Show(true);
                    this.mBtnXtralRvs[6].Show(true);
                    this.mBtnXtralRvs[10].Show(true);
                    this.mBtnXtralRvs[11].Show(true);
                } else if (this.mXtralRvsAss.AutoParkSta == 4) {
                    for (int i3 = 0; i3 < 6; i3++) {
                        this.mBtnXtralRvs[i3].Show(true);
                    }
                    this.mBtnXtralRvs[10].Show(true);
                    this.mBtnXtralRvs[11].Show(true);
                    this.mBtnXtralRvs[12].Show(true);
                    this.mXtralView[0].Show(true);
                } else if (this.mXtralRvsAss.AutoParkSta == 5) {
                    this.mBtnXtralRvs[10].Show(true);
                } else if (this.mXtralRvsAss.AutoParkSta == 255) {
                    for (CustomTextView ShowGone4 : this.mXtralDeclare) {
                        ShowGone4.ShowGone(true);
                    }
                }
                this.mXtralView[1].SetSel(this.mXtralRvsAss.Camera);
                if (this.mXtralRvsAss.DirValid[0] > 0) {
                    this.mBtnXtralRvs[1].setDrawable(R.drawable.can_oj_up_gray, R.drawable.can_oj_up_gray);
                } else {
                    this.mBtnXtralRvs[1].setDrawable(R.drawable.can_oj_up_up, R.drawable.can_oj_up_dn);
                }
                if (this.mXtralRvsAss.DirValid[1] > 0) {
                    this.mBtnXtralRvs[2].setDrawable(R.drawable.can_oj_down_gray, R.drawable.can_oj_down_gray);
                } else {
                    this.mBtnXtralRvs[2].setDrawable(R.drawable.can_oj_down_up, R.drawable.can_oj_down_dn);
                }
                if (this.mXtralRvsAss.DirValid[2] > 0) {
                    this.mBtnXtralRvs[3].setDrawable(R.drawable.can_oj_left_gray, R.drawable.can_oj_left_gray);
                } else {
                    this.mBtnXtralRvs[3].setDrawable(R.drawable.can_oj_left_up, R.drawable.can_oj_left_dn);
                }
                if (this.mXtralRvsAss.DirValid[3] > 0) {
                    this.mBtnXtralRvs[4].setDrawable(R.drawable.can_oj_right_gray, R.drawable.can_oj_right_gray);
                } else {
                    this.mBtnXtralRvs[4].setDrawable(R.drawable.can_oj_right_up, R.drawable.can_oj_right_dn);
                }
                if (this.mXtralRvsAss.DirValid[4] > 0) {
                    this.mBtnXtralRvs[5].setDrawable(R.drawable.can_oj_right_big_gray, R.drawable.can_oj_right_big_gray);
                } else {
                    this.mBtnXtralRvs[5].setDrawable(R.drawable.can_oj_right_big_up, R.drawable.can_oj_right_big_dn);
                }
                if (this.mXtralRvsAss.DirValid[5] > 0) {
                    this.mBtnXtralRvs[0].setDrawable(R.drawable.can_oj_left_big_gray, R.drawable.can_oj_left_big_gray);
                } else {
                    this.mBtnXtralRvs[0].setDrawable(R.drawable.can_oj_left_big_up, R.drawable.can_oj_left_big_dn);
                }
                if (this.mXtralTsyArr.length > this.mXtralRvsAss.AutoParkTsy) {
                    this.mXtralTsy.setText(this.mXtralTsyArr[this.mXtralRvsAss.AutoParkTsy]);
                }
            }
        }
    }

    private void CheckNissanRzcRvsAssist(boolean check) {
        if (this.mBtnNissanRzcRvs != null && this.mNissanRzcView != null && this.mNissanRzcRvsAss != null) {
            CanJni.NissanGetRvsAssis(this.mNissanRzcRvsAss);
            if (this.mNissanRzcRvsAss.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mNissanRzcRvsAss.Update != 0) {
                this.mNissanRzcRvsAss.Update = 0;
                for (ParamButton Show : this.mBtnNissanRzcRvs) {
                    Show.Show(false);
                }
                this.mNissanRzcView[0].Show(false);
                this.mNissanRzcView[1].Show(true);
                for (CustomTextView ShowGone : this.mNissanRzcDeclare) {
                    ShowGone.ShowGone(false);
                }
                for (CustomTextView ShowGone2 : this.mNissanRzcDeclare2) {
                    ShowGone2.ShowGone(false);
                }
                if (CanJni.GetSubType() == 8) {
                    int sta = this.mNissanRzcRvsAss.AutoParkSta & 7;
                    int brk = this.mNissanRzcRvsAss.AutoParkSta & 128;
                    if (sta == 1) {
                        this.mBtnNissanRzcRvs[8].Show(true);
                    } else if (sta == 2) {
                        if (brk != 0) {
                            this.mBtnNissanRzcRvs[7].Show(true);
                        }
                        this.mBtnNissanRzcRvs[10].Show(true);
                        this.mBtnNissanRzcRvs[11].Show(true);
                        this.mBtnNissanRzcRvs[13].Show(true);
                        this.mBtnNissanRzcRvs[14].Show(true);
                        this.mBtnNissanRzcRvs[15].Show(true);
                        this.mBtnNissanRzcRvs[16].Show(true);
                        this.mBtnNissanRzcRvs[17].Show(true);
                        this.mBtnNissanRzcRvs[20].Show(true);
                        this.mNissanRzcDeclare2[0].ShowGone(true);
                        this.mNissanRzcDeclare2[1].ShowGone(true);
                    } else if (sta == 3) {
                        if (brk != 0) {
                            this.mBtnNissanRzcRvs[7].Show(true);
                        }
                        this.mBtnNissanRzcRvs[10].Show(true);
                        this.mBtnNissanRzcRvs[11].Show(true);
                        this.mBtnNissanRzcRvs[13].Show(true);
                        this.mBtnNissanRzcRvs[18].Show(true);
                        this.mBtnNissanRzcRvs[19].Show(true);
                        this.mBtnNissanRzcRvs[21].Show(true);
                        this.mNissanRzcDeclare2[2].ShowGone(true);
                    } else if (sta == 4) {
                        this.mBtnNissanRzcRvs[12].Show(true);
                        for (CustomTextView ShowGone3 : this.mNissanRzcDeclare) {
                            ShowGone3.ShowGone(true);
                        }
                    } else if (sta == 5) {
                        if (brk != 0) {
                            this.mBtnNissanRzcRvs[7].Show(true);
                        }
                        this.mBtnNissanRzcRvs[10].Show(true);
                        this.mBtnNissanRzcRvs[11].Show(true);
                        this.mBtnNissanRzcRvs[12].Show(true);
                    } else if (sta == 6) {
                        for (int i = 0; i < 6; i++) {
                            this.mBtnNissanRzcRvs[i].Show(true);
                        }
                        this.mBtnNissanRzcRvs[10].Show(true);
                        this.mBtnNissanRzcRvs[11].Show(true);
                        this.mBtnNissanRzcRvs[12].Show(true);
                        this.mNissanRzcView[0].Show(true);
                    } else if (sta == 7) {
                        this.mBtnNissanRzcRvs[10].Show(true);
                    }
                    if (brk == 0) {
                        this.mBtnNissanRzcRvs[0].setDrawable(R.drawable.can_oj_left_big_gray, R.drawable.can_oj_left_big_gray);
                        this.mBtnNissanRzcRvs[1].setDrawable(R.drawable.can_oj_up_gray, R.drawable.can_oj_up_gray);
                        this.mBtnNissanRzcRvs[2].setDrawable(R.drawable.can_oj_down_gray, R.drawable.can_oj_down_gray);
                        this.mBtnNissanRzcRvs[3].setDrawable(R.drawable.can_oj_left_gray, R.drawable.can_oj_left_gray);
                        this.mBtnNissanRzcRvs[4].setDrawable(R.drawable.can_oj_right_gray, R.drawable.can_oj_right_gray);
                        this.mBtnNissanRzcRvs[5].setDrawable(R.drawable.can_oj_right_big_gray, R.drawable.can_oj_right_big_gray);
                        this.mBtnNissanRzcRvs[11].setDrawable(R.drawable.can_oj_rect_gray, R.drawable.can_oj_rect_gray);
                        this.mBtnNissanRzcRvs[13].setDrawable(R.drawable.can_oj_rect_gray, R.drawable.can_oj_rect_gray);
                    } else {
                        this.mBtnNissanRzcRvs[0].setDrawable(R.drawable.can_oj_left_big_up, R.drawable.can_oj_left_big_dn);
                        this.mBtnNissanRzcRvs[1].setDrawable(R.drawable.can_oj_up_up, R.drawable.can_oj_up_dn);
                        this.mBtnNissanRzcRvs[2].setDrawable(R.drawable.can_oj_down_up, R.drawable.can_oj_down_dn);
                        this.mBtnNissanRzcRvs[3].setDrawable(R.drawable.can_oj_left_up, R.drawable.can_oj_left_dn);
                        this.mBtnNissanRzcRvs[4].setDrawable(R.drawable.can_oj_right_up, R.drawable.can_oj_right_dn);
                        this.mBtnNissanRzcRvs[5].setDrawable(R.drawable.can_oj_right_big_up, R.drawable.can_oj_right_big_dn);
                        this.mBtnNissanRzcRvs[11].setDrawable(R.drawable.can_oj_rect_up, R.drawable.can_oj_rect_dn);
                        this.mBtnNissanRzcRvs[13].setDrawable(R.drawable.can_oj_rect_up, R.drawable.can_oj_rect_dn);
                    }
                    this.mNissanRzcView[1].SetSel(this.mNissanRzcRvsAss.Camera);
                    this.mNissanRzcTsy.setText(this.mNissanRzcTsyArr[this.mNissanRzcRvsAss.AutoParkTsy]);
                    return;
                }
                if (this.mNissanRzcRvsAss.AutoParkSta == 1) {
                    this.mBtnNissanRzcRvs[8].Show(true);
                } else if (this.mNissanRzcRvsAss.AutoParkSta == 2) {
                    this.mBtnNissanRzcRvs[9].Show(true);
                    this.mBtnNissanRzcRvs[10].Show(true);
                } else if (this.mNissanRzcRvsAss.AutoParkSta == 3) {
                    this.mBtnNissanRzcRvs[7].Show(true);
                    this.mBtnNissanRzcRvs[6].Show(true);
                    this.mBtnNissanRzcRvs[10].Show(true);
                    this.mBtnNissanRzcRvs[11].Show(true);
                } else if (this.mNissanRzcRvsAss.AutoParkSta == 4) {
                    for (int i2 = 0; i2 < 6; i2++) {
                        this.mBtnNissanRzcRvs[i2].Show(true);
                    }
                    this.mBtnNissanRzcRvs[10].Show(true);
                    this.mBtnNissanRzcRvs[11].Show(true);
                    this.mBtnNissanRzcRvs[12].Show(true);
                    this.mNissanRzcView[0].Show(true);
                } else if (this.mNissanRzcRvsAss.AutoParkSta == 5) {
                    this.mBtnNissanRzcRvs[10].Show(true);
                } else if (this.mNissanRzcRvsAss.AutoParkSta == 255) {
                    for (CustomTextView ShowGone4 : this.mNissanRzcDeclare) {
                        ShowGone4.ShowGone(true);
                    }
                }
                this.mNissanRzcView[1].SetSel(this.mNissanRzcRvsAss.Camera);
                if (this.mNissanRzcRvsAss.DirValid[0] > 0) {
                    this.mBtnNissanRzcRvs[1].setDrawable(R.drawable.can_oj_up_gray, R.drawable.can_oj_up_gray);
                } else {
                    this.mBtnNissanRzcRvs[1].setDrawable(R.drawable.can_oj_up_up, R.drawable.can_oj_up_dn);
                }
                if (this.mNissanRzcRvsAss.DirValid[1] > 0) {
                    this.mBtnNissanRzcRvs[2].setDrawable(R.drawable.can_oj_down_gray, R.drawable.can_oj_down_gray);
                } else {
                    this.mBtnNissanRzcRvs[2].setDrawable(R.drawable.can_oj_down_up, R.drawable.can_oj_down_dn);
                }
                if (this.mNissanRzcRvsAss.DirValid[2] > 0) {
                    this.mBtnNissanRzcRvs[3].setDrawable(R.drawable.can_oj_left_gray, R.drawable.can_oj_left_gray);
                } else {
                    this.mBtnNissanRzcRvs[3].setDrawable(R.drawable.can_oj_left_up, R.drawable.can_oj_left_dn);
                }
                if (this.mNissanRzcRvsAss.DirValid[3] > 0) {
                    this.mBtnNissanRzcRvs[4].setDrawable(R.drawable.can_oj_right_gray, R.drawable.can_oj_right_gray);
                } else {
                    this.mBtnNissanRzcRvs[4].setDrawable(R.drawable.can_oj_right_up, R.drawable.can_oj_right_dn);
                }
                if (this.mNissanRzcRvsAss.DirValid[4] > 0) {
                    this.mBtnNissanRzcRvs[5].setDrawable(R.drawable.can_oj_right_big_gray, R.drawable.can_oj_right_big_gray);
                } else {
                    this.mBtnNissanRzcRvs[5].setDrawable(R.drawable.can_oj_right_big_up, R.drawable.can_oj_right_big_dn);
                }
                if (this.mNissanRzcRvsAss.DirValid[5] > 0) {
                    this.mBtnNissanRzcRvs[0].setDrawable(R.drawable.can_oj_left_big_gray, R.drawable.can_oj_left_big_gray);
                } else {
                    this.mBtnNissanRzcRvs[0].setDrawable(R.drawable.can_oj_left_big_up, R.drawable.can_oj_left_big_dn);
                }
                this.mNissanRzcTsy.setText(this.mNissanRzcTsyArr[this.mNissanRzcRvsAss.AutoParkTsy]);
            }
        }
    }

    private void CheckBlsuT5Mode(boolean check) {
        if (this.mBtnBlsuT5 != null && this.mBlsuT5CamSta != null) {
            CanJni.BlsuT5GetCamSta(this.mBlsuT5CamSta);
            if (this.mBlsuT5CamSta.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mBlsuT5CamSta.Update != 0) {
                this.mBlsuT5CamSta.Update = 0;
                if (this.mBlsuT5CamSta.Sta == 161 || this.mBlsuT5CamSta.Sta == 162 || this.mBlsuT5CamSta.Sta == 163 || this.mBlsuT5CamSta.Sta == 164) {
                    this.mBtnBlsuT5[0].Show(false);
                    this.mBtnBlsuT5[2].Show(false);
                    this.mBtnBlsuT5[1].Show(true);
                    if (this.mBlsuT5CamSta.Sta == 161) {
                        this.mBtnBlsuT5[3].Show(true);
                    } else {
                        this.mBtnBlsuT5[3].Show(false);
                    }
                } else if (this.mBlsuT5CamSta.Sta == 165 || this.mBlsuT5CamSta.Sta == 166 || this.mBlsuT5CamSta.Sta == 167 || this.mBlsuT5CamSta.Sta == 168) {
                    this.mBtnBlsuT5[0].Show(false);
                    this.mBtnBlsuT5[1].Show(false);
                    this.mBtnBlsuT5[3].Show(false);
                    this.mBtnBlsuT5[2].Show(true);
                } else if (this.mBlsuT5CamSta.Sta == 169 || this.mBlsuT5CamSta.Sta == 170 || this.mBlsuT5CamSta.Sta == 171 || this.mBlsuT5CamSta.Sta == 172 || this.mBlsuT5CamSta.Sta == 173 || this.mBlsuT5CamSta.Sta == 174) {
                    this.mBtnBlsuT5[1].Show(false);
                    this.mBtnBlsuT5[2].Show(false);
                    this.mBtnBlsuT5[3].Show(false);
                    this.mBtnBlsuT5[0].Show(true);
                }
            }
        }
    }

    private void CheckMGZSRvsPark(boolean check) {
        if (this.mRvsData != null) {
            CanJni.MgZsWcGetRvsData(this.mRvsData);
            if (this.mRvsData.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mRvsData.Update != 0) {
                this.mRvsData.Update = 0;
                if (this.mTxtMGZSrvs == null) {
                    return;
                }
                if (this.mRvsData.DisMin == 0) {
                    this.mTxtMGZSrvs.setText(String.format(" ", new Object[]{Integer.valueOf(this.mRvsData.DisMin)}));
                    return;
                }
                this.mTxtMGZSrvs.setText(String.format("%d cm", new Object[]{Integer.valueOf(this.mRvsData.DisMin)}));
            }
        }
    }

    private void CheckDFFGRzcBtn(boolean check) {
        boolean z;
        if (this.mBtnDFFGRzcMode != null) {
            CanJni.DffgRzcGetAvmData(this.mDffg_RzcAvm);
            if (this.mDffg_RzcAvm.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mDffg_RzcAvm.Update != 0) {
                this.mDffg_RzcAvm.Update = 0;
                if (this.mDffg_RzcAvm.Sta == 0) {
                    for (int i = 0; i < this.mBtnDFFGRzcMode.length - 1; i++) {
                        this.mBtnDFFGRzcMode[i].setSelected(false);
                    }
                } else if (this.mDffg_RzcAvm.Sta - 5 < this.mBtnDFFGRzcMode.length - 1) {
                    for (int i2 = 0; i2 < this.mBtnDFFGRzcMode.length - 1; i2++) {
                        ParamButton paramButton = this.mBtnDFFGRzcMode[i2];
                        if (i2 == this.mDffg_RzcAvm.Sta - 5) {
                            z = true;
                        } else {
                            z = false;
                        }
                        paramButton.setSelected(z);
                    }
                }
            }
        }
    }

    private void CheckVwWcBtn(boolean check) {
        boolean z;
        boolean z2 = true;
        if (this.mBtnVwWcMode != null) {
            CanJni.VwWcGetAssistSet(this.mVwWcCamera);
            if (this.mVwWcCamera.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mVwWcCamera.Update != 0) {
                this.mVwWcCamera.Update = 0;
                this.mBtnVwWcMode[0].setSelected(this.mVwWcCamera.Lbzc > 0);
                ParamButton paramButton = this.mBtnVwWcMode[1];
                if (this.mVwWcCamera.Rkzc > 0) {
                    z = true;
                } else {
                    z = false;
                }
                paramButton.setSelected(z);
                ParamButton paramButton2 = this.mBtnVwWcMode[2];
                if (this.mVwWcCamera.Ldjy <= 0) {
                    z2 = false;
                }
                paramButton2.setSelected(z2);
            }
        }
    }

    private void CheckCCH6WcBtn(boolean check) {
        if (this.mBtnCCH6WcMode != null) {
            CanJni.CcH6WcGetCarCameraSet(this.mCcH6WcCamerSta);
            if (this.mCcH6WcCamerSta.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mCcH6WcCamerSta.Update != 0) {
                this.mCcH6WcCamerSta.Update = 0;
                this.mBtnCCH6WcMode[0].SetSel(this.mCcH6WcCamerSta.FrontCam);
                this.mBtnCCH6WcMode[1].SetSel(this.mCcH6WcCamerSta.RearCam);
                this.mBtnCCH6WcMode[2].SetSel(this.mCcH6WcCamerSta.LeftCam);
                this.mBtnCCH6WcMode[3].SetSel(this.mCcH6WcCamerSta.RightCam);
            }
        }
    }

    private void CheckGEELYjXBtn(boolean check) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = true;
        if (this.mBtnGeelyYjX6Mode != null) {
            CanJni.GeelyGetCameraSta(this.mGeelyCameraSta);
            if (this.mGeelyCameraSta.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mGeelyCameraSta.Update != 0) {
                this.mGeelyCameraSta.Update = 0;
                this.mBtnGeelyYjX6Mode[0].setSelected(this.mGeelyCameraSta.CameraSta == 3);
                ParamButton paramButton = this.mBtnGeelyYjX6Mode[1];
                if (this.mGeelyCameraSta.CameraSta == 4) {
                    z = true;
                } else {
                    z = false;
                }
                paramButton.setSelected(z);
                ParamButton paramButton2 = this.mBtnGeelyYjX6Mode[2];
                if (this.mGeelyCameraSta.CameraSta == 1) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                paramButton2.setSelected(z2);
                ParamButton paramButton3 = this.mBtnGeelyYjX6Mode[3];
                if (this.mGeelyCameraSta.CameraSta == 2) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                paramButton3.setSelected(z3);
                ParamButton paramButton4 = this.mBtnGeelyYjX63D;
                if (this.mGeelyCameraSta.CameraSta != 5) {
                    z4 = false;
                }
                paramButton4.setSelected(z4);
                this.mBtnGeelyYjX6GJ.SetSel(this.mGeelyCameraSta.CameraSet & 128);
                this.mBtnGeelyYjX6FXP.SetSel(this.mGeelyCameraSta.CameraSet & 64);
            }
        }
    }

    private void CheckCs75WcMode(boolean check) {
        if (this.mCs75CameraSta != null && this.mBtnCs75WcMode != null) {
            CanJni.ChanAWcCs75GetCameraSta(this.mCs75CameraSta);
        }
    }

    private void CheckTrumpchiWcMode(boolean check) {
        if (this.mTrumpchiWcCamera != null && this.mBtnTrumpchiWcMode != null) {
            CanJni.TrumpchiWcGetCameraSta(this.mTrumpchiWcCamera);
            if (this.mTrumpchiWcCamera.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mTrumpchiWcCamera.Update != 0) {
                this.mTrumpchiWcCamera.Update = 0;
                for (ParamButton selected : this.mBtnTrumpchiWcMode) {
                    selected.setSelected(false);
                }
                int sta = this.mTrumpchiWcCamera.Sta;
                if (sta < 6 && sta != 0) {
                    this.mBtnTrumpchiWcMode[sta - 1].setSelected(true);
                } else if (sta == 7) {
                    this.mBtnTrumpchiWcMode[5].setSelected(true);
                } else if (sta == 8) {
                    this.mBtnTrumpchiWcMode[6].setSelected(true);
                }
            }
        }
    }

    private void CheckJacRefineMode(boolean check) {
        if (this.mJacAvmData != null && this.mBtnJacRefineMode != null) {
            CanJni.JacGetCarAvmData(this.mJacAvmData);
            if (this.mJacAvmData.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mJacAvmData.Update != 0) {
                this.mJacAvmData.Update = 0;
                for (ParamButton btn : this.mBtnJacRefineStats) {
                    btn.setSelected(false);
                }
                if (this.mJacAvmData.Sta == 1) {
                    this.mBtnJacRefineStats[0].setSelected(true);
                } else if (this.mJacAvmData.Sta == 2) {
                    this.mBtnJacRefineStats[1].setSelected(true);
                } else if (this.mJacAvmData.Sta == 3) {
                    this.mBtnJacRefineStats[2].setSelected(true);
                }
                for (ParamButton btn2 : this.mBtnJacRefineMode) {
                    btn2.setSelected(false);
                }
                if (this.mJacAvmData.CameraMode == 3) {
                    this.mBtnJacRefineMode[0].setSelected(true);
                } else if (this.mJacAvmData.CameraMode == 5) {
                    this.mBtnJacRefineMode[1].setSelected(true);
                } else if (this.mJacAvmData.CameraMode == 7) {
                    this.mBtnJacRefineMode[2].setSelected(true);
                } else if (this.mJacAvmData.CameraMode == 9) {
                    this.mBtnJacRefineMode[3].setSelected(true);
                }
            }
        }
    }

    private void CheckJacRefineWcMode(boolean check) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5 = true;
        if (this.mJacWcAvmData != null && this.mBtnJacRefineWcMode != null) {
            CanJni.JACRefineWcGetAvmData(this.mJacWcAvmData);
            if (this.mJacWcAvmData.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mJacWcAvmData.Update != 0) {
                this.mJacWcAvmData.Update = 0;
                this.mBtnJacRefineWcStats[0].setSelected(this.mJacWcAvmData.Mode == 2);
                ParamButton paramButton = this.mBtnJacRefineWcStats[1];
                if (this.mJacWcAvmData.Mode == 1) {
                    z = true;
                } else {
                    z = false;
                }
                paramButton.setSelected(z);
                ParamButton paramButton2 = this.mBtnJacRefineWcMode[0];
                if (this.mJacWcAvmData.FrontCamera == 1) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                paramButton2.setSelected(z2);
                ParamButton paramButton3 = this.mBtnJacRefineWcMode[1];
                if (this.mJacWcAvmData.RearCamera == 1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                paramButton3.setSelected(z3);
                ParamButton paramButton4 = this.mBtnJacRefineWcMode[2];
                if (this.mJacWcAvmData.LeftCamera == 1) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                paramButton4.setSelected(z4);
                ParamButton paramButton5 = this.mBtnJacRefineWcMode[3];
                if (this.mJacWcAvmData.RightCamera != 1) {
                    z5 = false;
                }
                paramButton5.setSelected(z5);
            }
        }
    }

    private void CheckHondaWcMode(boolean check) {
        if (this.mBtnViewGj != null && this.mBtnViewBz != null && this.mBtnViewFj != null) {
            CanJni.HondaWcGetCameraSta(this.mHondawcCamera);
            if (this.mHondawcCamera.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mHondawcCamera.Update != 0) {
                this.mHondawcCamera.Update = 0;
                this.mBtnViewGj.setSelected(false);
                this.mBtnViewBz.setSelected(false);
                this.mBtnViewFj.setSelected(false);
                switch (this.mHondawcCamera.CameraMode) {
                    case 1:
                        this.mBtnViewGj.setSelected(true);
                        return;
                    case 2:
                        this.mBtnViewBz.setSelected(true);
                        return;
                    case 3:
                        this.mBtnViewFj.setSelected(true);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private void CheckLandWindMode(boolean check) {
        boolean z = false;
        if (this.mBtnLandWind != null && this.mLandwindAvm != null) {
            CanJni.LoadWindRzcGetAvmSta(this.mLandwindAvm);
            if (this.mLandwindAvm.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mLandwindAvm.Update != 0) {
                this.mLandwindAvm.Update = 0;
                for (ParamButton btn : this.mBtnLandWind) {
                    btn.setSelected(false);
                }
                int ms = this.mLandwindAvm.Ms;
                this.mBtnLandWind[0].Show(true);
                if (ms != 0) {
                    this.mBtnLandWind[0].setStateUpDn(R.drawable.can_rh7_3d_up, R.drawable.can_rh7_3d_dn);
                } else {
                    this.mBtnLandWind[0].setStateUpDn(R.drawable.can_rh7_2d_up, R.drawable.can_rh7_2d_dn);
                }
                if (ms == 0) {
                    z = true;
                }
                showLandwind23D(z);
                switch (this.mLandwindAvm.CameraSta) {
                    case 3:
                    case 7:
                        this.mBtnLandWind[1].setSelected(true);
                        return;
                    case 4:
                        this.mBtnLandWind[2].setSelected(true);
                        return;
                    case 5:
                        this.mBtnLandWind[3].setSelected(true);
                        return;
                    case 6:
                        this.mBtnLandWind[4].setSelected(true);
                        return;
                    case 8:
                        this.mBtnLandWind[5].setSelected(true);
                        return;
                    case 9:
                        this.mBtnLandWind[6].setSelected(true);
                        return;
                    case 10:
                        this.mBtnLandWind[7].setSelected(true);
                        return;
                    case 11:
                    case 14:
                        this.mBtnLandWind[8].setSelected(true);
                        return;
                    case 12:
                        this.mBtnLandWind[9].setSelected(true);
                        return;
                    case 13:
                        this.mBtnLandWind[10].setSelected(true);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private void showLandwind23D(boolean is2D) {
        if (this.mBtnLandWind != null) {
            for (int i = 1; i < this.mBtnLandWind.length; i++) {
                if (i < 5) {
                    this.mBtnLandWind[i].Show(is2D);
                } else {
                    this.mBtnLandWind[i].Show(!is2D);
                }
            }
        }
    }

    private void CheckGolfWcMode(boolean check) {
        if (this.mBtnGolfWcMode != null && this.mGolfWcCamSta != null) {
            CanJni.GolfWcGetCameraSta(this.mGolfWcCamSta);
            if (this.mGolfWcCamSta.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mGolfWcCamSta.Update != 0) {
                this.mGolfWcCamSta.Update = 0;
                for (ParamButton btn : this.mBtnGolfWcMode) {
                    btn.setSelected(false);
                }
                int mode = this.mGolfWcCamSta.Mode;
                if (mode == 1) {
                    this.mBtnGolfWcMode[0].setSelected(true);
                } else if (mode == 2) {
                    this.mBtnGolfWcMode[1].setSelected(true);
                } else if (mode == 3) {
                    this.mBtnGolfWcMode[2].setSelected(true);
                } else if (mode == 9) {
                    this.mBtnGolfWcMode[3].setSelected(true);
                }
            }
        }
    }

    private void CheckFordWcMode(boolean check) {
        boolean z = true;
        if (this.mBtnFordWcMode != null && this.mFordWcCameraData != null) {
            CanJni.FordWcGetCameraSet(this.mFordWcCameraData);
            if (this.mFordWcCameraData.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mFordWcCameraData.Update != 0) {
                this.mFordWcCameraData.Update = 0;
                this.mBtnFordWcMode[0].setSelected(this.mFordWcCameraData.Gjst > 0);
                ParamButton paramButton = this.mBtnFordWcMode[1];
                if (this.mFordWcCameraData.Fjst <= 0) {
                    z = false;
                }
                paramButton.setSelected(z);
            }
        }
    }

    private void CheckSenovaRzcMode(boolean check) {
        if (this.mSenovaRzcAvmData != null && this.mBtnSenovaRzcMode != null) {
            CanJni.SenovaRzcGetAvmData(this.mSenovaRzcAvmData);
            if (this.mSenovaRzcAvmData.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mSenovaRzcAvmData.Update != 0) {
                this.mSenovaRzcAvmData.Update = 0;
                if (this.mSenovaRzcAvmData.Mode == 0) {
                    this.mBtnSenovaRzcMode[0].setDrawable(R.drawable.can_rh7_2d_up, R.drawable.can_rh7_2d_dn);
                } else {
                    this.mBtnSenovaRzcMode[0].setDrawable(R.drawable.can_rh7_3d_up, R.drawable.can_rh7_3d_dn);
                }
            }
        }
    }

    private void CheckPorcheOdAvm(boolean check) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5 = true;
        if (this.mPorscheOdData != null) {
            CanJni.PorscheOdGetCarAvmData(this.mPorscheOdData);
            if (this.mPorscheOdData.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mPorscheOdData.Update != 0) {
                this.mPorscheOdData.Update = 0;
                this.mBtnProscheOdMode[0].setSelected(this.mPorscheOdData.Sta == 2);
                ParamButton paramButton = this.mBtnProscheOdMode[1];
                if (this.mPorscheOdData.Sta == 3) {
                    z = true;
                } else {
                    z = false;
                }
                paramButton.setSelected(z);
                ParamButton paramButton2 = this.mBtnProscheOdMode[2];
                if (this.mPorscheOdData.Sta == 4) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                paramButton2.setSelected(z2);
                ParamButton paramButton3 = this.mBtnProscheOdMode[3];
                if (this.mPorscheOdData.Sta == 5) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                paramButton3.setSelected(z3);
                ParamButton paramButton4 = this.mBtnProscheOdMode[4];
                if (this.mPorscheOdData.Sta == 6) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                paramButton4.setSelected(z4);
                ParamButton paramButton5 = this.mBtnProscheOdMode[5];
                if (this.mPorscheOdData.Sta != 1) {
                    z5 = false;
                }
                paramButton5.setSelected(z5);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateProgress(int progress) {
        if (this.mProgressBar.getVisibility() != 0) {
            this.mIvWarnning.setImageResource(R.drawable.can_focus_park_auto);
            this.mProgressBar.setVisibility(0);
            this.mTvTime.setVisibility(0);
        }
        if (progress <= 10) {
            this.mTvTime.setText(String.valueOf(progress) + " s");
            this.mProgressBar.setProgress(progress);
            this.mHandler.removeMessages(0);
            this.mHandler.sendEmptyMessageDelayed(0, 1000);
            return;
        }
        this.mHandler.removeMessages(0);
    }

    private void CheckFordActivePark(boolean check) {
        if (this.mActivePark != null) {
            CanJni.FordGetActivePark(this.mActivePark);
            if (this.mActivePark.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mActivePark.Update != 0) {
                this.mActivePark.Update = 0;
                if (this.mActivePark.fgOn != 0) {
                    updateStatus(this.mActivePark.Status);
                } else {
                    removeParkInfos();
                }
            }
        }
    }

    private void CheckFrCamera(boolean check) {
        if (this.mBtnFrCamera == null) {
            return;
        }
        if (this.mOldFrCamera != this.mFrCamera || !check) {
            this.mOldFrCamera = this.mFrCamera;
            if (this.mFrCamera > 0) {
                if (this.mCameraText != null) {
                    this.mCameraText.setText(R.string.can_front_camera);
                }
                this.mBtnFrCamera.setStateUpDn(R.drawable.can_gs5_bot_01_up, R.drawable.can_gs5_bot_01_dn);
                return;
            }
            if (this.mCameraText != null) {
                this.mCameraText.setText(R.string.can_rear_camera);
            }
            this.mBtnFrCamera.setStateUpDn(R.drawable.can_gs5_bot_02_up, R.drawable.can_gs5_bot_02_dn);
        }
    }

    private void CheckRenaultXpCameraMode(boolean check) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        if (this.mBtnRenaultXpCamMode != null) {
            CanJni.RenaultGetAvmData(this.mRensultXpAvmData);
            if (this.mRensultXpAvmData.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mRensultXpAvmData.Update != 0) {
                this.mRensultXpAvmData.Update = 0;
                this.mBtnRenaultXpCamMode[0].setSelected(this.mRensultXpAvmData.Mode == 1);
                ParamButton paramButton = this.mBtnRenaultXpCamMode[1];
                if (this.mRensultXpAvmData.Mode == 2) {
                    z = true;
                } else {
                    z = false;
                }
                paramButton.setSelected(z);
                ParamButton paramButton2 = this.mBtnRenaultXpCamMode[2];
                if (this.mRensultXpAvmData.Mode == 3) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                paramButton2.setSelected(z2);
                ParamButton paramButton3 = this.mBtnRenaultXpCamMode[3];
                if (this.mRensultXpAvmData.Mode != 4) {
                    z3 = false;
                }
                paramButton3.setSelected(z3);
            }
        }
    }

    private void showDialog(String text, boolean hideTakeCare) {
        int i = 0;
        this.mDialogLayout.setVisibility(0);
        TextView textView = this.mTvTakeCare;
        if (hideTakeCare) {
            i = 4;
        }
        textView.setVisibility(i);
        this.mTvDialogStatus.setText(text);
        this.mIvWarnning.setImageResource(R.drawable.can_focus_park_warnning);
        this.mProgressBar.setVisibility(4);
        this.mTvTime.setVisibility(4);
    }

    private void updateParkInfos(String text, int resId) {
        this.mDialogLayout.setVisibility(8);
        this.mTvParkStatus.setVisibility(0);
        this.mIvParkIcon.setVisibility(0);
        this.mTvParkStatus.setText(text);
        this.mIvParkIcon.setImageResource(resId);
    }

    private void removeParkInfos() {
        this.mDialogLayout.setVisibility(8);
        this.mTvParkStatus.setVisibility(8);
        this.mIvParkIcon.setVisibility(8);
    }

    private void updateStatus(int status) {
        this.mHandler.removeMessages(0);
        switch (status) {
            case 0:
            case 1:
                removeParkInfos();
                return;
            case 2:
                showDialog(this.mStatusValues[0], true);
                return;
            case 3:
                showDialog(this.mStatusValues[1], false);
                return;
            case 4:
                updateParkInfos(this.mStatusValues[2], R.drawable.can_focus_park_rv);
                return;
            case 5:
                updateParkInfos(this.mStatusValues[3], R.drawable.can_focus_park_lv);
                return;
            case 6:
                showDialog(this.mStatusValues[4], false);
                return;
            case 7:
            case 9:
                updateParkInfos(this.mStatusValues[5], R.drawable.can_focus_park_sarrow);
                return;
            case 8:
            case 10:
                updateParkInfos(this.mStatusValues[6], R.drawable.can_focus_park_sarrow);
                return;
            case 11:
                updateParkInfos(this.mStatusValues[7], R.drawable.can_focus_park_stop);
                return;
            case 12:
                updateParkInfos(this.mStatusValues[8], R.drawable.can_focus_park_stop);
                return;
            case 13:
                updateParkInfos(this.mStatusValues[9], R.drawable.can_focus_park_r);
                return;
            case 14:
                updateParkInfos(this.mStatusValues[10], R.drawable.can_focus_park_r);
                return;
            case 15:
            case 17:
                updateParkInfos(this.mStatusValues[11], R.drawable.can_focus_park_xarrow);
                return;
            case 16:
            case 18:
                updateParkInfos(this.mStatusValues[12], R.drawable.can_focus_park_xarrow);
                return;
            case 19:
                updateParkInfos(this.mStatusValues[13], R.drawable.can_focus_park_stop);
                return;
            case 20:
                updateParkInfos(this.mStatusValues[13], R.drawable.can_focus_park_stop);
                return;
            case 21:
            case 35:
                updateParkInfos(this.mStatusValues[14], R.drawable.can_focus_park_sarrow);
                return;
            case 22:
            case 36:
                updateParkInfos(this.mStatusValues[14], R.drawable.can_focus_park_sarrow);
                return;
            case 23:
                updateParkInfos(this.mStatusValues[15], R.drawable.can_focus_park_stop);
                return;
            case 24:
                updateParkInfos(this.mStatusValues[15], R.drawable.can_focus_park_stop);
                return;
            case 25:
            case 37:
                updateParkInfos(this.mStatusValues[16], R.drawable.can_focus_park_xarrow);
                return;
            case 26:
            case 38:
                updateParkInfos(this.mStatusValues[16], R.drawable.can_focus_park_xarrow);
                return;
            case 27:
                showDialog(this.mStatusValues[17], true);
                return;
            case 28:
                showDialog(this.mStatusValues[18], true);
                updateProgress(0);
                return;
            case 29:
            case 39:
                showDialog(this.mStatusValues[19], false);
                return;
            case 30:
                showDialog(this.mStatusValues[20], false);
                return;
            case 31:
                showDialog(this.mStatusValues[21], false);
                return;
            case 32:
                showDialog(this.mStatusValues[22], false);
                return;
            case 33:
                showDialog(this.mStatusValues[23], false);
                return;
            case 34:
                showDialog(this.mStatusValues[24], false);
                return;
            default:
                return;
        }
    }

    private void CheckGMRzcActivePark(boolean check) {
        if (this.mGMActivePark != null) {
            CanJni.GmDealAutoPark(this.mGMActivePark);
            if (this.mGMActivePark.UpdateOnce == 0) {
                return;
            }
            if (!check || this.mGMActivePark.Update != 0) {
                this.mGMActivePark.Update = 0;
                updateGMRzcStatus(this.mGMActivePark.TsMsg);
                this.mCanVerticalBar.setVisibility(0);
                this.mCanVerticalBar.setCurPos(this.mGMActivePark.Pro);
            }
        }
    }

    private void updateGMRzcParkInfos(String text) {
        this.mTvParkStatus.setVisibility(0);
        this.mTvParkStatus.setText(text);
    }

    private void removeGMRzcParkInfos() {
        this.mTvParkStatus.setVisibility(8);
    }

    private void updateGMRzcStatus(int status) {
        switch (status) {
            case 5:
            case 6:
            case 7:
            case 8:
                updateGMRzcParkInfos(this.mStatusValues[status - 3]);
                return;
            case 10:
            case 14:
            case 20:
            case 23:
                updateGMRzcParkInfos(this.mStatusValues[7]);
                return;
            case 11:
            case 15:
                updateGMRzcParkInfos(this.mStatusValues[8]);
                return;
            case 12:
            case 16:
            case 21:
            case 24:
                updateGMRzcParkInfos(this.mStatusValues[9]);
                return;
            case 25:
                updateGMRzcParkInfos(this.mStatusValues[15]);
                return;
            case 26:
                updateGMRzcParkInfos(this.mStatusValues[16]);
                return;
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
                updateGMRzcParkInfos(this.mStatusValues[status - 10]);
                return;
            default:
                removeGMRzcParkInfos();
                return;
        }
    }

    private void DealRadarVoiceFinsh() {
        switch (CanJni.GetCanType()) {
            case 132:
                if (CanJni.GetSubType() != 0) {
                    Log.d(TAG, "DealRadarVoiceFinsh");
                    BackCarSound.GetInstance().PlayRadar(false, 0);
                    this.mRadarVoice = 255;
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void DealRadarVoice() {
        int val1;
        int val2;
        CanDataInfo.CAN_RadarInfo cAN_RadarInfo = Can.mRadarForeInfo;
        CanDataInfo.CAN_RadarInfo mRearRadar = Can.mRadarRearInfo;
        switch (CanJni.GetCanType()) {
            case 132:
                if (CanJni.GetSubType() != 0) {
                    int[] rearRadarData = {mRearRadar.nLeftDis, mRearRadar.nMidLtDis, mRearRadar.nMidRtDis, mRearRadar.nRightDis};
                    int min = SupportMenu.USER_MASK;
                    int temp = 0;
                    for (int i = 0; i < 3; i++) {
                        if (rearRadarData[i] == 0) {
                            val1 = 255;
                        } else {
                            val1 = rearRadarData[i];
                        }
                        if (rearRadarData[i + 1] == 0) {
                            val2 = 255;
                        } else {
                            val2 = rearRadarData[i + 1];
                        }
                        if (val1 < val2) {
                            if (min > val1) {
                                min = val1;
                            }
                        } else if (min > val2) {
                            min = val2;
                        }
                    }
                    if (min == 0 || min > 10) {
                        temp = 0;
                    } else if (min == 1) {
                        temp = 2;
                    } else if (min <= 3) {
                        temp = 6;
                    } else if (min <= 6) {
                        temp = 10;
                    } else if (min <= 10) {
                        temp = 14;
                    }
                    if (temp != this.mRadarVoice) {
                        this.mRadarVoice = temp;
                        Log.d(TAG, "mRadarVoice=" + this.mRadarVoice);
                        if (this.mRadarVoice == 0) {
                            BackCarSound.GetInstance().PlayRadar(false, this.mRadarVoice);
                            return;
                        } else {
                            BackCarSound.GetInstance().PlayRadar(true, this.mRadarVoice);
                            return;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void SignalCheck() {
        if (MainSet.GetInstance().IsHaveCamSignal()) {
            this.nCurSignalSta = 1;
        } else {
            this.nCurSignalSta = 0;
        }
        if (this.nSignalSta != this.nCurSignalSta) {
            this.nSignalSta = this.nCurSignalSta;
            Log.i(TAG, "nCurSignalSta =" + this.nSignalSta);
            if (this.nSignalSta == 0) {
                this.mVedioStaText.setVisibility(0);
            } else {
                this.mVedioStaText.setVisibility(8);
            }
        }
    }

    public void UserAll() {
        if (MainSet.GetInstance().GetCamType() == 3 && CanJni.GetCanType() > 0) {
            Can.updateEps();
        }
        if (this.mbResume) {
            int i = this.nDelayCheck + 1;
            this.nDelayCheck = i;
            if (i % 15 == 0) {
                Log.i(TAG, "UserAll");
                SignalCheck();
            }
            if (this.mAvmIconTick > 0) {
                this.mAvmIconTick--;
                if (this.mAvmIconTick == 0 && ((FtSet.Getyw4() & 1) > 0 || (CanJni.GetCanType() == 6 && CanJni.GetSubType() == 7))) {
                    CanFunc.CameraBtnShow(0);
                    CanFunc.CameraBtnShow(1);
                }
            }
            ResetData(true);
            CheckPort();
            if (this.mfgShowTrack && MainSet.GetInstance().GetCamType() != 3 && Can.updateEps()) {
                updateEps();
            }
            if (!this.mfgFullScr && CanFunc.mbRadarUIUpdate) {
                CanFunc.mbRadarUIUpdate = false;
                CanRadarActivity.invalidateRadar(this.mRightForeRadar, this.mRightRearRadar);
                CanRadarActivity.invalidateSideRadar(this.mLeftSideRadar, this.mRightSideRadar);
            }
        }
    }

    private void ShowCanBtn(boolean show) {
        boolean is2D;
        boolean is2D2;
        if (this.mBtnViewFj != null) {
            this.mBtnViewFj.Show(show);
        }
        if (this.mBtnViewBz != null) {
            this.mBtnViewBz.Show(show);
        }
        if (this.mBtnViewGj != null) {
            this.mBtnViewGj.Show(show);
        }
        if (this.mBtnZoom != null) {
            this.mBtnZoom.Show(show);
        }
        if (this.mBtnDisplaySet != null) {
            if (show) {
                this.mBtnDisplaySet.setVisibility(0);
            } else {
                this.mBtnDisplaySet.setVisibility(4);
            }
        }
        if (this.mBtnGS5Mode != null) {
            this.mBtnGS5Mode.Show(show);
        }
        if (this.mBtnMode_HyRzc != null) {
            this.mBtnMode_HyRzc.Show(show);
        }
        if (this.mBtnMagotenMode != null) {
            for (int i = 0; i < 4; i++) {
                this.mBtnMagotenMode[i].Show(show);
            }
        }
        if (this.mBtnCCH2Mode != null) {
            this.mBtnCCH2Mode[0].Show(show);
            this.mBtnCCH2Mode[1].Show(show);
            this.mBtnCCH2Mode[2].Show(show);
        }
        if (this.mBtnCCH2WCMode != null) {
            this.mBtnCCH2WCMode[0].Show(show);
            this.mBtnCCH2WCMode[1].Show(show);
            this.mBtnCCH2WCMode[2].Show(show);
        }
        if (this.mBtnCCH2Esc != null) {
            this.mBtnCCH2Esc.Show(show);
        }
        if (this.mBtnBYDDJRadar != null) {
            this.mBtnBYDDJRadar.Show(show);
        }
        if (this.mBtnX80CamMode != null) {
            for (ParamButton Show : this.mBtnX80CamMode) {
                Show.Show(show);
            }
        }
        if (this.mBtnGeelyBoyMode != null) {
            this.mBtnGeelyBoyEsc.Show(show);
            for (ParamButton Show2 : this.mBtnGeelyBoyMode) {
                Show2.Show(show);
            }
        }
        if (this.mBtnVenuciaMode != null) {
            this.mBtnVenuciaEsc.Show(show);
            for (ParamButton Show3 : this.mBtnVenuciaMode) {
                Show3.Show(show);
            }
        }
        if (this.mBtnTrumpchiGs4Mode != null) {
            for (ParamButton Show4 : this.mBtnTrumpchiGs4Mode) {
                Show4.Show(show);
            }
        }
        if (this.mBtnHmS7Mode != null) {
            for (ParamButton btn : this.mBtnHmS7Mode) {
                btn.Show(show);
            }
            this.mBtnHmS7Esc.Show(show);
            this.mBtnHmS7Line.Show(show);
        }
        if (this.mBtnYg9Mode != null) {
            for (ParamButton Show5 : this.mBtnYg9Mode) {
                Show5.Show(show);
            }
        }
        if (this.mBtnGeelyYjX6Mode != null) {
            this.mBtnGeelyYjX6Esc.Show(show);
            this.mBtnGeelyYjX63D.Show(show);
            this.mBtnGeelyYjX6GJ.Show(show);
            this.mBtnGeelyYjX6FXP.Show(show);
            for (ParamButton Show6 : this.mBtnGeelyYjX6Mode) {
                Show6.Show(show);
            }
        }
        if (this.mBtnChanAAlsvinV76Mode != null) {
            for (ParamButton Show7 : this.mBtnChanAAlsvinV76Mode) {
                Show7.Show(show);
            }
        }
        if (this.mBtnZotyeMode != null) {
            for (ParamButton Show8 : this.mBtnZotyeMode) {
                Show8.Show(show);
            }
        }
        if (this.mBtnZotyeX5WcMode != null) {
            for (ParamButton Show9 : this.mBtnZotyeX5WcMode) {
                Show9.Show(show);
            }
        }
        if (this.mBtnBaicHSS6WcMode != null) {
            for (ParamButton Show10 : this.mBtnBaicHSS6WcMode) {
                Show10.Show(show);
            }
        }
        if (this.mBtnX80WcMode != null) {
            for (ParamButton Show11 : this.mBtnX80WcMode) {
                Show11.Show(show);
            }
        }
        if (this.mBtnCs75AvmMode != null) {
            for (ParamButton Show12 : this.mBtnCs75AvmMode) {
                Show12.Show(show);
            }
        }
        if (this.mBtnCameryMode != null) {
            for (ParamButton Show13 : this.mBtnCameryMode) {
                Show13.Show(show);
            }
        }
        if (this.mBtnVwWcMode != null) {
            for (ParamButton Show14 : this.mBtnVwWcMode) {
                Show14.Show(show);
            }
        }
        if (this.mBtnGolfWcMode != null) {
            for (ParamButton btn2 : this.mBtnGolfWcMode) {
                btn2.Show(show);
            }
        }
        if (this.mBtnFordRzcMode != null) {
            for (ParamButton btn3 : this.mBtnFordRzcMode) {
                btn3.Show(show);
            }
        }
        if (this.mBtnCCH9Mode != null) {
            if (!show) {
                for (ParamButton Show15 : this.mBtnCCH9Mode) {
                    Show15.Show(false);
                }
            } else if (this.isAutoParking) {
                this.mBtnCCH9Mode[0].Show(true);
                this.mBtnCCH9Mode[1].Show(true);
                this.mBtnCCH9Mode[2].Show(true);
                this.mBtnCCH9Mode[3].Show(false);
                this.mBtnCCH9Mode[4].Show(false);
                this.mBtnCCH9Mode[5].Show(false);
            } else {
                this.mBtnCCH9Mode[0].Show(false);
                this.mBtnCCH9Mode[1].Show(false);
                this.mBtnCCH9Mode[2].Show(false);
                this.mBtnCCH9Mode[3].Show(true);
                this.mBtnCCH9Mode[4].Show(true);
                this.mBtnCCH9Mode[5].Show(true);
            }
        }
        if (this.mBtnCCH9Direction != null) {
            this.mBtnCCH9Direction[0].Show(show);
            this.mBtnCCH9Direction[1].Show(show);
            this.mBtnCCH9Direction[2].Show(show);
            this.mBtnCCH9Direction[3].Show(show);
        }
        if (this.mBtnCCH9Bot != null) {
            this.mBtnCCH9Bot[0].Show(show);
            this.mBtnCCH9Bot[1].Show(show);
            this.mBtnCCH9Bot[2].Show(show);
            this.mBtnCCH9Bot[3].Show(show);
        }
        if (this.mBtnSenovaSubBJ40 != null) {
            for (ParamButton Show16 : this.mBtnSenovaSubBJ40) {
                Show16.Show(show);
            }
        }
        if (this.mBtnLandWind != null) {
            for (ParamButton btn4 : this.mBtnLandWind) {
                btn4.Show(show);
            }
            if (this.mLandwindAvm.Ms == 0) {
                is2D2 = true;
            } else {
                is2D2 = false;
            }
            if (this.mBtnLandWind != null) {
                for (int i2 = 1; i2 < this.mBtnLandWind.length; i2++) {
                    if (i2 < 5) {
                        if (!is2D2) {
                            this.mBtnLandWind[i2].Show(false);
                        }
                    } else if (is2D2) {
                        this.mBtnLandWind[i2].Show(false);
                    }
                }
            }
        }
        if (this.mBtnPorscheLZMode != null) {
            for (ParamButton btn5 : this.mBtnPorscheLZMode) {
                btn5.Show(show);
            }
        }
        if (this.mBtnChanaCos1WcMode != null) {
            for (ParamButton btn6 : this.mBtnChanaCos1WcMode) {
                btn6.Show(show);
            }
        }
        if (this.mBtnCCWcMode != null) {
            for (ParamButton btn7 : this.mBtnCCWcMode) {
                btn7.Show(show);
            }
        }
        if (this.mBtnCCH6WcMode != null) {
            for (ParamButton btn8 : this.mBtnCCH6WcMode) {
                btn8.Show(show);
            }
        }
        if (this.mBtnDFFGMode != null) {
            for (ParamButton btn9 : this.mBtnDFFGMode) {
                btn9.Show(show);
            }
        }
        if (this.mBtnDFFGRzcMode != null) {
            for (ParamButton btn10 : this.mBtnDFFGRzcMode) {
                btn10.Show(show);
            }
        }
        if (this.mBtnCs75WcMode != null) {
            this.mBtnCs75WcMode.Show(show);
        }
        if (this.mBtnTrumpchiWcMode != null) {
            for (ParamButton btn11 : this.mBtnTrumpchiWcMode) {
                btn11.Show(show);
            }
        }
        if (this.mBtnBlsuOdMode != null) {
            for (ParamButton btn12 : this.mBtnBlsuOdMode) {
                btn12.Show(show);
            }
        }
        if (this.mBtnBlsuOdStats != null) {
            for (ParamButton btn13 : this.mBtnBlsuOdStats) {
                btn13.Show(show);
            }
        }
        if (this.mBtnJacRefineMode != null) {
            for (ParamButton btn14 : this.mBtnJacRefineMode) {
                btn14.Show(show);
            }
        }
        if (this.mBtnJacRefineStats != null) {
            for (ParamButton btn15 : this.mBtnJacRefineStats) {
                btn15.Show(show);
            }
        }
        if (this.mBtnJacRefineEsc != null) {
            this.mBtnJacRefineEsc.Show(show);
        }
        if (this.mBtnChanaWcCamMode != null) {
            this.mBtnChanaWcCamMode.Show(show);
        }
        if (this.mBtnVenuciaWcM50 != null) {
            for (ParamButton btn16 : this.mBtnVenuciaWcM50) {
                btn16.Show(show);
            }
        }
        if (this.mBtnFordWcMode != null) {
            for (ParamButton btn17 : this.mBtnFordWcMode) {
                btn17.Show(show);
            }
        }
        if (this.mBtnSenovaOdMode != null) {
            this.mBtnSenovaOdMode.Show(show);
        }
        if (this.mBtnCheryWcMode != null) {
            for (ParamButton btn18 : this.mBtnCheryWcMode) {
                btn18.Show(show);
            }
        }
        if (this.mBtnCCHfDjDirection != null) {
            this.mBtnCCHfDjDirection[0].Show(show);
            this.mBtnCCHfDjDirection[1].Show(show);
            this.mBtnCCHfDjDirection[2].Show(show);
            this.mBtnCCHfDjDirection[3].Show(show);
        }
        if (this.mBtnCCHfDjMode != null) {
            this.mBtnCCHfDjMode[0].Show(show);
            this.mBtnCCHfDjMode[1].Show(show);
            this.mBtnCCHfDjMode[2].Show(show);
        }
        if (this.mBtnPsaRzcEsc != null) {
            this.mBtnPsaRzcEsc.Show(show);
            this.mBtnPsaRzcMode[0].Show(show);
        }
        if (this.mBtnFrCamera != null) {
            this.mBtnFrCamera.Show(show);
        }
        if (this.mBtnMitSubshiRzcMode != null) {
            this.mBtnMitSubshiRzcMode.Show(show);
        }
        if (this.mBtnRenaultXpCamMode != null) {
            for (ParamButton btn19 : this.mBtnRenaultXpCamMode) {
                btn19.Show(show);
            }
        }
        if (this.mBtnRenaultWcMode != null) {
            for (ParamButton btn20 : this.mBtnRenaultWcMode) {
                btn20.Show(show);
            }
        }
        if (this.mBtnSenovaRzcMode != null) {
            for (ParamButton btn21 : this.mBtnSenovaRzcMode) {
                btn21.Show(show);
            }
        }
        if (this.mBtnChanaODMode != null) {
            this.mBtnChanaODMode.Show(show);
        }
        if (this.mBtnMgGs != null) {
            if (!show) {
                for (ParamButton btn22 : this.mBtnMgGs) {
                    btn22.Show(show);
                }
            } else if (int2Bool(this.mMgGsAvmdata.Func)) {
                if (this.mMgGsAvmdata.Ms == 1) {
                    for (ParamButton btn23 : this.mBtnMgGs) {
                        btn23.Show(true);
                    }
                } else if (this.mMgGsAvmdata.Ms == 0) {
                    this.mBtnMgGs[2].Show(true);
                    this.mBtnMgGs[4].Show(true);
                    this.mBtnMgGs[6].Show(true);
                    this.mBtnMgGs[8].Show(true);
                } else {
                    for (ParamButton btn24 : this.mBtnMgGs) {
                        btn24.Show(false);
                    }
                }
                this.mBtnMgGs[0].Show(show);
                this.mBtnMgGs[1].Show(show);
                this.mBtnMgGs[this.mBtnMgGs.length - 1].Show(show);
            }
        }
        if (this.mBtnLandWindOd != null) {
            for (ParamButton btn25 : this.mBtnLandWindOd) {
                btn25.Show(show);
            }
            if (this.mLandWindOdCarSet.Avm == 1) {
                is2D = true;
            } else {
                is2D = false;
            }
            if (this.mBtnLandWindOd != null) {
                for (int i3 = 1; i3 < this.mBtnLandWindOd.length; i3++) {
                    if (i3 < 6) {
                        if (!is2D) {
                            this.mBtnLandWindOd[i3].Show(false);
                        }
                    } else if (is2D) {
                        this.mBtnLandWindOd[i3].Show(false);
                    }
                }
            }
        }
        if (this.mBtnFengShenAx7Mode != null) {
            for (ParamButton btn26 : this.mBtnFengShenAx7Mode) {
                btn26.Show(show);
            }
        }
        if (this.mBtnMgZsWcMode != null) {
            for (ParamButton btn27 : this.mBtnMgZsWcMode) {
                btn27.Show(show);
            }
        }
        if (this.mBtnBlsuRzcT5 != null) {
            for (ParamButton btn28 : this.mBtnBlsuRzcT5) {
                btn28.Show(show);
            }
        }
        if (this.mBtnMzdRzcMode != null) {
            this.mBtnMzdRzcMode.Show(show);
        }
        if (this.mBtnProscheOdMode != null) {
            for (ParamButton btn29 : this.mBtnProscheOdMode) {
                btn29.Show(show);
            }
        }
        if (this.mBtnJacRefineWcMode != null) {
            for (ParamButton btn30 : this.mBtnJacRefineWcMode) {
                btn30.Show(show);
            }
        }
        if (this.mBtnJacRefineWcStats != null) {
            for (ParamButton btn31 : this.mBtnJacRefineWcStats) {
                btn31.Show(show);
            }
        }
    }

    private void showCCH9View(boolean show) {
        if (show) {
            this.mBtnCCH9Mode[0].Show(true);
            this.mBtnCCH9Mode[1].Show(true);
            this.mBtnCCH9Mode[2].Show(true);
            this.mBtnCCH9Mode[3].Show(false);
            this.mBtnCCH9Mode[4].Show(false);
            this.mBtnCCH9Mode[5].Show(false);
            return;
        }
        this.mBtnCCH9Mode[0].Show(false);
        this.mBtnCCH9Mode[1].Show(false);
        this.mBtnCCH9Mode[2].Show(false);
        this.mBtnCCH9Mode[3].Show(true);
        this.mBtnCCH9Mode[4].Show(true);
        this.mBtnCCH9Mode[5].Show(true);
    }

    public void onClick(View v) {
        boolean z;
        boolean z2;
        boolean z3 = false;
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 99:
                if (this.mfgShowCanBtn) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                this.mfgShowCanBtn = z2;
                ShowCanBtn(this.mfgShowCanBtn);
                return;
            case 100:
                CanJni.HondaDACarSet(64, 1);
                return;
            case 101:
                CanJni.HondaDACarSet(64, 0);
                return;
            case 102:
                CanJni.HondaDACarSet(64, 2);
                return;
            case 103:
                ScreenSet.GetInstance().Show(0);
                return;
            case 104:
                CanDataInfo.FordSet set = new CanDataInfo.FordSet();
                CanJni.FordGetSetup(set);
                if (146 == CanJni.GetCanType()) {
                    CanJni.FordCarSet(171, 19 - (set.Zoom & 1));
                    return;
                } else if (8 == CanJni.GetSubType()) {
                    CanJni.FordCarSet(171, 19 - (set.Zoom & 1));
                    return;
                } else {
                    CanJni.FordCarSet(163, 19 - (set.Zoom & 1));
                    return;
                }
            case 105:
                CanJni.GqcqCamModeSW();
                return;
            case 111:
            case 112:
            case 113:
            case 114:
            case 115:
                CanJni.GqcqCamModeSet((id - 111) + 1);
                return;
            case 121:
                CanJni.CrstourCamModeSet(1);
                return;
            case 122:
                CanJni.CrstourCamModeSet(2);
                return;
            case 131:
                if (CanJni.ToyotaGetGear() == 1) {
                    CanJni.ToyotaCarSet(33, 4);
                    return;
                } else {
                    CanJni.ToyotaCarSet(33, 2);
                    return;
                }
            case 132:
                if (CanJni.ToyotaGetGear() == 1) {
                    CanJni.ToyotaCarSet(33, 7);
                    return;
                } else {
                    CanJni.ToyotaCarSet(33, 1);
                    return;
                }
            case 133:
                if (CanJni.ToyotaGetGear() == 1) {
                    CanJni.ToyotaCarSet(33, 3);
                    return;
                }
                return;
            case 141:
            case 142:
            case 143:
            case 144:
                CanJni.GolfSendCmd(70, id - 141);
                return;
            case 151:
                CanJni.Cs75SetCameraMode(0);
                return;
            case 161:
            case 162:
            case 163:
            case 164:
                CanJni.Tigger7CarVedioSet((id - 161) + 1);
                return;
            case 171:
            case 172:
                CanJni.LifanSetCamMode(id - 171);
                return;
            case 180:
                CanJni.CCH2RevCamSet(0);
                return;
            case 181:
            case 182:
            case 183:
                CanJni.CCH2SetCamMode((id - 181) + 1);
                return;
            case 190:
            case 191:
            case 192:
            case 193:
                CanJni.X80CameraSet((id - 190) + 1);
                return;
            case 200:
            case 201:
            case 202:
            case 203:
                CanJni.GeelyBoyCarCameraSet((id - 200) + 1);
                return;
            case 204:
                CanJni.GeelyBoyCarSet(13, 0);
                return;
            case 300:
            case 301:
            case 302:
            case 303:
                CanJni.VenuciaCarCamChange((id - 300) + 2);
                return;
            case 304:
                CanJni.VenuciaCarCamChange(0);
                return;
            case BTN_TRUMPCHI_GS4_MODE1 /*400*/:
            case BTN_TRUMPCHI_GS4_MODE2 /*401*/:
                CanJni.Gs4CarCamSwitch((id - 400) + 1);
                return;
            case BTN_TRUMPCHI_GS4_MODE3 /*402*/:
            case BTN_TRUMPCHI_GS4_MODE4 /*403*/:
                CanJni.Gs4CarCamSwitch((id - 400) + 5);
                return;
            case BTN_TRUMPCHI_GS4_MODE5 /*404*/:
                CanJni.Gs4CarCamSwitch(0);
                return;
            case 500:
            case BTN_HMS7_MODE2 /*501*/:
            case BTN_HMS7_MODE3 /*502*/:
            case BTN_HMS7_MODE4 /*503*/:
                CanJni.HmS7CarSet((id - 500) + 3);
                return;
            case BTN_HMS7_ESC /*504*/:
                CanJni.HmS7CarSet(2);
                return;
            case BTN_HMS7_HELP_LINE /*505*/:
                if (this.mHmS7Set.Fzxzt == 0) {
                    CanJni.HmS7CarSet(7);
                    return;
                } else {
                    CanJni.HmS7CarSet(8);
                    return;
                }
            case BTN_YG9_XBS_MODE1 /*510*/:
            case BTN_YG9_XBS_MODE2 /*511*/:
            case 512:
                if (96 == CanJni.GetCanType()) {
                    CanJni.Yg9XbsCarRvsSet((id - 510) + 1);
                    return;
                } else if (100 == CanJni.GetCanType()) {
                    CanJni.Yg9XbsCarRvsSet(id - 510);
                    return;
                } else {
                    return;
                }
            case BTN_GEELY_YJX6_MODE1 /*520*/:
            case BTN_GEELY_YJX6_MODE2 /*521*/:
            case BTN_GEELY_YJX6_MODE3 /*522*/:
            case BTN_GEELY_YJX6_MODE4 /*523*/:
                CanJni.GeelyCarCameraSet((id - 520) + 2);
                return;
            case BTN_GEELY_YJX6_ESC /*524*/:
                CanJni.GeelyCarCameraSet(0);
                return;
            case BTN_GEELY_YJX6_3D /*525*/:
                if (this.mGeelyCameraSta.CameraSta == 5) {
                    CanJni.GeelyCarCameraSet(9);
                    return;
                } else {
                    CanJni.GeelyCarCameraSet(8);
                    return;
                }
            case BTN_GEELY_YJX6_GJ /*526*/:
                if (int2Bool(this.mGeelyCameraSta.CameraSet & 128)) {
                    CanJni.GeelyCarCameraSet(7);
                    return;
                } else {
                    CanJni.GeelyCarCameraSet(6);
                    return;
                }
            case BTN_GEELY_YJX6_FXP /*527*/:
                if (int2Bool(this.mGeelyCameraSta.CameraSet & 64)) {
                    CanJni.GeelyCarCameraSet(11);
                    return;
                } else {
                    CanJni.GeelyCarCameraSet(10);
                    return;
                }
            case BTN_CHANA_ALSVINV7_MODE1 /*530*/:
            case BTN_CHANA_ALSVINV7_MODE2 /*531*/:
            case BTN_CHANA_ALSVINV7_MODE3 /*532*/:
                CanJni.AlsvinCarSet((id - 530) + 1, 0);
                return;
            case 540:
                CanJni.NissanCarSet(79, 1);
                return;
            case BTN_NISSAN_XTRAL_RVS_ASSIST2 /*541*/:
                CanJni.NissanCarSet(72, 1);
                return;
            case BTN_NISSAN_XTRAL_RVS_ASSIST3 /*542*/:
                CanJni.NissanCarSet(73, 1);
                return;
            case BTN_NISSAN_XTRAL_RVS_ASSIST4 /*543*/:
                CanJni.NissanCarSet(74, 1);
                return;
            case BTN_NISSAN_XTRAL_RVS_ASSIST5 /*544*/:
                CanJni.NissanCarSet(75, 1);
                return;
            case BTN_NISSAN_XTRAL_RVS_ASSIST6 /*545*/:
                CanJni.NissanCarSet(78, 1);
                return;
            case BTN_NISSAN_XTRAL_RVS_ASSIST7 /*546*/:
                CanJni.NissanCarSet(67, 1);
                return;
            case BTN_NISSAN_XTRAL_RVS_ASSIST8 /*547*/:
                CanJni.NissanCarSet(71, 1);
                return;
            case BTN_NISSAN_XTRAL_RVS_ASSIST9 /*548*/:
                CanJni.NissanCarSet(65, 1);
                return;
            case BTN_NISSAN_XTRAL_RVS_ASSIST10 /*549*/:
                CanJni.NissanCarSet(66, 1);
                return;
            case 550:
                CanJni.NissanCarSet(68, 1);
                return;
            case 551:
                CanJni.NissanCarSet(69, 1);
                return;
            case 552:
                CanJni.NissanCarSet(70, 1);
                return;
            case BTN_TRUMPCHI_GS7_MODE1 /*560*/:
                CanJni.GqcqCs7CameraSet(1, 1, 0, 0);
                return;
            case BTN_TRUMPCHI_GS7_MODE2 /*561*/:
                CanJni.GqcqCs7CameraSet(1, 3, 0, 0);
                return;
            case BTN_TRUMPCHI_GS7_MODE3 /*562*/:
                CanJni.GqcqCs7CameraSet(1, 4, 0, 0);
                return;
            case BTN_TRUMPCHI_GS7_MODE4 /*563*/:
                CanJni.GqcqCs7CameraSet(1, 2, 0, 0);
                return;
            case BTN_TRUMPCHI_GS7_MODE5 /*564*/:
                CanJni.GqcqCs7CameraSet(1, 5, 0, 0);
                return;
            case BTN_TRUMPCHI_GS7_MODE6 /*565*/:
                CanJni.GqcqCs7CameraSet(1, 6, 0, 0);
                return;
            case BTN_TRUMPCHI_GS7_MODE7 /*566*/:
                CanJni.GqcqCs7CameraSet(1, 7, 0, 0);
                return;
            case BTN_TRUMPCHI_GS7_MODE8 /*567*/:
                CanJni.GqcqCs7CameraSet(1, 8, 0, 0);
                return;
            case BTN_CHANA_CS75_MODE1 /*570*/:
            case BTN_CHANA_CS75_MODE2 /*571*/:
            case BTN_CHANA_CS75_MODE3 /*572*/:
            case BTN_CHANA_CS75_MODE4 /*573*/:
            case BTN_CHANA_CS75_MODE5 /*574*/:
                CanJni.ChanaCs75CameraSet((id - 570) + 1);
                return;
            case BTN_CHANA_CS75_MODE6 /*575*/:
                CanJni.ChanaCs75CameraSet(16);
                return;
            case 576:
                CanJni.Cs75SetCameraMode(0);
                return;
            case BTN_CAMERY_2018_MODE1 /*580*/:
                CanJni.ToyotaCarSet(42, 1);
                return;
            case BTN_CAMERY_2018_MODE2 /*581*/:
                CanJni.ToyotaCarSet(41, 1);
                return;
            case BTN_VW_WC_MODE1 /*590*/:
                if (this.mVwWcCamera.Lbzc <= 0) {
                    z3 = true;
                }
                CanJni.VwWcAssistSetCmd(9, z3 ? 1 : 0);
                return;
            case BTN_VW_WC_MODE2 /*591*/:
                if (this.mVwWcCamera.Rkzc <= 0) {
                    z3 = true;
                }
                CanJni.VwWcAssistSetCmd(10, z3 ? 1 : 0);
                return;
            case BTN_VW_WC_MODE3 /*592*/:
                if (this.mVwWcCamera.Ldjy <= 0) {
                    z3 = true;
                }
                CanJni.VwWcAssistSetCmd(11, z3 ? 1 : 0);
                return;
            case 600:
                CanJni.GolfWcCameraSet(1, 255);
                return;
            case BTN_GOLF_WC_MODE2 /*601*/:
                CanJni.GolfWcCameraSet(2, 255);
                return;
            case BTN_GOLF_WC_MODE3 /*602*/:
                CanJni.GolfWcCameraSet(3, 255);
                return;
            case BTN_GOLF_WC_MODE4 /*603*/:
                CanJni.GolfWcCameraSet(9, 255);
                return;
            case BTN_CCH9_MODE1 /*610*/:
                CanJni.CCH6CarSetRzc(20, 7, 0);
                return;
            case BTN_CCH9_MODE2 /*611*/:
                CanJni.CCH6CarSetRzc(20, 8, 0);
                return;
            case BTN_CCH9_MODE3 /*612*/:
                CanJni.CCH6CarSetRzc(20, 1, 0);
                this.isAutoParking = false;
                showCCH9View(this.isAutoParking);
                return;
            case BTN_CCH9_MODE4 /*613*/:
                CanJni.CCH6CarSetRzc(20, 2, 0);
                this.isAutoParking = true;
                showCCH9View(this.isAutoParking);
                return;
            case BTN_CCH9_MODE5 /*614*/:
                CanJni.CCH6CarSetRzc(10, 5, 0);
                return;
            case BTN_CCH9_MODE6 /*615*/:
                CanJni.CCH6CarSetRzc(20, 9, 0);
                return;
            case BTN_CCH9_MODE7 /*616*/:
                CanJni.CCH6CarSetRzc(20, 3, 0);
                return;
            case BTN_CCH9_MODE8 /*617*/:
                CanJni.CCH6CarSetRzc(20, 4, 0);
                return;
            case BTN_CCH9_MODE9 /*618*/:
                CanJni.CCH6CarSetRzc(20, 5, 0);
                return;
            case BTN_CCH9_MODE10 /*619*/:
                CanJni.CCH6CarSetRzc(20, 6, 0);
                return;
            case 620:
                CanJni.CCH6CarSetRzc(10, 1, 0);
                return;
            case 621:
                CanJni.CCH6CarSetRzc(10, 2, 0);
                return;
            case 622:
                CanJni.CCH6CarSetRzc(10, 3, 0);
                return;
            case BTN_CCH9_MODE14 /*623*/:
                CanJni.CCH6CarSetRzc(10, 4, 0);
                return;
            case BTN_SENOVA_SUB_BJ40_MODE3 /*632*/:
                CanJni.SenovaCameraSet(2, 1, 0);
                return;
            case BTN_SENOVA_SUB_BJ40_MODE4 /*633*/:
                CanJni.SenovaCameraSet(2, 2, 0);
                return;
            case BTN_SENOVA_SUB_BJ40_MODE5 /*634*/:
                CanJni.SenovaCameraSet(2, 3, 0);
                return;
            case BTN_LANDWIND_2D3D /*640*/:
                CanJni.LoadWindRzcAvmSet(LandWindNeg(this.mLandwindAvm.Ms));
                return;
            case BTN_LANDWIND_2D_FRONT /*641*/:
                CanJni.LoadWindRzcAvmSet(3);
                return;
            case BTN_LANDWIND_2D_REAR /*642*/:
                CanJni.LoadWindRzcAvmSet(4);
                return;
            case BTN_LANDWIND_2D_LEFT /*643*/:
                CanJni.LoadWindRzcAvmSet(5);
                return;
            case BTN_LANDWIND_2D_RIGHT /*644*/:
                CanJni.LoadWindRzcAvmSet(6);
                return;
            case BTN_LANDWIND_3D_FRONT /*645*/:
                CanJni.LoadWindRzcAvmSet(8);
                return;
            case BTN_LANDWIND_3D_RIGHT_UP /*646*/:
                CanJni.LoadWindRzcAvmSet(9);
                return;
            case BTN_LANDWIND_3D_RIGHT_DOWN /*647*/:
                CanJni.LoadWindRzcAvmSet(10);
                return;
            case BTN_LANDWIND_3D_REAR /*648*/:
                CanJni.LoadWindRzcAvmSet(11);
                return;
            case BTN_LANDWIND_3D_LEFT_DOWN /*649*/:
                CanJni.LoadWindRzcAvmSet(12);
                return;
            case BTN_LANDWIND_3D_LEFT_UP /*650*/:
                CanJni.LoadWindRzcAvmSet(13);
                return;
            case BTN_CC_WC_DIRECTION1 /*700*/:
            case BTN_CC_WC_DIRECTION2 /*701*/:
            case BTN_CC_WC_DIRECTION3 /*702*/:
            case BTN_CC_WC_DIRECTION4 /*703*/:
                CanJni.CcWcGetCameraSet(3, (id + 4) - 700);
                return;
            case 800:
            case 801:
            case 802:
            case 803:
                Log.d(TAG, "BTN_DFFG_DIRECTION = " + id);
                CanJni.DfFgS560CameraSet((id + 3) - 800);
                return;
            case 810:
                CanJni.HondaWcCameraSet(1, 255);
                return;
            case 811:
                CanJni.HondaWcCameraSet(2, 255);
                return;
            case 812:
                CanJni.HondaWcCameraSet(3, 255);
                return;
            case BTN_CS75_WC_MODE /*900*/:
                int sta = this.mCs75CameraSta.Sta + 1;
                if (sta > 2) {
                    sta = 0;
                }
                CanJni.ChanAWcCs75CameraSet(1, sta);
                return;
            case 1001:
            case 1002:
            case BTN_TRUMPCHI_WC_MODE3 /*1003*/:
            case BTN_TRUMPCHI_WC_MODE4 /*1004*/:
            case BTN_TRUMPCHI_WC_MODE5 /*1005*/:
                CanJni.TrumpchiWcCameraSet(7, (id - 1001) + 1);
                return;
            case BTN_TRUMPCHI_WC_MODE6 /*1006*/:
            case BTN_TRUMPCHI_WC_MODE7 /*1007*/:
                CanJni.TrumpchiWcCameraSet(7, (id - 1001) + 2);
                return;
            case BTN_JAC_REFINE_MODE1 /*1100*/:
            case BTN_JAC_REFINE_MODE2 /*1101*/:
            case BTN_JAC_REFINE_MODE3 /*1102*/:
            case BTN_JAC_REFINE_MODE4 /*1103*/:
                CanJni.JacCarAvmSet((id - 1100) + 5);
                return;
            case BTN_JAC_REFINE_3D /*1104*/:
                CanJni.JacCarAvmSet(2);
                return;
            case BTN_JAC_REFINE_2D /*1105*/:
                CanJni.JacCarAvmSet(1);
                return;
            case BTN_JAC_REFINE_FULL /*1106*/:
                CanJni.JacCarAvmSet(3);
                return;
            case BTN_JAC_REFINE_ESC /*1107*/:
                CanJni.JacCarAvmSet(0);
                return;
            case BTN_CCH2WC_MODE1 /*1110*/:
            case BTN_CCH2WC_MODE2 /*1111*/:
            case BTN_CCH2WC_MODE3 /*1112*/:
                CanJni.CcH2WcCameraSet(10, (id - 1110) + 1);
                return;
            case BTN_ZOYTE_MODE1 /*1120*/:
            case BTN_ZOYTE_MODE2 /*1121*/:
            case BTN_ZOYTE_MODE3 /*1122*/:
            case BTN_ZOYTE_MODE4 /*1123*/:
            case BTN_ZOYTE_MODE5 /*1124*/:
                CanJni.ZotyeSr9CameraSet((id - 1120) + 4);
                return;
            case BTN_ZOYTEESC /*1125*/:
                CanJni.ZotyeSr9CameraSet(0);
                return;
            case BTN_X80_2017_MODE1 /*1130*/:
            case BTN_X80_2017_MODE2 /*1131*/:
            case BTN_X80_2017_MODE3 /*1132*/:
            case BTN_X80_2017_MODE4 /*1133*/:
                CanJni.X80WcCameraSet(2, (id - 1130) + 1);
                return;
            case BTN_ZOYTEX5_WC_ESC /*1140*/:
            case BTN_ZOYTEX5_WC_MODE1 /*1141*/:
            case BTN_ZOYTEX5_WC_MODE2 /*1142*/:
            case BTN_ZOYTEX5_WC_MODE3 /*1143*/:
            case BTN_ZOYTEX5_WC_MODE4 /*1144*/:
                CanJni.ZtDmX5CameraSet(3, (id - 1140) + 3);
                return;
            case BTN_ACCORD9_WC_GJ /*1150*/:
                CanJni.HondaWcCameraSet(4, 255);
                return;
            case BTN_ACCORD9_WC_BZ /*1151*/:
                CanJni.HondaWcCameraSet(5, 255);
                return;
            case BTN_ACCORD9_WC_FJ /*1152*/:
                CanJni.HondaWcCameraSet(6, 255);
                return;
            case BTN_BAIC_HSS6_FDJ /*1160*/:
                CanJni.BaicWcS6CameraSet(11, 1);
                return;
            case BTN_BAIC_HSS6_QJQH /*1161*/:
                CanJni.BaicWcS6CameraSet(11, 2);
                return;
            case 1170:
            case BTN_HYUNDAI_XP_FRONT_MODE2 /*1171*/:
            case BTN_HYUNDAI_XP_FRONT_MODE3 /*1172*/:
            case BTN_HYUNDAI_XP_FRONT_MODE4 /*1173*/:
                CanJni.HyundaiXpCameraSet(1, (id - 1170) + 5);
                return;
            case BTN_HYUNDAI_XP_REAR_MODE1 /*1180*/:
            case BTN_HYUNDAI_XP_REAR_MODE2 /*1181*/:
            case BTN_HYUNDAI_XP_REAR_MODE3 /*1182*/:
            case BTN_HYUNDAI_XP_REAR_MODE4 /*1183*/:
                CanJni.HyundaiXpCameraSet(1, (id - 1180) + 1);
                return;
            case BTN_CHANA_WC_MODE /*1200*/:
                CanJni.ChanAWcGetCameraSta(this.mChanaWcCamera);
                int temp = this.mChanaWcCamera.Sta + 1;
                if (temp > 2) {
                    temp = 0;
                }
                CanJni.ChanAWcCarCameraSet(1, temp);
                return;
            case BTN_VENUCIA_WC_M50_MODE1 /*1210*/:
            case BTN_VENUCIA_WC_M50_MODE2 /*1211*/:
            case BTN_VENUCIA_WC_M50_MODE3 /*1212*/:
            case BTN_VENUCIA_WC_M50_MODE4 /*1213*/:
                CanJni.VenucaiWcM50vCameraSet(4, (id - 1210) + 3);
                return;
            case BTN_FORD_WC_GJ /*1220*/:
                if (this.mFordWcCameraData.Gjst <= 0) {
                    z3 = true;
                }
                CanJni.FordWcCarCameraSet(8, z3 ? 1 : 0);
                return;
            case BTN_FORD_WC_FJ /*1221*/:
                if (this.mFordWcCameraData.Fjst <= 0) {
                    z3 = true;
                }
                CanJni.FordWcCarCameraSet(9, z3 ? 1 : 0);
                return;
            case BTN_SENOVA_OD_MODE /*1330*/:
                CanJni.SenovaOdGetCameraSta(this.mSenovaOdCamera);
                int cameraSta = this.mSenovaOdCamera.Sta + 1;
                if (cameraSta > 2) {
                    cameraSta = 0;
                }
                CanJni.SenovaOdCameraSet(1, cameraSta + 1);
                return;
            case BTN_CHERY_WC_MODE1 /*1340*/:
            case BTN_CHERY_WC_MODE2 /*1341*/:
            case BTN_CHERY_WC_MODE3 /*1342*/:
            case BTN_CHERY_WC_MODE4 /*1343*/:
                CanJni.CheryWcCameraSet(4, (id - 1340) + 4);
                return;
            case BTN_PRODOC_MODE1 /*1350*/:
                CanJni.ToyotaCarSet(33, 1);
                return;
            case BTN_PRODOC_MODE2 /*1351*/:
                CanJni.ToyotaCarSet(33, 2);
                return;
            case BTN_PRODOC_MODE3 /*1352*/:
                CanJni.ToyotaCarSet(33, 3);
                return;
            case BTN_PRODOC_MODE4 /*1353*/:
                CanJni.ToyotaCarSet(33, 6);
                return;
            case BTN_CCHfDj_MODE1 /*1360*/:
                CanJni.CcHfDjCameraSet(0);
                return;
            case BTN_CCHfDj_MODE2 /*1361*/:
                CanJni.CcHfDjCameraSet(1);
                return;
            case BTN_CCHfDj_MODE3 /*1362*/:
                CanJni.CcHfDjCameraSet(2);
                return;
            case BTN_CCHfDj_MODE4 /*1363*/:
                CanJni.CcHfDjCameraSet(16);
                return;
            case BTN_CCHfDj_MODE5 /*1364*/:
                CanJni.CcHfDjCameraSet(17);
                return;
            case BTN_CCHfDj_MODE6 /*1365*/:
                CanJni.CcHfDjCameraSet(18);
                return;
            case BTN_CCHfDj_MODE7 /*1366*/:
                CanJni.CcHfDjCameraSet(19);
                return;
            case BTN_DFFG_RZC_FRONT /*1370*/:
            case BTN_DFFG_RZC_REAR /*1371*/:
            case BTN_DFFG_RZC_LEFT /*1372*/:
            case BTN_DFFG_RZC_RIGHT /*1373*/:
                CanJni.DffgRzcAvmCmd((id + 5) - 1370);
                return;
            case BTN_DFFG_RZC_OFF /*1374*/:
                CanJni.DffgRzcAvmCmd(0);
                return;
            case BTN_PSA_RZC_MODE /*1380*/:
                CanJni.PsaRzcGetAvmData(this.m_PsaRzcAvm);
                if (this.m_PsaRzcAvm.Mode == 1) {
                    CanJni.PsaRzcAvmCmd(3);
                    return;
                } else if (this.m_PsaRzcAvm.Mode == 4) {
                    CanJni.PsaRzcAvmCmd(4);
                    return;
                } else if (this.m_PsaRzcAvm.Mode == 3) {
                    CanJni.PsaRzcAvmCmd(5);
                    return;
                } else {
                    CanJni.PsaRzcAvmCmd(2);
                    return;
                }
            case BTN_PSA_RZC_ESC /*1381*/:
                CanJni.PsaRzcAvmCmd(0);
                return;
            case BTN_CC_H6_WC_DIRECTION1 /*1390*/:
            case BTN_CC_H6_WC_DIRECTION2 /*1391*/:
            case BTN_CC_H6_WC_DIRECTION3 /*1392*/:
            case BTN_CC_H6_WC_DIRECTION4 /*1393*/:
                CanJni.CcH6WcCarCameraSet(3, (id - 1390) + 4);
                return;
            case BTN_CC_H6_WC_DIRECTION5 /*1394*/:
                CanJni.CcH6WcCarCameraSet(4, 1);
                return;
            case BTN_BLSU_OD_MODE1 /*1400*/:
            case BTN_BLSU_OD_MODE2 /*1401*/:
            case BTN_BLSU_OD_MODE3 /*1402*/:
            case BTN_BLSU_OD_MODE4 /*1403*/:
                CanJni.BlsuOdAvmSet2((id + 1) - 1400);
                return;
            case BTN_BLSU_OD_3D /*1404*/:
                CanJni.BlsuOdAvmSet(1);
                return;
            case BTN_BLSU_OD_2D /*1405*/:
                CanJni.BlsuOdAvmSet(0);
                return;
            case BTN_HYUNDAI_WC_MODE1 /*1410*/:
            case BTN_HYUNDAI_WC_MODE2 /*1411*/:
            case BTN_HYUNDAI_WC_MODE3 /*1412*/:
            case BTN_HYUNDAI_WC_MODE4 /*1413*/:
            case BTN_HYUNDAI_WC_MODE5 /*1414*/:
            case BTN_HYUNDAI_WC_MODE6 /*1415*/:
            case BTN_HYUNDAI_WC_MODE7 /*1416*/:
            case BTN_HYUNDAI_WC_MODE8 /*1417*/:
            case BTN_HYUNDAI_WC_MODE9 /*1418*/:
            case BTN_HYUNDAI_WC_MODE10 /*1419*/:
                CanJni.HyundaiWcAvmSet(1, (id + 1) - 1410);
                return;
            case BTN_HYUNDAI_RZC_MODE1 /*1430*/:
            case BTN_HYUNDAI_RZC_MODE2 /*1431*/:
            case BTN_HYUNDAI_RZC_MODE3 /*1432*/:
            case BTN_HYUNDAI_RZC_MODE4 /*1433*/:
            case BTN_HYUNDAI_RZC_MODE5 /*1434*/:
            case BTN_HYUNDAI_RZC_MODE6 /*1435*/:
            case BTN_HYUNDAI_RZC_MODE7 /*1436*/:
            case BTN_HYUNDAI_RZC_MODE8 /*1437*/:
            case BTN_HYUNDAI_RZC_MODE9 /*1438*/:
            case BTN_HYUNDAI_RZC_MODE10 /*1439*/:
                CanJni.HyundaiRzcAvmSet((id + 1) - 1430);
                return;
            case BTN_HYUNDAI_RZC_MODE_BTN /*1440*/:
                CanJni.HyundaiRzcCarSet(9, 1);
                return;
            case BTN_MITSUBISHI_RZC_CAMERA /*1460*/:
                CanJni.MitSubishiRzcAvmCmd(1);
                return;
            case BTN_RENAULT_XP_MODE1 /*1470*/:
            case BTN_RENAULT_XP_MODE2 /*1471*/:
            case BTN_RENAULT_XP_MODE3 /*1472*/:
            case BTN_RENAULT_XP_MODE4 /*1473*/:
                CanJni.RenaultCarSet(4, (id - 1470) + 1);
                return;
            case BTN_CHANA_COS1_WC_MODE1 /*1480*/:
            case BTN_CHANA_COS1_WC_MODE2 /*1481*/:
            case BTN_CHANA_COS1_WC_MODE3 /*1482*/:
                CanJni.ChanAWcCos1CarCameraSet(1, id - 1480);
                return;
            case BTN_PORSCHE_LZ_MODE1 /*1490*/:
            case BTN_PORSCHE_LZ_MODE2 /*1491*/:
            case BTN_PORSCHE_LZ_MODE3 /*1492*/:
            case BTN_PORSCHE_LZ_MODE4 /*1493*/:
            case BTN_PORSCHE_LZ_MODE5 /*1494*/:
            case BTN_PORSCHE_LZ_MODE6 /*1495*/:
            case BTN_PORSCHE_LZ_FRONT /*1496*/:
            case BTN_PORSCHE_LZ_DOWN /*1497*/:
            case BTN_PORSCHE_LZ_LEFT /*1498*/:
            case BTN_PORSCHE_LZ_RIGHT /*1499*/:
            case BTN_PORSCHE_LZ_CAR /*1500*/:
                CanJni.PorscheLzAvmSet((id - 1490) + 1);
                return;
            case BTN_PORSCHE_LZ_SHOWCAR /*1501*/:
                if (this.mBtnPorscheLZCar != null) {
                    for (ParamButton btn : this.mBtnPorscheLZCar) {
                        if (btn.isShown()) {
                            z = false;
                        } else {
                            z = true;
                        }
                        btn.Show(z);
                    }
                }
                if (this.mCarBg != null) {
                    CustomImgView customImgView = this.mCarBg;
                    if (!this.mCarBg.isShown()) {
                        z3 = true;
                    }
                    customImgView.Show(z3);
                    return;
                }
                return;
            case BTN_PORSCHE_LZ_ESC /*1502*/:
                CanJni.PorscheLzAvmSet(15);
                return;
            case BTN_RENAULT_WC_MODE1 /*1510*/:
            case BTN_RENAULT_WC_MODE2 /*1511*/:
            case BTN_RENAULT_WC_MODE3 /*1512*/:
            case BTN_RENAULT_WC_MODE4 /*1513*/:
                CanJni.RenaultWcAvmCmd(16, (id - 1510) + 5);
                return;
            case BTN_SENOVA_RZC_MODE1 /*1520*/:
                if (this.mSenovaRzcAvmData.Mode == 0) {
                    CanJni.SenovaRzcAvmMdCmd(2, 2);
                    return;
                } else {
                    CanJni.SenovaRzcAvmMdCmd(2, 1);
                    return;
                }
            case BTN_SENOVA_RZC_MODE2 /*1521*/:
                CanJni.SenovaRzcAvmMdCmd(2, 3);
                return;
            case BTN_SENOVA_RZC_MODE3 /*1522*/:
                CanJni.SenovaRzcAvmMdCmd(2, 5);
                return;
            case BTN_SENOVA_RZC_MODE4 /*1523*/:
                CanJni.SenovaRzcAvmMdCmd(2, 6);
                return;
            case BTN_SENOVA_RZC_MODE5 /*1524*/:
                CanJni.SenovaRzcAvmMdCmd(2, 4);
                return;
            case BTN_SENOVA_RZC_MODE6 /*1525*/:
                if (this.mSenovaRzcAvmData.View == 5 || this.mSenovaRzcAvmData.View == 6 || this.mSenovaRzcAvmData.View == 7 || this.mSenovaRzcAvmData.View == 8) {
                    CanJni.SenovaRzcAvmMdCmd(2, 8);
                    return;
                } else {
                    CanJni.SenovaRzcAvmMdCmd(2, 7);
                    return;
                }
            case BTN_CHANA_OD_MODE /*1530*/:
                CanJni.ChanAOdCarTrackSet(0);
                return;
            case BTN_FORD_RZC_CLOSE /*1540*/:
            case BTN_FORD_RZC_PXBC /*1541*/:
            case BTN_FORD_RZC_CZBC /*1542*/:
            case BTN_FORD_RZC_PXCWBC /*1543*/:
                CanJni.FordRzcCarSet(168, id - 1540, 0);
                return;
            case BTN_LANDWINDOD_2D3D /*1560*/:
                CanJni.LandWindOdCarSet(153, Neg(this.mLandWindOdCarSet.Avm));
                return;
            case BTN_LANDWINDOD_2D_ALL /*1561*/:
                CanJni.LandWindOdCarSet(Can.CAN_CC_WC, 0);
                return;
            case BTN_LANDWINDOD_2D_FRONT /*1562*/:
                CanJni.LandWindOdCarSet(Can.CAN_CC_WC, 1);
                return;
            case BTN_LANDWINDOD_2D_LEFT /*1563*/:
                CanJni.LandWindOdCarSet(Can.CAN_CC_WC, 3);
                return;
            case BTN_LANDWINDOD_2D_RIGHT /*1564*/:
                CanJni.LandWindOdCarSet(Can.CAN_CC_WC, 4);
                return;
            case BTN_LANDWINDOD_2D_REAR /*1565*/:
                CanJni.LandWindOdCarSet(Can.CAN_CC_WC, 2);
                return;
            case BTN_LANDWINDOD_3D_ALL /*1566*/:
                CanJni.LandWindOdCarSet(Can.CAN_DFFG_S560, 0);
                return;
            case BTN_LANDWINDOD_3D_FRONT /*1567*/:
                CanJni.LandWindOdCarSet(Can.CAN_DFFG_S560, 1);
                return;
            case BTN_LANDWINDOD_3D_LEFT_UP /*1568*/:
                CanJni.LandWindOdCarSet(Can.CAN_DFFG_S560, 2);
                return;
            case BTN_LANDWINDOD_3D_RIGHT_UP /*1569*/:
                CanJni.LandWindOdCarSet(Can.CAN_DFFG_S560, 3);
                return;
            case BTN_LANDWINDOD_3D_LEFT_DOWN /*1570*/:
                CanJni.LandWindOdCarSet(Can.CAN_DFFG_S560, 4);
                return;
            case BTN_LANDWINDOD_3D_RIGHT_DOWN /*1571*/:
                CanJni.LandWindOdCarSet(Can.CAN_DFFG_S560, 5);
                return;
            case BTN_LANDWINDOD_3D_REAR /*1572*/:
                CanJni.LandWindOdCarSet(Can.CAN_DFFG_S560, 6);
                return;
            case BTN_FENGSHEN_AX7_MODE_UP /*1600*/:
            case BTN_FENGSHEN_AX7_MODE_LEFT /*1601*/:
            case BTN_FENGSHEN_AX7_MODE_RIGHT /*1602*/:
            case BTN_FENGSHEN_AX7_MODE_DN /*1603*/:
                CanJni.DfFengShenRzcAvmCmd((id - 1600) + 2);
                return;
            case BTN_FENGSHEN_AX7_MODE_FULL /*1604*/:
                if (this.mFengShenAvm.Sta < 5 || this.mFengShenAvm.Sta > 8) {
                    CanJni.DfFengShenRzcAvmCmd(6);
                    return;
                } else {
                    CanJni.DfFengShenRzcAvmCmd(7);
                    return;
                }
            case BTN_FENGSHEN_AX7_MODE_EXIT /*1605*/:
                CanJni.DfFengShenRzcAvmCmd(0);
                return;
            case BTN_MGZS_WC_MODE1 /*1610*/:
            case BTN_MGZS_WC_MODE2 /*1611*/:
            case BTN_MGZS_WC_MODE3 /*1612*/:
            case BTN_MGZS_WC_MODE4 /*1613*/:
            case BTN_MGZS_WC_MODE5 /*1614*/:
                CanJni.MgZsWcCarAvmSet(16, (id - 1610) + 5);
                return;
            case BTN_MG_2D /*1620*/:
            case BTN_MG_3D /*1621*/:
            case BTN_MG_QS /*1622*/:
            case BTN_MG_QZS /*1623*/:
            case BTN_MG_ZS /*1624*/:
            case BTN_MG_HZS /*1625*/:
            case BTN_MG_HS /*1626*/:
            case BTN_MG_HYS /*1627*/:
            case BTN_MG_YS /*1628*/:
            case BTN_MG_QYS /*1629*/:
                CanJni.MGGSAvmSet((id - 1620) + 2);
                return;
            case BTN_MG_ESC /*1630*/:
                CanJni.MGGSAvmSet(0);
                return;
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST1 /*1640*/:
                CanJni.NissanCarSet(79, 1);
                return;
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST2 /*1641*/:
                CanJni.NissanCarSet(72, 1);
                return;
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST3 /*1642*/:
                CanJni.NissanCarSet(73, 1);
                return;
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST4 /*1643*/:
                CanJni.NissanCarSet(74, 1);
                return;
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST5 /*1644*/:
                CanJni.NissanCarSet(75, 1);
                return;
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST6 /*1645*/:
                CanJni.NissanCarSet(78, 1);
                return;
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST7 /*1646*/:
                CanJni.NissanCarSet(67, 1);
                return;
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST8 /*1647*/:
                CanJni.NissanCarSet(71, 1);
                return;
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST9 /*1648*/:
                CanJni.NissanCarSet(65, 1);
                return;
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST10 /*1649*/:
                CanJni.NissanCarSet(66, 1);
                return;
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST11 /*1650*/:
                CanJni.NissanCarSet(68, 1);
                return;
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST12 /*1651*/:
                CanJni.NissanCarSet(69, 1);
                return;
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST13 /*1652*/:
                CanJni.NissanCarSet(70, 1);
                return;
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST14 /*1653*/:
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST15 /*1654*/:
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST16 /*1655*/:
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST17 /*1656*/:
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST18 /*1657*/:
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST19 /*1658*/:
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST20 /*1659*/:
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST21 /*1660*/:
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST22 /*1661*/:
            case BTN_NISSAN_XTRAL_RZC_RVS_ASSIST23 /*1662*/:
                CanJni.NissanCarSet((id - 1653) + 97, 1);
                return;
            case BTN_BLSU_RZC_T5_2D /*1670*/:
                CanJni.BlsuT5CarAvmCmd(1, 6, 0);
                return;
            case BTN_BLSU_RZC_T5_3D /*1671*/:
                CanJni.BlsuT5CarAvmCmd(1, 5, 0);
                return;
            case BTN_BLSU_RZC_T5_RETURN /*1672*/:
                CanJni.BlsuT5CarAvmCmd(1, 8, 0);
                return;
            case BTN_BLSU_RZC_T5_FULL /*1673*/:
                CanJni.BlsuT5CarAvmCmd(1, 7, 0);
                return;
            case BTN_BLSU_RZC_T5_UP /*1674*/:
                CanJni.BlsuT5CarAvmCmd(1, 1, 0);
                return;
            case BTN_BLSU_RZC_T5_DN /*1675*/:
                CanJni.BlsuT5CarAvmCmd(1, 2, 0);
                return;
            case BTN_BLSU_RZC_T5_L /*1676*/:
                CanJni.BlsuT5CarAvmCmd(1, 3, 0);
                return;
            case BTN_BLSU_RZC_T5_R /*1677*/:
                CanJni.BlsuT5CarAvmCmd(1, 4, 0);
                return;
            case BTN_MZD_RZC_MODE /*1680*/:
                CanJni.MzdRzcCarAvmCmd(1, 1);
                return;
            case BTN_PORSCHE_OD_MODE1 /*1690*/:
            case BTN_PORSCHE_OD_MODE2 /*1691*/:
            case BTN_PORSCHE_OD_MODE3 /*1692*/:
            case BTN_PORSCHE_OD_MODE4 /*1693*/:
            case BTN_PORSCHE_OD_MODE5 /*1694*/:
                CanJni.PorscheOdAvmCmd((id - 1690) + 2);
                return;
            case BTN_PORSCHE_OD_MODE6 /*1695*/:
                CanJni.PorscheOdAvmCmd(1);
                return;
            case BTN_PORSCHE_OD_MODE7 /*1696*/:
                CanJni.PorscheOdAvmCmd(0);
                return;
            case BTN_JAC_REFINE_WC_MODE1 /*1700*/:
            case BTN_JAC_REFINE_WC_MODE2 /*1701*/:
            case BTN_JAC_REFINE_WC_MODE3 /*1702*/:
            case BTN_JAC_REFINE_WC_MODE4 /*1703*/:
                CanJni.JACRefineWcAvmCmd(3, (id - 1700) + 4);
                return;
            case BTN_JAC_REFINE_WC_3D /*1704*/:
                CanJni.JACRefineWcAvmCmd(4, 2);
                return;
            case BTN_JAC_REFINE_WC_2D /*1705*/:
                CanJni.JACRefineWcAvmCmd(4, 1);
                return;
            case BTN_JAC_REFINE_WC_ESC /*1707*/:
                CanJni.JACRefineWcAvmCmd(1, 0);
                return;
            default:
                return;
        }
    }

    public int LandWindNeg(int val) {
        if (val == 0) {
            return 2;
        }
        return 1;
    }

    public int Neg(int val) {
        if (val == 0) {
            return 1;
        }
        return 0;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x06f8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(android.view.View r14, android.view.MotionEvent r15) {
        /*
            r13 = this;
            r12 = 1151336448(0x44a00000, float:1280.0)
            r11 = 1144258560(0x44340000, float:720.0)
            r10 = 1149239296(0x44800000, float:1024.0)
            r9 = 0
            r8 = 1
            r1 = 0
            r2 = 0
            java.lang.Boolean r3 = r13.IsTuobuAvm()
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L_0x010d
            int r3 = com.ts.main.common.MainSet.GetScreenType()
            r4 = 5
            if (r3 != r4) goto L_0x0093
            float r3 = r15.getX()
            int r3 = (int) r3
            int r3 = r3 * 4096
            int r1 = r3 / 1280
            float r3 = r15.getY()
            int r3 = (int) r3
            int r3 = r3 * 4096
            int r2 = r3 / 480
            java.lang.String r3 = "CanCameraActivity"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "IsTuobuAvm event.getX() = "
            r4.<init>(r5)
            java.lang.StringBuilder r4 = r4.append(r1)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r3, r4)
            java.lang.String r3 = "CanCameraActivity"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "IsTuobuAvm event.getY() = "
            r4.<init>(r5)
            java.lang.StringBuilder r4 = r4.append(r2)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r3, r4)
            int r3 = r15.getAction()
            if (r3 != 0) goto L_0x0081
            int r3 = r1 >> 8
            r4 = r1 & 255(0xff, float:3.57E-43)
            int r5 = r2 >> 8
            r5 = r5 | 128(0x80, float:1.794E-43)
            r6 = r2 & 255(0xff, float:3.57E-43)
            com.yyw.ts70xhw.Mcu.SendxyTouch(r3, r4, r5, r6)
        L_0x006c:
            java.lang.Object r3 = r14.getTag()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r0 = r3.intValue()
            int r3 = com.lgb.canmodule.CanJni.GetCanType()
            switch(r3) {
                case 5: goto L_0x0554;
                case 17: goto L_0x024a;
                case 49: goto L_0x0347;
                case 93: goto L_0x021b;
                case 110: goto L_0x06a2;
                case 128: goto L_0x03da;
                case 140: goto L_0x0438;
                case 144: goto L_0x02b8;
                case 149: goto L_0x0481;
                case 156: goto L_0x0368;
                case 180: goto L_0x040e;
                case 198: goto L_0x0669;
                case 199: goto L_0x05e1;
                case 200: goto L_0x03bc;
                case 210: goto L_0x0623;
                case 288: goto L_0x059b;
                default: goto L_0x007d;
            }
        L_0x007d:
            switch(r0) {
                case 1450: goto L_0x06f8;
                default: goto L_0x0080;
            }
        L_0x0080:
            return r9
        L_0x0081:
            int r3 = r15.getAction()
            if (r3 != r8) goto L_0x006c
            int r3 = r1 >> 8
            r4 = r1 & 255(0xff, float:3.57E-43)
            int r5 = r2 >> 8
            r6 = r2 & 255(0xff, float:3.57E-43)
            com.yyw.ts70xhw.Mcu.SendxyTouch(r3, r4, r5, r6)
            goto L_0x006c
        L_0x0093:
            float r3 = r15.getX()
            r4 = 1145044992(0x44400000, float:768.0)
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 > 0) goto L_0x006c
            float r3 = r15.getY()
            r4 = 1141243904(0x44060000, float:536.0)
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 > 0) goto L_0x006c
            float r3 = r15.getX()
            int r3 = (int) r3
            int r3 = r3 * 4096
            int r1 = r3 / 768
            float r3 = r15.getY()
            int r3 = (int) r3
            int r3 = r3 * 4096
            int r2 = r3 / 536
            java.lang.String r3 = "CanCameraActivity"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "IsTuobuAvm event.getX() = "
            r4.<init>(r5)
            java.lang.StringBuilder r4 = r4.append(r1)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r3, r4)
            java.lang.String r3 = "CanCameraActivity"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "IsTuobuAvm event.getY() = "
            r4.<init>(r5)
            java.lang.StringBuilder r4 = r4.append(r2)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r3, r4)
            int r3 = r15.getAction()
            if (r3 != 0) goto L_0x00fa
            int r3 = r1 >> 8
            r4 = r1 & 255(0xff, float:3.57E-43)
            int r5 = r2 >> 8
            r5 = r5 | 128(0x80, float:1.794E-43)
            r6 = r2 & 255(0xff, float:3.57E-43)
            com.yyw.ts70xhw.Mcu.SendxyTouch(r3, r4, r5, r6)
            goto L_0x006c
        L_0x00fa:
            int r3 = r15.getAction()
            if (r3 != r8) goto L_0x006c
            int r3 = r1 >> 8
            r4 = r1 & 255(0xff, float:3.57E-43)
            int r5 = r2 >> 8
            r6 = r2 & 255(0xff, float:3.57E-43)
            com.yyw.ts70xhw.Mcu.SendxyTouch(r3, r4, r5, r6)
            goto L_0x006c
        L_0x010d:
            int r3 = com.lgb.canmodule.CanJni.GetCanFsTp()
            r4 = 79
            if (r3 != r4) goto L_0x0168
            float r3 = r15.getX()
            int r3 = (int) r3
            int r3 = r3 * 4096
            int r1 = r3 / 1024
            float r3 = r15.getY()
            int r3 = (int) r3
            int r3 = r3 * 4096
            int r2 = r3 / 600
            java.lang.String r3 = "CanCameraActivity"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "IsTuobuAvm event.getX() = "
            r4.<init>(r5)
            java.lang.StringBuilder r4 = r4.append(r1)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r3, r4)
            java.lang.String r3 = "CanCameraActivity"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "IsTuobuAvm event.getY() = "
            r4.<init>(r5)
            java.lang.StringBuilder r4 = r4.append(r2)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r3, r4)
            int r3 = r15.getAction()
            if (r3 != 0) goto L_0x006c
            int r3 = r1 >> 8
            r4 = r1 & 255(0xff, float:3.57E-43)
            int r5 = r2 >> 8
            r5 = r5 | 128(0x80, float:1.794E-43)
            r6 = r2 & 255(0xff, float:3.57E-43)
            com.yyw.ts70xhw.Mcu.SendxyTouch(r3, r4, r5, r6)
            goto L_0x006c
        L_0x0168:
            int r3 = r15.getAction()
            if (r3 != 0) goto L_0x006c
            com.ts.main.common.MainSet r3 = com.ts.main.common.MainSet.GetInstance()
            boolean r3 = r3.IsDIAO()
            if (r3 == 0) goto L_0x01c9
            r3 = 252(0xfc, float:3.53E-43)
            r4 = 3
            float r5 = r15.getX()
            r6 = 1082130432(0x40800000, float:4.0)
            float r5 = r5 / r6
            int r5 = (int) r5
            byte r5 = (byte) r5
            float r6 = r15.getY()
            r7 = 1107296256(0x42000000, float:32.0)
            float r6 = r6 * r7
            r7 = 1117126656(0x42960000, float:75.0)
            float r6 = r6 / r7
            int r6 = (int) r6
            byte r6 = (byte) r6
            com.yyw.ts70xhw.Mcu.SendxyTouch(r3, r4, r5, r6)
            java.lang.String r3 = "CanCameraActivity"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "onTouch event.getX() = "
            r4.<init>(r5)
            float r5 = r15.getX()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r3, r4)
            java.lang.String r3 = "CanCameraActivity"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "onTouch event.getY() = "
            r4.<init>(r5)
            float r5 = r15.getY()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r3, r4)
            goto L_0x006c
        L_0x01c9:
            r3 = 170(0xaa, float:2.38E-43)
            r4 = 85
            float r5 = r15.getX()
            r6 = 1082130432(0x40800000, float:4.0)
            float r5 = r5 / r6
            int r5 = (int) r5
            byte r5 = (byte) r5
            float r6 = r15.getY()
            r7 = 1107296256(0x42000000, float:32.0)
            float r6 = r6 * r7
            r7 = 1117126656(0x42960000, float:75.0)
            float r6 = r6 / r7
            int r6 = (int) r6
            byte r6 = (byte) r6
            com.yyw.ts70xhw.Mcu.SendxyTouch(r3, r4, r5, r6)
            java.lang.String r3 = "CanCameraActivity"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "onTouch event.getX() = "
            r4.<init>(r5)
            float r5 = r15.getX()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r3, r4)
            java.lang.String r3 = "CanCameraActivity"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "onTouch event.getY() = "
            r4.<init>(r5)
            float r5 = r15.getY()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r3, r4)
            goto L_0x006c
        L_0x021b:
            int r3 = com.lgb.canmodule.CanJni.GetSubType()
            if (r8 != r3) goto L_0x007d
            int r3 = r15.getAction()
            if (r8 != r3) goto L_0x007d
            switch(r0) {
                case 550: goto L_0x022c;
                case 551: goto L_0x0233;
                case 552: goto L_0x023a;
                case 553: goto L_0x0242;
                default: goto L_0x022a;
            }
        L_0x022a:
            goto L_0x007d
        L_0x022c:
            r3 = 64
            com.lgb.canmodule.CanJni.BlsuT5CamSet(r3, r9)
            goto L_0x007d
        L_0x0233:
            r3 = 64
            com.lgb.canmodule.CanJni.BlsuT5CamSet(r3, r8)
            goto L_0x007d
        L_0x023a:
            r3 = 64
            r4 = 2
            com.lgb.canmodule.CanJni.BlsuT5CamSet(r3, r4)
            goto L_0x007d
        L_0x0242:
            r3 = 64
            r4 = 3
            com.lgb.canmodule.CanJni.BlsuT5CamSet(r3, r4)
            goto L_0x007d
        L_0x024a:
            r3 = 8
            int r4 = com.lgb.canmodule.CanJni.GetSubType()
            if (r3 != r4) goto L_0x007d
            int r3 = com.ts.main.common.MainSet.GetScreenType()
            r4 = 10
            if (r3 != r4) goto L_0x02a7
            float r3 = r15.getX()
            int r1 = (int) r3
            float r3 = r15.getY()
            int r2 = (int) r3
        L_0x0264:
            java.lang.String r3 = "CanCameraActivity"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "onTouch TRUMPCHI_SUB_GS7.getX() = "
            r4.<init>(r5)
            java.lang.StringBuilder r4 = r4.append(r1)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r3, r4)
            java.lang.String r3 = "CanCameraActivity"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "onTouch TRUMPCHI_SUB_GS7.getY() = "
            r4.<init>(r5)
            java.lang.StringBuilder r4 = r4.append(r2)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r3, r4)
            r3 = 900(0x384, float:1.261E-42)
            if (r1 <= r3) goto L_0x007d
            r3 = 2
            int r4 = r2 >> 4
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r5 = r2 << 4
            int r6 = r1 >> 8
            r6 = r6 & 255(0xff, float:3.57E-43)
            r5 = r5 | r6
            r6 = r1 & 255(0xff, float:3.57E-43)
            com.lgb.canmodule.CanJni.GqcqCs7CameraSet(r3, r4, r5, r6)
            goto L_0x007d
        L_0x02a7:
            float r3 = r15.getX()
            float r3 = r3 * r12
            float r3 = r3 / r10
            int r1 = (int) r3
            float r3 = r15.getY()
            float r3 = r3 * r11
            r4 = 1142292480(0x44160000, float:600.0)
            float r3 = r3 / r4
            int r2 = (int) r3
            goto L_0x0264
        L_0x02b8:
            r3 = 2
            int r4 = com.lgb.canmodule.CanJni.GetSubType()
            if (r3 == r4) goto L_0x02ed
            int r3 = com.ts.main.common.MainSet.GetScreenType()
            r4 = 10
            if (r3 != r4) goto L_0x02dc
            float r3 = r15.getX()
            float r3 = r3 * r10
            float r3 = r3 / r12
            int r1 = (int) r3
            float r3 = r15.getY()
            r4 = 1148846080(0x447a0000, float:1000.0)
            float r3 = r3 * r4
            float r3 = r3 / r11
            int r2 = (int) r3
        L_0x02d7:
            com.lgb.canmodule.CanJni.ToyotaWcTouchCmd(r8, r1, r2)
            goto L_0x007d
        L_0x02dc:
            float r3 = r15.getX()
            int r1 = (int) r3
            float r3 = r15.getY()
            r4 = 1148846080(0x447a0000, float:1000.0)
            float r3 = r3 * r4
            r4 = 1142292480(0x44160000, float:600.0)
            float r3 = r3 / r4
            int r2 = (int) r3
            goto L_0x02d7
        L_0x02ed:
            java.lang.String r3 = "CanCameraActivity"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "onTouch CAN_TOYOTA_WC id= "
            r4.<init>(r5)
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r3, r4)
            int r3 = r15.getAction()
            if (r3 != 0) goto L_0x0325
            switch(r0) {
                case 620: goto L_0x030e;
                case 621: goto L_0x0315;
                case 622: goto L_0x031d;
                default: goto L_0x030c;
            }
        L_0x030c:
            goto L_0x007d
        L_0x030e:
            r3 = 255(0xff, float:3.57E-43)
            com.lgb.canmodule.CanJni.ToyotaWcCameraSet(r3, r8, r8)
            goto L_0x007d
        L_0x0315:
            r3 = 255(0xff, float:3.57E-43)
            r4 = 3
            com.lgb.canmodule.CanJni.ToyotaWcCameraSet(r3, r4, r8)
            goto L_0x007d
        L_0x031d:
            r3 = 255(0xff, float:3.57E-43)
            r4 = 4
            com.lgb.canmodule.CanJni.ToyotaWcCameraSet(r3, r4, r8)
            goto L_0x007d
        L_0x0325:
            int r3 = r15.getAction()
            if (r3 != r8) goto L_0x007d
            switch(r0) {
                case 620: goto L_0x0330;
                case 621: goto L_0x0337;
                case 622: goto L_0x033f;
                default: goto L_0x032e;
            }
        L_0x032e:
            goto L_0x007d
        L_0x0330:
            r3 = 255(0xff, float:3.57E-43)
            com.lgb.canmodule.CanJni.ToyotaWcCameraSet(r3, r8, r9)
            goto L_0x007d
        L_0x0337:
            r3 = 255(0xff, float:3.57E-43)
            r4 = 3
            com.lgb.canmodule.CanJni.ToyotaWcCameraSet(r3, r4, r9)
            goto L_0x007d
        L_0x033f:
            r3 = 255(0xff, float:3.57E-43)
            r4 = 4
            com.lgb.canmodule.CanJni.ToyotaWcCameraSet(r3, r4, r9)
            goto L_0x007d
        L_0x0347:
            r3 = 4
            int r4 = com.lgb.canmodule.CanJni.GetSubType()
            if (r3 != r4) goto L_0x007d
            r3 = 631(0x277, float:8.84E-43)
            if (r0 != r3) goto L_0x007d
            int r3 = r15.getAction()
            if (r8 != r3) goto L_0x035d
            com.lgb.canmodule.CanJni.SenovaCameraSet(r8, r9, r9)
            goto L_0x007d
        L_0x035d:
            int r3 = r15.getAction()
            if (r3 != 0) goto L_0x007d
            com.lgb.canmodule.CanJni.SenovaCameraSet(r8, r8, r9)
            goto L_0x007d
        L_0x0368:
            r3 = 4
            int r4 = com.lgb.canmodule.CanJni.GetSubType()
            if (r3 == r4) goto L_0x0386
            r3 = 5
            int r4 = com.lgb.canmodule.CanJni.GetSubType()
            if (r3 == r4) goto L_0x0386
            r3 = 10
            int r4 = com.lgb.canmodule.CanJni.GetSubType()
            if (r3 == r4) goto L_0x0386
            r3 = 11
            int r4 = com.lgb.canmodule.CanJni.GetSubType()
            if (r3 != r4) goto L_0x007d
        L_0x0386:
            int r3 = com.ts.main.common.MainSet.GetScreenType()
            r4 = 10
            if (r3 != r4) goto L_0x03a6
            float r3 = r15.getX()
            r4 = 1153630208(0x44c30000, float:1560.0)
            float r3 = r3 * r4
            float r3 = r3 / r12
            int r1 = (int) r3
            float r3 = r15.getY()
            r4 = 1156415488(0x44ed8000, float:1900.0)
            float r3 = r3 * r4
            float r3 = r3 / r11
            int r2 = (int) r3
        L_0x03a1:
            com.lgb.canmodule.CanJni.HondaWcCarTouchCmd(r8, r1, r2)
            goto L_0x007d
        L_0x03a6:
            float r3 = r15.getX()
            r4 = 1153630208(0x44c30000, float:1560.0)
            float r3 = r3 * r4
            float r3 = r3 / r10
            int r1 = (int) r3
            float r3 = r15.getY()
            r4 = 1156415488(0x44ed8000, float:1900.0)
            float r3 = r3 * r4
            r4 = 1142292480(0x44160000, float:600.0)
            float r3 = r3 / r4
            int r2 = (int) r3
            goto L_0x03a1
        L_0x03bc:
            r3 = 1190(0x4a6, float:1.668E-42)
            if (r0 != r3) goto L_0x007d
            int r3 = r15.getAction()
            if (r8 != r3) goto L_0x03cd
            r3 = 16
            com.lgb.canmodule.CanJni.BydDjAcSet(r3, r9)
            goto L_0x007d
        L_0x03cd:
            int r3 = r15.getAction()
            if (r3 != 0) goto L_0x007d
            r3 = 16
            com.lgb.canmodule.CanJni.BydDjAcSet(r3, r8)
            goto L_0x007d
        L_0x03da:
            int r3 = com.lgb.canmodule.CanJni.GetSubType()
            if (r8 == r3) goto L_0x007d
            int r3 = com.ts.main.common.MainSet.GetScreenType()
            r4 = 10
            if (r3 != r4) goto L_0x03fd
            float r3 = r15.getX()
            float r3 = r3 * r10
            float r3 = r3 / r12
            int r1 = (int) r3
            float r3 = r15.getY()
            r4 = 1148846080(0x447a0000, float:1000.0)
            float r3 = r3 * r4
            float r3 = r3 / r11
            int r2 = (int) r3
        L_0x03f8:
            com.lgb.canmodule.CanJni.ToyotaRzcTouchCmd(r8, r1, r2)
            goto L_0x007d
        L_0x03fd:
            float r3 = r15.getX()
            int r1 = (int) r3
            float r3 = r15.getY()
            r4 = 1148846080(0x447a0000, float:1000.0)
            float r3 = r3 * r4
            r4 = 1142292480(0x44160000, float:600.0)
            float r3 = r3 / r4
            int r2 = (int) r3
            goto L_0x03f8
        L_0x040e:
            r3 = 4
            int r4 = com.lgb.canmodule.CanJni.GetSubType()
            if (r3 == r4) goto L_0x041d
            int r3 = com.lgb.canmodule.CanJni.GetSubType()
            r4 = 8
            if (r3 != r4) goto L_0x007d
        L_0x041d:
            float r3 = r15.getX()
            int r1 = (int) r3
            float r3 = r15.getY()
            int r2 = (int) r3
            r3 = 1024(0x400, float:1.435E-42)
            if (r1 > r3) goto L_0x0080
            if (r1 < 0) goto L_0x0080
            if (r2 < 0) goto L_0x0080
            r3 = 600(0x258, float:8.41E-43)
            if (r2 > r3) goto L_0x0080
            com.lgb.canmodule.CanJni.CheryWcTouchSet(r8, r1, r2)
            goto L_0x007d
        L_0x0438:
            int r3 = com.ts.can.benc.withcd.CanBencWithCDCarFuncActivity.RvsMode()
            r4 = 2
            if (r3 != r4) goto L_0x007d
            int r3 = com.ts.main.common.MainSet.GetScreenType()
            r4 = 9
            if (r3 != r4) goto L_0x0464
            float r3 = r15.getX()
            float r3 = r3 * r12
            r4 = 1156579328(0x44f00000, float:1920.0)
            float r3 = r3 / r4
            int r1 = (int) r3
            float r3 = r15.getY()
            r4 = 1139802112(0x43f00000, float:480.0)
            float r3 = r3 * r4
            float r3 = r3 / r11
            int r2 = (int) r3
        L_0x0459:
            int r3 = r15.getAction()
            if (r8 != r3) goto L_0x046f
            com.lgb.canmodule.CanJni.BencZmytCameraTouchCmd(r9, r1, r2)
            goto L_0x007d
        L_0x0464:
            float r3 = r15.getX()
            int r1 = (int) r3
            float r3 = r15.getY()
            int r2 = (int) r3
            goto L_0x0459
        L_0x046f:
            int r3 = r15.getAction()
            if (r3 == 0) goto L_0x047c
            r3 = 2
            int r4 = r15.getAction()
            if (r3 != r4) goto L_0x007d
        L_0x047c:
            com.lgb.canmodule.CanJni.BencZmytCameraTouchCmd(r8, r1, r2)
            goto L_0x007d
        L_0x0481:
            int r3 = com.lgb.canmodule.CanJni.GetSubType()
            r4 = 4
            if (r3 != r4) goto L_0x007d
            int r3 = r15.getAction()
            if (r8 != r3) goto L_0x04ee
            switch(r0) {
                case 540: goto L_0x0493;
                case 541: goto L_0x049a;
                case 542: goto L_0x04a1;
                case 543: goto L_0x04a8;
                case 544: goto L_0x04af;
                case 545: goto L_0x04b6;
                case 546: goto L_0x04bd;
                case 547: goto L_0x04c4;
                case 548: goto L_0x04cb;
                case 549: goto L_0x04d2;
                case 550: goto L_0x04d9;
                case 551: goto L_0x04e0;
                case 552: goto L_0x04e7;
                default: goto L_0x0491;
            }
        L_0x0491:
            goto L_0x007d
        L_0x0493:
            r3 = 29
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r9)
            goto L_0x007d
        L_0x049a:
            r3 = 24
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r9)
            goto L_0x007d
        L_0x04a1:
            r3 = 25
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r9)
            goto L_0x007d
        L_0x04a8:
            r3 = 26
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r9)
            goto L_0x007d
        L_0x04af:
            r3 = 27
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r9)
            goto L_0x007d
        L_0x04b6:
            r3 = 28
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r9)
            goto L_0x007d
        L_0x04bd:
            r3 = 19
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r9)
            goto L_0x007d
        L_0x04c4:
            r3 = 23
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r9)
            goto L_0x007d
        L_0x04cb:
            r3 = 17
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r9)
            goto L_0x007d
        L_0x04d2:
            r3 = 18
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r9)
            goto L_0x007d
        L_0x04d9:
            r3 = 21
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r9)
            goto L_0x007d
        L_0x04e0:
            r3 = 20
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r9)
            goto L_0x007d
        L_0x04e7:
            r3 = 22
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r9)
            goto L_0x007d
        L_0x04ee:
            int r3 = r15.getAction()
            if (r3 != 0) goto L_0x007d
            switch(r0) {
                case 540: goto L_0x04f9;
                case 541: goto L_0x0500;
                case 542: goto L_0x0507;
                case 543: goto L_0x050e;
                case 544: goto L_0x0515;
                case 545: goto L_0x051c;
                case 546: goto L_0x0523;
                case 547: goto L_0x052a;
                case 548: goto L_0x0531;
                case 549: goto L_0x0538;
                case 550: goto L_0x053f;
                case 551: goto L_0x0546;
                case 552: goto L_0x054d;
                default: goto L_0x04f7;
            }
        L_0x04f7:
            goto L_0x007d
        L_0x04f9:
            r3 = 29
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r8)
            goto L_0x007d
        L_0x0500:
            r3 = 24
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r8)
            goto L_0x007d
        L_0x0507:
            r3 = 25
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r8)
            goto L_0x007d
        L_0x050e:
            r3 = 26
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r8)
            goto L_0x007d
        L_0x0515:
            r3 = 27
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r8)
            goto L_0x007d
        L_0x051c:
            r3 = 28
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r8)
            goto L_0x007d
        L_0x0523:
            r3 = 19
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r8)
            goto L_0x007d
        L_0x052a:
            r3 = 23
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r8)
            goto L_0x007d
        L_0x0531:
            r3 = 17
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r8)
            goto L_0x007d
        L_0x0538:
            r3 = 18
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r8)
            goto L_0x007d
        L_0x053f:
            r3 = 21
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r8)
            goto L_0x007d
        L_0x0546:
            r3 = 20
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r8)
            goto L_0x007d
        L_0x054d:
            r3 = 22
            com.lgb.canmodule.CanJni.NissanWcCameryKey(r3, r8)
            goto L_0x007d
        L_0x0554:
            int r3 = com.lgb.canmodule.CanJni.GetSubType()
            r4 = 13
            if (r3 != r4) goto L_0x007d
            int r3 = com.ts.main.common.MainSet.GetScreenType()
            r4 = 10
            if (r3 != r4) goto L_0x057f
            float r3 = r15.getX()
            float r3 = r3 * r10
            float r3 = r3 / r12
            int r1 = (int) r3
            float r3 = r15.getY()
            r4 = 1148846080(0x447a0000, float:1000.0)
            float r3 = r3 * r4
            float r3 = r3 / r11
            int r2 = (int) r3
        L_0x0574:
            int r3 = r15.getAction()
            if (r8 != r3) goto L_0x0590
            com.lgb.canmodule.CanJni.HondaDaRzcAvmTouchCmd(r9, r1, r2)
            goto L_0x007d
        L_0x057f:
            float r3 = r15.getX()
            int r1 = (int) r3
            float r3 = r15.getY()
            r4 = 1148846080(0x447a0000, float:1000.0)
            float r3 = r3 * r4
            r4 = 1142292480(0x44160000, float:600.0)
            float r3 = r3 / r4
            int r2 = (int) r3
            goto L_0x0574
        L_0x0590:
            int r3 = r15.getAction()
            if (r3 != 0) goto L_0x007d
            com.lgb.canmodule.CanJni.HondaDaRzcAvmTouchCmd(r8, r1, r2)
            goto L_0x007d
        L_0x059b:
            int r3 = com.lgb.canmodule.CanJni.GetSubType()
            r4 = 4
            if (r3 != r4) goto L_0x007d
            int r3 = com.ts.main.common.MainSet.GetScreenType()
            r4 = 10
            if (r3 != r4) goto L_0x05c5
            float r3 = r15.getX()
            float r3 = r3 * r10
            float r3 = r3 / r12
            int r1 = (int) r3
            float r3 = r15.getY()
            r4 = 1148846080(0x447a0000, float:1000.0)
            float r3 = r3 * r4
            float r3 = r3 / r11
            int r2 = (int) r3
        L_0x05ba:
            int r3 = r15.getAction()
            if (r8 != r3) goto L_0x05d6
            com.lgb.canmodule.CanJni.HondaDaRzcAvmTouchCmd(r9, r1, r2)
            goto L_0x007d
        L_0x05c5:
            float r3 = r15.getX()
            int r1 = (int) r3
            float r3 = r15.getY()
            r4 = 1148846080(0x447a0000, float:1000.0)
            float r3 = r3 * r4
            r4 = 1142292480(0x44160000, float:600.0)
            float r3 = r3 / r4
            int r2 = (int) r3
            goto L_0x05ba
        L_0x05d6:
            int r3 = r15.getAction()
            if (r3 != 0) goto L_0x007d
            com.lgb.canmodule.CanJni.HondaDaRzcAvmTouchCmd(r8, r1, r2)
            goto L_0x007d
        L_0x05e1:
            int r3 = com.lgb.canmodule.CanJni.GetSubType()
            r4 = 7
            if (r3 != r4) goto L_0x007d
            int r3 = com.ts.main.common.MainSet.GetScreenType()
            r4 = 10
            if (r3 != r4) goto L_0x0609
            float r3 = r15.getX()
            float r3 = r3 * r10
            float r3 = r3 / r12
            int r1 = (int) r3
            float r3 = r15.getY()
            float r3 = r3 * r10
            float r3 = r3 / r11
            int r2 = (int) r3
        L_0x05fe:
            int r3 = r15.getAction()
            if (r8 != r3) goto L_0x0618
            com.lgb.canmodule.CanJni.NissanRzcTouchCmd(r9, r1, r2)
            goto L_0x007d
        L_0x0609:
            float r3 = r15.getX()
            int r1 = (int) r3
            float r3 = r15.getY()
            float r3 = r3 * r10
            r4 = 1142292480(0x44160000, float:600.0)
            float r3 = r3 / r4
            int r2 = (int) r3
            goto L_0x05fe
        L_0x0618:
            int r3 = r15.getAction()
            if (r3 != 0) goto L_0x007d
            com.lgb.canmodule.CanJni.NissanRzcTouchCmd(r8, r1, r2)
            goto L_0x007d
        L_0x0623:
            int r3 = com.lgb.canmodule.CanJni.GetSubType()
            r4 = 7
            if (r3 != r4) goto L_0x007d
            int r3 = com.ts.main.common.MainSet.GetScreenType()
            r4 = 10
            if (r3 != r4) goto L_0x064d
            float r3 = r15.getX()
            float r3 = r3 * r10
            float r3 = r3 / r12
            int r1 = (int) r3
            float r3 = r15.getY()
            r4 = 1148846080(0x447a0000, float:1000.0)
            float r3 = r3 * r4
            float r3 = r3 / r11
            int r2 = (int) r3
        L_0x0642:
            int r3 = r15.getAction()
            if (r8 != r3) goto L_0x065e
            com.lgb.canmodule.CanJni.GmRzcTouchCmd(r9, r1, r2)
            goto L_0x007d
        L_0x064d:
            float r3 = r15.getX()
            int r1 = (int) r3
            float r3 = r15.getY()
            r4 = 1148846080(0x447a0000, float:1000.0)
            float r3 = r3 * r4
            r4 = 1142292480(0x44160000, float:600.0)
            float r3 = r3 / r4
            int r2 = (int) r3
            goto L_0x0642
        L_0x065e:
            int r3 = r15.getAction()
            if (r3 != 0) goto L_0x007d
            com.lgb.canmodule.CanJni.GmRzcTouchCmd(r8, r1, r2)
            goto L_0x007d
        L_0x0669:
            int r3 = com.ts.main.common.MainSet.GetScreenType()
            r4 = 10
            if (r3 != r4) goto L_0x068c
            float r3 = r15.getX()
            float r3 = r3 * r10
            float r3 = r3 / r12
            int r1 = (int) r3
            float r3 = r15.getY()
            r4 = 1142292480(0x44160000, float:600.0)
            float r3 = r3 * r4
            float r3 = r3 / r11
            int r2 = (int) r3
        L_0x0681:
            int r3 = r15.getAction()
            if (r8 != r3) goto L_0x0697
            com.lgb.canmodule.CanJni.PorscheOdAvmTouchCmd(r1, r2, r9)
            goto L_0x007d
        L_0x068c:
            float r3 = r15.getX()
            int r1 = (int) r3
            float r3 = r15.getY()
            int r2 = (int) r3
            goto L_0x0681
        L_0x0697:
            int r3 = r15.getAction()
            if (r3 != 0) goto L_0x007d
            com.lgb.canmodule.CanJni.PorscheOdAvmTouchCmd(r1, r2, r8)
            goto L_0x007d
        L_0x06a2:
            int r3 = com.lgb.canmodule.CanJni.GetSubType()
            r4 = 2
            if (r3 == r4) goto L_0x06b0
            int r3 = com.lgb.canmodule.CanJni.GetSubType()
            r4 = 3
            if (r3 != r4) goto L_0x007d
        L_0x06b0:
            int r3 = com.ts.main.common.MainSet.GetScreenType()
            r4 = 10
            if (r3 != r4) goto L_0x06d6
            float r3 = r15.getX()
            r4 = 1145569280(0x44480000, float:800.0)
            float r3 = r3 * r4
            float r3 = r3 / r12
            int r1 = (int) r3
            float r3 = r15.getY()
            r4 = 1139802112(0x43f00000, float:480.0)
            float r3 = r3 * r4
            float r3 = r3 / r11
            int r2 = (int) r3
        L_0x06ca:
            int r3 = r15.getAction()
            if (r8 != r3) goto L_0x06eb
            r3 = 2
            com.lgb.canmodule.CanJni.JACRefineWcAvmTouchCmd(r8, r1, r2, r3)
            goto L_0x007d
        L_0x06d6:
            float r3 = r15.getX()
            r4 = 1145569280(0x44480000, float:800.0)
            float r3 = r3 * r4
            float r3 = r3 / r10
            int r1 = (int) r3
            float r3 = r15.getY()
            r4 = 1139802112(0x43f00000, float:480.0)
            float r3 = r3 * r4
            r4 = 1142292480(0x44160000, float:600.0)
            float r3 = r3 / r4
            int r2 = (int) r3
            goto L_0x06ca
        L_0x06eb:
            int r3 = r15.getAction()
            if (r3 != 0) goto L_0x007d
            r3 = 2
            r4 = 2
            com.lgb.canmodule.CanJni.JACRefineWcAvmTouchCmd(r3, r1, r2, r4)
            goto L_0x007d
        L_0x06f8:
            int r3 = r15.getAction()
            if (r8 != r3) goto L_0x0080
            com.ts.backcar.BackcarService r3 = com.ts.backcar.BackcarService.getInstance()
            r3.StopCamera()
            int r3 = r13.mFrCamera
            if (r3 <= 0) goto L_0x0720
            r13.mFrCamera = r9
            com.yyw.ts70xhw.Mcu.SetFcamstate(r9)
            com.ts.main.common.MainSet r3 = com.ts.main.common.MainSet.GetInstance()
            r3.SetVideoChannel(r9)
        L_0x0715:
            com.ts.backcar.BackcarService r3 = com.ts.backcar.BackcarService.getInstance()
            com.ts.backcar.AutoFitTextureView r4 = r13.mCameraView
            r3.StartCamera(r4, r8)
            goto L_0x0080
        L_0x0720:
            r13.mFrCamera = r8
            com.yyw.ts70xhw.Mcu.SetFcamstate(r8)
            com.ts.main.common.MainSet r3 = com.ts.main.common.MainSet.GetInstance()
            r4 = 2
            r3.SetVideoChannel(r4)
            goto L_0x0715
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.can.CanCameraUI.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
