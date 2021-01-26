package com.T.T.Tr.T;

import com.T.T.Tn;
import com.T.T.Tr.Tk;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.sql.Date;
import java.text.ParseException;

/* compiled from: Proguard */
public class Try extends T implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final Try f172T = new Try();

    /* access modifiers changed from: protected */
    public <T> T T(Ty parser, Type clazz, Object fieldName, Object val) {
        long longVal;
        Date val2;
        if (val == null) {
            return null;
        }
        if (val instanceof java.util.Date) {
            val2 = new Date(((java.util.Date) val).getTime());
        } else if (val instanceof Number) {
            val2 = new Date(((Number) val).longValue());
        } else if (val instanceof String) {
            String strVal = (String) val;
            if (strVal.length() == 0) {
                return null;
            }
            Tk dateLexer = new Tk(strVal);
            try {
                if (dateLexer.TI()) {
                    longVal = dateLexer.TB().getTimeInMillis();
                } else {
                    T date = new Date(parser.T().parse(strVal).getTime());
                    dateLexer.close();
                    return date;
                }
            } catch (ParseException e) {
                longVal = Long.parseLong(strVal);
            } catch (Throwable th) {
                dateLexer.close();
                throw th;
            }
            dateLexer.close();
            return new Date(longVal);
        } else {
            throw new Tn("parse error : " + val);
        }
        return val2;
    }

    public int T() {
        return 2;
    }
}
