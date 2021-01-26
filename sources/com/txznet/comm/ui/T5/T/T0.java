package com.txznet.comm.ui.T5.T;

import com.txznet.comm.Ty.Tr;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class T0 extends TB {

    /* renamed from: T  reason: collision with root package name */
    private ArrayList<T> f428T = new ArrayList<>();

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public int f429T;
        public String Tr;
    }

    public T0() {
        super(10);
    }

    public void T(Tr data) {
        this.f428T.clear();
        JSONArray obJsonArray = (JSONArray) data.T("themes", JSONArray.class);
        if (obJsonArray != null) {
            for (int i = 0; i < this.T9; i++) {
                try {
                    Tr objJson = new Tr(obJsonArray.getJSONObject(i));
                    T ttsBean = new T();
                    ttsBean.f429T = ((Integer) objJson.T("id", Integer.class)).intValue();
                    ttsBean.Tr = (String) objJson.T("name", String.class);
                    this.f428T.add(ttsBean);
                } catch (JSONException e) {
                }
            }
        }
    }
}
