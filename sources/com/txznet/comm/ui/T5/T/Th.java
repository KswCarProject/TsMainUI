package com.txznet.comm.ui.T5.T;

import com.txznet.comm.Ty.Tr;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class Th extends TB {

    /* renamed from: T  reason: collision with root package name */
    private ArrayList<T> f447T = new ArrayList<>();

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public String f448T;
        public String Tr;
        public double Ty;
    }

    public Th() {
        super(13);
    }

    public void T(Tr data) {
        this.f447T.clear();
        JSONArray obJsonArray = (JSONArray) data.T("cines", JSONArray.class);
        for (int i = 0; i < this.T9; i++) {
            try {
                Tr cBuilder = new Tr(obJsonArray.getJSONObject(i));
                T cinemaBean = new T();
                cinemaBean.f448T = (String) cBuilder.T("name", String.class);
                cinemaBean.Tr = (String) cBuilder.T("post", String.class);
                cinemaBean.Ty = ((Double) cBuilder.T("score", Double.class)).doubleValue();
                this.f447T.add(cinemaBean);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
