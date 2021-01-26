package com.ts.backcar;

import android.content.Context;
import android.media.SoundPool;
import android.util.Log;
import com.ts.MainUI.R;
import java.util.HashMap;

public class BackCarSound {
    private static final int BEEP_ = 1;
    static BackCarSound MyBackCarSound = null;
    private static final int RING_ = 2;
    private static final String TAG = "BackCarSound";
    private static final int TPMSWARNING_ = 3;
    boolean bSealBelt = false;
    Context mContext = null;
    int nRadar = 0;
    int nSnum = 0;
    int nState = 0;
    SoundPool soundPool = new SoundPool(3, 1, 0);

    public static BackCarSound GetInstance() {
        if (MyBackCarSound == null) {
            MyBackCarSound = new BackCarSound();
        }
        return MyBackCarSound;
    }

    public void Inint(Context myContext) {
        this.mContext = myContext;
        HashMap soundPoolMap = new HashMap();
        soundPoolMap.put(2, Integer.valueOf(this.soundPool.load(this.mContext, R.raw.ring, 1)));
        soundPoolMap.put(3, Integer.valueOf(this.soundPool.load(this.mContext, R.raw.waring, 1)));
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            public void onLoadComplete(SoundPool arg0, int arg1, int arg2) {
            }
        });
    }

    public void KeyBeep() {
        this.soundPool.play(1, 1.0f, 1.0f, 100, 0, 1.0f);
    }

    public void PlayRing() {
        Log.i(TAG, "PlayRing==");
        this.soundPool.play(2, 1.0f, 1.0f, 100, 0, 1.0f);
    }

    public void TpmsWaring() {
        this.soundPool.play(3, 1.0f, 1.0f, 100, 3, 1.0f);
    }

    public void PlaySealBelt(boolean bEnable) {
    }

    public void PlayWaring(boolean bEnable) {
    }

    public void PlayRadar(boolean bEnable, int nRate) {
    }

    public int Task(int nBatfirst) {
        return 1;
    }
}
