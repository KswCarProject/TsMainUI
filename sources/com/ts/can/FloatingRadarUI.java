package com.ts.can;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.backcar.BackCarSound;
import com.yyw.ts70xhw.FtSet;

public class FloatingRadarUI {
    private static final String TAG = "FloatingRadarUI";
    private static int nOldRadarVoice = 255;
    private static FloatingRadarUI sInstance = null;
    private View mContentView;
    private WindowManager.LayoutParams mLayoutParams;
    private CanDataInfo.Ehs3T3_Radar mRadarData = new CanDataInfo.Ehs3T3_Radar();
    private FloatingRadarView mRadarView;
    private int mTime;
    private WindowManager mWindowManager;

    private FloatingRadarUI() {
        Context context = MyApplication.getContext();
        initRadarView(context);
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mLayoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams.width = -1;
        this.mLayoutParams.height = -1;
        this.mLayoutParams.flags = 1032;
        this.mLayoutParams.format = 1;
        this.mLayoutParams.type = 2003;
        this.mLayoutParams.systemUiVisibility = CanCameraUI.BTN_FORD_RZC_PXBC;
    }

    private void initRadarView(Context context) {
        this.mContentView = View.inflate(context, R.layout.floating_radar_layout, (ViewGroup) null);
        this.mRadarView = (FloatingRadarView) this.mContentView.findViewById(R.id.radar_view);
    }

    public static FloatingRadarUI getInstance() {
        if (sInstance == null) {
            sInstance = new FloatingRadarUI();
        }
        return sInstance;
    }

    public void UserAll() {
        if (this.mTime >= 20) {
            this.mTime = 0;
            show();
        }
        this.mTime++;
    }

    public void show() {
        if (FtSet.Getyw9() <= 0) {
            this.mTime = 0;
            CanJni.Ehs3T3GetRadarInfo(this.mRadarData);
            if (this.mRadarData.UpdateOnce != 0 && this.mRadarData.Update != 0) {
                this.mRadarData.Update = 0;
                if (this.mRadarData.Dw == 1) {
                    Log.d(TAG, "updateRadar");
                    updateRadar(this.mRadarData.Data);
                    int temp = 0;
                    if (this.mRadarData.Data[0] == 1 || this.mRadarData.Data[1] == 1 || this.mRadarData.Data[2] == 1 || this.mRadarData.Data[3] == 1 || this.mRadarData.Data[4] == 1 || this.mRadarData.Data[5] == 1 || this.mRadarData.Data[6] == 1 || this.mRadarData.Data[7] == 1) {
                        temp = 9;
                    }
                    if (this.mRadarData.Data[0] == 2 || this.mRadarData.Data[1] == 2 || this.mRadarData.Data[2] == 2 || this.mRadarData.Data[3] == 2 || this.mRadarData.Data[4] == 2 || this.mRadarData.Data[5] == 2 || this.mRadarData.Data[6] == 2 || this.mRadarData.Data[7] == 2) {
                        temp = 6;
                    }
                    if (this.mRadarData.Data[0] == 3 || this.mRadarData.Data[1] == 3 || this.mRadarData.Data[2] == 3 || this.mRadarData.Data[3] == 3 || this.mRadarData.Data[4] == 3 || this.mRadarData.Data[5] == 3 || this.mRadarData.Data[6] == 3 || this.mRadarData.Data[7] == 3) {
                        temp = 3;
                    }
                    if (nOldRadarVoice != temp) {
                        nOldRadarVoice = temp;
                        Log.d(TAG, "mRadarVoice=" + nOldRadarVoice);
                        if (temp == 0) {
                            BackCarSound.GetInstance().PlayRadar(false, 0);
                        } else {
                            BackCarSound.GetInstance().PlayRadar(true, nOldRadarVoice);
                        }
                    }
                } else {
                    Log.d(TAG, "dismiss");
                    nOldRadarVoice = 255;
                    BackCarSound.GetInstance().PlayRadar(false, 0);
                    dismiss();
                }
            }
        }
    }

    private void updateRadar(int[] data) {
        if (this.mContentView != null && this.mContentView.getParent() == null) {
            this.mWindowManager.addView(this.mContentView, this.mLayoutParams);
        }
        if (this.mRadarView != null) {
            this.mRadarView.updateRadar(data);
        }
    }

    private void dismiss() {
        if (this.mContentView != null && this.mContentView.getParent() != null) {
            this.mWindowManager.removeView(this.mContentView);
        }
    }
}
