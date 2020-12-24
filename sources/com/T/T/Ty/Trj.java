package com.T.T.Ty;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: Proguard */
public class Trj implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final Trj f221T = new Trj();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Object item;
        if (object instanceof AtomicReference) {
            item = ((AtomicReference) object).get();
        } else {
            item = ((Reference) object).get();
        }
        serializer.Tn(item);
    }
}
