package com.ts.can.nissan.xc.teana;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanTeanaOldXcCDView extends CanRelativeCarInfoView {
    public static final int ITEM_CAMERA = 8;
    public static final int ITEM_NEXT = 4;
    public static final int ITEM_PAUSE = 3;
    public static final int ITEM_PLAY = 2;
    public static final int ITEM_PREV = 1;
    public static final int ITEM_RDM = 6;
    public static final int ITEM_RPT = 5;
    public static final int ITEM_STOP = 7;
    protected static int mOldSta = -1;
    private boolean isCamera = false;
    protected ParamButton mBtnCamera;
    protected ParamButton mBtnNext;
    protected ParamButton mBtnPause;
    protected ParamButton mBtnPlay;
    protected ParamButton mBtnPrev;
    protected ParamButton mBtnStop;
    private AutoFitTextureView mCameraView;
    private CanDataInfo.TeanaOldXc_Dvd mDvdInfo;
    protected CustomImgView mImgSta;
    protected RelativeLayoutManager mManager;
    private SharedPreferences mSharedPreferences;
    protected CustomTextView mSta;
    protected int mStartCount = 0;
    protected String[] mStrSta;
    protected CustomTextView mTime;
    protected CustomTextView mTrack;
    private View view1;

    public CanTeanaOldXcCDView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View view, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) view.getTag()).intValue();
        if (action == 0) {
            CdSet(Id, 1);
        } else if (1 == action) {
            CdSet(Id, 0);
        }
        return false;
    }

    private void CdSet(int id, int para) {
        switch (id) {
            case 1:
                CanJni.TeanOldXcDvdCmd(5, para);
                return;
            case 2:
                CanJni.TeanOldXcDvdCmd(1, para);
                return;
            case 3:
                CanJni.TeanOldXcDvdCmd(2, para);
                return;
            case 4:
                CanJni.TeanOldXcDvdCmd(4, para);
                return;
            case 7:
                CanJni.TeanOldXcDvdCmd(3, para);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 8:
                initVisible();
                return;
            default:
                return;
        }
    }

    private void initVisible() {
        int visible;
        if (this.view1.getVisibility() == 8) {
            visible = 8;
        } else {
            visible = 0;
        }
        if (this.view1.getVisibility() == 8) {
            this.isCamera = true;
            this.view1.setVisibility(0);
        } else {
            this.isCamera = false;
            this.view1.setVisibility(8);
        }
        SharedPreferences.Editor editor = this.mSharedPreferences.edit();
        editor.putBoolean("isCamera", this.isCamera);
        editor.commit();
        this.mBtnPrev.setVisibility(visible);
        this.mBtnPlay.setVisibility(visible);
        this.mBtnPause.setVisibility(visible);
        this.mBtnNext.setVisibility(visible);
        this.mBtnStop.setVisibility(visible);
        this.mImgSta.setVisibility(visible);
        this.mSta.setVisibility(visible);
        this.mTrack.setVisibility(visible);
        this.mTime.setVisibility(visible);
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
        MainSet.GetInstance().SetVideoChannel(2);
        BackcarService.getInstance().StartCamera(this.mCameraView, true);
    }

    public void doOnPause() {
        super.doOnPause();
        BackcarService.getInstance().StopCamera();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = getRelativeManager();
        Activity activity = getActivity();
        getActivity();
        this.mSharedPreferences = activity.getSharedPreferences("CanTeanaOldXcCDView", 0);
        this.isCamera = this.mSharedPreferences.getBoolean("isCamera", false);
        this.mDvdInfo = new CanDataInfo.TeanaOldXc_Dvd();
        this.mStrSta = getStringArray(R.array.can_teanaoldxc_car_play_sta);
        BaseUI();
        if (this.isCamera) {
            initVisible();
        }
    }

    private void BaseUI() {
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_jeep_ycsb_bg);
        this.view1 = View.inflate(getActivity().getApplicationContext(), R.layout.activity_can_withcd_base, (ViewGroup) null);
        getRelativeManager().AddViewWrapContent(this.view1, 0, 0);
        this.mCameraView = (AutoFitTextureView) getActivity().findViewById(R.id.DevtextureView);
        this.view1.setVisibility(8);
        this.mBtnPrev = AddBtn(1, 197, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.original_car_seekup_up, R.drawable.original_car_seekup_dn);
        this.mBtnPlay = AddBtn(2, KeyDef.RKEY_RDS_PTY, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.original_car_play_up, R.drawable.original_car_play_dn);
        this.mBtnStop = AddBtn(7, 466, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.original_car_stop_up, R.drawable.original_car_stop_dn);
        this.mBtnPause = AddBtn(3, 600, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.original_car_pause_up, R.drawable.original_car_pause_dn);
        this.mBtnNext = AddBtn(4, 734, CanCameraUI.BTN_TRUMPCHI_GS4_MODE5, R.drawable.original_car_seekdn_up, R.drawable.original_car_seekdn_dn);
        this.mBtnCamera = AddBtn(8, 46, 25, R.drawable.can_jeep_ac_kong_up, R.drawable.can_jeep_ac_kong_dn);
        this.mBtnCamera.setText("Vedio");
        this.mBtnCamera.setTextColor(-1);
        this.mImgSta = this.mManager.AddImage(44, 127, R.drawable.can_jeep_ycsb_sjx);
        this.mSta = AddLeftText(84, 119, 300, 42);
        this.mTrack = AddCenterText(KeyDef.RKEY_MEDIA_OSD, 23, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 55);
        this.mTrack.SetPixelSize(40);
        this.mTime = AddCenterText(KeyDef.RKEY_MEDIA_OSD, KeyDef.RKEY_res4, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 35);
    }

    public void ResetData(boolean check) {
        CanJni.TeanOldXcGetDvdInfo(this.mDvdInfo);
        if (!i2b(this.mDvdInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDvdInfo.Update)) {
            this.mDvdInfo.Update = 0;
            if (this.mDvdInfo.Bfzt >= this.mStrSta.length) {
                this.mSta.setText(" ");
            } else {
                this.mSta.setText(this.mStrSta[this.mDvdInfo.Bfzt]);
            }
            if (this.mDvdInfo.Bfzt != 3) {
                this.mTrack.setText(String.format("TRACK %d", new Object[]{Integer.valueOf(this.mDvdInfo.Track)}));
                this.mTime.setText(String.format("%02d:%02d", new Object[]{Integer.valueOf(this.mDvdInfo.Min), Integer.valueOf(this.mDvdInfo.Sec)}));
                return;
            }
            resetText();
        }
    }

    private void resetText() {
        this.mTrack.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mTime.setText(TXZResourceManager.STYLE_DEFAULT);
    }

    public void QueryData() {
        CanJni.TeanOldXcQuery(18);
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddLeftText(int x, int y, int w, int h) {
        CustomTextView item = this.mManager.AddCusText(x, y, w, h);
        item.SetPixelSize(30);
        item.setGravity(19);
        return item;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddCenterText(int x, int y, int w, int h) {
        CustomTextView item = AddLeftText(x, y, w, h);
        item.setGravity(17);
        return item;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setOnTouchListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setOnTouchListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }
}
