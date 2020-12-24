package com.txznet.sdk;

import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.TXZService;
import com.txznet.sdk.tongting.IConstantData;

/* compiled from: Proguard */
public class TXZCarControlManager {

    /* renamed from: T  reason: collision with root package name */
    private static TXZCarControlManager f719T;
    private Integer T9;
    private Integer TZ;
    private Integer Tk;
    private Integer Tn;
    private boolean Tr = false;
    /* access modifiers changed from: private */
    public ACMgrTool Ty = null;

    private TXZCarControlManager() {
    }

    public static TXZCarControlManager getInstance() {
        if (f719T == null) {
            synchronized (TXZCarControlManager.class) {
                if (f719T == null) {
                    f719T = new TXZCarControlManager();
                }
            }
        }
        return f719T;
    }

    /* compiled from: Proguard */
    public static abstract class ACMgrTool {

        /* compiled from: Proguard */
        public enum ACMode {
            MODE_BLOW_FACE,
            MODE_BLOW_FACE_FOOT,
            MODE_BLOW_FOOT,
            MODE_BLOW_FOOT_DEFROST,
            MODE_DEFROST,
            MODE_AUTO
        }

        public boolean incTemp() {
            return false;
        }

        public boolean decTemp() {
            return false;
        }

        public boolean maxTemp() {
            return false;
        }

        public boolean minTemp() {
            return false;
        }

        public boolean incWSpeed() {
            return false;
        }

        public boolean decWSpeed() {
            return false;
        }

        public boolean openAirConditioner() {
            return false;
        }

        public boolean closeAirConditioner() {
            return false;
        }

        public boolean outLoop() {
            return false;
        }

        public boolean innerLoop() {
            return false;
        }

        public boolean openFDef() {
            return false;
        }

        public boolean closeFDef() {
            return false;
        }

        public boolean openADef() {
            return false;
        }

        public boolean closeADef() {
            return false;
        }

        public boolean openCompressor() {
            return false;
        }

        public boolean closeCompressor() {
            return false;
        }

        public boolean selectMode(ACMode mode) {
            return false;
        }

        public boolean ctrlToTemp(int temp) {
            return false;
        }

        public boolean incTemp(int temp) {
            return false;
        }

        public boolean decTemp(int temp) {
            return false;
        }

        public boolean ctrlToWSpeed(int speed) {
            return false;
        }

        public boolean minWSpeed() {
            return false;
        }

        public boolean maxWSpeed() {
            return false;
        }
    }

