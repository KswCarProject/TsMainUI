package com.T.T.Tr.T;

import com.T.T.Tn;
import com.T.T.Tr.T9;
import com.T.T.Tr.Tk;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.sql.Time;

/* compiled from: Proguard */
public class TrE implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final TrE f163T = new TrE();

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        long longVal;
        T9 lexer = parser.Th();
        if (lexer.Tn() == 16) {
            lexer.T(4);
            if (lexer.Tn() != 4) {
                throw new Tn("syntax error");
            }
            lexer.Tr(2);
            if (lexer.Tn() != 2) {
                throw new Tn("syntax error");
            }
            long time = lexer.TM();
            lexer.T(13);
            if (lexer.Tn() != 13) {
                throw new Tn("syntax error");
            }
            lexer.T(16);
            return new Time(time);
        }
        Object val = parser.Tv();
        if (val == null) {
            return null;
        }
        if (val instanceof Time) {
            return val;
        }
        if (val instanceof Number) {
            return new Time(((Number) val).longValue());
        }
        if (val instanceof String) {
            String strVal = (String) val;
            if (strVal.length() == 0) {
                return null;
            }
            Tk dateLexer = new Tk(strVal);
            if (dateLexer.TI()) {
                longVal = dateLexer.TB().getTimeInMillis();
            } else {
                longVal = Long.parseLong(strVal);
            }
            dateLexer.close();
            return new Time(longVal);
        }
        throw new Tn("parse error");
    }

    public int T() {
        return 2;
    }
}
