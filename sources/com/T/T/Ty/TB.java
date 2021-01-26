package com.T.T.Ty;

import com.txznet.sdk.TXZResourceManager;
import java.io.IOException;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class TB implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final TB f199T = new TB();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        Character value = (Character) object;
        if (value == null) {
            out.T(TXZResourceManager.STYLE_DEFAULT);
        } else if (value.charValue() == 0) {
            out.T("\u0000");
        } else {
            out.T(value.toString());
        }
    }
}
