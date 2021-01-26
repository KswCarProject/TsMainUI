package com.T.T.Ty;

import com.txznet.sdk.TXZResourceManager;
import java.io.IOException;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class TrD implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static TrD f221T = new TrD();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        T(serializer, (String) object);
    }

    public void T(T7 serializer, String value) {
        Trs out = serializer.Tv();
        if (value != null) {
            out.T(value);
        } else if (out.T(TrG.WriteNullStringAsEmpty)) {
            out.T(TXZResourceManager.STYLE_DEFAULT);
        } else {
            out.T();
        }
    }
}
