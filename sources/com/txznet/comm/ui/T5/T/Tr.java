package com.txznet.comm.ui.T5.T;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class Tr extends TB {

    /* renamed from: T  reason: collision with root package name */
    public boolean f456T;
    private ArrayList<T> Tr = new ArrayList<>();

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public String f457T;
        public String T9;
        public String Tn;
        public String Tr;
        public String Ty;
    }

    public Tr() {
        super(6);
    }

    public void T(com.txznet.comm.Ty.Tr data) {
        this.Tr.clear();
        this.f456T = ((Boolean) data.T("isMultiName", Boolean.class)).booleanValue();
        JSONArray obJsonArray = (JSONArray) data.T("contacts", JSONArray.class);
        if (obJsonArray != null) {
            this.T9 = obJsonArray.length();
            for (int i = 0; i < this.T9; i++) {
                try {
                    com.txznet.comm.Ty.Tr objJson = new com.txznet.comm.Ty.Tr(obJsonArray.getJSONObject(i));
                    T callBean = new T();
                    callBean.Tr = (String) objJson.T("name", String.class);
                    callBean.f457T = (String) objJson.T("number", String.class);
                    callBean.Ty = (String) objJson.T("province", String.class);
                    callBean.Tn = (String) objJson.T("city", String.class);
                    callBean.T9 = (String) objJson.T("isp", String.class);
                    this.Tr.add(callBean);
                } catch (JSONException e) {
                }
            }
        }
    }
}
