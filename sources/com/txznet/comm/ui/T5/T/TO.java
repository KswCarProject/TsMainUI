package com.txznet.comm.ui.T5.T;

import android.text.TextUtils;
import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.bean.BusinessPoiDetail;
import com.txznet.sdk.bean.Poi;
import com.txznet.sdk.bean.PoiDetail;
import com.txznet.sdk.bean.TxzPoi;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
public class TO extends TB {

    /* renamed from: T  reason: collision with root package name */
    public boolean f443T;
    public Boolean T5;
    public Double T6;
    private ArrayList<Poi> TF = new ArrayList<>();
    public Double Te;
    public Double Th;
    public String Tq;
    public Integer Tr;
    public Double Tv;
    public Integer Ty;

    public TO() {
        super(19);
    }

    public ArrayList<Poi> T() {
        return this.TF;
    }

    public void T(Tr data) {
        this.TF.clear();
        this.Tq = (String) data.T("action", String.class);
        String str = (String) data.T("city", String.class);
        String business = (String) data.T("poitype", String.class);
        this.Ty = (Integer) data.T("showcount", Integer.class);
        this.Tr = (Integer) data.T("mapAction", Integer.class);
        this.T6 = (Double) data.T("locationLat", Double.class);
        this.Te = (Double) data.T("locationLng", Double.class);
        this.Tv = (Double) data.T("destinationLat", Double.class);
        this.Th = (Double) data.T("destinationLng", Double.class);
        this.T5 = (Boolean) data.T("listmodel", Boolean.class);
        this.f443T = false;
        if (!TextUtils.isEmpty(business) && business.equals("business")) {
            this.f443T = true;
        }
        JSONArray obJsonArray = (JSONArray) data.T("pois", JSONArray.class);
        if (obJsonArray != null) {
            for (int i = 0; i < this.T9; i++) {
                try {
                    JSONObject jo = obJsonArray.getJSONObject(i);
                    int poitype = 1;
                    if (jo.has("poitype")) {
                        poitype = jo.optInt("poitype");
                    }
                    String objJson = jo.toString();
                    Poi poi = null;
                    switch (poitype) {
                        case 1:
                            poi = PoiDetail.fromString(objJson);
                            break;
                        case 2:
                            poi = BusinessPoiDetail.fromString(objJson);
                            break;
                        case 3:
                            poi = TxzPoi.fromString(objJson);
                            break;
                    }
                    poi.setAction(this.Tk);
                    this.TF.add(poi);
                } catch (JSONException e) {
                }
            }
        }
    }
}
