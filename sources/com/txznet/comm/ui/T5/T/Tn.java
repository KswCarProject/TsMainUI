package com.txznet.comm.ui.T5.T;

import android.text.TextUtils;
import com.ts.bt.ContactInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Proguard */
public class Tn extends TM {

    /* renamed from: T  reason: collision with root package name */
    public List<T> f452T;
    public Double T9;
    public Double TZ;
    public Double Tk;
    public Double Tn;
    public Double Tr;
    public Double Ty;

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public List<Double[]> f453T;
        public int Tr = 0;
        public String Ty = null;
    }

    public Tn() {
        super(18);
    }

    public void T(String data) {
        if (!TextUtils.isEmpty(data)) {
            this.f452T = new ArrayList();
            try {
                JSONObject js = new JSONObject(data);
                if (js.has("local")) {
                    String myLocal = js.getString("local");
                    if (!TextUtils.isEmpty(myLocal)) {
                        this.Tk = Double.valueOf(Double.parseDouble(myLocal.split(ContactInfo.COMBINATION_SEPERATOR)[0]));
                        this.TZ = Double.valueOf(Double.parseDouble(myLocal.split(ContactInfo.COMBINATION_SEPERATOR)[1]));
                        T(new Double[]{this.Tk, this.TZ});
                    }
                }
                if (js.has("polyLine")) {
                    String polyLine = js.getString("polyLine");
                    if (!TextUtils.isEmpty(polyLine)) {
                        JSONArray trafficArray = new JSONArray(polyLine);
                        for (int i = 0; i < trafficArray.length(); i++) {
                            if (!trafficArray.isNull(i)) {
                                T info = new T();
                                JSONObject json = trafficArray.getJSONObject(i);
                                info.Tr = json.getInt("status");
                                info.f453T = Tr(json.getString("line"));
                                this.f452T.add(info);
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }
            if (this.f452T.size() <= 0) {
                this.f452T = null;
            }
        }
    }

    private List<Double[]> Tr(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        List<Double[]> result = new ArrayList<>();
        for (String point : str.split(";")) {
            if (!TextUtils.isEmpty(point)) {
                String[] split2 = point.split(ContactInfo.COMBINATION_SEPERATOR);
                Double[] gps = {Double.valueOf(0.0d), Double.valueOf(0.0d)};
                gps[0] = Double.valueOf(Double.parseDouble(split2[0]));
                gps[1] = Double.valueOf(Double.parseDouble(split2[1]));
                T(gps);
                result.add(gps);
            }
        }
        if (result.size() <= 0) {
            return null;
        }
        return result;
    }

    private void T(Double[] gps) {
        double lat = gps[0].doubleValue();
        double lng = gps[1].doubleValue();
        if (this.Tr == null) {
            this.Tr = Double.valueOf(lat);
            this.Tn = Double.valueOf(lat);
            this.Ty = Double.valueOf(lng);
            this.T9 = Double.valueOf(lng);
            return;
        }
        if (lat > this.Tn.doubleValue()) {
            this.Tn = Double.valueOf(lat);
        }
        if (lat < this.Tr.doubleValue()) {
            this.Tr = Double.valueOf(lat);
        }
        if (lng > this.T9.doubleValue()) {
            this.T9 = Double.valueOf(lng);
        }
        if (lng < this.Ty.doubleValue()) {
            this.Ty = Double.valueOf(lng);
        }
    }
}
