package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class Tr implements Trh {

    /* renamed from: T  reason: collision with root package name */
    private final Class<?> f212T;
    private final Trh Tr;

    public Tr(Class<?> componentType, Trh compObjectSerializer) {
        this.f212T = componentType;
        this.Tr = compObjectSerializer;
    }

    public final void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (object != null) {
            Object[] array = (Object[]) object;
            int size = array.length;
            TrB context = serializer.Tr();
            serializer.T(context, object, fieldName);
            try {
                out.append('[');
                for (int i = 0; i < size; i++) {
                    if (i != 0) {
                        out.append(',');
                    }
                    Object item = array[i];
                    if (item == null) {
                        out.append((CharSequence) "null");
                    } else if (item.getClass() == this.f212T) {
                        this.Tr.T(serializer, item, Integer.valueOf(i), (Type) null);
                    } else {
                        serializer.T(item.getClass()).T(serializer, item, Integer.valueOf(i), (Type) null);
                    }
                }
                out.append(']');
            } finally {
                serializer.T(context);
            }
        } else if (out.T(TrG.WriteNullListAsEmpty)) {
            out.write("[]");
        } else {
            out.T();
        }
    }
}
