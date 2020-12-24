package com.T.T.Tr.T;

import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.util.regex.Pattern;

/* compiled from: Proguard */
public class T1 implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final T1 f138T = new T1();

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        Object value = parser.Tv();
        if (value == null) {
            return null;
        }
        return Pattern.compile((String) value);
    }

    public int T() {
        return 4;
    }
}
