package com.txznet.sdk;

import android.text.TextUtils;
import com.txznet.comm.Tr.Tn;
import com.txznet.sdk.TXZService;

/* compiled from: Proguard */
public class TXZSimManager {

    /* renamed from: T  reason: collision with root package name */
    private static TXZSimManager f817T = new TXZSimManager();
    /* access modifiers changed from: private */
    public SimTool Tr;
    private boolean Ty;

    /* compiled from: Proguard */
    public interface SimTool {
        void onSimAlarmHandle(String str);

        void onSimRechargeQrHandle(String str);

        void onSimRechargeResultHandle(String str);
    }

    private TXZSimManager() {
    }

    public static TXZSimManager getInstance() {
        return f817T;
    }

    public void onReconnectTXZ() {
        if (this.Ty) {
            setSimTool(this.Tr);
        }
    }

    public void setSimTool(SimTool tool) {
        this.Tr = tool;
        if (tool == null) {
            this.Ty = false;
            Tn.Tr().T("com.txznet.txz", "txz.sim.tool.clear", (byte[]) null, (Tn.Tr) null);
            return;
        }
        this.Ty = true;
        TXZService.T("tool.sim.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                SimTool tool;
                if (!TextUtils.isEmpty(command) && (tool = TXZSimManager.this.Tr) != null) {
                    if (command.equals("alarm")) {
                        tool.onSimAlarmHandle(new String(data));
                    } else if (command.equals("recharge.qr")) {
                        tool.onSimRechargeQrHandle(new String(data));
                    } else if (command.equals("recharge.result")) {
                        tool.onSimRechargeResultHandle(new String(data));
                    }
                }
                return null;
            }
        });
        Tn.Tr().T("com.txznet.txz", "txz.sim.tool.set", (byte[]) null, (Tn.Tr) null);
    }
}
