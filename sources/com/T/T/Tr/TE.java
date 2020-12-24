package com.T.T.Tr;

import java.util.HashMap;
import java.util.Map;

/* compiled from: Proguard */
public class TE {

    /* renamed from: T  reason: collision with root package name */
    public static TE f177T;
    private final Map<String, Integer> Tr;

    static {
        Map<String, Integer> map = new HashMap<>();
        map.put("null", 8);
        map.put("new", 9);
        map.put("true", 6);
        map.put("false", 7);
        f177T = new TE(map);
    }

    public TE(Map<String, Integer> keywords) {
        this.Tr = keywords;
    }

    public Integer T(String key) {
        return this.Tr.get(key);
    }
}
