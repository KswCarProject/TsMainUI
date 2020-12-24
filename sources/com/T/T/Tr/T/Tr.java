package com.T.T.Tr.T;

/* compiled from: Proguard */
public class Tr implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final Tr f159T = new Tr();

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Class<java.util.concurrent.atomic.AtomicIntegerArray>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Class<java.util.concurrent.atomic.AtomicLongArray>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v14, resolved type: java.lang.reflect.Type[]} */
    /* JADX WARNING: type inference failed for: r8v1, types: [java.lang.reflect.Type] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T T(com.T.T.Tr.Ty r19, java.lang.reflect.Type r20, java.lang.Object r21) {
        /*
            r18 = this;
            com.T.T.Tr.T9 r10 = r19.Th()
            int r16 = r10.Tn()
            r17 = 8
            r0 = r16
            r1 = r17
            if (r0 != r1) goto L_0x0019
            r16 = 16
            r0 = r16
            r10.T((int) r0)
            r4 = 0
        L_0x0018:
            return r4
        L_0x0019:
            java.lang.Class<java.util.concurrent.atomic.AtomicIntegerArray> r16 = java.util.concurrent.atomic.AtomicIntegerArray.class
            r0 = r20
            r1 = r16
            if (r0 != r1) goto L_0x004f
            com.T.T.Tr r3 = new com.T.T.Tr
            r3.<init>()
            r0 = r19
            r0.Tr((java.util.Collection) r3)
            java.util.concurrent.atomic.AtomicIntegerArray r4 = new java.util.concurrent.atomic.AtomicIntegerArray
            int r16 = r3.size()
            r0 = r16
            r4.<init>(r0)
            r9 = 0
        L_0x0037:
            int r16 = r3.size()
            r0 = r16
            if (r9 >= r0) goto L_0x0018
            java.lang.Integer r16 = r3.T((int) r9)
            int r16 = r16.intValue()
            r0 = r16
            r4.set(r9, r0)
            int r9 = r9 + 1
            goto L_0x0037
        L_0x004f:
            java.lang.Class<java.util.concurrent.atomic.AtomicLongArray> r16 = java.util.concurrent.atomic.AtomicLongArray.class
            r0 = r20
            r1 = r16
            if (r0 != r1) goto L_0x0085
            com.T.T.Tr r3 = new com.T.T.Tr
            r3.<init>()
            r0 = r19
            r0.Tr((java.util.Collection) r3)
            java.util.concurrent.atomic.AtomicLongArray r4 = new java.util.concurrent.atomic.AtomicLongArray
            int r16 = r3.size()
            r0 = r16
            r4.<init>(r0)
            r9 = 0
        L_0x006d:
            int r16 = r3.size()
            r0 = r16
            if (r9 >= r0) goto L_0x0018
            java.lang.Long r16 = r3.Tr((int) r9)
            long r16 = r16.longValue()
            r0 = r16
            r4.set(r9, r0)
            int r9 = r9 + 1
            goto L_0x006d
        L_0x0085:
            int r16 = r10.Tn()
            r17 = 4
            r0 = r16
            r1 = r17
            if (r0 != r1) goto L_0x009f
            byte[] r5 = r10.TO()
            r16 = 16
            r0 = r16
            r10.T((int) r0)
            r4 = r5
            goto L_0x0018
        L_0x009f:
            r0 = r20
            boolean r0 = r0 instanceof java.lang.reflect.GenericArrayType
            r16 = r0
            if (r16 == 0) goto L_0x0124
            r6 = r20
            java.lang.reflect.GenericArrayType r6 = (java.lang.reflect.GenericArrayType) r6
            java.lang.reflect.Type r8 = r6.getGenericComponentType()
            boolean r0 = r8 instanceof java.lang.reflect.TypeVariable
            r16 = r0
            if (r16 == 0) goto L_0x0120
            r15 = r8
            java.lang.reflect.TypeVariable r15 = (java.lang.reflect.TypeVariable) r15
            com.T.T.Tr.T5 r16 = r19.Tk()
            java.lang.reflect.Type r13 = r16.T()
            boolean r0 = r13 instanceof java.lang.reflect.ParameterizedType
            r16 = r0
            if (r16 == 0) goto L_0x011d
            r11 = r13
            java.lang.reflect.ParameterizedType r11 = (java.lang.reflect.ParameterizedType) r11
            java.lang.reflect.Type r12 = r11.getRawType()
            r2 = 0
            boolean r0 = r12 instanceof java.lang.Class
            r16 = r0
            if (r16 == 0) goto L_0x00fb
            java.lang.Class r12 = (java.lang.Class) r12
            java.lang.reflect.TypeVariable[] r14 = r12.getTypeParameters()
            r9 = 0
        L_0x00db:
            int r0 = r14.length
            r16 = r0
            r0 = r16
            if (r9 >= r0) goto L_0x00fb
            r16 = r14[r9]
            java.lang.String r16 = r16.getName()
            java.lang.String r17 = r15.getName()
            boolean r16 = r16.equals(r17)
            if (r16 == 0) goto L_0x00f8
            java.lang.reflect.Type[] r16 = r11.getActualTypeArguments()
            r2 = r16[r9]
        L_0x00f8:
            int r9 = r9 + 1
            goto L_0x00db
        L_0x00fb:
            boolean r0 = r2 instanceof java.lang.Class
            r16 = r0
            if (r16 == 0) goto L_0x011a
            r7 = r2
            java.lang.Class r7 = (java.lang.Class) r7
        L_0x0104:
            com.T.T.Tr r3 = new com.T.T.Tr
            r3.<init>()
            r0 = r19
            r1 = r21
            r0.T((java.lang.reflect.Type) r7, (java.util.Collection) r3, (java.lang.Object) r1)
            r0 = r18
            r1 = r19
            java.lang.Object r4 = r0.T((com.T.T.Tr.Ty) r1, (java.lang.Class<?>) r7, (com.T.T.Tr) r3)
            goto L_0x0018
        L_0x011a:
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            goto L_0x0104
        L_0x011d:
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            goto L_0x0104
        L_0x0120:
            r7 = r8
            java.lang.Class r7 = (java.lang.Class) r7
            goto L_0x0104
        L_0x0124:
            r6 = r20
            java.lang.Class r6 = (java.lang.Class) r6
            java.lang.Class r7 = r6.getComponentType()
            r8 = r7
            goto L_0x0104
        */
        throw new UnsupportedOperationException("Method not decompiled: com.T.T.Tr.T.Tr.T(com.T.T.Tr.Ty, java.lang.reflect.Type, java.lang.Object):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r13v0, types: [java.lang.reflect.Type, java.lang.Class<?>, java.lang.Class] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <T> T T(com.T.T.Tr.Ty r12, java.lang.Class<?> r13, com.T.T.Tr r14) {
        /*
            r11 = this;
            if (r14 != 0) goto L_0x0004
            r3 = 0
        L_0x0003:
            return r3
        L_0x0004:
            int r4 = r14.size()
            java.lang.Object r3 = java.lang.reflect.Array.newInstance(r13, r4)
            r2 = 0
        L_0x000d:
            if (r2 >= r4) goto L_0x0064
            java.lang.Object r5 = r14.get(r2)
            if (r5 != r14) goto L_0x001b
            java.lang.reflect.Array.set(r3, r2, r3)
        L_0x0018:
            int r2 = r2 + 1
            goto L_0x000d
        L_0x001b:
            boolean r10 = r13.isArray()
            if (r10 == 0) goto L_0x0033
            boolean r10 = r13.isInstance(r5)
            if (r10 == 0) goto L_0x002c
            r1 = r5
        L_0x0028:
            java.lang.reflect.Array.set(r3, r2, r1)
            goto L_0x0018
        L_0x002c:
            com.T.T.Tr r5 = (com.T.T.Tr) r5
            java.lang.Object r1 = r11.T((com.T.T.Tr.Ty) r12, (java.lang.Class<?>) r13, (com.T.T.Tr) r5)
            goto L_0x0028
        L_0x0033:
            r1 = 0
            boolean r10 = r5 instanceof com.T.T.Tr
            if (r10 == 0) goto L_0x0056
            r0 = 0
            r6 = r5
            com.T.T.Tr r6 = (com.T.T.Tr) r6
            int r7 = r6.size()
            r9 = 0
        L_0x0041:
            if (r9 >= r7) goto L_0x0050
            java.lang.Object r8 = r6.get(r9)
            if (r8 != r14) goto L_0x004d
            r6.set(r2, r3)
            r0 = 1
        L_0x004d:
            int r9 = r9 + 1
            goto L_0x0041
        L_0x0050:
            if (r0 == 0) goto L_0x0056
            java.lang.Object[] r1 = r6.toArray()
        L_0x0056:
            if (r1 != 0) goto L_0x0060
            com.T.T.Tr.Tv r10 = r12.Ty()
            java.lang.Object r1 = com.T.T.Tn.TZ.T((java.lang.Object) r5, r13, (com.T.T.Tr.Tv) r10)
        L_0x0060:
            java.lang.reflect.Array.set(r3, r2, r1)
            goto L_0x0018
        L_0x0064:
            r14.Tr((java.lang.Object) r3)
            r14.T((java.lang.reflect.Type) r13)
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.T.T.Tr.T.Tr.T(com.T.T.Tr.Ty, java.lang.Class, com.T.T.Tr):java.lang.Object");
    }

    public int T() {
        return 14;
    }
}
