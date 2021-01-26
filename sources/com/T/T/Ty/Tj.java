package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class Tj implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static Tj f211T = new Tj();

    public final void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (object != null) {
            out.T(new String((char[]) object));
        } else if (out.T(TrG.WriteNullListAsEmpty)) {
            out.write("[]");
        } else {
            out.T();
        }
    }
}
