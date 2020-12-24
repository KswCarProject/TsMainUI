package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class TA implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final TA f195T = new TA();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (object == null) {
            serializer.Tv().T();
        } else if (serializer.T(TrG.WriteEnumUsingToString)) {
            serializer.T(((Enum) object).name());
        } else {
            out.Tr(((Enum) object).ordinal());
        }
    }
}
