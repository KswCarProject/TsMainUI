package com.T.T.Tr.T;

import com.T.T.Tn.TZ;
import com.T.T.Tr.T9;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: Proguard */
public class TC implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final TC f148T = new TC();

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        Long longObject;
        T9 lexer = parser.Th();
        if (lexer.Tn() == 2) {
            long longValue = lexer.TM();
            lexer.T(16);
            longObject = Long.valueOf(longValue);
        } else {
            Object value = parser.Tv();
            if (value == null) {
                return null;
            }
            longObject = TZ.T6(value);
        }
        if (clazz == AtomicLong.class) {
            return new AtomicLong(longObject.longValue());
        }
        return longObject;
    }

    public int T() {
        return 2;
    }
}
