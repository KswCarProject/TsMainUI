package com.txznet.sdk;

import com.txznet.comm.Tr.Tn;
import com.txznet.sdk.bean.Poi;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
public class TXZPoiSearchManager {
    public static final int DEFAULT_NEARBY_RADIUS = 3000;
    public static final int DEFAULT_SEARCH_AMOUNT = 10;
    public static final int DEFAULT_SEARCH_TIMEOUT = 10000;
    public static final int ERROR_CODE_EMPTY = 2;
    public static final int ERROR_CODE_TIMEOUT = 3;
    public static final int ERROR_CODE_UNKNOW = 1;

    /* renamed from: T  reason: collision with root package name */
    private static TXZPoiSearchManager f781T = new TXZPoiSearchManager();
    private Boolean T9 = null;
    private Boolean TZ = null;
    private Boolean Tk = null;
    private Boolean Tn = null;
    private boolean Tr = false;
    private Object Ty = null;

    /* compiled from: Proguard */
    public static class PoiDisplayStyle {
        public boolean mPoiResultDisplayWinRecord = true;
        public boolean mShowQRCodeWhenNoResult = true;
    }

    /* compiled from: Proguard */
    public interface PoiSearchResultListener {
        void onError(int i, String str);

        void onResult(List<Poi> list);

        void onSuggestion(SearchPoiSuggestion searchPoiSuggestion);
    }

    /* compiled from: Proguard */
    public interface PoiSearchTool {
        int getPoiSearchType();

        SearchReq searchInCity(CityPoiSearchOption cityPoiSearchOption, PoiSearchResultListener poiSearchResultListener);

        SearchReq searchNearby(NearbyPoiSearchOption nearbyPoiSearchOption, PoiSearchResultListener poiSearchResultListener);

        void stopPoiSearchTool(int i);
    }

    /* compiled from: Proguard */
    public enum PoiSearchToolType {
        TXZ,
        QIHOO
    }

    /* compiled from: Proguard */
    public interface SearchReq {
        void cancel();
    }

    private TXZPoiSearchManager() {
    }

    public static TXZPoiSearchManager getInstance() {
        return f781T;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        if (this.Tr) {
            if (this.Ty == null) {
                setPoiSearchTool((PoiSearchToolType) null);
            } else if (this.Ty instanceof PoiSearchToolType) {
                setPoiSearchTool((PoiSearchToolType) this.Ty);
            } else if (this.Ty instanceof PoiSearchTool) {
                T((PoiSearchTool) this.Ty);
            }
        }
        if (this.Tn != null) {
            setPoiSearchResultList(this.Tn.booleanValue());
        }
        if (this.T9 != null) {
            setMapPoiViewEnable(this.T9.booleanValue());
        }
        if (this.Tk != null) {
            setGaoDeAutoPlanningRoute(this.Tk.booleanValue());
        }
        if (this.TZ != null) {
            setPoiPlayTipTts(this.TZ.booleanValue());
        }
    }

    /* compiled from: Proguard */
    public static class SearchPoiSuggestion {

        /* renamed from: T  reason: collision with root package name */
        List<String> f787T;
        List<String> Tr;

        public SearchPoiSuggestion setCity(List<String> city) {
            this.f787T = city;
            return this;
        }

        public SearchPoiSuggestion setKeywrods(List<String> keywrods) {
            this.Tr = keywrods;
            return this;
        }

        public List<String> getCity() {
            return this.f787T;
        }

        public List<String> getKeywrods() {
            return this.Tr;
        }
    }

    /* compiled from: Proguard */
    public static class PoiSearchInfo {

        /* renamed from: T  reason: collision with root package name */
        boolean f784T = true;
        int Tn = 1;
        int Tr = -4098;
        int Ty = 0;

        public boolean isTxzPoiToolComplete() {
            return this.f784T;
        }

        public void setTxzPoiToolComplete(boolean txzPoiToolComplete) {
            this.f784T = txzPoiToolComplete;
        }

        public int getPoiSourceConf() {
            return this.Tr;
        }

        public int getPoiRetryCount() {
            return this.Tn;
        }

        public void setPoiRetryCount(int poiRetryCount) {
            this.Tn = poiRetryCount;
        }

        public void setPoiSourceConf(int poiSourceConf) {
            this.Tr = poiSourceConf;
        }

        public int getDisShowEngine() {
            return this.Ty;
        }

        public void setDisShowEngine(int disShowEngine) {
            this.Ty = disShowEngine;
        }
    }

    /* compiled from: Proguard */
    public static class PoiSearchOption {

        /* renamed from: T  reason: collision with root package name */
        private PoiSearchInfo f785T = new PoiSearchInfo();
        protected int T5 = TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT;
        protected String TE;
        protected int TZ = 10;

        public PoiSearchOption setTimeout(int timeout) {
            this.T5 = timeout;
            return this;
        }

        public int getTimeout() {
            return this.T5;
        }

        public String getKeywords() {
            return this.TE;
        }

        public PoiSearchOption setKeywords(String keywords) {
            this.TE = keywords;
            return this;
        }

        public int getNum() {
            return this.TZ;
        }

        public PoiSearchOption setNum(int num) {
            this.TZ = num;
            return this;
        }

        public PoiSearchInfo getSearchInfo() {
            return this.f785T;
        }

        public PoiSearchInfo setSearchInfo(PoiSearchInfo info) {
            this.f785T = info;
            return info;
        }
    }

    /* compiled from: Proguard */
    public static class CityPoiSearchOption extends PoiSearchOption {
        protected String T9;
        protected String Tk;

