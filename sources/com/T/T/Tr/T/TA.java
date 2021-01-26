package com.T.T.Tr.T;

import com.T.T.Tn.TZ;
import com.T.T.Tr.T9;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: Proguard */
public class TA implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final TA f147T = new TA();

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        Integer intObj;
        T9 lexer = parser.Th();
        if (lexer.Tn() == 8) {
            lexer.T(16);
            return null;
        }
        if (lexer.Tn() == 2) {
            int val = lexer.TK();
            lexer.T(16);
            intObj = Integer.valueOf(val);
        } else if (lexer.Tn() == 3) {
            BigDecimal decimalValue = lexer.TX();
            lexer.T(16);
            intObj = Integer.valueOf(decimalValue.intValue());
        } else {
            intObj = TZ.Te(parser.Tv());
        }
        if (clazz == AtomicInteger.class) {
            return new AtomicInteger(intObj.intValue());
        }
        return intObj;
    }

    public int T() {
        return 2;
    }
}
