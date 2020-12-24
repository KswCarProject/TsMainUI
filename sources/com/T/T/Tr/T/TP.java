package com.T.T.Tr.T;

import com.T.T.T9;
import com.T.T.Tn.Tr;
import com.T.T.Tn.Ty;
import com.T.T.Tr.Tn;
import com.T.T.Tr.Tv;
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
    private final Map<String, Ts> f153T;
    private Tr Tn;
    private final List<Ts> Tr;
    private final Class<?> Ty;

    public TP(Tv config, Class<?> clazz) {
        this(config, clazz, clazz);
    }

    public TP(Tv config, Class<?> clazz, Type type) {
        this.f153T = new IdentityHashMap();
        this.Tr = new ArrayList();
        this.Ty = clazz;
        this.Tn = Tr.T(clazz, type);
        for (Ty fieldInfo : this.Tn.Tn()) {
            Tr(config, clazz, fieldInfo);
        }
    }

    public Map<String, Ts> Tr() {
        return this.f153T;
    }

    private void Tr(Tv mapping, Class<?> clazz, Ty fieldInfo) {
        Ts fieldDeserializer = T(mapping, clazz, fieldInfo);
        this.f153T.put(fieldInfo.Ty().intern(), fieldDeserializer);
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
                                fieldInfo.T(object, (Object) "");
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

    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0230, code lost:
        r26 = com.T.T.Tn.TZ.T(r25);
        r3 = r28.Ty().T((java.lang.reflect.Type) r26).T(r28, r26, r30);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0248, code lost:
        if (r10 == null) goto L_0x024f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x024a, code lost:
        r10.T((java.lang.Object) r31);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x024f, code lost:
        r28.T(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0295, code lost:
        r18.T();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x02eb, code lost:
        r15 = r27.Tn.Tn();
        r24 = r15.size();
        r20 = new java.lang.Object[r24];
        r17 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0303, code lost:
        if (r17 >= r24) goto L_0x031a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0305, code lost:
        r20[r17] = r8.get(r15.get(r17).Ty());
        r17 = r17 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0322, code lost:
        if (r27.Tn.Tr() == null) goto L_0x0368;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:?, code lost:
        r31 = r27.Tn.Tr().newInstance(r20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x0332, code lost:
        if (r10 == null) goto L_0x0339;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0334, code lost:
        r10.T((java.lang.Object) r31);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0339, code lost:
        r28.T(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0370, code lost:
        if (r27.Tn.Ty() == null) goto L_0x0332;
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
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00cb, code lost:
        if (r31 != null) goto L_0x0332;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00cd, code lost:
        if (r8 != null) goto L_0x02eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r31 = T(r28, r29);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00d3, code lost:
        if (r10 == null) goto L_0x00da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d5, code lost:
        r10.T(r31);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00da, code lost:
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
            int r3 = r18.Tn()     // Catch:{ all -> 0x0091 }
            r4 = 13
            if (r3 != r4) goto L_0x0045
            r3 = 16
            r0 = r18
            r0.T((int) r3)     // Catch:{ all -> 0x0091 }
            if (r31 != 0) goto L_0x0036
            java.lang.Object r31 = r27.T(r28, r29)     // Catch:{ all -> 0x0091 }
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
            int r3 = r18.Tn()     // Catch:{ all -> 0x0091 }
            r4 = 12
            if (r3 == r4) goto L_0x009f
            int r3 = r18.Tn()     // Catch:{ all -> 0x0091 }
            r4 = 16
            if (r3 == r4) goto L_0x009f
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch:{ all -> 0x0091 }
            r3.<init>()     // Catch:{ all -> 0x0091 }
            java.lang.String r4 = "syntax error, expect {, actual "
            java.lang.StringBuffer r3 = r3.append(r4)     // Catch:{ all -> 0x0091 }
            java.lang.String r4 = r18.T9()     // Catch:{ all -> 0x0091 }
            java.lang.StringBuffer r3 = r3.append(r4)     // Catch:{ all -> 0x0091 }
            java.lang.String r4 = ", pos "
            java.lang.StringBuffer r3 = r3.append(r4)     // Catch:{ all -> 0x0091 }
            int r4 = r18.Tk()     // Catch:{ all -> 0x0091 }
            java.lang.StringBuffer r9 = r3.append(r4)     // Catch:{ all -> 0x0091 }
            r0 = r30
            boolean r3 = r0 instanceof java.lang.String     // Catch:{ all -> 0x0091 }
            if (r3 == 0) goto L_0x0087
            java.lang.String r3 = ", fieldName "
            java.lang.StringBuffer r3 = r9.append(r3)     // Catch:{ all -> 0x0091 }
            r0 = r30
            r3.append(r0)     // Catch:{ all -> 0x0091 }
        L_0x0087:
            com.T.T.Tn r3 = new com.T.T.Tn     // Catch:{ all -> 0x0091 }
            java.lang.String r4 = r9.toString()     // Catch:{ all -> 0x0091 }
            r3.<init>(r4)     // Catch:{ all -> 0x0091 }
            throw r3     // Catch:{ all -> 0x0091 }
        L_0x0091:
            r3 = move-exception
        L_0x0092:
            if (r10 == 0) goto L_0x0099
            r0 = r31
            r10.T((java.lang.Object) r0)
        L_0x0099:
            r0 = r28
            r0.T((com.T.T.Tr.T5) r11)
            throw r3
        L_0x009f:
            int r3 = r28.Tn()     // Catch:{ all -> 0x0091 }
            r4 = 2
            if (r3 != r4) goto L_0x00ac
            r3 = 0
            r0 = r28
            r0.T((int) r3)     // Catch:{ all -> 0x0091 }
        L_0x00ac:
            r16 = r8
        L_0x00ae:
            com.T.T.Tr.Th r3 = r28.Tr()     // Catch:{ all -> 0x012f }
            r0 = r18
            java.lang.String r5 = r0.T((com.T.T.Tr.Th) r3)     // Catch:{ all -> 0x012f }
            if (r5 != 0) goto L_0x00f5
            int r3 = r18.Tn()     // Catch:{ all -> 0x012f }
            r4 = 13
            if (r3 != r4) goto L_0x00e3
            r3 = 16
            r0 = r18
            r0.T((int) r3)     // Catch:{ all -> 0x012f }
            r8 = r16
        L_0x00cb:
            if (r31 != 0) goto L_0x0332
            if (r8 != 0) goto L_0x02eb
            java.lang.Object r31 = r27.T(r28, r29)     // Catch:{ all -> 0x0091 }
            if (r10 == 0) goto L_0x00da
            r0 = r31
            r10.T((java.lang.Object) r0)
        L_0x00da:
            r0 = r28
            r0.T((com.T.T.Tr.T5) r11)
            r3 = r31
            goto L_0x0014
        L_0x00e3:
            int r3 = r18.Tn()     // Catch:{ all -> 0x012f }
            r4 = 16
            if (r3 != r4) goto L_0x00f5
            com.T.T.Tr.Tn r3 = com.T.T.Tr.Tn.AllowArbitraryCommas     // Catch:{ all -> 0x012f }
            r0 = r28
            boolean r3 = r0.T((com.T.T.Tr.Tn) r3)     // Catch:{ all -> 0x012f }
            if (r3 != 0) goto L_0x00ae
        L_0x00f5:
            java.lang.String r3 = "$ref"
            if (r3 != r5) goto L_0x01ee
            r3 = 4
            r0 = r18
            r0.Tr((int) r3)     // Catch:{ all -> 0x012f }
            int r3 = r18.Tn()     // Catch:{ all -> 0x012f }
            r4 = 4
            if (r3 != r4) goto L_0x01ad
            java.lang.String r22 = r18.Tf()     // Catch:{ all -> 0x012f }
            java.lang.String r3 = "@"
            r0 = r22
            boolean r3 = r3.equals(r0)     // Catch:{ all -> 0x012f }
            if (r3 == 0) goto L_0x0134
            java.lang.Object r31 = r11.Tr()     // Catch:{ all -> 0x012f }
        L_0x0118:
            r3 = 13
            r0 = r18
            r0.T((int) r3)     // Catch:{ all -> 0x012f }
            int r3 = r18.Tn()     // Catch:{ all -> 0x012f }
            r4 = 13
            if (r3 == r4) goto L_0x01ce
            com.T.T.Tn r3 = new com.T.T.Tn     // Catch:{ all -> 0x012f }
            java.lang.String r4 = "illegal ref"
            r3.<init>(r4)     // Catch:{ all -> 0x012f }
            throw r3     // Catch:{ all -> 0x012f }
        L_0x012f:
            r3 = move-exception
            r8 = r16
            goto L_0x0092
        L_0x0134:
            java.lang.String r3 = ".."
            r0 = r22
            boolean r3 = r3.equals(r0)     // Catch:{ all -> 0x012f }
            if (r3 == 0) goto L_0x0162
            com.T.T.Tr.T5 r21 = r11.Ty()     // Catch:{ all -> 0x012f }
            java.lang.Object r3 = r21.Tr()     // Catch:{ all -> 0x012f }
            if (r3 == 0) goto L_0x014d
            java.lang.Object r31 = r21.Tr()     // Catch:{ all -> 0x012f }
            goto L_0x0118
        L_0x014d:
            com.T.T.Tr.Ty$T r3 = new com.T.T.Tr.Ty$T     // Catch:{ all -> 0x012f }
            r0 = r21
            r1 = r22
            r3.<init>(r0, r1)     // Catch:{ all -> 0x012f }
            r0 = r28
            r0.T((com.T.T.Tr.Ty.T) r3)     // Catch:{ all -> 0x012f }
            r3 = 1
            r0 = r28
            r0.T((int) r3)     // Catch:{ all -> 0x012f }
            goto L_0x0118
        L_0x0162:
            java.lang.String r3 = "$"
            r0 = r22
            boolean r3 = r3.equals(r0)     // Catch:{ all -> 0x012f }
            if (r3 == 0) goto L_0x0199
            r23 = r11
        L_0x016e:
            com.T.T.Tr.T5 r3 = r23.Ty()     // Catch:{ all -> 0x012f }
            if (r3 == 0) goto L_0x0179
            com.T.T.Tr.T5 r23 = r23.Ty()     // Catch:{ all -> 0x012f }
            goto L_0x016e
        L_0x0179:
            java.lang.Object r3 = r23.Tr()     // Catch:{ all -> 0x012f }
            if (r3 == 0) goto L_0x0184
            java.lang.Object r31 = r23.Tr()     // Catch:{ all -> 0x012f }
            goto L_0x0118
        L_0x0184:
            com.T.T.Tr.Ty$T r3 = new com.T.T.Tr.Ty$T     // Catch:{ all -> 0x012f }
            r0 = r23
            r1 = r22
            r3.<init>(r0, r1)     // Catch:{ all -> 0x012f }
            r0 = r28
            r0.T((com.T.T.Tr.Ty.T) r3)     // Catch:{ all -> 0x012f }
            r3 = 1
            r0 = r28
            r0.T((int) r3)     // Catch:{ all -> 0x012f }
            goto L_0x0118
        L_0x0199:
            com.T.T.Tr.Ty$T r3 = new com.T.T.Tr.Ty$T     // Catch:{ all -> 0x012f }
            r0 = r22
            r3.<init>(r11, r0)     // Catch:{ all -> 0x012f }
            r0 = r28
            r0.T((com.T.T.Tr.Ty.T) r3)     // Catch:{ all -> 0x012f }
            r3 = 1
            r0 = r28
            r0.T((int) r3)     // Catch:{ all -> 0x012f }
            goto L_0x0118
        L_0x01ad:
            com.T.T.Tn r3 = new com.T.T.Tn     // Catch:{ all -> 0x012f }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x012f }
            r4.<init>()     // Catch:{ all -> 0x012f }
            java.lang.String r6 = "illegal ref, "
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x012f }
            int r6 = r18.Tn()     // Catch:{ all -> 0x012f }
            java.lang.String r6 = com.T.T.Tr.TZ.T(r6)     // Catch:{ all -> 0x012f }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x012f }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x012f }
            r3.<init>(r4)     // Catch:{ all -> 0x012f }
            throw r3     // Catch:{ all -> 0x012f }
        L_0x01ce:
            r3 = 16
            r0 = r18
            r0.T((int) r3)     // Catch:{ all -> 0x012f }
            r0 = r28
            r1 = r31
            r2 = r30
            r0.T((com.T.T.Tr.T5) r11, (java.lang.Object) r1, (java.lang.Object) r2)     // Catch:{ all -> 0x012f }
            if (r10 == 0) goto L_0x01e5
            r0 = r31
            r10.T((java.lang.Object) r0)
        L_0x01e5:
            r0 = r28
            r0.T((com.T.T.Tr.T5) r11)
            r3 = r31
            goto L_0x0014
        L_0x01ee:
            java.lang.String r3 = com.T.T.T.f128T     // Catch:{ all -> 0x012f }
            if (r3 != r5) goto L_0x025e
            r3 = 4
            r0 = r18
            r0.Tr((int) r3)     // Catch:{ all -> 0x012f }
            int r3 = r18.Tn()     // Catch:{ all -> 0x012f }
            r4 = 4
            if (r3 != r4) goto L_0x0256
            java.lang.String r25 = r18.Tf()     // Catch:{ all -> 0x012f }
            r3 = 16
            r0 = r18
            r0.T((int) r3)     // Catch:{ all -> 0x012f }
            r0 = r29
            boolean r3 = r0 instanceof java.lang.Class     // Catch:{ all -> 0x012f }
            if (r3 == 0) goto L_0x0230
            r0 = r29
            java.lang.Class r0 = (java.lang.Class) r0     // Catch:{ all -> 0x012f }
            r3 = r0
            java.lang.String r3 = r3.getName()     // Catch:{ all -> 0x012f }
            r0 = r25
            boolean r3 = r0.equals(r3)     // Catch:{ all -> 0x012f }
            if (r3 == 0) goto L_0x0230
            int r3 = r18.Tn()     // Catch:{ all -> 0x012f }
            r4 = 13
            if (r3 != r4) goto L_0x00ae
            r18.T()     // Catch:{ all -> 0x012f }
            r8 = r16
            goto L_0x00cb
        L_0x0230:
            java.lang.Class r26 = com.T.T.Tn.TZ.T((java.lang.String) r25)     // Catch:{ all -> 0x012f }
            com.T.T.Tr.Tv r3 = r28.Ty()     // Catch:{ all -> 0x012f }
            r0 = r26
            com.T.T.Tr.T.T7 r12 = r3.T((java.lang.reflect.Type) r0)     // Catch:{ all -> 0x012f }
            r0 = r28
            r1 = r26
            r2 = r30
            java.lang.Object r3 = r12.T(r0, r1, r2)     // Catch:{ all -> 0x012f }
            if (r10 == 0) goto L_0x024f
            r0 = r31
            r10.T((java.lang.Object) r0)
        L_0x024f:
            r0 = r28
            r0.T((com.T.T.Tr.T5) r11)
            goto L_0x0014
        L_0x0256:
            com.T.T.Tn r3 = new com.T.T.Tn     // Catch:{ all -> 0x012f }
            java.lang.String r4 = "syntax error"
            r3.<init>(r4)     // Catch:{ all -> 0x012f }
            throw r3     // Catch:{ all -> 0x012f }
        L_0x025e:
            if (r31 != 0) goto L_0x03ac
            if (r16 != 0) goto L_0x03ac
            java.lang.Object r31 = r27.T(r28, r29)     // Catch:{ all -> 0x012f }
            if (r31 != 0) goto L_0x03a8
            java.util.HashMap r8 = new java.util.HashMap     // Catch:{ all -> 0x012f }
            r0 = r27
            java.util.List<com.T.T.Tr.T.Ts> r3 = r0.Tr     // Catch:{ all -> 0x012f }
            int r3 = r3.size()     // Catch:{ all -> 0x012f }
            r8.<init>(r3)     // Catch:{ all -> 0x012f }
        L_0x0275:
            r0 = r28
            r1 = r31
            r2 = r30
            com.T.T.Tr.T5 r10 = r0.T((com.T.T.Tr.T5) r11, (java.lang.Object) r1, (java.lang.Object) r2)     // Catch:{ all -> 0x0091 }
        L_0x027f:
            r3 = r27
            r4 = r28
            r6 = r31
            r7 = r29
            boolean r19 = r3.T(r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0091 }
            if (r19 != 0) goto L_0x029a
            int r3 = r18.Tn()     // Catch:{ all -> 0x0091 }
            r4 = 13
            if (r3 != r4) goto L_0x00ac
            r18.T()     // Catch:{ all -> 0x0091 }
            goto L_0x00cb
        L_0x029a:
            int r3 = r18.Tn()     // Catch:{ all -> 0x0091 }
            r4 = 16
            if (r3 != r4) goto L_0x02a6
            r16 = r8
            goto L_0x00ae
        L_0x02a6:
            int r3 = r18.Tn()     // Catch:{ all -> 0x0091 }
            r4 = 13
            if (r3 != r4) goto L_0x02b7
            r3 = 16
            r0 = r18
            r0.T((int) r3)     // Catch:{ all -> 0x0091 }
            goto L_0x00cb
        L_0x02b7:
            int r3 = r18.Tn()     // Catch:{ all -> 0x0091 }
            r4 = 18
            if (r3 == r4) goto L_0x02c6
            int r3 = r18.Tn()     // Catch:{ all -> 0x0091 }
            r4 = 1
            if (r3 != r4) goto L_0x02e7
        L_0x02c6:
            com.T.T.Tn r3 = new com.T.T.Tn     // Catch:{ all -> 0x0091 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0091 }
            r4.<init>()     // Catch:{ all -> 0x0091 }
            java.lang.String r6 = "syntax error, unexpect token "
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x0091 }
            int r6 = r18.Tn()     // Catch:{ all -> 0x0091 }
            java.lang.String r6 = com.T.T.Tr.TZ.T(r6)     // Catch:{ all -> 0x0091 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x0091 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0091 }
            r3.<init>(r4)     // Catch:{ all -> 0x0091 }
            throw r3     // Catch:{ all -> 0x0091 }
        L_0x02e7:
            r16 = r8
            goto L_0x00ae
        L_0x02eb:
            r0 = r27
            com.T.T.Tn.Tr r3 = r0.Tn     // Catch:{ all -> 0x0091 }
            java.util.List r15 = r3.Tn()     // Catch:{ all -> 0x0091 }
            int r24 = r15.size()     // Catch:{ all -> 0x0091 }
            r0 = r24
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x0091 }
            r20 = r0
            r17 = 0
        L_0x02ff:
            r0 = r17
            r1 = r24
            if (r0 >= r1) goto L_0x031a
            r0 = r17
            java.lang.Object r14 = r15.get(r0)     // Catch:{ all -> 0x0091 }
            com.T.T.Tn.Ty r14 = (com.T.T.Tn.Ty) r14     // Catch:{ all -> 0x0091 }
            java.lang.String r3 = r14.Ty()     // Catch:{ all -> 0x0091 }
            java.lang.Object r3 = r8.get(r3)     // Catch:{ all -> 0x0091 }
            r20[r17] = r3     // Catch:{ all -> 0x0091 }
            int r17 = r17 + 1
            goto L_0x02ff
        L_0x031a:
            r0 = r27
            com.T.T.Tn.Tr r3 = r0.Tn     // Catch:{ all -> 0x0091 }
            java.lang.reflect.Constructor r3 = r3.Tr()     // Catch:{ all -> 0x0091 }
            if (r3 == 0) goto L_0x0368
            r0 = r27
            com.T.T.Tn.Tr r3 = r0.Tn     // Catch:{ Exception -> 0x0342 }
            java.lang.reflect.Constructor r3 = r3.Tr()     // Catch:{ Exception -> 0x0342 }
            r0 = r20
            java.lang.Object r31 = r3.newInstance(r0)     // Catch:{ Exception -> 0x0342 }
        L_0x0332:
            if (r10 == 0) goto L_0x0339
            r0 = r31
            r10.T((java.lang.Object) r0)
        L_0x0339:
            r0 = r28
            r0.T((com.T.T.Tr.T5) r11)
            r3 = r31
            goto L_0x0014
        L_0x0342:
            r13 = move-exception
            com.T.T.Tn r3 = new com.T.T.Tn     // Catch:{ all -> 0x0091 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0091 }
            r4.<init>()     // Catch:{ all -> 0x0091 }
            java.lang.String r6 = "create instance error, "
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x0091 }
            r0 = r27
            com.T.T.Tn.Tr r6 = r0.Tn     // Catch:{ all -> 0x0091 }
            java.lang.reflect.Constructor r6 = r6.Tr()     // Catch:{ all -> 0x0091 }
            java.lang.String r6 = r6.toGenericString()     // Catch:{ all -> 0x0091 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x0091 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0091 }
            r3.<init>(r4, r13)     // Catch:{ all -> 0x0091 }
            throw r3     // Catch:{ all -> 0x0091 }
        L_0x0368:
            r0 = r27
            com.T.T.Tn.Tr r3 = r0.Tn     // Catch:{ all -> 0x0091 }
            java.lang.reflect.Method r3 = r3.Ty()     // Catch:{ all -> 0x0091 }
            if (r3 == 0) goto L_0x0332
            r0 = r27
            com.T.T.Tn.Tr r3 = r0.Tn     // Catch:{ Exception -> 0x0382 }
            java.lang.reflect.Method r3 = r3.Ty()     // Catch:{ Exception -> 0x0382 }
            r4 = 0
            r0 = r20
            java.lang.Object r31 = r3.invoke(r4, r0)     // Catch:{ Exception -> 0x0382 }
            goto L_0x0332
        L_0x0382:
            r13 = move-exception
            com.T.T.Tn r3 = new com.T.T.Tn     // Catch:{ all -> 0x0091 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0091 }
            r4.<init>()     // Catch:{ all -> 0x0091 }
            java.lang.String r6 = "create factory method error, "
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x0091 }
            r0 = r27
            com.T.T.Tn.Tr r6 = r0.Tn     // Catch:{ all -> 0x0091 }
            java.lang.reflect.Method r6 = r6.Ty()     // Catch:{ all -> 0x0091 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0091 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x0091 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0091 }
            r3.<init>(r4, r13)     // Catch:{ all -> 0x0091 }
            throw r3     // Catch:{ all -> 0x0091 }
        L_0x03a8:
            r8 = r16
            goto L_0x0275
        L_0x03ac:
            r8 = r16
            goto L_0x027f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.T.T.Tr.T.TP.T(com.T.T.Tr.Ty, java.lang.reflect.Type, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public boolean T(com.T.T.Tr.Ty parser, String key, Object object, Type objectType, Map<String, Object> fieldValues) {
        com.T.T.Tr.T9 lexer = parser.Th();
        Ts fieldDeserializer = this.f153T.get(key);
        if (fieldDeserializer == null) {
            Iterator i$ = this.f153T.entrySet().iterator();
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
