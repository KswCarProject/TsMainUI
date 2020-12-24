package com.txznet.comm.ui.T5.T;

import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.tongting.IConstantData;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class T6 extends TB {

    /* renamed from: T  reason: collision with root package name */
    public boolean f427T;
    private String T5;
    private ArrayList<T> Tr = new ArrayList<>();
    private String Ty;

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public String f428T;
        public String Tn;
        public String Tr;
        public String Ty;
    }

    public T6() {
        super(24);
    }

    public ArrayList<T> T() {
        return this.Tr;
    }

    public String Tr() {
        return this.T5;
    }

    public void T(Tr data) {
        this.Tr.clear();
        this.Ty = (String) data.T("label", String.class);
        this.T5 = (String) data.T(IConstantData.KEY_TITLE, String.class);
        this.f427T = ((Boolean) data.T("isFromFile", Boolean.class, false)).booleanValue();
        JSONArray obJsonArray = (JSONArray) data.T("helpDetails", JSONArray.class);
        if (obJsonArray != null) {
            for (int i = 0; i < this.T9; i++) {
                try {
                    Tr objJson = new Tr(obJsonArray.getJSONObject(i));
                    T helpBean = new T();
                    helpBean.f428T = (String) objJson.T(IConstantData.KEY_ID, String.class);
                    helpBean.Tr = (String) objJson.T("text", String.class);
                    helpBean.Ty = (String) objJson.T("time", String.class);
                    helpBean.Tn = (String) objJson.T("img", String.class);
                    this.Tr.add(helpBean);
                } catch (JSONException e) {
                }
            }
        }
    }
}
