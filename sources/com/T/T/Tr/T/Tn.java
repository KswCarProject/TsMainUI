package com.T.T.Tr.T;

import com.T.T.Tn.TZ;
import com.T.T.Tr.T9;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/* compiled from: Proguard */
public class Tn implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final Tn f160T = new Tn();

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        return T(parser);
    }

    public static <T> T T(Ty parser) {
        T9 lexer = parser.Th();
        if (lexer.Tn() == 2) {
            long val = lexer.TM();
            lexer.T(16);
            return new BigDecimal(val);
        } else if (lexer.Tn() == 3) {
            T TX = lexer.TX();
            lexer.T(16);
            return TX;
        } else {
            Object value = parser.Tv();
            if (value == null) {
                return null;
            }
            return TZ.T9(value);
        }
    }

    public int T() {
        return 2;
    }
}
