package com.txznet.sdk.bean;

import android.text.TextUtils;
import com.android.SdkConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
public class PoiDeepInfo {
    public static final int DEEPINFO_CATEGORY_HOTEL = 2;
    public static final int DEEPINFO_CATEGORY_PARKING = 0;
    public static final int DEEPINFO_CATEGORY_STATION = 1;
    public int category;
    public List<String> feature;
    public String tag;
    public Object tagInfo;

    /* compiled from: Proguard */
    public static class PackingInfo {
        public String parkinginfo;
        public String priceInfo;

        public static PackingInfo parseFromString(JSONObject info) {
            try {
                JSONObject js = new JSONObject(info.getString("taginfo"));
                PackingInfo packingInfo = new PackingInfo();
                if (js.has("priceinfo")) {
                    packingInfo.priceInfo = js.getString("priceinfo ");
                }
                if (!js.has("parkinginfo")) {
                    return packingInfo;
                }
                packingInfo.parkinginfo = js.getString("parkinginfo");
                return packingInfo;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /* compiled from: Proguard */
    public static class StationInfo {
        public String price;
        public String pricetag;
        public String type;

        public static List<StationInfo> parseFromString(JSONObject info) {
            try {
                JSONArray js = new JSONArray(info.getString("taginfo"));
                List<StationInfo> result = new ArrayList<>();
                for (int i = 0; i < js.length(); i++) {
                    if (!js.isNull(i)) {
                        JSONObject json = js.getJSONObject(i);
                        StationInfo stationInfo = new StationInfo();
                        stationInfo.type = json.getString("type").replace("#", "号");
                        stationInfo.price = json.getString("price");
                        stationInfo.pricetag = json.getString("pricetag");
                        result.add(stationInfo);
                    }
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static PoiDeepInfo parseFromString(String str) {
        PoiDeepInfo result = new PoiDeepInfo();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject js = new JSONObject(str);
            result.category = js.getInt("category");
            result.tag = js.getString(SdkConstants.ATTR_TAG);
            T(result, js);
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void T(PoiDeepInfo result, JSONObject js) {
        Object tagInfo2 = null;
        List<String> feature2 = new ArrayList<>();
        switch (result.category) {
            case 0:
                tagInfo2 = PackingInfo.parseFromString(js);
                break;
            case 1:
                List<StationInfo> stationList = StationInfo.parseFromString(js);
                if (stationList != null) {
                    for (int i = 0; i < stationList.size(); i++) {
                        feature2.add(stationList.get(i).type);
                    }
                }
                if (result.tag.matches("碧辟")) {
                    result.tag = "碧辟";
                }
                if (result.tag.matches("道达尔")) {
                    result.tag = "道达尔";
                }
                tagInfo2 = stationList;
                break;
        }
        feature2.add(result.tag);
        result.feature = feature2;
        result.tagInfo = tagInfo2;
    }
}
