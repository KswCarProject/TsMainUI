package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class T4 implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static T4 f192T = new T4();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        Number value = (Number) object;
        if (value != null) {
            out.Tr(value.intValue());
        } else if (out.T(TrG.WriteNullNumberAsZero)) {
            out.T('0');
        } else {
            out.T();
        }
    }
}
