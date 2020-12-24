package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigInteger;

/* compiled from: Proguard */
public class T5 implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final T5 f190T = new T5();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (object != null) {
            out.write(((BigInteger) object).toString());
        } else if (out.T(TrG.WriteNullNumberAsZero)) {
            out.T('0');
        } else {
            out.T();
        }
    }
}
