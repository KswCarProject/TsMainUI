package com.txznet.comm.Ty;

import java.lang.reflect.Array;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
public class Tr {

    /* renamed from: T  reason: collision with root package name */
    private JSONObject f411T;

    public Tr() {
        this.f411T = new JSONObject();
    }

    public Tr(byte[] data) {
        if (data == null) {
            this.f411T = new JSONObject();
            return;
        }
        try {
            this.f411T = new JSONObject(new String(data));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Tr(String data) {
        if (data == null) {
            this.f411T = new JSONObject();
            return;
        }
        try {
            this.f411T = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Tr(JSONObject json) {
        this.f411T = json;
    }

    public JSONObject T() {
        return this.f411T;
    }

    public Tr T(String key, Object val) {
        if (this.f411T == null) {
            this.f411T = new JSONObject();
        }
        if (val != null) {
            try {
                if (val.getClass().isArray()) {
                    JSONArray jArr = new JSONArray();
                    for (Object obj : (Object[]) val) {
                        jArr.put(obj);
                    }
                    this.f411T.put(key, jArr);
                } else if (val instanceof Collection) {
                    this.f411T.put(key, new JSONArray((Collection) val));
                } else {
                    this.f411T.put(key, val);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public Tr T(String key) {
        this.f411T.remove(key);
        return this;
    }

    public <T> T T(String key, Class<T> clazz) {
        return T(key, clazz, (Object) null);
    }

    public <T> T T(String key, Class<T> clazz, T def) {
        if (this.f411T == null || !this.f411T.has(key)) {
            return def;
        }
        try {
            T obj = this.f411T.get(key);
            if (JSONObject.NULL.equals(obj)) {
                return def;
            }
            if (clazz == Double.class) {
                return new Double(this.f411T.getDouble(key));
            }
            if (clazz == Float.class) {
                return new Float(this.f411T.getDouble(key));
            }
            if (clazz == Integer.class) {
                return new Integer(this.f411T.getInt(key));
            }
            if (clazz == Long.class) {
                return new Long(this.f411T.getLong(key));
            }
            if (!(obj instanceof JSONArray) || !clazz.isArray()) {
                return obj;
            }
            JSONArray jArr = this.f411T.getJSONArray(key);
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
        return this.f411T;
    }

    public String toString() {
        return this.f411T.toString();
    }

    public byte[] Ty() {
        return toString().getBytes();
    }
}
