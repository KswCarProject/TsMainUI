package com.T.T.Ty;

import com.txznet.sdk.TXZResourceManager;
import java.io.IOException;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class T implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final T f188T = new T();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        if (object == null) {
            Trs out = serializer.Tv();
            if (out.T(TrG.WriteNullStringAsEmpty)) {
                out.T(TXZResourceManager.STYLE_DEFAULT);
            } else {
                out.T();
            }
        } else {
            serializer.T(object.toString());
        }
    }
}
