package com.ts.factoryset;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemProperties;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.ts.MainUI.R;
import com.ts.factoryset.FsInputDlg;
import com.ts.main.common.MainSet;
import com.ts.other.ParamButton;
import com.ts.other.ValCal;
import com.yyw.ts70xhw.FtSet;

public class FsOtherActivity extends FsBaseActivity implements FsInputDlg.onInputOK {
    public static final String ENVIRONMENT_CHANGE = "android.intent.action.ENVIRONMENT_CHANGE";
    public static final int OTHER_8824 = 32;
    public static final int OTHER_AC_OPT_SW = 18;
    public static final int OTHER_AUDIO_IC = 36;
    public static final int OTHER_BACK_SW = 19;
    public static final int OTHER_BRAKE = 1;
    public static final int OTHER_BTVOL_SYNC = 41;
    public static final int OTHER_BT_NAME = 28;
    public static final int OTHER_BT_VER = 35;
    public static final int OTHER_CAM_FIX = 12;
    public static final int OTHER_CAM_LINE_POS = 10;
    public static final int OTHER_CAM_LINE_TYPE = 9;
    public static final int OTHER_CAM_MIR = 13;
    public static final int OTHER_CAM_MUTE = 6;
    public static final int OTHER_CAM_OPT = 20;
    public static final int OTHER_CMMB_CHANEL = 34;
    public static final int OTHER_DITHER = 26;
    public static final int OTHER_DTV_TYPE = 4;
    public static final int OTHER_DVR_TYPE = 3;
    public static final int OTHER_ECD_SWAP = 25;
    public static final int OTHER_EMMC_INFO = 8;
    public static final int OTHER_EXAMP = 40;
    public static final int OTHER_INSTAL = 31;
    public static final int OTHER_LAST_MEMORY = 21;
    public static final int OTHER_LCD_MODE = 27;
    public static final int OTHER_LCM_TYPE = 0;
    public static final int OTHER_MAX = 42;
    public static final int OTHER_MCU_TYPE = 11;
    public static final int OTHER_MEM_INFO = 7;
    public static final int OTHER_MIN = 0;
    public static final int OTHER_MODEL_NAME = 42;
    public static final int OTHER_NAVI_ON = 14;
    public static final int OTHER_NOISE = 33;
    public static final int OTHER_OPT_COLOR_LIGHT = 15;
    public static final int OTHER_OPT_LOGO = 16;
    public static final int OTHER_OPT_SW = 17;
    public static final int OTHER_REAR = 30;
    public static final int OTHER_SCR_ORIGN = 37;
    public static final int OTHER_SCR_TYPE = 5;
    public static final int OTHER_SCR_X = 38;
    public static final int OTHER_SCR_Y = 39;
    public static final int OTHER_USB1_SPEED = 22;
    public static final int OTHER_USB2_SPEED = 23;
    public static final int OTHER_USBA = 2;
    public static final int OTHER_USB_DEBUG = 24;
    public static final int OTHER_VER = 29;
    public static final String TAG = "FsOtherActivity";
    private FsInputDlg mBtDlg;
    private char[] mBtName = new char[32];
    private FsInputDlg mDbgDlg;
    private Fs2SelItem mItem8824;
    private Fs2SelItem mItemACOpt;
    private FsAdjItem mItemAmp;
    private Fs2SelItem mItemAudioIC;
    private Fs2SelItem mItemAutoNavi;
    private Fs2SelItem mItemBTMode;
    private Fs2SelItem mItemBTvolSync;
    private Fs2SelItem mItemBackLineOpt;
    private Fs2SelItem mItemBootWarn;
    private FsAdjItem mItemBrake;
    private FsInputItem mItemBtName;
    private Fs2SelItem mItemCamDetect;
    private Fs2SelItem mItemCamMirror;
    private FsAdjItem mItemCamMute;
    private Fs2SelItem mItemColorLight;
    private Fs2SelItem mItemDebugOut;
    private Fs2SelItem mItemDtvCh;
    private FsAdjItem mItemDtvType;
    private FsAdjItem mItemDvrType;
    private Fs2SelItem mItemEcdSwap;
    private FsAdjItem mItemEmmcInfo;
    private FsAdjItem mItemExtUart;
    private Fs2SelItem mItemGpsMode;
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
    private Fs2SelItem mItemSWCOpt;
    private FsAdjItem mItemScrOrign;
    private FsAdjItem mItemScrType;
    private FsAdjItem mItemScrX;
    private FsAdjItem mItemScrY;
    private Fs2SelItem mItemTvFormat;
    private Fs2SelItem mItemUsb1Speed;
    private Fs2SelItem mItemUsb2Speed;
    private Fs2SelItem mItemUsbDebug;
    private FsAdjItem mItemVerInfo;
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
        this.mItemExtUart = new FsAdjItem((ViewGroup) rl, 2, R.string.fs_other_usba, 0, (View.OnClickListener) this);
        this.mItemDvrType = new FsAdjItem((ViewGroup) rl, 3, R.string.fs_other_dvr_type, 0, (View.OnClickListener) this);
        this.mItemDtvType = new FsAdjItem((ViewGroup) rl, 4, R.string.fs_other_dtv_type, 0, (View.OnClickListener) this);
        this.mItemScrType = new FsAdjItem((ViewGroup) rl, 5, R.string.fs_other_scr_type, 0, (View.OnClickListener) this);
        this.mItemScrOrign = new FsAdjItem((ViewGroup) rl, 37, R.string.fs_other_scr_origh, 0, (View.OnClickListener) this);
        this.mItemScrX = new FsAdjItem((ViewGroup) rl, 38, R.string.fs_other_scr_x, 0, (View.OnClickListener) this);
        this.mItemScrY = new FsAdjItem((ViewGroup) rl, 39, R.string.fs_other_scr_y, 0, (View.OnClickListener) this);
        this.mItemCamMute = new FsAdjItem((ViewGroup) rl, 6, R.string.fs_other_cam_mute, R.array.str_fsother_arrparkmute, (View.OnClickListener) this);
        this.mItemMemInfo = new FsAdjItem((ViewGroup) rl, 7, R.string.fs_other_mem_info, 0, (View.OnClickListener) this);
        this.mItemEmmcInfo = new FsAdjItem((ViewGroup) rl, 8, R.string.fs_other_Emmc_info, 0, (View.OnClickListener) this);
        this.mItemLineType = new FsAdjItem((ViewGroup) rl, 9, R.string.fs_other_Line_type, 0, (View.OnClickListener) this);
        this.mItemLinePos = new FsAdjItem((ViewGroup) rl, 10, R.string.fs_other_Line_pos, 0, (View.OnClickListener) this);
        this.mItemMcuType = new FsAdjItem((ViewGroup) rl, 11, R.string.fs_other_mcu_type, 0, (View.OnClickListener) this);
        this.mItemAmp = new FsAdjItem((ViewGroup) rl, 40, R.string.fs_other_amp, 0, (View.OnClickListener) this);
        this.mItemCamDetect = new Fs2SelItem((ViewGroup) rl, 12, R.string.fs_other_cam_detect, R.string.fs_set_not_detect, R.string.fs_set_detect, (View.OnClickListener) this);
        this.mItemCamMirror = new Fs2SelItem((ViewGroup) rl, 13, R.string.fs_other_cam_mirror, R.string.fs_set_not_mirror, R.string.fs_set_mirror, (View.OnClickListener) this);
        this.mItemAutoNavi = new Fs2SelItem((ViewGroup) rl, 14, R.string.fs_other_auto_navi, R.string.fs_set_not_start, R.string.fs_set_qd, (View.OnClickListener) this);
        this.mItemColorLight = new Fs2SelItem((ViewGroup) rl, 15, R.string.fs_other_color_light, R.string.fs_set_no, R.string.fs_set_have, (View.OnClickListener) this);
        this.mItemLogoOpt = new Fs2SelItem((ViewGroup) rl, 16, R.string.fs_other_logo_opt, R.string.fs_set_no, R.string.fs_set_have, (View.OnClickListener) this);
        this.mItemSWCOpt = new Fs2SelItem((ViewGroup) rl, 17, R.string.fs_other_swc_opt, R.string.fs_set_no, R.string.fs_set_have, (View.OnClickListener) this);
        this.mItemACOpt = new Fs2SelItem((ViewGroup) rl, 18, R.string.fs_other_ac_opt, R.string.fs_set_no, R.string.fs_set_have, (View.OnClickListener) this);
        this.mItemBackLineOpt = new Fs2SelItem((ViewGroup) rl, 19, R.string.fs_other_back_opt, R.string.fs_set_no, R.string.fs_set_have, (View.OnClickListener) this);
        this.mItemBootWarn = new Fs2SelItem((ViewGroup) rl, 20, R.string.fs_other_boot_warn, R.string.fs_android_v_l, R.string.fs_android_v_h, (View.OnClickListener) this);
        this.mItemLastMemory = new Fs2SelItem((ViewGroup) rl, 21, R.string.fs_other_last_mem, R.string.fs_set_not_mem, R.string.fs_set_memory, (View.OnClickListener) this);
        this.mItemUsb1Speed = new Fs2SelItem((ViewGroup) rl, 22, res.getString(R.string.fs_other_usb1_speed), "2.0", "1.1", (View.OnClickListener) this);
        this.mItemUsb2Speed = new Fs2SelItem((ViewGroup) rl, 23, res.getString(R.string.fs_other_usb2_speed), "2.0", "1.1", (View.OnClickListener) this);
        this.mItemUsbDebug = new Fs2SelItem((ViewGroup) rl, 24, R.string.fs_other_usb2_dbg, R.string.fs_set_off, R.string.fs_set_kq, (View.OnClickListener) this);
        this.mItemEcdSwap = new Fs2SelItem((ViewGroup) rl, 25, R.string.fs_other_ecd_swap, R.string.fs_set_not_swap, R.string.fs_set_swap, (View.OnClickListener) this);
        this.mItemDebugOut = new Fs2SelItem((ViewGroup) rl, 26, R.string.fs_other_debug_out, R.string.fs_set_off, R.string.fs_set_kq, (View.OnClickListener) this);
        this.mItemTvFormat = new Fs2SelItem((ViewGroup) rl, 27, res.getString(R.string.fs_other_tv_out), "8bit", "6bit", (View.OnClickListener) this);
        if (Build.VERSION.SDK_INT == 27) {
            this.mItemBTMode = new Fs2SelItem((ViewGroup) rl, 35, R.string.title_activity_bt, R.string.fs_android_v_l, R.string.fs_android_v_h, (View.OnClickListener) this);
        }
        this.mItemVerInfo = new FsAdjItem((ViewGroup) rl, 29, R.string.fs_other_sd_swap, 0, (View.OnClickListener) this);
        this.mItemVerInfo.mVal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                new FsInputDlg().createDlg(FsOtherActivity.this, new FsInputDlg.onInputOK() {
                    public void onOK(String strVal) {
                        if (strVal != null && strVal.length() > 0) {
                            try {
                                FtSet.SetSdSwap(Integer.parseInt(strVal));
                                FsOtherActivity.this.UpdateItem(29);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, true);
            }
        });
        this.mItemGpsMode = new Fs2SelItem((ViewGroup) rl, 30, R.string.fs_other_gps_mode, R.string.fs_set_off, R.string.fs_set_kq, (View.OnClickListener) this);
        this.mItemInstall = new Fs2SelItem((ViewGroup) rl, 31, R.string.fs_other_install, R.string.fs_set_kq, R.string.fs_set_off, (View.OnClickListener) this);
        if (MainSet.GetInstance().Is3561()) {
            this.mItem8824 = new Fs2SelItem((ViewGroup) rl, 32, R.string.fs_other_cameramode, R.string.fs_set_no, R.string.fs_set_have, (View.OnClickListener) this);
        } else {
            this.mItem8824 = new Fs2SelItem((ViewGroup) rl, 32, R.string.fs_other_cameramode, R.string.fs_set_kq, R.string.fs_set_off, (View.OnClickListener) this);
        }
        this.mItemNoise = new Fs2SelItem((ViewGroup) rl, 33, R.string.fs_other_denoise, R.string.fs_set_no, R.string.fs_set_have, (View.OnClickListener) this);
        this.mItemDtvCh = new Fs2SelItem((ViewGroup) rl, 34, R.string.fs_other_dvtch, R.string.title_activity_cmmb_main, R.string.title_activity_avin_main, (View.OnClickListener) this);
        this.mItemAudioIC = new Fs2SelItem((ViewGroup) rl, 36, R.string.fs_other_audioic, R.string.fs_other_audionodsp, R.string.fs_other_audioicdsp, (View.OnClickListener) this);
        this.mItemBTvolSync = new Fs2SelItem((ViewGroup) rl, 41, R.string.fs_other_btvol, R.string.fs_other_btvoasync, R.string.fs_other_btvosync, (View.OnClickListener) this);
        this.mItemBtName = new FsInputItem((ViewGroup) rl, 28, R.string.fs_other_bt_name, (View.OnClickListener) this);
        this.mItemModelName = new FsInputItem((ViewGroup) rl, 42, R.string.fs_other_modlename, (View.OnClickListener) this);
        this.mBtDlg = new FsInputDlg();
        this.mDbgDlg = new FsInputDlg();
        this.mModelDlg = new FsInputDlg();
        for (int i = 0; i <= 42; i++) {
            UpdateItem(i);
        }
    }

