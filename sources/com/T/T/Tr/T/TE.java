package com.T.T.Tr.T;

import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

/* compiled from: Proguard */
public class TE implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final TE f150T = new TE();

    public <T> T T(Ty parser, Type type, Object fieldName) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime((Date) TF.f151T.T(parser, type, fieldName));
        return calendar;
    }

    public int T() {
        return 2;
    }
}
