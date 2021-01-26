package com.T.T.Tr;

import com.android.SdkConstants;
import com.autochips.camera.util.DVRConst;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Proguard */
public class TE {

    /* renamed from: T  reason: collision with root package name */
    public static TE f180T;
    private final Map<String, Integer> Tr;

    static {
        Map<String, Integer> map = new HashMap<>();
        map.put(DVRConst.UNKOWN_CAMERA_ID, 8);
        map.put("new", 9);
        map.put(SdkConstants.VALUE_TRUE, 6);
        map.put(SdkConstants.VALUE_FALSE, 7);
        f180T = new TE(map);
    }

    public TE(Map<String, Integer> keywords) {
        this.Tr = keywords;
    }

    public Integer T(String key) {
        return this.Tr.get(key);
    }
}
