package com.T.T.Tr.T;

import com.T.T.T;
import com.T.T.Tn;
import com.T.T.Tr.T5;
import com.T.T.Tr.T9;
import com.T.T.Tr.TZ;
import com.T.T.Tr.Ty;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: Proguard */
public class T2 implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final T2 f142T = new T2();

    public <T> T T(Ty parser, Type type, Object fieldName) {
        T9 lexer = parser.Th();
        if (lexer.Tn() == 8) {
            lexer.T(16);
            return null;
        }
        Map<Object, Object> map = T(type);
        T5 context = parser.Tk();
        try {
            parser.T(context, (Object) map, fieldName);
            return T(parser, type, fieldName, (Map) map);
        } finally {
            parser.T(context);
        }
    }

    /* access modifiers changed from: protected */
    public Object T(Ty parser, Type type, Object fieldName, Map map) {
        if (!(type instanceof ParameterizedType)) {
            return parser.T(map, fieldName);
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type keyType = parameterizedType.getActualTypeArguments()[0];
        Type valueType = parameterizedType.getActualTypeArguments()[1];
        if (String.class == keyType) {
            return T(parser, (Map<String, Object>) map, valueType, fieldName);
        }
        return T(parser, map, keyType, valueType, fieldName);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        r3 = r12.Ty().T((java.lang.reflect.Type) r1);
        r5.T(16);
        r12.T(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x017d, code lost:
        if (r2 == null) goto L_0x0186;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0181, code lost:
        if ((r15 instanceof java.lang.Integer) != false) goto L_0x0186;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0183, code lost:
        r12.T5();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0186, code lost:
        r9 = (java.util.Map) r3.T(r12, r1, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x018c, code lost:
        r12.T(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:?, code lost:
        return r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map T(com.T.T.Tr.Ty r12, java.util.Map<java.lang.String, java.lang.Object> r13, java.lang.reflect.Type r14, java.lang.Object r15) {
        /*
            com.T.T.Tr.T9 r5 = r12.Th()
            int r9 = r5.Tn()
            r10 = 12
            if (r9 == r10) goto L_0x002a
            com.T.T.Tn r9 = new com.T.T.Tn
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "syntax error, expect {, actual "
            java.lang.StringBuilder r10 = r10.append(r11)
            int r11 = r5.Tn()
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        L_0x002a:
            com.T.T.Tr.T5 r2 = r12.Tk()
        L_0x002e:
            r5.Tx()     // Catch:{ all -> 0x0083 }
            char r0 = r5.Te()     // Catch:{ all -> 0x0083 }
            com.T.T.Tr.Tn r9 = com.T.T.Tr.Tn.AllowArbitraryCommas     // Catch:{ all -> 0x0083 }
            boolean r9 = r12.T((com.T.T.Tr.Tn) r9)     // Catch:{ all -> 0x0083 }
            if (r9 == 0) goto L_0x004c
        L_0x003d:
            r9 = 44
            if (r0 != r9) goto L_0x004c
            r5.Tq()     // Catch:{ all -> 0x0083 }
            r5.Tx()     // Catch:{ all -> 0x0083 }
            char r0 = r5.Te()     // Catch:{ all -> 0x0083 }
            goto L_0x003d
        L_0x004c:
            r9 = 34
            if (r0 != r9) goto L_0x0088
            com.T.T.Tr.Th r9 = r12.Tr()     // Catch:{ all -> 0x0083 }
            r10 = 34
            java.lang.String r4 = r5.T(r9, r10)     // Catch:{ all -> 0x0083 }
            r5.Tx()     // Catch:{ all -> 0x0083 }
            char r0 = r5.Te()     // Catch:{ all -> 0x0083 }
            r9 = 58
            if (r0 == r9) goto L_0x0130
            com.T.T.Tn r9 = new com.T.T.Tn     // Catch:{ all -> 0x0083 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0083 }
            r10.<init>()     // Catch:{ all -> 0x0083 }
            java.lang.String r11 = "expect ':' at "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0083 }
            int r11 = r5.Tk()     // Catch:{ all -> 0x0083 }
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0083 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0083 }
            r9.<init>(r10)     // Catch:{ all -> 0x0083 }
            throw r9     // Catch:{ all -> 0x0083 }
        L_0x0083:
            r9 = move-exception
            r12.T((com.T.T.Tr.T5) r2)
            throw r9
        L_0x0088:
            r9 = 125(0x7d, float:1.75E-43)
            if (r0 != r9) goto L_0x009b
            r5.Tq()     // Catch:{ all -> 0x0083 }
            r5.TF()     // Catch:{ all -> 0x0083 }
            r9 = 16
            r5.T((int) r9)     // Catch:{ all -> 0x0083 }
            r12.T((com.T.T.Tr.T5) r2)
        L_0x009a:
            return r13
        L_0x009b:
            r9 = 39
            if (r0 != r9) goto L_0x00e3
            com.T.T.Tr.Tn r9 = com.T.T.Tr.Tn.AllowSingleQuotes     // Catch:{ all -> 0x0083 }
            boolean r9 = r12.T((com.T.T.Tr.Tn) r9)     // Catch:{ all -> 0x0083 }
            if (r9 != 0) goto L_0x00b0
            com.T.T.Tn r9 = new com.T.T.Tn     // Catch:{ all -> 0x0083 }
            java.lang.String r10 = "syntax error"
            r9.<init>(r10)     // Catch:{ all -> 0x0083 }
            throw r9     // Catch:{ all -> 0x0083 }
        L_0x00b0:
            com.T.T.Tr.Th r9 = r12.Tr()     // Catch:{ all -> 0x0083 }
            r10 = 39
            java.lang.String r4 = r5.T(r9, r10)     // Catch:{ all -> 0x0083 }
            r5.Tx()     // Catch:{ all -> 0x0083 }
            char r0 = r5.Te()     // Catch:{ all -> 0x0083 }
            r9 = 58
            if (r0 == r9) goto L_0x0130
            com.T.T.Tn r9 = new com.T.T.Tn     // Catch:{ all -> 0x0083 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0083 }
            r10.<init>()     // Catch:{ all -> 0x0083 }
            java.lang.String r11 = "expect ':' at "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0083 }
            int r11 = r5.Tk()     // Catch:{ all -> 0x0083 }
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0083 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0083 }
            r9.<init>(r10)     // Catch:{ all -> 0x0083 }
            throw r9     // Catch:{ all -> 0x0083 }
        L_0x00e3:
            com.T.T.Tr.Tn r9 = com.T.T.Tr.Tn.AllowUnQuotedFieldNames     // Catch:{ all -> 0x0083 }
            boolean r9 = r12.T((com.T.T.Tr.Tn) r9)     // Catch:{ all -> 0x0083 }
            if (r9 != 0) goto L_0x00f4
            com.T.T.Tn r9 = new com.T.T.Tn     // Catch:{ all -> 0x0083 }
            java.lang.String r10 = "syntax error"
            r9.<init>(r10)     // Catch:{ all -> 0x0083 }
            throw r9     // Catch:{ all -> 0x0083 }
        L_0x00f4:
            com.T.T.Tr.Th r9 = r12.Tr()     // Catch:{ all -> 0x0083 }
            java.lang.String r4 = r5.Tr((com.T.T.Tr.Th) r9)     // Catch:{ all -> 0x0083 }
            r5.Tx()     // Catch:{ all -> 0x0083 }
            char r0 = r5.Te()     // Catch:{ all -> 0x0083 }
            r9 = 58
            if (r0 == r9) goto L_0x0130
            com.T.T.Tn r9 = new com.T.T.Tn     // Catch:{ all -> 0x0083 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0083 }
            r10.<init>()     // Catch:{ all -> 0x0083 }
            java.lang.String r11 = "expect ':' at "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0083 }
            int r11 = r5.Tk()     // Catch:{ all -> 0x0083 }
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0083 }
            java.lang.String r11 = ", actual "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0083 }
            java.lang.StringBuilder r10 = r10.append(r0)     // Catch:{ all -> 0x0083 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0083 }
            r9.<init>(r10)     // Catch:{ all -> 0x0083 }
            throw r9     // Catch:{ all -> 0x0083 }
        L_0x0130:
            r5.Tq()     // Catch:{ all -> 0x0083 }
            r5.Tx()     // Catch:{ all -> 0x0083 }
            char r0 = r5.Te()     // Catch:{ all -> 0x0083 }
            r5.TF()     // Catch:{ all -> 0x0083 }
            java.lang.String r9 = com.T.T.T.f131T     // Catch:{ all -> 0x0083 }
            if (r4 != r9) goto L_0x0192
            com.T.T.Tr.Th r9 = r12.Tr()     // Catch:{ all -> 0x0083 }
            r10 = 34
            java.lang.String r7 = r5.T(r9, r10)     // Catch:{ all -> 0x0083 }
            java.lang.Class r1 = com.T.T.Tn.TZ.T((java.lang.String) r7)     // Catch:{ all -> 0x0083 }
            java.lang.Class r9 = r13.getClass()     // Catch:{ all -> 0x0083 }
            if (r1 != r9) goto L_0x016c
            r9 = 16
            r5.T((int) r9)     // Catch:{ all -> 0x0083 }
            int r9 = r5.Tn()     // Catch:{ all -> 0x0083 }
            r10 = 13
            if (r9 != r10) goto L_0x002e
            r9 = 16
            r5.T((int) r9)     // Catch:{ all -> 0x0083 }
            r12.T((com.T.T.Tr.T5) r2)
            goto L_0x009a
        L_0x016c:
            com.T.T.Tr.Tv r9 = r12.Ty()     // Catch:{ all -> 0x0083 }
            com.T.T.Tr.T.T7 r3 = r9.T((java.lang.reflect.Type) r1)     // Catch:{ all -> 0x0083 }
            r9 = 16
            r5.T((int) r9)     // Catch:{ all -> 0x0083 }
            r9 = 2
            r12.T((int) r9)     // Catch:{ all -> 0x0083 }
            if (r2 == 0) goto L_0x0186
            boolean r9 = r15 instanceof java.lang.Integer     // Catch:{ all -> 0x0083 }
            if (r9 != 0) goto L_0x0186
            r12.T5()     // Catch:{ all -> 0x0083 }
        L_0x0186:
            java.lang.Object r9 = r3.T(r12, r1, r15)     // Catch:{ all -> 0x0083 }
            java.util.Map r9 = (java.util.Map) r9     // Catch:{ all -> 0x0083 }
            r12.T((com.T.T.Tr.T5) r2)
            r13 = r9
            goto L_0x009a
        L_0x0192:
            r5.T()     // Catch:{ all -> 0x0083 }
            int r9 = r5.Tn()     // Catch:{ all -> 0x0083 }
            r10 = 8
            if (r9 != r10) goto L_0x01bb
            r8 = 0
            r5.T()     // Catch:{ all -> 0x0083 }
        L_0x01a1:
            r13.put(r4, r8)     // Catch:{ all -> 0x0083 }
            r12.T((java.util.Map) r13, (java.lang.String) r4)     // Catch:{ all -> 0x0083 }
            r12.T((com.T.T.Tr.T5) r2, (java.lang.Object) r8, (java.lang.Object) r4)     // Catch:{ all -> 0x0083 }
            int r6 = r5.Tn()     // Catch:{ all -> 0x0083 }
            r9 = 20
            if (r6 == r9) goto L_0x01b6
            r9 = 15
            if (r6 != r9) goto L_0x01c0
        L_0x01b6:
            r12.T((com.T.T.Tr.T5) r2)
            goto L_0x009a
        L_0x01bb:
            java.lang.Object r8 = r12.T((java.lang.reflect.Type) r14)     // Catch:{ all -> 0x0083 }
            goto L_0x01a1
        L_0x01c0:
            r9 = 13
            if (r6 != r9) goto L_0x002e
            r5.T()     // Catch:{ all -> 0x0083 }
            r12.T((com.T.T.Tr.T5) r2)
            goto L_0x009a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.T.T.Tr.T.T2.T(com.T.T.Tr.Ty, java.util.Map, java.lang.reflect.Type, java.lang.Object):java.util.Map");
    }

    public static Object T(Ty parser, Map<Object, Object> map, Type keyType, Type valueType, Object fieldName) {
        T9 lexer = parser.Th();
        if (lexer.Tn() == 12 || lexer.Tn() == 16) {
            T7 keyDeserializer = parser.Ty().T(keyType);
            T7 valueDeserializer = parser.Ty().T(valueType);
            lexer.T(keyDeserializer.T());
            T5 context = parser.Tk();
            while (lexer.Tn() != 13) {
                try {
                    if (lexer.Tn() != 4 || !lexer.TN()) {
                        if (map.size() == 0 && lexer.Tn() == 4 && T.f131T.equals(lexer.Tf())) {
                            lexer.Tr(4);
                            lexer.T(16);
                            if (lexer.Tn() == 13) {
                                lexer.T();
                                return map;
                            }
                            lexer.T(keyDeserializer.T());
                        }
                        Object key = keyDeserializer.T(parser, keyType, (Object) null);
                        if (lexer.Tn() != 17) {
                            throw new Tn("syntax error, expect :, actual " + lexer.Tn());
                        }
                        lexer.T(valueDeserializer.T());
                        map.put(key, valueDeserializer.T(parser, valueType, key));
                        if (lexer.Tn() == 16) {
                            lexer.T(keyDeserializer.T());
                        }
                    } else {
                        Object object = null;
                        lexer.Tr(4);
                        if (lexer.Tn() == 4) {
                            String ref = lexer.Tf();
                            if ("..".equals(ref)) {
                                object = context.Ty().Tr();
                            } else if ("$".equals(ref)) {
                                T5 rootContext = context;
                                while (rootContext.Ty() != null) {
                                    rootContext = rootContext.Ty();
                                }
                                object = rootContext.Tr();
                            } else {
                                parser.T(new Ty.T(context, ref));
                                parser.T(1);
                            }
                            lexer.T(13);
                            if (lexer.Tn() != 13) {
                                throw new Tn("illegal ref");
                            }
                            lexer.T(16);
                            parser.T(context);
                            return object;
                        }
                        throw new Tn("illegal ref, " + TZ.T(lexer.Tn()));
                    }
                } finally {
                    parser.T(context);
                }
            }
            lexer.T(16);
            parser.T(context);
            return map;
        }
        throw new Tn("syntax error, expect {, actual " + lexer.T9());
    }

    /* access modifiers changed from: protected */
    public Map<Object, Object> T(Type type) {
        if (type == Properties.class) {
            return new Properties();
        }
        if (type == Hashtable.class) {
            return new Hashtable();
        }
        if (type == IdentityHashMap.class) {
            return new IdentityHashMap();
        }
        if (type == SortedMap.class || type == TreeMap.class) {
            return new TreeMap();
        }
        if (type == ConcurrentMap.class || type == ConcurrentHashMap.class) {
            return new ConcurrentHashMap();
        }
        if (type == Map.class || type == HashMap.class) {
            return new HashMap();
        }
        if (type == LinkedHashMap.class) {
            return new LinkedHashMap();
        }
        if (type instanceof ParameterizedType) {
            return T(((ParameterizedType) type).getRawType());
        }
        Class<?> clazz = (Class) type;
        if (clazz.isInterface()) {
            throw new Tn("unsupport type " + type);
        }
        try {
            return (Map) clazz.newInstance();
        } catch (Exception e) {
            throw new Tn("unsupport type " + type, e);
        }
    }

    public int T() {
        return 12;
    }
}
