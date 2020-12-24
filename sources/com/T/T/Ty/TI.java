package com.T.T.Ty;

import java.util.List;

/* compiled from: Proguard */
public class TI {
    public static Object T(T7 serializer, Object object, String key, Object propertyValue) {
        List<Tr0> valueFilters = serializer.Ty();
        if (valueFilters != null) {
            for (Tr0 valueFilter : valueFilters) {
                propertyValue = valueFilter.T(object, key, propertyValue);
            }
        }
        return propertyValue;
    }

    public static String Tr(T7 serializer, Object object, String key, Object propertyValue) {
        List<TrZ> nameFilters = serializer.TZ();
        if (nameFilters != null) {
            for (TrZ nameFilter : nameFilters) {
                key = nameFilter.T(object, key, propertyValue);
            }
        }
        return key;
    }

    public static boolean T(T7 serializer, Object object, String key) {
        List<TrF> filters = serializer.TE();
        if (filters == null) {
            return true;
        }
        for (TrF filter : filters) {
            if (!filter.T(serializer, object, key)) {
                return false;
            }
        }
        return true;
    }

    public static boolean Ty(T7 serializer, Object object, String key, Object propertyValue) {
        List<Trq> propertyFilters = serializer.T5();
        if (propertyFilters == null) {
            return true;
        }
        for (Trq propertyFilter : propertyFilters) {
            if (!propertyFilter.T(object, key, propertyValue)) {
                return false;
            }
        }
        return true;
    }
}
