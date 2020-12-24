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
public class Tu extends TB {

    /* renamed from: T  reason: collision with root package name */
    public boolean f457T;
    public int Tr;
    private ArrayList<Poi> Ty = new ArrayList<>();

    public Tu() {
        super(5);
    }

    public ArrayList<Poi> T() {
        return this.Ty;
    }

    public void T(Tr data) {
        this.Ty.clear();
        String action = (String) data.T("action", String.class);
        String str = (String) data.T("city", String.class);
        String business = (String) data.T("poitype", String.class);
        this.Tr = ((Integer) data.T("showcount", Integer.class)).intValue();
        this.f457T = false;
        if (!TextUtils.isEmpty(business) && business.equals("business")) {
            this.f457T = true;
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
                    poi.setAction(action);
                    this.Ty.add(poi);
                } catch (JSONException e) {
                }
            }
        }
    }
}
