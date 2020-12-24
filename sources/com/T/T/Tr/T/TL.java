package com.T.T.Tr.T;

import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/* compiled from: Proguard */
public final class TL extends Ts {
    private final Ty T9;
    private final List Tn;
    private final int Ty;

    public TL(Ty parser, List list, int index) {
        super((Class<?>) null, (com.T.T.Tn.Ty) null);
        this.T9 = parser;
        this.Ty = index;
        this.Tn = list;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000d, code lost:
        r3 = (com.T.T.Tr) r6.Tn;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void T(java.lang.Object r7, java.lang.Object r8) {
        /*
            r6 = this;
            java.util.List r4 = r6.Tn
            int r5 = r6.Ty
            r4.set(r5, r8)
            java.util.List r4 = r6.Tn
            boolean r4 = r4 instanceof com.T.T.Tr
            if (r4 == 0) goto L_0x0038
            java.util.List r3 = r6.Tn
            com.T.T.Tr r3 = (com.T.T.Tr) r3
            java.lang.Object r0 = r3.Tr()
            if (r0 == 0) goto L_0x0038
            int r1 = java.lang.reflect.Array.getLength(r0)
            int r4 = r6.Ty
            if (r1 <= r4) goto L_0x0038
            java.lang.reflect.Type r4 = r3.Ty()
            if (r4 == 0) goto L_0x0039
            java.lang.reflect.Type r4 = r3.Ty()
            com.T.T.Tr.Ty r5 = r6.T9
            com.T.T.Tr.Tv r5 = r5.Ty()
            java.lang.Object r2 = com.T.T.Tn.TZ.T((java.lang.Object) r8, (java.lang.reflect.Type) r4, (com.T.T.Tr.Tv) r5)
        L_0x0033:
            int r4 = r6.Ty
            java.lang.reflect.Array.set(r0, r4, r2)
        L_0x0038:
            return
        L_0x0039:
            r2 = r8
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: com.T.T.Tr.T.TL.T(java.lang.Object, java.lang.Object):void");
    }

    public void T(Ty parser, Object object, Type objectType, Map<String, Object> map) {
    }
}
