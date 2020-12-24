package com.ts.main.txz;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.bt.BtExe;
import com.ts.main.common.PhoneUtils;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import net.easyconn.platform.wrc.core.ICode;
import net.easyconn.platform.wrc.core.WrcDevice;
import net.easyconn.platform.wrc.core.WrcManager;

public class Wrc {
    static Wrc m_wrc;
    /* access modifiers changed from: private */
    public WrcManager.WrcCallback mWrcCallback = new WrcManager.WrcCallback() {
        public void onConnected(WrcDevice device) {
            Log.d("lh", "connected");
            Toast.makeText(Wrc.this.myContext, Wrc.this.myContext.getResources().getString(R.string.wrc_connect), 0).show();
        }

        public void onDisconnected(WrcDevice device) {
            Log.d("lh", "diconnected");
            Wrc.GetInstance().startScan();
        }

        public void onWrcKeyEvent(byte keyCode, byte action) {
            switch (keyCode) {
                case -93:
                    Mcu.SetCkey(16);
                    return;
                case 1:
                    switch (action) {
                        case -95:
                            if (FtSet.GetUartDbg() == 1) {
                                Mcu.SetCkey(44);
                                return;
                            } else {
                                Mcu.SetCkey(45);
                                return;
                            }
                        case -93:
                            Log.d("lh", "right_up_long");
                            return;
                        default:
                            return;
                    }
                case 2:
                    switch (action) {
                        case -95:
                            if (FtSet.GetUartDbg() == 1) {
                                Mcu.SetCkey(45);
                                return;
                            } else {
                                Mcu.SetCkey(44);
                                return;
                            }
                        default:
                            return;
                    }
                case 4:
                    switch (action) {
                        case -95:
                            if (FtSet.GetUartDbg() == 1) {
                                Mcu.SetCkey(20);
                                return;
                            } else {
                                Mcu.SetCkey(19);
                                return;
                            }
                        case -93:
                            Log.d("lh", "left_up_long");
                            return;
                        default:
                            return;
                    }
                case 8:
                    switch (action) {
                        case -95:
                            if (FtSet.GetUartDbg() == 1) {
                                Mcu.SetCkey(19);
                                return;
                            } else {
                                Mcu.SetCkey(20);
                                return;
                            }
                        default:
                            return;
                    }
                case 16:
                    switch (action) {
                        case -95:
                            BtExe.getBtInstance();
                            if (BtExe.mCallSta == 3) {
                                BtExe.getBtInstance().answer();
                                return;
                            } else if (Evc.GetInstance().PhoneState == 1) {
                                PhoneUtils.answerCall((TelephonyManager) Wrc.this.myContext.getSystemService("phone"));
                                return;
                            } else if (Evc.GetInstance().PhoneState == 2) {
                                PhoneUtils.rejectCall((TelephonyManager) Wrc.this.myContext.getSystemService("phone"));
                                return;
                            } else if (BtExe.getBtInstance().isHaveCall()) {
                                BtExe.getBtInstance().hangup();
                                return;
                            } else {
                                Mcu.SetCkey(95);
                                return;
                            }
                        default:
                            return;
                    }
                default:
                    return;
            }
        }

        public void onCharacteristicRead(WrcDevice device) {
        }

        public void onError(int code) {
            switch (code) {
                case 3:
                    return;
                case 4:
                    return;
                case ICode.ERROR_PACKAGE_NO_BACKUP /*9001*/:
                    return;
                case ICode.ERROR_KEY /*9002*/:
                    return;
                case ICode.ERROR_PACKAGE_NAME_NULL /*9003*/:
                    return;
                case ICode.ERROR_KEY_NULL /*9004*/:
                    return;
                case ICode.ERROR_PROJECT_NUMBER_NULL /*9011*/:
                    return;
                case ICode.ERROR_PROJECT_NUMBER /*9012*/:
                    return;
                case ICode.ERROR_PACKAGE_PROJECT_NUMBER /*9013*/:
                    return;
                case ICode.ERROR_SCAN_UUID_NULL /*9080*/:
                    return;
                case ICode.ERROR_UUID_CHECK /*9081*/:
                    return;
                default:
                    return;
            }
        }
    };
    private WrcManager.ScanCallback mWrcScanCallback = new WrcManager.ScanCallback() {
        public List<UUID> getUuidFilter() {
            try {
                return Arrays.asList(new UUID[]{UUID.fromString("00001C00-D102-11E1-9B23-00025b01aab2"), UUID.fromString("0000474d-0000-1000-8000-00805f9b34fb")});
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public void onWrcScan(WrcDevice device) {
            Log.d("lh", "scan device");
            WrcManager.getInstance().connectWrc(device, Wrc.this.mWrcCallback);
        }

        public void onScanError(int code) {
            switch (code) {
                case 1:
                    return;
                case 2:
                    return;
                case ICode.ERROR_SCAN_UUID_NULL /*9080*/:
                    return;
                default:
                    return;
            }
        }
    };
    /* access modifiers changed from: private */
    public Context myContext = null;

    public static Wrc GetInstance() {
        if (m_wrc == null) {
            m_wrc = new Wrc();
        }
        return m_wrc;
    }

    public void Inint(Context context) {
        this.myContext = context;
        WrcManager.getInstance().init(this.myContext, "c96129b4721a70058042168e3641021d", "demo");
    }

    public void Task() {
    }

    public boolean isEnable() {
        return WrcManager.getInstance().isBluetoothEnabled();
    }

    public boolean isConnectWrc() {
        return WrcManager.getInstance().isConnectWrc();
    }

    public void startScan() {
        if (!WrcManager.getInstance().isConnectWrc()) {
            WrcManager.getInstance().startWrcScan(this.mWrcScanCallback);
        }
    }

    public void stopScan() {
        WrcManager.getInstance().stopWrcScan();
    }

    public void destroy() {
        WrcManager.getInstance().destroy();
    }

    public void connectWrc(WrcDevice device) {
        WrcManager.getInstance().connectWrc(device, this.mWrcCallback);
    }
}