        public CityPoiSearchOption setTimeout(int timeout) {
            super.setTimeout(timeout);
            return this;
        }

        public String getCity() {
            return this.T9;
        }

        public CityPoiSearchOption setCity(String city) {
            this.T9 = city;
            return this;
        }

        public String getRegion() {
            return this.Tk;
        }

        public CityPoiSearchOption setRegion(String region) {
            this.Tk = region;
            return this;
        }

        public CityPoiSearchOption setKeywords(String keywords) {
            super.setKeywords(keywords);
            return this;
        }

        public CityPoiSearchOption setNum(int num) {
            super.setNum(num);
            return this;
        }
    }

    /* compiled from: Proguard */
    public static class NearbyPoiSearchOption extends CityPoiSearchOption {

        /* renamed from: T  reason: collision with root package name */
        protected double f783T;
        protected double Tr;
        protected int Ty = -1;

        public CityPoiSearchOption setTimeout(int timeout) {
            super.setTimeout(timeout);
            return this;
        }

        public int getRadius() {
            return this.Ty;
        }

        public NearbyPoiSearchOption setRadius(int radius) {
            this.Ty = radius;
            return this;
        }

        public double getCenterLat() {
            return this.f783T;
        }

        public NearbyPoiSearchOption setCenterLat(double lat) {
            this.f783T = lat;
            return this;
        }

        public double getCenterLng() {
            return this.Tr;
        }

        public NearbyPoiSearchOption setCenterLng(double lng) {
            this.Tr = lng;
            return this;
        }

        public NearbyPoiSearchOption setKeywords(String keywords) {
            super.setKeywords(keywords);
            return this;
        }

        public NearbyPoiSearchOption setNum(int num) {
            super.setNum(num);
            return this;
        }

        public NearbyPoiSearchOption setCity(String city) {
            super.setCity(city);
            return this;
        }
    }

    /* compiled from: Proguard */
    public static class BoundPoiSearchOption extends CityPoiSearchOption {

        /* renamed from: T  reason: collision with root package name */
        protected double f782T;
        protected double Tn;
        protected double Tr;
        protected double Ty;

        public double getMinLat() {
            return this.f782T;
        }

        public BoundPoiSearchOption setMinLat(double minLat) {
            this.f782T = minLat;
            return this;
        }

        public double getMaxLat() {
            return this.Tr;
        }

        public BoundPoiSearchOption setMaxLat(double maxLat) {
            this.Tr = maxLat;
            return this;
        }

        public double getMinLng() {
            return this.Ty;
        }

        public BoundPoiSearchOption setMinLng(double minLng) {
            this.Ty = minLng;
            return this;
        }

        public double getMaxLng() {
            return this.Tn;
        }

        public BoundPoiSearchOption setMaxLng(double maxLng) {
            this.Tn = maxLng;
            return this;
        }

        public BoundPoiSearchOption setKeywords(String keywords) {
            super.setKeywords(keywords);
            return this;
        }

        public BoundPoiSearchOption setNum(int num) {
            super.setNum(num);
            return this;
        }

        public BoundPoiSearchOption setCity(String city) {
            super.setCity(city);
            return this;
        }
    }

    /* access modifiers changed from: package-private */
    public void T(PoiSearchTool tool) {
        this.Tr = true;
        this.Ty = tool;
    }

    public void setPoiSearchTool(PoiSearchToolType type) {
        this.Tr = true;
        this.Ty = type;
        if (type == null) {
            Tn.Tr().T("com.txznet.txz", "txz.poi.cleartool", (byte[]) null, (Tn.Tr) null);
        } else {
            Tn.Tr().T("com.txznet.txz", "txz.poi.setInnerTool", type.name().getBytes(), (Tn.Tr) null);
        }
    }

    public void setPoiSearchResultList(boolean isList) {
        this.Tn = Boolean.valueOf(isList);
        JSONObject json = new JSONObject();
        try {
            json.put("isList", isList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Tn.Tr().T("com.txznet.txz", "txz.poi.setShowModel", json.toString().getBytes(), (Tn.Tr) null);
    }

    public void setMapPoiViewEnable(boolean isEnable) {
        this.T9 = Boolean.valueOf(isEnable);
        JSONObject json = new JSONObject();
        try {
            json.put("isEnable", isEnable);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Tn.Tr().T("com.txznet.txz", "txz.poi.stopMapPoiViewModle", json.toString().getBytes(), (Tn.Tr) null);
    }

    public void setGaoDeAutoPlanningRoute(boolean isPlanning) {
        this.Tk = Boolean.valueOf(isPlanning);
        JSONObject json = new JSONObject();
        try {
            json.put("isPlanning", isPlanning);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Tn.Tr().T("com.txznet.txz", "txz.poi.setGaoDeAutoPlanningRoute", json.toString().getBytes(), (Tn.Tr) null);
    }

    public void setPoiPlayTipTts(boolean isPlayPoiTip) {
        this.TZ = Boolean.valueOf(isPlayPoiTip);
        JSONObject json = new JSONObject();
        try {
            json.put("isPlayPoiTip", isPlayPoiTip);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Tn.Tr().T("com.txznet.txz", "txz.poi.setPoiPlayTipTts", json.toString().getBytes(), (Tn.Tr) null);
    }

    public void navNearbyPoint() {
        com.txznet.comm.Tr.Tr.Tn.T("TXZPoiSearchManager navNearbyPoint");
        Tn.Tr().T("com.txznet.txz", "txz.poi.nearbyPoint", (byte[]) null, (Tn.Tr) null);
    }
}
