package com.ts.factoryset;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;
import com.autochips.metazone.AtcMetazone;
import com.ts.bt.ContactInfo;
import java.util.List;

public class AtcDisplaySettingsUtils {
    public static final int ANTICLOCKWISE_LANDSCAPE = 180;
    public static final int ANTICLOCKWISE_PORTRAIT = 270;
    public static final int CAMERA_FHD25 = 5;
    public static final int CAMERA_FHD30 = 6;
    public static final int CAMERA_HD25 = 3;
    public static final int CAMERA_HD30 = 4;
    public static final int CAMERA_HD_25_TVI = 9;
    public static final int CAMERA_HD_30_TVI = 10;
    public static final int CAMERA_NOT_RESERVE = 0;
    public static final int CAMERA_NTSC = 1;
    public static final int CAMERA_NTSC_TVI = 7;
    public static final int CAMERA_PAL = 2;
    public static final int CAMERA_PAL_TVI = 8;
    public static final int CAMERA_USER_DEF = 128;
    public static final int CLOCKWISE_PORTRAIT = 90;
    public static final int DITHER_OFF = 0;
    public static final int DITHER_ON = 1;
    public static final int FORCE_LANDSCAPE = 1;
    public static final int FORCE_PORTRAIT = 2;
    public static final int FULLSCREEN_X = 0;
    public static final int FULLSCREEN_Y = 0;
    public static final int LANDSCAPE = 0;
    public static final int LRMIRROR_OPEN_FLAG = 1;
    public static final int NO_FORCE = 0;
    public static final int SMALL2_SCREEN_HEIGHT = 720;
    public static final int SMALL2_SCREEN_WIDTH = 1280;
    public static final int SMALL3_SCREEN_HEIGHT = 768;
    public static final int SMALL3_SCREEN_WIDTH = 1024;
    public static final int SMALL4_SCREEN_HEIGHT = 1080;
    public static final int SMALL4_SCREEN_WIDTH = 1920;
    public static final int SMALL_SCREEN_HEIGHT = 600;
    public static final int SMALL_SCREEN_ROTATE_HEIGHT = 1024;
    public static final int SMALL_SCREEN_ROTATE_WIDTH = 600;
    public static final int SMALL_SCREEN_WIDTH = 1024;
    public static final int SOURCE_AVM = 3;
    public static final int SOURCE_CVBS = 1;
    public static final int SOURCE_DEFAULT = 0;
    public static final int SOURCE_MIPI = 2;
    public static final int SPECIFIC_H = 480;
    public static final int SPECIFIC_H_SMALL = 420;
    public static final int SPECIFIC_H_SMALL2 = 540;
    public static final int SPECIFIC_H_SMALL3 = 512;
    public static final int SPECIFIC_H_SMALL4 = 1170;
    public static final int SPECIFIC_W = 800;
    public static final int SPECIFIC_W_SMALL = 600;
    public static final int SPECIFIC_W_SMALL2 = 720;
    public static final int SPECIFIC_W_SMALL3 = 768;
    public static final int SPECIFIC_W_SMALL4 = 1080;
    public static final int SPECIFIC_X = 0;
    public static final int SPECIFIC_X_SMALL = 0;
    public static final int SPECIFIC_X_SMALL2 = 0;
    public static final int SPECIFIC_X_SMALL3 = 0;
    public static final int SPECIFIC_X_SMALL4 = 0;
    public static final int SPECIFIC_Y = 420;
    public static final int SPECIFIC_Y_SMALL = 310;
    public static final int SPECIFIC_Y_SMALL2 = 370;
    public static final int SPECIFIC_Y_SMALL3 = 256;
    public static final int SPECIFIC_Y_SMALL4 = 0;
    public static final int UDMIRROR_OPEN_FLAG = 2;
    public static int mLRMirrorStatusFlag = 0;
    public static int mUDMirrorStatusFlag = 0;
    private final int ADDR_AHD_CAMERA_MODE;
    private final int ADDR_AHD_CAMERA_SCRX;
    private final int ADDR_AHD_CAMERA_SCRY;
    private final int ADDR_AVM_CAMERA_MODE;
    private final int ADDR_BACKCAR_DISPLAY_H;
    private final int ADDR_BACKCAR_DISPLAY_W;
    private final int ADDR_BACKCAR_DISPLAY_X;
    private final int ADDR_BACKCAR_DISPLAY_Y;
    private final int ADDR_BACKCAR_MIPI_DISPLAY_CUT_H;
    private final int ADDR_BACKCAR_MIPI_DISPLAY_CUT_W;
    private final int ADDR_BACKCAR_MIPI_DISPLAY_CUT_X;
    private final int ADDR_BACKCAR_MIPI_DISPLAY_CUT_Y;
    private final int ADDR_BACKCAR_MIPI_MIRROR;
    private final int ADDR_BACKCAR_MIRROR;
    private final int ADDR_BACKCAR_ROTATION;
    private final int ADDR_BACKCAR_SOURCE_MODE;
    private final int ADDR_DITHER;
    private final int ADDR_FAST_AVM;
    private final int ADDR_REAR_DISPLAY;
    private final int ADDR_SHARPNESS;
    private final int ARRD_BACKCAR_DISPLAY_CUT_H;
    private final int ARRD_BACKCAR_DISPLAY_CUT_W;
    private final int ARRD_BACKCAR_DISPLAY_CUT_X;
    private final int ARRD_BACKCAR_DISPLAY_CUT_Y;
    private final int ARRD_FORCE_DIRECTION;
    private final int METAZONE_READ_SUCCESS;
    private final int METAZONE_START_ADDR;
    private final String TAG;
    private int displayH;
    private int displayW;
    private int displayX;
    private int displayY;
    private int forceDirection;
    private int rotation;

