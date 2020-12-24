package com.T.T.Tr.T;

import com.T.T.Tn.TZ;
import com.T.T.Tr.T9;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/* compiled from: Proguard */
public class T3 implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final T3 f140T = new T3();

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        T9 lexer = parser.Th();
        if (lexer.Tn() == 2) {
            if (clazz == Double.TYPE || clazz == Double.class) {
                String val = lexer.Th();
                lexer.T(16);
                return Double.valueOf(Double.parseDouble(val));
            }
            long val2 = lexer.TM();
            lexer.T(16);
            if (clazz == Short.TYPE || clazz == Short.class) {
                return Short.valueOf((short) ((int) val2));
            }
            if (clazz == Byte.TYPE || clazz == Byte.class) {
                return Byte.valueOf((byte) ((int) val2));
            }
            if (val2 < -2147483648L || val2 > 2147483647L) {
                return Long.valueOf(val2);
            }
            return Integer.valueOf((int) val2);
        } else if (lexer.Tn() != 3) {
            Object value = parser.Tv();
            if (value == null) {
                return null;
            }
            if (clazz == Double.TYPE || clazz == Double.class) {
                return TZ.TE(value);
            }
            if (clazz == Short.TYPE || clazz == Short.class) {
                return TZ.Tn(value);
            }
            if (clazz == Byte.TYPE || clazz == Byte.class) {
                return TZ.Tr(value);
            }
            return TZ.T9(value);
        } else if (clazz == Double.TYPE || clazz == Double.class) {
            String val3 = lexer.Th();
            lexer.T(16);
            return Double.valueOf(Double.parseDouble(val3));
        } else {
            BigDecimal val4 = lexer.TX();
            lexer.T(16);
            if (clazz == Short.TYPE || clazz == Short.class) {
                return Short.valueOf(val4.shortValue());
            }
            if (clazz == Byte.TYPE || clazz == Byte.class) {
                return Byte.valueOf(val4.byteValue());
            }
            return val4;
        }
    }

    public int T() {
        return 2;
    }
}
