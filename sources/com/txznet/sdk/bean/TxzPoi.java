package com.txznet.sdk.bean;

import android.text.TextUtils;
import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.tongting.IConstantData;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Proguard */
public class TxzPoi extends Poi {

    /* renamed from: T  reason: collision with root package name */
    String f867T;
    int T9;
    String[] Tn;
    int Tr;
    GeoDetail Ty;
    public boolean isTop;

    public TxzPoi() {
        setPoiType(3);
    }

    /* compiled from: Proguard */
    public static class GeoDetail {
        public String area;
        public String building;
        public String country;
        public String number;
        public String province;
        public String room;
        public String street;
        public String town;

        public static GeoDetail creatGeoDetail(String string) {
            JSONObject jo;
            GeoDetail gd = new GeoDetail();
            if (!TextUtils.isEmpty(string) && (jo = new Tr(string).Tr()) != null) {
                try {
                    if (jo.has("country")) {
                        gd.country = jo.getString("country");
                    }
                    if (jo.has("province")) {
                        gd.province = jo.getString("province");
                    }
                    if (jo.has("town")) {
                        gd.town = jo.getString("town");
                    }
                    if (jo.has("area")) {
                        gd.area = jo.getString("area");
                    }
                    if (jo.has("street")) {
                        gd.street = jo.getString("street");
                    }
                    if (jo.has("number")) {
                        gd.number = jo.getString("number");
                    }
                    if (jo.has("building")) {
                        gd.building = jo.getString("building");
                    }
                    if (jo.has("room")) {
                        gd.room = jo.getString("room");
                    }
                } catch (Exception e) {
                }
            }
            return gd;
        }

        public String toString() {
            Tr json = new Tr();
            json.T("country", (Object) this.country);
            json.T("province", (Object) this.province);
            json.T("town", (Object) this.town);
            json.T("area", (Object) this.area);
            json.T("street", (Object) this.street);
            json.T("number", (Object) this.number);
            json.T("building", (Object) this.building);
            json.T("room", (Object) this.room);
            return json.toString();
        }
    }

    public String[] getKeyWords() {
        return this.Tn;
    }

    public void setKeyWords(String[] keyWords) {
        this.Tn = keyWords;
    }

    public int getPoiShowType() {
        return this.T9;
    }

    public void setPoiShowType(int poiType) {
        this.T9 = poiType;
    }

    public boolean isTop() {
        return this.isTop;
    }

    public void setTop(boolean isTop2) {
        this.isTop = isTop2;
    }

    public GeoDetail getGeoDetail() {
        return this.Ty;
    }

    public void setGeoDetail(GeoDetail geoDetail) {
        this.Ty = geoDetail;
    }

    public int getHot() {
        return this.Tr;
    }

    public void setHot(int hot) {
        this.Tr = hot;
    }

    public String getLogo() {
        return this.f867T;
    }

    public void setLogo(String logo) {
        this.f867T = logo;
    }

    public static ArrayList<Poi> getCompanyPoiForJson(String jsonResult) {
        TxzPoi poi;
        if (TextUtils.isEmpty(jsonResult)) {
            return null;
        }
        ArrayList<Poi> pois = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonResult);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                if (!(json == null || (poi = creatCompanyPoi(json)) == null)) {
                    pois.add(poi);
                }
            }
            return pois;
        } catch (Exception e) {
            return null;
        }
    }

    public String toString() {
        Tr json = super.T();
        json.T("geo", (Object) getGeoinfo());
        json.T("top", (Object) Boolean.valueOf(isTop()));
        json.T("hot", (Object) Integer.valueOf(getHot()));
        json.T(IConstantData.KEY_LOGO, (Object) getLogo());
        json.T("detail", (Object) getGeoDetail() != null ? getGeoDetail().toString() : null);
        return json.toString();
    }

    public static TxzPoi fromString(String str) {
        Tr json = new Tr(str);
        TxzPoi p = new TxzPoi();
        p.T(json);
        p.setTop(((Boolean) json.T("top", Boolean.class, false)).booleanValue());
        p.setHot(((Integer) json.T("hot", Integer.class, 0)).intValue());
        p.setLogo((String) json.T(IConstantData.KEY_LOGO, String.class, ""));
        p.setGeoDetail(GeoDetail.creatGeoDetail((String) json.T("detail", String.class, "")));
        return p;
    }

    public static TxzPoi creatCompanyPoi(JSONObject jo) {
        TxzPoi po = new TxzPoi();
        try {
            if (!jo.has("lng") || jo.isNull("lng")) {
                return null;
            }
            po.setLng(jo.getDouble("lng"));
            if (!jo.has("lat") || jo.isNull("lat")) {
                return null;
            }
            po.setLat(jo.getDouble("lat"));
            if (!jo.has(IConstantData.KEY_NAME) || jo.isNull(IConstantData.KEY_NAME)) {
                return null;
            }
            po.setName(jo.getString(IConstantData.KEY_NAME));
            if (!jo.has("geo") || jo.isNull("geo")) {
                return null;
            }
            po.setGeoinfo(jo.getString("geo"));
            if (jo.has("city") && !jo.isNull("city")) {
                po.setCity(jo.getString("city"));
            }
            if (jo.has(IConstantData.KEY_LOGO) && !jo.isNull(IConstantData.KEY_LOGO)) {
                po.setLogo(jo.getString(IConstantData.KEY_LOGO));
            }
            if (jo.has("hot") && !jo.isNull("hot")) {
                po.setHot(jo.getInt("hot"));
            }
            if (jo.has("detail") && !jo.isNull("detail")) {
                po.setGeoDetail(GeoDetail.creatGeoDetail(jo.getString("detail")));
            }
            if (!jo.has("top") || jo.isNull("top")) {
                return po;
            }
            po.setTop(jo.getBoolean("top"));
            return po;
        } catch (Exception e) {
            return null;
        }
    }
}
