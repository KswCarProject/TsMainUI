package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class Tr9 implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static Tr9 f218T = new Tr9();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (object != null) {
            long value = ((Long) object).longValue();
            out.T(value);
            if (serializer.T(TrG.WriteClassName) && value <= 2147483647L && value >= -2147483648L && fieldType != Long.class) {
                out.T('L');
            }
        } else if (out.T(TrG.WriteNullNumberAsZero)) {
            out.T('0');
        } else {
            out.T();
        }
    }
}
