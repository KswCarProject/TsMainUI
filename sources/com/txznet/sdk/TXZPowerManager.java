package com.txznet.sdk;

import com.txznet.comm.Tr.Tn;
import java.util.LinkedList;
import java.util.List;

/* compiled from: Proguard */
public class TXZPowerManager {

    /* renamed from: T  reason: collision with root package name */
    static Boolean f788T = null;
    private static TXZPowerManager Tr = new TXZPowerManager();
    private List<Runnable> Ty;

    /* compiled from: Proguard */
    public enum PowerAction {
        POWER_ACTION_POWER_ON,
        POWER_ACTION_BEFORE_SLEEP,
        POWER_ACTION_SLEEP,
        POWER_ACTION_WAKEUP,
        POWER_ACTION_SHOCK_WAKEUP,
        POWER_ACTION_ENTER_REVERSE,
        POWER_ACTION_QUIT_REVERSE,
        POWER_ACTION_BEFORE_POWER_OFF,
        POWER_ACTION_POWER_OFF
    }

    private TXZPowerManager() {
    }

    public static TXZPowerManager getInstance() {
        return Tr;
    }

    /* access modifiers changed from: package-private */
    public void T() {
    }

    public void notifyPowerAction(PowerAction action) {
        if (action != null) {
            switch (action) {
                case POWER_ACTION_ENTER_REVERSE:
                    Tn.Tr().T("com.txznet.txz", "comm.power.ENTER_REVERSE", (byte[]) null, (Tn.Tr) null);
                    return;
                case POWER_ACTION_QUIT_REVERSE:
                    Tn.Tr().T("com.txznet.txz", "comm.power.QUIT_REVERSE", (byte[]) null, (Tn.Tr) null);
                    return;
                case POWER_ACTION_BEFORE_POWER_OFF:
                    Tn.Tr().T("com.txznet.txz", "comm.power.BEFORE_POWER_OFF", (byte[]) null, (Tn.Tr) null);
                    return;
                case POWER_ACTION_BEFORE_SLEEP:
                    Tn.Tr().T("com.txznet.txz", "comm.power.BEFORE_SLEEP", (byte[]) null, (Tn.Tr) null);
                    return;
                case POWER_ACTION_POWER_OFF:
                    Tn.Tr().T("com.txznet.txz", "comm.power.POWER_OFF", (byte[]) null, (Tn.Tr) null);
                    return;
                case POWER_ACTION_POWER_ON:
                    Tn.Tr().T("com.txznet.txz", "comm.power.POWER_ON", (byte[]) null, (Tn.Tr) null);
                    return;
                case POWER_ACTION_SLEEP:
                    Tn.Tr().T("com.txznet.txz", "comm.power.SLEEP", (byte[]) null, (Tn.Tr) null);
                    return;
                case POWER_ACTION_WAKEUP:
                    Tn.Tr().T("com.txznet.txz", "comm.power.WAKEUP", (byte[]) null, (Tn.Tr) null);
                    return;
                case POWER_ACTION_SHOCK_WAKEUP:
                    Tn.Tr().T("com.txznet.txz", "comm.power.SHOCK_WAKEUP", (byte[]) null, (Tn.Tr) null);
                    return;
                default:
                    return;
            }
        }
    }

    public void releaseTXZ() {
        synchronized (TXZPowerManager.class) {
            Tn.Tr().T9 = false;
            Tn.Tr().T();
            f788T = true;
        }
        Tn.Tr().T("com.txznet.txz", "comm.exitTXZ", (byte[]) null, (Tn.Tr) null);
    }

    public void reinitTXZ() {
        synchronized (TXZPowerManager.class) {
            f788T = false;
            TXZService.f820T = false;
            Tn.Tr().T9 = false;
        }
        TXZConfigManager.getInstance().Ty();
    }

    public void reinitTXZ(Runnable onSucc) {
        if (this.Ty == null) {
            this.Ty = new LinkedList();
        }
        synchronized (this.Ty) {
            this.Ty.add(onSucc);
        }
        reinitTXZ();
    }

    /* access modifiers changed from: package-private */
    public void Tr() {
        if (this.Ty != null && !this.Ty.isEmpty()) {
            synchronized (this.Ty) {
                for (Runnable callback : this.Ty) {
                    Tn.Tr().T(callback, 0);
                }
                this.Ty.clear();
            }
        }
    }
}
