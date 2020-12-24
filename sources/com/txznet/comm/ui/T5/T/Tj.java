package com.txznet.comm.ui.T5.T;

import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.tongting.IConstantData;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class Tj extends TM {

    /* renamed from: T  reason: collision with root package name */
    public String f445T;
    public int Tr;
    private ArrayList<T> Ty = new ArrayList<>();

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public String f446T;
        public String Tr;
    }

    public Tj() {
        super(23);
    }

    public ArrayList<T> T() {
        return this.Ty;
    }

    public void T(Tr jsonBuilder) {
        this.Ty.clear();
        this.f445T = (String) jsonBuilder.T(IConstantData.KEY_TITLE, String.class);
        JSONArray jsonArray = (JSONArray) jsonBuilder.T(IConstantData.KEY_DATA, JSONArray.class);
        if (jsonArray != null) {
            this.Tr = jsonArray.length();
            this.Ty = new ArrayList<>(this.Tr);
            for (int i = 0; i < this.Tr; i++) {
                try {
                    Tr jsonBean = new Tr(jsonArray.getJSONObject(i));
                    T mHelpTipBean = new T();
                    mHelpTipBean.f446T = (String) jsonBean.T("resId", String.class);
                    mHelpTipBean.Tr = (String) jsonBean.T("label", String.class);
                    this.Ty.add(mHelpTipBean);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
