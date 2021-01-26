package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class Trn implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static Trn f226T = new Trn();

    public final void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (object != null) {
            long[] array = (long[]) object;
            out.T('[');
            for (int i = 0; i < array.length; i++) {
                if (i != 0) {
                    out.T(',');
                }
                out.T(array[i]);
            }
            out.T(']');
        } else if (out.T(TrG.WriteNullListAsEmpty)) {
            out.write("[]");
        } else {
            out.T();
        }
    }
}