    public static boolean isTs3561() {
        return MainSet.GetInstance().Is3561();
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
            case 2:
                FtSet.SetUsbCurrent(ValCal.dataStepNoLoop(FtSet.GetUsbCurrent(), 0, 7, inc));
                if (FtSet.GetUsbCurrent() != 0 && FtSet.GetUsbCurrent() != 5) {
                    setUsbDvrEnhancement(false);
                    break;
                } else {
                    setUsbDvrEnhancement(true);
                    break;
                }
            case 3:
                FtSet.SetDvrType(ValCal.dataStepNoLoop(FtSet.GetDvrType(), 0, 99, inc));
                break;
            case 4:
                FtSet.SetDtvType(ValCal.dataStepNoLoop(FtSet.GetDtvType(), 0, 99, inc));
                break;
            case 5:
                FtSet.SetCtType(ValCal.dataStepNoLoop(FtSet.GetCtType(), 0, 99, inc));
                break;
            case 6:
                FtSet.SetParkMuteDef(ValCal.dataStepNoLoop(FtSet.GetParkMuteDef(), 0, 3, inc));
                break;
            case 7:
                FtSet.SetRam(ValCal.dataStepNoLoop(FtSet.GetRam(), 0, 8, inc));
                break;
            case 8:
                FtSet.SetRom(ValCal.dataStepNoLoop(FtSet.GetRom(), 0, 8, inc));
                break;
            case 9:
                FtSet.SetLineType(ValCal.dataStepNoLoop(FtSet.GetLineType(), 0, 2, inc));
                break;
            case 10:
                FtSet.SetLinePos(ValCal.dataStepNoLoop(FtSet.GetLinePos(), -200, 200, inc));
                break;
            case 11:
                FtSet.SetMcuType(ValCal.dataStepNoLoop(FtSet.GetMcuType(), 0, 10, inc));
                break;
            case 12:
                FtSet.SetCamFix(val);
                break;
            case 13:
                FtSet.SetCamMir(val);
                break;
            case 14:
                FtSet.SetAutoNaviDef(val);
                break;
            case 15:
                FtSet.SetOptionIll(val);
                break;
            case 16:
                FtSet.SetOptionLogo(val);
                break;
            case 17:
                FtSet.SetOptionSW(val);
                break;
            case 18:
                FtSet.SetOptionAC(val);
                break;
            case 19:
                FtSet.SetOptionCam(val);
                break;
            case 20:
                FtSet.SetOptionWarn(val);
                break;
            case 21:
                FtSet.SetLastMemory(val);
                break;
            case 22:
                FtSet.SetUsb1Spd(val);
                setUsb0Procotol(val);
                break;
            case 23:
                FtSet.SetUsb2Spd(val);
                setUsbProcotol(val);
                break;
            case 24:
                if (FtSet.GetUsbHost() == 0 && val != 0) {
                    Log.e(TAG, "GetUsbHost");
                    setUsbMode(1);
                    break;
                } else {
                    setUsbMode(0);
                    break;
                }
            case 25:
                FtSet.SetEcdSwap(val);
                break;
            case 26:
                FtSet.SetDither(val);
                break;
            case 27:
                FtSet.SetLcdMode(val);
                break;
            case 28:
                this.mBtDlg.createDlg(this, this, false);
                break;
            case 29:
                FtSet.SetSdSwap(ValCal.dataStepNoLoop(FtSet.GetSdSwap(), 0, 10, inc));
                sendBroadcast(new Intent(ENVIRONMENT_CHANGE));
                break;
            case 30:
                FtSet.SetGnssMode(val);
                break;
            case 31:
                if (FtSet.GetApkForbid() != 1) {
                    FtSet.SetApkForbid(1);
                    SystemProperties.set("forfan.notallow.install", MainSet.SP_XPH5);
                    break;
                } else {
                    this.mDbgDlg.createDlg(this, new FsInputDlg.onInputOK() {
                        public void onOK(String strVal) {
                            if (strVal != null && strVal.equals(FsOtherActivity.this.getResources().getString(R.string.custom_apk_install_pwd))) {
                                FtSet.SetApkForbid(0);
                                SystemProperties.set("forfan.notallow.install", "0");
                                FsOtherActivity.this.UpdateItem(31);
                            }
                        }
                    }, true);
                    break;
                }
            case 32:
                FtSet.SetCam8824(val);
                break;
            case 33:
                FtSet.SetNoiseDown(val);
                break;
            case 34:
                FtSet.SetExUart(val);
                break;
            case 35:
                if (Build.VERSION.SDK_INT >= 27) {
                    FtSet.SetBlueEn(val);
                    if (val != 1) {
                        SystemProperties.set("persist.atc.bt.disablessp", "false");
                        break;
                    } else {
                        SystemProperties.set("persist.atc.bt.disablessp", "true");
                        break;
                    }
                }
                break;
            case 36:
                FtSet.SetDspEn(val);
                break;
            case 40:
                FtSet.SetExAmp(ValCal.dataStepNoLoop(FtSet.GetExAmp(), 0, 1, inc));
                break;
            case 41:
                FtSet.SetBlueSync(val);
                break;
            case 42:
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
                        FsOtherActivity.this.UpdateItem(42);
                    }
                }, false);
                break;
        }
        if (28 != id || 42 != id) {
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
            case 2:
                this.mItemExtUart.SetVal(FtSet.GetUsbCurrent());
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
                this.mItemCamMute.SetVal(FtSet.GetParkMuteDef());
                return;
            case 7:
                this.mItemMemInfo.SetVal(FtSet.GetRam());
                return;
            case 8:
                this.mItemEmmcInfo.SetVal(FtSet.GetRom());
                return;
            case 9:
                this.mItemLineType.SetVal(FtSet.GetLineType());
                return;
            case 10:
                this.mItemLinePos.SetVal(FtSet.GetLinePos());
                return;
            case 11:
                this.mItemMcuType.SetVal(FtSet.GetMcuType());
                return;
            case 12:
                this.mItemCamDetect.SetVal(FtSet.GetCamFix());
                return;
            case 13:
                this.mItemCamMirror.SetVal(FtSet.GetCamMir());
                return;
            case 14:
                this.mItemAutoNavi.SetVal(FtSet.GetAutoNaviDef());
                return;
            case 15:
                this.mItemColorLight.SetVal(FtSet.GetOptionIll());
                return;
            case 16:
                this.mItemLogoOpt.SetVal(FtSet.GetOptionLogo());
                return;
            case 17:
                this.mItemSWCOpt.SetVal(FtSet.GetOptionSW());
                return;
            case 18:
                this.mItemACOpt.SetVal(FtSet.GetOptionAC());
                return;
            case 19:
                this.mItemBackLineOpt.SetVal(FtSet.GetOptionCam());
                return;
            case 20:
                this.mItemBootWarn.SetVal(FtSet.GetOptionWarn());
                return;
            case 21:
                this.mItemLastMemory.SetVal(FtSet.GetLastMemory());
                return;
            case 22:
                this.mItemUsb1Speed.SetVal(FtSet.GetUsb1Spd());
                Log.d(TAG, "FtSet.GetUsb1Spd() = " + FtSet.GetUsb1Spd());
                return;
            case 23:
                this.mItemUsb2Speed.SetVal(FtSet.GetUsb2Spd());
                Log.d(TAG, "FtSet.GetUsb2Spd() = " + FtSet.GetUsb2Spd());
                return;
            case 24:
                this.mItemUsbDebug.SetVal(FtSet.GetUsbHost());
                return;
            case 25:
                this.mItemEcdSwap.SetVal(FtSet.GetEcdSwap());
                return;
            case 26:
                this.mItemDebugOut.SetVal(FtSet.GetDither());
                return;
            case 27:
                this.mItemTvFormat.SetVal(FtSet.GetLcdMode());
                return;
            case 28:
                FtSet.GetBtName(this.mBtName);
                String strName = new String();
                int i = 0;
                while (i < this.mBtName.length && this.mBtName[i] != 0) {
                    strName = String.valueOf(strName) + this.mBtName[i];
                    i++;
                }
                this.mItemBtName.SetVal(strName);
                return;
            case 29:
                if (this.mItemVerInfo != null) {
                    this.mItemVerInfo.SetVal(FtSet.GetSdSwap());
                    return;
                }
                return;
            case 30:
                this.mItemGpsMode.SetVal(FtSet.GetGnssMode());
                return;
            case 31:
                this.mItemInstall.SetVal(FtSet.GetApkForbid());
                return;
            case 32:
                this.mItem8824.SetVal(FtSet.GetCam8824());
                return;
            case 33:
                this.mItemNoise.SetVal(FtSet.GetNoiseDown());
                return;
            case 34:
                this.mItemDtvCh.SetVal(FtSet.GetExUart());
                return;
            case 35:
                if (this.mItemBTMode != null) {
                    this.mItemBTMode.SetVal(FtSet.GetBlueEn());
                    return;
                }
                return;
            case 36:
                this.mItemAudioIC.SetVal(FtSet.GetDspEn());
                return;
            case 37:
                this.mItemScrOrign.SetVal(FtSet.GetCtXYswap());
                return;
            case 38:
                this.mItemScrX.SetVal(FtSet.GetCtXYrange() / 65536);
                return;
            case 39:
                this.mItemScrY.SetVal(FtSet.GetCtXYrange() % 65536);
                return;
            case 40:
                this.mItemAmp.SetVal(FtSet.GetExAmp());
                return;
            case 41:
                this.mItemBTvolSync.SetVal(FtSet.GetBlueSync());
                return;
            case 42:
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
        UpdateItem(28);
    }
}
