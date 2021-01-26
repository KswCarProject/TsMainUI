package com.txznet.comm.ui.TZ;

import com.txznet.comm.ui.TE.Tr;
import com.txznet.sdk.TXZResourceManager;

/* compiled from: Proguard */
public class Tn {
    public static int T0;
    public static int T5;
    public static int T6;
    public static int T9;
    public static int TA;
    public static int TB;
    public static int TD;
    public static int TE;
    public static int TF;
    public static int TG;
    public static int TI;
    public static int TK;
    public static int TL;
    public static int TM;
    public static int TN;
    public static int TO;
    public static int TP;
    public static int TV;
    public static int TX;
    public static int TZ;
    private static Tn Ta;
    public static int Tb;
    public static int Te;
    public static int Tf;
    public static int Th;
    public static int Tj;
    public static int Tk;
    public static int Tn;
    public static int Tq;
    public static int Tr;
    public static int Ts;
    public static int Tt;
    public static int Tu;
    public static int Tv;
    public static int Tw;
    public static int Tx;
    public static int Ty;

    /* renamed from: T  reason: collision with root package name */
    String f544T = TXZResourceManager.STYLE_DEFAULT;

    private Tn() {
    }

    public void T() {
        Ty();
    }

    public static Tn Tr() {
        if (Ta == null) {
            synchronized (Tn.class) {
                if (Ta == null) {
                    Ta = new Tn();
                }
            }
        }
        return Ta;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: com.txznet.comm.ui.TZ.T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: com.txznet.comm.ui.TZ.T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v15, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: com.txznet.comm.ui.TZ.T} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void Ty() {
        /*
            r11 = this;
            r10 = 4
            r9 = 3
            r8 = 2
            r7 = 1
            r3 = 0
            r1 = 0
            java.lang.String r4 = com.txznet.comm.ui.TE.T.Tk()
            r11.f544T = r4
            java.lang.String r4 = r11.f544T
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 == 0) goto L_0x0124
        L_0x0014:
            int r4 = com.txznet.comm.ui.TE.T.Tr()     // Catch:{ Exception -> 0x0208 }
            if (r4 != r7) goto L_0x01d8
            com.txznet.comm.ui.Tk.T r4 = com.txznet.comm.ui.Tk.T.Tr()     // Catch:{ Exception -> 0x0208 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0208 }
            r5.<init>()     // Catch:{ Exception -> 0x0208 }
            java.lang.String r6 = r11.f544T     // Catch:{ Exception -> 0x0208 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x0208 }
            java.lang.String r6 = "ThemeConfigHorizontal"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x0208 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0208 }
            java.lang.Class r1 = r4.Ty(r5)     // Catch:{ Exception -> 0x0208 }
            if (r1 != 0) goto L_0x0042
            java.lang.String r4 = "get ThemeConfigVertical error!"
            com.txznet.comm.Tr.Tr.Tn.Ty((java.lang.String) r4)     // Catch:{ Exception -> 0x0208 }
            java.lang.Class<com.txznet.comm.ui.TZ.Tr> r1 = com.txznet.comm.ui.TZ.Tr.class
        L_0x0042:
            java.lang.String r4 = "RECORD_WIN_CIRCLE_LY_HEIGHT"
            java.lang.reflect.Field r4 = r1.getField(r4)     // Catch:{ Exception -> 0x0208 }
            r5 = 0
            int r4 = r4.getInt(r5)     // Catch:{ Exception -> 0x0208 }
            TP = r4     // Catch:{ Exception -> 0x0208 }
            java.lang.String r4 = "RECORD_WIN_CIRCLE_LY_HEIGHT"
            java.lang.reflect.Field r4 = r1.getField(r4)     // Catch:{ Exception -> 0x0208 }
            r5 = 0
            int r4 = r4.getInt(r5)     // Catch:{ Exception -> 0x0208 }
            TI = r4     // Catch:{ Exception -> 0x0208 }
            java.lang.String r4 = "RECORD_WIN_VOICE_VIEW_WIDTH"
            java.lang.reflect.Field r4 = r1.getField(r4)     // Catch:{ Exception -> 0x0208 }
            r5 = 0
            int r4 = r4.getInt(r5)     // Catch:{ Exception -> 0x0208 }
            TL = r4     // Catch:{ Exception -> 0x0208 }
            java.lang.String r4 = "RECORD_WIN_VOICE_VIEW_HEIGHT"
            java.lang.reflect.Field r4 = r1.getField(r4)     // Catch:{ Exception -> 0x0208 }
            r5 = 0
            int r4 = r4.getInt(r5)     // Catch:{ Exception -> 0x0208 }
            Tw = r4     // Catch:{ Exception -> 0x0208 }
        L_0x007a:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "themeConfig:"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r3)
            java.lang.String r4 = r4.toString()
            com.txznet.comm.Tr.Tr.Tn.T((java.lang.String) r4)
            if (r3 != 0) goto L_0x009e
            int r4 = com.txznet.comm.ui.TE.T.T()
            if (r4 != r9) goto L_0x020e
            com.txznet.comm.ui.TZ.T.Ty r3 = new com.txznet.comm.ui.TZ.T.Ty
            r3.<init>()
        L_0x009e:
            r11.T((com.txznet.comm.ui.TZ.T) r3)
            if (r1 != 0) goto L_0x00e3
            int r4 = com.txznet.comm.ui.TE.T.Tr()
            if (r4 != r7) goto L_0x0235
            java.lang.Class<com.txznet.comm.ui.TZ.Tr> r1 = com.txznet.comm.ui.TZ.Tr.class
        L_0x00ab:
            java.lang.String r4 = "RECORD_WIN_CIRCLE_LY_HEIGHT"
            java.lang.reflect.Field r4 = r1.getField(r4)     // Catch:{ Exception -> 0x023f }
            r5 = 0
            int r4 = r4.getInt(r5)     // Catch:{ Exception -> 0x023f }
            TP = r4     // Catch:{ Exception -> 0x023f }
            java.lang.String r4 = "RECORD_WIN_CIRCLE_LY_HEIGHT"
            java.lang.reflect.Field r4 = r1.getField(r4)     // Catch:{ Exception -> 0x023f }
            r5 = 0
            int r4 = r4.getInt(r5)     // Catch:{ Exception -> 0x023f }
            TI = r4     // Catch:{ Exception -> 0x023f }
            java.lang.String r4 = "RECORD_WIN_VOICE_VIEW_WIDTH"
            java.lang.reflect.Field r4 = r1.getField(r4)     // Catch:{ Exception -> 0x023f }
            r5 = 0
            int r4 = r4.getInt(r5)     // Catch:{ Exception -> 0x023f }
            TL = r4     // Catch:{ Exception -> 0x023f }
            java.lang.String r4 = "RECORD_WIN_VOICE_VIEW_HEIGHT"
            java.lang.reflect.Field r4 = r1.getField(r4)     // Catch:{ Exception -> 0x023f }
            r5 = 0
            int r4 = r4.getInt(r5)     // Catch:{ Exception -> 0x023f }
            Tw = r4     // Catch:{ Exception -> 0x023f }
        L_0x00e3:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "RECORD_WIN_CIRCLE_LY_WIDTH:"
            java.lang.StringBuilder r4 = r4.append(r5)
            int r5 = TP
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = "RECORD_WIN_CIRCLE_LY_HEIGHT:"
            java.lang.StringBuilder r4 = r4.append(r5)
            int r5 = TI
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = "RECORD_WIN_VOICE_VIEW_WIDTH:"
            java.lang.StringBuilder r4 = r4.append(r5)
            int r5 = TL
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = "RECORD_WIN_VOICE_VIEW_HEIGHT:"
            java.lang.StringBuilder r4 = r4.append(r5)
            int r5 = Tw
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            com.txznet.comm.Tr.Tr.Tn.T((java.lang.String) r4)
            return
        L_0x0124:
            int r4 = com.txznet.comm.ui.TE.T.T()     // Catch:{ Exception -> 0x01cc }
            if (r4 != r9) goto L_0x014e
            com.txznet.comm.ui.Tk.T r4 = com.txznet.comm.ui.Tk.T.Tr()     // Catch:{ Exception -> 0x01cc }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01cc }
            r5.<init>()     // Catch:{ Exception -> 0x01cc }
            java.lang.String r6 = r11.f544T     // Catch:{ Exception -> 0x01cc }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01cc }
            java.lang.String r6 = "ThemeConfigLarge"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01cc }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x01cc }
            java.lang.Object r4 = r4.Tr(r5)     // Catch:{ Exception -> 0x01cc }
            r0 = r4
            com.txznet.comm.ui.TZ.T r0 = (com.txznet.comm.ui.TZ.T) r0     // Catch:{ Exception -> 0x01cc }
            r3 = r0
            goto L_0x0014
        L_0x014e:
            int r4 = com.txznet.comm.ui.TE.T.T()     // Catch:{ Exception -> 0x01cc }
            if (r4 != r7) goto L_0x0178
            com.txznet.comm.ui.Tk.T r4 = com.txznet.comm.ui.Tk.T.Tr()     // Catch:{ Exception -> 0x01cc }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01cc }
            r5.<init>()     // Catch:{ Exception -> 0x01cc }
            java.lang.String r6 = r11.f544T     // Catch:{ Exception -> 0x01cc }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01cc }
            java.lang.String r6 = "ThemeConfigSmall"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01cc }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x01cc }
            java.lang.Object r4 = r4.Tr(r5)     // Catch:{ Exception -> 0x01cc }
            r0 = r4
            com.txznet.comm.ui.TZ.T r0 = (com.txznet.comm.ui.TZ.T) r0     // Catch:{ Exception -> 0x01cc }
            r3 = r0
            goto L_0x0014
        L_0x0178:
            int r4 = com.txznet.comm.ui.TE.T.T()     // Catch:{ Exception -> 0x01cc }
            if (r4 != r10) goto L_0x01a2
            com.txznet.comm.ui.Tk.T r4 = com.txznet.comm.ui.Tk.T.Tr()     // Catch:{ Exception -> 0x01cc }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01cc }
            r5.<init>()     // Catch:{ Exception -> 0x01cc }
            java.lang.String r6 = r11.f544T     // Catch:{ Exception -> 0x01cc }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01cc }
            java.lang.String r6 = "ThemeConfigCar"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01cc }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x01cc }
            java.lang.Object r4 = r4.Tr(r5)     // Catch:{ Exception -> 0x01cc }
            r0 = r4
            com.txznet.comm.ui.TZ.T r0 = (com.txznet.comm.ui.TZ.T) r0     // Catch:{ Exception -> 0x01cc }
            r3 = r0
            goto L_0x0014
        L_0x01a2:
            int r4 = com.txznet.comm.ui.TE.T.T()     // Catch:{ Exception -> 0x01cc }
            if (r4 != r8) goto L_0x0014
            com.txznet.comm.ui.Tk.T r4 = com.txznet.comm.ui.Tk.T.Tr()     // Catch:{ Exception -> 0x01cc }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01cc }
            r5.<init>()     // Catch:{ Exception -> 0x01cc }
            java.lang.String r6 = r11.f544T     // Catch:{ Exception -> 0x01cc }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01cc }
            java.lang.String r6 = "ThemeConfig"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01cc }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x01cc }
            java.lang.Object r4 = r4.Tr(r5)     // Catch:{ Exception -> 0x01cc }
            r0 = r4
            com.txznet.comm.ui.TZ.T r0 = (com.txznet.comm.ui.TZ.T) r0     // Catch:{ Exception -> 0x01cc }
            r3 = r0
            goto L_0x0014
        L_0x01cc:
            r2 = move-exception
            java.lang.String r4 = "load theme config error"
            com.txznet.comm.Tr.Tr.Tn.Tn(r4)
            r2.printStackTrace()
            goto L_0x0014
        L_0x01d8:
            int r4 = com.txznet.comm.ui.TE.T.Tr()     // Catch:{ Exception -> 0x0208 }
            if (r4 != r8) goto L_0x0042
            com.txznet.comm.ui.Tk.T r4 = com.txznet.comm.ui.Tk.T.Tr()     // Catch:{ Exception -> 0x0208 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0208 }
            r5.<init>()     // Catch:{ Exception -> 0x0208 }
            java.lang.String r6 = r11.f544T     // Catch:{ Exception -> 0x0208 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x0208 }
            java.lang.String r6 = "ThemeConfigVertical"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x0208 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0208 }
            java.lang.Class r1 = r4.Ty(r5)     // Catch:{ Exception -> 0x0208 }
            if (r1 != 0) goto L_0x0042
            java.lang.String r4 = "get ThemeConfigVertical error!"
            com.txznet.comm.Tr.Tr.Tn.Ty((java.lang.String) r4)     // Catch:{ Exception -> 0x0208 }
            java.lang.Class<com.txznet.comm.ui.TZ.Tr> r1 = com.txznet.comm.ui.TZ.Tr.class
            goto L_0x0042
        L_0x0208:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x007a
        L_0x020e:
            int r4 = com.txznet.comm.ui.TE.T.T()
            if (r4 != r7) goto L_0x021b
            com.txznet.comm.ui.TZ.T.Tn r3 = new com.txznet.comm.ui.TZ.T.Tn
            r3.<init>()
            goto L_0x009e
        L_0x021b:
            int r4 = com.txznet.comm.ui.TE.T.T()
            if (r4 != r10) goto L_0x0228
            com.txznet.comm.ui.TZ.T.Tr r3 = new com.txznet.comm.ui.TZ.T.Tr
            r3.<init>()
            goto L_0x009e
        L_0x0228:
            int r4 = com.txznet.comm.ui.TE.T.T()
            if (r4 != r8) goto L_0x009e
            com.txznet.comm.ui.TZ.T.T r3 = new com.txznet.comm.ui.TZ.T.T
            r3.<init>()
            goto L_0x009e
        L_0x0235:
            int r4 = com.txznet.comm.ui.TE.T.Tr()
            if (r4 != r8) goto L_0x00ab
            java.lang.Class<com.txznet.comm.ui.TZ.Ty> r1 = com.txznet.comm.ui.TZ.Ty.class
            goto L_0x00ab
        L_0x023f:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x00e3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.txznet.comm.ui.TZ.Tn.Ty():void");
    }

    public static float T(int size) {
        return Tr.Tn("x" + size);
    }

    public static float Tr(int size) {
        return Tr.Tn("y" + size);
    }

    public void T(T src) {
        Tr = src.f543T;
        Ty = src.Tr;
        Tn = src.Ty;
        T9 = src.Tn;
        Tk = src.T9;
        TZ = src.Tk;
        TE = src.TZ;
        T5 = src.TE;
        Tv = src.T5;
        Th = src.Tv;
        T6 = src.Th;
        Te = src.Th;
        Tq = src.Te;
        TF = src.Tq;
        Tj = src.TF;
        TB = src.Tj;
        TK = src.TB;
        TO = src.TK;
        TN = src.TO;
        Ts = src.TN;
        TG = src.Ts;
        Tu = src.TG;
        Tt = src.Tu;
        TD = src.Tt;
        Tf = src.TD;
        TA = src.Tf;
        Tx = src.TA;
        T0 = src.Tx;
        TV = src.T0;
        Tb = src.TV;
        TM = src.Tb;
        TX = src.TM;
    }
}
