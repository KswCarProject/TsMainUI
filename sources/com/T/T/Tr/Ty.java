package com.T.T.Tr;

import com.T.T.T9;
import com.T.T.Tn;
import com.T.T.Tr;
import com.T.T.Tr.T.T4;
import com.T.T.Tr.T.T7;
import com.T.T.Tr.T.TA;
import com.T.T.Tr.T.TL;
import com.T.T.Tr.T.Tr9;
import com.T.T.Tr.T.Ts;
import java.io.Closeable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/* compiled from: Proguard */
public class Ty extends T implements Closeable {
    private static final Set<Class<?>> Tk = new HashSet();

    /* renamed from: T  reason: collision with root package name */
    protected final Object f186T;
    private T5[] T5;
    private int T6;
    protected T5 T9;
    private DateFormat TE;
    private String TZ;
    private final List<T> Th;
    protected final T9 Tn;
    protected final Th Tr;
    private int Tv;
    protected Tv Ty;

    static {
        Tk.add(Boolean.TYPE);
        Tk.add(Byte.TYPE);
        Tk.add(Short.TYPE);
        Tk.add(Integer.TYPE);
        Tk.add(Long.TYPE);
        Tk.add(Float.TYPE);
        Tk.add(Double.TYPE);
        Tk.add(Boolean.class);
        Tk.add(Byte.class);
        Tk.add(Short.class);
        Tk.add(Integer.class);
        Tk.add(Long.class);
        Tk.add(Float.class);
        Tk.add(Double.class);
        Tk.add(BigInteger.class);
        Tk.add(BigDecimal.class);
        Tk.add(String.class);
    }

    public DateFormat T() {
        if (this.TE == null) {
            this.TE = new SimpleDateFormat(this.TZ);
        }
        return this.TE;
    }

    public Ty(String input, Tv config) {
        this((Object) input, (T9) new Tk(input, com.T.T.T.Tr), config);
    }

    public Ty(String input, Tv config, int features) {
        this((Object) input, (T9) new Tk(input, features), config);
    }

    public Ty(Object input, T9 lexer, Tv config) {
        this.TZ = com.T.T.T.Ty;
        this.T5 = new T5[8];
        this.Tv = 0;
        this.Th = new ArrayList();
        this.T6 = 0;
        this.Tn = lexer;
        this.f186T = input;
        this.Ty = config;
        this.Tr = config.Tr();
        lexer.T(12);
    }

