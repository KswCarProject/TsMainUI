package com.txznet.comm.ui.T5.T;

import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.tongting.IConstantData;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class Tq extends TB {

    /* renamed from: T  reason: collision with root package name */
    public boolean f450T = true;
    private ArrayList<T> Tr = new ArrayList<>();
    private String Ty;

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public String f451T;
        public int T9;
        public boolean Tn;
        public String Tr;
        public String Ty;
    }

    public Tq() {
        super(17);
    }

    public void T(Tr data) {
        this.Tr.clear();
        this.Ty = (String) data.T("label", String.class);
        this.f450T = ((Boolean) data.T("hasNet", Boolean.class, true)).booleanValue();
        JSONArray obJsonArray = (JSONArray) data.T("helpDetails", JSONArray.class);
        if (obJsonArray != null) {
            for (int i = 0; i < this.T9; i++) {
                try {
                    Tr objJson = new Tr(obJsonArray.getJSONObject(i));
                    T helpBean = new T();
                    helpBean.f451T = (String) objJson.T(IConstantData.KEY_ID, String.class);
                    helpBean.Tr = (String) objJson.T(IConstantData.KEY_TITLE, String.class);
                    helpBean.Ty = (String) objJson.T("time", String.class);
                    helpBean.Tn = ((Boolean) objJson.T("isNew", Boolean.class, false)).booleanValue();
                    helpBean.T9 = ((Integer) objJson.T("netType", Integer.class, 0)).intValue();
                    this.Tr.add(helpBean);
                } catch (JSONException e) {
                }
            }
        }
    }
}
