package com.T.T.Tr.T;

import com.T.T.Tn.TZ;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.math.BigInteger;

/* compiled from: Proguard */
public class T9 implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final T9 f146T = new T9();

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        return T(parser);
    }

    public static <T> T T(Ty parser) {
        com.T.T.Tr.T9 lexer = parser.Th();
        if (lexer.Tn() == 2) {
            String val = lexer.Th();
            lexer.T(16);
            return new BigInteger(val);
        }
        Object value = parser.Tv();
        if (value == null) {
            return null;
        }
        return TZ.Tk(value);
    }

    public int T() {
        return 2;
    }
}
