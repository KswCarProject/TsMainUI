package com.txznet.sdk.media;

/* compiled from: Proguard */
public interface ITXZMediaTool {
    void collect();

    void continuePlay();

    void exit();

    PlayerStatus getStatus();

    boolean hasNext();

    boolean hasPrev();

    boolean interceptTts();

    void next();

    void open(boolean z);

    void pause();

    void play(TXZMediaModel tXZMediaModel);

    void playCollection();

    void playSubscribe();

    void prev();

    void subscribe();

    boolean supportCollect();

    boolean supportLoopMode(PlayerLoopMode playerLoopMode);

    boolean supportPlayCollection();

    boolean supportPlaySubscribe();

    boolean supportSearch();

    boolean supportSubscribe();

    boolean supportUnCollect();

    boolean supportUnSubscribe();

    void switchLoopMode(PlayerLoopMode playerLoopMode);

    void unCollect();

    void unSubscribe();
}
