package com.ts.main.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.CheckBox;
import android.widget.TextView;
import com.autochips.storage.EnvironmentATC;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.TsFile;
import com.ts.MainUI.UserCallBack;
import com.ts.main.common.MainSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;
import java.io.IOException;

public class FactorytestarmActivity extends Activity implements UserCallBack {
    private static final int ARM_TIME = 1000;
    static EnvironmentATC EnvATC = null;
    private static final String NO_ERR = " ";
    private static final String TAG = "[scj:]Test";
    int Num = 0;
    boolean bCheck = true;
    CheckBox cbIIC;
    CheckBox cbMCU;
    CheckBox cbSim;
    CheckBox cbwifiUART;
    CheckBox cbxEmmc;
    CheckBox cbxSD;
    int nMcu = 0;
    String strERR;
    String[] strMcuMe = {"BRK-IN", "AMP-POW", "CAN-RX", "IR", "RVS-IN"};
    TextView texmesTextView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory_testarm);
        this.strERR = NO_ERR;
        this.cbxEmmc = (CheckBox) findViewById(R.id.ck_factort_emmc);
        this.cbxSD = (CheckBox) findViewById(R.id.ck_factort_sd);
        this.cbSim = (CheckBox) findViewById(R.id.ck_factort_sim);
        this.cbIIC = (CheckBox) findViewById(R.id.ck_factort_iic);
        this.cbwifiUART = (CheckBox) findViewById(R.id.ck_factort_uart);
        this.cbMCU = (CheckBox) findViewById(R.id.ck_factort_mcu);
        this.texmesTextView = (TextView) findViewById(R.id.btn_fatctoryarmmes);
    }

    /* access modifiers changed from: package-private */
    public boolean bOK() {
        if (!this.cbxEmmc.isChecked() || !this.cbMCU.isChecked() || !this.cbxSD.isChecked() || !this.cbSim.isChecked() || !this.cbIIC.isChecked() || !this.cbwifiUART.isChecked()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void SetCheckBoxState(int nCK, boolean bOK) {
        if (bOK) {
            switch (nCK) {
                case 0:
                    this.cbxEmmc.setTextColor(-16711936);
                    this.cbxEmmc.setChecked(true);
                    return;
                case 1:
                    this.cbMCU.setTextColor(-16711936);
                    this.cbMCU.setChecked(true);
                    return;
                case 2:
                    this.cbxSD.setTextColor(-16711936);
                    this.cbxSD.setChecked(true);
                    return;
                case 3:
                    this.cbSim.setTextColor(-16711936);
                    this.cbSim.setChecked(true);
                    return;
                case 4:
                    this.cbIIC.setTextColor(-16711936);
                    this.cbIIC.setChecked(true);
                    return;
                case 5:
                    this.cbwifiUART.setText("串口正常");
                    this.cbwifiUART.setTextColor(-16711936);
                    this.cbwifiUART.setChecked(true);
                    return;
                default:
                    return;
            }
        } else {
            switch (nCK) {
                case 0:
                    this.cbxEmmc.setText("内部存储错误");
                    this.cbxEmmc.setTextColor(SupportMenu.CATEGORY_MASK);
                    this.cbxEmmc.setChecked(false);
                    return;
                case 1:
                    this.cbMCU.setText("MCU错误" + Mcu.McuTestResult());
                    this.cbMCU.setTextColor(SupportMenu.CATEGORY_MASK);
                    this.cbMCU.setChecked(false);
                    return;
                case 2:
                    this.cbxSD.setText("SD错误");
                    this.cbxSD.setTextColor(SupportMenu.CATEGORY_MASK);
                    this.cbxSD.setChecked(false);
                    return;
                case 3:
                    this.cbSim.setText("SIM卡错误");
                    this.cbSim.setTextColor(SupportMenu.CATEGORY_MASK);
                    this.cbSim.setChecked(false);
                    return;
                case 4:
                    this.cbIIC.setText("IIC错误");
                    this.cbIIC.setTextColor(SupportMenu.CATEGORY_MASK);
                    this.cbIIC.setChecked(false);
                    return;
                case 5:
                    this.cbwifiUART.setText("UART错误");
                    this.cbwifiUART.setTextColor(SupportMenu.CATEGORY_MASK);
                    this.cbwifiUART.setChecked(false);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void enterSubWin(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 0) {
            return true;
        }
        event.getAction();
        return true;
    }

    public void UserAll() {
        if (this.bCheck) {
            this.Num++;
            if (this.Num % 30 == 0) {
                if (!this.cbMCU.isChecked()) {
                    this.nMcu = Mcu.McuTestResult();
                    if (this.nMcu == 0) {
                        SetCheckBoxState(1, true);
                    }
                }
                if (!this.cbSim.isChecked() && CheckMySinState()) {
                    SetCheckBoxState(3, true);
                }
                if (bOK()) {
                    this.texmesTextView.setText("测试OK");
                    factory_test.AddToSort("ARM测试ok");
                    this.bCheck = false;
                    enterSubWin(FactorytestvideoActivity.class);
                } else if (factory_test.getTickCount() - factory_test.TotaArmlTime > 1000) {
                    this.bCheck = false;
                    if (!bOK()) {
                        this.texmesTextView.setTextColor(SupportMenu.CATEGORY_MASK);
                        if (!this.cbSim.isChecked()) {
                            SetCheckBoxState(3, false);
                            this.texmesTextView.setText("SIM卡座异常");
                            factory_test.AddToSort("SIM卡座异常");
                        }
                        if (!this.cbxSD.isChecked()) {
                            SetCheckBoxState(2, false);
                            this.texmesTextView.setText("SD卡座异常");
                            factory_test.AddToSort("SD卡座异常");
                        }
                        if (!this.cbxEmmc.isChecked()) {
                            this.texmesTextView.setText("内部存储器异常");
                            factory_test.AddToSort("内部存储器异常");
                        }
                        if (!this.cbMCU.isChecked()) {
                            SetCheckBoxState(1, false);
                            factory_test.AddToSort("MCU异常");
                        }
                        if (!this.cbwifiUART.isChecked()) {
                            factory_test.AddToSort("串口异常");
                        }
                        if (!this.cbIIC.isChecked()) {
                            factory_test.AddToSort("IIC异常");
                        }
                        if (!this.cbMCU.isChecked() || !this.cbIIC.isChecked() || !this.cbwifiUART.isChecked()) {
                            this.texmesTextView.setText("排母座焊接异常");
                            factory_test.AddToSort("排母座焊接异常");
                            factory_test.WriteReport();
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean ReadUartOk() {
        byte[] Readtestdata = new byte[2];
        byte[] Senddata = {85, -86};
        Iop.UartSend(Senddata, 2);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int nNum = Iop.UartRead(Readtestdata, 2);
        Log.i(TAG, "ReadUartdata  nNum = " + nNum);
        Log.i(TAG, "Readtestdata[0] = " + Readtestdata[0]);
        Log.i(TAG, "Readtestdata[1] = " + Readtestdata[1]);
        if (nNum != 2) {
            Log.i(TAG, "ReadUart error");
            return false;
        } else if (Readtestdata[0] == Senddata[0] && Readtestdata[1] == Senddata[1]) {
            return true;
        } else {
            Log.i(TAG, "ReadUartdata  error");
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void TestIIC() {
        Iop.IIcSendOneByte((byte) 80, (byte) 0, (byte) 90);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (Iop.IIcReadOneByte((byte) 80, (byte) 0) == 90) {
            SetCheckBoxState(4, true);
        } else {
            SetCheckBoxState(4, false);
        }
    }

    /* access modifiers changed from: package-private */
    public void TestUart() {
        Iop.UartOpen(115200);
        Iop.RstPort(0);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean bReadOne = ReadUartOk();
        Iop.RstPort(1);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        boolean bReadTwo = ReadUartOk();
        Iop.UartClose();
        if (bReadOne && bReadTwo) {
            this.cbwifiUART.setText("串口短路");
            this.cbwifiUART.setTextColor(SupportMenu.CATEGORY_MASK);
            this.cbwifiUART.setChecked(false);
        } else if (bReadOne || bReadTwo) {
            Log.i(TAG, "bReadOne == " + bReadOne);
            Log.i(TAG, "bReadOne == " + bReadTwo);
            SetCheckBoxState(5, true);
        } else {
            this.cbwifiUART.setText("串口异常");
            this.cbwifiUART.setTextColor(SupportMenu.CATEGORY_MASK);
            this.cbwifiUART.setChecked(false);
        }
    }

    /* access modifiers changed from: package-private */
    public void TestArmmemory() {
        String[] mCtouchPath = MainSet.GetInstance().GetMountedStorage();
        for (int i = 0; i < mCtouchPath.length; i++) {
            Log.i(TAG, "==" + mCtouchPath[i]);
            if (CheckMemory(mCtouchPath[i], String.valueOf(mCtouchPath[i]) + "/test.bin")) {
                if (mCtouchPath[i].contains("emulated")) {
                    SetCheckBoxState(0, true);
                } else if (mCtouchPath[i].contains("sdcard1")) {
                    SetCheckBoxState(2, true);
                } else {
                    SetCheckBoxState(1, true);
                }
            } else if (mCtouchPath[i].contains("emulated")) {
                SetCheckBoxState(0, false);
            } else if (mCtouchPath[i].contains("sdcard1")) {
                SetCheckBoxState(2, false);
            } else {
                SetCheckBoxState(1, false);
            }
            if (MainSet.Testmode.bSD == 0) {
                SetCheckBoxState(2, true);
            }
            if (MainSet.Testmode.USB_PORT == 0) {
                SetCheckBoxState(1, true);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean IsWifiVer() {
        return MainSet.Testmode.KeyType == 2;
    }

    /* access modifiers changed from: package-private */
    public boolean CheckMySinState() {
        if (!IsWifiVer() && ((TelephonyManager) getSystemService("phone")).getSimState() != 5) {
            return false;
        }
        return true;
    }

    public boolean StorageMounted(String Path) {
        if (EnvATC == null) {
            EnvATC = new EnvironmentATC(this);
        }
        String[] strSDMountedPath = EnvATC.getStorageMountedPaths();
        for (String equals : strSDMountedPath) {
            if (Path.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean CheckMemory(String Path, String File) {
        if (StorageMounted(Path)) {
            if (!TsFile.fileIsExists(File)) {
                try {
                    TsFile.writeFileSdcardFile(File, TAG);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String Read = new String();
            try {
                Read = TsFile.readFileSdcardFile(File);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            if (Read == null || !Read.equals(TAG)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        MainTask.GetInstance().SetUserCallBack(this);
        TestArmmemory();
        TestUart();
        TestIIC();
        Mcu.McuTestStart();
        factory_test.TotaArmlTime = factory_test.getTickCount();
        super.onResume();
    }
}
