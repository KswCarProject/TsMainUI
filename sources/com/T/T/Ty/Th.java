package com.T.T.Ty;

import com.android.SdkConstants;
import java.io.IOException;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class Th implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final Th f210T = new Th();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        Boolean value = (Boolean) object;
        if (value == null) {
            if (out.T(TrG.WriteNullBooleanAsFalse)) {
                out.write(SdkConstants.VALUE_FALSE);
            } else {
                out.T();
            }
        } else if (value.booleanValue()) {
            out.write(SdkConstants.VALUE_TRUE);
        } else {
            out.write(SdkConstants.VALUE_FALSE);
        }
    }
}