    public void setACMgrTool(ACMgrTool acMgrTool) {
        this.Tr = true;
        this.Ty = acMgrTool;
        if (this.Ty == null) {
            Tn.Tr().T("com.txznet.txz", "txz.ac.acmgr.cleartool", (byte[]) null, (Tn.Tr) null);
            return;
        }
        TXZService.T("tool.acmgr.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                boolean ret = false;
                if (command.equals("incTemp")) {
                    if (data == null || data.length <= 0) {
                        ret = TXZCarControlManager.this.Ty.incTemp();
                    } else {
                        try {
                            ret = TXZCarControlManager.this.Ty.incTemp(((Integer) new Tr(data).T(IConstantData.KEY_DATA, Integer.class, 0)).intValue());
                        } catch (Exception e) {
                        }
                    }
                } else if (command.equals("decTemp")) {
                    if (data == null || data.length <= 0) {
                        ret = TXZCarControlManager.this.Ty.decTemp();
                    } else {
                        try {
                            ret = TXZCarControlManager.this.Ty.decTemp(((Integer) new Tr(data).T(IConstantData.KEY_DATA, Integer.class, 0)).intValue());
                        } catch (Exception e2) {
                        }
                    }
                } else if (command.equals("maxTemp")) {
                    ret = TXZCarControlManager.this.Ty.maxTemp();
                } else if (command.equals("minTemp")) {
                    ret = TXZCarControlManager.this.Ty.minTemp();
                } else if (command.equals("incWSpeed")) {
                    ret = TXZCarControlManager.this.Ty.incWSpeed();
                } else if (command.equals("decWSpeed")) {
                    ret = TXZCarControlManager.this.Ty.decWSpeed();
                } else if (command.equals("openAC")) {
                    ret = TXZCarControlManager.this.Ty.openAirConditioner();
                } else if (command.equals("closeAC")) {
                    ret = TXZCarControlManager.this.Ty.closeAirConditioner();
                } else if (command.equals("outLoop")) {
                    ret = TXZCarControlManager.this.Ty.outLoop();
                } else if (command.equals("innerLoop")) {
                    ret = TXZCarControlManager.this.Ty.innerLoop();
                } else if (command.equals("openFDef")) {
                    ret = TXZCarControlManager.this.Ty.openFDef();
                } else if (command.equals("closeFDef")) {
                    ret = TXZCarControlManager.this.Ty.closeFDef();
                } else if (command.equals("openADef")) {
                    ret = TXZCarControlManager.this.Ty.openADef();
                } else if (command.equals("closeADef")) {
                    ret = TXZCarControlManager.this.Ty.closeADef();
                } else if (command.equals("selectMode")) {
                    try {
                        ret = TXZCarControlManager.this.Ty.selectMode(ACMgrTool.ACMode.valueOf((String) new Tr(data).T(IConstantData.KEY_DATA, String.class, "")));
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                } else if (command.equals("openCompressor")) {
                    ret = TXZCarControlManager.this.Ty.openCompressor();
                } else if (command.equals("closeCompressor")) {
                    ret = TXZCarControlManager.this.Ty.closeCompressor();
                } else if (command.equals("ctrlToTemp")) {
                    try {
                        ret = TXZCarControlManager.this.Ty.ctrlToTemp(((Integer) new Tr(data).T(IConstantData.KEY_DATA, Integer.class, 0)).intValue());
                    } catch (Exception e4) {
                    }
                } else if (command.equals("ctrlToWSpeed")) {
                    try {
                        ret = TXZCarControlManager.this.Ty.ctrlToWSpeed(((Integer) new Tr(data).T(IConstantData.KEY_DATA, Integer.class, 0)).intValue());
                    } catch (Exception e5) {
                    }
                } else if (command.equals("maxWSpeed")) {
                    ret = TXZCarControlManager.this.Ty.maxWSpeed();
                } else if (command.equals("minWSpeed")) {
                    ret = TXZCarControlManager.this.Ty.minWSpeed();
                }
                if (!ret) {
                    TXZResourceManager.getInstance().speakTextOnRecordWin("抱歉，当前不支持该操作", false, (Runnable) null);
                }
                return null;
            }
        });
        Tn.Tr().T("com.txznet.txz", "txz.ac.acmgr.settool", (byte[]) null, (Tn.Tr) null);
    }

    /* access modifiers changed from: package-private */
    public void T() {
        if (!this.Tr) {
            return;
        }
        if (this.Ty == null) {
            Tn.Tr().T("com.txznet.txz", "txz.ac.acmgr.cleartool", (byte[]) null, (Tn.Tr) null);
            return;
        }
        Tn.Tr().T("com.txznet.txz", "txz.ac.acmgr.settool", (byte[]) null, (Tn.Tr) null);
        if (!(this.T9 == null || this.Tn == null)) {
            setTEMPDistance(this.T9.intValue(), this.Tn.intValue());
        }
        if (this.TZ != null && this.Tk != null) {
            setWSpeedDistance(this.TZ.intValue(), this.Tk.intValue());
        }
    }

    public boolean setTEMPDistance(int minTempValue, int maxTempValue) {
        if (minTempValue <= 0 || maxTempValue <= minTempValue) {
            return false;
        }
        this.Tn = Integer.valueOf(maxTempValue);
        this.T9 = Integer.valueOf(minTempValue);
        Tr json = new Tr();
        json.T("maxVal", (Object) this.Tn);
        json.T("minVal", (Object) this.T9);
        Tn.Tr().T("com.txznet.txz", "txz.ac.settempdistance", json.toString().getBytes(), (Tn.Tr) null);
        return true;
    }

    public boolean setWSpeedDistance(int minWSpeedValue, int maxWSpeedValue) {
        if (minWSpeedValue < 0 || maxWSpeedValue <= minWSpeedValue) {
            return false;
        }
        this.Tk = Integer.valueOf(maxWSpeedValue);
        this.TZ = Integer.valueOf(minWSpeedValue);
        Tr json = new Tr();
        json.T("maxVal", (Object) this.Tk);
        json.T("minVal", (Object) this.TZ);
        Tn.Tr().T("com.txznet.txz", "txz.ac.setwspeeddistance", json.toString().getBytes(), (Tn.Tr) null);
        return true;
    }
}
