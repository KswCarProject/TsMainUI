package com.ts.MainUI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AutoBootUp extends BroadcastReceiver {
    private static final String TAG = "AutoBootUp";
    public static boolean bBootComplete = false;
    Evc mEvc = Evc.GetInstance();

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.BOOT_COMPLETED".equals(action)) {
            Log.w(TAG, "######## B+ On (0)!");
            bBootComplete = true;
            if (context != null) {
                Intent startIntent = new Intent("android.intent.action.MAIN_SERVICE");
                startIntent.setPackage("com.ts.MainUI");
                context.startService(startIntent);
            }
        }
        if ("android.media.GIS_AUDIO_STATUS_ACTION".equals(action)) {
            Log.d(TAG, "get GIS_AUDIO_STATUS_ACTION ");
            boolean GISAudiostatus = intent.getBooleanExtra("android.media.EXTRA_GIS_AUDIO_STATUS_TYPE", false);
            if (GISAudiostatus) {
                this.mEvc.evol_navi_set(1, false);
            } else {
                this.mEvc.evol_navi_set(0, false);
            }
            Log.d(TAG, "get GIS_AUDIO_STATUS_ACTION " + GISAudiostatus);
        }
    }
}
