package com.ts;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class HideActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        setTheme(16973840);
        super.onCreate(arg0);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = 1;
        params.height = 1;
        getWindow().setAttributes(params);
        setContentView(new View(this));
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        moveTaskToBack(false);
        super.onResume();
    }
}
