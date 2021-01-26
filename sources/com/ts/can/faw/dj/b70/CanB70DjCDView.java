package com.ts.can.faw.dj.b70;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.txznet.sdk.TXZResourceManager;

public class CanB70DjCDView extends CanRelativeCarInfoView {
    public static final int ITEM_PAUSE = 2;
    public static final int ITEM_PLAY = 1;
    protected static String[] mModeSta = {TXZResourceManager.STYLE_DEFAULT, "顺序播放", "重复播放", "扫描", "随机播放"};
    protected ParamButton mBtnPause;
    protected ParamButton mBtnPlay;
    protected CanDataInfo.B70_Dj_CdInfo mInfoData;
    protected CustomTextView mMode;
    protected CustomTextView mSta;
    protected CustomTextView mTime;
    protected CustomTextView mTrack;

    public CanB70DjCDView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        initCommonScreen();
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
    }

    private void initCommonScreen() {
        this.mInfoData = new CanDataInfo.B70_Dj_CdInfo();
        setBackgroundResource(R.drawable.can_jeep_ycsb_bg02);
        this.mBtnPlay = AddBtn(1, 391, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_play_up, R.drawable.can_jeep_ycsb_play_dn);
        this.mBtnPause = AddBtn(2, CanCameraUI.BTN_GEELY_YJX6_GJ, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.can_jeep_ycsb_pause_up, R.drawable.can_jeep_ycsb_pause_dn);
        this.mTrack = AddTxtCenter(362, 110, 300, 60);
        this.mMode = AddTxtCenter(362, 200, 300, 60);
        this.mTime = AddTxtCenter(362, 290, 300, 60);
    }

    public void ResetData(boolean check) {
        boolean z;
        CanJni.B70DjGetCdInfo(this.mInfoData);
        if (!i2b(this.mInfoData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mInfoData.Update)) {
            this.mInfoData.Update = 0;
            this.mTrack.setText("Track:" + String.format("%d", new Object[]{Integer.valueOf(this.mInfoData.Track)}));
            this.mTime.setText(String.format("%02d:%02d", new Object[]{Integer.valueOf(this.mInfoData.Min), Integer.valueOf(this.mInfoData.Sec)}));
            this.mBtnPlay.setSelected(this.mInfoData.Pp == 1);
            ParamButton paramButton = this.mBtnPause;
            if (this.mInfoData.Pp == 2) {
                z = true;
            } else {
                z = false;
            }
            paramButton.setSelected(z);
            if (this.mInfoData.Mode == 0) {
                this.mMode.ShowGone(false);
                return;
            }
            this.mMode.ShowGone(true);
            if (this.mInfoData.Mode < mModeSta.length) {
                this.mMode.setText(mModeSta[this.mInfoData.Mode]);
            }
        }
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTxtLt(int x, int y, int w, int h) {
        CustomTextView temp = getRelativeManager().AddCusText(x, y, w, h);
        temp.SetPixelSize(30);
        temp.setGravity(19);
        return temp;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTxtCenter(int x, int y, int w, int h) {
        CustomTextView temp = getRelativeManager().AddCusText(x, y, w, h);
        temp.SetPixelSize(40);
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public void CdCtrl(int cmd, int part1) {
        CanJni.CrownWcCdSet(cmd, part1);
    }

    public void QueryData() {
        CanJni.B70DjQueryInfo(2, 0);
    }
}
