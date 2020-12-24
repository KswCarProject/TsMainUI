package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.TimeZone;

/* compiled from: Proguard */
public class TrA implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final TrA f216T = new TrA();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        if (object == null) {
            serializer.Th();
        } else {
            serializer.T(((TimeZone) object).getID());
        }
    }
}
