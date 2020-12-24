package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class Th implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final Th f207T = new Th();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        Boolean value = (Boolean) object;
        if (value == null) {
            if (out.T(TrG.WriteNullBooleanAsFalse)) {
                out.write("false");
            } else {
                out.T();
            }
        } else if (value.booleanValue()) {
            out.write("true");
        } else {
            out.write("false");
        }
    }
}
