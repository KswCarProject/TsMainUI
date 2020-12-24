package com.txznet.sdk.bean;

import com.txznet.comm.Tr.Tr.Ty;
import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.tongting.IConstantData;
import org.json.JSONObject;

/* compiled from: Proguard */
public class Poi {
    public static final int POI_SOURCE_BAIDU_IMPL = 6;
    public static final int POI_SOURCE_BAIDU_LOCAL = 7;
    public static final int POI_SOURCE_BAIDU_WEB = 8;
    public static final int POI_SOURCE_DZDP = 2;
    public static final int POI_SOURCE_GAODE_IMPL = 3;
    public static final int POI_SOURCE_GAODE_LOCAL = 4;
    public static final int POI_SOURCE_GAODE_WEB = 5;
    public static final int POI_SOURCE_KAILIDE = 9;
    public static final int POI_SOURCE_MEIXING = 11;
    public static final int POI_SOURCE_QIHOO = 12;
    public static final int POI_SOURCE_TENCENT = 10;
    public static final int POI_SOURCE_TXZ = 1;
    public static final int POI_SOURCE_TXZ_BEIJIXING = 15;
    public static final int POI_SOURCE_TXZ_DZDP = 14;
    public static final int POI_SOURCE_TXZ_POI = 13;
    public static final int POI_TYPE_BUSINESS = 2;
    public static final int POI_TYPE_POIDEATAIL = 1;
    public static final int POI_TYPE_TXZ = 3;

    /* renamed from: T  reason: collision with root package name */
    private static double f865T = 52.35987755982988d;
    int TA = 1;
    double TB;
    CoordType TD = CoordType.GCJ02;
    String[] TG;
    int TK;
    String TN;
    String TO;
    int Tf = 1;
    double Tj;
    String Ts;
    String Tt;
    String Tu;

    /* compiled from: Proguard */
    public enum CoordType {
        BAIDU,
        GCJ02
    }

    /* compiled from: Proguard */
    public static class PoiAction {
        public static final String ACTION_AUDIO = "audio";
        public static final String ACTION_COMPANY = "setCompany";
        public static final String ACTION_DEL_JINGYOU = "delJingYou";
        public static final String ACTION_HOME = "setHome";
        public static final String ACTION_HUIBI = "setHuiBi";
        public static final String ACTION_JINGYOU = "setJingYou";
        public static final String ACTION_NAVI = "nav";
        public static final String ACTION_NAVI_END = "navEnd";
        public static final String ACTION_NAV_HISTORY = "nav_history";
        public static final String ACTION_NAV_RECOMMAND = "nav_recommand";
        public static final String ACTION_RECOMM_COMPANY = "recomm_company";
        public static final String ACTION_RECOMM_HOME = "recomm_home";
    }

    public double getLat() {
        if (this.TD != CoordType.BAIDU) {
            return this.Tj;
        }
        double[] orgLatLng = Ty.T(this.Tj, this.TB);
        if (orgLatLng == null) {
            return this.Tj;
        }
        return orgLatLng[0];
    }

    public Poi setLat(double lat) {
        this.Tj = lat;
        return this;
    }

    public double getLng() {
        if (this.TD != CoordType.BAIDU) {
            return this.TB;
        }
        double[] orgLatLng = Ty.T(this.Tj, this.TB);
        if (orgLatLng == null) {
            return this.TB;
        }
        return orgLatLng[1];
    }

    public double getOriginalLat() {
        return this.Tj;
    }

    public double getOriginalLng() {
        return this.TB;
    }

    public Poi setLng(double lng) {
        this.TB = lng;
        return this;
    }

    public int getDistance() {
        return this.TK;
    }

    public Poi setDistance(int distance) {
        this.TK = distance;
        return this;
    }

    public String getName() {
        return this.TO;
    }

    public Poi setName(String name) {
        this.TO = name;
        return this;
    }

    public String getCity() {
        return this.TN;
    }

    public Poi setCity(String city) {
        this.TN = city;
        return this;
    }

    public String getGeoinfo() {
        return this.Ts;
    }

    public Poi setGeoinfo(String geoinfo) {
        this.Ts = geoinfo;
        return this;
    }

    public String[] getAlias() {
        return this.TG;
    }

    public Poi setAlias(String[] alias) {
        this.TG = alias;
        return this;
    }

    public String getAction() {
        return this.Tu;
    }

    public Poi setAction(String action) {
        this.Tu = action;
        return this;
    }

    public String getExtraStr() {
        return this.Tt;
    }

    public Poi setExtraStr(String extra) {
        this.Tt = extra;
        return this;
    }

    public Poi setCoordType(CoordType type) {
        this.TD = type;
        return this;
    }

    public CoordType getType() {
        return this.TD;
    }

    public int getSourceType() {
        return this.Tf;
    }

    public Poi setSourceType(int source) {
        this.Tf = source;
        return this;
    }

    public int getPoiType() {
        return this.TA;
    }

    public Poi setPoiType(int type) {
        this.TA = type;
        return this;
    }

    /* access modifiers changed from: protected */
    public Tr T() {
        Tr json = new Tr();
        json.T("lat", (Object) Double.valueOf(getLat()));
        json.T("lng", (Object) Double.valueOf(getLng()));
        json.T("city", (Object) getCity());
        json.T(IConstantData.KEY_NAME, (Object) getName());
        json.T("geo", (Object) getGeoinfo());
        json.T("distance", (Object) Integer.valueOf(getDistance()));
        json.T("action", (Object) getAction());
        json.T("coordtype", (Object) getType());
        json.T("extre", (Object) getExtraStr());
        json.T(IConstantData.KEY_SOURCE_FROM, (Object) Integer.valueOf(getSourceType()));
        json.T("poitype", (Object) Integer.valueOf(this.TA));
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
        this.Tj = ((Double) json.T("lat", Double.class, Double.valueOf(0.0d))).doubleValue();
        this.TB = ((Double) json.T("lng", Double.class, Double.valueOf(0.0d))).doubleValue();
        this.TN = (String) json.T("city", String.class);
        this.TO = (String) json.T(IConstantData.KEY_NAME, String.class);
        this.Ts = (String) json.T("geo", String.class);
        this.TK = ((Integer) json.T("distance", Integer.class, 0)).intValue();
        this.Tu = (String) json.T("action", String.class);
        String type = (String) json.T("coordtype", String.class);
        this.Tf = ((Integer) json.T(IConstantData.KEY_SOURCE_FROM, Integer.class, 1)).intValue();
        this.Tt = (String) json.T("extre", String.class);
        this.TA = ((Integer) json.T("poitype", Integer.class, 1)).intValue();
        if (type == null || type.equals("")) {
            this.TD = CoordType.GCJ02;
        } else if (type.equals("BAIDU")) {
            this.TD = CoordType.BAIDU;
        } else {
            this.TD = CoordType.GCJ02;
        }
    }

    public static Poi fromString(String data) {
        Poi p = new Poi();
        p.T(new Tr(data));
        return p;
    }

    public static double[] Convert_BD09_To_GCJ02(double lat, double lng) {
        double x = lng - 0.0065d;
        double y = lat - 0.006d;
        double z = Math.sqrt((x * x) + (y * y)) - (2.0E-5d * Math.sin(f865T * y));
        double theta = Math.atan2(y, x) - (3.0E-6d * Math.cos(f865T * x));
        return new double[]{z * Math.sin(theta), z * Math.cos(theta)};
    }
}