    private AtcDisplaySettingsUtils() {
        this.TAG = "AtcDisplay";
        this.METAZONE_READ_SUCCESS = 4;
        this.METAZONE_START_ADDR = 65536;
        this.ADDR_BACKCAR_ROTATION = 65587;
        this.ADDR_BACKCAR_MIRROR = 65588;
        this.ADDR_BACKCAR_DISPLAY_X = 65589;
        this.ADDR_BACKCAR_DISPLAY_Y = 65590;
        this.ADDR_BACKCAR_DISPLAY_W = 65591;
        this.ADDR_BACKCAR_DISPLAY_H = 65592;
        this.ARRD_FORCE_DIRECTION = 65593;
        this.ARRD_BACKCAR_DISPLAY_CUT_X = 65594;
        this.ARRD_BACKCAR_DISPLAY_CUT_Y = 65595;
        this.ARRD_BACKCAR_DISPLAY_CUT_W = 65596;
        this.ARRD_BACKCAR_DISPLAY_CUT_H = 65597;
        this.ADDR_SHARPNESS = 65598;
        this.ADDR_DITHER = 65599;
        this.ADDR_BACKCAR_MIPI_MIRROR = 65600;
        this.ADDR_BACKCAR_MIPI_DISPLAY_CUT_X = 65601;
        this.ADDR_BACKCAR_MIPI_DISPLAY_CUT_Y = 65602;
        this.ADDR_BACKCAR_MIPI_DISPLAY_CUT_W = 65603;
        this.ADDR_BACKCAR_MIPI_DISPLAY_CUT_H = 65604;
        this.ADDR_AHD_CAMERA_MODE = 65605;
        this.ADDR_AVM_CAMERA_MODE = 65606;
        this.ADDR_BACKCAR_SOURCE_MODE = 65607;
        this.ADDR_FAST_AVM = 65608;
        this.ADDR_REAR_DISPLAY = 65550;
        this.ADDR_AHD_CAMERA_SCRX = 66034;
        this.ADDR_AHD_CAMERA_SCRY = 66035;
    }

    /* synthetic */ AtcDisplaySettingsUtils(AtcDisplaySettingsUtils atcDisplaySettingsUtils) {
        this();
    }

    private static class SingletonHolder {
        /* access modifiers changed from: private */
        public static final AtcDisplaySettingsUtils INSTANCE = new AtcDisplaySettingsUtils((AtcDisplaySettingsUtils) null);

        private SingletonHolder() {
        }
    }

    public static AtcDisplaySettingsUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private void writeForceDirectionRotationAndDisplayZone(int direction, int rotation2, int displayX2, int displayY2, int displayW2, int displayH2) {
        Log.d("AtcDisplay", "direction:" + direction + ",rotation =" + rotation2);
        Log.d("AtcDisplay", "(x,y,w,h) =(" + displayX2 + ContactInfo.COMBINATION_SEPERATOR + displayY2 + ContactInfo.COMBINATION_SEPERATOR + displayW2 + ContactInfo.COMBINATION_SEPERATOR + displayH2 + ")");
        AtcMetazone.writeval(65593, direction);
        AtcMetazone.writeval(65587, rotation2);
        AtcMetazone.writeval(65589, displayX2);
        AtcMetazone.writeval(65590, displayY2);
        AtcMetazone.writeval(65591, displayW2);
        AtcMetazone.writeval(65592, displayH2);
        AtcMetazone.flush(true);
    }

