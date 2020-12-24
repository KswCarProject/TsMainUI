package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/* compiled from: Proguard */
public class TE implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final TE f199T = new TE();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (object != null) {
            BigDecimal val = (BigDecimal) object;
            out.write(val.toString());
            if (out.T(TrG.WriteClassName) && fieldType != BigDecimal.class && val.scale() == 0) {
                out.T('.');
            }
        } else if (out.T(TrG.WriteNullNumberAsZero)) {
            out.T('0');
        } else {
            out.T();
        }
    }
}
