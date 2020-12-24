package com.txznet.comm.ui.T5.T;

import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.tongting.IConstantData;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class Tx extends TB {

    /* renamed from: T  reason: collision with root package name */
    private ArrayList<T> f459T = new ArrayList<>();

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public String f460T;
        public int Tr;
        public int Ty;
    }

    public Tx() {
        super(9);
    }

    public void T(Tr data) {
        this.f459T.clear();
        JSONArray obJsonArray = (JSONArray) data.T(IConstantData.KEY_DATA, JSONArray.class);
        if (obJsonArray != null) {
            for (int i = 0; i < this.T9; i++) {
                try {
                    Tr objJson = new Tr(obJsonArray.getJSONObject(i));
                    T simBean = new T();
                    simBean.f460T = (String) objJson.T(IConstantData.KEY_TITLE, String.class);
                    simBean.Tr = ((Integer) objJson.T("price", Integer.class)).intValue();
                    simBean.Ty = ((Integer) objJson.T("rawPrice", Integer.class)).intValue();
                    this.f459T.add(simBean);
                } catch (JSONException e) {
                }
            }
        }
    }
}
