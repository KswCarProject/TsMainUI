package com.ts.main.txz;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.txznet.sdk.TXZCallManager;
import com.txznet.sdk.TXZCameraManager;
import com.txznet.sdk.tongting.IConstantData;

public class Glsx {
    public static final int DDBX = 32;
    public static final int DDDDSC = 12;
    public static final int DDDLJY = 28;
    public static final int DDHCZ = 40;
    public static final int DDHCZDH = 50;
    public static final int DDHFAV = 4;
    public static final int DDHLOOP = 2;
    public static final int DDHNEXT = 3;
    public static final int DDHOPENFAV = 5;
    public static final int DDHPAUSE = 1;
    public static final int DDHPLAY = 0;
    public static final int DDHPRE = 2;
    public static final int DDHRADOM = 1;
    public static final int DDHSLOOP = 3;
    public static final int DDHUMNAVI = 5;
    public static final int DDHWZCX = 25;
    public static final int DDLK = 30;
    public static final int DDMUSIC = 3;
    static Glsx m_Gxsl = null;
    private Context myContext = null;

    public static Glsx GetInstance() {
        if (m_Gxsl == null) {
            m_Gxsl = new Glsx();
        }
        return m_Gxsl;
    }

    public void Inint(Context m_Context) {
        this.myContext = m_Context;
    }

    public void OpenApp(int nID) {
        Intent intent = new Intent("com.glsx.ddbox.voice.action.OPENAPPS");
        intent.putExtra("app_id", nID);
        this.myContext.sendBroadcast(intent);
    }

    public void OpenDDBOX() {
        Intent it = this.myContext.getPackageManager().getLaunchIntentForPackage("com.glsx.ddbox");
        Log.i("NaviMainActivity  PATH =", "com.glsx.ddbox");
        if (it != null) {
            this.myContext.startActivity(it);
        }
    }

    public void PlayMusic(int nMode, String Album, String SongName) {
        Intent intent = new Intent("com.glsx.bootup.ddmusic");
        intent.putExtra(IConstantData.KEY_TYPE, nMode);
        intent.putExtra("artist", Album);
        intent.putExtra("musicName", SongName);
        this.myContext.sendBroadcast(intent);
    }

    public void PlayCmd(int nCmd) {
        Intent intent = new Intent("com.glsx.ddmusic.action.PLAY_CONTROL");
        intent.putExtra("play_command", nCmd);
        this.myContext.sendBroadcast(intent);
    }

    public void PlayMode(int nMode) {
        Intent intent = new Intent("com.glsx.ddmusic.action.PLAY_MODE");
        intent.putExtra("play_mode", nMode);
        this.myContext.sendBroadcast(intent);
    }

    public void PlayMusicInfo(int nInfo) {
        Intent intent = new Intent("com.glsx.ddmusic.action.PLAY_APPOINT");
        intent.putExtra("play_command ", nInfo);
        this.myContext.sendBroadcast(intent);
    }

    public void CheckLk(int nMode) {
        Intent intent = new Intent("com.glsx.ddroad.action.MODE");
        intent.putExtra("road_mode", nMode);
        intent.putExtra("road_name", "");
        this.myContext.sendBroadcast(intent);
    }

    public void PlayIndex(int nIndex) {
        Intent intent = new Intent("com.glsx.ddmusic.voicesearch.appointplay");
        intent.putExtra(TXZCameraManager.REMOTE_NAME_CAMERA_POSITION, nIndex);
        this.myContext.sendBroadcast(intent);
    }

    public void Dail(TXZCallManager.Contact cot) {
        Intent intent = new Intent("com.glsx.ddbox.telephone.callByVoice");
        intent.putExtra(IConstantData.KEY_NAME, cot.getName());
        intent.putExtra("phoneNumber", cot.getNumber());
        this.myContext.sendBroadcast(intent);
    }
}
