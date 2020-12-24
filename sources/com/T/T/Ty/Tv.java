package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class Tv implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static Tv f233T = new Tv();

    public final void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (object != null) {
            boolean[] array = (boolean[]) object;
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
