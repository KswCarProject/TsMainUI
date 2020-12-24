package com.txznet.comm.Tr.Tr;

import com.txznet.comm.Tr.Tn;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
public class T5 {

    /* renamed from: T  reason: collision with root package name */
    static Set<Tr> f384T = new HashSet();
    /* access modifiers changed from: private */
    public static boolean Tn = false;
    /* access modifiers changed from: private */
    public static boolean Tr = false;
    /* access modifiers changed from: private */
    public static boolean Ty = false;

    /* compiled from: Proguard */
    public static abstract class T {

        /* renamed from: T  reason: collision with root package name */
        public Boolean f386T = false;
        public Boolean Tr = false;
        public Boolean Ty = false;

        public abstract void T();
    }

    /* compiled from: Proguard */
    public interface Tr {
        void onBeepEnd();

        void onBeginAsr();

        void onBeginCall();

        void onBeginTts();

        void onEndAsr();

        void onEndCall();

        void onEndTts();

        void onMusicPause();

        void onMusicPlay();
    }

    static {
        Tn.Tr().T("com.txznet.txz", (Runnable) new Runnable() {
            public void run() {
                Tn.Tr().T("com.txznet.txz", "comm.subscribe.broadcast", (byte[]) null, (Tn.Tr) null);
            }
        });
        T((T) new T() {
            public void T() {
                boolean unused = T5.Tr = this.f386T.booleanValue();
                boolean unused2 = T5.Ty = this.Tr.booleanValue();
                boolean unused3 = T5.Tn = this.Ty.booleanValue();
            }
        });
    }

    public static boolean T() {
        return Tr;
    }

    public static boolean Tr() {
        return Ty;
    }

    public static boolean Ty() {
        return Tn;
    }

    /* access modifiers changed from: private */
    public static boolean TZ(boolean b) {
        if (!Tr && b) {
            Tr = b;
            Tn.Tr().T((Runnable) new Runnable() {
                public void run() {
                    for (Tr l : T5.f384T) {
                        l.onBeginAsr();
                    }
                }
            }, 0);
        } else if (Tr && !b) {
            Tr = b;
            Tn.Tr().T((Runnable) new Runnable() {
                public void run() {
                    for (Tr l : T5.f384T) {
                        l.onEndAsr();
                    }
                }
            }, 0);
        }
        return b;
    }

    /* access modifiers changed from: private */
    public static boolean TE(boolean b) {
        if (!Ty && b) {
            Ty = b;
            Tn.Tr().T((Runnable) new Runnable() {
                public void run() {
                    for (Tr l : T5.f384T) {
                        l.onBeginTts();
                    }
                }
            }, 0);
        } else if (Ty && !b) {
            Ty = b;
            Tn.Tr().T((Runnable) new Runnable() {
                public void run() {
                    for (Tr l : T5.f384T) {
                        l.onEndTts();
                    }
                }
            }, 0);
        }
        return b;
    }

    /* access modifiers changed from: private */
    public static boolean T5(boolean b) {
        if (!Tn && b) {
            Tn = b;
            Tn.Tr().T((Runnable) new Runnable() {
                public void run() {
                    for (Tr l : T5.f384T) {
                        l.onBeginCall();
                    }
                }
            }, 0);
        } else if (Tn && !b) {
            Tn = b;
            Tn.Tr().T((Runnable) new Runnable() {
                public void run() {
                    for (Tr l : T5.f384T) {
                        l.onEndCall();
                    }
                }
            }, 0);
        }
        return b;
    }

    public static void T(Tr listener) {
        Tn.Tr().T((Runnable) new com.txznet.txz.util.T.T<Tr>(listener) {
            public void run() {
                T5.f384T.add(this.Ty);
            }
        }, 0);
    }

    public static void Tr(Tr listener) {
        Tn.Tr().T((Runnable) new com.txznet.txz.util.T.T<Tr>(listener) {
            public void run() {
                T5.f384T.remove(this.Ty);
            }
        }, 0);
    }

    public static byte[] T(String status) {
        if (status.equals("onBeginMusic")) {
            Tn.Tr().T((Runnable) new Runnable() {
                public void run() {
                    for (Tr l : T5.f384T) {
                        l.onMusicPlay();
                    }
                }
            }, 0);
        } else if (status.equals("onEndMusic")) {
            Tn.Tr().T((Runnable) new Runnable() {
                public void run() {
                    for (Tr l : T5.f384T) {
                        l.onMusicPause();
                    }
                }
            }, 0);
        } else if (status.equals("onBeginAsr")) {
            TZ(true);
        } else if (status.equals("onBeepEnd")) {
            if (Tr) {
                Tn.Tr().T((Runnable) new Runnable() {
                    public void run() {
                        for (Tr l : T5.f384T) {
                            l.onBeepEnd();
                        }
                    }
                }, 0);
            }
        } else if (status.equals("onEndAsr")) {
            TZ(false);
        } else if (status.equals("onBeginTts")) {
            TE(true);
        } else if (status.equals("onEndTts")) {
            TE(false);
        } else if (status.equals("onBeginCall")) {
            T5(true);
        } else if (status.equals("onEndCall")) {
            T5(false);
        }
        return null;
    }

    public static void T(final T cb) {
        if (cb != null) {
            Tn.Tr res = new Tn.Tr() {
                public void T(Tn.Ty data) {
                    if (data != null) {
                        JSONObject json = data.Tk();
                        try {
                            if (cb.f386T != null) {
                                cb.f386T = Boolean.valueOf(T5.TZ(json.getBoolean("asr")));
                            }
                        } catch (Exception e) {
                        }
                        try {
                            if (cb.Tr != null) {
                                cb.Tr = Boolean.valueOf(T5.TE(json.getBoolean("tts")));
                            }
                        } catch (Exception e2) {
                        }
                        try {
                            if (cb.Ty != null) {
                                cb.Ty = Boolean.valueOf(T5.T5(json.getBoolean("call")));
                            }
                        } catch (Exception e3) {
                        }
                    }
                    cb.T();
                }
            };
            JSONObject json = new JSONObject();
            try {
                if (cb.f386T != null) {
                    json.put("asr", true);
                }
                if (cb.Tr != null) {
                    json.put("tts", true);
                }
                if (cb.Ty != null) {
                    json.put("call", true);
                }
            } catch (JSONException e) {
            }
            Tn.Tr().T("com.txznet.txz", "comm.status.get", json.toString().getBytes(), res);
        }
    }
}
