package com.txznet.comm.ui.T5.T;

import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.tongting.IConstantData;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class TP extends TB {

    /* renamed from: T  reason: collision with root package name */
    private ArrayList<T> f440T = new ArrayList<>();

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public String f441T;
        public String Tr;
    }

    public TP() {
        super(7);
    }

    public void T(Tr data) {
        this.f440T.clear();
        JSONArray obJsonArray = (JSONArray) data.T("contacts", JSONArray.class);
        if (obJsonArray != null) {
            for (int i = 0; i < this.T9; i++) {
                try {
                    Tr objJson = new Tr(obJsonArray.getJSONObject(i));
                    T weChatBean = new T();
                    weChatBean.Tr = (String) objJson.T(IConstantData.KEY_NAME, String.class);
                    weChatBean.f441T = (String) objJson.T(IConstantData.KEY_ID, String.class);
                    this.f440T.add(weChatBean);
                } catch (JSONException e) {
                }
            }
        }
    }
}
