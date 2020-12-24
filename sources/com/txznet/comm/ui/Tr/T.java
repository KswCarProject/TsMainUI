package com.txznet.comm.ui.Tr;

import android.text.TextUtils;
import com.txznet.comm.ui.T9.Tk;
import com.txznet.comm.ui.TE.Tr;
import java.util.HashMap;

/* compiled from: Proguard */
public class T {
    private static T Tn;

    /* renamed from: T  reason: collision with root package name */
    HashMap<String, Object> f560T = null;
    private Object T9 = new Object();
    private Float Tk = null;
    public int Tr = -16777216;
    public int Ty = 15;

    private T() {
    }

    public void T() {
        synchronized (this.T9) {
            T9();
            if (!Tk.Tr().TZ()) {
                Tn();
            }
            Ty();
        }
    }

    public static T Tr() {
        if (Tn == null) {
            synchronized (T.class) {
                if (Tn == null) {
                    Tn = new T();
                }
            }
        }
        return Tn;
    }

    private void Ty() {
        this.f560T = com.txznet.comm.ui.TE.T.T(this.f560T, com.txznet.comm.Ty.T.T9);
    }

    private void Tn() {
        this.f560T = com.txznet.comm.ui.TE.T.T(this.f560T, "/etc/txz/theme.cfg");
        this.f560T = com.txznet.comm.ui.TE.T.T(this.f560T, "/system/txz/theme.cfg");
        this.f560T = com.txznet.comm.ui.TE.T.T(this.f560T, "/system/app/theme.cfg");
        this.f560T = com.txznet.comm.ui.TE.T.T(this.f560T, "/custom/etc/theme.cfg");
        this.f560T = com.txznet.comm.ui.TE.T.T(this.f560T, "/vendor/txz/theme.cfg");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v17, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.util.HashMap} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void T9() {
        /*
            r12 = this;
            r11 = 4
            r10 = 3
            r9 = 2
            r8 = 1
            java.lang.String r6 = com.txznet.comm.ui.TE.T.Tk()
            int r7 = com.txznet.comm.ui.TE.T.T()
            if (r7 != r10) goto L_0x0023
            java.util.HashMap r7 = com.txznet.comm.ui.TZ.T.Ty.Tr()
            r12.f560T = r7
        L_0x0014:
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r1 = 0
            boolean r7 = android.text.TextUtils.isEmpty(r6)
            if (r7 == 0) goto L_0x004a
        L_0x0020:
            if (r2 != 0) goto L_0x00f6
        L_0x0022:
            return
        L_0x0023:
            int r7 = com.txznet.comm.ui.TE.T.T()
            if (r7 != r8) goto L_0x0030
            java.util.HashMap r7 = com.txznet.comm.ui.TZ.T.Tn.Tr()
            r12.f560T = r7
            goto L_0x0014
        L_0x0030:
            int r7 = com.txznet.comm.ui.TE.T.T()
            if (r7 != r11) goto L_0x003d
            java.util.HashMap r7 = com.txznet.comm.ui.TZ.T.Tr.Tr()
            r12.f560T = r7
            goto L_0x0014
        L_0x003d:
            int r7 = com.txznet.comm.ui.TE.T.T()
            if (r7 != r9) goto L_0x0014
            java.util.HashMap r7 = com.txznet.comm.ui.TZ.T.T.T()
            r12.f560T = r7
            goto L_0x0014
        L_0x004a:
            int r7 = com.txznet.comm.ui.TE.T.T()     // Catch:{ Exception -> 0x00f0 }
            if (r7 != r10) goto L_0x0084
            com.txznet.comm.ui.Tk.T r7 = com.txznet.comm.ui.Tk.T.Tr()     // Catch:{ Exception -> 0x00f0 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00f0 }
            r8.<init>()     // Catch:{ Exception -> 0x00f0 }
            java.lang.StringBuilder r8 = r8.append(r6)     // Catch:{ Exception -> 0x00f0 }
            java.lang.String r9 = "ThemeConfigLarge"
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x00f0 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x00f0 }
            java.lang.Class r1 = r7.Ty(r8)     // Catch:{ Exception -> 0x00f0 }
        L_0x006b:
            if (r1 == 0) goto L_0x0020
            java.lang.String r7 = "getConfig"
            r8 = 0
            java.lang.Class[] r8 = new java.lang.Class[r8]     // Catch:{ Exception -> 0x00ea }
            java.lang.reflect.Method r4 = r1.getMethod(r7, r8)     // Catch:{ Exception -> 0x00ea }
            java.lang.String r7 = "getConfig"
            r8 = 0
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ Exception -> 0x00ea }
            java.lang.Object r7 = r4.invoke(r7, r8)     // Catch:{ Exception -> 0x00ea }
            r0 = r7
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Exception -> 0x00ea }
            r2 = r0
            goto L_0x0020
        L_0x0084:
            int r7 = com.txznet.comm.ui.TE.T.T()     // Catch:{ Exception -> 0x00f0 }
            if (r7 != r8) goto L_0x00a6
            com.txznet.comm.ui.Tk.T r7 = com.txznet.comm.ui.Tk.T.Tr()     // Catch:{ Exception -> 0x00f0 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00f0 }
            r8.<init>()     // Catch:{ Exception -> 0x00f0 }
            java.lang.StringBuilder r8 = r8.append(r6)     // Catch:{ Exception -> 0x00f0 }
            java.lang.String r9 = "ThemeConfigSmall"
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x00f0 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x00f0 }
            java.lang.Class r1 = r7.Ty(r8)     // Catch:{ Exception -> 0x00f0 }
            goto L_0x006b
        L_0x00a6:
            int r7 = com.txznet.comm.ui.TE.T.T()     // Catch:{ Exception -> 0x00f0 }
            if (r7 != r11) goto L_0x00c8
            com.txznet.comm.ui.Tk.T r7 = com.txznet.comm.ui.Tk.T.Tr()     // Catch:{ Exception -> 0x00f0 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00f0 }
            r8.<init>()     // Catch:{ Exception -> 0x00f0 }
            java.lang.StringBuilder r8 = r8.append(r6)     // Catch:{ Exception -> 0x00f0 }
            java.lang.String r9 = "ThemeConfigCar"
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x00f0 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x00f0 }
            java.lang.Class r1 = r7.Ty(r8)     // Catch:{ Exception -> 0x00f0 }
            goto L_0x006b
        L_0x00c8:
            int r7 = com.txznet.comm.ui.TE.T.T()     // Catch:{ Exception -> 0x00f0 }
            if (r7 != r9) goto L_0x006b
            com.txznet.comm.ui.Tk.T r7 = com.txznet.comm.ui.Tk.T.Tr()     // Catch:{ Exception -> 0x00f0 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00f0 }
            r8.<init>()     // Catch:{ Exception -> 0x00f0 }
            java.lang.StringBuilder r8 = r8.append(r6)     // Catch:{ Exception -> 0x00f0 }
            java.lang.String r9 = "ThemeConfig"
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ Exception -> 0x00f0 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x00f0 }
            java.lang.Class r1 = r7.Ty(r8)     // Catch:{ Exception -> 0x00f0 }
            goto L_0x006b
        L_0x00ea:
            r3 = move-exception
            r3.printStackTrace()     // Catch:{ Exception -> 0x00f0 }
            goto L_0x0020
        L_0x00f0:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x0020
        L_0x00f6:
            java.util.Set r7 = r2.keySet()
            java.util.Iterator r7 = r7.iterator()
        L_0x00fe:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x0022
            java.lang.Object r5 = r7.next()
            java.lang.String r5 = (java.lang.String) r5
            java.util.HashMap<java.lang.String, java.lang.Object> r8 = r12.f560T
            java.lang.Object r9 = r2.get(r5)
            r8.put(r5, r9)
            goto L_0x00fe
        */
        throw new UnsupportedOperationException("Method not decompiled: com.txznet.comm.ui.Tr.T.T9():void");
    }

