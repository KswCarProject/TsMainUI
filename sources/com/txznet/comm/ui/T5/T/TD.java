package com.txznet.comm.ui.T5.T;

import com.android.SdkConstants;
import com.ts.main.common.MainUI;
import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.TXZCameraManager;
import com.txznet.sdk.TXZResourceManager;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class TD extends TB {

    /* renamed from: T  reason: collision with root package name */
    private ArrayList<T> f436T = new ArrayList<>();

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public String f437T;
        public String Tn;
        public String Tr;
        public String Ty;
    }

    public TD() {
        super(26);
    }

    public ArrayList<T> T() {
        return this.f436T;
    }

    public void T(Tr data) {
        this.f436T.clear();
        JSONArray obJsonArray = (JSONArray) data.T("reminders", JSONArray.class);
        if (obJsonArray != null) {
            for (int i = 0; i < this.T9; i++) {
                try {
                    Tr objJson = new Tr(obJsonArray.getJSONObject(i));
                    T reminderBean = new T();
                    reminderBean.f437T = (String) objJson.T("id", String.class, TXZResourceManager.STYLE_DEFAULT);
                    reminderBean.Tr = (String) objJson.T(SdkConstants.ATTR_CONTENT, String.class, TXZResourceManager.STYLE_DEFAULT);
                    reminderBean.Ty = (String) objJson.T(MainUI.NET_TIME_, String.class, TXZResourceManager.STYLE_DEFAULT);
                    reminderBean.Tn = (String) objJson.T(TXZCameraManager.REMOTE_NAME_CAMERA_POSITION, String.class, TXZResourceManager.STYLE_DEFAULT);
                    this.f436T.add(reminderBean);
                } catch (JSONException e) {
                }
            }
        }
    }
}
