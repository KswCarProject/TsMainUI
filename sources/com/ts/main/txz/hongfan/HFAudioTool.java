package com.ts.main.txz.hongfan;

import android.content.Context;
import com.hongfans.carmedia.MediaAPI;
import com.txznet.sdk.TXZAudioManager;
import org.json.JSONException;
import org.json.JSONObject;

public class HFAudioTool extends TXZAudioManager.IAudioTool {
    public static HFAudioTool sRadio;
    private Context mContext;
    private MediaAPI mMediaAPI;

    public static HFAudioTool getInstance() {
        if (sRadio == null) {
            synchronized (HFMusicAndRadioTool.class) {
                if (sRadio == null) {
                    sRadio = new HFAudioTool();
                }
            }
        }
        return sRadio;
    }

    public void init(Context context, MediaAPI mediaAPI) {
        if (context != null && mediaAPI != null) {
            this.mContext = context;
            this.mMediaAPI = mediaAPI;
        }
    }

    public void exit() {
        this.mMediaAPI.exitAPP(this.mContext);
    }

    public void pause() {
        this.mMediaAPI.PlayPause(this.mContext);
    }

    public void playFm(String jsonString) {
        try {
            AudioModel am = AudioModel.parse(new JSONObject(jsonString));
            if (am.getKeywords() != null && am.getKeywords().length() > 0) {
                this.mMediaAPI.playMusic(this.mContext, am.getKeywords().getString(0), "");
            } else if (am.getAlbum() != null) {
                this.mMediaAPI.playMusic(this.mContext, am.getAlbum(), "");
            } else if (am.getArtists() != null && am.getArtists().length() > 0) {
                this.mMediaAPI.playMusic(this.mContext, am.getArtists().getString(0), "");
            }
        } catch (JSONException e) {
            this.mMediaAPI.playMusic(this.mContext, jsonString, "");
            e.printStackTrace();
        }
    }

    public void setAudioStatusListener(TXZAudioManager.AudioToolStatusListener arg0) {
    }

    public void start() {
        this.mMediaAPI.PlayResume(this.mContext);
    }
}
