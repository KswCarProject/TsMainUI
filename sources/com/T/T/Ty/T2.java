package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class T2 implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static T2 f187T = new T2();

    public final void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (object != null) {
            int[] array = (int[]) object;
            out.T('[');
            for (int i = 0; i < array.length; i++) {
                if (i != 0) {
                    out.T(',');
                }
                out.Tr(array[i]);
            }
            out.T(']');
        } else if (out.T(TrG.WriteNullListAsEmpty)) {
            out.write("[]");
        } else {
            out.T();
        }
    }
}
