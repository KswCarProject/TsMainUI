package com.txznet.sdk;

import android.os.SystemClock;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.Tr;
import java.util.HashSet;
import java.util.LinkedList;

/* compiled from: Proguard */
public class TXZWheelControlManager {

    /* renamed from: T  reason: collision with root package name */
    private static final TXZWheelControlManager f862T = new TXZWheelControlManager();
    private long T9;
    private HashSet<Integer> TE;
    private OnTXZGlobalWheelControlListener TZ;
    private OnConnectionStatusLinstener Tk;
    private LinkedList<OnTXZWheelControlListener> Tn;
    private Boolean Tr = null;
    private boolean Ty = false;

    /* compiled from: Proguard */
    public interface OnConnectionStatusLinstener {
        void isConnected(boolean z);
    }

    /* compiled from: Proguard */
    public interface OnTXZGlobalWheelControlListener {
        void onKeyEvent(int i);
    }

    /* compiled from: Proguard */
    public interface OnTXZWheelControlListener {
        void onKeyEvent(int i);
    }

    private TXZWheelControlManager() {
    }

    public static TXZWheelControlManager getInstance() {
        return f862T;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        if (this.Tr != null) {
            Tn.Tr().T("com.txznet.txz", "comm.wheelcontrol.enable", this.Tr.toString().getBytes(), (Tn.Tr) null);
        }
        if (this.Tk != null) {
            Tn.Tr().T("com.txznet.txz", "comm.wheelcontrol.connectionstatus", (byte[]) null, (Tn.Tr) null);
        }
        if (this.Tn != null) {
            Tn.Tr().T("com.txznet.txz", "comm.wheelcontrol.setlistener", (TXZResourceManager.STYLE_DEFAULT + this.T9).getBytes(), (Tn.Tr) null);
        }
        if (this.TZ != null && !this.TE.isEmpty()) {
            Tr jsonBuilder = new Tr();
            jsonBuilder.T("globalevent", (Object) this.TE.toArray());
            Tn.Tr().T("com.txznet.txz", "comm.wheelcontrol.setgloballistener", jsonBuilder.Ty(), (Tn.Tr) null);
        }
    }

    public byte[] notifyCallback(String command, byte[] data) {
        Tr jsonBuilder;
        int eventId;
        com.txznet.comm.Tr.Tr.Tn.T("WheelControl : notify callback: " + command);
        if (data == null) {
            com.txznet.comm.Tr.Tr.Tn.Tn("WheelControl : data == null");
        } else if ("txz.wheelcontrol.notify.connected".equals(command)) {
            boolean connected = Boolean.parseBoolean(new String(data));
            com.txznet.comm.Tr.Tr.Tn.T("WheelControl : connection state: " + connected);
            this.Ty = connected;
            if (this.Tk != null) {
                this.Tk.isConnected(connected);
            }
        } else if ("txz.wheelcontrol.notify.event".equals(command)) {
            Tr jsonBuilder2 = new Tr(data);
            if (jsonBuilder2 != null) {
                int eventId2 = ((Integer) jsonBuilder2.T("evnetid", Integer.class, 0)).intValue();
                com.txznet.comm.Tr.Tr.Tn.T("WheelControl : onKeyEvent: " + eventId2);
                if (!(eventId2 == 0 || this.Tn == null || this.Tn.isEmpty())) {
                    this.Tn.getLast().onKeyEvent(eventId2);
                }
            }
        } else if (!(!"txz.wheelcontrol.notify.globalevent".equals(command) || (jsonBuilder = new Tr(data)) == null || (eventId = ((Integer) jsonBuilder.T("evnetid", Integer.class, 0)).intValue()) == 0)) {
            T(eventId);
        }
        return null;
    }

    private boolean T(int eventId) {
        if (this.TZ == null || this.TE.isEmpty()) {
            return false;
        }
        if (this.TE.contains(Integer.valueOf(eventId))) {
            this.TZ.onKeyEvent(eventId);
        }
        return true;
    }

    public boolean isWheelControlConnected() {
        return this.Ty;
    }

    public void enableWheelControl(boolean enable) {
        this.Tr = Boolean.valueOf(enable);
        Tn.Tr().T("com.txznet.txz", "comm.wheelcontrol.enable", this.Tr.toString().getBytes(), (Tn.Tr) null);
    }

    public void scanLEDevice(boolean enable) {
        if (enable) {
            Tn.Tr().T("com.txznet.txz", "comm.wheelcontrol.startlescan", (byte[]) null, (Tn.Tr) null);
        } else {
            Tn.Tr().T("com.txznet.txz", "comm.wheelcontrol.stoplescan", (byte[]) null, (Tn.Tr) null);
        }
    }

    public void setConnectionStatusLinstener(OnConnectionStatusLinstener listener) {
        this.Tk = listener;
        if (listener != null) {
            Tn.Tr().T("com.txznet.txz", "comm.wheelcontrol.connectionstatus", (byte[]) null, (Tn.Tr) null);
        }
    }

    public void registerWheelControlListener(OnTXZWheelControlListener listener) {
        if (listener != null) {
            if (this.Tn == null) {
                this.Tn = new LinkedList<>();
            }
            if (this.Tn.contains(listener)) {
                this.Tn.remove(listener);
            }
            this.Tn.add(listener);
            this.T9 = SystemClock.elapsedRealtime();
            Tn.Tr().T("com.txznet.txz", "comm.wheelcontrol.setlistener", (TXZResourceManager.STYLE_DEFAULT + this.T9).getBytes(), (Tn.Tr) null);
        }
    }

    public void unregisterWheelControlListener(OnTXZWheelControlListener listener) {
        if (listener != null && this.Tn != null && !this.Tn.isEmpty()) {
            this.Tn.remove(listener);
            if (this.Tn.isEmpty()) {
                Tn.Tr().T("com.txznet.txz", "comm.wheelcontrol.removelistener", (byte[]) null, (Tn.Tr) null);
            }
        }
    }

    public boolean regGlobalEvent(int eventId) {
        if (this.TZ != null) {
            return false;
        }
        if (this.TE == null) {
            this.TE = new HashSet<>();
        }
        switch (eventId) {
            case 11:
            case 12:
            case 13:
            case 15:
            case 16:
            case 17:
            case 19:
            case 20:
            case 21:
            case 23:
            case 24:
            case 25:
                return this.TE.add(Integer.valueOf(eventId));
            default:
                return false;
        }
    }

    public boolean setGlobalWheelControlListener(OnTXZGlobalWheelControlListener listener) {
        if (listener == null || this.TE == null || this.TE.isEmpty() || this.TZ != null) {
            return false;
        }
        this.TZ = listener;
        Tr jsonBuilder = new Tr();
        jsonBuilder.T("globalevent", (Object) this.TE.toArray());
        Tn.Tr().T("com.txznet.txz", "comm.wheelcontrol.setgloballistener", jsonBuilder.Ty(), (Tn.Tr) null);
        return true;
    }

    public void removeGlobalWheelControlListener() {
        this.TZ = null;
        this.TE = null;
        Tn.Tr().T("com.txznet.txz", "comm.wheelcontrol.removegloballistener", (byte[]) null, (Tn.Tr) null);
    }
}
