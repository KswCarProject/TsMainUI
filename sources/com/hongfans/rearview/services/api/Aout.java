package com.hongfans.rearview.services.api;

import android.media.AudioTrack;
import android.os.Parcel;
import android.os.Parcelable;

public class Aout implements Parcelable {
    public static final Parcelable.Creator<Aout> CREATOR = new Parcelable.Creator<Aout>() {
        public Aout createFromParcel(Parcel in) {
            return new Aout(in);
        }

        public Aout[] newArray(int size) {
            return new Aout[size];
        }
    };
    private static final String TAG = "AudioPlayer/aout";
    private int[] height = new int[80];
    private AudioTrack mAudioTrack;
    private float maxVolume = 0.0f;
    private float minVolume = 0.0f;
    private int numBands = 80;
    public int playing_progress = 0;

    public Aout() {
    }

    public Aout(Parcel pl) {
        this.numBands = pl.readInt();
        pl.readIntArray(this.height);
    }

    public void changeVolume(int ramp) {
        if (this.mAudioTrack != null) {
            float volume = this.minVolume + ((((float) ramp) * (this.maxVolume - this.minVolume)) / 16.0f);
            this.mAudioTrack.setStereoVolume(volume, volume);
        }
    }

    public void initAout(int sampleRateInHz, int channels, int samples) {
        int i;
        int i2 = 12;
        if (channels == 1) {
            i = 4;
        } else {
            i = 12;
        }
        int minBufferSize = AudioTrack.getMinBufferSize(sampleRateInHz, i, 2);
        if (channels == 1) {
            i2 = 4;
        }
        this.mAudioTrack = new AudioTrack(3, sampleRateInHz, i2, 2, Math.max(minBufferSize, channels * samples * 2), 1);
        AudioTrack audioTrack = this.mAudioTrack;
        this.maxVolume = AudioTrack.getMaxVolume();
        AudioTrack audioTrack2 = this.mAudioTrack;
        this.minVolume = AudioTrack.getMinVolume();
    }

    public void closeAout() {
        if (this.mAudioTrack != null) {
            this.mAudioTrack.pause();
            this.mAudioTrack.flush();
            this.mAudioTrack.stop();
            this.mAudioTrack.release();
        }
        this.mAudioTrack = null;
    }

    public void playAudio(byte[] audioData, int bufferSize) {
        if (this.mAudioTrack.getState() != 0) {
            if (this.mAudioTrack.write(audioData, 0, bufferSize) != bufferSize) {
            }
            this.mAudioTrack.play();
        }
    }

    public int[] getHeight() {
        return this.height;
    }

    public void setHeight(int[] height2) {
        this.height = height2;
    }

    public int getNumBands() {
        return this.numBands;
    }

    public void setNumBands(int numBands2) {
        this.numBands = numBands2;
    }

    public void showSpectrumHeight(int[] height2, int numBands2, int playing_progress2) {
        try {
            System.arraycopy(height2, 0, this.height, 0, numBands2);
        } catch (IndexOutOfBoundsException e) {
        }
        this.numBands = numBands2;
        this.playing_progress = playing_progress2;
    }

    public void pauseAout() {
        this.mAudioTrack.pause();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.numBands);
        dest.writeIntArray(this.height);
    }

    public int describeContents() {
        return 0;
    }
}
