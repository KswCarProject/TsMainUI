package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class Tw implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static Tw f237T = new Tw();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (object != null) {
            float floatValue = ((Float) object).floatValue();
            if (Float.isNaN(floatValue)) {
                out.T();
            } else if (Float.isInfinite(floatValue)) {
                out.T();
            } else {
                String floatText = Float.toString(floatValue);
                if (floatText.endsWith(".0")) {
                    floatText = floatText.substring(0, floatText.length() - 2);
                }
                out.write(floatText);
                if (serializer.T(TrG.WriteClassName)) {
                    out.T('F');
                }
            }
        } else if (serializer.T(TrG.WriteNullNumberAsZero)) {
            out.T('0');
        } else {
            out.T();
        }
    }
}