    public int[] getForceDirectionRotationAndDisplayZone() {
        readDisplayZone();
        return new int[]{readForceDirection(), readRotation()};
    }

    public int readForceDirection() {
        int[] result = new int[4];
        AtcMetazone.readval(65593, result);
        if (result.length == 0) {
            Log.e("AtcDisplay", "Error:Get forceDirection data null,make it default direction!");
            result[0] = 0;
        }
        return result[0];
    }

    public int readRotation() {
        int[] result = new int[4];
        AtcMetazone.readval(65587, result);
        if (result.length == 0) {
            Log.e("AtcDisplay", "Error:Get Rotation data null,make it default rotation!");
            result[0] = 0;
        }
        return result[0];
    }

    private void readDisplayZone() {
        int[] result = new int[4];
        AtcMetazone.readval(65589, result);
        int x = result[0];
        AtcMetazone.readval(65590, result);
        int y = result[0];
        AtcMetazone.readval(65591, result);
        int w = result[0];
        AtcMetazone.readval(65592, result);
        Log.d("AtcDisplay", "Display zone:(x,y,w,h)=(" + x + ContactInfo.COMBINATION_SEPERATOR + y + ContactInfo.COMBINATION_SEPERATOR + w + ContactInfo.COMBINATION_SEPERATOR + result[0] + ")");
    }

    public void setScreenOrientation(int index, int screenW, int screenH) {
        Log.d("AtcDisplay", "setScreenOrientation:index=" + index + ",screenW=" + screenW + ",screenH=" + screenH);
        switch (index) {
            case 0:
                if (screenW < screenH) {
                    int temp = screenW;
                    screenW = screenH;
                    screenH = temp;
                }
                this.forceDirection = 0;
                this.rotation = 0;
                this.displayX = 0;
                this.displayY = 0;
                this.displayW = screenW;
                this.displayH = screenH;
                break;
            case 1:
                if (screenW < screenH) {
                    int temp2 = screenW;
                    screenW = screenH;
                    screenH = temp2;
                }
                this.forceDirection = 1;
                this.rotation = 0;
                this.displayX = 0;
                this.displayY = 0;
                this.displayW = screenW;
                this.displayH = screenH;
                break;
            case 2:
                this.forceDirection = 2;
                this.rotation = 90;
                if ((screenW != 1024 || screenH != 600) && (screenW != 600 || screenH != 1024)) {
                    if ((screenW != 1280 || screenH != 720) && (screenW != 720 || screenH != 1280)) {
                        if ((screenW != 1024 || screenH != 768) && (screenW != 768 || screenH != 1024)) {
                            if ((screenW != 1920 || screenH != 1080) && (screenW != 1080 || screenH != 1920)) {
                                this.displayX = 0;
                                this.displayY = 420;
                                this.displayW = 800;
                                this.displayH = 480;
                                break;
                            } else {
                                this.displayX = 0;
                                this.displayY = 0;
                                this.displayW = 1080;
                                this.displayH = 1170;
                                break;
                            }
                        } else {
                            this.displayX = 0;
                            this.displayY = 256;
                            this.displayW = 768;
                            this.displayH = 512;
                            break;
                        }
                    } else {
                        this.displayX = 0;
                        this.displayY = SPECIFIC_Y_SMALL2;
                        this.displayW = 720;
                        this.displayH = 540;
                        break;
                    }
                } else {
                    this.displayX = 0;
                    this.displayY = 310;
                    this.displayW = 600;
                    this.displayH = 420;
                    break;
                }
                break;
            case 3:
                if (screenW < screenH) {
                    int temp3 = screenW;
                    screenW = screenH;
                    screenH = temp3;
                }
                this.forceDirection = 1;
                this.rotation = 180;
                this.displayX = 0;
                this.displayY = 0;
                this.displayW = screenW;
                this.displayH = screenH;
                break;
            case 4:
                this.forceDirection = 2;
                this.rotation = 270;
                if ((screenW != 1024 || screenH != 600) && (screenW != 600 || screenH != 1024)) {
                    if ((screenW != 1280 || screenH != 720) && (screenW != 720 || screenH != 1280)) {
                        if ((screenW != 1024 || screenH != 768) && (screenW != 768 || screenH != 1024)) {
                            if ((screenW != 1920 || screenH != 1080) && (screenW != 1080 || screenH != 1920)) {
                                this.displayX = 0;
                                this.displayY = 420;
                                this.displayW = 800;
                                this.displayH = 480;
                                break;
                            } else {
                                this.displayX = 0;
                                this.displayY = 0;
                                this.displayW = 1080;
                                this.displayH = 1170;
                                break;
                            }
                        } else {
                            this.displayX = 0;
                            this.displayY = 256;
                            this.displayW = 768;
                            this.displayH = 512;
                            break;
                        }
                    } else {
                        this.displayX = 0;
                        this.displayY = SPECIFIC_Y_SMALL2;
                        this.displayW = 720;
                        this.displayH = 540;
                        break;
                    }
                } else {
                    this.displayX = 0;
                    this.displayY = 310;
                    this.displayW = 600;
                    this.displayH = 420;
                    break;
                }
                break;
        }
        writeForceDirectionRotationAndDisplayZone(this.forceDirection, this.rotation, this.displayX, this.displayY, this.displayW, this.displayH);
    }

