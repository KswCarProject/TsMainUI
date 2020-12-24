package com.txznet.comm.ui.Ty;

import com.ts.can.CanCameraUI;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.ui.T5.Ty;
import com.txznet.sdk.TXZAsrManager;
import java.util.HashSet;
import java.util.Set;

/* compiled from: Proguard */
public class Tr {

    /* renamed from: T  reason: collision with root package name */
    private static Tr f561T = new Tr();
    private Set<T> Tn = new HashSet();
    private T Tr;
    private int Ty = 0;

    /* compiled from: Proguard */
    public interface T {
        void T(boolean z);
    }

    private Tr() {
    }

    public static Tr T() {
        return f561T;
    }

    public void Tr() {
        try {
            this.Tr = (T) com.txznet.comm.ui.Tk.T.Tr().T(com.txznet.comm.ui.TE.T.TZ());
        } catch (Exception e) {
            Tn.Tn("[UI2.0] get keyEventDispatcher error");
        }
    }

    public void T(Ty viewBase) {
        if (this.Tr != null) {
            this.Tr.T(viewBase);
        } else {
            Tn.Tn("[UI2.0] mKeyEventDispatcher is null,update content view failed");
        }
    }

    public void T(int selection, int value) {
        if (this.Tr != null) {
            this.Tr.T(selection, value);
        }
    }

    public boolean T(int keyEvent) {
        switch (keyEvent) {
            case 4:
            case 13:
                Tn.T("[UI2.0] on KEYCODE_BACK pressed");
                TXZAsrManager.getInstance().cancel();
                return true;
            case 14:
                return Ty(CanCameraUI.BTN_TRUMPCHI_WC_MODE1);
            case 19:
            case 100:
                return Ty(19);
            case 20:
            case 101:
                return Ty(20);
            case 23:
            case 102:
                return Ty(23);
            default:
                return Ty(keyEvent);
        }
    }

    public void Tr(int state) {
        Tn.T("[UI2.0] onWheelControlStateChanged :" + state);
        if (this.Ty != state) {
            this.Ty = state;
            for (T listener : this.Tn) {
                if (state == 1) {
                    listener.T(true);
                }
                if (state == 0) {
                    listener.T(false);
                }
            }
        }
    }

    private boolean Ty(int keyEvent) {
        if (this.Tr != null) {
            return this.Tr.T(keyEvent);
        }
        Tn.Tn("[UI2.0] mKeyEventDispatcher is null,ingore keyEvent:" + keyEvent);
        return false;
    }
}
