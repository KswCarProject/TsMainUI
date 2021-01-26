package com.T.T.Ty;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class TP implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static TP f208T = new TP();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (object == null) {
            out.T();
        } else {
            serializer.T(((File) object).getPath());
        }
    }
}
