package com.ts.factoryset;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.SystemProperties;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.ts.MainUI.R;
import com.ts.factoryset.FsInputDlg;
import com.ts.other.ParamButton;
import com.ts.other.ValCal;
import com.yyw.ts70xhw.FtSet;

public class FsOtherActivity extends FsBaseActivity implements FsInputDlg.onInputOK {
    public static final String ENVIRONMENT_CHANGE = "android.intent.action.ENVIRONMENT_CHANGE";
    public static final int OTHER_ACCDELA = 16;
    public static final int OTHER_AC_OPT_SW = 28;
    public static final int OTHER_AUDIO_IC = 45;
    public static final int OTHER_BACK_SW = 29;
    public static final int OTHER_BRAKE = 1;
    public static final int OTHER_BTVOL_SYNC = 46;
    public static final int OTHER_BT_NAME = 47;
    public static final int OTHER_BT_VER = 44;
    public static final int OTHER_CAM_FIX = 23;
    public static final int OTHER_CAM_LINE_POS = 11;
    public static final int OTHER_CAM_LINE_TYPE = 10;
    public static final int OTHER_CAM_MIR = 24;
    public static final int OTHER_CAM_MUTE = 9;
    public static final int OTHER_CAM_TYPE = 12;
    public static final int OTHER_CARPLAY_PORT = 18;
    public static final int OTHER_CMMB_CHANEL = 43;
    public static final int OTHER_DITHER = 37;
    public static final int OTHER_DTV_TYPE = 4;
    public static final int OTHER_DVR_TYPE = 3;
    public static final int OTHER_ECD_SWAP = 36;
    public static final int OTHER_EMMC_INFO = 14;
    public static final int OTHER_EXAMP = 15;
    public static final int OTHER_INSTAL = 40;
    public static final int OTHER_LAST_MEMORY = 31;
    public static final int OTHER_LCD_MODE = 38;
    public static final int OTHER_LCD_ROTATE = 19;
    public static final int OTHER_LCM_TYPE = 0;
    public static final int OTHER_MACHINE_ROTATE = 20;
    public static final int OTHER_MAX = 48;
    public static final int OTHER_MCU_TYPE = 21;
    public static final int OTHER_MEM_INFO = 13;
    public static final int OTHER_MIN = 0;
    public static final int OTHER_MODEL_NAME = 48;
    public static final int OTHER_NAVI_ON = 22;
    public static final int OTHER_NOISE = 42;
    public static final int OTHER_OFF_TIME_OPT = 30;
    public static final int OTHER_OPT_COLOR_LIGHT = 25;
    public static final int OTHER_OPT_LOGO = 26;
    public static final int OTHER_OPT_SW = 27;
    public static final int OTHER_PROTECT = 41;
    public static final int OTHER_REAR = 39;
    public static final int OTHER_SCR_ORIGN = 6;
    public static final int OTHER_SCR_TYPE = 5;
    public static final int OTHER_SCR_X = 7;
    public static final int OTHER_SCR_Y = 8;
    public static final int OTHER_USB1_SPEED = 32;
    public static final int OTHER_USB2_SPEED = 33;
    public static final int OTHER_USB_DEBUG = 34;
    public static final int OTHER_USB_ENHANCE = 35;
    public static final int OTHER_VER = 17;
    public static final String TAG = "FsOtherActivity";
    private FsInputDlg mBtDlg;
    private char[] mBtName = new char[32];
    private FsInputDlg mDbgDlg;
    private Fs2SelItem mItemACOpt;
    private FsAdjItem mItemAccDelay;
    private FsAdjItem mItemAmp;
    private Fs2SelItem mItemAudioIC;
    private Fs2SelItem mItemAutoNavi;
    private Fs2SelItem mItemBTMode;
    private Fs2SelItem mItemBTvolSync;
    private Fs2SelItem mItemBackLineOpt;
    private FsAdjItem mItemBrake;
    private FsInputItem mItemBtName;
    private Fs2SelItem mItemCamDetect;
    private Fs2SelItem mItemCamMirror;
    private FsAdjItem mItemCamMute;
    private FsAdjItem mItemCamType;
    private FsAdjItem mItemCarplayPort;
    private Fs2SelItem mItemColorLight;
    private Fs2SelItem mItemDither;
    private Fs2SelItem mItemDtvCh;
    private FsAdjItem mItemDtvType;
    private FsAdjItem mItemDvrType;
    private Fs2SelItem mItemEcdSwap;
    private FsAdjItem mItemEmmcInfo;
    private Fs2SelItem mItemInstall;
    private Fs2SelItem mItemLastMemory;
    private FsAdjItem mItemLcmType;
    private FsAdjItem mItemLinePos;
    private FsAdjItem mItemLineType;
    private Fs2SelItem mItemLogoOpt;
    private FsAdjItem mItemMcuType;
    private FsAdjItem mItemMemInfo;
    private FsInputItem mItemModelName;
    private Fs2SelItem mItemNoise;
    private Fs2SelItem mItemPro;
    private Fs2SelItem mItemRearview;
    private Fs2SelItem mItemSWCOpt;
    private FsAdjItem mItemScrOrign;
    private FsAdjItem mItemScrType;
    private FsAdjItem mItemScrX;
    private FsAdjItem mItemScrY;
    private Fs2SelItem mItemScreenoffClock;
    private Fs2SelItem mItemTvFormat;
    private Fs2SelItem mItemUsb1Speed;
    private Fs2SelItem mItemUsb2Speed;
    private Fs2SelItem mItemUsbDebug;
    private Fs2SelItem mItemUsbEnhance;
    private FsAdjItem mItemVerInfo;
    private FsAdjItem mItemlcdrotate;
    private FsAdjItem mItemmachinerotate;
    private FsInputDlg mModelDlg;
    private char[] mModelName = new char[32];

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fs_other);
        topBtnInit(R.string.str_fsmain_other);
        LinearLayout rl = (LinearLayout) findViewById(R.id.lineView);
        Resources res = getResources();
        this.mItemLcmType = new FsAdjItem((ViewGroup) rl, 0, R.string.fs_other_lcmtype, R.array.str_fsother_arraylcmstype, (View.OnClickListener) this);
        this.mItemBrake = new FsAdjItem((ViewGroup) rl, 1, R.string.fs_other_brake, R.array.str_fsother_arraybrake, (View.OnClickListener) this);
        this.mItemDvrType = new FsAdjItem((ViewGroup) rl, 3, R.string.fs_other_dvr_type, 0, (View.OnClickListener) this);
        this.mItemDtvType = new FsAdjItem((ViewGroup) rl, 4, R.string.fs_other_dtv_type, 0, (View.OnClickListener) this);
        this.mItemScrType = new FsAdjItem((ViewGroup) rl, 5, R.string.fs_other_scr_type, 0, (View.OnClickListener) this);
        this.mItemScrOrign = new FsAdjItem((ViewGroup) rl, 6, R.string.fs_other_scr_origh, 0, (View.OnClickListener) this);
        this.mItemScrX = new FsAdjItem((ViewGroup) rl, 7, R.string.fs_other_scr_x, 0, (View.OnClickListener) this);
        this.mItemScrY = new FsAdjItem((ViewGroup) rl, 8, R.string.fs_other_scr_y, 0, (View.OnClickListener) this);
        this.mItemMemInfo = new FsAdjItem((ViewGroup) rl, 13, R.string.fs_other_mem_info, 0, (View.OnClickListener) this);
        this.mItemAmp = new FsAdjItem((ViewGroup) rl, 15, R.string.fs_other_amp, 0, (View.OnClickListener) this);
        this.mItemAccDelay = new FsAdjItem((ViewGroup) rl, 16, R.string.fs_other_accdelay, 0, (View.OnClickListener) this);
        this.mItemCarplayPort = new FsAdjItem((ViewGroup) rl, 18, R.string.fs_other_carplayprot, 0, (View.OnClickListener) this);
        this.mItemVerInfo = new FsAdjItem((ViewGroup) rl, 17, R.string.fs_other_ver, 0, (View.OnClickListener) this);
        this.mItemVerInfo.mVal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                new FsInputDlg().createDlg(FsOtherActivity.this, new FsInputDlg.onInputOK() {
                    public void onOK(String strVal) {
                        if (strVal != null && strVal.length() > 0) {
                            try {
                                FtSet.SetSdSwap(Integer.parseInt(strVal));
                                FsOtherActivity.this.UpdateItem(17);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, true);
            }
        });
        this.mItemlcdrotate = new FsAdjItem((ViewGroup) rl, 19, R.string.fs_other_lcdrotate, R.array.str_fsother_lcd_rotate, (View.OnClickListener) this);
        this.mItemmachinerotate = new FsAdjItem((ViewGroup) rl, 20, R.string.fs_other_machinerotate, R.array.str_fsother_machine_rotate, (View.OnClickListener) this);
        this.mItemMcuType = new FsAdjItem((ViewGroup) rl, 21, R.string.fs_other_mcu_type, 0, (View.OnClickListener) this);
        this.mItemColorLight = new Fs2SelItem((ViewGroup) rl, 25, R.string.fs_other_color_light, R.string.fs_set_no, R.string.fs_set_have, (View.OnClickListener) this);
        this.mItemLogoOpt = new Fs2SelItem((ViewGroup) rl, 26, R.string.fs_other_logo_opt, R.string.fs_set_no, R.string.fs_set_have, (View.OnClickListener) this);
        this.mItemSWCOpt = new Fs2SelItem((ViewGroup) rl, 27, R.string.fs_other_swc_opt, R.string.fs_set_no, R.string.fs_set_have, (View.OnClickListener) this);
        this.mItemACOpt = new Fs2SelItem((ViewGroup) rl, 28, R.string.fs_other_ac_opt, R.string.fs_set_no, R.string.fs_set_have, (View.OnClickListener) this);
        this.mItemLastMemory = new Fs2SelItem((ViewGroup) rl, 31, R.string.fs_other_last_mem, R.string.fs_set_not_mem, R.string.fs_set_memory, (View.OnClickListener) this);
        this.mItemUsb1Speed = new Fs2SelItem((ViewGroup) rl, 32, res.getString(R.string.fs_other_usb1_speed), "2.0", "1.1", (View.OnClickListener) this);
        this.mItemUsb2Speed = new Fs2SelItem((ViewGroup) rl, 33, res.getString(R.string.fs_other_usb2_speed), "2.0", "1.1", (View.OnClickListener) this);
        this.mItemUsbDebug = new Fs2SelItem((ViewGroup) rl, 34, R.string.fs_other_usb2_dbg, R.string.fs_set_off, R.string.fs_set_kq, (View.OnClickListener) this);
        this.mItemUsbEnhance = new Fs2SelItem((ViewGroup) rl, 35, R.string.fs_other_usbenhance, R.string.fs_set_off, R.string.fs_set_kq, (View.OnClickListener) this);
        this.mItemEcdSwap = new Fs2SelItem((ViewGroup) rl, 36, R.string.fs_other_ecd_swap, R.string.fs_set_not_swap, R.string.fs_set_swap, (View.OnClickListener) this);
        this.mItemDither = new Fs2SelItem((ViewGroup) rl, 37, R.string.fs_other_dither, R.string.fs_set_off, R.string.fs_set_kq, (View.OnClickListener) this);
        this.mItemTvFormat = new Fs2SelItem((ViewGroup) rl, 38, res.getString(R.string.fs_other_tv_out), "8bit", "6bit", (View.OnClickListener) this);
        this.mItemBTMode = new Fs2SelItem((ViewGroup) rl, 44, R.string.fs_other_bt_pair, R.string.fs_android_v_l, R.string.fs_android_v_h, (View.OnClickListener) this);
        this.mItemInstall = new Fs2SelItem((ViewGroup) rl, 40, R.string.fs_other_install, R.string.fs_set_kq, R.string.fs_set_off, (View.OnClickListener) this);
        this.mItemPro = new Fs2SelItem((ViewGroup) rl, 41, R.string.fs_other_cameramode, R.string.fs_set_off, R.string.fs_set_kq, (View.OnClickListener) this);
        this.mItemDtvCh = new Fs2SelItem((ViewGroup) rl, 43, R.string.fs_other_dvtch, R.string.title_activity_cmmb_main, R.string.title_activity_avin_main, (View.OnClickListener) this);
        this.mItemBTvolSync = new Fs2SelItem((ViewGroup) rl, 46, R.string.fs_other_btvol, R.string.fs_other_btvoasync, R.string.fs_other_btvosync, (View.OnClickListener) this);
        this.mItemBtName = new FsInputItem((ViewGroup) rl, 47, R.string.fs_other_bt_name, (View.OnClickListener) this);
        this.mItemModelName = new FsInputItem((ViewGroup) rl, 48, R.string.fs_other_modlename, (View.OnClickListener) this);
        this.mBtDlg = new FsInputDlg();
        this.mDbgDlg = new FsInputDlg();
        this.mModelDlg = new FsInputDlg();
        for (int i = 0; i <= 48; i++) {
            UpdateItem(i);
        }
    }

    public void onClick(View v) {
        boolean inc;
        ParamButton btn = (ParamButton) v;
        int id = btn.getIntParam();
        int val = btn.getIntParam2();
        if (val != 0) {
            inc = true;
        } else {
            inc = false;
        }
        switch (id) {
            case 0:
                FtSet.SetLcmType(ValCal.dataStepNoLoop(FtSet.GetLcmType(), 0, 3, inc));
                break;
            case 1:
                FtSet.SetBrakeDef(ValCal.dataStepNoLoop(FtSet.GetBrakeDef(), 0, 3, inc));
                break;
            case 3:
                FtSet.SetDvrType(ValCal.dataStepNoLoop(FtSet.GetDvrType(), 0, 99, inc));
                break;
            case 4:
                FtSet.SetDtvType(ValCal.dataStepNoLoop(FtSet.GetDtvType(), 0, 99, inc));
                break;
            case 5:
                FtSet.SetCtType(ValCal.dataStepNoLoop(FtSet.GetCtType(), 0, 99, inc));
                break;
            case 9:
                FtSet.SetParkMuteDef(ValCal.dataStepNoLoop(FtSet.GetParkMuteDef(), 0, 3, inc));
                break;
            case 10:
                FtSet.SetLineType(ValCal.dataStepNoLoop(FtSet.GetLineType(), 0, 2, inc));
                break;
            case 11:
                FtSet.SetLinePos(ValCal.dataStepNoLoop(FtSet.GetLinePos(), -200, 200, inc));
                break;
            case 12:
                FtSet.SetCamType(ValCal.dataStepNoLoop(FtSet.GetCamType(), 0, 2, inc));
                break;
            case 13:
                FtSet.SetRam(ValCal.dataStepNoLoop(FtSet.GetRam(), 0, 8, inc));
                break;
            case 14:
                FtSet.SetRom(ValCal.dataStepNoLoop(FtSet.GetRom(), 0, 8, inc));
                break;
            case 15:
                FtSet.SetExAmp(ValCal.dataStepNoLoop(FtSet.GetExAmp(), 0, 1, inc));
                break;
            case 16:
                FtSet.SetAccoffFilter(ValCal.dataStepNoLoop(FtSet.GetAccoffFilter(), 0, 10, inc));
                break;
            case 17:
                FtSet.SetSdSwap(ValCal.dataStepNoLoop(FtSet.GetSdSwap(), 0, 100, inc));
                break;
            case 18:
                FtSet.SetiicMfi(ValCal.dataStepNoLoop(FtSet.GetiicMfi(), 0, 6, inc));
                break;
            case 19:
                FtSet.SetLcdUpDown(ValCal.dataStepNoLoop(FtSet.GetLcdUpDown(), 0, 3, inc));
                break;
            case 20:
                FtSet.SetLcdLeftRight(ValCal.dataStepNoLoop(FtSet.GetLcdLeftRight(), 0, 1, inc));
                break;
            case 21:
                FtSet.SetMcuType(ValCal.dataStepNoLoop(FtSet.GetMcuType(), 0, 10, inc));
                break;
            case 22:
                FtSet.SetAutoNaviDef(val);
                break;
            case 23:
                FtSet.SetCamFix(val);
                break;
            case 24:
                FtSet.SetCamMir(val);
                break;
            case 25:
                FtSet.SetOptionIll(val);
                break;
            case 26:
                FtSet.SetOptionLogo(val);
                break;
            case 27:
                FtSet.SetOptionSW(val);
                break;
            case 28:
                FtSet.SetOptionAC(val);
                break;
            case 29:
                FtSet.SetOptionCam(val);
                break;
            case 30:
                FtSet.SetOptionWarn(val);
                break;
            case 31:
                FtSet.SetLastMemory(val);
                break;
            case 32:
                FtSet.SetUsb1Spd(val);
                setUsb0Procotol(val);
                break;
            case 33:
                FtSet.SetUsb2Spd(val);
                setUsbProcotol(val);
                break;
            case 34:
                if (FtSet.GetUsbHost() == 0 && val != 0) {
                    Log.e(TAG, "GetUsbHost");
                    setUsbMode(1);
                    break;
                } else {
                    setUsbMode(0);
                    break;
                }
            case 35:
                FtSet.SetUsbCurrent(val);
                break;
            case 36:
                FtSet.SetEcdSwap(val);
                break;
            case 37:
                FtSet.SetDither(val);
                break;
            case 38:
                FtSet.SetLcdMode(val);
                break;
            case 39:
                FtSet.SetGnssMode(val);
                break;
            case 40:
                if (FtSet.GetApkForbid() != 1) {
                    FtSet.SetApkForbid(1);
                    SystemProperties.set("forfan.notallow.install", "1");
                    break;
                } else {
                    this.mDbgDlg.createDlg(this, new FsInputDlg.onInputOK() {
                        public void onOK(String strVal) {
                            if (strVal != null && strVal.equals(FsOtherActivity.this.getResources().getString(R.string.custom_apk_install_pwd))) {
                                FtSet.SetApkForbid(0);
                                SystemProperties.set("forfan.notallow.install", "0");
                                FsOtherActivity.this.UpdateItem(40);
                            }
                        }
                    }, true);
                    break;
                }
            case 41:
                FtSet.SetCam8824(val);
                break;
            case 42:
                FtSet.SetNoiseDown(val);
                break;
            case 43:
                FtSet.SetExUart(val);
                break;
            case 44:
                FtSet.SetBlueEn(val);
                break;
            case 45:
                FtSet.SetDspEn(val);
                break;
            case 46:
                FtSet.SetBlueSync(val);
                break;
            case 47:
                this.mBtDlg.createDlg(this, this, false);
                break;
            case 48:
                this.mModelDlg.createDlg(this, new FsInputDlg.onInputOK() {
                    public void onOK(String strVal) {
                        char[] ModelName = new char[32];
                        if (strVal.length() > 0) {
                            Log.e(FsOtherActivity.TAG, "Input val = " + strVal);
                            for (int i = 0; i < strVal.length(); i++) {
                                ModelName[i] = strVal.charAt(i);
                            }
                            for (char c : ModelName) {
                            }
                        }
                        FtSet.SetModelName(ModelName);
                        FsOtherActivity.this.UpdateItem(48);
                    }
                }, false);
                break;
        }
        if (47 != id || 48 != id) {
            UpdateItem(id);
        }
    }

    /* access modifiers changed from: protected */
    public void UpdateItem(int id) {
        switch (id) {
            case 0:
                this.mItemLcmType.SetVal(FtSet.GetLcmType());
                return;
            case 1:
                this.mItemBrake.SetVal(FtSet.GetBrakeDef());
                return;
            case 3:
                this.mItemDvrType.SetVal(FtSet.GetDvrType());
                return;
            case 4:
                this.mItemDtvType.SetVal(FtSet.GetDtvType());
                return;
            case 5:
                this.mItemScrType.SetVal(FtSet.GetCtType());
                return;
            case 6:
                this.mItemScrOrign.SetVal(FtSet.GetCtXYswap());
                return;
            case 7:
                this.mItemScrX.SetVal(FtSet.GetCtXYrange() / 65536);
                return;
            case 8:
                this.mItemScrY.SetVal(FtSet.GetCtXYrange() % 65536);
                return;
            case 9:
                if (this.mItemCamMute != null) {
                    this.mItemCamMute.SetVal(FtSet.GetParkMuteDef());
                    return;
                }
                return;
            case 10:
                if (this.mItemLineType != null) {
                    this.mItemLineType.SetVal(FtSet.GetLineType());
                    return;
                }
                return;
            case 11:
                if (this.mItemLinePos != null) {
                    this.mItemLinePos.SetVal(FtSet.GetLinePos());
                    return;
                }
                return;
            case 12:
                if (this.mItemCamType != null) {
                    this.mItemCamType.SetVal(FtSet.GetCamType());
                    return;
                }
                return;
            case 13:
                this.mItemMemInfo.SetVal(FtSet.GetRam());
                return;
            case 14:
                if (this.mItemEmmcInfo != null) {
                    this.mItemEmmcInfo.SetVal(FtSet.GetRom());
                    return;
                }
                return;
            case 15:
                this.mItemAmp.SetVal(FtSet.GetExAmp());
                return;
            case 16:
                if (this.mItemAccDelay != null) {
                    this.mItemAccDelay.SetVal(FtSet.GetAccoffFilter());
                    return;
                }
                return;
            case 17:
                if (this.mItemVerInfo != null) {
                    this.mItemVerInfo.SetVal(FtSet.GetSdSwap());
                    return;
                }
                return;
            case 18:
                this.mItemCarplayPort.SetVal(FtSet.GetiicMfi());
                return;
            case 19:
                if (this.mItemlcdrotate != null) {
                    this.mItemlcdrotate.SetVal(FtSet.GetLcdUpDown());
                    return;
                }
                return;
            case 20:
                if (this.mItemmachinerotate != null) {
                    this.mItemmachinerotate.SetVal(FtSet.GetLcdLeftRight());
                    return;
                }
                return;
            case 21:
                this.mItemMcuType.SetVal(FtSet.GetMcuType());
                return;
            case 22:
                if (this.mItemAutoNavi != null) {
                    this.mItemAutoNavi.SetVal(FtSet.GetAutoNaviDef());
                    return;
                }
                return;
            case 23:
                if (this.mItemCamDetect != null) {
                    this.mItemCamDetect.SetVal(FtSet.GetCamFix());
                    return;
                }
                return;
            case 24:
                if (this.mItemCamMirror != null) {
                    this.mItemCamMirror.SetVal(FtSet.GetCamMir());
                    return;
                }
                return;
            case 25:
                this.mItemColorLight.SetVal(FtSet.GetOptionIll());
                return;
            case 26:
                this.mItemLogoOpt.SetVal(FtSet.GetOptionLogo());
                return;
            case 27:
                this.mItemSWCOpt.SetVal(FtSet.GetOptionSW());
                return;
            case 28:
                this.mItemACOpt.SetVal(FtSet.GetOptionAC());
                return;
            case 29:
                if (this.mItemBackLineOpt != null) {
                    this.mItemBackLineOpt.SetVal(FtSet.GetOptionCam());
                    return;
                }
                return;
            case 30:
                if (this.mItemScreenoffClock != null) {
                    this.mItemScreenoffClock.SetVal(FtSet.GetOptionWarn());
                    return;
                }
                return;
            case 31:
                if (this.mItemLastMemory != null) {
                    this.mItemLastMemory.SetVal(FtSet.GetLastMemory());
                    return;
                }
                return;
            case 32:
                this.mItemUsb1Speed.SetVal(FtSet.GetUsb1Spd());
                Log.d(TAG, "FtSet.GetUsb1Spd() = " + FtSet.GetUsb1Spd());
                return;
            case 33:
                this.mItemUsb2Speed.SetVal(FtSet.GetUsb2Spd());
                Log.d(TAG, "FtSet.GetUsb2Spd() = " + FtSet.GetUsb2Spd());
                return;
            case 34:
                this.mItemUsbDebug.SetVal(FtSet.GetUsbHost());
                return;
            case 35:
                this.mItemUsbEnhance.SetVal(FtSet.GetUsbCurrent());
                return;
            case 36:
                this.mItemEcdSwap.SetVal(FtSet.GetEcdSwap());
                return;
            case 37:
                this.mItemDither.SetVal(FtSet.GetDither());
                return;
            case 38:
                this.mItemTvFormat.SetVal(FtSet.GetLcdMode());
                return;
            case 39:
                if (this.mItemRearview != null) {
                    this.mItemRearview.SetVal(FtSet.GetGnssMode());
                    return;
                }
                return;
            case 40:
                this.mItemInstall.SetVal(FtSet.GetApkForbid());
                return;
            case 41:
                if (this.mItemPro != null) {
                    this.mItemPro.SetVal(FtSet.GetCam8824());
                    return;
                }
                return;
            case 42:
                if (this.mItemNoise != null) {
                    this.mItemNoise.SetVal(FtSet.GetNoiseDown());
                    return;
                }
                return;
            case 43:
                if (this.mItemDtvCh != null) {
                    this.mItemDtvCh.SetVal(FtSet.GetExUart());
                    return;
                }
                return;
            case 44:
                if (this.mItemBTMode != null) {
                    this.mItemBTMode.SetVal(FtSet.GetBlueEn());
                    return;
                }
                return;
            case 45:
                if (this.mItemAudioIC != null) {
                    this.mItemAudioIC.SetVal(FtSet.GetDspEn());
                    return;
                }
                return;
            case 46:
                this.mItemBTvolSync.SetVal(FtSet.GetBlueSync());
                return;
            case 47:
                FtSet.GetBtName(this.mBtName);
                String strName = new String();
                int i = 0;
                while (i < this.mBtName.length && this.mBtName[i] != 0) {
                    strName = String.valueOf(strName) + this.mBtName[i];
                    i++;
                }
                this.mItemBtName.SetVal(strName);
                return;
            case 48:
                FtSet.GetModelName(this.mModelName);
                String strbName = new String();
                int j = 0;
                while (j < this.mModelName.length && this.mModelName[j] != 0) {
                    strbName = String.valueOf(strbName) + this.mModelName[j];
                    j++;
                }
                this.mItemModelName.SetVal(strbName);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public void onOK(String strVal) {
        char[] BtName = new char[32];
        if (strVal.length() > 0) {
            Log.e(TAG, "Input val = " + strVal);
            for (int i = 0; i < strVal.length(); i++) {
                BtName[i] = strVal.charAt(i);
            }
            for (char c : BtName) {
            }
        }
        FtSet.SetBtName(BtName);
        UpdateItem(47);
    }
}
