package com.T.T.Tr.T;

import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/* compiled from: Proguard */
public class Th implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final Th f157T = new Th();

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        Object value = parser.Tv();
        if (value == null) {
            return null;
        }
        return Charset.forName((String) value);
    }

    public int T() {
        return 4;
    }
}
