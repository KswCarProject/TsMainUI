package com.ts.can.honda.accord;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemBlankTextList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.FtSet;

public class CanAccordCamModeActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange, CanItemPopupList.onPopItemClick {
    private static final int ITEM_MAX = 6;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_MODE_BZ = 5;
    public static final int ITEM_MODE_FJ = 6;
    public static final int ITEM_MODE_GJ = 4;
    public static final int ITEM_MODE_TITLE = 3;
    public static final int ITEM_SCR_COLOR = 2;
    public static final int ITEM_SCR_LIGHT = 1;
    public static final String TAG = "CanAccordCamModeActivity";
    public static final int[] mColorArr = {R.string.can_color_amber, R.string.can_color_red, R.string.can_color_violet, R.string.can_color_blue};
    protected ParamButton mBtnBz;
    protected ParamButton mBtnFj;
    protected ParamButton mBtnGj;
    protected CustomImgView[] mImgLight;
    protected CanItemPopupList mItemColor;
    protected CanItemTextBtnList mItemLight;
    protected CanItemBlankTextList mItemModeTitle;
    protected RelativeLayoutManager mManager;
    protected CanDataInfo.AccordCamMode mModeData = new CanDataInfo.AccordCamMode();
    protected CanDataInfo.AccordScrta mScrData = new CanDataInfo.AccordScrta();
    protected boolean mfgJump;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mItemLight = new CanItemTextBtnList((Context) this, R.string.can_car_scr_light);
        this.mItemLight.ShowArrow(false);
        this.mItemLight.SetIdClickListener(this, 1);
        this.mManager.AddView(this.mItemLight.GetView(), 0, 10, -2, 85);
        this.mImgLight = new CustomImgView[2];
        for (int i = 0; i < this.mImgLight.length; i++) {
            this.mImgLight[i] = new CustomImgView(this);
            this.mImgLight[i].setStateDrawable(R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
            AddViewWrapContent((RelativeLayout) this.mItemLight.GetView(), this.mImgLight[i], (i * 40) + CanCameraUI.BTN_CS75_WC_MODE, 35);
        }
        this.mItemColor = new CanItemPopupList((Context) this, R.string.can_car_color, mColorArr, (CanItemPopupList.onPopItemClick) this);
        this.mItemColor.SetId(2);
        this.mManager.AddView(this.mItemColor.GetView(), 0, 95, -2, 85);
        this.mBtnGj = this.mManager.AddButton(68, 265);
        this.mBtnGj.setTag(4);
        this.mBtnGj.setDrawable(R.drawable.can_yg_mode_gj_up, R.drawable.can_yg_mode_gj_dn);
        this.mBtnGj.setOnClickListener(this);
        this.mBtnBz = this.mManager.AddButton(387, 265);
        this.mBtnBz.setTag(5);
        this.mBtnBz.setDrawable(R.drawable.can_yg_mode_bz_up, R.drawable.can_yg_mode_bz_dn);
        this.mBtnBz.setOnClickListener(this);
        this.mBtnFj = this.mManager.AddButton(705, 265);
        this.mBtnFj.setTag(6);
        this.mBtnFj.setDrawable(R.drawable.can_yg_mode_fj_up, R.drawable.can_yg_mode_fj_dn);
        this.mBtnFj.setOnClickListener(this);
        this.mfgJump = CanFunc.IsCanActivityJumped(this);
        this.mBtnGj.Show(false);
        this.mBtnBz.Show(false);
        this.mBtnFj.Show(false);
    }

    public void AddViewWrapContent(RelativeLayout layout, View view, int x, int y) {
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(-2, -2);
        rl.leftMargin = x;
        rl.topMargin = y;
        view.setLayoutParams(rl);
        layout.addView(view);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        if (this.mfgJump) {
            finish();
        }
        super.onPause();
    }

    private void ResetData(boolean check) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7 = true;
        CanJni.AccordGetScrSta(this.mScrData);
        if (i2b(this.mScrData.UpdateOnce) && (!check || i2b(this.mScrData.Update))) {
            this.mScrData.Update = 0;
            this.mImgLight[0].setSelected(this.mScrData.Val <= 1);
            CustomImgView customImgView = this.mImgLight[1];
            if (this.mScrData.Val == 0) {
                z6 = true;
            } else {
                z6 = false;
            }
            customImgView.setSelected(z6);
        }
        CanJni.AccordGetCamMode(this.mModeData);
        if (i2b(this.mModeData.UpdateOnce) && (!check || i2b(this.mModeData.Update))) {
            this.mModeData.Update = 0;
            ParamButton paramButton = this.mBtnGj;
            if (this.mModeData.Mode == 1) {
                z = true;
            } else {
                z = false;
            }
            paramButton.setSelected(z);
            ParamButton paramButton2 = this.mBtnBz;
            if (this.mModeData.Mode == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            paramButton2.setSelected(z2);
            ParamButton paramButton3 = this.mBtnFj;
            if (this.mModeData.Mode == 2) {
                z3 = true;
            } else {
                z3 = false;
            }
            paramButton3.setSelected(z3);
            ParamButton paramButton4 = this.mBtnGj;
            if (this.mModeData.fgOn == 1) {
                z4 = true;
            } else {
                z4 = false;
            }
            paramButton4.Show(z4);
            ParamButton paramButton5 = this.mBtnBz;
            if (this.mModeData.fgOn == 1) {
                z5 = true;
            } else {
                z5 = false;
            }
            paramButton5.Show(z5);
            ParamButton paramButton6 = this.mBtnFj;
            if (this.mModeData.fgOn != 1) {
                z7 = false;
            }
            paramButton6.Show(z7);
        }
        if (this.mfgJump && this.mModeData.fgOn == 0) {
            finish();
            this.mfgJump = false;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                int light = 0;
                switch (this.mScrData.Val) {
                    case 0:
                        light = 1;
                        break;
                    case 1:
                        light = 2;
                        break;
                }
                CanJni.AccordCarCtrl(66, light);
                FtSet.Setlgb1(light);
                return;
            case 4:
                CanJni.AccordCarCtrl(64, 1);
                return;
            case 5:
                CanJni.AccordCarCtrl(64, 0);
                return;
            case 6:
                CanJni.AccordCarCtrl(64, 2);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
    }

    public void onItem(int id, int item) {
        if (id == 2) {
            CanJni.AccordCarCtrl(65, item);
        }
    }
}
