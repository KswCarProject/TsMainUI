package com.ts.can.ford;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.MotionEvent;
import com.ts.MainUI.R;

public class MyParkDialog extends AlertDialog {
    protected MyParkDialog(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(32, 32);
        getWindow().setFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START, AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START);
        setContentView(R.layout.park_dialog_content);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (4 == event.getAction()) {
            return true;
        }
        return super.onTouchEvent(event);
    }
}