    public void setMirror(int sourceType) {
        int value = mLRMirrorStatusFlag + mUDMirrorStatusFlag;
        Log.d("AtcDisplay", "writeMirror:value=" + value);
        if (sourceType == 2) {
            AtcMetazone.writeval(65600, value);
        } else {
            AtcMetazone.writeval(65588, value);
        }
        AtcMetazone.flush(true);
    }

    public int getMirror(int sourceType) {
        int[] result = new int[4];
        if (sourceType == 2) {
            AtcMetazone.readval(65600, result);
        } else {
            AtcMetazone.readval(65588, result);
        }
        return result[0];
    }

    public void writeCutCoordinate(int x, int y, int w, int h, int sourceType) {
        Log.d("AtcDisplay", "writeCutCoordinate:(x" + x + ",y" + y + ",w" + w + ",h" + h + ")");
        if (sourceType == 2) {
            AtcMetazone.writeval(65601, x);
            AtcMetazone.writeval(65602, y);
            AtcMetazone.writeval(65603, w);
            AtcMetazone.writeval(65604, h);
        } else {
            AtcMetazone.writeval(65594, x);
            AtcMetazone.writeval(65595, y);
            AtcMetazone.writeval(65596, w);
            AtcMetazone.writeval(65597, h);
        }
        AtcMetazone.flush(true);
    }

    public int[] getCutCoordinate(int sourceType) {
        Log.d("AtcDisplay", "getCutCoordinate");
        int[] res = new int[4];
        int[] realRes = new int[4];
        if (sourceType == 2) {
            AtcMetazone.readval(65601, res);
        } else {
            AtcMetazone.readval(65594, res);
        }
        realRes[0] = res[0];
        if (sourceType == 2) {
            AtcMetazone.readval(65602, res);
        } else {
            AtcMetazone.readval(65595, res);
        }
        realRes[1] = res[0];
        if (sourceType == 2) {
            AtcMetazone.readval(65603, res);
        } else {
            AtcMetazone.readval(65596, res);
        }
        realRes[2] = res[0];
        if (sourceType == 2) {
            AtcMetazone.readval(65604, res);
        } else {
            AtcMetazone.readval(65597, res);
        }
        realRes[3] = res[0];
        Log.d("AtcDisplay", "Cut coordinate:(x,y,w,h)=(" + realRes[0] + ContactInfo.COMBINATION_SEPERATOR + realRes[1] + ContactInfo.COMBINATION_SEPERATOR + realRes[2] + ContactInfo.COMBINATION_SEPERATOR + realRes[3] + ")");
        if (realRes.length == 0) {
            for (int i = 0; i < 4; i++) {
                realRes[i] = 0;
            }
        }
        return realRes;
    }

    public void setSharpness(int sharpnessValue) {
        Log.d("AtcDisplay", "setSharpness:sharpnessValue=" + sharpnessValue);
        AtcMetazone.writeval(65598, sharpnessValue);
        AtcMetazone.flush(true);
    }

    public int getSharpness() {
        int[] result = new int[4];
        AtcMetazone.readval(65598, result);
        return result[0];
    }

    public void setDither(int ditherValue) {
        Log.d("AtcDisplay", "setDither:ditherValue=" + ditherValue);
        AtcMetazone.writeval(65599, ditherValue);
        AtcMetazone.flush(true);
    }

