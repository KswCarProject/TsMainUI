package com.txznet.sdk;

import android.text.TextUtils;
import com.Tn.Tr.Tr.T;
import com.Tr.T.T.T9;
import com.ts.can.bmw.mini.CanBMWMiniServiceDetailActivity;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.TXZService;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: Proguard */
public class TXZCallManager {
    private static TXZCallManager Ty = new TXZCallManager();

    /* renamed from: T  reason: collision with root package name */
    byte[] f710T = null;
    private String T5;
    private String T6;
    /* access modifiers changed from: private */
    public CallTool T9 = null;
    private String TE;
    private boolean TZ = false;
    private String Th;
    /* access modifiers changed from: private */
    public String Tk = null;
    private boolean Tn = false;
    Boolean Tr;
    private boolean Tv = false;

    /* compiled from: Proguard */
    public interface CallTool {

        /* compiled from: Proguard */
        public enum CallStatus {
            CALL_STATUS_IDLE,
            CALL_STATUS_RINGING,
            CALL_STATUS_OFFHOOK
        }

        boolean acceptIncoming();

        CallStatus getStatus();

        boolean hangupCall();

        boolean makeCall(Contact contact);

        boolean rejectIncoming();

        void setStatusListener(CallToolStatusListener callToolStatusListener);
    }

    /* compiled from: Proguard */
    public interface CallToolStatusListener {
        void onDisabled(String str);

        void onEnabled();

        void onIdle();

        void onIncoming(Contact contact, boolean z, boolean z2);

        void onMakeCall(Contact contact);

        void onOffhook();
    }

    private TXZCallManager() {
    }

    public static TXZCallManager getInstance() {
        return Ty;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        if (this.Tn) {
            setCallTool(this.T9);
        }
        Tr();
        if (this.Tn && this.T9 != null) {
            if (this.Tk == null) {
                Tn.Tr().T("com.txznet.txz", "txz.call.enable", (byte[]) null, (Tn.Tr) null);
            } else {
                Tn.Tr().T("com.txznet.txz", "txz.call.disable", this.Tk.getBytes(), (Tn.Tr) null);
            }
        }
        if (this.TZ) {
            syncLocalBluetoothInfo(this.TE, this.T5);
        }
        if (this.Tv) {
            syncLocalBluetoothInfo(this.Th, this.T6);
        }
        if (this.Tr != null) {
            setCanAutoCall(this.Tr.booleanValue());
        }
    }

    /* access modifiers changed from: package-private */
    public void Tr() {
        if (this.f710T != null) {
            Tn.Tr().T("com.txznet.txz", "txz.call.sync", this.f710T, (Tn.Tr) null);
        }
    }

    /* compiled from: Proguard */
    public static class Contact {

        /* renamed from: T  reason: collision with root package name */
        protected String f715T;
        protected String Tr;
        protected long Ty;

        public String getName() {
            return this.f715T;
        }

        public void setName(String name) {
            this.f715T = name;
        }

        public String getNumber() {
            return this.Tr;
        }

        public void setNumber(String number) {
            this.Tr = number;
        }

        public long getLastTimeContacted() {
            return this.Ty;
        }

        public void setLastTimeContacted(long lastTimeContacted) {
            this.Ty = lastTimeContacted;
        }
    }

    public void syncContacts(Collection<Contact> cons) {
        T.Tr contacts = new T.Tr();
        contacts.Tr = new T.C0007T[cons.size()];
        int i = 0;
        Map<String, Integer> conMap = new HashMap<>();
        for (Contact con : cons) {
            if (!TextUtils.isEmpty(con.f715T)) {
                if (TextUtils.isEmpty(con.Tr)) {
                    con.Tr = "empty";
                }
                if (conMap.containsKey(con.f715T)) {
                    conMap.put(con.f715T, Integer.valueOf(conMap.get(con.f715T).intValue() + 1));
                    if (conMap.get(con.f715T).intValue() > 10) {
                    }
                } else {
                    conMap.put(con.f715T, 1);
                }
                contacts.Tr[i] = new T.C0007T();
                contacts.Tr[i].Tr = con.f715T;
                T.C0007T t = contacts.Tr[i];
                t.Ty = new String[]{con.Tr};
                contacts.Tr[i].Tk = Integer.valueOf((int) (con.Ty / 1000));
                i++;
            }
        }
        this.f710T = T9.T((T9) contacts);
        Tn.Tr().T("com.txznet.txz", "txz.call.sync", this.f710T, (Tn.Tr) null);
    }

