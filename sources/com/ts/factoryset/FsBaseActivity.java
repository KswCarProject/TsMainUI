package com.ts.factoryset;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.ServiceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.factoryset.NvRAMAgent;
import com.ts.main.common.MainSet;
import com.ts.main.common.tool;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FsBaseActivity extends Activity implements View.OnClickListener {
    public static final String TAG = "FsBaseActivity";
    private static NvRAMAgent mAgent = null;
    public static UsbMode mCurrentUsbMode = UsbMode.USB_MODE_UNKNOW;
    public static UsbProcotol mCurrentUsbProcotol = UsbProcotol.USB_PROCOTOL_UNKNOW;
    private static String usbDeviceMode = "b_peripheral";
    private static String usbHostMode = "a_host";
    private static final String usbModeFileName = "/sys/devices/platform/mt_usb/musb-hdrc.0.auto/mode";
    private static final String usbNvramFile = "/data/nvram/APCFG/APRDEB/USB";
    private static final int usb_file_lid_id = 71;
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
                tool.GetInstance().importLauncherInfo();
                AlertDialog.Builder build = new AlertDialog.Builder(FsBaseActivity.this);
                build.setTitle(R.string.str_fs_tip);
                build.setCancelable(false);
                build.setMessage(text);
                if (ret == 0) {
                    build.setPositiveButton(R.string.str_fsmp_ok, (DialogInterface.OnClickListener) null);
                } else {
                    MainSet.writeFtsetLauncher(true);
                    FsBaseActivity.this.setUsbProcotol(FtSet.GetUsb2Spd());
                    FsBaseActivity.this.setUsb0Procotol(FtSet.GetUsb1Spd());
                    build.setPositiveButton(R.string.str_fs_reset, FsBaseActivity.this.mResetClick);
                }
                build.show();
                Log.e(FsBaseActivity.TAG, "************FtSetLoadFromSd************");
            } else if (id == R.id.fs_top_btn_save) {
                FsBaseActivity.this.onSave();
                FtSet.Save(1);
                Evc.GetInstance().SetMicGain();
                MainSet.GetInstance().DeleteTempFile();
                MainSet.writeFtsetLauncher(true);
                if (FtSet.GetCanTp() == 0 || FtSet.IsIconExist(122) != 0) {
                    showResetDialog();
                    return;
                }
                new AlertDialog.Builder(FsBaseActivity.this).setTitle(R.string.str_fs_tip).setMessage(String.format(FsBaseActivity.this.getResources().getString(R.string.str_fs_carinfo_icon_choose), new Object[]{FsBaseActivity.this.getResources().getStringArray(R.array.menu_ico_arr)[21]})).setCancelable(false).setOnDismissListener(new DialogInterface.OnDismissListener() {
                    public void onDismiss(DialogInterface arg0) {
                        AnonymousClass2.this.showResetDialog();
                    }
                }).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        FtSet.GetIcon(FsBaseActivity.this.mFtIco);
                        int i = 0;
                        while (true) {
                            if (i < FsBaseActivity.this.mFtIco.length) {
                                if (FsBaseActivity.this.mFtIco[i] == 0) {
                                    FsBaseActivity.this.mFtIco[i] = 22;
                                    break;
                                }
                                i++;
                            } else {
                                break;
                            }
                        }
                        FtSet.SetIcon(FsBaseActivity.this.mFtIco);
                        FtSet.Save(1);
                        MainSet.writeFtsetLauncher(true);
                    }
                }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).show();
            }
        }

        /* access modifiers changed from: private */
        public void showResetDialog() {
            new AlertDialog.Builder(FsBaseActivity.this).setTitle(R.string.str_fs_tip).setMessage(R.string.str_fs_savereset).setCancelable(false).setPositiveButton(R.string.str_fs_reset, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Mcu.SendXKey(20);
                }
            }).show();
        }
    };
    private Button mBtnExport;
    private Button mBtnImport;
    /* access modifiers changed from: private */
    public int[] mFtIco = new int[100];
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
                    Mcu.SendXKey(20);
                }
            }.start();
        }
    };
    private TextView mTvTitle;

    private enum UsbMode {
        USB_MODE_UNKNOW,
        USB_MODE_HOST,
        USB_MODE_DEVICE
    }

    public enum UsbPort {
        USB_PORT0,
        USB_PORT1
    }

    private enum UsbProcotol {
        USB_PROCOTOL_UNKNOW,
        USB_PROCOTOL_11,
        USB_PROCOTOL_20
    }

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

    public void onClick(View v) {
        int id = v.getId();
        if (id != R.id.fs_top_btn_import && id == R.id.fs_top_btn_save) {
            onSave();
            FtSet.Save(0);
            MainSet.writeFtsetLauncher(true);
        }
    }

    public boolean setUsbDvrEnhancement(boolean enable) {
        return false;
    }

    public void setUsb0Procotol(int Val) {
    }

    public void setUsbProcotol(int nval) {
    }

    private static byte[] intToBytes(int num) {
        return new byte[]{(byte) (num & 255), (byte) ((num >> 8) & 255), (byte) ((num >> 16) & 255), (byte) ((num >> 24) & 255)};
    }

    private static byte[] makeUsbNvramData() {
        byte[] usbModeProtocolByte = null;
        int usbMode = mCurrentUsbMode.ordinal();
        byte[] protocolByte = intToBytes(mCurrentUsbProcotol.ordinal());
        byte[] modeByte = intToBytes(usbMode);
        if (!(protocolByte == null || modeByte == null)) {
            usbModeProtocolByte = new byte[(protocolByte.length + modeByte.length)];
        }
        if (usbModeProtocolByte != null) {
            System.arraycopy(protocolByte, 0, usbModeProtocolByte, 0, protocolByte.length);
            System.arraycopy(modeByte, 0, usbModeProtocolByte, protocolByte.length, modeByte.length);
        }
        return usbModeProtocolByte;
    }

    public static void setUsbMode(int val) {
        Log.d(TAG, "----setUsbMode()");
        if (val == 1) {
            mCurrentUsbMode = UsbMode.USB_MODE_DEVICE;
        } else {
            mCurrentUsbMode = UsbMode.USB_MODE_HOST;
        }
        if (mAgent == null) {
            mAgent = NvRAMAgent.Stub.asInterface(ServiceManager.getService("NvRAMAgent"));
        }
        File modeFile = new File(usbModeFileName);
        String str = null;
        byte[] bytes = makeUsbNvramData();
        if (mCurrentUsbMode == UsbMode.USB_MODE_HOST) {
            str = usbHostMode;
        } else if (mCurrentUsbMode == UsbMode.USB_MODE_DEVICE) {
            str = usbDeviceMode;
        }
        if (mAgent == null || bytes == null || str == null) {
            Log.e(TAG, "setUsbMode fail , mAgent: " + mAgent + ", str: " + str);
        } else if (modeFile == null || !modeFile.exists()) {
            Log.e(TAG, "setUsbMode fail , file(/sys/devices/platform/mt_usb/musb-hdrc.0.auto/mode) not exist ");
        } else {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(modeFile));
                Log.i(TAG, "setUsbMode , write nvram, id : 71   usbNvramFile = /data/nvram/APCFG/APRDEB/USB");
                mAgent.writeFileByName(usbNvramFile, bytes);
                Log.i(TAG, "setUsbMode , write kernel node, id : 71, str: " + str);
                writer.write(str, 0, str.length());
                writer.close();
                if (mCurrentUsbMode == UsbMode.USB_MODE_HOST) {
                    FtSet.SetUsbHost(0);
                } else if (mCurrentUsbMode == UsbMode.USB_MODE_DEVICE) {
                    FtSet.SetUsbHost(1);
                }
            } catch (Exception e) {
            }
        }
    }
}