    public int getDither() {
        int[] result = new int[4];
        AtcMetazone.readval(65599, result);
        return result[0];
    }

    public static boolean isAppInstalled(Context context, String packageName) {
        List<PackageInfo> pInfos = context.getPackageManager().getInstalledPackages(0);
        if (pInfos != null && pInfos.size() > 0) {
            int size = pInfos.size();
            for (int i = 0; i < size; i++) {
                if (pInfos.get(i).packageName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getAVMCameraMode() {
        int[] result = new int[4];
        AtcMetazone.readval(65606, result);
        if (result.length == 0) {
            Log.e("AtcDisplay", "Error:Get getAVMCameraMode data null,make it default mode!");
            result[0] = 0;
        }
        return result[0];
    }

    public void setAVMCameraMode(int mode) {
        Log.d("AtcDisplay", "setAVMCameraMode:mode=" + mode);
        AtcMetazone.writeval(65606, mode);
        AtcMetazone.flush(true);
    }

    public int getAHDCameraMode() {
        int[] result = new int[4];
        AtcMetazone.readval(65605, result);
        if (result.length == 0) {
            Log.e("AtcDisplay", "Error:Get getAHDCameraMode data null,make it default mode!");
            result[0] = 0;
        }
        return result[0];
    }

    public void setAHDCameraMode(int mode) {
        if (getAHDCameraMode() != mode) {
            Log.d("AtcDisplay", "setAHDCameraMode:mode=" + mode);
            AtcMetazone.writeval(65605, mode);
            AtcMetazone.flush(true);
        }
    }

    public void setBackcarSourceMode(int mode) {
        if (getBackcarSourceMode() != mode) {
            Log.d("AtcDisplay", "setBackcarSourceMode:mode=" + mode);
            AtcMetazone.writeval(65607, mode);
            AtcMetazone.flush(true);
        }
    }

    public int getBackcarSourceMode() {
        int[] result = new int[4];
        AtcMetazone.readval(65607, result);
        if (result.length == 0) {
            Log.e("AtcDisplay", "Error:Get getBackcarSourceMode data null,make it default mode!");
            result[0] = 0;
        }
        return result[0];
    }

    public int GetMetZoneVal(int nAdd) {
        int[] result = new int[4];
        AtcMetazone.readval(nAdd, result);
        return result[0];
    }

    public void SetMetZoneVal(int nAdd, int nVal) {
        AtcMetazone.writeval(nAdd, nVal);
        AtcMetazone.flush(true);
    }

    public void SetMipiUserScrX(int X) {
        if (GetMipiUserScrX() != X) {
            Log.d("AtcDisplay", "SetMipiUserScrX X=" + X);
            SetMetZoneVal(66034, X);
        }
    }

    public void SetMipiUserScrY(int Y) {
        if (GetMipiUserScrY() != Y) {
            Log.d("AtcDisplay", "SetMipiUserScrY X=" + Y);
            SetMetZoneVal(66035, Y);
        }
    }

    public int GetMipiUserScrX() {
        Log.d("AtcDisplay", "GetMipiUserScrX");
        return GetMetZoneVal(66034);
    }

    public int GetMipiUserScrY() {
        Log.d("AtcDisplay", "GetMipiUserScrY");
        return GetMetZoneVal(66035);
    }

    public void SetBackCarW(int w) {
        SetMetZoneVal(65591, w);
    }

    public void SetBackCarH(int h) {
        SetMetZoneVal(65592, h);
    }

    public int GetBackCarW() {
        return GetMetZoneVal(65591);
    }

    public int GetBackCarH() {
        return GetMetZoneVal(65592);
    }

    public void setFastAVM(int fastAVMValue) {
        Log.d("AtcDisplay", "setFastAVM:fastAVMValue=" + fastAVMValue);
        AtcMetazone.writeval(65608, fastAVMValue);
        AtcMetazone.flush(true);
    }

    public int getFastAVM() {
        int[] result = new int[4];
        AtcMetazone.readval(65608, result);
        return result[0];
    }

    public void setRearDisplay(int RearValue) {
        Log.d("AtcDisplay", "setRearDisplay:RearValue=" + RearValue);
        AtcMetazone.writeval(65550, RearValue);
        AtcMetazone.flush(true);
    }

    public int getRearDisplay() {
        int[] result = new int[4];
        AtcMetazone.readval(65550, result);
        return result[0];
    }
}
