package com.ts.can.gm.onstar;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.can.CanIF;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanVerticalBar;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanOnStarNaviActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_CANCEL = 7;
    public static final int ITEM_DES_INFO = 5;
    public static final int ITEM_NEXT_STA = 4;
    public static final int ITEM_PREVVIEW = 2;
    public static final int ITEM_PREV_STA = 3;
    public static final int ITEM_REPEAT = 1;
    public static final int ITEM_UPDATE = 6;
    public static final String TAG = "CanOnStarNaviActivity";
    protected static final int[] mDirectArr = {R.drawable.can_onstar_indicate00, R.drawable.can_onstar_indicate01, R.drawable.can_onstar_indicate02, R.drawable.can_onstar_indicate03, R.drawable.can_onstar_indicate04, R.drawable.can_onstar_indicate05, R.drawable.can_onstar_indicate06, R.drawable.can_onstar_indicate07, R.drawable.can_onstar_indicate08, R.drawable.can_onstar_indicate09, R.drawable.can_onstar_indicate0a, R.drawable.can_onstar_indicate0b, R.drawable.can_onstar_indicate0c, R.drawable.can_onstar_indicate0d, R.drawable.can_onstar_indicate0e, R.drawable.can_onstar_indicate0f, R.drawable.can_onstar_indicate10, R.drawable.can_onstar_indicate11, R.drawable.can_onstar_indicate12, R.drawable.can_onstar_indicate13, R.drawable.can_onstar_indicate14, R.drawable.can_onstar_indicate15, R.drawable.can_onstar_indicate16, R.drawable.can_onstar_indicate17, R.drawable.can_onstar_indicate18, R.drawable.can_onstar_indicate19, R.drawable.can_onstar_indicate1a, R.drawable.can_onstar_indicate1b, R.drawable.can_onstar_indicate1c, R.drawable.can_onstar_indicate1d, R.drawable.can_onstar_indicate1e, R.drawable.can_onstar_indicate1f, R.drawable.can_onstar_indicate20};
    protected static DisplayMetrics mDisplayMetrics = new DisplayMetrics();
    protected ParamButton mBtnCancel;
    protected ParamButton mBtnDesInfo;
    protected ParamButton mBtnNextSta;
    protected ParamButton mBtnPrevSta;
    protected ParamButton mBtnPreview;
    protected ParamButton mBtnRepeat;
    protected ParamButton mBtnUpdate;
    private CanDataInfo.GM_GpsStr mCommData = new CanDataInfo.GM_GpsStr();
    protected CustomTextView mCommText;
    private CanDataInfo.GM_GpsStr mDesData = new CanDataInfo.GM_GpsStr();
    protected CustomTextView mDesText;
    protected CustomImgView mImgDirect;
    protected RelativeLayoutManager mManager;
    protected CustomTextView mNextDisText;
    protected CanVerticalBar mProgress;
    private String mStrComm;
    private String mStrDes;
    private String mStrNextDis;
    private String mStrTotalRange;
    protected CustomTextView mTotalDisText;
    private CanDataInfo.GM_Trip mTripData = new CanDataInfo.GM_Trip();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        Log.d("nyw", String.format("%d,%d", new Object[]{Integer.valueOf(mDisplayMetrics.widthPixels), Integer.valueOf(mDisplayMetrics.heightPixels)}));
        if (MainSet.GetScreenType() == 3) {
            initScreen_768x1024();
        } else if (MainSet.GetScreenType() == 5) {
            initScreen_1280x480();
        } else {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
            lp.width = 1024;
            lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
            lp.gravity = 17;
            this.mManager.GetLayout().setLayoutParams(lp);
            initCommonScreen();
            this.mManager.GetLayout().setScaleX((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f);
            this.mManager.GetLayout().setScaleY((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f);
            Log.d("nyw", String.format("%.2f,%.2f", new Object[]{Float.valueOf((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f), Float.valueOf((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f)}));
        }
        Resources res = getResources();
        this.mStrDes = res.getString(R.string.can_zdxx);
        this.mStrComm = res.getString(R.string.can_ybxx);
        this.mStrNextDis = res.getString(R.string.can_jxyld);
        this.mStrTotalRange = res.getString(R.string.can_zxc);
    }

    private void initScreen_1280x480() {
        this.mManager.AddImage(150, 10, R.drawable.can_onstar_bg);
        this.mImgDirect = this.mManager.AddImage(260, 43, 116, 117);
        this.mProgress = new CanVerticalBar(this, R.drawable.can_sponstar_pro_dn, R.drawable.can_sponstar_pro_up);
        this.mProgress.setMinMax(0.0f, 100.0f);
        this.mManager.AddViewWrapContent(this.mProgress, 425, 25);
        CustomTextView tv = this.mManager.AddCusText(260, 0, 110, 38);
        tv.setGravity(17);
        tv.setText(R.string.can_onstar_direct);
        tv.SetPxSize(36);
        this.mDesText = addInfoText(CanCameraUI.BTN_YG9_XBS_MODE1, 15, CanCameraUI.BTN_GEELY_YJX6_MODE1, 35);
        this.mCommText = addInfoText(CanCameraUI.BTN_YG9_XBS_MODE1, 52, CanCameraUI.BTN_GEELY_YJX6_MODE1, 35);
        this.mNextDisText = addInfoText(CanCameraUI.BTN_YG9_XBS_MODE1, 105, CanCameraUI.BTN_GEELY_YJX6_MODE1, 35);
        this.mTotalDisText = addInfoText(CanCameraUI.BTN_YG9_XBS_MODE1, 141, CanCameraUI.BTN_GEELY_YJX6_MODE1, 35);
        this.mDesText.SetPxSize(32);
        this.mCommText.SetPxSize(32);
        this.mNextDisText.SetPxSize(32);
        this.mTotalDisText.SetPxSize(32);
        this.mBtnRepeat = addBtnLong(Can.CAN_FORD_EDGE_XFY, 208, 380, 64, R.string.can_onstar_repeat, 1);
        this.mBtnDesInfo = addBtnLong(CanCameraUI.BTN_LANDWIND_2D3D, 208, 380, 64, R.string.can_onstar_des_info, 5);
        this.mBtnUpdate = addBtnLong(CanCameraUI.BTN_LANDWIND_2D3D, 280, 380, 64, R.string.can_onstar_update, 6);
        this.mBtnCancel = addBtnLong(CanCameraUI.BTN_LANDWIND_2D3D, 350, 380, 64, R.string.can_onstar_cancle, 7);
        this.mBtnPreview = addBtnShort(Can.CAN_FORD_EDGE_XFY, KeyDef.RKEY_POWER, 185, 64, R.string.can_onstar_preview, 2);
        this.mBtnPrevSta = addBtnShort(443, 280, 185, 64, R.string.can_onstar_last_sta, 3);
        this.mBtnNextSta = addBtnShort(443, 350, 185, 64, R.string.can_onstar_next_sta, 4);
    }

    private void initScreen_768x1024() {
        this.mManager.AddImage(0, 0, R.drawable.can_onstar_bg);
        int nY = 0;
        if (CanFunc.getInstance().IsCore() == 1) {
            nY = 35;
        }
        this.mImgDirect = this.mManager.AddImage(23, nY + 43, 116, 117);
        this.mProgress = new CanVerticalBar(this, R.drawable.can_sponstar_pro_dn, R.drawable.can_sponstar_pro_up);
        this.mProgress.setMinMax(0.0f, 100.0f);
        this.mManager.AddViewWrapContent(this.mProgress, 186, nY + 28);
        CustomTextView tv = this.mManager.AddCusText(28, nY + 0, 110, 38);
        tv.setGravity(17);
        tv.setText(R.string.can_onstar_direct);
        tv.SetPxSize(36);
        this.mDesText = addInfoText(255, nY + 22, 500, 35);
        this.mCommText = addInfoText(255, nY + 57, 500, 35);
        this.mNextDisText = addInfoText(255, nY + 105, 500, 35);
        this.mTotalDisText = addInfoText(255, nY + 141, 500, 35);
        this.mBtnRepeat = addBtnLong(20, nY + 204, 354, 64, R.string.can_onstar_repeat, 1);
        this.mBtnDesInfo = addBtnLong(395, nY + 204, 354, 64, R.string.can_onstar_des_info, 5);
        this.mBtnUpdate = addBtnLong(395, nY + 273, 354, 64, R.string.can_onstar_update, 6);
        this.mBtnCancel = addBtnLong(395, nY + 343, 354, 64, R.string.can_onstar_cancle, 7);
        this.mBtnPreview = addBtnShort(20, nY + 309, 174, 64, R.string.can_onstar_preview, 2);
        this.mBtnPrevSta = addBtnShort(200, nY + 273, 174, 64, R.string.can_onstar_last_sta, 3);
        this.mBtnNextSta = addBtnShort(200, nY + 343, 174, 64, R.string.can_onstar_next_sta, 4);
    }

    private void initCommonScreen() {
        this.mManager.AddImage(34, 30, R.drawable.can_onstar_bg);
        this.mImgDirect = this.mManager.AddImage(62, 58, 150, 150);
        this.mProgress = new CanVerticalBar(this, R.drawable.can_onstar_pro_dn, R.drawable.can_onstar_pro_up);
        this.mProgress.setMinMax(0.0f, 100.0f);
        this.mManager.AddViewWrapContent(this.mProgress, 266, 38);
        CustomTextView tv = this.mManager.AddCusText(64, 10, 145, 44);
        tv.setGravity(17);
        tv.setText(R.string.can_onstar_direct);
        tv.SetPxSize(39);
        this.mDesText = addInfoText(KeyDef.RKEY_res2, 36, CanCameraUI.BTN_LANDWIND_2D_RIGHT, 40);
        this.mCommText = addInfoText(KeyDef.RKEY_res2, 82, CanCameraUI.BTN_LANDWIND_2D_RIGHT, 40);
        this.mNextDisText = addInfoText(KeyDef.RKEY_res2, 143, CanCameraUI.BTN_LANDWIND_2D_RIGHT, 40);
        this.mTotalDisText = addInfoText(KeyDef.RKEY_res2, 189, CanCameraUI.BTN_LANDWIND_2D_RIGHT, 40);
        this.mBtnRepeat = addBtnLong(45, 265, R.string.can_onstar_repeat, 1);
        this.mBtnPreview = addBtnShort(45, 392, R.string.can_onstar_preview, 2);
        this.mBtnPrevSta = addBtnShort(284, 352, R.string.can_onstar_last_sta, 3);
        this.mBtnNextSta = addBtnShort(284, 438, R.string.can_onstar_next_sta, 4);
        this.mBtnDesInfo = addBtnLong(CanCameraUI.BTN_GEELY_YJX6_MODE2, 265, R.string.can_onstar_des_info, 5);
        this.mBtnUpdate = addBtnLong(CanCameraUI.BTN_GEELY_YJX6_MODE2, 352, R.string.can_onstar_update, 6);
        this.mBtnCancel = addBtnLong(CanCameraUI.BTN_GEELY_YJX6_MODE2, 438, R.string.can_onstar_cancle, 7);
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
        super.onPause();
    }

    private void ResetData(boolean check) {
        CanJni.GMGetGps(this.mDesData, this.mCommData, this.mTripData);
        if (i2b(this.mDesData.UpdateOnce) && (!check || i2b(this.mDesData.Update))) {
            this.mDesData.Update = 0;
            this.mDesText.setText(String.format("%s: %s", new Object[]{this.mStrDes, CanIF.byte2String(this.mDesData.szInfo)}));
        }
        if (i2b(this.mCommData.UpdateOnce) && (!check || i2b(this.mCommData.Update))) {
            this.mCommData.Update = 0;
            this.mCommText.setText(String.format("%s: %s", new Object[]{this.mStrComm, CanIF.byte2String(this.mCommData.szInfo)}));
        }
        if (!i2b(this.mTripData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTripData.Update)) {
            Log.d(TAG, "JXYLD = " + this.mTripData.JXYLD + ", ZXCJL = " + this.mTripData.ZXCJL);
            this.mTripData.Update = 0;
            if (this.mTripData.Icon > 32 || this.mTripData.Icon < 0) {
                this.mImgDirect.Show(false);
            } else {
                this.mImgDirect.setImageResource(mDirectArr[this.mTripData.Icon]);
                this.mImgDirect.Show(true);
            }
            this.mProgress.setCurPos(this.mTripData.Progress);
            if (16777215 == this.mTripData.JXYLD) {
                this.mNextDisText.setText(String.valueOf(this.mStrNextDis) + ": ");
            } else if (this.mTripData.JXYLD > 1000) {
                this.mNextDisText.setText(String.format("%s: %.3fkm", new Object[]{this.mStrNextDis, Float.valueOf(((float) this.mTripData.JXYLD) / 1000.0f)}));
            } else {
                this.mNextDisText.setText(String.format("%s: %dm", new Object[]{this.mStrNextDis, Integer.valueOf(this.mTripData.JXYLD)}));
            }
            if (16777215 == this.mTripData.ZXCJL) {
                this.mTotalDisText.setText(String.valueOf(this.mStrTotalRange) + ": ");
            } else if (this.mTripData.JXYLD > 1000) {
                this.mTotalDisText.setText(String.format("%s: %.3fkm", new Object[]{this.mStrTotalRange, Float.valueOf(((float) this.mTripData.ZXCJL) / 1000.0f)}));
            } else {
                this.mTotalDisText.setText(String.format("%s: %dm", new Object[]{this.mStrTotalRange, Integer.valueOf(this.mTripData.ZXCJL)}));
            }
        }
    }

    private CustomTextView addInfoText(int x, int y, int w, int h) {
        CustomTextView tv = this.mManager.AddCusText(x, y, w, h);
        tv.setGravity(19);
        tv.SetPxSize(39);
        return tv;
    }

    private ParamButton addBtn(int x, int y, int resTitle, int id) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setTextSize(0, 36.0f);
        btn.setText(resTitle);
        btn.setTag(Integer.valueOf(id));
        btn.setTextColor(-1);
        btn.setOnClickListener(this);
        return btn;
    }

    private ParamButton addBtnLong(int x, int y, int resTitle, int id) {
        ParamButton btn = addBtn(x, y, resTitle, id);
        btn.setStateUpDn(R.drawable.can_onstar_rect_up, R.drawable.can_onstar_rect_dn);
        return btn;
    }

    private ParamButton addBtnShort(int x, int y, int resTitle, int id) {
        ParamButton btn = addBtn(x, y, resTitle, id);
        btn.setStateUpDn(R.drawable.can_onstar_txt_up, R.drawable.can_onstar_txt_dn);
        return btn;
    }

    private ParamButton addBtnLong(int x, int y, int w, int h, int resTitle, int id) {
        ParamButton btn = this.mManager.AddButton(x, y, w, h);
        btn.setTextSize(0, 25.0f);
        btn.setText(resTitle);
        btn.setTag(Integer.valueOf(id));
        btn.setTextColor(-1);
        btn.setOnClickListener(this);
        btn.setStateUpDn(R.drawable.can_onstar_rect_up, R.drawable.can_onstar_rect_dn);
        return btn;
    }

    private ParamButton addBtnShort(int x, int y, int w, int h, int resTitle, int id) {
        ParamButton btn = this.mManager.AddButton(x, y, w, h);
        btn.setTextSize(0, 25.0f);
        btn.setText(resTitle);
        btn.setTag(Integer.valueOf(id));
        btn.setTextColor(-1);
        btn.setOnClickListener(this);
        btn.setStateUpDn(R.drawable.can_onstar_txt_up, R.drawable.can_onstar_txt_dn);
        return btn;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.GMGpsCtrl(0, 0);
                return;
            case 2:
                CanJni.GMGpsCtrl(1, 0);
                return;
            case 3:
                CanJni.GMGpsCtrl(2, 0);
                return;
            case 4:
                CanJni.GMGpsCtrl(3, 0);
                return;
            case 5:
                CanJni.GMGpsCtrl(4, 0);
                return;
            case 6:
                CanJni.GMGpsCtrl(5, 0);
                return;
            case 7:
                CanJni.GMGpsCtrl(6, 0);
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
}