    public Th Tr() {
        return this.Tr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x02bb, code lost:
        if (r26.T9 == null) goto L_0x02c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x02c3, code lost:
        if ((r28 instanceof java.lang.Integer) != false) goto L_0x02c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x02c5, code lost:
        T5();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x02c8, code lost:
        r27 = r26.Ty.T((java.lang.reflect.Type) r4).T(r26, r4, r28);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x02db, code lost:
        T(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:?, code lost:
        r12.T(4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x02fb, code lost:
        if (r12.Tn() != 4) goto L_0x03e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x02fd, code lost:
        r16 = r12.Tf();
        r12.T(13);
        r17 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0315, code lost:
        if (com.android.SdkConstants.PREFIX_RESOURCE_REF.equals(r16) == false) goto L_0x033c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x031b, code lost:
        if (Tk() == null) goto L_0x03d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x031d, code lost:
        r27 = Tk().Tr();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0331, code lost:
        if (r12.Tn() == 13) goto L_0x03d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x033b, code lost:
        throw new com.T.T.Tn("syntax error");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0347, code lost:
        if ("..".equals(r16) == false) goto L_0x0374;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0349, code lost:
        r15 = r5.Ty();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0351, code lost:
        if (r15.Tr() == null) goto L_0x035a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0353, code lost:
        r17 = r15.Tr();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x0357, code lost:
        r27 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x035a, code lost:
        T(new com.T.T.Tr.Ty.T(r15, r16));
        T(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x037f, code lost:
        if ("$".equals(r16) == false) goto L_0x03b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0381, code lost:
        r18 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0387, code lost:
        if (r18.Ty() == null) goto L_0x038e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0389, code lost:
        r18 = r18.Ty();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0392, code lost:
        if (r18.Tr() == null) goto L_0x039b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0394, code lost:
        r17 = r18.Tr();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0398, code lost:
        r27 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x039b, code lost:
        T(new com.T.T.Tr.Ty.T(r18, r16));
        T(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x03b7, code lost:
        T(new com.T.T.Tr.Ty.T(r5, r16));
        T(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x03d0, code lost:
        r27 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x03d4, code lost:
        r12.T(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x03db, code lost:
        T(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x0403, code lost:
        throw new com.T.T.Tn("illegal ref, " + com.T.T.Tr.TZ.T(r12.Tn()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x05e4, code lost:
        if (r3 != '}') goto L_0x05f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x05e6, code lost:
        r12.Tq();
        r12.TF();
        r12.T();
        T((java.lang.Object) r27, r28);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x05f2, code lost:
        T(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x0623, code lost:
        throw new com.T.T.Tn("syntax error, position at " + r12.Tk() + ", name " + r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:247:?, code lost:
        return r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:?, code lost:
        return r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:?, code lost:
        return r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:?, code lost:
        return r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x024e, code lost:
        r12.T(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x025f, code lost:
        if (r12.Tn() != 13) goto L_0x02ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0261, code lost:
        r12.T(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0268, code lost:
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:?, code lost:
        r6 = r26.Ty.T((java.lang.reflect.Type) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0279, code lost:
        if ((r6 instanceof com.T.T.Tr.T.TP) == false) goto L_0x0283;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x027b, code lost:
        r8 = ((com.T.T.Tr.T.TP) r6).T(r26, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0283, code lost:
        if (r8 != null) goto L_0x0290;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0289, code lost:
        if (r4 != java.lang.Cloneable.class) goto L_0x0299;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x028b, code lost:
        r8 = new java.util.HashMap();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0290, code lost:
        T(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:?, code lost:
        r8 = r4.newInstance();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x02ac, code lost:
        T(2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object T(java.util.Map r27, java.lang.Object r28) {
        /*
            r26 = this;
            r0 = r26
            com.T.T.Tr.T9 r12 = r0.Tn
            int r23 = r12.Tn()
            r24 = 12
            r0 = r23
            r1 = r24
            if (r0 == r1) goto L_0x003a
            int r23 = r12.Tn()
            r24 = 16
            r0 = r23
            r1 = r24
            if (r0 == r1) goto L_0x003a
            com.T.T.Tn r23 = new com.T.T.Tn
            java.lang.StringBuilder r24 = new java.lang.StringBuilder
            r24.<init>()
            java.lang.String r25 = "syntax error, expect {, actual "
            java.lang.StringBuilder r24 = r24.append(r25)
            java.lang.String r25 = r12.T9()
            java.lang.StringBuilder r24 = r24.append(r25)
            java.lang.String r24 = r24.toString()
            r23.<init>(r24)
            throw r23
        L_0x003a:
            com.T.T.Tr.T5 r5 = r26.Tk()
            r19 = 0
        L_0x0040:
            r12.Tx()     // Catch:{ all -> 0x00b3 }
            char r3 = r12.Te()     // Catch:{ all -> 0x00b3 }
            com.T.T.Tr.Tn r23 = com.T.T.Tr.Tn.AllowArbitraryCommas     // Catch:{ all -> 0x00b3 }
            r0 = r26
            r1 = r23
            boolean r23 = r0.T((com.T.T.Tr.Tn) r1)     // Catch:{ all -> 0x00b3 }
            if (r23 == 0) goto L_0x0064
        L_0x0053:
            r23 = 44
            r0 = r23
            if (r3 != r0) goto L_0x0064
            r12.Tq()     // Catch:{ all -> 0x00b3 }
            r12.Tx()     // Catch:{ all -> 0x00b3 }
            char r3 = r12.Te()     // Catch:{ all -> 0x00b3 }
            goto L_0x0053
        L_0x0064:
            r9 = 0
            r23 = 34
            r0 = r23
            if (r3 != r0) goto L_0x00ba
            r0 = r26
            com.T.T.Tr.Th r0 = r0.Tr     // Catch:{ all -> 0x00b3 }
            r23 = r0
            r24 = 34
            r0 = r23
            r1 = r24
            java.lang.String r11 = r12.T(r0, r1)     // Catch:{ all -> 0x00b3 }
            r12.Tx()     // Catch:{ all -> 0x00b3 }
            char r3 = r12.Te()     // Catch:{ all -> 0x00b3 }
            r23 = 58
            r0 = r23
            if (r3 == r0) goto L_0x01bd
            com.T.T.Tn r23 = new com.T.T.Tn     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r24 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b3 }
            r24.<init>()     // Catch:{ all -> 0x00b3 }
            java.lang.String r25 = "expect ':' at "
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            int r25 = r12.Tk()     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            java.lang.String r25 = ", name "
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            r0 = r24
            java.lang.StringBuilder r24 = r0.append(r11)     // Catch:{ all -> 0x00b3 }
            java.lang.String r24 = r24.toString()     // Catch:{ all -> 0x00b3 }
            r23.<init>(r24)     // Catch:{ all -> 0x00b3 }
            throw r23     // Catch:{ all -> 0x00b3 }
        L_0x00b3:
            r23 = move-exception
            r0 = r26
            r0.T((com.T.T.Tr.T5) r5)
            throw r23
        L_0x00ba:
            r23 = 125(0x7d, float:1.75E-43)
            r0 = r23
            if (r3 != r0) goto L_0x00cf
            r12.Tq()     // Catch:{ all -> 0x00b3 }
            r12.TF()     // Catch:{ all -> 0x00b3 }
            r12.T()     // Catch:{ all -> 0x00b3 }
            r0 = r26
            r0.T((com.T.T.Tr.T5) r5)
        L_0x00ce:
            return r27
        L_0x00cf:
            r23 = 39
            r0 = r23
            if (r3 != r0) goto L_0x0125
            com.T.T.Tr.Tn r23 = com.T.T.Tr.Tn.AllowSingleQuotes     // Catch:{ all -> 0x00b3 }
            r0 = r26
            r1 = r23
            boolean r23 = r0.T((com.T.T.Tr.Tn) r1)     // Catch:{ all -> 0x00b3 }
            if (r23 != 0) goto L_0x00ea
            com.T.T.Tn r23 = new com.T.T.Tn     // Catch:{ all -> 0x00b3 }
            java.lang.String r24 = "syntax error"
            r23.<init>(r24)     // Catch:{ all -> 0x00b3 }
            throw r23     // Catch:{ all -> 0x00b3 }
        L_0x00ea:
            r0 = r26
            com.T.T.Tr.Th r0 = r0.Tr     // Catch:{ all -> 0x00b3 }
            r23 = r0
            r24 = 39
            r0 = r23
            r1 = r24
            java.lang.String r11 = r12.T(r0, r1)     // Catch:{ all -> 0x00b3 }
            r12.Tx()     // Catch:{ all -> 0x00b3 }
            char r3 = r12.Te()     // Catch:{ all -> 0x00b3 }
            r23 = 58
            r0 = r23
            if (r3 == r0) goto L_0x01bd
            com.T.T.Tn r23 = new com.T.T.Tn     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r24 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b3 }
            r24.<init>()     // Catch:{ all -> 0x00b3 }
            java.lang.String r25 = "expect ':' at "
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            int r25 = r12.Tk()     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            java.lang.String r24 = r24.toString()     // Catch:{ all -> 0x00b3 }
            r23.<init>(r24)     // Catch:{ all -> 0x00b3 }
            throw r23     // Catch:{ all -> 0x00b3 }
        L_0x0125:
            r23 = 26
            r0 = r23
            if (r3 != r0) goto L_0x0134
            com.T.T.Tn r23 = new com.T.T.Tn     // Catch:{ all -> 0x00b3 }
            java.lang.String r24 = "syntax error"
            r23.<init>(r24)     // Catch:{ all -> 0x00b3 }
            throw r23     // Catch:{ all -> 0x00b3 }
        L_0x0134:
            r23 = 44
            r0 = r23
            if (r3 != r0) goto L_0x0143
            com.T.T.Tn r23 = new com.T.T.Tn     // Catch:{ all -> 0x00b3 }
            java.lang.String r24 = "syntax error"
            r23.<init>(r24)     // Catch:{ all -> 0x00b3 }
            throw r23     // Catch:{ all -> 0x00b3 }
        L_0x0143:
            r23 = 48
            r0 = r23
            if (r3 < r0) goto L_0x014f
            r23 = 57
            r0 = r23
            if (r3 <= r0) goto L_0x0155
        L_0x014f:
            r23 = 45
            r0 = r23
            if (r3 != r0) goto L_0x01a9
        L_0x0155:
            r12.TF()     // Catch:{ all -> 0x00b3 }
            r12.Tb()     // Catch:{ all -> 0x00b3 }
            int r23 = r12.Tn()     // Catch:{ all -> 0x00b3 }
            r24 = 2
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x01a0
            java.lang.Number r11 = r12.TE()     // Catch:{ all -> 0x00b3 }
        L_0x016b:
            char r3 = r12.Te()     // Catch:{ all -> 0x00b3 }
            r23 = 58
            r0 = r23
            if (r3 == r0) goto L_0x01bd
            com.T.T.Tn r23 = new com.T.T.Tn     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r24 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b3 }
            r24.<init>()     // Catch:{ all -> 0x00b3 }
            java.lang.String r25 = "expect ':' at "
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            int r25 = r12.Tk()     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            java.lang.String r25 = ", name "
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            r0 = r24
            java.lang.StringBuilder r24 = r0.append(r11)     // Catch:{ all -> 0x00b3 }
            java.lang.String r24 = r24.toString()     // Catch:{ all -> 0x00b3 }
            r23.<init>(r24)     // Catch:{ all -> 0x00b3 }
            throw r23     // Catch:{ all -> 0x00b3 }
        L_0x01a0:
            r23 = 1
            r0 = r23
            java.lang.Number r11 = r12.T((boolean) r0)     // Catch:{ all -> 0x00b3 }
            goto L_0x016b
        L_0x01a9:
            r23 = 123(0x7b, float:1.72E-43)
            r0 = r23
            if (r3 == r0) goto L_0x01b5
            r23 = 91
            r0 = r23
            if (r3 != r0) goto L_0x01f5
        L_0x01b5:
            r12.T()     // Catch:{ all -> 0x00b3 }
            java.lang.Object r11 = r26.Tv()     // Catch:{ all -> 0x00b3 }
            r9 = 1
        L_0x01bd:
            if (r9 != 0) goto L_0x01c5
            r12.Tq()     // Catch:{ all -> 0x00b3 }
            r12.Tx()     // Catch:{ all -> 0x00b3 }
        L_0x01c5:
            char r3 = r12.Te()     // Catch:{ all -> 0x00b3 }
            r12.TF()     // Catch:{ all -> 0x00b3 }
            java.lang.String r23 = com.T.T.T.f131T     // Catch:{ all -> 0x00b3 }
            r0 = r23
            if (r11 != r0) goto L_0x02e3
            r0 = r26
            com.T.T.Tr.Th r0 = r0.Tr     // Catch:{ all -> 0x00b3 }
            r23 = r0
            r24 = 34
            r0 = r23
            r1 = r24
            java.lang.String r21 = r12.T(r0, r1)     // Catch:{ all -> 0x00b3 }
            java.lang.Class r4 = com.T.T.Tn.TZ.T((java.lang.String) r21)     // Catch:{ all -> 0x00b3 }
            if (r4 != 0) goto L_0x024e
            java.lang.String r23 = com.T.T.T.f131T     // Catch:{ all -> 0x00b3 }
            r0 = r27
            r1 = r23
            r2 = r21
            r0.put(r1, r2)     // Catch:{ all -> 0x00b3 }
            goto L_0x0040
        L_0x01f5:
            com.T.T.Tr.Tn r23 = com.T.T.Tr.Tn.AllowUnQuotedFieldNames     // Catch:{ all -> 0x00b3 }
            r0 = r26
            r1 = r23
            boolean r23 = r0.T((com.T.T.Tr.Tn) r1)     // Catch:{ all -> 0x00b3 }
            if (r23 != 0) goto L_0x020a
            com.T.T.Tn r23 = new com.T.T.Tn     // Catch:{ all -> 0x00b3 }
            java.lang.String r24 = "syntax error"
            r23.<init>(r24)     // Catch:{ all -> 0x00b3 }
            throw r23     // Catch:{ all -> 0x00b3 }
        L_0x020a:
            r0 = r26
            com.T.T.Tr.Th r0 = r0.Tr     // Catch:{ all -> 0x00b3 }
            r23 = r0
            r0 = r23
            java.lang.String r11 = r12.Tr((com.T.T.Tr.Th) r0)     // Catch:{ all -> 0x00b3 }
            r12.Tx()     // Catch:{ all -> 0x00b3 }
            char r3 = r12.Te()     // Catch:{ all -> 0x00b3 }
            r23 = 58
            r0 = r23
            if (r3 == r0) goto L_0x01bd
            com.T.T.Tn r23 = new com.T.T.Tn     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r24 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b3 }
            r24.<init>()     // Catch:{ all -> 0x00b3 }
            java.lang.String r25 = "expect ':' at "
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            int r25 = r12.Tk()     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            java.lang.String r25 = ", actual "
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            r0 = r24
            java.lang.StringBuilder r24 = r0.append(r3)     // Catch:{ all -> 0x00b3 }
            java.lang.String r24 = r24.toString()     // Catch:{ all -> 0x00b3 }
            r23.<init>(r24)     // Catch:{ all -> 0x00b3 }
            throw r23     // Catch:{ all -> 0x00b3 }
        L_0x024e:
            r23 = 16
            r0 = r23
            r12.T((int) r0)     // Catch:{ all -> 0x00b3 }
            int r23 = r12.Tn()     // Catch:{ all -> 0x00b3 }
            r24 = 13
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x02ac
            r23 = 16
            r0 = r23
            r12.T((int) r0)     // Catch:{ all -> 0x00b3 }
            r8 = 0
            r0 = r26
            com.T.T.Tr.Tv r0 = r0.Ty     // Catch:{ Exception -> 0x029e }
            r23 = r0
            r0 = r23
            com.T.T.Tr.T.T7 r6 = r0.T((java.lang.reflect.Type) r4)     // Catch:{ Exception -> 0x029e }
            boolean r0 = r6 instanceof com.T.T.Tr.T.TP     // Catch:{ Exception -> 0x029e }
            r23 = r0
            if (r23 == 0) goto L_0x0283
            com.T.T.Tr.T.TP r6 = (com.T.T.Tr.T.TP) r6     // Catch:{ Exception -> 0x029e }
            r0 = r26
            java.lang.Object r8 = r6.T(r0, r4)     // Catch:{ Exception -> 0x029e }
        L_0x0283:
            if (r8 != 0) goto L_0x0290
            java.lang.Class<java.lang.Cloneable> r23 = java.lang.Cloneable.class
            r0 = r23
            if (r4 != r0) goto L_0x0299
            java.util.HashMap r8 = new java.util.HashMap     // Catch:{ Exception -> 0x029e }
            r8.<init>()     // Catch:{ Exception -> 0x029e }
        L_0x0290:
            r0 = r26
            r0.T((com.T.T.Tr.T5) r5)
            r27 = r8
            goto L_0x00ce
        L_0x0299:
            java.lang.Object r8 = r4.newInstance()     // Catch:{ Exception -> 0x029e }
            goto L_0x0290
        L_0x029e:
            r7 = move-exception
            com.T.T.Tn r23 = new com.T.T.Tn     // Catch:{ all -> 0x00b3 }
            java.lang.String r24 = "create instance error"
            r0 = r23
            r1 = r24
            r0.<init>(r1, r7)     // Catch:{ all -> 0x00b3 }
            throw r23     // Catch:{ all -> 0x00b3 }
        L_0x02ac:
            r23 = 2
            r0 = r26
            r1 = r23
            r0.T((int) r1)     // Catch:{ all -> 0x00b3 }
            r0 = r26
            com.T.T.Tr.T5 r0 = r0.T9     // Catch:{ all -> 0x00b3 }
            r23 = r0
            if (r23 == 0) goto L_0x02c8
            r0 = r28
            boolean r0 = r0 instanceof java.lang.Integer     // Catch:{ all -> 0x00b3 }
            r23 = r0
            if (r23 != 0) goto L_0x02c8
            r26.T5()     // Catch:{ all -> 0x00b3 }
        L_0x02c8:
            r0 = r26
            com.T.T.Tr.Tv r0 = r0.Ty     // Catch:{ all -> 0x00b3 }
            r23 = r0
            r0 = r23
            com.T.T.Tr.T.T7 r6 = r0.T((java.lang.reflect.Type) r4)     // Catch:{ all -> 0x00b3 }
            r0 = r26
            r1 = r28
            java.lang.Object r27 = r6.T(r0, r4, r1)     // Catch:{ all -> 0x00b3 }
            r0 = r26
            r0.T((com.T.T.Tr.T5) r5)
            goto L_0x00ce
        L_0x02e3:
            java.lang.String r23 = "$ref"
            r0 = r23
            if (r11 != r0) goto L_0x0404
            r23 = 4
            r0 = r23
            r12.T((int) r0)     // Catch:{ all -> 0x00b3 }
            int r23 = r12.Tn()     // Catch:{ all -> 0x00b3 }
            r24 = 4
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x03e2
            java.lang.String r16 = r12.Tf()     // Catch:{ all -> 0x00b3 }
            r23 = 13
            r0 = r23
            r12.T((int) r0)     // Catch:{ all -> 0x00b3 }
            r17 = 0
            java.lang.String r23 = "@"
            r0 = r23
            r1 = r16
            boolean r23 = r0.equals(r1)     // Catch:{ all -> 0x00b3 }
            if (r23 == 0) goto L_0x033c
            com.T.T.Tr.T5 r23 = r26.Tk()     // Catch:{ all -> 0x00b3 }
            if (r23 == 0) goto L_0x03d0
            com.T.T.Tr.T5 r23 = r26.Tk()     // Catch:{ all -> 0x00b3 }
            java.lang.Object r17 = r23.Tr()     // Catch:{ all -> 0x00b3 }
            r27 = r17
        L_0x0327:
            int r23 = r12.Tn()     // Catch:{ all -> 0x00b3 }
            r24 = 13
            r0 = r23
            r1 = r24
            if (r0 == r1) goto L_0x03d4
            com.T.T.Tn r23 = new com.T.T.Tn     // Catch:{ all -> 0x00b3 }
            java.lang.String r24 = "syntax error"
            r23.<init>(r24)     // Catch:{ all -> 0x00b3 }
            throw r23     // Catch:{ all -> 0x00b3 }
        L_0x033c:
            java.lang.String r23 = ".."
            r0 = r23
            r1 = r16
            boolean r23 = r0.equals(r1)     // Catch:{ all -> 0x00b3 }
            if (r23 == 0) goto L_0x0374
            com.T.T.Tr.T5 r15 = r5.Ty()     // Catch:{ all -> 0x00b3 }
            java.lang.Object r23 = r15.Tr()     // Catch:{ all -> 0x00b3 }
            if (r23 == 0) goto L_0x035a
            java.lang.Object r17 = r15.Tr()     // Catch:{ all -> 0x00b3 }
        L_0x0357:
            r27 = r17
            goto L_0x0327
        L_0x035a:
            com.T.T.Tr.Ty$T r23 = new com.T.T.Tr.Ty$T     // Catch:{ all -> 0x00b3 }
            r0 = r23
            r1 = r16
            r0.<init>(r15, r1)     // Catch:{ all -> 0x00b3 }
            r0 = r26
            r1 = r23
            r0.T((com.T.T.Tr.Ty.T) r1)     // Catch:{ all -> 0x00b3 }
            r23 = 1
            r0 = r26
            r1 = r23
            r0.T((int) r1)     // Catch:{ all -> 0x00b3 }
            goto L_0x0357
        L_0x0374:
            java.lang.String r23 = "$"
            r0 = r23
            r1 = r16
            boolean r23 = r0.equals(r1)     // Catch:{ all -> 0x00b3 }
            if (r23 == 0) goto L_0x03b7
            r18 = r5
        L_0x0383:
            com.T.T.Tr.T5 r23 = r18.Ty()     // Catch:{ all -> 0x00b3 }
            if (r23 == 0) goto L_0x038e
            com.T.T.Tr.T5 r18 = r18.Ty()     // Catch:{ all -> 0x00b3 }
            goto L_0x0383
        L_0x038e:
            java.lang.Object r23 = r18.Tr()     // Catch:{ all -> 0x00b3 }
            if (r23 == 0) goto L_0x039b
            java.lang.Object r17 = r18.Tr()     // Catch:{ all -> 0x00b3 }
        L_0x0398:
            r27 = r17
            goto L_0x0327
        L_0x039b:
            com.T.T.Tr.Ty$T r23 = new com.T.T.Tr.Ty$T     // Catch:{ all -> 0x00b3 }
            r0 = r23
            r1 = r18
            r2 = r16
            r0.<init>(r1, r2)     // Catch:{ all -> 0x00b3 }
            r0 = r26
            r1 = r23
            r0.T((com.T.T.Tr.Ty.T) r1)     // Catch:{ all -> 0x00b3 }
            r23 = 1
            r0 = r26
            r1 = r23
            r0.T((int) r1)     // Catch:{ all -> 0x00b3 }
            goto L_0x0398
        L_0x03b7:
            com.T.T.Tr.Ty$T r23 = new com.T.T.Tr.Ty$T     // Catch:{ all -> 0x00b3 }
            r0 = r23
            r1 = r16
            r0.<init>(r5, r1)     // Catch:{ all -> 0x00b3 }
            r0 = r26
            r1 = r23
            r0.T((com.T.T.Tr.Ty.T) r1)     // Catch:{ all -> 0x00b3 }
            r23 = 1
            r0 = r26
            r1 = r23
            r0.T((int) r1)     // Catch:{ all -> 0x00b3 }
        L_0x03d0:
            r27 = r17
            goto L_0x0327
        L_0x03d4:
            r23 = 16
            r0 = r23
            r12.T((int) r0)     // Catch:{ all -> 0x00b3 }
            r0 = r26
            r0.T((com.T.T.Tr.T5) r5)
            goto L_0x00ce
        L_0x03e2:
            com.T.T.Tn r23 = new com.T.T.Tn     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r24 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b3 }
            r24.<init>()     // Catch:{ all -> 0x00b3 }
            java.lang.String r25 = "illegal ref, "
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            int r25 = r12.Tn()     // Catch:{ all -> 0x00b3 }
            java.lang.String r25 = com.T.T.Tr.TZ.T(r25)     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            java.lang.String r24 = r24.toString()     // Catch:{ all -> 0x00b3 }
            r23.<init>(r24)     // Catch:{ all -> 0x00b3 }
            throw r23     // Catch:{ all -> 0x00b3 }
        L_0x0404:
            if (r19 != 0) goto L_0x041e
            r26.T((java.lang.Object) r27, (java.lang.Object) r28)     // Catch:{ all -> 0x00b3 }
            r19 = 1
            r0 = r26
            com.T.T.Tr.T5 r0 = r0.T9     // Catch:{ all -> 0x00b3 }
            r23 = r0
            if (r23 == 0) goto L_0x041e
            r0 = r28
            boolean r0 = r0 instanceof java.lang.Integer     // Catch:{ all -> 0x00b3 }
            r23 = r0
            if (r23 != 0) goto L_0x041e
            r26.T5()     // Catch:{ all -> 0x00b3 }
        L_0x041e:
            r23 = 34
            r0 = r23
            if (r3 != r0) goto L_0x0482
            r12.Tj()     // Catch:{ all -> 0x00b3 }
            java.lang.String r20 = r12.Tf()     // Catch:{ all -> 0x00b3 }
            r22 = r20
            com.T.T.Tr.Tn r23 = com.T.T.Tr.Tn.AllowISO8601DateFormat     // Catch:{ all -> 0x00b3 }
            r0 = r23
            boolean r23 = r12.T((com.T.T.Tr.Tn) r0)     // Catch:{ all -> 0x00b3 }
            if (r23 == 0) goto L_0x044f
            com.T.T.Tr.Tk r10 = new com.T.T.Tr.Tk     // Catch:{ all -> 0x00b3 }
            r0 = r20
            r10.<init>(r0)     // Catch:{ all -> 0x00b3 }
            boolean r23 = r10.TI()     // Catch:{ all -> 0x00b3 }
            if (r23 == 0) goto L_0x044c
            java.util.Calendar r23 = r10.TB()     // Catch:{ all -> 0x00b3 }
            java.util.Date r22 = r23.getTime()     // Catch:{ all -> 0x00b3 }
        L_0x044c:
            r10.close()     // Catch:{ all -> 0x00b3 }
        L_0x044f:
            java.lang.Class r23 = r27.getClass()     // Catch:{ all -> 0x00b3 }
            java.lang.Class<com.T.T.T9> r24 = com.T.T.T9.class
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x047a
            java.lang.String r23 = r11.toString()     // Catch:{ all -> 0x00b3 }
            r0 = r27
            r1 = r23
            r2 = r22
            r0.put(r1, r2)     // Catch:{ all -> 0x00b3 }
        L_0x0468:
            r12.Tx()     // Catch:{ all -> 0x00b3 }
            char r3 = r12.Te()     // Catch:{ all -> 0x00b3 }
            r23 = 44
            r0 = r23
            if (r3 != r0) goto L_0x05e0
            r12.Tq()     // Catch:{ all -> 0x00b3 }
            goto L_0x0040
        L_0x047a:
            r0 = r27
            r1 = r22
            r0.put(r11, r1)     // Catch:{ all -> 0x00b3 }
            goto L_0x0468
        L_0x0482:
            r23 = 48
            r0 = r23
            if (r3 < r0) goto L_0x048e
            r23 = 57
            r0 = r23
            if (r3 <= r0) goto L_0x0494
        L_0x048e:
            r23 = 45
            r0 = r23
            if (r3 != r0) goto L_0x04b4
        L_0x0494:
            r12.Tb()     // Catch:{ all -> 0x00b3 }
            int r23 = r12.Tn()     // Catch:{ all -> 0x00b3 }
            r24 = 2
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x04af
            java.lang.Number r22 = r12.TE()     // Catch:{ all -> 0x00b3 }
        L_0x04a7:
            r0 = r27
            r1 = r22
            r0.put(r11, r1)     // Catch:{ all -> 0x00b3 }
            goto L_0x0468
        L_0x04af:
            java.lang.Number r22 = r12.TP()     // Catch:{ all -> 0x00b3 }
            goto L_0x04a7
        L_0x04b4:
            r23 = 91
            r0 = r23
            if (r3 != r0) goto L_0x04fb
            r12.T()     // Catch:{ all -> 0x00b3 }
            com.T.T.Tr r13 = new com.T.T.Tr     // Catch:{ all -> 0x00b3 }
            r13.<init>()     // Catch:{ all -> 0x00b3 }
            r0 = r26
            r0.T((java.util.Collection) r13, (java.lang.Object) r11)     // Catch:{ all -> 0x00b3 }
            r22 = r13
            r0 = r27
            r1 = r22
            r0.put(r11, r1)     // Catch:{ all -> 0x00b3 }
            int r23 = r12.Tn()     // Catch:{ all -> 0x00b3 }
            r24 = 13
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x04e6
            r12.T()     // Catch:{ all -> 0x00b3 }
            r0 = r26
            r0.T((com.T.T.Tr.T5) r5)
            goto L_0x00ce
        L_0x04e6:
            int r23 = r12.Tn()     // Catch:{ all -> 0x00b3 }
            r24 = 16
            r0 = r23
            r1 = r24
            if (r0 == r1) goto L_0x0040
            com.T.T.Tn r23 = new com.T.T.Tn     // Catch:{ all -> 0x00b3 }
            java.lang.String r24 = "syntax error"
            r23.<init>(r24)     // Catch:{ all -> 0x00b3 }
            throw r23     // Catch:{ all -> 0x00b3 }
        L_0x04fb:
            r23 = 123(0x7b, float:1.72E-43)
            r0 = r23
            if (r3 != r0) goto L_0x0585
            r12.T()     // Catch:{ all -> 0x00b3 }
            com.T.T.T9 r23 = new com.T.T.T9     // Catch:{ all -> 0x00b3 }
            r23.<init>()     // Catch:{ all -> 0x00b3 }
            r0 = r26
            r1 = r23
            java.lang.Object r14 = r0.T((java.util.Map) r1, (java.lang.Object) r11)     // Catch:{ all -> 0x00b3 }
            java.lang.String r23 = r11.toString()     // Catch:{ all -> 0x00b3 }
            r0 = r26
            r1 = r27
            r2 = r23
            r0.T((java.util.Map) r1, (java.lang.String) r2)     // Catch:{ all -> 0x00b3 }
            java.lang.Class r23 = r27.getClass()     // Catch:{ all -> 0x00b3 }
            java.lang.Class<com.T.T.T9> r24 = com.T.T.T9.class
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x0555
            java.lang.String r23 = r11.toString()     // Catch:{ all -> 0x00b3 }
            r0 = r27
            r1 = r23
            r0.put(r1, r14)     // Catch:{ all -> 0x00b3 }
        L_0x0535:
            r0 = r26
            r0.T((com.T.T.Tr.T5) r5, (java.lang.Object) r14, (java.lang.Object) r11)     // Catch:{ all -> 0x00b3 }
            int r23 = r12.Tn()     // Catch:{ all -> 0x00b3 }
            r24 = 13
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x055b
            r12.T()     // Catch:{ all -> 0x00b3 }
            r0 = r26
            r0.T((com.T.T.Tr.T5) r5)     // Catch:{ all -> 0x00b3 }
            r0 = r26
            r0.T((com.T.T.Tr.T5) r5)
            goto L_0x00ce
        L_0x0555:
            r0 = r27
            r0.put(r11, r14)     // Catch:{ all -> 0x00b3 }
            goto L_0x0535
        L_0x055b:
            int r23 = r12.Tn()     // Catch:{ all -> 0x00b3 }
            r24 = 16
            r0 = r23
            r1 = r24
            if (r0 == r1) goto L_0x0040
            com.T.T.Tn r23 = new com.T.T.Tn     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r24 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b3 }
            r24.<init>()     // Catch:{ all -> 0x00b3 }
            java.lang.String r25 = "syntax error, "
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            java.lang.String r25 = r12.T9()     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            java.lang.String r24 = r24.toString()     // Catch:{ all -> 0x00b3 }
            r23.<init>(r24)     // Catch:{ all -> 0x00b3 }
            throw r23     // Catch:{ all -> 0x00b3 }
        L_0x0585:
            r12.T()     // Catch:{ all -> 0x00b3 }
            java.lang.Object r22 = r26.Tv()     // Catch:{ all -> 0x00b3 }
            r0 = r27
            r1 = r22
            r0.put(r11, r1)     // Catch:{ all -> 0x00b3 }
            int r23 = r12.Tn()     // Catch:{ all -> 0x00b3 }
            r24 = 13
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x05a9
            r12.T()     // Catch:{ all -> 0x00b3 }
            r0 = r26
            r0.T((com.T.T.Tr.T5) r5)
            goto L_0x00ce
        L_0x05a9:
            int r23 = r12.Tn()     // Catch:{ all -> 0x00b3 }
            r24 = 16
            r0 = r23
            r1 = r24
            if (r0 == r1) goto L_0x0040
            com.T.T.Tn r23 = new com.T.T.Tn     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r24 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b3 }
            r24.<init>()     // Catch:{ all -> 0x00b3 }
            java.lang.String r25 = "syntax error, position at "
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            int r25 = r12.Tk()     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            java.lang.String r25 = ", name "
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            r0 = r24
            java.lang.StringBuilder r24 = r0.append(r11)     // Catch:{ all -> 0x00b3 }
            java.lang.String r24 = r24.toString()     // Catch:{ all -> 0x00b3 }
            r23.<init>(r24)     // Catch:{ all -> 0x00b3 }
            throw r23     // Catch:{ all -> 0x00b3 }
        L_0x05e0:
            r23 = 125(0x7d, float:1.75E-43)
            r0 = r23
            if (r3 != r0) goto L_0x05f9
            r12.Tq()     // Catch:{ all -> 0x00b3 }
            r12.TF()     // Catch:{ all -> 0x00b3 }
            r12.T()     // Catch:{ all -> 0x00b3 }
            r26.T((java.lang.Object) r27, (java.lang.Object) r28)     // Catch:{ all -> 0x00b3 }
            r0 = r26
            r0.T((com.T.T.Tr.T5) r5)
            goto L_0x00ce
        L_0x05f9:
            com.T.T.Tn r23 = new com.T.T.Tn     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r24 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b3 }
            r24.<init>()     // Catch:{ all -> 0x00b3 }
            java.lang.String r25 = "syntax error, position at "
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            int r25 = r12.Tk()     // Catch:{ all -> 0x00b3 }
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            java.lang.String r25 = ", name "
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ all -> 0x00b3 }
            r0 = r24
            java.lang.StringBuilder r24 = r0.append(r11)     // Catch:{ all -> 0x00b3 }
            java.lang.String r24 = r24.toString()     // Catch:{ all -> 0x00b3 }
            r23.<init>(r24)     // Catch:{ all -> 0x00b3 }
            throw r23     // Catch:{ all -> 0x00b3 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.T.T.Tr.Ty.T(java.util.Map, java.lang.Object):java.lang.Object");
    }

    public Tv Ty() {
        return this.Ty;
    }

    public <T> T T(Class<T> clazz) {
        return T((Type) clazz);
    }

    public <T> T T(Type type) {
        if (this.Tn.Tn() == 8) {
            this.Tn.T();
            return null;
        }
        try {
            return this.Ty.T(type).T(this, type, (Object) null);
        } catch (Tn e) {
            throw e;
        } catch (Throwable e2) {
            throw new Tn(e2.getMessage(), e2);
        }
    }

    public void T(Class<?> clazz, Collection array) {
        T((Type) clazz, array);
    }

    public void T(Type type, Collection array) {
        T(type, array, (Object) null);
    }

    /* JADX INFO: finally extract failed */
    public void T(Type type, Collection array, Object fieldName) {
        T7 deserializer;
        Object T2;
        String value;
        if (this.Tn.Tn() == 21 || this.Tn.Tn() == 22) {
            this.Tn.T();
        }
        if (this.Tn.Tn() != 14) {
            throw new Tn("exepct '[', but " + TZ.T(this.Tn.Tn()));
        }
        if (Integer.TYPE == type) {
            deserializer = TA.f147T;
            this.Tn.T(2);
        } else if (String.class == type) {
            deserializer = Tr9.f165T;
            this.Tn.T(4);
        } else {
            deserializer = this.Ty.T(type);
            this.Tn.T(deserializer.T());
        }
        T5 context = Tk();
        T((Object) array, fieldName);
        int i = 0;
        while (true) {
            if (T(Tn.AllowArbitraryCommas)) {
                while (this.Tn.Tn() == 16) {
                    this.Tn.T();
                }
            }
            try {
                if (this.Tn.Tn() == 15) {
                    T(context);
                    this.Tn.T(16);
                    return;
                }
                if (Integer.TYPE == type) {
                    array.add(TA.f147T.T(this, (Type) null, (Object) null));
                } else if (String.class == type) {
                    if (this.Tn.Tn() == 4) {
                        value = this.Tn.Tf();
                        this.Tn.T(16);
                    } else {
                        Object obj = Tv();
                        if (obj == null) {
                            value = null;
                        } else {
                            value = obj.toString();
                        }
                    }
                    array.add(value);
                } else {
                    if (this.Tn.Tn() == 8) {
                        this.Tn.T();
                        T2 = null;
                    } else {
                        T2 = deserializer.T(this, type, Integer.valueOf(i));
                    }
                    array.add(T2);
                    T(array);
                }
                if (this.Tn.Tn() == 16) {
                    this.Tn.T(deserializer.T());
                }
                i++;
            } catch (Throwable th) {
                T(context);
                throw th;
            }
        }
    }

    public int Tn() {
        return this.T6;
    }

    public void T(int resolveStatus) {
        this.T6 = resolveStatus;
    }

    public Object T(String path) {
        for (int i = 0; i < this.Tv; i++) {
            if (path.equals(this.T5[i].Tn())) {
                return this.T5[i].Tr();
            }
        }
        return null;
    }

    public void T(Collection array) {
        if (this.T6 == 1) {
            T task = TE();
            task.T((Ts) new TL(this, (List) array, array.size() - 1));
            task.T(this.T9);
            T(0);
        }
    }

    public void T(Map object, String fieldName) {
        if (this.T6 == 1) {
            T4 fieldResolver = new T4(object, fieldName);
            T task = TE();
            task.T((Ts) fieldResolver);
            task.T(this.T9);
            T(0);
        }
    }

    public Object T(Map object) {
        return T(object, (Object) null);
    }

    public T9 T9() {
        T9 object = new T9();
        T((Map) object);
        return object;
    }

    public final void Tr(Collection array) {
        T(array, (Object) null);
    }

    public final void T(Collection array, Object fieldName) {
        Object obj;
        Object obj2;
        T9 lexer = Th();
        if (lexer.Tn() == 21 || lexer.Tn() == 22) {
            lexer.T();
        }
        if (lexer.Tn() != 14) {
            throw new Tn("syntax error, expect [, actual " + TZ.T(lexer.Tn()) + ", pos " + lexer.Tk());
        }
        lexer.T(4);
        T5 context = Tk();
        T((Object) array, fieldName);
        int i = 0;
        while (true) {
            if (T(Tn.AllowArbitraryCommas)) {
                while (lexer.Tn() == 16) {
                    lexer.T();
                }
            }
            try {
                switch (lexer.Tn()) {
                    case 2:
                        obj = lexer.TE();
                        lexer.T(16);
                        break;
                    case 3:
                        if (lexer.T(Tn.UseBigDecimal)) {
                            obj = lexer.T(true);
                        } else {
                            obj = lexer.T(false);
                        }
                        lexer.T(16);
                        break;
                    case 4:
                        String stringLiteral = lexer.Tf();
                        lexer.T(16);
                        if (!lexer.T(Tn.AllowISO8601DateFormat)) {
                            obj = stringLiteral;
                            break;
                        } else {
                            Tk iso8601Lexer = new Tk(stringLiteral);
                            if (iso8601Lexer.TI()) {
                                obj2 = iso8601Lexer.TB().getTime();
                            } else {
                                obj2 = stringLiteral;
                            }
                            iso8601Lexer.close();
                            break;
                        }
                    case 6:
                        obj = Boolean.TRUE;
                        lexer.T(16);
                        break;
                    case 7:
                        obj = Boolean.FALSE;
                        lexer.T(16);
                        break;
                    case 8:
                        obj = null;
                        lexer.T(4);
                        break;
                    case 12:
                        obj = T((Map) new T9(), (Object) Integer.valueOf(i));
                        break;
                    case 14:
                        Collection items = new Tr();
                        T(items, (Object) Integer.valueOf(i));
                        obj = items;
                        break;
                    case 15:
                        lexer.T(16);
                        return;
                    case 20:
                        throw new Tn("unclosed jsonArray");
                    default:
                        obj = Tv();
                        break;
                }
                array.add(obj);
                T(array);
                if (lexer.Tn() == 16) {
                    lexer.T(4);
                }
                i++;
            } finally {
                T(context);
            }
        }
    }

    public T5 Tk() {
        return this.T9;
    }

    public List<T> TZ() {
        return this.Th;
    }

    public void T(T task) {
        this.Th.add(task);
    }

    public T TE() {
        return this.Th.get(this.Th.size() - 1);
    }

    public void T(T5 context) {
        if (!T(Tn.DisableCircularReferenceDetect)) {
            this.T9 = context;
        }
    }

    public void T5() {
        if (!T(Tn.DisableCircularReferenceDetect)) {
            this.T9 = this.T9.Ty();
            this.T5[this.Tv - 1] = null;
            this.Tv--;
        }
    }

    public T5 T(Object object, Object fieldName) {
        if (T(Tn.DisableCircularReferenceDetect)) {
            return null;
        }
        return T(this.T9, object, fieldName);
    }

    public T5 T(T5 parent, Object object, Object fieldName) {
        if (T(Tn.DisableCircularReferenceDetect)) {
            return null;
        }
        this.T9 = new T5(parent, object, fieldName);
        Tr(this.T9);
        return this.T9;
    }

    private void Tr(T5 context) {
        int i = this.Tv;
        this.Tv = i + 1;
        if (i >= this.T5.length) {
            T5[] newArray = new T5[((this.T5.length * 3) / 2)];
            System.arraycopy(this.T5, 0, newArray, 0, this.T5.length);
            this.T5 = newArray;
        }
        this.T5[i] = context;
    }

    public Object Tv() {
        return T((Object) null);
    }

    public Object T(Object fieldName) {
        T9 lexer = Th();
        switch (lexer.Tn()) {
            case 2:
                Number intValue = lexer.TE();
                lexer.T();
                return intValue;
            case 3:
                Number value = lexer.T(T(Tn.UseBigDecimal));
                lexer.T();
                return value;
            case 4:
                String stringLiteral = lexer.Tf();
                lexer.T(16);
                if (lexer.T(Tn.AllowISO8601DateFormat)) {
                    Tk iso8601Lexer = new Tk(stringLiteral);
                    try {
                        if (iso8601Lexer.TI()) {
                            return iso8601Lexer.TB().getTime();
                        }
                        iso8601Lexer.close();
                    } finally {
                        iso8601Lexer.close();
                    }
                }
                return stringLiteral;
            case 6:
                lexer.T();
                return Boolean.TRUE;
            case 7:
                lexer.T();
                return Boolean.FALSE;
            case 8:
                lexer.T();
                return null;
            case 9:
                lexer.T(18);
                if (lexer.Tn() != 18) {
                    throw new Tn("syntax error");
                }
                lexer.T(10);
                Tr(10);
                long time = lexer.TE().longValue();
                Tr(2);
                Tr(11);
                return new Date(time);
            case 12:
                return T((Map) new T9(), fieldName);
            case 14:
                Tr array = new Tr();
                T((Collection) array, fieldName);
                return array;
            case 20:
                if (lexer.TA()) {
                    return null;
                }
                throw new Tn("unterminated json string, pos " + lexer.TZ());
            case 21:
                lexer.T();
                HashSet<Object> set = new HashSet<>();
                T((Collection) set, fieldName);
                return set;
            case 22:
                lexer.T();
                TreeSet<Object> treeSet = new TreeSet<>();
                T((Collection) treeSet, fieldName);
                return treeSet;
            default:
                throw new Tn("syntax error, pos " + lexer.TZ());
        }
    }

    public boolean T(Tn feature) {
        return Th().T(feature);
    }

    public T9 Th() {
        return this.Tn;
    }

    public final void Tr(int token) {
        T9 lexer = Th();
        if (lexer.Tn() == token) {
            lexer.T();
            return;
        }
        throw new Tn("syntax error, expect " + TZ.T(token) + ", actual " + TZ.T(lexer.Tn()));
    }

    public void close() {
        T9 lexer = Th();
        try {
            if (T(Tn.AutoCloseSource) && lexer.Tn() != 20) {
                throw new Tn("not close json text, token : " + TZ.T(lexer.Tn()));
            }
        } finally {
            lexer.close();
        }
    }

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        private final T5 f187T;
        private T5 Tn;
        private final String Tr;
        private Ts Ty;

        public T(T5 context, String referenceValue) {
            this.f187T = context;
            this.Tr = referenceValue;
        }

        public T5 T() {
            return this.f187T;
        }

        public String Tr() {
            return this.Tr;
        }

        public Ts Ty() {
            return this.Ty;
        }

        public void T(Ts fieldDeserializer) {
            this.Ty = fieldDeserializer;
        }

        public T5 Tn() {
            return this.Tn;
        }

        public void T(T5 ownerContext) {
            this.Tn = ownerContext;
        }
    }
}
