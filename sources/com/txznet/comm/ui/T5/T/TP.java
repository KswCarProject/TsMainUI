package com.txznet.comm.ui.T5.T;

import com.txznet.comm.Ty.Tr;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class TP extends TB {

    /* renamed from: T  reason: collision with root package name */
    private ArrayList<T> f444T = new ArrayList<>();

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public String f445T;
        public String Tr;
    }

    public TP() {
        super(7);
    }

    public void T(Tr data) {
        this.f444T.clear();
        JSONArray obJsonArray = (JSONArray) data.T("contacts", JSONArray.class);
        if (obJsonArray != null) {
            for (int i = 0; i < this.T9; i++) {
                try {
                    Tr objJson = new Tr(obJsonArray.getJSONObject(i));
                    T weChatBean = new T();
                    weChatBean.Tr = (String) objJson.T("name", String.class);
                    weChatBean.f445T = (String) objJson.T("id", String.class);
                    this.f444T.add(weChatBean);
                } catch (JSONException e) {
                }
            }
        }
    }
}
