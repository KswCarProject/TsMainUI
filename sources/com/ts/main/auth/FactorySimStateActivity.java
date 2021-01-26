package com.ts.main.auth;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ts.MainUI.R;

public class FactorySimStateActivity extends Activity {
    RelativeLayout MyRelativeLayout = null;
    TextView[] SimState = new TextView[10];

    /* access modifiers changed from: protected */
    public void setViewPos(View view, int x, int y, int w, int h) {
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(w, h);
        rl.leftMargin = x;
        rl.topMargin = y;
        view.setLayoutParams(rl);
        this.MyRelativeLayout.addView(view);
    }

    /* access modifiers changed from: protected */
    public TextView TvCreateRelative(int x, int y, int w, int h) {
        TextView tv = new TextView(this);
        setViewPos(tv, x, y, w, h);
        tv.setTextColor(-1);
        return tv;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory_sim_state);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }
}
