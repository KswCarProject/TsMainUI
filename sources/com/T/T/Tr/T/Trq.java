package com.T.T.Tr.T;

import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.util.UUID;

/* compiled from: Proguard */
public class Trq implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final Trq f166T = new Trq();

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        String name = (String) parser.Tv();
        if (name == null) {
            return null;
        }
        return UUID.fromString(name);
    }

    public int T() {
        return 4;
    }
}
