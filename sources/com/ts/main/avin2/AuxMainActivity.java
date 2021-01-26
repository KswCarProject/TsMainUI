package com.ts.main.avin2;

import android.app.Activity;
import android.os.Bundle;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;

public class AuxMainActivity extends Activity implements UserCallBack {
    private static final String TAG = "AuxMainActivity";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_aux_main);
        super.onCreate(savedInstanceState);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Evc.GetInstance().evol_workmode_set(8);
        MainTask.GetInstance().SetUserCallBack(this);
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    public void UserAll() {
    }
}
