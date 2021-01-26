package com.T.T.Tr.T;

import com.T.T.Tr.Ty;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: Proguard */
public class Trr implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final Trr f170T = new Trr();

    public <T> T T(Ty parser, Type type, Object fieldName) {
        ParameterizedType paramType = (ParameterizedType) type;
        Object itemObject = parser.T(paramType.getActualTypeArguments()[0]);
        Type rawType = paramType.getRawType();
        if (rawType == AtomicReference.class) {
            return new AtomicReference(itemObject);
        }
        if (rawType == WeakReference.class) {
            return new WeakReference(itemObject);
        }
        if (rawType == SoftReference.class) {
            return new SoftReference(itemObject);
        }
        throw new UnsupportedOperationException(rawType.toString());
    }

    public int T() {
        return 12;
    }
}
