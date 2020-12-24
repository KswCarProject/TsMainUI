package com.txznet.comm.ui.T5.T;

import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.TXZCameraManager;
import com.txznet.sdk.tongting.IConstantData;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class TD extends TB {

    /* renamed from: T  reason: collision with root package name */
    private ArrayList<T> f432T = new ArrayList<>();

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public String f433T;
        public String Tn;
        public String Tr;
        public String Ty;
    }

    public TD() {
        super(26);
    }

    public ArrayList<T> T() {
        return this.f432T;
    }

    public void T(Tr data) {
        this.f432T.clear();
        JSONArray obJsonArray = (JSONArray) data.T("reminders", JSONArray.class);
        if (obJsonArray != null) {
            for (int i = 0; i < this.T9; i++) {
                try {
                    Tr objJson = new Tr(obJsonArray.getJSONObject(i));
                    T reminderBean = new T();
                    reminderBean.f433T = (String) objJson.T(IConstantData.KEY_ID, String.class, "");
                    reminderBean.Tr = (String) objJson.T("content", String.class, "");
                    reminderBean.Ty = (String) objJson.T("time", String.class, "");
                    reminderBean.Tn = (String) objJson.T(TXZCameraManager.REMOTE_NAME_CAMERA_POSITION, String.class, "");
                    this.f432T.add(reminderBean);
                } catch (JSONException e) {
                }
            }
        }
    }
}
