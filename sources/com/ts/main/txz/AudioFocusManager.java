package com.ts.main.txz;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import com.txznet.sdk.bean.Poi;

public class AudioFocusManager {
    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener;
    private AudioManager mAudioManager;

    public interface AudioListener {
        void pause();

        void start();
    }

    public int requestTheAudioFocus(Context m_Context, final AudioListener audioListener) {
        if (Build.VERSION.SDK_INT < 8) {
            return 0;
        }
        if (this.mAudioManager == null) {
            this.mAudioManager = (AudioManager) m_Context.getSystemService(Poi.PoiAction.ACTION_AUDIO);
        }
        if (this.mAudioFocusChangeListener == null) {
            this.mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    switch (focusChange) {
                        case -3:
                        case -2:
                        case -1:
                            audioListener.pause();
                            return;
                        case 1:
                        case 2:
                        case 3:
                            audioListener.start();
                            return;
                        default:
                            return;
                    }
                }
            };
        }
        return this.mAudioManager.requestAudioFocus(this.mAudioFocusChangeListener, 3, 2);
    }

    public void releaseTheAudioFocus() {
        if (this.mAudioManager != null && this.mAudioFocusChangeListener != null) {
            this.mAudioManager.abandonAudioFocus(this.mAudioFocusChangeListener);
        }
    }
}
