package com.T.T.Tr.T;

import com.T.T.Tn;
import com.T.T.Tr.Tk;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

/* compiled from: Proguard */
public class TF extends T implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final TF f148T = new TF();

    /* access modifiers changed from: protected */
    public <T> T T(Ty parser, Type clazz, Object fieldName, Object val) {
        if (val == null) {
            return null;
        }
        if (val instanceof Date) {
            return val;
        }
        if (val instanceof Number) {
            return new Date(((Number) val).longValue());
        }
        if (val instanceof String) {
            String strVal = (String) val;
            if (strVal.length() == 0) {
                return null;
            }
            Tk dateLexer = new Tk(strVal);
            try {
                if (dateLexer.Tr(false)) {
                    return dateLexer.TB().getTime();
                }
                dateLexer.close();
                try {
                    return parser.T().parse(strVal);
                } catch (ParseException e) {
                    return new Date(Long.parseLong(strVal));
                }
            } finally {
                dateLexer.close();
            }
        } else {
            throw new Tn("parse error");
        }
    }

    public int T() {
        return 2;
    }
}
