package com.ts.backcar;

import android.content.Context;
import android.media.SoundPool;
import android.util.Log;
import com.ts.MainUI.R;
import com.yyw.ts70xhw.Iop;
import java.util.HashMap;

public class BackCarSound {
    private static final int BEEP_ = 1;
    static BackCarSound MyBackCarSound = null;
    private static final int RADAR_ = 3;
    private static final int SEALBELT_ = 2;
    private static final String TAG = "BackCarSound";
    boolean bSealBelt = false;
    Context mContext = null;
    int nRadar = 0;
    int nSnum = 0;
    int nState = 0;
    SoundPool soundPool = new SoundPool(3, 11, 0);

    public static BackCarSound GetInstance() {
        if (MyBackCarSound == null) {
            MyBackCarSound = new BackCarSound();
        }
        return MyBackCarSound;
    }

    public void Inint(Context myContext) {
        this.mContext = myContext;
        HashMap soundPoolMap = new HashMap();
        soundPoolMap.put(1, Integer.valueOf(this.soundPool.load(this.mContext, R.raw.keybeep, 1)));
        soundPoolMap.put(2, Integer.valueOf(this.soundPool.load(this.mContext, R.raw.sealbelt, 1)));
        soundPoolMap.put(3, Integer.valueOf(this.soundPool.load(this.mContext, R.raw.radar, 1)));
    }

    public void KeyBeep() {
        if (this.nState > 1) {
        }
    }

    public void PlaySealBelt(boolean bEnable) {
        if (this.nState <= 2) {
            if (bEnable) {
                Iop.NaviSpeaking(2);
                this.nState = 2;
                this.nSnum = 0;
                return;
            }
            Iop.NaviSpeaking(0);
            this.nState = 0;
        }
    }

    public void PlayRadar(boolean bEnable, int nRate) {
        if (bEnable) {
            Iop.NaviSpeaking(2);
            this.nState = 3;
            this.nSnum = 0;
            if (nRate > 0) {
                this.nRadar = nRate;
            } else {
                this.nRadar = 3;
            }
        } else {
            Iop.NaviSpeaking(0);
            this.soundPool.stop(3);
            this.nState = 0;
        }
    }

    public int Task(int nBatfirst) {
        switch (this.nState) {
            case 2:
                if (this.nSnum % 30 == 0) {
                    this.soundPool.play(2, 1.0f, 1.0f, 100, 0, 1.0f);
                }
                this.nSnum++;
                return 1;
            case 3:
                if (this.nSnum % this.nRadar == 0) {
                    this.soundPool.play(3, 1.0f, 1.0f, 100, 0, 1.0f);
                    Log.d(TAG, "paly==RADAR_");
                    this.nSnum = 0;
                }
                this.nSnum++;
                return 1;
            default:
                return 1;
        }
    }
}
