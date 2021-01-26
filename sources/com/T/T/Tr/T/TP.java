package com.T.T.Tr.T;

import com.T.T.T9;
import com.T.T.Tn.Tr;
import com.T.T.Tn.Ty;
import com.T.T.Tr.Tn;
import com.T.T.Tr.Tv;
import com.txznet.sdk.TXZResourceManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: Proguard */
public class TP implements T7 {

    /* renamed from: T  reason: collision with root package name */
    private final Map<String, Ts> f156T;
    private Tr Tn;
    private final List<Ts> Tr;
    private final Class<?> Ty;

    public TP(Tv config, Class<?> clazz) {
        this(config, clazz, clazz);
    }

    public TP(Tv config, Class<?> clazz, Type type) {
        this.f156T = new IdentityHashMap();
        this.Tr = new ArrayList();
        this.Ty = clazz;
        this.Tn = Tr.T(clazz, type);
        for (Ty fieldInfo : this.Tn.Tn()) {
            Tr(config, clazz, fieldInfo);
        }
    }

    public Map<String, Ts> Tr() {
        return this.f156T;
    }

    private void Tr(Tv mapping, Class<?> clazz, Ty fieldInfo) {
        Ts fieldDeserializer = T(mapping, clazz, fieldInfo);
        this.f156T.put(fieldInfo.Ty().intern(), fieldDeserializer);
        this.Tr.add(fieldDeserializer);
    }

    public Ts T(Tv mapping, Class<?> clazz, Ty fieldInfo) {
        return mapping.T(mapping, clazz, fieldInfo);
    }

