package android.support.v4.util;

public class DebugUtils {
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0018, code lost:
        r1 = r3.getClass().getName();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void buildShortClassTag(java.lang.Object r3, java.lang.StringBuilder r4) {
        /*
            if (r3 != 0) goto L_0x0008
            java.lang.String r2 = "null"
            r4.append(r2)
        L_0x0007:
            return
        L_0x0008:
            java.lang.Class r2 = r3.getClass()
            java.lang.String r1 = r2.getSimpleName()
            if (r1 == 0) goto L_0x0018
            int r2 = r1.length()
            if (r2 > 0) goto L_0x002e
        L_0x0018:
            java.lang.Class r2 = r3.getClass()
            java.lang.String r1 = r2.getName()
            r2 = 46
            int r0 = r1.lastIndexOf(r2)
            if (r0 <= 0) goto L_0x002e
            int r2 = r0 + 1
            java.lang.String r1 = r1.substring(r2)
        L_0x002e:
            r4.append(r1)
            r2 = 123(0x7b, float:1.72E-43)
            r4.append(r2)
            int r2 = java.lang.System.identityHashCode(r3)
            java.lang.String r2 = java.lang.Integer.toHexString(r2)
            r4.append(r2)
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.DebugUtils.buildShortClassTag(java.lang.Object, java.lang.StringBuilder):void");
    }
}
