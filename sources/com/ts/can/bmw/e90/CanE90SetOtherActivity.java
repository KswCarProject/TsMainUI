package com.ts.can.bmw.e90;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.canview.CanItemIcoList;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanE90SetOtherActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, View.OnTouchListener {
    public static final int ITEM_LTHOT = 2;
    public static final int ITEM_RADAR = 1;
    public static final int ITEM_RTHOT = 3;
    public static final int ITEM_WIND = 4;
    public static final String TAG = "CanE90SetOtherActivity";
    protected ParamButton mBtnLtHot;
    protected ParamButton mBtnRadar;
    protected ParamButton mBtnRtHot;
    protected ParamButton mBtnWind;
    protected CanDataInfo.BMW_CtrlInfo mCtrlData = new CanDataInfo.BMW_CtrlInfo();
    protected CustomImgView[] mImgLtHot = new CustomImgView[3];
    protected CustomImgView[] mImgRtHot = new CustomImgView[3];
    protected CanItemIcoList mItemRadar;
    protected RelativeLayoutManager mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        if (MainSet.GetScreenType() == 5) {
            InitUI_1280x480();
        } else {
            InitUI();
        }
    }

    private void InitUI() {
        this.mBtnLtHot = AddBtn(2, 183, 70, R.drawable.can_e90_lthot_up, R.drawable.can_e90_lthot_dn);
        this.mBtnRtHot = AddBtn(3, CanCameraUI.BTN_GOLF_WC_MODE4, 70, R.drawable.can_e90_rthot_up, R.drawable.can_e90_rthot_dn);
        this.mBtnWind = AddBtn(4, 183, KeyDef.RKEY_MEDIA_SLOW, R.drawable.can_e90_window_up, R.drawable.can_e90_window_dn);
        this.mBtnRadar = AddBtn(1, CanCameraUI.BTN_GOLF_WC_MODE4, KeyDef.RKEY_MEDIA_SLOW, R.drawable.can_e90_leida_up, R.drawable.can_e90_leida_dn);
        this.mBtnRadar.setText(R.string.title_activity_can_radar);
        this.mBtnWind.setText(R.string.can_curtain);
        this.mManager.AddImage(KeyDef.RKEY_FF, 148, R.drawable.can_e90_fire);
        this.mManager.AddImage(713, 148, R.drawable.can_e90_fire);
        for (int i = 0; i < 3; i++) {
            this.mImgLtHot[i] = this.mManager.AddImage((i * 39) + Can.CAN_FORD_MONDEO_XFY, 185);
            this.mImgLtHot[i].setStateDrawable(R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
            this.mImgRtHot[i] = this.mManager.AddImage((i * 39) + 665, 185);
            this.mImgRtHot[i].setStateDrawable(R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
        }
    }

    private void InitUI_1280x480() {
        this.mBtnLtHot = AddBtn(2, 95, 104, R.drawable.can_e90_lthot_up, R.drawable.can_e90_lthot_dn);
        this.mBtnRtHot = AddBtn(3, 374, 104, R.drawable.can_e90_rthot_up, R.drawable.can_e90_rthot_dn);
        this.mBtnWind = AddBtn(4, 654, 104, R.drawable.can_e90_window_up, R.drawable.can_e90_window_dn);
        this.mBtnRadar = AddBtn(1, 937, 104, R.drawable.can_e90_leida_up, R.drawable.can_e90_leida_dn);
        this.mBtnRadar.setText(R.string.title_activity_can_radar);
        this.mBtnWind.setText(R.string.can_curtain);
        this.mManager.AddImage(Can.CAN_LEXUS_IZ, 182, R.drawable.can_e90_fire);
        this.mManager.AddImage(484, 182, R.drawable.can_e90_fire);
        for (int i = 0; i < 3; i++) {
            this.mImgLtHot[i] = this.mManager.AddImage((i * 39) + Can.CAN_FORD_WC, 219);
            this.mImgLtHot[i].setStateDrawable(R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
            this.mImgRtHot[i] = this.mManager.AddImage((i * 39) + 436, 219);
            this.mImgRtHot[i].setStateDrawable(R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Log.d(TAG, "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnTouchListener(this);
        btn.setDrawable(up, dn);
        btn.setPadding(0, 104, 0, 0);
        btn.setTextSize(0, 30.0f);
        btn.setTextColor(-1);
        return btn;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTxtLt(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPixelSize(30);
        temp.setGravity(19);
        return temp;
    }

    private void ResetData(boolean check) {
        CanJni.E90GetCtrlData(this.mCtrlData);
        if (i2b(this.mCtrlData.UpdateOnce)) {
            if (!check || i2b(this.mCtrlData.Update)) {
                this.mCtrlData.Update = 0;
            }
            int lthot = this.mCtrlData.LtHot;
            int rthot = this.mCtrlData.RtHot;
            if (lthot < 0 || lthot > 3) {
                lthot = 0;
            }
            if (rthot < 0 || rthot > 3) {
                rthot = 0;
            }
            for (int i = 0; i < lthot; i++) {
                this.mImgLtHot[i].setSelected(true);
            }
            for (int i2 = lthot; i2 < 3; i2++) {
                this.mImgLtHot[i2].setSelected(false);
            }
            for (int i3 = 0; i3 < rthot; i3++) {
                this.mImgRtHot[i3].setSelected(true);
            }
            for (int i4 = rthot; i4 < 3; i4++) {
                this.mImgRtHot[i4].setSelected(false);
            }
            this.mBtnRadar.SetSel(this.mCtrlData.fgRadarOn);
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int id = ((Integer) v.getTag()).intValue();
        if (action == 0) {
            Log.d(TAG, "****ACTION_DOWN*****");
            switch (id) {
                case 1:
                    KeyDown(3);
                    return false;
                case 2:
                    KeyDown(1);
                    return false;
                case 3:
                    KeyDown(2);
                    return false;
                case 4:
                    KeyDown(4);
                    return false;
                default:
                    return false;
            }
        } else if (1 != action) {
            return false;
        } else {
            Log.d(TAG, "****ACTION_UP*****");
            switch (id) {
                case 1:
                    KeyRel(3);
                    return false;
                case 2:
                    KeyRel(1);
                    return false;
                case 3:
                    KeyRel(2);
                    return false;
                case 4:
                    KeyRel(4);
                    return false;
                default:
                    return false;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void KeyDown(int key) {
        CanJni.E90KeySend(key, 1);
    }

    /* access modifiers changed from: protected */
    public void KeyRel(int key) {
        CanJni.E90KeySend(key, 0);
    }
}
