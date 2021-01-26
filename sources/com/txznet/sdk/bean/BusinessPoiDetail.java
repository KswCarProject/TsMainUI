package com.txznet.sdk.bean;

import com.txznet.comm.Ty.Tr;

/* compiled from: Proguard */
public class BusinessPoiDetail extends PoiDetail {

    /* renamed from: T  reason: collision with root package name */
    String f863T;
    boolean T5;
    int T6;
    double T9;
    double TE;
    boolean TF;
    double TZ;
    double Te;
    boolean Th;
    double Tk;
    String[] Tn;
    boolean Tq;
    String Tr;
    int Tv;
    String[] Ty;

    public BusinessPoiDetail() {
        setPoiType(2);
    }

    public String getBranchName() {
        return this.f863T;
    }

    public String getPhotoUrl() {
        return this.Tr;
    }

    public String[] getRegions() {
        return this.Ty;
    }

    public String[] getCategories() {
        return this.Tn;
    }

    public double getScore() {
        return this.T9;
    }

    public double getScoreProduct() {
        return this.Tk;
    }

    public double getScoreDecoration() {
        return this.TZ;
    }

    public double getScoreService() {
        return this.TE;
    }

    public boolean isHasDeal() {
        return this.T5;
    }

    public int getDealCount() {
        return this.Tv;
    }

    public boolean isHasCoupon() {
        return this.Th;
    }

    public int getReviewCount() {
        return this.T6;
    }

    public double getAvgPrice() {
        return this.Te;
    }

    public boolean isHasWifi() {
        return this.Tq;
    }

    public boolean isHasPark() {
        return this.Tq;
    }

    public BusinessPoiDetail setBranchName(String branchName) {
        this.f863T = branchName;
        return this;
    }

    public BusinessPoiDetail setPhotoUrl(String photoUrl) {
        this.Tr = photoUrl;
        return this;
    }

    public BusinessPoiDetail setRegions(String[] regions) {
        this.Ty = regions;
        return this;
    }

    public BusinessPoiDetail setCategories(String[] categories) {
        this.Tn = categories;
        return this;
    }

    public BusinessPoiDetail setScore(float score) {
        this.T9 = (double) score;
        return this;
    }

    public BusinessPoiDetail setScoreProduct(float scoreProduct) {
        this.Tk = (double) scoreProduct;
        return this;
    }

    public BusinessPoiDetail setScoreDecoration(float scoreDecoration) {
        this.TZ = (double) scoreDecoration;
        return this;
    }

    public BusinessPoiDetail setScoreService(float scoreService) {
        this.TE = (double) scoreService;
        return this;
    }

    public BusinessPoiDetail setHasDeal(boolean hasDeal) {
        this.T5 = hasDeal;
        return this;
    }

    public BusinessPoiDetail setDealCount(int dealCount) {
        this.Tv = dealCount;
        return this;
    }

    public BusinessPoiDetail setHasCoupon(boolean hasCoupon) {
        this.Th = hasCoupon;
        return this;
    }

    public BusinessPoiDetail setReviewCount(int reviewCount) {
        this.T6 = reviewCount;
        return this;
    }

    public BusinessPoiDetail setAvgPrice(float avgPrice) {
        this.Te = (double) avgPrice;
        return this;
    }

    public BusinessPoiDetail setHasWifi(boolean hasWifi) {
        this.Tq = hasWifi;
        return this;
    }

    public BusinessPoiDetail setHasPark(boolean hasPark) {
        this.TF = hasPark;
        return this;
    }

    public BusinessPoiDetail setTelephone(String telephone) {
        this.T0 = telephone;
        return this;
    }

    public BusinessPoiDetail setProvince(String province) {
        this.Tx = province;
        return this;
    }

    public BusinessPoiDetail setPostcode(String postcode) {
        this.TV = postcode;
        return this;
    }

    public BusinessPoiDetail setDistance(int distance) {
        this.TK = distance;
        return this;
    }

    public BusinessPoiDetail setWebsite(String website) {
        this.Tb = website;
        return this;
    }

    public BusinessPoiDetail setLat(double lat) {
        super.setLat(lat);
        return this;
    }

    public BusinessPoiDetail setLng(double lng) {
        super.setLng(lng);
        return this;
    }

    public BusinessPoiDetail setName(String name) {
        super.setName(name);
        return this;
    }

    public BusinessPoiDetail setCity(String city) {
        super.setCity(city);
        return this;
    }

    public BusinessPoiDetail setGeoinfo(String geoinfo) {
        super.setGeoinfo(geoinfo);
        return this;
    }

    public BusinessPoiDetail setAlias(String[] alias) {
        this.TG = alias;
        return this;
    }

    public BusinessPoiDetail setSourceType(int source) {
        super.setSourceType(source);
        return this;
    }

    /* access modifiers changed from: protected */
    public Tr T() {
        Tr json = super.T();
        json.T("avgPrice", (Object) Double.valueOf(this.Te));
        json.T("branchName", (Object) this.f863T);
        json.T("categories", (Object) this.Tn);
        json.T("dealCount", (Object) Integer.valueOf(this.Tv));
        json.T("hasCoupon", (Object) Boolean.valueOf(this.Th));
        json.T("hasDeal", (Object) Boolean.valueOf(this.T5));
        json.T("hasPark", (Object) Boolean.valueOf(this.TF));
        json.T("hasWifi", (Object) Boolean.valueOf(this.Tq));
        json.T("photoUrl", (Object) this.Tr);
        json.T("regions", (Object) this.Ty);
        json.T("reviewCount", (Object) Integer.valueOf(this.T6));
        json.T("score", (Object) Double.valueOf(this.T9));
        json.T("scoreDecoration", (Object) Double.valueOf(this.TZ));
        json.T("scoreProduct", (Object) Double.valueOf(this.Tk));
        json.T("scoreService", (Object) Double.valueOf(this.TE));
        return json;
    }

    public String toString() {
        return T().toString();
    }

    /* access modifiers changed from: protected */
    public void T(Tr json) {
        super.T(json);
        this.Te = ((Double) json.T("avgPrice", Double.class, Double.valueOf(0.0d))).doubleValue();
        this.f863T = (String) json.T("branchName", String.class);
        this.Tn = (String[]) json.T("categories", String[].class);
        this.Tv = ((Integer) json.T("dealCount", Integer.class, 0)).intValue();
        this.Th = ((Boolean) json.T("hasCoupon", Boolean.class, false)).booleanValue();
        this.T5 = ((Boolean) json.T("hasDeal", Boolean.class, false)).booleanValue();
        this.TF = ((Boolean) json.T("hasPark", Boolean.class, false)).booleanValue();
        this.Tq = ((Boolean) json.T("hasWifi", Boolean.class, false)).booleanValue();
        this.Tr = (String) json.T("photoUrl", String.class);
        this.Ty = (String[]) json.T("regions", String[].class);
        this.T6 = ((Integer) json.T("reviewCount", Integer.class, 0)).intValue();
        this.T9 = ((Double) json.T("score", Double.class, Double.valueOf(0.0d))).doubleValue();
        this.TZ = ((Double) json.T("scoreDecoration", Double.class, Double.valueOf(0.0d))).doubleValue();
        this.Tk = ((Double) json.T("scoreProduct", Double.class, Double.valueOf(0.0d))).doubleValue();
        this.TE = ((Double) json.T("scoreService", Double.class, Double.valueOf(0.0d))).doubleValue();
    }

    public static BusinessPoiDetail fromString(String data) {
        BusinessPoiDetail p = new BusinessPoiDetail();
        p.T(new Tr(data));
        return p;
    }
}
