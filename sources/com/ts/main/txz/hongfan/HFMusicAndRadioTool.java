package com.ts.main.txz.hongfan;

import android.content.Context;
import android.util.Log;
import com.hongfans.carmedia.MediaAPI;
import com.txznet.sdk.TXZAsrManager;
import com.txznet.sdk.TXZMusicManager;

public class HFMusicAndRadioTool implements TXZMusicManager.MusicTool {
    private static HFMusicAndRadioTool sHFMusicTool;
    private TXZAsrManager.CommandListener mCommandListener = new TXZAsrManager.CommandListener() {
        public void onCommand(String cmd, String data) {
            if (data.startsWith("HFFM#")) {
                HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "FM" + data.split("#")[1], "");
            }
        }
    };
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public MediaAPI mMediaAPI;

    public static HFMusicAndRadioTool getInstance() {
        if (sHFMusicTool == null) {
            synchronized (HFMusicAndRadioTool.class) {
                if (sHFMusicTool == null) {
                    sHFMusicTool = new HFMusicAndRadioTool();
                }
            }
        }
        return sHFMusicTool;
    }

    public void init(Context context, MediaAPI mediaAPI) {
        if (context != null && mediaAPI != null) {
            this.mContext = context;
            this.mMediaAPI = mediaAPI;
            TXZMusicManager.getInstance().setMusicTool((TXZMusicManager.MusicTool) this);
            initLive();
        }
    }

    private void initLive() {
        TXZAsrManager.getInstance().addCommandListener(new TXZAsrManager.CommandListener() {
            public void onCommand(String arg0, String arg1) {
                if (arg1.equals("CMD_PLAY_CCTV")) {
                    Log.d("hongfan", "CMD_PLAY_CCTV");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "CCTV1", "");
                } else if (arg1.equals("CMD_PLAY_CCTV1")) {
                    Log.d("hongfan", "CMD_PLAY_CCTV1");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "CCTV1", "");
                } else if (arg1.equals("CMD_PLAY_CCTV2")) {
                    Log.d("hongfan", "CMD_PLAY_CCTV2");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "CCTV2", "");
                } else if (arg1.equals("CMD_PLAY_CCTV3")) {
                    Log.d("hongfan", "CMD_PLAY_CCTV3");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "CCTV3", "");
                } else if (arg1.equals("CMD_PLAY_CCTV5")) {
                    Log.d("hongfan", "CMD_PLAY_CCTV5");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "CCTV5", "");
                } else if (arg1.equals("CMD_PLAY_CCTV13")) {
                    Log.d("hongfan", "CMD_PLAY_CCTV13");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "CCTV13", "");
                } else if (arg1.equals("CMD_PLAY_CCTV15")) {
                    Log.d("hongfan", "CMD_PLAY_CCTV15");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "CCTV15", "");
                } else if (arg1.equals("CMD_PLAY_CCTV22")) {
                    Log.d("hongfan", "CMD_PLAY_CCTV22");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "CCTV22", "");
                } else if (arg1.equals("CMD_PLAY_CCTV23")) {
                    Log.d("hongfan", "CMD_PLAY_CCTV23");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "CCTV23", "");
                } else if (arg1.equals("CMD_PLAY_FHWS")) {
                    Log.d("hongfan", "CMD_PLAY_FHWS");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "凤凰卫视", "");
                } else if (arg1.equals("CMD_PLAY_SYXW")) {
                    Log.d("hongfan", "CMD_PLAY_SYXW");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "沈阳新闻", "");
                } else if (arg1.equals("CMD_PLAY_SZWS")) {
                    Log.d("hongfan", "CMD_PLAY_SZWS");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "深圳卫视", "");
                } else if (arg1.equals("CMD_PLAY_JSWS")) {
                    Log.d("hongfan", "CMD_PLAY_JSWS");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "江苏卫视", "");
                } else if (arg1.equals("CMD_PLAY_HNWS")) {
                    Log.d("hongfan", "CMD_PLAY_HNWS");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "湖南卫视", "");
                } else if (arg1.equals("CMD_PLAY_DFXW")) {
                    Log.d("hongfan", "CMD_PLAY_DFXW");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "东方卫视", "");
                } else if (arg1.equals("CMD_PLAY_BJWS")) {
                    Log.d("hongfan", "CMD_PLAY_BJWS");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "北京卫视", "");
                } else if (arg1.equals("CMD_PLAY_ZJWS")) {
                    Log.d("hongfan", "CMD_PLAY_ZJWS");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "浙江卫视", "");
                } else if (arg1.equals("CMD_PLAY_ZZXW")) {
                    Log.d("hongfan", "CMD_PLAY_ZZXW");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "郑州新闻", "");
                } else if (arg1.equals("CMD_PLAY_GZXW")) {
                    Log.d("hongfan", "CMD_PLAY_GZXW");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "广州新闻", "");
                } else if (arg1.equals("CMD_PLAY_BJXW")) {
                    Log.d("hongfan", "CMD_PLAY_BJXW");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "北京新闻", "");
                } else if (arg1.equals("CMD_PLAY_SHDY")) {
                    Log.d("hongfan", "CMD_PLAY_SHDY");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "四海钓鱼", "");
                } else if (arg1.equals("CMD_PLAY_ZQZX")) {
                    Log.d("hongfan", "CMD_PLAY_ZQZX");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "证券资讯", "");
                } else if (arg1.equals("CMD_PLAY_DYCJ")) {
                    Log.d("hongfan", "CMD_PLAY_DYCJ");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "第一财经", "");
                } else if (arg1.equals("CMD_PLAY_CFTX")) {
                    Log.d("hongfan", "CMD_PLAY_CFTX");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "财富天下", "");
                } else if (arg1.equals("CMD_PLAY_HBWS")) {
                    Log.d("hongfan", "CMD_PLAY_HBWS");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "湖北卫视", "");
                } else if (arg1.equals("CMD_PLAY_QDXW")) {
                    Log.d("hongfan", "CMD_PLAY_QDXW");
                    HFMusicAndRadioTool.this.mMediaAPI.playMusic(HFMusicAndRadioTool.this.mContext, "青岛新闻", "");
                }
            }
        });
        TXZAsrManager.getInstance().regCommand(new String[]{"播放CCTV", "播放中央电视台"}, "CMD_PLAY_CCTV");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放CCTV1", "播放中央一台", "播放中央1台", "播放中央综合"}, "CMD_PLAY_CCTV1");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放CCTV2", "播放中央二台", "播放中央2台", "播放中央经济"}, "CMD_PLAY_CCTV2");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放CCTV3", "播放中央三台", "播放中央3台", "播放中央综艺"}, "CMD_PLAY_CCTV3");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放CCTV5", "播放中央五台", "播放中央5台", "播放中央体育"}, "CMD_PLAY_CCTV5");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放CCTV13", "播放中央十三台", "播放中央13台", "播放中央新闻"}, "CMD_PLAY_CCTV13");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放CCTV15", "播放中央十五台", "播放中央15台", "播放中央音乐"}, "CMD_PLAY_CCTV15");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放CCTV22", "播放中央二十二台", "播放中央22台", "播放风云足球"}, "CMD_PLAY_CCTV22");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放CCTV23", "播放中央二十三台", "播放中央23台", "播放中央高尔夫", "播放中央网球", "播放中央高网"}, "CMD_PLAY_CCTV23");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放凤凰卫视"}, "CMD_PLAY_FHWS");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放沈阳新闻"}, "CMD_PLAY_SYXW");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放深圳卫视"}, "CMD_PLAY_SZWS");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放江苏卫视"}, "CMD_PLAY_JSWS");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放湖南卫视"}, "CMD_PLAY_HNWS");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放东方卫视", "播放上海卫视", "播放上海东方卫视"}, "CMD_PLAY_DFXW");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放北京卫视"}, "CMD_PLAY_BJWS");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放浙江卫视"}, "CMD_PLAY_ZJWS");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放郑州新闻"}, "CMD_PLAY_ZZXW");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放广州新闻"}, "CMD_PLAY_GZXW");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放北京新闻"}, "CMD_PLAY_BJXW");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放四海钓鱼"}, "CMD_PLAY_SHDY");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放证券资讯", "播放证券咨询"}, "CMD_PLAY_ZQZX");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放第一财经"}, "CMD_PLAY_DYCJ");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放财富天下"}, "CMD_PLAY_CFTX");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放湖北卫视"}, "CMD_PLAY_HBWS");
        TXZAsrManager.getInstance().regCommand(new String[]{"播放青岛新闻"}, "CMD_PLAY_QDXW");
    }

    public void exit() {
        this.mMediaAPI.exitAPP(this.mContext);
    }

    public void favourMusic() {
    }

    public TXZMusicManager.MusicModel getCurrentMusicModel() {
        return null;
    }

    public boolean isPlaying() {
        return false;
    }

    public void next() {
        this.mMediaAPI.PlayNext(this.mContext);
    }

    public void pause() {
        this.mMediaAPI.PlayPause(this.mContext);
    }

    public void play() {
        this.mMediaAPI.PlayResume(this.mContext);
    }

    public void playFavourMusic() {
    }

    public void playMusic(TXZMusicManager.MusicModel model) {
        String title = model.getTitle();
        String artist = null;
        String[] artists = model.getArtist();
        if (artists != null && artists.length > 0) {
            artist = artists[0];
        }
        if (title == null) {
            title = "";
        }
        if (artist == null) {
            artist = "";
        }
        Log.d("playMusic", String.valueOf(title) + artist);
        this.mMediaAPI.playMusic(this.mContext, String.valueOf(title) + artist, "");
    }

    public void playRandom() {
    }

    public void prev() {
        this.mMediaAPI.PlayPrevious(this.mContext);
    }

    public void setStatusListener(TXZMusicManager.MusicToolStatusListener arg0) {
    }

    public void switchModeLoopAll() {
    }

    public void switchModeLoopOne() {
    }

    public void switchModeRandom() {
    }

    public void switchSong() {
    }

    public void unfavourMusic() {
    }

    public void continuePlay() {
    }
}
