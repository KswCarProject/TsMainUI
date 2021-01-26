package com.ts.main.avin;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import com.ts.MainUI.R;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;

public class AvRearView extends Presentation {
    private static final String TAG = "AvRearView";

    public AvRearView(Context outerContext, Display display) {
        super(outerContext, display);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_rear_avin_main);
        BackcarService.getInstance().StartCamera((AutoFitTextureView) findViewById(R.id.textureView), false);
        BackcarService.getInstance().bRearShow = true;
    }
}