    public Object T(com.T.T.Tr.Ty parser, Type type) {
        Object object;
        if ((type instanceof Class) && this.Ty.isInterface()) {
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{(Class) type}, new T9());
        } else if (this.Tn.T() == null) {
            return null;
        } else {
            try {
                Constructor<?> constructor = this.Tn.T();
                if (constructor.getParameterTypes().length == 0) {
                    object = constructor.newInstance(new Object[0]);
                } else {
                    object = constructor.newInstance(new Object[]{parser.Tk().Tr()});
                }
                if (parser.T(Tn.InitStringFieldAsEmpty)) {
                    for (Ty fieldInfo : this.Tn.Tn()) {
                        if (fieldInfo.T() == String.class) {
                            try {
                                fieldInfo.T(object, (Object) TXZResourceManager.STYLE_DEFAULT);
                            } catch (Exception e) {
                                throw new com.T.T.Tn("create instance error, class " + this.Ty.getName(), e);
                            }
                        }
                    }
                }
                return object;
            } catch (Exception e2) {
                throw new com.T.T.Tn("create instance error, class " + this.Ty.getName(), e2);
            }
        }
    }

    public <T> T T(com.T.T.Tr.Ty parser, Type type, Object fieldName) {
        return T(parser, type, fieldName, (Object) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:103:0x023a, code lost:
        r26 = com.T.T.Tn.TZ.T(r25);
        r3 = r28.Ty().T((java.lang.reflect.Type) r26).T(r28, r26, r30);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0252, code lost:
        if (r10 == null) goto L_0x0259;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0254, code lost:
        r10.T((java.lang.Object) r31);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0259, code lost:
        r28.T(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x02a0, code lost:
        r18.T();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x02f7, code lost:
        r15 = r27.Tn.Tn();
        r24 = r15.size();
        r20 = new java.lang.Object[r24];
        r17 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x030f, code lost:
        if (r17 >= r24) goto L_0x0326;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0311, code lost:
        r20[r17] = r8.get(r15.get(r17).Ty());
        r17 = r17 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x032e, code lost:
        if (r27.Tn.Tr() == null) goto L_0x0375;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:?, code lost:
        r31 = r27.Tn.Tr().newInstance(r20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x033e, code lost:
        if (r10 == null) goto L_0x0345;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0340, code lost:
        r10.T((java.lang.Object) r31);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0345, code lost:
        r28.T(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x037d, code lost:
        if (r27.Tn.Ty() == null) goto L_0x033e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:?, code lost:
        r31 = r27.Tn.Ty().invoke((java.lang.Object) null, r20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:?, code lost:
        return r31;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:?, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:?, code lost:
        return r31;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ce, code lost:
        if (r31 != null) goto L_0x033e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d0, code lost:
        if (r8 != null) goto L_0x02f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r31 = T(r28, r29);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00d6, code lost:
        if (r10 == null) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d8, code lost:
        r10.T(r31);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00dd, code lost:
        r28.T(r11);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T T(com.T.T.Tr.Ty r28, java.lang.reflect.Type r29, java.lang.Object r30, java.lang.Object r31) {
        /*
            r27 = this;
            com.T.T.Tr.T9 r18 = r28.Th()
            int r3 = r18.Tn()
            r4 = 8
            if (r3 != r4) goto L_0x0015
            r3 = 16
            r0 = r18
            r0.T((int) r3)
            r3 = 0
        L_0x0014:
            return r3
        L_0x0015:
            com.T.T.Tr.T5 r11 = r28.Tk()
            if (r31 == 0) goto L_0x001f
            com.T.T.Tr.T5 r11 = r11.Ty()
        L_0x001f:
            r10 = 0
            r8 = 0
            int r3 = r18.Tn()     // Catch:{ all -> 0x0094 }
            r4 = 13
            if (r3 != r4) goto L_0x0045
            r3 = 16
            r0 = r18
            r0.T((int) r3)     // Catch:{ all -> 0x0094 }
            if (r31 != 0) goto L_0x0036
            java.lang.Object r31 = r27.T(r28, r29)     // Catch:{ all -> 0x0094 }
        L_0x0036:
            if (r10 == 0) goto L_0x003d
            r0 = r31
            r10.T((java.lang.Object) r0)
        L_0x003d:
            r0 = r28
            r0.T((com.T.T.Tr.T5) r11)
            r3 = r31
            goto L_0x0014
        L_0x0045:
            int r3 = r18.Tn()     // Catch:{ all -> 0x0094 }
            r4 = 12
            if (r3 == r4) goto L_0x00a2
            int r3 = r18.Tn()     // Catch:{ all -> 0x0094 }
            r4 = 16
            if (r3 == r4) goto L_0x00a2
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch:{ all -> 0x0094 }
            r3.<init>()     // Catch:{ all -> 0x0094 }
            java.lang.String r4 = "syntax error, expect {, actual "
            java.lang.StringBuffer r3 = r3.append(r4)     // Catch:{ all -> 0x0094 }
            java.lang.String r4 = r18.T9()     // Catch:{ all -> 0x0094 }
            java.lang.StringBuffer r3 = r3.append(r4)     // Catch:{ all -> 0x0094 }
            java.lang.String r4 = ", pos "
            java.lang.StringBuffer r3 = r3.append(r4)     // Catch:{ all -> 0x0094 }
            int r4 = r18.Tk()     // Catch:{ all -> 0x0094 }
            java.lang.StringBuffer r9 = r3.append(r4)     // Catch:{ all -> 0x0094 }
            r0 = r30
            boolean r3 = r0 instanceof java.lang.String     // Catch:{ all -> 0x0094 }
            if (r3 == 0) goto L_0x008a
            java.lang.String r3 = ", fieldName "
            java.lang.StringBuffer r3 = r9.append(r3)     // Catch:{ all -> 0x0094 }
            r0 = r30
            r3.append(r0)     // Catch:{ all -> 0x0094 }
        L_0x008a:
            com.T.T.Tn r3 = new com.T.T.Tn     // Catch:{ all -> 0x0094 }
            java.lang.String r4 = r9.toString()     // Catch:{ all -> 0x0094 }
            r3.<init>(r4)     // Catch:{ all -> 0x0094 }
            throw r3     // Catch:{ all -> 0x0094 }
        L_0x0094:
            r3 = move-exception
        L_0x0095:
            if (r10 == 0) goto L_0x009c
            r0 = r31
            r10.T((java.lang.Object) r0)
        L_0x009c:
            r0 = r28
            r0.T((com.T.T.Tr.T5) r11)
            throw r3
        L_0x00a2:
            int r3 = r28.Tn()     // Catch:{ all -> 0x0094 }
            r4 = 2
            if (r3 != r4) goto L_0x00af
            r3 = 0
            r0 = r28
            r0.T((int) r3)     // Catch:{ all -> 0x0094 }
        L_0x00af:
            r16 = r8
        L_0x00b1:
            com.T.T.Tr.Th r3 = r28.Tr()     // Catch:{ all -> 0x0135 }
            r0 = r18
            java.lang.String r5 = r0.T((com.T.T.Tr.Th) r3)     // Catch:{ all -> 0x0135 }
            if (r5 != 0) goto L_0x00f8
            int r3 = r18.Tn()     // Catch:{ all -> 0x0135 }
            r4 = 13
            if (r3 != r4) goto L_0x00e6
            r3 = 16
            r0 = r18
            r0.T((int) r3)     // Catch:{ all -> 0x0135 }
            r8 = r16
        L_0x00ce:
            if (r31 != 0) goto L_0x033e
            if (r8 != 0) goto L_0x02f7
            java.lang.Object r31 = r27.T(r28, r29)     // Catch:{ all -> 0x0094 }
            if (r10 == 0) goto L_0x00dd
            r0 = r31
            r10.T((java.lang.Object) r0)
        L_0x00dd:
            r0 = r28
            r0.T((com.T.T.Tr.T5) r11)
            r3 = r31
            goto L_0x0014
        L_0x00e6:
            int r3 = r18.Tn()     // Catch:{ all -> 0x0135 }
            r4 = 16
            if (r3 != r4) goto L_0x00f8
            com.T.T.Tr.Tn r3 = com.T.T.Tr.Tn.AllowArbitraryCommas     // Catch:{ all -> 0x0135 }
            r0 = r28
            boolean r3 = r0.T((com.T.T.Tr.Tn) r3)     // Catch:{ all -> 0x0135 }
            if (r3 != 0) goto L_0x00b1
        L_0x00f8:
            java.lang.String r3 = "$ref"
            if (r3 != r5) goto L_0x01f8
            r3 = 4
            r0 = r18
            r0.Tr((int) r3)     // Catch:{ all -> 0x0135 }
            int r3 = r18.Tn()     // Catch:{ all -> 0x0135 }
            r4 = 4
            if (r3 != r4) goto L_0x01b6
            java.lang.String r22 = r18.Tf()     // Catch:{ all -> 0x0135 }
            java.lang.String r3 = "@"
            r0 = r22
            boolean r3 = r3.equals(r0)     // Catch:{ all -> 0x0135 }
            if (r3 == 0) goto L_0x013a
            java.lang.Object r31 = r11.Tr()     // Catch:{ all -> 0x0135 }
        L_0x011d:
            r3 = 13
            r0 = r18
            r0.T((int) r3)     // Catch:{ all -> 0x0135 }
            int r3 = r18.Tn()     // Catch:{ all -> 0x0135 }
            r4 = 13
            if (r3 == r4) goto L_0x01d8
            com.T.T.Tn r3 = new com.T.T.Tn     // Catch:{ all -> 0x0135 }
            java.lang.String r4 = "illegal ref"
            r3.<init>(r4)     // Catch:{ all -> 0x0135 }
            throw r3     // Catch:{ all -> 0x0135 }
        L_0x0135:
            r3 = move-exception
            r8 = r16
            goto L_0x0095
        L_0x013a:
            java.lang.String r3 = ".."
            r0 = r22
            boolean r3 = r3.equals(r0)     // Catch:{ all -> 0x0135 }
            if (r3 == 0) goto L_0x0169
            com.T.T.Tr.T5 r21 = r11.Ty()     // Catch:{ all -> 0x0135 }
            java.lang.Object r3 = r21.Tr()     // Catch:{ all -> 0x0135 }
            if (r3 == 0) goto L_0x0154
            java.lang.Object r31 = r21.Tr()     // Catch:{ all -> 0x0135 }
            goto L_0x011d
        L_0x0154:
            com.T.T.Tr.Ty$T r3 = new com.T.T.Tr.Ty$T     // Catch:{ all -> 0x0135 }
            r0 = r21
            r1 = r22
            r3.<init>(r0, r1)     // Catch:{ all -> 0x0135 }
            r0 = r28
            r0.T((com.T.T.Tr.Ty.T) r3)     // Catch:{ all -> 0x0135 }
            r3 = 1
            r0 = r28
            r0.T((int) r3)     // Catch:{ all -> 0x0135 }
            goto L_0x011d
        L_0x0169:
            java.lang.String r3 = "$"
            r0 = r22
            boolean r3 = r3.equals(r0)     // Catch:{ all -> 0x0135 }
            if (r3 == 0) goto L_0x01a2
            r23 = r11
        L_0x0176:
            com.T.T.Tr.T5 r3 = r23.Ty()     // Catch:{ all -> 0x0135 }
            if (r3 == 0) goto L_0x0181
            com.T.T.Tr.T5 r23 = r23.Ty()     // Catch:{ all -> 0x0135 }
            goto L_0x0176
        L_0x0181:
            java.lang.Object r3 = r23.Tr()     // Catch:{ all -> 0x0135 }
            if (r3 == 0) goto L_0x018c
            java.lang.Object r31 = r23.Tr()     // Catch:{ all -> 0x0135 }
            goto L_0x011d
        L_0x018c:
            com.T.T.Tr.Ty$T r3 = new com.T.T.Tr.Ty$T     // Catch:{ all -> 0x0135 }
            r0 = r23
            r1 = r22
            r3.<init>(r0, r1)     // Catch:{ all -> 0x0135 }
            r0 = r28
            r0.T((com.T.T.Tr.Ty.T) r3)     // Catch:{ all -> 0x0135 }
            r3 = 1
            r0 = r28
            r0.T((int) r3)     // Catch:{ all -> 0x0135 }
            goto L_0x011d
        L_0x01a2:
            com.T.T.Tr.Ty$T r3 = new com.T.T.Tr.Ty$T     // Catch:{ all -> 0x0135 }
            r0 = r22
            r3.<init>(r11, r0)     // Catch:{ all -> 0x0135 }
            r0 = r28
            r0.T((com.T.T.Tr.Ty.T) r3)     // Catch:{ all -> 0x0135 }
            r3 = 1
            r0 = r28
            r0.T((int) r3)     // Catch:{ all -> 0x0135 }
            goto L_0x011d
        L_0x01b6:
            com.T.T.Tn r3 = new com.T.T.Tn     // Catch:{ all -> 0x0135 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0135 }
            r4.<init>()     // Catch:{ all -> 0x0135 }
            java.lang.String r6 = "illegal ref, "
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x0135 }
            int r6 = r18.Tn()     // Catch:{ all -> 0x0135 }
            java.lang.String r6 = com.T.T.Tr.TZ.T(r6)     // Catch:{ all -> 0x0135 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x0135 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0135 }
            r3.<init>(r4)     // Catch:{ all -> 0x0135 }
            throw r3     // Catch:{ all -> 0x0135 }
        L_0x01d8:
            r3 = 16
            r0 = r18
            r0.T((int) r3)     // Catch:{ all -> 0x0135 }
            r0 = r28
            r1 = r31
            r2 = r30
            r0.T((com.T.T.Tr.T5) r11, (java.lang.Object) r1, (java.lang.Object) r2)     // Catch:{ all -> 0x0135 }
            if (r10 == 0) goto L_0x01ef
            r0 = r31
            r10.T((java.lang.Object) r0)
        L_0x01ef:
            r0 = r28
            r0.T((com.T.T.Tr.T5) r11)
            r3 = r31
            goto L_0x0014
        L_0x01f8:
            java.lang.String r3 = com.T.T.T.f131T     // Catch:{ all -> 0x0135 }
            if (r3 != r5) goto L_0x0269
            r3 = 4
            r0 = r18
            r0.Tr((int) r3)     // Catch:{ all -> 0x0135 }
            int r3 = r18.Tn()     // Catch:{ all -> 0x0135 }
            r4 = 4
            if (r3 != r4) goto L_0x0260
            java.lang.String r25 = r18.Tf()     // Catch:{ all -> 0x0135 }
            r3 = 16
            r0 = r18
            r0.T((int) r3)     // Catch:{ all -> 0x0135 }
            r0 = r29
            boolean r3 = r0 instanceof java.lang.Class     // Catch:{ all -> 0x0135 }
            if (r3 == 0) goto L_0x023a
            r0 = r29
            java.lang.Class r0 = (java.lang.Class) r0     // Catch:{ all -> 0x0135 }
            r3 = r0
            java.lang.String r3 = r3.getName()     // Catch:{ all -> 0x0135 }
            r0 = r25
            boolean r3 = r0.equals(r3)     // Catch:{ all -> 0x0135 }
            if (r3 == 0) goto L_0x023a
            int r3 = r18.Tn()     // Catch:{ all -> 0x0135 }
            r4 = 13
            if (r3 != r4) goto L_0x00b1
            r18.T()     // Catch:{ all -> 0x0135 }
            r8 = r16
            goto L_0x00ce
        L_0x023a:
            java.lang.Class r26 = com.T.T.Tn.TZ.T((java.lang.String) r25)     // Catch:{ all -> 0x0135 }
            com.T.T.Tr.Tv r3 = r28.Ty()     // Catch:{ all -> 0x0135 }
            r0 = r26
            com.T.T.Tr.T.T7 r12 = r3.T((java.lang.reflect.Type) r0)     // Catch:{ all -> 0x0135 }
            r0 = r28
            r1 = r26
            r2 = r30
            java.lang.Object r3 = r12.T(r0, r1, r2)     // Catch:{ all -> 0x0135 }
            if (r10 == 0) goto L_0x0259
            r0 = r31
            r10.T((java.lang.Object) r0)
        L_0x0259:
            r0 = r28
            r0.T((com.T.T.Tr.T5) r11)
            goto L_0x0014
        L_0x0260:
            com.T.T.Tn r3 = new com.T.T.Tn     // Catch:{ all -> 0x0135 }
            java.lang.String r4 = "syntax error"
            r3.<init>(r4)     // Catch:{ all -> 0x0135 }
            throw r3     // Catch:{ all -> 0x0135 }
        L_0x0269:
            if (r31 != 0) goto L_0x03ba
            if (r16 != 0) goto L_0x03ba
            java.lang.Object r31 = r27.T(r28, r29)     // Catch:{ all -> 0x0135 }
            if (r31 != 0) goto L_0x03b6
            java.util.HashMap r8 = new java.util.HashMap     // Catch:{ all -> 0x0135 }
            r0 = r27
            java.util.List<com.T.T.Tr.T.Ts> r3 = r0.Tr     // Catch:{ all -> 0x0135 }
            int r3 = r3.size()     // Catch:{ all -> 0x0135 }
            r8.<init>(r3)     // Catch:{ all -> 0x0135 }
        L_0x0280:
            r0 = r28
            r1 = r31
            r2 = r30
            com.T.T.Tr.T5 r10 = r0.T((com.T.T.Tr.T5) r11, (java.lang.Object) r1, (java.lang.Object) r2)     // Catch:{ all -> 0x0094 }
        L_0x028a:
            r3 = r27
            r4 = r28
            r6 = r31
            r7 = r29
            boolean r19 = r3.T(r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0094 }
            if (r19 != 0) goto L_0x02a5
            int r3 = r18.Tn()     // Catch:{ all -> 0x0094 }
            r4 = 13
            if (r3 != r4) goto L_0x00af
            r18.T()     // Catch:{ all -> 0x0094 }
            goto L_0x00ce
        L_0x02a5:
            int r3 = r18.Tn()     // Catch:{ all -> 0x0094 }
            r4 = 16
            if (r3 != r4) goto L_0x02b1
            r16 = r8
            goto L_0x00b1
        L_0x02b1:
            int r3 = r18.Tn()     // Catch:{ all -> 0x0094 }
            r4 = 13
            if (r3 != r4) goto L_0x02c2
            r3 = 16
            r0 = r18
            r0.T((int) r3)     // Catch:{ all -> 0x0094 }
            goto L_0x00ce
        L_0x02c2:
            int r3 = r18.Tn()     // Catch:{ all -> 0x0094 }
            r4 = 18
            if (r3 == r4) goto L_0x02d1
            int r3 = r18.Tn()     // Catch:{ all -> 0x0094 }
            r4 = 1
            if (r3 != r4) goto L_0x02f3
        L_0x02d1:
            com.T.T.Tn r3 = new com.T.T.Tn     // Catch:{ all -> 0x0094 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0094 }
            r4.<init>()     // Catch:{ all -> 0x0094 }
            java.lang.String r6 = "syntax error, unexpect token "
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x0094 }
            int r6 = r18.Tn()     // Catch:{ all -> 0x0094 }
            java.lang.String r6 = com.T.T.Tr.TZ.T(r6)     // Catch:{ all -> 0x0094 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x0094 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0094 }
            r3.<init>(r4)     // Catch:{ all -> 0x0094 }
            throw r3     // Catch:{ all -> 0x0094 }
        L_0x02f3:
            r16 = r8
            goto L_0x00b1
        L_0x02f7:
            r0 = r27
            com.T.T.Tn.Tr r3 = r0.Tn     // Catch:{ all -> 0x0094 }
            java.util.List r15 = r3.Tn()     // Catch:{ all -> 0x0094 }
            int r24 = r15.size()     // Catch:{ all -> 0x0094 }
            r0 = r24
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x0094 }
            r20 = r0
            r17 = 0
        L_0x030b:
            r0 = r17
            r1 = r24
            if (r0 >= r1) goto L_0x0326
            r0 = r17
            java.lang.Object r14 = r15.get(r0)     // Catch:{ all -> 0x0094 }
            com.T.T.Tn.Ty r14 = (com.T.T.Tn.Ty) r14     // Catch:{ all -> 0x0094 }
            java.lang.String r3 = r14.Ty()     // Catch:{ all -> 0x0094 }
            java.lang.Object r3 = r8.get(r3)     // Catch:{ all -> 0x0094 }
            r20[r17] = r3     // Catch:{ all -> 0x0094 }
            int r17 = r17 + 1
            goto L_0x030b
        L_0x0326:
            r0 = r27
            com.T.T.Tn.Tr r3 = r0.Tn     // Catch:{ all -> 0x0094 }
            java.lang.reflect.Constructor r3 = r3.Tr()     // Catch:{ all -> 0x0094 }
            if (r3 == 0) goto L_0x0375
            r0 = r27
            com.T.T.Tn.Tr r3 = r0.Tn     // Catch:{ Exception -> 0x034e }
            java.lang.reflect.Constructor r3 = r3.Tr()     // Catch:{ Exception -> 0x034e }
            r0 = r20
            java.lang.Object r31 = r3.newInstance(r0)     // Catch:{ Exception -> 0x034e }
        L_0x033e:
            if (r10 == 0) goto L_0x0345
            r0 = r31
            r10.T((java.lang.Object) r0)
        L_0x0345:
            r0 = r28
            r0.T((com.T.T.Tr.T5) r11)
            r3 = r31
            goto L_0x0014
        L_0x034e:
            r13 = move-exception
            com.T.T.Tn r3 = new com.T.T.Tn     // Catch:{ all -> 0x0094 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0094 }
            r4.<init>()     // Catch:{ all -> 0x0094 }
            java.lang.String r6 = "create instance error, "
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x0094 }
            r0 = r27
            com.T.T.Tn.Tr r6 = r0.Tn     // Catch:{ all -> 0x0094 }
            java.lang.reflect.Constructor r6 = r6.Tr()     // Catch:{ all -> 0x0094 }
            java.lang.String r6 = r6.toGenericString()     // Catch:{ all -> 0x0094 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x0094 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0094 }
            r3.<init>(r4, r13)     // Catch:{ all -> 0x0094 }
            throw r3     // Catch:{ all -> 0x0094 }
        L_0x0375:
            r0 = r27
            com.T.T.Tn.Tr r3 = r0.Tn     // Catch:{ all -> 0x0094 }
            java.lang.reflect.Method r3 = r3.Ty()     // Catch:{ all -> 0x0094 }
            if (r3 == 0) goto L_0x033e
            r0 = r27
            com.T.T.Tn.Tr r3 = r0.Tn     // Catch:{ Exception -> 0x038f }
            java.lang.reflect.Method r3 = r3.Ty()     // Catch:{ Exception -> 0x038f }
            r4 = 0
            r0 = r20
            java.lang.Object r31 = r3.invoke(r4, r0)     // Catch:{ Exception -> 0x038f }
            goto L_0x033e
        L_0x038f:
            r13 = move-exception
            com.T.T.Tn r3 = new com.T.T.Tn     // Catch:{ all -> 0x0094 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0094 }
            r4.<init>()     // Catch:{ all -> 0x0094 }
            java.lang.String r6 = "create factory method error, "
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x0094 }
            r0 = r27
            com.T.T.Tn.Tr r6 = r0.Tn     // Catch:{ all -> 0x0094 }
            java.lang.reflect.Method r6 = r6.Ty()     // Catch:{ all -> 0x0094 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0094 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x0094 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0094 }
            r3.<init>(r4, r13)     // Catch:{ all -> 0x0094 }
            throw r3     // Catch:{ all -> 0x0094 }
        L_0x03b6:
            r8 = r16
            goto L_0x0280
        L_0x03ba:
            r8 = r16
            goto L_0x028a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.T.T.Tr.T.TP.T(com.T.T.Tr.Ty, java.lang.reflect.Type, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public boolean T(com.T.T.Tr.Ty parser, String key, Object object, Type objectType, Map<String, Object> fieldValues) {
        com.T.T.Tr.T9 lexer = parser.Th();
        Ts fieldDeserializer = this.f156T.get(key);
        if (fieldDeserializer == null) {
            Iterator i$ = this.f156T.entrySet().iterator();
            while (true) {
                if (!i$.hasNext()) {
                    break;
                }
                Map.Entry<String, Ts> entry = i$.next();
                if (entry.getKey().equalsIgnoreCase(key)) {
                    fieldDeserializer = entry.getValue();
                    break;
                }
            }
        }
        if (fieldDeserializer != null) {
            lexer.Tr(fieldDeserializer.T());
            fieldDeserializer.T(parser, object, objectType, fieldValues);
            return true;
        } else if (!parser.T(Tn.IgnoreNotMatch)) {
            throw new com.T.T.Tn("setter not found, class " + this.Ty.getName() + ", property " + key);
        } else {
            lexer.Ty();
            parser.Tv();
            return false;
        }
    }

    public int T() {
        return 12;
    }
}
