package com.txznet.comm.ui.T5.T;

import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.tongting.IConstantData;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class TF extends TB {

    /* renamed from: T  reason: collision with root package name */
    public boolean f435T;
    private ArrayList<T> T5 = new ArrayList<>();
    public boolean Tr;
    public String Ty;

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public String f436T;
        public boolean T9;
        public boolean Tk;
        public String Tn;
        public String Tr;
        public String Ty;
    }

    public TF() {
        super(11);
    }

    public void T(Tr data) {
        this.T5.clear();
        this.f435T = ((Boolean) data.T("canOpenDetail", Boolean.class, true)).booleanValue();
        this.Tr = ((Boolean) data.T("isShowTips", Boolean.class, false)).booleanValue();
        this.Ty = (String) data.T("tips", String.class, "");
        JSONArray obJsonArray = (JSONArray) data.T("helps", JSONArray.class);
        if (obJsonArray != null) {
            for (int i = 0; i < this.T9; i++) {
                try {
                    Tr objJson = new Tr(obJsonArray.getJSONObject(i));
                    T helpBean = new T();
                    helpBean.f436T = (String) objJson.T("iconName", String.class);
                    helpBean.Tr = (String) objJson.T(IConstantData.KEY_TITLE, String.class);
                    helpBean.Ty = (String) objJson.T("intro", String.class);
                    helpBean.T9 = ((Boolean) objJson.T("isNew", Boolean.class, false)).booleanValue();
                    helpBean.Tn = (String) objJson.T("time", String.class);
                    helpBean.Tk = ((Boolean) objJson.T("isFromFile", Boolean.class, false)).booleanValue();
                    this.T5.add(helpBean);
                } catch (JSONException e) {
                }
            }
        }
    }
}
