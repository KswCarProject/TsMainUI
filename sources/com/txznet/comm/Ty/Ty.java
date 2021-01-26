package com.txznet.comm.Ty;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.PowerManager;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.txz.util.TE;
import java.util.HashMap;

/* compiled from: Proguard */
public class Ty {
    private static Boolean Tk = null;

    /* renamed from: T  reason: collision with root package name */
    private Context f416T;
    private KeyguardManager.KeyguardLock T9;
    private PowerManager.WakeLock Tn;
    private PowerManager Tr = ((PowerManager) this.f416T.getSystemService("power"));
    private KeyguardManager Ty = ((KeyguardManager) this.f416T.getSystemService("keyguard"));

    public Ty(Context context) {
        this.f416T = context;
        HashMap<String, String> config = TE.T("enableScreenLock");
        if (config != null && config.get("enableScreenLock") != null) {
            try {
                Tk = Boolean.valueOf(Boolean.parseBoolean(config.get("enableScreenLock")));
            } catch (Exception e) {
                Tn.T("parse screen lock error", (Throwable) e);
            }
        }
    }

    public void T() {
        if (Tk == null || Tk.booleanValue()) {
            if (!(this.Tn == null && this.T9 == null)) {
                Tr();
            }
            this.Tn = this.Tr.newWakeLock(805306394, "TXZ_WakeLock");
            this.Tn.acquire();
            this.T9 = this.Ty.newKeyguardLock("KeyguardLock");
            this.T9.disableKeyguard();
            return;
        }
        Tn.T("disable screen lock,return");
    }

    public void Tr() {
        if (this.Tn != null) {
            if (this.Tn.isHeld()) {
                try {
                    this.Tn.release();
                } catch (Exception e) {
                }
            }
            this.Tn = null;
        }
        if (this.T9 != null) {
            this.T9.reenableKeyguard();
            this.T9 = null;
        }
    }
}
