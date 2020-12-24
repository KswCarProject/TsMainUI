package com.ts.main.txz;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import com.hongfans.carmedia.MediaAPI;
import com.ts.main.common.MainSet;

public class Cyb {
    static Cyb m_Cyb = null;
    public MediaAPI mMediaAPI = null;
    private Context myContext = null;

    public static Cyb GetInstance() {
        if (m_Cyb == null) {
            m_Cyb = new Cyb();
        }
        return m_Cyb;
    }

    public void Inint(Context context) {
        Settings.System.putString(context.getContentResolver(), "hongfan_key", "H1TSDZ1611000000");
        Settings.System.putString(context.getContentResolver(), "hongfan_appsecret", "hf6a5d41f90101c5c84f6b01b6a227225a");
        ContentResolver contentResolver = context.getContentResolver();
        MainSet.GetInstance();
        Settings.System.putString(contentResolver, "hongfan_information", MainSet.seiid);
        this.myContext = context;
        this.mMediaAPI = MediaAPI.createMediaAPI(this.myContext, "hongfans");
        this.mMediaAPI.setIsDebug(false);
    }

    public void startApp() {
        this.mMediaAPI.startAPP(this.myContext);
    }

    public void exitApp() {
        this.mMediaAPI.exitAPP(this.myContext);
    }

    public void playResume() {
        this.mMediaAPI.PlayResume(this.myContext);
    }

    public void playPause() {
        this.mMediaAPI.PlayPause(this.myContext);
    }

    public void playNext() {
        this.mMediaAPI.PlayNext(this.myContext);
    }

    public void playPrevious() {
        this.mMediaAPI.PlayPrevious(this.myContext);
    }

    public void playMusic(String keyword) {
        this.mMediaAPI.playMusic(this.myContext, keyword, (String) null);
    }
}
