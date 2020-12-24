package com.T.T.Tr.T;

import com.T.T.Tn;
import com.T.T.Tn.TZ;
import com.T.T.Tr.T9;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class T6 implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final T6 f142T = new T6();

    public <T> T T(Ty parser, Type type, Object fieldName) {
        T9 lexer = parser.Th();
        if (lexer.Tn() == 8) {
            lexer.T();
            return null;
        } else if (lexer.Tn() != 4) {
            throw new Tn("expect className");
        } else {
            String className = lexer.Tf();
            lexer.T(16);
            return TZ.T(className);
        }
    }

    public int T() {
        return 4;
    }
}
