package com.txznet.comm.ui.T5.T;

import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.tongting.IConstantData;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class Th extends TB {

    /* renamed from: T  reason: collision with root package name */
    private ArrayList<T> f443T = new ArrayList<>();

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public String f444T;
        public String Tr;
        public double Ty;
    }

    public Th() {
        super(13);
    }

    public void T(Tr data) {
        this.f443T.clear();
        JSONArray obJsonArray = (JSONArray) data.T("cines", JSONArray.class);
        for (int i = 0; i < this.T9; i++) {
            try {
                Tr cBuilder = new Tr(obJsonArray.getJSONObject(i));
                T cinemaBean = new T();
                cinemaBean.f444T = (String) cBuilder.T(IConstantData.KEY_NAME, String.class);
                cinemaBean.Tr = (String) cBuilder.T("post", String.class);
                cinemaBean.Ty = ((Double) cBuilder.T("score", Double.class)).doubleValue();
                this.f443T.add(cinemaBean);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
