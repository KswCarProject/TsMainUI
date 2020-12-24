package com.ts.bt;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.main.common.MainVolume;

@SuppressLint({"NewApi"})
public class BtMusicActivity extends BtBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int BT_ACTIVITY_ID = 7;
    public static final byte CMD_UPDATE_PLAY_POSITION = 2;
    public static final byte CMD_UPDATE_PLAY_STATUS = 1;
    private static final String TAG = "BtMusicActivity";
    public Boolean D = true;
    private BtExe bt = BtExe.getBtInstance();
    private TextView mTvAlbum;
    private TextView mTvArtist;
    private TextView mTvName;
    private String musicAlbum = null;
    private String musicArtist = null;
    private String musicTitle = null;
    ImageButton mutebutton;
    ImageButton nextbutton;
    ImageButton pausebutton;
    ImageButton playbutton;
    ImageButton prevbutton;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt_music);
        initView();
        if (!this.bt.isConnected()) {
            showActivity(1);
            finish();
        } else if (this.D.booleanValue()) {
            Log.d(TAG, "+ onCreat()+");
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (this.D.booleanValue()) {
            Log.d(TAG, "+ onStart()+");
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        if (this.D.booleanValue()) {
            Log.d(TAG, "+ onPause()+");
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        SubItemsInit(this, 7);
        if (this.isShowActivity) {
            EnterSubFocus();
        }
        if (this.D.booleanValue()) {
            Log.d(TAG, "+ onResume()+");
        }
        this.bt.regMetadataCallback();
        Evc.GetInstance().evol_workmode_set(5);
        if (this.bt.getMusicState() != 1) {
            this.bt.musicPlay();
        }
        UpdateUI();
        MainTask.GetInstance().SetUserCallBack(this);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        if (this.D.booleanValue()) {
            Log.d(TAG, "+ onStop()+");
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_music_play) {
            if (BtExe.mCallSta <= 1) {
                this.bt.musicPlay();
            }
        } else if (id == R.id.btn_music_pause) {
            this.bt.musicPause();
        } else if (id == R.id.btn_music_prev) {
            this.bt.musicPrev();
        } else if (id == R.id.btn_music_next) {
            this.bt.musicNext();
        } else if (id == R.id.btn_music_mute) {
            MainVolume.GetInstance().VolWinShow();
        }
        this.mbSubFocus = 2;
        updateFocus(v);
    }

    private void initView() {
        this.mFocusView = new View[5];
        this.mutebutton = (ImageButton) findViewById(R.id.btn_music_mute);
        this.pausebutton = (ImageButton) findViewById(R.id.btn_music_pause);
        this.playbutton = (ImageButton) findViewById(R.id.btn_music_play);
        this.mutebutton.setOnClickListener(this);
        this.pausebutton.setOnClickListener(this);
        this.playbutton.setOnClickListener(this);
        this.prevbutton = (ImageButton) findViewById(R.id.btn_music_prev);
        this.prevbutton.setOnClickListener(this);
        this.nextbutton = (ImageButton) findViewById(R.id.btn_music_next);
        this.nextbutton.setOnClickListener(this);
        this.mTvName = (TextView) findViewById(R.id.tv_music_title);
        this.mTvAlbum = (TextView) findViewById(R.id.tv_music_icon_album);
        this.mTvArtist = (TextView) findViewById(R.id.tv_music_icon_artist);
        this.mFocusView[0] = this.prevbutton;
        this.mFocusView[1] = this.playbutton;
        this.mFocusView[2] = this.pausebutton;
        this.mFocusView[3] = this.nextbutton;
        this.mFocusView[4] = this.mutebutton;
    }

    public void onTimer() {
        if (BtExe.mIsId3Update) {
            UpdateUI();
            BtExe.mIsId3Update = false;
        }
        if (!this.bt.isConnected()) {
            showActivity(1);
            finish();
        }
    }

    /* access modifiers changed from: package-private */
    public void resetData() {
        this.musicTitle = null;
        this.musicAlbum = null;
        this.musicArtist = null;
    }

    private void UpdateUI() {
        if (BtExe.mStrId3Name != null) {
            if (BtExe.mStrId3Name.isEmpty()) {
                this.musicTitle = null;
                this.mTvName.setText("NULL");
            } else if (!BtExe.mStrId3Name.equals(this.musicTitle)) {
                this.musicTitle = BtExe.mStrId3Name;
                this.mTvName.setText(BtExe.mStrId3Name);
            }
        }
        if (BtExe.mStrId3Album != null) {
            if (BtExe.mStrId3Album.isEmpty()) {
                this.musicAlbum = null;
                this.mTvAlbum.setText("NULL");
            } else if (!BtExe.mStrId3Album.equals(this.musicAlbum)) {
                this.musicAlbum = BtExe.mStrId3Album;
                this.mTvAlbum.setText(BtExe.mStrId3Album);
            }
        }
        if (BtExe.mStrId3Artist == null) {
            return;
        }
        if (BtExe.mStrId3Artist.isEmpty()) {
            this.musicArtist = null;
            this.mTvArtist.setText("NULL");
        } else if (!BtExe.mStrId3Artist.equals(this.musicArtist)) {
            this.musicArtist = BtExe.mStrId3Artist;
            this.mTvArtist.setText(BtExe.mStrId3Artist);
        }
    }

    public void UserAll() {
        onTimer();
    }

    /* access modifiers changed from: protected */
    public int GetMusicFocusIndex() {
        if (this.mOldFocusView == null) {
            return 0;
        }
        for (int j = 0; j < this.mFocusView.length; j++) {
            if (this.mOldFocusView == this.mFocusView[j]) {
                return j;
            }
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void EnterSubFocus() {
        this.mbSubFocus = 2;
        updateFocus(this.mFocusView[2]);
    }

    /* access modifiers changed from: protected */
    public void MusicPrev() {
        int index;
        int index2 = GetMusicFocusIndex();
        if (index2 > 0) {
            index = index2 - 1;
        } else {
            index = this.mFocusView.length - 1;
        }
        Log.d(TAG, "GetMusicFocusIndex LtPrev = " + index);
        updateFocus(this.mFocusView[index]);
    }

    /* access modifiers changed from: protected */
    public void MusicNext() {
        int index;
        int index2 = GetMusicFocusIndex();
        if (index2 < this.mFocusView.length - 1) {
            index = index2 + 1;
        } else {
            index = 0;
        }
        Log.d(TAG, "GetMusicFocusIndex LtNext = " + index);
        updateFocus(this.mFocusView[index]);
    }

    /* access modifiers changed from: protected */
    public void MusicCenter() {
        this.mFocusView[GetMusicFocusIndex()].performClick();
    }

    /* access modifiers changed from: protected */
    public boolean DealSubKey(int key) {
        if (this.mOrientation == 1) {
            return false;
        }
        switch (key) {
            case 19:
                MusicPrev();
                return true;
            case 20:
                MusicNext();
                return true;
            case 21:
                if (this.mbSubFocus != 2) {
                    return true;
                }
                updateFocus(this.mFocusView[2]);
                return true;
            case 23:
                MusicCenter();
                return true;
            default:
                return true;
        }
    }
}
