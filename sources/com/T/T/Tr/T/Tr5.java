package com.T.T.Tr.T;

import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.util.TimeZone;

/* compiled from: Proguard */
public class Tr5 implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final Tr5 f160T = new Tr5();

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        String id = (String) parser.Tv();
        if (id == null) {
            return null;
        }
        return TimeZone.getTimeZone(id);
    }

    public int T() {
        return 4;
    }
}