    public void setCallTool(CallTool tool) {
        this.Tn = true;
        this.T9 = tool;
        if (tool == null) {
            Tn.Tr().T("com.txznet.txz", "txz.call.cleartool", (byte[]) null, (Tn.Tr) null);
            return;
        }
        tool.setStatusListener(new CallToolStatusListener() {
            public void onOffhook() {
                Tn.Tr().T("com.txznet.txz", "txz.call.notifyOffhook", (byte[]) null, (Tn.Tr) null);
            }

            public void onMakeCall(Contact con) {
                JSONObject json = new JSONObject();
                try {
                    json.put("name", con.f715T);
                    json.put(CanBMWMiniServiceDetailActivity.KEY_NUM, con.Tr);
                } catch (Exception e) {
                }
                Tn.Tr().T("com.txznet.txz", "txz.call.notifyMakeCall", json.toString().getBytes(), (Tn.Tr) null);
            }

            public void onIncoming(Contact con, boolean needTts, boolean needAsr) {
                JSONObject json = new JSONObject();
                try {
                    json.put("tts", needTts);
                    json.put("asr", needAsr);
                    json.put("name", con.f715T);
                    json.put(CanBMWMiniServiceDetailActivity.KEY_NUM, con.Tr);
                } catch (Exception e) {
                }
                Tn.Tr().T("com.txznet.txz", "txz.call.notifyIncoming", json.toString().getBytes(), (Tn.Tr) null);
            }

            public void onIdle() {
                Tn.Tr().T("com.txznet.txz", "txz.call.notifyIdle", (byte[]) null, (Tn.Tr) null);
            }

            public void onEnabled() {
                String unused = TXZCallManager.this.Tk = null;
                Tn.Tr().T("com.txznet.txz", "txz.call.enable", (byte[]) null, (Tn.Tr) null);
            }

            public void onDisabled(String reason) {
                String unused = TXZCallManager.this.Tk = reason;
                Tn.Tr().T("com.txznet.txz", "txz.call.disable", reason.getBytes(), (Tn.Tr) null);
            }
        });
        TXZService.T("tool.call.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if (command.equals("getStatus")) {
                    try {
                        switch (AnonymousClass3.f713T[TXZCallManager.this.T9.getStatus().ordinal()]) {
                            case 1:
                                return "idle".getBytes();
                            case 2:
                                return "offhook".getBytes();
                            case 3:
                                return "ringing".getBytes();
                            default:
                                return null;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                } else if (command.equals("makeCall")) {
                    try {
                        Contact con = new Contact();
                        JSONObject json = new JSONObject(new String(data));
                        con.setName(json.getString("name"));
                        con.setNumber(json.getString(CanBMWMiniServiceDetailActivity.KEY_NUM));
                        TXZCallManager.this.T9.makeCall(con);
                        return null;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return null;
                    }
                } else if (command.equals("acceptIncoming")) {
                    try {
                        TXZCallManager.this.T9.acceptIncoming();
                        return null;
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        return null;
                    }
                } else if (command.equals("rejectIncoming")) {
                    try {
                        TXZCallManager.this.T9.rejectIncoming();
                        return null;
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        return null;
                    }
                } else if (!command.equals("hangupCall")) {
                    return null;
                } else {
                    try {
                        TXZCallManager.this.T9.hangupCall();
                        return null;
                    } catch (Exception e5) {
                        e5.printStackTrace();
                        return null;
                    }
                }
            }
        });
        Tn.Tr().T("com.txznet.txz", "txz.call.settool", (byte[]) null, (Tn.Tr) null);
    }

    /* renamed from: com.txznet.sdk.TXZCallManager$3  reason: invalid class name */
    /* compiled from: Proguard */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: T  reason: collision with root package name */
        static final /* synthetic */ int[] f713T = new int[CallTool.CallStatus.values().length];

        static {
            try {
                f713T[CallTool.CallStatus.CALL_STATUS_IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f713T[CallTool.CallStatus.CALL_STATUS_OFFHOOK.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f713T[CallTool.CallStatus.CALL_STATUS_RINGING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public void syncLocalBluetoothInfo(String name, String mac) {
        this.TE = name;
        this.T5 = mac;
        this.TZ = true;
        Tr json = new Tr();
        json.T("name", (Object) this.TE);
        json.T("mac", (Object) this.T5);
        Tn.Tr().T("com.txznet.txz", "txz.bt.localinfo", json.Ty(), (Tn.Tr) null);
    }

    public void syncRemoteBluetoothInfo(String name, String mac) {
        this.Th = name;
        this.T6 = mac;
        this.Tv = true;
        Tr json = new Tr();
        json.T("name", (Object) this.Th);
        json.T("mac", (Object) this.T6);
        Tn.Tr().T("com.txznet.txz", "txz.bt.remoteinfo", json.Ty(), (Tn.Tr) null);
    }

    public void setCanAutoCall(boolean canAuto) {
        this.Tr = Boolean.valueOf(canAuto);
        Tn.Tr().T("com.txznet.txz", "txz.call.canProgress", (canAuto + TXZResourceManager.STYLE_DEFAULT).getBytes(), (Tn.Tr) null);
    }
}
