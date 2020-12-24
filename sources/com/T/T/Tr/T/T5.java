package com.T.T.Tr.T;

import com.T.T.T;
import com.T.T.Tr.T9;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class T5 implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final T5 f141T = new T5();

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        return T(parser);
    }

    public static <T> T T(Ty parser) {
        T9 lexer = parser.Th();
        if (lexer.Tn() == 4) {
            String val = lexer.Tf();
            lexer.T(16);
            return val.toCharArray();
        } else if (lexer.Tn() == 2) {
            Number val2 = lexer.TE();
            lexer.T(16);
            return val2.toString().toCharArray();
        } else {
            Object value = parser.Tv();
            if (value == null) {
                return null;
            }
            return T.T(value).toCharArray();
        }
    }

    public int T() {
        return 4;
    }
}
