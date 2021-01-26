package com.txznet.comm.ui.T5.T;

import com.txznet.comm.Ty.Tr;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class Tx extends TB {

    /* renamed from: T  reason: collision with root package name */
    private ArrayList<T> f463T = new ArrayList<>();

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public String f464T;
        public int Tr;
        public int Ty;
    }

    public Tx() {
        super(9);
    }

    public void T(Tr data) {
        this.f463T.clear();
        JSONArray obJsonArray = (JSONArray) data.T("data", JSONArray.class);
        if (obJsonArray != null) {
            for (int i = 0; i < this.T9; i++) {
                try {
                    Tr objJson = new Tr(obJsonArray.getJSONObject(i));
                    T simBean = new T();
                    simBean.f464T = (String) objJson.T("title", String.class);
                    simBean.Tr = ((Integer) objJson.T("price", Integer.class)).intValue();
                    simBean.Ty = ((Integer) objJson.T("rawPrice", Integer.class)).intValue();
                    this.f463T.add(simBean);
                } catch (JSONException e) {
                }
            }
        }
    }
}
