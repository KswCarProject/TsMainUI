package com.txznet.sdk.bean;

import com.txznet.comm.Ty.Tr;

/* compiled from: Proguard */
public class PoiDetail extends Poi {
    String T0;
    String TV;
    String Tb;
    String Tx;

    public PoiDetail() {
        setPoiType(1);
    }

    public String getTelephone() {
        return this.T0;
    }

    public PoiDetail setTelephone(String telephone) {
        this.T0 = telephone;
        return this;
    }

    public String getProvince() {
        return this.Tx;
    }

    public PoiDetail setProvince(String province) {
        this.Tx = province;
        return this;
    }

    public String getPostcode() {
        return this.TV;
    }

    public PoiDetail setPostcode(String postcode) {
        this.TV = postcode;
        return this;
    }

    public PoiDetail setDistance(int distance) {
        super.setDistance(distance);
        return this;
    }

    public String getWebsite() {
        return this.Tb;
    }

    public PoiDetail setWebsite(String website) {
        this.Tb = website;
        return this;
    }

    public PoiDetail setLat(double lat) {
        super.setLat(lat);
        return this;
    }

    public PoiDetail setLng(double lng) {
        super.setLng(lng);
        return this;
    }

    public PoiDetail setName(String name) {
        super.setName(name);
        return this;
    }

    public PoiDetail setCity(String city) {
        super.setCity(city);
        return this;
    }

    public PoiDetail setGeoinfo(String geoinfo) {
        super.setGeoinfo(geoinfo);
        return this;
    }

    public PoiDetail setAlias(String[] alias) {
        this.TG = alias;
        return this;
    }

    public PoiDetail setSourceType(int source) {
        super.setSourceType(source);
        return this;
    }

    /* access modifiers changed from: protected */
    public Tr T() {
        Tr json = super.T();
        json.T("province", (Object) this.Tx);
        json.T("postcode", (Object) this.TV);
        json.T("telephone", (Object) this.T0);
        json.T("website", (Object) this.Tb);
        return json;
    }

    public String toString() {
        return T().toString();
    }

    /* access modifiers changed from: protected */
    public void T(Tr json) {
        super.T(json);
        this.Tx = (String) json.T("province", String.class);
        this.TV = (String) json.T("postcode", String.class);
        this.T0 = (String) json.T("telephone", String.class);
        this.Tb = (String) json.T("website", String.class);
    }

    public static PoiDetail fromString(String data) {
        PoiDetail p = new PoiDetail();
        p.T(new Tr(data));
        return p;
    }
}
