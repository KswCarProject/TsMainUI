package com.T.T.Tr.T;

import com.T.T.Tr.Ty;
import java.io.File;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class TG implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final TG f149T = new TG();

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        Object value = parser.Tv();
        if (value == null) {
            return null;
        }
        return new File((String) value);
    }

    public int T() {
        return 4;
    }
}
