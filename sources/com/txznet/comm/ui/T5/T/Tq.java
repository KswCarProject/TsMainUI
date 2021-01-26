package com.txznet.comm.ui.T5.T;

import com.android.SdkConstants;
import com.ts.main.common.MainUI;
import com.txznet.comm.Ty.Tr;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class Tq extends TB {

    /* renamed from: T  reason: collision with root package name */
    public boolean f454T = true;
    private ArrayList<T> Tr = new ArrayList<>();
    private String Ty;

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public String f455T;
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
        this.Ty = (String) data.T(SdkConstants.ATTR_LABEL, String.class);
        this.f454T = ((Boolean) data.T("hasNet", Boolean.class, true)).booleanValue();
        JSONArray obJsonArray = (JSONArray) data.T("helpDetails", JSONArray.class);
        if (obJsonArray != null) {
            for (int i = 0; i < this.T9; i++) {
                try {
                    Tr objJson = new Tr(obJsonArray.getJSONObject(i));
                    T helpBean = new T();
                    helpBean.f455T = (String) objJson.T("id", String.class);
                    helpBean.Tr = (String) objJson.T("title", String.class);
                    helpBean.Ty = (String) objJson.T(MainUI.NET_TIME_, String.class);
                    helpBean.Tn = ((Boolean) objJson.T("isNew", Boolean.class, false)).booleanValue();
                    helpBean.T9 = ((Integer) objJson.T("netType", Integer.class, 0)).intValue();
                    this.Tr.add(helpBean);
                } catch (JSONException e) {
                }
            }
        }
    }
}
