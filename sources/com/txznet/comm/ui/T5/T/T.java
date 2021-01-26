package com.txznet.comm.ui.T5.T;

import com.android.SdkConstants;
import com.txznet.comm.Ty.Tr;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class T extends TB {

    /* renamed from: T  reason: collision with root package name */
    private ArrayList<C0018T> f426T = new ArrayList<>();

    /* renamed from: com.txznet.comm.ui.T5.T.T$T  reason: collision with other inner class name */
    /* compiled from: Proguard */
    public static class C0018T {

        /* renamed from: T  reason: collision with root package name */
        public int f427T;
        public String T9;
        public String Tn;
        public int Tr;
        public String Ty;
    }

    public T() {
        super(8);
    }

    public void T(Tr data) {
        this.f426T.clear();
        JSONArray obJsonArray = (JSONArray) data.T("audios", JSONArray.class);
        if (obJsonArray != null) {
            for (int i = 0; i < this.T9; i++) {
                try {
                    Tr objJson = new Tr(obJsonArray.getJSONObject(i));
                    C0018T audioBean = new C0018T();
                    audioBean.f427T = ((Integer) objJson.T("id", Integer.class, 0)).intValue();
                    audioBean.Tr = ((Integer) objJson.T("albumTrackCount", Integer.class, 0)).intValue();
                    audioBean.Ty = (String) objJson.T(SdkConstants.ATTR_TEXT, String.class);
                    audioBean.Tn = (String) objJson.T("title", String.class);
                    audioBean.T9 = (String) objJson.T("name", String.class);
                    this.f426T.add(audioBean);
                } catch (JSONException e) {
                }
            }
        }
    }
}
