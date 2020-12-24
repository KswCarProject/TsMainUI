package com.txznet.comm.ui.TZ;

import com.txznet.comm.ui.TE.Tr;

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
    String f540T = "";

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
            r11.f540T = r4
            java.lang.String r4 = r11.f540T
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 == 0) goto L_0x0115
        L_0x0014:
            int r4 = com.txznet.comm.ui.TE.T.Tr()     // Catch:{ Exception -> 0x01f2 }
            if (r4 != r7) goto L_0x01c4
            com.txznet.comm.ui.Tk.T r4 = com.txznet.comm.ui.Tk.T.Tr()     // Catch:{ Exception -> 0x01f2 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01f2 }
            r5.<init>()     // Catch:{ Exception -> 0x01f2 }
            java.lang.String r6 = r11.f540T     // Catch:{ Exception -> 0x01f2 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01f2 }
            java.lang.String r6 = "ThemeConfigHorizontal"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01f2 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x01f2 }
            java.lang.Class r1 = r4.Ty(r5)     // Catch:{ Exception -> 0x01f2 }
            if (r1 != 0) goto L_0x0040
            java.lang.String r4 = "get ThemeConfigVertical error!"
            com.txznet.comm.Tr.Tr.Tn.Ty((java.lang.String) r4)     // Catch:{ Exception -> 0x01f2 }
            java.lang.Class<com.txznet.comm.ui.TZ.Tr> r1 = com.txznet.comm.ui.TZ.Tr.class
        L_0x0040:
            java.lang.String r4 = "RECORD_WIN_CIRCLE_LY_HEIGHT"
            java.lang.reflect.Field r4 = r1.getField(r4)     // Catch:{ Exception -> 0x01f2 }
            r5 = 0
            int r4 = r4.getInt(r5)     // Catch:{ Exception -> 0x01f2 }
            TP = r4     // Catch:{ Exception -> 0x01f2 }
            java.lang.String r4 = "RECORD_WIN_CIRCLE_LY_HEIGHT"
            java.lang.reflect.Field r4 = r1.getField(r4)     // Catch:{ Exception -> 0x01f2 }
            r5 = 0
            int r4 = r4.getInt(r5)     // Catch:{ Exception -> 0x01f2 }
            TI = r4     // Catch:{ Exception -> 0x01f2 }
            java.lang.String r4 = "RECORD_WIN_VOICE_VIEW_WIDTH"
            java.lang.reflect.Field r4 = r1.getField(r4)     // Catch:{ Exception -> 0x01f2 }
            r5 = 0
            int r4 = r4.getInt(r5)     // Catch:{ Exception -> 0x01f2 }
            TL = r4     // Catch:{ Exception -> 0x01f2 }
            java.lang.String r4 = "RECORD_WIN_VOICE_VIEW_HEIGHT"
            java.lang.reflect.Field r4 = r1.getField(r4)     // Catch:{ Exception -> 0x01f2 }
            r5 = 0
            int r4 = r4.getInt(r5)     // Catch:{ Exception -> 0x01f2 }
            Tw = r4     // Catch:{ Exception -> 0x01f2 }
        L_0x0074:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "themeConfig:"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r3)
            java.lang.String r4 = r4.toString()
            com.txznet.comm.Tr.Tr.Tn.T((java.lang.String) r4)
            if (r3 != 0) goto L_0x0097
            int r4 = com.txznet.comm.ui.TE.T.T()
            if (r4 != r9) goto L_0x01f8
            com.txznet.comm.ui.TZ.T.Ty r3 = new com.txznet.comm.ui.TZ.T.Ty
            r3.<init>()
        L_0x0097:
            r11.T((com.txznet.comm.ui.TZ.T) r3)
            if (r1 != 0) goto L_0x00d8
            int r4 = com.txznet.comm.ui.TE.T.Tr()
            if (r4 != r7) goto L_0x021f
            java.lang.Class<com.txznet.comm.ui.TZ.Tr> r1 = com.txznet.comm.ui.TZ.Tr.class
        L_0x00a4:
            java.lang.String r4 = "RECORD_WIN_CIRCLE_LY_HEIGHT"
            java.lang.reflect.Field r4 = r1.getField(r4)     // Catch:{ Exception -> 0x0229 }
            r5 = 0
            int r4 = r4.getInt(r5)     // Catch:{ Exception -> 0x0229 }
            TP = r4     // Catch:{ Exception -> 0x0229 }
            java.lang.String r4 = "RECORD_WIN_CIRCLE_LY_HEIGHT"
            java.lang.reflect.Field r4 = r1.getField(r4)     // Catch:{ Exception -> 0x0229 }
            r5 = 0
            int r4 = r4.getInt(r5)     // Catch:{ Exception -> 0x0229 }
            TI = r4     // Catch:{ Exception -> 0x0229 }
            java.lang.String r4 = "RECORD_WIN_VOICE_VIEW_WIDTH"
            java.lang.reflect.Field r4 = r1.getField(r4)     // Catch:{ Exception -> 0x0229 }
            r5 = 0
            int r4 = r4.getInt(r5)     // Catch:{ Exception -> 0x0229 }
            TL = r4     // Catch:{ Exception -> 0x0229 }
            java.lang.String r4 = "RECORD_WIN_VOICE_VIEW_HEIGHT"
            java.lang.reflect.Field r4 = r1.getField(r4)     // Catch:{ Exception -> 0x0229 }
            r5 = 0
            int r4 = r4.getInt(r5)     // Catch:{ Exception -> 0x0229 }
            Tw = r4     // Catch:{ Exception -> 0x0229 }
        L_0x00d8:
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
        L_0x0115:
            int r4 = com.txznet.comm.ui.TE.T.T()     // Catch:{ Exception -> 0x01b9 }
            if (r4 != r9) goto L_0x013e
            com.txznet.comm.ui.Tk.T r4 = com.txznet.comm.ui.Tk.T.Tr()     // Catch:{ Exception -> 0x01b9 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b9 }
            r5.<init>()     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r6 = r11.f540T     // Catch:{ Exception -> 0x01b9 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r6 = "ThemeConfigLarge"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x01b9 }
            java.lang.Object r4 = r4.Tr(r5)     // Catch:{ Exception -> 0x01b9 }
            r0 = r4
            com.txznet.comm.ui.TZ.T r0 = (com.txznet.comm.ui.TZ.T) r0     // Catch:{ Exception -> 0x01b9 }
            r3 = r0
            goto L_0x0014
        L_0x013e:
            int r4 = com.txznet.comm.ui.TE.T.T()     // Catch:{ Exception -> 0x01b9 }
            if (r4 != r7) goto L_0x0167
            com.txznet.comm.ui.Tk.T r4 = com.txznet.comm.ui.Tk.T.Tr()     // Catch:{ Exception -> 0x01b9 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b9 }
            r5.<init>()     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r6 = r11.f540T     // Catch:{ Exception -> 0x01b9 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r6 = "ThemeConfigSmall"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x01b9 }
            java.lang.Object r4 = r4.Tr(r5)     // Catch:{ Exception -> 0x01b9 }
            r0 = r4
            com.txznet.comm.ui.TZ.T r0 = (com.txznet.comm.ui.TZ.T) r0     // Catch:{ Exception -> 0x01b9 }
            r3 = r0
            goto L_0x0014
        L_0x0167:
            int r4 = com.txznet.comm.ui.TE.T.T()     // Catch:{ Exception -> 0x01b9 }
            if (r4 != r10) goto L_0x0190
            com.txznet.comm.ui.Tk.T r4 = com.txznet.comm.ui.Tk.T.Tr()     // Catch:{ Exception -> 0x01b9 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b9 }
            r5.<init>()     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r6 = r11.f540T     // Catch:{ Exception -> 0x01b9 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r6 = "ThemeConfigCar"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x01b9 }
            java.lang.Object r4 = r4.Tr(r5)     // Catch:{ Exception -> 0x01b9 }
            r0 = r4
            com.txznet.comm.ui.TZ.T r0 = (com.txznet.comm.ui.TZ.T) r0     // Catch:{ Exception -> 0x01b9 }
            r3 = r0
            goto L_0x0014
        L_0x0190:
            int r4 = com.txznet.comm.ui.TE.T.T()     // Catch:{ Exception -> 0x01b9 }
            if (r4 != r8) goto L_0x0014
            com.txznet.comm.ui.Tk.T r4 = com.txznet.comm.ui.Tk.T.Tr()     // Catch:{ Exception -> 0x01b9 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b9 }
            r5.<init>()     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r6 = r11.f540T     // Catch:{ Exception -> 0x01b9 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r6 = "ThemeConfig"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x01b9 }
            java.lang.Object r4 = r4.Tr(r5)     // Catch:{ Exception -> 0x01b9 }
            r0 = r4
            com.txznet.comm.ui.TZ.T r0 = (com.txznet.comm.ui.TZ.T) r0     // Catch:{ Exception -> 0x01b9 }
            r3 = r0
            goto L_0x0014
        L_0x01b9:
            r2 = move-exception
            java.lang.String r4 = "load theme config error"
            com.txznet.comm.Tr.Tr.Tn.Tn(r4)
            r2.printStackTrace()
            goto L_0x0014
        L_0x01c4:
            int r4 = com.txznet.comm.ui.TE.T.Tr()     // Catch:{ Exception -> 0x01f2 }
            if (r4 != r8) goto L_0x0040
            com.txznet.comm.ui.Tk.T r4 = com.txznet.comm.ui.Tk.T.Tr()     // Catch:{ Exception -> 0x01f2 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01f2 }
            r5.<init>()     // Catch:{ Exception -> 0x01f2 }
            java.lang.String r6 = r11.f540T     // Catch:{ Exception -> 0x01f2 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01f2 }
            java.lang.String r6 = "ThemeConfigVertical"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01f2 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x01f2 }
            java.lang.Class r1 = r4.Ty(r5)     // Catch:{ Exception -> 0x01f2 }
            if (r1 != 0) goto L_0x0040
            java.lang.String r4 = "get ThemeConfigVertical error!"
            com.txznet.comm.Tr.Tr.Tn.Ty((java.lang.String) r4)     // Catch:{ Exception -> 0x01f2 }
            java.lang.Class<com.txznet.comm.ui.TZ.Tr> r1 = com.txznet.comm.ui.TZ.Tr.class
            goto L_0x0040
        L_0x01f2:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0074
        L_0x01f8:
            int r4 = com.txznet.comm.ui.TE.T.T()
            if (r4 != r7) goto L_0x0205
            com.txznet.comm.ui.TZ.T.Tn r3 = new com.txznet.comm.ui.TZ.T.Tn
            r3.<init>()
            goto L_0x0097
        L_0x0205:
            int r4 = com.txznet.comm.ui.TE.T.T()
            if (r4 != r10) goto L_0x0212
            com.txznet.comm.ui.TZ.T.Tr r3 = new com.txznet.comm.ui.TZ.T.Tr
            r3.<init>()
            goto L_0x0097
        L_0x0212:
            int r4 = com.txznet.comm.ui.TE.T.T()
            if (r4 != r8) goto L_0x0097
            com.txznet.comm.ui.TZ.T.T r3 = new com.txznet.comm.ui.TZ.T.T
            r3.<init>()
            goto L_0x0097
        L_0x021f:
            int r4 = com.txznet.comm.ui.TE.T.Tr()
            if (r4 != r8) goto L_0x00a4
            java.lang.Class<com.txznet.comm.ui.TZ.Ty> r1 = com.txznet.comm.ui.TZ.Ty.class
            goto L_0x00a4
        L_0x0229:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x00d8
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
        Tr = src.f539T;
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
