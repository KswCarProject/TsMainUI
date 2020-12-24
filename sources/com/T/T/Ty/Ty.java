package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: Proguard */
public class Ty implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final Ty f236T = new Ty();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (((AtomicBoolean) object).get()) {
            out.append((CharSequence) "true");
        } else {
            out.append((CharSequence) "false");
        }
    }
}
