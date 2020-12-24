package com.T.T.Tr.T;

import com.T.T.Tr.T9;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class Tr9 implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final Tr9 f162T = new Tr9();

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        return T(parser);
    }

    public static <T> T T(Ty parser) {
        T9 lexer = parser.Th();
        if (lexer.Tn() == 4) {
            String val = lexer.Tf();
            lexer.T(16);
            return val;
        } else if (lexer.Tn() == 2) {
            String val2 = lexer.Th();
            lexer.T(16);
            return val2;
        } else {
            Object value = parser.Tv();
            if (value == null) {
                return null;
            }
            return value.toString();
        }
    }

    public int T() {
        return 4;
    }
}
