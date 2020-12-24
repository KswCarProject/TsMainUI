package com.txznet.sdk.bean;

import com.txznet.comm.Ty.Tr;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
public class NaviLatLng {

    /* renamed from: T  reason: collision with root package name */
    private double f864T;
    private double Tr;

    public double getLatitude() {
        return this.f864T;
    }

    public void setLatitude(double latitude) {
        this.f864T = latitude;
    }

    public double getLongitude() {
        return this.Tr;
    }

    public void setLongitude(double longitude) {
        this.Tr = longitude;
    }

    /* access modifiers changed from: protected */
    public Tr T() {
        Tr json = new Tr();
        json.T("mLatitude", (Object) Double.valueOf(this.f864T));
        json.T("mLongitude", (Object) Double.valueOf(this.Tr));
        return json;
    }

    public String toString() {
        return T().toString();
    }

    public JSONObject toJsonObject() {
        return T().Tr();
    }

    /* access modifiers changed from: protected */
    public void T(Tr json) {
        this.f864T = ((Double) json.T("mLatitude", Double.class, Double.valueOf(0.0d))).doubleValue();
        this.Tr = ((Double) json.T("mLongitude", Double.class, Double.valueOf(0.0d))).doubleValue();
    }

    public static NaviLatLng fromString(String data) {
        NaviLatLng naviLatLng = new NaviLatLng();
        naviLatLng.T(new Tr(data));
        return naviLatLng;
    }

    public String toJson() {
        JSONObject jo = new JSONObject();
        try {
            jo.put("mLatitude", this.f864T);
            jo.put("mLongitude", this.Tr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jo.toString();
    }
}
