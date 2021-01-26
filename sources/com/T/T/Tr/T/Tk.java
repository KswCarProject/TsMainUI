package com.T.T.Tr.T;

import com.T.T.Tn.TZ;
import com.T.T.Tr.T9;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: Proguard */
public class Tk implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final Tk f159T = new Tk();

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        Boolean boolObj;
        T9 lexer = parser.Th();
        if (lexer.Tn() == 6) {
            lexer.T(16);
            boolObj = Boolean.TRUE;
        } else if (lexer.Tn() == 7) {
            lexer.T(16);
            boolObj = Boolean.FALSE;
        } else if (lexer.Tn() == 2) {
            int intValue = lexer.TK();
            lexer.T(16);
            if (intValue == 1) {
                boolObj = Boolean.TRUE;
            } else {
                boolObj = Boolean.FALSE;
            }
        } else {
            Object value = parser.Tv();
            if (value == null) {
                return null;
            }
            boolObj = TZ.Tq(value);
        }
        if (clazz == AtomicBoolean.class) {
            return new AtomicBoolean(boolObj.booleanValue());
        }
        return boolObj;
    }

    public int T() {
        return 6;
    }
}
