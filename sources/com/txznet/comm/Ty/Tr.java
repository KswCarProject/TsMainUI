package com.txznet.comm.Ty;

import java.lang.reflect.Array;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
public class Tr {

    /* renamed from: T  reason: collision with root package name */
    private JSONObject f415T;

    public Tr() {
        this.f415T = new JSONObject();
    }

    public Tr(byte[] data) {
        if (data == null) {
            this.f415T = new JSONObject();
            return;
        }
        try {
            this.f415T = new JSONObject(new String(data));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Tr(String data) {
        if (data == null) {
            this.f415T = new JSONObject();
            return;
        }
        try {
            this.f415T = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Tr(JSONObject json) {
        this.f415T = json;
    }

    public JSONObject T() {
        return this.f415T;
    }

    public Tr T(String key, Object val) {
        if (this.f415T == null) {
            this.f415T = new JSONObject();
        }
        if (val != null) {
            try {
                if (val.getClass().isArray()) {
                    JSONArray jArr = new JSONArray();
                    for (Object obj : (Object[]) val) {
                        jArr.put(obj);
                    }
                    this.f415T.put(key, jArr);
                } else if (val instanceof Collection) {
                    this.f415T.put(key, new JSONArray((Collection) val));
                } else {
                    this.f415T.put(key, val);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public Tr T(String key) {
        this.f415T.remove(key);
        return this;
    }

    public <T> T T(String key, Class<T> clazz) {
        return T(key, clazz, (Object) null);
    }

    public <T> T T(String key, Class<T> clazz, T def) {
        if (this.f415T == null || !this.f415T.has(key)) {
            return def;
        }
        try {
            T obj = this.f415T.get(key);
            if (JSONObject.NULL.equals(obj)) {
                return def;
            }
            if (clazz == Double.class) {
                return new Double(this.f415T.getDouble(key));
            }
            if (clazz == Float.class) {
                return new Float(this.f415T.getDouble(key));
            }
            if (clazz == Integer.class) {
                return new Integer(this.f415T.getInt(key));
            }
            if (clazz == Long.class) {
                return new Long(this.f415T.getLong(key));
            }
            if (!(obj instanceof JSONArray) || !clazz.isArray()) {
                return obj;
            }
            JSONArray jArr = this.f415T.getJSONArray(key);
            Class componentType = clazz.getComponentType();
            Object[] arr = (Object[]) Array.newInstance(componentType, jArr.length());
            int len = jArr.length();
            for (int i = 0; i < len; i++) {
                if (componentType == Double.class) {
                    arr[i] = Double.valueOf(jArr.getDouble(i));
                } else if (componentType == Float.class) {
                    arr[i] = Float.valueOf((float) jArr.getDouble(i));
                } else if (componentType == Integer.class) {
                    arr[i] = Integer.valueOf(jArr.getInt(i));
                } else if (componentType == Long.class) {
                    arr[i] = Long.valueOf(jArr.getLong(i));
                } else {
                    arr[i] = jArr.get(i);
                }
            }
            return arr;
        } catch (Exception e) {
            e.printStackTrace();
            return def;
        }
    }

    public JSONObject Tr() {
        return this.f415T;
    }

    public String toString() {
        return this.f415T.toString();
    }

    public byte[] Ty() {
        return toString().getBytes();
    }
}
