package com.T.T.Tr.T;

import com.T.T.Tn.TZ;
import com.T.T.Tr.T9;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class Tu implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final Tu f172T = new Tu();

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        return T(parser);
    }

    public static <T> T T(Ty parser) {
        T9 lexer = parser.Th();
        if (lexer.Tn() == 2) {
            String val = lexer.Th();
            lexer.T(16);
            return Float.valueOf(Float.parseFloat(val));
        } else if (lexer.Tn() == 3) {
            float val2 = lexer.T5();
            lexer.T(16);
            return Float.valueOf(val2);
        } else {
            Object value = parser.Tv();
            if (value == null) {
                return null;
            }
            return TZ.TZ(value);
        }
    }

    public int T() {
        return 2;
    }
}
