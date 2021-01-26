package com.ts.can.ford.rzc;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanBaseCarDeviceActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCarInfoSub2Activity;
import com.ts.can.CanIF;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import java.io.UnsupportedEncodingException;

public class CanFordRzcCDView extends CanRelativeCarInfoView {
    public static final int ITEM_FF = 6;
    public static final int ITEM_FR = 1;
    public static final int ITEM_LIST = 9;
    public static final int ITEM_NEXT = 5;
    public static final int ITEM_PAUSE = 4;
    public static final int ITEM_PLAY = 3;
    public static final int ITEM_PREV = 2;
    public static final int ITEM_RDM = 8;
    public static final int ITEM_RPT = 7;
    protected CustomTextView mAlbum;
    protected CustomTextView mArtist;
    protected ParamButton mBtnList;
    protected ParamButton mBtnPause;
    protected ParamButton mBtnPlay;
    protected ParamButton mBtnRdm;
    protected ParamButton mBtnRpt;
    protected CanDataInfo.FordRzcHostCdInfo mCDInfo = new CanDataInfo.FordRzcHostCdInfo();
    protected CanDataInfo.FordRzcHostListText mCDMax = new CanDataInfo.FordRzcHostListText();
    protected CanDataInfo.FordRzcHostCdTime mCDTime = new CanDataInfo.FordRzcHostCdTime();
    protected CanDataInfo.FordRzcHostInfo mHostInfo = new CanDataInfo.FordRzcHostInfo();
    protected RelativeLayoutManager mManager;
    protected CustomTextView mSong;
    protected CustomTextView mSta;
    protected String[] mStrSta;
    protected CustomTextView mTime;
    protected CustomTextView mTrack;

