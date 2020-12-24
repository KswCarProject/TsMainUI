package com.ts.main.avin2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.ExploreByTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.toyota.dj.CanToyotaDJCarDeviceView;
import com.ts.main.common.WinShow;

public class AuxMainActivity extends Activity implements UserCallBack, View.OnClickListener {
    private static final String TAG = "AuxMainActivity";
    Button btnSound;
    private Evc mEvc = Evc.GetInstance();
    private int nMode = 0;
    private int nOpen = 0;
    private TextView textview;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_aux_main);
        getWindow().clearFlags(201326592);
        getWindow().getDecorView().setSystemUiVisibility(CanToyotaDJCarDeviceView.ITEM_RPT);
        getWindow().addFlags(ExploreByTouchHelper.INVALID_ID);
        getWindow().setStatusBarColor(0);
        getWindow().setNavigationBarColor(0);
        this.btnSound = (Button) findViewById(R.id.btn_auxsound);
        this.btnSound.setOnClickListener(this);
        super.onCreate(savedInstanceState);
    }

    private void DisplayToast(String string) {
        Toast.makeText(this, string, 1).show();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.mEvc.evol_workmode_set(8);
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

    public void onClick(View arg0) {
        WinShow.TurnToEq();
    }
}