    public Object T(String attrName) {
        Object obj = null;
        synchronized (this.T9) {
            if (!TextUtils.isEmpty(attrName)) {
                if (this.f560T == null) {
                    if (com.txznet.comm.ui.TE.T.Tr(attrName) == 2) {
                        obj = Float.valueOf(T(this.Ty));
                    } else if (com.txznet.comm.ui.TE.T.Tr(attrName) == 1) {
                        obj = Integer.valueOf(this.Tr);
                    }
                }
                if (this.f560T == null || this.f560T.get(attrName) == null) {
                    int splitIndex = attrName.indexOf(".");
                    if (splitIndex != -1 && splitIndex < attrName.length()) {
                        obj = T(attrName.substring(splitIndex + 1, attrName.length()));
                    } else if (com.txznet.comm.ui.TE.T.Tr(attrName) == 1) {
                        obj = Integer.valueOf(this.Tr);
                    } else if (com.txznet.comm.ui.TE.T.Tr(attrName) == 2) {
                        obj = Float.valueOf(T(this.Ty));
                    }
                } else {
                    obj = com.txznet.comm.ui.TE.T.Tr(attrName) == 2 ? Float.valueOf(T(((Integer) this.f560T.get(attrName)).intValue())) : this.f560T.get(attrName);
                }
            }
        }
        return obj;
    }

    public int Tr(String colorName) {
        int Ty2;
        synchronized (this.T9) {
            if (TextUtils.isEmpty(colorName)) {
                Ty2 = this.Tr;
            } else {
                if (!(this.f560T == null || this.f560T.get(colorName) == null)) {
                    try {
                        Ty2 = ((Integer) this.f560T.get(colorName)).intValue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Ty2 = Ty(colorName);
            }
        }
        return Ty2;
    }

    private int Ty(String colorName) {
        if ("theme_color1".equals(colorName)) {
            return com.txznet.comm.ui.TE.T.T("#14211A").intValue();
        }
        if ("theme_color2".equals(colorName)) {
            return com.txznet.comm.ui.TE.T.T("#4C3736").intValue();
        }
        if ("theme_color2".equals(colorName)) {
            return com.txznet.comm.ui.TE.T.T("#FFFFFF").intValue();
        }
        return this.Tr;
    }

    public float T(int size) {
        float ysize = Tr.Tn("y" + size);
        float xsize = Tr.Tn("x" + size);
        float ssize = ysize;
        if (xsize != 0.0f && xsize < ysize) {
            ssize = xsize;
        }
        if (ssize == 0.0f) {
            return (float) size;
        }
        if (this.Tk != null && this.Tk.floatValue() >= 0.0f) {
            return ((float) size) * this.Tk.floatValue();
        }
        if (com.txznet.comm.ui.TE.T.T() == 4) {
            return ssize * 0.8f;
        }
        return ssize;
    }
}
