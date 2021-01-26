package com.T.T.Ty;

import com.T.T.T;
import com.T.T.Tn.Tn;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* compiled from: Proguard */
public class Tu implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final Tu f235T = new Tu();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        char[] buf;
        Trs out = serializer.Tv();
        if (object == null) {
            out.T();
        } else if (!out.T(TrG.WriteClassName) || object.getClass() == fieldType) {
            Date date = (Date) object;
            if (out.T(TrG.WriteDateUseDateFormat)) {
                DateFormat format = serializer.T();
                if (format == null) {
                    format = new SimpleDateFormat(T.Ty);
                }
                out.T(format.format(date));
                return;
            }
            long time = date.getTime();
            if (serializer.T(TrG.UseISO8601DateFormat)) {
                if (serializer.T(TrG.UseSingleQuotes)) {
                    out.append('\'');
                } else {
                    out.append('\"');
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(time);
                int year = calendar.get(1);
                int month = calendar.get(2) + 1;
                int day = calendar.get(5);
                int hour = calendar.get(11);
                int minute = calendar.get(12);
                int second = calendar.get(13);
                int millis = calendar.get(14);
                if (millis != 0) {
                    buf = "0000-00-00T00:00:00.000".toCharArray();
                    Tn.T(millis, 23, buf);
                    Tn.T(second, 19, buf);
                    Tn.T(minute, 16, buf);
                    Tn.T(hour, 13, buf);
                    Tn.T(day, 10, buf);
                    Tn.T(month, 7, buf);
                    Tn.T(year, 4, buf);
                } else if (second == 0 && minute == 0 && hour == 0) {
                    buf = "0000-00-00".toCharArray();
                    Tn.T(day, 10, buf);
                    Tn.T(month, 7, buf);
                    Tn.T(year, 4, buf);
                } else {
                    buf = "0000-00-00T00:00:00".toCharArray();
                    Tn.T(second, 19, buf);
                    Tn.T(minute, 16, buf);
                    Tn.T(hour, 13, buf);
                    Tn.T(day, 10, buf);
                    Tn.T(month, 7, buf);
                    Tn.T(year, 4, buf);
                }
                out.write(buf);
                if (serializer.T(TrG.UseSingleQuotes)) {
                    out.append('\'');
                } else {
                    out.append('\"');
                }
            } else {
                out.T(time);
            }
        } else if (object.getClass() == Date.class) {
            out.write("new Date(");
            out.T(((Date) object).getTime(), ')');
        } else {
            out.T('{');
            out.Tr(T.f131T);
            serializer.T(object.getClass().getName());
            out.T(',');
            out.Tr("val");
            out.T(((Date) object).getTime());
            out.T('}');
        }
    }
}