    public CanFordRzcCDView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 9:
                enterSubWin(CanCarInfoSub2Activity.class, -1);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        initCommonScreen();
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
    }

    public void doOnFinish() {
        super.doOnFinish();
    }

    public void doOnPause() {
        super.doOnPause();
    }

    private void initCommonScreen() {
        this.mManager = getRelativeManager();
        setBackgroundResource(R.drawable.can_jeep_ycsb_bg);
        this.mBtnPlay = AddBtn(3, 391, 424, R.drawable.can_jeep_ycsb_play_up, R.drawable.can_jeep_ycsb_play_dn);
        this.mBtnPause = AddBtn(4, CanCameraUI.BTN_GEELY_YJX6_GJ, 424, R.drawable.can_jeep_ycsb_pause_up, R.drawable.can_jeep_ycsb_pause_dn);
        this.mBtnRpt = AddBtn(7, 46, 25, R.drawable.can_jeep_ycsb_cycle_up, R.drawable.can_jeep_ycsb_cycle_dn);
        this.mBtnRdm = AddBtn(8, 180, 25, R.drawable.can_jeep_ycsb_random_up, R.drawable.can_jeep_ycsb_random_dn);
        this.mBtnList = AddBtn(9, 850, 25, R.drawable.can_jeep_ycsb_list_up, R.drawable.can_jeep_ycsb_list_dn);
        this.mSta = AddTxtLt(84, 124, 300, 36);
        this.mManager.AddImage(44, 184, R.drawable.can_jeep_ycsb_music);
        this.mManager.AddImage(44, Can.CAN_MZD_LUOMU, R.drawable.can_jeep_ycsb_disc);
        this.mSong = AddTxtLt(84, 181, 600, 36);
        this.mAlbum = AddTxtLt(84, Can.CAN_GM_CAPTIVA_OD, 600, 36);
        this.mArtist = AddTxtLt(84, 296, 600, 36);
        this.mTrack = AddTxtCenter(412, 33, 200, 35);
        this.mTime = AddTxtCenter(362, 351, 300, 35);
        this.mStrSta = getStringArray(R.array.can_ford_car_play_sta);
    }

    public void ResetData(boolean check) {
        boolean z;
        CanJni.FordRzcGetHostInfo(this.mHostInfo);
        CanJni.FordRzcGetCdInfo(this.mCDInfo);
        CanJni.FordRzcGetCdTime(this.mCDTime);
        if (i2b(this.mHostInfo.CdUpdateOnce)) {
            if (i2b(this.mHostInfo.Update)) {
                this.mHostInfo.Update = 0;
                if (this.mHostInfo.Src == 1) {
                    enterSubWin(CanBaseCarDeviceActivity.class, -1);
                }
            }
            if (!check || i2b(this.mHostInfo.CdUpdate)) {
                this.mHostInfo.CdUpdate = 0;
                if (this.mHostInfo.TotalTrack <= 0 || this.mHostInfo.TotalTrack < this.mHostInfo.Track) {
                    this.mTrack.setText(" ");
                } else {
                    this.mTrack.setText(String.format("%d/%d", new Object[]{Integer.valueOf(this.mHostInfo.Track), Integer.valueOf(this.mHostInfo.TotalTrack)}));
                }
            }
        }
        if (i2b(this.mCDTime.UpdateOnce) && (!check || i2b(this.mCDTime.Update))) {
            this.mCDTime.Update = 0;
            if (this.mCDTime.TotalHour <= 0 || this.mCDTime.TotalHour < this.mCDTime.Hour) {
                this.mTime.setText(String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(this.mCDTime.Hour), Integer.valueOf(this.mCDTime.Min), Integer.valueOf(this.mCDTime.Sec)}));
            } else {
                this.mTime.setText(String.format("%02d:%02d:%02d / %02d:%02d:%02d", new Object[]{Integer.valueOf(this.mCDTime.Hour), Integer.valueOf(this.mCDTime.Min), Integer.valueOf(this.mCDTime.Sec), Integer.valueOf(this.mCDTime.TotalHour), Integer.valueOf(this.mCDTime.TotalMin), Integer.valueOf(this.mCDTime.TotalSec)}));
            }
        }
        if (i2b(this.mCDInfo.UpdateOnce) && (!check || i2b(this.mCDInfo.Update))) {
            this.mCDInfo.Update = 0;
            this.mBtnRdm.SetSel(this.mCDInfo.Rdm);
            this.mBtnRpt.SetSel(this.mCDInfo.Rpm);
            this.mBtnPlay.setSelected(this.mCDInfo.Pause == 1);
            ParamButton paramButton = this.mBtnPause;
            if (this.mCDInfo.Pause == 0) {
                z = true;
            } else {
                z = false;
            }
            paramButton.setSelected(z);
            if (this.mCDInfo.Sta == 0 || this.mCDInfo.Sta == 1 || this.mCDInfo.Sta == 2 || this.mCDInfo.Sta == 3) {
                this.mSta.setText(this.mStrSta[this.mCDInfo.Sta]);
            } else if (this.mCDInfo.Sta == 255) {
                this.mSta.setText(this.mStrSta[4]);
            }
        }
        CanJni.FordRzcGetCdId3Info(this.mCDMax, 0);
        if (i2b(this.mCDMax.UpdateOnce) && (!check || i2b(this.mCDMax.Update))) {
            this.mCDMax.Update = 0;
            this.mSong.setText(byte2String(this.mCDMax.Type, this.mCDMax.Text, this.mCDMax.Text.length));
        }
        CanJni.FordRzcGetCdId3Info(this.mCDMax, 1);
        if (!i2b(this.mCDMax.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCDMax.Update)) {
            this.mCDMax.Update = 0;
            this.mAlbum.setText(byte2String(this.mCDMax.Type, this.mCDMax.Text, this.mCDMax.Text.length));
        }
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTxtLt(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPixelSize(30);
        temp.setGravity(19);
        return temp;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTxtCenter(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPixelSize(30);
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

    public void QueryData() {
    }

    private String byte2ASCIIString(byte[] data, int len) {
        try {
            return new String(data, 0, len, "GBK");
        } catch (UnsupportedEncodingException e) {
            return TXZResourceManager.STYLE_DEFAULT;
        }
    }

    private String byte2String(int encode, byte[] data, int len) {
        if (encode == 0) {
            return CanIF.byte2UnicodeStr(data);
        }
        return byte2ASCIIString(data, len);
    }
}
