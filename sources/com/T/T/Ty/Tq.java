package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class Tq implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static Tq f214T = new Tq();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (((Number) object) != null) {
            out.Tr((int) ((Number) object).shortValue());
            if (serializer.T(TrG.WriteClassName)) {
                out.T('B');
            }
        } else if (out.T(TrG.WriteNullNumberAsZero)) {
            out.T('0');
        } else {
            out.T();
        }
    }
}
