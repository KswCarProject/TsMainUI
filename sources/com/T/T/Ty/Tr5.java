package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class Tr5 implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final Tr5 f216T = new Tr5();

    public final void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        Object[] array = (Object[]) object;
        if (object != null) {
            int size = array.length;
            int end = size - 1;
            if (end == -1) {
                out.append((CharSequence) "[]");
                return;
            }
            TrB context = serializer.Tr();
            serializer.T(context, object, fieldName);
            Class<?> preClazz = null;
            Trh preWriter = null;
            try {
                out.append('[');
                if (out.T(TrG.PrettyFormat)) {
                    serializer.Tn();
                    serializer.Tk();
                    for (int i = 0; i < size; i++) {
                        if (i != 0) {
                            out.T(',');
                            serializer.Tk();
                        }
                        serializer.Tn(array[i]);
                    }
                    serializer.T9();
                    serializer.Tk();
                    out.T(']');
                    return;
                }
                for (int i2 = 0; i2 < end; i2++) {
                    Object item = array[i2];
                    if (item == null) {
                        out.append((CharSequence) "null,");
                    } else {
                        if (serializer.Tr(item)) {
                            serializer.Ty(item);
                        } else {
                            Class<?> clazz = item.getClass();
                            if (clazz == preClazz) {
                                preWriter.T(serializer, item, (Object) null, (Type) null);
                            } else {
                                preClazz = clazz;
                                preWriter = serializer.T(clazz);
                                preWriter.T(serializer, item, (Object) null, (Type) null);
                            }
                        }
                        out.append(',');
                    }
                }
                Object item2 = array[end];
                if (item2 == null) {
                    out.append((CharSequence) "null]");
                } else {
                    if (serializer.Tr(item2)) {
                        serializer.Ty(item2);
                    } else {
                        serializer.Tn(item2);
                    }
                    out.append(']');
                }
                serializer.T(context);
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
