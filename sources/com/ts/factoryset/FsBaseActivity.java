package com.ts.factoryset;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.Camera;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbPort;
import android.hardware.usb.UsbPortStatus;
import android.os.Bundle;
import android.os.SystemProperties;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.android.SdkConstants;
import com.autochips.backcar.BackCar;
import com.autochips.metazone.AtcMetazone;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.ts.main.common.tool;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class FsBaseActivity extends Activity implements View.OnClickListener {
    private static final int METAZONE_START_ADDR = 65536;
    public static final String TAG = "FsBaseActivity";
    private static final int USB_CARPLAY_INDEX = 90;
    private static final int USB_DVR_INDEX = 82;
    private static final int USB_MODE_INDEX = 80;
    private static final int USB_PORT0_DVRE = 1;
    private static final int USB_PORT0_PROTOCOL_1_1 = 1;
    private static final int USB_PORT0_PROTOCOL_2_0 = 2;
    private static final int USB_PORT1_DVRE = 2;
    private static final int USB_PORT1_PROTOCOL_1_1 = 256;
    private static final int USB_PORT1_PROTOCOL_2_0 = 512;
    private static final int USB_PORT1_PROTOCOL_3_0 = 1024;
    private static final int USB_PROTOCOL_INDEX = 81;
    /* access modifiers changed from: private */
    public static int[] mFtIco = new int[100];
    static int numberOfCameras = 0;
    private final int USB_MODE_DEVICE = 1;
    private final int USB_MODE_HOST = 2;
    private final int USB_MODE_UNKNOWN = 0;
    View.OnClickListener baseClick = new View.OnClickListener() {
        public void onClick(View v) {
            int text;
            int id = v.getId();
            if (id == R.id.fs_top_btn_import) {
                int ret = FtSet.LoadFromSd();
                if (ret == 0) {
                    text = R.string.str_fs_import_faild;
                } else if (ret <= 2) {
                    text = R.string.str_fs_sd_import_reset;
                } else {
                    text = R.string.str_fs_usb_import_reset;
                }
                if (ret == 0) {
                    FsBaseActivity.this.m_dialgo = new AlertDialog.Builder(FsBaseActivity.this).setTitle(R.string.str_fs_tip).setCancelable(false).setMessage(text).setPositiveButton(R.string.str_fsmp_ok, (DialogInterface.OnClickListener) null).show();
                } else {
                    MainSet.writeFtsetLauncher(true);
                    FsBaseActivity.SyncMetZoneData();
                    FsBaseActivity.this.m_dialgo = new AlertDialog.Builder(FsBaseActivity.this).setTitle(R.string.str_fs_tip).setCancelable(false).setMessage(text).setPositiveButton(R.string.str_fs_reset, FsBaseActivity.this.mResetClick).show();
                }
                MainSet.GetInstance().RefreshDialog(FsBaseActivity.this, FsBaseActivity.this.m_dialgo);
                Log.e(FsBaseActivity.TAG, "************FtSetLoadFromSd************");
            } else if (id == R.id.fs_top_btn_save) {
                FsBaseActivity.this.onSave();
                FtSet.Save(1);
                MainSet.GetInstance().DeleteTempFile();
                FsBaseActivity.SyncMetZoneData();
                if (FtSet.GetCanTp() == 0 || FtSet.IsIconExist(122) != 0) {
                    MainSet.writeFtsetLauncher(true);
                    showResetDialog();
                    return;
                }
                String msg = String.format(FsBaseActivity.this.getResources().getString(R.string.str_fs_carinfo_icon_choose), new Object[]{FsBaseActivity.this.getResources().getString(R.string.str_fs_carinfo)});
                FsBaseActivity.this.m_dialgo = new AlertDialog.Builder(FsBaseActivity.this).setTitle(R.string.str_fs_tip).setMessage(msg).setCancelable(false).setOnDismissListener(new DialogInterface.OnDismissListener() {
                    public void onDismiss(DialogInterface arg0) {
                        AnonymousClass2.this.showResetDialog();
                    }
                }).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        FtSet.GetIcon(FsBaseActivity.mFtIco);
                        int i = 0;
                        while (true) {
                            if (i < FsBaseActivity.mFtIco.length) {
                                if (FsBaseActivity.mFtIco[i] == 0) {
                                    FsBaseActivity.mFtIco[i] = 22;
                                    break;
                                }
                                i++;
                            } else {
                                break;
                            }
                        }
                        FtSet.SetIcon(FsBaseActivity.mFtIco);
                        FtSet.Save(1);
                        MainSet.writeFtsetLauncher(true);
                    }
                }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).show();
                MainSet.GetInstance().RefreshDialog(FsBaseActivity.this, FsBaseActivity.this.m_dialgo);
            }
        }

        /* access modifiers changed from: private */
        public void showResetDialog() {
            FsBaseActivity.this.m_dialgo = new AlertDialog.Builder(FsBaseActivity.this).setTitle(R.string.str_fs_tip).setMessage(R.string.str_fs_savereset).setCancelable(false).setPositiveButton(R.string.str_fs_reset, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    new Thread() {
                        public void run() {
                            try {
                                sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Mcu.SendXKey(19);
                        }
                    }.start();
                }
            }).show();
            MainSet.GetInstance().RefreshDialog(FsBaseActivity.this, FsBaseActivity.this.m_dialgo);
        }
    };
    private Button mBtnExport;
    private Button mBtnImport;
    private int mCurrentUsbMode = 0;
    private UsbPort mPort;
    DialogInterface.OnClickListener mResetClick = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            MainSet.UpdateSysLanguage();
            new Thread() {
                public void run() {
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Mcu.SendXKey(19);
                    MainSet.GetInstance().SystemReboot();
                }
            }.start();
        }
    };
    private TextView mTvTitle;
    private int mUsbDataRole = 0;
    private UsbManager mUsbManager;
    AlertDialog m_dialgo;

    /* access modifiers changed from: protected */
    public void onSave() {
    }

    /* access modifiers changed from: package-private */
    public void topBtnInit(int titleId) {
        this.mBtnImport = (Button) findViewById(R.id.fs_top_btn_import);
        this.mBtnExport = (Button) findViewById(R.id.fs_top_btn_save);
        if (this.mBtnImport != null) {
            this.mBtnImport.setOnClickListener(this.baseClick);
        }
        if (this.mBtnExport != null) {
            this.mBtnExport.setOnClickListener(this.baseClick);
        }
        this.mTvTitle = (TextView) findViewById(R.id.fs_top_title);
        if (this.mTvTitle != null) {
            this.mTvTitle.setText(titleId);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        this.mUsbManager = (UsbManager) getSystemService("usb");
    }

    public static void SyncMetZoneData() {
        ResetUsbMode();
        ReSetBackCarsource();
        ResetDither();
        ResetBtSyncMode();
        SetCarplayAddress();
        ResetLcdMode();
        MainSet.GetInstance().ReSetTimeZone();
    }

    static void ResetLcdMode() {
        switch (FtSet.GetLcdUpDown()) {
            case 0:
                AtcDisplaySettingsUtils.getInstance().setScreenOrientation(0, MainUI.GetSrcW(), MainUI.GetSrcH());
                break;
            case 1:
                AtcDisplaySettingsUtils.getInstance().setScreenOrientation(2, MainUI.GetSrcW(), MainUI.GetSrcH());
                break;
            case 2:
                AtcDisplaySettingsUtils.getInstance().setScreenOrientation(3, MainUI.GetSrcW(), MainUI.GetSrcH());
                break;
            case 3:
                AtcDisplaySettingsUtils.getInstance().setScreenOrientation(4, MainUI.GetSrcW(), MainUI.GetSrcH());
                break;
        }
        ResetMdp();
    }

    private static void ResetUsbMode() {
        setUsbProcotol(FtSet.GetUsb2Spd());
        setUsb0Procotol(FtSet.GetUsb1Spd());
        if (FtSet.GetUsbCurrent() != 0) {
            setUsbDvrEnhancement(true);
            setUsbPort1DvrEnhancement(false);
            return;
        }
        setUsbDvrEnhancement(false);
        setUsbPort1DvrEnhancement(false);
    }

    private static void ResetDither() {
        if (FtSet.GetDither() == 1) {
            AtcDisplaySettingsUtils.getInstance().setDither(1);
        } else {
            AtcDisplaySettingsUtils.getInstance().setDither(0);
        }
    }

    private static void ResetBtSyncMode() {
        if (FtSet.GetBlueSync() == 0) {
            SystemProperties.set("persist.bluetooth.atc.volumesync", "disable");
            SystemProperties.set("persist.bluetooth.atc.scovolumesync", "disable");
        } else {
            SystemProperties.set("persist.bluetooth.atc.volumesync", "enable");
            SystemProperties.set("persist.bluetooth.atc.scovolumesync", "enable");
        }
        if (FtSet.GetBlueEn() == 1) {
            SystemProperties.set("persist.atc.bt.disablessp", SdkConstants.VALUE_TRUE);
        } else {
            SystemProperties.set("persist.atc.bt.disablessp", SdkConstants.VALUE_FALSE);
        }
        Evc.GetInstance().SetMicGain();
    }

    public static void CanSetBackcartype(int nType, int srcX, int srcY) {
        if (!(nType == 65535 || FtSet.GetCamType() == nType)) {
            FtSet.SetCamType(nType);
            FtSet.Save(0);
            ReSetBackCarsource();
        }
        if (srcX != 65535 || srcY != 65535) {
            if (!(AtcDisplaySettingsUtils.getInstance().GetMipiUserScrX() == srcX && AtcDisplaySettingsUtils.getInstance().GetMipiUserScrY() == srcY)) {
                AtcDisplaySettingsUtils.getInstance().SetMipiUserScrX(srcX);
                AtcDisplaySettingsUtils.getInstance().SetMipiUserScrY(srcY);
                ResetMdp();
            }
            if (AtcDisplaySettingsUtils.getInstance().getAHDCameraMode() != 128) {
                AtcDisplaySettingsUtils.getInstance().setAHDCameraMode(128);
                ResetMdp();
            }
        }
    }

    static void SetFastAvmMode(int nState) {
        if (FtSet.GetFastAVM() != nState) {
            FtSet.SetFastAVM(nState);
            FtSet.Save(0);
        }
        if (AtcDisplaySettingsUtils.getInstance().getFastAVM() != nState) {
            AtcDisplaySettingsUtils.getInstance().setFastAVM(nState);
        }
    }

    static boolean IsAvmCamera() {
        if (FtSet.GetCamType() == 4 || FtSet.GetCamType() == 8 || FtSet.GetCamType() == 9 || FtSet.GetCamType() == 10) {
            return true;
        }
        return false;
    }

    public static void SetCamMir(int nSrcMode) {
        if (FtSet.GetCamMir() == 1) {
            AtcDisplaySettingsUtils.mLRMirrorStatusFlag = 1;
            AtcDisplaySettingsUtils.getInstance().setMirror(nSrcMode);
            return;
        }
        AtcDisplaySettingsUtils.mLRMirrorStatusFlag = 0;
        AtcDisplaySettingsUtils.getInstance().setMirror(nSrcMode);
    }

    public static void ReSetCamNum() {
        if (!MainSet.GetInstance().IsSupprotRemotecontrol()) {
            if (numberOfCameras == 0) {
                numberOfCameras = Camera.getNumberOfCameras();
            }
            Log.d(TAG, "numberOfCameras  =" + numberOfCameras);
            Log.d(TAG, "FtSet.GetCamType()  =" + FtSet.GetCamType());
            if (IsAvmCamera() && numberOfCameras < 5) {
                tool.GetInstance().RootSystem();
                tool.GetInstance().DealSu("chmod 0755 vendor/etc/atc_camera_config.xml");
                tool.GetInstance().DealSu("rm -r vendor/etc/atc_camera_config.xml");
                tool.GetInstance().DealSu("cp system/tsoem/camera/7/atc_camera_config.xml vendor/etc/atc_camera_config.xml");
                tool.GetInstance().DealSu("sync");
                Log.d(TAG, "copy 7");
                numberOfCameras = 7;
            } else if (FtSet.GetCamType() == 3 && numberOfCameras > 2) {
                tool.GetInstance().RootSystem();
                tool.GetInstance().DealSu("chmod 0755 vendor/etc/atc_camera_config.xml");
                tool.GetInstance().DealSu("rm -r vendor/etc/atc_camera_config.xml");
                tool.GetInstance().DealSu("cp system/tsoem/camera/2/atc_camera_config.xml vendor/etc/atc_camera_config.xml");
                tool.GetInstance().DealSu("sync");
                Log.d(TAG, "copy 2");
                numberOfCameras = 2;
            }
        } else {
            int numberOfCameras2 = Camera.getNumberOfCameras();
            Log.d(TAG, "ReSetBackCarsource  =" + numberOfCameras2);
            Log.d(TAG, "FtSet.GetCamType()  =" + FtSet.GetCamType());
            if (FtSet.GetCamType() == 3 && numberOfCameras2 < 5) {
                tool.GetInstance().RootSystem();
                tool.GetInstance().DealSu("chmod 0755 vendor/etc/atc_camera_config.xml");
                tool.GetInstance().DealSu("rm -r vendor/etc/atc_camera_config.xml");
                tool.GetInstance().DealSu("cp system/tsoem/camera/7/atc_camera_config.xml vendor/etc/atc_camera_config.xml");
                tool.GetInstance().DealSu("sync");
                Log.d(TAG, "copy 7");
            }
        }
    }

    public static void ReSetBackCarsource() {
        ReSetCamNum();
        switch (FtSet.GetCamType()) {
            case 0:
                AtcDisplaySettingsUtils.getInstance().setBackcarSourceMode(1);
                SetCamMir(1);
                SetFastAvmMode(0);
                break;
            case 1:
                AtcDisplaySettingsUtils.getInstance().setBackcarSourceMode(2);
                AtcDisplaySettingsUtils.getInstance().setAHDCameraMode(3);
                SetCamMir(2);
                SetFastAvmMode(0);
                break;
            case 2:
                AtcDisplaySettingsUtils.getInstance().setBackcarSourceMode(2);
                AtcDisplaySettingsUtils.getInstance().setAHDCameraMode(5);
                SetCamMir(2);
                SetFastAvmMode(0);
                break;
            case 3:
                AtcDisplaySettingsUtils.getInstance().setBackcarSourceMode(3);
                AtcDisplaySettingsUtils.getInstance().setAVMCameraMode(3);
                SetCamMir(2);
                SetFastAvmMode(FtSet.GetFastAVM());
                break;
            case 4:
            case 10:
                AtcDisplaySettingsUtils.getInstance().setBackcarSourceMode(3);
                AtcDisplaySettingsUtils.getInstance().setAVMCameraMode(3);
                SetFastAvmMode(0);
                SetCamMir(2);
                break;
            case 5:
                AtcDisplaySettingsUtils.getInstance().setBackcarSourceMode(2);
                AtcDisplaySettingsUtils.mLRMirrorStatusFlag = 0;
                AtcDisplaySettingsUtils.getInstance().setMirror(2);
                SetFastAvmMode(0);
                break;
            case 6:
                AtcDisplaySettingsUtils.getInstance().setBackcarSourceMode(2);
                AtcDisplaySettingsUtils.getInstance().setAHDCameraMode(4);
                SetCamMir(2);
                SetFastAvmMode(0);
                break;
            case 7:
                AtcDisplaySettingsUtils.getInstance().setBackcarSourceMode(2);
                AtcDisplaySettingsUtils.getInstance().setAHDCameraMode(6);
                SetCamMir(2);
                SetFastAvmMode(0);
                break;
            case 8:
                AtcDisplaySettingsUtils.getInstance().setBackcarSourceMode(3);
                AtcDisplaySettingsUtils.getInstance().setAVMCameraMode(9);
                SetFastAvmMode(0);
                SetCamMir(2);
                break;
            case 9:
                AtcDisplaySettingsUtils.getInstance().setBackcarSourceMode(3);
                AtcDisplaySettingsUtils.getInstance().setAVMCameraMode(10);
                SetFastAvmMode(0);
                SetCamMir(2);
                break;
        }
        MainSet.GetInstance();
        MainSet.writeFtSetInfo(true);
        ResetMdp();
    }

    public static void ResetMdp() {
        new BackCar().updateArm2MdpSetting();
    }

    /* access modifiers changed from: package-private */
    public void RefreshDialog(AlertDialog dialog) {
        Window dialogWindow = dialog.getWindow();
        Display d = getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.width = (int) (((double) d.getWidth()) * 0.5d);
        p.gravity = 17;
        dialogWindow.setAttributes(p);
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id != R.id.fs_top_btn_import && id == R.id.fs_top_btn_save) {
            onSave();
            FtSet.Save(0);
            SyncMetZoneData();
            MainSet.writeFtsetLauncher(true);
        }
    }

    public static void setUsbPort1DvrEnhancement(boolean enable) {
        int valdata;
        Log.d(TAG, "setUsbPort1DvrEnhancement");
        int[] value = new int[10];
        AtcMetazone.readval(65618, value);
        int realVal = value[0] & 15;
        if (enable) {
            valdata = realVal | 2;
        } else {
            valdata = realVal & -3;
        }
        AtcMetazone.writeval(65618, valdata);
        AtcMetazone.flush(true);
    }

    public static boolean setUsbDvrEnhancement(boolean enable) {
        int valdata;
        Log.d(TAG, "setUsbPort0DvrEnhancement");
        int[] value = new int[10];
        AtcMetazone.readval(65618, value);
        int realVal = value[0] & 15;
        if (enable) {
            valdata = realVal | 1;
        } else {
            valdata = realVal & -2;
        }
        AtcMetazone.writeval(65618, valdata);
        AtcMetazone.flush(true);
        return true;
    }

    public static void SetCarplayAddress() {
        int valdata;
        int nI2cPort = FtSet.GetiicMfi();
        if (nI2cPort == 0) {
            nI2cPort = 3;
        }
        Log.d(TAG, "SetCarplayAddress nI2cPort=" + nI2cPort);
        if (nI2cPort == 6) {
            valdata = (nI2cPort * 256) + 32;
            Log.d(TAG, "valdata =" + valdata);
        } else {
            valdata = (nI2cPort * 256) + 16;
            Log.d(TAG, "valdata =" + valdata);
        }
        if (ReadCarplayAddress() != valdata) {
            AtcMetazone.writeval(65626, valdata);
            AtcMetazone.flush(true);
        }
    }

    public static int ReadCarplayAddress() {
        int[] value = new int[10];
        AtcMetazone.readval(65626, value);
        int realVal = value[0];
        Log.d(TAG, "ReadCarplayAddress realVal=" + realVal);
        return realVal;
    }

    public static void setUsbProcotol(int nval) {
        Log.d(TAG, "setUsbPort1Protocol");
        int[] valdata = new int[2];
        AtcMetazone.readval(65617, valdata);
        Log.d(TAG, "valdata[0]=" + valdata[0]);
        if (nval == 0) {
            AtcMetazone.writeval(65617, (valdata[0] & 2147479807) | 512);
        } else if (nval == 1) {
            AtcMetazone.writeval(65617, (valdata[0] & 2147479807) | 256);
        } else {
            AtcMetazone.writeval(65617, (valdata[0] & 2147479807) | 1024);
        }
        AtcMetazone.flush(true);
    }

    public static void setUsb0Procotol(int Val) {
        Log.d(TAG, "setUsbPort0Protocol");
        int[] valdata = new int[2];
        AtcMetazone.readval(65617, valdata);
        Log.d(TAG, "valdata[0]=" + valdata[0]);
        if (Val == 0) {
            AtcMetazone.writeval(65617, (valdata[0] & 2147483632) | 2);
        } else {
            AtcMetazone.writeval(65617, (valdata[0] & 2147483632) | 1);
        }
        AtcMetazone.flush(true);
    }

    /* access modifiers changed from: package-private */
    public void setUsbMode(int val) {
        if (val == 1) {
            this.mCurrentUsbMode = 1;
            this.mUsbDataRole = 2;
        } else {
            this.mCurrentUsbMode = 2;
            this.mUsbDataRole = 1;
        }
        AtcMetazone.writeval(65616, this.mCurrentUsbMode);
        AtcMetazone.flush(true);
        UsbPort[] ports = this.mUsbManager.getPorts();
        if (ports == null) {
            Log.e(TAG, "ports is null");
            return;
        }
        int N = ports.length;
        int i = 0;
        while (true) {
            if (i >= N) {
                break;
            }
            UsbPortStatus status = this.mUsbManager.getPortStatus(ports[i]);
            Log.d(TAG, "port[" + i + "]=" + status.isConnected());
            if (status.isConnected()) {
                this.mPort = ports[i];
                break;
            }
            i++;
        }
        if (this.mPort == null) {
            Log.d(TAG, "setUsbMode Port is null");
            return;
        }
        if (this.mCurrentUsbMode == 2) {
            FtSet.SetUsbHost(0);
        } else if (this.mCurrentUsbMode == 1) {
            FtSet.SetUsbHost(1);
        }
        this.mUsbManager.setPortRoles(this.mPort, 1, this.mUsbDataRole);
    }
}
